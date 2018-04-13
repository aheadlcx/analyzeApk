package com.facebook.stetho.inspector.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

class DownloadingAsyncPrettyPrinterFactory$2 implements AsyncPrettyPrinter {
    final /* synthetic */ String val$headerName;
    final /* synthetic */ String val$headerValue;

    DownloadingAsyncPrettyPrinterFactory$2(String str, String str2) {
        this.val$headerName = str;
        this.val$headerValue = str2;
    }

    public void printTo(PrintWriter printWriter, InputStream inputStream) throws IOException {
        DownloadingAsyncPrettyPrinterFactory.access$000(printWriter, inputStream, "[Failed to parse header: " + this.val$headerName + " : " + this.val$headerValue + " ]");
    }

    public PrettyPrinterDisplayType getPrettifiedType() {
        return PrettyPrinterDisplayType.TEXT;
    }
}
