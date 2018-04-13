package qsbk.app.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Pair;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import kotlin.text.Typography;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.emotion.QiubaiEmotionProvider;

public class HttpClient {
    public static final int AUTH_NEED_LOGIN = 107;
    private static final String CHARSET = "utf-8";
    public static final int READ_TIME_OUT = 10000;
    public static final int REQUEST_TIMEOUT_CODE = 408;
    public static final int REQUEST_TIME_OUT = 10000;
    public static final int RESP_CODE_LOCAL_ERROR = 9999;
    public static final int RESP_CODE_SUCCESS = 0;
    private static HttpClient httpClient = null;
    private static int requestCount = 0;
    private static final LruCache<String, Boolean> sSSLExceptionHostUrl = new LruCache(15);
    private long lastSuccessTime = -1;
    private String lastSuccessUrl = null;
    private int responseCode = 1;

    public static native String getEnString();

    public static native String verifyStringEncode(String str, String str2);

    static {
        System.loadLibrary("qbappsecret");
    }

    public static String getLocalErrorStr() {
        return QsbkApp.mContext.getResources().getString(R.string.network_error_retry);
    }

    public static synchronized HttpClient getIntentce() {
        HttpClient httpClient;
        synchronized (HttpClient.class) {
            if (httpClient == null) {
                httpClient = new HttpClient();
                HttpsConnectionUtil.setAllTrust();
            }
            httpClient = httpClient;
        }
        return httpClient;
    }

    public static String getClientSource() {
        return "android_" + Constants.localVersionName;
    }

