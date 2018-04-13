package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.UIHelper;

public class ChangeGroupNameActivity extends BaseActionBarActivity {
    private GroupInfo a;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public static void launch(Context context, GroupInfo groupInfo) {
        Intent intent = new Intent(context, ChangeGroupNameActivity.class);
        intent.putExtra("group_info", groupInfo);
        context.startActivity(intent);
    }

    protected String c() {
        return "修改群名";
    }

    protected int a() {
        return R.layout.activity_change_group_name;
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
        if (getIntent() != null) {
            this.a = (GroupInfo) getIntent().getSerializableExtra("group_info");
        }
        if (this.a == null) {
            finish();
        } else {
            f();
        }
    }

    private void f() {
        TextView textView = (TextView) findViewById(R.id.tips);
        EditText editText = (EditText) findViewById(R.id.groupname);
        editText.setText(this.a.name);
        editText.setSelection(this.a.name.length());
        findViewById(R.id.finish).setOnClickListener(new dt(this, editText, textView));
    }
}
