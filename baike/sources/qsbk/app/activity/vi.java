package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;

class vi implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ MyInfoActivity b;

    vi(MyInfoActivity myInfoActivity, String str) {
        this.b = myInfoActivity;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = Constants.REL_MOVEOFF_BLACKLIST;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        str = String.format(str, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", this.b.b);
        this.b.a(Constants.REL_MOVEOFF_BLACKLIST, str, hashMap, this.a);
        dialogInterface.cancel();
    }
}
