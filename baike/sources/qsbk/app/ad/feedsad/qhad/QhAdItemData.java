package qsbk.app.ad.feedsad.qhad;

import qsbk.app.ad.feedsad.AdItemData;

public class QhAdItemData implements AdItemData {
    private QhAdView adView;

    public QhAdView getAdView() {
        return this.adView;
    }

    public void setAdView(QhAdView qhAdView) {
        this.adView = qhAdView;
    }
}
