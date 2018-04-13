package qsbk.app.core.net;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.facebook.common.util.UriUtil;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.stat.DeviceInfo;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.text.Typography;
import org.json.JSONObject;
import qsbk.app.core.R;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.Md5Utils;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.core.utils.ToastUtil;

public class NetRequest {
    public static String LIVE_SALT = "go!live!";
    private static String a = "heyyeah!";
    private static final String b = NetRequest.class.getSimpleName();
    private static NetRequest c;
    private static long e = 0;
    private Context d;

    public NetRequest(Context context) {
        this.d = context;
    }

    public static synchronized NetRequest getInstance() {
        NetRequest netRequest;
        synchronized (NetRequest.class) {
            Context appContext = AppUtils.getInstance().getAppContext();
            if (c == null || c.d != appContext) {
                c = new NetRequest(appContext);
            }
            netRequest = c;
        }
        return netRequest;
    }

    public static void setLiveSalt(String str) {
        LIVE_SALT = str;
    }

    public static void setSalt(String str) {
        a = str;
    }

    public void get(String str, NetworkCallback networkCallback) {
        get(str, networkCallback, null);
    }

    public void get(String str, NetworkCallback networkCallback, String str2) {
        executeNetworkInvoke(str, 0, networkCallback, str2, false);
    }

    public void get(String str, NetworkCallback networkCallback, String str2, boolean z) {
        executeNetworkInvoke(str, 0, networkCallback, str2, z);
    }

    public void post(String str, NetworkCallback networkCallback) {
        post(str, networkCallback, null);
    }

    public void post(String str, NetworkCallback networkCallback, String str2) {
        executeNetworkInvoke(str, 1, networkCallback, str2, false);
    }

    public void post(String str, NetworkCallback networkCallback, String str2, boolean z) {
        executeNetworkInvoke(str, 1, networkCallback, str2, z);
    }

