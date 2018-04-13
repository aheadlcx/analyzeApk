package cn.v6.sixrooms.room.view;

import android.view.View;
import android.view.View.OnClickListener;

final class e implements OnClickListener {
    final /* synthetic */ EditDialog a;

    e(EditDialog editDialog) {
        this.a = editDialog;
    }

    public final void onClick(View view) {
        if (this.a.a != null) {
            this.a.a.ok();
        }
    }
}
