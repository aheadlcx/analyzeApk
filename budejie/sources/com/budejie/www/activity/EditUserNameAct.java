package com.budejie.www.activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.bean.UserItem;
import com.budejie.www.c.m;
import com.budejie.www.f.a;
import com.budejie.www.util.an;
import com.budejie.www.util.k;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class EditUserNameAct extends SensorBaseActivity implements OnClickListener, a {
    String a = "EditUserNameAct";
    EditUserNameAct b;
    Button c;
    Button d;
    TextView e;
    LinearLayout f;
    RelativeLayout g;
    TextView h;
    EditText i;
    Toast j;
    String k;
    String l;
    Dialog m;
    m n;
    com.budejie.www.http.a o;
    SharedPreferences p;
    Handler q = new Handler(this) {
        final /* synthetic */ EditUserNameAct a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            String str;
            HashMap l;
            if (i == 957) {
                str = (String) message.obj;
                this.a.m.dismiss();
                l = z.l(str);
                if (l.isEmpty()) {
                    this.a.j = an.a(this.a.b, this.a.b.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if ("0".equals(l.get(j.c))) {
                    this.a.j = an.a(this.a.b, this.a.b.getString(R.string.person_edit_data_success), -1);
                    this.a.j.show();
                    this.a.n.a("name", this.a.l, this.a.k);
                    UserItem g = an.g(this.a.b);
                    if (g != null) {
                        com.d.a.a(g.getProfile(), g.getName());
                    }
                } else {
                    this.a.j = an.a(this.a.b, (String) l.get("msg"), -1);
                    this.a.j.show();
                }
                this.a.b.setResult(718);
                this.a.b.finish();
            } else if (i == 958 || i == 1958) {
                this.a.j = an.a(this.a.b, this.a.b.getString(R.string.person_edit_data_faild), -1);
                this.a.j.show();
            } else if (i == 1991) {
                str = (String) message.obj;
                this.a.m.dismiss();
                l = z.l(str);
                if (l.isEmpty()) {
                    this.a.j = an.a(this.a.b, this.a.b.getString(R.string.person_edit_data_faild), -1);
                    this.a.j.show();
                } else if ("0".equals(l.get(j.c))) {
                    this.a.j = an.a(this.a.b, this.a.b.getString(R.string.person_edit_data_success), -1);
                    this.a.j.show();
                    this.a.n.a("qq", this.a.i.getText().toString(), this.a.k);
                } else {
                    this.a.j = an.a(this.a.b, (String) l.get("msg"), -1);
                    this.a.j.show();
                }
                this.a.b.setResult(55);
                this.a.b.finish();
            }
        }
    };
    private RelativeLayout r;
    private Button s;
    private TextView t;
    private String u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_editname);
        this.b = this;
        this.u = getIntent().getStringExtra("page_type");
        a();
    }

    public void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.c = (Button) findViewById(R.id.refresh_btn);
        this.c.setVisibility(8);
        this.d = (Button) findViewById(R.id.title_left_btn);
        this.d.setVisibility(0);
        this.e = (TextView) findViewById(R.id.title_right_btn);
        this.e.setVisibility(0);
        this.e.setText("完成");
        this.f = (LinearLayout) findViewById(R.id.left_layout);
        this.g = (RelativeLayout) findViewById(R.id.title_refresh_layout);
        this.h = (TextView) findViewById(R.id.title_center_txt);
        this.i = (EditText) findViewById(R.id.edit_name_edit);
        this.r = (RelativeLayout) findViewById(R.id.title);
        this.s = (Button) findViewById(R.id.person_name_cancel_btn);
        this.t = (TextView) findViewById(R.id.default_text);
        this.f.setVisibility(0);
        this.g.setVisibility(0);
        this.d.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.g.setOnClickListener(this);
        Object stringExtra = getIntent().getStringExtra("name");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.i.setText(stringExtra);
            this.i.setSelection(stringExtra.length());
        }
        if (Constants.SOURCE_QQ.equals(this.u)) {
            this.h.setText(R.string.person_edit_qq);
            this.i.setInputType(2);
            this.i.setHint("请输入QQ");
            this.t.setText("填写QQ方便有问题时与您联系");
        } else {
            this.h.setText(R.string.person_edit_name);
        }
        this.o = new com.budejie.www.http.a(this, this);
        this.m = new Dialog(this, R.style.dialogTheme);
        this.m.setContentView(R.layout.loaddialog);
        this.n = new m(this);
        this.p = getSharedPreferences("weiboprefer", 0);
    }

    public boolean a(String str) {
        return Pattern.compile("^[a-zA-Z0-9_一-龥-]+$").matcher(str).find();
    }

    public void editDelName$Click(View view) {
        this.i.setText("");
    }

    public void onClick(View view) {
        if (view == this.d || view == this.f) {
            finish();
        } else if (view != this.e && view != this.g) {
        } else {
            if (Constants.SOURCE_QQ.equals(this.u)) {
                if (TextUtils.isEmpty(this.i.getText().toString())) {
                    Toast.makeText(this, "QQ不能为空", 0).show();
                } else if (b(this.i.getText().toString())) {
                    this.m.show();
                    this.k = this.p.getString("id", "");
                    try {
                        this.o.a(this.k, "", "", "", "", "", this.i.getText().toString(), 1999);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "QQ必须为数字组合", 0).show();
                }
            } else if (TextUtils.isEmpty(this.i.getText())) {
                Toast.makeText(this, "姓名不能为空", 0).show();
            } else {
                int length;
                this.l = this.i.getText().toString().trim();
                try {
                    length = this.l.getBytes("GBK").length;
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                    length = 0;
                }
                if (length < 4 || length > 24) {
                    Toast.makeText(this, "姓名长度应在4-24个字符之间", 0).show();
                } else if (a(this.l)) {
                    this.m.show();
                    this.k = this.p.getString("id", "");
                    try {
                        this.o.a(this.k, "", this.l, "", "", "", "", 956);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "姓名不符合标准", 0).show();
                }
            }
        }
    }

    public boolean b(String str) {
        if (Pattern.compile("[0-9]*").matcher(str).matches()) {
            return true;
        }
        return false;
    }

    public void a(int i, String str) {
        if (i == 956) {
            this.q.sendMessage(this.q.obtainMessage(957, str));
        }
        if (i == 1999) {
            this.q.sendMessage(this.q.obtainMessage(1991, str));
        }
    }

    public void a(int i) {
        if (i == 956) {
            this.q.sendEmptyMessage(958);
        }
        if (i == 1999) {
            this.q.sendEmptyMessage(1958);
        }
    }

    public void onrefreshTheme() {
        this.r.setBackgroundResource(com.budejie.www.util.j.a);
        this.h.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        k.a(this.i, com.budejie.www.util.j.P);
        this.i.setTextColor(getResources().getColor(com.budejie.www.util.j.O));
        this.s.setBackgroundResource(com.budejie.www.util.j.Q);
        onRefreshTitleFontTheme(this.e, false);
        onRefreshTitleFontTheme(this.d, true);
    }
}
