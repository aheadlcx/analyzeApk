package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
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
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.android.a.a.c;

public class SDPopupMenu extends FrameLayout implements OnClickListener {
    private View a;
    private View b;
    private LinearLayout c;
    private Rect d = new Rect();
    private Rect e;
    private Activity f;
    private b g;
    private a h;
    private Animation i;
    private Animation j;

    public interface b {
        void b(int i);
    }

    public interface a {
        void a(SDPopupMenu sDPopupMenu);
    }

    public SDPopupMenu(Activity activity, View view, Rect rect, b bVar) {
        super(activity);
        this.f = activity;
        this.g = bVar;
        view.getGlobalVisibleRect(this.d);
        this.e = new Rect(rect);
        LayoutInflater.from(activity).inflate(R.layout.dialog_popup_menu, this);
        getViews();
        b(view);
        setId(R.id.sd_popup_menu);
        this.i = AnimationUtils.loadAnimation(activity, R.anim.anim_popup_menu_in);
        this.j = AnimationUtils.loadAnimation(activity, R.anim.anim_popup_menu_out);
    }

    private void getViews() {
        this.a = findViewById(R.id.menu_layout);
        this.b = findViewById(R.id.menu_anim_layout);
        this.c = (LinearLayout) findViewById(R.id.menu_container);
    }

    private void c() {
        ViewGroup c = c(cn.htjyb.ui.b.a(this.f));
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 48;
        setLayoutParams(layoutParams);
        c.addView(this);
    }

    private void b(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getWindowVisibleDisplayFrame(rect2);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams.topMargin = rect.bottom - rect2.top;
        if (!c.a()) {
            layoutParams.topMargin += getResources().getDimensionPixelOffset(R.dimen.status_bar_height);
        }
        this.a.setLayoutParams(layoutParams);
    }

    public static Rect a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return new Rect(0, rect.bottom, e.b(), e.c());
    }

    private static SDPopupMenu b(Activity activity) {
        View c = c(activity);
        if (c == null) {
            return null;
        }
        return (SDPopupMenu) c.findViewById(R.id.sd_popup_menu);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean a(Activity activity) {
        SDPopupMenu b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        b.a(true, null);
        return true;
    }

    public void setOnDismissListener(a aVar) {
        this.h = aVar;
    }

    public boolean a() {
        return getVisibility() == 0;
    }

    public void b() {
        if (b(this.f) == null) {
            c();
            this.b.startAnimation(this.i);
        }
    }

    public void a(boolean z, final AnimationListener animationListener) {
        if (z) {
            this.j.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ SDPopupMenu b;

                public void onAnimationStart(Animation animation) {
                    if (animationListener != null) {
                        animationListener.onAnimationStart(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    this.b.d();
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
            this.b.startAnimation(this.j);
            return;
        }
        d();
    }

    private void d() {
        c(cn.htjyb.ui.b.a(this.f)).removeView(this);
        if (this.h != null) {
            this.h.a(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            this.c.getGlobalVisibleRect(rect);
            if (!rect.contains(rawX, rawY)) {
                if (!(this.e.isEmpty() || this.e.contains(rawX, rawY) || this.d.contains(rawX, rawY))) {
                    z = false;
                }
                a(z, null);
            }
        }
        return z;
    }

    public void a(String str, int i) {
        a(str, i, false);
    }

    public void a(String str, int i, boolean z) {
        a(str, 0, i, z);
    }

    public void a(String str, int i, int i2, boolean z) {
        View inflate = LayoutInflater.from(this.f).inflate(R.layout.view_popup_menu_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tvItem);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.linearItem);
        textView.setText(str);
        linearLayout.setOnClickListener(this);
        linearLayout.setTag(Integer.valueOf(i2));
        if (i != 0) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.ivIcon);
            imageView.setVisibility(0);
            imageView.setImageResource(i);
            textView.setGravity(3);
        } else {
            textView.setGravity(17);
        }
        this.c.addView(inflate);
    }

    public void onClick(View view) {
        final int intValue = ((Integer) view.getTag()).intValue();
        a(true, new AnimationListener(this) {
            final /* synthetic */ SDPopupMenu b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.b.g != null) {
                    this.b.g.b(intValue);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
