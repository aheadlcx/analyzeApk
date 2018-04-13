package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;

class ij implements OnClickListener {
    final /* synthetic */ ih a;

    ij(ih ihVar) {
        this.a = ihVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Map hashMap = new HashMap();
        hashMap.put("pid", this.a.a.i);
        QsbkApp.getInstance();
        hashMap.put("uid", QsbkApp.currentUser.userId);
        hashMap.put("cancel", Integer.valueOf(1));
        this.a.a.a(Constants.OFFICIAL_SUBSCRIBE + "CANCEL", Constants.OFFICIAL_SUBSCRIBE, hashMap);
    }
}
