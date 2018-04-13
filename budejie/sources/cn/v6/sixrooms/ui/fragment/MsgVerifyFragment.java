package cn.v6.sixrooms.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.mvp.interfaces.IRemoteLoginRunnable;
import cn.v6.sixrooms.mvp.interfaces.IUnBindMobileRunnable;
import cn.v6.sixrooms.mvp.interfaces.IVerifyMessageRunnable;
import cn.v6.sixrooms.presenter.RemoteLoginPresenter;
import cn.v6.sixrooms.presenter.UnBindMobilePresenter;
import cn.v6.sixrooms.presenter.VerifyMessagePresenter;
import cn.v6.sixrooms.ui.phone.MsgVerifyFragmentActivity;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;

public class MsgVerifyFragment extends BaseFragment implements IRemoteLoginRunnable, IUnBindMobileRunnable, IVerifyMessageRunnable {
    private View a;
    private MsgVerifyFragmentActivity b;
    private VerifyMessagePresenter c;
    private UnBindMobilePresenter d;
    private RemoteLoginPresenter e;
    private int f;
    private MsgVerifyCallBack g;
    private String h;
    private String i;
    private String j;
    private DialogUtils k;
    private RelativeLayout l;
    private TextView m;
    private TextView n;
    private EditText o;
    private Button p;
    private GetVerificationCodeView q;
    private ImageView r;
    private String s = "";
    private SpannableString t;
    private InputMethodManager u;
    private Handler v = new Handler();

