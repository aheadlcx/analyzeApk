package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;
import qsbk.app.R;
import qsbk.app.nearby.api.NearbyEngine;

class p implements OnClickListener {
    final /* synthetic */ BaseNearbyFragment a;

    p(BaseNearbyFragment baseNearbyFragment) {
        this.a = baseNearbyFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a("dialog_ok");
        if (((CheckBox) this.a.c.findViewById(R.id.checkBox)).isChecked()) {
            NearbyEngine.instance().setNearbyNoMoreWarn();
        }
        this.a.c = null;
        dialogInterface.dismiss();
        this.a.startLocate();
        this.a.d = false;
    }
}
