package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.message.TokenParser;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;

@Immutable
public class NTUserPrincipal implements Serializable, Principal {
    private final String a;
    private final String b;
    private final String c;

    public NTUserPrincipal(String str, String str2) {
        Args.notNull(str2, "User name");
        this.a = str2;
        if (str != null) {
            this.b = str.toUpperCase(Locale.ROOT);
        } else {
            this.b = null;
        }
        if (this.b == null || this.b.isEmpty()) {
            this.c = this.a;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.b);
        stringBuilder.append(TokenParser.ESCAPE);
        stringBuilder.append(this.a);
        this.c = stringBuilder.toString();
    }

    public String getName() {
        return this.c;
    }

    public String getDomain() {
        return this.b;
    }

    public String getUsername() {
        return this.a;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(17, this.a), this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NTUserPrincipal) {
            NTUserPrincipal nTUserPrincipal = (NTUserPrincipal) obj;
            if (LangUtils.equals(this.a, nTUserPrincipal.a) && LangUtils.equals(this.b, nTUserPrincipal.b)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.c;
    }
}
