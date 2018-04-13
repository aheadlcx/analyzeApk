package com.iflytek.cloud.thirdparty;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.xiaochuan.jsbridge.data.d;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.aiui.Version;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.util.ResourceUtil.RESOURCE_TYPE;
import com.umeng.analytics.b.g;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ac {
    public static Map<String, RESOURCE_TYPE> a = new HashMap();
    private static Map<String, JSONObject> b = new HashMap();

    static {
        a.put(RESOURCE_TYPE.assets.name(), RESOURCE_TYPE.assets);
        a.put(RESOURCE_TYPE.path.name(), RESOURCE_TYPE.path);
        a.put(RESOURCE_TYPE.res.name(), RESOURCE_TYPE.res);
    }

    public static int a(String str, String str2, int i) {
        JSONObject b = b(str);
        return b == null ? i : b.optInt(str2, i);
    }

    public static ce a(String str) {
        return b(b(str));
    }

    public static String a() {
        return a(d.a, "appid", "");
    }

    public static String a(String str, String str2, String str3) {
        synchronized (ac.class) {
            try {
                Object b = b(str);
                if (b == null) {
                    return str3;
                }
                str3 = b.optString(str2, str3);
            } finally {
                Class cls = ac.class;
            }
        }
        return str3;
    }

    public static JSONObject a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("appid")) {
            jSONObject.put("appid", a());
        }
        if (jSONObject.has("uid")) {
            jSONObject.put("uid", b());
        }
        return jSONObject;
    }

    public static void a(ce ceVar) {
        if (ceVar != null) {
            ceVar.a("sdk_ver", Version.getVersion());
            ceVar.a("ver_type", Version.getVersionType());
            ceVar.a("dev_lang", "java");
            ceVar.a(g.p, "android");
        }
    }

    public static boolean a(String str, String str2, boolean z) {
        JSONObject b = b(str);
        if (b == null) {
            return z;
        }
        CharSequence optString = b.optString(str2);
        return !TextUtils.isEmpty(optString) ? !"0".equals(optString) : z;
    }

    private static boolean a(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || jSONObject == null) {
            return false;
        }
        if (b.containsKey(str)) {
            JSONObject jSONObject2 = (JSONObject) b.get(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                try {
                    jSONObject2.put(str2, jSONObject.opt(str2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            b.put(str, jSONObject);
        }
        return true;
    }

    private static String[] a(String str, String str2) {
        String[] split = str.split(str2);
        return (split == null || 2 != split.length) ? null : split;
    }

    public static ce b(ce ceVar) {
        ce b = b(b("audioparams"));
        if (b != null) {
            ceVar.a(b);
        }
        JSONObject b2 = b("attachparams");
        if (b2 != null) {
            ceVar.a("attachparams", c(b2).toString());
        }
        a(ceVar);
        int a = ceVar.a("sample_rate", 16000);
        String a2 = a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_DATA_SOURCE, "sdk");
        ceVar.a("data_type", "audio", false);
        ceVar.a(AIUIConstant.KEY_DATA_SOURCE, a2, false);
        ceVar.a("data_format", "audio/L16;rate=" + a, false);
        ceVar.a(AIUIConstant.KEY_AUDIO_ENCODE, 16000 == a ? a("iat", AIUIConstant.KEY_AUDIO_ENCODE, "speex-wb;10") : a("iat", AIUIConstant.KEY_AUDIO_ENCODE, "speex;10"), false);
        ceVar.a(i.a);
        return ceVar;
    }

    public static ce b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ce ceVar = new ce();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            ceVar.a(str, jSONObject.optString(str));
        }
        return ceVar;
    }

    public static String b() {
        return SpeechUtility.getUtility() != null ? SpeechUtility.getUtility().getParameter("uid") : "";
    }

    public static JSONObject b(String str) {
        return (JSONObject) b.get(str);
    }

    public static void b(String str, String str2, String str3) {
        synchronized (ac.class) {
            JSONObject b = b(str);
            if (b == null) {
                b = new JSONObject();
                a(str, b);
            }
            try {
                b.put(str2, str3);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                Class cls = ac.class;
            }
        }
    }

    public static RESOURCE_TYPE c(String str) {
        return (RESOURCE_TYPE) a.get(str);
    }

    public static String c() {
        JSONObject b = b("userparams");
        if (b == null) {
            return "";
        }
        try {
            return bq.a(b.toString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String c(ce ceVar) {
        String e = ceVar.e("stmid");
        String e2 = ceVar.e("dtype");
        String e3 = ceVar.e("dts");
        Object e4 = ceVar.e("dtf");
        String e5 = ceVar.e("dte");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stmid").append("=").append(e).append(",").append("dtype").append("=").append(e2).append(",").append("dts").append("=").append(e3).append(",").append("cnt_id").append("=").append("0");
        if (!TextUtils.isEmpty(e4)) {
            stringBuilder.append(",").append("dtf").append("=").append(e4).append(",").append("dte").append("=").append(e5);
        }
        return stringBuilder.toString();
    }

    private static JSONObject c(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                jSONObject2.put(str, jSONObject.get(str).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject2;
    }

    public static ce d(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ce();
        }
        Matcher matcher = Pattern.compile(",[ ]*[\\w\\.:]+=").matcher(str);
        Map hashMap = new HashMap();
        int i = 0;
        while (matcher.find()) {
            String substring = str.substring(i, matcher.start());
            i = matcher.start() + 1;
            String[] a = a(substring, "=");
            if (a != null) {
                hashMap.put(a[0], a[1]);
            }
        }
        String[] a2 = a(str.substring(i), "=");
        if (a2 != null) {
            hashMap.put(a2[0], a2[1]);
        }
        ce ceVar = new ce();
        for (Entry entry : hashMap.entrySet()) {
            ceVar.a(((String) entry.getKey()).trim(), (String) entry.getValue());
        }
        return ceVar;
    }

    public static String d(ce ceVar) {
        ce b = ceVar.b();
        JSONObject jSONObject = new JSONObject();
        try {
            String d;
            String d2;
            JSONObject jSONObject2;
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            String d3 = b.d("dtype");
            String d4 = b.d("stmid");
            jSONObject4.put("dtype", d3);
            jSONObject4.put("stmid", d4);
            if (b.g("dsrc")) {
                jSONObject4.put("dsrc", b.d("dsrc"));
            }
            jSONObject4.put("ver_type", Version.getVersionType());
            CharSequence b2 = al.b();
            if (!TextUtils.isEmpty(b2)) {
                jSONObject4.put("wake_id", b2);
            }
            if (b.g("scene")) {
                jSONObject4.put("scene", b.d("scene"));
            }
            if (b.g("msc.lng")) {
                d3 = b.d("msc.lng");
                d4 = b.d("msc.lat");
                jSONObject4.put("msc.lng", d3);
                jSONObject4.put("msc.lat", d4);
            }
            if (b.g(NotificationCompat.CATEGORY_EVENT)) {
                d3 = b.d(NotificationCompat.CATEGORY_EVENT);
                d4 = b.d("state");
                String d5 = b.d("last_state");
                d = b.d("event_only");
                d2 = b.d("operation");
                String d6 = b.d("source");
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("name", d3);
                jSONObject5.put("source", d6);
                jSONObject2 = new JSONObject();
                jSONObject2.put("name", d2);
                jSONObject5.put("operation", jSONObject2);
                jSONObject2 = new JSONObject();
                jSONObject2.put("name", d4);
                jSONObject2.put("last_state", d5);
                JSONObject jSONObject6 = new JSONObject();
                jSONObject6.put(NotificationCompat.CATEGORY_EVENT, jSONObject5);
                jSONObject6.put("state", jSONObject2);
                jSONObject4.put(NotificationCompat.CATEGORY_STATUS, jSONObject6);
                jSONObject4.put("event_only", d);
            }
            jSONObject4.put(AIUIConstant.KEY_INTERACT_MODE, a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTERACT_MODE, AIUIConstant.INTERACT_MODE_CONTINUOUS));
            d4 = b.d("userparams");
            JSONArray jSONArray2 = new JSONArray();
            jSONObject2 = new JSONObject();
            d = b.d("dtf");
            d2 = b.d("dte");
            jSONObject2.put("cnt_id", "0");
            jSONObject2.put("dtf", d);
            jSONObject2.put("dte", d2);
            jSONArray2.put(jSONObject2);
            Map c = b.c();
            Set<String> keySet = c.keySet();
            if (!(keySet == null || keySet.isEmpty())) {
                for (String str : keySet) {
                    Object obj = (String) c.get(str);
                    if (str.equals("pers_param")) {
                        obj = f(obj);
                    }
                    jSONObject4.put(str, obj);
                }
            }
            jSONObject3.put(SpeechConstant.PARAMS, jSONObject4);
            jSONObject3.put("userparams", d4);
            jSONObject3.put("content", jSONArray2);
            jSONArray.put(jSONObject3);
            jSONObject.put("data", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static void d(JSONObject jSONObject) {
        if ("1".equals(jSONObject.optString("debug_log"))) {
            AIUISetting.setShowLog(true);
        } else {
            AIUISetting.setShowLog(false);
        }
        if ("1".equals(jSONObject.optString("save_datalog"))) {
            AIUISetting.setSaveDataLog(true);
            if (jSONObject.has("datalog_size")) {
                AIUISetting.setSaveDataLog(true, (long) ((jSONObject.optInt("datalog_size") * 1024) * 1024), 104857600, 1800);
            }
        }
        Object optString = jSONObject.optString("datalog_path");
        if (!TextUtils.isEmpty(optString)) {
            AIUISetting.setDataLogPath(optString);
        }
        optString = jSONObject.optString("raw_audio_path");
        if (!TextUtils.isEmpty(optString)) {
            AIUISetting.setRawAudioPath(optString);
        }
    }

    public static boolean e(String str) {
        JSONException e;
        if (str == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                if (jSONObject.has("log")) {
                    d(jSONObject.getJSONObject("log"));
                }
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                        if (jSONObject2.length() == 0) {
                            b.put(str2, new JSONObject());
                        } else {
                            a(str2, jSONObject2);
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            } catch (JSONException e3) {
                e2 = e3;
                e2.printStackTrace();
                return false;
            }
        } catch (JSONException e4) {
            e2 = e4;
            e2.printStackTrace();
            return false;
        }
    }

    private static String f(String str) {
        try {
            str = a(new JSONObject(str)).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}
