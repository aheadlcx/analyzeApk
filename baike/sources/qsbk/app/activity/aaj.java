package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpTask;

class aaj implements OnClickListener {
    final /* synthetic */ QiushiTopicActivity a;

    aaj(QiushiTopicActivity qiushiTopicActivity) {
        this.a = qiushiTopicActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.a.startActivity(new Intent(this.a, ActionBarLoginActivity.class));
            this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
            return;
        }
        Object hashMap = new HashMap();
        hashMap.put("topic_id", Integer.valueOf(this.a.a.id));
        if (this.a.a.isSubscribed) {
            new Builder(this.a).setCancelable(true).setMessage("是否取消订阅").setPositiveButton("不再订阅", new aal(this, hashMap)).setNegativeButton("继续订阅", null).create().show();
            return;
        }
        this.a.d.getTopicSub().setClickable(false);
        this.a.o = new HttpTask(Constants.QIUSHI_TOPIC_SUBCRIBE, Constants.QIUSHI_TOPIC_SUBCRIBE, new aak(this));
        this.a.o.setMapParams(hashMap);
        this.a.o.execute(new Void[0]);
    }
}
