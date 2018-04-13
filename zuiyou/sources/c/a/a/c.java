package c.a.a;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import c.a.i.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class c implements LayoutInflaterFactory {
    private final Context a;
    private f b;
    private List<WeakReference<u>> c = new ArrayList();

    private c(Context context) {
        this.a = context;
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View a = a(view, str, context, attributeSet);
        if (a == null) {
            return null;
        }
        if (!(a instanceof u)) {
            return a;
        }
        this.c.add(new WeakReference((u) a));
        return a;
    }

    public View a(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean z;
        boolean z2 = VERSION.SDK_INT < 21;
        if (this.b == null) {
            this.b = new f();
        }
        if (z2 && a((ViewParent) view)) {
            z = true;
        } else {
            z = false;
        }
        return this.b.a(view, str, context, attributeSet, z, z2, true, VectorEnabledTintResources.shouldBeUsed());
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        if (!(this.a instanceof Activity)) {
            return false;
        }
        ViewParent decorView = ((Activity) this.a).getWindow().getDecorView();
        ViewParent viewParent2 = viewParent;
        while (viewParent2 != null) {
            if (viewParent2 == decorView || !(viewParent2 instanceof View) || ViewCompat.isAttachedToWindow((View) viewParent2)) {
                return false;
            }
            viewParent2 = viewParent2.getParent();
        }
        return true;
    }

    public static c a(Context context) {
        return new c(context);
    }

    public void a() {
        if (this.c != null && !this.c.isEmpty()) {
            for (WeakReference weakReference : this.c) {
                if (!(weakReference == null || weakReference.get() == null)) {
                    ((u) weakReference.get()).d();
                }
            }
        }
    }
}
