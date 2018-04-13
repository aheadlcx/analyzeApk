package cn.v6.sixrooms.room.popwindows;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.surfaceanim.specialenterframe.FortuneUtils;
import cn.v6.sixrooms.utils.DrawableResourceUtils;

public class RoomUpgradeWindow extends UpgradeWindow implements OnClickListener {
    private final int a = 5000;
    private RoomUpgradeMsg b;
    private TextView c;
    private ImageView d;
    private View e;
    private View f;
    private ScaleAnimation g;
    private Context h;
    private AnimationSet i;
    private OnDismissListener j;
    private a k = new a(this);

    class a implements Runnable {
        final /* synthetic */ RoomUpgradeWindow a;

        a(RoomUpgradeWindow roomUpgradeWindow) {
            this.a = roomUpgradeWindow;
        }

        public final void run() {
            this.a.dismiss();
        }
    }

    public RoomUpgradeWindow(Context context, OnDismissListener onDismissListener) {
        super(context);
        this.h = context;
        this.j = onDismissListener;
        this.e = LayoutInflater.from(context).inflate(R.layout.dialog_room_upgrade_layout, null);
        setContentView(this.e);
        this.f = this.e.findViewById(R.id.rl_bg);
        this.e.findViewById(R.id.btn_del).setOnClickListener(this);
        this.e.findViewById(R.id.upgrade_deliver).setOnClickListener(this);
        this.d = (ImageView) this.e.findViewById(R.id.iv_layer_0);
        this.c = (TextView) this.e.findViewById(R.id.tv_message);
        setBackgroundDrawable(context.getResources().getDrawable(R.drawable.transparent));
        setWidth(-2);
        setHeight(-2);
        setOutsideTouchable(false);
        this.i = new AnimationSet(false);
        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatMode(1);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setDuration(12000);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(500);
        this.i.addAnimation(rotateAnimation);
        this.i.addAnimation(alphaAnimation);
        this.g = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.g.setInterpolator(new OvershootInterpolator());
        this.g.setDuration(350);
    }

    public void show(RoomUpgradeMsg roomUpgradeMsg) {
        this.b = roomUpgradeMsg;
        if (this.b == null) {
            throw new RuntimeException("请先设置RoomUpgradeMsg消息");
        }
        int fortuneLevelUrl = RoomUpgradeMsg.TYPE_COIN.equals(this.b.getType()) ? FortuneUtils.getFortuneLevelUrl(String.valueOf(this.b.getRank())) : RoomUpgradeMsg.TYPE_WEALTH.equals(this.b.getType()) ? DrawableResourceUtils.getStarLevelImageResource(this.b.getRank()) : 0;
        if (fortuneLevelUrl != 0) {
            Drawable drawable = this.h.getResources().getDrawable(fortuneLevelUrl);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                this.c.setCompoundDrawables(null, null, drawable, null);
            }
        }
        String name = this.b.getName();
        if (name.length() > 10) {
            name = name.substring(0, 7) + "...";
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(name + "晋升");
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.h.getResources().getColor(R.color.room_upgrade_name_color)), 0, name.length(), 33);
        this.c.setText(spannableStringBuilder);
        this.f.startAnimation(this.g);
        this.d.startAnimation(this.i);
        showAtLocation(getContentView(), 17, 0, 0);
        this.e.postDelayed(this.k, 5000);
    }

    public void dismiss() {
        super.dismiss();
        this.b = null;
        if (this.i != null) {
            this.i.cancel();
        }
        if (this.g != null) {
            this.g.cancel();
        }
        if (this.j != null) {
            this.j.onDismiss();
        }
        this.e.removeCallbacks(this.k);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_del) {
            dismiss();
        } else if (id == R.id.upgrade_deliver) {
            deliver(this.b);
            dismiss();
        }
    }
}
