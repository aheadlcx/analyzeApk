package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.htjyb.ui.b;

public class c extends FrameLayout {
    private Context a;
    private boolean b = false;
    private View c;
    private View d;

    public c(Context context) {
        super(context);
        this.a = context;
    }

    public void a(int i, int i2, int i3) {
        LayoutInflater.from(this.a).inflate(i, this);
        this.c = findViewById(i2);
        if (i3 != 0) {
            this.d = findViewById(i3);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.c();
                }
            });
        }
    }

    public void a() {
        this.b = true;
        ViewGroup a = a(b.a((Activity) this.a));
        setLayoutParams(new LayoutParams(-1, -1));
        a.addView(this);
    }

    private static ViewGroup a(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    private void c() {
        a(b.a((Activity) this.a)).removeView(this);
        this.b = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            this.c.getGlobalVisibleRect(rect);
            if (!rect.contains(rawX, rawY)) {
                c();
            }
        }
        return true;
    }

    public boolean b() {
        if (!this.b) {
            return false;
        }
        c();
        return true;
    }
}
