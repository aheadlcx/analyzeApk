package qsbk.app.ad.feedsad.baiduad;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.BaiduNative.BaiduNativeNetworkListener;
import com.baidu.mobad.feeds.NativeErrorCode;
import com.baidu.mobad.feeds.NativeResponse;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobad.feeds.RequestParameters.Builder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.publish.Publish_Image;
import qsbk.app.ad.feedsad.BaseAdProvider;
import qsbk.app.ad.feedsad.IFeedsAdLoaded;
import qsbk.app.core.utils.PictureGetHelper;
import qsbk.app.utils.DebugUtil;

public class BaiduAd extends BaseAdProvider implements BaiduNativeNetworkListener {
    public static final int BAIDU_ADS_MAX = 6;
    private static final String TAG = BaiduAd.class.getSimpleName();
    private final int BAIDU_AD_IMAGE_REQUEST_HEIGHT = PictureGetHelper.IMAGE_SIZE;
    private final int BAIDU_AD_IMAGE_REQUEST_WIDTH = Publish_Image.MIN_IMG_HEIGHT;
    private BaiduNative mBaiduNative;
    private Context mContext;
    private IFeedsAdLoaded mFeedsAdLoadedListener;

    public BaiduAd(int i) {
        DebugUtil.debug(TAG, "BaiduAd");
        this.ratio = i;
    }

    public void init(Activity activity, IFeedsAdLoaded iFeedsAdLoaded) {
        this.mContext = activity;
        this.mBaiduNative = new BaiduNative(activity, "2546370", this);
        this.mFeedsAdLoadedListener = iFeedsAdLoaded;
        BaiduAdRequest(getRatio() > 0);
    }

    public void BaiduAdRequest(boolean z) {
        Resources resources;
        DebugUtil.debug(TAG, "startAdRequest, needLoad=" + z);
        if (this.mContext != null) {
            resources = this.mContext.getResources();
        } else {
            resources = QsbkApp.mContext.getResources();
        }
        int dimensionPixelSize = (int) (((float) (resources.getDisplayMetrics().widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2))) / 1.6f);
        if (z) {
            this.isFetchingAd = true;
            RequestParameters build = new Builder().setWidth(Publish_Image.MIN_IMG_HEIGHT).setHeight(PictureGetHelper.IMAGE_SIZE).confirmDownloading(false).build();
            build.setAdsType(3);
            if (this.mBaiduNative == null) {
                this.mBaiduNative = new BaiduNative(QsbkApp.mContext, "2546370", this);
            }
            this.mBaiduNative.makeRequest(build);
        }
    }

    public void exit() {
        DebugUtil.debug(TAG, "exit, BaiduNative Destory");
        if (this.mBaiduNative != null) {
            this.mBaiduNative.destroy();
        }
    }

    public void fetchAd() {
        BaiduAdRequest(true);
    }

    public void onNativeLoad(List<NativeResponse> list) {
        this.isFetchingAd = false;
        DebugUtil.debug(TAG, Thread.currentThread().getName() + " Baidu Ad loaded onNativeLoad, Size=" + list.size());
        Collection arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            BaiduAdView baiduAdView = new BaiduAdView(this.mContext);
            baiduAdView.init(this.mBaiduNative, (NativeResponse) list.get(i));
            BaiduAdItemData baiduAdItemData = new BaiduAdItemData();
            baiduAdItemData.setView(baiduAdView);
            arrayList.add(baiduAdItemData);
        }
        if (arrayList != null && arrayList.size() > 0) {
            this.mAdItems.addAll(arrayList);
            if (this.mFeedsAdLoadedListener != null) {
                this.mFeedsAdLoadedListener.onFeedsAdLoaded();
            }
        }
    }

    public void onNativeFail(NativeErrorCode nativeErrorCode) {
        this.isFetchingAd = false;
        DebugUtil.debug(TAG, "onNativeFail reason:" + nativeErrorCode.name());
    }
}
