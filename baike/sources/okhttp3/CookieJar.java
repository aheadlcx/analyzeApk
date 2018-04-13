package okhttp3;

import java.util.List;

public interface CookieJar {
    public static final CookieJar NO_COOKIES = new h();

    List<Cookie> loadForRequest(HttpUrl httpUrl);

    void saveFromResponse(HttpUrl httpUrl, List<Cookie> list);
}
