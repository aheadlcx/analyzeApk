package qsbk.app.adapter;

import qsbk.app.adapter.ArticleAdapter.ViewHolder;

class ad implements Runnable {
    final /* synthetic */ ViewHolder a;

    ad(ViewHolder viewHolder) {
        this.a = viewHolder;
    }

    public void run() {
        this.a.loop.setText(this.a.article.getLoopString());
    }
}
