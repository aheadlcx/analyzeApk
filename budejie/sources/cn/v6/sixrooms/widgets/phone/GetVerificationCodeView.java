package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class GetVerificationCodeView extends FrameLayout {
    private Context a;
    private boolean b = true;
    private TextView c;
    private TextView d;
    private CountDownTimer e;
    private GetVerificationCodeListener f;
    private GetVerificationCodeView$RunCountdownCallback g = new q(this);

    public interface GetVerificationCodeListener {
        void clickGetVerificationCodeCallback(GetVerificationCodeView$RunCountdownCallback getVerificationCodeView$RunCountdownCallback);
    }

    public void setOnGetVerificationCodeListener(GetVerificationCodeListener getVerificationCodeListener) {
        this.f = getVerificationCodeListener;
    }

    public GetVerificationCodeView(Context context) {
        super(context);
        this.a = context;
        a();
    }

    public GetVerificationCodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        setLayoutParams(new LayoutParams(-2, -2));
        LayoutInflater.from(this.a).inflate(R.layout.custom_get_verification_code_layout, this);
        this.c = (TextView) findViewById(R.id.id_tv_get_verification_code);
        this.d = (TextView) findViewById(R.id.id_tv_countdown);
        c();
        d();
        b();
        this.c.setOnClickListener(new s(this));
    }

    private void b() {
        this.e = new r(this);
    }

    private void c() {
        this.d.setVisibility(8);
    }

    private void d() {
        if (!this.b) {
            this.c.setText("再次获取");
        }
        this.c.setVisibility(0);
    }

    protected void onDetachedFromWindow() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
        super.onDetachedFromWindow();
    }

    public void runCountdown() {
        this.b = false;
        if (this.e == null) {
            b();
        }
        this.c.setVisibility(8);
        this.d.setVisibility(0);
        this.e.start();
    }
}
