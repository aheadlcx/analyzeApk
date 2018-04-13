package com.sprite.ads.nati.reporter;

public abstract class ConfirmDownloadReporter implements Reporter {
    protected int downwarn;

    public void setDownwarn(int i) {
        this.downwarn = i;
    }
}
