package cn.tatagou.sdk.a;

import android.util.Log;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.v6.sixrooms.constants.CommonInts;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.alipay.sdk.data.a;
import com.taobao.dp.http.ResCode;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import okhttp3.ab;
import okhttp3.s;
import retrofit2.d;
import retrofit2.l;

public class b {
    private static final String a = b.class.getSimpleName();

    private static int b(Throwable th) {
        Log.e("TTG", "apiFail: " + th.getMessage(), th);
        String message = th.getMessage();
        if (TtgSDK.getContext() != null) {
            if (!p.isNetworkOpen(TtgSDK.getContext())) {
                return 10000;
            }
            if (!p.isEmpty(message) && (message.contains("java.lang.IllegalStateException") || message.contains("okhttp3") || message.contains("No address associated with hostname") || message.contains("tatagou") || message.contains("com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException"))) {
                return SystemMessageConstants.USER_CANCEL_CODE;
            }
            if (!p.isEmpty(message) && message.contains("Permission denied")) {
                return 10001;
            }
            if (!p.isEmpty(message) && message.contains(a.f)) {
                return CommonInts.GT_ERROR;
            }
            if ((!p.isEmpty(message) && message.contains("Unexpected exception")) || p.isEmpty(message)) {
                return ResCode.NPE_WSG_DECRYTION;
            }
            if (!(message.contains("Canceled") || message.contains("Socket is closed"))) {
                if (message.contains("Socket closed")) {
                    return 20000;
                }
                return ResCode.NPE_WSG_DECRYTION;
            }
        }
        return 20000;
    }

    public static <T> void onRequestAPIaaa(final IUpdateViewManager iUpdateViewManager, retrofit2.b<ab> bVar, final String str, final Type type) {
        if (bVar != null) {
            bVar.a(new d<ab>() {
                public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
                    b.b(iUpdateViewManager, str, bVar, lVar, type);
                }

                public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
                    if (!p.isEmpty(str) && iUpdateViewManager != null) {
                        iUpdateViewManager.notifyIUpdateView(str, null, b.b(th));
                    }
                }
            });
        } else {
            b(iUpdateViewManager, str, null, null, type);
        }
    }

    private static <T> void a(retrofit2.b<T> bVar, String str) {
        if (bVar != null) {
            try {
                if (!p.isEmpty(str)) {
                    s a = bVar.e().a();
                    if (a != null && a.a() != null) {
                        URI a2 = a.a();
                        c.getInstance().addProperty(a2.toString(), str.replace("W/", ""));
                        Log.d(a, "onResponseHome saveUri: " + a2.toString() + "::---eTag---" + str);
                    }
                }
            } catch (Throwable e) {
                Log.e(a, "onSaveEtag: " + e.getMessage(), e);
            }
        }
    }

    private static <T> void b(IUpdateViewManager iUpdateViewManager, String str, retrofit2.b<ab> bVar, l<ab> lVar, Type type) {
        if (iUpdateViewManager != null && str != null && lVar != null) {
            Object onParseBodyData = onParseBodyData((ab) lVar.e(), type);
            int a = lVar.a();
            if (onParseBodyData != null) {
                String a2 = lVar.c() != null ? lVar.c().a("ETag") : null;
                if (a == 200 && a2 != null) {
                    a(bVar, a2);
                }
            }
            iUpdateViewManager.notifyIUpdateView(str, onParseBodyData, a);
        } else if (iUpdateViewManager != null && str != null) {
            iUpdateViewManager.notifyIUpdateView(str, null, ResCode.NPE_WSG_DECRYTION);
        }
    }

    public static <T> void onRequestAPI(a aVar, retrofit2.b<ab> bVar, String str, Type type) {
        if (bVar != null) {
            try {
                URI a = bVar.e().a().a();
                Log.d(a, "onRequestAPI: " + a.getHost() + a.getPath());
                b(aVar, bVar, bVar.a(), type);
                return;
            } catch (Exception e) {
                Log.d(a, "onRequestAPI: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        b(aVar, null, null, type);
    }

    public static <T> void onCommRequestApi(final a<T> aVar, retrofit2.b<ab> bVar, final Type type) {
        if (bVar != null) {
            bVar.a(new d<ab>() {
                public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
                    b.b(aVar, bVar, lVar, type);
                }

                public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
                    if (aVar != null) {
                        aVar.onApiDataResult(null, b.b(th));
                    }
                }
            });
        } else {
            b(aVar, null, null, type);
        }
    }

    private static <T> void b(a<T> aVar, retrofit2.b<ab> bVar, l<ab> lVar, Type type) {
        if (aVar == null) {
            return;
        }
        if (lVar != null) {
            Object onParseBodyData = onParseBodyData((ab) lVar.e(), type);
            int a = lVar.a();
            if (onParseBodyData != null) {
                String a2 = lVar.c() != null ? lVar.c().a("ETag") : null;
                if (a == 200 && a2 != null) {
                    a(bVar, a2);
                }
            }
            aVar.onApiDataResult(onParseBodyData, a);
            return;
        }
        aVar.onApiDataResult(null, ResCode.NPE_WSG_DECRYTION);
    }

    public static <T> T onParseBodyData(ab abVar, Type type) {
        if (abVar != null) {
            try {
                Reader e = abVar.e();
                JSONReader jSONReader = new JSONReader(e);
                T readObject = jSONReader.readObject(type);
                Log.d(a, "onParseBodyData: " + JSON.toJSONString(readObject));
                jSONReader.close();
                e.close();
                return readObject;
            } catch (Throwable e2) {
                Log.d(a, "onParseBodyData:" + e2.getMessage(), e2);
            }
        }
        return null;
    }
}
