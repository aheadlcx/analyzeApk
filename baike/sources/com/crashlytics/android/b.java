package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.util.concurrent.Callable;

final class b implements Callable<Boolean> {
    private /* synthetic */ ba a;

    b(ba baVar) {
        this.a = baVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        return a();
    }

    private Boolean a() throws Exception {
        try {
            boolean delete = this.a.l.delete();
            v.a().b().a(Crashlytics.TAG, "Initialization marker file removed: " + delete);
            return Boolean.valueOf(delete);
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Problem encountered deleting Crashlytics initialization marker.", e);
            return Boolean.valueOf(false);
        }
    }
}
