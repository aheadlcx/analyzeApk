package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class g extends FutureTask<Response> {
    final /* synthetic */ HttpWorker a;
    final /* synthetic */ HttpManager b;

    g(HttpManager httpManager, Callable callable, HttpWorker httpWorker) {
        this.b = httpManager;
        this.a = httpWorker;
        super(callable);
    }

    protected void done() {
        Request request = this.a.getRequest();
        TransportCallback callback = request.getCallback();
        if (callback == null) {
            super.done();
            return;
        }
        try {
            Response response = (Response) get();
            if (isCancelled() || request.isCanceled()) {
                request.cancel();
                if (!(isCancelled() && isDone())) {
                    cancel(false);
                }
                callback.onCancelled(request);
            } else if (response != null) {
                callback.onPostExecute(request, response);
            }
        } catch (InterruptedException e) {
            callback.onFailed(request, 7, String.valueOf(e));
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                callback.onFailed(request, 6, String.valueOf(e2));
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            callback.onFailed(request, httpException.getCode(), httpException.getMsg());
        } catch (CancellationException e3) {
            request.cancel();
            callback.onCancelled(request);
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException("An error occured while executing http request", th);
        }
    }
}
