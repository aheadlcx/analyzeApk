package io.agora.rtc.video;

import android.os.Handler;
import android.os.SystemClock;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    public interface BlockingOperation {
        void run() throws InterruptedException;
    }

    /* renamed from: io.agora.rtc.video.ThreadUtils$1 */
    final class AnonymousClass1 implements BlockingOperation {
        final /* synthetic */ Thread val$thread;

        AnonymousClass1(Thread thread) {
            this.val$thread = thread;
        }

        public void run() throws InterruptedException {
            this.val$thread.join();
        }
    }

    /* renamed from: io.agora.rtc.video.ThreadUtils$2 */
    final class AnonymousClass2 implements BlockingOperation {
        final /* synthetic */ CountDownLatch val$latch;

        AnonymousClass2(CountDownLatch countDownLatch) {
            this.val$latch = countDownLatch;
        }

        public void run() throws InterruptedException {
            this.val$latch.await();
        }
    }

    /* renamed from: io.agora.rtc.video.ThreadUtils$3 */
    final class AnonymousClass3 implements Runnable {
        final /* synthetic */ CountDownLatch val$barrier;
        final /* synthetic */ Callable val$callable;
        final /* synthetic */ AnonymousClass1Result val$result;

        AnonymousClass3(AnonymousClass1Result anonymousClass1Result, Callable callable, CountDownLatch countDownLatch) {
            this.val$result = anonymousClass1Result;
            this.val$callable = callable;
            this.val$barrier = countDownLatch;
        }

        public void run() {
            try {
                this.val$result.value = this.val$callable.call();
                this.val$barrier.countDown();
            } catch (Exception e) {
                RuntimeException runtimeException = new RuntimeException("Callable threw exception: " + e);
                runtimeException.setStackTrace(e.getStackTrace());
                throw runtimeException;
            }
        }
    }

    /* renamed from: io.agora.rtc.video.ThreadUtils$4 */
    final class AnonymousClass4 implements Runnable {
        final /* synthetic */ CountDownLatch val$barrier;
        final /* synthetic */ Runnable val$runner;

        AnonymousClass4(Runnable runnable, CountDownLatch countDownLatch) {
            this.val$runner = runnable;
            this.val$barrier = countDownLatch;
        }

        public void run() {
            this.val$runner.run();
            this.val$barrier.countDown();
        }
    }

    public static class ThreadChecker {
        private Thread thread = Thread.currentThread();

        public void checkIsOnValidThread() {
            if (this.thread == null) {
                this.thread = Thread.currentThread();
            }
            if (Thread.currentThread() != this.thread) {
                throw new IllegalStateException("Wrong thread");
            }
        }

        public void detachThread() {
            this.thread = null;
        }
    }

    public static void executeUninterruptibly(BlockingOperation blockingOperation) {
        Object obj = null;
        while (true) {
            try {
                blockingOperation.run();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            }
        }
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean joinUninterruptibly(Thread thread, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = false;
        long j2 = j;
        while (j2 > 0) {
            try {
                thread.join(j2);
                break;
            } catch (InterruptedException e) {
                j2 = j - (SystemClock.elapsedRealtime() - elapsedRealtime);
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        if (thread.isAlive()) {
            return false;
        }
        return true;
    }

    public static void joinUninterruptibly(Thread thread) {
        executeUninterruptibly(new AnonymousClass1(thread));
    }

    public static void awaitUninterruptibly(CountDownLatch countDownLatch) {
        executeUninterruptibly(new AnonymousClass2(countDownLatch));
    }

    public static boolean awaitUninterruptibly(CountDownLatch countDownLatch, long j) {
        boolean z = false;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = null;
        long j2 = j;
        do {
            try {
                z = countDownLatch.await(j2, TimeUnit.MILLISECONDS);
                break;
            } catch (InterruptedException e) {
                obj = 1;
                j2 = j - (SystemClock.elapsedRealtime() - elapsedRealtime);
                if (j2 <= 0) {
                }
            }
        } while (j2 <= 0);
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
        return z;
    }

    public static <V> V invokeUninterruptibly(Handler handler, Callable<V> callable) {
        AnonymousClass1Result anonymousClass1Result = new Object() {
            public V value;
        };
        CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new AnonymousClass3(anonymousClass1Result, callable, countDownLatch));
        awaitUninterruptibly(countDownLatch);
        return anonymousClass1Result.value;
    }

    public static void invokeUninterruptibly(Handler handler, Runnable runnable) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new AnonymousClass4(runnable, countDownLatch));
        awaitUninterruptibly(countDownLatch);
    }
}
