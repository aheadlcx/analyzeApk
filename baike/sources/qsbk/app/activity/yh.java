package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class yh implements OnClickListener {
    final /* synthetic */ NoAvailableSpaceActivity a;

    yh(NoAvailableSpaceActivity noAvailableSpaceActivity) {
        this.a = noAvailableSpaceActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.finish();
        this.a.b();
    }
}
