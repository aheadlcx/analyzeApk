package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;

class my implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ mx b;

    my(mx mxVar, String str) {
        this.b = mxVar;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.URL_MOTIFY_GROUP_INFO, new Object[]{Integer.valueOf(this.b.a.d)}), new mz(this, dialogInterface));
            Map hashMap = new HashMap();
            hashMap.put(b.c, String.valueOf(this.b.a.d));
            hashMap.put("description", this.a);
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        dialogInterface.dismiss();
    }
}
