package cn.tatagou.sdk.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.R$dimen;
import cn.tatagou.sdk.adapter.ScrollAdapter;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.k;
import cn.tatagou.sdk.util.p;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import java.util.List;

public class TtgScrollView extends LinearLayout {
    private List<Special> a;
    private List<ImageView> b;
    private ImageView[] c;
    private ScrollAdapter d;
    private ViewPager e;
    private LinearLayout f;
    private FrameLayout g;
    private String h;
    private int i;
    private Runnable j = new Runnable(this) {
        final /* synthetic */ TtgScrollView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.k.obtainMessage().sendToTarget();
            if (this.a.c != null && this.a.c.length > 0) {
                this.a.k.postDelayed(this, 5000);
            }
        }
    };
    private Handler k = new Handler(this) {
        final /* synthetic */ TtgScrollView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.e.setCurrentItem(this.a.e.getCurrentItem() + 1);
        }
    };

    private class a implements OnPageChangeListener {
        final /* synthetic */ TtgScrollView a;

        private a(TtgScrollView ttgScrollView) {
            this.a = ttgScrollView;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            int i2 = 0;
            if (this.a.c.length <= 2) {
                i %= 2;
            } else if (i > 2) {
                i %= this.a.c.length;
            }
            Drawable circularDrawable = p.getCircularDrawable(TtgConfig.getInstance().getThemeColor(), 0, 0);
            Drawable circularDrawable2 = p.getCircularDrawable(-1, 0, 0);
            while (i2 < this.a.c.length) {
                if (i != i2) {
                    this.a.c[i2].setBackgroundDrawable(circularDrawable2);
                } else {
                    this.a.c[i].setBackgroundDrawable(circularDrawable);
                }
                i2++;
            }
        }
    }

    public TtgScrollView(Context context) {
        super(context);
        a();
    }

    public TtgScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TtgScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        View inflate = inflate(getContext(), R.layout.ttg_sroll_view, this);
        this.i = (int) getResources().getDimension(R$dimen.ttg_main_point_size);
        this.f = (LinearLayout) inflate.findViewById(R.id.ly_point);
        this.e = (ViewPager) inflate.findViewById(R.id.vp_img);
        this.g = (FrameLayout) inflate.findViewById(R.id.fy_scroll);
        this.a = new ArrayList();
        this.b = new ArrayList();
        int windowWidth = p.getWindowWidth(getContext());
        if (windowWidth != -1) {
            LayoutParams layoutParams = (LayoutParams) this.g.getLayoutParams();
            layoutParams.width = windowWidth;
            layoutParams.height = windowWidth / 3;
            this.g.setLayoutParams(layoutParams);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startScroll();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScroll();
    }

    public void stopScroll() {
        if (this.a != null && this.a.size() > 0) {
            k.closeRunnable(this.k, this.j);
        }
    }

    public void startScroll() {
        if (this.a != null && this.a.size() > 0) {
            this.k.removeCallbacks(this.j);
            k.runRunnable(this.k, this.j, 5000);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private boolean a(List<Special> list) {
        boolean z = false;
        Log.d("TTG", "isScrollUpdate: 11111111111");
        if (list == null) {
            return false;
        }
        boolean z2 = this.a != null;
        if (z2 && list.size() == this.a.size() && this.a.size() != 0) {
            boolean z3 = false;
            z2 = false;
            for (Special special : list) {
                boolean z4 = z2;
                int i = z3;
                for (Special special2 : this.a) {
                    if (!(special == null || special2 == null || p.isEmpty(special.getId()) || !special.getId().equals(special2.getId()))) {
                        i++;
                        if (!(p.isEmpty(special.getCoverImg()) || special.getCoverImg().equals(special2.getCoverImg()))) {
                            z4 = true;
                        }
                    }
                }
                z3 = i;
                z2 = z4;
            }
            z = z3;
        }
        if (z == list.size()) {
            return z2;
        }
        return true;
    }

    private void a(Activity activity, List<Special> list) {
        int i;
        if (this.b == null) {
            this.b = new ArrayList();
        } else if (this.b.size() > 0) {
            this.b.clear();
        }
        com.bumptech.glide.k a = i.a(activity);
        if (list.size() == 2) {
            i = 2;
        } else {
            i = 0;
        }
        for (int i2 = 0; i2 < list.size() + i; i2++) {
            int i3 = i != 2 ? i2 : i2 % 2 == 0 ? 0 : 1;
            Special special = (Special) list.get(i3);
            if (!(special == null || activity == null)) {
                ImageView imageView = new ImageView(activity);
                imageView.setScaleType(ScaleType.FIT_XY);
                a.a(p.onImgUrlJpg(special.getCoverImg())).a(DiskCacheStrategy.SOURCE).a(false).b().c().a(imageView);
                this.b.add(imageView);
            }
        }
        if (this.a.size() > 0) {
            this.a.clear();
        }
        this.a.addAll(list);
    }

    private void b(Activity activity, List<Special> list) {
        int i = 0;
        if (this.f != null) {
            this.f.removeAllViews();
        }
        this.c = new ImageView[list.size()];
        Drawable circularDrawable = p.getCircularDrawable(TtgConfig.getInstance().getThemeColor(), 0, 0);
        Drawable circularDrawable2 = p.getCircularDrawable(-1, 0, 0);
        while (i < this.c.length) {
            ImageView imageView = new ImageView(activity);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.i, this.i);
            layoutParams.leftMargin = p.dip2px(activity, 4.0f);
            layoutParams.rightMargin = p.dip2px(activity, 4.0f);
            imageView.setLayoutParams(layoutParams);
            this.c[i] = imageView;
            if (VERSION.SDK_INT >= 16) {
                Drawable drawable;
                ImageView imageView2 = this.c[i];
                if (i == 0) {
                    drawable = circularDrawable;
                } else {
                    drawable = circularDrawable2;
                }
                imageView2.setBackground(drawable);
            } else {
                this.c[i].setBackgroundDrawable(i == 0 ? circularDrawable : circularDrawable2);
            }
            if (this.f != null) {
                this.f.addView(this.c[i]);
            }
            i++;
        }
    }

    public void onBannerSpecialShow(Activity activity, String str, List<Special> list) {
        this.h = str;
        if (a((List) list)) {
            a(activity, list);
            this.d = new ScrollAdapter(activity, this.b, this.k, this.j, list, str);
            this.e.setAdapter(this.d);
            b(activity, list);
            this.e.setOnPageChangeListener(new a());
            this.e.setCurrentItem(100);
            if (this.k != null && this.j != null) {
                this.k.removeCallbacks(this.j);
                this.k.postDelayed(this.j, 5000);
            }
        }
    }
}
