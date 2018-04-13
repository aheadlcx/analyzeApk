package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class ip implements OnClickListener {
    final /* synthetic */ OfficialSubscribeListItem a;
    final /* synthetic */ int b;
    final /* synthetic */ OfficialSubscribeListAdapter c;

    ip(OfficialSubscribeListAdapter officialSubscribeListAdapter, OfficialSubscribeListItem officialSubscribeListItem, int i) {
        this.c = officialSubscribeListAdapter;
        this.a = officialSubscribeListItem;
        this.b = i;
    }

    public void onClick(View view) {
        if (this.a.canCancel) {
            Map hashMap;
            if (this.a.isSubscribe) {
                hashMap = new HashMap();
                hashMap.put("pid", this.a.id);
                QsbkApp.getInstance();
                hashMap.put("uid", QsbkApp.currentUser.userId);
                hashMap.put("cancel", Integer.valueOf(1));
                this.c.a(Constants.OFFICIAL_SUBSCRIBE + "CANCEL", Constants.OFFICIAL_SUBSCRIBE, hashMap, this.b);
            } else {
                hashMap = new HashMap();
                hashMap.put("pid", this.a.id);
                QsbkApp.getInstance();
                hashMap.put("uid", QsbkApp.currentUser.userId);
                hashMap.put("cancel", Integer.valueOf(0));
                this.c.a(Constants.OFFICIAL_SUBSCRIBE, Constants.OFFICIAL_SUBSCRIBE, hashMap, this.b);
            }
            this.c.checkChange(this.b);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.a.name + "不能取消收听", Integer.valueOf(0)).show();
    }
}
