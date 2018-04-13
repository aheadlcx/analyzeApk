package com.facebook.common.internal;

import javax.annotation.Nullable;

public final class f$a {
    private final String a;
    private a b;
    private a c;
    private boolean d;

    private static final class a {
        String a;
        Object b;
        a c;

        private a() {
        }
    }

    private f$a(String str) {
        this.b = new a();
        this.c = this.b;
        this.d = false;
        this.a = (String) g.a((Object) str);
    }

    public f$a a(String str, @Nullable Object obj) {
        return b(str, obj);
    }

    public f$a a(String str, boolean z) {
        return b(str, String.valueOf(z));
    }

    public f$a a(String str, int i) {
        return b(str, String.valueOf(i));
    }

    public String toString() {
        boolean z = this.d;
        StringBuilder append = new StringBuilder(32).append(this.a).append('{');
        String str = "";
        a aVar = this.b.c;
        while (aVar != null) {
            if (!z || aVar.b != null) {
                append.append(str);
                str = ", ";
                if (aVar.a != null) {
                    append.append(aVar.a).append('=');
                }
                append.append(aVar.b);
            }
            aVar = aVar.c;
        }
        return append.append('}').toString();
    }

    private a a() {
        a aVar = new a();
        this.c.c = aVar;
        this.c = aVar;
        return aVar;
    }

    private f$a b(String str, @Nullable Object obj) {
        a a = a();
        a.b = obj;
        a.a = (String) g.a((Object) str);
        return this;
    }
}
