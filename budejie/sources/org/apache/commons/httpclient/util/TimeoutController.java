package org.apache.commons.httpclient.util;

public final class TimeoutController {

    public static class TimeoutException extends Exception {
    }

    private TimeoutController() {
    }

    public static void execute(Thread thread, long j) throws TimeoutException {
        thread.start();
        try {
            thread.join(j);
        } catch (InterruptedException e) {
        }
        if (thread.isAlive()) {
            thread.interrupt();
            throw new TimeoutException();
        }
    }

    public static void execute(Runnable runnable, long j) throws TimeoutException {
        Thread thread = new Thread(runnable, "Timeout guard");
        thread.setDaemon(true);
        execute(thread, j);
    }
}
