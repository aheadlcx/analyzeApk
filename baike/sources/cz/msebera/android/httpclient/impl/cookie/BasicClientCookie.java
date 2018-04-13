package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@NotThreadSafe
public class BasicClientCookie implements ClientCookie, SetCookie, Serializable, Cloneable {
    private final String a;
    private Map<String, String> b = new HashMap();
    private String c;
    private String d;
    private String e;
    private Date f;
    private String g;
    private boolean h;
    private int i;
    private Date j;

    public BasicClientCookie(String str, String str2) {
        Args.notNull(str, "Name");
        this.a = str;
        this.c = str2;
    }

    public String getName() {
        return this.a;
    }

    public String getValue() {
        return this.c;
    }

    public void setValue(String str) {
        this.c = str;
    }

    public String getComment() {
        return this.d;
    }

    public void setComment(String str) {
        this.d = str;
    }

    public String getCommentURL() {
        return null;
    }

    public Date getExpiryDate() {
        return this.f;
    }

    public void setExpiryDate(Date date) {
        this.f = date;
    }

    public boolean isPersistent() {
        return this.f != null;
    }

    public String getDomain() {
        return this.e;
    }

    public void setDomain(String str) {
        if (str != null) {
            this.e = str.toLowerCase(Locale.ROOT);
        } else {
            this.e = null;
        }
    }

    public String getPath() {
        return this.g;
    }

    public void setPath(String str) {
        this.g = str;
    }

    public boolean isSecure() {
        return this.h;
    }

    public void setSecure(boolean z) {
        this.h = z;
    }

    public int[] getPorts() {
        return null;
    }

    public int getVersion() {
        return this.i;
    }

    public void setVersion(int i) {
        this.i = i;
    }

    public boolean isExpired(Date date) {
        Args.notNull(date, "Date");
        return this.f != null && this.f.getTime() <= date.getTime();
    }

    public Date getCreationDate() {
        return this.j;
    }

    public void setCreationDate(Date date) {
        this.j = date;
    }

    public void setAttribute(String str, String str2) {
        this.b.put(str, str2);
    }

    public String getAttribute(String str) {
        return (String) this.b.get(str);
    }

    public boolean containsAttribute(String str) {
        return this.b.containsKey(str);
    }

    public boolean removeAttribute(String str) {
        return this.b.remove(str) != null;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie basicClientCookie = (BasicClientCookie) super.clone();
        basicClientCookie.b = new HashMap(this.b);
        return basicClientCookie;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[version: ");
        stringBuilder.append(Integer.toString(this.i));
        stringBuilder.append("]");
        stringBuilder.append("[name: ");
        stringBuilder.append(this.a);
        stringBuilder.append("]");
        stringBuilder.append("[value: ");
        stringBuilder.append(this.c);
        stringBuilder.append("]");
        stringBuilder.append("[domain: ");
        stringBuilder.append(this.e);
        stringBuilder.append("]");
        stringBuilder.append("[path: ");
        stringBuilder.append(this.g);
        stringBuilder.append("]");
        stringBuilder.append("[expiry: ");
        stringBuilder.append(this.f);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
