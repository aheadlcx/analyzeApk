package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.ConnectionClosedException;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpRequestFactory;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.message.LineParser;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;

@NotThreadSafe
@Deprecated
public class HttpRequestParser extends AbstractMessageParser<HttpMessage> {
    private final HttpRequestFactory b;
    private final CharArrayBuffer c = new CharArrayBuffer(128);

    public HttpRequestParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpRequestFactory httpRequestFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.b = (HttpRequestFactory) Args.notNull(httpRequestFactory, "Request factory");
    }

    protected HttpMessage b(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException {
        this.c.clear();
        if (sessionInputBuffer.readLine(this.c) == -1) {
            throw new ConnectionClosedException("Client closed connection");
        }
        return this.b.newHttpRequest(this.a.parseRequestLine(this.c, new ParserCursor(0, this.c.length())));
    }
}
