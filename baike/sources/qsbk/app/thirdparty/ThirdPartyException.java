package qsbk.app.thirdparty;

public class ThirdPartyException extends Exception {
    private int a = -1;

    public ThirdPartyException(String str) {
        super(str);
    }

    public ThirdPartyException(Exception exception) {
        super(exception);
    }

    public ThirdPartyException(String str, int i) {
        super(str);
        this.a = i;
    }

    public ThirdPartyException(String str, Exception exception) {
        super(str, exception);
    }

    public ThirdPartyException(String str, Exception exception, int i) {
        super(str, exception);
        this.a = i;
    }

    public ThirdPartyException(String str, Throwable th) {
        super(str, th);
    }

    public ThirdPartyException(Throwable th) {
        super(th);
    }

    public ThirdPartyException(int i) {
        this.a = i;
    }

    public int getStatusCode() {
        return this.a;
    }

    public void setStatusCode(int i) {
        this.a = i;
    }
}
