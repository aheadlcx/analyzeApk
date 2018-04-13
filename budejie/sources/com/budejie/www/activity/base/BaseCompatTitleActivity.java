package com.budejie.www.activity.base;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.OauthWeiboCompatActivity;
import com.budejie.www.h.c;
import com.budejie.www.util.ai;
import com.budejie.www.widget.NavigationBar;

public abstract class BaseCompatTitleActivity extends OauthWeiboCompatActivity {
    protected NavigationBar f;
    private Dialog g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(c.a().a(ai.a(this)));
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                this.g = new Dialog(this, R.style.dialogTheme);
                this.g.setContentView(R.layout.loaddialog);
                this.g.setCanceledOnTouchOutside(true);
                this.g.setCancelable(true);
                return this.g;
            default:
                return super.onCreateDialog(i);
        }
    }

    public void setTitle(int i) {
        super.setTitle(i);
        setTitle(getResources().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_title, null);
        textView.setText(charSequence);
        textView.setTextSize(19.0f);
        if (this.f != null) {
            this.f.setMiddleView(textView);
        }
    }

    public void a() {
        if (this.g != null && this.g.isShowing()) {
            dismissDialog(1);
        }
    }

    public void b() {
        try {
            showDialog(1);
        } catch (RuntimeException e) {
        }
    }
}
