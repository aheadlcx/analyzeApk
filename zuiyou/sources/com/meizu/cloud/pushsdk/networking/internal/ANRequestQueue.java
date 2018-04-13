package com.meizu.cloud.pushsdk.networking.internal;

import com.meizu.cloud.pushsdk.networking.common.ANLog;
import com.meizu.cloud.pushsdk.networking.common.ANRequest;
import com.meizu.cloud.pushsdk.networking.common.Priority;
import com.meizu.cloud.pushsdk.networking.core.Core;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ANRequestQueue {
    private static final String TAG = ANRequestQueue.class.getSimpleName();
    private static ANRequestQueue sInstance = null;
    private final Set<ANRequest> mCurrentRequests = new HashSet();
    private AtomicInteger mSequenceGenerator = new AtomicInteger();

    public interface RequestFilter {
        boolean apply(ANRequest aNRequest);
    }

    public static void initialize() {
        getInstance();
    }

    public static ANRequestQueue getInstance() {
        if (sInstance == null) {
            synchronized (ANRequestQueue.class) {
                if (sInstance == null) {
                    sInstance = new ANRequestQueue();
                }
            }
        }
        return sInstance;
    }

    private void cancel(RequestFilter requestFilter, boolean z) {
        synchronized (this.mCurrentRequests) {
            try {
                Iterator it = this.mCurrentRequests.iterator();
                while (it.hasNext()) {
                    ANRequest aNRequest = (ANRequest) it.next();
                    if (requestFilter.apply(aNRequest)) {
                        aNRequest.cancel(z);
                        if (aNRequest.isCanceled()) {
                            aNRequest.destroy();
                            it.remove();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelAll(boolean z) {
        synchronized (this.mCurrentRequests) {
            try {
                Iterator it = this.mCurrentRequests.iterator();
                while (it.hasNext()) {
                    ANRequest aNRequest = (ANRequest) it.next();
                    aNRequest.cancel(z);
                    if (aNRequest.isCanceled()) {
                        aNRequest.destroy();
                        it.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelRequestWithGivenTag(final Object obj, boolean z) {
        if (obj != null) {
            try {
                cancel(new RequestFilter() {
                    public boolean apply(ANRequest aNRequest) {
                        if ((aNRequest.getTag() instanceof String) && (obj instanceof String)) {
                            return ((String) aNRequest.getTag()).equals((String) obj);
                        }
                        return aNRequest.getTag().equals(obj);
                    }
                }, z);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getSequenceNumber() {
        return this.mSequenceGenerator.incrementAndGet();
    }

    public ANRequest addRequest(ANRequest aNRequest) {
        synchronized (this.mCurrentRequests) {
            try {
                this.mCurrentRequests.add(aNRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            aNRequest.setSequenceNumber(getSequenceNumber());
            if (aNRequest.getPriority() == Priority.IMMEDIATE) {
                aNRequest.setFuture(Core.getInstance().getExecutorSupplier().forImmediateNetworkTasks().submit(new InternalRunnable(aNRequest)));
            } else {
                aNRequest.setFuture(Core.getInstance().getExecutorSupplier().forNetworkTasks().submit(new InternalRunnable(aNRequest)));
            }
            ANLog.d("addRequest: after addition - mCurrentRequests size: " + this.mCurrentRequests.size());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return aNRequest;
    }

    public void finish(ANRequest aNRequest) {
        synchronized (this.mCurrentRequests) {
            try {
                this.mCurrentRequests.remove(aNRequest);
                ANLog.d("finish: after removal - mCurrentRequests size: " + this.mCurrentRequests.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
