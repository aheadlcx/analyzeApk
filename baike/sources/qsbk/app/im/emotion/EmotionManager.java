package qsbk.app.im.emotion;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.im.ChatMsgEmotionData;

public class EmotionManager {
    private static final String a = EmotionManager.class.getSimpleName();
    private static final Map<String, List<ChatMsgEmotionData>> b = new HashMap();
    private static final Map<String, ChatMsgEmotionData> c = new HashMap();
    private static EmotionManager d = null;
    private static byte[] e = new byte[0];
    private boolean f = false;
    private boolean g = false;

    private EmotionManager() {
    }

    public static final EmotionManager getInstance() {
        EmotionManager emotionManager;
        synchronized (e) {
            if (d == null) {
                d = new EmotionManager();
            }
            emotionManager = d;
        }
        return emotionManager;
    }

    private EmotionManager$a a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        EmotionManager$a emotionManager$a;
        Resources resources = context.getResources();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(IndexEntry.COLUMN_NAME_WIDTH);
            int i2 = jSONObject.getInt(IndexEntry.COLUMN_NAME_HEIGHT);
            String string = jSONObject.getString("namespace");
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            int length = jSONArray.length();
            List arrayList = new ArrayList(length);
            for (int i3 = 0; i3 < length; i3++) {
                ChatMsgEmotionData chatMsgEmotionData = new ChatMsgEmotionData(jSONArray.getJSONObject(i3));
                chatMsgEmotionData.height = i2;
                chatMsgEmotionData.width = i;
                chatMsgEmotionData.namespace = string;
                chatMsgEmotionData.localResource = resources.getIdentifier(chatMsgEmotionData.key, "drawable", context.getPackageName());
                arrayList.add(chatMsgEmotionData);
            }
            emotionManager$a = new EmotionManager$a(arrayList, string);
        } catch (JSONException e) {
            e.printStackTrace();
            emotionManager$a = null;
        }
        return emotionManager$a;
    }

    public ChatMsgEmotionData getLocalChatMsgEmotionData(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String str3 = str + "_" + str2;
        ChatMsgEmotionData chatMsgEmotionData = (ChatMsgEmotionData) c.get(str3);
        if (chatMsgEmotionData != null) {
            return chatMsgEmotionData;
        }
        List<ChatMsgEmotionData> list = (List) b.get(str);
        if (list == null) {
            return null;
        }
        for (ChatMsgEmotionData chatMsgEmotionData2 : list) {
            if (chatMsgEmotionData2.key.equalsIgnoreCase(str2)) {
                c.put(str3, chatMsgEmotionData2);
                return chatMsgEmotionData2;
            }
        }
        return null;
    }

    public Map<String, List<ChatMsgEmotionData>> getAll() {
        Map<String, List<ChatMsgEmotionData>> hashMap = new HashMap();
        hashMap.putAll(b);
        return hashMap;
    }

    public void init(Context context) {
        if (!this.f && !this.g) {
            this.g = true;
            new c(this, context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private final void b() {
        if (!this.f) {
            throw new RuntimeException("Not inited yet, please call init(Context ctx)");
        }
    }

    public List<ChatMsgEmotionData> getDataByNS(String str) {
        b();
        return (List) b.get(str);
    }

    public int getEmotionNameSpaceCount() {
        return b.keySet().size();
    }

    public Set<String> getEmotionNameSpaces() {
        return b.keySet();
    }
}
