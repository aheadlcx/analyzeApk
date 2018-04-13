package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.Switch;

public class MemberTitleActivity extends BaseActionBarActivity {
    private static GroupInfo a = null;
    private GroupInfo b;
    private Switch c;
    private EditText[] d;
    private View e;
    private View f;
    private View g;
    private boolean h;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, GroupInfo groupInfo, boolean z) {
        Intent intent = new Intent(context, MemberTitleActivity.class);
        intent.putExtra("admin", z);
        context.startActivity(intent);
        a = groupInfo;
    }

    protected String f() {
        return "群成员等级";
    }

    protected int a() {
        return R.layout.group_member_level;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.h = getIntent().getBooleanExtra("admin", false);
        this.b = a;
        if (a == null) {
            finish();
            return;
        }
        a = null;
        g();
    }

    private void g() {
        int i;
        int i2 = 8;
        this.f = findViewById(R.id.level_switch_layout);
        this.g = findViewById(R.id.tips_layout);
        this.c = (Switch) findViewById(R.id.level_switch);
        this.d = new EditText[]{(EditText) findViewById(R.id.level6_text), (EditText) findViewById(R.id.level5_text), (EditText) findViewById(R.id.level4_text), (EditText) findViewById(R.id.level3_text), (EditText) findViewById(R.id.level2_text), (EditText) findViewById(R.id.level1_text)};
        this.e = findViewById(R.id.titles_layout);
        this.e.setVisibility(this.b.titleEnable ? 0 : 8);
        this.c.setChecked(this.b.titleEnable);
        this.c.setOnCheckedChangeListener(new tw(this));
        View view = this.f;
        if (this.h) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.g;
        if (!(this.h || this.b.titleEnable)) {
            i2 = 0;
        }
        view2.setVisibility(i2);
        for (i = 0; i < this.d.length; i++) {
            EditText editText = this.d[i];
            editText.setText(this.b.titles[i]);
            if (!this.h) {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                editText.setEnabled(false);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(this.h);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            case R.id.action_save:
                i();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void i() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.c.isChecked() ? "1:" : "0:");
        EditText[] editTextArr = this.d;
        int length = editTextArr.length;
        int i = 0;
        int i2 = 1;
        while (i < length) {
            EditText editText = editTextArr[i];
            if (i2 != 0) {
                i2 = 0;
            } else {
                stringBuilder.append(',');
            }
            String obj = editText.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "等级不能为空，请填写后重试", Integer.valueOf(0)).show();
                return;
            } else if (obj.indexOf(44) != -1) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "等级不能带逗号，请修改后重试", Integer.valueOf(0)).show();
                return;
            } else {
                stringBuilder.append(obj);
                i++;
            }
        }
        String format = String.format(Constants.URL_MOTIFY_GROUP_INFO, new Object[]{Integer.valueOf(this.b.id)});
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.b.id));
        hashMap.put("title", stringBuilder.toString());
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new tx(this, this, "保存中..."));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public void onBackPressed() {
        int i = 1;
        int i2 = 0;
        int i3 = this.b.titleEnable != this.c.isChecked() ? 1 : 0;
        if (i3 == 0) {
            while (i2 < 6) {
                if (!this.b.titles[i2].equals(this.d[i2].getText().toString())) {
                    break;
                }
                i2++;
            }
        }
        i = i3;
        if (i != 0) {
            new Builder(this).setTitle("提示").setMessage("部分设置尚未保存，是否保存").setPositiveButton("保存", new tz(this)).setNegativeButton("不保存", new ty(this)).show();
        } else {
            super.onBackPressed();
        }
    }

    public void finish() {
        super.finish();
    }
}
