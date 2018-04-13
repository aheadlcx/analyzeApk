package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.MessageConstraintException;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.io.HttpMessageParser;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.message.BasicLineParser;
import cz.msebera.android.httpclient.message.LineParser;
import cz.msebera.android.httpclient.message.TokenParser;
import cz.msebera.android.httpclient.params.HttpParamConfig;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NotThreadSafe
public abstract class AbstractMessageParser<T extends HttpMessage> implements HttpMessageParser<T> {
    protected final LineParser a;
    private final SessionInputBuffer b;
    private final MessageConstraints c;
    private final List<CharArrayBuffer> d;
    private int e;
    private T f;

    protected abstract T b(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException;

    @Deprecated
    public AbstractMessageParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpParams httpParams) {
        Args.notNull(sessionInputBuffer, "Session input buffer");
        Args.notNull(httpParams, "HTTP parameters");
        this.b = sessionInputBuffer;
        this.c = HttpParamConfig.getMessageConstraints(httpParams);
        if (lineParser == null) {
            lineParser = BasicLineParser.INSTANCE;
        }
        this.a = lineParser;
        this.d = new ArrayList();
        this.e = 0;
    }

    public AbstractMessageParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, MessageConstraints messageConstraints) {
        this.b = (SessionInputBuffer) Args.notNull(sessionInputBuffer, "Session input buffer");
        if (lineParser == null) {
            lineParser = BasicLineParser.INSTANCE;
        }
        this.a = lineParser;
        if (messageConstraints == null) {
            messageConstraints = MessageConstraints.DEFAULT;
        }
        this.c = messageConstraints;
        this.d = new ArrayList();
        this.e = 0;
    }

    public static Header[] parseHeaders(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser) throws HttpException, IOException {
        List arrayList = new ArrayList();
        if (lineParser == null) {
            lineParser = BasicLineParser.INSTANCE;
        }
        return parseHeaders(sessionInputBuffer, i, i2, lineParser, arrayList);
    }

    public static Header[] parseHeaders(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser, List<CharArrayBuffer> list) throws HttpException, IOException {
        Header[] headerArr;
        int i3 = 0;
        Args.notNull(sessionInputBuffer, "Session input buffer");
        Args.notNull(lineParser, "Line parser");
        Args.notNull(list, "Header line list");
        CharArrayBuffer charArrayBuffer = null;
        CharArrayBuffer charArrayBuffer2 = null;
        while (true) {
            if (charArrayBuffer2 == null) {
                charArrayBuffer2 = new CharArrayBuffer(64);
            } else {
                charArrayBuffer2.clear();
            }
            if (sessionInputBuffer.readLine(charArrayBuffer2) == -1 || charArrayBuffer2.length() < 1) {
                headerArr = new Header[list.size()];
            } else {
                CharArrayBuffer charArrayBuffer3;
                if ((charArrayBuffer2.charAt(0) == TokenParser.SP || charArrayBuffer2.charAt(0) == '\t') && charArrayBuffer != null) {
                    int i4 = 0;
                    while (i4 < charArrayBuffer2.length()) {
                        char charAt = charArrayBuffer2.charAt(i4);
                        if (charAt != TokenParser.SP && charAt != '\t') {
                            break;
                        }
                        i4++;
                    }
                    if (i2 <= 0 || ((charArrayBuffer.length() + 1) + charArrayBuffer2.length()) - i4 <= i2) {
                        charArrayBuffer.append(TokenParser.SP);
                        charArrayBuffer.append(charArrayBuffer2, i4, charArrayBuffer2.length() - i4);
                        charArrayBuffer3 = charArrayBuffer2;
                        charArrayBuffer2 = charArrayBuffer;
                    } else {
                        throw new MessageConstraintException("Maximum line length limit exceeded");
                    }
                }
                list.add(charArrayBuffer2);
                charArrayBuffer3 = null;
                if (i <= 0 || list.size() < i) {
                    charArrayBuffer = charArrayBuffer2;
                    charArrayBuffer2 = charArrayBuffer3;
                } else {
                    throw new MessageConstraintException("Maximum header count exceeded");
                }
            }
        }
        headerArr = new Header[list.size()];
        while (i3 < list.size()) {
            try {
                headerArr[i3] = lineParser.parseHeader((CharArrayBuffer) list.get(i3));
                i3++;
            } catch (ParseException e) {
                throw new ProtocolException(e.getMessage());
            }
        }
        return headerArr;
    }

    public T parse() throws IOException, HttpException {
        switch (this.e) {
            case 0:
                try {
                    this.f = b(this.b);
                    this.e = 1;
                    break;
                } catch (Throwable e) {
                    throw new ProtocolException(e.getMessage(), e);
                }
            case 1:
                break;
            default:
                throw new IllegalStateException("Inconsistent parser state");
        }
        this.f.setHeaders(parseHeaders(this.b, this.c.getMaxHeaderCount(), this.c.getMaxLineLength(), this.a, this.d));
        T t = this.f;
        this.f = null;
        this.d.clear();
        this.e = 0;
        return t;
    }
}
