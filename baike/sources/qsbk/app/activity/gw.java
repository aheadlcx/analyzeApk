package qsbk.app.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.ToastAndDialog;

class gw implements OnClickListener {
    final /* synthetic */ CircleArticleImageViewer a;

    gw(CircleArticleImageViewer circleArticleImageViewer) {
        this.a = circleArticleImageViewer;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(DeviceUtils.getSDPath())) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现SD卡,保存失败！", Integer.valueOf(0)).show();
            this.a.a.setVisibility(4);
            return;
        }
        this.a.g();
        this.a.a.setImageResource(R.drawable.icon_save_circle);
    }
}
