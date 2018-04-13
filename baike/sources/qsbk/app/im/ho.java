package qsbk.app.im;

import java.util.Comparator;

final class ho implements Comparator<ContactListItem> {
    ho() {
    }

    public int compare(ContactListItem contactListItem, ContactListItem contactListItem2) {
        long j = contactListItem.msgId - contactListItem2.msgId;
        if (j == 0) {
            return 0;
        }
        if (j > 0) {
            return 1;
        }
        return -1;
    }
}
