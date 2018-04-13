package qsbk.app.im;

import com.baidu.mobstat.Config;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.Laisee;

public class SyncMsg {
    private Map<String, Long> a;
    public long p2p = -1;
    public long push = -1;
    public int sync_type;

    public void addTribeSeqId(String str, long j) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, Long.valueOf(j));
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.sync_type == 1) {
                jSONObject.put("sync_type", this.sync_type);
            }
            if (this.p2p >= 0) {
                jSONObject.put(Laisee.TYPE_P2P, this.p2p);
            }
            if (this.push >= 0) {
                jSONObject.put("push", this.push);
            }
            if (this.a != null && this.a.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (Entry entry : this.a.entrySet()) {
                    Object obj;
                    Long l = (Long) entry.getValue();
                    if (l == null || l.longValue() <= 0) {
                        String str = (String) entry.getKey();
                    } else {
                        obj = ((String) entry.getKey()) + Config.TRACE_TODAY_VISIT_SPLIT + l;
                    }
                    jSONArray.put(obj);
                }
                jSONObject.put(Laisee.TYPE_TRIBE, jSONArray);
            }
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }
}
