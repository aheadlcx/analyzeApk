package qsbk.app.live.ui.bag;

import android.content.Intent;
import android.view.View;
import qsbk.app.live.ui.bag.BagAdapter.ItemClickListener;

class h implements ItemClickListener {
    final /* synthetic */ BagFragment a;

    h(BagFragment bagFragment) {
        this.a = bagFragment;
    }

    public void onClick(View view) {
        this.a.startActivityForResult(new Intent(this.a.getContext(), MarketActivity.class), 1101);
    }

    public void onRefresh() {
        this.a.refresh();
    }
}
