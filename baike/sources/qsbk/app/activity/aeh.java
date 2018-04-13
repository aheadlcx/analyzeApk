package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;

class aeh implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ aeg b;

    aeh(aeg aeg, String str) {
        this.b = aeg;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            Map hashMap = new HashMap();
            hashMap.put("user_name", QsbkApp.currentUser.userName);
            hashMap.put("content", this.a);
            hashMap.put("article_id", this.b.a.d);
            hashMap.put("user_id", QsbkApp.currentUser.userId);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(this.b.a.e, new aei(this, dialogInterface));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        dialogInterface.dismiss();
    }
}
