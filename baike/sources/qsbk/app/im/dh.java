package qsbk.app.im;

import android.util.Pair;
import java.util.List;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.comm.ArrayUtils;

class dh implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ dg b;

    dh(dg dgVar, List list) {
        this.b = dgVar;
        this.a = list;
    }

    public void run() {
        Pair mergeUnExistMsg = this.b.a.g.mergeUnExistMsg(this.a);
        if (!ArrayUtils.isEmpty((List) mergeUnExistMsg.second)) {
            this.b.a.g.notifyDataSetChanged();
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new di(this, mergeUnExistMsg));
        } else if (((Boolean) mergeUnExistMsg.first).booleanValue()) {
            this.b.a.g.notifyDataSetChanged();
        }
    }
}
