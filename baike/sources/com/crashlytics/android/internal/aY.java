package com.crashlytics.android.internal;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.utils.ACache;

public class aY {
    aY() {
    }

    public aX a(ah ahVar, JSONObject jSONObject) throws JSONException {
        long j;
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", ACache.TIME_HOUR);
        JSONObject jSONObject2 = jSONObject.getJSONObject("app");
        String string = jSONObject2.getString("identifier");
        String string2 = jSONObject2.getString("status");
        String string3 = jSONObject2.getString("url");
        String string4 = jSONObject2.getString("reports_url");
        boolean optBoolean = jSONObject2.optBoolean("update_required", false);
        bn bnVar = null;
        if (jSONObject2.has("icon") && jSONObject2.getJSONObject("icon").has("hash")) {
            jSONObject2 = jSONObject2.getJSONObject("icon");
            bnVar = new bn(jSONObject2.getString("hash"), jSONObject2.getInt(IndexEntry.COLUMN_NAME_WIDTH), jSONObject2.getInt(IndexEntry.COLUMN_NAME_HEIGHT));
        }
        aM aMVar = new aM(string, string2, string3, string4, optBoolean, bnVar);
        JSONObject jSONObject3 = jSONObject.getJSONObject("session");
        aR aRVar = new aR(jSONObject3.optInt("log_buffer_size", 64000), jSONObject3.optInt("max_chained_exception_depth", 8), jSONObject3.optInt("max_custom_exception_events", 64), jSONObject3.optInt("max_custom_key_value_pairs", 64), jSONObject3.optInt("identifier_mask", 255), jSONObject3.optBoolean("send_session_without_crash", false));
        JSONObject jSONObject4 = jSONObject.getJSONObject("prompt");
        aQ aQVar = new aQ(jSONObject4.optString("title", "Send Crash Report?"), jSONObject4.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject4.optString("send_button_title", "Send"), jSONObject4.optBoolean("show_cancel_button", true), jSONObject4.optString("cancel_button_title", "Don't Send"), jSONObject4.optBoolean("show_always_send_button", true), jSONObject4.optString("always_send_button_title", "Always Send"));
        JSONObject jSONObject5 = jSONObject.getJSONObject("features");
        aP aPVar = new aP(jSONObject5.optBoolean("prompt_enabled", false), jSONObject5.optBoolean("collect_logged_exceptions", true), jSONObject5.optBoolean("collect_reports", true), jSONObject5.optBoolean("collect_analytics", false));
        jSONObject5 = jSONObject.getJSONObject("analytics");
        aK aKVar = new aK(jSONObject5.optString("url", "https://e.crashlytics.com/spi/v2/events"), jSONObject5.optInt("flush_interval_secs", 600), jSONObject5.optInt("max_byte_size_per_file", 8000), jSONObject5.optInt("max_file_count_per_send", 1), jSONObject5.optInt("max_pending_send_file_count", 100));
        long j2 = (long) optInt2;
        if (jSONObject.has("expires_at")) {
            j = jSONObject.getLong("expires_at");
        } else {
            j = ahVar.a() + (j2 * 1000);
        }
        return new aX(j, aMVar, aRVar, aQVar, aPVar, aKVar, optInt, optInt2);
    }

    public JSONObject a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
        jSONObject2.getJSONObject("features").remove("collect_analytics");
        jSONObject2.remove("analytics");
        return jSONObject2;
    }
}
