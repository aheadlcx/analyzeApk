package cn.xiaochuankeji.tieba.ui.videomaker.sticker;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

public class TouchProcessStickerLayout extends FrameLayout {
    private View a;
    private View b;
    private int c;
    private boolean d;
    private float e;
    private float f;

    public TouchProcessStickerLayout(@NonNull Context context) {
        super(context);
        a(context);
    }

    public TouchProcessStickerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TouchProcessStickerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void a(View view, View view2) {
        this.a = view;
        this.b = view2;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.e = motionEvent.getX();
            this.f = motionEvent.getY();
            this.d = false;
        }
        if (this.b.getVisibility() != 0 || this.d) {
            z = false;
        } else {
            z = this.b.dispatchTouchEvent(motionEvent);
        }
        if (!(z || this.d || action != 2)) {
            float y = motionEvent.getY() - this.f;
            if (Math.abs(motionEvent.getX() - this.e) > ((float) this.c) || Math.abs(y) > ((float) this.c)) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, this.e, this.f, 0);
                this.a.dispatchTouchEvent(obtain);
                obtain.recycle();
                this.d = true;
            }
        }
        if (this.d) {
            this.a.dispatchTouchEvent(motionEvent);
        }
        return true;
    }
}
