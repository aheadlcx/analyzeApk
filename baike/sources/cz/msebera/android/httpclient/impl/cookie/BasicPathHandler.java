package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.cookie.CommonCookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieRestrictionViolationException;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SM;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@Immutable
public class BasicPathHandler implements CommonCookieAttributeHandler {
    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Args.notNull(setCookie, SM.COOKIE);
        if (TextUtils.isBlank(str)) {
            str = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        setCookie.setPath(str);
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        if (!match(cookie, cookieOrigin)) {
            throw new CookieRestrictionViolationException("Illegal 'path' attribute \"" + cookie.getPath() + "\". Path of origin: \"" + cookieOrigin.getPath() + "\"");
        }
    }

    static boolean a(String str, String str2) {
        if (str2 == null) {
            str2 = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        if (str2.length() > 1 && str2.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        if (str.startsWith(str2) && (str2.equals(MqttTopic.TOPIC_LEVEL_SEPARATOR) || str.length() == str2.length() || str.charAt(str2.length()) == '/')) {
            return true;
        }
        return false;
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, SM.COOKIE);
        Args.notNull(cookieOrigin, "Cookie origin");
        return a(cookieOrigin.getPath(), cookie.getPath());
    }

    public String getAttributeName() {
        return "path";
    }
}
