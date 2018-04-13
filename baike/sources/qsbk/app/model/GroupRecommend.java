package qsbk.app.model;

import android.util.SparseArray;
import com.crashlytics.android.Crashlytics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.JoinedGroupGetter;
import qsbk.app.utils.SharePreferenceUtils;

public class GroupRecommend {
    private static GroupRecommend a;
    public static SparseArray<Boolean> changeSign = new SparseArray();
    public static ArrayList<WeakReference<GroupRecommendObserver>> listeners;
    public List<GroupItem> groups = new ArrayList();

    public interface GroupRecommendObserver {
        void onNewGroupRecommend(GroupRecommend groupRecommend);
    }

    public static class GroupItem {
        public String desc;
        public String icon;
        public int id;
        public int joinStatus;
        public List<BaseUserInfo> members;
        public String name;

        private static GroupItem b(JSONObject jSONObject) {
            try {
                GroupItem groupItem = new GroupItem();
                JSONArray optJSONArray = jSONObject.optJSONArray("member_list");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    groupItem.members = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        if (jSONObject2 != null) {
                            BaseUserInfo baseUserInfo = new BaseUserInfo();
                            baseUserInfo.parseBaseInfo(jSONObject2);
                            groupItem.members.add(baseUserInfo);
                        }
                    }
                }
                groupItem.id = jSONObject.getInt("id");
                groupItem.name = jSONObject.getString("name");
                groupItem.icon = jSONObject.optString("icon");
                groupItem.joinStatus = jSONObject.getInt("join_status");
                groupItem.desc = jSONObject.optString("description");
                return groupItem;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        private JSONObject a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", this.id);
                jSONObject.put("name", this.name);
                jSONObject.put("icon", this.icon);
                jSONObject.put("description", this.desc);
                jSONObject.put("join_status", this.joinStatus);
                if (this.members != null && this.members.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (BaseUserInfo toJSONObject : this.members) {
                        jSONArray.put(toJSONObject.toJSONObject());
                    }
                    jSONObject.put("member_list", jSONArray);
                }
                return jSONObject;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public GroupInfo toGroupInfo() {
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.id = this.id;
            groupInfo.name = this.name;
            groupInfo.icon = this.icon;
            groupInfo.joinStatus = this.joinStatus;
            return groupInfo;
        }
    }

    private static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    public static GroupRecommend getInstance() {
        if (a == null) {
            synchronized (GroupRecommend.class) {
                if (a == null) {
                    a = new GroupRecommend();
                }
            }
        }
        return a;
    }

    public static void register(GroupRecommendObserver groupRecommendObserver) {
        if (listeners == null) {
            listeners = new ArrayList();
        }
        listeners.add(new WeakReference(groupRecommendObserver));
        if (getInstance().groups.size() <= 0) {
            c(a());
            if (getInstance().groups.size() == 0) {
                d();
            } else if (groupRecommendObserver != null) {
                groupRecommendObserver.onNewGroupRecommend(getInstance());
            }
        } else if (groupRecommendObserver != null) {
            groupRecommendObserver.onNewGroupRecommend(getInstance());
        }
    }

    public static void unregister(GroupRecommendObserver groupRecommendObserver) {
        if (listeners != null) {
            int i = 0;
            while (i < listeners.size()) {
                WeakReference weakReference = (WeakReference) listeners.get(i);
                if (weakReference == null || weakReference.get() == null || weakReference.get() == groupRecommendObserver) {
                    listeners.remove(i);
                    i--;
                }
                i++;
            }
            if (listeners.size() == 0) {
                listeners = null;
            }
        }
    }

    public static void notifyChange() {
        int i = 0;
        for (int i2 = 0; i2 < changeSign.size(); i2++) {
            changeSign.setValueAt(i2, Boolean.valueOf(true));
        }
        if (listeners != null) {
            while (i < listeners.size()) {
                WeakReference weakReference = (WeakReference) listeners.get(i);
                if (weakReference == null || weakReference.get() == null) {
                    listeners.remove(i);
                    i--;
                } else {
                    ((GroupRecommendObserver) weakReference.get()).onNewGroupRecommend(getInstance());
                }
                i++;
            }
        }
    }

    public static void refresh() {
        a = null;
        int a = (a() + 5) - 1;
        d(a);
        c(a);
        if (getInstance().groups.size() == 0) {
            d();
        } else {
            notifyChange();
        }
    }

    public static void refresh(int i) {
        int a = a() + 1;
        d(a);
        ArrayList load = load();
        if (!(load == null || load.size() == 0)) {
            int size = load.size();
            GroupRecommend instance = getInstance();
            if (instance != null && i < instance.groups.size()) {
                if (a >= size) {
                    a %= size;
                }
                instance.groups.set(i, load.get(a));
            }
        }
        notifyChange();
    }

