package com.facebook.stetho.inspector.network;

protected class DownloadingAsyncPrettyPrinterFactory$MatchResult {
    private final PrettyPrinterDisplayType mDisplayType;
    private final String mSchemaUri;
    final /* synthetic */ DownloadingAsyncPrettyPrinterFactory this$0;

    public DownloadingAsyncPrettyPrinterFactory$MatchResult(DownloadingAsyncPrettyPrinterFactory downloadingAsyncPrettyPrinterFactory, String str, PrettyPrinterDisplayType prettyPrinterDisplayType) {
        this.this$0 = downloadingAsyncPrettyPrinterFactory;
        this.mSchemaUri = str;
        this.mDisplayType = prettyPrinterDisplayType;
    }

    public String getSchemaUri() {
        return this.mSchemaUri;
    }

    public PrettyPrinterDisplayType getDisplayType() {
        return this.mDisplayType;
    }
}
