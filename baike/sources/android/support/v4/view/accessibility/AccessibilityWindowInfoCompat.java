package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityWindowInfo;

public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private Object a;

    static AccessibilityWindowInfoCompat a(Object obj) {
        if (obj != null) {
            return new AccessibilityWindowInfoCompat(obj);
        }
        return null;
    }

    private AccessibilityWindowInfoCompat(Object obj) {
        this.a = obj;
    }

    public int getType() {
        if (VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.a).getType();
        }
        return -1;
    }

    public int getLayer() {
        if (VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.a).getLayer();
        }
        return -1;
    }

    public AccessibilityNodeInfoCompat getRoot() {
        if (VERSION.SDK_INT >= 21) {
            return AccessibilityNodeInfoCompat.a(((AccessibilityWindowInfo) this.a).getRoot());
        }
        return null;
    }

    public AccessibilityWindowInfoCompat getParent() {
        if (VERSION.SDK_INT >= 21) {
            return a(((AccessibilityWindowInfo) this.a).getParent());
        }
        return null;
    }

    public int getId() {
        if (VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.a).getId();
        }
        return -1;
    }

    public void getBoundsInScreen(Rect rect) {
        if (VERSION.SDK_INT >= 21) {
            ((AccessibilityWindowInfo) this.a).getBoundsInScreen(rect);
        }
    }

    public boolean isActive() {
        if (VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.a).isActive();
        }
        return true;
    }

    public boolean isFocused() {
        if (VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.a).isFocused();
        }
        return true;
    }

    public boolean isAccessibilityFocused() {
        if (VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.a).isAccessibilityFocused();
        }
        return true;
    }

    public int getChildCount() {
        if (VERSION.SDK_INT >= 21) {
            return ((AccessibilityWindowInfo) this.a).getChildCount();
        }
        return 0;
    }

    public AccessibilityWindowInfoCompat getChild(int i) {
        if (VERSION.SDK_INT >= 21) {
            return a(((AccessibilityWindowInfo) this.a).getChild(i));
        }
        return null;
    }

    public CharSequence getTitle() {
        if (VERSION.SDK_INT >= 24) {
            return ((AccessibilityWindowInfo) this.a).getTitle();
        }
        return null;
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        if (VERSION.SDK_INT >= 24) {
            return AccessibilityNodeInfoCompat.a(((AccessibilityWindowInfo) this.a).getAnchor());
        }
        return null;
    }

    public static AccessibilityWindowInfoCompat obtain() {
        if (VERSION.SDK_INT >= 21) {
            return a(AccessibilityWindowInfo.obtain());
        }
        return null;
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        if (VERSION.SDK_INT < 21 || accessibilityWindowInfoCompat == null) {
            return null;
        }
        return a(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo) accessibilityWindowInfoCompat.a));
    }

    public void recycle() {
        if (VERSION.SDK_INT >= 21) {
            ((AccessibilityWindowInfo) this.a).recycle();
        }
    }

    public int hashCode() {
        return this.a == null ? 0 : this.a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat) obj;
        if (this.a == null) {
            if (accessibilityWindowInfoCompat.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(accessibilityWindowInfoCompat.a)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        boolean z;
        boolean z2 = true;
        StringBuilder stringBuilder = new StringBuilder();
        Rect rect = new Rect();
        getBoundsInScreen(rect);
        stringBuilder.append("AccessibilityWindowInfo[");
        stringBuilder.append("id=").append(getId());
        stringBuilder.append(", type=").append(a(getType()));
        stringBuilder.append(", layer=").append(getLayer());
        stringBuilder.append(", bounds=").append(rect);
        stringBuilder.append(", focused=").append(isFocused());
        stringBuilder.append(", active=").append(isActive());
        StringBuilder append = stringBuilder.append(", hasParent=");
        if (getParent() != null) {
            z = true;
        } else {
            z = false;
        }
        append.append(z);
        StringBuilder append2 = stringBuilder.append(", hasChildren=");
        if (getChildCount() <= 0) {
            z2 = false;
        }
        append2.append(z2);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private static String a(int i) {
        switch (i) {
            case 1:
                return "TYPE_APPLICATION";
            case 2:
                return "TYPE_INPUT_METHOD";
            case 3:
                return "TYPE_SYSTEM";
            case 4:
                return "TYPE_ACCESSIBILITY_OVERLAY";
            default:
                return "<UNKNOWN>";
        }
    }
}
