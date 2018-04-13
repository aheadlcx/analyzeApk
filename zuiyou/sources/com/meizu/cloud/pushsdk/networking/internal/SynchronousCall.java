package com.meizu.cloud.pushsdk.networking.internal;

import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import com.meizu.cloud.pushsdk.networking.common.ANRequest;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;
import com.meizu.cloud.pushsdk.networking.utils.Utils;

public final class SynchronousCall {
    private SynchronousCall() {
    }

    public static <T> ANResponse<T> execute(ANRequest aNRequest) {
        switch (aNRequest.getRequestType()) {
            case 0:
                return executeSimpleRequest(aNRequest);
            case 1:
                return executeDownloadRequest(aNRequest);
            case 2:
                return executeUploadRequest(aNRequest);
            default:
                return new ANResponse(new ANError());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> com.meizu.cloud.pushsdk.networking.common.ANResponse<T> executeSimpleRequest(com.meizu.cloud.pushsdk.networking.common.ANRequest r4) {
        /*
        r1 = 0;
        r1 = com.meizu.cloud.pushsdk.networking.internal.InternalNetworking.performSimpleRequest(r4);	 Catch:{ ANError -> 0x0059, Exception -> 0x006e, all -> 0x007e }
        if (r1 != 0) goto L_0x0019;
    L_0x0007:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2 = new com.meizu.cloud.pushsdk.networking.error.ANError;	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2.<init>();	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForConnection(r2);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r0.<init>(r2);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r4.getResponseAs();	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2 = com.meizu.cloud.pushsdk.networking.common.ResponseType.OK_HTTP_RESPONSE;	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        if (r0 != r2) goto L_0x002d;
    L_0x0021:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r0.<init>(r1);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r0.setOkHttpResponse(r1);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        goto L_0x0018;
    L_0x002d:
        r0 = r1.code();	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r0 < r2) goto L_0x004e;
    L_0x0035:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2 = new com.meizu.cloud.pushsdk.networking.error.ANError;	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2.<init>(r1);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r3 = r1.code();	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r2 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForServerResponse(r2, r4, r3);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r0.<init>(r2);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r0.setOkHttpResponse(r1);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        goto L_0x0018;
    L_0x004e:
        r0 = r4.parseResponse(r1);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        r0.setOkHttpResponse(r1);	 Catch:{ ANError -> 0x008a, Exception -> 0x0086, all -> 0x007e }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        goto L_0x0018;
    L_0x0059:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
    L_0x005c:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ all -> 0x0083 }
        r3 = new com.meizu.cloud.pushsdk.networking.error.ANError;	 Catch:{ all -> 0x0083 }
        r3.<init>(r1);	 Catch:{ all -> 0x0083 }
        r1 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForConnection(r3);	 Catch:{ all -> 0x0083 }
        r0.<init>(r1);	 Catch:{ all -> 0x0083 }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r2, r4);
        goto L_0x0018;
    L_0x006e:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
    L_0x0071:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ all -> 0x0083 }
        r1 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForNetworkOnMainThreadOrConnection(r1);	 Catch:{ all -> 0x0083 }
        r0.<init>(r1);	 Catch:{ all -> 0x0083 }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r2, r4);
        goto L_0x0018;
    L_0x007e:
        r0 = move-exception;
    L_0x007f:
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        throw r0;
    L_0x0083:
        r0 = move-exception;
        r1 = r2;
        goto L_0x007f;
    L_0x0086:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x0071;
    L_0x008a:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.networking.internal.SynchronousCall.executeSimpleRequest(com.meizu.cloud.pushsdk.networking.common.ANRequest):com.meizu.cloud.pushsdk.networking.common.ANResponse<T>");
    }

    private static <T> ANResponse<T> executeDownloadRequest(ANRequest aNRequest) {
        try {
            Response performDownloadRequest = InternalNetworking.performDownloadRequest(aNRequest);
            if (performDownloadRequest == null) {
                return new ANResponse(Utils.getErrorForConnection(new ANError()));
            }
            ANResponse<T> aNResponse;
            if (performDownloadRequest.code() >= 400) {
                aNResponse = new ANResponse(Utils.getErrorForServerResponse(new ANError(performDownloadRequest), aNRequest, performDownloadRequest.code()));
                aNResponse.setOkHttpResponse(performDownloadRequest);
                return aNResponse;
            }
            aNResponse = new ANResponse(ANConstants.SUCCESS);
            aNResponse.setOkHttpResponse(performDownloadRequest);
            return aNResponse;
        } catch (Throwable e) {
            return new ANResponse(Utils.getErrorForConnection(new ANError(e)));
        } catch (Exception e2) {
            return new ANResponse(Utils.getErrorForNetworkOnMainThreadOrConnection(e2));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> com.meizu.cloud.pushsdk.networking.common.ANResponse<T> executeUploadRequest(com.meizu.cloud.pushsdk.networking.common.ANRequest r4) {
        /*
        r1 = 0;
        r1 = com.meizu.cloud.pushsdk.networking.internal.InternalNetworking.performUploadRequest(r4);	 Catch:{ ANError -> 0x0059, Exception -> 0x0069, all -> 0x0079 }
        if (r1 != 0) goto L_0x0019;
    L_0x0007:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2 = new com.meizu.cloud.pushsdk.networking.error.ANError;	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2.<init>();	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForConnection(r2);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r0.<init>(r2);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r4.getResponseAs();	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2 = com.meizu.cloud.pushsdk.networking.common.ResponseType.OK_HTTP_RESPONSE;	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        if (r0 != r2) goto L_0x002d;
    L_0x0021:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r0.<init>(r1);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r0.setOkHttpResponse(r1);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        goto L_0x0018;
    L_0x002d:
        r0 = r1.code();	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r0 < r2) goto L_0x004e;
    L_0x0035:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2 = new com.meizu.cloud.pushsdk.networking.error.ANError;	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2.<init>(r1);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r3 = r1.code();	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r2 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForServerResponse(r2, r4, r3);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r0.<init>(r2);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r0.setOkHttpResponse(r1);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        goto L_0x0018;
    L_0x004e:
        r0 = r4.parseResponse(r1);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        r0.setOkHttpResponse(r1);	 Catch:{ ANError -> 0x0085, Exception -> 0x0081, all -> 0x0079 }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        goto L_0x0018;
    L_0x0059:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
    L_0x005c:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ all -> 0x007e }
        r1 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForConnection(r1);	 Catch:{ all -> 0x007e }
        r0.<init>(r1);	 Catch:{ all -> 0x007e }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r2, r4);
        goto L_0x0018;
    L_0x0069:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
    L_0x006c:
        r0 = new com.meizu.cloud.pushsdk.networking.common.ANResponse;	 Catch:{ all -> 0x007e }
        r1 = com.meizu.cloud.pushsdk.networking.utils.Utils.getErrorForNetworkOnMainThreadOrConnection(r1);	 Catch:{ all -> 0x007e }
        r0.<init>(r1);	 Catch:{ all -> 0x007e }
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r2, r4);
        goto L_0x0018;
    L_0x0079:
        r0 = move-exception;
    L_0x007a:
        com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil.close(r1, r4);
        throw r0;
    L_0x007e:
        r0 = move-exception;
        r1 = r2;
        goto L_0x007a;
    L_0x0081:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x006c;
    L_0x0085:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.networking.internal.SynchronousCall.executeUploadRequest(com.meizu.cloud.pushsdk.networking.common.ANRequest):com.meizu.cloud.pushsdk.networking.common.ANResponse<T>");
    }
}
