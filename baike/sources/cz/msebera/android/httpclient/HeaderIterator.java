package cz.msebera.android.httpclient;

import java.util.Iterator;

public interface HeaderIterator extends Iterator<Object> {
    boolean hasNext();

    Header nextHeader();
}
