package cn.v6.sixrooms.ui.phone;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.mvp.interfaces.IFindUserOrPwdRunnable;
import cn.v6.sixrooms.presenter.FindUserOrPwdPresenter;
import cn.v6.sixrooms.utils.MobilePhoneUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class RetrieveNameOrPasswordActivity extends SlidingActivity implements IFindUserOrPwdRunnable {
    private static int a;
    private RelativeLayout b;
    private RelativeLayout c;
    private ImageView d;
    private GetVerificationCodeView e;
    private EditText f;
    private EditText g;
    private EditText h;
    private View i;
    private FindUserOrPwdPresenter j;
    private InputMethodManager k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private Handler o = new Handler();

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_retrieve_name_or_password);
        if ("usename".equals(getIntent().getExtras().get("flag").toString())) {
            a = 1;
        } else {
            a = 0;
        }
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new cq(this));
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.titlebar_next_selector), "", new co(this), new cp(this));
        this.b = (RelativeLayout) findViewById(R.id.rl_find_phone_view);
        this.c = (RelativeLayout) findViewById(R.id.rl_username);
        this.f = (EditText) findViewById(R.id.et_phone_number);
        this.g = (EditText) findViewById(R.id.et_retrieve_auth_code);
        this.h = (EditText) findViewById(R.id.et_username);
        this.l = (ImageView) findViewById(R.id.id_iv_clean_username);
        this.m = (ImageView) findViewById(R.id.id_iv_clean_phone_number);
        this.n = (ImageView) findViewById(R.id.id_iv_clean_phone_code);
        this.d = (ImageView) findViewById(R.id.iv_username);
        this.e = (GetVerificationCodeView) findViewById(R.id.btn_phone_number);
        this.i = findViewById(R.id.bundle_phone_number_progreebar);
        if (a == 1) {
            setTitleText("找回用户名");
            this.c.setVisibility(8);
            this.d.setVisibility(8);
        } else {
            setTitleText("找回密码");
        }
        hideCleanUsernameView();
        hideCleanAudoCodeView();
        hideCleanPhoneNumView();
        Animation translateAnimation = new TranslateAnimation((float) getWindowManager().getDefaultDisplay().getWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.b.startAnimation(translateAnimation);
        this.e.setOnGetVerificationCodeListener(new cr(this));
        this.h.addTextChangedListener(new cs(this));
        this.f.addTextChangedListener(new ct(this));
        this.l.setOnClickListener(new cu(this));
        this.m.setOnClickListener(new cv(this));
        this.n.setOnClickListener(new cj(this));
        this.g.addTextChangedListener(new ck(this));
        this.f.setOnFocusChangeListener(new cl(this));
        this.g.setOnFocusChangeListener(new cm(this));
        this.h.setOnFocusChangeListener(new cn(this));
        this.j = new FindUserOrPwdPresenter(this);
        this.o.postDelayed(new ci(this), 500);
    }

    protected void showCleanAutoCodeView(boolean z) {
        if (z) {
            this.n.setVisibility(0);
        } else {
            hideCleanAudoCodeView();
        }
    }

    protected void hideCleanAudoCodeView() {
        this.n.setVisibility(8);
    }

    protected void clearAutoCode() {
        this.g.setText("");
    }

    protected void clearPhoneNum() {
        this.f.setText("");
    }

    protected void showCleanPhoneNumView(boolean z) {
        if (z) {
            this.m.setVisibility(0);
        } else {
            hideCleanPhoneNumView();
        }
    }

    protected void hideCleanPhoneNumView() {
        this.m.setVisibility(8);
    }

    protected void showCleanUsernameView(boolean z) {
        if (z) {
            this.l.setVisibility(0);
        } else {
            hideCleanUsernameView();
        }
    }

    protected void hideCleanUsernameView() {
        this.l.setVisibility(8);
    }

    protected void clearUsername() {
        this.h.setText("");
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        getSlidingMenu().a();
        return true;
    }

    public void showLoading() {
        this.i.setVisibility(0);
    }

    public void hideLoading() {
        this.i.setVisibility(8);
    }

    public String getAuthCode() {
        return this.g.getText().toString().trim();
    }

    public String getPhoneNumber() {
        return this.f.getText().toString().trim();
    }

    public String getUserName() {
        return this.h.getText().toString().trim();
    }

    public Activity getActivity() {
        return this;
    }

    public boolean isRetrieveName() {
        return a == 1;
    }

    public void hideSystemInputManager() {
        this.k.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }

    public void handleErrorInfo(String str, String str2) {
        handleErrorResult(str, str2, this);
    }

    private boolean a() {
        try {
            if (MobilePhoneUtils.isMobileNO(getPhoneNumber())) {
                return true;
            }
            ToastUtils.showToast(R.string.phone_number_error);
            return false;
        } catch (Exception e) {
            ToastUtils.showToast(R.string.phone_number_empty);
            return false;
        }
    }

    public void error(int i) {
        showErrorToast(i);
    }

    static /* synthetic */ boolean d(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        if (!TextUtils.isEmpty(retrieveNameOrPasswordActivity.getAuthCode())) {
            return true;
        }
        ToastUtils.showToast(R.string.phone_verify_empty);
        return false;
    }

    static /* synthetic */ boolean e(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        if (!TextUtils.isEmpty(retrieveNameOrPasswordActivity.getUserName())) {
            return true;
        }
        ToastUtils.showToast(R.string.username_empty);
        return false;
    }
}
