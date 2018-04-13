package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class mn implements OnClickListener {
    final /* synthetic */ GroupInfoActivity a;

    mn(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a("上传图片中，请稍等...");
        this.a.k();
    }
}
