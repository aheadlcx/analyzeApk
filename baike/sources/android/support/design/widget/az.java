package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.View;
import android.view.ViewParent;

class az extends Callback {
    final /* synthetic */ SwipeDismissBehavior a;
    private int b;
    private int c = -1;

    az(SwipeDismissBehavior swipeDismissBehavior) {
        this.a = swipeDismissBehavior;
    }

    public boolean tryCaptureView(View view, int i) {
        return this.c == -1 && this.a.canSwipeDismissView(view);
    }

    public void onViewCaptured(View view, int i) {
        this.c = i;
        this.b = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    public void onViewDragStateChanged(int i) {
        if (this.a.c != null) {
            this.a.c.onDragStateChanged(i);
        }
    }

    public void onViewReleased(View view, float f, float f2) {
        this.c = -1;
        int width = view.getWidth();
        boolean z = false;
        if (a(view, f)) {
            width = view.getLeft() < this.b ? this.b - width : this.b + width;
            z = true;
        } else {
            width = this.b;
        }
        if (this.a.b.settleCapturedViewAt(width, view.getTop())) {
            ViewCompat.postOnAnimation(view, new a(this.a, view, z));
        } else if (z && this.a.c != null) {
            this.a.c.onDismiss(view);
        }
    }

    private boolean a(View view, float f) {
        if (f != 0.0f) {
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            if (this.a.d == 2) {
                return true;
            }
            if (this.a.d == 0) {
                if (z) {
                    if (f >= 0.0f) {
                        return false;
                    }
                    return true;
                } else if (f <= 0.0f) {
                    return false;
                } else {
                    return true;
                }
            } else if (this.a.d != 1) {
                return false;
            } else {
                if (z) {
                    if (f <= 0.0f) {
                        return false;
                    }
                    return true;
                } else if (f >= 0.0f) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (Math.abs(view.getLeft() - this.b) < Math.round(((float) view.getWidth()) * this.a.e)) {
            return false;
        }
        return true;
    }

    public int getViewHorizontalDragRange(View view) {
        return view.getWidth();
    }

    public int clampViewPositionHorizontal(View view, int i, int i2) {
        int width;
        int i3;
        Object obj = ViewCompat.getLayoutDirection(view) == 1 ? 1 : null;
        if (this.a.d == 0) {
            if (obj != null) {
                width = this.b - view.getWidth();
                i3 = this.b;
            } else {
                width = this.b;
                i3 = this.b + view.getWidth();
            }
        } else if (this.a.d != 1) {
            width = this.b - view.getWidth();
            i3 = this.b + view.getWidth();
        } else if (obj != null) {
            width = this.b;
            i3 = this.b + view.getWidth();
        } else {
            width = this.b - view.getWidth();
            i3 = this.b;
        }
        return SwipeDismissBehavior.a(width, i, i3);
    }

    public int clampViewPositionVertical(View view, int i, int i2) {
        return view.getTop();
    }

    public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        float width = ((float) this.b) + (((float) view.getWidth()) * this.a.f);
        float width2 = ((float) this.b) + (((float) view.getWidth()) * this.a.g);
        if (((float) i) <= width) {
            view.setAlpha(1.0f);
        } else if (((float) i) >= width2) {
            view.setAlpha(0.0f);
        } else {
            view.setAlpha(SwipeDismissBehavior.a(0.0f, 1.0f - SwipeDismissBehavior.b(width, width2, (float) i), 1.0f));
        }
    }
}
