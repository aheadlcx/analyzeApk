package cn.v6.sixrooms.widgets.phone;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.GuardStausBean;
import cn.v6.sixrooms.utils.DensityUtil;
import java.util.ArrayList;
import java.util.List;

public class ShowGuardPopWindow extends PopupWindow {
    private Context a;
    private View b;
    private View c;
    private RelativeLayout d;
    private TextView e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private AnimationDrawable n;
    private boolean o = false;
    private List<GuardStausBean> p = new ArrayList();
    private int[] q = new int[]{R.drawable.room_guard_gold_left_wing, R.drawable.room_guard_silver_left_wing};
    private int[] r = new int[]{R.drawable.room_guard_gold_right_wing, R.drawable.room_guard_silver_right_wing};
    private int[] s = new int[]{R.drawable.room_guard_gold_entiey, R.drawable.room_guard_silver_entiey};
    private int[] t = new int[]{R.drawable.room_guard_gold_light, R.drawable.room_guard_silver_light};
    private int[] u = new int[]{R.drawable.room_guard_gold_entiey_bg, R.drawable.room_guard_silver_entiey_bg};
    private Handler v = new ah(this);

    static /* synthetic */ void a(ShowGuardPopWindow showGuardPopWindow) {
        Animator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.l, "scaleX", new float[]{1.0f, 0.5f, 0.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(showGuardPopWindow.l, "scaleY", new float[]{1.0f, 0.5f, 0.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(showGuardPopWindow.l, "alpha", new float[]{1.0f, 0.5f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        ofFloat.addListener(new am(showGuardPopWindow));
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.setStartDelay(300);
        animatorSet.start();
    }

    static /* synthetic */ void d(ShowGuardPopWindow showGuardPopWindow) {
        Animator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.g, "scaleX", new float[]{1.0f, 0.6f, 0.3f, 0.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(showGuardPopWindow.g, "scaleY", new float[]{1.0f, 0.6f, 0.3f, 0.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(showGuardPopWindow.g, "rotation", new float[]{-0.0f, -10.0f, -60.0f, -80.0f, -150.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.play(ofFloat).with(ofFloat3).with(ofFloat2);
        ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.h, "scaleX", new float[]{1.0f, 0.6f, 0.3f, 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(showGuardPopWindow.h, "scaleY", new float[]{1.0f, 0.6f, 0.3f, 0.0f});
        ofFloat3 = ObjectAnimator.ofFloat(showGuardPopWindow.h, "rotation", new float[]{0.0f, 10.0f, 60.0f, 80.0f, 150.0f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(500);
        animatorSet2.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.addListener(new ak(showGuardPopWindow));
        animatorSet.start();
        animatorSet2.start();
    }

    static /* synthetic */ void e(ShowGuardPopWindow showGuardPopWindow) {
        Animator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.d, "scaleX", new float[]{1.0f, 0.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(showGuardPopWindow.d, "scaleY", new float[]{1.0f, 0.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(showGuardPopWindow.d, "alpha", new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    static /* synthetic */ void f(ShowGuardPopWindow showGuardPopWindow) {
        Animator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.i, "scaleX", new float[]{1.0f, 0.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(showGuardPopWindow.i, "scaleY", new float[]{1.0f, 0.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(showGuardPopWindow.i, "alpha", new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        ofFloat.addListener(new an(showGuardPopWindow));
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.setDuration(500);
        animatorSet.setStartDelay(300);
        animatorSet.start();
    }

    static /* synthetic */ void i(ShowGuardPopWindow showGuardPopWindow) {
        showGuardPopWindow.g.setVisibility(0);
        showGuardPopWindow.h.setVisibility(0);
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("rotation", new float[]{150.0f, 90.0f, 80.0f, 60.0f, 10.0f, 0.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("rotation", new float[]{-150.0f, -90.0f, -80.0f, -60.0f, -10.0f, 0.0f});
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("scaleX", new float[]{0.0f, 0.1f, 0.4f, 0.8f, 1.2f, 1.0f});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("scaleY", new float[]{0.0f, 0.1f, 0.4f, 0.8f, 1.2f, 1.0f});
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(showGuardPopWindow.h, new PropertyValuesHolder[]{ofFloat, ofFloat3, ofFloat4}).setDuration(500);
        duration.addListener(new aj(showGuardPopWindow));
        ObjectAnimator.ofPropertyValuesHolder(showGuardPopWindow.g, new PropertyValuesHolder[]{ofFloat2, ofFloat3, ofFloat4}).setDuration(500).start();
        duration.start();
        showGuardPopWindow.v.sendEmptyMessageDelayed(1, 7000);
    }

    static /* synthetic */ void j(ShowGuardPopWindow showGuardPopWindow) {
        showGuardPopWindow.k.setVisibility(0);
        Animator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.k, "Rotation", new float[]{0.0f, 30.0f});
        ofFloat.setDuration(5500);
        Animator ofFloat2 = ObjectAnimator.ofFloat(showGuardPopWindow.k, "alpha", new float[]{0.0f, 1.0f});
        ofFloat2.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).after(ofFloat2);
        animatorSet.start();
    }

    static /* synthetic */ void m(ShowGuardPopWindow showGuardPopWindow) {
        showGuardPopWindow.m.setVisibility(0);
        for (int i = 1; i <= 2; i++) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.m, "translationY", new float[]{0.0f, (float) DensityUtil.dip2px(90.0f)});
            ofFloat.setDuration(1500);
            if (i == 1) {
                ofFloat.setStartDelay(1000);
            } else {
                ofFloat.setStartDelay(5000);
            }
            ofFloat.start();
        }
    }

    public void onShow(GuardStausBean guardStausBean, View view) {
        this.c = view;
        if (this.o) {
            this.p.add(guardStausBean);
        } else {
            a(guardStausBean);
        }
    }

    public ShowGuardPopWindow(Context context) {
        super(context);
        this.a = context;
        this.b = LayoutInflater.from(context).inflate(R.layout.phone_room_guard_show_popupwindow, null);
        setContentView(this.b);
        setBackgroundDrawable(context.getResources().getDrawable(R.drawable.transparent));
        setWidth(-1);
        setHeight(-2);
        this.d = (RelativeLayout) this.b.findViewById(R.id.guard_title_layout);
        this.e = (TextView) this.b.findViewById(R.id.guard_title);
        this.f = (ImageView) this.b.findViewById(R.id.guard_title_iv);
        this.g = (ImageView) this.b.findViewById(R.id.guard_left_wing_iv);
        this.h = (ImageView) this.b.findViewById(R.id.guard_right_wing_iv);
        this.i = (ImageView) this.b.findViewById(R.id.guard_entiey_iv);
        this.j = (ImageView) this.b.findViewById(R.id.guard_stars_point_iv);
        this.k = (ImageView) this.b.findViewById(R.id.guard_light_iv);
        this.l = (ImageView) this.b.findViewById(R.id.guard_entiey_bg_iv);
        this.m = (ImageView) this.b.findViewById(R.id.guard_entiey_light_iv);
    }

    private void a(GuardStausBean guardStausBean) {
        String str;
        int i = 1;
        showAtLocation(this.c, 17, 0, DensityUtil.dip2px(20.0f));
        this.o = true;
        if ("7570".equals(guardStausBean.getPropid())) {
            str = "开通了金守护";
            i = 0;
        } else {
            str = "开通了银守护";
        }
        this.g.setImageResource(this.q[i]);
        this.h.setImageResource(this.r[i]);
        this.i.setImageResource(this.s[i]);
        this.k.setImageResource(this.t[i]);
        this.l.setImageResource(this.u[i]);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(guardStausBean.getAlias() + str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getResources().getColor(R.color.guard_drawtext_color)), 0, guardStausBean.getAlias().length(), 33);
        this.e.setText(spannableStringBuilder);
        this.g.setVisibility(4);
        this.h.setVisibility(4);
        a();
        this.f.setVisibility(4);
        this.k.setVisibility(4);
        this.k.setRotation(0.0f);
        this.j.setAlpha(1.0f);
        this.j.setVisibility(4);
        Animator ofFloat = ObjectAnimator.ofFloat(this.d, "scaleX", new float[]{0.0f, 1.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(this.d, "scaleY", new float[]{0.0f, 1.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(this.d, "alpha", new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.setDuration(500);
        animatorSet.start();
        this.l.setVisibility(0);
        ofFloat = ObjectAnimator.ofFloat(this.l, "scaleX", new float[]{0.0f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.l, "scaleY", new float[]{0.0f, 1.0f});
        Animator ofFloat4 = ObjectAnimator.ofFloat(this.l, "alpha", new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(300);
        animatorSet2.play(ofFloat).with(ofFloat2).with(ofFloat4);
        animatorSet2.start();
        ofFloat = ObjectAnimator.ofFloat(this.i, "scaleX", new float[]{0.0f, 1.2f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.i, "scaleY", new float[]{0.0f, 1.2f, 1.0f});
        ofFloat4 = ObjectAnimator.ofFloat(this.i, "alpha", new float[]{0.0f, 1.0f});
        animatorSet2 = new AnimatorSet();
        animatorSet2.addListener(new al(this));
        animatorSet2.play(ofFloat).with(ofFloat2).with(ofFloat4);
        animatorSet2.setDuration(500);
        animatorSet2.start();
        this.v.postDelayed(new ai(this), 200);
    }

    private void a() {
        this.k.clearAnimation();
        this.m.setVisibility(4);
    }

    @SuppressLint({"NewApi"})
    protected void showTitleBgAnimator() {
        this.f.setVisibility(0);
        Animator ofFloat = ObjectAnimator.ofFloat(this.f, "translationX", new float[]{0.0f, (float) (this.d.getWidth() - this.f.getWidth())});
        ofFloat.setRepeatCount(2);
        Animator ofFloat2 = ObjectAnimator.ofFloat(this.f, "alpha", new float[]{0.0f, 0.5f, 1.0f, 0.5f, 0.0f});
        ofFloat2.setRepeatCount(2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(3800);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void onDesdory() {
        this.v.removeCallbacksAndMessages(null);
        dismiss();
    }

    static /* synthetic */ void c(ShowGuardPopWindow showGuardPopWindow) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.k, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    static /* synthetic */ void k(ShowGuardPopWindow showGuardPopWindow) {
        showGuardPopWindow.j.clearAnimation();
        ValueAnimator ofFloat = ObjectAnimator.ofFloat(showGuardPopWindow.j, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    static /* synthetic */ void l(ShowGuardPopWindow showGuardPopWindow) {
        showGuardPopWindow.j.setImageResource(R.drawable.guard_star_point_anim);
        showGuardPopWindow.n = (AnimationDrawable) showGuardPopWindow.j.getDrawable();
        showGuardPopWindow.j.setVisibility(0);
        showGuardPopWindow.n.start();
    }
}
