package com.liulishuo.filedownloader.message;

public class MessageSnapshotFlow {
    private volatile MessageSnapshotThreadPool a;
    private volatile MessageReceiver b;

    public static final class HolderClass {
        private static final MessageSnapshotFlow a = new MessageSnapshotFlow();
    }

    public interface MessageReceiver {
        void receive(MessageSnapshot messageSnapshot);
    }

    public static MessageSnapshotFlow getImpl() {
        return HolderClass.a;
    }

    public void setReceiver(MessageReceiver messageReceiver) {
        this.b = messageReceiver;
        if (messageReceiver == null) {
            this.a = null;
        } else {
            this.a = new MessageSnapshotThreadPool(5, messageReceiver);
        }
    }

    public void inflow(MessageSnapshot messageSnapshot) {
        if (messageSnapshot instanceof IFlowDirectly) {
            if (this.b != null) {
                this.b.receive(messageSnapshot);
            }
        } else if (this.a != null) {
            this.a.execute(messageSnapshot);
        }
    }
}
