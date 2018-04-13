package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.model.BaseUserInfo;

class dq implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ int b;
    final /* synthetic */ UnSubscribeAdapter c;

    dq(UnSubscribeAdapter unSubscribeAdapter, BaseUserInfo baseUserInfo, int i) {
        this.c = unSubscribeAdapter;
        this.a = baseUserInfo;
        this.b = i;
    }

    public void onClick(View view) {
        Map hashMap = new HashMap();
        hashMap.put("user_id", this.a.userId);
        this.c.a(Constants.SUBSCRIBE_TA, Constants.SUBSCRIBE_TA, hashMap, this.b);
    }
}
