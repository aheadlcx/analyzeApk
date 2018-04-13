package cn.xiaochuankeji.tieba.ui.my.account;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import c.a.c;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.modules.a.h.a;
import cn.xiaochuankeji.tieba.background.modules.a.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.account.AccountCheckJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.SDEditSheet;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONException;

public class AccountInfoActivity extends h implements OnClickListener, a, j.a, SDEditSheet.a {
    protected File d;
    private final int e = 1;
    private final int f = 2;
    private final int g = 3;
    private final int h = 5;
    private final int i = 6;
    private File j;
    private WebImageView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private View o;
    private View p;
    private File q;
    private cn.xiaochuankeji.tieba.api.account.a r = new cn.xiaochuankeji.tieba.api.account.a();

    public static void a(Context context) {
        context.startActivity(new Intent(context, AccountInfoActivity.class));
    }

    protected int a() {
        return R.layout.activity_ac_account_info;
    }

    protected boolean a(Bundle bundle) {
        this.j = new File(cn.xiaochuankeji.tieba.background.a.e().q());
        this.d = new File(cn.xiaochuankeji.tieba.background.a.e().q() + ".clipped");
        return true;
    }

    protected void c() {
        this.k = (WebImageView) findViewById(R.id.picAvatar);
        this.l = (TextView) findViewById(R.id.tvNickName);
        this.o = findViewById(R.id.ivMale);
        this.p = findViewById(R.id.ivFemale);
        this.m = (TextView) findViewById(R.id.tvSignOrLoginTips);
        this.n = (TextView) findViewById(R.id.tvBirth);
    }

    protected void i_() {
        j();
    }

    private void j() {
        Member q = cn.xiaochuankeji.tieba.background.a.g().q();
        this.k.setWebImage(b.a(q.getId(), q.getAvatarID()));
        this.l.setText(q.getName());
        this.m.setText(q.getSign());
        v();
        k();
    }

    private void k() {
        if (cn.xiaochuankeji.tieba.background.a.g() != null) {
            Member q = cn.xiaochuankeji.tieba.background.a.g().q();
            if (q != null && q.getBirth() != 0) {
                this.n.setText(new SimpleDateFormat("yyyy-MM-dd ").format(new Date(q.getBirth() * 1000)));
            }
        }
    }

    private void v() {
        int gender = cn.xiaochuankeji.tieba.background.a.g().q().getGender();
        if (gender == 0) {
            this.o.setSelected(true);
            this.p.setSelected(false);
        } else if (gender == 1) {
            this.o.setSelected(true);
            this.p.setSelected(false);
        } else if (gender == 2) {
            this.o.setSelected(false);
            this.p.setSelected(true);
        }
    }

