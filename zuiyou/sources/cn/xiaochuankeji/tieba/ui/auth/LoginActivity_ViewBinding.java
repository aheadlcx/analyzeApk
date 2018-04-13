package cn.xiaochuankeji.tieba.ui.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class LoginActivity_ViewBinding implements Unbinder {
    private LoginActivity b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;

    @UiThread
    public LoginActivity_ViewBinding(final LoginActivity loginActivity, View view) {
        this.b = loginActivity;
        View a = b.a(view, R.id.forget, "field 'forget' and method 'forget'");
        loginActivity.forget = (TextView) b.c(a, R.id.forget, "field 'forget'", TextView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.forget();
            }
        });
        a = b.a(view, R.id.register, "field 'register' and method 'register'");
        loginActivity.register = (TextView) b.c(a, R.id.register, "field 'register'", TextView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.register();
            }
        });
        a = b.a(view, R.id.cc, "field 'cc' and method 'openRegion'");
        loginActivity.cc = (AppCompatTextView) b.c(a, R.id.cc, "field 'cc'", AppCompatTextView.class);
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.openRegion();
            }
        });
        a = b.a(view, R.id.use_phone, "field 'use_phone' and method 'phoneLogin'");
        loginActivity.use_phone = (AppCompatTextView) b.c(a, R.id.use_phone, "field 'use_phone'", AppCompatTextView.class);
        this.f = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.phoneLogin();
            }
        });
        loginActivity.tip_other_way = (AppCompatTextView) b.b(view, R.id.tip_other_way, "field 'tip_other_way'", AppCompatTextView.class);
        a = b.a(view, R.id.login, "field 'login' and method 'login'");
        loginActivity.login = (Button) b.c(a, R.id.login, "field 'login'", Button.class);
        this.g = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.login();
            }
        });
        loginActivity.phone_layout = b.a(view, R.id.phone_layout, "field 'phone_layout'");
        a = b.a(view, R.id.phone, "field 'phoneEdit' and method 'phoneFocus'");
        loginActivity.phoneEdit = (EditText) b.c(a, R.id.phone, "field 'phoneEdit'", EditText.class);
        this.h = a;
        a.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ LoginActivity_ViewBinding b;

            public void onFocusChange(View view, boolean z) {
                loginActivity.phoneFocus(z);
            }
        });
        a = b.a(view, R.id.code, "field 'codeEdit' and method 'codeFocus'");
        loginActivity.codeEdit = (EditText) b.c(a, R.id.code, "field 'codeEdit'", EditText.class);
        this.i = a;
        a.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ LoginActivity_ViewBinding b;

            public void onFocusChange(View view, boolean z) {
                loginActivity.codeFocus(z);
            }
        });
        loginActivity.imageStateTip = (ImageView) b.b(view, R.id.iv_state_tip, "field 'imageStateTip'", ImageView.class);
        a = b.a(view, R.id.wx, "field 'wx' and method 'wxLogin'");
        loginActivity.wx = (AppCompatImageView) b.c(a, R.id.wx, "field 'wx'", AppCompatImageView.class);
        this.j = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.wxLogin();
            }
        });
        a = b.a(view, R.id.qq, "field 'qq' and method 'qqLogin'");
        loginActivity.qq = (AppCompatImageView) b.c(a, R.id.qq, "field 'qq'", AppCompatImageView.class);
        this.k = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.qqLogin();
            }
        });
        a = b.a(view, R.id.sina, "field 'sina' and method 'sinaLogin'");
        loginActivity.sina = (AppCompatImageView) b.c(a, R.id.sina, "field 'sina'", AppCompatImageView.class);
        this.l = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.sinaLogin();
            }
        });
        loginActivity.phone_icon = (AppCompatImageView) b.b(view, R.id.phone_icon, "field 'phone_icon'", AppCompatImageView.class);
        loginActivity.layout_title = b.a(view, R.id.layout_title, "field 'layout_title'");
        View a2 = b.a(view, R.id.back, "method 'back'");
        this.m = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ LoginActivity_ViewBinding c;

            public void a(View view) {
                loginActivity.back();
            }
        });
    }

    @CallSuper
    public void a() {
        LoginActivity loginActivity = this.b;
        if (loginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        loginActivity.forget = null;
        loginActivity.register = null;
        loginActivity.cc = null;
        loginActivity.use_phone = null;
        loginActivity.tip_other_way = null;
        loginActivity.login = null;
        loginActivity.phone_layout = null;
        loginActivity.phoneEdit = null;
        loginActivity.codeEdit = null;
        loginActivity.imageStateTip = null;
        loginActivity.wx = null;
        loginActivity.qq = null;
        loginActivity.sina = null;
        loginActivity.phone_icon = null;
        loginActivity.layout_title = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
        this.h.setOnFocusChangeListener(null);
        this.h = null;
        this.i.setOnFocusChangeListener(null);
        this.i = null;
        this.j.setOnClickListener(null);
        this.j = null;
        this.k.setOnClickListener(null);
        this.k = null;
        this.l.setOnClickListener(null);
        this.l = null;
        this.m.setOnClickListener(null);
        this.m = null;
    }
}
