package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.ReasonPhraseCatalog;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;

@NotThreadSafe
public class BasicHttpResponse extends AbstractHttpMessage implements HttpResponse {
    private StatusLine c;
    private ProtocolVersion d;
    private int e;
    private String f;
    private HttpEntity g;
    private final ReasonPhraseCatalog h;
    private Locale i;

    public BasicHttpResponse(StatusLine statusLine, ReasonPhraseCatalog reasonPhraseCatalog, Locale locale) {
        this.c = (StatusLine) Args.notNull(statusLine, "Status line");
        this.d = statusLine.getProtocolVersion();
        this.e = statusLine.getStatusCode();
        this.f = statusLine.getReasonPhrase();
        this.h = reasonPhraseCatalog;
        this.i = locale;
    }

    public BasicHttpResponse(StatusLine statusLine) {
        this.c = (StatusLine) Args.notNull(statusLine, "Status line");
        this.d = statusLine.getProtocolVersion();
        this.e = statusLine.getStatusCode();
        this.f = statusLine.getReasonPhrase();
        this.h = null;
        this.i = null;
    }

    public BasicHttpResponse(ProtocolVersion protocolVersion, int i, String str) {
        Args.notNegative(i, "Status code");
        this.c = null;
        this.d = protocolVersion;
        this.e = i;
        this.f = str;
        this.h = null;
        this.i = null;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.d;
    }

    public StatusLine getStatusLine() {
        if (this.c == null) {
            String str;
            ProtocolVersion protocolVersion = this.d != null ? this.d : HttpVersion.HTTP_1_1;
            int i = this.e;
            if (this.f != null) {
                str = this.f;
            } else {
                str = a(this.e);
            }
            this.c = new BasicStatusLine(protocolVersion, i, str);
        }
        return this.c;
    }

    public HttpEntity getEntity() {
        return this.g;
    }

    public Locale getLocale() {
        return this.i;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.c = (StatusLine) Args.notNull(statusLine, "Status line");
        this.d = statusLine.getProtocolVersion();
        this.e = statusLine.getStatusCode();
        this.f = statusLine.getReasonPhrase();
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i) {
        Args.notNegative(i, "Status code");
        this.c = null;
        this.d = protocolVersion;
        this.e = i;
        this.f = null;
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        Args.notNegative(i, "Status code");
        this.c = null;
        this.d = protocolVersion;
        this.e = i;
        this.f = str;
    }

    public void setStatusCode(int i) {
        Args.notNegative(i, "Status code");
        this.c = null;
        this.e = i;
        this.f = null;
    }

    public void setReasonPhrase(String str) {
        this.c = null;
        this.f = str;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.g = httpEntity;
    }

    public void setLocale(Locale locale) {
        this.i = (Locale) Args.notNull(locale, "Locale");
        this.c = null;
    }

    protected String a(int i) {
        if (this.h == null) {
            return null;
        }
        Locale locale;
        ReasonPhraseCatalog reasonPhraseCatalog = this.h;
        if (this.i != null) {
            locale = this.i;
        } else {
            locale = Locale.getDefault();
        }
        return reasonPhraseCatalog.getReason(i, locale);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStatusLine());
        stringBuilder.append(TokenParser.SP);
        stringBuilder.append(this.a);
        if (this.g != null) {
            stringBuilder.append(TokenParser.SP);
            stringBuilder.append(this.g);
        }
        return stringBuilder.toString();
    }
}
