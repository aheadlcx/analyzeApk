package qsbk.app.video;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.timer.TimerHelper;

public class VideoLoopStatistics {
    private static VideoLoopStatistics a;
    private final Map<String, a> b = new HashMap();
    private final Map<String, a> c = new HashMap();
    private boolean d = false;
    private final TimerHelper e = new TimerHelper(new ay(this), 10000, 60000);

    public interface ICallback {
        void onFailure(String str, String str2, Exception exception);

        void onSuccess(String str);
    }

    private static class a {
        String a;
        int b;
        int c;

        public a(String str, int i) {
            this(str, i, 1);
        }

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
            return "LoopVote [articleId=" + this.a + ", fakeCount=" + this.b + ", realCount=" + this.c + "]";
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject;
            Object obj = null;
            try {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", this.a);
                    jSONObject.put("real_count", this.c);
                    jSONObject.put("rand_count", this.b);
                } catch (JSONException e) {
                    obj = 1;
                    if (obj != null) {
                        return jSONObject;
                    }
                    return null;
                }
            } catch (JSONException e2) {
                jSONObject = null;
                obj = 1;
                if (obj != null) {
                    return null;
                }
                return jSONObject;
            }
            if (obj != null) {
                return null;
            }
            return jSONObject;
        }
    }

    private VideoLoopStatistics() {
        this.e.startTimer();
    }

    public static VideoLoopStatistics getInstance() {
        VideoLoopStatistics videoLoopStatistics;
        synchronized (VideoLoopStatistics.class) {
            if (a == null) {
                a = new VideoLoopStatistics();
            }
            videoLoopStatistics = a;
        }
        return videoLoopStatistics;
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
            jSONObject.put("loops", jSONArray);
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
            LogUtil.d("before submit batch:");
            if (a != null) {
                synchronized (this.b) {
                    if (this.d) {
                        return;
                    }
                    this.d = true;
                    new az(this, a).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[]{Constants.VIDEO_LOOP_BATCH});
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

    private void a(Map<String, a> map, String str, int i) {
        if (map.containsKey(str)) {
            a aVar = (a) map.get(str);
            aVar.b += i;
            aVar.c++;
            map.put(str, aVar);
            return;
        }
        map.put(str, new a(str, i));
    }

    public void loopBatch(String str, int i) {
        synchronized (this.b) {
            if (this.d) {
                a(this.c, str, i);
            } else {
                a(this.b, str, i);
            }
        }
    }

    public void loop(String str, int i) {
        loop(str, i, null);
    }

    public void loop(String str, int i, ICallback iCallback) {
        if (str == null || str.trim().length() == 0) {
            Log.e(VideoLoopStatistics.class.getSimpleName(), "article id is null.");
        } else if (HttpUtils.netIsAvailable()) {
            new ba(this, iCallback, str, i).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }
}
