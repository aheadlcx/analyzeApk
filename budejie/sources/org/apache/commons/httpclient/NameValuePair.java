package org.apache.commons.httpclient;

import java.io.Serializable;
import org.apache.commons.httpclient.util.LangUtils;

public class NameValuePair implements Serializable {
    private String name;
    private String value;

    public NameValuePair() {
        this(null, null);
    }

    public NameValuePair(String str, String str2) {
        this.name = null;
        this.value = null;
        this.name = str;
        this.value = str2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return new StringBuffer().append("name=").append(this.name).append(", ").append("value=").append(this.value).toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NameValuePair)) {
            return false;
        }
        NameValuePair nameValuePair = (NameValuePair) obj;
        if (!(LangUtils.equals(this.name, nameValuePair.name) && LangUtils.equals(this.value, nameValuePair.value))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
    }
}
