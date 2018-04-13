package cn.v6.sixrooms.ui.phone.input;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class j implements DialogListener {
    final /* synthetic */ BaseRoomInputDialog a;

    j(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void positive(int i) {
        this.a.sendFlyText();
    }

    public final void negative(int i) {
    }
}
