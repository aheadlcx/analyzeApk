package qsbk.app.activity;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.activity.base.ResultFragmentActivity;

public class UserRevokeActivity extends ResultFragmentActivity {
    protected RelativeLayout a;
    protected RelativeLayout b;
    EditText c;
    String d;
    String e = "http://m2.qiushibaike.com/appeal";
    Intent f;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return null;
    }

    protected int a() {
        return R.layout.actiivity_user_revoke;
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        if (VERSION.SDK_INT < 11) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.hide();
            }
        }
        this.f = getIntent();
        this.d = this.f.getStringExtra("articleId");
    }

    protected void a(Bundle bundle) {
        g();
        i();
    }

    private void i() {
        this.c = (EditText) findViewById(R.id.revoke_content);
        this.c.setHint(R.string.revoke_hint);
        this.a.setOnClickListener(new aef(this));
        this.b.setOnClickListener(new aeg(this));
        this.c.setFocusable(true);
        this.c.requestFocus();
    }

    protected void g() {
        this.a = (RelativeLayout) findViewById(R.id.cancel);
        this.b = (RelativeLayout) findViewById(R.id.sure);
        try {
            ((TextView) findViewById(R.id.id_sure_txt)).setText("申诉");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
