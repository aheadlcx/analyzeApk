package android.support.v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.FocusStrategy.BoundsAdapter;
import android.support.v4.widget.FocusStrategy.CollectionAdapter;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect a = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final BoundsAdapter<AccessibilityNodeInfoCompat> m = new j();
    private static final CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> n = new k();
    private final Rect c = new Rect();
    private final Rect d = new Rect();
    private final Rect e = new Rect();
    private final int[] f = new int[2];
    private final AccessibilityManager g;
    private final View h;
    private a i;
    private int j = Integer.MIN_VALUE;
    private int k = Integer.MIN_VALUE;
    private int l = Integer.MIN_VALUE;

    private class a extends AccessibilityNodeProviderCompat {
        final /* synthetic */ ExploreByTouchHelper a;

        a(ExploreByTouchHelper exploreByTouchHelper) {
            this.a = exploreByTouchHelper;
        }

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i) {
            return AccessibilityNodeInfoCompat.obtain(this.a.a(i));
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.a.a(i, i2, bundle);
        }

        public AccessibilityNodeInfoCompat findFocus(int i) {
            int a = i == 2 ? this.a.j : this.a.k;
            if (a == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(a);
        }
    }

    protected abstract int a(float f, float f2);

    protected abstract void a(int i, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    protected abstract void a(List<Integer> list);

    protected abstract boolean b(int i, int i2, @Nullable Bundle bundle);

    public ExploreByTouchHelper(@NonNull View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.h = view;
        this.g = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (ViewCompat.getImportantForAccessibility(view) == 0) {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.i == null) {
            this.i = new a(this);
        }
        return this.i;
    }

    public final boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        boolean z = true;
        if (!this.g.isEnabled() || !this.g.isTouchExplorationEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 7:
            case 9:
                int a = a(motionEvent.getX(), motionEvent.getY());
                c(a);
                if (a == Integer.MIN_VALUE) {
                    z = false;
                }
                return z;
            case 10:
                if (this.j == Integer.MIN_VALUE) {
                    return false;
                }
                c(Integer.MIN_VALUE);
                return true;
            default:
                return false;
        }
    }

    public final boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        boolean z = false;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case 19:
            case 20:
            case 21:
            case 22:
                if (!keyEvent.hasNoModifiers()) {
                    return false;
                }
                int b = b(keyCode);
                int repeatCount = keyEvent.getRepeatCount() + 1;
                keyCode = 0;
                while (keyCode < repeatCount && b(b, null)) {
                    keyCode++;
                    z = true;
                }
                return z;
            case 23:
            case 66:
                if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
                    return false;
                }
                c();
                return true;
            case 61:
                if (keyEvent.hasNoModifiers()) {
                    return b(2, null);
                }
                if (keyEvent.hasModifiers(1)) {
                    return b(1, null);
                }
                return false;
            default:
                return false;
        }
    }

    public final void onFocusChanged(boolean z, int i, @Nullable Rect rect) {
        if (this.k != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(this.k);
        }
        if (z) {
            b(i, rect);
        }
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.j;
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.k;
    }

    private static int b(int i) {
        switch (i) {
            case 19:
                return 33;
            case 21:
                return 17;
            case 22:
                return 66;
            default:
                return 130;
        }
    }

    private void a(int i, Rect rect) {
        a(i).getBoundsInParent(rect);
    }

    private boolean b(int i, @Nullable Rect rect) {
        int i2;
        boolean z = true;
        SparseArrayCompat b = b();
        int i3 = this.k;
        if (i3 == Integer.MIN_VALUE) {
            Object obj = null;
        } else {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) b.get(i3);
        }
        switch (i) {
            case 1:
            case 2:
                if (ViewCompat.getLayoutDirection(this.h) != 1) {
                    z = false;
                }
                Object obj2 = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInRelativeDirection(b, n, m, obj, i, z, false);
                break;
            case 17:
            case 33:
            case 66:
            case 130:
                Rect rect2 = new Rect();
                if (this.k != Integer.MIN_VALUE) {
                    a(this.k, rect2);
                } else if (rect != null) {
                    rect2.set(rect);
                } else {
                    a(this.h, i, rect2);
                }
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInAbsoluteDirection(b, n, m, obj, rect2, i);
                break;
            default:
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        if (obj2 == null) {
            i2 = Integer.MIN_VALUE;
        } else {
            i2 = b.keyAt(b.indexOfValue(obj2));
        }
        return requestKeyboardFocusForVirtualView(i2);
    }

    private SparseArrayCompat<AccessibilityNodeInfoCompat> b() {
        List arrayList = new ArrayList();
        a(arrayList);
        SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat = new SparseArrayCompat();
        for (int i = 0; i < arrayList.size(); i++) {
            sparseArrayCompat.put(i, e(i));
        }
        return sparseArrayCompat;
    }

    private static Rect a(@NonNull View view, int i, @NonNull Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        switch (i) {
            case 17:
                rect.set(width, 0, width, height);
                break;
            case 33:
                rect.set(0, height, width, height);
                break;
            case 66:
                rect.set(-1, 0, -1, height);
                break;
            case 130:
                rect.set(0, -1, width, -1);
                break;
            default:
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return rect;
    }

    private boolean c() {
        return this.k != Integer.MIN_VALUE && b(this.k, 16, null);
    }

    public final boolean sendEventForVirtualView(int i, int i2) {
        if (i == Integer.MIN_VALUE || !this.g.isEnabled()) {
            return false;
        }
        ViewParent parent = this.h.getParent();
        if (parent == null) {
            return false;
        }
        return ViewParentCompat.requestSendAccessibilityEvent(parent, this.h, a(i, i2));
    }

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i) {
        invalidateVirtualView(i, 0);
    }

    public final void invalidateVirtualView(int i, int i2) {
        if (i != Integer.MIN_VALUE && this.g.isEnabled()) {
            ViewParent parent = this.h.getParent();
            if (parent != null) {
                AccessibilityEvent a = a(i, 2048);
                AccessibilityEventCompat.setContentChangeTypes(a, i2);
                ViewParentCompat.requestSendAccessibilityEvent(parent, this.h, a);
            }
        }
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    protected void a(int i, boolean z) {
    }

    private void c(int i) {
        if (this.l != i) {
            int i2 = this.l;
            this.l = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(i2, 256);
        }
    }

    private AccessibilityEvent a(int i, int i2) {
        switch (i) {
            case -1:
                return d(i2);
            default:
                return b(i, i2);
        }
    }

    private AccessibilityEvent d(int i) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
        this.h.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        a(accessibilityEvent);
    }

    private AccessibilityEvent b(int i, int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        AccessibilityNodeInfoCompat a = a(i);
        obtain.getText().add(a.getText());
        obtain.setContentDescription(a.getContentDescription());
        obtain.setScrollable(a.isScrollable());
        obtain.setPassword(a.isPassword());
        obtain.setEnabled(a.isEnabled());
        obtain.setChecked(a.isChecked());
        a(i, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain.setClassName(a.getClassName());
        AccessibilityRecordCompat.setSource(obtain, this.h, i);
        obtain.setPackageName(this.h.getContext().getPackageName());
        return obtain;
    }

    @NonNull
    AccessibilityNodeInfoCompat a(int i) {
        if (i == -1) {
            return d();
        }
        return e(i);
    }

    @NonNull
    private AccessibilityNodeInfoCompat d() {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(this.h);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.h, obtain);
        List arrayList = new ArrayList();
        a(arrayList);
        if (obtain.getChildCount() <= 0 || arrayList.size() <= 0) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                obtain.addChild(this.h, ((Integer) arrayList.get(i)).intValue());
            }
            return obtain;
        }
        throw new RuntimeException("Views cannot have both real and virtual children");
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        a(accessibilityNodeInfoCompat);
    }

    @NonNull
    private AccessibilityNodeInfoCompat e(int i) {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        obtain.setClassName("android.view.View");
        obtain.setBoundsInParent(a);
        obtain.setBoundsInScreen(a);
        obtain.setParent(this.h);
        a(i, obtain);
        if (obtain.getText() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.d);
        if (this.d.equals(a)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = obtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        } else if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        } else {
            boolean z;
            obtain.setPackageName(this.h.getContext().getPackageName());
            obtain.setSource(this.h, i);
            if (this.j == i) {
                obtain.setAccessibilityFocused(true);
                obtain.addAction(128);
            } else {
                obtain.setAccessibilityFocused(false);
                obtain.addAction(64);
            }
            if (this.k == i) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                obtain.addAction(2);
            } else if (obtain.isFocusable()) {
                obtain.addAction(1);
            }
            obtain.setFocused(z);
            this.h.getLocationOnScreen(this.f);
            obtain.getBoundsInScreen(this.c);
            if (this.c.equals(a)) {
                obtain.getBoundsInParent(this.c);
                if (obtain.mParentVirtualDescendantId != -1) {
                    AccessibilityNodeInfoCompat obtain2 = AccessibilityNodeInfoCompat.obtain();
                    for (actions = obtain.mParentVirtualDescendantId; actions != -1; actions = obtain2.mParentVirtualDescendantId) {
                        obtain2.setParent(this.h, -1);
                        obtain2.setBoundsInParent(a);
                        a(actions, obtain2);
                        obtain2.getBoundsInParent(this.d);
                        this.c.offset(this.d.left, this.d.top);
                    }
                    obtain2.recycle();
                }
                this.c.offset(this.f[0] - this.h.getScrollX(), this.f[1] - this.h.getScrollY());
            }
            if (this.h.getLocalVisibleRect(this.e)) {
                this.e.offset(this.f[0] - this.h.getScrollX(), this.f[1] - this.h.getScrollY());
                if (this.c.intersect(this.e)) {
                    obtain.setBoundsInScreen(this.c);
                    if (a(this.c)) {
                        obtain.setVisibleToUser(true);
                    }
                }
            }
            return obtain;
        }
    }

    boolean a(int i, int i2, Bundle bundle) {
        switch (i) {
            case -1:
                return a(i2, bundle);
            default:
                return c(i, i2, bundle);
        }
    }

    private boolean a(int i, Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.h, i, bundle);
    }

    private boolean c(int i, int i2, Bundle bundle) {
        switch (i2) {
            case 1:
                return requestKeyboardFocusForVirtualView(i);
            case 2:
                return clearKeyboardFocusForVirtualView(i);
            case 64:
                return f(i);
            case 128:
                return g(i);
            default:
                return b(i, i2, bundle);
        }
    }

    private boolean a(Rect rect) {
        if (rect == null || rect.isEmpty() || this.h.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent parent = this.h.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    private boolean f(int i) {
        if (!this.g.isEnabled() || !this.g.isTouchExplorationEnabled() || this.j == i) {
            return false;
        }
        if (this.j != Integer.MIN_VALUE) {
            g(this.j);
        }
        this.j = i;
        this.h.invalidate();
        sendEventForVirtualView(i, 32768);
        return true;
    }

    private boolean g(int i) {
        if (this.j != i) {
            return false;
        }
        this.j = Integer.MIN_VALUE;
        this.h.invalidate();
        sendEventForVirtualView(i, 65536);
        return true;
    }

    public final boolean requestKeyboardFocusForVirtualView(int i) {
        if ((!this.h.isFocused() && !this.h.requestFocus()) || this.k == i) {
            return false;
        }
        if (this.k != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(this.k);
        }
        this.k = i;
        a(i, true);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean clearKeyboardFocusForVirtualView(int i) {
        if (this.k != i) {
            return false;
        }
        this.k = Integer.MIN_VALUE;
        a(i, false);
        sendEventForVirtualView(i, 8);
        return true;
    }

    protected void a(int i, @NonNull AccessibilityEvent accessibilityEvent) {
    }

    protected void a(@NonNull AccessibilityEvent accessibilityEvent) {
    }

    protected void a(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }
}
