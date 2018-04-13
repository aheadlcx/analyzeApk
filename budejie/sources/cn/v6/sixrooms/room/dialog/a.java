package cn.v6.sixrooms.room.dialog;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

final class a implements OnCheckedChangeListener {
    final /* synthetic */ FansDialog a;

    a(FansDialog fansDialog) {
        this.a = fansDialog;
    }

    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        FansDialog.a(this.a, i);
        FansDialog.b(this.a, i);
        this.a.p.changeToFansList(i, this.a.mWrapRoomInfo.getRoominfoBean().getId());
    }
}
