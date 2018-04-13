package com.ishumei.smantifraud;

import android.content.Context;
import android.text.TextUtils;
import com.ishumei.a.c;
import com.ishumei.a.f;
import com.ishumei.dfp.SMSDK;
import com.ishumei.e.b.b;
import com.ishumei.f.d;
import com.ishumei.f.e;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SmAntiFraud {
    public static final String SDK_VERSION = "236";
    public static final int SM_AF_ASYN_MODE = 1;
    public static final int SM_AF_SUCCESS = 0;
    public static final int SM_AF_SYN_MODE = 0;
    public static final int SM_AF_UNINIT = 1;
    private static final String TAG = "SmAntiFraud";
    static final b baseResHandler = new AnonymousClass1(true, 2);
    static final a baseTrasport = new a(new a() {
        public final String a() {
            int i = 0;
            int i2 = SmAntiFraud.option.needUsingMD5() ? 1 : 0;
            if (SmAntiFraud.cloudConfiguration.b().c()) {
                i = 2;
            }
            return SmAntiFraud.assembleJsonData(SmAntiFraud.getXXXJsonInfo(com.ishumei.a.a.a(), i2 | i), true);
        }
    }, true, 1, baseResHandler, bitag);
    static String bitag;
    static String citag;
    static final com.ishumei.c.b cloudConfHandler = new AnonymousClass3(true, 1);
    public static com.ishumei.b.a cloudConfiguration;
    static final b coreResHandler = new AnonymousClass2(false, 2);
    static final a coreTransport = new a(new a() {
        public final String a() {
            int i = 0;
            int i2 = SmAntiFraud.option.needUsingMD5() ? 1 : 0;
            if (SmAntiFraud.cloudConfiguration.b().c()) {
                i = 2;
            }
            return SmAntiFraud.assembleJsonData(SmAntiFraud.getXXXJsonInfo(com.ishumei.a.b.a(), i2 | i), true);
        }
    }, true, 1, coreResHandler, citag);
    static final b financeResHandler = new AnonymousClass6(true, 2);
    static final a financeTrasport = new a(new a() {
        public final String a() {
            return SmAntiFraud.assembleJsonData(SmAntiFraud.getXXXJsonInfo(c.a(), 0), false);
        }
    }, true, 1, financeResHandler, fitag);
    static String fitag;
    public static com.ishumei.e.a httpConfiguration;
    private static int initStatus = 1;
    private static boolean isInited = false;
    private static IServerSmidCallback mServerIdCallback = null;
    public static SmOption option;

    /* renamed from: com.ishumei.smantifraud.SmAntiFraud$1 */
    final class AnonymousClass1 extends b<Object> {
        AnonymousClass1(boolean z, int i) {
            super(z, i);
        }

        public final void a(String str) {
            f.a().a(SmAntiFraud.obtainDeviceId(str));
        }
    }

    /* renamed from: com.ishumei.smantifraud.SmAntiFraud$2 */
    final class AnonymousClass2 extends b<Object> {
        AnonymousClass2(boolean z, int i) {
            super(z, i);
        }

        public final void a(String str) {
            f.a().a(SmAntiFraud.obtainDeviceId(str));
            synchronized (SmAntiFraud.class) {
                if (SmAntiFraud.mServerIdCallback != null) {
                    SmAntiFraud.mServerIdCallback.onReceive(SmAntiFraud.obtainDeviceId(str), 0);
                }
            }
        }

        public final boolean a(String str, int i) {
            boolean a = super.a(str, i);
            if (a) {
                SmAntiFraud.mServerIdCallback.onReceive(str, i);
            }
            return a;
        }
    }

    /* renamed from: com.ishumei.smantifraud.SmAntiFraud$3 */
    final class AnonymousClass3 extends com.ishumei.c.b {
        AnonymousClass3(boolean z, int i) {
            super(z, i);
        }

        public final void run() {
            try {
                if (SmAntiFraud.option.isTransport()) {
                    SmAntiFraud.cloudConfiguration.a(SmAntiFraud.httpConfiguration);
                }
                SmAntiFraud.baseTrasport.b();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.ishumei.smantifraud.SmAntiFraud$6 */
    final class AnonymousClass6 extends b<Object> {
        AnonymousClass6(boolean z, int i) {
            super(z, i);
        }

        public final void a(String str) {
        }
    }

    public interface IServerSmidCallback {
        void onReceive(String str, int i);
    }

    public static class SmOption {
        private IServerSmidCallback callback;
        private String channel;
        private String confUrl;
        private boolean encrypt;
        private int httpType;
        private String organization;
        private String privk;
        private boolean synMode;
        private String traceUrl;
        private boolean transport;
        private String url;
        private boolean usingMD5;

        public SmOption() {
            this.synMode = false;
            this.organization = "";
            this.channel = "";
            this.privk = "";
            this.transport = true;
            this.encrypt = true;
            this.url = null;
            this.confUrl = null;
            this.traceUrl = null;
            this.usingMD5 = false;
            this.httpType = 1;
            this.callback = null;
            this.url = "http://fp-bj.fengkongcloud.com/v2/device/profile";
            this.confUrl = "http://cloudconf.fengkongcloud.com/v2/device/conf";
            this.traceUrl = "http://tracker.fengkongcloud.com/exception?os=android";
        }

        public String getChannel() {
            return this.channel;
        }

        public String getConfUrl() {
            return this.confUrl;
        }

        public int getHttpType() {
            return this.httpType;
        }

        public String getOrganization() {
            return this.organization;
        }

        public String getPrivKey() {
            return this.privk;
        }

        public IServerSmidCallback getServerIdCallback() {
            return this.callback;
        }

        public String getTraceUrl() {
            return this.traceUrl;
        }

        public String getUrl() {
            return this.url;
        }

        public boolean isSynMode() {
            return this.synMode;
        }

        public boolean isTransport() {
            return this.transport;
        }

        public boolean needEncrypt() {
            return this.encrypt;
        }

        public boolean needUsingMD5() {
            return this.usingMD5;
        }

        public void setChannel(String str) {
            this.channel = str;
        }

        public void setConfUrl(String str) {
            this.confUrl = str;
        }

        public void setEncrypt(boolean z) {
            this.encrypt = z;
        }

        public void setHttpType(int i) {
            this.httpType = i;
        }

        public void setOrganization(String str) {
            this.organization = str;
        }

        public void setPrivKey(String str) {
            this.privk = str;
        }

        public void setServerIdCallback(IServerSmidCallback iServerSmidCallback) {
            this.callback = iServerSmidCallback;
        }

        public void setSynMode(boolean z) {
            this.synMode = z;
        }

        public void setTraceUrl(String str) {
            this.traceUrl = str;
        }

        public void setTransport(boolean z) {
            this.transport = z;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void setUsingMD5(boolean z) {
            this.usingMD5 = z;
        }
    }

    public static class a {
        boolean a = false;
        int b = 0;
        String c = null;
        b<?> d = null;
        private String e = null;
        private a f = null;

        public interface a {
            String a();
        }

        a(a aVar, boolean z, int i, b<?> bVar, String str) {
            this.f = aVar;
            this.a = z;
            this.b = i;
            this.d = bVar;
            this.c = str;
        }

        public void a() {
            a(false);
        }

        public void a(boolean z) {
            if (SmAntiFraud.initStatus != 0) {
                throw new Exception("init failed");
            } else if (SmAntiFraud.option.isTransport()) {
                try {
                    new com.ishumei.c.b(this, z, this.b, false, 0, false) {
                        final /* synthetic */ a a;

                        public void run() {
                            try {
                                if (this.a.f != null) {
                                    this.a.e = this.a.f.a();
                                }
                                new com.ishumei.e.b().a(SmAntiFraud.httpConfiguration).a(this.a.e.getBytes("utf-8"), null, this.a.d);
                            } catch (Exception e) {
                            }
                        }
                    }.a();
                } catch (Exception e) {
                }
            }
        }

        public void b() {
            a(this.a);
        }
    }

    static {
        citag = "";
        bitag = "";
        fitag = "";
        try {
            citag = d.g("9c908d9adf96919990");
            bitag = d.g("9c908d9adf96919990");
            fitag = d.g("9c908d9adf96919990");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String assembleJsonData(String str, boolean z) {
        return assembleJsonData(str, z, false);
    }

    private static String assembleJsonData(String str, boolean z, boolean z2) {
        String str2;
        try {
            Map hashMap;
            Map hashMap2;
            if (!option.needEncrypt() || z2) {
                str2 = str;
                hashMap = new HashMap();
                if (z && d.a(str2)) {
                    throw new IOException();
                }
                if (d.a(str2)) {
                    hashMap.put(d.g("999691989a8d8f8d96918b"), str2);
                    if (TextUtils.isEmpty(option.getPrivKey())) {
                        hashMap.put(d.g("998fba919c909b9a"), Integer.valueOf(5));
                    } else {
                        hashMap.put(d.g("998fba919c909b9a"), Integer.valueOf(3));
                    }
                } else {
                    hashMap.put(d.g("999691989a8d8f8d96918b"), str);
                }
                hashMap.put(d.g("8c9a8c8c969091b69b"), String.valueOf(System.currentTimeMillis()));
                hashMap2 = new HashMap();
                hashMap2.put(d.g("908d989e9196859e8b969091"), option.getOrganization());
                hashMap2.put(d.g("9b9e8b9e"), hashMap);
                hashMap2.put(d.g("9c979e91919a93"), option.getChannel());
                hashMap2.put(d.g("9a919c8d868f8b"), Integer.valueOf(option.needEncrypt() ? 1 : 0));
                return e.a(hashMap2).toString();
            }
            str2 = SMSDK.x1(option.getOrganization(), option.getPrivKey() == null ? "" : option.getPrivKey(), str);
            hashMap = new HashMap();
            if (z) {
            }
            if (d.a(str2)) {
                hashMap.put(d.g("999691989a8d8f8d96918b"), str2);
                if (TextUtils.isEmpty(option.getPrivKey())) {
                    hashMap.put(d.g("998fba919c909b9a"), Integer.valueOf(5));
                } else {
                    hashMap.put(d.g("998fba919c909b9a"), Integer.valueOf(3));
                }
            } else {
                hashMap.put(d.g("999691989a8d8f8d96918b"), str);
            }
            hashMap.put(d.g("8c9a8c8c969091b69b"), String.valueOf(System.currentTimeMillis()));
            hashMap2 = new HashMap();
            hashMap2.put(d.g("908d989e9196859e8b969091"), option.getOrganization());
            hashMap2.put(d.g("9b9e8b9e"), hashMap);
            hashMap2.put(d.g("9c979e91919a93"), option.getChannel());
            if (option.needEncrypt()) {
            }
            hashMap2.put(d.g("9a919c8d868f8b"), Integer.valueOf(option.needEncrypt() ? 1 : 0));
            return e.a(hashMap2).toString();
        } catch (Throwable e) {
            if (z) {
                str2 = null;
            } else {
                throw new IOException(e);
            }
        } catch (Exception e2) {
            return "";
        }
    }

    public static boolean cleanSmid() {
        return f.a().e();
    }

    public static void create(Context context, SmOption smOption) {
        Context applicationContext = context.getApplicationContext();
        com.ishumei.b.d.a = applicationContext;
        if (applicationContext != null) {
            com.ishumei.b.c.a(smOption.transport);
            try {
                if (d.a(unsafeCreate(smOption))) {
                    com.ishumei.b.c.a(new Exception(d.g("9c8d9a9e8b9adf8d9a8b8a8d91df8c92969bdf9a928f8b86")));
                }
            } catch (Throwable e) {
                com.ishumei.b.c.a(e);
                com.ishumei.f.c.a(e);
            }
        }
    }

    public static String getBase(int i) {
        if (i != 0 && 1 != i) {
            return "";
        }
        if (i == 0) {
            try {
                baseTrasport.a();
            } catch (Exception e) {
                return "";
            }
        }
        baseTrasport.b();
        return getBaseSyn();
    }

    public static String getBaseSyn() {
        return getBaseSyn(false);
    }

    public static String getBaseSyn(boolean z) {
        return getXXXJsonInfo(com.ishumei.a.a.a(), 0);
    }

    public static String getContact(int i) {
        if (i != 0 && 1 != i) {
            return "";
        }
        if (i == 0) {
            try {
                financeTrasport.a();
            } catch (Throwable e) {
                com.ishumei.b.c.a(e);
                return "";
            }
        }
        financeTrasport.b();
        return getContactSyn();
    }

    public static String getContactSyn() {
        return getContactSyn(false);
    }

    public static String getContactSyn(boolean z) {
        boolean z2 = false;
        try {
            String xXXJsonInfo = getXXXJsonInfo(c.a(), 0);
            if (!z) {
                z2 = true;
            }
            return assembleJsonData(xXXJsonInfo, false, z2);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getCore() {
        return getCore(false);
    }

    public static String getCore(boolean z) {
        boolean z2 = false;
        String xXXJsonInfo = getXXXJsonInfo(com.ishumei.a.b.a(), 0);
        if (!z) {
            z2 = true;
        }
        return assembleJsonData(xXXJsonInfo, true, z2);
    }

    public static String getDeviceId() {
        return f.a().c();
    }

    public static String getXXXJsonInfo(com.ishumei.a.d dVar, int i) {
        try {
            return e.a(dVar.a(i)).toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static void handleCoreResponse(String str) {
        f.a().a(obtainDeviceId(str));
        synchronized (SmAntiFraud.class) {
            if (mServerIdCallback != null) {
                mServerIdCallback.onReceive(obtainDeviceId(str), 0);
            }
        }
    }

    private static void init(SmOption smOption) {
        if (smOption == null) {
            throw new Exception("option null");
        }
        option = smOption;
        if (d.a(smOption.getOrganization())) {
            throw new Exception("organization empty");
        }
        com.ishumei.c.a.b().c();
        com.ishumei.b.c.b(option.getOrganization());
        com.ishumei.b.c.a(smOption.getTraceUrl());
        cloudConfiguration = new com.ishumei.b.a(option.getOrganization(), smOption.getConfUrl());
        com.ishumei.e.a aVar = new com.ishumei.e.a();
        httpConfiguration = aVar;
        aVar.a();
        if (option.getUrl().startsWith("https://")) {
            httpConfiguration.a(0);
        } else {
            httpConfiguration.a(option.getHttpType());
        }
        httpConfiguration.a(option.getUrl());
        if (option.getServerIdCallback() != null) {
            mServerIdCallback = option.getServerIdCallback();
        }
    }

    private static String obtainDeviceId(String str) {
        try {
            String string = new JSONObject(str).getJSONObject(d.g("9b9a8b9e9693")).getString(d.g("8c969b"));
            if (string != null) {
                return string;
            }
            throw new IOException();
        } catch (Exception e) {
            return "";
        }
    }

    public static synchronized void registerServerIdCallback(IServerSmidCallback iServerSmidCallback) {
        synchronized (SmAntiFraud.class) {
            mServerIdCallback = iServerSmidCallback;
        }
    }

    public static void setBaseResponse(String str) {
        String obtainDeviceId = obtainDeviceId(str);
        if (!TextUtils.isEmpty(obtainDeviceId)) {
            f.a().a(obtainDeviceId);
        }
    }

    public static boolean setCloudConfigWithStr(String str) {
        boolean z;
        synchronized (SmAntiFraud.class) {
            if (cloudConfiguration == null || d.a(str)) {
                z = false;
            } else {
                z = cloudConfiguration.a(str);
            }
        }
        return z;
    }

    public static boolean setDeviceIdWithStr(String str) {
        if (d.a(str)) {
            return false;
        }
        synchronized (SmAntiFraud.class) {
            f.a().a(str);
        }
        return true;
    }

    public static String unsafeCreate(SmOption smOption) {
        if (!isInited) {
            synchronized (SmAntiFraud.class) {
                if (!isInited) {
                    isInited = true;
                    init(smOption);
                    initStatus = 0;
                }
            }
        }
        if (initStatus != 0) {
            throw new IOException();
        }
        com.ishumei.f.a aVar = new com.ishumei.f.a();
        aVar.a();
        String c = f.a().c();
        if (c == null || c.isEmpty()) {
            c = f.a().f();
            if (d.a(c)) {
                throw new Exception();
            }
            f.a().a(c);
        }
        aVar.a();
        if (SMSDK.idType(c) != 1) {
            coreTransport.b();
        } else if (mServerIdCallback != null) {
            synchronized (SmAntiFraud.class) {
                mServerIdCallback.onReceive(c, 0);
            }
        }
        cloudConfHandler.a();
        com.ishumei.f.c.a(TAG, "unsafeCreate finish.");
        return f.a().c();
    }
}
