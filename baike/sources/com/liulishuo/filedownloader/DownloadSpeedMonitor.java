package com.liulishuo.filedownloader;

import android.os.SystemClock;
import com.liulishuo.filedownloader.IDownloadSpeed.Lookup;
import com.liulishuo.filedownloader.IDownloadSpeed.Monitor;

public class DownloadSpeedMonitor implements Lookup, Monitor {
    private long a;
    private long b;
    private long c;
    private long d;
    private int e;
    private long f;
    private int g = 5;

    public void start() {
        this.d = SystemClock.uptimeMillis();
        this.c = this.f;
    }

    public void end(long j) {
        if (this.d > 0 && this.c > 0) {
            long j2 = j - this.c;
            this.a = 0;
            long uptimeMillis = SystemClock.uptimeMillis() - this.d;
            if (uptimeMillis < 0) {
                this.e = (int) j2;
            } else {
                this.e = (int) (j2 / uptimeMillis);
            }
        }
    }

    public void update(long j) {
        int i = 1;
        if (this.g > 0) {
            if (this.a != 0) {
                long uptimeMillis = SystemClock.uptimeMillis() - this.a;
                if (uptimeMillis >= ((long) this.g) || (this.e == 0 && uptimeMillis > 0)) {
                    this.e = (int) ((j - this.b) / uptimeMillis);
                    this.e = Math.max(0, this.e);
                } else {
                    i = 0;
                }
            }
            if (i != 0) {
                this.b = j;
                this.a = SystemClock.uptimeMillis();
            }
        }
    }

    public void reset() {
        this.e = 0;
        this.a = 0;
    }

    public int getSpeed() {
        return this.e;
    }

    public void setMinIntervalUpdateSpeed(int i) {
        this.g = i;
    }
}
