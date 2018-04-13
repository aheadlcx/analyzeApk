package cn.v6.sixrooms.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.listener.LoginManagerListener;
import cn.v6.sixrooms.mvp.interfaces.ILoginRunnable;
import cn.v6.sixrooms.presenter.LoginPresenter;
import cn.v6.sixrooms.ui.phone.MsgVerifyFragmentActivity;
import cn.v6.sixrooms.ui.phone.UserManagerActivity;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView;
import cn.v6.sixrooms.widgets.phone.UserLoginTitleView;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.budejie.www.R$styleable;

public class LoginFragment extends BaseFragment implements ILoginRunnable {
    private LoginManagerListener a;
    private InputMethodManager b;
    private UserManagerActivity c;
    private View d;
    private EditText e;
    private EditText f;
    private Button g;
    private LinearLayout h;
    private TextView i;
    private TextView j;
    private HideOrDisplayThePasswordView k;
    private LoginPresenter l;
    private Handler m = new Handler();
    private String[] n = new String[]{"com.tencent.mobileqq", "com.tencent.mm"};
    private ImageView o;

    static /* synthetic */ boolean c(LoginFragment loginFragment) {
        if (TextUtils.isEmpty(loginFragment.getUsername())) {
            ToastUtils.showToast(loginFragment.getString(R.string.username_empty));
            return false;
        } else if (!loginFragment.getUsername().contains(" ")) {
            return true;
        } else {
            ToastUtils.showToast(loginFragment.getString(R.string.tip_username_containBlank));
            return false;
        }
    }

