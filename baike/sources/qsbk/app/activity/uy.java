package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

class uy implements OnClickListener {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ MyInfoActivity b;

    uy(MyInfoActivity myInfoActivity, PopupWindow popupWindow) {
        this.b = myInfoActivity;
        this.a = popupWindow;
    }

    public void onClick(View view) {
        if (this.a.isShowing()) {
            this.a.dismiss();
        }
        this.b.blackPersonal(5);
    }
}
