package qsbk.app.share.message;

import java.util.Comparator;
import qsbk.app.im.ContactListItem;

final class e implements Comparator<ContactListItem> {
    e() {
    }

    public int compare(ContactListItem contactListItem, ContactListItem contactListItem2) {
        long j = contactListItem2.mLastUpdateTime - contactListItem.mLastUpdateTime;
        if (j == 0) {
            return 0;
        }
        if (j > 0) {
            return 1;
        }
        return -1;
    }
}
