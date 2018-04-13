package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;

@NotThreadSafe
public class BufferedHeader implements FormattedHeader, Serializable, Cloneable {
    private final String a;
    private final CharArrayBuffer b;
    private final int c;

    public BufferedHeader(CharArrayBuffer charArrayBuffer) throws ParseException {
        Args.notNull(charArrayBuffer, "Char array buffer");
        int indexOf = charArrayBuffer.indexOf(58);
        if (indexOf == -1) {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        String substringTrimmed = charArrayBuffer.substringTrimmed(0, indexOf);
        if (substringTrimmed.length() == 0) {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        this.b = charArrayBuffer;
        this.a = substringTrimmed;
        this.c = indexOf + 1;
    }

    public String getName() {
        return this.a;
    }

    public String getValue() {
        return this.b.substringTrimmed(this.c, this.b.length());
    }

    public HeaderElement[] getElements() throws ParseException {
        ParserCursor parserCursor = new ParserCursor(0, this.b.length());
        parserCursor.updatePos(this.c);
        return BasicHeaderValueParser.INSTANCE.parseElements(this.b, parserCursor);
    }

    public int getValuePos() {
        return this.c;
    }

    public CharArrayBuffer getBuffer() {
        return this.b;
    }

    public String toString() {
        return this.b.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
