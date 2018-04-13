package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.http.HttpTask;

class aal implements OnClickListener {
    final /* synthetic */ HashMap a;
    final /* synthetic */ aaj b;

    aal(aaj aaj, HashMap hashMap) {
        this.b = aaj;
        this.a = hashMap;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a.d.getTopicSub().setClickable(false);
        this.b.a.o = new HttpTask(Constants.QIUSHI_TOPIC_UNSUBCRIBE, Constants.QIUSHI_TOPIC_UNSUBCRIBE, new aam(this));
        this.b.a.o.setMapParams(this.a);
        this.b.a.o.execute(new Void[0]);
    }
}
