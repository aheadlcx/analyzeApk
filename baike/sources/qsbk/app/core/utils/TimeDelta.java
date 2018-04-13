package qsbk.app.core.utils;

public class TimeDelta {
    public long start = System.currentTimeMillis();

    public long getDelta() {
        return System.currentTimeMillis() - this.start;
    }

    public void renew() {
        this.start = System.currentTimeMillis();
    }
}
