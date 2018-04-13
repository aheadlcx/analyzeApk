package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;

class kg implements OnClickListener {
    final /* synthetic */ EditInfoEntranceActivity a;

    kg(EditInfoEntranceActivity editInfoEntranceActivity) {
        this.a = editInfoEntranceActivity;
    }

    public void onClick(View view) {
        new Builder(this.a).setTitle("选择头像").setItems(new String[]{"拍照", "相册"}, new ki(this)).setNegativeButton("取消", new kh(this)).show();
    }
}
