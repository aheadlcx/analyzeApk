package android.support.transition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@RequiresApi(14)
class ViewOverlayApi14 implements ViewOverlayImpl {
    protected OverlayViewGroup mOverlayViewGroup;

    static class OverlayViewGroup extends ViewGroup {
        static Method sInvalidateChildInParentFastMethod;
        ArrayList<Drawable> mDrawables = null;
        ViewGroup mHostView;
        View mRequestingView;
        ViewOverlayApi14 mViewOverlay;

        static class TouchInterceptor extends View {
            TouchInterceptor(Context context) {
                super(context);
            }
        }

        static {
            try {
                sInvalidateChildInParentFastMethod = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", new Class[]{Integer.TYPE, Integer.TYPE, Rect.class});
            } catch (NoSuchMethodException e) {
            }
        }

        OverlayViewGroup(Context context, ViewGroup viewGroup, View view, ViewOverlayApi14 viewOverlayApi14) {
            super(context);
            this.mHostView = viewGroup;
            this.mRequestingView = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.mViewOverlay = viewOverlayApi14;
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public void add(Drawable drawable) {
            if (this.mDrawables == null) {
                this.mDrawables = new ArrayList();
            }
            if (!this.mDrawables.contains(drawable)) {
                this.mDrawables.add(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(this);
            }
        }

        public void remove(Drawable drawable) {
            if (this.mDrawables != null) {
                this.mDrawables.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
            }
        }

        protected boolean verifyDrawable(@NonNull Drawable drawable) {
            return super.verifyDrawable(drawable) || (this.mDrawables != null && this.mDrawables.contains(drawable));
        }

        public void add(View view) {
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (!(viewGroup == this.mHostView || viewGroup.getParent() == null || !ViewCompat.isAttachedToWindow(viewGroup))) {
                    int[] iArr = new int[2];
                    int[] iArr2 = new int[2];
                    viewGroup.getLocationOnScreen(iArr);
                    this.mHostView.getLocationOnScreen(iArr2);
                    ViewCompat.offsetLeftAndRight(view, iArr[0] - iArr2[0]);
                    ViewCompat.offsetTopAndBottom(view, iArr[1] - iArr2[1]);
                }
                viewGroup.removeView(view);
                if (view.getParent() != null) {
                    viewGroup.removeView(view);
                }
            }
            super.addView(view, getChildCount() - 1);
        }

        public void remove(View view) {
            super.removeView(view);
            if (isEmpty()) {
                this.mHostView.removeView(this);
            }
        }

        public void clear() {
            removeAllViews();
            if (this.mDrawables != null) {
                this.mDrawables.clear();
            }
        }

        boolean isEmpty() {
            return getChildCount() == 0 && (this.mDrawables == null || this.mDrawables.size() == 0);
        }

        public void invalidateDrawable(@NonNull Drawable drawable) {
            invalidate(drawable.getBounds());
        }

        protected void dispatchDraw(Canvas canvas) {
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            this.mHostView.getLocationOnScreen(iArr);
            this.mRequestingView.getLocationOnScreen(iArr2);
            canvas.translate((float) (iArr2[0] - iArr[0]), (float) (iArr2[1] - iArr[1]));
            canvas.clipRect(new Rect(0, 0, this.mRequestingView.getWidth(), this.mRequestingView.getHeight()));
            super.dispatchDraw(canvas);
            int size = this.mDrawables == null ? 0 : this.mDrawables.size();
            for (int i = 0; i < size; i++) {
                ((Drawable) this.mDrawables.get(i)).draw(canvas);
            }
        }

        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        private void getOffset(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.mHostView.getLocationOnScreen(iArr2);
            this.mRequestingView.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        public void invalidateChildFast(View view, Rect rect) {
            if (this.mHostView != null) {
                int left = view.getLeft();
                int top = view.getTop();
                int[] iArr = new int[2];
                getOffset(iArr);
                rect.offset(left + iArr[0], top + iArr[1]);
                this.mHostView.invalidate(rect);
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        protected ViewParent invalidateChildInParentFast(int i, int i2, Rect rect) {
            if ((this.mHostView instanceof ViewGroup) && sInvalidateChildInParentFastMethod != null) {
                try {
                    getOffset(new int[2]);
                    sInvalidateChildInParentFastMethod.invoke(this.mHostView, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), rect});
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }

        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.mHostView != null) {
                rect.offset(iArr[0], iArr[1]);
                if (this.mHostView instanceof ViewGroup) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    int[] iArr2 = new int[2];
                    getOffset(iArr2);
                    rect.offset(iArr2[0], iArr2[1]);
                    return super.invalidateChildInParent(iArr, rect);
                }
                invalidate(rect);
            }
            return null;
        }
    }

    ViewOverlayApi14(Context context, ViewGroup viewGroup, View view) {
        this.mOverlayViewGroup = new OverlayViewGroup(context, viewGroup, view, this);
    }

    static ViewGroup getContentView(View view) {
        View view2 = view;
        while (view2 != null) {
            if (view2.getId() == 16908290 && (view2 instanceof ViewGroup)) {
                return (ViewGroup) view2;
            }
            if (view2.getParent() instanceof ViewGroup) {
                view2 = (ViewGroup) view2.getParent();
            }
        }
        return null;
    }

    static ViewOverlayApi14 createFrom(View view) {
        ViewGroup contentView = getContentView(view);
        if (contentView == null) {
            return null;
        }
        int childCount = contentView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = contentView.getChildAt(i);
            if (childAt instanceof OverlayViewGroup) {
                return ((OverlayViewGroup) childAt).mViewOverlay;
            }
        }
        return new ViewGroupOverlayApi14(contentView.getContext(), contentView, view);
    }

    ViewGroup getOverlayView() {
        return this.mOverlayViewGroup;
    }

    public void add(@NonNull Drawable drawable) {
        this.mOverlayViewGroup.add(drawable);
    }

    public void clear() {
        this.mOverlayViewGroup.clear();
    }

    public void remove(@NonNull Drawable drawable) {
        this.mOverlayViewGroup.remove(drawable);
    }

    boolean isEmpty() {
        return this.mOverlayViewGroup.isEmpty();
    }
}
