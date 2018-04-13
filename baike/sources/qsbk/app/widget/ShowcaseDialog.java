package qsbk.app.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import qsbk.app.R;

public class ShowcaseDialog extends AlertDialog {
    private CharSequence a;
    private Window b;
    private int c;
    private int d;
    private int e = 53;
    private int f;

    public static class Builder {
        ShowcaseDialog a;

        public Builder(Context context) {
            this.a = new ShowcaseDialog(context);
        }

        public Builder setShowcaseMessage(CharSequence charSequence) {
            this.a.setShowcaseMessage(charSequence);
            return this;
        }

        public Builder setShowAtXY(int i, int i2) {
            this.a.setXY(i, i2);
            return this;
        }

        public Builder setGravity(int i) {
            this.a.setGravity(i);
            return this;
        }

        public Builder setTextBackGroundResource(int i) {
            this.a.a(i);
            return this;
        }

        public ShowcaseDialog build() {
            return this.a;
        }
    }

    public ShowcaseDialog(Context context, int i) {
        super(context, i);
        a(context);
    }

    public ShowcaseDialog(Context context) {
        super(context, R.style.user_info_dialog);
        a(context);
    }

    private void a(Context context) {
        setCanceledOnTouchOutside(true);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_showcase);
        this.b = getWindow();
        LayoutParams attributes = this.b.getAttributes();
        attributes.x = this.c;
        attributes.y = this.d;
        this.b.setAttributes(attributes);
        this.b.setGravity(this.e);
        TextView textView = (TextView) findViewById(R.id.user_info_textView);
        if (this.f != 0) {
            textView.setBackgroundResource(this.f);
        }
        textView.setText(this.a);
        textView.setOnClickListener(new er(this));
    }

    public void setShowcaseMessage(CharSequence charSequence) {
        this.a = charSequence;
    }

    public void setXY(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public void setGravity(int i) {
        this.e = i;
    }

    private void a(int i) {
        this.f = i;
    }
}
