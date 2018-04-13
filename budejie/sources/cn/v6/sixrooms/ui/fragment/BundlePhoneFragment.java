package cn.v6.sixrooms.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.listener.VerifyMessageCallback;
import cn.v6.sixrooms.mvp.interfaces.IVerifyPhoneRunnable;
import cn.v6.sixrooms.presenter.VerifyPhonePresenter;
import cn.v6.sixrooms.ui.phone.MsgVerifyFragmentActivity;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.MobilePhoneUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.widgets.phone.FilterView;
import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView;
import com.budejie.www.R$styleable;

public class BundlePhoneFragment extends BaseFragment implements IVerifyPhoneRunnable {
    private VerifyMessageCallback a;
    private View b;
    private Bundle c;
    private String d;
    private DialogUtils e;
    private MsgVerifyFragmentActivity f;
    private VerifyPhonePresenter g;
    private EditText h;
    private EditText i;
    private RelativeLayout j;
    private TextView k;
    private RelativeLayout l;
    private HideOrDisplayThePasswordView m;
    private FilterView n;
    private FilterView o;
    private ImageView p;
    private InputMethodManager q;
    private Handler r = new Handler();

    public static BundlePhoneFragment newInstance() {
        return new BundlePhoneFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = (MsgVerifyFragmentActivity) getActivity();
        this.a = (VerifyMessageCallback) getActivity();
        this.q = (InputMethodManager) getActivity().getSystemService("input_method");
        this.g = new VerifyPhonePresenter(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = layoutInflater.inflate(R.layout.phone_activity_bundle_phone, viewGroup, false);
        return this.b;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.c = getArguments();
        this.d = this.c.getString("isneedpaawd");
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.titlebar_next_selector), getResources().getString(R.string.keep_secret_phone), new e(this), new f(this));
        ((TextView) this.b.findViewById(R.id.bundle_phone_description)).setText(Html.fromHtml(getActivity().getString(R.string.bundle_description_top)));
        this.l = (RelativeLayout) this.b.findViewById(R.id.rl_bundle_phone_password);
        this.i = (EditText) this.b.findViewById(R.id.et_bundle_phone_password);
        this.h = (EditText) this.b.findViewById(R.id.et_bundle_phone_number);
        this.n = (FilterView) this.b.findViewById(R.id.fv_bundle_phone_password);
        this.o = (FilterView) this.b.findViewById(R.id.fv_bundle_phone_number);
        this.m = (HideOrDisplayThePasswordView) this.b.findViewById(R.id.id_view_hide_or_display_password);
        this.p = (ImageView) this.b.findViewById(R.id.id_iv_clear_number);
        this.j = (RelativeLayout) this.b.findViewById(R.id.bundle_phone_number_progreebar);
        this.k = (TextView) this.b.findViewById(R.id.tv_loadingHint);
        this.k.setText(getResources().getString(R.string.bundle_send));
        if ("0".equals(this.d)) {
            this.l.setVisibility(8);
            this.b.findViewById(R.id.id_line).setVisibility(8);
        }
        b();
        this.e = new DialogUtils(this.f);
        this.m.setOnHideOrDisplayListener(new g(this));
        this.n.setOnClickListener(new h(this));
        this.o.setOnClickListener(new i(this));
        this.p.setOnClickListener(new j(this));
        this.h.addTextChangedListener(new k(this));
        this.i.addTextChangedListener(new l(this));
        this.h.setOnFocusChangeListener(new b(this));
        this.i.setOnFocusChangeListener(new c(this));
        this.r.postDelayed(new a(this), 500);
    }

    private boolean a() {
        Object phoneNumber = getPhoneNumber();
        CharSequence password = getPassword();
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showToast(R.string.phone_number_empty);
            return false;
        }
        try {
            if (!MobilePhoneUtils.isMobileNO(phoneNumber)) {
                ToastUtils.showToast(R.string.phone_number_error);
                return false;
            } else if (!this.d.equals("1") || !TextUtils.isEmpty(password)) {
                return true;
            } else {
                ToastUtils.showToast(R.string.phone_password_empty);
                return false;
            }
        } catch (Exception e) {
            ToastUtils.showToast(R.string.phone_number_empty);
            return false;
        }
    }

    protected void setPasswordType(boolean z) {
        if (z) {
            this.i.setInputType(R$styleable.Theme_Custom_phone_verify_btn_bg);
        } else {
            this.i.setInputType(129);
        }
        this.i.setSelection(this.i.length());
    }

    protected void cleanPasswordView(boolean z) {
        if (z) {
            this.m.showCleanTag();
        } else {
            this.m.hideCleanTag();
        }
    }

    protected void clearPassword() {
        this.i.setText("");
    }

    private void b() {
        this.p.setVisibility(4);
    }

    public void showLoading() {
        this.j.setVisibility(0);
    }

    public void hideLoading() {
        this.j.setVisibility(8);
    }

    public String getPassword() {
        return this.i.getText().toString().trim();
    }

    public String getPhoneNumber() {
        return this.h.getText().toString().trim();
    }

    public void verifyPhoneSucceed() {
        this.a.verifyMessage(getPhoneNumber(), getPassword());
    }

    public void error(int i) {
        this.f.showErrorToast(i);
    }

    public void handleErrorResult(String str, String str2) {
        this.f.handleErrorResult(str, str2, this.f);
    }
}
