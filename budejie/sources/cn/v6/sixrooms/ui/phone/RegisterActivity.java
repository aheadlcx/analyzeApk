package cn.v6.sixrooms.ui.phone;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.login.manager.RegisterManager;
import cn.v6.sixrooms.net.NetworkState;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.ImprovedProgressDialog;
import cn.v6.sixrooms.utils.MobilePhoneUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView;
import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView;

public class RegisterActivity extends BaseFragmentActivity implements OnClickListener {
    private static final String a = RegisterActivity.class.getSimpleName();
    private EditText b;
    private EditText c;
    private EditText d;
    private EditText e;
    private RegisterManager f;
    private ImageView g;
    private ImageView h;
    private HideOrDisplayThePasswordView i;
    private DialogUtils j;
    private ImprovedProgressDialog k;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phone_fragment_rigister_layout);
        this.f = new RegisterManager(this, new bm(this));
        g();
        findViewById(R.id.id_iv_title_back).setOnClickListener(this);
        findViewById(R.id.but_register).setOnClickListener(this);
        findViewById(R.id.id_tv_title_user_login_login).setVisibility(8);
        this.b = (EditText) findViewById(R.id.et_username);
        this.c = (EditText) findViewById(R.id.et_password);
        this.d = (EditText) findViewById(R.id.id_et_phone_number);
        this.e = (EditText) findViewById(R.id.id_et_phone_code);
        this.g = (ImageView) findViewById(R.id.id_iv_clean_username);
        this.h = (ImageView) findViewById(R.id.id_iv_clean_phone_number);
        this.i = (HideOrDisplayThePasswordView) findViewById(R.id.id_password_show_hide);
        GetVerificationCodeView getVerificationCodeView = (GetVerificationCodeView) findViewById(R.id.id_view_get_verification_code);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnHideOrDisplayListener(new bn(this));
        getVerificationCodeView.setOnGetVerificationCodeListener(new bo(this));
        this.d.addTextChangedListener(new bp(this));
        this.d.setOnFocusChangeListener(new bq(this));
        this.b.addTextChangedListener(new br(this));
        this.b.setOnFocusChangeListener(new bs(this));
        this.c.addTextChangedListener(new bt(this));
        this.c.setOnFocusChangeListener(new bu(this));
        a(d());
    }

    public void onClick(View view) {
        boolean z = false;
        int id = view.getId();
        if (id == R.id.id_iv_title_back) {
            finish();
        } else if (id == R.id.id_iv_clean_username) {
            this.b.setText("");
            b(b());
        } else if (id == R.id.id_iv_clean_phone_number) {
            this.d.setText("");
            a(d());
        } else if (id != R.id.but_register) {
        } else {
            if (NetworkState.checkNet(getApplicationContext())) {
                boolean z2;
                if (TextUtils.isEmpty(b())) {
                    ToastUtils.showToast(R.string.authorization_username_empty);
                    z2 = false;
                } else if (b().contains(" ")) {
                    ToastUtils.showToast(R.string.authorization_username_contain_blank);
                    z2 = false;
                } else if (b().matches(CommonStrs.USERNAME_REGEX)) {
                    z2 = true;
                } else {
                    ToastUtils.showToast(R.string.username_contain_special_characters);
                    z2 = false;
                }
                if (z2) {
                    if (TextUtils.isEmpty(c())) {
                        ToastUtils.showToast(getString(R.string.authorization_password_empty));
                    } else if (c().contains(" ")) {
                        ToastUtils.showToast(getString(R.string.authorization_password_contain_blank));
                    } else {
                        z = true;
                    }
                    if (!z) {
                        return;
                    }
                    if (TextUtils.isEmpty(e())) {
                        ToastUtils.showToast(R.string.authorization_identifying_code_empty);
                        return;
                    }
                    this.f.perRegister(true, b(), c(), d(), e());
                    a(R.string.authorization_ready_register);
                    return;
                }
                return;
            }
            ToastUtils.showToast(R.string.tip_no_network);
        }
    }

    private String b() {
        return this.b.getText().toString();
    }

    private String c() {
        return this.c.getText().toString();
    }

    private String d() {
        return this.d.getText().toString();
    }

    private String e() {
        return this.e.getText().toString();
    }

    private void a(CharSequence charSequence) {
        if (charSequence.length() > 0) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
    }

    private void b(CharSequence charSequence) {
        if (charSequence.length() > 0) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
    }

    private boolean f() {
        try {
            if (MobilePhoneUtils.isPhoneNumber(d())) {
                return true;
            }
            ToastUtils.showToast(R.string.phone_number_error);
            return false;
        } catch (Exception e) {
            ToastUtils.showToast(R.string.phone_number_empty);
        }
    }

    private void a(String str) {
        if (this.j == null) {
            this.j = new DialogUtils(this);
        }
        this.j.createDiaglog(str, getString(R.string.InfoAbout)).show();
    }

    private void g() {
        if (this.k == null) {
            this.k = new ImprovedProgressDialog(this, "");
        }
    }

    private void a(int i) {
        g();
        this.k.show();
        this.k.changeMessage(getString(i));
    }

    static /* synthetic */ void a(RegisterActivity registerActivity) {
        if (registerActivity.k != null && registerActivity.k.isShowing()) {
            registerActivity.k.dismiss();
        }
    }

    static /* synthetic */ void b(RegisterActivity registerActivity, int i) {
        if (i == 1003) {
            registerActivity.a(registerActivity.getString(R.string.tip_regist_user_occupied_title));
        } else if (i == CommonInts.PCK_ERROE_CODE) {
            registerActivity.a(registerActivity.getString(R.string.connection_timeouts));
        } else if (i == 1004) {
            registerActivity.a(registerActivity.getString(R.string.tip_security_error_title));
        } else if (i == 1011) {
            registerActivity.a(registerActivity.getString(R.string.username_forbidden_word_str));
        } else if (i == 1008) {
            registerActivity.a(registerActivity.getString(R.string.username_illegal));
        } else if (i == CommonInts.GT_ERROR) {
            registerActivity.a(registerActivity.getString(R.string.gt_error));
        } else {
            registerActivity.a(registerActivity.getString(R.string.tip_server_busy_title));
        }
    }

    static /* synthetic */ void c(RegisterActivity registerActivity, CharSequence charSequence) {
        if (charSequence.length() > 0) {
            registerActivity.i.showCleanTag();
        } else {
            registerActivity.i.hideCleanTag();
        }
    }
}
