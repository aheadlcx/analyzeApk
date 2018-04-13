package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.Util;

public class ModifyPwdActivity extends BaseActionBarActivity implements OnClickListener {
    EditText a;
    EditText b;
    EditText c;
    Button d;
    TextView e;
    ProgressDialog f;
    String g;
    String h;
    String i;
    EncryptHttpTask j;
    TextWatcher k = new ue(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ModifyPwdActivity.class));
    }

    private void g() {
        this.g = this.a.getText().toString().trim();
        this.h = this.b.getText().toString().trim();
        this.i = this.c.getText().toString().trim();
    }

    private boolean i() {
        g();
        if (!Util.isValidPwd(this.h)) {
            ToastAndDialog.makeNegativeToast(this, "密码应为6-16位的数字和字母的组合").show();
            return false;
        } else if (TextUtils.equals(this.h, this.g)) {
            ToastAndDialog.makeNegativeToast(this, "新旧密码不能相同，请重新输入").show();
            return false;
        } else if (TextUtils.equals(this.h, this.i)) {
            return true;
        } else {
            ToastAndDialog.makeNegativeToast(this, "新密码输入不一致，请重新输入").show();
            return false;
        }
    }

    protected String f() {
        return getString(R.string.modify_pwd);
    }

    protected int a() {
        return R.layout.activity_modify_pwd;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        if (QsbkApp.currentUser == null) {
            finish();
            return;
        }
        this.a = (EditText) findViewById(R.id.old_pwd);
        this.b = (EditText) findViewById(R.id.new_pwd);
        this.c = (EditText) findViewById(R.id.repeat_pwd);
        this.d = (Button) findViewById(R.id.submit);
        this.e = (TextView) findViewById(R.id.forget_pwd);
        this.a.addTextChangedListener(this.k);
        this.b.addTextChangedListener(this.k);
        this.c.addTextChangedListener(this.k);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    private void j() {
        if (i()) {
            String str = Constants.MODIFY_PWD;
            Map hashMap = new HashMap();
            hashMap.put("old_password", this.g);
            hashMap.put("new_password", this.h);
            this.j = new EncryptHttpTask(str, str, new uf(this));
            this.j.setMapParams(hashMap);
            this.j.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            a(false);
        }
    }

    private void a(boolean z) {
        if (this.f == null) {
            this.f = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.f.setCancelable(z);
        this.f.show();
    }

    private void k() {
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.removeTextChangedListener(this.k);
        }
        if (this.a != null) {
            this.a.removeTextChangedListener(this.k);
        }
        if (this.c != null) {
            this.c.removeTextChangedListener(this.k);
        }
        if (this.j != null) {
            this.j.cancel(true);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                j();
                return;
            case R.id.forget_pwd:
                if (QsbkApp.currentUser.isBindSocail() || QsbkApp.currentUser.hasPhone() || QsbkApp.currentUser.isBindAndVerifyEmail()) {
                    CharSequence[] charSequenceArr = new CharSequence[]{"邮箱找回密码", "手机找回密码"};
                    Builder builder = new Builder(this);
                    builder.setItems(charSequenceArr, new ug(this));
                    builder.setTitle("遇到问题？请加QQ群：274070027");
                    builder.setNegativeButton("取消", new uh(this));
                    AlertDialog create = builder.create();
                    create.setCanceledOnTouchOutside(true);
                    create.show();
                    return;
                }
                ToastAndDialog.makeNegativeToast(this, "请加群：274070027 联系客服，找回密码").show();
                return;
            default:
                return;
        }
    }
}
