package cn.v6.sixrooms.presenter;

final class k implements Runnable {
    final /* synthetic */ RedPresenter a;

    k(RedPresenter redPresenter) {
        this.a = redPresenter;
    }

    public final void run() {
        this.a.i.sendEmptyMessage(0);
    }
}
