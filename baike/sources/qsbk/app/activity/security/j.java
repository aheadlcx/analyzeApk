package qsbk.app.activity.security;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

class j implements OnClickListener {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    j(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a.g.getText().toString())) {
            this.a.startActivityForResult(new Intent(this.a, EmailBindActivity.class), this.a.h);
            return;
        }
        new Builder(this.a).setTitle("操作").setItems(new String[]{"换一个", "取消"}, new k(this)).show();
    }
}
