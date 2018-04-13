package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class cd implements OnClickListener {
    final /* synthetic */ GameMVPDialog a;

    cd(GameMVPDialog gameMVPDialog) {
        this.a = gameMVPDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
