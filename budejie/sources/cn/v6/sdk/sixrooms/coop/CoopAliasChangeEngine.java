package cn.v6.sdk.sixrooms.coop;

import android.util.Log;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class CoopAliasChangeEngine {
    public static final String TAG = "CoopAliasChangeEngine";
    private CallBack callBack;

    public interface CallBack {
        void failed(String str);

        void success();
    }

    public CoopAliasChangeEngine(CallBack callBack) {
        this.callBack = callBack;
    }

    public void coopAliasChange(String str, String str2, String str3, String str4, String str5) {
        String encode;
        try {
            encode = URLEncoder.encode(str2, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            encode = "";
        }
        encode = UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-coopAliasChange.php&coop=" + str + "&alias=" + encode + "&encpass=" + str3 + "&logiuid=" + str4 + "&avatar=" + str5;
        Log.d(TAG, encode);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new VLAsyncHandler<String>(null, 0) {
            protected void handler(boolean z) {
                if (z) {
                    try {
                        JSONObject jSONObject = new JSONObject((String) getParam());
                        String string = jSONObject.getString("flag");
                        String string2 = jSONObject.getString("content");
                        if ("001".equals(string)) {
                            CoopAliasChangeEngine.this.callBack.success();
                        } else {
                            CoopAliasChangeEngine.this.callBack.failed(string2);
                        }
                    } catch (JSONException e) {
                        CoopAliasChangeEngine.this.callBack.failed(V6Coop.PARSE_JSON_ERROR);
                        e.printStackTrace();
                    }
                } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr())) {
                    CoopAliasChangeEngine.this.callBack.failed(V6Coop.NET_ERROR);
                }
            }
        }, encode, "");
    }
}
