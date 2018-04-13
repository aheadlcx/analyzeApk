package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.core.SerializableString;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class MessagePackSerializedString implements SerializableString {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final Object value;

    public MessagePackSerializedString(Object obj) {
        this.value = obj;
    }

    public String getValue() {
        return this.value.toString();
    }

    public int charLength() {
        return getValue().length();
    }

    public char[] asQuotedChars() {
        return getValue().toCharArray();
    }

    public byte[] asUnquotedUTF8() {
        return getValue().getBytes(UTF8);
    }

    public byte[] asQuotedUTF8() {
        return ("\"" + getValue() + "\"").getBytes(UTF8);
    }

    public int appendQuotedUTF8(byte[] bArr, int i) {
        return 0;
    }

    public int appendQuoted(char[] cArr, int i) {
        return 0;
    }

    public int appendUnquotedUTF8(byte[] bArr, int i) {
        return 0;
    }

    public int appendUnquoted(char[] cArr, int i) {
        return 0;
    }

    public int writeQuotedUTF8(OutputStream outputStream) throws IOException {
        return 0;
    }

    public int writeUnquotedUTF8(OutputStream outputStream) throws IOException {
        return 0;
    }

    public int putQuotedUTF8(ByteBuffer byteBuffer) throws IOException {
        return 0;
    }

    public int putUnquotedUTF8(ByteBuffer byteBuffer) throws IOException {
        return 0;
    }

    public Object getRawValue() {
        return this.value;
    }
}
