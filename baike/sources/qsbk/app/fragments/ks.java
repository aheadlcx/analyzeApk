package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;
import qsbk.app.R;
import qsbk.app.nearby.api.NearbyEngine;

class ks implements OnClickListener {
    final /* synthetic */ RandomDoorUsersFragment a;

    ks(RandomDoorUsersFragment randomDoorUsersFragment) {
        this.a = randomDoorUsersFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (((CheckBox) this.a.t.findViewById(R.id.checkBox)).isChecked()) {
            NearbyEngine.instance().setNearbyNoMoreWarnInfoComplete();
        }
        this.a.u = 1;
        dialogInterface.dismiss();
        this.a.t = null;
        this.a.s = 1;
        this.a.b(false);
    }
}
