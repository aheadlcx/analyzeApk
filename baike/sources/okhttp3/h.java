package okhttp3;

import java.util.Collections;
import java.util.List;

final class h implements CookieJar {
    h() {
    }

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return Collections.emptyList();
    }
}
