package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.transition.Transition.TransitionListener;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Visibility extends Transition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String[] g = new String[]{"android:visibility:visibility", "android:visibility:parent"};
    private int h = 3;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private static class a extends AnimatorListenerAdapter implements TransitionListener, a {
        boolean a = false;
        private final View b;
        private final int c;
        private final ViewGroup d;
        private final boolean e;
        private boolean f;

        a(View view, int i, boolean z) {
            this.b = view;
            this.c = i;
            this.d = (ViewGroup) view.getParent();
            this.e = z;
            a(true);
        }

        public void onAnimationPause(Animator animator) {
            if (!this.a) {
                bz.a(this.b, this.c);
            }
        }

        public void onAnimationResume(Animator animator) {
            if (!this.a) {
                bz.a(this.b, 0);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            a();
        }

        public void onTransitionStart(@NonNull Transition transition) {
        }

        public void onTransitionEnd(@NonNull Transition transition) {
            a();
            transition.removeListener(this);
        }

        public void onTransitionCancel(@NonNull Transition transition) {
        }

        public void onTransitionPause(@NonNull Transition transition) {
            a(false);
        }

        public void onTransitionResume(@NonNull Transition transition) {
            a(true);
        }

        private void a() {
            if (!this.a) {
                bz.a(this.b, this.c);
                if (this.d != null) {
                    this.d.invalidate();
                }
            }
            a(false);
        }

        private void a(boolean z) {
            if (this.e && this.f != z && this.d != null) {
                this.f = z;
                br.a(this.d, z);
            }
        }
    }

    private static class b {
        boolean a;
        boolean b;
        int c;
        int d;
        ViewGroup e;
        ViewGroup f;

        private b() {
        }
    }

    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, be.e);
        int namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        obtainStyledAttributes.recycle();
        if (namedInt != 0) {
            setMode(namedInt);
        }
    }

    public void setMode(int i) {
        if ((i & -4) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.h = i;
    }

    public int getMode() {
        return this.h;
    }

    @Nullable
    public String[] getTransitionProperties() {
        return g;
    }

    private void b(TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
        Object obj = new int[2];
        transitionValues.view.getLocationOnScreen(obj);
        transitionValues.values.put("android:visibility:screenLocation", obj);
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public boolean isVisible(TransitionValues transitionValues) {
        if (transitionValues == null) {
            return false;
        }
        boolean z = ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue() == 0 && ((View) transitionValues.values.get("android:visibility:parent")) != null;
        return z;
    }

    private b a(TransitionValues transitionValues, TransitionValues transitionValues2) {
        b bVar = new b();
        bVar.a = false;
        bVar.b = false;
        if (transitionValues == null || !transitionValues.values.containsKey("android:visibility:visibility")) {
            bVar.c = -1;
            bVar.e = null;
        } else {
            bVar.c = ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue();
            bVar.e = (ViewGroup) transitionValues.values.get("android:visibility:parent");
        }
        if (transitionValues2 == null || !transitionValues2.values.containsKey("android:visibility:visibility")) {
            bVar.d = -1;
            bVar.f = null;
        } else {
            bVar.d = ((Integer) transitionValues2.values.get("android:visibility:visibility")).intValue();
            bVar.f = (ViewGroup) transitionValues2.values.get("android:visibility:parent");
        }
        if (transitionValues == null || transitionValues2 == null) {
            if (transitionValues == null && bVar.d == 0) {
                bVar.b = true;
                bVar.a = true;
            } else if (transitionValues2 == null && bVar.c == 0) {
                bVar.b = false;
                bVar.a = true;
            }
        } else if (bVar.c == bVar.d && bVar.e == bVar.f) {
            return bVar;
        } else {
            if (bVar.c != bVar.d) {
                if (bVar.c == 0) {
                    bVar.b = false;
                    bVar.a = true;
                } else if (bVar.d == 0) {
                    bVar.b = true;
                    bVar.a = true;
                }
            } else if (bVar.f == null) {
                bVar.b = false;
                bVar.a = true;
            } else if (bVar.e == null) {
                bVar.b = true;
                bVar.a = true;
            }
        }
        return bVar;
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        b a = a(transitionValues, transitionValues2);
        if (!a.a || (a.e == null && a.f == null)) {
            return null;
        }
        if (a.b) {
            return onAppear(viewGroup, transitionValues, a.c, transitionValues2, a.d);
        }
        return onDisappear(viewGroup, transitionValues, a.c, transitionValues2, a.d);
    }

    public Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        if ((this.h & 1) != 1 || transitionValues2 == null) {
            return null;
        }
        if (transitionValues == null) {
            View view = (View) transitionValues2.view.getParent();
            if (a(a(view, false), getTransitionValues(view, false)).a) {
                return null;
            }
        }
        return onAppear(viewGroup, transitionValues2.view, transitionValues, transitionValues2);
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public Animator onDisappear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        Animator animator = null;
        if ((this.h & 2) == 2) {
            View view;
            int id;
            View view2 = transitionValues != null ? transitionValues.view : null;
            if (transitionValues2 != null) {
                view = transitionValues2.view;
            } else {
                view = null;
            }
            if (view == null || view.getParent() == null) {
                if (view != null) {
                    view2 = view;
                    view = null;
                } else {
                    if (view2 != null) {
                        if (view2.getParent() == null) {
                            view = null;
                        } else if (view2.getParent() instanceof View) {
                            view = (View) view2.getParent();
                            if (a(getTransitionValues(view, true), a(view, true)).a) {
                                if (view.getParent() == null) {
                                    id = view.getId();
                                    if (!(id == -1 || viewGroup.findViewById(id) == null || !this.e)) {
                                        view = view2;
                                    }
                                }
                                view = null;
                            } else {
                                view = bk.a(viewGroup, view2, view);
                            }
                            view2 = view;
                            view = null;
                        }
                    }
                    view = null;
                    view2 = null;
                }
            } else if (i2 == 4) {
                view2 = null;
            } else if (view2 == view) {
                view2 = null;
            } else {
                view = null;
            }
            if (view2 != null && transitionValues != null) {
                int[] iArr = (int[]) transitionValues.values.get("android:visibility:screenLocation");
                int i3 = iArr[0];
                id = iArr[1];
                int[] iArr2 = new int[2];
                viewGroup.getLocationOnScreen(iArr2);
                view2.offsetLeftAndRight((i3 - iArr2[0]) - view2.getLeft());
                view2.offsetTopAndBottom((id - iArr2[1]) - view2.getTop());
                bq a = br.a(viewGroup);
                a.add(view2);
                animator = onDisappear(viewGroup, view2, transitionValues, transitionValues2);
                if (animator == null) {
                    a.remove(view2);
                } else {
                    animator.addListener(new ci(this, a, view2));
                }
            } else if (view != null) {
                int visibility = view.getVisibility();
                bz.a(view, 0);
                animator = onDisappear(viewGroup, view, transitionValues, transitionValues2);
                if (animator != null) {
                    TransitionListener aVar = new a(view, i2, true);
                    animator.addListener(aVar);
                    a.a(animator, aVar);
                    addListener(aVar);
                } else {
                    bz.a(view, visibility);
                }
            }
        }
        return animator;
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null && transitionValues2.values.containsKey("android:visibility:visibility") != transitionValues.values.containsKey("android:visibility:visibility")) {
            return false;
        }
        b a = a(transitionValues, transitionValues2);
        if (!a.a) {
            return false;
        }
        if (a.c == 0 || a.d == 0) {
            return true;
        }
        return false;
    }
}
