package com.meizu.cloud.pushsdk.networking.utils;

import com.meizu.cloud.pushsdk.networking.common.ANLog;
import com.meizu.cloud.pushsdk.networking.common.ANRequest;
import com.meizu.cloud.pushsdk.networking.common.ResponseType;
import com.meizu.cloud.pushsdk.networking.http.Response;

public final class SourceCloseUtil {
    private SourceCloseUtil() {
    }

    public static void close(Response response, ANRequest aNRequest) {
        if (aNRequest.getResponseAs() != ResponseType.OK_HTTP_RESPONSE && response != null && response.body() != null && response.body().source() != null) {
            try {
                response.body().source().close();
            } catch (Exception e) {
                ANLog.d("Unable to close source data");
            }
        }
    }
}
