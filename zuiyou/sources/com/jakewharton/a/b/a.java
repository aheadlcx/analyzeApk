package com.jakewharton.a.b;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.jakewharton.a.a.b;
import rx.d;

public final class a {
    @CheckResult
    @NonNull
    public static d<Void> a(@NonNull View view) {
        b.a(view, "view == null");
        return d.a(new b(view));
    }

    @CheckResult
    @NonNull
    public static d<Void> b(@NonNull View view) {
        b.a(view, "view == null");
        return d.a(new c(view, com.jakewharton.a.a.a.a));
    }
}
