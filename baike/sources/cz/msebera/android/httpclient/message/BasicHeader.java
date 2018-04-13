package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

@Immutable
public class BasicHeader implements Header, Serializable, Cloneable {
    private final String a;
    private final String b;

    public BasicHeader(String str, String str2) {
        this.a = (String) Args.notNull(str, "Name");
        this.b = str2;
    }

    public String getName() {
        return this.a;
    }

    public String getValue() {
        return this.b;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatHeader(null, this).toString();
    }

    public HeaderElement[] getElements() throws ParseException {
        if (this.b != null) {
            return BasicHeaderValueParser.parseElements(this.b, null);
        }
        return new HeaderElement[0];
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
