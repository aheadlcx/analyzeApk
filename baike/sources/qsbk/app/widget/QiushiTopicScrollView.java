package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class QiushiTopicScrollView extends ScrollView {
    private boolean a = false;
    private boolean b = false;
    private boolean c;

    public QiushiTopicScrollView(Context context) {
        super(context);
    }

    public QiushiTopicScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QiushiTopicScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean getOrientation() {
        return this.c;
    }

    public void setOrientation(boolean z) {
        this.c = z;
    }

    public boolean getIsTop() {
        return this.b;
    }

    public void setIsTop(boolean z) {
        this.b = z;
    }

    public boolean isIfIntercept() {
        return this.a;
    }

    public void setIfIntercept(boolean z) {
        this.a = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a && !this.b && this.c) {
            return true;
        }
        if (!this.a && (!this.b || !this.c)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.c = false;
        return false;
    }
}
