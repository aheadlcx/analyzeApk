package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.ExceptionUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class DownloadingAsyncPrettyPrinterFactory$1 implements AsyncPrettyPrinter {
    final /* synthetic */ DownloadingAsyncPrettyPrinterFactory this$0;
    final /* synthetic */ Future val$response;
    final /* synthetic */ DownloadingAsyncPrettyPrinterFactory$MatchResult val$result;

    DownloadingAsyncPrettyPrinterFactory$1(DownloadingAsyncPrettyPrinterFactory downloadingAsyncPrettyPrinterFactory, Future future, DownloadingAsyncPrettyPrinterFactory$MatchResult downloadingAsyncPrettyPrinterFactory$MatchResult) {
        this.this$0 = downloadingAsyncPrettyPrinterFactory;
        this.val$response = future;
        this.val$result = downloadingAsyncPrettyPrinterFactory$MatchResult;
    }

    public void printTo(PrintWriter printWriter, InputStream inputStream) throws IOException {
        try {
            this.this$0.doPrint(printWriter, inputStream, (String) this.val$response.get());
        } catch (ExecutionException e) {
            if (IOException.class.isInstance(e.getCause())) {
                DownloadingAsyncPrettyPrinterFactory.access$000(printWriter, inputStream, "Cannot successfully download schema: " + e.getMessage());
                return;
            }
            throw e;
        } catch (InterruptedException e2) {
            DownloadingAsyncPrettyPrinterFactory.access$000(printWriter, inputStream, "Encountered spurious interrupt while downloading schema for pretty printing: " + e2.getMessage());
        } catch (ExecutionException e3) {
            throw ExceptionUtil.propagate(e3.getCause());
        }
    }

    public PrettyPrinterDisplayType getPrettifiedType() {
        return this.val$result.getDisplayType();
    }
}