    public static String getUserAgent() {
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            sharePreferencesValue = "auto";
        }
        return "qiushibalke_" + Constants.localVersionName + "_" + HttpUtils.getNetworkType(QsbkApp.mContext) + "_" + sharePreferencesValue + "_" + ConfigManager.getInstance().getChannel();
    }

    public static void testURLDomainReplace() {
        try {
            LogUtil.d("testURLDomainReplace:");
            LogUtil.d(replaceURLDomainToIp(new URL("http://www.qq.com/123"), "192.168.1.1"));
            LogUtil.d(replaceURLDomainToIp(new URL("http://www.qq.com:80/123"), "192.168.1.1"));
            LogUtil.d(replaceURLDomainToIp(new URL("http://www.qq.com:80/123?abc=1"), "192.168.1.1"));
            LogUtil.d(replaceURLDomainToIp(new URL("http://www.qq.com:80/123?abc=1&d=1"), "192.168.1.1"));
            LogUtil.d(replaceURLDomainToIp(new URL("http://www.qq.com:80/123?abc=1&d=1#haha"), "192.168.1.1"));
        } catch (Exception e) {
        }
    }

    public static String replaceURLDomainToIp(URL url, String str) {
        String str2 = url.getProtocol() + "://" + str;
        if (url.getPort() != -1) {
            str2 = str2 + Config.TRACE_TODAY_VISIT_SPLIT + url.getPort();
        }
        str2 = str2 + url.getFile();
        if (TextUtils.isEmpty(url.getRef())) {
            return str2;
        }
        return str2 + MqttTopic.MULTI_LEVEL_WILDCARD + url.getRef();
    }

    public static String getHttpStatusEvent(int i) {
        if (i / 100 == 2) {
            return "suc";
        }
        if (i == -1) {
            return "exp";
        }
        if (i / 100 == 4 || i / 100 == 5) {
            return NotificationCompat.CATEGORY_ERROR;
        }
        if (i / 100 == 3) {
            return "redir";
        }
        return "unknow";
    }

    public static String getNormalUrl(String str) {
        String str2 = null;
        try {
            URL url = new URL(str);
            String host = url.getHost();
            if (!(host.matches("[\\d.]+") || "localhost".equalsIgnoreCase(host))) {
                str2 = host + url.getPath().replaceAll("/\\d+", "/_id_");
            }
        } catch (Exception e) {
        }
        return str2;
    }

    public static String readStream(InputStream inputStream) throws Exception {
        String str = "";
        if (inputStream != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                str = stringBuffer.toString();
                if (str.startsWith("<html>")) {
                    str = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String encodeParameters(Map<String, Object> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject.toString();
    }

    public static boolean testNeedLogin(JSONObject jSONObject, Activity activity, int i, Bundle bundle) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("Args JSONObject can not be null");
        }
        boolean equalsIgnoreCase = "107".equalsIgnoreCase(jSONObject.optString(NotificationCompat.CATEGORY_ERROR));
        if (equalsIgnoreCase && activity != null) {
            ActivityOpener.login(activity, i, bundle);
        }
        return equalsIgnoreCase;
    }

    private static String addTail(String str) {
        int i;
        String androidId = DeviceUtils.getAndroidId();
        if (androidId == null || androidId.length() <= 32) {
            i = 0;
        } else {
            i = 1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = "&r=%s%s";
        Object[] objArr = new Object[2];
        objArr[0] = i != 0 ? androidId.substring(androidId.length() - 8) : Long.valueOf(new Random(currentTimeMillis).nextLong());
        objArr[1] = Long.valueOf(currentTimeMillis);
        return str.concat(String.format(str2, objArr));
    }

    public long getLastSuccessTime() {
        return this.lastSuccessTime;
    }

    public String getLastSuccessUrl() {
        return this.lastSuccessUrl;
    }

    private HttpURLConnection addBasicParams(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("Source", getClientSource());
        httpURLConnection.setRequestProperty("Model", Build.FINGERPRINT);
        if (QsbkApp.currentUser != null) {
            httpURLConnection.setRequestProperty("Qbtoken", QsbkApp.currentUser.token);
        }
        httpURLConnection.setRequestProperty("Uuid", DeviceUtils.getAndroidId());
        httpURLConnection.setRequestProperty("Deviceidinfo", DeviceUtils.getDeviceIdInfo());
        return httpURLConnection;
    }

    private HttpURLConnection addOtherParams(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty(QiubaiEmotionProvider.NAMESPACE, UUID.randomUUID().toString().trim().replaceAll(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER, ""));
        return httpURLConnection;
    }

    private boolean httpsOnly(String str) {
        if (str == null || (!str.startsWith(Constants.LAISEE_DOMAINS) && !str.startsWith(Constants.PAY_DOMAINS))) {
            return false;
        }
        return true;
    }

    protected HttpURLConnection getConnection(String str, Pair<String, DomainRecord> pair) throws IOException {
        URL url;
        if (QsbkApp.isHttpsEnable()) {
            str = str.replace("http://", "https://");
        } else if (!httpsOnly(str)) {
            str = str.replace("https://", "http://");
        }
        URL url2 = new URL(str);
        if (!(str.indexOf("https://") == -1 || sSSLExceptionHostUrl.get(url2.getHost()) == null || httpsOnly(str))) {
            str = str.replace("https://", "http://");
            url2 = new URL(str);
        }
        Object obj = pair != null ? ((DomainRecord) pair.second).ip : null;
        if (TextUtils.isEmpty(obj)) {
            url = url2;
        } else {
            url = new URL(replaceURLDomainToIp(url2, obj));
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (pair != null) {
            httpURLConnection.setRequestProperty("Host", ((DomainRecord) pair.second).domain);
        }
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        if (!(str.indexOf("vote_queue") == -1 && str.indexOf("signup") == -1 && str.indexOf("v2/signin") == -1 && str.indexOf("review") == -1 && str.indexOf("/report") == -1)) {
            httpURLConnection = addOtherParams(httpURLConnection);
        }
        httpURLConnection.setRequestProperty("User-Agent", getUserAgent());
        return addBasicParams(httpURLConnection);
    }

    private String getReport() {
        String str;
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            sharePreferencesValue = "auto";
        }
        if (TextUtils.isEmpty(SharePreferenceUtils.getSharePreferencesValue("location"))) {
            str = "null";
        } else {
            str = SharePreferenceUtils.getSharePreferencesValue("location");
        }
        StringBuffer append = new StringBuffer(str).append("_");
        append.append(sharePreferencesValue);
        return append.toString();
    }

    public String get(String str) throws QiushibaikeException {
        return httpRequest(str, null, "GET");
    }

    public String post(String str, Map<String, Object> map) throws QiushibaikeException {
        StringBuffer stringBuffer = new StringBuffer("");
        if (map != null) {
            try {
                stringBuffer.append(encodeParameters(map));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return httpRequest(str, stringBuffer.toString(), "POST");
    }

    public String post(String str) throws QiushibaikeException {
        return httpRequest(str, null, "POST");
    }

    public String post(String str, String str2) throws QiushibaikeException {
        return httpRequest(str, str2, "POST");
    }

    private String httpRequest(String str, String str2, String str3) throws QiushibaikeException {
        return httpRequest(str, str2, str3, null);
    }

    public Pair<String, DomainRecord> getNewUrlAndDomain(String str) {
        if (httpsOnly(str)) {
            return null;
        }
        try {
            URL url = new URL(str);
            String host = url.getHost();
            if (TextUtils.isEmpty(host) || host.matches("[\\d.]+") || "localhost".equalsIgnoreCase(host)) {
                return null;
            }
            String httpDnsIp = HttpDNSManager.instance().getHttpDnsIp(host);
            if (TextUtils.isEmpty(httpDnsIp)) {
                return null;
            }
            replaceURLDomainToIp(url, httpDnsIp);
            return new Pair(str, new DomainRecord(host, httpDnsIp));
        } catch (Exception e) {
            return null;
        }
    }

    private void httpStatus(String str, int i, int i2, boolean z) {
        String network = HttpUtils.getNetwork(QsbkApp.mContext);
        if (!network.equalsIgnoreCase("unconnect")) {
            String normalUrl = getNormalUrl(str);
            if (normalUrl != null && QsbkApp.isInConfigRatio("http_status_ratio", 0)) {
                String str2 = (z ? "hs_ip_" : "hs_dns_") + network + "_" + getHttpStatusEvent(i);
                LogUtil.d("[HTTPStatus] url:  " + normalUrl + " event:" + str2 + " milSecond:" + i2);
                StatService.onEventDuration(AppContext.getContext(), str2, normalUrl, (long) (i2 * 1000));
                StatSDK.onEventDuration(AppContext.getContext(), str2, normalUrl, (long) i2);
            }
        }
    }

    public String httpRequest(String str, String str2, String str3, Map<String, String> map) throws QiushibaikeException {
        return CustomHttpClient.getInstance().httpRequest(str, str2, str3, map);
    }

    public String submitForm(String str, Map<String, Object> map) throws QiushibaikeException {
        return CustomHttpClient.getInstance().submitForm(str, map);
    }

    public String messgePorst(String str) throws QiushibaikeException {
        String str2;
        if (str.indexOf("rqcnt=") == -1) {
            Object[] objArr = new Object[2];
            objArr[0] = Character.valueOf(str.indexOf("?") > -1 ? Typography.amp : '?');
            int i = requestCount;
            requestCount = i + 1;
            objArr[1] = Integer.valueOf(i);
            str = str.concat(String.format("%crqcnt=%d", objArr));
        }
        String addTail = addTail(str);
        String str3 = "";
        if (addTail.indexOf("vote_queue") != -1) {
            i = 1;
        } else {
            i = 2;
        }
        HttpURLConnection httpURLConnection = null;
        String str4 = str3;
        int i2 = 0;
        while (i2 < i) {
            try {
                httpURLConnection = getConnection(addTail, null);
                try {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write("".toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
                    this.responseCode = httpURLConnection.getResponseCode();
                    if (this.responseCode != 200) {
                        throw new QiushibaikeException("服务器路径不存在", this.responseCode);
                    }
                    InputStream inputStream;
                    InputStream inputStream2 = httpURLConnection.getInputStream();
                    if (inputStream2 == null || !"gzip".equals(httpURLConnection.getContentEncoding())) {
                        inputStream = inputStream2;
                    } else {
                        inputStream = new GZIPInputStream(inputStream2);
                    }
                    inputStream.available();
                    str4 = readStream(inputStream);
                    inputStream.close();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return str4;
                } catch (JSONException e) {
                    JSONException jSONException = e;
                    str2 = str4;
                    jSONException.printStackTrace();
                    if (i2 != i) {
                        continue;
                    } else {
                        throw new QiushibaikeException(this.responseCode + "", this.responseCode);
                    }
                } catch (Exception e2) {
                    Exception exception = e2;
                    str2 = str4;
                    exception.printStackTrace();
                    if (i2 == i) {
                        throw new QiushibaikeException(this.responseCode + "", this.responseCode);
                    }
                } catch (Exception e3) {
                }
            } catch (Exception e4) {
                str2 = str4;
                if (i2 == i && i2 == i) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw new QiushibaikeException("服务器离家出走了", this.responseCode);
                }
                i2++;
                str4 = str2;
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        return str4;
    }

    public String submit(String str, Map<String, Object> map, String str2) throws QiushibaikeException {
        return CustomHttpClient.getInstance().submit(str, map, str2);
    }

    public String submitWithFile(String str, Map<String, Object> map) throws QiushibaikeException {
        return CustomHttpClient.getInstance().submitWithFile(str, map);
    }

    public String delete(String str) {
        int i = 0;
        String str2 = "";
        while (i < 1) {
            try {
                HttpURLConnection connection = getConnection(str, null);
                connection.setRequestMethod("DELETE");
                this.responseCode = connection.getResponseCode();
                if (this.responseCode == 200) {
                    InputStream inputStream = connection.getInputStream();
                    str2 = readStream(inputStream);
                    inputStream.close();
                    break;
                }
                throw new QiushibaikeException("服务器路径不存在", this.responseCode);
            } catch (Exception e) {
                Exception exception = e;
                String str3 = str2;
                exception.printStackTrace();
                i++;
                str2 = str3;
            }
        }
        return str2;
    }
}
