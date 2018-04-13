package com.meizu.cloud.pushsdk.networking;

import android.content.Context;
import com.meizu.cloud.pushsdk.networking.common.ANLog;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.DeleteRequestBuilder;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.DownloadBuilder;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.GetRequestBuilder;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.HeadRequestBuilder;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.MultiPartBuilder;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.PatchRequestBuilder;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.PostRequestBuilder;
import com.meizu.cloud.pushsdk.networking.common.ANRequest.PutRequestBuilder;
import com.meizu.cloud.pushsdk.networking.common.ConnectionClassManager;
import com.meizu.cloud.pushsdk.networking.common.ConnectionQuality;
import com.meizu.cloud.pushsdk.networking.core.Core;
import com.meizu.cloud.pushsdk.networking.interfaces.ConnectionQualityChangeListener;
import com.meizu.cloud.pushsdk.networking.interfaces.Parser.Factory;
import com.meizu.cloud.pushsdk.networking.internal.ANImageLoader;
import com.meizu.cloud.pushsdk.networking.internal.ANImageLoader.ImageCache;
import com.meizu.cloud.pushsdk.networking.internal.ANRequestQueue;
import com.meizu.cloud.pushsdk.networking.internal.InternalNetworking;

public class AndroidNetworking {
    private AndroidNetworking() {
    }

    public static void initialize(Context context) {
        ANRequestQueue.initialize();
        ANImageLoader.initialize();
    }

    public static void setConnectionQualityChangeListener(ConnectionQualityChangeListener connectionQualityChangeListener) {
        ConnectionClassManager.getInstance().setListener(connectionQualityChangeListener);
    }

    public static void removeConnectionQualityChangeListener() {
        ConnectionClassManager.getInstance().removeListener();
    }

    public static GetRequestBuilder get(String str) {
        return new GetRequestBuilder(str);
    }

    public static HeadRequestBuilder head(String str) {
        return new HeadRequestBuilder(str);
    }

    public static PostRequestBuilder post(String str) {
        return new PostRequestBuilder(str);
    }

    public static PutRequestBuilder put(String str) {
        return new PutRequestBuilder(str);
    }

    public static DeleteRequestBuilder delete(String str) {
        return new DeleteRequestBuilder(str);
    }

    public static PatchRequestBuilder patch(String str) {
        return new PatchRequestBuilder(str);
    }

    public static DownloadBuilder download(String str, String str2, String str3) {
        return new DownloadBuilder(str, str2, str3);
    }

    public static MultiPartBuilder upload(String str) {
        return new MultiPartBuilder(str);
    }

    public static void cancel(Object obj) {
        ANRequestQueue.getInstance().cancelRequestWithGivenTag(obj, false);
    }

    public static void forceCancel(Object obj) {
        ANRequestQueue.getInstance().cancelRequestWithGivenTag(obj, true);
    }

    public static void cancelAll() {
        ANRequestQueue.getInstance().cancelAll(false);
    }

    public static void forceCancelAll() {
        ANRequestQueue.getInstance().cancelAll(true);
    }

    public static void enableLogging() {
        ANLog.enableLogging();
    }

    public static void enableLogging(String str) {
        ANLog.enableLogging();
        ANLog.setTag(str);
    }

    public static void disableLogging() {
        ANLog.disableLogging();
    }

    public static void evictBitmap(String str) {
        ImageCache imageCache = ANImageLoader.getInstance().getImageCache();
        if (imageCache != null && str != null) {
            imageCache.evictBitmap(str);
        }
    }

    public static void evictAllBitmap() {
        ImageCache imageCache = ANImageLoader.getInstance().getImageCache();
        if (imageCache != null) {
            imageCache.evictAllBitmap();
        }
    }

    public static void setUserAgent(String str) {
        InternalNetworking.setUserAgent(str);
    }

    public static int getCurrentBandwidth() {
        return ConnectionClassManager.getInstance().getCurrentBandwidth();
    }

    public static ConnectionQuality getCurrentConnectionQuality() {
        return ConnectionClassManager.getInstance().getCurrentConnectionQuality();
    }

    public static void setParserFactory(Factory factory) {
    }

    public static void shutDown() {
        Core.shutDown();
        evictAllBitmap();
        ConnectionClassManager.getInstance().removeListener();
        ConnectionClassManager.shutDown();
    }
}
