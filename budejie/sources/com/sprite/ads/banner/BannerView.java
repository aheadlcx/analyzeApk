package com.sprite.ads.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.sprite.ads.internal.a.c.a;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.internal.imageCache.AdImageLoader;
import com.sprite.ads.internal.imageCache.AdImageLoadingListener;
import com.sprite.ads.internal.utils.AdUtil;
import com.sprite.ads.internal.utils.ViewUtil;
import com.sprite.ads.nati.reporter.SelfReporter;
import pl.droidsonroids.gif.GifDrawable;

public class BannerView extends RelativeLayout implements OnClickListener {
    BannerADListener adListener;
    ImageView imageView;
    SelfItem mAdItem;
    Context mContext;
    String mPostId;
    SelfReporter selfReporter = new SelfReporter(this.mAdItem);

    public BannerView(String str, AdItem adItem, Context context, BannerADListener bannerADListener) {
        super(context);
        this.mContext = context;
        this.mPostId = str;
        this.mAdItem = (SelfItem) adItem;
        this.selfReporter.setDownwarn(this.mAdItem.downwarn);
        this.adListener = bannerADListener;
        initView();
    }

    private LayoutParams getBannerLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-1, getBannerHeight());
        layoutParams.addRule(11);
        return layoutParams;
    }

    private void initView() {
        setOnClickListener(this);
        this.selfReporter.onExposured(this);
        this.imageView = new ImageView(this.mContext);
        if (!this.mAdItem.getPic().endsWith(".gif")) {
            this.imageView.setScaleType(ScaleType.FIT_XY);
        }
        addView(this.imageView, getBannerLayoutParams());
        AdImageLoader.getInstance().displayImage(this.mAdItem.getPic(), this.imageView, new AdImageLoadingListener() {
            public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            }
        });
        AdUtil.addAdCaption(this.mContext, this);
    }

    public int getBannerHeight() {
        int i = (int) (this.mAdItem.extra.screen_ratio * ((double) ViewUtil.SCREEN_WIDTH));
        return i == 0 ? -2 : i;
    }

    public void onClick(View view) {
        this.selfReporter.onClicked(view, new a() {
            public void onClick() {
                if (BannerView.this.adListener != null) {
                    BannerView.this.adListener.onADSkip(BannerView.this.mAdItem);
                }
            }

            public void onDismiss() {
            }

            public void onPositive() {
            }
        });
    }
}
