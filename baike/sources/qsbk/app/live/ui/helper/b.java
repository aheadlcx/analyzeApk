package qsbk.app.live.ui.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.core.model.User;

final class b implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ User b;
    final /* synthetic */ User c;

    b(Context context, User user, User user2) {
        this.a = context;
        this.b = user;
        this.c = user2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                InspectHelper.b(this.a, this.b, this.c);
                return;
            case 1:
                InspectHelper.b(this.b, this.c);
                return;
            default:
                return;
        }
    }
}
