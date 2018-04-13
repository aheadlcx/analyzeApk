package com.linkedin.urls;

public class b {
    private String a;
    private int b = -1;
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g = -1;
    private int h = -1;

    public a a() {
        return new a(this);
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.a;
    }

    public void a(UrlPart urlPart, int i) {
        switch (urlPart) {
            case SCHEME:
                this.b = i;
                return;
            case USERNAME_PASSWORD:
                this.c = i;
                return;
            case HOST:
                this.d = i;
                return;
            case PORT:
                this.e = i;
                return;
            case PATH:
                this.f = i;
                return;
            case QUERY:
                this.g = i;
                return;
            case FRAGMENT:
                this.h = i;
                return;
            default:
                return;
        }
    }

    public int a(UrlPart urlPart) {
        switch (urlPart) {
            case SCHEME:
                return this.b;
            case USERNAME_PASSWORD:
                return this.c;
            case HOST:
                return this.d;
            case PORT:
                return this.e;
            case PATH:
                return this.f;
            case QUERY:
                return this.g;
            case FRAGMENT:
                return this.h;
            default:
                return -1;
        }
    }

    public void b(UrlPart urlPart) {
        a(urlPart, -1);
    }
}
