package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.NoHttpResponseException;
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
public class HttpResponseParser extends AbstractMessageParser<HttpMessage> {
    private final HttpResponseFactory b;
    private final CharArrayBuffer c = new CharArrayBuffer(128);

    public HttpResponseParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.b = (HttpResponseFactory) Args.notNull(httpResponseFactory, "Response factory");
    }

    protected HttpMessage b(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException {
        this.c.clear();
        if (sessionInputBuffer.readLine(this.c) == -1) {
            throw new NoHttpResponseException("The target server failed to respond");
        }
        return this.b.newHttpResponse(this.a.parseStatusLine(this.c, new ParserCursor(0, this.c.length())), null);
    }
}
