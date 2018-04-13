package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.message.MessageSnapshotThreadPool.FlowSingleExecutor;

class c implements Runnable {
    final /* synthetic */ MessageSnapshot a;
    final /* synthetic */ FlowSingleExecutor b;

    c(FlowSingleExecutor flowSingleExecutor, MessageSnapshot messageSnapshot) {
        this.b = flowSingleExecutor;
        this.a = messageSnapshot;
    }

    public void run() {
        this.b.a.b.receive(this.a);
        this.b.b.remove(Integer.valueOf(this.a.getId()));
    }
}
