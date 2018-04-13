package cn.xiaochuankeji.tieba.ui.my.account;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.c.d;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.user.SettingJson;
import cn.xiaochuankeji.tieba.background.modules.a.c;
import cn.xiaochuankeji.tieba.background.modules.a.h.a;
import cn.xiaochuankeji.tieba.background.modules.a.j;
import cn.xiaochuankeji.tieba.background.modules.a.k;
import cn.xiaochuankeji.tieba.background.modules.a.l;
import cn.xiaochuankeji.tieba.background.utils.monitor.a.b;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONException;

public class InputPasswordNicknameAvatarActivity extends h implements OnClickListener, a, j.a, l.a, NavigationBar.a, SDEditSheet.a {
    private int A;
    private boolean B = false;
    private cn.xiaochuankeji.tieba.api.account.a C;
    k d;
    private String e;
    private String f;
    private View g;
    private View h;
    private EditText i;
    private EditText j;
    private ImageView k;
    private ImageView l;
    private TextView m;
    private EditText n;
    private cn.xiaochuankeji.tieba.api.user.a o = new cn.xiaochuankeji.tieba.api.user.a();
    private ImageView p;
    private SDEditSheet q;
    private final int r = 1;
    private final int s = 2;
    private final int t = 4;
    private boolean u = false;
    private File v;
    private File w;
    private File x;
    private long y = 0;
    private int z;

    public static void a(Activity activity, String str, String str2, int i, int i2) {
        Intent intent = new Intent(activity, InputPasswordNicknameAvatarActivity.class);
        intent.putExtra("sms_code", str2);
        intent.putExtra("phone", str);
        intent.putExtra("region_code", i);
        intent.putExtra("request_code", i2);
        activity.startActivityForResult(intent, i2);
    }

    protected int a() {
        return R.layout.activity_ac_input_password_nickname_avatar;
    }

    protected boolean a(Bundle bundle) {
        this.w = new File(cn.xiaochuankeji.tieba.background.a.e().q() + ".clipped");
        this.v = new File(cn.xiaochuankeji.tieba.background.a.e().q());
        Intent intent = getIntent();
        this.e = intent.getStringExtra("phone");
        this.z = intent.getIntExtra("region_code", -1);
        this.f = intent.getStringExtra("sms_code");
        this.A = intent.getIntExtra("request_code", -1);
        if (TextUtils.isEmpty(this.e) || TextUtils.isEmpty(this.f)) {
            this.B = true;
        }
        if (this.B) {
            b.a().a("show", "thirdparty_register_update", null);
        }
        return true;
    }

    protected void c() {
        this.i = (EditText) findViewById(R.id.titleEditPassword);
        this.j = (EditText) findViewById(R.id.titleEditNickname);
        this.p = (ImageView) findViewById(R.id.ivAvatar);
        this.k = (ImageView) findViewById(R.id.ivMale);
        this.l = (ImageView) findViewById(R.id.ivFemale);
        this.m = (TextView) findViewById(R.id.titleEditbirthday);
        this.g = findViewById(R.id.ll_nickname);
        this.h = findViewById(R.id.ll_password);
        this.q = new SDEditSheet(this, this, "选择头像");
        this.q.a("拍照", 41, false);
        this.q.a("从手机相册中选择", 43, true);
        this.n = (EditText) findViewById(R.id.titleEditSign);
    }

    protected void i_() {
        this.i.setInputType(145);
        this.j.setFilters(new InputFilter[]{new LengthFilter(10)});
        this.j.setSingleLine();
        if (this.B) {
            findViewById(R.id.ll_nickname).setVisibility(8);
            this.h.setVisibility(8);
        }
        if (this.A == 705) {
            this.g.setVisibility(8);
            this.h.setVisibility(8);
        }
    }

    protected void j_() {
        findViewById(R.id.bnSubmit).setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        ((NavigationBar) findViewById(R.id.navBar)).setListener(this);
    }

