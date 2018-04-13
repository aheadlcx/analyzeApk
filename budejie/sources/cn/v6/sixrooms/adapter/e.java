package cn.v6.sixrooms.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.pojo.HistroyWatch;
import cn.v6.sixrooms.utils.phone.HistoryDbTool;

final class e implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ HistoryBaseAdapter b;

    e(HistoryBaseAdapter historyBaseAdapter, int i) {
        this.b = historyBaseAdapter;
        this.a = i;
    }

    public final void onClick(View view) {
        if (this.a < HistoryBaseAdapter.a(this.b).size()) {
            HistoryDbTool.delete(HistoryBaseAdapter.b(this.b), ((HistroyWatch) HistoryBaseAdapter.a(this.b).get(this.a)).getRid());
            this.b.setRes();
            this.b.notifyDataSetChanged();
            HistoryBaseAdapter.c(this.b).delete();
        }
    }
}
