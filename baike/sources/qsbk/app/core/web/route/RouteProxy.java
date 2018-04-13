package qsbk.app.core.web.route;

import android.text.TextUtils;
import android.webkit.WebView;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.utils.LogUtils;

public class RouteProxy {
    private WebView a;
    private Route b = new Route();

    public RouteProxy(WebView webView) {
        this.a = webView;
    }

    public void reqWeb(String str, String str2, String str3, IWebResponse iWebResponse) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && iWebResponse != null) {
            String recordNode = this.b.recordNode(iWebResponse);
            String str4 = "%s('%s','%s', '%s',%s);";
            Object[] objArr = new Object[5];
            objArr[0] = "javascript:Interface.reqWeb";
            objArr[1] = str;
            objArr[2] = str2;
            objArr[3] = recordNode;
            if (TextUtils.isEmpty(str3)) {
                str3 = "null";
            }
            objArr[4] = str3;
            recordNode = String.format(str4, objArr);
            LogUtils.d("call js with script:" + recordNode);
            this.a.loadUrl(recordNode);
        }
    }

    public void onWebResp(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            IWebResponse removeNode = this.b.removeNode(str);
            if (removeNode != null) {
                removeNode.webResponse(jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
