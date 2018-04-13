package qsbk.app.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class Remark {
    public static final int FAILED = 2;
    public static final int SUCCESS = 1;
    public String remark;
    public int state;
    public long t;
    public String uid;
    public String uname;

    public Remark(String str, String str2, long j, int i, String str3) {
        this.uid = str;
        this.remark = str2;
        this.t = j;
        this.state = i;
        this.uname = str3;
    }

    public static Remark parseJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            Remark remark = new Remark();
            remark.uid = jSONObject.optString("uid");
            remark.uname = jSONObject.optString("uname", "");
            remark.remark = jSONObject.optString("remark");
            remark.state = jSONObject.optInt("state", 1);
            remark.t = jSONObject.optLong("t", new Date().getTime());
            return remark;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Remark> parseJsonFromServer(JSONObject jSONObject) {
        ArrayList<Remark> arrayList = new ArrayList();
        if (jSONObject != null) {
            try {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    Remark remark = new Remark();
                    String obj = keys.next().toString();
                    remark.uid = obj;
                    remark.remark = jSONObject.getString(obj);
                    remark.uname = jSONObject.optString("uname", "");
                    remark.state = jSONObject.optInt("state", 1);
                    remark.t = jSONObject.optLong("t", new Date().getTime());
                    if (!(TextUtils.isEmpty(remark.remark) || TextUtils.isEmpty(remark.uid))) {
                        arrayList.add(remark);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static JSONObject toJson(Remark remark) {
        if (remark == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("uid", remark.uid);
            jSONObject.put("uname", remark.uname);
            jSONObject.put("remark", remark.remark);
            jSONObject.put("t", remark.t);
            jSONObject.put("state", remark.state);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJsonForServer(ArrayList<Remark> arrayList) {
        if (arrayList == null || (arrayList != null && arrayList.size() == 0)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Remark remark = (Remark) it.next();
                if (TextUtils.isEmpty(remark.remark)) {
                    remark.remark = "";
                }
                jSONObject.put(remark.uid, remark.remark);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJsonForServer(Remark remark) {
        String str = null;
        if (remark != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (TextUtils.isEmpty(remark.remark)) {
                    remark.remark = "";
                }
                jSONObject.put(remark.uid, remark.remark);
                str = jSONObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public String toString() {
        return "Remark{  uid=" + this.uid + ", remark='" + this.remark + '\'' + ", t=" + this.t + ", state=" + this.state + ", uname='" + this.uname + '\'' + '}';
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Remark)) {
            return false;
        }
        if ((obj instanceof Remark) && TextUtils.equals(((Remark) obj).uid, this.uid)) {
            return true;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.uid.hashCode();
    }
}
