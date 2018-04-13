package qsbk.app.utils;

import android.widget.TextView;

public class AnimateDotRunnable implements Runnable {
    public static final int DEFAULT_MAX_DOT_COUNT = 3;
    public static final long DEFAULT_PERIOD = 100;
    public static final int MIN_DOT_COUNT = 2;
    private TextView a;
    private long b = 100;
    private int c = 3;
    private String d;
    private int e = 0;

    public AnimateDotRunnable(TextView textView, int i, long j) {
        if (textView == null || i < 2 || j <= 0) {
            throw new IllegalArgumentException(String.format("Not valid args %s, %s, %s", new Object[]{textView, Integer.valueOf(i), Long.valueOf(j)}));
        }
        this.a = textView;
        this.c = i;
        this.b = j;
    }

    public void setBaseText(String str) {
        this.d = str;
    }

    public void start() {
        stop();
        this.a.postDelayed(this, this.b);
    }

    public void stop() {
        this.a.removeCallbacks(this);
    }

    public void destroy() {
        this.a = null;
        this.d = null;
    }

    public void run() {
        this.e = (this.e % this.c) + 1;
        CharSequence stringBuffer = new StringBuffer(this.d);
        for (int i = 0; i < this.e; i++) {
            stringBuffer.append(".");
        }
        this.a.setText(stringBuffer);
        start();
    }
}
