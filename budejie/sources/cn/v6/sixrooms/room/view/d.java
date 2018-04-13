package cn.v6.sixrooms.room.view;

import android.view.View;
import android.view.View.OnClickListener;

final class d implements OnClickListener {
    final /* synthetic */ EditDialog a;

    d(EditDialog editDialog) {
        this.a = editDialog;
    }

    public final void onClick(View view) {
        if (this.a.a != null) {
            this.a.a.cancle();
        }
    }
}
