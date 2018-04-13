package org.apache.commons.codec;

public class DecoderException extends Exception {
    private static final long serialVersionUID = 1;

    public DecoderException(String str) {
        super(str);
    }

    public DecoderException(String str, Throwable th) {
        super(str, th);
    }

    public DecoderException(Throwable th) {
        super(th);
    }
}
