package qsbk.app.utils;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.QsbkApp;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.datastore.GroupStore;
import qsbk.app.model.GroupInfo;

public class GroupNotifier {
    public static final int KICKED = 2;
    public static final int QUIT = 3;
    public static final int UPDATE = 1;
    public static ArrayList<OnNotifyListener> listeners;

    public interface OnNotifyListener {
        void onGroupNotify(int i, int i2);
    }

    public static void register(OnNotifyListener onNotifyListener) {
        if (listeners == null) {
            listeners = new ArrayList();
        }
        if (!listeners.contains(onNotifyListener)) {
            listeners.add(onNotifyListener);
        }
    }

    public static void unregister(OnNotifyListener onNotifyListener) {
        listeners.remove(onNotifyListener);
        if (listeners.size() == 0) {
            listeners = null;
        }
    }

    public static void notify(int i, int i2) {
        if (listeners != null) {
            Iterator it = listeners.iterator();
            while (it.hasNext()) {
                ((OnNotifyListener) it.next()).onGroupNotify(i, i2);
            }
        }
    }

    public static void updateGroupInfo(String str, String str2, String str3) {
        ContactListItemStore contactStore = ContactListItemStore.getContactStore(QsbkApp.currentUser.userId);
        ContactListItem withGroup = contactStore.getWithGroup(str);
        if (withGroup != null) {
            if (str2 != null) {
                withGroup.name = str2;
            }
            if (!TextUtils.isEmpty(str3)) {
                withGroup.icon = str3;
            }
            contactStore.update(withGroup);
        }
        GroupStore instance = GroupStore.getInstance(QsbkApp.currentUser.userId);
        GroupInfo groupInfo = instance.get(str);
        if (groupInfo != null) {
            if (str2 != null) {
                groupInfo.name = str2;
            }
            if (str3 != null) {
                groupInfo.icon = str3;
            }
            instance.update(groupInfo);
        }
    }
}
