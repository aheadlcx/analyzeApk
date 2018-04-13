package cn.v6.sixrooms.room.popwindows;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.utils.DensityUtil;

public class GodUpgradeWindow extends UpgradeWindow {
    private Context a;
    private View b;
    private RelativeLayout c;
    private RelativeLayout d;
    private TextView e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private OnDismissListener m;
    private RoomUpgradeMsg n;
    private int[] o = new int[]{R.drawable.room_god_wing_left, R.drawable.room_creator_wing_left, R.drawable.room_gods_god_wing_left};
    private int[] p = new int[]{R.drawable.room_god_wing_right, R.drawable.room_creator_wing_right, R.drawable.room_gods_god_wing_right};
    private int[] q = new int[]{R.drawable.room_god_icon, R.drawable.room_creator_icon, R.drawable.room_gods_god_icon};
    private int[] r = new int[]{R.drawable.room_god_light, R.drawable.room_creator_light, R.drawable.room_gods_god_light};
    private Handler s = new a(this);

    static /* synthetic */ void c(GodUpgradeWindow godUpgradeWindow) {
        ValueAnimator ofFloat = ObjectAnimator.ofFloat(godUpgradeWindow.c, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.addListener(new f(godUpgradeWindow));
        ofFloat.setDuration(200);
        ofFloat.setStartDelay(200);
        ofFloat.start();
    }

    static /* synthetic */ void h(GodUpgradeWindow godUpgradeWindow) {
        godUpgradeWindow.g.setVisibility(0);
        godUpgradeWindow.h.setVisibility(0);
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("rotation", new float[]{90.0f, 0.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("rotation", new float[]{-90.0f, 0.0f});
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("scaleX", new float[]{0.0f, 0.5f, 1.0f});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("scaleY", new float[]{0.0f, 0.5f, 1.0f});
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(godUpgradeWindow.h, new PropertyValuesHolder[]{ofFloat, ofFloat3, ofFloat4}).setDuration(400);
        ObjectAnimator.ofPropertyValuesHolder(godUpgradeWindow.g, new PropertyValuesHolder[]{ofFloat2, ofFloat3, ofFloat4}).setDuration(400).start();
        duration.start();
        duration.addListener(new d(godUpgradeWindow));
    }

    static /* synthetic */ void i(GodUpgradeWindow godUpgradeWindow) {
        Animator ofFloat = ObjectAnimator.ofFloat(godUpgradeWindow.d, "scaleX", new float[]{0.0f, 1.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(godUpgradeWindow.d, "scaleY", new float[]{0.0f, 1.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(godUpgradeWindow.d, "alpha", new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.setDuration(400);
        animatorSet.start();
    }

    public GodUpgradeWindow(Context context, OnDismissListener onDismissListener) {
        super(context);
        this.a = context;
        this.m = onDismissListener;
        this.b = LayoutInflater.from(context).inflate(R.layout.phone_room_god_popupwindow, null);
        setContentView(this.b);
        setBackgroundDrawable(context.getResources().getDrawable(R.drawable.transparent));
        this.d = (RelativeLayout) this.b.findViewById(R.id.god_upgrade_layout);
        this.c = (RelativeLayout) this.b.findViewById(R.id.god_layout);
        this.e = (TextView) this.b.findViewById(R.id.god_upgrade);
        this.f = (ImageView) this.b.findViewById(R.id.deliver_iv);
        this.g = (ImageView) this.b.findViewById(R.id.god_left_wing_iv);
        this.h = (ImageView) this.b.findViewById(R.id.god_right_wing_iv);
        this.i = (ImageView) this.b.findViewById(R.id.god_entiey_iv);
        this.j = (ImageView) this.b.findViewById(R.id.god_stars_point_iv);
        this.k = (ImageView) this.b.findViewById(R.id.god_light_iv);
        this.l = (ImageView) this.b.findViewById(R.id.god_close);
        this.l.setOnClickListener(new b(this));
        this.f.setOnClickListener(new c(this));
    }

    public void show(RoomUpgradeMsg roomUpgradeMsg) {
        Object obj;
        int i;
        this.n = roomUpgradeMsg;
        RoomUpgradeMsg roomUpgradeMsg2 = this.n;
        int rank = roomUpgradeMsg2.getRank();
        LayoutParams layoutParams = (LayoutParams) this.g.getLayoutParams();
        LayoutParams layoutParams2 = (LayoutParams) this.h.getLayoutParams();
        String string = V6Coop.getInstance().getContext().getString(R.string.god);
        if (rank == 26) {
            string = V6Coop.getInstance().getContext().getString(R.string.gods_god);
            layoutParams.topMargin = DensityUtil.dip2px(84.0f);
            layoutParams.leftMargin = DensityUtil.dip2px(70.0f);
            layoutParams2.topMargin = DensityUtil.dip2px(84.0f);
            layoutParams2.rightMargin = DensityUtil.dip2px(70.0f);
            obj = string;
            i = 2;
        } else if (rank == 27) {
            string = V6Coop.getInstance().getContext().getString(R.string.creator);
            layoutParams.topMargin = DensityUtil.dip2px(75.0f);
            layoutParams.leftMargin = DensityUtil.dip2px(55.0f);
            layoutParams2.topMargin = DensityUtil.dip2px(75.0f);
            layoutParams2.rightMargin = DensityUtil.dip2px(55.0f);
            r0 = string;
            i = 1;
        } else {
            layoutParams.topMargin = DensityUtil.dip2px(70.0f);
            layoutParams.leftMargin = DensityUtil.dip2px(66.0f);
            layoutParams2.topMargin = DensityUtil.dip2px(70.0f);
            layoutParams2.rightMargin = DensityUtil.dip2px(66.0f);
            r0 = string;
            i = 0;
        }
        string = roomUpgradeMsg2.getName();
        if (string.length() > 6) {
            string = string.substring(0, 5) + "…";
        }
        CharSequence string2 = V6Coop.getInstance().getContext().getString(R.string.god_upgrade, new Object[]{string, obj});
        this.g.setImageResource(this.o[i]);
        this.h.setImageResource(this.p[i]);
        this.i.setImageResource(this.q[i]);
        this.k.setImageResource(this.r[i]);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(string2);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getResources().getColor(R.color.guard_drawtext_color)), 2, string.length() + 2, 33);
        this.e.setText(spannableStringBuilder);
        this.g.setVisibility(4);
        this.h.setVisibility(4);
        this.k.setAlpha(1.0f);
        this.k.setRotation(0.0f);
        this.j.setAlpha(1.0f);
        this.j.setVisibility(4);
        this.d.setAlpha(0.0f);
        Animator ofFloat = ObjectAnimator.ofFloat(this.c, "scaleX", new float[]{0.0f, 1.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(this.c, "scaleY", new float[]{0.0f, 1.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(this.c, "alpha", new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new e(this));
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.setDuration(400);
        animatorSet.start();
        showAtLocation(getContentView(), 17, 0, DensityUtil.dip2px(-16.0f));
        this.s.sendEmptyMessageDelayed(1, 6900);
    }

    public void dismiss() {
        super.dismiss();
        if (this.m != null) {
            this.m.onDismiss();
        }
        this.s.removeCallbacksAndMessages(null);
    }

    static /* synthetic */ void a(GodUpgradeWindow godUpgradeWindow) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(godUpgradeWindow.k, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.setDuration(400);
        ofFloat.start();
    }

    static /* synthetic */ void b(GodUpgradeWindow godUpgradeWindow) {
        ValueAnimator ofFloat = ObjectAnimator.ofFloat(godUpgradeWindow.d, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.setDuration(400);
        ofFloat.start();
    }

    static /* synthetic */ void d(GodUpgradeWindow godUpgradeWindow) {
        godUpgradeWindow.j.clearAnimation();
        ValueAnimator ofFloat = ObjectAnimator.ofFloat(godUpgradeWindow.j, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.setDuration(400);
        ofFloat.start();
    }

    static /* synthetic */ void g(GodUpgradeWindow godUpgradeWindow) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(godUpgradeWindow.c, "translationY", new float[]{0.0f, 8.0f, 0.0f});
        ofFloat.setDuration(1200);
        ofFloat.setRepeatCount(5);
        ofFloat.start();
    }

    static /* synthetic */ void j(GodUpgradeWindow godUpgradeWindow) {
        godUpgradeWindow.j.setImageResource(R.drawable.god_star_point_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) godUpgradeWindow.j.getDrawable();
        godUpgradeWindow.j.setVisibility(0);
        animationDrawable.start();
    }

    static /* synthetic */ void k(GodUpgradeWindow godUpgradeWindow) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(godUpgradeWindow.k, "rotation", new float[]{0.0f, 180.0f});
        ofFloat.setDuration(6500);
        ofFloat.start();
    }
}
