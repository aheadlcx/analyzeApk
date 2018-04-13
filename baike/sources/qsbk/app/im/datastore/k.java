package qsbk.app.im.datastore;

import java.util.Comparator;
import qsbk.app.im.ChatMsg;

final class k implements Comparator<ChatMsg> {
    k() {
    }

    public int compare(ChatMsg chatMsg, ChatMsg chatMsg2) {
        long j = chatMsg.time - chatMsg2.time;
        if (j == 0) {
            return 0;
        }
        if (j > 0) {
            return 1;
        }
        return -1;
    }
}
