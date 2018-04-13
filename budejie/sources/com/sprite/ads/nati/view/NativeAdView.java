package com.sprite.ads.nati.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.sprite.ads.R$integer;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.NativeAdRef;
import com.sprite.ads.nati.a.a;
import com.sprite.ads.nati.a.b;
import com.sprite.ads.nati.loader.NativeAdLoader;
import com.sprite.ads.nati.loader.NativeAdLoaderFactory;
import com.sprite.ads.nati.reporter.Reporter.OnChangeAdListener;
import com.sprite.ads.nati.reporter.SelfReporter;

public class NativeAdView extends RelativeLayout implements OnClickListener {
    a adItemRef;
    NativeAdLoader adLoader;
    Context mContext;
    private OnChangeAdListener onChangeAdListener;
    private NativeAdView$OnPreClickListener preClickListener;

    public NativeAdView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    public void loadAd(NativeAdRef nativeAdRef, NativeAdDataLoadedListener nativeAdDataLoadedListener) {
        setOnClickListener(this);
        this.adItemRef = (a) nativeAdRef;
        AdItem b = this.adItemRef.b();
        NativeAdLoaderRef a = com.sprite.ads.nati.a.a().a(b.postId);
        if (a == null || a.adLoader == null) {
            this.adLoader = NativeAdLoaderFactory.createAdLoader(nativeAdRef.getDataSourceType(), this.mContext, ((a) nativeAdRef).b());
        } else {
            this.adLoader = a.adLoader;
        }
        if (this.adLoader != null) {
            com.sprite.ads.nati.a.a().a(b.postId, new NativeAdLoaderRef(this.adItemRef, this.adLoader));
            this.adLoader.loadAd(this.mContext, this.adItemRef, new NativeAdView$1(this, nativeAdDataLoadedListener));
        } else if (b instanceof ThirdItem) {
            b = ((ThirdItem) b).getDefAdItem();
            NativeAdData bVar = new b(b);
            this.adItemRef.a(new SelfReporter(b));
            nativeAdDataLoadedListener.onAdLoaded(bVar);
        }
    }

    public void onClick(View view) {
        if (this.preClickListener != null) {
            this.preClickListener.onClicked(view);
        }
        ADLog.d("广告点击");
        if (this.adItemRef != null) {
            if (this.onChangeAdListener != null) {
                view.setTag(R$integer.native_ad_view_tag_key, new NativeAdView$2(this));
            }
            this.adItemRef.onClicked(view);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.adItemRef.b().getInterceptTouchEvent();
    }

    public void setOnChangeAdListener(OnChangeAdListener onChangeAdListener) {
        this.onChangeAdListener = onChangeAdListener;
    }

    public void setPreClickListener(NativeAdView$OnPreClickListener nativeAdView$OnPreClickListener) {
        this.preClickListener = nativeAdView$OnPreClickListener;
    }
}
