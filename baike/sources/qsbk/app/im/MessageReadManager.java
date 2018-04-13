package qsbk.app.im;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.comm.ArrayUtils;

public class MessageReadManager {
    private static MessageReadManager a;

    public static synchronized MessageReadManager instance() {
        MessageReadManager messageReadManager;
        synchronized (MessageReadManager.class) {
            if (a == null) {
                a = new MessageReadManager();
            }
            messageReadManager = a;
        }
        return messageReadManager;
    }

    public JSONObject getFailedReadedMsg(String str) {
        try {
            return new JSONObject(SharePreferenceUtils.getSharePreferencesValue("IM_UNREAD_MSG_" + str));
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    public void saveFailedReadedMsg(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            SharePreferenceUtils.setSharePreferencesValue("IM_UNREAD_MSG_" + str, jSONObject.toString());
        }
    }

    public void addUnreadMsgIds(String str, String str2, List<String> list) {
        if (!ArrayUtils.isEmpty(list)) {
            JSONObject failedReadedMsg = getFailedReadedMsg(str);
            JSONArray optJSONArray = failedReadedMsg.optJSONArray(str2);
            Set hashSet = new HashSet();
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    CharSequence optString = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString)) {
                        hashSet.add(optString);
                    }
                }
            }
            for (String str3 : list) {
                if (!TextUtils.isEmpty(str3)) {
                    hashSet.add(str3);
                }
            }
            try {
                failedReadedMsg.put(str2, new JSONArray(ArrayUtils.toList(hashSet)));
            } catch (Exception e) {
            }
            saveFailedReadedMsg(str, failedReadedMsg);
        }
    }

    public void removeUnReadMsgIds(String str, String str2, List<String> list) {
        if (!ArrayUtils.isEmpty(list)) {
            JSONObject failedReadedMsg = getFailedReadedMsg(str);
            JSONArray optJSONArray = failedReadedMsg.optJSONArray(str2);
            Set hashSet = new HashSet();
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    CharSequence optString = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString)) {
                        hashSet.add(optString);
                    }
                }
            }
            for (String str3 : list) {
                if (hashSet.contains(str3)) {
                    hashSet.remove(str3);
                }
            }
            try {
                failedReadedMsg.put(str2, new JSONArray(ArrayUtils.toList(hashSet)));
            } catch (Exception e) {
            }
            saveFailedReadedMsg(str, failedReadedMsg);
        }
    }
}
