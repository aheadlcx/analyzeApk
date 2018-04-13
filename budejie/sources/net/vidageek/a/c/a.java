package net.vidageek.a.c;

public class a extends RuntimeException {
    public a(String str) {
        super(str);
    }

    public a(String str, Throwable th) {
        super(str, th);
    }
}
