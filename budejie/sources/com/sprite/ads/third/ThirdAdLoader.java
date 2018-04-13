package com.sprite.ads.third;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.a.a;
import com.sprite.ads.nati.a.b;
import com.sprite.ads.nati.loader.AdLoaderListener;
import com.sprite.ads.nati.loader.NativeAdLoader;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.nati.reporter.SelfReporter;
import com.sprite.ads.nati.view.NativeAdDataLoadedListener;
import com.sprite.ads.third.ThirdNativeAdPool.RefreshListener;
import java.util.List;

public abstract class ThirdAdLoader implements NativeAdLoader {
    private ThirdItem adItem;
    private Context context;
    private boolean isInit = false;
    private boolean isLoadAd = false;
    private AdLoaderListener mLoaderListener;
    private ThirdNativeAdPool mPool = new ThirdNativeAdPool();
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private RefreshTask refreshTask = new RefreshTask() {
        public void refreshData() {
            ADLog.d("refreshData: 定时" + ThirdAdLoader.this.adItem);
            ThirdAdLoader.this.loadAd(ThirdAdLoader.this.context, ThirdAdLoader.this.adItem);
        }
    };

    public ThirdAdLoader(final Context context, final AdItem adItem) {
        this.context = context;
        this.adItem = (ThirdItem) adItem;
        if (this.adItem.refreshTime > 0) {
            this.refreshTask.setRefreshTime(this.adItem.refreshTime);
        }
        if (this.adItem.getIsLoadNextAd()) {
            this.mPool.setListener(new RefreshListener() {
                public void refreshData() {
                    ADLog.d("refreshData: 循环结束" + adItem);
                    ThirdAdLoader.this.loadAd(context, adItem);
                }
            });
        }
    }

    public void adLoadFailed() {
        this.mainHandler.post(new Runnable() {
            public void run() {
                if (ThirdAdLoader.this.mLoaderListener != null) {
                    ThirdAdLoader.this.mLoaderListener.loadFailed();
                }
            }
        });
    }

    public void addThirdToPool(List<NativeAdData> list) {
        this.mPool.clear();
        this.mPool.addAll(list);
        this.mainHandler.post(new Runnable() {
            public void run() {
                if (ThirdAdLoader.this.mLoaderListener != null) {
                    ThirdAdLoader.this.mLoaderListener.loadSuccess();
                }
            }
        });
        this.isInit = true;
    }

    public abstract Reporter getAdReporter(NativeAdData nativeAdData, int i);

    protected NativeAdData getDefAdItem(a aVar) {
        AdItem defAdItem = ((ThirdItem) aVar.b()).getDefAdItem();
        if (defAdItem == null) {
            return null;
        }
        Reporter selfReporter = new SelfReporter(defAdItem);
        selfReporter.setDownwarn(aVar.a());
        aVar.a(selfReporter);
        return new b(defAdItem);
    }

    public NativeAdData getNativeAdData() {
        return this.mPool.getNativeAdData();
    }

    public boolean isInit() {
        return this.isInit;
    }

    public void loadAd(final Context context, final a aVar, final NativeAdDataLoadedListener nativeAdDataLoadedListener) {
        this.adItem = (ThirdItem) aVar.b();
        NativeAdData nativeAdData = getNativeAdData();
        if (nativeAdData != null) {
            aVar.a(getAdReporter(nativeAdData, aVar.a()));
            nativeAdDataLoadedListener.onAdLoaded(nativeAdData);
            if (this.mPool.isLastAd() && this.adItem.getIsLoadNextAd()) {
                loadAd(context, this.adItem);
            }
        } else if (isInit()) {
            loadAd(context, this.adItem);
            nativeAdDataLoadedListener.onAdLoaded(getDefAdItem(aVar));
        } else {
            setLoaderListener(new AdLoaderListener() {
                public void loadFailed() {
                }

                public void loadSuccess() {
                    if (ThirdAdLoader.this.isLoadAd) {
                        ThirdAdLoader.this.setLoaderListener(null);
                        return;
                    }
                    ThirdAdLoader.this.isLoadAd = true;
                    NativeAdData nativeAdData = ThirdAdLoader.this.getNativeAdData();
                    if (nativeAdData == null) {
                        ThirdAdLoader.this.loadAd(context, ThirdAdLoader.this.adItem);
                        nativeAdDataLoadedListener.onAdLoaded(ThirdAdLoader.this.getDefAdItem(aVar));
                        return;
                    }
                    aVar.a(ThirdAdLoader.this.getAdReporter(nativeAdData, aVar.a()));
                    nativeAdDataLoadedListener.onAdLoaded(nativeAdData);
                    if (ThirdAdLoader.this.mPool.isLastAd() && ThirdAdLoader.this.adItem.getIsLoadNextAd()) {
                        ThirdAdLoader.this.loadAd(context, ThirdAdLoader.this.adItem);
                    }
                }
            });
        }
    }

    public void removeCurrentAd() {
        this.mPool.removeCurrentAd();
    }

    public void setIsInit(boolean z) {
        this.isInit = z;
    }

    public void setLoaderListener(AdLoaderListener adLoaderListener) {
        this.mLoaderListener = adLoaderListener;
    }

    public void startRefreshTask() {
        ADLog.d("startRefreshTask:" + getClass());
        this.refreshTask.start();
    }
}
