package qsbk.app.live.adapter;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.PopupWindow;

class i implements OnKeyListener {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ FamilyAllMemberAdapter b;

    i(FamilyAllMemberAdapter familyAllMemberAdapter, PopupWindow popupWindow) {
        this.b = familyAllMemberAdapter;
        this.a = popupWindow;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 82 || keyEvent.getRepeatCount() != 0 || keyEvent.getAction() != 0) {
            return false;
        }
        if (this.a != null && this.a.isShowing()) {
            this.a.dismiss();
        }
        return true;
    }
}
