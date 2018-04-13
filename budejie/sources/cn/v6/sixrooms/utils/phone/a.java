package cn.v6.sixrooms.utils.phone;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;
import cn.v6.sixrooms.room.RoomActivity;

final class a extends ClickableSpan {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    a(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void onClick(View view) {
        ((RoomActivity) this.a).showEnterRoomDialog(this.b);
    }
}
