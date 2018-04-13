package cn.v6.sixrooms.room;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;
import cn.v6.sixrooms.utils.ImprovedProgressDialog;

final class as implements DialogListener {
    final /* synthetic */ RoomActivity a;

    as(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void positive(int i) {
        if (this.a.mProDialog == null) {
            this.a.mProDialog = new ImprovedProgressDialog(this.a, this.a.getString(R.string.deal_with));
        }
        if (!this.a.mProDialog.isShowing()) {
            this.a.mProDialog.show();
        }
    }

    public final void negative(int i) {
    }
}
