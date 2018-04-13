package com.meizu.cloud.pushsdk.networking.core;

import android.net.NetworkInfo;
import com.meizu.cloud.pushsdk.networking.common.Priority;
import com.meizu.cloud.pushsdk.networking.internal.InternalRunnable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ANExecutor extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    private static final class AndroidNetworkingFutureTask extends FutureTask<InternalRunnable> implements Comparable<AndroidNetworkingFutureTask> {
        private final InternalRunnable hunter;

        public AndroidNetworkingFutureTask(InternalRunnable internalRunnable) {
            super(internalRunnable, null);
            this.hunter = internalRunnable;
        }

        public int compareTo(AndroidNetworkingFutureTask androidNetworkingFutureTask) {
            Priority priority = this.hunter.getPriority();
            Priority priority2 = androidNetworkingFutureTask.hunter.getPriority();
            return priority == priority2 ? this.hunter.sequence - androidNetworkingFutureTask.hunter.sequence : priority2.ordinal() - priority.ordinal();
        }
    }

    ANExecutor(int i, ThreadFactory threadFactory) {
        super(i, i, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), threadFactory);
    }

    void adjustThreadCount(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            setThreadCount(3);
            return;
        }
        switch (networkInfo.getType()) {
            case 0:
                switch (networkInfo.getSubtype()) {
                    case 1:
                    case 2:
                        setThreadCount(1);
                        return;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 12:
                        setThreadCount(2);
                        return;
                    case 13:
                    case 14:
                    case 15:
                        setThreadCount(3);
                        return;
                    default:
                        setThreadCount(3);
                        return;
                }
            case 1:
            case 6:
            case 9:
                setThreadCount(4);
                return;
            default:
                setThreadCount(3);
                return;
        }
    }

    private void setThreadCount(int i) {
        setCorePoolSize(i);
        setMaximumPoolSize(i);
    }

    public Future<?> submit(Runnable runnable) {
        Object androidNetworkingFutureTask = new AndroidNetworkingFutureTask((InternalRunnable) runnable);
        execute(androidNetworkingFutureTask);
        return androidNetworkingFutureTask;
    }
}
