package qsbk.app.activity;

import android.widget.ImageView;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.RoundedDrawable;

class yf implements SimpleCallBack {
    final /* synthetic */ NewImageViewer a;

    yf(NewImageViewer newImageViewer) {
        this.a = newImageViewer;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "匿名糗事成功！", Integer.valueOf(0)).show();
        ImageView imageView = (ImageView) this.a.findViewById(R.id.userIcon);
        imageView.setImageDrawable(RoundedDrawable.fromDrawable(imageView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
