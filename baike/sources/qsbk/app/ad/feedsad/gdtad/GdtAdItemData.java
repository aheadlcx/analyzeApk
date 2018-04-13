package qsbk.app.ad.feedsad.gdtad;

import qsbk.app.ad.feedsad.AdItemData;

public class GdtAdItemData implements AdItemData {
    private GdtAdView mAdView;

    public GdtAdView getView() {
        return this.mAdView;
    }

    public void setView(GdtAdView gdtAdView) {
        this.mAdView = gdtAdView;
    }
}
