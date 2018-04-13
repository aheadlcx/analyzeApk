package cn.v6.sixrooms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

public class SharedPreferencesUtils {
    public static final String APP_VERSIONCODE = "app_versioncode";
    public static final String CODEC_CONFIGURE = "codec_configer";
    public static final String DYNAMIC_DATA = "dynamic_cache";
    public static final String FIRST_START_KEY = "first_start";
    public static final String GPS_CONFIGURE = "gps_configer";
    public static final String HARD_CODEC_KEY = "hard_codec";
    public static final String IS_FIRST = "isFirst";
    public static final String LIVE_SHARE = "live_share";
    public static final String MBLOG_DRAFT_MSG = "mblogDraftMsg";
    public static final String MBLOG_DRAFT_PIC = "mblogDraftPic";
    public static final String MBLOG_DRAFT_UID = "mblogDraftUid";
    public static final String MIC_VOLUME = "mic_volume";
    public static final String MINI_GAME_SWITCH = "miniGameSwitch";
    public static final String MUSIC_VOLUME = "music_volume";
    public static final String SOUND_SWITCH = "soundSwitch";
    public static final String SOUND_VOLUME = "sound_volume";
    public static final String SP_KEY_NEWID = "newid";
    public static final String SP_KEY_PIC = "pic";
    public static final String SP_KEY_READ = "read";
    public static final String START_APP = "startApp";
    public static final String TIP_LOCATION_VERSION = "tip_location_version";
    public static final String USER_COIN_V = "userCoinV ";
    public static final String VIDEO_DECODE_TYPE = "video_decode_type";
    public static final String VISITOR_ID = "visitorId";
    private static String a = "";

    @Deprecated
    public static void put(Context context, String str, int i, String str2, Object obj) {
        a(str, i, str2, obj);
    }

    @Deprecated
    public static void putOnDefault(Context context, int i, String str, Object obj) {
        a("sixrooms_data", i, str, obj);
    }

    public static void put(int i, String str, Object obj) {
        a("sixrooms_data", i, str, obj);
    }

    public static void put(String str, Object obj) {
        a("sixrooms_data", 0, str, obj);
    }

    public static void put(String str, int i, String str2, Object obj) {
        a(str, i, str2, obj);
    }

    private static void a(String str, int i, String str2, Object obj) {
        if (TextUtils.isEmpty(str)) {
            str = "sixrooms_data";
        }
        Editor edit = V6Coop.getInstance().getContext().getSharedPreferences(str, i).edit();
        if (obj instanceof String) {
            edit.putString(str2, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str2, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str2, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str2, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str2, ((Long) obj).longValue());
        }
        SharedPreferencesUtils$a.a(edit);
    }

    @Deprecated
    public static Object getOnDefault(Context context, int i, String str, Object obj) {
        return b("sixrooms_data", i, str, obj);
    }

    @Deprecated
    public static Object get(Context context, String str, int i, String str2, Object obj) {
        return b(str, i, str2, obj);
    }

    public static Object get(int i, String str, Object obj) {
        return b("sixrooms_data", i, str, obj);
    }

    public static Object get(String str, Object obj) {
        return b("sixrooms_data", 0, str, obj);
    }

    public static Object get(String str, int i, String str2, Object obj) {
        return b(str, i, str2, obj);
    }

    public static void put(HashMap<String, ?> hashMap) {
        for (Entry entry : hashMap.entrySet()) {
            a("sixrooms_data", 0, (String) entry.getKey(), entry.getValue());
        }
    }

    public static void get(HashMap<String, ?> hashMap) {
        for (Entry entry : hashMap.entrySet()) {
            entry.setValue(b("sixrooms_data", 0, (String) entry.getKey(), entry.getValue()));
        }
    }

    private static Object b(String str, int i, String str2, Object obj) {
        SharedPreferences sharedPreferences = V6Coop.getInstance().getContext().getSharedPreferences(str, i);
        if (obj instanceof String) {
            return sharedPreferences.getString(str2, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str2, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str2, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str2, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str2, ((Long) obj).longValue()));
        }
        return null;
    }

    public static void remove(Context context, String str, int i, String str2) {
        Editor edit = context.getSharedPreferences(str, i).edit();
        edit.remove(str2);
        SharedPreferencesUtils$a.a(edit);
    }

    public static void clear(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences(str, i).edit();
        edit.clear();
        SharedPreferencesUtils$a.a(edit);
    }

    public static boolean contains(Context context, String str, int i, String str2) {
        return context.getSharedPreferences(str, i).contains(str2);
    }

    public static Map<String, ?> getAll(Context context, String str, int i) {
        return context.getSharedPreferences(str, i).getAll();
    }

    public static int[] getBeauty(Context context) {
        return new int[]{((Integer) get(context, "shader_beauty", 0, "alpha", Integer.valueOf(50))).intValue(), ((Integer) get(context, "shader_beauty", 0, "beta", Integer.valueOf(50))).intValue(), ((Integer) get(context, "shader_beauty", 0, "enlarge_eye", Integer.valueOf(0))).intValue(), ((Integer) get(context, "shader_beauty", 0, "shrink_face", Integer.valueOf(0))).intValue()};
    }

    public static void setBeauty(Context context, int i, int i2, int i3, int i4) {
        put(context, "shader_beauty", 0, "alpha", Integer.valueOf(i));
        put(context, "shader_beauty", 0, "beta", Integer.valueOf(i2));
        put(context, "shader_beauty", 0, "enlarge_eye", Integer.valueOf(i3));
        put(context, "shader_beauty", 0, "shrink_face", Integer.valueOf(i4));
    }

    public static boolean isFirstShowGift(Context context) {
        get(context, "gift_show", 0, "first", Boolean.valueOf(true));
        return ((Boolean) get(context, "gift_show", 0, "first", Boolean.valueOf(true))).booleanValue();
    }

    public static void setGiftShown(Context context) {
        put(context, "gift_show", 0, "first", Boolean.valueOf(false));
    }

    public static void saveUserCoinV(Context context, String str, String str2) {
        put(context, USER_COIN_V, 0, str, str2);
    }

    public static String getUserCoinV(Context context, String str) {
        return (String) get(context, USER_COIN_V, 0, str, "");
    }

    public static void saveMBlogDraftData(Context context, String str, String str2) {
        put(context, "mblogDraft", 0, str, str2);
    }

    public static String getMBlogDraftData(Context context, String str) {
        return (String) get(context, "mblogDraft", 0, str, "");
    }

    public static void clearMBlogDraftData(Context context) {
        clear(context, "mblogDraft", 0);
    }

    public static void saveSettings(Context context, String str, boolean z) {
        put(context, "appSettings", 32768, str, Boolean.valueOf(z));
    }

    public static boolean getBooleanSettings(Context context, String str) {
        return ((Boolean) get(context, "appSettings", 32768, str, Boolean.valueOf(true))).booleanValue();
    }

    public static List<Long> getHideList(String str) {
        List<Long> arrayList = new ArrayList();
        String str2 = (String) get(V6Coop.getInstance().getContext(), "imRecenter", 32768, str, "");
        if (!TextUtils.isEmpty(str2)) {
            Collection treeSet = new TreeSet();
            for (String parseLong : str2.split(h.b)) {
                treeSet.add(Long.valueOf(Long.parseLong(parseLong)));
            }
            arrayList.addAll(treeSet);
        }
        return arrayList;
    }

    public static void saveHideList(String str, List<Long> list) {
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Long l : list) {
                stringBuilder.append(l + h.b);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            put(V6Coop.getInstance().getContext(), "imRecenter", 32768, str, stringBuilder.toString());
        }
    }
}
