package com.sprite.ads.banner;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.reporter.NoReporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BannerViewContainer extends RelativeLayout {
    public static final int NEXT_BANNER = 1;
    private Map<String, BannerView> bannerViewMap = new HashMap();
    private BannerView curBannerView;
    private int index = 0;
    private Context mContext;
    private Map<String, AdItem> mData;
    private BannerADListener mListener;
    private ViewGroup mParentLayout;
    private List<String> mPostIds = new ArrayList();
    private int rollTime = 30000;
    private Handler timeHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    BannerViewContainer.this.replaceBannerView();
                    sendEmptyMessageDelayed(1, (long) BannerViewContainer.this.rollTime);
                    return;
                default:
                    return;
            }
        }
    };

    public BannerViewContainer(Map<String, AdItem> map, Context context, ViewGroup viewGroup, BannerADListener bannerADListener) {
        super(context);
        this.mContext = context;
        this.mParentLayout = viewGroup;
        this.mListener = bannerADListener;
        this.mListener.onADReceive(new NoReporter(), false);
        this.mData = map;
        for (Entry key : this.mData.entrySet()) {
            this.mPostIds.add(key.getKey());
        }
        this.mParentLayout.addView(this, getBannerLayoutParams());
        this.timeHandler.sendEmptyMessage(1);
    }

    private LayoutParams getBannerLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(13);
        return layoutParams;
    }

    private void replaceBannerView() {
        if (!this.mPostIds.isEmpty()) {
            if (this.index >= this.mPostIds.size()) {
                this.index = 0;
            }
            String str = (String) this.mPostIds.get(this.index);
            BannerView bannerView = (BannerView) this.bannerViewMap.get(str);
            ADLog.d("轮播bannerView postId:" + str + " bannerView" + bannerView);
            if (bannerView == null) {
                if (((AdItem) this.mData.get(str)) instanceof SelfItem) {
                    BannerView bannerView2 = new BannerView(str, (AdItem) this.mData.get(str), this.mContext, this.mListener);
                    this.bannerViewMap.put(str, bannerView2);
                    bannerView = bannerView2;
                } else {
                    return;
                }
            }
            if (this.curBannerView != null) {
                removeView(this.curBannerView);
            }
            this.curBannerView = bannerView;
            addView(this.curBannerView, new LayoutParams(-2, this.curBannerView.getBannerHeight()));
            this.index++;
        } else if (this.mListener != null) {
            this.mListener.onNoAD(15);
        }
    }

    private void setAnimator() {
        if (VERSION.SDK_INT >= 11) {
            LayoutTransition layoutTransition = new LayoutTransition();
            layoutTransition.setAnimator(0, ObjectAnimator.ofFloat(this, "scaleX", new float[]{0.0f, 1.0f}));
            layoutTransition.setAnimator(2, ObjectAnimator.ofFloat(this, "scaleX", new float[]{0.0f, 1.0f}));
            setLayoutTransition(layoutTransition);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        ADLog.d("bannerView onWindowFocusChanged:" + z);
        if (z) {
            this.timeHandler.sendEmptyMessage(1);
        } else {
            this.timeHandler.removeMessages(1);
        }
    }
}
