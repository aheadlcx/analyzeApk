package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;

@Immutable
public class BasicNameValuePair implements NameValuePair, Serializable, Cloneable {
    private final String a;
    private final String b;

    public BasicNameValuePair(String str, String str2) {
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
        if (this.b == null) {
            return this.a;
        }
        StringBuilder stringBuilder = new StringBuilder((this.a.length() + 1) + this.b.length());
        stringBuilder.append(this.a);
        stringBuilder.append("=");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameValuePair)) {
            return false;
        }
        BasicNameValuePair basicNameValuePair = (BasicNameValuePair) obj;
        if (this.a.equals(basicNameValuePair.a) && LangUtils.equals(this.b, basicNameValuePair.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, this.a), this.b);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
