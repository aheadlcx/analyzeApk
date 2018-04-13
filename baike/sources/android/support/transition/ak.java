package android.support.transition;

import android.animation.Animator;
import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class ak implements al {
    private static Method a;
    private static boolean b;

    ak() {
    }

    public void startAnimateTransform(ImageView imageView) {
    }

    public void animateTransform(ImageView imageView, Matrix matrix) {
        a();
        if (a != null) {
            try {
                a.invoke(imageView, new Object[]{matrix});
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public void reserveEndAnimateTransform(ImageView imageView, Animator animator) {
    }

    private void a() {
        if (!b) {
            try {
                a = ImageView.class.getDeclaredMethod("animateTransform", new Class[]{Matrix.class});
                a.setAccessible(true);
            } catch (Throwable e) {
                Log.i("ImageViewUtilsApi21", "Failed to retrieve animateTransform method", e);
            }
            b = true;
        }
    }
}
