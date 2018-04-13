package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@ThreadSafe
public class HttpDateGenerator {
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    @GuardedBy("this")
    private final DateFormat a = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    @GuardedBy("this")
    private long b = 0;
    @GuardedBy("this")
    private String c = null;

    public HttpDateGenerator() {
        this.a.setTimeZone(GMT);
    }

    public synchronized String getCurrentDate() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.b > 1000) {
            this.c = this.a.format(new Date(currentTimeMillis));
            this.b = currentTimeMillis;
        }
        return this.c;
    }
}
