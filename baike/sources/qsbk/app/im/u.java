package qsbk.app.im;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.im.datastore.ChatMsgStoreProxy;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastUtil;

class u extends ProgressDialogCallBack {
    final /* synthetic */ h a;

    u(h hVar, Context context, String str) {
        this.a = hVar;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastUtil.Short("已经成功加入群");
        if (!(this.a.l == null || this.a.c == null || this.a.c.getVisibility() != 0)) {
            this.a.l.status = 2;
            this.a.c.setText("已加入");
        }
        if (this.a.n != null) {
            this.a.n.data = this.a.n.getInviteInfo().toJSONString();
            ChatMsgStoreProxy.newInstance(QsbkApp.currentUser.userId, 3).updateMessageData(this.a.n);
        }
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastUtil.Long(str);
    }
}
