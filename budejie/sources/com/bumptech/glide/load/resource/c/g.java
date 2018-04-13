package com.bumptech.glide.load.resource.c;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.a.c;
import com.bumptech.glide.load.b.l;

class g implements l<com.bumptech.glide.b.a, com.bumptech.glide.b.a> {

    private static class a implements c<com.bumptech.glide.b.a> {
        private final com.bumptech.glide.b.a a;

        public /* synthetic */ Object a(Priority priority) throws Exception {
            return b(priority);
        }

        public a(com.bumptech.glide.b.a aVar) {
            this.a = aVar;
        }

        public com.bumptech.glide.b.a b(Priority priority) {
            return this.a;
        }

        public void a() {
        }

        public String b() {
            return String.valueOf(this.a.f());
        }

        public void c() {
        }
    }

    g() {
    }

    public c<com.bumptech.glide.b.a> a(com.bumptech.glide.b.a aVar, int i, int i2) {
        return new a(aVar);
    }
}
