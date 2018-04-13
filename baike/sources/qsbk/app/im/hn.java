package qsbk.app.im;

import java.util.Comparator;

final class hn implements Comparator<ContactListItem> {
    hn() {
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
