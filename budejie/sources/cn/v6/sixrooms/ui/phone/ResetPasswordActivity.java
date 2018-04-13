package cn.v6.sixrooms.ui.phone;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.mvp.interfaces.IResetPasswordRunnable;
import cn.v6.sixrooms.presenter.ResetPasswordPresenter;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.MobilePhoneUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView;
import com.budejie.www.R$styleable;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class ResetPasswordActivity extends SlidingActivity implements IResetPasswordRunnable {
    private String a;
    private String b;
    private String c;
    private String d;
    private RelativeLayout e;
    private RelativeLayout f;
    private EditText g;
    private EditText h;
    private HideOrDisplayThePasswordView i;
    private InputMethodManager j;
    private ResetPasswordPresenter k;
    private ImageView l;
    private Handler m = new Handler();

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_reset_password);
        this.j = (InputMethodManager) getSystemService("input_method");
        this.k = new ResetPasswordPresenter(this);
        this.a = getIntent().getExtras().get("authCode").toString();
        this.b = getIntent().getExtras().get("mobileNumber").toString();
        this.c = getIntent().getExtras().get("userName").toString();
        String stringExtra = getIntent().getStringExtra(HistoryOpenHelper.COLUMN_UID);
        if (stringExtra != null) {
            this.d = stringExtra.toString();
        }
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new cc(this));
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), "", getResources().getDrawable(R.drawable.default_titlebar_over__defult), "重置密码", new ca(this), new cb(this));
        this.e = (RelativeLayout) findViewById(R.id.rl_reset_password_view);
        this.f = (RelativeLayout) findViewById(R.id.rl_progressBar);
        this.g = (EditText) findViewById(R.id.et_password);
        this.h = (EditText) findViewById(R.id.et_confirm_password);
        this.i = (HideOrDisplayThePasswordView) findViewById(R.id.id_view_hide_or_display_password);
        this.l = (ImageView) findViewById(R.id.id_iv_clean_confirm_password);
        hideCleanConfirmPwdView();
        Animation translateAnimation = new TranslateAnimation((float) getWindowManager().getDefaultDisplay().getWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.e.startAnimation(translateAnimation);
        this.g.addTextChangedListener(new cd(this));
        this.i.setOnHideOrDisplayListener(new ce(this));
        this.h.addTextChangedListener(new cf(this));
        this.l.setOnClickListener(new cg(this));
        this.g.setOnFocusChangeListener(new ch(this));
        this.h.setOnFocusChangeListener(new bw(this));
        this.m.postDelayed(new bv(this), 500);
    }

    private boolean a() {
        if (TextUtils.isEmpty(getPassword()) || TextUtils.isEmpty(getConfirmPassword())) {
            ToastUtils.showToast(R.string.password_empty);
            return true;
        } else if (!getPassword().equals(getConfirmPassword())) {
            ToastUtils.showToast(R.string.password_not_equals);
            return true;
        } else if (TextUtils.isEmpty(getMobileNumber())) {
            showErrorDialog(getString(R.string.phone_number_empty));
            return true;
        } else {
            try {
                if (!MobilePhoneUtils.isMobileNO(getMobileNumber())) {
                    showErrorDialog(getString(R.string.phone_number_error));
                    return true;
                } else if (TextUtils.isEmpty(getAuthCode())) {
                    showErrorDialog(getString(R.string.phone_verify_empty));
                    return true;
                } else if (!TextUtils.isEmpty(getUserName())) {
                    return false;
                } else {
                    showErrorDialog(getString(R.string.username_empty));
                    return true;
                }
            } catch (Exception e) {
                showErrorDialog(getString(R.string.phone_number_error));
                return true;
            }
        }
    }

    protected void showConfirmPwdView(boolean z) {
        if (z) {
            this.l.setVisibility(0);
        } else {
            hideCleanConfirmPwdView();
        }
    }

    protected void hideCleanConfirmPwdView() {
        this.l.setVisibility(8);
    }

    protected void clearConfirmPwd() {
        this.h.setText("");
    }

    protected void clearPassword() {
        this.g.setText("");
    }

    protected void showCleanPasswordView(boolean z) {
        if (z) {
            this.i.showCleanTag();
        } else {
            this.i.hideCleanTag();
        }
    }

    protected void setPasswordType(boolean z) {
        if (z) {
            this.g.setInputType(R$styleable.Theme_Custom_phone_verify_btn_bg);
            this.h.setInputType(R$styleable.Theme_Custom_phone_verify_btn_bg);
            return;
        }
        this.g.setInputType(129);
        this.h.setInputType(129);
    }

    public void resetPwdSucceed() {
        new DialogUtils(this).createConfirmDialogs(0, "提示", "密码修改成功!", "立即登录", new bx(this)).show();
    }

    public void fail() {
        new DialogUtils(this).createConfirmDialogs(0, "提示", "请确认验证码正确, 或者重新获取一个验证码.", "确定", new by(this)).show();
    }

    public void error(int i) {
        showErrorToast(i);
    }

    public void showLoading() {
        if (this.f == null) {
            this.f = (RelativeLayout) findViewById(R.id.rl_progressBar);
        }
        this.f.setVisibility(0);
    }

    public void hideLoading() {
        this.f.setVisibility(8);
    }

    public String getAuthCode() {
        return this.a;
    }

    public String getMobileNumber() {
        return this.b;
    }

    public String getUserName() {
        return this.c;
    }

    public String getUid() {
        return this.d;
    }

    public String getPassword() {
        return this.g.getText().toString().trim();
    }

    public String getConfirmPassword() {
        return this.h.getText().toString().trim();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        getSlidingMenu().a();
        return true;
    }

    public void showErrorDialog(String str) {
        if (!isFinishing()) {
            new DialogUtils(this).createConfirmDialogs(206, getResources().getString(R.string.tip_show_tip_title), str, getResources().getString(R.string.phone_confirm), new bz(this)).show();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.m != null) {
            this.m.removeCallbacksAndMessages(null);
            this.m = null;
        }
    }
}
