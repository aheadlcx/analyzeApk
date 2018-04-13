package android.support.design.internal;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;

class c extends AccessibilityDelegateCompat {
    final /* synthetic */ NavigationMenuItemView a;

    c(NavigationMenuItemView navigationMenuItemView) {
        this.a = navigationMenuItemView;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setCheckable(this.a.c);
    }
}
