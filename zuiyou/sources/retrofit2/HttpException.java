package retrofit2;

public class HttpException extends RuntimeException {
    private final transient l<?> a;
    private final int code;
    private final String message;

    private static String a(l<?> lVar) {
        o.a((Object) lVar, "response == null");
        return "HTTP " + lVar.b() + " " + lVar.c();
    }

    public HttpException(l<?> lVar) {
        super(a(lVar));
        this.code = lVar.b();
        this.message = lVar.c();
        this.a = lVar;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public l<?> response() {
        return this.a;
    }
}
