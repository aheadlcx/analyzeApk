package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParser;

public class ChangeTransform extends Transition {
    private static final String[] g = new String[]{"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    private static final Property<b, float[]> h = new t(float[].class, "nonTranslations");
    private static final Property<b, PointF> i = new u(PointF.class, "translations");
    private static final boolean j;
    private boolean k = true;
    private boolean l = true;
    private Matrix m = new Matrix();

    private static class a extends TransitionListenerAdapter {
        private View a;
        private GhostViewImpl b;

        a(View view, GhostViewImpl ghostViewImpl) {
            this.a = view;
            this.b = ghostViewImpl;
        }

        public void onTransitionEnd(@NonNull Transition transition) {
            transition.removeListener(this);
            ag.a(this.a);
            this.a.setTag(R.id.transition_transform, null);
            this.a.setTag(R.id.parent_matrix, null);
        }

        public void onTransitionPause(@NonNull Transition transition) {
            this.b.setVisibility(4);
        }

        public void onTransitionResume(@NonNull Transition transition) {
            this.b.setVisibility(0);
        }
    }

    private static class b {
        private final Matrix a = new Matrix();
        private final View b;
        private final float[] c;
        private float d;
        private float e;

        b(View view, float[] fArr) {
            this.b = view;
            this.c = (float[]) fArr.clone();
            this.d = this.c[2];
            this.e = this.c[5];
            b();
        }

        void a(float[] fArr) {
            System.arraycopy(fArr, 0, this.c, 0, fArr.length);
            b();
        }

        void a(PointF pointF) {
            this.d = pointF.x;
            this.e = pointF.y;
            b();
        }

        private void b() {
            this.c[2] = this.d;
            this.c[5] = this.e;
            this.a.setValues(this.c);
            bz.c(this.b, this.a);
        }

        Matrix a() {
            return this.a;
        }
    }

    private static class c {
        final float a;
        final float b;
        final float c;
        final float d;
        final float e;
        final float f;
        final float g;
        final float h;

        c(View view) {
            this.a = view.getTranslationX();
            this.b = view.getTranslationY();
            this.c = ViewCompat.getTranslationZ(view);
            this.d = view.getScaleX();
            this.e = view.getScaleY();
            this.f = view.getRotationX();
            this.g = view.getRotationY();
            this.h = view.getRotation();
        }

        public void restore(View view) {
            ChangeTransform.b(view, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (cVar.a == this.a && cVar.b == this.b && cVar.c == this.c && cVar.d == this.d && cVar.e == this.e && cVar.f == this.f && cVar.g == this.g && cVar.h == this.h) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int floatToIntBits;
            int i = 0;
            int floatToIntBits2 = (this.a != 0.0f ? Float.floatToIntBits(this.a) : 0) * 31;
            if (this.b != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.b);
            } else {
                floatToIntBits = 0;
            }
            floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
            if (this.c != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.c);
            } else {
                floatToIntBits = 0;
            }
            floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
            if (this.d != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.d);
            } else {
                floatToIntBits = 0;
            }
            floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
            if (this.e != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.e);
            } else {
                floatToIntBits = 0;
            }
            floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
            if (this.f != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.f);
            } else {
                floatToIntBits = 0;
            }
            floatToIntBits2 = (floatToIntBits + floatToIntBits2) * 31;
            if (this.g != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.g);
            } else {
                floatToIntBits = 0;
            }
            floatToIntBits = (floatToIntBits + floatToIntBits2) * 31;
            if (this.h != 0.0f) {
                i = Float.floatToIntBits(this.h);
            }
            return floatToIntBits + i;
        }
    }

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 21) {
            z = false;
        }
        j = z;
    }

    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, be.g);
        this.k = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlPullParser) attributeSet, "reparentWithOverlay", 1, true);
        this.l = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlPullParser) attributeSet, "reparent", 0, true);
        obtainStyledAttributes.recycle();
    }

    public boolean getReparentWithOverlay() {
        return this.k;
    }

    public void setReparentWithOverlay(boolean z) {
        this.k = z;
    }

    public boolean getReparent() {
        return this.l;
    }

    public void setReparent(boolean z) {
        this.l = z;
    }

    public String[] getTransitionProperties() {
        return g;
    }

    private void b(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() != 8) {
            Object obj;
            transitionValues.values.put("android:changeTransform:parent", view.getParent());
            transitionValues.values.put("android:changeTransform:transforms", new c(view));
            Matrix matrix = view.getMatrix();
            if (matrix == null || matrix.isIdentity()) {
                obj = null;
            } else {
                obj = new Matrix(matrix);
            }
            transitionValues.values.put("android:changeTransform:matrix", obj);
            if (this.l) {
                matrix = new Matrix();
                View view2 = (ViewGroup) view.getParent();
                bz.a(view2, matrix);
                matrix.preTranslate((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
                transitionValues.values.put("android:changeTransform:parentMatrix", matrix);
                transitionValues.values.put("android:changeTransform:intermediateMatrix", view.getTag(R.id.transition_transform));
                transitionValues.values.put("android:changeTransform:intermediateParentMatrix", view.getTag(R.id.parent_matrix));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
        if (!j) {
            ((ViewGroup) transitionValues.view.getParent()).startViewTransition(transitionValues.view);
        }
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || !transitionValues.values.containsKey("android:changeTransform:parent") || !transitionValues2.values.containsKey("android:changeTransform:parent")) {
            return null;
        }
        boolean z;
        ViewGroup viewGroup2 = (ViewGroup) transitionValues.values.get("android:changeTransform:parent");
        ViewGroup viewGroup3 = (ViewGroup) transitionValues2.values.get("android:changeTransform:parent");
        if (!this.l || a(viewGroup2, viewGroup3)) {
            z = false;
        } else {
            z = true;
        }
        Matrix matrix = (Matrix) transitionValues.values.get("android:changeTransform:intermediateMatrix");
        if (matrix != null) {
            transitionValues.values.put("android:changeTransform:matrix", matrix);
        }
        matrix = (Matrix) transitionValues.values.get("android:changeTransform:intermediateParentMatrix");
        if (matrix != null) {
            transitionValues.values.put("android:changeTransform:parentMatrix", matrix);
        }
        if (z) {
            a(transitionValues, transitionValues2);
        }
        Animator a = a(transitionValues, transitionValues2, z);
        if (z && a != null && this.k) {
            a(viewGroup, transitionValues, transitionValues2);
        } else if (!j) {
            viewGroup2.endViewTransition(transitionValues.view);
        }
        return a;
    }

    private ObjectAnimator a(TransitionValues transitionValues, TransitionValues transitionValues2, boolean z) {
        Matrix matrix;
        Matrix matrix2 = (Matrix) transitionValues.values.get("android:changeTransform:matrix");
        Matrix matrix3 = (Matrix) transitionValues2.values.get("android:changeTransform:matrix");
        if (matrix2 == null) {
            matrix2 = am.a;
        }
        if (matrix3 == null) {
            matrix = am.a;
        } else {
            matrix = matrix3;
        }
        if (matrix2.equals(matrix)) {
            return null;
        }
        c cVar = (c) transitionValues2.values.get("android:changeTransform:transforms");
        View view = transitionValues2.view;
        c(view);
        r1 = new float[9];
        matrix2.getValues(r1);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        b bVar = new b(view, r1);
        PropertyValuesHolder ofObject = PropertyValuesHolder.ofObject(h, new x(new float[9]), new float[][]{r1, fArr});
        PropertyValuesHolder a = at.a(i, getPathMotion().getPath(r1[2], r1[5], fArr[2], fArr[5]));
        Animator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bVar, new PropertyValuesHolder[]{ofObject, a});
        Object vVar = new v(this, z, matrix, view, cVar, bVar);
        ofPropertyValuesHolder.addListener(vVar);
        a.a(ofPropertyValuesHolder, vVar);
        return ofPropertyValuesHolder;
    }

    private boolean a(ViewGroup viewGroup, ViewGroup viewGroup2) {
        if (b((View) viewGroup) && b((View) viewGroup2)) {
            TransitionValues a = a((View) viewGroup, true);
            if (a == null) {
                return false;
            }
            if (viewGroup2 != a.view) {
                return false;
            }
            return true;
        } else if (viewGroup == viewGroup2) {
            return true;
        } else {
            return false;
        }
    }

    private void a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        View view = transitionValues2.view;
        Matrix matrix = new Matrix((Matrix) transitionValues2.values.get("android:changeTransform:parentMatrix"));
        bz.b(viewGroup, matrix);
        GhostViewImpl a = ag.a(view, viewGroup, matrix);
        if (a != null) {
            Transition transition;
            a.reserveEndViewTransition((ViewGroup) transitionValues.values.get("android:changeTransform:parent"), transitionValues.view);
            while (transition.d != null) {
                transition = transition.d;
            }
            transition.addListener(new a(view, a));
            if (j) {
                if (transitionValues.view != transitionValues2.view) {
                    bz.a(transitionValues.view, 0.0f);
                }
                bz.a(view, 1.0f);
            }
        }
    }

    private void a(TransitionValues transitionValues, TransitionValues transitionValues2) {
        Matrix matrix;
        Matrix matrix2 = (Matrix) transitionValues2.values.get("android:changeTransform:parentMatrix");
        transitionValues2.view.setTag(R.id.parent_matrix, matrix2);
        Matrix matrix3 = this.m;
        matrix3.reset();
        matrix2.invert(matrix3);
        matrix2 = (Matrix) transitionValues.values.get("android:changeTransform:matrix");
        if (matrix2 == null) {
            matrix2 = new Matrix();
            transitionValues.values.put("android:changeTransform:matrix", matrix2);
            matrix = matrix2;
        } else {
            matrix = matrix2;
        }
        matrix.postConcat((Matrix) transitionValues.values.get("android:changeTransform:parentMatrix"));
        matrix.postConcat(matrix3);
    }

    private static void c(View view) {
        b(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    private static void b(View view, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        view.setTranslationX(f);
        view.setTranslationY(f2);
        ViewCompat.setTranslationZ(view, f3);
        view.setScaleX(f4);
        view.setScaleY(f5);
        view.setRotationX(f6);
        view.setRotationY(f7);
        view.setRotation(f8);
    }
}
