package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.security.Principal;

@Immutable
public final class BasicUserPrincipal implements Serializable, Principal {
    private final String a;

    public BasicUserPrincipal(String str) {
        Args.notNull(str, "User name");
        this.a = str;
    }

    public String getName() {
        return this.a;
    }

    public int hashCode() {
        return LangUtils.hashCode(17, this.a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BasicUserPrincipal) {
            if (LangUtils.equals(this.a, ((BasicUserPrincipal) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[principal: ");
        stringBuilder.append(this.a);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
