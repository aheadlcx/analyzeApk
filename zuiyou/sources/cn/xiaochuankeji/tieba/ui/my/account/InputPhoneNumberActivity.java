package cn.xiaochuankeji.tieba.ui.my.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.jsbridge.data.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.account.VerifyJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.picker.RegionPicker;
import cn.xiaochuankeji.tieba.ui.utils.TipsDialog;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.alibaba.fastjson.JSONObject;
import java.util.Locale;
import org.json.JSONException;
import rx.b.g;
import rx.e;

public class InputPhoneNumberActivity extends cn.xiaochuankeji.tieba.ui.base.a implements TextWatcher, OnClickListener {
    private static String c = "";
    cn.xiaochuankeji.tieba.api.account.a a = new cn.xiaochuankeji.tieba.api.account.a();
    private String b;
    @BindView
    AppCompatTextView bind_tips;
    @BindView
    Button bnNext;
    @BindView
    AppCompatTextView cc;
    @BindView
    AppCompatImageView clearPhoneView;
    @BindView
    TextView codeActionText;
    @BindView
    EditText codeEdit;
    private boolean d = false;
    private b e = new b(60000);
    @BindView
    TextView errorTipText;
    private g<JSONObject, Boolean> f = new g<JSONObject, Boolean>(this) {
        final /* synthetic */ InputPhoneNumberActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ Object call(Object obj) {
            return a((JSONObject) obj);
        }

        public Boolean a(JSONObject jSONObject) {
            long optLong;
            JSONException e;
            String string;
            long longValue = jSONObject.getLong("mid").longValue();
            cn.xiaochuankeji.tieba.background.modules.a.b i = cn.xiaochuankeji.tieba.background.a.i();
            try {
                org.json.JSONObject jSONObject2 = new org.json.JSONObject(jSONObject.toJSONString());
                if (longValue == 0) {
                    optLong = jSONObject2.optLong("id", longValue);
                } else {
                    optLong = longValue;
                }
                try {
                    i.a(jSONObject2);
                    Object string2 = jSONObject.getString("phone");
                    if (!TextUtils.isEmpty(string2)) {
                        i.q().setPhone(string2);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    i.a(false, false);
                    i.a(true);
                    i.a(optLong);
                    string = jSONObject.getString("token");
                    if (!TextUtils.isEmpty(string)) {
                        i.b(string);
                    }
                    i.t();
                    i.u();
                    return Boolean.valueOf(true);
                }
            } catch (JSONException e3) {
                JSONException jSONException = e3;
                optLong = longValue;
                e = jSONException;
                e.printStackTrace();
                i.a(false, false);
                i.a(true);
                i.a(optLong);
                string = jSONObject.getString("token");
                if (TextUtils.isEmpty(string)) {
                    i.b(string);
                }
                i.t();
                i.u();
                return Boolean.valueOf(true);
            }
            i.a(false, false);
            i.a(true);
            i.a(optLong);
            string = jSONObject.getString("token");
            if (TextUtils.isEmpty(string)) {
                i.b(string);
            }
            i.t();
            i.u();
            return Boolean.valueOf(true);
        }
    };
    @BindView
    EditText phoneEdit;
    @BindView
    View phone_layout;
    @BindView
    AppCompatTextView tips;
    @BindView
    TextView title;
    @BindView
    TextView tvIntro;

    private class a extends ClickableSpan {
        final /* synthetic */ InputPhoneNumberActivity a;

        private a(InputPhoneNumberActivity inputPhoneNumberActivity) {
            this.a = inputPhoneNumberActivity;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.tvIntro) {
                WebActivity.a(this.a, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/private.html")));
            } else if (view.getId() == R.id.code_action) {
                this.a.e();
            }
        }

        public void updateDrawState(TextPaint textPaint) {
        }
    }

    private class b extends CountDownTimer {
        final /* synthetic */ InputPhoneNumberActivity a;

        private b(InputPhoneNumberActivity inputPhoneNumberActivity, long j) {
            this.a = inputPhoneNumberActivity;
            super(j, 990);
        }

        public void onTick(long j) {
            this.a.d = true;
            if (this.a.codeActionText != null) {
                this.a.codeActionText.setText(String.format(Locale.SIMPLIFIED_CHINESE, "重新发送(%d)", new Object[]{Long.valueOf((15 + j) / 1000)}));
                this.a.p();
            }
        }

        public void onFinish() {
            this.a.d = false;
            if (this.a.codeActionText != null) {
                this.a.k();
            }
        }

        public void a() {
            this.a.d = false;
            cancel();
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.k();
                }
            });
        }
    }

    public static void a(Activity activity, int i, String str) {
        Intent intent = new Intent(activity, InputPhoneNumberActivity.class);
        intent.putExtra("VerifyCodeType", str);
        activity.startActivityForResult(intent, i);
    }

    protected int a() {
        return R.layout.activity_ac_register;
    }

    protected boolean a(Bundle bundle) {
        this.b = getIntent().getStringExtra("VerifyCodeType");
        return true;
    }

    @OnClick
    public void back() {
        cn.htjyb.c.a.a((Activity) this);
        setResult(0);
        onBackPressed();
    }

    protected void c() {
        ButterKnife.a((Activity) this);
        this.cc.setText("+86");
        if ("reg".equals(this.b)) {
            this.title.setText("注册");
            this.bind_tips.setVisibility(8);
        } else if ("pwd".equals(this.b)) {
            this.title.setText("找回密码");
            this.bind_tips.setVisibility(8);
        } else if ("bind".equals(this.b)) {
            this.title.setText("更换手机号码");
            this.bind_tips.setVisibility(0);
        } else if ("certify".equals(this.b)) {
            this.title.setText("实名认证");
            this.tips.setVisibility(0);
            this.bind_tips.setVisibility(0);
        } else if (d.a.equals(this.b)) {
            this.title.setText("手机验证码登录");
            this.bind_tips.setVisibility(8);
        } else if ("rebind".equals(this.b)) {
            this.title.setText("更换手机号");
            this.bind_tips.setVisibility(0);
        }
    }

    protected void i_() {
        if ("reg".equals(this.b)) {
            Object obj = "注册即表示同意《隐私权声明》";
            CharSequence spannableString = new SpannableString(obj);
            spannableString.setSpan(new a(), 7, obj.length(), 33);
            this.tvIntro.setText(spannableString);
            this.tvIntro.setMovementMethod(LinkMovementMethod.getInstance());
            this.tvIntro.setVisibility(0);
        } else {
            this.tvIntro.setVisibility(8);
        }
        this.e.a();
        p();
    }

    protected void j_() {
        this.clearPhoneView.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        this.phoneEdit.addTextChangedListener(this);
        this.codeEdit.addTextChangedListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.phoneEdit.removeTextChangedListener(this);
        this.codeEdit.removeTextChangedListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_clear_phone:
                this.phoneEdit.getText().clear();
                return;
            default:
                return;
        }
    }

    @OnClick
    public void commit() {
        String obj = this.phoneEdit.getText().toString();
        String trim = this.codeEdit.getText().toString().trim();
        if (TextUtils.isEmpty(c)) {
            cn.xiaochuankeji.tieba.background.utils.g.b("请您先获取验证码");
        } else if (!cn.xiaochuankeji.tieba.background.a.g().a(c, obj, trim)) {
            cn.xiaochuankeji.tieba.background.utils.g.b("验证码错误");
            this.e.a();
        } else if ("reg".equals(this.b)) {
            InputPasswordNicknameAvatarActivity.a(this, obj, trim, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), 102);
        } else if ("pwd".equals(this.b)) {
            SetPasswordActivity.a(this, obj, trim, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), 101);
        } else if ("bind".equals(this.b)) {
            a(obj, trim);
        } else if (d.a.equals(this.b)) {
            SetPasswordActivity.a(this, obj, trim, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), 113);
        } else if ("certify".equals(this.b)) {
            a(obj, trim);
        } else if ("rebind".equals(this.b)) {
            b(obj, trim);
        }
    }

    private void a(final String str, final String str2) {
        final boolean equals = "bind".equals(this.b);
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, equals ? "修改中..." : "绑定中...");
        this.a.a(str, str2, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText())).d(this.f).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Boolean>(this) {
            final /* synthetic */ InputPhoneNumberActivity d;

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.d);
                this.d.a(th, str, str2, equals);
            }

            public void a(Boolean bool) {
                cn.xiaochuankeji.tieba.background.utils.g.a(equals ? "修改成功" : "绑定成功,可以使用该手机号进行登录了");
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.d);
                this.d.setResult(-1);
                this.d.finish();
            }
        });
    }

    private void b(final String str, final String str2) {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, "绑定中...");
        String stringExtra = getIntent().getStringExtra("kPhone");
        String stringExtra2 = getIntent().getStringExtra("kPass_w");
        int intExtra = getIntent().getIntExtra("kRegionCode", -1);
        this.a.a(stringExtra, stringExtra2, intExtra, str, str2, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText())).d(this.f).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Boolean>(this) {
            final /* synthetic */ InputPhoneNumberActivity c;

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.c);
                this.c.a(th, str, str2, false);
            }

            public void a(Boolean bool) {
                cn.xiaochuankeji.tieba.background.utils.g.a("绑定成功,可以使用该手机号进行登录了");
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.c);
                this.c.setResult(-1);
                this.c.finish();
            }
        });
    }

    private void e() {
        this.codeActionText.setEnabled(false);
        String j = j();
        if (cn.htjyb.c.d.a(cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), j)) {
            this.e.start();
            this.a.a(j, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), this.b).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<VerifyJson>(this) {
                final /* synthetic */ InputPhoneNumberActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((VerifyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.a.e.a();
                    cn.xiaochuankeji.tieba.background.utils.g.a(th == null ? "验证码获取失败" : th.getMessage());
                }

                public void a(VerifyJson verifyJson) {
                    InputPhoneNumberActivity.c = verifyJson.hash_code;
                }
            });
            return;
        }
        this.codeActionText.setEnabled(true);
        cn.xiaochuankeji.tieba.background.utils.g.a("手机格式错误");
    }

    private String j() {
        return this.phoneEdit.getText().toString().trim();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 102) {
            setResult(-1);
            finish();
        } else if (i == 104) {
            CharSequence stringExtra = intent.getStringExtra("kRegionCode");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.cc.setText(stringExtra);
            }
        } else {
            setResult(-1);
            finish();
        }
    }

    protected void onDestroy() {
        this.e.cancel();
        super.onDestroy();
    }

    private void k() {
        CharSequence spannableString = new SpannableString("获取验证码");
        spannableString.setSpan(new a(), 0, 5, 33);
        this.codeActionText.setText(spannableString);
        this.codeActionText.setMovementMethod(LinkMovementMethod.getInstance());
        p();
    }

    private void a(Throwable th, final String str, final String str2, boolean z) {
        String str3;
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        if ((th instanceof ClientErrorException) && ((ClientErrorException) th).errCode() == -4) {
            org.json.JSONObject errData = ((ClientErrorException) th).errData();
            if (errData != null) {
                String optString = errData.optString("name");
                final int optInt = errData.optInt("reg", -1);
                if ((optInt == 0 || optInt == 1) && !TextUtils.isEmpty(optString)) {
                    String str4 = optInt == 1 ? "此手机号已被账号[" + optString + "]使用" : "此手机号已绑定到账号[" + optString + "]";
                    str3 = optInt == 1 ? "确认绑定后，你将无法再通过此手机号登录[" + optString + "],同时[" + optString + "]将被废弃，可能无法找回" : "确认绑定后,你将无法再通过此手机号登录[" + optString + "]";
                    Bundle bundle = new Bundle();
                    bundle.putString("td_title", str4);
                    bundle.putString("td_content", str3);
                    bundle.putString("td_left", "放弃");
                    bundle.putString("td_right", "rebind".equals(this.b) ? "确认更换" : "确认绑定");
                    TipsDialog.a(getSupportFragmentManager(), bundle).a(new OnClickListener(this) {
                        final /* synthetic */ InputPhoneNumberActivity d;

                        public void onClick(View view) {
                            h.a("zy_event_account", optInt == 1 ? "tag2" : "tag4");
                            if ("rebind".equalsIgnoreCase(this.d.b)) {
                                this.d.c(str, str2);
                            } else {
                                this.d.d(str, str2);
                            }
                        }
                    });
                    h.a("zy_event_account", optInt == 1 ? "tag1" : "tag3");
                    return;
                }
            }
        }
        str3 = th == null ? z ? "修改出错" : "绑定失败,请重试" : th.getMessage();
        cn.xiaochuankeji.tieba.background.utils.g.a(str3);
    }

    private void c(String str, String str2) {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, "绑定中...");
        String stringExtra = getIntent().getStringExtra("kPhone");
        String stringExtra2 = getIntent().getStringExtra("kPass_w");
        int intExtra = getIntent().getIntExtra("kRegionCode", -1);
        this.a.b(stringExtra, stringExtra2, intExtra, str, str2, cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText())).d(this.f).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Boolean>(this) {
            final /* synthetic */ InputPhoneNumberActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                cn.xiaochuankeji.tieba.background.utils.g.a(th == null ? "绑定出错,请重试" : th.getMessage());
            }

            public void a(Boolean bool) {
                cn.xiaochuankeji.tieba.background.utils.g.a("绑定成功,可以使用该手机号进行登录了");
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                this.a.setResult(-1);
                this.a.finish();
            }
        });
    }

    private void d(String str, String str2) {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        this.a.a(str, str2).d(this.f).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Boolean>(this) {
            final /* synthetic */ InputPhoneNumberActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                cn.xiaochuankeji.tieba.background.utils.g.a(th == null ? "绑定出错,请重试" : th.getMessage());
            }

            public void a(Boolean bool) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                this.a.setResult(-1);
                this.a.finish();
            }
        });
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        boolean z = false;
        String trim = this.phoneEdit.getText().toString().trim();
        String trim2 = this.codeEdit.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.clearPhoneView.setVisibility(8);
            p();
        } else {
            this.clearPhoneView.setVisibility(0);
            this.errorTipText.setText("");
            if (!this.d) {
                p();
            }
        }
        if (!TextUtils.isEmpty(trim2)) {
            this.errorTipText.setText("");
        }
        if (cn.htjyb.c.d.a(cn.xiaochuankeji.tieba.api.account.a.a(this.cc.getText()), trim) && cn.htjyb.c.d.b(trim2)) {
            z = true;
        }
        this.bnNext.setEnabled(z);
    }

    private void p() {
        int i = 1;
        this.codeActionText.setEnabled(!this.d);
        if (TextUtils.isEmpty(this.phoneEdit.getText()) || this.d) {
            i = 0;
        }
        this.codeActionText.setTextColor(c.a.d.a.a.a().a(i != 0 ? R.color.CM : R.color.CT_5));
        c.a.b.a(this.codeActionText, 0, 0, i != 0 ? R.drawable.ic_arrow : R.drawable.ic_arrow_right, 0);
    }

    @OnClick
    public void openRegion() {
        startActivityForResult(new Intent(this, RegionPicker.class), 104);
    }
}
