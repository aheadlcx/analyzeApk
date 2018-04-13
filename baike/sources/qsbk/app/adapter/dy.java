package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;

class dy implements Runnable {
    final /* synthetic */ VideoImmersionCell a;

    dy(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void run() {
        this.a.loop.setText(this.a.article.getLoopString());
    }
}
