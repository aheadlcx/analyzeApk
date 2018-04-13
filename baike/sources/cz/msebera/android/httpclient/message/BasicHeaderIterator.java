package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.NoSuchElementException;

@NotThreadSafe
public class BasicHeaderIterator implements HeaderIterator {
    protected final Header[] a;
    protected int b = a(-1);
    protected String c;

    public BasicHeaderIterator(Header[] headerArr, String str) {
        this.a = (Header[]) Args.notNull(headerArr, "Header array");
        this.c = str;
    }

    protected int a(int i) {
        if (i < -1) {
            return -1;
        }
        int length = this.a.length - 1;
        boolean z = false;
        int i2 = i;
        while (!z && i2 < length) {
            i = i2 + 1;
            z = b(i);
            i2 = i;
        }
        if (!z) {
            i2 = -1;
        }
        return i2;
    }

    protected boolean b(int i) {
        return this.c == null || this.c.equalsIgnoreCase(this.a[i].getName());
    }

    public boolean hasNext() {
        return this.b >= 0;
    }

    public Header nextHeader() throws NoSuchElementException {
        int i = this.b;
        if (i < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.b = a(i);
        return this.a[i];
    }

    public final Object next() throws NoSuchElementException {
        return nextHeader();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing headers is not supported.");
    }
}