    static /* synthetic */ boolean d(LoginFragment loginFragment) {
        if (TextUtils.isEmpty(loginFragment.getPassword())) {
            ToastUtils.showToast(loginFragment.getString(R.string.password_empty));
            return false;
        } else if (!loginFragment.getPassword().contains(" ")) {
            return true;
        } else {
            ToastUtils.showToast(loginFragment.getString(R.string.tip_password_containBlank));
            return false;
        }
    }

    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.a = (LoginManagerListener) getActivity();
            this.c = (UserManagerActivity) getActivity();
            this.b = (InputMethodManager) getActivity().getSystemService("input_method");
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnOperateListener");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.phone_fragment_login, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) this.d.findViewById(R.id.layout);
        View userLoginTitleView = new UserLoginTitleView(getActivity(), 1, new n(this));
        linearLayout.addView(userLoginTitleView, 0);
        LayoutParams layoutParams = userLoginTitleView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = (int) getResources().getDimension(R.dimen.title_login_height);
        userLoginTitleView.setLayoutParams(layoutParams);
        return this.d;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.l = new LoginPresenter(this);
        this.e = (EditText) this.d.findViewById(R.id.et_username);
        this.f = (EditText) this.d.findViewById(R.id.et_password);
        this.o = (ImageView) this.d.findViewById(R.id.id_iv_clean_loginname);
        this.k = (HideOrDisplayThePasswordView) this.d.findViewById(R.id.id_password_show_hide);
        this.h = (LinearLayout) this.d.findViewById(R.id.ll_into_register);
        this.g = (Button) this.d.findViewById(R.id.but_login);
        this.i = (TextView) this.d.findViewById(R.id.login_forget_username);
        this.j = (TextView) this.d.findViewById(R.id.login_forget_password);
        this.i.getPaint().setFlags(9);
        this.j.getPaint().setFlags(9);
        hideUsernameCleanView();
        this.h.setOnClickListener(new r(this));
        this.g.setOnClickListener(new s(this));
        this.i.setOnClickListener(new t(this));
        this.j.setOnClickListener(new u(this));
        this.e.addTextChangedListener(new v(this));
        this.f.addTextChangedListener(new w(this));
        this.e.setOnFocusChangeListener(new x(this));
        this.f.setOnFocusChangeListener(new y(this));
        this.k.setOnHideOrDisplayListener(new o(this));
        this.o.setOnClickListener(new p(this));
    }

    protected void clearUsername() {
        this.e.setText("");
    }

    protected void clearPassword() {
        this.f.setText("");
    }

    protected void showUsernameCleanView(boolean z) {
        if (z) {
            this.o.setVisibility(0);
        } else {
            hideUsernameCleanView();
        }
    }

    protected void hideUsernameCleanView() {
        this.o.setVisibility(8);
    }

    protected void setPasswordViewTag(boolean z) {
        if (this.k == null || z) {
            this.k.hideCleanTag();
        } else {
            this.k.showCleanTag();
        }
    }

    protected void setPasswordType(boolean z) {
        if (z) {
            this.f.setInputType(R$styleable.Theme_Custom_phone_verify_btn_bg);
        } else {
            this.f.setInputType(129);
        }
        this.f.setSelection(this.f.length());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public String getUsername() {
        return this.e.getText().toString().trim();
    }

    public String getPassword() {
        return this.f.getText().toString().trim();
    }

    public void hideSystemInput() {
        if (getView() != null) {
            this.b.hideSoftInputFromWindow(getView().getWindowToken(), 2);
        }
    }

    public void onResume() {
        super.onResume();
        if (GlobleValue.forgetUserName != null && this.e != null) {
            this.e.setText(GlobleValue.forgetUserName);
            this.e.setSelection(GlobleValue.forgetUserName.length());
            GlobleValue.forgetUserName = null;
        }
    }

    public void onStop() {
        super.onStop();
        if (this.m != null) {
            this.m.removeCallbacksAndMessages(null);
        }
    }

    public void showLoading() {
        if (isAdded()) {
            String string = getString(R.string.tip_show_on_logining);
            if (isAdded()) {
                this.c.makeView(this.c, getString(R.string.InfoAbout), string);
            }
        }
    }

    public void gtError(String str) {
        if (TextUtils.isEmpty(str)) {
            this.c.showTipDialog(this.c.getResources().getString(R.string.gt_error));
        } else {
            ToastUtils.showToast(str);
        }
    }

    public void clearUsernamePassword() {
        clearPassword();
        clearUsername();
    }

    public void requestCode(int i) {
        if (i == 1001 || i == 1002) {
            this.c.showTipDialog(getActivity().getResources().getString(R.string.tip_login_unsuccessful_str));
        } else if (i == CommonInts.PCK_ERROE_CODE) {
            this.c.showTipDialog(getActivity().getResources().getString(R.string.connection_timeouts));
        } else if (i == 1004) {
            this.c.showTipDialog(getActivity().getResources().getString(R.string.tip_security_error_title));
        } else if (i == 1010) {
            this.c.showTipDialog(getActivity().getResources().getString(R.string.tip_login_username_not_exist_str));
        } else if (i == 1011) {
            this.c.showTipDialog(getActivity().getResources().getString(R.string.username_forbidden_word_str));
        } else if (i == 1008) {
            this.c.showTipDialog(getActivity().getResources().getString(R.string.username_illegal));
        } else {
            this.c.showTipDialog(getActivity().getResources().getString(R.string.tip_server_busy_title));
        }
    }

    public void noNetwork() {
        ToastUtils.showToast(getString(R.string.tip_no_network));
    }

    public void hideLoading() {
        this.c.dismiss();
    }

    public void remoteLogin(boolean z, String str, String str2) {
        new DialogUtils(this.c).createConfirmDialog(22, getResources().getString(R.string.tip_show_tip_title), getResources().getString(R.string.other_place_login_get_msg_verify), getResources().getString(R.string.phone_cancel), getResources().getString(R.string.get_verify), new q(this, str2, z)).show();
    }

    public void loginSuccess(String str) {
        this.a.userLoginSuccess(str);
    }

    public void loginError(int i) {
        this.c.showErrorToast(i);
    }

    static /* synthetic */ void a(LoginFragment loginFragment, String str) {
        Intent intent = new Intent(loginFragment.c, MsgVerifyFragmentActivity.class);
        intent.putExtra(UserTrackerConstants.FROM, "otherPlaceLogin");
        intent.putExtra("ticket", str);
        intent.putExtra("phoneNumber", loginFragment.getResources().getString(R.string.you_phone));
        loginFragment.startActivity(intent);
        loginFragment.c.finish();
    }
}
