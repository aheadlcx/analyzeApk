package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.j;
import java.util.Arrays;
import java.util.Collection;

public class c<T> implements f<T> {
    private final Collection<? extends f<T>> a;
    private String b;

    @SafeVarargs
    public c(f<T>... fVarArr) {
        if (fVarArr.length < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.a = Arrays.asList(fVarArr);
    }

    public j<T> a(j<T> jVar, int i, int i2) {
        j<T> jVar2 = jVar;
        for (f a : this.a) {
            j<T> a2 = a.a(jVar2, i, i2);
            if (!(jVar2 == null || jVar2.equals(jVar) || jVar2.equals(a2))) {
                jVar2.d();
            }
            jVar2 = a2;
        }
        return jVar2;
    }

    public String a() {
        if (this.b == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (f a : this.a) {
                stringBuilder.append(a.a());
            }
            this.b = stringBuilder.toString();
        }
        return this.b;
    }
}
