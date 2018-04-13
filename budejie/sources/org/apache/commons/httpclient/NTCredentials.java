package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.LangUtils;

public class NTCredentials extends UsernamePasswordCredentials {
    private String domain;
    private String host;

    public NTCredentials(String str, String str2, String str3, String str4) {
        super(str, str2);
        if (str4 == null) {
            throw new IllegalArgumentException("Domain may not be null");
        }
        this.domain = str4;
        if (str3 == null) {
            throw new IllegalArgumentException("Host may not be null");
        }
        this.host = str3;
    }

    public void setDomain(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Domain may not be null");
        }
        this.domain = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setHost(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Host may not be null");
        }
        this.host = str;
    }

    public String getHost() {
        return this.host;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(super.toString());
        stringBuffer.append("@");
        stringBuffer.append(this.host);
        stringBuffer.append(".");
        stringBuffer.append(this.domain);
        return stringBuffer.toString();
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(super.hashCode(), this.host), this.domain);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || !(obj instanceof NTCredentials)) {
            return false;
        }
        NTCredentials nTCredentials = (NTCredentials) obj;
        if (!(LangUtils.equals(this.domain, nTCredentials.domain) && LangUtils.equals(this.host, nTCredentials.host))) {
            z = false;
        }
        return z;
    }
}
