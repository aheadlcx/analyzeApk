package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import java.util.Comparator;
import java.util.Date;

@Immutable
public class CookiePriorityComparator implements Comparator<Cookie> {
    public static final CookiePriorityComparator INSTANCE = new CookiePriorityComparator();

    private int a(Cookie cookie) {
        String path = cookie.getPath();
        return path != null ? path.length() : 1;
    }

    public int compare(Cookie cookie, Cookie cookie2) {
        int a = a(cookie2) - a(cookie);
        if (a != 0 || !(cookie instanceof BasicClientCookie) || !(cookie2 instanceof BasicClientCookie)) {
            return a;
        }
        Date creationDate = ((BasicClientCookie) cookie).getCreationDate();
        Date creationDate2 = ((BasicClientCookie) cookie2).getCreationDate();
        if (creationDate == null || creationDate2 == null) {
            return a;
        }
        return (int) (creationDate.getTime() - creationDate2.getTime());
    }
}
