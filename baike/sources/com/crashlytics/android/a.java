package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.util.concurrent.Callable;

final class a implements Callable<Void> {
    private /* synthetic */ ba a;

    a(ba baVar) {
        this.a = baVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.a.l.createNewFile();
        v.a().b().a(Crashlytics.TAG, "Initialization marker file created.");
        return null;
    }
}
