package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.util.FileDownloadUtils;
import junit.framework.Assert;

public interface BlockCompleteMessage {

    public static class BlockCompleteMessageImpl extends MessageSnapshot implements BlockCompleteMessage {
        private final MessageSnapshot b;

        public BlockCompleteMessageImpl(MessageSnapshot messageSnapshot) {
            boolean z = true;
            super(messageSnapshot.getId());
            String formatString = FileDownloadUtils.formatString("can't create the block complete message for id[%d], status[%d]", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus()));
            if (messageSnapshot.getStatus() != (byte) -3) {
                z = false;
            }
            Assert.assertTrue(formatString, z);
            this.b = messageSnapshot;
        }

        public MessageSnapshot transmitToCompleted() {
            return this.b;
        }

        public byte getStatus() {
            return (byte) 4;
        }
    }

    MessageSnapshot transmitToCompleted();
}
