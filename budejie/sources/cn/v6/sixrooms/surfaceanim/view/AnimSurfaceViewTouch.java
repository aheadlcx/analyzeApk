package cn.v6.sixrooms.surfaceanim.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.ViewConfiguration;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.SurfaceTouchEvent;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.SurfaceTouchManager;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.TouchEntity;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.TouchEntity.TouchParameter;
import java.util.ArrayList;
import java.util.List;

public class AnimSurfaceViewTouch extends AnimSurfaceView implements Callback {
    private List<TouchEntity> a;
    private AnimCallback b;
    private float c;
    private float d;
    private TouchEntity e;
    private SurfaceTouchEvent f = new a(this);

    public interface AnimCallback {
        void goRoom(String str, String str2);
    }

    public void setOnAnimCallback(AnimCallback animCallback) {
        this.b = animCallback;
    }

    public AnimSurfaceViewTouch(Context context) {
        super(context);
        a();
    }

    public AnimSurfaceViewTouch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AnimSurfaceViewTouch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public AnimSurfaceViewTouch(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        this.a = new ArrayList();
        setZOrderOnTop(true);
        getHolder().setFormat(-3);
        setBackgroundColor(0);
        getHolder().addCallback(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        TouchEntity touchEntity;
        switch (motionEvent.getAction()) {
            case 0:
                synchronized (this.f) {
                    for (TouchEntity touchEntity2 : this.a) {
                        if (touchEntity2.getRect().contains((int) x, (int) y)) {
                            this.c = x;
                            this.d = y;
                            this.e = touchEntity2;
                            return true;
                        }
                    }
                    break;
                }
            case 1:
                int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
                float f = this.c - ((float) scaledTouchSlop);
                float f2 = this.c + ((float) scaledTouchSlop);
                float f3 = this.d - ((float) scaledTouchSlop);
                float f4 = ((float) scaledTouchSlop) + this.d;
                if (x > f && x < f2 && y > f3 && y < f4) {
                    touchEntity2 = this.e;
                    if (touchEntity2 != null) {
                        switch (touchEntity2.getWhat()) {
                            case 100:
                                TouchParameter touchParameter = touchEntity2.getTouchParameter();
                                if (!(this.b == null || touchParameter == null)) {
                                    this.b.goRoom(touchParameter.getRid(), touchParameter.getUid());
                                    break;
                                }
                        }
                    }
                    return true;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        SurfaceTouchManager.getDefault().attach(this.f);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        cleanData();
        SurfaceTouchManager.getDefault().detach(this.f);
    }

    public void cleanData() {
        if (this.a != null) {
            this.e = null;
            this.a.clear();
        }
    }
}
