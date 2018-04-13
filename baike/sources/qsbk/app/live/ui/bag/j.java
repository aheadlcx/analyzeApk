package qsbk.app.live.ui.bag;

import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;

class j extends SpanSizeLookup {
    final /* synthetic */ BagAdapter b;
    final /* synthetic */ BagFragment c;

    j(BagFragment bagFragment, BagAdapter bagAdapter) {
        this.c = bagFragment;
        this.b = bagAdapter;
    }

    public int getSpanSize(int i) {
        if (i >= this.b.getItems().size()) {
            return 2;
        }
        return 1;
    }
}
