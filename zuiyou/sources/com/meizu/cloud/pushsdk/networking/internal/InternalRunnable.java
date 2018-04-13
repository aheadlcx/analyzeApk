package com.meizu.cloud.pushsdk.networking.internal;

import com.meizu.cloud.pushsdk.networking.common.ANLog;
import com.meizu.cloud.pushsdk.networking.common.ANRequest;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.common.Priority;
import com.meizu.cloud.pushsdk.networking.common.ResponseType;
import com.meizu.cloud.pushsdk.networking.core.Core;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;
import com.meizu.cloud.pushsdk.networking.utils.SourceCloseUtil;
import com.meizu.cloud.pushsdk.networking.utils.Utils;

public class InternalRunnable implements Runnable {
    private final Priority priority;
    public final ANRequest request;
    public final int sequence;

    public InternalRunnable(ANRequest aNRequest) {
        this.request = aNRequest;
        this.sequence = aNRequest.getSequenceNumber();
        this.priority = aNRequest.getPriority();
    }

    public void run() {
        ANLog.d("execution started : " + this.request.toString());
        switch (this.request.getRequestType()) {
            case 0:
                executeSimpleRequest();
                break;
            case 1:
                executeDownloadRequest();
                break;
            case 2:
                executeUploadRequest();
                break;
        }
        ANLog.d("execution done : " + this.request.toString());
    }

    private void executeSimpleRequest() {
        Response response = null;
        try {
            response = InternalNetworking.performSimpleRequest(this.request);
            if (response == null) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError()));
            } else if (this.request.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                this.request.deliverOkHttpResponse(response);
                SourceCloseUtil.close(response, this.request);
            } else if (response.code() >= 400) {
                deliverError(this.request, Utils.getErrorForServerResponse(new ANError(response), this.request, response.code()));
                SourceCloseUtil.close(response, this.request);
            } else {
                ANResponse parseResponse = this.request.parseResponse(response);
                if (parseResponse.isSuccess()) {
                    parseResponse.setOkHttpResponse(response);
                    this.request.deliverResponse(parseResponse);
                    SourceCloseUtil.close(response, this.request);
                    return;
                }
                deliverError(this.request, parseResponse.getError());
                SourceCloseUtil.close(response, this.request);
            }
        } catch (Throwable e) {
            deliverError(this.request, Utils.getErrorForConnection(new ANError(e)));
        } finally {
            SourceCloseUtil.close(response, this.request);
        }
    }

    private void executeDownloadRequest() {
        try {
            Response performDownloadRequest = InternalNetworking.performDownloadRequest(this.request);
            if (performDownloadRequest == null) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError()));
            } else if (performDownloadRequest.code() >= 400) {
                deliverError(this.request, Utils.getErrorForServerResponse(new ANError(performDownloadRequest), this.request, performDownloadRequest.code()));
            } else {
                this.request.updateDownloadCompletion();
            }
        } catch (Throwable e) {
            deliverError(this.request, Utils.getErrorForConnection(new ANError(e)));
        }
    }

    private void executeUploadRequest() {
        Response response = null;
        try {
            response = InternalNetworking.performUploadRequest(this.request);
            if (response == null) {
                deliverError(this.request, Utils.getErrorForConnection(new ANError()));
            } else if (this.request.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                this.request.deliverOkHttpResponse(response);
                SourceCloseUtil.close(response, this.request);
            } else if (response.code() >= 400) {
                deliverError(this.request, Utils.getErrorForServerResponse(new ANError(response), this.request, response.code()));
                SourceCloseUtil.close(response, this.request);
            } else {
                ANResponse parseResponse = this.request.parseResponse(response);
                if (parseResponse.isSuccess()) {
                    parseResponse.setOkHttpResponse(response);
                    this.request.deliverResponse(parseResponse);
                    SourceCloseUtil.close(response, this.request);
                    return;
                }
                deliverError(this.request, parseResponse.getError());
                SourceCloseUtil.close(response, this.request);
            }
        } catch (Throwable e) {
            deliverError(this.request, Utils.getErrorForConnection(new ANError(e)));
        } finally {
            SourceCloseUtil.close(response, this.request);
        }
    }

    public Priority getPriority() {
        return this.priority;
    }

    private void deliverError(final ANRequest aNRequest, final ANError aNError) {
        Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() {
            public void run() {
                aNRequest.deliverError(aNError);
                aNRequest.finish();
            }
        });
    }
}
