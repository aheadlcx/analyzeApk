package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

public abstract class DownloadingAsyncPrettyPrinterFactory implements AsyncPrettyPrinterFactory {
    protected abstract void doPrint(PrintWriter printWriter, InputStream inputStream, String str) throws IOException;

    @Nullable
    protected abstract DownloadingAsyncPrettyPrinterFactory$MatchResult matchAndParseHeader(String str, String str2);

    public AsyncPrettyPrinter getInstance(String str, String str2) {
        DownloadingAsyncPrettyPrinterFactory$MatchResult matchAndParseHeader = matchAndParseHeader(str, str2);
        if (matchAndParseHeader == null) {
            return null;
        }
        URL parseURL = parseURL(matchAndParseHeader.getSchemaUri());
        if (parseURL == null) {
            return getErrorAsyncPrettyPrinter(str, str2);
        }
        ExecutorService executorService = AsyncPrettyPrinterExecutorHolder.getExecutorService();
        if (executorService != null) {
            return new DownloadingAsyncPrettyPrinterFactory$1(this, executorService.submit(new DownloadingAsyncPrettyPrinterFactory$Request(parseURL)), matchAndParseHeader);
        }
        return null;
    }

    @Nullable
    private static URL parseURL(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private static void doErrorPrint(PrintWriter printWriter, InputStream inputStream, String str) throws IOException {
        printWriter.print(str + "\n" + Util.readAsUTF8(inputStream));
    }

    private static AsyncPrettyPrinter getErrorAsyncPrettyPrinter(String str, String str2) {
        return new DownloadingAsyncPrettyPrinterFactory$2(str, str2);
    }
}
