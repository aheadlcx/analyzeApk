package com.umeng.commonsdk.proguard;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.statistics.common.e;
import org.json.JSONException;
import org.json.JSONObject;

class bd extends f {
    final /* synthetic */ d a;
    final /* synthetic */ bc b;

    bd(bc bcVar, d dVar) {
        this.b = bcVar;
        this.a = dVar;
    }

    public void a(Location location) {
        if (location != null) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            float accuracy = location.getAccuracy();
            e.a("UMSysLocationCache", "lon is " + longitude + ", lat is " + latitude + ", acc is " + accuracy + ", alt is " + location.getAltitude());
            if (!(longitude == 0.0d || latitude == 0.0d)) {
                long time = location.getTime();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("lng", longitude);
                    jSONObject.put("lat", latitude);
                    jSONObject.put("ts", time);
                    jSONObject.put("acc", (double) accuracy);
                    jSONObject.put("alt", r6);
                } catch (JSONException e) {
                    e.a("UMSysLocationCache", "e is " + e);
                }
                e.a("UMSysLocationCache", "locationJSONObject is " + jSONObject.toString());
                synchronized (e.d) {
                    try {
                        SharedPreferences sharedPreferences = this.b.a.getSharedPreferences(a.o, 0);
                        if (sharedPreferences == null) {
                            return;
                        }
                        Editor edit = sharedPreferences.edit();
                        edit.putString(a.p, jSONObject.toString());
                        edit.commit();
                    } catch (Throwable th) {
                        b.a(this.b.a, th);
                    }
                }
            }
        }
        this.a.a();
    }
}
