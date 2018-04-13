package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import cn.xiaochuankeji.tieba.widget.fits.b;
import com.c.a.c;
import com.c.a.e;

public class TBViewPager extends b {
    private boolean a = true;
    private boolean b = false;
    private a c;

    public interface a {
        void a(MotionEvent motionEvent);
    }

    public TBViewPager(Context context) {
        super(context);
    }

    public TBViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        super.setAdapter(pagerAdapter);
        Context context = getContext();
        if (context instanceof Activity) {
            e a = c.a((Activity) context);
            if (a != null) {
                a.a(0.15f);
            }
        }
    }

    public void a() {
        this.a = true;
        c();
    }

    public void b() {
        c();
        this.a = false;
    }

    private void c() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        dispatchTouchEvent(obtain);
        obtain.recycle();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.a) {
            try {
                return super.onTouchEvent(motionEvent);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.c != null) {
            this.c.a(motionEvent);
        }
        if (!this.a) {
            return false;
        }
        try {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.b = false;
            } else if (action == 2 && !this.b) {
                this.b = true;
                return false;
            }
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void setTouchListener(a aVar) {
        this.c = aVar;
    }
}
