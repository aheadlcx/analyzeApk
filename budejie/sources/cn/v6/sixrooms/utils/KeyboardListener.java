package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;

public class KeyboardListener {
    public static final int KEY_CLOSE_STATE = 1;
    public static final int KEY_OPEN_STATE = 0;
    Runnable a = new s(this);
    private Activity b;
    private Handler c;
    private int d;
    private int e;
    private OnListener f;
    private int g = 1;
    private boolean h = true;

    public interface OnListener {
        void keyStatechanged(int i, int i2);
    }

    public KeyboardListener(Activity activity) {
        this.b = activity;
        this.c = new Handler();
    }

    public void updateDisplaySize() {
        this.b.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        Rect rect = new Rect();
        this.b.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        this.d = rect.height();
        if (this.h) {
            this.e = this.d;
            this.h = false;
        }
        int i = this.e - this.d;
        if (this.g == 1 && this.e != this.d) {
            this.g = 0;
            if (this.f != null) {
                this.f.keyStatechanged(0, i);
            }
        } else if (this.g == 0 && this.e == this.d) {
            this.g = 1;
            if (this.f != null) {
                this.f.keyStatechanged(1, i);
            }
        }
    }

    public boolean isOpen() {
        if (this.g == 0) {
            return true;
        }
        return false;
    }

    public void startTask() {
        this.c.postDelayed(this.a, 3000);
    }

    public void startTask(long j) {
        this.c.postDelayed(this.a, j);
    }

    public void stopTask() {
        this.c.removeCallbacks(this.a);
    }

    public void setOnListener(OnListener onListener) {
        this.f = onListener;
    }
}
