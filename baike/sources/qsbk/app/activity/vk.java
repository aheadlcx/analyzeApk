package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import qsbk.app.Constants;

class vk implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    vk(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String format = String.format(Constants.ADMIN_FORBID_USER, new Object[]{Integer.valueOf(Integer.parseInt(this.a.bF.userId))});
        HashMap hashMap = new HashMap();
        this.a.forbidUser(format, "正在删除并封禁用户,请稍后..");
        dialogInterface.cancel();
    }
}
