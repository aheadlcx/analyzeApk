package qsbk.app.model;

import qsbk.app.model.EvaluateCard.ViewHolder;
import qsbk.app.widget.EasyRatingBar;
import qsbk.app.widget.EasyRatingBar.OnRateListener;

class j implements OnRateListener {
    final /* synthetic */ ViewHolder a;

    j(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void onRate(EasyRatingBar easyRatingBar, int i) {
        EvaluateCard.d = i;
        this.a.a(i);
    }
}
