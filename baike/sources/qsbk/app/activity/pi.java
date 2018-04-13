package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class pi implements OnClickListener {
    final /* synthetic */ c a;

    pi(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        if (this.a.a.e.size() < ImagesPickerActivity.maxCount) {
            this.a.a.startCamera();
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format("最多只能选取%s张图片哦", new Object[]{Integer.valueOf(ImagesPickerActivity.maxCount)}), Integer.valueOf(0)).show();
    }
}
