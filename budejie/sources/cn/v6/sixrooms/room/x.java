package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.bean.OperatorFlowBean;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class x implements DialogListener {
    final /* synthetic */ OperatorFlowBean a;
    final /* synthetic */ w b;

    x(w wVar, OperatorFlowBean operatorFlowBean) {
        this.b = wVar;
        this.a = operatorFlowBean;
    }

    public final void positive(int i) {
        this.b.a.startEventActivity(this.a.getActivityUrl(), "");
    }

    public final void negative(int i) {
    }
}
