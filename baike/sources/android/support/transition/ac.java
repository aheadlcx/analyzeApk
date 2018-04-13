package android.support.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.GhostViewImpl.Creator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;

@SuppressLint({"ViewConstructor"})
@RequiresApi(14)
class ac extends View implements GhostViewImpl {
    final View a;
    ViewGroup b;
    View c;
    int d;
    Matrix e;
    private int f;
    private int g;
    private final Matrix h = new Matrix();
    private final OnPreDrawListener i = new ad(this);

    static class a implements Creator {
        a() {
        }

        public GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
            GhostViewImpl a = ac.a(view);
            if (a == null) {
                FrameLayout a2 = a(viewGroup);
                if (a2 == null) {
                    return null;
                }
                a = new ac(view);
                a2.addView(a);
            }
            a.d++;
            return a;
        }

        public void removeGhost(View view) {
            View a = ac.a(view);
            if (a != null) {
                a.d--;
                if (a.d <= 0) {
                    ViewParent parent = a.getParent();
                    if (parent instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) parent;
                        viewGroup.endViewTransition(a);
                        viewGroup.removeView(a);
                    }
                }
            }
        }

        private static FrameLayout a(ViewGroup viewGroup) {
            ViewGroup viewGroup2 = viewGroup;
            while (!(viewGroup2 instanceof FrameLayout)) {
                ViewParent parent = viewGroup2.getParent();
                if (!(parent instanceof ViewGroup)) {
                    return null;
                }
                viewGroup2 = (ViewGroup) parent;
            }
            return (FrameLayout) viewGroup2;
        }
    }

    ac(View view) {
        super(view.getContext());
        this.a = view;
        setLayerType(2, null);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(this.a, this);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr);
        this.a.getLocationOnScreen(iArr2);
        iArr2[0] = (int) (((float) iArr2[0]) - this.a.getTranslationX());
        iArr2[1] = (int) (((float) iArr2[1]) - this.a.getTranslationY());
        this.f = iArr2[0] - iArr[0];
        this.g = iArr2[1] - iArr[1];
        this.a.getViewTreeObserver().addOnPreDrawListener(this.i);
        this.a.setVisibility(4);
    }

    protected void onDetachedFromWindow() {
        this.a.getViewTreeObserver().removeOnPreDrawListener(this.i);
        this.a.setVisibility(0);
        a(this.a, null);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas) {
        this.h.set(this.e);
        this.h.postTranslate((float) this.f, (float) this.g);
        canvas.setMatrix(this.h);
        this.a.draw(canvas);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        this.a.setVisibility(i == 0 ? 4 : 0);
    }

    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
        this.b = viewGroup;
        this.c = view;
    }

    private static void a(@NonNull View view, ac acVar) {
        view.setTag(R.id.ghost_view, acVar);
    }

    static ac a(@NonNull View view) {
        return (ac) view.getTag(R.id.ghost_view);
    }
}
