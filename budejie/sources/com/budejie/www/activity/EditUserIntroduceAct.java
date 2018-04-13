package com.budejie.www.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.tencent.open.SocialConstants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class EditUserIntroduceAct extends BaseTitleActivity {
    String a = "EditUserIntroduceAct";
    String b;
    String c;
    m d;
    SharedPreferences e;
    private int i = 100;
    private boolean j = false;
    private Toast k;
    private EditText l;
    private TextView m;
    private j n;
    private TextWatcher o = new TextWatcher(this) {
        final /* synthetic */ EditUserIntroduceAct a;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(this.a.l.getText())) {
                this.a.m.setText(String.valueOf(this.a.i));
                return;
            }
            this.a.m.setText(String.valueOf(this.a.i - this.a.l.getText().length()));
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    private a p = new a(this) {
        final /* synthetic */ EditUserIntroduceAct a;

        {
            this.a = r1;
        }

        public void onStart() {
            this.a.e();
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.j = false;
            this.a.f();
            this.a.k = an.a(this.a, str, -1);
            this.a.k.show();
        }

        public void onSuccess(Object obj) {
            this.a.j = false;
            if (obj != null) {
                this.a.f();
                String obj2 = obj.toString();
                Log.e(com.alipay.sdk.util.j.c, obj2);
                try {
                    JSONObject jSONObject = new JSONObject(obj2);
                    if (jSONObject != null) {
                        obj2 = jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                        if (obj2 != null && obj2.equals("0")) {
                            this.a.d.a("introduction", this.a.c, this.a.b);
                            this.a.setResult(726);
                            this.a.finish();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.edit_instroduce_layout);
        this.n = new j();
        a("修改简介");
        b("取消");
        b(false);
        c("完成");
        f(0);
        a();
    }

    private void a() {
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "instroduce_text_length");
        if (!TextUtils.isEmpty(configParams)) {
            try {
                this.i = Integer.parseInt(configParams);
            } catch (NumberFormatException e) {
                this.i = 100;
            }
        }
        this.l = (EditText) findViewById(R.id.edit_instroduce);
        this.m = (TextView) findViewById(R.id.text_tips);
        this.l.setFilters(new InputFilter[]{new LengthFilter(this.i)});
        this.l.addTextChangedListener(this.o);
        String stringExtra = getIntent().getStringExtra(SocialConstants.PARAM_APP_DESC);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.l.setText(stringExtra);
            this.l.setSelection(stringExtra.length());
            this.m.setText(String.valueOf(this.i - stringExtra.length()));
        }
        this.d = new m(this);
        this.e = getSharedPreferences("weiboprefer", 0);
        this.b = this.e.getString("id", "");
        this.l.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ EditUserIntroduceAct a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (66 == i && keyEvent.getAction() == 0) {
                    return true;
                }
                return false;
            }
        });
    }

    public void onRightClick(View view) {
        if (!this.j) {
            this.c = this.l.getText().toString();
            if (TextUtils.isEmpty(this.c)) {
                Toast.makeText(this, "内容不能为空", 0).show();
                return;
            }
            this.c = this.c.replace("\n", "");
            b();
        }
    }

    private void b() {
        this.j = true;
        if (an.a((Context) this)) {
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.n.c((Context) this, this.c), this.p);
            return;
        }
        Toast.makeText(this, getString(R.string.nonet), 0).show();
    }
}
