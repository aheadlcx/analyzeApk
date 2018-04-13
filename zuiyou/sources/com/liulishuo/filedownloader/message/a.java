package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.g.f;

public interface a {

    public static class a extends MessageSnapshot implements a {
        private final MessageSnapshot b;

        public a(MessageSnapshot messageSnapshot) {
            super(messageSnapshot.m());
            if (messageSnapshot.b() != (byte) -3) {
                throw new IllegalArgumentException(f.a("can't create the block complete message for id[%d], status[%d]", Integer.valueOf(messageSnapshot.m()), Byte.valueOf(messageSnapshot.b())));
            } else {
                this.b = messageSnapshot;
            }
        }

        public MessageSnapshot n_() {
            return this.b;
        }

        public byte b() {
            return (byte) 4;
        }
    }

    MessageSnapshot n_();
}
