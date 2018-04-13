package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class acf implements OnClickListener {
    final /* synthetic */ ace a;

    acf(ace ace) {
        this.a = ace;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                new Builder(this.a.a).setTitle("更换手机号").setItems(new String[]{"原手机号能接收验证码", "原手机号不能接收验证码"}, new ach(this)).setNegativeButton("取消", new acg(this)).show();
                return;
            case 1:
                dialogInterface.dismiss();
                return;
            default:
                return;
        }
    }
}
