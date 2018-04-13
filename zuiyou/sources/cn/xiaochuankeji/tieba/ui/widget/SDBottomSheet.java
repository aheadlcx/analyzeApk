package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;

public class SDBottomSheet extends FrameLayout implements OnClickListener {
    private static final int a = e.a(43.0f);
    private static final int b = e.a(15.0f);
    private static final int c = e.a(0.0f);
    private View d;
    private View e;
    private View f;
    private TextView g;
    private TextView h;
    private LinearLayout i;
    private LinearLayout j;
    private TextView k;
    private View l;
    private View m;
    private Activity n;
    private b o;
    private a p;
    private Animation q;
    private Animation r;
    private Animation s;
    private Animation t;
    private boolean u = false;

    public interface b {
        void a(int i);
    }

    public interface a {
        void a(SDBottomSheet sDBottomSheet);
    }

    public static class c {
        public int a;
        public String b;
        public int c;

        public c(int i, String str, int i2) {
            this.a = i;
            this.b = str;
            this.c = i2;
        }
    }

    public SDBottomSheet(Activity activity, b bVar) {
        super(activity);
        this.n = activity;
        this.o = bVar;
        LayoutInflater.from(activity).inflate(R.layout.dialog_bottom_sheet, this);
        getViews();
        e();
        setId(R.id.sd_bottom_sheet);
        this.q = AnimationUtils.loadAnimation(activity, R.anim.bottom_in);
        this.r = AnimationUtils.loadAnimation(activity, R.anim.bottom_out);
        this.s = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_in);
        this.t = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_out);
    }

    private void getViews() {
        this.d = findViewById(R.id.dim_view);
        this.e = findViewById(R.id.layout_sheet_dialog);
        this.f = findViewById(R.id.layout_title);
        this.g = (TextView) findViewById(R.id.label_title);
        this.h = (TextView) findViewById(R.id.label_desc);
        this.i = (LinearLayout) findViewById(R.id.first_option_container);
        this.j = (LinearLayout) findViewById(R.id.second_option_container);
        this.l = findViewById(R.id.hsv_first);
        this.m = findViewById(R.id.line_first);
        this.k = (TextView) findViewById(R.id.btn_cancel);
    }

    private void d() {
        int a;
        Context a2 = cn.htjyb.ui.b.a(this.n);
        ViewGroup c = c(a2);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        if (c.a.b.a(a2.getWindow())) {
            a = c.a.b.a(a2);
        } else {
            a = 0;
        }
        setPadding(0, 0, 0, a);
        setLayoutParams(layoutParams);
        c.addView(this);
    }

    private void e() {
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SDBottomSheet a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(true, null);
            }
        });
    }

    private static SDBottomSheet b(Activity activity) {
        View c = c(activity);
        if (c == null) {
            return null;
        }
        return (SDBottomSheet) c.findViewById(R.id.sd_bottom_sheet);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean a(Activity activity) {
        SDBottomSheet b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        b.a(true, null);
        return true;
    }

    public void setOnDismissListener(a aVar) {
        this.p = aVar;
    }

    public boolean a() {
        return getVisibility() == 0;
    }

    public void b() {
        if (b(this.n) == null) {
            d();
            this.d.startAnimation(this.s);
            this.e.startAnimation(this.q);
        }
        try {
            if (getContext() instanceof h) {
                h hVar = (h) getContext();
                if (hVar.g()) {
                    com.c.a.c.b(hVar).b(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(boolean z, final AnimationListener animationListener) {
        if (z) {
            this.r.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ SDBottomSheet b;

                public void onAnimationStart(Animation animation) {
                    this.b.u = true;
                    if (animationListener != null) {
                        animationListener.onAnimationStart(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.u = false;
                    this.b.f();
                    if (animationListener != null) {
                        animationListener.onAnimationEnd(animation);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationRepeat(animation);
                    }
                }
            });
            this.d.startAnimation(this.t);
            this.e.startAnimation(this.r);
        } else {
            f();
        }
        try {
            if (getContext() instanceof h) {
                h hVar = (h) getContext();
                if (hVar.g()) {
                    com.c.a.c.b(hVar).b(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f() {
        c(cn.htjyb.ui.b.a(this.n)).removeView(this);
        if (this.p != null) {
            this.p.a(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (this.u) {
            return false;
        }
        Rect rect = new Rect();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        this.i.getGlobalVisibleRect(rect);
        if (rect.contains(rawX, rawY)) {
            return true;
        }
        a(true, null);
        return true;
    }

    public void a(ArrayList<c> arrayList, ArrayList<c> arrayList2) {
        int min;
        int i;
        int a = a((ArrayList) arrayList);
        if (arrayList2 != null) {
            min = Math.min(a((ArrayList) arrayList2), a);
        } else {
            min = a;
        }
        int i2 = min + a;
        for (i = 0; i < arrayList.size(); i++) {
            c cVar = (c) arrayList.get(i);
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.n).inflate(R.layout.layout_option_view, null);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, -2);
            TextView textView = (TextView) linearLayout.findViewById(R.id.tvName);
            ((ImageView) linearLayout.findViewById(R.id.ivIcon)).setImageResource(cVar.a);
            textView.setText(cVar.b);
            linearLayout.setTag(Integer.valueOf(cVar.c));
            linearLayout.setOnClickListener(this);
            this.i.addView(linearLayout, layoutParams);
        }
        if (arrayList2 != null && arrayList2.size() > 0) {
            this.j.setVisibility(0);
            findViewById(R.id.second_option_line).setVisibility(0);
            for (i = 0; i < arrayList2.size(); i++) {
                cVar = (c) arrayList2.get(i);
                linearLayout = (LinearLayout) LayoutInflater.from(this.n).inflate(R.layout.layout_option_view, null);
                layoutParams = new LinearLayout.LayoutParams(i2, -2);
                textView = (TextView) linearLayout.findViewById(R.id.tvName);
                ((ImageView) linearLayout.findViewById(R.id.ivIcon)).setImageResource(cVar.a);
                textView.setText(cVar.b);
                linearLayout.setTag(Integer.valueOf(cVar.c));
                linearLayout.setOnClickListener(this);
                this.j.addView(linearLayout, layoutParams);
            }
        }
        setContainerPadding(min);
    }

    private int a(ArrayList<c> arrayList) {
        int b = (int) ((((float) (e.b(this.n) - (c * 2))) - ((arrayList.size() > 5 ? 5.5f : 5.0f) * ((float) a))) / 6.0f);
        if (b <= b) {
            return b;
        }
        return b;
    }

    private void setContainerPadding(int i) {
        int i2 = i / 2;
        this.i.setPadding(i2, 0, i2, 0);
        if (this.j.getVisibility() == 0) {
            this.j.setPadding(i2, 0, i2, 0);
        }
    }

    public void onClick(View view) {
        final int intValue = ((Integer) view.getTag()).intValue();
        a(true, new AnimationListener(this) {
            final /* synthetic */ SDBottomSheet b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.b.o != null) {
                    this.b.o.a(intValue);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public ArrayList<c> c() {
        ArrayList<c> arrayList = new ArrayList();
        c cVar = new c(R.drawable.share_qq, Constants.SOURCE_QQ, 1);
        c cVar2 = new c(R.drawable.share_wechat, "微信好友", 2);
        c cVar3 = new c(R.drawable.share_circle, "朋友圈", 4);
        c cVar4 = new c(R.drawable.share_qzone, "QQ空间", 5);
        c cVar5 = new c(R.drawable.share_weibo, "微博", 3);
        arrayList.add(cVar);
        arrayList.add(cVar2);
        arrayList.add(cVar3);
        arrayList.add(cVar4);
        arrayList.add(cVar5);
        arrayList.add(new c(R.drawable.icon_option_copy_link, "复制链接", 18));
        return arrayList;
    }
}
