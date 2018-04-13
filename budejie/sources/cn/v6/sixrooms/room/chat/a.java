package cn.v6.sixrooms.room.chat;

import android.text.style.ClickableSpan;
import android.view.View;
import cn.v6.sixrooms.room.RoomActivity;

final class a extends ClickableSpan {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ChatListAdapter c;

    a(ChatListAdapter chatListAdapter, String str, String str2) {
        this.c = chatListAdapter;
        this.a = str;
        this.b = str2;
    }

    public final void onClick(View view) {
        ((RoomActivity) this.c.c).resetData(this.a, this.b);
    }
}
