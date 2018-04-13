package com.meizu.cloud.pushsdk.networking.internal;

import com.meizu.cloud.pushsdk.networking.common.ANRequest;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Headers;
import com.meizu.cloud.pushsdk.networking.http.HttpURLConnectionCall;
import com.meizu.cloud.pushsdk.networking.http.Request.Builder;
import com.meizu.cloud.pushsdk.networking.http.Response;
import com.meizu.cloud.pushsdk.networking.utils.Utils;
import java.io.File;

public final class InternalNetworking {
    public static String sUserAgent = null;

    private InternalNetworking() {
    }

    public static Response performSimpleRequest(ANRequest aNRequest) throws ANError {
        try {
            Builder url = new Builder().url(aNRequest.getUrl());
            addHeadersToRequestBuilder(url, aNRequest);
            switch (aNRequest.getMethod()) {
                case 0:
                    url = url.get();
                    break;
                case 1:
                    url = url.post(aNRequest.getRequestBody());
                    break;
                case 2:
                    url = url.put(aNRequest.getRequestBody());
                    break;
                case 3:
                    url = url.delete(aNRequest.getRequestBody());
                    break;
                case 4:
                    url = url.head();
                    break;
                case 5:
                    url = url.patch(aNRequest.getRequestBody());
                    break;
            }
            aNRequest.setCall(new HttpURLConnectionCall(url.build()));
            return aNRequest.getCall().execute();
        } catch (Throwable e) {
            throw new ANError(e);
        }
    }

    public static Response performDownloadRequest(ANRequest aNRequest) throws ANError {
        try {
            Builder url = new Builder().url(aNRequest.getUrl());
            addHeadersToRequestBuilder(url, aNRequest);
            aNRequest.setCall(new HttpURLConnectionCall(url.get().build()));
            Response execute = aNRequest.getCall().execute();
            Utils.saveFile(execute, aNRequest.getDirPath(), aNRequest.getFileName());
            return execute;
        } catch (Throwable e) {
            try {
                File file = new File(aNRequest.getDirPath() + File.separator + aNRequest.getFileName());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            throw new ANError(e);
        }
    }

    public static Response performUploadRequest(ANRequest aNRequest) throws ANError {
        try {
            Builder url = new Builder().url(aNRequest.getUrl());
            addHeadersToRequestBuilder(url, aNRequest);
            aNRequest.setCall(new HttpURLConnectionCall(url.post(new RequestProgressBody(aNRequest.getMultiPartRequestBody(), aNRequest.getUploadProgressListener())).build()));
            return aNRequest.getCall().execute();
        } catch (Throwable e) {
            throw new ANError(e);
        }
    }

    public static void addHeadersToRequestBuilder(Builder builder, ANRequest aNRequest) {
        if (aNRequest.getUserAgent() != null) {
            builder.addHeader("User-Agent", aNRequest.getUserAgent());
        } else if (sUserAgent != null) {
            aNRequest.setUserAgent(sUserAgent);
            builder.addHeader("User-Agent", sUserAgent);
        }
        Headers headers = aNRequest.getHeaders();
        if (headers != null) {
            builder.headers(headers);
            if (aNRequest.getUserAgent() != null && !headers.names().contains("User-Agent")) {
                builder.addHeader("User-Agent", aNRequest.getUserAgent());
            }
        }
    }

    public static void setUserAgent(String str) {
        sUserAgent = str;
    }
}
