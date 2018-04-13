package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class me extends BaseGroupDialog {
    final /* synthetic */ GroupInfoActivity a;

    me(GroupInfoActivity groupInfoActivity, Context context) {
        this.a = groupInfoActivity;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_group_resign);
        CheckBox checkBox = (CheckBox) findViewById(R.id.quit_check);
        findViewById(R.id.quit_box).setOnClickListener(new mf(this, checkBox));
        ((TextView) findViewById(R.id.cancel)).setOnClickListener(new mg(this));
        ((TextView) findViewById(R.id.submit)).setOnClickListener(new mh(this, checkBox));
    }
}