    public interface MsgVerifyCallBack {
        void bundleAgain();
    }

    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    public static MsgVerifyFragment newInstance() {
        return new MsgVerifyFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = (MsgVerifyFragmentActivity) getActivity();
        this.g = (MsgVerifyCallBack) getActivity();
        this.k = new DialogUtils(this.b);
        this.u = (InputMethodManager) getActivity().getSystemService("input_method");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.phone_activity_msg_verify, viewGroup, false);
        return this.a;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle arguments = getArguments();
        this.i = arguments.getString("phoneNumber");
        this.h = arguments.getString("password");
        this.j = arguments.getString("ticket");
        String string = arguments.getString(UserTrackerConstants.FROM);
        if ("bundle".equals(string)) {
            this.f = 1;
            this.c = new VerifyMessagePresenter(this);
        } else if ("unbundle".equals(string)) {
            this.f = 2;
            this.d = new UnBindMobilePresenter(this);
        } else if ("otherPlaceLogin".equals(string)) {
            this.f = 3;
            this.e = new RemoteLoginPresenter(this);
        }
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, null, "", new aa(this), null);
        this.l = (RelativeLayout) this.a.findViewById(R.id.bundle_phone_progreebar);
        this.m = (TextView) this.a.findViewById(R.id.tv_loadingHint);
        this.n = (TextView) this.a.findViewById(R.id.tv_msg_verify_tip);
        this.o = (EditText) this.a.findViewById(R.id.et_bundle_phone_verify_code);
        this.p = (Button) this.a.findViewById(R.id.but_bundle_phone_submit);
        this.r = (ImageView) this.a.findViewById(R.id.id_iv_clear_code);
        this.q = (GetVerificationCodeView) this.a.findViewById(R.id.id_view_get_verification_code);
        switch (this.f) {
            case 1:
                setTitleText(getResources().getString(R.string.bundle_fill_in_verify));
                break;
            case 2:
                setTitleText(getResources().getString(R.string.remove_secret_phone));
                break;
            case 3:
                setTitleText(getResources().getString(R.string.msg_verify_other_place_login));
                break;
        }
        if (TextUtils.isEmpty(this.i)) {
            this.s = getResources().getString(R.string.msg_verify_sended_to_you);
        } else {
            this.s = getResources().getString(R.string.msg_verify_sended);
            this.s = String.format(this.s, new Object[]{this.i});
            this.t = new SpannableString(this.s);
            this.t.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), this.s.indexOf(this.i), this.t.length(), 18);
            this.n.setText(this.t);
        }
        a();
        this.q.runCountdown();
        this.p.setOnClickListener(new ab(this));
        this.r.setOnClickListener(new ac(this));
        this.o.addTextChangedListener(new ad(this));
        this.q.setOnGetVerificationCodeListener(new ae(this));
        switch (this.f) {
            case 2:
                this.d.getUnBindVerifyCode();
                break;
            case 3:
                this.e.getLoginVerifyCode();
                break;
        }
        this.v.postDelayed(new z(this), 500);
    }

    public void handleVerifySucceed(String str) {
        switch (this.f) {
            case 1:
            case 2:
                this.n.setText(this.t);
                break;
            case 3:
                this.n.setText(this.s);
                break;
        }
        this.b.showToast(str);
    }

    public void cleanPromat() {
        this.n.setText("");
    }

    public void remoteLoginSucceed(String str) {
        this.k.createConfirmDialogs(205, getResources().getString(R.string.bundle_phone_success_tip), str, getResources().getString(R.string.phone_confirm), new af(this)).show();
    }

    public void showErrorDialog(String str, String str2) {
        if (this.k == null) {
            this.k = new DialogUtils(this.b);
        }
        this.k.createConfirmDialogs(206, getResources().getString(R.string.tip_show_tip_title), str2, getResources().getString(R.string.phone_confirm), new ag(this, str)).show();
    }

    public void showLoading(boolean z) {
        TextView textView;
        CharSequence string;
        switch (this.f) {
            case 1:
                textView = this.m;
                if (z) {
                    string = getResources().getString(R.string.bundle_bundling);
                } else {
                    string = getResources().getString(R.string.bundle_send);
                }
                textView.setText(string);
                break;
            case 2:
                textView = this.m;
                if (z) {
                    string = getResources().getString(R.string.bundle_unbundling);
                } else {
                    string = getResources().getString(R.string.bundle_send);
                }
                textView.setText(string);
                break;
            case 3:
                this.m.setText(getResources().getString(R.string.bundle_send));
                break;
        }
        this.l.setVisibility(0);
    }

    public void hideLoading() {
        this.l.setVisibility(8);
    }

    public String getCode() {
        return this.o.getText().toString().trim();
    }

    public void clearCode() {
        this.o.setText("");
    }

    public void bindSucceed() {
        Intent intent = new Intent();
        intent.putExtra("issucceed", true);
        this.b.setResult(0, intent);
        remoteLoginSucceed(getResources().getString(R.string.bundle_phone_success_msg));
    }

    public void unbundleSucceed() {
        Intent intent = new Intent();
        intent.putExtra("issucceed", true);
        this.b.setResult(0, intent);
        remoteLoginSucceed(getResources().getString(R.string.unbundle_phone_success_msg));
    }

    public void error(int i) {
        this.b.showErrorToast(i);
    }

    public String getPhoneNumber() {
        return this.i.trim();
    }

    public String getPassword() {
        return this.h.trim();
    }

    public void handleErrorInfo(String str, String str2) {
        if (CommonStrs.FLAG_TYPE_NEED_LOGIN.equals(str)) {
            this.b.handleErrorResult(str, str2, this.b);
        } else {
            showErrorDialog(str, str2);
        }
    }

    public String getTicket() {
        return this.j.trim();
    }

    public void logOut() {
        this.b.logout();
    }

    public void onDetach() {
        if (this.e != null) {
            this.e.onDetach();
        }
        super.onDetach();
    }

    private void a() {
        this.r.setVisibility(8);
    }

    static /* synthetic */ void k(MsgVerifyFragment msgVerifyFragment) {
        switch (msgVerifyFragment.f) {
            case 1:
                msgVerifyFragment.c.getBindVerifyCode();
                return;
            case 2:
                msgVerifyFragment.d.getUnBindVerifyCode();
                return;
            case 3:
                msgVerifyFragment.e.getLoginVerifyCode();
                return;
            default:
                return;
        }
    }
}
