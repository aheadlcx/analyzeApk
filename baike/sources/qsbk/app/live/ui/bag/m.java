package qsbk.app.live.ui.bag;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.LiveMarketDataRecord;

class m implements OnClickListener {
    final /* synthetic */ LiveMarketDataRecord a;
    final /* synthetic */ MarketAdapter b;

    m(MarketAdapter marketAdapter, LiveMarketDataRecord liveMarketDataRecord) {
        this.b = marketAdapter;
        this.a = liveMarketDataRecord;
    }

    public void onClick(View view) {
        if (this.b.c != null) {
            this.b.c.onItemClick(this.a);
        }
    }
}
