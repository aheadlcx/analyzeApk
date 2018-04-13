package org.apache.commons.httpclient.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterParser;

public final class AuthChallengeParser {
    public static String extractScheme(String str) throws MalformedChallengeException {
        if (str == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            str2 = str;
        } else {
            str2 = str.substring(0, indexOf);
        }
        if (!str2.equals("")) {
            return str2.toLowerCase();
        }
        throw new MalformedChallengeException(new StringBuffer().append("Invalid challenge: ").append(str).toString());
    }

    public static Map extractParams(String str) throws MalformedChallengeException {
        if (str == null) {
            throw new IllegalArgumentException("Challenge may not be null");
        }
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new MalformedChallengeException(new StringBuffer().append("Invalid challenge: ").append(str).toString());
        }
        Map hashMap = new HashMap();
        List parse = new ParameterParser().parse(str.substring(indexOf + 1, str.length()), ',');
        for (int i = 0; i < parse.size(); i++) {
            NameValuePair nameValuePair = (NameValuePair) parse.get(i);
            hashMap.put(nameValuePair.getName().toLowerCase(), nameValuePair.getValue());
        }
        return hashMap;
    }

    public static Map parseChallenges(Header[] headerArr) throws MalformedChallengeException {
        if (headerArr == null) {
            throw new IllegalArgumentException("Array of challenges may not be null");
        }
        Map hashMap = new HashMap(headerArr.length);
        for (NameValuePair value : headerArr) {
            String value2 = value.getValue();
            hashMap.put(extractScheme(value2), value2);
        }
        return hashMap;
    }
}
