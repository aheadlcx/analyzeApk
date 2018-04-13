package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.security.Principal;

@Immutable
public class UsernamePasswordCredentials implements Credentials, Serializable {
    private final BasicUserPrincipal a;
    private final String b;

    public UsernamePasswordCredentials(String str) {
        Args.notNull(str, "Username:password string");
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            this.a = new BasicUserPrincipal(str.substring(0, indexOf));
            this.b = str.substring(indexOf + 1);
            return;
        }
        this.a = new BasicUserPrincipal(str);
        this.b = null;
    }

    public UsernamePasswordCredentials(String str, String str2) {
        Args.notNull(str, "Username");
        this.a = new BasicUserPrincipal(str);
        this.b = str2;
    }

    public Principal getUserPrincipal() {
        return this.a;
    }

    public String getUserName() {
        return this.a.getName();
    }

    public String getPassword() {
        return this.b;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UsernamePasswordCredentials) {
            if (LangUtils.equals(this.a, ((UsernamePasswordCredentials) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.a.toString();
    }
}
