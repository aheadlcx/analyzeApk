package qsbk.app.ad.feedsad.baiduad;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.baidu.mobad.feeds.BaiduNative;
import com.baidu.mobad.feeds.NativeResponse;
import qsbk.app.R;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.RatingView;

public class BaiduAdView {
    private static final String TAG = "BaiduAd";
    private String mAdDesc;
    public int mAdId;
    private String mAdTitle;
    private BaiduNative mBaiduNative;
    private NativeResponse mBaiduNativeResponse;
    private Context mContext;
    private int mImageHeight;
    private int mImageWidth;
    private LayoutInflater mInflater;
    private String mLogoImageUrl;
    private String mMainImageUrl;
    private int mMaxHeight;
    private int mPersonNum;
    private int mRequestWidth;
    private double mStarLevel;
    private int mStaus = 1;
    private View mView;

    class a {
        RelativeLayout a;
        ImageView b;
        TextView c;
        TextView d;
        ImageView e;
        RatingView f;
        TextView g;
        TextView h;
        TextView i;
        final /* synthetic */ BaiduAdView j;

        a(BaiduAdView baiduAdView) {
            this.j = baiduAdView;
        }
    }

    public BaiduAdView(Context context) {
        DebugUtil.debug(TAG, "BaiduAdView Constructor");
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public NativeResponse getRef() {
        return this.mBaiduNativeResponse;
    }

    public void init(BaiduNative baiduNative, NativeResponse nativeResponse) {
        this.mBaiduNative = baiduNative;
        this.mBaiduNativeResponse = nativeResponse;
        this.mLogoImageUrl = this.mBaiduNativeResponse.getIconUrl();
        this.mMainImageUrl = this.mBaiduNativeResponse.getImageUrl();
        this.mAdTitle = this.mBaiduNativeResponse.getTitle();
        this.mAdDesc = this.mBaiduNativeResponse.getDesc();
        Resources resources = this.mContext.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.mRequestWidth = displayMetrics.widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2);
        this.mMaxHeight = (int) (((double) displayMetrics.heightPixels) * 1.5d);
        this.mImageWidth = this.mRequestWidth;
        this.mImageHeight = (int) (((float) this.mImageWidth) / 1.6f);
        DebugUtil.debug(TAG, "BaiduAdView init, title=" + this.mAdTitle + ",AdId=" + this.mAdId);
    }

    private String getStatus(int i) {
        switch (i) {
            case 0:
                return this.mContext.getResources().getString(R.string.try_agin);
            case 1:
                return this.mContext.getResources().getString(R.string.download);
            case 2:
                return this.mContext.getResources().getString(R.string.downloading);
            case 3:
                return this.mContext.getResources().getString(R.string.click_to_install);
            case 4:
                return this.mContext.getResources().getString(R.string.click_to_start);
            default:
                return null;
        }
    }

    public void updateStatus(int i) {
        this.mStaus = i;
        if (this.mView != null) {
            ((TextView) this.mView.findViewById(R.id.downbt)).setText(getStatus(i));
        }
    }

    public View getView(View view) {
        a aVar;
        DebugUtil.debug(TAG, "BaiduAdView getView");
        if (view == null || !(view.getTag() instanceof a)) {
            a aVar2 = new a(this);
            view = this.mInflater.inflate(R.layout.feeds_baidu, null);
            aVar2.e = (ImageView) view.findViewById(R.id.image);
            aVar2.d = (TextView) view.findViewById(R.id.content);
            aVar2.h = (TextView) view.findViewById(R.id.downbt);
            aVar2.b = (ImageView) view.findViewById(R.id.userIcon);
            aVar2.g = (TextView) view.findViewById(R.id.players);
            aVar2.f = (RatingView) view.findViewById(R.id.ratting);
            aVar2.c = (TextView) view.findViewById(R.id.userName);
            aVar2.a = (RelativeLayout) view.findViewById(R.id.mainLayout);
            aVar2.i = (TextView) view.findViewById(R.id.downbt);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (this.mContext != null) {
            if (UIHelper.isNightTheme()) {
                aVar.a.setBackgroundColor(UIHelper.getColor(R.color.main_bg_night));
                aVar.i.setBackgroundColor(UIHelper.getColor(R.color.downbt_background_night));
                aVar.i.setTextColor(UIHelper.getColor(R.color.downbt_text_color_night));
            } else {
                aVar.a.setBackgroundColor(UIHelper.getColor(R.color.white));
                aVar.i.setBackgroundColor(UIHelper.getColor(R.color.downbt_background));
                aVar.i.setTextColor(UIHelper.getColor(R.color.downbt_text_color));
            }
        }
        aVar.a.setOnClickListener(new a(this));
        aVar.g.setText("本广告由百度广告提供");
        if (this.mBaiduNativeResponse.isDownloadApp()) {
            aVar.h.setText(getStatus(this.mStaus));
        } else {
            aVar.h.setText("查看");
        }
        aVar.d.setText(this.mAdDesc);
        aVar.c.setText(this.mAdTitle);
        FrescoImageloader.displayAvatar(aVar.b, this.mLogoImageUrl);
        FrescoImageloader.displayImage(aVar.e, this.mMainImageUrl, TileBackground.getBackgroud(aVar.e.getContext(), BgImageType.AD));
        DebugUtil.debug(TAG, "mLogoImageUrl=" + this.mLogoImageUrl + ",mMainImageUrl=" + this.mMainImageUrl);
        this.mBaiduNativeResponse.recordImpression(aVar.a);
        setImageViewLayoutParam(aVar.e);
        this.mView = view;
        FeedsAdStat.stat(view, "baidu");
        return view;
    }

    @TargetApi(11)
    private void setImageViewLayoutParam(ImageView imageView) {
        if (imageView != null) {
            int[] iArr = new int[2];
            caculateImageSize(iArr, this.mImageWidth, this.mImageHeight);
            DebugUtil.debug(TAG, "BaiduAdView, setImageViewLayoutParam, mImageWidth=" + this.mImageWidth + ",mImageHeight=" + this.mImageHeight);
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
}
