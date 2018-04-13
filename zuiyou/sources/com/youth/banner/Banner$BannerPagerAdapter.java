package com.youth.banner;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

class Banner$BannerPagerAdapter extends PagerAdapter {
    final /* synthetic */ Banner this$0;

    Banner$BannerPagerAdapter(Banner banner) {
        this.this$0 = banner;
    }

    public int getCount() {
        return Banner.access$700(this.this$0).size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        viewGroup.addView((View) Banner.access$700(this.this$0).get(i));
        View view = (View) Banner.access$700(this.this$0).get(i);
        if (Banner.access$800(this.this$0) != null) {
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Log.e(Banner$BannerPagerAdapter.this.this$0.tag, "你正在使用旧版点击事件接口，下标是从1开始，为了体验请更换为setOnBannerListener，下标从0开始计算");
                    Banner.access$800(Banner$BannerPagerAdapter.this.this$0).OnBannerClick(i);
                }
            });
        }
        if (Banner.access$900(this.this$0) != null) {
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Banner.access$900(Banner$BannerPagerAdapter.this.this$0).OnBannerClick(Banner$BannerPagerAdapter.this.this$0.toRealPosition(i));
                }
            });
        }
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
