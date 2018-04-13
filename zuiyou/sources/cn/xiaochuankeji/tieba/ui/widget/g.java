package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.ui.b;
import cn.xiaochuankeji.tieba.R;

public class g extends FrameLayout {
    private TextView a = ((TextView) findViewById(R.id.textProgress));
    private ImageView b = ((ImageView) findViewById(R.id.imageProgress));
    private RotateAnimation c;

    private g(Context context, boolean z) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_sd_progress_hud, this, true);
        if (z) {
            findViewById(R.id.viewProgressHub).setBackgroundResource(0);
        }
        a();
    }

    private void a() {
        this.c = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.c.setInterpolator(new LinearInterpolator());
        this.c.setDuration(1500);
        this.c.setRepeatCount(-1);
    }

    public static g a(Activity activity) {
        return a(activity, null, false);
    }

    public static g a(Activity activity, String str) {
        return a(activity, str, false);
    }

    public static g a(Activity activity, boolean z) {
        return a(activity, null, z);
    }

    private static g a(Activity activity, String str, boolean z) {
        Context a = b.a(activity);
        g gVar = (g) d(a);
        if (gVar != null) {
            gVar.a(str);
            return gVar;
        }
        gVar = new g(a, z);
        gVar.a(str);
        ViewGroup e = e(a);
        new LayoutParams(-1, -1).gravity = 17;
        gVar.setId(R.id.view_progress_hub);
        e.addView(gVar);
        gVar.b();
        return gVar;
    }

    private static View d(Activity activity) {
        return activity.findViewById(16908290).findViewById(R.id.view_progress_hub);
    }

    private static ViewGroup e(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean b(Activity activity) {
        return d(activity) != null;
    }

    public static void c(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            Activity a = b.a(activity);
            g gVar = (g) d(a);
            if (gVar != null) {
                gVar.c();
                e(a).removeView(gVar);
            }
        }
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.a.setVisibility(8);
            return;
        }
        this.a.setVisibility(0);
        this.a.setText(str);
    }

    private void b() {
        this.b.startAnimation(this.c);
    }

    private void c() {
        this.b.clearAnimation();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
