package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;

class x implements OnClickListener {
    final /* synthetic */ ActionBarLoginActivity a;

    x(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onClick(View view) {
        CharSequence[] charSequenceArr = new CharSequence[]{"邮箱找回密码", "手机找回密码"};
        Builder builder = new Builder(this.a);
        builder.setItems(charSequenceArr, new y(this));
        builder.setTitle("遇到问题？请加QQ群：274070027");
        builder.setNegativeButton("取消", new z(this));
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(true);
        create.show();
    }
}
