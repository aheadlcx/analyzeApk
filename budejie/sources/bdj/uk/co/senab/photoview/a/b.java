package bdj.uk.co.senab.photoview.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import bdj.uk.co.senab.photoview.a;

@TargetApi(5)
public class b extends a {
    private int f = -1;
    private int g = 0;

    public b(Context context) {
        super(context);
    }

    float a(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.g);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.g);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    public boolean c(MotionEvent motionEvent) {
        int i = 0;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f = motionEvent.getPointerId(0);
                break;
            case 1:
            case 3:
                this.f = -1;
                break;
            case 6:
                int a = a.a(motionEvent.getAction());
                if (motionEvent.getPointerId(a) == this.f) {
                    if (a == 0) {
                        a = 1;
                    } else {
                        a = 0;
                    }
                    this.f = motionEvent.getPointerId(a);
                    this.b = motionEvent.getX(a);
                    this.c = motionEvent.getY(a);
                    break;
                }
                break;
        }
        if (this.f != -1) {
            i = this.f;
        }
        this.g = motionEvent.findPointerIndex(i);
        return super.c(motionEvent);
    }
}
