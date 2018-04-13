package qsbk.app.ad.feedsad;

import android.app.Activity;

public interface AdProvider {
    void exit();

    void fetchAd();

    int getAdCount();

    int getRatio();

    void init(Activity activity, IFeedsAdLoaded iFeedsAdLoaded);

    AdItemData popAd();

    void tryFetchAd();
}
