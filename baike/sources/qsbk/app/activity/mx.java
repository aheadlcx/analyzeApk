package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class mx implements OnClickListener {
    final /* synthetic */ GroupIntroduceActivity a;

    mx(GroupIntroduceActivity groupIntroduceActivity) {
        this.a = groupIntroduceActivity;
    }

    public void onClick(View view) {
        String trim = this.a.c.getText().toString().trim();
        if (trim.length() <= 0) {
            ToastAndDialog.makeNegativeToast(this.a, "简介不能为空!", Integer.valueOf(1)).show();
        } else if (trim.length() > Callback.DEFAULT_SWIPE_ANIMATION_DURATION) {
            ToastAndDialog.makeNegativeToast(this.a, String.format("简介最多能输入%s个字，您已输入%s个字!", new Object[]{Integer.valueOf(Callback.DEFAULT_SWIPE_ANIMATION_DURATION), Integer.valueOf(trim.length())}), Integer.valueOf(1)).show();
        } else if (QsbkApp.currentUser != null) {
            new Builder(this.a).setItems(new String[]{"确认修改简介?", "取消"}, new my(this, trim)).show();
        }
    }
}
