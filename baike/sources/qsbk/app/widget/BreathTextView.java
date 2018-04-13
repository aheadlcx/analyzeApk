package qsbk.app.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class BreathTextView extends TextView {
    public static final int MAX_ALPHA = 255;
    public static final int MIN_ALPHA = 125;
    private a a;

    private class a implements Runnable {
        final /* synthetic */ BreathTextView a;
        private Drawable b;
        private int c = 2;
        private int d = 125;

        public a(BreathTextView breathTextView, Drawable drawable) {
            this.a = breathTextView;
            this.b = drawable;
        }

        void a(long j) {
            a();
            this.a.postDelayed(this, j);
        }

        void a() {
            this.a.removeCallbacks(this);
        }

        public void run() {
            if (this.d <= 125) {
                this.c = 2;
            } else if (this.d >= 255) {
                this.c = -2;
            }
            this.d += this.c;
            this.b.setAlpha(this.d);
            this.a.postDelayed(this, 10);
        }
    }

    public BreathTextView(Context context) {
        super(context);
    }

    public BreathTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BreathTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void startBreath(long j) {
        Drawable background = getBackground();
        if (background == null) {
            Log.e(BreathTextView.class.getSimpleName(), "Background not set.");
            return;
        }
        if (this.a == null) {
            this.a = new a(this, background);
        }
        this.a.a(j);
    }

    public void stopBreath() {
        if (this.a != null) {
            this.a.a();
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.a);
        this.a = null;
        super.onDetachedFromWindow();
    }
}
