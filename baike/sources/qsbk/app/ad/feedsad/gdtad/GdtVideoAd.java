package qsbk.app.ad.feedsad.gdtad;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.baidu.mobstat.StatService;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.nativ.NativeMediaAD;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.tencent.connect.common.Constants;
import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.ConfigManager;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.ad.feedsad.AdItemData;
import qsbk.app.ad.feedsad.BaseAdProvider;
import qsbk.app.ad.feedsad.IFeedsAdLoaded;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;

public class GdtVideoAd extends BaseAdProvider {
    public static final int BG_Dark_Color = Color.argb(0, 0, 0, 0);
    public static final int BG_Light_Color = Color.argb(0, 0, 0, 0);
    public static final int GDT_ADS_MAX = 6;
    private static final String GDT_AD_APP_ID = "100722332";
    private static final String GDT_AD_POSITION_ID = "1060114408438188";
    private static final int RELOAD_MAX_TIME = 2;
    private static final String TAG = GdtVideoAd.class.getSimpleName();
    IFeedsAdLoaded adLoadedListener = null;
    private Activity mActivity;
    private String mAdPositionId;
    private LayoutInflater mInflater;
    private int mMaxHeight;
    private NativeMediaAD mNatvieAd = null;
    public int mPullAdCounts = 6;
    private int mReLoadCount = 0;
    private int mRequestWidth;
    protected List<AdItemData> mVideoAdItems = new LinkedList();

    public GdtVideoAd(int i) {
        this.ratio = i;
        this.mAdPositionId = GDT_AD_POSITION_ID;
    }

    public void exit() {
        this.mActivity = null;
    }

    public void fetchAd() {
        GdtAdRequest(true);
    }

    public void init(Activity activity, IFeedsAdLoaded iFeedsAdLoaded) {
        this.mActivity = activity;
        this.mInflater = LayoutInflater.from(this.mActivity);
        initialImageLoader();
        this.adLoadedListener = iFeedsAdLoaded;
        Resources resources = this.mActivity.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.mRequestWidth = displayMetrics.widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2);
        this.mMaxHeight = (int) (((double) displayMetrics.heightPixels) * 1.5d);
        GdtAdRequest(getRatio() > 0);
    }

    public void GdtAdRequest(boolean z) {
        DebugUtil.debug("luolong", "GdtAdRequest, needLoad=" + z);
        this.isFetchingAd = true;
        try {
            this.mNatvieAd = createGDTNativeAd();
            this.mNatvieAd.setDownAPPConfirmPolicy(DownAPPConfirmPolicy.NOConfirm);
            this.mReLoadCount = 0;
            loadNativeAd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialImageLoader() {
    }

    private boolean isAnzhi() {
        return Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE.equalsIgnoreCase(ConfigManager.getInstance().getChannel());
    }

    private NativeMediaAD createGDTNativeAd() {
        return new NativeMediaAD(this.mActivity, GDT_AD_APP_ID, this.mAdPositionId, new f(this));
    }

    protected void setImageViewLayoutParam(ImageView imageView, int i, int i2) {
        if (imageView != null) {
            int[] iArr = new int[2];
            caculateImageSize(iArr, i, i2);
            imageView.setLayoutParams(new LayoutParams(iArr[0], iArr[1]));
        }
    }

    private void caculateImageSize(int[] iArr, int i, int i2) {
        if (iArr == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        int i3 = (int) ((((float) this.mRequestWidth) / ((float) i)) * ((float) i2));
        if (this.mMaxHeight != -1 && i3 > this.mMaxHeight) {
            i3 = this.mMaxHeight;
        }
        iArr[0] = this.mRequestWidth;
        iArr[1] = i3;
    }

    private void loadNativeAd() {
        DebugUtil.debug(TAG, "loadFeedAd, mPullAdCounts:" + this.mPullAdCounts);
        this.mNatvieAd.loadAD(this.mPullAdCounts);
    }

    private void reLoad() {
        if (this.mReLoadCount < 2) {
            loadNativeAd();
            this.mReLoadCount++;
            return;
        }
        StatService.onEvent(QsbkApp.mContext, g.an, "req_gdt_ad_fail");
        this.isFetchingAd = false;
    }

    private void onFeedsLoaded(Collection<NativeMediaADData> collection) {
        if (collection != null) {
            DebugUtil.debug(TAG, " onFeedsLoaded");
            Collection arrayList = new ArrayList();
            Collection arrayList2 = new ArrayList();
            for (NativeMediaADData nativeMediaADData : collection) {
                GdtAdItemData gdtAdItemData = new GdtAdItemData();
                GdtAdView gdtAdView = new GdtAdView(this.mActivity);
                gdtAdView.init(nativeMediaADData);
                gdtAdItemData.setView(gdtAdView);
                if (nativeMediaADData.isVideoAD()) {
                    arrayList2.add(gdtAdItemData);
                    nativeMediaADData.preLoadVideo();
                    LogUtil.e("test find video view");
                } else {
                    arrayList.add(gdtAdItemData);
                }
            }
            if (arrayList2.size() > 0) {
                this.mVideoAdItems.addAll(arrayList2);
            } else {
                LogUtil.e("test not find video view");
            }
            if (arrayList.size() > 0) {
                this.mAdItems.addAll(arrayList);
            }
            if (this.adLoadedListener != null) {
                this.adLoadedListener.onFeedsAdLoaded();
            }
        }
    }

    public AdItemData popAd() {
        AdItemData adItemData = null;
        if (this.mAdItems.size() > 0) {
            adItemData = (AdItemData) this.mAdItems.remove(0);
            if (this.mVideoAdItems.size() == 0 && this.mAdItems.size() == 0) {
                tryFetchAd();
            }
        } else {
            tryFetchAd();
        }
        return adItemData;
    }

    public AdItemData popVideoAd() {
        AdItemData adItemData = null;
        if (this.mVideoAdItems.size() > 0) {
            adItemData = (AdItemData) this.mVideoAdItems.remove(0);
            if (this.mVideoAdItems.size() == 0 && this.mAdItems.size() == 0) {
                tryFetchAd();
            }
        } else if (this.mAdItems.size() == 0) {
            tryFetchAd();
        }
        return adItemData;
    }
}
