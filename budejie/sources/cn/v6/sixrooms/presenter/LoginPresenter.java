package cn.v6.sixrooms.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import cn.v6.sixrooms.bean.PassportLoginAndRegisterParams;
import cn.v6.sixrooms.engine.PassportLoginEngine;
import cn.v6.sixrooms.mvp.interfaces.ILoginRunnable;
import cn.v6.sixrooms.net.NetworkState;
import cn.v6.sixrooms.ui.phone.RetrieveNameOrPasswordActivity;
import com.a.a.d;

public class LoginPresenter {
    private ILoginRunnable a;
    private Handler b = new Handler();
    private PassportLoginEngine c;
    public String challenge;
    private PassportLoginAndRegisterParams d = new PassportLoginAndRegisterParams();
    private boolean e;
    public String gt;

    public class GtAppDlgTask extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ LoginPresenter a;

        public GtAppDlgTask(LoginPresenter loginPresenter) {
            this.a = loginPresenter;
        }

        protected Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(true);
        }

        protected void onPostExecute(Boolean bool) {
            openGtTest(this.a.a.getActivity(), this.a.gt, this.a.challenge, bool.booleanValue());
        }

        public void openGtTest(Context context, String str, String str2, boolean z) {
            this.a.a.hideLoading();
            d dVar = new d(context, str, str2, Boolean.valueOf(z));
            dVar.a(new g(this));
            dVar.show();
        }
    }

    public LoginPresenter(ILoginRunnable iLoginRunnable) {
        this.a = iLoginRunnable;
        this.c = new PassportLoginEngine(this.d, new f(this));
    }

    protected void updatePreLoginState() {
        this.e = true;
    }

    public void resetPreLoginState() {
        this.e = false;
    }

    public void preLogin() {
        if (!this.e) {
            this.c.perLogin(this.a.getUsername(), false);
        }
    }

    public void login() {
        if (NetworkState.checkNet(this.a.getActivity())) {
            this.d.setUsername(this.a.getUsername());
            this.d.setPassword(this.a.getPassword());
            if (this.e) {
                a();
                return;
            }
            this.a.showLoading();
            this.c.perLogin(this.a.getUsername(), true);
            return;
        }
        this.a.noNetwork();
    }

    public void forgetUsername() {
        Intent intent = new Intent(this.a.getActivity(), RetrieveNameOrPasswordActivity.class);
        intent.putExtra("flag", "usename");
        this.a.getActivity().startActivity(intent);
    }

    public void forgetPassword() {
        Intent intent = new Intent(this.a.getActivity(), RetrieveNameOrPasswordActivity.class);
        intent.putExtra("flag", "password");
        this.a.getActivity().startActivity(intent);
    }

    private void a() {
        resetPreLoginState();
        new GtAppDlgTask(this).execute(new Void[0]);
    }

    public void destroy() {
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
        }
        this.d = null;
    }
}
