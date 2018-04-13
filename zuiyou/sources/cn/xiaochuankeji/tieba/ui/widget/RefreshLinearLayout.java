package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

public class RefreshLinearLayout extends LinearLayout {
    private int a;
    private a b;
    private boolean c;
    private View d;
    private float e;
    private float f;
    private boolean g;
    private boolean h;

    public interface a {
        void a();

        void a(float f);

        void b(float f);
    }

    public RefreshLinearLayout(Context context) {
        this(context, null);
    }

    public RefreshLinearLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RefreshLinearLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    public void setOnScrollListener(a aVar) {
        this.b = aVar;
    }

    public void setRefreshing(boolean z) {
        this.g = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.d == null) {
            this.d = getChildAt(0);
        }
        if (a() || this.g) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.c = false;
                this.e = motionEvent.getY();
                this.h = true;
                break;
            case 1:
            case 3:
                this.c = false;
                break;
            case 2:
                a(motionEvent.getY());
                break;
        }
        return this.c;
    }

    private void a(float f) {
        if (f - this.e > ((float) this.a) && !this.c) {
            this.f = this.e + ((float) this.a);
            this.c = true;
        }
    }

    public boolean a() {
        return this.d.getScrollY() > 0;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (a()) {
            return false;
        }
        if (!this.h) {
            return true;
        }
        switch (action) {
            case 0:
                this.c = false;
                break;
            case 1:
                if (this.c) {
                    float y = (motionEvent.getY() - this.f) * 0.5f;
                    this.c = false;
                    if (this.b != null) {
                        this.b.b(y);
                    }
                }
                this.h = false;
                return false;
            case 2:
                float y2 = motionEvent.getY();
                a(y2);
                if (this.c) {
                    y2 = (y2 - this.f) * 0.5f;
                    if (y2 > 0.0f) {
                        if (this.b != null) {
                            this.b.a(y2);
                            break;
                        }
                    } else if (this.b == null) {
                        return false;
                    } else {
                        this.b.a();
                        return false;
                    }
                }
                break;
            case 3:
                this.h = false;
                return false;
        }
        return true;
    }
}
