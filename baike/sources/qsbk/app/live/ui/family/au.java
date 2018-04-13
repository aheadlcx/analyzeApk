package qsbk.app.live.ui.family;

import android.view.View;
import android.view.View.OnClickListener;

class au implements OnClickListener {
    final /* synthetic */ FamilyMessageActivity a;

    au(FamilyMessageActivity familyMessageActivity) {
        this.a = familyMessageActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
