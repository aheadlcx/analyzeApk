package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

final class ad implements OnClickListener {
    final /* synthetic */ EventActivity a;

    ad(EventActivity eventActivity) {
        this.a = eventActivity;
    }

    public final void onClick(View view) {
        this.a.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
    }
}
