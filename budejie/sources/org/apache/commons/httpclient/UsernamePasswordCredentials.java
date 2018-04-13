package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.LangUtils;

public class UsernamePasswordCredentials implements Credentials {
    private String password;
    private String userName;

    public UsernamePasswordCredentials(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Username:password string may not be null");
        }
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            this.userName = str.substring(0, indexOf);
            this.password = str.substring(indexOf + 1);
            return;
        }
        this.userName = str;
    }

    public UsernamePasswordCredentials(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Username may not be null");
        }
        this.userName = str;
        this.password = str2;
    }

    public void setUserName(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Username may not be null");
        }
        this.userName = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getPassword() {
        return this.password;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.userName);
        stringBuffer.append(":");
        stringBuffer.append(this.password == null ? "null" : this.password);
        return stringBuffer.toString();
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, this.userName), this.password);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) obj;
        if (LangUtils.equals(this.userName, usernamePasswordCredentials.userName) && LangUtils.equals(this.password, usernamePasswordCredentials.password)) {
            return true;
        }
        return false;
    }
}
