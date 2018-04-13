package cn.v6.sixrooms.net;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.base.VLAsyncHandler$VLAsyncRes;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.MultipartEntity;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkServiceSingleton {
    private static volatile NetworkServiceSingleton a;

    public static NetworkServiceSingleton createInstance() {
        if (a == null) {
            synchronized (NetworkServiceSingleton.class) {
                if (a == null) {
                    a = new NetworkServiceSingleton();
                }
            }
        }
        return a;
    }

    private NetworkServiceSingleton() {
    }

    public void sendRequest(VLAsyncHandler<String> vLAsyncHandler, String str, String str2) {
        b((VLAsyncHandler) vLAsyncHandler, str, str2);
    }

    public void sendRequest(VLAsyncHandler<String> vLAsyncHandler, String str, List<NameValuePair> list) {
        b((VLAsyncHandler) vLAsyncHandler, str, (List) list);
    }

    public void sendAsyncRequest(VLAsyncHandler<String> vLAsyncHandler, String str, String str2) {
        VLScheduler.instance.schedule(0, 2, new a(this, vLAsyncHandler, str, str2));
    }

    public void sendAsyncRequest(VLAsyncHandler<String> vLAsyncHandler, String str, List<NameValuePair> list) {
        VLScheduler.instance.schedule(0, 2, new b(this, vLAsyncHandler, str, list));
    }

    public void uploadFileOrPic(VLAsyncHandler<String> vLAsyncHandler, String str, MultipartEntity multipartEntity) {
        VLScheduler.instance.schedule(0, 2, new c(this, vLAsyncHandler, str, multipartEntity));
    }

    private static void b(VLAsyncHandler<String> vLAsyncHandler, String str, String str2) {
        InputStream sendGetRequest;
        if (TextUtils.isEmpty(str2)) {
            sendGetRequest = NetworkManager.getInstance().sendGetRequest(str);
        } else {
            sendGetRequest = NetworkManager.getInstance().sendPostRequest(str, str2);
        }
        if (sendGetRequest != null) {
            String loadToString = StreamUtils.loadToString(sendGetRequest);
            a(loadToString);
            if (vLAsyncHandler != null) {
                vLAsyncHandler.handlerSuccess(loadToString);
            }
        } else if (vLAsyncHandler != null) {
            try {
                vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResFailed, CommonStrs.NET_CONNECT_FAIL);
            } catch (IOException e) {
                e.printStackTrace();
                if (vLAsyncHandler != null) {
                    vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResFailed, CommonStrs.NET_CONNECT_FAIL);
                }
            }
        }
    }

    private static void b(VLAsyncHandler<String> vLAsyncHandler, String str, List<NameValuePair> list) {
        InputStream sendGetRequest;
        if (list == null || list.size() <= 0) {
            sendGetRequest = NetworkManager.getInstance().sendGetRequest(str);
        } else {
            sendGetRequest = NetworkManager.getInstance().sendPostRequest(str, list);
        }
        if (sendGetRequest != null) {
            String loadToString = StreamUtils.loadToString(sendGetRequest);
            a(loadToString);
            if (vLAsyncHandler != null) {
                vLAsyncHandler.handlerSuccess(loadToString);
            }
        } else if (vLAsyncHandler != null) {
            try {
                vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResFailed, CommonStrs.NET_CONNECT_FAIL);
            } catch (IOException e) {
                e.printStackTrace();
                if (vLAsyncHandler != null) {
                    vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResFailed, CommonStrs.NET_CONNECT_FAIL);
                }
            }
        }
    }

    private static void a(String str) {
        try {
            if (GlobleValue.getUserBean() != null && !TextUtils.isEmpty(GlobleValue.getUserBean().getId()) && str.contains("flag") && str.contains("key")) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("flag") || jSONObject.has("key")) {
                    Object string = jSONObject.getString("flag");
                    if (!TextUtils.isEmpty(string) && !string.equals(CommonStrs.FLAG_TYPE_NEED_LOGIN)) {
                        String string2 = jSONObject.getString("key");
                        if (!TextUtils.isEmpty(string2) && !string2.equals("0") && !SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()).equals(string2)) {
                            SaveUserInfoUtils.saveEncpass(V6Coop.getInstance().getContext(), string2);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void a(VLAsyncHandler vLAsyncHandler, String str, MultipartEntity multipartEntity) {
        InputStream sendPostRequestUploadFile = NetworkManager.getInstance().sendPostRequestUploadFile(str, multipartEntity);
        if (sendPostRequestUploadFile != null) {
            String loadToString = StreamUtils.loadToString(sendPostRequestUploadFile);
            a(loadToString);
            if (vLAsyncHandler != null) {
                vLAsyncHandler.handlerSuccess(loadToString);
            }
        } else if (vLAsyncHandler != null) {
            try {
                vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResFailed, CommonStrs.NET_CONNECT_FAIL);
            } catch (IOException e) {
                e.printStackTrace();
                if (vLAsyncHandler != null) {
                    vLAsyncHandler.handlerError(VLAsyncHandler$VLAsyncRes.VLAsyncResFailed, CommonStrs.NET_CONNECT_FAIL);
                }
            }
        }
    }
}
