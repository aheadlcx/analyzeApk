package android.support.v7.util;

import android.support.v7.util.ThreadUtil.BackgroundCallback;
import android.support.v7.util.ThreadUtil.MainThreadCallback;

class e<T> implements ThreadUtil<T> {

    static class a {
        private b a;

        a() {
        }

        synchronized b a() {
            b bVar;
            if (this.a == null) {
                bVar = null;
            } else {
                bVar = this.a;
                this.a = this.a.c;
            }
            return bVar;
        }

        synchronized void a(b bVar) {
            bVar.c = this.a;
            this.a = bVar;
        }

        synchronized void b(b bVar) {
            if (this.a == null) {
                this.a = bVar;
            } else {
                b bVar2 = this.a;
                while (bVar2.c != null) {
                    bVar2 = bVar2.c;
                }
                bVar2.c = bVar;
            }
        }

        synchronized void a(int i) {
            while (this.a != null && this.a.what == i) {
                b bVar = this.a;
                this.a = this.a.c;
                bVar.a();
            }
            if (this.a != null) {
                bVar = this.a;
                b a = bVar.c;
                while (a != null) {
                    b a2 = a.c;
                    if (a.what == i) {
                        bVar.c = a2;
                        a.a();
                    } else {
                        bVar = a;
                    }
                    a = a2;
                }
            }
        }
    }

    static class b {
        private static b a;
        private static final Object b = new Object();
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        private b c;
        public Object data;
        public int what;

        b() {
        }

        void a() {
            this.c = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            synchronized (b) {
                if (a != null) {
                    this.c = a;
                }
                a = this;
            }
        }

        static b a(int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
            b bVar;
            synchronized (b) {
                if (a == null) {
                    bVar = new b();
                } else {
                    bVar = a;
                    a = a.c;
                    bVar.c = null;
                }
                bVar.what = i;
                bVar.arg1 = i2;
                bVar.arg2 = i3;
                bVar.arg3 = i4;
                bVar.arg4 = i5;
                bVar.arg5 = i6;
                bVar.data = obj;
            }
            return bVar;
        }

        static b a(int i, int i2, int i3) {
            return a(i, i2, i3, 0, 0, 0, null);
        }

        static b a(int i, int i2, Object obj) {
            return a(i, i2, 0, 0, 0, 0, obj);
        }
    }

    e() {
    }

    public MainThreadCallback<T> getMainThreadProxy(MainThreadCallback<T> mainThreadCallback) {
        return new f(this, mainThreadCallback);
    }

    public BackgroundCallback<T> getBackgroundProxy(BackgroundCallback<T> backgroundCallback) {
        return new h(this, backgroundCallback);
    }
}
