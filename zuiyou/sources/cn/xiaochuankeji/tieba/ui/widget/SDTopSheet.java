package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import com.android.a.a.c;
import com.google.android.flexbox.FlexboxLayout;

public class SDTopSheet extends FrameLayout implements OnClickListener {
    private View a;
    private View b;
    private FlexboxLayout c;
    private Activity d;
    private b e;
    private a f;
    private Animation g;
    private Animation h;

    public interface b {
        void a(int i);
    }

    public interface a {
        void a(SDTopSheet sDTopSheet);
    }

    public SDTopSheet(Activity activity, b bVar) {
        super(activity);
        this.d = activity;
        this.e = bVar;
        LayoutInflater.from(activity).inflate(R.layout.dialog_top_sheet, this);
        getViews();
        d();
        setId(R.id.sd_top_sheet);
        this.g = AnimationUtils.loadAnimation(activity, R.anim.anim_popup_menu_in);
        this.h = AnimationUtils.loadAnimation(activity, R.anim.anim_popup_menu_out);
    }

    private void getViews() {
        this.a = findViewById(R.id.statusbar_placeholder_view);
        this.b = findViewById(R.id.sheet_anim_layout);
        this.c = (FlexboxLayout) findViewById(R.id.sheet_container);
    }

    private void c() {
        ViewGroup c = c(cn.htjyb.ui.b.a(this.d));
        setLayoutParams(new LayoutParams(-1, -1));
        c.addView(this);
    }

    private void d() {
        if (c.a()) {
            this.a.setVisibility(8);
        }
    }

    private static SDTopSheet b(Activity activity) {
        View c = c(activity);
        if (c == null) {
            return null;
        }
        return (SDTopSheet) c.findViewById(R.id.sd_top_sheet);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean a(Activity activity) {
        SDTopSheet b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        b.a(null);
        return true;
    }

    public void setOnDismissListener(a aVar) {
        this.f = aVar;
    }

    public boolean a() {
        return getVisibility() == 0;
    }

    public void b() {
        if (b(this.d) == null) {
            c();
            this.b.startAnimation(this.g);
        }
    }

    public void a(final AnimationListener animationListener) {
        this.h.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ SDTopSheet b;

            public void onAnimationStart(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationStart(animation);
                }
            }

            public void onAnimationEnd(Animation animation) {
                this.b.e();
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
        this.b.startAnimation(this.h);
    }

    private void e() {
        c(cn.htjyb.ui.b.a(this.d)).removeView(this);
        if (this.f != null) {
            this.f.a(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            this.c.getGlobalVisibleRect(rect);
            if (!rect.contains(rawX, rawY)) {
                a(null);
            }
        }
        return true;
    }

    public void a(String str, int i, int i2) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.view_top_sheet_item, this.c, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.linearItem);
        ((TextView) inflate.findViewById(R.id.tvItem)).setText(str);
        linearLayout.setOnClickListener(this);
        linearLayout.setTag(Integer.valueOf(i2));
        if (i != 0) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.ivIcon);
            imageView.setVisibility(0);
            imageView.setImageResource(i);
        }
        this.c.addView(inflate);
    }

    public void onClick(View view) {
        final int intValue = ((Integer) view.getTag()).intValue();
        a(new AnimationListener(this) {
            final /* synthetic */ SDTopSheet b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (this.b.e != null) {
                    this.b.e.a(intValue);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
