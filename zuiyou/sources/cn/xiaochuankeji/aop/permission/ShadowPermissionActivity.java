package cn.xiaochuankeji.aop.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import cn.xiaochuankeji.aop.a.d;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShadowPermissionActivity extends AppCompatActivity {
    private static HashMap<String, String> m = new HashMap();
    private static HashMap<String, String> n = new HashMap();
    private static a o;
    private static e p;
    String a;
    String b;
    String[] c;
    boolean d = false;
    String e;
    boolean f = false;
    String g;
    String h;
    boolean i;
    String j;
    String k;
    String l;

    public interface a {
        boolean a(String[] strArr);
    }

    private class b extends ViewHolder {
        TextView a;
        final /* synthetic */ ShadowPermissionActivity b;

        public b(ShadowPermissionActivity shadowPermissionActivity, View view) {
            this.b = shadowPermissionActivity;
            super(view);
            this.a = (TextView) view.findViewById(cn.xiaochuankeji.aop.a.b.tv_permission);
        }
    }

    private class c extends Adapter<b> {
        final /* synthetic */ ShadowPermissionActivity a;
        private final ArrayList<String> b;

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((b) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        public c(ShadowPermissionActivity shadowPermissionActivity, ArrayList<String> arrayList) {
            this.a = shadowPermissionActivity;
            this.b = arrayList;
        }

        public b a(ViewGroup viewGroup, int i) {
            return new b(this.a, this.a.getLayoutInflater().inflate(cn.xiaochuankeji.aop.a.c.permission_item, viewGroup, false));
        }

        public void a(b bVar, int i) {
            if (i < this.b.size() && i >= 0) {
                String str = (String) this.b.get(i);
                String str2 = (String) ShadowPermissionActivity.m.get(str);
                bVar.a.setText(str2 + "：" + ((String) ShadowPermissionActivity.n.get(str)));
            }
        }

        public int getItemCount() {
            return this.b.size();
        }
    }

    static {
        m.put("android.permission.READ_PHONE_STATE", "获取手机信息");
        m.put("android.permission.ACCESS_FINE_LOCATION", "获取位置信息");
        m.put("android.permission.ACCESS_COARSE_LOCATION", "获取位置信息");
        m.put("android.permission.WRITE_EXTERNAL_STORAGE", "存储");
        m.put("android.permission.READ_EXTERNAL_STORAGE", "存储");
        m.put("android.permission.RECORD_AUDIO", "录音");
        m.put("android.permission.CAMERA", "录像");
        n.put("android.permission.READ_PHONE_STATE", "开启后推荐内容会更符合您的口味哦, 同时提高账户安全系数");
        n.put("android.permission.ACCESS_FINE_LOCATION", "为您推荐更适合您的内容");
        n.put("android.permission.ACCESS_COARSE_LOCATION", "为您推荐更适合您的内容");
        n.put("android.permission.WRITE_EXTERNAL_STORAGE", "浏览图片和视频缓存需要本地读写，方便浏览并节约流量");
        n.put("android.permission.READ_EXTERNAL_STORAGE", "浏览图片和视频缓存需要本地读写，方便浏览并节约流量");
        n.put("android.permission.RECORD_AUDIO", "如需录音需先开启手机麦克风使用权限哦~");
        n.put("android.permission.CAMERA", "如需拍摄图片和视频，需先开启手机相机使用权限哦~");
    }

    public static void a(a aVar) {
        o = aVar;
    }

    private static void a(e eVar) {
        p = eVar;
    }

    public static void a(Context context, String[] strArr, String str, String str2, boolean z, String str3, String str4, String str5, e eVar) {
        a(eVar);
        Intent intent = new Intent(context, ShadowPermissionActivity.class);
        intent.putExtra("permissions", strArr);
        intent.putExtra("rationale_message", str);
        intent.putExtra("rationale_confirm_text", str2);
        intent.putExtra("setting_button", z);
        intent.putExtra("setting_button_text", str3);
        intent.putExtra("deny_message", str4);
        intent.putExtra("denied_dialog_close_text", str5);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(16);
        LayoutParams attributes = window.getAttributes();
        attributes.flags = 128;
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
        onNewIntent(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            setIntent(intent);
        }
        this.h = getPackageName();
        Bundle extras = getIntent().getExtras();
        this.c = extras.getStringArray("permissions");
        this.a = extras.getString("rationale_message");
        this.b = extras.getString("deny_message");
        this.i = extras.getBoolean("setting_button", false);
        this.j = extras.getString("setting_button_text", getString(d.permission_setting));
        this.l = extras.getString("rationale_confirm_text", getString(d.permission_ok));
        this.k = extras.getString("denied_dialog_close_text", getString(d.permission_close));
        a(false);
    }

    private void c() {
        if (p != null) {
            p.permissionGranted();
            p = null;
        }
        if (o == null || !o.a(this.c)) {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    private void b(List<String> list) {
        if (p != null) {
            p.permissionDenied();
            p = null;
        }
        if (o == null || !o.a(this.c)) {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    private void d() {
        try {
            startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.parse("package:" + this.h)), 119);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            startActivityForResult(new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS"), 119);
        }
    }

    private void a(boolean z) {
        List<String> a = f.a((Activity) this, this.c);
        Object obj = null;
        for (String str : a) {
            Object obj2;
            if (!this.d && str.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                this.e = "android.permission.SYSTEM_ALERT_WINDOW";
                obj2 = obj;
            } else if (!this.f && str.equals("android.permission.WRITE_SETTINGS")) {
                this.g = "android.permission.WRITE_SETTINGS";
                obj2 = obj;
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                int i = 1;
            } else {
                obj2 = obj;
            }
            obj = obj2;
        }
        if (!f.a()) {
            c();
        } else if (a.isEmpty()) {
            c();
        } else if (z) {
            b((List) a);
        } else if (obj == null || TextUtils.isEmpty(this.a)) {
            a((List) a);
        } else {
            c(a);
        }
    }

    @TargetApi(23)
    public void a(List<String> list) {
        if (!this.d && !TextUtils.isEmpty(this.e)) {
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + this.h)), 120);
        } else if (this.f || TextUtils.isEmpty(this.g)) {
            ActivityCompat.requestPermissions(this, (String[]) list.toArray(new String[list.size()]), 110);
        } else {
            startActivityForResult(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + this.h)), 121);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            Object obj = strArr[i2];
            if (iArr[i2] == -1) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            c();
        } else {
            a(arrayList);
        }
    }

    private void c(final List<String> list) {
        new Builder(this).setMessage(this.a).setCancelable(false).setPositiveButton(this.l, new OnClickListener(this) {
            final /* synthetic */ ShadowPermissionActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.a(list);
            }
        }).show();
    }

    public void a(ArrayList<String> arrayList) {
        this.b = TextUtils.isEmpty(this.b) ? getString(d.permission_denied_msg_default) : this.b;
        this.k = TextUtils.isEmpty(this.k) ? getString(d.permission_close) : this.k;
        if (this.i) {
            b((ArrayList) arrayList);
            return;
        }
        cn.htjyb.ui.widget.a.a((Context) this, this.b, 1).show();
        b((List) arrayList);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 119:
                a(true);
                return;
            case 120:
                this.d = true;
                a(false);
                return;
            case 121:
                this.f = true;
                a(false);
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    public void onBackPressed() {
    }

    private void b(final ArrayList<String> arrayList) {
        AlertDialog create = new Builder(this).setCancelable(false).create();
        View inflate = getLayoutInflater().inflate(cn.xiaochuankeji.aop.a.c.permission_dialog, null);
        TextView textView = (TextView) inflate.findViewById(cn.xiaochuankeji.aop.a.b.dialog_message);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(cn.xiaochuankeji.aop.a.b.dialog_items);
        Button button = (Button) inflate.findViewById(cn.xiaochuankeji.aop.a.b.btn_ok);
        View findViewById = inflate.findViewById(cn.xiaochuankeji.aop.a.b.dialog_close);
        button.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ ShadowPermissionActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        findViewById.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ ShadowPermissionActivity b;

            public void onClick(View view) {
                this.b.b(arrayList);
            }
        });
        if (!TextUtils.isEmpty(this.b)) {
            textView.setText(this.b);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        recyclerView.setAdapter(new c(this, arrayList));
        create.setView(inflate, 0, 0, 0, 0);
        create.show();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = (int) (displayMetrics.density * 254.0f);
        LayoutParams attributes = create.getWindow().getAttributes();
        attributes.width = i;
        attributes.height = -2;
        attributes.gravity = 17;
        attributes.alpha = 1.0f;
        create.getWindow().setAttributes(attributes);
        create.getWindow().setBackgroundDrawableResource(cn.xiaochuankeji.aop.a.a.scrollbar_transparent);
    }
}
