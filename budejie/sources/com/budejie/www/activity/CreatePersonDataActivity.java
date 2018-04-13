package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.alipay.sdk.util.j;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.phonenumber.PhoneNumBindingActivity;
import com.budejie.www.bean.UserItem;
import com.budejie.www.f.a;
import com.budejie.www.http.m;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.budejie.www.util.p.c;
import com.budejie.www.util.z;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class CreatePersonDataActivity extends OauthWeiboBaseAct implements OnClickListener, a {
    private Button A;
    private LinearLayout B;
    private TextView C;
    private RelativeLayout D;
    private Button E;
    private TextView F;
    private String G;
    private InputMethodManager H;
    private RelativeLayout I;
    private String J = "";
    private String K = "";
    private ArrayList<NameValuePair> L;
    Dialog a;
    SharedPreferences b;
    HashMap<String, String> c;
    Handler d = new Handler(this) {
        final /* synthetic */ CreatePersonDataActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT) {
                this.a.b();
                HashMap k = z.k((String) message.obj);
                if (k.get("result_desc") == null || TextUtils.isEmpty((CharSequence) k.get("result_desc"))) {
                    this.a.f = an.a(this.a.e, this.a.e.getString(R.string.person_edit_data_faild), -1);
                    this.a.f.show();
                } else if ("0".equals(k.get(j.c))) {
                    this.a.z.a("portrait", (String) k.get("url"), this.a.x);
                    UserItem g = an.g(this.a.e);
                    if (g != null) {
                        com.d.a.a(g.getProfile(), g.getName());
                    }
                    this.a.f();
                } else {
                    this.a.f = an.a(this.a.e, (String) k.get("result_desc"), -1);
                    this.a.f.show();
                }
            }
        }
    };
    private Activity e;
    private Toast f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private AsyncImageView j;
    private EditText k;
    private RadioGroup l;
    private RadioButton m;
    private RadioButton n;
    private String o;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private String v;
    private com.budejie.www.http.a w;
    private String x;
    private m y;
    private com.budejie.www.c.m z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.create_person_data_layout);
        this.e = this;
        this.z = new com.budejie.www.c.m(this);
        this.y = new m();
        this.w = new com.budejie.www.http.a(this.e, this);
        this.G = getIntent().getStringExtra("source");
        this.b = getSharedPreferences("weiboprefer", 0);
        this.x = this.b.getString("id", "");
        a();
        e();
    }

    private void e() {
        if ("third_party".equals(this.G)) {
            this.c = (HashMap) getIntent().getSerializableExtra("user_info");
            if (this.c != null) {
                if (!TextUtils.isEmpty((CharSequence) this.c.get(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY))) {
                    this.j.setAsyncCacheImage((String) this.c.get(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY), R.drawable.set_icon);
                    this.p = true;
                }
                this.k.setText((CharSequence) this.c.get(HistoryOpenHelper.COLUMN_USERNAME));
                this.k.setSelection(((String) this.c.get(HistoryOpenHelper.COLUMN_USERNAME)).length());
                if ("m".equals(this.c.get("sex"))) {
                    this.l.check(this.m.getId());
                } else if ("f".equals(this.c.get("sex"))) {
                    this.l.check(this.n.getId());
                }
            }
        }
    }

    public void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.I = (RelativeLayout) findViewById(R.id.click_hide_input);
        this.I.setOnClickListener(this);
        this.B = (LinearLayout) findViewById(R.id.left_layout);
        this.B.setVisibility(4);
        this.A = (Button) findViewById(R.id.title_left_btn);
        this.A.setVisibility(4);
        this.C = (TextView) findViewById(R.id.title_center_txt);
        this.C.setText("创建个人资料");
        this.D = (RelativeLayout) findViewById(R.id.title_refresh_layout);
        this.D.setVisibility(0);
        this.D.setOnClickListener(this);
        this.E = (Button) findViewById(R.id.refresh_btn);
        this.E.setVisibility(4);
        this.F = (TextView) findViewById(R.id.title_right_btn);
        this.F.setVisibility(0);
        this.F.setText("下次再说");
        this.F.setTextSize(15.0f);
        this.F.setOnClickListener(this);
        this.i = (ImageView) findViewById(R.id.next);
        this.i.setClickable(false);
        this.g = (ImageView) findViewById(R.id.iv_left_line);
        this.h = (ImageView) findViewById(R.id.iv_right_line);
        if ("third_party".equals(this.G)) {
            this.g.setVisibility(8);
            this.h.setVisibility(0);
            this.i.setBackgroundResource(R.drawable.default_next_button);
        } else if ("phone_register".equals(this.G)) {
            this.g.setVisibility(0);
            this.h.setVisibility(8);
            this.i.setBackgroundResource(R.drawable.default_finish_button);
        }
        this.j = (AsyncImageView) findViewById(R.id.set_icon);
        this.k = (EditText) findViewById(R.id.set_username);
        this.k.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ CreatePersonDataActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    this.a.k.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color));
                } else {
                    this.a.k.setHintTextColor(this.a.getResources().getColor(R.color.login_input_text_color_no_focus));
                }
            }
        });
        this.k.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ CreatePersonDataActivity a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(this.a.k.getText().toString())) {
                    this.a.q = false;
                    this.a.a(false);
                    return;
                }
                this.a.q = true;
                if (this.a.p && this.a.q && this.a.r) {
                    this.a.a(true);
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.l = (RadioGroup) findViewById(R.id.selector_sex);
        this.m = (RadioButton) findViewById(R.id.set_man);
        this.n = (RadioButton) findViewById(R.id.set_woman);
        this.l.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ CreatePersonDataActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == this.a.m.getId()) {
                    this.a.v = "男";
                    this.a.r = true;
                } else if (i == this.a.n.getId()) {
                    this.a.v = "女";
                    this.a.r = true;
                }
                if (this.a.p && this.a.q && this.a.r) {
                    this.a.a(true);
                }
            }
        });
    }

    private void a(boolean z) {
        if ("third_party".equals(this.G)) {
            if (z) {
                this.i.setBackgroundResource(R.drawable.next_button);
            } else {
                this.i.setBackgroundResource(R.drawable.default_next_button);
            }
        } else if ("phone_register".equals(this.G)) {
            if (z) {
                this.i.setBackgroundResource(R.drawable.finish_button);
            } else {
                this.i.setBackgroundResource(R.drawable.default_finish_button);
            }
        }
        if (z) {
            this.i.setClickable(true);
        } else {
            this.i.setClickable(false);
        }
    }

    public void onClick(View view) {
        if (view == this.B) {
            finish();
        } else if (view == this.F) {
            if ("third_party".equals(this.G)) {
                Intent intent = new Intent(this.e, PhoneNumBindingActivity.class);
                intent.putExtra("source", this.G);
                intent.putExtra("is_skip", true);
                this.e.startActivityForResult(intent, 5050);
            } else if ("phone_register".equals(this.G)) {
                setResult(-1);
                finish();
            }
        } else if (view != this.I) {
        } else {
            if (this.H != null) {
                this.H.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return;
            }
            this.H = (InputMethodManager) getSystemService("input_method");
            this.H.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void b() {
        try {
            if (this.a == null || !this.a.isShowing()) {
                showDialog(1);
            } else {
                dismissDialog(1);
            }
        } catch (RuntimeException e) {
        }
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                this.a = new Dialog(this, R.style.dialogTheme);
                this.a.setContentView(R.layout.loaddialog);
                this.a.setCanceledOnTouchOutside(true);
                this.a.setCancelable(true);
                return this.a;
            default:
                return super.onCreateDialog(i);
        }
    }

    public void a(int i, String str) {
        if (i == 2959) {
            b();
            HashMap k = z.k(str);
            if ("0".equals(k.get(j.c))) {
                this.x = this.b.getString("id", "");
                this.z.a("name", this.k.getText().toString(), this.x);
                this.z.a("sex", "男".equals(this.v) ? "m" : "f", this.x);
                if (this.s) {
                    b();
                    this.y.a(this.e, true, "http://api.budejie.com/api/api_open.php?c=user&a=modifyheader", this.o, "jpg", this.L, this.d, SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT);
                    return;
                }
                this.f = an.a(this.e, this.e.getString(R.string.person_edit_data_success), -1);
                this.f.show();
                UserItem g = an.g(this.e);
                if (g != null) {
                    com.d.a.a(g.getProfile(), g.getName());
                }
                f();
            } else if (TextUtils.isEmpty((CharSequence) k.get("result_desc"))) {
                this.f = an.a(this.e, this.e.getString(R.string.person_edit_data_faild), -1);
                this.f.show();
            } else {
                this.f = an.a(this.e, (String) k.get("result_desc"), -1);
                this.f.show();
            }
        }
    }

    private void f() {
        String stringExtra = getIntent().getStringExtra("source");
        if ("third_party".equals(stringExtra)) {
            Intent intent = new Intent(this.e, PhoneNumBindingActivity.class);
            intent.putExtra("source", stringExtra);
            this.e.startActivityForResult(intent, 5050);
        } else if ("phone_register".equals(stringExtra)) {
            setResult(-1);
            this.e.finish();
        }
    }

    public void a(int i) {
        if (i == 2959) {
            b();
            this.f = an.a(this.e, this.e.getString(R.string.person_edit_data_faild), -1);
            this.f.show();
        }
    }

    public void next$press(View view) {
        b();
        this.L = new ArrayList();
        this.L.add(new BasicNameValuePair("id", this.x));
        an.a(this.L, this.e);
        if ("third_party".equals(this.G)) {
            if (this.k.getText().toString().equals(this.c.get(HistoryOpenHelper.COLUMN_USERNAME))) {
                this.J = "";
            } else if (b(this.k.getText().toString())) {
                this.u = true;
                this.J = this.k.getText().toString();
            } else {
                return;
            }
            if (this.l.getCheckedRadioButtonId() == this.m.getId()) {
                if ("m".equals(this.c.get("sex"))) {
                    this.K = "";
                } else {
                    this.t = true;
                    this.K = "m";
                }
            } else if ("f".equals(this.c.get("sex"))) {
                this.K = "";
            } else {
                this.t = true;
                this.K = "f";
            }
            if (!this.s && !this.u && !this.t) {
                f();
            } else if (this.u || this.t) {
                this.w.a(this.x, this.K, this.J, "", "", "", "", 2959);
            } else {
                this.y.a(this.e, true, "http://api.budejie.com/api/api_open.php?c=user&a=modifyheader", this.o, "jpg", this.L, this.d, SystemMessageConstants.JS_BRIDGE_ANNOTATION_NOT_PRESENT);
            }
        } else if ("phone_register".equals(this.G) && b(this.k.getText().toString())) {
            this.w.a(this.x, "男".equals(this.v) ? "m" : "f", this.k.getText().toString(), "", "", "", "", 2959);
        }
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "姓名不能为空", 0).show();
            return false;
        }
        int length;
        String trim = str.toString().trim();
        try {
            length = trim.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            length = 0;
        }
        if (length < 4 || length > 24) {
            Toast.makeText(this, "姓名长度应在4-24个字符之间", 0).show();
            return false;
        } else if (a(trim)) {
            return true;
        } else {
            Toast.makeText(this, "姓名不符合标准", 0).show();
            return false;
        }
    }

    public boolean a(String str) {
        return Pattern.compile("^[a-zA-Z0-9_一-龥-]+$").matcher(str).find();
    }

    public void btnProfile$Click(View view) {
        c("photo");
    }

    private void c(String str) {
        p.a(this.e, str, new c(this) {
            final /* synthetic */ CreatePersonDataActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                if (!TextUtils.isEmpty(str)) {
                    if ("拍照".equals(str)) {
                        this.a.g();
                        this.a.c();
                    } else if ("从相册中选择".equals(str)) {
                        this.a.g();
                        this.a.d();
                    }
                }
            }
        });
    }

    private void g() {
        this.f = an.a(this.e, this.e.getString(R.string.upload_portrait_hint), -1);
        this.f.show();
    }

    public void c() {
        if (com.budejie.www.activity.video.a.a()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "camera.jpg")));
            try {
                startActivityForResult(intent, 716);
                return;
            } catch (Exception e) {
                this.f = an.a(this.e, this.e.getString(R.string.no_camera), -1);
                this.f.show();
                return;
            }
        }
        this.f = an.a(this.e, this.e.getString(R.string.no_sdcard), -1);
        this.f.show();
    }

    public void d() {
        try {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setDataAndType(Media.INTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 714);
        } catch (Exception e) {
            an.a(this.e, getString(R.string.no_available_album), -1).show();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (i == 716) {
                p.a(this.e, Environment.getExternalStorageDirectory() + "/camera.jpg", 1280, 1280);
            } else if (i == 714) {
                if (intent != null) {
                    Uri data = intent.getData();
                    if (data.toString().startsWith(UriUtil.LOCAL_FILE_SCHEME)) {
                        p.a(this.e, data.getPath(), 1280, 1280);
                    } else {
                        Cursor query = getContentResolver().query(data, null, null, null, null);
                        if (query != null) {
                            query.moveToFirst();
                            p.a(this.e, query.getString(query.getColumnIndex("_data")), 1280, 1280);
                        }
                    }
                }
            } else if (i == 728) {
                this.o = intent.getStringExtra("image-path");
                if (this.o != null) {
                    this.j.setImageBitmap(BitmapFactory.decodeFile(this.o));
                    this.p = true;
                    this.s = true;
                    if (this.p && this.q && this.r) {
                        a(true);
                    }
                } else {
                    return;
                }
            }
        }
        if (i == 5050) {
            setResult(5051);
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        setResult(5051);
        finish();
        return false;
    }
}
