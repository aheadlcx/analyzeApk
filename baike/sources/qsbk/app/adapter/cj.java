package qsbk.app.adapter;

import qsbk.app.adapter.ManageMyContentsAdapter.ViewHolder;

class cj implements Runnable {
    final /* synthetic */ ViewHolder a;

    cj(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void run() {
        this.a.loop.setText(this.a.article.getLoopString());
    }
}
