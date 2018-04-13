package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ThreadSafe
public class ExponentialBackOffSchedulingStrategy implements SchedulingStrategy {
    public static final long DEFAULT_BACK_OFF_RATE = 10;
    public static final long DEFAULT_INITIAL_EXPIRY_IN_MILLIS = TimeUnit.SECONDS.toMillis(6);
    public static final long DEFAULT_MAX_EXPIRY_IN_MILLIS = TimeUnit.SECONDS.toMillis(86400);
    private final long a;
    private final long b;
    private final long c;
    private final ScheduledExecutorService d;

    public ExponentialBackOffSchedulingStrategy(CacheConfig cacheConfig) {
        this(cacheConfig, 10, DEFAULT_INITIAL_EXPIRY_IN_MILLIS, DEFAULT_MAX_EXPIRY_IN_MILLIS);
    }

    public ExponentialBackOffSchedulingStrategy(CacheConfig cacheConfig, long j, long j2, long j3) {
        this(a(cacheConfig), j, j2, j3);
    }

    private static ScheduledThreadPoolExecutor a(CacheConfig cacheConfig) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(cacheConfig.getAsynchronousWorkersMax());
        scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        return scheduledThreadPoolExecutor;
    }

    ExponentialBackOffSchedulingStrategy(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3) {
        this.d = (ScheduledExecutorService) Args.notNull(scheduledExecutorService, "Executor");
        this.a = Args.notNegative(j, "BackOffRate");
        this.b = Args.notNegative(j2, "InitialExpiryInMillis");
        this.c = Args.notNegative(j3, "MaxExpiryInMillis");
    }

    public void schedule(AsynchronousValidationRequest asynchronousValidationRequest) {
        Args.notNull(asynchronousValidationRequest, "RevalidationRequest");
        this.d.schedule(asynchronousValidationRequest, a(asynchronousValidationRequest.getConsecutiveFailedAttempts()), TimeUnit.MILLISECONDS);
    }

    public void close() {
        this.d.shutdown();
    }

    public long getBackOffRate() {
        return this.a;
    }

    public long getInitialExpiryInMillis() {
        return this.b;
    }

    public long getMaxExpiryInMillis() {
        return this.c;
    }

    protected long a(int i) {
        if (i > 0) {
            return Math.min((long) (((double) this.b) * Math.pow((double) this.a, (double) (i - 1))), this.c);
        }
        return 0;
    }
}
