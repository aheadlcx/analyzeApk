package com.umeng.a;

import android.content.Context;
import com.umeng.analytics.d.q;

public class i {

    public static class h {
        public boolean a(boolean z) {
            return true;
        }

        public boolean a() {
            return true;
        }
    }

    public static class a extends h {
        private final long a = 15000;
        private q b;

        public a(q qVar) {
            this.b = qVar;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b.c >= 15000) {
                return true;
            }
            return false;
        }
    }

    public static class b extends h {
        private com.umeng.analytics.e.b a;
        private q b;

        public b(q qVar, com.umeng.analytics.e.b bVar) {
            this.b = qVar;
            this.a = bVar;
        }

        public boolean a(boolean z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.b.c >= this.a.b()) {
                return true;
            }
            return false;
        }

        public boolean a() {
            return this.a.d();
        }
    }

    public static class c extends h {
        private long a;
        private long b = 0;

        public c(int i) {
            this.a = (long) i;
            this.b = System.currentTimeMillis();
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b >= this.a) {
                return true;
            }
            return false;
        }

        public boolean a() {
            return System.currentTimeMillis() - this.b < this.a;
        }
    }

    public static class d extends h {
        public boolean a(boolean z) {
            return z;
        }
    }

    public static class e extends h {
        private static long a = 90000;
        private static long b = com.umeng.analytics.a.i;
        private long c;
        private q d;

        public e(q qVar, long j) {
            this.d = qVar;
            a(j);
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.d.c >= this.c) {
                return true;
            }
            return false;
        }

        public void a(long j) {
            if (j < a || j > b) {
                this.c = a;
            } else {
                this.c = j;
            }
        }
    }

    public static class f extends h {
        private long a = com.umeng.analytics.a.i;
        private q b;

        public f(q qVar) {
            this.b = qVar;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b.c >= this.a) {
                return true;
            }
            return false;
        }
    }

    public static class g extends h {
        public boolean a(boolean z) {
            return true;
        }
    }

    public static class i extends h {
        private Context a = null;

        public i(Context context) {
            this.a = context;
        }

        public boolean a(boolean z) {
            return d.i(this.a);
        }
    }

    public static class j extends h {
        private final long a = 10800000;
        private q b;

        public j(q qVar) {
            this.b = qVar;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b.c >= 10800000) {
                return true;
            }
            return false;
        }
    }

    public static boolean a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            default:
                return false;
        }
    }
}