    public void executeNetworkInvoke(String str, int i, NetworkCallback networkCallback, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || networkCallback == null) {
            throw new IllegalArgumentException("request url or callback can not be null");
        } else if (NetworkUtils.getInstance().isNetworkAvailable()) {
            Map hashMap;
            JSONObject a;
            Map params = networkCallback.getParams();
            if (params == null) {
                params = new HashMap();
            }
            if (i == 1) {
                hashMap = new HashMap();
                a = a(params);
            } else {
                a = null;
                HashMap hashMap2 = new HashMap();
                hashMap = params;
                Object obj = hashMap2;
            }
            String str3 = a;
            if (str.startsWith(UrlConstants.API_DOMAIN)) {
                str = str.replace(UrlConstants.API_DOMAIN, UrlConstants.getApiDomain());
                if (!hashMap.containsKey("source")) {
                    hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
                    hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
                }
                str3 = a;
            } else {
                if (str.startsWith(UrlConstants.LIVE_DOMAIN)) {
                    str = str.replace(UrlConstants.LIVE_DOMAIN, UrlConstants.getLiveDomain());
                    hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
                    hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
                    str3 = LIVE_SALT;
                } else {
                    if (str.startsWith(UrlConstants.LIVE_DOMAIN_HTTPS)) {
                        str = str.replace(UrlConstants.LIVE_DOMAIN_HTTPS, UrlConstants.getLiveHttpsDomain());
                        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
                        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
                        str3 = LIVE_SALT;
                    } else {
                        if (str.startsWith(UrlConstants.PAY_DOMAIN)) {
                            str = str.replace(UrlConstants.PAY_DOMAIN, UrlConstants.getPayDomain());
                            hashMap.put("source", Constants.SOURCE + "");
                            hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getPlatformId() + "");
                            str3 = LIVE_SALT;
                        } else {
                            if (str.startsWith("https://pay.werewolf.mobi")) {
                                str3 = LIVE_SALT;
                            } else {
                                if (str.startsWith(UrlConstants.DOLL_DOMAIN)) {
                                    str = str.replace(UrlConstants.DOLL_DOMAIN, UrlConstants.getDollApiDomain());
                                    if (!hashMap.containsKey("source")) {
                                        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
                                        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
                                    }
                                    str3 = a;
                                } else {
                                    if (str.startsWith(UrlConstants.DOLL_LIVE_DOMAIN)) {
                                        str = str.replace(UrlConstants.DOLL_LIVE_DOMAIN, UrlConstants.getDollLiveDomain());
                                        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
                                        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
                                        str3 = LIVE_SALT;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (str.contains("/live/stream")) {
                hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
                hashMap.put("user_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
            }
            str3 = convertParams(str, hashMap, params, str3);
            String domain = getDomain(str3);
            if (i == 0) {
                LogUtils.d(b, "get request url: " + str3);
            } else {
                LogUtils.d(b, "post request url: " + str3 + "\nrequest body:" + a.toString());
            }
            if (!TextUtils.isEmpty(str2)) {
                cancelRequest(str2);
            }
            onPreExecute(networkCallback);
            Request cVar = new c(this, i, str3, a, new a(this, domain, networkCallback, z), new b(this, str3, a, domain, networkCallback, z), networkCallback);
            cVar.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
            AppUtils.getInstance().addToRequestQueue(cVar, str2);
        } else {
            onFailed(networkCallback, 0, AppUtils.getInstance().getAppContext().getString(R.string.network_not_well), z);
            onFinished(networkCallback);
        }
    }

    public static String getDomain(String str) {
        String host;
        try {
            host = new URI(str).getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            host = null;
        }
        if (TextUtils.isEmpty(host) || (!host.matches("[\\d.]+") && !"localhost".equalsIgnoreCase(host))) {
            return host;
        }
        return null;
    }

    private JSONObject a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (Entry entry : map.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static String convertParams(String str, Map<String, String> map, int i, String str2) {
        Map hashMap;
        if (map == null) {
            hashMap = new HashMap();
        }
        Map hashMap2 = new HashMap();
        if (i == 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.putAll(hashMap);
            hashMap = hashMap3;
        }
        return convertParams(str, hashMap, hashMap2, str2);
    }

    public static String convertParams(String str, Map<String, String> map, Map<String, String> map2, String str2) {
        addDefaultParams(map, map2, str2);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (str.contains("?")) {
            stringBuffer.append(a.b);
        } else {
            stringBuffer.append("?");
        }
        for (Object valueOf : map.keySet().toArray()) {
            String valueOf2 = String.valueOf(valueOf);
            String str3 = (String) map.get(valueOf2);
            if (!TextUtils.isEmpty(str3)) {
                try {
                    str3 = URLEncoder.encode(str3, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(valueOf2).append("=").append(str3).append(a.b);
            }
        }
        if (stringBuffer.charAt(stringBuffer.length() - 1) == Typography.amp) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static void addDefaultParams(Map<String, String> map, Map<String, String> map2, String str) {
        if (map != null) {
            long currentTimeMillis = System.currentTimeMillis();
            CharSequence token = AppUtils.getInstance().getUserInfoProvider().getToken();
            if (!TextUtils.isEmpty(token)) {
                map.put("token", token);
            }
            String country = AppUtils.getInstance().getAppContext().getResources().getConfiguration().locale.getCountry();
            int i = 2;
            if (country.equalsIgnoreCase("CN")) {
                i = 0;
            } else if (country.equalsIgnoreCase("TW") || country.equalsIgnoreCase("HK")) {
                i = 1;
            }
            map.put("sdk", "7.7.7");
            map.put("app", Integer.toString(Constants.APP_FLAG));
            map.put("lan", Integer.toString(i));
            map.put(DeviceInfo.TAG_VERSION, DeviceUtils.getAppVersion());
            map.put(NotificationCompat.CATEGORY_SYSTEM, "android_" + DeviceUtils.getSystemVersion());
            map.put("chn", AppUtils.getInstance().getChannel());
            map.put("net", NetworkUtils.getInstance().getNetworkType() + "");
            map.put("did", DeviceUtils.getDeviceId());
            map.put("mod", DeviceUtils.getDeviceModel().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
            map.put("ts", Long.toString(currentTimeMillis + e));
            map.put("sig", genSign(map, map2, str).toLowerCase(Locale.CHINESE));
        }
    }

    public static String genSign(Map<String, String> map, Map<String, String> map2, String str) {
        int i = 0;
        if (map == null) {
            return null;
        }
        Object obj;
        StringBuffer stringBuffer = new StringBuffer();
        if (!(map2 == null || map2.isEmpty())) {
            Object[] toArray = map2.keySet().toArray();
            if (toArray != null) {
                Arrays.sort(toArray);
            }
            for (Object obj2 : toArray) {
                String valueOf = String.valueOf(obj2);
                if (!(valueOf.equals(UriUtil.LOCAL_FILE_SCHEME) || valueOf.equals("sig"))) {
                    stringBuffer.append(valueOf).append("").append((String) map2.get(obj2));
                }
            }
        }
        Object[] toArray2 = map.keySet().toArray();
        if (toArray2 != null) {
            Arrays.sort(toArray2);
        }
        int length = toArray2.length;
        while (i < length) {
            obj2 = toArray2[i];
            String valueOf2 = String.valueOf(obj2);
            if (!(valueOf2.equals(UriUtil.LOCAL_FILE_SCHEME) || valueOf2.equals("sig"))) {
                stringBuffer.append(valueOf2).append("").append((String) map.get(obj2));
            }
            i++;
        }
        String stringBuffer2 = stringBuffer.toString();
        LogUtils.d(b, "sign data: " + stringBuffer2);
        stringBuffer2 = Md5Utils.encryptMD5(stringBuffer2, str);
        LogUtils.d(b, "sign result: " + stringBuffer2);
        return stringBuffer2;
    }

    private void c() {
        this.d.sendBroadcast(new Intent(Constants.FORCE_LOGOUT));
    }

    public void onPreExecute(NetworkCallback networkCallback) {
        if (networkCallback != null) {
            networkCallback.onPreExecute();
        }
    }

    public void onSuccess(NetworkCallback networkCallback, JSONObject jSONObject) {
        if (networkCallback != null) {
            networkCallback.onSuccess(jSONObject);
        }
    }

    public void onFinished(NetworkCallback networkCallback) {
        if (networkCallback != null) {
            networkCallback.onFinished();
        }
    }

    public void onFailed(NetworkCallback networkCallback, int i, String str, boolean z) {
        if (!(TextUtils.isEmpty(str) || z)) {
            a(str);
        }
        if (networkCallback != null) {
            networkCallback.onFailed(i, str);
        }
    }

    protected void a(String str) {
        ToastUtil.Short(str);
    }

    public void cancelRequest(String str) {
        AppUtils.getInstance().cancelPendingRequests(str);
    }
}
