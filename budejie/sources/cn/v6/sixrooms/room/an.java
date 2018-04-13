package cn.v6.sixrooms.room;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class an implements DialogListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ RoomActivity c;

    an(RoomActivity roomActivity, String str, String str2) {
        this.c = roomActivity;
        this.a = str;
        this.b = str2;
    }

    public final void positive(int i) {
        this.c.resetData(this.a, this.b);
    }

    public final void negative(int i) {
    }
}
