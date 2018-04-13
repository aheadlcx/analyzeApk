package qsbk.app.video;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.timer.TimerHelper;

public class QiuyouCircleVideoLoopStatistics {
    private static QiuyouCircleVideoLoopStatistics a;
    private final Map<String, a> b = new HashMap();
    private final Map<String, a> c = new HashMap();
    private boolean d = false;
    private final TimerHelper e = new TimerHelper(new h(this), 10000, 60000);

    private static class a {
        String a;
        int b;
        int c;

        public a(String str, int i, int i2) {
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        public int hashCode() {
            return this.a == null ? super.hashCode() : this.a.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (aVar.a == null || !aVar.a.equalsIgnoreCase(this.a)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return "ReportData{articleId='" + this.a + '\'' + ", showCount=" + this.b + ", playCount=" + this.c + '}';
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject;
            Object obj;
            JSONObject jSONObject2;
            JSONException e;
            int i;
            try {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", this.a);
                    jSONObject.put("show_count", this.b);
                    jSONObject.put("play_count", this.c);
                    JSONObject jSONObject3 = jSONObject;
                    obj = null;
                    jSONObject2 = jSONObject3;
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    jSONObject2 = jSONObject;
                    i = 1;
                    if (obj != null) {
                        return jSONObject2;
                    }
                    return null;
                }
            } catch (JSONException e3) {
                e = e3;
                jSONObject = null;
                e.printStackTrace();
                jSONObject2 = jSONObject;
                i = 1;
                if (obj != null) {
                    return null;
                }
                return jSONObject2;
            }
            if (obj != null) {
                return null;
            }
            return jSONObject2;
        }
    }

    private QiuyouCircleVideoLoopStatistics() {
        this.e.startTimer();
    }

    public static QiuyouCircleVideoLoopStatistics getInstance() {
        QiuyouCircleVideoLoopStatistics qiuyouCircleVideoLoopStatistics;
        synchronized (QiuyouCircleVideoLoopStatistics.class) {
            if (a == null) {
                a = new QiuyouCircleVideoLoopStatistics();
            }
            qiuyouCircleVideoLoopStatistics = a;
        }
        return qiuyouCircleVideoLoopStatistics;
    }

    private static JSONObject a(Map<String, a> map) {
        if (map.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (a aVar : map.values()) {
            if (aVar != null) {
                jSONArray.put(aVar.toJSONObject());
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("report", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void a() {
        if (HttpUtils.netIsAvailable()) {
            JSONObject a;
            synchronized (this.b) {
                a = a(this.b);
            }
            if (a != null) {
                synchronized (this.b) {
                    if (this.d) {
                        return;
                    }
                    this.d = true;
                    new i(this, a).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[]{Constants.QIUYOUCIRCLE_VIDEO_LOOP_BATCH});
                }
            }
        }
    }

    public void onDestroy() {
        this.e.stopTimer();
        synchronized (this.b) {
            this.b.clear();
        }
        this.c.clear();
        a = null;
    }

    private void b() {
        synchronized (this.b) {
            this.b.clear();
            this.b.putAll(this.c);
        }
        this.c.clear();
    }

    private void a(Map<String, a> map, String str, int i, int i2) {
        if (map.containsKey(str)) {
            a aVar = (a) map.get(str);
            aVar.b += i;
            aVar.c += i2;
            map.put(str, aVar);
            return;
        }
        map.put(str, new a(str, i, i2));
    }

    public void loopBatch(String str, int i, int i2) {
        synchronized (this.b) {
            if (this.d) {
                a(this.c, str, i, i2);
            } else {
                a(this.b, str, i, i2);
            }
        }
    }
}
