package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.UIHelper;

public class ApplyForOwnerActivity extends BaseActionBarActivity {
    public static final int INVALID_ID = -1;
    public static final int REQ_APPLY = 157;
    private int a;
    private EditText b;
    private TextView c;
    private TextView d;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public static void launchForResult(Fragment fragment, int i, String str) {
        a(null, fragment, i, str);
    }

    public static void launchForResult(Context context, int i, String str) {
        a(context, null, i, str);
    }

    private static void a(Context context, Fragment fragment, int i, String str) {
        Intent intent = new Intent(context, ApplyForOwnerActivity.class);
        intent.putExtra(b.c, i);
        intent.putExtra("text", str);
        if (fragment != null) {
            fragment.startActivityForResult(intent, REQ_APPLY);
        } else if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, REQ_APPLY);
        } else {
            context.startActivity(intent);
        }
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
    }

    protected String c() {
        return null;
    }

    protected int a() {
        return R.layout.activity_apply_for_group;
    }

    protected void a(Bundle bundle) {
        this.a = getIntent().getIntExtra(b.c, -1);
        f();
    }

    private void f() {
        View findViewById = findViewById(R.id.apply_group);
        if (findViewById != null) {
            findViewById.setBackgroundColor(UIHelper.isNightTheme() ? UIHelper.getColor(R.color.main_bg_night) : UIHelper.getColor(R.color.white));
        }
        this.b = (EditText) findViewById(R.id.content);
        this.d = (TextView) findViewById(R.id.prompt);
        this.d.setVisibility(8);
        this.b.setHint("描述一下你可以为群员们带来什么，会营造怎样的群环境。");
        Object stringExtra = getIntent().getStringExtra("text");
        if (stringExtra != null) {
            this.b.setText(stringExtra);
            this.b.setSelection(stringExtra.length());
        }
        this.c = (TextView) findViewById(R.id.content_left);
        ((TextView) findViewById(R.id.name)).setText("竞选宣言");
        this.c.setText("250");
        this.b.addTextChangedListener(new bg(this));
        findViewById(R.id.send).setOnClickListener(new bh(this));
    }

    public void onBackPressed() {
        setResult(-1);
        super.onBackPressed();
    }

    private void a(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在处理中...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String format = String.format(Constants.URL_RUN_FOR_OWNER, new Object[]{Integer.valueOf(this.a)});
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.a));
        hashMap.put("reason", str);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new bi(this, str, progressDialog));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }
}
