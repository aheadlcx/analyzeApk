package qsbk.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupInfo;

public class GroupMemberManager {
    public static final CallBack EMPTY_CALLBACK = new af();
    private int a;
    private int b;
    private boolean c;

    public interface CallBack {
        void onFailure(int i, String str);

        void onSuccess(ArrayList<BaseUserInfo> arrayList, int i);
    }

    public GroupMemberManager(int i) {
        this(i, 0, true);
    }

    public GroupMemberManager(GroupInfo groupInfo) {
        this(groupInfo.id, groupInfo.ownerId, groupInfo.joinStatus == 2);
    }

    private GroupMemberManager(int i, int i2, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = z;
    }

    private static File a(int i) {
        String sDPath = DeviceUtils.getSDPath();
        if (sDPath == null || sDPath.length() == 0) {
            sDPath = QsbkApp.getInstance().getFilesDir().getAbsolutePath();
        }
        String str = QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId;
        File file = new File(sDPath + File.separator + "qsbk/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, Integer.toHexString("MemberList".hashCode()) + str + "_" + i);
    }

    private void a(String str) {
        File a = a(this.a);
        if (str == null) {
            a.delete();
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(a);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
            a.setLastModified(System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String a() {
        String str = null;
        File a = a(this.a);
        if (a.exists()) {
            if (a.lastModified() < System.currentTimeMillis() - 86400000) {
                a.delete();
            } else {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(a));
                    StringBuilder stringBuilder = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    bufferedReader.close();
                    str = stringBuilder.toString();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return str;
    }

    private ArrayList<BaseUserInfo> a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        ArrayList<BaseUserInfo> arrayList = new ArrayList();
        String valueOf = String.valueOf(this.b);
        for (int i = 0; i < jSONArray.length(); i++) {
            BaseUserInfo baseUserInfo = new BaseUserInfo();
            baseUserInfo.parseBaseInfo(jSONArray.getJSONObject(i));
            arrayList.add(baseUserInfo);
            if (baseUserInfo.userId.equals(valueOf)) {
                baseUserInfo.isOwner = true;
            }
        }
        return arrayList;
    }

    private JSONObject a(ArrayList<BaseUserInfo> arrayList) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(((BaseUserInfo) it.next()).toJSONObject());
            }
            jSONObject.put("data", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<BaseUserInfo> loadMemberFromCache() {
        String a = a();
        if (a != null) {
            try {
                ArrayList<BaseUserInfo> a2 = a(new JSONObject(a));
                if (a2.size() > 0) {
                    return a2;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void saveMemberToCache(ArrayList<BaseUserInfo> arrayList) {
        JSONObject a = a((ArrayList) arrayList);
        if (a != null) {
            a(a.toString());
        } else {
            a(null);
        }
    }

    public BaseUserInfo getMember(String str) {
        ArrayList loadMemberFromCache = loadMemberFromCache();
        if (loadMemberFromCache == null) {
            return null;
        }
        Iterator it = loadMemberFromCache.iterator();
        while (it.hasNext()) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) it.next();
            if (baseUserInfo.userId.equals(str)) {
                return baseUserInfo;
            }
        }
        return null;
    }

    public void updateMember(BaseUserInfo baseUserInfo) {
        ArrayList loadMemberFromCache = loadMemberFromCache();
        if (loadMemberFromCache != null) {
            Iterator it = loadMemberFromCache.iterator();
            while (it.hasNext()) {
                BaseUserInfo baseUserInfo2 = (BaseUserInfo) it.next();
                if (baseUserInfo2.userId.equals(baseUserInfo.userId)) {
                    loadMemberFromCache.set(loadMemberFromCache.indexOf(baseUserInfo2), baseUserInfo);
                    saveMemberToCache(loadMemberFromCache);
                    return;
                }
            }
        }
    }

    public void loadMemberFromServer(CallBack callBack) {
        new SimpleHttpTask(String.format(Constants.URL_GROUP_ALL_MEMBERS + (this.c ? "&active=1" : ""), new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.a)}), new ag(this, callBack)).execute();
    }

    public void loadMember(CallBack callBack) {
        ArrayList loadMemberFromCache = loadMemberFromCache();
        if (loadMemberFromCache != null) {
            callBack.onSuccess(loadMemberFromCache, 0);
        } else {
            loadMemberFromServer(callBack);
        }
    }
}
