package cz.msebera.android.httpclient.conn.scheme;

import android.support.v4.internal.view.SupportMenu;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.util.Locale;

@Immutable
@Deprecated
public final class Scheme {
    private final String a;
    private final SchemeSocketFactory b;
    private final int c;
    private final boolean d;
    private String e;

    public Scheme(String str, int i, SchemeSocketFactory schemeSocketFactory) {
        Args.notNull(str, "Scheme name");
        boolean z = i > 0 && i <= SupportMenu.USER_MASK;
        Args.check(z, "Port is invalid");
        Args.notNull(schemeSocketFactory, "Socket factory");
        this.a = str.toLowerCase(Locale.ENGLISH);
        this.c = i;
        if (schemeSocketFactory instanceof SchemeLayeredSocketFactory) {
            this.d = true;
            this.b = schemeSocketFactory;
        } else if (schemeSocketFactory instanceof LayeredSchemeSocketFactory) {
            this.d = true;
            this.b = new c((LayeredSchemeSocketFactory) schemeSocketFactory);
        } else {
            this.d = false;
            this.b = schemeSocketFactory;
        }
    }

    @Deprecated
    public Scheme(String str, SocketFactory socketFactory, int i) {
        Args.notNull(str, "Scheme name");
        Args.notNull(socketFactory, "Socket factory");
        boolean z = i > 0 && i <= SupportMenu.USER_MASK;
        Args.check(z, "Port is invalid");
        this.a = str.toLowerCase(Locale.ENGLISH);
        if (socketFactory instanceof LayeredSocketFactory) {
            this.b = new b((LayeredSocketFactory) socketFactory);
            this.d = true;
        } else {
            this.b = new d(socketFactory);
            this.d = false;
        }
        this.c = i;
    }

    public final int getDefaultPort() {
        return this.c;
    }

    @Deprecated
    public final SocketFactory getSocketFactory() {
        if (this.b instanceof d) {
            return ((d) this.b).getFactory();
        }
        if (this.d) {
            return new a((LayeredSchemeSocketFactory) this.b);
        }
        return new e(this.b);
    }

    public final SchemeSocketFactory getSchemeSocketFactory() {
        return this.b;
    }

    public final String getName() {
        return this.a;
    }

    public final boolean isLayered() {
        return this.d;
    }

    public final int resolvePort(int i) {
        return i <= 0 ? this.c : i;
    }

    public final String toString() {
        if (this.e == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append(':');
            stringBuilder.append(Integer.toString(this.c));
            this.e = stringBuilder.toString();
        }
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scheme)) {
            return false;
        }
        Scheme scheme = (Scheme) obj;
        if (this.a.equals(scheme.a) && this.c == scheme.c && this.d == scheme.d) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.c), this.a), this.d);
    }
}
