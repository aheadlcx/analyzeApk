package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bx implements OnClickListener {
    final /* synthetic */ GameHelpDialog a;

    bx(GameHelpDialog gameHelpDialog) {
        this.a = gameHelpDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
