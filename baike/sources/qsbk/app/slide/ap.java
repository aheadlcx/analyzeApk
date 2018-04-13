package qsbk.app.slide;

import android.widget.ImageView;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.RoundedDrawable;

class ap implements SimpleCallBack {
    final /* synthetic */ SingleArticleFragment a;

    ap(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "匿名糗事成功！", Integer.valueOf(0)).show();
        this.a.x.setText(BaseUserInfo.ANONYMOUS_USER_NAME);
        this.a.x.setTextColor(this.a.getResources().getColor(UIHelper.isNightTheme() ? R.color.uesr_night : R.color.g_txt_small));
        ImageView imageView = (ImageView) this.a.w.findViewById(R.id.userIcon);
        imageView.setImageDrawable(RoundedDrawable.fromDrawable(imageView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
