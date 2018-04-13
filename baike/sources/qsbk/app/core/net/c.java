package qsbk.app.core.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class c extends JsonObjectRequest {
    final /* synthetic */ NetworkCallback a;
    final /* synthetic */ NetRequest b;

    c(NetRequest netRequest, int i, String str, JSONObject jSONObject, Listener listener, ErrorListener errorListener, NetworkCallback networkCallback) {
        this.b = netRequest;
        this.a = networkCallback;
        super(i, str, jSONObject, listener, errorListener);
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = this.a.getHeaders();
        if (headers == null) {
            return new HashMap();
        }
        return headers;
    }
}
