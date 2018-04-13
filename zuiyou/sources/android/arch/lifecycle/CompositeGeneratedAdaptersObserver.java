package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final c[] a;

    public void a(g gVar, Event event) {
        int i = 0;
        j jVar = new j();
        for (c a : this.a) {
            a.a(gVar, event, false, jVar);
        }
        c[] cVarArr = this.a;
        int length = cVarArr.length;
        while (i < length) {
            cVarArr[i].a(gVar, event, true, jVar);
            i++;
        }
    }
}
