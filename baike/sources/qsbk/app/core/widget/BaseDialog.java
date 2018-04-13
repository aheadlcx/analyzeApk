package qsbk.app.core.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import qsbk.app.core.R;

public abstract class BaseDialog extends Dialog {
    protected Context a;
    protected Handler b;

    public interface OnItemClickListener {
        void click();
    }

    protected abstract int c();

    protected abstract void d();

    protected abstract void e();

    public BaseDialog(Context context) {
        this(context, R.style.SimpleDialog);
    }

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.a = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.a);
    }

    protected void a(Context context) {
        this.b = new Handler();
        setContentView(getLayoutInflater().inflate(c(), null));
        Window window = getWindow();
        window.setGravity(a());
        d();
        e();
        if (b() == 0.0f) {
            window.setLayout(-2, -2);
        } else {
            WindowManager windowManager = window.getWindowManager();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((float) displayMetrics.widthPixels) * b());
            attributes.dimAmount = f();
            Drawable h = h();
            if (h != null) {
                window.setBackgroundDrawable(h);
            }
            window.addFlags(2);
            window.setAttributes(attributes);
        }
        setCanceledOnTouchOutside(g());
    }

    protected int a() {
        return 80;
    }

    protected float b() {
        return 1.0f;
    }

    protected <T extends View> T a(int i) {
        return findViewById(i);
    }

    protected float f() {
        return 0.0f;
    }

    protected boolean g() {
        return true;
    }

    protected Drawable h() {
        return null;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 24:
                if (this.a instanceof Activity) {
                    ((Activity) this.a).onKeyDown(i, keyEvent);
                }
                return true;
            case 25:
                if (this.a instanceof Activity) {
                    ((Activity) this.a).onKeyDown(i, keyEvent);
                }
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    protected void i() {
        a(new a(this));
    }

    protected void a(OnClickListener onClickListener) {
        View findViewById = findViewById(R.id.iv_up);
        if (findViewById != null) {
            findViewById.setOnClickListener(onClickListener);
        }
    }

    public void dismiss() {
        super.dismiss();
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
            this.b = null;
        }
    }

    protected void a(Runnable runnable) {
        a(runnable, 0);
    }

    protected void a(Runnable runnable, long j) {
        if (this.b != null) {
            this.b.removeCallbacks(runnable);
            this.b.postDelayed(runnable, j);
        }
    }
}
