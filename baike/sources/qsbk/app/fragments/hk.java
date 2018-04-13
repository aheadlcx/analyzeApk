package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;
import qsbk.app.R;
import qsbk.app.nearby.api.NearbyEngine;

class hk implements OnClickListener {
    final /* synthetic */ NearbyUsersFragment a;

    hk(NearbyUsersFragment nearbyUsersFragment) {
        this.a = nearbyUsersFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (((CheckBox) this.a.v.findViewById(R.id.checkBox)).isChecked()) {
            NearbyEngine.instance().setNearbyNoMoreWarnInfoComplete();
        }
        dialogInterface.dismiss();
        this.a.v = null;
        this.a.f();
    }
}
