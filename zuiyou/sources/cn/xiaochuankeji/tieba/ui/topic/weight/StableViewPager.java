package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class StableViewPager extends ViewPager {
    float a;
    float b;
    private int c;

    public StableViewPager(@NonNull Context context) {
        super(context);
        a();
    }

    public StableViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.c = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(getContext()));
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (getAdapter() == null || getAdapter().getCount() > 1) {
            if (getCurrentItem() == 0) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                switch (motionEvent.getAction()) {
                    case 1:
                        getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    case 2:
                        float abs = Math.abs(x - this.b);
                        float abs2 = Math.abs(y - this.b);
                        if (abs <= 0.0f || x - this.a >= 0.0f || 0.5f * abs <= abs2) {
                            if (abs2 > ((float) this.c) || abs < ((float) this.c)) {
                                getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                            }
                        }
                        getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                        break;
                }
                this.a = x;
                this.b = y;
            } else {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        getParent().requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(motionEvent);
    }
}
