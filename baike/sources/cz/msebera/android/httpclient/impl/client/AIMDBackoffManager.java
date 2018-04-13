package cz.msebera.android.httpclient.impl.client;

import com.baidu.mobstat.Config;
import cz.msebera.android.httpclient.client.BackoffManager;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.pool.ConnPoolControl;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;
import java.util.Map;

public class AIMDBackoffManager implements BackoffManager {
    private final ConnPoolControl<HttpRoute> a;
    private final c b;
    private final Map<HttpRoute, Long> c;
    private final Map<HttpRoute, Long> d;
    private long e;
    private double f;
    private int g;

    public AIMDBackoffManager(ConnPoolControl<HttpRoute> connPoolControl) {
        this(connPoolControl, new n());
    }

    AIMDBackoffManager(ConnPoolControl<HttpRoute> connPoolControl, c cVar) {
        this.e = Config.BPLUS_DELAY_TIME;
        this.f = 0.5d;
        this.g = 2;
        this.b = cVar;
        this.a = connPoolControl;
        this.c = new HashMap();
        this.d = new HashMap();
    }

    public void backOff(HttpRoute httpRoute) {
        synchronized (this.a) {
            int maxPerRoute = this.a.getMaxPerRoute(httpRoute);
            Long a = a(this.d, httpRoute);
            long currentTime = this.b.getCurrentTime();
            if (currentTime - a.longValue() < this.e) {
                return;
            }
            this.a.setMaxPerRoute(httpRoute, a(maxPerRoute));
            this.d.put(httpRoute, Long.valueOf(currentTime));
        }
    }

    private int a(int i) {
        if (i <= 1) {
            return 1;
        }
        return (int) Math.floor(this.f * ((double) i));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void probe(cz.msebera.android.httpclient.conn.routing.HttpRoute r11) {
        /*
        r10 = this;
        r1 = r10.a;
        monitor-enter(r1);
        r0 = r10.a;	 Catch:{ all -> 0x004e }
        r0 = r0.getMaxPerRoute(r11);	 Catch:{ all -> 0x004e }
        r2 = r10.g;	 Catch:{ all -> 0x004e }
        if (r0 < r2) goto L_0x003b;
    L_0x000d:
        r0 = r10.g;	 Catch:{ all -> 0x004e }
    L_0x000f:
        r2 = r10.c;	 Catch:{ all -> 0x004e }
        r2 = r10.a(r2, r11);	 Catch:{ all -> 0x004e }
        r3 = r10.d;	 Catch:{ all -> 0x004e }
        r3 = r10.a(r3, r11);	 Catch:{ all -> 0x004e }
        r4 = r10.b;	 Catch:{ all -> 0x004e }
        r4 = r4.getCurrentTime();	 Catch:{ all -> 0x004e }
        r6 = r2.longValue();	 Catch:{ all -> 0x004e }
        r6 = r4 - r6;
        r8 = r10.e;	 Catch:{ all -> 0x004e }
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x0039;
    L_0x002d:
        r2 = r3.longValue();	 Catch:{ all -> 0x004e }
        r2 = r4 - r2;
        r6 = r10.e;	 Catch:{ all -> 0x004e }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 >= 0) goto L_0x003e;
    L_0x0039:
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
    L_0x003a:
        return;
    L_0x003b:
        r0 = r0 + 1;
        goto L_0x000f;
    L_0x003e:
        r2 = r10.a;	 Catch:{ all -> 0x004e }
        r2.setMaxPerRoute(r11, r0);	 Catch:{ all -> 0x004e }
        r0 = r10.c;	 Catch:{ all -> 0x004e }
        r2 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x004e }
        r0.put(r11, r2);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x003a;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.client.AIMDBackoffManager.probe(cz.msebera.android.httpclient.conn.routing.HttpRoute):void");
    }

    private Long a(Map<HttpRoute, Long> map, HttpRoute httpRoute) {
        Long l = (Long) map.get(httpRoute);
        if (l == null) {
            return Long.valueOf(0);
        }
        return l;
    }

    public void setBackoffFactor(double d) {
        boolean z = d > 0.0d && d < 1.0d;
        Args.check(z, "Backoff factor must be 0.0 < f < 1.0");
        this.f = d;
    }

    public void setCooldownMillis(long j) {
        Args.positive(this.e, "Cool down");
        this.e = j;
    }

    public void setPerHostConnectionCap(int i) {
        Args.positive(i, "Per host connection cap");
        this.g = i;
    }
}
