package cn.xiaochuankeji.tieba.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.user.SettingJson;
import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.background.modules.a.i;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.account.VerifyJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.my.account.InputPasswordNicknameAvatarActivity;
import cn.xiaochuankeji.tieba.ui.my.account.InputPhoneNumberActivity;
import cn.xiaochuankeji.tieba.ui.picker.RegionPicker;
import cn.xiaochuankeji.tieba.ui.utils.TipsDialog;
import com.alibaba.fastjson.JSONObject;
import com.android.a.a.c;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.Locale;
import org.aspectj.a.b.b;
import org.json.JSONException;
import rx.e;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class LoginActivity extends cn.xiaochuankeji.tieba.ui.base.a implements TextWatcher, i, cn.xiaochuankeji.tieba.ui.auth.a.b.a {
    private static final int[] a = new int[]{R.drawable.login_state_uninput, R.drawable.login_state_input, R.drawable.login_state_password, R.drawable.login_state_error_password};
    private static final int[] b = new int[]{R.drawable.login_background_night1, R.drawable.login_background_night2, R.drawable.login_background_night3, R.drawable.login_background_night4};
    private static final org.aspectj.lang.a.a j = null;
    private String c = "";
    @BindView
    AppCompatTextView cc;
    @BindView
    EditText codeEdit;
    private boolean d = false;
    private boolean e = true;
    private a f = new a(this, 60000);
    @BindView
    TextView forget;
    private cn.xiaochuankeji.tieba.api.account.a g = new cn.xiaochuankeji.tieba.api.account.a();
    private cn.xiaochuankeji.tieba.api.user.a h = new cn.xiaochuankeji.tieba.api.user.a();
    private boolean i = false;
    @BindView
    ImageView imageStateTip;
    @BindView
    View layout_title;
    @BindView
    Button login;
    @BindView
    EditText phoneEdit;
    @BindView
    AppCompatImageView phone_icon;
    @BindView
    View phone_layout;
    @BindView
    AppCompatImageView qq;
    @BindView
    TextView register;
    @BindView
    AppCompatImageView sina;
    @BindView
    AppCompatTextView tip_other_way;
    @BindView
    AppCompatTextView use_phone;
    @BindView
    AppCompatImageView wx;

    private class a extends CountDownTimer {
        final /* synthetic */ LoginActivity a;

        a(LoginActivity loginActivity, long j) {
            this.a = loginActivity;
            super(j, 990);
        }

        public void onTick(long j) {
            this.a.d = true;
            if (this.a.forget != null && !this.a.i) {
                this.a.q();
                this.a.forget.setText(String.format(Locale.SIMPLIFIED_CHINESE, "重新发送(%d)", new Object[]{Long.valueOf((15 + j) / 1000)}));
            }
        }

        public void onFinish() {
            a();
        }

        public void a() {
            this.a.d = false;
            cancel();
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.q();
                    if (!this.a.a.i) {
                        this.a.a.forget.setText("获取验证码");
                    }
                }
            });
        }
    }

    private static void r() {
        b bVar = new b("LoginActivity.java", LoginActivity.class);
        j = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.auth.LoginActivity", "android.os.Bundle", "savedInstanceState", "", "void"), (int) Opcodes.REM_INT_LIT16);
    }

    static void a(Activity activity, String str, int i, int i2) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("_refer", str);
        intent.putExtra("_src", i);
        if (-1 == i2) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, i2);
        }
    }

    static {
        r();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        boolean z = true;
        String trim = this.phoneEdit.getText().toString().trim();
        String trim2 = this.codeEdit.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || !this.d) {
            q();
        }
        if (this.i) {
            if (!(d.a(cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), trim) && d.a(trim2))) {
                z = false;
            }
        } else if (!(d.a(cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), trim) && d.b(trim2))) {
            z = false;
        }
        this.login.setEnabled(z);
    }

    static final void a(LoginActivity loginActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        if (!c.a()) {
            loginActivity.layout_title.setPadding(loginActivity.layout_title.getPaddingLeft(), loginActivity.getResources().getDimensionPixelOffset(R.dimen.status_bar_height), loginActivity.layout_title.getPaddingRight(), 0);
        }
        c.a().a(loginActivity.getIntent());
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(j, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new b(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onResume() {
        super.onResume();
        this.codeEdit.addTextChangedListener(this);
        this.phoneEdit.addTextChangedListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.codeEdit.removeTextChangedListener(this);
        this.phoneEdit.removeTextChangedListener(this);
    }

    protected void onDestroy() {
        this.f.cancel();
        c.a().o();
        super.onDestroy();
        if (cn.xiaochuankeji.tieba.background.a.b != null) {
            cn.xiaochuankeji.tieba.background.a.b.a();
        }
    }

    private int[] j() {
        return c.a.c.e().c() ? b : a;
    }

    protected int a() {
        return R.layout.activity_ac_login_new;
    }

    protected void c() {
        ButterKnife.a((Activity) this);
    }

    protected void i_() {
        k();
        this.cc.setText("+86");
    }

    private void k() {
        if (this.i) {
            this.codeEdit.setInputType(129);
            this.codeEdit.setText("");
            this.codeEdit.setHint("请输入密码");
            this.forget.setText("无法登录?");
            cn.xiaochuankeji.tieba.background.utils.i.a(this.codeEdit, 20);
            this.forget.setCompoundDrawables(null, null, null, null);
            this.use_phone.setText("手机验证码登录");
            this.f.a();
        } else {
            this.codeEdit.setInputType(2);
            this.codeEdit.setText("");
            this.codeEdit.setHint("请输入验证码");
            cn.xiaochuankeji.tieba.background.utils.i.a(this.codeEdit, 8);
            this.forget.setText("获取验证码");
            this.use_phone.setText("密码登录");
            this.f.a();
        }
        if (!this.i) {
            q();
        }
    }

    @OnFocusChange
    public void phoneFocus(boolean z) {
        if (z) {
            this.imageStateTip.setImageResource(j()[1]);
        }
    }

    @OnFocusChange
    public void codeFocus(boolean z) {
        if (z) {
            this.imageStateTip.setImageResource(j()[2]);
        }
    }

    protected void j_() {
        this.codeEdit.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 2) {
                    return false;
                }
                this.a.login.performClick();
                return true;
            }
        });
    }

    @OnClick
    public void login() {
        String trim = this.phoneEdit.getText().toString().trim();
        String trim2 = this.codeEdit.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            g.a("手机号码不能为空");
            this.phoneEdit.performClick();
        } else if (!d.a(cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), trim)) {
            g.a("手机号码格式错误");
            this.phoneEdit.performClick();
        } else if (this.i) {
            if (d.a(trim2)) {
                b(trim, trim2);
                return;
            }
            g.a("密码格式错误");
            this.codeEdit.performClick();
        } else if (d.b(trim2)) {
            a(trim, trim2);
        } else {
            g.a("验证码格式错误");
            this.codeEdit.performClick();
        }
    }

    private void a(String str, String str2) {
        c.a().l();
        if (!cn.xiaochuankeji.tieba.background.a.g().a(this.c, str, str2)) {
            g.b("验证码错误");
            this.f.a();
        } else if (this.e) {
            e();
            this.g.b(str, str2, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText())).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<JSONObject>(this) {
                final /* synthetic */ LoginActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((JSONObject) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.a.login.setEnabled(true);
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                    this.a.f.a();
                    g.a(th == null ? "登录失败" : th.getMessage());
                }

                public void a(JSONObject jSONObject) {
                    this.a.login.setEnabled(true);
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                    this.a.f.a();
                    cn.xiaochuankeji.tieba.background.a.a().edit().putInt("kLoginBySMS", 1).apply();
                    this.a.a(jSONObject, null);
                }
            });
        } else {
            InputPasswordNicknameAvatarActivity.a(this, str, str2, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), 705);
        }
    }

    @OnClick
    public void register() {
        c.a().n();
        InputPhoneNumberActivity.a((Activity) this, 102, "reg");
    }

    @OnClick
    public void phoneLogin() {
        this.i = !this.i;
        k();
        if (this.i) {
            c.a().b();
        } else {
            c.a().c();
        }
    }

    @OnClick
    public void forget() {
        if (this.i) {
            GetAccountTipsFragment.a(getSupportFragmentManager());
            c.a().e();
            return;
        }
        c.a().d();
        String trim = this.phoneEdit.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            g.a("手机号码不能为空");
            this.phoneEdit.performClick();
        } else if (d.a(cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), trim)) {
            a(trim);
        } else {
            g.a("手机格式错误");
        }
    }

    private void a(String str) {
        this.forget.setEnabled(false);
        this.f.start();
        this.g.a(str, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), cn.xiaochuan.jsbridge.data.d.a).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<VerifyJson>(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((VerifyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.f.a();
                g.a(th == null ? "验证码获取失败" : th.getMessage());
            }

            public void a(VerifyJson verifyJson) {
                boolean z = true;
                this.a.c = verifyJson.hash_code;
                LoginActivity loginActivity = this.a;
                if (verifyJson.is_phone_reg != 1) {
                    z = false;
                }
                loginActivity.e = z;
            }
        });
    }

    @OnClick
    public void qqLogin() {
        c.a().h();
        b(1);
    }

    @OnClick
    public void wxLogin() {
        c.a().i();
        b(2);
    }

    @OnClick
    public void sinaLogin() {
        c.a().j();
        b(3);
    }

    @OnClick
    public void back() {
        setResult(0);
        finish();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null && currentFocus.getId() == R.id.code) {
                this.phoneEdit.clearFocus();
                this.codeEdit.clearFocus();
                cn.htjyb.c.a.a((Activity) this);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void b(final String str, final String str2) {
        c.a().k();
        e();
        this.g.c(str, str2, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText())).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<JSONObject>(this) {
            final /* synthetic */ LoginActivity c;

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.c);
                this.c.login.setEnabled(true);
                this.c.a(th, str, str2);
            }

            public void a(JSONObject jSONObject) {
                this.c.login.setEnabled(true);
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.c);
                this.c.a(jSONObject, str2);
            }
        });
        com.izuiyou.a.a.b.b("login with phone number");
    }

    private void b(int i) {
        cn.xiaochuankeji.tieba.background.a.m().a((Activity) this, i, (cn.xiaochuankeji.tieba.ui.auth.a.b.a) this, (i) this);
        com.izuiyou.a.a.b.b("login with socail account");
    }

    private void p() {
        setResult(-1, new Intent());
        finish();
    }

    public void a(final int i, final boolean z, int i2, final String str) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ LoginActivity d;

            public void run() {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.d);
                cn.xiaochuankeji.tieba.background.a.m().a();
                if (z) {
                    String str = null;
                    if (i == 1 || i == 5) {
                        str = "qq_bc";
                    } else if (i == 3) {
                        str = "sina_bc";
                    } else if (i == 2 || i == 4) {
                        str = "wx_bc";
                    }
                    this.d.a(false, str);
                    return;
                }
                if (!this.d.isFinishing()) {
                    this.d.imageStateTip.setImageResource(this.d.j()[3]);
                }
                g.a(str);
            }
        });
    }

    public void a(int i) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        String str = null;
        if (i == 1 || i == 5) {
            str = "qq_bc";
        } else if (i == 3) {
            str = "sina_bc";
        } else if (i == 2 || i == 4) {
            str = "wx_bc";
        }
        a(true, str);
    }

    private void a(boolean z, String str) {
        cn.xiaochuankeji.tieba.background.utils.c.a.c().d();
        c.a().a(z, str);
        if (z) {
            if (a.a()) {
                a.a(this, IMediaPlayer.MEDIA_INFO_BUFFERING_END);
                return;
            }
            GrayConfigBean C = cn.xiaochuankeji.tieba.background.utils.c.a.c().C();
            if (C != null && C.accountModifyAb == 1) {
                InputPasswordNicknameAvatarActivity.a(this, "", "", cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), 701);
            }
            p();
        } else if (a.a()) {
            a.a(this, IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH);
            setResult(-1);
            finish();
        } else {
            p();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == IMediaPlayer.MEDIA_INFO_BUFFERING_END) {
            GrayConfigBean C = cn.xiaochuankeji.tieba.background.utils.c.a.c().C();
            if (C != null && C.accountModifyAb == 1) {
                InputPasswordNicknameAvatarActivity.a(this, "", "", cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), 701);
            }
            p();
        } else if (i2 != -1) {
        } else {
            if (i == 705) {
                p();
            } else if (i == 102) {
                p();
            } else if (i == 103) {
                p();
            } else if (i == IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH) {
                p();
            } else if (i == 701) {
                p();
            } else if (i == 704) {
                CharSequence stringExtra = intent.getStringExtra("kRegionCode");
                if (!TextUtils.isEmpty(stringExtra)) {
                    this.cc.setText(stringExtra);
                }
            } else if (i == 114) {
                p();
            }
        }
    }

    public void e() {
        this.login.setEnabled(false);
        cn.htjyb.c.a.a((Activity) this);
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, "登录中...");
    }

    private void a(JSONObject jSONObject, String str) {
        boolean z = false;
        String string = jSONObject.getString("token");
        if (TextUtils.isEmpty(string)) {
            a(-1, false, 0, "获取token失败");
            return;
        }
        long longValue = jSONObject.getLong("mid").longValue();
        if (longValue <= 0) {
            a(-1, false, 0, "解析数据失败");
            return;
        }
        int intValue = jSONObject.getIntValue("register");
        cn.xiaochuankeji.tieba.background.modules.a.b i = cn.xiaochuankeji.tieba.background.a.i();
        i.a(true);
        i.a(longValue);
        i.a(false, false);
        if (TextUtils.isEmpty(str)) {
            i.a(jSONObject.getString("passwd"));
        } else {
            i.a(d.e(str));
        }
        try {
            i.a(new org.json.JSONObject(jSONObject.toJSONString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        i.b(string);
        i.t();
        i.u();
        cn.xiaochuankeji.tieba.background.utils.c.a.c().d();
        this.h.a().d(new rx.b.g<SettingJson, Boolean>(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((SettingJson) obj);
            }

            public Boolean a(SettingJson settingJson) {
                boolean z;
                boolean z2 = false;
                Editor putBoolean = cn.xiaochuankeji.tieba.background.a.a().edit().putBoolean("key_recommend_notification", settingJson.good == 1);
                String str = "key_comment_notification";
                if (settingJson.review == 1) {
                    z = true;
                } else {
                    z = false;
                }
                Editor putBoolean2 = putBoolean.putBoolean(str, z);
                String str2 = "kChatMsgNotification";
                if (settingJson.msg == 1) {
                    z2 = true;
                }
                putBoolean2.putBoolean(str2, z2).apply();
                return Boolean.valueOf(true);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Boolean>(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Boolean bool) {
            }
        });
        c a = c.a();
        if (intValue == 1) {
            z = true;
        }
        a.a(z, TextUtils.isEmpty(str) ? "sms_login_bc" : "pwd_login_bc");
        if (intValue == 1) {
            InputPasswordNicknameAvatarActivity.a(this, "", "", cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), 701);
            p();
            return;
        }
        p();
    }

    private void q() {
        int i = 1;
        TextView textView = this.forget;
        boolean z = this.i || !this.d;
        textView.setEnabled(z);
        if (TextUtils.isEmpty(this.phoneEdit.getText()) || this.d) {
            i = 0;
        }
        c.a.d.a.a a = c.a.d.a.a.a();
        int i2 = (this.i || i != 0) ? R.color.CM : R.color.CT_5;
        this.forget.setTextColor(a.a(i2));
        i2 = this.i ? 0 : i != 0 ? R.drawable.ic_arrow : R.drawable.ic_arrow_right;
        c.a.b.a(this.forget, 0, 0, i2, 0);
    }

    @OnClick
    public void openRegion() {
        c.a().m();
        startActivityForResult(new Intent(this, RegionPicker.class), 704);
    }

    private void a(Throwable th, final String str, final String str2) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        if ((th instanceof ClientErrorException) && ((ClientErrorException) th).errCode() == -320) {
            String str3 = "此手机号已被使用,请更换手机号";
            str3 = "亲爱的小柚子,小右发现当前用于登录的手机号已被其他账号占用,请更换手机号";
            Bundle bundle = new Bundle();
            bundle.putString("td_title", "此手机号已被使用,请更换手机号");
            bundle.putString("td_content", "亲爱的小柚子,小右发现当前用于登录的手机号已被其他账号占用,请更换手机号");
            bundle.putString("td_left", "放弃");
            bundle.putString("td_right", "更换手机号");
            TipsDialog.a(getSupportFragmentManager(), bundle).a(new OnClickListener(this) {
                final /* synthetic */ LoginActivity c;

                public void onClick(View view) {
                    Intent intent = new Intent(this.c, InputPhoneNumberActivity.class);
                    intent.putExtra("VerifyCodeType", "rebind");
                    intent.putExtra("kPhone", str);
                    intent.putExtra("kPass_w", str2);
                    intent.putExtra("kRegionCode", cn.xiaochuankeji.tieba.api.account.a.a(this.c.cc.getText()));
                    this.c.startActivityForResult(intent, 114);
                    h.a("zy_event_account", "tag6");
                }
            });
            h.a("zy_event_account", "tag5");
            return;
        }
        g.a(th == null ? "登录失败,请重试" : th.getMessage());
    }
}
