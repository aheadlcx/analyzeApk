package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.cookie.CommonCookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SM;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.Args;
import io.agora.rtc.Constants;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Immutable
public class LaxExpiresHandler extends AbstractCookieAttributeHandler implements CommonCookieAttributeHandler {
    static final TimeZone a = TimeZone.getTimeZone("UTC");
    private static final BitSet b;
    private static final Map<String, Integer> c;
    private static final Pattern d = Pattern.compile("^([0-9]{1,2}):([0-9]{1,2}):([0-9]{1,2})([^0-9].*)?$");
    private static final Pattern e = Pattern.compile("^([0-9]{1,2})([^0-9].*)?$");
    private static final Pattern f = Pattern.compile("^(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)(.*)?$", 2);
    private static final Pattern g = Pattern.compile("^([0-9]{2,4})([^0-9].*)?$");

    static {
        int i;
        BitSet bitSet = new BitSet();
        bitSet.set(9);
        for (i = 32; i <= 47; i++) {
            bitSet.set(i);
        }
        for (i = 59; i <= 64; i++) {
            bitSet.set(i);
        }
        for (i = 91; i <= 96; i++) {
            bitSet.set(i);
        }
        for (i = 123; i <= 126; i++) {
            bitSet.set(i);
        }
        b = bitSet;
        Map concurrentHashMap = new ConcurrentHashMap(12);
        concurrentHashMap.put("jan", Integer.valueOf(0));
        concurrentHashMap.put("feb", Integer.valueOf(1));
        concurrentHashMap.put("mar", Integer.valueOf(2));
        concurrentHashMap.put("apr", Integer.valueOf(3));
        concurrentHashMap.put("may", Integer.valueOf(4));
        concurrentHashMap.put("jun", Integer.valueOf(5));
        concurrentHashMap.put("jul", Integer.valueOf(6));
        concurrentHashMap.put("aug", Integer.valueOf(7));
        concurrentHashMap.put("sep", Integer.valueOf(8));
        concurrentHashMap.put("oct", Integer.valueOf(9));
        concurrentHashMap.put("nov", Integer.valueOf(10));
        concurrentHashMap.put("dec", Integer.valueOf(11));
        c = concurrentHashMap;
    }

    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Args.notNull(setCookie, SM.COOKIE);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        CharSequence stringBuilder = new StringBuilder();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        Object obj = null;
        Object obj2 = null;
        int i6 = 0;
        Object obj3 = null;
        Object obj4 = null;
        while (!parserCursor.atEnd()) {
            try {
                a(str, parserCursor);
                stringBuilder.setLength(0);
                a(str, parserCursor, stringBuilder);
                if (stringBuilder.length() == 0) {
                    break;
                }
                Matcher matcher;
                if (obj == null) {
                    matcher = d.matcher(stringBuilder);
                    if (matcher.matches()) {
                        int parseInt = Integer.parseInt(matcher.group(1));
                        i3 = Integer.parseInt(matcher.group(2));
                        i = Integer.parseInt(matcher.group(3));
                        i2 = i3;
                        i3 = parseInt;
                        obj = 1;
                    }
                }
                if (obj3 == null) {
                    matcher = e.matcher(stringBuilder);
                    if (matcher.matches()) {
                        i4 = Integer.parseInt(matcher.group(1));
                        obj3 = 1;
                    }
                }
                if (obj2 == null) {
                    Matcher matcher2 = f.matcher(stringBuilder);
                    if (matcher2.matches()) {
                        obj2 = 1;
                        i5 = ((Integer) c.get(matcher2.group(1).toLowerCase(Locale.ROOT))).intValue();
                    }
                }
                if (obj4 == null) {
                    matcher = g.matcher(stringBuilder);
                    if (matcher.matches()) {
                        i6 = Integer.parseInt(matcher.group(1));
                        obj4 = 1;
                    }
                }
            } catch (NumberFormatException e) {
                throw new MalformedCookieException("Invalid 'expires' attribute: " + str);
            }
        }
        if (obj == null || obj3 == null || obj2 == null || obj4 == null) {
            throw new MalformedCookieException("Invalid 'expires' attribute: " + str);
        }
        int i7;
        if (i6 < 70 || i6 > 99) {
            i7 = i6;
        } else {
            i7 = i6 + 1900;
        }
        if (i7 >= 0 && i7 <= 69) {
            i7 += 2000;
        }
        if (i4 < 1 || i4 > 31 || i7 < Constants.ERR_VCM_ENCODER_INIT_ERROR || i3 > 23 || i2 > 59 || i > 59) {
            throw new MalformedCookieException("Invalid 'expires' attribute: " + str);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(a);
        instance.setTimeInMillis(0);
        instance.set(13, i);
        instance.set(12, i2);
        instance.set(11, i3);
        instance.set(5, i4);
        instance.set(2, i5);
        instance.set(1, i7);
        setCookie.setExpiryDate(instance.getTime());
    }

    private void a(CharSequence charSequence, ParserCursor parserCursor) {
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        for (int pos2 = parserCursor.getPos(); pos2 < upperBound; pos2++) {
            if (!b.get(charSequence.charAt(pos2))) {
                break;
            }
            pos++;
        }
        parserCursor.updatePos(pos);
    }

    private void a(CharSequence charSequence, ParserCursor parserCursor, StringBuilder stringBuilder) {
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        for (int pos2 = parserCursor.getPos(); pos2 < upperBound; pos2++) {
            char charAt = charSequence.charAt(pos2);
            if (b.get(charAt)) {
                break;
            }
            pos++;
            stringBuilder.append(charAt);
        }
        parserCursor.updatePos(pos);
    }

    public String getAttributeName() {
        return "max-age";
    }
}
