package cn.v6.sixrooms.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class ImprovedProgressDialog extends AlertDialog {
    private String a;
    private TextView b;

    public ImprovedProgressDialog(Context context, String str) {
        this(context, str, R.style.ImprovedProgressDialog);
    }

    public ImprovedProgressDialog(Context context, String str, int i) {
        super(context, i);
        this.a = str;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phone_custom_progressbar);
        this.b = (TextView) findViewById(R.id.tv_loadingHint);
        this.b.setText(this.a);
        getWindow().getAttributes().gravity = 17;
        setCancelable(false);
    }

    public void onBackPressed() {
        if (isShowing()) {
            dismiss();
        } else {
            super.onBackPressed();
        }
    }

    public void changeMessage(String str) {
        this.b.setText(str);
    }
}
