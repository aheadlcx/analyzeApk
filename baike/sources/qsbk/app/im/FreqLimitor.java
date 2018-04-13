package qsbk.app.im;

public class FreqLimitor {
    private long a = 0;
    private long b = 1000;

    public FreqLimitor(long j) {
        this.b = j;
    }

    public boolean allow() {
        return System.currentTimeMillis() - this.a >= this.b;
    }

    public void renew() {
        this.a = System.currentTimeMillis();
    }
}
