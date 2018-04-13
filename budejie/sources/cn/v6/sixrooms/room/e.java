package cn.v6.sixrooms.room;

final class e implements Runnable {
    final /* synthetic */ InitHeadLineBean a;
    final /* synthetic */ c b;

    e(c cVar, InitHeadLineBean initHeadLineBean) {
        this.b = cVar;
        this.a = initHeadLineBean;
    }

    public final void run() {
        for (OnChatMsgSocketCallBack updateHeadLineMsg : this.b.a.j) {
            updateHeadLineMsg.updateHeadLineMsg(this.a);
        }
    }
}
