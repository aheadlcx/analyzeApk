package com.google.analytics.tracking.android;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.Command;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

class GAThread extends Thread implements AnalyticsThread {
    static final String API_VERSION = "1";
    private static final String CLIENT_VERSION = "ma3.0.2";
    private static final int MAX_SAMPLE_RATE = 100;
    private static final int SAMPLE_RATE_MODULO = 10000;
    private static final int SAMPLE_RATE_MULTIPLIER = 100;
    private static GAThread sInstance;
    private volatile String mClientId;
    private volatile boolean mClosed = false;
    private volatile List<Command> mCommands;
    private final Context mContext;
    private volatile boolean mDisabled = false;
    private volatile String mInstallCampaign;
    private volatile ServiceProxy mServiceProxy;
    private final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue();

    static GAThread getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GAThread(context);
        }
        return sInstance;
    }

    private GAThread(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    @VisibleForTesting
    GAThread(Context context, ServiceProxy serviceProxy) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        this.mServiceProxy = serviceProxy;
        start();
    }

    @VisibleForTesting
    protected void init() {
        this.mServiceProxy.createService();
        this.mCommands = new ArrayList();
        this.mCommands.add(new Command(Command.APPEND_VERSION, "&_v".substring(1), CLIENT_VERSION));
        this.mCommands.add(new Command(Command.APPEND_QUEUE_TIME, "&qt".substring(1), null));
        this.mCommands.add(new Command(Command.APPEND_CACHE_BUSTER, "&z".substring(1), null));
    }

    public void sendHit(Map<String, String> map) {
        final Map hashMap = new HashMap(map);
        String str = (String) map.get("&ht");
        if (str != null) {
            try {
                Long.valueOf(str).longValue();
            } catch (NumberFormatException e) {
                str = null;
            }
        }
        if (str == null) {
            hashMap.put("&ht", Long.toString(System.currentTimeMillis()));
        }
        queueToThread(new Runnable() {
            public void run() {
                if (TextUtils.isEmpty((CharSequence) hashMap.get(Fields.CLIENT_ID))) {
                    hashMap.put(Fields.CLIENT_ID, GAThread.this.mClientId);
                }
                if (!GoogleAnalytics.getInstance(GAThread.this.mContext).getAppOptOut() && !GAThread.this.isSampledOut(hashMap)) {
                    if (!TextUtils.isEmpty(GAThread.this.mInstallCampaign)) {
                        GAUsage.getInstance().setDisableUsage(true);
                        hashMap.putAll(new MapBuilder().setCampaignParamsFromUrl(GAThread.this.mInstallCampaign).build());
                        GAUsage.getInstance().setDisableUsage(false);
                        GAThread.this.mInstallCampaign = null;
                    }
                    GAThread.this.fillAppParameters(hashMap);
                    GAThread.this.mServiceProxy.putHit(HitBuilder.generateHitParams(hashMap), Long.valueOf((String) hashMap.get("&ht")).longValue(), GAThread.this.getUrlScheme(hashMap), GAThread.this.mCommands);
                }
            }
        });
    }

    private String getUrlScheme(Map<String, String> map) {
        if (map.containsKey(Fields.USE_SECURE)) {
            return Utils.safeParseBoolean((String) map.get(Fields.USE_SECURE), true) ? "https:" : "http:";
        } else {
            return "https:";
        }
    }

    private boolean isSampledOut(Map<String, String> map) {
        if (map.get(Fields.SAMPLE_RATE) == null) {
            return false;
        }
        double safeParseDouble = Utils.safeParseDouble((String) map.get(Fields.SAMPLE_RATE), 100.0d);
        if (safeParseDouble >= 100.0d) {
            return false;
        }
        if (((double) (hashClientIdForSampling((String) map.get(Fields.CLIENT_ID)) % 10000)) < safeParseDouble * 100.0d) {
            return false;
        }
        String str = map.get(Fields.HIT_TYPE) == null ? "unknown" : (String) map.get(Fields.HIT_TYPE);
        Log.v(String.format("%s hit sampled out", new Object[]{str}));
        return true;
    }

    @VisibleForTesting
    static int hashClientIdForSampling(String str) {
        int i = 1;
        if (!TextUtils.isEmpty(str)) {
            i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                i = (((i << 6) & 268435455) + charAt) + (charAt << 14);
                int i2 = 266338304 & i;
                if (i2 != 0) {
                    i ^= i2 >> 21;
                }
            }
        }
        return i;
    }

    private void fillAppParameters(Map<String, String> map) {
        DefaultProvider provider = AppFieldsDefaultProvider.getProvider();
        Utils.putIfAbsent(map, Fields.APP_NAME, provider.getValue(Fields.APP_NAME));
        Utils.putIfAbsent(map, Fields.APP_VERSION, provider.getValue(Fields.APP_VERSION));
        Utils.putIfAbsent(map, Fields.APP_ID, provider.getValue(Fields.APP_ID));
        Utils.putIfAbsent(map, Fields.APP_INSTALLER_ID, provider.getValue(Fields.APP_INSTALLER_ID));
        map.put("&v", "1");
    }

    public void dispatch() {
        queueToThread(new Runnable() {
            public void run() {
                GAThread.this.mServiceProxy.dispatch();
            }
        });
    }

    public void clearHits() {
        queueToThread(new Runnable() {
            public void run() {
                GAThread.this.mServiceProxy.clearHits();
            }
        });
    }

    public void setForceLocalDispatch() {
        queueToThread(new Runnable() {
            public void run() {
                GAThread.this.mServiceProxy.setForceLocalDispatch();
            }
        });
    }

    @VisibleForTesting
    void queueToThread(Runnable runnable) {
        this.queue.add(runnable);
    }

    @VisibleForTesting
    static String getAndClearCampaign(Context context) {
        try {
            FileInputStream openFileInput = context.openFileInput("gaInstallData");
            byte[] bArr = new byte[8192];
            int read = openFileInput.read(bArr, 0, 8192);
            if (openFileInput.available() > 0) {
                Log.e("Too much campaign data, ignoring it.");
                openFileInput.close();
                context.deleteFile("gaInstallData");
                return null;
            }
            openFileInput.close();
            context.deleteFile("gaInstallData");
            if (read <= 0) {
                Log.w("Campaign file is empty.");
                return null;
            }
            String str = new String(bArr, 0, read);
            Log.i("Campaign found: " + str);
            return str;
        } catch (FileNotFoundException e) {
            Log.i("No campaign data found.");
            return null;
        } catch (IOException e2) {
            Log.e("Error reading campaign data.");
            context.deleteFile("gaInstallData");
            return null;
        }
    }

    private String printStackTrace(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Log.w("sleep interrupted in GAThread initialize");
        }
        try {
            if (this.mServiceProxy == null) {
                this.mServiceProxy = new GAServiceProxy(this.mContext, this);
            }
            init();
            this.mClientId = ClientIdDefaultProvider.getProvider().getValue(Fields.CLIENT_ID);
            this.mInstallCampaign = getAndClearCampaign(this.mContext);
        } catch (Throwable th) {
            Log.e("Error initializing the GAThread: " + printStackTrace(th));
            Log.e("Google Analytics will not start up.");
            this.mDisabled = true;
        }
        while (!this.mClosed) {
            try {
                Runnable runnable = (Runnable) this.queue.take();
                if (!this.mDisabled) {
                    runnable.run();
                }
            } catch (InterruptedException e2) {
                Log.i(e2.toString());
            } catch (Throwable th2) {
                Log.e("Error on GAThread: " + printStackTrace(th2));
                Log.e("Google Analytics is shutting down.");
                this.mDisabled = true;
            }
        }
    }

    public LinkedBlockingQueue<Runnable> getQueue() {
        return this.queue;
    }

    public Thread getThread() {
        return this;
    }

    @VisibleForTesting
    void close() {
        this.mClosed = true;
        interrupt();
    }

    @VisibleForTesting
    boolean isDisabled() {
        return this.mDisabled;
    }
}
