package cn.xiaochuankeji.tieba.push.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class c {
    public boolean A;
    public long a;
    public int b;
    public long c;
    public long d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    public long j;
    public long k;
    public int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public String q;
    public String r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public JSONObject y;
    public JSONArray z;

    public static final class a {
        private long a;
        private int b;
        private long c;
        private long d;
        private long e;
        private long f;
        private long g;
        private long h;
        private long i;
        private long j;
        private long k;
        private int l;
        private boolean m;
        private boolean n;
        private boolean o;
        private boolean p;
        private boolean q;
        private String r;
        private String s;
        private int t;
        private int u;
        private int v;
        private int w;
        private int x;
        private int y;
        private JSONObject z;

        public a a(long j) {
            this.a = j;
            return this;
        }

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a b(long j) {
            this.c = j;
            return this;
        }

        public a c(long j) {
            this.d = j;
            return this;
        }

        public a d(long j) {
            this.i = j;
            return this;
        }

        public a e(long j) {
            this.j = j;
            return this;
        }

        public a f(long j) {
            this.k = j;
            return this;
        }

        public a b(int i) {
            this.l = i;
            return this;
        }

        public a a(boolean z) {
            this.n = z;
            return this;
        }

        public a b(boolean z) {
            this.o = z;
            return this;
        }

        public a c(boolean z) {
            this.p = z;
            return this;
        }

        public a d(boolean z) {
            this.q = z;
            return this;
        }

        public a a(String str) {
            this.r = str;
            return this;
        }

        public a b(String str) {
            this.s = str;
            return this;
        }

        public a c(int i) {
            this.t = i;
            return this;
        }

        public a d(int i) {
            this.u = i;
            return this;
        }

        public a e(int i) {
            this.v = i;
            return this;
        }

        public a f(int i) {
            this.w = i;
            return this;
        }

        public a g(int i) {
            this.x = i;
            return this;
        }

        public a h(int i) {
            this.y = i;
            return this;
        }

        public a a(JSONObject jSONObject) {
            this.z = jSONObject;
            return this;
        }

        public c a() {
            return new c();
        }

        public a g(long j) {
            this.e = j;
            return this;
        }

        public a h(long j) {
            this.f = j;
            return this;
        }

        public a i(long j) {
            this.g = j;
            return this;
        }

        public a e(boolean z) {
            this.m = z;
            return this;
        }

        public a j(long j) {
            this.h = j;
            return this;
        }
    }

    public boolean a() {
        return this.d > 0;
    }

    private c(a aVar) {
        this.z = new JSONArray();
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
        this.A = aVar.m;
        this.m = aVar.n;
        this.n = aVar.o;
        this.o = aVar.p;
        this.p = aVar.q;
        this.q = aVar.s;
        this.r = aVar.r;
        this.q = aVar.s;
        this.s = aVar.t;
        this.t = aVar.u;
        this.u = aVar.v;
        this.v = aVar.w;
        this.w = aVar.x;
        this.x = aVar.y;
        this.y = aVar.z;
    }

    public String toString() {
        return "Notify{id=" + this.a + ", type=" + this.b + ", pid=" + this.c + ", rid=" + this.d + ", tid=" + this.e + ", vid=" + this.f + ", prid=" + this.g + ", did=" + this.h + ", thumbnail=" + this.i + ", owner=" + this.j + ", time=" + this.k + ", imageType=" + this.l + ", isRead=" + this.m + ", hasSound=" + this.n + ", hasImage=" + this.o + ", hasVideo=" + this.p + ", content='" + this.q + '\'' + ", brief='" + this.r + '\'' + ", like=" + this.s + ", ugc=" + this.t + ", vote=" + this.u + ", review=" + this.v + ", danmaku=" + this.w + ", share=" + this.x + ", member=" + this.y + ", members=" + this.z + ", isUgc=" + this.A + '}';
    }
}
