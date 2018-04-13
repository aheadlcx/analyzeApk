package qsbk.app.service;

import qsbk.app.QsbkApp;

class m implements Runnable {
    final /* synthetic */ VoteHandler a;

    m(VoteHandler voteHandler) {
        this.a = voteHandler;
    }

    public void run() {
        QsbkApp.getVoteHandler().obtainMessage().sendToTarget();
    }
}
