package cn.xiaochuankeji.tieba.ui.my.assessor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.izuiyou.a.a.b;

public class MonitorLeftFlingFrameLayout extends FrameLayout {
    private int a;
    private int b;
    private int c;
    private int d;
    private float e;
    private float f = (this.e * 10.0f);
    private float g = (this.e * 10.0f);
    private long h;
    private a i;

    public interface a {
        void e();
    }

    public MonitorLeftFlingFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context.getResources().getDisplayMetrics().density;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a = (int) motionEvent.getRawX();
                this.b = (int) motionEvent.getRawY();
                this.h = System.currentTimeMillis();
                break;
            case 2:
                this.c = (int) motionEvent.getRawX();
                this.d = (int) motionEvent.getRawY();
                int i = this.d - this.b;
                if (((float) (this.a - this.c)) >= this.f && ((float) Math.abs(i)) < this.g) {
                    return true;
                }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
                int rawX = this.a - ((int) motionEvent.getRawX());
                long currentTimeMillis = System.currentTimeMillis() - this.h;
                b.c("dx: " + rawX + ", costT: " + currentTimeMillis + ", density: " + this.e);
                if (((double) (rawX * 3)) > ((double) currentTimeMillis) * Math.sqrt((double) this.e) && this.i != null) {
                    this.i.e();
                    break;
                }
        }
        return true;
    }

    public void a(a aVar) {
        this.i = aVar;
    }
}
