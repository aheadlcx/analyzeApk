package qsbk.app.im;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class ih implements OnClickListener {
    final /* synthetic */ OfficialInfoActivity a;

    ih(OfficialInfoActivity officialInfoActivity) {
        this.a = officialInfoActivity;
    }

    public void onClick(View view) {
        if (!this.a.l) {
            Map hashMap = new HashMap();
            hashMap.put("pid", this.a.i);
            QsbkApp.getInstance();
            hashMap.put("uid", QsbkApp.currentUser.userId);
            hashMap.put("cancel", Integer.valueOf(0));
            this.a.a(Constants.OFFICIAL_SUBSCRIBE, Constants.OFFICIAL_SUBSCRIBE, hashMap);
        } else if (this.a.m) {
            new Builder(this.a).setMessage("取消收听将会删除" + this.a.j + "推送的所有消息，确定要取消收听吗？").setPositiveButton("确定", new ij(this)).setNegativeButton("再想想", new ii(this)).show();
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "此号不可取消收听", Integer.valueOf(0)).show();
        }
    }
}
