package android.support.design.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;

class x extends AccessibilityDelegateCompat {
    final /* synthetic */ BottomSheetDialog a;

    x(BottomSheetDialog bottomSheetDialog) {
        this.a = bottomSheetDialog;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (this.a.a) {
            accessibilityNodeInfoCompat.addAction(1048576);
            accessibilityNodeInfoCompat.setDismissable(true);
            return;
        }
        accessibilityNodeInfoCompat.setDismissable(false);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (i != 1048576 || !this.a.a) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        this.a.cancel();
        return true;
    }
}
