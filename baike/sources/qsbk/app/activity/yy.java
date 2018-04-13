package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;

class yy implements OnItemClickListener {
    final /* synthetic */ OtherInfoActivity a;

    yy(OtherInfoActivity otherInfoActivity) {
        this.a = otherInfoActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        OtherInfoActivity.h(this.a).dismiss();
        if (i == 0) {
            Map hashMap = new HashMap();
            hashMap.put("user_id", OtherInfoActivity.d(this.a));
            if (OtherInfoActivity.i(this.a)) {
                OtherInfoActivity.a(this.a, Constants.SUBSCRIBE_TA, Constants.SUBSCRIBE_TA, hashMap);
            } else {
                new Builder(this.a).setTitle("是否确定不再订阅TA的糗事").setMessage("不订阅后，专享列表将不会收到TA的相关糗事").setPositiveButton("再想想", new za(this)).setNegativeButton("不订阅", new yz(this, hashMap)).create().show();
            }
        }
    }
}
