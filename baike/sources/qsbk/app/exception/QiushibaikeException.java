package qsbk.app.exception;

public class QiushibaikeException extends Exception {
    private int a = -1;

    public QiushibaikeException(String str) {
        super(str);
    }

    public QiushibaikeException(Exception exception) {
        super(exception);
    }

    public QiushibaikeException(String str, int i) {
        super(str);
        this.a = i;
    }

    public QiushibaikeException(String str, Exception exception) {
        super(str, exception);
    }

    public QiushibaikeException(String str, Exception exception, int i) {
        super(str, exception);
        this.a = i;
    }

    public int getStatusCode() {
        return this.a;
    }

    public String toString() {
        return super.toString() + " statucCode:" + this.a;
    }
}
