package com.ali.auth.third.core.service.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.ali.auth.third.core.service.MemberExecutorService;
import com.ali.auth.third.core.trace.SDKLogger;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public final class ExecutorServiceImpl implements MemberExecutorService {
    private final Handler handler;
    private final HandlerThread handlerThread = new HandlerThread("SDK Looper Thread");
    private ThreadPoolExecutor mExecutor;
    private final BlockingQueue<Runnable> mPoolWorkQueue = new LinkedBlockingQueue(128);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public static class CoordinatorRejectHandler implements RejectedExecutionHandler {
        private BlockingQueue<Runnable> mPoolWorkQueue;

        public CoordinatorRejectHandler(BlockingQueue<Runnable> blockingQueue) {
            this.mPoolWorkQueue = blockingQueue;
        }

        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Object[] toArray = this.mPoolWorkQueue.toArray();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            for (Object obj : toArray) {
                if (obj.getClass().isAnonymousClass()) {
                    stringBuilder.append(getOuterClass(obj));
                    stringBuilder.append(',').append(' ');
                } else {
                    stringBuilder.append(obj.getClass());
                    stringBuilder.append(',').append(' ');
                }
            }
            stringBuilder.append(']');
            throw new RejectedExecutionException("Task " + runnable.toString() + " rejected from " + threadPoolExecutor.toString() + " in " + stringBuilder.toString());
        }

        private Object getOuterClass(Object obj) {
            try {
                Field declaredField = obj.getClass().getDeclaredField("this$0");
                declaredField.setAccessible(true);
                obj = declaredField.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public ExecutorServiceImpl() {
        this.handlerThread.start();
        synchronized (this.handlerThread) {
            while (this.handlerThread.getLooper() == null) {
                try {
                    this.handlerThread.wait();
                } catch (InterruptedException e) {
                }
            }
        }
        this.handler = new Handler(this.handlerThread.getLooper());
        this.mExecutor = new ThreadPoolExecutor(8, 16, (long) 1, TimeUnit.SECONDS, this.mPoolWorkQueue, new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "ExecutorTask #" + this.mCount.getAndIncrement());
            }
        }, new CoordinatorRejectHandler(this.mPoolWorkQueue));
    }

    public void postHandlerTask(Runnable runnable) {
        this.handler.post(runnable);
    }

    public void postUITask(final Runnable runnable) {
        this.mainHandler.post(new Runnable() {
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    SDKLogger.e("kernel", th.getMessage(), th);
                }
            }
        });
    }

    public void postTask(Runnable runnable) {
        this.mExecutor.execute(runnable);
    }

    public Looper getDefaultLooper() {
        return this.handlerThread.getLooper();
    }

    public void execute(Runnable runnable) {
        this.mExecutor.execute(runnable);
    }

    public void shutdown() {
        this.mExecutor.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.mExecutor.shutdownNow();
    }

    public boolean isShutdown() {
        return this.mExecutor.isShutdown();
    }

    public boolean isTerminated() {
        return this.mExecutor.isTerminated();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.mExecutor.awaitTermination(j, timeUnit);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return this.mExecutor.submit(callable);
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.mExecutor.submit(runnable, t);
    }

    public Future<?> submit(Runnable runnable) {
        return this.mExecutor.submit(runnable);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.mExecutor.invokeAll(collection);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.mExecutor.invokeAll(collection, j, timeUnit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return this.mExecutor.invokeAny(collection);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mExecutor.invokeAny(collection, j, timeUnit);
    }
}
