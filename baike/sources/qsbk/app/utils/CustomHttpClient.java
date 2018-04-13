package qsbk.app.utils;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.mobstat.StatService;
import com.qiniu.android.http.Client;
import com.qiushibaike.statsdk.StatSDK;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.entity.mime.MIME;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLException;
import okhttp3.FormBody;
import okhttp3.Headers$Builder;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Request$Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.emotion.QiubaiEmotionProvider;
import qsbk.app.image.OkHttpDns;
import qsbk.app.image.OkHttpDnsUtil;
import qsbk.app.image.OkHttpDnsUtil.DnsInfo;

public class CustomHttpClient {
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final int MAX_RETRY = 2;
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MULTIPART = MediaType.parse("multipart/form-data; charset=utf-8");
    public static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain; charset=utf-8");
    private static final String a = CustomHttpClient.class.getSimpleName();
    private static final String[] b = new String[]{"signup", "vote_queue", "review", "v2/signin", "/report"};
    private static OkHttpClient c;

    private static class a implements Interceptor {
        private a() {
        }

        public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
            long currentTimeMillis = System.currentTimeMillis();
            Response response = null;
            try {
                Response proceed = interceptor$Chain.proceed(interceptor$Chain.request());
                if (proceed != null) {
                    currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                    proceed.code();
                    proceed.sentRequestAtMillis();
                    proceed.receivedResponseAtMillis();
                }
                return proceed;
            } catch (IOException e) {
                throw e;
            } catch (Throwable th) {
                if (response != null) {
                    currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                    response.code();
                    response.sentRequestAtMillis();
                    response.receivedResponseAtMillis();
                }
            }
        }
    }

    private static class b {
        private static CustomHttpClient a = new CustomHttpClient();
    }

    private static class c implements Interceptor {
        final int a;

        c(int i) {
            this.a = i;
        }

        public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
            Request request = interceptor$Chain.request();
            Response response = null;
            int i = 0;
            while (i < this.a) {
                try {
                    response = interceptor$Chain.proceed(request);
                    if (response.isSuccessful()) {
                        break;
                    }
                    i++;
                } catch (IOException e) {
                    if (i == this.a - 1) {
                        throw e;
                    }
                }
            }
            return response;
        }
    }

    private CustomHttpClient() {
        Builder builder = new Builder();
        if (DebugUtil.DEBUG) {
            builder.addInterceptor(new a());
        }
        c = builder.dns(new OkHttpDns()).addInterceptor(new c(2)).connectTimeout(10000, TimeUnit.MILLISECONDS).readTimeout(10000, TimeUnit.MILLISECONDS).writeTimeout(10000, TimeUnit.MILLISECONDS).followRedirects(true).followSslRedirects(true).retryOnConnectionFailure(true).sslSocketFactory(HttpsConnectionUtil.getSslSocketFactory(), HttpsConnectionUtil.getX509TrustManager()).hostnameVerifier(HttpsConnectionUtil.getHostnameVerifier()).build();
        HttpsConnectionUtil.setAllTrust();
    }

    private static boolean a(String str) {
        for (String indexOf : b) {
            if (str.indexOf(indexOf) != -1) {
                return true;
            }
        }
        return false;
    }

    private static Request$Builder a(String str, String str2, Request$Builder request$Builder) {
        request$Builder.header("Uuid", DeviceUtils.getAndroidId()).header("Deviceidinfo", DeviceUtils.getDeviceIdInfo()).header("Source", HttpClient.getClientSource()).header("User-Agent", HttpClient.getUserAgent());
        if (QsbkApp.currentUser != null) {
            request$Builder.header("Qbtoken", QsbkApp.currentUser.token);
        }
        try {
            request$Builder.header("Model", Build.MODEL + "_" + VERSION.SDK_INT);
        } catch (Exception e) {
            request$Builder.header("Model", "unknown_" + VERSION.SDK_INT);
        }
        if (!TextUtils.isEmpty(str2) && a(str)) {
            String replaceAll = UUID.randomUUID().toString().trim().replaceAll(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "");
            request$Builder.header(QiubaiEmotionProvider.NAMESPACE, replaceAll);
            request$Builder.header("Url-Verify", HttpClient.verifyStringEncode(str2.toString(), replaceAll));
        }
        return request$Builder;
    }

    public static CustomHttpClient getInstance() {
        return b.a;
    }

    private static boolean a(String str, boolean z) {
        DnsInfo dnsInfo = OkHttpDnsUtil.getInstance().get(str);
        if (dnsInfo == null || !dnsInfo.isHttpDns()) {
            return false;
        }
        if (z) {
            HttpDNSManager.instance().reportOK(dnsInfo.host, dnsInfo.ip);
        } else {
            HttpDNSManager.instance().reportError(dnsInfo.host, dnsInfo.ip);
        }
        return true;
    }

    private static String a(Request request) throws QiushibaikeException {
        String str = null;
        String str2 = "";
        int i = -1;
        TimeDelta timeDelta = new TimeDelta();
        boolean a;
        try {
            Response execute = c.newCall(request).execute();
            i = execute.code();
            if (execute.isSuccessful()) {
                str2 = execute.body().string();
                a = a(request.url().host(), true);
                if (str != null) {
                    a(request.url().toString(), i, (int) timeDelta.getDelta(), a(request.url().host(), false));
                    throw str;
                }
                a(request.url().toString(), i, (int) timeDelta.getDelta(), a);
                return str2;
            }
            throw new QiushibaikeException("服务器正在休假，请联系客服。", i);
        } catch (IOException e) {
            IOException iOException = e;
            int i2 = i;
            String str3 = str2;
            if (iOException instanceof SSLException) {
                StatSDK.onEvent(QsbkApp.mContext, "https", com.umeng.analytics.pro.b.ao, request.url().toString(), iOException.toString(), null);
            }
            str = new QiushibaikeException("服务器离家出走了，请联系客服。", i2);
            a = false;
            i = i2;
            str2 = str3;
        } catch (Exception e2) {
            if (e2 instanceof QiushibaikeException) {
                Object obj = (QiushibaikeException) e2;
                a = false;
            } else {
                str = new QiushibaikeException("发生未知错误，请联系客服。", i);
                a = false;
            }
        }
    }

    private static void a(String str, int i, int i2, boolean z) {
        String network = HttpUtils.getNetwork(QsbkApp.mContext);
        if (!network.equalsIgnoreCase("unconnect")) {
            String normalUrl = HttpClient.getNormalUrl(str);
            if (normalUrl != null && QsbkApp.isInConfigRatio("http_status_ratio", 0)) {
                String str2 = (z ? "hs_ip_" : "hs_dns_") + network + "_" + HttpClient.getHttpStatusEvent(i);
                StatService.onEventDuration(AppContext.getContext(), str2, normalUrl, (long) (i2 * 1000));
                StatSDK.onEventDuration(AppContext.getContext(), str2, normalUrl, (long) i2);
            }
        }
    }

    public OkHttpClient getOKHttpClient() {
        return c;
    }

    private String a(String str, String str2, String str3) throws QiushibaikeException {
        return httpRequest(str, str2, str3, null);
    }

    public String httpRequest(String str, String str2, String str3, Map<String, String> map) throws QiushibaikeException {
        Request$Builder url = new Request$Builder().url(str);
        a(str, str2, url);
        if (!(map == null || map.isEmpty())) {
            for (String str4 : map.keySet()) {
                url.header(str4, (String) map.get(str4));
            }
        }
        if (!"POST".equals(str3)) {
            url.header("Content-Type", Client.JsonMime);
            url.get();
        } else if (TextUtils.isEmpty(str2)) {
            url.post(RequestBody.create(MEDIA_TYPE_JSON, ""));
        } else {
            url.post(RequestBody.create(MEDIA_TYPE_JSON, str2));
        }
        String str5 = "";
        try {
            return a(url.build());
        } catch (QiushibaikeException e) {
            throw e;
        }
    }

    public String get(String str) throws QiushibaikeException {
        return a(str, null, "GET");
    }

    public String post(String str) throws QiushibaikeException {
        return a(str, null, "POST");
    }

    public String post(String str, String str2) throws QiushibaikeException {
        return a(str, str2, "POST");
    }

    public String post(String str, Map<String, Object> map) throws QiushibaikeException {
        StringBuffer stringBuffer = new StringBuffer("");
        if (map != null) {
            try {
                stringBuffer.append(HttpClient.encodeParameters(map));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return a(str, stringBuffer.toString(), "POST");
    }

    public String submit(String str, Map<String, Object> map, String str2) throws QiushibaikeException {
        File file = null;
        if (!TextUtils.isEmpty(str2)) {
            file = new File(str2);
        }
        StringBuffer stringBuffer = new StringBuffer("");
        if (map != null && map.size() > 0) {
            try {
                stringBuffer.append(HttpClient.encodeParameters(map));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Request$Builder url = new Request$Builder().url(str);
        a(str, stringBuffer.toString(), url);
        RequestBody create = RequestBody.create(MEDIA_TYPE_TEXT, stringBuffer.toString());
        String str3 = "image";
        str3 = "sendpic.jpg";
        MultipartBody.Builder type = new MultipartBody.Builder().addPart(new Headers$Builder().add("Content-Disposition: form-data; name=json").add("Content-Transfer-Encoding: 8bit").build(), create).setType(MEDIA_TYPE_MULTIPART);
        if (file != null && file.exists()) {
            type.addFormDataPart("image", "sendpic.jpg", RequestBody.create(MEDIA_TYPE_TEXT, file));
        }
        Request build = url.post(type.build()).build();
        String str4 = "";
        try {
            return a(build);
        } catch (QiushibaikeException e2) {
            throw e2;
        }
    }

    public String submitWithFile(String str, Map<String, Object> map) throws QiushibaikeException {
        if (map == null || map.size() == 0) {
            return "";
        }
        try {
            new StringBuffer("").append(HttpClient.encodeParameters(map));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Request$Builder url = new Request$Builder().url(str);
        a(str, null, url);
        url.header(MIME.CONTENT_DISPOSITION, "form-data");
        url.header(MIME.CONTENT_TRANSFER_ENC, MIME.ENC_8BIT);
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MEDIA_TYPE_MULTIPART);
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof File) {
                File file = (File) value;
                type.addFormDataPart((String) entry.getKey(), file.getName(), RequestBody.create(MEDIA_TYPE_TEXT, file));
            } else {
                type.addFormDataPart((String) entry.getKey(), value.toString());
            }
        }
        String str2 = "";
        try {
            return a(url.post(type.build()).build());
        } catch (QiushibaikeException e2) {
            throw e2;
        }
    }

    public String submitForm(String str, Map<String, Object> map) throws QiushibaikeException {
        StringBuffer stringBuffer = new StringBuffer("");
        if (map != null && map.size() > 0) {
            try {
                stringBuffer.append(HttpClient.encodeParameters(map));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Request$Builder url = new Request$Builder().url(str);
        a(str, stringBuffer.toString(), url);
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                builder.add(str2, String.valueOf(map.get(str2)));
            }
        }
        Request build = url.post(builder.build()).build();
        String str3 = "";
        try {
            return a(build);
        } catch (QiushibaikeException e2) {
            throw e2;
        }
    }
}
