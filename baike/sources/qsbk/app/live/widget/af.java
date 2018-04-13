package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class af implements OnClickListener {
    final /* synthetic */ FamilyGatherDialog a;

    af(FamilyGatherDialog familyGatherDialog) {
        this.a = familyGatherDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
