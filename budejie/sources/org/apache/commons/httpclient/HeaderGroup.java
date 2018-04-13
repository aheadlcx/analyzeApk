package org.apache.commons.httpclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeaderGroup {
    private List headers = new ArrayList();

    public void clear() {
        this.headers.clear();
    }

    public void addHeader(Header header) {
        this.headers.add(header);
    }

    public void removeHeader(Header header) {
        this.headers.remove(header);
    }

    public void setHeaders(Header[] headerArr) {
        clear();
        for (Header addHeader : headerArr) {
            addHeader(addHeader);
        }
    }

    public Header getCondensedHeader(String str) {
        int i = 1;
        Header[] headers = getHeaders(str);
        if (headers.length == 0) {
            return null;
        }
        if (headers.length == 1) {
            return new Header(headers[0].getName(), headers[0].getValue());
        }
        StringBuffer stringBuffer = new StringBuffer(headers[0].getValue());
        while (i < headers.length) {
            stringBuffer.append(", ");
            stringBuffer.append(headers[i].getValue());
            i++;
        }
        return new Header(str.toLowerCase(), stringBuffer.toString());
    }

    public Header[] getHeaders(String str) {
        ArrayList arrayList = new ArrayList();
        for (Header header : this.headers) {
            if (header.getName().equalsIgnoreCase(str)) {
                arrayList.add(header);
            }
        }
        return (Header[]) arrayList.toArray(new Header[arrayList.size()]);
    }

    public Header getFirstHeader(String str) {
        for (Header header : this.headers) {
            if (header.getName().equalsIgnoreCase(str)) {
                return header;
            }
        }
        return null;
    }

    public Header getLastHeader(String str) {
        for (int size = this.headers.size() - 1; size >= 0; size--) {
            Header header = (Header) this.headers.get(size);
            if (header.getName().equalsIgnoreCase(str)) {
                return header;
            }
        }
        return null;
    }

    public Header[] getAllHeaders() {
        return (Header[]) this.headers.toArray(new Header[this.headers.size()]);
    }

    public boolean containsHeader(String str) {
        for (Header name : this.headers) {
            if (name.getName().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public Iterator getIterator() {
        return this.headers.iterator();
    }
}
