package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.Locale;

@ThreadSafe
class e {
    private final String a;
    private final SecureRandom b;
    @GuardedBy("this")
    private long c;

    public e() {
        String hostName;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostName = "localhost";
        }
        this.a = hostName;
        try {
            this.b = SecureRandom.getInstance("SHA1PRNG");
        } catch (Throwable e2) {
            throw new Error(e2);
        }
    }

    public synchronized void generate(StringBuilder stringBuilder) {
        this.c++;
        int nextInt = this.b.nextInt();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append('.');
        Formatter formatter = new Formatter(stringBuilder, Locale.US);
        formatter.format("%1$016x-%2$08x", new Object[]{Long.valueOf(this.c), Integer.valueOf(nextInt)});
        formatter.close();
        stringBuilder.append('.');
        stringBuilder.append(this.a);
    }

    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        generate(stringBuilder);
        return stringBuilder.toString();
    }
}
