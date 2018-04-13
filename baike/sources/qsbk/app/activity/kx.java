package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;

class kx implements OnClickListener {
    final /* synthetic */ FillUserDataActivity a;

    kx(FillUserDataActivity fillUserDataActivity) {
        this.a = fillUserDataActivity;
    }

    public void onClick(View view) {
        new Builder(this.a).setTitle("选择头像").setItems(new String[]{"拍照", "相册"}, new kz(this)).setNegativeButton("取消", new ky(this)).show();
    }
}
