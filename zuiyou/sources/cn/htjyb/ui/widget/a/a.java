package cn.htjyb.ui.widget.a;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.GridView;
import com.izuiyou.a.a.b;

public class a extends GridView {
    private a a;
    private long b = 0;
    private boolean c = false;

    public interface a {
        void a();

        void b();
    }

    public a(Context context) {
        super(context);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY()) == -1) {
            if (motionEvent.getAction() == 0) {
                this.b = System.currentTimeMillis();
            } else if (motionEvent.getAction() == 1) {
                r0 = System.currentTimeMillis() - this.b;
                if (this.a != null) {
                    if (r0 > 600) {
                        this.b = System.currentTimeMillis();
                        this.a.b();
                        b.e("调用了onLongClick1");
                    } else if (!this.c) {
                        this.a.a();
                        b.e("调用了onClick");
                    }
                }
                this.c = false;
            } else if (motionEvent.getAction() == 2) {
                r0 = System.currentTimeMillis() - this.b;
                if (this.a != null && r0 > 600) {
                    this.b = Long.MAX_VALUE;
                    this.a.b();
                    this.c = true;
                    b.e("调用了onLongClick2");
                }
            } else if (motionEvent.getAction() == 3) {
                this.b = Long.MAX_VALUE;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setOnBlankAreaClickListener(a aVar) {
        this.a = aVar;
    }
}
