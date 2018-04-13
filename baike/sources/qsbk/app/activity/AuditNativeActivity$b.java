package qsbk.app.activity;

import android.view.View;
import java.util.LinkedList;
import java.util.List;

class AuditNativeActivity$b {
    int a;
    LinkedList<View> b;
    final /* synthetic */ AuditNativeActivity c;

    AuditNativeActivity$b(AuditNativeActivity auditNativeActivity, int i) {
        this.c = auditNativeActivity;
        this.a = 1;
        if (i < 1) {
            throw new IllegalArgumentException("Max Size " + i + " must be positive.");
        }
        this.b = new LinkedList();
        this.a = i;
    }

    public AuditNativeActivity$b(AuditNativeActivity auditNativeActivity) {
        this(auditNativeActivity, 3);
    }

    public View add(View view) {
        View view2 = null;
        synchronized (this.b) {
            if (this.b.size() >= this.a) {
                view2 = (View) this.b.removeFirst();
            }
            this.b.addLast(view);
        }
        return view2;
    }

    public List getAll() {
        return this.b;
    }

    public void clear() {
        if (this.b != null) {
            this.b.clear();
        }
    }
}
