package com.sprite.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.BookItem;
import com.sprite.ads.nati.loader.AdLoaderListener;
import com.sprite.ads.third.book.BookAdData;
import com.sprite.ads.third.book.BookAdLoader;

public class BookBannerAd extends BannerAdapter {
    private static BookAdLoader adLoader;

    public BookBannerAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    private void show(Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        if (context != null) {
            try {
                if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (bannerADListener != null) {
                    bannerADListener.onNoAD(0);
                    return;
                }
                return;
            }
        }
        BookAdData bookAdData = (BookAdData) adLoader.getNativeAdData();
        if (bookAdData == null) {
            adLoader.loadAd(null, this.mAdItem);
            return;
        }
        BookBannerView bookBannerView = new BookBannerView(adLoader, bookAdData, context, viewGroup, bannerADListener, (BookItem) this.mAdItem);
    }

    public void loadAd(final Context context, final ViewGroup viewGroup, final BannerADListener bannerADListener) {
        if (adLoader == null) {
            adLoader = new BookAdLoader(null, this.mAdItem);
            adLoader.setLoaderListener(new AdLoaderListener() {
                public void loadFailed() {
                    if (bannerADListener != null) {
                        bannerADListener.onNoAD(0);
                    }
                }

                public void loadSuccess() {
                    BookBannerAd.this.show(context, viewGroup, bannerADListener);
                }
            });
            adLoader.loadAd(null, this.mAdItem);
            return;
        }
        show(context, viewGroup, bannerADListener);
    }
}
