package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PressListenerView extends AppCompatImageView {
    private a a;

    public interface a {
        void a();

        void b();
    }

    public PressListenerView(Context context) {
        super(context);
    }

    public PressListenerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PressListenerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a.a();
                return true;
            case 1:
                this.a.b();
                return false;
            default:
                return false;
        }
    }

    public void setOnPressListener(a aVar) {
        this.a = aVar;
    }
}
