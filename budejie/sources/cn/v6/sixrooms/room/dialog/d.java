package cn.v6.sixrooms.room.dialog;

import android.view.View;
import android.view.View.OnClickListener;

final class d implements OnClickListener {
    final /* synthetic */ SpectatorsDialog a;

    d(SpectatorsDialog spectatorsDialog) {
        this.a = spectatorsDialog;
    }

    public final void onClick(View view) {
        this.a.n.openGuard();
    }
}
