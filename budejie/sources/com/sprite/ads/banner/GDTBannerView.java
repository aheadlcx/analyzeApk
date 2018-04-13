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
import com.sprite.ads.internal.utils.b;
import com.sprite.ads.nati.reporter.NoReporter;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdAdLoader;
import com.sprite.ads.third.ThirdApiAdData;
import pl.droidsonroids.gif.GifDrawable;

public class GDTBannerView extends RelativeLayout implements OnClickListener {
    Reporter gdtApiReporter;
    ImageView imageView;
    Context mContext;
    BannerADListener mListener;
    ViewGroup mParentLayout;
    ThirdApiAdData nativeAdData;

    public GDTBannerView(ThirdAdLoader thirdAdLoader, int i, ThirdApiAdData thirdApiAdData, Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        super(context);
        this.mContext = context;
        this.nativeAdData = thirdApiAdData;
        this.gdtApiReporter = thirdAdLoader.getAdReporter(thirdApiAdData, i);
        this.mParentLayout = viewGroup;
        this.mListener = bannerADListener;
        this.mParentLayout.removeAllViews();
        this.mParentLayout.addView(this, new LayoutParams(-2, -2));
        initView();
    }

    private int getBannerHeight() {
        return (int) (this.nativeAdData.screenRatio * ((double) ViewUtil.SCREEN_WIDTH));
    }

    private RelativeLayout.LayoutParams getBannerLayoutParams() {
        int bannerHeight = getBannerHeight();
        if (bannerHeight == 0) {
            bannerHeight = ViewUtil.dip2px(60.0f);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, bannerHeight);
        layoutParams.addRule(13);
        return layoutParams;
    }

    private void initView() {
        this.gdtApiReporter.onExposured(this);
        this.mListener.onADReceive(new NoReporter(), false);
        this.imageView = new ImageView(this.mContext);
        this.imageView.setOnClickListener(this);
        if (!this.nativeAdData.getPic().endsWith(".gif")) {
            this.imageView.setScaleType(ScaleType.FIT_XY);
        }
        addView(this.imageView, getBannerLayoutParams());
        AdImageLoader.getInstance().displayImage(this.nativeAdData.getPic(), this.imageView, new AdImageLoadingListener() {
            public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            }
        });
        b.a(this.mContext, this);
    }

    public void onClick(View view) {
        this.gdtApiReporter.onClicked(view);
    }
}