    private static void c(int i) {
        ArrayList load = load();
        long b = (long) b();
        getInstance().groups.clear();
        if (load != null && load.size() != 0 && b * 1000 >= System.currentTimeMillis()) {
            int size = load.size();
            int i2 = i;
            for (int i3 = 0; i3 < 5; i3++) {
                i2 = i + i3;
                if (i2 < size) {
                    getInstance().groups.add(load.get(i2));
                } else {
                    i2 %= size;
                    getInstance().groups.add(load.get(i2));
                }
            }
            d(i2);
        }
    }

    private static int a() {
        return SharePreferenceUtils.getSharePreferencesIntValue("GroupRecommendPos_" + (QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId));
    }

    private static void d(int i) {
        SharePreferenceUtils.setSharePreferencesValue("GroupRecommendPos_" + (QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId), i);
    }

    private static int b() {
        return SharePreferenceUtils.getSharePreferencesIntValue("GroupRecommendTime_" + (QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId));
    }

    private static void e(int i) {
        SharePreferenceUtils.setSharePreferencesValue("GroupRecommendTime_" + (QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId), i);
    }

    private static File c() {
        String sDPath = DeviceUtils.getSDPath();
        if (sDPath == null || sDPath.length() == 0) {
            sDPath = QsbkApp.getInstance().getFilesDir().getAbsolutePath();
        }
        String str = QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId;
        File file = new File(sDPath + File.separator + "qsbk/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, Integer.toHexString("GroupRecommend".hashCode()) + str);
    }

    public static void save(ArrayList<GroupItem> arrayList) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            jSONArray.put(((GroupItem) arrayList.get(i)).a());
        }
        String jSONArray2 = jSONArray.toString();
        try {
            FileWriter fileWriter = new FileWriter(c());
            fileWriter.write(jSONArray2);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<GroupItem> load() {
        ArrayList<GroupItem> arrayList;
        FileNotFoundException fileNotFoundException;
        IOException iOException;
        JSONException jSONException;
        try {
            File c = c();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(c));
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
            }
            bufferedReader.close();
            ArrayList<GroupItem> b = b(new JSONArray(stringBuilder.toString()));
            if (b != null) {
                try {
                    if (!(b.size() == 0 || b.get(0) == null || ((GroupItem) b.get(0)).members == null)) {
                        return b;
                    }
                } catch (FileNotFoundException e) {
                    FileNotFoundException fileNotFoundException2 = e;
                    arrayList = b;
                    fileNotFoundException = fileNotFoundException2;
                    fileNotFoundException.printStackTrace();
                    return arrayList;
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                    arrayList = b;
                    iOException = iOException2;
                    iOException.printStackTrace();
                    return arrayList;
                } catch (JSONException e3) {
                    JSONException jSONException2 = e3;
                    arrayList = b;
                    jSONException = jSONException2;
                    jSONException.printStackTrace();
                    return arrayList;
                }
            }
            c.delete();
            return null;
        } catch (FileNotFoundException e4) {
            fileNotFoundException = e4;
            arrayList = null;
            fileNotFoundException.printStackTrace();
            return arrayList;
        } catch (IOException e22) {
            iOException = e22;
            arrayList = null;
            iOException.printStackTrace();
            return arrayList;
        } catch (JSONException e32) {
            jSONException = e32;
            arrayList = null;
            jSONException.printStackTrace();
            return arrayList;
        }
    }

    private static ArrayList<GroupItem> b(JSONArray jSONArray) throws JSONException {
        int length = jSONArray.length();
        ArrayList<GroupItem> arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            GroupItem a = GroupItem.b(jSONArray.getJSONObject(i));
            if (QsbkApp.currentUser != null) {
                List<GroupInfo> joinedGroupsFromLocal = JoinedGroupGetter.getJoinedGroupsFromLocal();
                if (a != null && a.joinStatus == 0) {
                    int i2;
                    for (GroupInfo groupInfo : joinedGroupsFromLocal) {
                        if (groupInfo.id == a.id) {
                            Crashlytics.logException(new a(String.format("Update time %s , current time %s, %s joined group %s ", new Object[]{Integer.valueOf(b()), Long.valueOf(System.currentTimeMillis() / 1000), QsbkApp.currentUser.userId, Integer.valueOf(((GroupInfo) r7.next()).id)})));
                            i2 = 1;
                            break;
                        }
                    }
                    i2 = 0;
                    if (i2 == 0 && a != null) {
                        arrayList.add(a);
                    }
                }
            } else if (a != null) {
                arrayList.add(a);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    private static void d() {
        double latitude = LocationHelper.getLatitude();
        double longitude = LocationHelper.getLongitude();
        if (latitude == 0.0d || longitude == 0.0d) {
            LocationHelper.loadCache();
            latitude = LocationHelper.getLatitude();
            longitude = LocationHelper.getLongitude();
        }
        a(latitude, longitude);
    }

    private static void a(double d, double d2) {
        new SimpleHttpTask(String.format(Constants.URL_GROUP_RECOMMEND, new Object[]{Double.valueOf(d2), Double.valueOf(d)}) + "&b=1", new n()).execute();
    }
}
