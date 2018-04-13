package cz.msebera.android.httpclient.message;

import com.alipay.sdk.packet.d;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.io.Serializable;

@Immutable
public class BasicRequestLine implements RequestLine, Serializable, Cloneable {
    private final ProtocolVersion a;
    private final String b;
    private final String c;

    public BasicRequestLine(String str, String str2, ProtocolVersion protocolVersion) {
        this.b = (String) Args.notNull(str, "Method");
        this.c = (String) Args.notNull(str2, "URI");
        this.a = (ProtocolVersion) Args.notNull(protocolVersion, d.e);
    }

    public String getMethod() {
        return this.b;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.a;
    }

    public String getUri() {
        return this.c;
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatRequestLine(null, this).toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
