package org.aspectj.lang;

public class NoAspectBoundException extends RuntimeException {
    Throwable cause;

    public NoAspectBoundException(String str, Throwable th) {
        if (th != null) {
            str = new StringBuffer().append("Exception while initializing ").append(str).append(": ").append(th).toString();
        }
        super(str);
        this.cause = th;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
