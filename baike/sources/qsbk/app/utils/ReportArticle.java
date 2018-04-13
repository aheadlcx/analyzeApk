package qsbk.app.utils;

import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;

public class ReportArticle {
    private static int a = 0;
    private static boolean b = false;
    public static final HashMap<String, Integer> mReportArtcicle = new HashMap();

    private ReportArticle() {
    }

    public static void setReportArticle(Article article, int i) {
        if (article != null) {
            String str = article.id;
            if (!mReportArtcicle.containsKey(str)) {
                mReportArtcicle.put(str, Integer.valueOf(i));
            }
        }
    }

    public static String getString() {
        Set<String> keySet = mReportArtcicle.keySet();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (String str : keySet) {
            stringBuffer.append(str);
            stringBuffer.append("_");
            stringBuffer.append(mReportArtcicle.get(str));
            stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        if (stringBuffer.length() > 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static void init() {
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("_report_article_");
        if (sharePreferencesValue.length() > 2) {
            for (String split : sharePreferencesValue.substring(1, sharePreferencesValue.length() - 1).split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                String[] split2 = split.split("_");
                mReportArtcicle.put(split2[0], Integer.valueOf(split2[1]));
            }
        }
    }

    public static void martReport() {
        mReportArtcicle.clear();
    }

    public static void destroy() {
        save2Local();
        mReportArtcicle.clear();
    }

    public static void stop() {
        save2Local();
    }

    public static boolean getIsReporting() {
        return b;
    }

    public static void save2Local() {
        SharePreferenceUtils.setSharePreferencesValue("_report_article_", getString());
    }

    public static void report(JSONArray jSONArray, boolean z) {
        if (!b) {
            if (a > 1) {
                a = 0;
            } else if (jSONArray.length() > 0) {
                Map hashMap = new HashMap();
                hashMap.put("data", jSONArray);
                SimpleHttpTask awVar = new aw(qsbk.app.Constants.REPORT_ARTICLES, new av(z, jSONArray));
                awVar.setMapParams(hashMap);
                awVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }
    }

    public static void reportHandler(boolean z) {
        if (HttpUtils.isNetworkConnected(QsbkApp.mContext)) {
            JSONArray jSONArray = new JSONArray();
            if (mReportArtcicle.keySet().size() > 0) {
                for (Entry entry : mReportArtcicle.entrySet()) {
                    String str = (String) entry.getKey();
                    int parseInt = Integer.parseInt(String.valueOf(entry.getValue()));
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("id", str);
                        jSONObject.put("reason", parseInt);
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (jSONArray.length() > 0) {
                    report(jSONArray, z);
                }
            }
        }
    }
}
