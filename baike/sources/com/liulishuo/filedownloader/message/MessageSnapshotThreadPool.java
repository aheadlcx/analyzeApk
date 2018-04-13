package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.message.MessageSnapshotFlow.MessageReceiver;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MessageSnapshotThreadPool {
    private final List<FlowSingleExecutor> a = new ArrayList();
    private final MessageReceiver b;

    public class FlowSingleExecutor {
        final /* synthetic */ MessageSnapshotThreadPool a;
        private final List<Integer> b = new ArrayList();
        private final Executor c;

        public FlowSingleExecutor(MessageSnapshotThreadPool messageSnapshotThreadPool, int i) {
            this.a = messageSnapshotThreadPool;
            this.c = FileDownloadExecutors.newDefaultThreadPool(1, "Flow-" + i);
        }

        public void enqueue(int i) {
            this.b.add(Integer.valueOf(i));
        }

        public void execute(MessageSnapshot messageSnapshot) {
            this.c.execute(new c(this, messageSnapshot));
        }
    }

    MessageSnapshotThreadPool(int i, MessageReceiver messageReceiver) {
        this.b = messageReceiver;
        for (int i2 = 0; i2 < i; i2++) {
            this.a.add(new FlowSingleExecutor(this, i2));
        }
    }

    public void execute(MessageSnapshot messageSnapshot) {
        FlowSingleExecutor flowSingleExecutor = null;
        try {
            synchronized (this.a) {
                int id = messageSnapshot.getId();
                for (FlowSingleExecutor flowSingleExecutor2 : this.a) {
                    if (flowSingleExecutor2.b.contains(Integer.valueOf(id))) {
                        flowSingleExecutor = flowSingleExecutor2;
                        break;
                    }
                }
                if (flowSingleExecutor == null) {
                    int i = 0;
                    for (FlowSingleExecutor flowSingleExecutor22 : this.a) {
                        if (flowSingleExecutor22.b.size() <= 0) {
                            flowSingleExecutor = flowSingleExecutor22;
                            break;
                        }
                        int i2;
                        FlowSingleExecutor flowSingleExecutor3;
                        if (i != 0) {
                            if (flowSingleExecutor22.b.size() >= i) {
                                i2 = i;
                                flowSingleExecutor3 = flowSingleExecutor;
                                flowSingleExecutor = flowSingleExecutor3;
                                i = i2;
                            }
                        }
                        flowSingleExecutor3 = flowSingleExecutor22;
                        i2 = flowSingleExecutor22.b.size();
                        flowSingleExecutor = flowSingleExecutor3;
                        i = i2;
                    }
                }
                flowSingleExecutor.enqueue(id);
            }
        } finally {
            flowSingleExecutor.execute(messageSnapshot);
        }
    }
}
