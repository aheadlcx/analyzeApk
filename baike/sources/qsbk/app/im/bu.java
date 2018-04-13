package qsbk.app.im;

import android.util.Pair;
import java.util.List;
import qsbk.app.utils.comm.ArrayUtils;

class bu implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ bt b;

    bu(bt btVar, List list) {
        this.b = btVar;
        this.a = list;
    }

    public void run() {
        Pair mergeUnExistMsg = this.b.a.g.mergeUnExistMsg(this.a);
        if (!ArrayUtils.isEmpty((List) mergeUnExistMsg.second)) {
            this.b.a.g.notifyDataSetChanged();
            this.b.a.a((List) mergeUnExistMsg.second);
        } else if (((Boolean) mergeUnExistMsg.first).booleanValue()) {
            this.b.a.g.notifyDataSetChanged();
        }
    }
}
