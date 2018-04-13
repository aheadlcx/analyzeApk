package qsbk.app.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.im.ChatClientManager;

public class SwitchTestDomainActivity extends BaseActionBarActivity {
    private EditText a;
    private EditText b;
    private EditText c;
    private EditText d;
    private EditText e;
    private EditText f;
    private CheckBox g;
    private EditText h;
    private EditText i;
    private EditText j;
    private EditText k;
    private EditText l;
    private View m;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    private void g() {
        this.a = (EditText) findViewById(R.id.content);
        this.a.setText(Constants.CONTENT_DOMAINS);
        this.b = (EditText) findViewById(R.id.push);
        this.b.setText(Constants.PUSH_DOMAINS);
        this.c = (EditText) findViewById(R.id.nearby);
        this.c.setText(Constants.NEARBY);
        this.d = (EditText) findViewById(R.id.insp);
        this.d.setText(Constants.INSP);
        this.e = (EditText) findViewById(R.id.picture);
        this.e.setText(Constants.IMG_DOMAINS);
        this.f = (EditText) findViewById(R.id.vote);
        this.f.setText(Constants.VOTE_DOMAINS);
        this.h = (EditText) findViewById(R.id.relationship);
        this.h.setText(Constants.RELATIONSHIP);
        this.i = (EditText) findViewById(R.id.tribe);
        this.i.setText(Constants.TRIBE_DOMAINS);
        this.j = (EditText) findViewById(R.id.im_server);
        this.j.setText(ChatClientManager.testConnectHost);
        this.k = (EditText) findViewById(R.id.im_keepalive_interval);
        this.k.setText(String.valueOf(ChatClientManager.KEEP_ALIVE_INTERVEL));
        this.g = (CheckBox) findViewById(R.id.test_mode);
        this.g.setChecked(Constants.isTestMode);
        this.l = (EditText) findViewById(R.id.qiuyou_circle);
        this.l.setText(Constants.CIRCLE_SERVER);
        findViewById(R.id.save).setOnClickListener(new adf(this));
        this.m = findViewById(R.id.live_test_mode);
        this.m.setOnClickListener(new adg(this));
        findViewById(R.id.live_test_mode_qb).setOnClickListener(new adh(this));
    }

    private void i() {
        if (!TextUtils.isEmpty(this.a.getText().toString().trim())) {
            Constants.CONTENT_DOMAINS = this.a.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.b.getText().toString().trim())) {
            Constants.PUSH_DOMAINS = this.b.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.c.getText().toString().trim())) {
            Constants.NEARBY = this.c.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.d.getText().toString().trim())) {
            Constants.INSP = this.d.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.e.getText().toString().trim())) {
            Constants.IMG_DOMAINS = this.e.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.f.getText().toString().trim())) {
            Constants.VOTE_DOMAINS = this.f.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.h.getText().toString().trim())) {
            Constants.RELATIONSHIP = this.h.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.i.getText().toString().trim())) {
            Constants.TRIBE_DOMAINS = this.i.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.l.getText().toString().trim())) {
            Constants.CIRCLE_SERVER = this.l.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.j.getText())) {
            ChatClientManager.testConnectHost = this.j.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(this.k.getText())) {
            try {
                int parseInt = Integer.parseInt(this.k.getText().toString());
                if (parseInt > 0) {
                    ChatClientManager.KEEP_ALIVE_INTERVEL = parseInt;
                }
            } catch (Exception e) {
            }
        }
        Constants.isTestMode = this.g.isChecked();
    }

    protected String f() {
        return "测试服务器配置";
    }

    protected int a() {
        return R.layout.activity_switch_domain;
    }

    protected void a(Bundle bundle) {
        g();
    }
}
