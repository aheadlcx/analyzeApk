package qsbk.app.model;

import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.SharePreferenceUtils;

public class EditorMsg {
    public String bottomDesc;
    public String brief;
    public String highlight;
    public String icon;
    public Map<String, String> links;
    public String login;
    public String msgId;
    public String qq;
    public String tribeId;
    public Vote vote;

    public static class Vote {
        public String down;
        public String downDesc;
        public String my;
        public String up;
        public String upDesc;

        public Vote(String str, String str2, String str3, String str4, String str5) {
            this.up = str;
            this.down = str2;
            this.upDesc = str3;
            this.downDesc = str4;
            this.my = str5;
        }

        public static Vote mock() {
            return new Vote("60 %赏了笑脸给小编", "40%扣小编工资", "赏个笑脸给小编", "扣小编工资", "up");
        }

        public boolean isVoted() {
            return !TextUtils.isEmpty(this.my);
        }

        public boolean isVoteUp() {
            return TextUtils.equals(this.my, "up");
        }
    }

    public EditorMsg(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Vote vote) {
        this.msgId = str;
        this.login = str2;
        this.icon = str3;
        this.brief = str4;
        this.bottomDesc = str5;
        this.qq = str6;
        this.tribeId = str7;
        this.highlight = str8;
        this.vote = vote;
        a();
    }

    private void a() {
        this.links = new HashMap();
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("promote_inform");
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            try {
                JSONArray jSONArray = new JSONArray(sharePreferencesValue);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    CharSequence optString = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
                    CharSequence optString2 = jSONObject.optString("url");
                    if (!(TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2))) {
                        this.links.put(optString, optString2);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void vote(boolean z, SimpleCallBack simpleCallBack) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.HIGH_LIGHT_VOTE, new Object[]{this.msgId}), new h(this, simpleCallBack));
        Map hashMap = new HashMap();
        hashMap.put("action", z ? "up" : "down");
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public String toString() {
        return "EditorMsg{login='" + this.login + '\'' + ", icon='" + this.icon + '\'' + ", brief='" + this.brief + '\'' + '}';
    }
}
