package com.sprite.ads.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.sprite.ads.internal.imageCache.AdImageLoader;
import com.sprite.ads.internal.imageCache.AdImageLoadingListener;
import com.sprite.ads.internal.utils.ViewUtil;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdSdkNativeAdData;
import pl.droidsonroids.gif.GifDrawable;

public class ThirdBannerViewBase<T extends ThirdSdkNativeAdData> extends RelativeLayout implements OnClickListener {
    protected Context mContext;
    protected BannerADListener mListener;
    protected ThirdSdkNativeAdData mNativeAdData;
    protected Reporter mNativeReporter;
    protected ViewGroup mParentLayout;

    public ThirdBannerViewBase(Context context, ViewGroup viewGroup, Reporter reporter, ThirdSdkNativeAdData thirdSdkNativeAdData, BannerADListener bannerADListener) {
        super(context);
        this.mContext = context;
        this.mParentLayout = viewGroup;
        this.mNativeReporter = reporter;
        this.mNativeAdData = thirdSdkNativeAdData;
        this.mListener = bannerADListener;
        if (!checkFail()) {
            createBannerView();
        } else if (this.mListener != null) {
            this.mListener.onNoAD(0);
        }
    }

    private boolean checkFail() {
        return this.mContext == null || this.mParentLayout == null || this.mNativeReporter == null || this.mNativeAdData == null || this.mListener == null;
    }

    protected void createBannerView() {
        this.mParentLayout.removeAllViews();
        this.mParentLayout.addView(this, new LayoutParams(-2, -2));
        this.mListener.onADReceive(this.mNativeReporter, false);
        View imageView = new ImageView(this.mContext);
        imageView.setOnClickListener(this);
        if (!this.mNativeAdData.getPic().endsWith(".gif")) {
            imageView.setScaleType(ScaleType.FIT_XY);
        }
        addView(imageView, getBannerLayoutParams(this.mNativeAdData.screenRatio));
        AdImageLoader.getInstance().displayImage(this.mNativeAdData.getPic(), imageView, new AdImageLoadingListener() {
            public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            }
        });
    }

    protected int getBannerHeight(double d) {
        return (int) (((double) ViewUtil.SCREEN_WIDTH) * d);
    }

    protected RelativeLayout.LayoutParams getBannerLayoutParams(double d) {
        int bannerHeight = getBannerHeight(d);
        if (bannerHeight == 0) {
            bannerHeight = ViewUtil.dip2px(60.0f);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, bannerHeight);
        layoutParams.addRule(13);
        return layoutParams;
    }

    public void onClick(View view) {
        if (this.mNativeReporter != null) {
            this.mNativeReporter.onClicked(view);
        }
    }
}
