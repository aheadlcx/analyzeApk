package com.tencent.smtt.export.external.interfaces;

public class IX5WebViewBase$ImageInfo {
    public String mPicUrl;
    public long mRawDataSize;

    public long getPicSize() {
        return this.mRawDataSize;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
