package com.budejie.www.http;

import android.content.Context;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

class n$2 implements Function<Integer, Boolean> {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;

    n$2(Context context, String str, String str2, String str3) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
        return a((Integer) obj);
    }

    public Boolean a(@NonNull Integer num) throws Exception {
        try {
            n.b(this.a, this.b, this.c, this.d);
        } catch (Error e) {
        }
        return Boolean.valueOf(true);
    }
}
