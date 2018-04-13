package qsbk.app.utils;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;
import qsbk.app.model.Comment;

public class ReportCommon {
    private static int a = 0;
    private static boolean b = false;
    public static final HashMap<String, String> mReportCommon = new HashMap();

    private ReportCommon() {
    }

    public static void setReportCommon(Article article, int i, Comment comment) {
        if (article != null && comment != null) {
            String str = comment.id;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str + "_" + i + "_" + article.id + "_" + comment.uid + "_" + article.user_id);
            if (!mReportCommon.containsKey(str)) {
                mReportCommon.put(str, stringBuffer.toString());
            }
        }
    }

    public static String getString() {
        Set<String> keySet = mReportCommon.keySet();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (String str : keySet) {
            stringBuffer.append((String) mReportCommon.get(str));
            stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        if (stringBuffer.length() > 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public static void init() {
        LogUtil.e("report article int");
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("_report_common_");
        LogUtil.e("tmp===" + sharePreferencesValue);
        if (sharePreferencesValue.length() > 2) {
            for (String str : sharePreferencesValue.substring(1, sharePreferencesValue.length() - 1).split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                mReportCommon.put(str.split("_")[0], str);
            }
        }
    }

    public static void martReport() {
        mReportCommon.clear();
    }

    public static void destroy() {
        save2Local();
        mReportCommon.clear();
    }

    public static void stop() {
        save2Local();
    }

    public static boolean getIsReporting() {
        return b;
    }

    public static void save2Local() {
        SharePreferenceUtils.setSharePreferencesValue("_report_common_", getString());
    }

    public static void report(JSONArray jSONArray, boolean z) {
        if (!b) {
            if (a > 1) {
                a = 0;
            } else if (jSONArray.length() > 0) {
                Map hashMap = new HashMap();
                hashMap.put("data", jSONArray);
                SimpleHttpTask ayVar = new ay(qsbk.app.Constants.REPORT_COMMENTS, new ax(z, jSONArray));
                ayVar.setMapParams(hashMap);
                ayVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }
    }

    public static void reportHandler(boolean z) {
        if (HttpUtils.isNetworkConnected(QsbkApp.mContext)) {
            JSONArray jSONArray = new JSONArray();
            if (mReportCommon.keySet().size() > 0) {
                for (Entry entry : mReportCommon.entrySet()) {
                    String str = (String) entry.getKey();
                    String[] split = ((String) entry.getValue()).split("_");
                    Object obj = new String[]{"abusive", "porn", ManageMyContentsAdapter.SPAM, "waste"}[Integer.parseInt(split[1])];
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("id", str);
                        jSONObject.put("reason", obj);
                        jSONObject.put("article_id", split[2]);
                        jSONObject.put("user_id", split[3]);
                        if (!TextUtils.isEmpty(split[4])) {
                            jSONObject.put("author_id", split[4]);
                        }
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
