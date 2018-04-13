package qsbk.app.live.ui.family;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

class z implements OnClickListener {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ FamilyDetailActivity b;

    z(FamilyDetailActivity familyDetailActivity, PopupWindow popupWindow) {
        this.b = familyDetailActivity;
        this.a = popupWindow;
    }

    public void onClick(View view) {
        this.a.dismiss();
        this.b.k();
    }
}
