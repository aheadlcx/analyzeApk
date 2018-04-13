package cn.v6.sixrooms.room;

import android.app.Dialog;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.bean.OperatorFlowBean;
import cn.v6.sixrooms.room.engine.OperatorFlowEngine.CallBack;
import cn.v6.sixrooms.utils.HandleErrorUtils;

final class w implements CallBack {
    final /* synthetic */ RoomActivity a;

    w(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void result(OperatorFlowBean operatorFlowBean) {
        V6Coop.isGetOperatorFlow = false;
        if (this.a.mDialogUtils == null) {
            this.a.a();
        }
        Dialog createConfirmDialog = this.a.mDialogUtils.createConfirmDialog(0, "提示", operatorFlowBean.getMsg(), this.a.getResources().getString(R.string.cancel), this.a.getResources().getString(R.string.confirm), new x(this, operatorFlowBean));
        if (!createConfirmDialog.isShowing()) {
            createConfirmDialog.show();
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        V6Coop.isGetOperatorFlow = false;
        HandleErrorUtils.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        V6Coop.isGetOperatorFlow = false;
    }
}
