package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.activity.base.ResultFragmentActivity;

public class GroupIntroduceActivity extends ResultFragmentActivity {
    protected RelativeLayout a;
    protected RelativeLayout b;
    EditText c;
    int d;
    Intent e;
    String f;
    String g;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected String e() {
        return null;
    }

    protected int a() {
        return R.layout.activity_group_introduce;
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        this.e = getIntent();
        this.d = this.e.getIntExtra(ImageViewer.KEY_GROUP_ID, 0);
        this.f = this.e.getStringExtra("groupIntro");
        this.g = this.e.getStringExtra("groupName");
        this.c.setText(this.f);
    }

    protected void a(Bundle bundle) {
        f();
        g();
    }

    private void g() {
        this.c = (EditText) findViewById(R.id.group_introduce_info);
        this.a.setOnClickListener(new mw(this));
        this.b.setOnClickListener(new mx(this));
        this.c.setFocusable(true);
        this.c.requestFocus();
    }

    protected void f() {
        this.a = (RelativeLayout) findViewById(R.id.cancel);
        this.b = (RelativeLayout) findViewById(R.id.sure);
        try {
            ((TextView) findViewById(R.id.id_sure_txt)).setText("保存");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
