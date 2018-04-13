package com.budejie.www.activity.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.base.a;
import com.budejie.www.activity.base.a.g;
import com.budejie.www.bean.BannerResponse;
import com.budejie.www.bean.Topic;
import com.budejie.www.util.z;
import com.elves.update.d;
import java.util.ArrayList;
import java.util.List;

public class TabWidget extends RelativeLayout {
    private MyViewPager a;
    private TabPageIndicator b;
    private List<RelativeLayout> c = new ArrayList();
    private int d;
    private int e;
    private Context f;
    private g g;
    private int h = 3;
    private RelativeLayout i;
    private int j;
    private int k = 1;
    private String l = "0";
    private Handler m = new TabWidget$3(this);

    public ViewPager getmViewPager() {
        return this.a;
    }

    public TabWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public TabWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TabWidget(Context context, int i, String str) {
        super(context);
        this.k = i;
        this.l = str;
        a(context);
    }

    private void a(Context context) {
        this.f = context;
        a();
    }

    public void a() {
        if (d.a()) {
            if (getChildCount() != 0) {
                removeAllViews();
            }
            this.i = (RelativeLayout) View.inflate(this.f, R.layout.tab_page_widget, null);
            this.a = (MyViewPager) this.i.findViewById(R.id.viewpager);
            this.a.setOnPageChangeListener(new TabWidget$1(this));
            this.a.setOnSingleTouchListener(new TabWidget$2(this));
            this.b = (TabPageIndicator) this.i.findViewById(R.id.tab_page_indicator);
            this.b.setPointResource(R.drawable.banner_page_indicator);
            this.b.setmSpace(8);
            a aVar = new a(this.f);
            aVar.getClass();
            this.g = new g(aVar, "banner_response_cache", null);
            String a = this.g.a();
            if (a != null && a.trim().length() > 0) {
                List list;
                BannerResponse bannerResponse = (BannerResponse) z.a(a, BannerResponse.class);
                ArrayList arrayList = new ArrayList();
                if (!(bannerResponse == null || bannerResponse.result == null)) {
                    if (1 == this.k && bannerResponse.result.jingxuan != null) {
                        list = (ArrayList) bannerResponse.result.jingxuan.get(this.l);
                        if (list != null) {
                        }
                    } else if (2 == this.k && bannerResponse.result.zuixin != null) {
                        ArrayList arrayList2 = (ArrayList) bannerResponse.result.zuixin.get(this.l);
                        if (list != null && list.size() > 0) {
                            a(list);
                            return;
                        }
                    }
                }
                Object obj = arrayList;
                if (list != null) {
                }
            }
        }
    }

    private void a(List<Topic> list) {
        if (list == null || list.size() <= 0) {
            this.e = 0;
            return;
        }
        this.c.clear();
        this.j = list.size();
        try {
            if (this.b == null) {
                this.b = (TabPageIndicator) this.i.findViewById(R.id.tab_page_indicator);
                this.b.setPointResource(R.drawable.banner_page_indicator);
                this.b.setmSpace(8);
            }
            this.b.a(this.j);
        } catch (RuntimeException e) {
        }
        if (this.j == 2 || this.j == 3) {
            list.addAll(new ArrayList(list));
        }
        this.e = list.size();
        for (Topic a : list) {
            this.c.add(a(a));
        }
        d();
        if (getChildCount() == 0) {
            addView(this.i);
        }
        b();
    }

    private void d() {
        if (this.a == null) {
            this.a = (MyViewPager) this.i.findViewById(R.id.viewpager);
        }
        if (this.a.getAdapter() == null) {
            this.a.setAdapter(new TabWidget$a(this));
        }
        this.a.getAdapter().notifyDataSetChanged();
        this.d = 1073741823 - (1073741823 % this.e);
        this.a.setCurrentItem(this.d);
        if (this.b == null) {
            this.b = (TabPageIndicator) this.i.findViewById(R.id.tab_page_indicator);
            this.b.setPointResource(R.drawable.banner_page_indicator);
            this.b.setmSpace(8);
        }
        this.b.setSelectIndicator(this.d % this.e);
    }

    private RelativeLayout a(Topic topic) {
        RelativeLayout relativeLayout = (RelativeLayout) View.inflate(this.f, R.layout.tab_page_banner_item, null);
        TextView textView = (TextView) relativeLayout.findViewById(R.id.banner_item_title);
        ((AsyncImageView) relativeLayout.findViewById(R.id.banner_item_image)).setAsyncCacheImage(topic.image, R.color.apply_listview_cacahecolor);
        textView.setText(topic.title);
        relativeLayout.setTag(topic);
        return relativeLayout;
    }

    public void b() {
        if (this.m.hasMessages(0)) {
            if (this.e <= 1) {
                this.m.removeMessages(0);
            }
        } else if (this.e > 1) {
            this.m.sendEmptyMessageDelayed(0, (long) (this.h * 1000));
        }
    }

    public void c() {
        if (this.m.hasMessages(0)) {
            this.m.removeMessages(0);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                c();
                break;
            case 1:
            case 3:
                b();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
