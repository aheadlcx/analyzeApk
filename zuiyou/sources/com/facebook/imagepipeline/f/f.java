package com.facebook.imagepipeline.f;

import com.facebook.common.internal.g;
import com.facebook.imagepipeline.g.h;
import java.util.Collections;
import java.util.List;

public class f implements d {
    private final b a;

    public interface b {
        List<Integer> a();

        int b();
    }

    private static class a implements b {
        private a() {
        }

        public List<Integer> a() {
            return Collections.EMPTY_LIST;
        }

        public int b() {
            return 0;
        }
    }

    public f() {
        this(new a());
    }

    public f(b bVar) {
        this.a = (b) g.a((Object) bVar);
    }

    public int a(int i) {
        List a = this.a.a();
        if (a == null || a.isEmpty()) {
            return i + 1;
        }
        for (int i2 = 0; i2 < a.size(); i2++) {
            if (((Integer) a.get(i2)).intValue() > i) {
                return ((Integer) a.get(i2)).intValue();
            }
        }
        return Integer.MAX_VALUE;
    }

    public h b(int i) {
        boolean z;
        if (i >= this.a.b()) {
            z = true;
        } else {
            z = false;
        }
        return com.facebook.imagepipeline.g.g.a(i, z, false);
    }
}
