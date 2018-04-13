package cz.msebera.android.httpclient;

import java.util.Iterator;

public interface HeaderElementIterator extends Iterator<Object> {
    boolean hasNext();

    HeaderElement nextElement();
}
