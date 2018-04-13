package qsbk.app.live.ui.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.core.model.User;

final class a implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ User b;
    final /* synthetic */ User c;

    a(Context context, User user, User user2) {
        this.a = context;
        this.b = user;
        this.c = user2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                InspectHelper.b(this.a, this.b);
                return;
            case 1:
                InspectHelper.b(this.a, this.c, this.b);
                return;
            case 2:
                InspectHelper.b(this.c, this.b);
                return;
            case 3:
                InspectHelper.b(this.b);
                return;
            case 4:
                InspectHelper.b(this.a, this.b, "1");
                return;
            case 5:
                InspectHelper.b(this.a, this.b, "2");
                return;
            default:
                return;
        }
    }
}
