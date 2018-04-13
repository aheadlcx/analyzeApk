package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.adapter.HistoryBaseAdapter;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;
import cn.v6.sixrooms.utils.phone.HistoryDbTool;

final class bb implements DialogListener {
    final /* synthetic */ ba a;

    bb(ba baVar) {
        this.a = baVar;
    }

    public final void positive(int i) {
        HistoryDbTool.deleteAll(this.a.a);
        HistoryActivity.a(this.a.a, new HistoryBaseAdapter(this.a.a, HistoryActivity.b(this.a.a)));
        HistoryActivity.d(this.a.a).setAdapter(HistoryActivity.c(this.a.a));
        HistoryActivity.e(this.a.a);
    }

    public final void negative(int i) {
    }
}
