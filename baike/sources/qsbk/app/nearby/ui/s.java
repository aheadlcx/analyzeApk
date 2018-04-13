package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;

class s implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    s(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(View view) {
        this.a.hideSoftKeyboard();
        this.a.E();
    }
}
