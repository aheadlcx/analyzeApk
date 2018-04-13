package android.support.v4.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;

public class AccessibilityDelegateCompat {
    private static final b a;
    private static final AccessibilityDelegate c = new AccessibilityDelegate();
    final AccessibilityDelegate b = a.newAccessibilityDelegateBridge(this);

    static class b {
        b() {
        }

        public AccessibilityDelegate newAccessibilityDelegateBridge(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return new c(this, accessibilityDelegateCompat);
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(AccessibilityDelegate accessibilityDelegate, View view) {
            return null;
        }

        public boolean performAccessibilityAction(AccessibilityDelegate accessibilityDelegate, View view, int i, Bundle bundle) {
            return false;
        }
    }

    @RequiresApi(16)
    static class a extends b {
        a() {
        }

        public AccessibilityDelegate newAccessibilityDelegateBridge(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return new b(this, accessibilityDelegateCompat);
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(AccessibilityDelegate accessibilityDelegate, View view) {
            AccessibilityNodeProvider accessibilityNodeProvider = accessibilityDelegate.getAccessibilityNodeProvider(view);
            if (accessibilityNodeProvider != null) {
                return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
            }
            return null;
        }

        public boolean performAccessibilityAction(AccessibilityDelegate accessibilityDelegate, View view, int i, Bundle bundle) {
            return accessibilityDelegate.performAccessibilityAction(view, i, bundle);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new a();
        } else {
            a = new b();
        }
    }

    AccessibilityDelegate a() {
        return this.b;
    }

    public void sendAccessibilityEvent(View view, int i) {
        c.sendAccessibilityEvent(view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        c.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return c.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        c.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        c.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        c.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.unwrap());
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return c.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        return a.getAccessibilityNodeProvider(c, view);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return a.performAccessibilityAction(c, view, i, bundle);
    }
}
