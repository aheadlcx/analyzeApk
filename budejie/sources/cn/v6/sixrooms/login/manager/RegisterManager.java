package cn.v6.sixrooms.login.manager;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.v6.sixrooms.login.beans.PassportLoginAndRegisterParams;
import cn.v6.sixrooms.login.engines.GetRegisterVerificationEngine;
import cn.v6.sixrooms.login.engines.GtAuthEngine;
import cn.v6.sixrooms.login.engines.LoginClientEngine;
import cn.v6.sixrooms.login.engines.PassportRegisterEngine;
import cn.v6.sixrooms.login.interfaces.RegisterCallback;
import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView$RunCountdownCallback;
import com.a.a.d;

public class RegisterManager implements Runnable {
    private Activity a;
    private RegisterCallback b;
    private PassportRegisterEngine c;
    private LoginClientEngine d;
    private GtAuthEngine e;
    private PassportLoginAndRegisterParams f;
    private GetVerificationCodeView$RunCountdownCallback g;
    private String h;
    private String i;
    private Handler j = new Handler(Looper.getMainLooper());
    private GetRegisterVerificationEngine k;

    public RegisterManager(Activity activity, RegisterCallback registerCallback) {
        this.a = activity;
        this.b = registerCallback;
        b();
        c();
        a();
    }

    private void a() {
        this.e = new GtAuthEngine(new b(this));
    }

    private void b() {
        if (this.c == null) {
            this.f = new PassportLoginAndRegisterParams();
            this.c = new PassportRegisterEngine();
            this.c.setParams(this.f);
            this.c.setPassportRegisterCallback(new c(this));
        }
    }

    private void c() {
        if (this.d == null) {
            this.d = new LoginClientEngine();
            this.d.setLoginClientCallback(new d(this));
        }
    }

    public void perRegister(boolean z, String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str.trim())) {
            b();
            if (z) {
                this.f.setUsername(str);
                this.f.setPassword(str2);
                this.f.setPhoneNumber(str3);
                this.f.setIdentifyingCode(str4);
            }
            this.c.perRegister(str, z);
        }
    }

    public void getGtParams(String str, GetVerificationCodeView$RunCountdownCallback getVerificationCodeView$RunCountdownCallback) {
        this.g = getVerificationCodeView$RunCountdownCallback;
        this.f.setPhoneNumber(str);
        a();
        this.e.getGtChanllenge();
    }

    public void run() {
        if (this.a != null && !this.a.isFinishing()) {
            d dVar = new d(this.a, this.h, this.i, Boolean.valueOf(true));
            dVar.a(new e(this));
            dVar.show();
        }
    }

    static /* synthetic */ void a(RegisterManager registerManager, boolean z) {
        if (registerManager.k == null) {
            registerManager.k = new GetRegisterVerificationEngine(new a(registerManager));
        }
        if (z) {
            registerManager.k.setChallenge(registerManager.f.getChallenge()).setValidate(registerManager.f.getValidate()).setSeccode(registerManager.f.getSeccode());
        }
        registerManager.k.getAuthCode(registerManager.f.getPhoneNumber());
    }
}
