package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.security.EmailBindActivity;

class acd implements OnClickListener {
    final /* synthetic */ acc a;

    acd(acc acc) {
        this.a = acc;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.a.l()) {
            switch (i) {
                case 0:
                    this.a.a.j();
                    break;
                case 1:
                    this.a.a.b(EmailBindActivity.ACTION_UNBIND);
                    break;
            }
        } else if (i == 0) {
            this.a.a.b(EmailBindActivity.ACTION_UNBIND);
        }
        dialogInterface.dismiss();
    }
}
