package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class x implements OnClickListener {
    final /* synthetic */ FamilyCardDialog a;

    x(FamilyCardDialog familyCardDialog) {
        this.a = familyCardDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
