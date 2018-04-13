package qsbk.app.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

public class GroupMsgUtils {
    private static final String a = ("group_msg_" + QsbkApp.currentUser.userId);
    private static SimpleCallBack b = null;
    private static ArrayList<SimpleCallBack> c = null;
    private static Boolean d = null;
    private static HashMap<String, Boolean> e = new HashMap();

    private static SharedPreferences a() {
        return QsbkApp.getInstance().getSharedPreferences(a, 0);
    }

    public static boolean isLoaded() {
        if (d == null) {
            d = Boolean.valueOf(a().getBoolean("loaded", false));
        }
        return d.booleanValue();
    }

    public static void setOpenable(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            e.put(str, Boolean.valueOf(z));
            a().edit().putBoolean(str, z).apply();
        }
    }

    public static void setOpenable(List<Object> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            Editor edit = a().edit();
            e.clear();
            for (int i = 0; i < size; i++) {
                boolean z;
                Pair pair = (Pair) list.get(i);
                String valueOf = String.valueOf(pair.first);
                if (((Integer) pair.second).intValue() != 0) {
                    z = true;
                } else {
                    z = false;
                }
                e.put(valueOf, Boolean.valueOf(z));
                edit.putBoolean(valueOf, z);
            }
            edit.apply();
        }
    }

    private static void b(int i, SimpleCallBack simpleCallBack) {
        new SimpleHttpTask(String.format(Constants.URL_SET_GROUP_MSG_SWITCH + "?page=%d", new Object[]{Integer.valueOf(i)}), simpleCallBack).execute();
    }

    private static boolean a(String str, boolean z) {
        return a().getBoolean(str, z);
    }

    public static boolean has(String str) {
        return a().contains(str);
    }

    public static boolean isOpen(String str, boolean z) {
        Boolean bool = (Boolean) e.get(str);
        if (bool == null) {
            bool = Boolean.valueOf(a(str, z));
            e.put(str, bool);
        }
        return bool.booleanValue();
    }

    private static void b(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("sws_dict");
        Iterator keys = jSONObject2.keys();
        Editor edit = a().edit();
        edit.clear();
        edit.putBoolean("loaded", true);
        d = Boolean.TRUE;
        e.clear();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            boolean z = !"0".equals(jSONObject2.getString(str));
            e.put(str, Boolean.valueOf(z));
            edit.putBoolean(str, z);
        }
        edit.apply();
    }

    public static void loadSyncIfNeed() {
        if (!isLoaded()) {
            JSONObject syncHttp = SimpleHttpTask.syncHttp(String.format(Constants.URL_SET_GROUP_MSG_SWITCH + "?page=%d", new Object[]{Integer.valueOf(1)}), null);
            if (syncHttp != null) {
                try {
                    b(syncHttp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void b(boolean z, int i, String str) {
        if (c != null) {
            Iterator it = c.iterator();
            while (it.hasNext()) {
                SimpleCallBack simpleCallBack = (SimpleCallBack) it.next();
                if (z) {
                    simpleCallBack.onSuccess(null);
                } else {
                    simpleCallBack.onFailure(i, str);
                }
            }
        } else if (z) {
            b.onSuccess(null);
        } else {
            b.onFailure(i, str);
        }
        c = null;
        b = null;
    }

    private static boolean a(SimpleCallBack simpleCallBack) {
        if (b != null) {
            if (c == null) {
                c = new ArrayList();
                c.add(b);
            }
            b = simpleCallBack;
            c.add(simpleCallBack);
            return true;
        }
        b = simpleCallBack;
        return false;
    }

    public static void loadAll(SimpleCallBack simpleCallBack) {
        if (!a(simpleCallBack)) {
            b(0, new ah());
        }
    }
}
