package cz.msebera.android.httpclient.message;

import com.alipay.sdk.packet.d;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

@Immutable
public class BasicStatusLine implements StatusLine, Serializable, Cloneable {
    private final ProtocolVersion a;
    private final int b;
    private final String c;

    public BasicStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        this.a = (ProtocolVersion) Args.notNull(protocolVersion, d.e);
        this.b = Args.notNegative(i, "Status code");
        this.c = str;
    }

    public int getStatusCode() {
        return this.b;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.a;
    }

    public String getReasonPhrase() {
        return this.c;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatStatusLine(null, this).toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
