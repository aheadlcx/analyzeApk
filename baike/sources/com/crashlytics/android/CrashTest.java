package com.crashlytics.android;

import com.crashlytics.android.internal.v;

public class CrashTest {
    public void throwRuntimeException(String str) {
        throw new RuntimeException(str);
    }

    public void stackOverflow() {
        stackOverflow();
    }

    public void indexOutOfBounds() {
        v.a().b().a(Crashlytics.TAG, "Out of bounds value: " + new int[2][10]);
    }

    public void crashAsyncTask(long j) {
        new ao(this, j).execute(new Void[]{null});
    }

    public void throwFiveChainedExceptions() {
        try {
            throw new RuntimeException("1");
        } catch (Throwable e) {
            throw new RuntimeException("2", e);
        } catch (Throwable e2) {
            try {
                throw new RuntimeException("3", e2);
            } catch (Throwable e22) {
                try {
                    throw new RuntimeException("4", e22);
                } catch (Throwable e222) {
                    throw new RuntimeException("5", e222);
                }
            }
        }
    }
}
