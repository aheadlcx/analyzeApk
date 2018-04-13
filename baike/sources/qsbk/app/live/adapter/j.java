package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import qsbk.app.live.model.FamilyMemberData;

class j implements OnClickListener {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ FamilyMemberData b;
    final /* synthetic */ FamilyAllMemberAdapter c;

    j(FamilyAllMemberAdapter familyAllMemberAdapter, PopupWindow popupWindow, FamilyMemberData familyMemberData) {
        this.c = familyAllMemberAdapter;
        this.a = popupWindow;
        this.b = familyMemberData;
    }

    public void onClick(View view) {
        this.a.dismiss();
        this.c.a(this.b);
    }
}
