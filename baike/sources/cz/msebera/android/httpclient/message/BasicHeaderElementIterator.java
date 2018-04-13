package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HeaderElementIterator;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.NoSuchElementException;

@NotThreadSafe
public class BasicHeaderElementIterator implements HeaderElementIterator {
    private final HeaderIterator a;
    private final HeaderValueParser b;
    private HeaderElement c;
    private CharArrayBuffer d;
    private ParserCursor e;

    public BasicHeaderElementIterator(HeaderIterator headerIterator, HeaderValueParser headerValueParser) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = (HeaderIterator) Args.notNull(headerIterator, "Header iterator");
        this.b = (HeaderValueParser) Args.notNull(headerValueParser, "Parser");
    }

    public BasicHeaderElementIterator(HeaderIterator headerIterator) {
        this(headerIterator, BasicHeaderValueParser.INSTANCE);
    }

    private void a() {
        this.e = null;
        this.d = null;
        while (this.a.hasNext()) {
            Header nextHeader = this.a.nextHeader();
            if (nextHeader instanceof FormattedHeader) {
                this.d = ((FormattedHeader) nextHeader).getBuffer();
                this.e = new ParserCursor(0, this.d.length());
                this.e.updatePos(((FormattedHeader) nextHeader).getValuePos());
                return;
            }
            String value = nextHeader.getValue();
            if (value != null) {
                this.d = new CharArrayBuffer(value.length());
                this.d.append(value);
                this.e = new ParserCursor(0, this.d.length());
                return;
            }
        }
    }

    private void b() {
        HeaderElement parseHeaderElement;
        loop0:
        while (true) {
            if (this.a.hasNext() || this.e != null) {
                if (this.e == null || this.e.atEnd()) {
                    a();
                }
                if (this.e != null) {
                    while (!this.e.atEnd()) {
                        parseHeaderElement = this.b.parseHeaderElement(this.d, this.e);
                        if (parseHeaderElement.getName().length() == 0) {
                            if (parseHeaderElement.getValue() != null) {
                                break loop0;
                            }
                        }
                        break loop0;
                    }
                    if (this.e.atEnd()) {
                        this.e = null;
                        this.d = null;
                    }
                }
            } else {
                return;
            }
        }
        this.c = parseHeaderElement;
    }

    public boolean hasNext() {
        if (this.c == null) {
            b();
        }
        return this.c != null;
    }

    public HeaderElement nextElement() throws NoSuchElementException {
        if (this.c == null) {
            b();
        }
        if (this.c == null) {
            throw new NoSuchElementException("No more header elements available");
        }
        HeaderElement headerElement = this.c;
        this.c = null;
        return headerElement;
    }

    public final Object next() throws NoSuchElementException {
        return nextElement();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
