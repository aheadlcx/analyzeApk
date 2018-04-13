package qsbk.app.ad.feedsad.baiduad;

import qsbk.app.ad.feedsad.AdItemData;

public class BaiduAdItemData implements AdItemData {
    private BaiduAdView mBaiduAdView;

    public BaiduAdView getView() {
        return this.mBaiduAdView;
    }

    public void setView(BaiduAdView baiduAdView) {
        this.mBaiduAdView = baiduAdView;
    }
}
