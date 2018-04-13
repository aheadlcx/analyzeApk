package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public class ChangeBounds extends Transition {
    private static final String[] g = new String[]{"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property<Drawable, PointF> h = new e(PointF.class, "boundsOrigin");
    private static final Property<a, PointF> i = new g(PointF.class, "topLeft");
    private static final Property<a, PointF> j = new h(PointF.class, "bottomRight");
    private static final Property<View, PointF> k = new i(PointF.class, "bottomRight");
    private static final Property<View, PointF> l = new j(PointF.class, "topLeft");
    private static final Property<View, PointF> m = new k(PointF.class, "position");
    private static ax q = new ax();
    private int[] n;
    private boolean o;
    private boolean p;

    private static class a {
        private int a;
        private int b;
        private int c;
        private int d;
        private View e;
        private int f;
        private int g;

        a(View view) {
            this.e = view;
        }

        void a(PointF pointF) {
            this.a = Math.round(pointF.x);
            this.b = Math.round(pointF.y);
            this.f++;
            if (this.f == this.g) {
                a();
            }
        }

        void b(PointF pointF) {
            this.c = Math.round(pointF.x);
            this.d = Math.round(pointF.y);
            this.g++;
            if (this.f == this.g) {
                a();
            }
        }

        private void a() {
            bz.a(this.e, this.a, this.b, this.c, this.d);
            this.f = 0;
            this.g = 0;
        }
    }

    public ChangeBounds() {
        this.n = new int[2];
        this.o = false;
        this.p = false;
    }

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = new int[2];
        this.o = false;
        this.p = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, be.d);
        boolean namedBoolean = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlResourceParser) attributeSet, "resizeClip", 0, false);
        obtainStyledAttributes.recycle();
        setResizeClip(namedBoolean);
    }

    @Nullable
    public String[] getTransitionProperties() {
        return g;
    }

    public void setResizeClip(boolean z) {
        this.o = z;
    }

    public boolean getResizeClip() {
        return this.o;
    }

    private void b(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
            if (this.p) {
                transitionValues.view.getLocationInWindow(this.n);
                transitionValues.values.put("android:changeBounds:windowX", Integer.valueOf(this.n[0]));
                transitionValues.values.put("android:changeBounds:windowY", Integer.valueOf(this.n[1]));
            }
            if (this.o) {
                transitionValues.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    private boolean a(View view, View view2) {
        if (!this.p) {
            return true;
        }
        TransitionValues a = a(view, true);
        if (a == null) {
            if (view == view2) {
                return true;
            }
            return false;
        } else if (view2 != a.view) {
            return false;
        } else {
            return true;
        }
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Map map = transitionValues.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) transitionValues2.values.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view = transitionValues2.view;
        int i;
        int i2;
        Animator a;
        if (a(viewGroup2, viewGroup3)) {
            Rect rect = (Rect) transitionValues.values.get("android:changeBounds:bounds");
            Rect rect2 = (Rect) transitionValues2.values.get("android:changeBounds:bounds");
            int i3 = rect.left;
            i = rect2.left;
            int i4 = rect.top;
            int i5 = rect2.top;
            int i6 = rect.right;
            int i7 = rect2.right;
            int i8 = rect.bottom;
            int i9 = rect2.bottom;
            int i10 = i6 - i3;
            int i11 = i8 - i4;
            int i12 = i7 - i;
            int i13 = i9 - i5;
            rect = (Rect) transitionValues.values.get("android:changeBounds:clip");
            Rect rect3 = (Rect) transitionValues2.values.get("android:changeBounds:clip");
            i2 = 0;
            if (!((i10 == 0 || i11 == 0) && (i12 == 0 || i13 == 0))) {
                if (!(i3 == i && i4 == i5)) {
                    i2 = 1;
                }
                if (!(i6 == i7 && i8 == i9)) {
                    i2++;
                }
            }
            if (!(rect == null || rect.equals(rect3)) || (rect == null && rect3 != null)) {
                i2++;
            }
            if (i2 > 0) {
                Animator animator;
                if (this.o) {
                    Animator animator2;
                    Rect rect4;
                    Object rect5;
                    bz.a(view, i3, i4, Math.max(i10, i12) + i3, Math.max(i11, i13) + i4);
                    if (i3 == i && i4 == i5) {
                        animator2 = null;
                    } else {
                        animator2 = ao.a(view, m, getPathMotion().getPath((float) i3, (float) i4, (float) i, (float) i5));
                    }
                    if (rect == null) {
                        rect4 = new Rect(0, 0, i10, i11);
                    } else {
                        rect4 = rect;
                    }
                    if (rect3 == null) {
                        rect5 = new Rect(0, 0, i12, i13);
                    } else {
                        rect = rect3;
                    }
                    if (rect4.equals(rect5)) {
                        animator = null;
                    } else {
                        ViewCompat.setClipBounds(view, rect4);
                        Animator ofObject = ObjectAnimator.ofObject(view, "clipBounds", q, new Object[]{rect4, rect5});
                        ofObject.addListener(new m(this, view, rect3, i, i5, i7, i9));
                        animator = ofObject;
                    }
                    a = bk.a(animator2, animator);
                } else {
                    bz.a(view, i3, i4, i6, i8);
                    if (i2 == 2) {
                        if (i10 == i12 && i11 == i13) {
                            a = ao.a(view, m, getPathMotion().getPath((float) i3, (float) i4, (float) i, (float) i5));
                        } else {
                            a aVar = new a(view);
                            ObjectAnimator a2 = ao.a(aVar, i, getPathMotion().getPath((float) i3, (float) i4, (float) i, (float) i5));
                            ObjectAnimator a3 = ao.a(aVar, j, getPathMotion().getPath((float) i6, (float) i8, (float) i7, (float) i9));
                            animator = new AnimatorSet();
                            animator.playTogether(new Animator[]{a2, a3});
                            animator.addListener(new l(this, aVar));
                            a = animator;
                        }
                    } else if (i3 == i && i4 == i5) {
                        a = ao.a(view, k, getPathMotion().getPath((float) i6, (float) i8, (float) i7, (float) i9));
                    } else {
                        a = ao.a(view, l, getPathMotion().getPath((float) i3, (float) i4, (float) i, (float) i5));
                    }
                }
                if (!(view.getParent() instanceof ViewGroup)) {
                    return a;
                }
                viewGroup2 = (ViewGroup) view.getParent();
                br.a(viewGroup2, true);
                addListener(new n(this, viewGroup2));
                return a;
            }
        }
        i2 = ((Integer) transitionValues.values.get("android:changeBounds:windowX")).intValue();
        int intValue = ((Integer) transitionValues.values.get("android:changeBounds:windowY")).intValue();
        i = ((Integer) transitionValues2.values.get("android:changeBounds:windowX")).intValue();
        int intValue2 = ((Integer) transitionValues2.values.get("android:changeBounds:windowY")).intValue();
        if (!(i2 == i && intValue == intValue2)) {
            viewGroup.getLocationInWindow(this.n);
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            view.draw(new Canvas(createBitmap));
            Drawable bitmapDrawable = new BitmapDrawable(createBitmap);
            float c = bz.c(view);
            bz.a(view, 0.0f);
            bz.a(viewGroup).add(bitmapDrawable);
            PropertyValuesHolder a4 = at.a(h, getPathMotion().getPath((float) (i2 - this.n[0]), (float) (intValue - this.n[1]), (float) (i - this.n[0]), (float) (intValue2 - this.n[1])));
            a = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, new PropertyValuesHolder[]{a4});
            a.addListener(new f(this, viewGroup, bitmapDrawable, view, c));
            return a;
        }
        return null;
    }
}
