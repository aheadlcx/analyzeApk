package net.tsz.afinal.a;

public abstract class a<T> {
    private boolean progress = true;
    private int rate = 1000;

    public boolean isProgress() {
        return this.progress;
    }

    public int getRate() {
        return this.rate;
    }

    public a<T> progress(boolean z, int i) {
        this.progress = z;
        this.rate = i;
        return this;
    }

    public void onStart() {
    }

    public void onLoading(long j, long j2) {
    }

    public void onSuccess(T t) {
    }

    public void onFailure(Throwable th, int i, String str) {
    }
}
