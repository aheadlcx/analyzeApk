package qsbk.app.share;

import android.view.View;
import android.view.View.OnClickListener;

class j implements OnClickListener {
    final /* synthetic */ NewShareActivity a;

    j(NewShareActivity newShareActivity) {
        this.a = newShareActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
