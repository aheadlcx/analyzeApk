package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;

@NotThreadSafe
public class BasicHeaderElement implements HeaderElement, Cloneable {
    private final String a;
    private final String b;
    private final NameValuePair[] c;

    public BasicHeaderElement(String str, String str2, NameValuePair[] nameValuePairArr) {
        this.a = (String) Args.notNull(str, "Name");
        this.b = str2;
        if (nameValuePairArr != null) {
            this.c = nameValuePairArr;
        } else {
            this.c = new NameValuePair[0];
        }
    }

    public BasicHeaderElement(String str, String str2) {
        this(str, str2, null);
    }

    public String getName() {
        return this.a;
    }

    public String getValue() {
        return this.b;
    }

    public NameValuePair[] getParameters() {
        return (NameValuePair[]) this.c.clone();
    }

    public int getParameterCount() {
        return this.c.length;
    }

    public NameValuePair getParameter(int i) {
        return this.c[i];
    }

    public NameValuePair getParameterByName(String str) {
        Args.notNull(str, "Name");
        for (NameValuePair nameValuePair : this.c) {
            if (nameValuePair.getName().equalsIgnoreCase(str)) {
                return nameValuePair;
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HeaderElement)) {
            return false;
        }
        BasicHeaderElement basicHeaderElement = (BasicHeaderElement) obj;
        if (this.a.equals(basicHeaderElement.a) && LangUtils.equals(this.b, basicHeaderElement.b) && LangUtils.equals(this.c, basicHeaderElement.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = LangUtils.hashCode(LangUtils.hashCode(17, this.a), this.b);
        for (Object hashCode2 : this.c) {
            hashCode = LangUtils.hashCode(hashCode, hashCode2);
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        if (this.b != null) {
            stringBuilder.append("=");
            stringBuilder.append(this.b);
        }
        for (Object obj : this.c) {
            stringBuilder.append("; ");
            stringBuilder.append(obj);
        }
        return stringBuilder.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
