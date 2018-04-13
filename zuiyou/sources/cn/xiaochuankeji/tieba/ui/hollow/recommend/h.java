package cn.xiaochuankeji.tieba.ui.hollow.recommend;

import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.e.d;
import com.facebook.e.e;
import com.facebook.e.i;

public class h {
    public static void a(final View view, final Runnable runnable) {
        if (VERSION.SDK_INT >= 11) {
            final e b = i.c().b();
            b.a(new d() {
                public void a(e eVar) {
                    float b = 1.0f - (((float) eVar.b()) * 0.2f);
                    view.setScaleX(b);
                    view.setScaleY(b);
                }
            });
            view.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            b.a(1.0d);
                            break;
                        case 1:
                            if (runnable != null) {
                                runnable.run();
                            }
                            b.a(0.0d);
                            view.playSoundEffect(0);
                            break;
                        case 3:
                            b.a(0.0d);
                            break;
                        case 4:
                            b.a(0.0d);
                            break;
                    }
                    return true;
                }
            });
        } else if (runnable != null) {
            runnable.run();
        }
    }
}
