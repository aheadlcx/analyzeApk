package cz.msebera.android.httpclient;

import java.util.Iterator;

public interface TokenIterator extends Iterator<Object> {
    boolean hasNext();

    String nextToken();
}
