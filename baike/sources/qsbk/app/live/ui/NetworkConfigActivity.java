package qsbk.app.live.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.r0adkll.slidr.Slidr;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

public class NetworkConfigActivity extends BaseActivity implements OnClickListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private EditText d;
    private EditText e;
    private EditText f;
    private Button g;
    private Button h;
    private Button i;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getSlidrConfig());
    }

    protected void onResume() {
        super.onResume();
        b();
    }

    protected int getLayoutId() {
        return R.layout.activity_network_config;
    }

    protected void initView() {
        setUp();
        setTitle("热猫配置");
        this.a = (TextView) findViewById(R.id.im_host);
        this.b = (TextView) findViewById(R.id.remix_host);
        this.c = (TextView) findViewById(R.id.pay_host);
        this.d = (EditText) findViewById(R.id.et_im_host);
        this.e = (EditText) findViewById(R.id.et_remix_host);
        this.f = (EditText) findViewById(R.id.et_pay_host);
        this.g = (Button) findViewById(R.id.btn_save);
        this.g.setOnClickListener(new fh(this));
        this.h = (Button) findViewById(R.id.btn_change);
        this.h.setOnClickListener(new fi(this));
        this.i = (Button) findViewById(R.id.btn_show_live_info);
        this.i.setOnClickListener(new fj(this));
        findViewById(R.id.btn_change_livetest2).setOnClickListener(this);
        findViewById(R.id.btn_change_livetest3).setOnClickListener(this);
        findViewById(R.id.btn_change_livetest4).setOnClickListener(this);
        findViewById(R.id.btn_change_livetest5).setOnClickListener(this);
        findViewById(R.id.btn_change_livetest6).setOnClickListener(this);
    }

    private void a() {
        boolean z;
        boolean z2 = PreferenceUtils.instance().getBoolean("show_live_info", false);
        ToastUtil.Short(z2 ? "不显示了" : "切为显示");
        PreferenceUtils instance = PreferenceUtils.instance();
        String str = "show_live_info";
        if (z2) {
            z = false;
        } else {
            z = true;
        }
        instance.putBoolean(str, z);
        b();
    }

    private void b() {
        this.i.setText(PreferenceUtils.instance().getBoolean("show_live_info", false) ? "隐藏直播信息" : "显示直播信息");
    }

    private void c() {
        if (UrlConstants.getApiDomain().equals(UrlConstants.API_DOMAIN)) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN);
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS);
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN);
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境");
        } else {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_DOMAIN);
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_DOMAIN_HTTPS);
            UrlConstants.setApiDomain(UrlConstants.API_DOMAIN);
            UrlConstants.setPayDomain(UrlConstants.PAY_DOMAIN);
            ToastUtil.Short("切到正式环境");
        }
        e();
    }

    private void d() {
        Object trim = this.d.getText().toString().trim();
        Object trim2 = this.e.getText().toString().trim();
        Object trim3 = this.f.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            UrlConstants.setLiveDomain(trim);
        }
        if (!TextUtils.isEmpty(trim2)) {
            UrlConstants.setApiDomain(trim2);
        }
        if (!TextUtils.isEmpty(trim3)) {
            UrlConstants.setPayDomain(trim3);
        }
        e();
        ToastUtil.Short("保存成功");
    }

    protected void initData() {
        e();
    }

    private void e() {
        this.a.setText(UrlConstants.getLiveDomain());
        this.b.setText(UrlConstants.getApiDomain());
        this.c.setText(UrlConstants.getPayDomain());
        this.d.setText(UrlConstants.getLiveDomain());
        this.e.setText(UrlConstants.getApiDomain());
        this.f.setText(UrlConstants.getPayDomain());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_change_livetest2) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN.replace("livetest1", "livetest2"));
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS.replace("livetest1", "livetest2"));
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN.replace("test1", "test2"));
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境2");
            e();
        } else if (view.getId() == R.id.btn_change_livetest3) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN.replace("livetest1", "livetest3"));
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS.replace("livetest1", "livetest3"));
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN.replace("test1", "test3"));
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境3");
            e();
        } else if (view.getId() == R.id.btn_change_livetest4) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN.replace("livetest1", "livetest4"));
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS.replace("livetest1", "livetest4"));
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN.replace("test1", "test4"));
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境4");
            e();
        } else if (view.getId() == R.id.btn_change_livetest5) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN.replace("livetest1", "livetest5"));
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS.replace("livetest1", "livetest5"));
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN.replace("test1", "test5"));
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境5");
            e();
        } else if (view.getId() == R.id.btn_change_livetest6) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN.replace("livetest1", "livetest6"));
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS.replace("livetest1", "livetest6"));
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN.replace("test1", "test6"));
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境6");
            e();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        ConfigInfoUtil.instance().deleteConfigAndUpdate();
    }
}
