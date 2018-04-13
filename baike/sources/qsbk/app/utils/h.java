package qsbk.app.utils;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.http.SimpleHttpTask;

final class h implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ Context b;

    h(List list, Context context) {
        this.a = list;
        this.b = context;
    }

    public void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            String format = String.format(Constants.CIRCLE_ARTICLE_VOTE, new Object[]{aVar.a});
            Map hashMap = new HashMap();
            hashMap.put("option", aVar.b);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new i(this, it, aVar));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.syncRun();
        }
        CircleVoteBuffer.b(this.b, this.a);
    }
}
