package org.apache.commons.codec;

public class EncoderException extends Exception {
    private static final long serialVersionUID = 1;

    public EncoderException(String str) {
        super(str);
    }

    public EncoderException(String str, Throwable th) {
        super(str, th);
    }

    public EncoderException(Throwable th) {
        super(th);
    }
}
