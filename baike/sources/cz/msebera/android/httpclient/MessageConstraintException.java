package cz.msebera.android.httpclient;

import java.nio.charset.CharacterCodingException;

public class MessageConstraintException extends CharacterCodingException {
    private final String a;

    public MessageConstraintException(String str) {
        this.a = str;
    }

    public String getMessage() {
        return this.a;
    }
}
