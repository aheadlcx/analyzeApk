package qsbk.app.utils;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.datastore.GroupStore;
import qsbk.app.model.GroupInfo;

public class JoinedGroupGetter {
    private static CallBack a;
    private static ArrayList<CallBack> b;
    private static Boolean c = null;

    public interface CallBack {
        void onFail(int i, String str);

        void onSuccess(List<GroupInfo> list);
    }

    private static class a {
        static Map<String, a> a = new HashMap();
        long b;
        GroupInfo c;

        private a() {
        }

        static GroupInfo a(String str) {
            a aVar = (a) a.get(str);
            if (aVar == null || Math.abs(aVar.b - SystemClock.elapsedRealtime()) > 60000) {
                return null;
            }
            return aVar.c;
        }

        static void a(GroupInfo groupInfo) {
            if (groupInfo != null) {
                a aVar = (a) a.get(String.valueOf(groupInfo.id));
                if (aVar == null) {
                    aVar = new a();
                }
                aVar.c = groupInfo;
                aVar.b = SystemClock.elapsedRealtime();
                a.put(String.valueOf(groupInfo.id), aVar);
            }
        }
    }

    public static void getJoinedGroups(CallBack callBack) {
        if (!hasGet()) {
            getJoinedGroupsFromServer(callBack);
        } else if (callBack != null) {
            callBack.onSuccess(getJoinedGroupsFromLocal());
        }
    }

    public static void getJoinedGroup(int i, CallBack callBack) {
        if (hasGet()) {
            GroupInfo joinedGroupFromLocal = getJoinedGroupFromLocal(String.valueOf(i));
            if (joinedGroupFromLocal == null || joinedGroupFromLocal.id != i) {
                getJoinedGroupFromServer(i, callBack);
                return;
            } else if (callBack != null) {
                List arrayList = new ArrayList();
                arrayList.add(joinedGroupFromLocal);
                callBack.onSuccess(arrayList);
                return;
            } else {
                return;
            }
        }
        getJoinedGroupFromServer(i, callBack);
    }

    public static boolean hasGet() {
        if (c == null) {
            c = Boolean.valueOf(SharePreferenceUtils.getSharePreferencesBoolValue("get_joined_group_" + QsbkApp.currentUser.userId));
        }
        return c.booleanValue();
    }

    private static void b(boolean z, ArrayList<GroupInfo> arrayList, int i, String str) {
        if (b != null) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                CallBack callBack = (CallBack) it.next();
                if (z) {
                    callBack.onSuccess(arrayList);
                } else {
                    callBack.onFail(i, str);
                }
            }
        } else if (z) {
            a.onSuccess(arrayList);
        } else {
            a.onFail(i, str);
        }
        b = null;
        a = null;
    }

    private static boolean a(CallBack callBack) {
        if (a != null) {
            if (b == null) {
                b = new ArrayList();
                b.add(a);
            }
            a = callBack;
            b.add(callBack);
            return true;
        }
        a = callBack;
        return false;
    }

    public static void getJoinedGroupFromServer(int i, CallBack callBack) {
        getJoinedGroupsFromServer(new ak(i, callBack));
    }

    public static GroupInfo getJoinedGroupFromLocal(String str) {
        GroupInfo a = a.a(str);
        if (a != null) {
            return a;
        }
        a = GroupStore.getInstance(QsbkApp.currentUser.userId).get(str);
        a.a(a);
        return a;
    }

    public static List<GroupInfo> getJoinedGroupsFromLocal() {
        List<GroupInfo> joinedGroups = GroupStore.getInstance(QsbkApp.currentUser.userId).getJoinedGroups();
        for (GroupInfo a : joinedGroups) {
            a.a(a);
        }
        return joinedGroups;
    }

    public static void getJoinedGroupsFromServer(CallBack callBack) {
        if (!a(callBack) && QsbkApp.currentUser != null) {
            String str = QsbkApp.currentUser.userId;
            new SimpleHttpTask(Constants.URL_MY_GROUP_LIST + "?brief=1", new al(str, GroupStore.getInstance(str))).execute();
        }
    }
}
