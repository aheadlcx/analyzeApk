package qsbk.app.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class CaptchaButton extends AppCompatButton {
    CountDownTimer a;
    OnTickListener b;
    boolean c;

    public interface OnTickListener {
        void onFinish(CaptchaButton captchaButton);

        void onTick(CaptchaButton captchaButton, long j);
    }

    public CaptchaButton(Context context) {
        super(context);
    }

    public CaptchaButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CaptchaButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void startCountDown(long j, long j2) {
        a();
        this.a = new an(this, j, j2);
        this.a.start();
        this.c = true;
    }

    public void stopCountDown() {
        this.c = false;
        a();
        if (this.b != null) {
            this.b.onFinish(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    private void a() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
    }

    public boolean isOnTick() {
        return this.c;
    }

    public void setOnTickListener(OnTickListener onTickListener) {
        this.b = onTickListener;
    }
}
