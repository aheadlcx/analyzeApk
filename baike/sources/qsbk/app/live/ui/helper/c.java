package qsbk.app.live.ui.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.core.model.User;

final class c implements OnClickListener {
    final /* synthetic */ User a;
    final /* synthetic */ User b;

    c(User user, User user2) {
        this.a = user;
        this.b = user2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                InspectHelper.b(this.a, this.b, "1");
                return;
            case 1:
                InspectHelper.b(this.a, this.b, "2");
                return;
            case 2:
                InspectHelper.b(this.a, this.b, "3");
                return;
            case 3:
                InspectHelper.b(this.a, this.b, "4");
                return;
            default:
                return;
        }
    }
}
