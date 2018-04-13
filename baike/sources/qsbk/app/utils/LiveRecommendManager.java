package qsbk.app.utils;

import android.text.TextUtils;
import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Live;
import qsbk.app.model.LiveRecommend;

public class LiveRecommendManager {
    public static final String LIVE_INFOS = "_live_infos_";
    public static LiveRecommend LIVE_RECOMMEND = null;
    public static final long LIVE_REFRESH_TIME = 1800000;
    private static LiveRecommendManager a;

    private LiveRecommendManager() {
    }

    public static LiveRecommendManager getInstance() {
        if (a == null) {
            a = new LiveRecommendManager();
        }
        return a;
    }

    public static void save(String str) {
        SharePreferenceUtils.setSharePreferencesValue(LIVE_INFOS, str);
    }

    public static String load() {
        return SharePreferenceUtils.getSharePreferencesValue(LIVE_INFOS);
    }

    public static JSONObject toJson(ArrayList<Live> arrayList) {
        if (arrayList.size() == 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONObject toJson = Live.toJson((Live) arrayList.get(i));
                if (toJson != null) {
                    jSONArray.put(toJson);
                }
            }
            if (jSONArray.length() == 0) {
                return null;
            }
            jSONObject.put("data", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void save(ArrayList<Live> arrayList, long j, long j2) {
        if (arrayList != null && arrayList.size() != 0) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONObject toJson = Live.toJson((Live) arrayList.get(i));
                if (toJson != null) {
                    jSONArray.put(toJson);
                }
            }
            if (jSONArray.length() > 0) {
                try {
                    jSONObject.put("data", jSONArray);
                    jSONObject.put(IndexEntry.COLUMN_NAME_DATE, j);
                    jSONObject.put(g.az, j2);
                    save(jSONObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static LiveRecommend loadFromCache(boolean z) {
        LiveRecommend parse = parse(load());
        if (parse != null && z) {
            LIVE_RECOMMEND = parse;
        }
        return parse;
    }

    public static LiveRecommend parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            LiveRecommend liveRecommend = new LiveRecommend();
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            long optLong = jSONObject.optLong(IndexEntry.COLUMN_NAME_DATE);
            long optLong2 = jSONObject.optLong(g.az);
            for (int i = 0; i < optJSONArray.length(); i++) {
                Live parseJson = Live.parseJson(optJSONArray.getJSONObject(i));
                if (parseJson != null) {
                    if (parseJson.isFollow) {
                        arrayList.add(0, parseJson);
                    } else {
                        arrayList.add(parseJson);
                    }
                }
            }
            if (arrayList.size() <= 0) {
                return null;
            }
            liveRecommend.lastUpdateTime = optLong;
            liveRecommend.lives = arrayList;
            liveRecommend.interval = optLong2;
            return liveRecommend;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void a(boolean z) {
        new SimpleHttpTask(Constants.LIVE_RECOMMEND, new an(z)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static void update(long j) {
        if (LIVE_RECOMMEND != null && LIVE_RECOMMEND.lives.size() > 0) {
            LIVE_RECOMMEND.updateStatus(j);
        }
    }

    public void init() {
        LIVE_RECOMMEND = loadFromCache(false);
        long currentTimeMillis = System.currentTimeMillis();
        if (LIVE_RECOMMEND == null || (LIVE_RECOMMEND != null && currentTimeMillis - LIVE_RECOMMEND.lastUpdateTime > LIVE_RECOMMEND.interval)) {
            a(false);
        }
    }

    public void refresh() {
        long currentTimeMillis = System.currentTimeMillis();
        if (LIVE_RECOMMEND == null) {
            a(true);
        } else if (LIVE_RECOMMEND != null && currentTimeMillis - LIVE_RECOMMEND.lastUpdateTime > LIVE_RECOMMEND.interval * 1000) {
            a(false);
        }
    }
}