    public void onBackPressed() {
        if (!g.b(this)) {
            super.onBackPressed();
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMale:
                this.k.setSelected(true);
                this.l.setSelected(false);
                return;
            case R.id.ivFemale:
                this.l.setSelected(true);
                this.k.setSelected(false);
                return;
            case R.id.ivAvatar:
                cn.htjyb.c.a.a((Activity) this);
                PermissionItem permissionItem = new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA");
                permissionItem.runIgnorePermission = false;
                permissionItem.settingText = "设置";
                permissionItem.deniedMessage = "开启以下权限才能正常浏览图片和视频";
                permissionItem.needGotoSetting = true;
                cn.xiaochuankeji.aop.permission.a.a((Context) this).a(permissionItem, new e(this) {
                    final /* synthetic */ InputPasswordNicknameAvatarActivity a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        this.a.q.b();
                    }

                    public void permissionDenied() {
                        cn.xiaochuankeji.tieba.background.utils.g.a("开启以下权限才能正常浏览图片和视频");
                    }
                });
                return;
            case R.id.titleEditbirthday:
                z();
                return;
            case R.id.bnSubmit:
                if (this.B) {
                    if (x() && y()) {
                        k();
                        return;
                    }
                    return;
                } else if (this.A == 705) {
                    if (x() && y()) {
                        j();
                        return;
                    }
                    return;
                } else if (v() && x() && y() && w()) {
                    k();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void j() {
        g.a((Activity) this);
        if (this.C == null) {
            this.C = new cn.xiaochuankeji.tieba.api.account.a();
        }
        CharSequence trim = this.j.getText().toString().trim();
        String obj = this.n.getText().toString();
        int i = 0;
        if (this.k.isSelected() && !this.l.isSelected()) {
            i = 1;
        } else if (!this.k.isSelected() && this.l.isSelected()) {
            i = 2;
        }
        JSONObject jSONObject = new JSONObject();
        long c = cn.xiaochuankeji.tieba.background.a.g().c();
        if (c > 0) {
            jSONObject.put("mid", Long.valueOf(c));
        }
        jSONObject.put("phone", this.e);
        jSONObject.put("code", this.f);
        if (!TextUtils.isEmpty(trim)) {
            jSONObject.put("name", trim);
        }
        jSONObject.put("gender", Integer.valueOf(i));
        jSONObject.put("sign", obj);
        jSONObject.put("birth", Long.valueOf(this.y));
        jSONObject.put("region_code", Integer.valueOf(this.z));
        this.C.a(jSONObject).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<JSONObject>(this) {
            final /* synthetic */ InputPasswordNicknameAvatarActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.c(this.a);
                cn.xiaochuankeji.tieba.background.utils.g.a(th == null ? "登录失败" : th.getMessage());
            }

            public void a(JSONObject jSONObject) {
                g.c(this.a);
                String string = jSONObject.getString("token");
                long longValue = jSONObject.getLong("mid").longValue();
                cn.xiaochuankeji.tieba.background.modules.a.b i = cn.xiaochuankeji.tieba.background.a.i();
                i.a(true);
                i.a(longValue);
                i.a(false, false);
                i.a(jSONObject.getString("passwd"));
                try {
                    i.a(new org.json.JSONObject(jSONObject.toJSONString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i.b(string);
                i.t();
                i.u();
                cn.xiaochuankeji.tieba.background.utils.c.a.c().d();
                this.a.o.a().d(new rx.b.g<SettingJson, Boolean>(this) {
                    final /* synthetic */ AnonymousClass2 a;

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
                }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<Boolean>(this) {
                    final /* synthetic */ AnonymousClass2 a;

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
                if (this.a.u) {
                    g.a(this.a);
                    if (this.a.w != null && this.a.w.exists()) {
                        cn.xiaochuankeji.tieba.background.a.h().a(this.a.w.getPath(), this.a);
                        return;
                    }
                    return;
                }
                this.a.setResult(-1);
                this.a.finish();
            }
        });
    }

    private void k() {
        g.a((Activity) this);
        String trim = this.j.getText().toString().trim();
        String obj = this.i.getText().toString();
        String obj2 = this.n.getText().toString();
        c h = cn.xiaochuankeji.tieba.background.a.h();
        int i = 0;
        if (this.k.isSelected() && !this.l.isSelected()) {
            i = 1;
        } else if (!this.k.isSelected() && this.l.isSelected()) {
            i = 2;
        }
        if (this.B) {
            h.a(i, obj2, this.y, this);
            b.a().a("finish", "thirdparty_register_update", null);
            return;
        }
        this.d = new k(this.e, this.f, trim, obj, i, this.y, obj2, this.z);
        h.a(this.d, (l.a) this);
    }

    private boolean v() {
        if (!TextUtils.isEmpty(this.j.getText().toString().trim())) {
            return true;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a("昵称不能为空");
        return false;
    }

    private boolean w() {
        if (d.a(this.i.getText().toString())) {
            return true;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a("密码格式错误");
        return false;
    }

    private boolean x() {
        if (this.k.isSelected() || this.l.isSelected()) {
            return true;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a("您还没有选择性别");
        return false;
    }

    private boolean y() {
        boolean z = !TextUtils.isEmpty(this.m.getText());
        if (!z) {
            cn.xiaochuankeji.tieba.background.utils.g.a("您还没有填写生日");
        }
        return z;
    }

    public void b(boolean z, String str) {
        g.c(this);
        if (!z) {
            cn.xiaochuankeji.tieba.background.utils.g.a(str);
        } else if (this.u) {
            g.a((Activity) this);
            if (this.w != null && this.w.exists()) {
                cn.xiaochuankeji.tieba.background.a.h().a(this.w.getPath(), (j.a) this);
            }
        } else {
            setResult(-1);
            finish();
        }
    }

    public void a(int i) {
        switch (i) {
            case 41:
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Parcelable fromFile = Uri.fromFile(this.v);
                if (VERSION.SDK_INT >= 24) {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put("_data", this.v.getAbsolutePath());
                    fromFile = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
                }
                intent.putExtra("output", fromFile);
                startActivityForResult(intent, 2);
                return;
            case 43:
                Intent intent2 = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
                intent2.putExtra("output", Uri.fromFile(this.v));
                try {
                    startActivityForResult(intent2, 1);
                    return;
                } catch (ActivityNotFoundException e) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("打开手机相册失败!");
                    return;
                }
            default:
                return;
        }
    }

    public void a(boolean z, Object obj) {
        g.c(this);
        if (!z) {
            cn.xiaochuankeji.tieba.background.utils.g.a(String.valueOf(obj));
        }
        setResult(-1);
        finish();
    }

    private void z() {
        int i;
        Calendar instance = Calendar.getInstance();
        if (c.a.c.e().c()) {
            i = R.style.DatePickerNightTheme;
        } else {
            i = R.style.DatePickerTheme;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, i, new OnDateSetListener(this) {
            final /* synthetic */ InputPasswordNicknameAvatarActivity a;

            {
                this.a = r1;
            }

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Calendar instance = Calendar.getInstance();
                instance.set(i, i2, i3);
                this.a.m.setText(new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime()));
                this.a.y = instance.getTimeInMillis() / 1000;
            }
        }, 1997, 0, 1);
        long currentTimeMillis = System.currentTimeMillis();
        instance.setTimeInMillis(currentTimeMillis);
        instance.add(1, -120);
        datePickerDialog.getDatePicker().setMaxDate(currentTimeMillis);
        datePickerDialog.getDatePicker().setMinDate(instance.getTimeInMillis());
        datePickerDialog.show();
    }

    private boolean a(File file, File file2) {
        if (cn.htjyb.c.b.b.a(file, file2, 80, 800)) {
            return true;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a("保存照片失败");
        return false;
    }

    private void A() {
        Bitmap a = cn.htjyb.c.b.b.a(this.w.getPath(), 800);
        if (a != null) {
            this.p.setImageBitmap(cn.htjyb.c.b.b.a(a, true));
            this.u = true;
        }
    }

    private void a(Intent intent) {
        if (cn.htjyb.c.b.b.a(intent, getContentResolver(), 800, this.v)) {
            a(this.v);
        }
    }

    private void b(Intent intent) {
        if (a(this.v, this.v)) {
            a(this.v);
        }
    }

    public void a(File file) {
        if (this.x != null) {
            this.x.delete();
        }
        this.x = new File(file.getPath() + "." + System.currentTimeMillis());
        com.izuiyou.a.a.b.c("tempFile: " + this.x.getPath());
        cn.htjyb.c.a.b.a(file, this.x);
        if (this.w != null) {
            this.w.delete();
        }
        Uri fromFile = Uri.fromFile(this.x);
        Uri fromFile2 = Uri.fromFile(this.w);
        if (fromFile != null) {
            try {
                if (fromFile.isAbsolute()) {
                    cn.xiaochuan.image.a.b.a((Activity) this, fromFile, fromFile2, "剪裁头像");
                }
            } catch (Exception e) {
                if (fromFile != null) {
                    try {
                        if (fromFile.isAbsolute()) {
                            cn.xiaochuan.image.a.b.a((Activity) this, fromFile, fromFile2, 70);
                        }
                    } catch (Exception e2) {
                        this.w = file;
                    }
                }
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.x != null) {
            this.x.delete();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 1:
                    a(intent);
                    break;
                case 2:
                    b(intent);
                    break;
                case 69:
                case 70:
                    A();
                    break;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void a(boolean z, String str) {
        g.c(this);
        if (!z) {
            cn.xiaochuankeji.tieba.background.utils.g.a(str);
        } else if (this.u) {
            g.a((Activity) this);
            cn.xiaochuankeji.tieba.background.a.h().a(this.v.getPath(), (j.a) this);
        } else {
            setResult(-1);
            finish();
        }
    }

    public void r() {
        cn.htjyb.c.a.a((Activity) this);
        onBackPressed();
        if (this.B) {
            b.a().a("close", "thirdparty_register_update", null);
        }
    }
}
