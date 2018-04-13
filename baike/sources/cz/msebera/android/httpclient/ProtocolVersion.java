package cz.msebera.android.httpclient;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

@Immutable
public class ProtocolVersion implements Serializable, Cloneable {
    protected final String a;
    protected final int b;
    protected final int c;

    public ProtocolVersion(String str, int i, int i2) {
        this.a = (String) Args.notNull(str, "Protocol name");
        this.b = Args.notNegative(i, "Protocol minor version");
        this.c = Args.notNegative(i2, "Protocol minor version");
    }

    public final String getProtocol() {
        return this.a;
    }

    public final int getMajor() {
        return this.b;
    }

    public final int getMinor() {
        return this.c;
    }

    public ProtocolVersion forVersion(int i, int i2) {
        return (i == this.b && i2 == this.c) ? this : new ProtocolVersion(this.a, i, i2);
    }

    public final int hashCode() {
        return (this.a.hashCode() ^ (this.b * 100000)) ^ this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProtocolVersion)) {
            return false;
        }
        ProtocolVersion protocolVersion = (ProtocolVersion) obj;
        if (this.a.equals(protocolVersion.a) && this.b == protocolVersion.b && this.c == protocolVersion.c) {
            return true;
        }
        return false;
    }

    public boolean isComparable(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.a.equals(protocolVersion.a);
    }

    public int compareToVersion(ProtocolVersion protocolVersion) {
        Args.notNull(protocolVersion, "Protocol version");
        Args.check(this.a.equals(protocolVersion.a), "Versions for different protocols cannot be compared: %s %s", this, protocolVersion);
        int major = getMajor() - protocolVersion.getMajor();
        if (major == 0) {
            return getMinor() - protocolVersion.getMinor();
        }
        return major;
    }

    public final boolean greaterEquals(ProtocolVersion protocolVersion) {
        return isComparable(protocolVersion) && compareToVersion(protocolVersion) >= 0;
    }

    public final boolean lessEquals(ProtocolVersion protocolVersion) {
        return isComparable(protocolVersion) && compareToVersion(protocolVersion) <= 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append('/');
        stringBuilder.append(Integer.toString(this.b));
        stringBuilder.append('.');
        stringBuilder.append(Integer.toString(this.c));
        return stringBuilder.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
