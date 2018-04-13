package cn.v6.sixrooms.room;

final class i implements Runnable {
    final /* synthetic */ PublicNoticeBean a;
    final /* synthetic */ c b;

    i(c cVar, PublicNoticeBean publicNoticeBean) {
        this.b = cVar;
        this.a = publicNoticeBean;
    }

    public final void run() {
        this.b.a.processPublicNotice(this.a);
    }
}
