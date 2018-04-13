package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.TokenIterator;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.NoSuchElementException;

@NotThreadSafe
public class BasicTokenIterator implements TokenIterator {
    public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
    protected final HeaderIterator a;
    protected String b;
    protected String c;
    protected int d = a(-1);

    public BasicTokenIterator(HeaderIterator headerIterator) {
        this.a = (HeaderIterator) Args.notNull(headerIterator, "Header iterator");
    }

    public boolean hasNext() {
        return this.c != null;
    }

    public String nextToken() throws NoSuchElementException, ParseException {
        if (this.c == null) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        String str = this.c;
        this.d = a(this.d);
        return str;
    }

    public final Object next() throws NoSuchElementException, ParseException {
        return nextToken();
    }

    public final void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }

    protected int a(int i) throws ParseException {
        int c;
        if (i >= 0) {
            c = c(i);
        } else if (!this.a.hasNext()) {
            return -1;
        } else {
            this.b = this.a.nextHeader().getValue();
            c = 0;
        }
        int b = b(c);
        if (b < 0) {
            this.c = null;
            return -1;
        }
        c = d(b);
        this.c = a(this.b, b, c);
        return c;
    }

    protected String a(String str, int i, int i2) {
        return str.substring(i, i2);
    }

    protected int b(int i) {
        int notNegative = Args.notNegative(i, "Search position");
        Object obj = null;
        while (obj == null && this.b != null) {
            int length = this.b.length();
            Object obj2 = obj;
            int i2 = notNegative;
            Object obj3 = obj2;
            while (obj3 == null && i2 < length) {
                char charAt = this.b.charAt(i2);
                if (a(charAt) || b(charAt)) {
                    i2++;
                } else if (c(this.b.charAt(i2))) {
                    obj3 = 1;
                } else {
                    throw new ParseException("Invalid character before token (pos " + i2 + "): " + this.b);
                }
            }
            if (obj3 == null) {
                if (this.a.hasNext()) {
                    this.b = this.a.nextHeader().getValue();
                    i2 = 0;
                } else {
                    this.b = null;
                }
            }
            obj2 = obj3;
            notNegative = i2;
            obj = obj2;
        }
        return obj != null ? notNegative : -1;
    }

    protected int c(int i) {
        int notNegative = Args.notNegative(i, "Search position");
        Object obj = null;
        int length = this.b.length();
        while (obj == null && notNegative < length) {
            char charAt = this.b.charAt(notNegative);
            if (a(charAt)) {
                obj = 1;
            } else if (b(charAt)) {
                notNegative++;
            } else if (c(charAt)) {
                throw new ParseException("Tokens without separator (pos " + notNegative + "): " + this.b);
            } else {
                throw new ParseException("Invalid character after token (pos " + notNegative + "): " + this.b);
            }
        }
        return notNegative;
    }

    protected int d(int i) {
        Args.notNegative(i, "Search position");
        int length = this.b.length();
        int i2 = i + 1;
        while (i2 < length && c(this.b.charAt(i2))) {
            i2++;
        }
        return i2;
    }

    protected boolean a(char c) {
        return c == ',';
    }

    protected boolean b(char c) {
        return c == '\t' || Character.isSpaceChar(c);
    }

    protected boolean c(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        }
        if (Character.isISOControl(c)) {
            return false;
        }
        if (d(c)) {
            return false;
        }
        return true;
    }

    protected boolean d(char c) {
        return HTTP_SEPARATORS.indexOf(c) >= 0;
    }
}
