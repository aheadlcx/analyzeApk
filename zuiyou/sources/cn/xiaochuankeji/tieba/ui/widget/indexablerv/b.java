package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

public class b<T> {
    private String a;
    private String b;
    private String c;
    private String d;
    private T e;
    private int f = -1;
    private int g = Integer.MAX_VALUE;
    private int h;

    b() {
    }

    b(String str, int i) {
        this.a = str;
        this.b = str;
        this.c = str;
        this.g = i;
    }

    public String a() {
        return this.a;
    }

    void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    void d(String str) {
        this.d = str;
    }

    public T e() {
        return this.e;
    }

    void a(T t) {
        this.e = t;
    }

    public int f() {
        return this.f;
    }

    void a(int i) {
        this.f = i;
    }

    int g() {
        return this.g;
    }

    void b(int i) {
        this.g = i;
    }

    int h() {
        return this.h;
    }

    void c(int i) {
        this.h = i;
    }

    public String toString() {
        return "EntityWrapper{index='" + this.a + '\'' + ", indexTitle='" + this.b + '\'' + ", pinyin='" + this.c + '\'' + ", indexByField='" + this.d + '\'' + ", data=" + this.e + ", originalPosition=" + this.f + ", itemType=" + this.g + ", headerFooterType=" + this.h + '}';
    }
}
