package qsbk.app.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.ToastUtil;

public class RemixConfigActivity extends BaseActivity implements OnClickListener {
    private TextView a;
    private EditText b;
    private Button c;
    private Button d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    protected int getLayoutId() {
        return R.layout.live_activity_remix_config;
    }

    protected void initView() {
        setUp();
        setTitle("糗百直播 配置");
        this.a = (TextView) findViewById(R.id.all_host);
        this.b = (EditText) findViewById(R.id.et_all_host);
        this.c = (Button) findViewById(R.id.btn_save);
        this.c.setOnClickListener(new aax(this));
        this.d = (Button) findViewById(R.id.btn_change);
        this.d.setOnClickListener(new aay(this));
        findViewById(R.id.btn_change_livetest2).setOnClickListener(this);
        findViewById(R.id.btn_change_livetest3).setOnClickListener(this);
    }

    private boolean a() {
        String liveDomain = Constants.getLiveDomain();
        int length = liveDomain.length();
        for (int i = 0; i < length; i++) {
            char charAt = liveDomain.charAt(i);
            if ('0' <= charAt && charAt <= '9') {
                return true;
            }
        }
        return false;
    }

    private void b() {
        Object obj;
        if (a()) {
            obj = "https://live.qiushibaike.com";
            this.b.setText(obj);
            Constants.setLiveDomain(obj);
            ToastUtil.Short("切到正式环境");
        } else {
            obj = "http://203.195.191.194:10003";
            this.b.setText(obj);
            Constants.setLiveDomain(obj);
            ToastUtil.Short("切到测试环境");
        }
        d();
    }

    private void c() {
        Object trim = this.b.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            Constants.setLiveDomain(trim);
        }
        d();
        ToastUtil.Short("保存成功");
    }

    protected void initData() {
        d();
    }

    private void d() {
        this.a.setText(Constants.getLiveDomain());
        this.b.setText(Constants.getLiveDomain());
    }

    public void onClick(View view) {
        Object obj;
        switch (view.getId()) {
            case R.id.btn_change_livetest2:
                obj = "http://203.195.191.194:10005";
                this.b.setText(obj);
                Constants.setLiveDomain(obj);
                ToastUtil.Short("切到测试环境2");
                d();
                return;
            case R.id.btn_change_livetest3:
                obj = "http://203.195.191.194:10006";
                this.b.setText(obj);
                Constants.setLiveDomain(obj);
                ToastUtil.Short("切到测试环境3");
                d();
                return;
            default:
                return;
        }
    }
}
