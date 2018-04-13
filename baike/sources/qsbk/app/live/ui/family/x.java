package qsbk.app.live.ui.family;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.PopupWindow;

class x implements OnKeyListener {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ FamilyDetailActivity b;

    x(FamilyDetailActivity familyDetailActivity, PopupWindow popupWindow) {
        this.b = familyDetailActivity;
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
