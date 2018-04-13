package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Immutable
class g {
    private final ResourceFactory a;

    g() {
        this(new HeapResourceFactory());
    }

    g(ResourceFactory resourceFactory) {
        this.a = resourceFactory;
    }

    public HttpCacheEntry updateCacheEntry(String str, HttpCacheEntry httpCacheEntry, Date date, Date date2, HttpResponse httpResponse) throws IOException {
        Args.check(httpResponse.getStatusLine().getStatusCode() == 304, "Response must have 304 status code");
        Header[] a = a(httpCacheEntry, httpResponse);
        Resource resource = null;
        if (httpCacheEntry.getResource() != null) {
            resource = this.a.copy(str, httpCacheEntry.getResource());
        }
        return new HttpCacheEntry(date, date2, httpCacheEntry.getStatusLine(), a, resource, httpCacheEntry.getRequestMethod());
    }

    protected Header[] a(HttpCacheEntry httpCacheEntry, HttpResponse httpResponse) {
        if (c(httpCacheEntry, httpResponse) && b(httpCacheEntry, httpResponse)) {
            return httpCacheEntry.getAllHeaders();
        }
        List arrayList = new ArrayList(Arrays.asList(httpCacheEntry.getAllHeaders()));
        a(arrayList, httpResponse);
        a(arrayList, httpCacheEntry);
        arrayList.addAll(Arrays.asList(httpResponse.getAllHeaders()));
        return (Header[]) arrayList.toArray(new Header[arrayList.size()]);
    }

    private void a(List<Header> list, HttpResponse httpResponse) {
        for (Header header : httpResponse.getAllHeaders()) {
            ListIterator listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                if (((Header) listIterator.next()).getName().equals(header.getName())) {
                    listIterator.remove();
                }
            }
        }
    }

    private void a(List<Header> list, HttpCacheEntry httpCacheEntry) {
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if ("Warning".equals(((Header) listIterator.next()).getName())) {
                for (Header value : httpCacheEntry.getHeaders("Warning")) {
                    if (value.getValue().startsWith("1")) {
                        listIterator.remove();
                    }
                }
            }
        }
    }

    private boolean b(HttpCacheEntry httpCacheEntry, HttpResponse httpResponse) {
        Date parseDate = DateUtils.parseDate(httpCacheEntry.getFirstHeader("Date").getValue());
        Date parseDate2 = DateUtils.parseDate(httpResponse.getFirstHeader("Date").getValue());
        if (parseDate == null || parseDate2 == null || !parseDate.after(parseDate2)) {
            return false;
        }
        return true;
    }

    private boolean c(HttpCacheEntry httpCacheEntry, HttpResponse httpResponse) {
        if (httpCacheEntry.getFirstHeader("Date") == null || httpResponse.getFirstHeader("Date") == null) {
            return false;
        }
        return true;
    }
}
