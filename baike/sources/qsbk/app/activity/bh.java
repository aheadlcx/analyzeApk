package qsbk.app.activity;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class bh implements OnClickListener {
    final /* synthetic */ ApplyForOwnerActivity a;

    bh(ApplyForOwnerActivity applyForOwnerActivity) {
        this.a = applyForOwnerActivity;
    }

    public void onClick(View view) {
        String obj = this.a.b.getText().toString();
        if (obj.length() > Callback.DEFAULT_SWIPE_ANIMATION_DURATION) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "字数超出限制").show();
        } else {
            this.a.a(obj);
        }
    }
}
