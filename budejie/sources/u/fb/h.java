package u.fb;

import android.content.Context;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserReply;
import com.umeng.fb.model.UserTitleReply;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h {
    private static final String a = h.class.getName();
    private Context b;

    public h(Context context) {
        this.b = context;
    }

    public j a(i iVar) {
        int nextInt = new Random().nextInt(1000);
        String str = iVar.f;
        String str2 = iVar.d;
        JSONObject jSONObject = iVar.e;
        if (!(iVar instanceof i)) {
            b.b(a, "request type error, request must be type of FbReportRequest");
            return null;
        } else if (str.length() <= 1) {
            b.b(a, new StringBuilder(String.valueOf(nextInt)).append(":\tInvalid baseUrl.").toString());
            return null;
        } else {
            HttpGet httpGet;
            if (str2 != null) {
                b.a(a, new StringBuilder(String.valueOf(nextInt)).append(": post: ").append(str).append(" ").append(jSONObject.toString()).toString());
                List arrayList = new ArrayList(1);
                arrayList.add(new BasicNameValuePair(str2, jSONObject.toString()));
                try {
                    HttpEntity urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList, "UTF-8");
                    HttpRequest httpPost = new HttpPost(str);
                    httpPost.addHeader(urlEncodedFormEntity.getContentType());
                    ((HttpPost) httpPost).setEntity(urlEncodedFormEntity);
                    httpGet = httpPost;
                } catch (UnsupportedEncodingException e) {
                    throw new AssertionError(e);
                }
            }
            b.a(a, new StringBuilder(String.valueOf(nextInt)).append(":\tget: ").append(str).toString());
            httpGet = new HttpGet(str);
            HttpClient defaultHttpClient = new DefaultHttpClient();
            HttpParams params = defaultHttpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 30000);
            HttpConnectionParams.setSoTimeout(params, 30000);
            ConnManagerParams.setTimeout(params, 30000);
            try {
                HttpResponse execute = defaultHttpClient.execute(httpGet);
                if (execute.getStatusLine().getStatusCode() != 200) {
                    return null;
                }
                String entityUtils = EntityUtils.toString(execute.getEntity());
                b.a(a, "res :" + entityUtils);
                return new j(new JSONObject(entityUtils));
            } catch (Exception e2) {
                b.c(a, new StringBuilder(String.valueOf(nextInt)).append(":\tClientProtocolException,Failed to send message.").append(str).toString(), e2);
                return null;
            } catch (Exception e22) {
                b.c(a, new StringBuilder(String.valueOf(nextInt)).append(":\tIOException,Failed to send message.").append(str).toString(), e22);
                return null;
            }
        }
    }

    public List<DevReply> a(List<String> list, String str, String str2) {
        if (list == null || list.size() == 0 || g.b(str2)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str3 : list) {
            String str32;
            if (!g.b(str32)) {
                stringBuilder.append(str32);
                stringBuilder.append(",");
            }
        }
        if (stringBuilder.length() > 1) {
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        }
        StringBuilder stringBuilder2 = new StringBuilder("http://feedback.umeng.com/feedback/reply");
        stringBuilder2.append("?appkey=" + str2);
        stringBuilder2.append("&feedback_id=" + stringBuilder);
        if (!g.b(str)) {
            stringBuilder2.append("&startkey=" + str);
        }
        b.c(a, "getDevReply url: " + stringBuilder2);
        HttpClient defaultHttpClient = new DefaultHttpClient();
        HttpParams params = defaultHttpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 30000);
        HttpConnectionParams.setSoTimeout(params, 30000);
        ConnManagerParams.setTimeout(params, 30000);
        try {
            HttpResponse execute = defaultHttpClient.execute(new HttpGet(stringBuilder2.toString()));
            if (execute.getStatusLine().getStatusCode() == 200) {
                str32 = EntityUtils.toString(execute.getEntity());
                b.c(a, "getDevReply resp: " + str32);
                JSONArray jSONArray = new JSONArray(str32);
                List<DevReply> arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            try {
                                arrayList.add(new DevReply(jSONArray2.getJSONObject(i2)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                return arrayList;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public boolean a(Reply reply) throws IllegalArgumentException {
        if (reply == null) {
            return true;
        }
        if (reply instanceof UserReply) {
            return a((UserReply) reply);
        }
        if (reply instanceof UserTitleReply) {
            return a((UserTitleReply) reply);
        }
        throw new IllegalArgumentException("Illegal argument: " + reply.getClass().getName() + ". reply must be " + UserReply.class.getName() + " or " + UserTitleReply.class.getName() + ".");
    }

    private boolean a(UserReply userReply) {
        try {
            JSONObject toJson = userReply.toJson();
            a(toJson);
            b(toJson);
            j a = a(new i("reply", toJson, "http://feedback.umeng.com/feedback/reply"));
            if (a != null && "ok".equalsIgnoreCase(a.a().get("state").toString())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean a(UserTitleReply userTitleReply) {
        try {
            JSONObject toJson = userTitleReply.toJson();
            a(toJson);
            b(toJson);
            j a = a(new i("feedback", toJson, "http://feedback.umeng.com/feedback/feedbacks"));
            if (a != null && "ok".equalsIgnoreCase(a.a().get("state").toString())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void a(JSONObject jSONObject) {
        try {
            JSONObject b = p.b(this.b);
            b.c(a, "addRequestHeader: " + b.toString());
            Iterator keys = b.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject.put(str, b.get(str));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void b(JSONObject jSONObject) {
        try {
            long userInfoLastSyncAt = Store.getInstance(this.b).getUserInfoLastSyncAt();
            long userInfoLastUpdateAt = Store.getInstance(this.b).getUserInfoLastUpdateAt();
            b.c(a, "addUserInfoIfNotSynced: last_sync_at=" + userInfoLastSyncAt + " last_update_at=" + userInfoLastUpdateAt);
            if (userInfoLastSyncAt < userInfoLastUpdateAt) {
                jSONObject.put("userinfo", Store.getInstance(this.b).getUserInfo().toJson());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
