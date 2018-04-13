package qsbk.app.core.ui;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ BrowseLargeImageActivity a;

    d(BrowseLargeImageActivity browseLargeImageActivity) {
        this.a = browseLargeImageActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
