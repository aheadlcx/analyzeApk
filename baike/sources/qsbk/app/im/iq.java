package qsbk.app.im;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.baidu.mobstat.StatService;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.image.issue.Logger;

class iq implements HttpCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ OfficialSubscribeListAdapter b;

    iq(OfficialSubscribeListAdapter officialSubscribeListAdapter, int i) {
        this.b = officialSubscribeListAdapter;
        this.a = i;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        OfficialSubscribeListItem item = this.b.getItem(this.a);
        if ((Constants.OFFICIAL_SUBSCRIBE + "CANCEL").equalsIgnoreCase(str)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已经取消收听，你将不会收到" + item.name + "推送的消息", Integer.valueOf(0)).show();
            Intent intent = new Intent();
            intent.setAction(IMMessageListFragment.ACTION_DELETE_CONTACT_ITEM);
            intent.putExtra(IMMessageListFragment.ACTION_DELETE_CONTACT_ITEM, item.id);
            LocalBroadcastManager.getInstance(this.b.c).sendBroadcast(intent);
            StatService.onEvent(this.b.c, "OFFICIAL_UNFOLLOW", "unit_" + item.id);
            Logger.getInstance().debug(OfficialSubscribeListAdapter.b, "cancelSubscribe", "unit_" + item.id);
        } else if (Constants.OFFICIAL_SUBSCRIBE.equalsIgnoreCase(str)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "欢迎收听" + item.name + "官号，官号消息将出现在您的小纸条里", Integer.valueOf(0)).show();
        }
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.b.checkChange(this.a);
    }
}
