package retrofit2;

public class HttpException extends Exception {
    private final int code;
    private final String message;
    private final transient l<?> response;

    private static String getMessage(l<?> lVar) {
        if (lVar != null) {
            return "HTTP " + lVar.a() + " " + lVar.b();
        }
        throw new NullPointerException("response == null");
    }

    public HttpException(l<?> lVar) {
        super(getMessage(lVar));
        this.code = lVar.a();
        this.message = lVar.b();
        this.response = lVar;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public l<?> response() {
        return this.response;
    }
}
