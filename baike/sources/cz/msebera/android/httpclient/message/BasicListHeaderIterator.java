package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.List;
import java.util.NoSuchElementException;

@NotThreadSafe
public class BasicListHeaderIterator implements HeaderIterator {
    protected final List<Header> a;
    protected int b = a(-1);
    protected int c = -1;
    protected String d;

    public BasicListHeaderIterator(List<Header> list, String str) {
        this.a = (List) Args.notNull(list, "Header list");
        this.d = str;
    }

    protected int a(int i) {
        if (i < -1) {
            return -1;
        }
        int size = this.a.size() - 1;
        boolean z = false;
        int i2 = i;
        while (!z && i2 < size) {
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
        if (this.d == null) {
            return true;
        }
        return this.d.equalsIgnoreCase(((Header) this.a.get(i)).getName());
    }

    public boolean hasNext() {
        return this.b >= 0;
    }

    public Header nextHeader() throws NoSuchElementException {
        int i = this.b;
        if (i < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.c = i;
        this.b = a(i);
        return (Header) this.a.get(i);
    }

    public final Object next() throws NoSuchElementException {
        return nextHeader();
    }

    public void remove() throws UnsupportedOperationException {
        Asserts.check(this.c >= 0, "No header to remove");
        this.a.remove(this.c);
        this.c = -1;
        this.b--;
    }
}
