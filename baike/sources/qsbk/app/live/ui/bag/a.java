package qsbk.app.live.ui.bag;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ BagActivity a;

    a(BagActivity bagActivity) {
        this.a = bagActivity;
    }

    public void onClick(View view) {
        this.a.startActivityForResult(new Intent(this.a, MarketActivity.class), 1101);
    }
}
