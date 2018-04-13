package cn.v6.sixrooms.base;

public class RPCException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private int a;
    private String b;

    public RPCException(int i, String str) {
        this(i, str, null);
    }

    public RPCException(int i, String str, Throwable th) {
        super(th);
        this.a = i;
        this.b = str;
    }

    public int getCode() {
        return this.a;
    }

    public String getMessage() {
        return this.b;
    }
}
