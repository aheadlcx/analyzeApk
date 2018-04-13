package cn.xiaochuankeji.tieba.ui.my.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.a.e.a;
import cn.xiaochuankeji.tieba.background.modules.a.f;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;

public class SetPasswordActivity extends h implements OnClickListener, a, f.a {
    private Button d;
    private EditText e;
    private String f;
    private String g;
    private String h;
    private NavigationBar i;
    private int j;
    private int k;

    public static void a(Activity activity, String str, String str2, int i, int i2) {
        Intent intent = new Intent(activity, SetPasswordActivity.class);
        intent.putExtra("keyPhone", str);
        intent.putExtra("keyVeritifyCode", str2);
        intent.putExtra("requestType", i2);
        intent.putExtra("region_code", i);
        activity.startActivityForResult(intent, i2);
    }

    protected int a() {
        return R.layout.activity_ac_set_password;
    }

    protected boolean a(Bundle bundle) {
        this.h = getIntent().getExtras().getString("keyPhone");
        this.g = getIntent().getExtras().getString("keyVeritifyCode");
        this.j = getIntent().getExtras().getInt("requestType", 0);
        this.k = getIntent().getExtras().getInt("region_code", -1);
        return true;
    }

    protected void c() {
        this.d = (Button) findViewById(R.id.bnNext);
        this.e = (EditText) findViewById(R.id.etPassWord);
        this.i = (NavigationBar) findViewById(R.id.navBar);
        if (this.j == 113) {
            this.i.setTitle("设置绑定密码");
        } else if (this.j == 101) {
            this.i.setTitle("设置新密码");
        }
    }

    protected void i_() {
        this.e.setInputType(129);
    }

    protected void j_() {
        this.d.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnNext:
                String trim = this.e.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    g.b("请输入密码");
                    return;
                } else if (d.a(trim)) {
                    cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
                    if (this.j == 113) {
                        k();
                        return;
                    } else {
                        j();
                        return;
                    }
                } else {
                    g.a("密码格式错误");
                    this.e.performClick();
                    return;
                }
            default:
                return;
        }
    }

    private void j() {
        v();
        cn.xiaochuankeji.tieba.background.a.h().a(this.h, this.g, this.f, this.k, (f.a) this);
    }

    private void k() {
        v();
        cn.xiaochuankeji.tieba.background.a.h().a(this.h, this.g, this.f, this.k, (a) this);
    }

    private String v() {
        this.f = this.e.getText().toString();
        return this.f.trim();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            setResult(-1);
            finish();
        }
    }

    public void b(boolean z, String str) {
        if (z) {
            cn.htjyb.ui.widget.a.a((Context) this, (CharSequence) "密码设置成功", 0).show();
            setResult(-1);
            finish();
            return;
        }
        cn.htjyb.ui.widget.a.a((Context) this, (CharSequence) "密码设置错误", 0).show();
    }

    public void a(boolean z, String str) {
        if (z) {
            g.b("绑定成功，以后可用该手机号登录");
            setResult(-1);
            finish();
            return;
        }
        g.b(str);
    }
}
