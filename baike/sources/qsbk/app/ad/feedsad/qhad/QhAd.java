package qsbk.app.ad.feedsad.qhad;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.ak.android.engine.nav.NativeAd;
import com.ak.android.engine.nav.NativeAdLoaderListener;
import com.ak.android.engine.navbase.NativeAdLoader;
import com.ak.android.shell.AKAD;
import com.baidu.mobstat.StatService;
import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.ad.feedsad.AdItemData;
import qsbk.app.ad.feedsad.BaseAdProvider;
import qsbk.app.ad.feedsad.IFeedsAdLoaded;

public class QhAd extends BaseAdProvider implements NativeAdLoaderListener {
    public static final String QH_360_QIUSHI_AD_ID = "uPkvBFQFIF";
    public static final String QH_360_QIUYOUCIRCLE_AD_ID = "a5u6gm50ex";
    private static final int RELOAD_MAX_TIME = 2;
    private boolean fromQiushi;
    private IFeedsAdLoaded listenner;
    protected Activity mActivity;
    private int mMaxHeight;
    private NativeAdLoader mNativeLoader;
    private int mReLoadCount = 0;
    private int mRequestWidth;

    public QhAd(int i) {
        this.ratio = i;
    }

    public QhAd(int i, boolean z) {
        this.ratio = i;
        this.fromQiushi = z;
    }

    public void exit() {
        if (this.mNativeLoader != null) {
            this.mNativeLoader.destroy();
        }
        if (this.mActivity != null) {
            this.mActivity = null;
        }
    }

    public void init(Activity activity, IFeedsAdLoaded iFeedsAdLoaded) {
        this.listenner = iFeedsAdLoaded;
        this.mActivity = activity;
        Resources resources = this.mActivity.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.mRequestWidth = displayMetrics.widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2);
        this.mMaxHeight = (int) (((double) displayMetrics.heightPixels) * 1.5d);
        getQhAd(getRatio() > 0);
    }

    public void getQhAd(boolean z) {
        if (z && this.mActivity != null) {
            this.isFetchingAd = true;
            try {
                this.mReLoadCount = 0;
                if (this.fromQiushi) {
                    this.mNativeLoader = AKAD.getNativeAdLoader(this.mActivity, QH_360_QIUSHI_AD_ID, this);
                } else {
                    this.mNativeLoader = AKAD.getNativeAdLoader(this.mActivity, QH_360_QIUYOUCIRCLE_AD_ID, this);
                }
                this.mNativeLoader.loadAds();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void fetchAd() {
        if (this.mAdItems.size() == 0) {
            getQhAd(true);
        }
    }

    public void onAdLoadSuccess(ArrayList<NativeAd> arrayList) {
        this.isFetchingAd = false;
        Collection arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NativeAd nativeAd = (NativeAd) it.next();
            QhAdItemData qhAdItemData = new QhAdItemData();
            QhAdView qhAdView = new QhAdView(this.mActivity);
            qhAdView.init(nativeAd);
            qhAdItemData.setAdView(qhAdView);
            arrayList2.add(qhAdItemData);
            int hashCode = qhAdItemData.hashCode();
            setAdFetchTime(hashCode);
            setAdUnShow(hashCode);
        }
        if (arrayList2.size() > 0) {
            this.mAdItems.addAll(arrayList2);
        }
        if (this.listenner != null) {
            this.listenner.onFeedsAdLoaded();
        }
    }

    public void onAdLoadFailed(int i, String str) {
        this.isFetchingAd = false;
        reload();
    }

    private void reload() {
        if (this.mReLoadCount < 2) {
            this.mNativeLoader.loadAds();
            this.mReLoadCount++;
            return;
        }
        StatService.onEvent(QsbkApp.mContext, g.an, "req_qh_360_ad_fail");
    }

    public AdItemData popAd() {
        return super.popAdAndSigned();
    }
}
