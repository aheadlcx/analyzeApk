package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Map;

public class ChangeImageTransform extends Transition {
    private static final String[] g = new String[]{"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
    private static final TypeEvaluator<Matrix> h = new p();
    private static final Property<ImageView, Matrix> i = new q(Matrix.class, "animatedTransform");

    public ChangeImageTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void b(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() != null) {
                Map map = transitionValues.values;
                map.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
                map.put("android:changeImageTransform:matrix", b(imageView));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public String[] getTransitionProperties() {
        return g;
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues.values.get("android:changeImageTransform:bounds");
        Rect rect2 = (Rect) transitionValues2.values.get("android:changeImageTransform:bounds");
        if (rect == null || rect2 == null) {
            return null;
        }
        Matrix matrix = (Matrix) transitionValues.values.get("android:changeImageTransform:matrix");
        Matrix matrix2 = (Matrix) transitionValues2.values.get("android:changeImageTransform:matrix");
        Object obj = (!(matrix == null && matrix2 == null) && (matrix == null || !matrix.equals(matrix2))) ? null : 1;
        if (rect.equals(rect2) && obj != null) {
            return null;
        }
        Animator a;
        ImageView imageView = (ImageView) transitionValues2.view;
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        ah.a(imageView);
        if (intrinsicWidth == 0 || intrinsicHeight == 0) {
            a = a(imageView);
        } else {
            if (matrix == null) {
                matrix = am.a;
            }
            if (matrix2 == null) {
                matrix2 = am.a;
            }
            i.set(imageView, matrix);
            a = a(imageView, matrix, matrix2);
        }
        ah.a(imageView, a);
        return a;
    }

    private ObjectAnimator a(ImageView imageView) {
        return ObjectAnimator.ofObject(imageView, i, h, new Matrix[]{null, null});
    }

    private ObjectAnimator a(ImageView imageView, Matrix matrix, Matrix matrix2) {
        return ObjectAnimator.ofObject(imageView, i, new a(), new Matrix[]{matrix, matrix2});
    }

    private static Matrix b(ImageView imageView) {
        switch (s.a[imageView.getScaleType().ordinal()]) {
            case 1:
                return c(imageView);
            case 2:
                return d(imageView);
            default:
                return new Matrix(imageView.getImageMatrix());
        }
    }

    private static Matrix c(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) imageView.getWidth()) / ((float) drawable.getIntrinsicWidth()), ((float) imageView.getHeight()) / ((float) drawable.getIntrinsicHeight()));
        return matrix;
    }

    private static Matrix d(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int width = imageView.getWidth();
        float f = ((float) width) / ((float) intrinsicWidth);
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int height = imageView.getHeight();
        f = Math.max(f, ((float) height) / ((float) intrinsicHeight));
        float f2 = ((float) intrinsicHeight) * f;
        intrinsicWidth = Math.round((((float) width) - (((float) intrinsicWidth) * f)) / 2.0f);
        intrinsicHeight = Math.round((((float) height) - f2) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        matrix.postTranslate((float) intrinsicWidth, (float) intrinsicHeight);
        return matrix;
    }
}
