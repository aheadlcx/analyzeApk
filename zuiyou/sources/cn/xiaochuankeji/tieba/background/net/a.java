package cn.xiaochuankeji.tieba.background.net;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuankeji.tieba.network.custom.a.c;
import cn.xiaochuankeji.tieba.network.f;
import com.meizu.cloud.pushsdk.pushtracer.constant.TrackerConstants;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.u;
import okhttp3.w;
import okhttp3.y$a;
import okhttp3.z;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static final u JSON = u.a(TrackerConstants.POST_CONTENT_TYPE);
    private Object bindParam;
    private Handler callbackHandler;
    private a errorListener;
    private JSONObject requestBodyJson;
    private b<JSONObject> successListener;
    private String url;

    public interface b<T> {
        void onResponse(T t, Object obj);
    }

    public interface a {
        void onErrorResponse(XCError xCError, Object obj);
    }

    public a(String str, b<JSONObject> bVar, a aVar) {
        this(str, cn.xiaochuankeji.tieba.background.utils.d.a.b(), null, bVar, aVar);
    }

    public a(String str, JSONObject jSONObject, Object obj, b<JSONObject> bVar, a aVar) {
        this.url = str;
        this.requestBodyJson = jSONObject;
        this.bindParam = obj;
        this.successListener = bVar;
        this.errorListener = aVar;
        this.callbackHandler = new Handler(Looper.getMainLooper());
    }

    private w buildHttpClient() {
        okhttp3.w.a aVar = new okhttp3.w.a();
        aVar.a(new c());
        aVar.a(cn.xiaochuankeji.tieba.network.custom.a.a.a());
        SSLSocketFactory fVar = new f();
        aVar.a(new cn.xiaochuankeji.tieba.network.custom.a());
        try {
            aVar.a(fVar, f.a());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        aVar.a(10, TimeUnit.SECONDS).b(15, TimeUnit.SECONDS).c(15, TimeUnit.SECONDS);
        return aVar.a();
    }

    private z buildJsonRequestBody(JSONObject jSONObject) {
        if (jSONObject != null) {
            return z.create(JSON, jSONObject.toString());
        }
        return z.create(JSON, "");
    }

    public void execute() {
        buildHttpClient().a(new y$a().a(this.url).a(buildJsonRequestBody(this.requestBodyJson)).b("ZYP", "mid=" + cn.xiaochuankeji.tieba.background.a.g().c()).d()).a(new okhttp3.f(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(e eVar, IOException iOException) {
                XCError xCError;
                if (NetworkMonitor.a()) {
                    xCError = new XCError(iOException.getMessage());
                } else {
                    xCError = new XCError("网络不给力哦~");
                }
                this.a.postErrorMessage(xCError);
            }

            public void a(e eVar, aa aaVar) throws IOException {
                if (aaVar.i()) {
                    XCError xCError = new XCError("请求被重定向到：" + aaVar.a("Location"));
                    if (this.a.errorListener != null) {
                        this.a.errorListener.onErrorResponse(xCError, this.a.bindParam);
                    }
                }
                if (aaVar.c()) {
                    int b = aaVar.b();
                    if (b != 200) {
                        this.a.postErrorMessage(new XCError("请求失败，状态码：" + b));
                    }
                    Object a = aaVar.a("Content-Type");
                    if (TextUtils.isEmpty(a) || !a.contains("text")) {
                        this.a.postErrorMessage(new XCError("请求被劫持"));
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(new String(aaVar.g().bytes(), "utf-8"));
                        if (jSONObject.optInt("ret", 0) == 1) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            if (this.a.successListener != null) {
                                this.a.postSuccessMessage(optJSONObject);
                                return;
                            }
                            return;
                        }
                        this.a.postErrorMessage(new XCError(jSONObject.optString("msg", "请求失败")));
                    } catch (JSONException e) {
                        this.a.postErrorMessage(new XCError("请求结果异常:" + e.getMessage()));
                    } catch (NullPointerException e2) {
                        this.a.postErrorMessage(new XCError("请求结果为空"));
                    }
                }
            }
        });
    }

    private void postSuccessMessage(final JSONObject jSONObject) {
        if (this.callbackHandler != null) {
            this.callbackHandler.post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (this.b.successListener != null) {
                        this.b.successListener.onResponse(jSONObject, this.b.bindParam);
                    }
                }
            });
        }
    }

    private void postErrorMessage(final XCError xCError) {
        if (this.callbackHandler != null) {
            this.callbackHandler.post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (this.b.errorListener != null) {
                        this.b.errorListener.onErrorResponse(xCError, this.b.bindParam);
                    }
                }
            });
        }
    }
}
