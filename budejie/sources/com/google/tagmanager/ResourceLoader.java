package com.google.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.analytics.containertag.proto.Serving.SupplementedResource;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.LoadCallback.Failure;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

class ResourceLoader implements Runnable {
    private static final String CTFE_URL_PREFIX = "/r?id=";
    private static final String CTFE_URL_SUFFIX = "&v=a62676326";
    private static final String PREVIOUS_CONTAINER_VERSION_QUERY_NAME = "pv";
    @VisibleForTesting
    static final String SDK_VERSION = "a62676326";
    private LoadCallback<SupplementedResource> mCallback;
    private final NetworkClientFactory mClientFactory;
    private final String mContainerId;
    private final Context mContext;
    private volatile CtfeHost mCtfeHost;
    private volatile String mCtfeUrlPathAndQuery;
    private final String mDefaultCtfeUrlPathAndQuery;
    private volatile String mPreviousVersion;

    public ResourceLoader(Context context, String str, CtfeHost ctfeHost) {
        this(context, str, new NetworkClientFactory(), ctfeHost);
    }

    @VisibleForTesting
    ResourceLoader(Context context, String str, NetworkClientFactory networkClientFactory, CtfeHost ctfeHost) {
        this.mContext = context;
        this.mClientFactory = networkClientFactory;
        this.mContainerId = str;
        this.mCtfeHost = ctfeHost;
        this.mDefaultCtfeUrlPathAndQuery = CTFE_URL_PREFIX + str;
        this.mCtfeUrlPathAndQuery = this.mDefaultCtfeUrlPathAndQuery;
        this.mPreviousVersion = null;
    }

    public void run() {
        if (this.mCallback == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.mCallback.startLoad();
        loadResource();
    }

    private boolean okToLoad() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        Log.v("...no network connectivity");
        return false;
    }

    void setLoadCallback(LoadCallback<SupplementedResource> loadCallback) {
        this.mCallback = loadCallback;
    }

    private void loadResource() {
        if (okToLoad()) {
            Log.v("Start loading resource from network ...");
            String ctfeUrl = getCtfeUrl();
            NetworkClient createNetworkClient = this.mClientFactory.createNetworkClient();
            try {
                InputStream inputStream = createNetworkClient.getInputStream(ctfeUrl);
                try {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ResourceUtil.copyStream(inputStream, byteArrayOutputStream);
                    SupplementedResource parseFrom = SupplementedResource.parseFrom(byteArrayOutputStream.toByteArray());
                    Log.v("Successfully loaded supplemented resource: " + parseFrom);
                    if (parseFrom.resource == null) {
                        Log.v("No change for container: " + this.mContainerId);
                    }
                    this.mCallback.onSuccess(parseFrom);
                    Log.v("Load resource from network finished.");
                } catch (Throwable e) {
                    Log.w("Error when parsing downloaded resources from url: " + ctfeUrl + " " + e.getMessage(), e);
                    this.mCallback.onFailure(Failure.SERVER_ERROR);
                    createNetworkClient.close();
                }
            } catch (FileNotFoundException e2) {
                Log.w("No data is retrieved from the given url: " + ctfeUrl + ". Make sure container_id: " + this.mContainerId + " is correct.");
                this.mCallback.onFailure(Failure.SERVER_ERROR);
            } catch (Throwable e3) {
                Log.w("Error when loading resources from url: " + ctfeUrl + " " + e3.getMessage(), e3);
                this.mCallback.onFailure(Failure.IO_ERROR);
            } finally {
                createNetworkClient.close();
            }
        } else {
            this.mCallback.onFailure(Failure.NOT_AVAILABLE);
        }
    }

    @VisibleForTesting
    String getCtfeUrl() {
        String str = this.mCtfeHost.getCtfeServerAddress() + this.mCtfeUrlPathAndQuery + CTFE_URL_SUFFIX;
        if (!(this.mPreviousVersion == null || this.mPreviousVersion.trim().equals(""))) {
            str = str + "&pv=" + this.mPreviousVersion;
        }
        if (PreviewManager.getInstance().getPreviewMode().equals(PreviewMode.CONTAINER_DEBUG)) {
            return str + "&gtm_debug=x";
        }
        return str;
    }

    @VisibleForTesting
    void setCtfeURLPathAndQuery(String str) {
        if (str == null) {
            this.mCtfeUrlPathAndQuery = this.mDefaultCtfeUrlPathAndQuery;
            return;
        }
        Log.d("Setting CTFE URL path: " + str);
        this.mCtfeUrlPathAndQuery = str;
    }

    @VisibleForTesting
    void setPreviousVersion(String str) {
        Log.d("Setting previous container version: " + str);
        this.mPreviousVersion = str;
    }
}
