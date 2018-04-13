package cn.xiaochuankeji.tieba.ui.my.download;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class i extends FrameLayout {
    private View a;
    private boolean b = false;
    private ViewGroup c;
    private ProgressBar d;
    private TextView e;
    private int f;

    public i(Activity activity) {
        super(activity);
        LayoutInflater.from(activity).inflate(R.layout.view_progress_dlg, this);
        setId(R.id.view_progress_dlg);
        setVisibility(8);
        setLayoutParams(new LayoutParams(-1, -1));
        this.c = a(activity);
        this.a = findViewById(R.id.alertDlgFrame);
        this.d = (ProgressBar) findViewById(R.id.pb);
        this.e = (TextView) findViewById(R.id.tv_progress);
    }

    public boolean a() {
        if (b()) {
            return true;
        }
        return false;
    }

    public boolean b() {
        return getVisibility() == 0;
    }

    public void c() {
        if (8 == getVisibility()) {
            setVisibility(0);
            this.c.addView(this);
        }
    }

    public void d() {
        if (getVisibility() == 0) {
            setVisibility(8);
            this.c.removeView(this);
        }
    }

    private ViewGroup a(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b && motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            this.a.getGlobalVisibleRect(rect);
            if (!rect.contains(rawX, rawY)) {
                d();
            }
        }
        return true;
    }

    public void a(int i, int i2) {
        if (!isShown()) {
            return;
        }
        if (this.f == 0) {
            this.f = i2;
            this.d.setMax(i2);
            return;
        }
        this.d.setProgress(i);
        this.e.setText(i + "/" + i2);
    }
}