    protected void j_() {
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.n.setOnClickListener(this);
        findViewById(R.id.tvSignOrLoginTips).setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.picAvatar:
                PermissionItem permissionItem = new PermissionItem("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA");
                permissionItem.runIgnorePermission = false;
                permissionItem.settingText = "设置";
                permissionItem.deniedMessage = "开启以下权限才能正常浏览图片和视频";
                permissionItem.needGotoSetting = true;
                cn.xiaochuankeji.aop.permission.a.a((Context) this).a(permissionItem, new e(this) {
                    final /* synthetic */ AccountInfoActivity a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        cn.htjyb.c.a.a(this.a);
                        SDEditSheet sDEditSheet = new SDEditSheet(this.a, this.a, "选择头像");
                        sDEditSheet.a("拍照", 41, false);
                        sDEditSheet.a("从手机相册选择", 43, true);
                        sDEditSheet.b();
                    }

                    public void permissionDenied() {
                        g.a("开启以下权限才能正常浏览图片和视频");
                    }
                });
                return;
            case R.id.tvNickName:
                com.izuiyou.a.a.b.c("click nick name");
                w();
                return;
            case R.id.ivMale:
                this.o.setSelected(true);
                this.p.setSelected(false);
                cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
                cn.xiaochuankeji.tieba.background.a.h().a(1, null, 0, this);
                return;
            case R.id.ivFemale:
                this.p.setSelected(true);
                this.o.setSelected(false);
                cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
                cn.xiaochuankeji.tieba.background.a.h().a(2, null, 0, this);
                return;
            case R.id.tvBirth:
                y();
                return;
            case R.id.tvSignOrLoginTips:
                ModifySignActivity.a((Activity) this, 5);
                return;
            default:
                return;
        }
    }

    private void w() {
        this.r.a().a(rx.a.b.a.a()).b(new rx.j<AccountCheckJson>(this) {
            final /* synthetic */ AccountInfoActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((AccountCheckJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(((ClientErrorException) th).errMessage());
                    return;
                }
                com.izuiyou.a.a.b.e(th);
                g.a("未知错误:" + th.getMessage());
            }

            public void a(AccountCheckJson accountCheckJson) {
                if (accountCheckJson == null || !accountCheckJson.isEnable()) {
                    String msg = (accountCheckJson == null || TextUtils.isEmpty(accountCheckJson.getMsg())) ? "您被禁止更新昵称" : accountCheckJson.getMsg();
                    g.a(msg);
                    return;
                }
                ModifyNickNameActivity.a(this.a, cn.xiaochuankeji.tieba.background.a.g().q().getRawName(), accountCheckJson.getMsg(), 3);
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.q != null) {
            this.q.delete();
        }
    }

    public void onBackPressed() {
        if (!cn.xiaochuankeji.tieba.ui.widget.g.b(this)) {
            super.onBackPressed();
        }
    }

    public void a(int i) {
        switch (i) {
            case 41:
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Parcelable fromFile = Uri.fromFile(this.j);
                if (VERSION.SDK_INT >= 24) {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put("_data", this.j.getAbsolutePath());
                    fromFile = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
                }
                intent.putExtra("output", fromFile);
                startActivityForResult(intent, 2);
                return;
            case 43:
                Intent intent2 = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
                intent2.putExtra("output", Uri.fromFile(this.j));
                try {
                    startActivityForResult(intent2, 1);
                    return;
                } catch (ActivityNotFoundException e) {
                    g.a("打开手机相册失败!");
                    return;
                }
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (1 == i) {
            if (-1 == i2) {
                a(intent);
            }
        } else if (2 == i) {
            if (-1 == i2) {
                b(intent);
            }
        } else if (3 == i) {
            if (-1 == i2) {
                this.l.setText(cn.xiaochuankeji.tieba.background.a.g().q().getName());
            }
        } else if (5 == i) {
            if (-1 == i2) {
                this.m.setText(cn.xiaochuankeji.tieba.background.a.g().q().getSign());
            }
        } else if (6 == i) {
            if (-1 == i2) {
                v();
            }
        } else if (i == 69) {
            if (-1 == i2) {
                x();
            }
        } else if (i == 70) {
            if (-1 == i2) {
                x();
            }
        } else if (i == 45) {
            k();
        }
    }

    private void a(Intent intent) {
        if (intent != null && cn.htjyb.c.b.b.a(intent, getContentResolver(), 800, this.j)) {
            a(this.j);
        }
    }

    private boolean a(File file, File file2) {
        if (cn.htjyb.c.b.b.a(file, file2, 80, 800)) {
            return true;
        }
        cn.htjyb.ui.widget.a.a((Context) this, (CharSequence) "保存照片失败", 0).show();
        return false;
    }

    private void x() {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        cn.xiaochuankeji.tieba.background.a.h().a(this.d.getAbsolutePath(), (j.a) this);
    }

    private void b(Intent intent) {
        if (a(this.j, this.j)) {
            a(this.j);
        }
    }

    public void a(File file) {
        if (this.q != null) {
            this.q.delete();
        }
        if (this.d != null) {
            this.d.delete();
        }
        this.q = new File(file.getPath() + "." + System.currentTimeMillis());
        cn.htjyb.c.a.b.a(file, this.q);
        Uri fromFile = Uri.fromFile(this.q);
        Uri fromFile2 = Uri.fromFile(this.d);
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
                        this.d = file;
                        x();
                    }
                }
            }
        }
    }

    public void a(boolean z, Object obj) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        if (z) {
            if (obj instanceof cn.htjyb.b.a) {
                this.k.setData((cn.htjyb.b.a) obj);
            }
            g.a("头像设置成功");
            return;
        }
        g.a(String.valueOf(obj));
    }

    public void a(boolean z, String str) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        if (z) {
            g.a("性别修改成功");
        } else {
            g.a(str);
        }
    }

    private void y() {
        cn.xiaochuankeji.tieba.background.modules.a.a g = cn.xiaochuankeji.tieba.background.a.g();
        if (g != null) {
            int i;
            final Calendar instance = Calendar.getInstance();
            long birth = g.q().getBirth() * 1000;
            Calendar instance2 = Calendar.getInstance(TimeZone.getDefault());
            instance2.set(1900, 0, 0);
            final boolean z = birth != 0 && birth > instance2.getTimeInMillis() / 1000;
            if (z) {
                instance.setTimeInMillis(birth);
            } else {
                instance.set(1, 1997);
                instance.set(2, 0);
                instance.set(5, 1);
            }
            if (c.e().c()) {
                i = R.style.DatePickerNightTheme;
            } else {
                i = R.style.DatePickerTheme;
            }
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, i, new OnDateSetListener(this) {
                final /* synthetic */ AccountInfoActivity c;

                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    if (!z || instance.get(1) != i || instance.get(2) != i2 || instance.get(5) != i3) {
                        instance.set(i, i2, i3);
                        this.c.n.setText(new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime()));
                        long timeInMillis = instance.getTimeInMillis() / 1000;
                        cn.xiaochuankeji.tieba.background.modules.a.a g = cn.xiaochuankeji.tieba.background.a.g();
                        if (g != null) {
                            cn.xiaochuankeji.tieba.ui.widget.g.a(this.c);
                            if (instance.get(1) < 1900) {
                                instance.set(1, 1901);
                            }
                            Member q = g.q();
                            this.c.r.a(q.getGender(), q.getSign(), timeInMillis).d(new rx.b.g<JSONObject, Boolean>(this) {
                                final /* synthetic */ AnonymousClass3 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ Object call(Object obj) {
                                    return a((JSONObject) obj);
                                }

                                public Boolean a(JSONObject jSONObject) {
                                    org.json.JSONObject jSONObject2;
                                    long longValue = jSONObject.getLong("mid").longValue();
                                    cn.xiaochuankeji.tieba.background.modules.a.b i = cn.xiaochuankeji.tieba.background.a.i();
                                    i.a(longValue);
                                    try {
                                        jSONObject2 = new org.json.JSONObject(jSONObject.toJSONString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        jSONObject2 = null;
                                    }
                                    i.a(jSONObject2);
                                    cn.xiaochuankeji.tieba.background.a.i().t();
                                    return Boolean.valueOf(true);
                                }
                            }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<Boolean>(this) {
                                final /* synthetic */ AnonymousClass3 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ void onNext(Object obj) {
                                    a((Boolean) obj);
                                }

                                public void onCompleted() {
                                }

                                public void onError(Throwable th) {
                                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.c);
                                    g.a(th == null ? "修改失败" : th.getMessage());
                                }

                                public void a(Boolean bool) {
                                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.c);
                                    g.a("生日修改成功");
                                }
                            });
                        }
                    }
                }
            }, instance.get(1), instance.get(2), instance.get(5));
            birth = System.currentTimeMillis();
            instance2 = Calendar.getInstance();
            instance2.setTimeInMillis(birth);
            instance2.add(1, -100);
            datePickerDialog.getDatePicker().setMaxDate(birth);
            datePickerDialog.getDatePicker().setMinDate(instance2.getTimeInMillis());
            datePickerDialog.show();
        }
    }
}
