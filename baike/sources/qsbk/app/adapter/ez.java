package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;

class ez implements OnClickListener {
    final /* synthetic */ VideoImmersionCell a;

    ez(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onClick(View view) {
        this.a.a.l.getOnItemLongClickListener().onItemLongClick(this.a.a.l, this.a.getCellView(), this.a.q + this.a.a.l.getHeaderViewsCount(), (long) this.a.q);
    }
}
