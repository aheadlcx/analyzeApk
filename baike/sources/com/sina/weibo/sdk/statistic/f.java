package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.sina.weibo.sdk.net.ConnectionFactory;
import com.sina.weibo.sdk.net.NetStateManager;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class f {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static JSONObject g;
    private static String h = "uploadtime";
    private static String i = "https://api.weibo.com/2/proxy/sdk/statistic.json";
    public static f mLogReport;

    public f(Context context) {
        try {
            if (c == null) {
                c = context.getPackageName();
            }
            b = i.getAppkey(context);
            a(context);
            d = Utility.getSign(context, c);
            e = c.getVersion(context);
            f = i.getChannel(context);
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, e.toString());
        }
        a();
    }

    private static JSONObject a() {
        if (g == null) {
            g = new JSONObject();
        }
        try {
            g.put("appkey", b);
            g.put("platform", "Android");
            g.put("packagename", c);
            g.put("key_hash", d);
            g.put("version", e);
            g.put("channel", f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return g;
    }

    private static void a(Context context) {
        if (TextUtils.isEmpty(a)) {
            a = Utility.getAid(context, b);
        }
        if (g == null) {
            g = new JSONObject();
        }
        try {
            g.put("aid", a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setPackageName(String str) {
        c = str;
    }

    public static String getPackageName() {
        return c;
    }

    public static synchronized void uploadAppLogs(Context context, String str) {
        synchronized (f.class) {
            if (mLogReport == null) {
                mLogReport = new f(context);
            }
            if (NetStateManager.isNetworkConnected(context)) {
                List<JSONArray> validUploadLogs = c.getValidUploadLogs(str);
                if (validUploadLogs == null) {
                    LogUtil.i(WBAgent.TAG, "applogs is null");
                } else {
                    List<JSONArray> arrayList = new ArrayList();
                    a(context);
                    for (JSONArray jSONArray : validUploadLogs) {
                        if (a(i, "POST", g, jSONArray, context)) {
                            a(context, Long.valueOf(System.currentTimeMillis()));
                        } else {
                            arrayList.add(jSONArray);
                            LogUtil.e(WBAgent.TAG, "upload applogs error");
                        }
                    }
                    e.delete(e.getAppLogPath(e.ANALYTICS_FILE_NAME));
                    if (arrayList.size() > 0) {
                        for (JSONArray jSONArray2 : arrayList) {
                            e.writeToFile(e.getAppLogPath(e.ANALYTICS_FILE_NAME), jSONArray2.toString(), true);
                            LogUtil.d(WBAgent.TAG, "save failed_log");
                        }
                    }
                }
            } else {
                LogUtil.i(WBAgent.TAG, "network is not connected");
                e.writeToFile(e.getAppLogPath(e.ANALYTICS_FILE_NAME), str, true);
            }
        }
    }

    private static boolean a(String str, String str2, JSONObject jSONObject, JSONArray jSONArray, Context context) {
        UnsupportedEncodingException e;
        Throwable th;
        IOException e2;
        boolean z = false;
        if (TextUtils.isEmpty(b)) {
            LogUtil.e(WBAgent.TAG, "unexpected null AppKey");
        } else {
            ByteArrayOutputStream byteArrayOutputStream = null;
            if (jSONObject == null) {
                try {
                    jSONObject = a();
                } catch (UnsupportedEncodingException e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e2 = e6;
                    e2.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e7) {
                        }
                    }
                    return z;
                }
            }
            try {
                jSONObject.put("time", System.currentTimeMillis() / 1000);
                jSONObject.put("length", jSONArray.length());
                jSONObject.put(Config.SIGN, a(jSONObject.getString("aid"), jSONObject.getString("appkey"), jSONObject.getLong("time")));
                jSONObject.put("content", jSONArray);
                LogUtil.d(WBAgent.TAG, "post content--- " + jSONObject.toString());
            } catch (JSONException e8) {
                e8.printStackTrace();
            }
            HttpURLConnection createConnect = ConnectionFactory.createConnect(str + "?" + "source=" + b, context);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                if (i.isNeedGizp()) {
                    byteArrayOutputStream2.write(a(jSONObject.toString()));
                } else {
                    byteArrayOutputStream2.write(jSONObject.toString().getBytes());
                }
                a(createConnect);
                createConnect.connect();
                DataOutputStream dataOutputStream = new DataOutputStream(createConnect.getOutputStream());
                dataOutputStream.write(a(jSONObject.toString()));
                dataOutputStream.flush();
                dataOutputStream.close();
                int responseCode = createConnect.getResponseCode();
                if (responseCode == 200) {
                    z = true;
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (IOException e9) {
                        }
                    }
                } else {
                    LogUtil.i(WBAgent.TAG, "status code = " + responseCode);
                    if (byteArrayOutputStream2 != null) {
                        try {
                            byteArrayOutputStream2.close();
                        } catch (IOException e10) {
                        }
                    }
                }
            } catch (UnsupportedEncodingException e11) {
                e = e11;
                byteArrayOutputStream = byteArrayOutputStream2;
                e.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return z;
            } catch (IOException e12) {
                e2 = e12;
                byteArrayOutputStream = byteArrayOutputStream2;
                e2.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = byteArrayOutputStream2;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return z;
    }

    private static void a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Connection", HTTP.CONN_KEEP_ALIVE);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
        } catch (Exception e) {
        }
    }

    private static String a(String str, String str2, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append(str2).append("dqwef1864il4c9m6").append(j);
        String hexdigest = MD5.hexdigest(stringBuilder.toString());
        hexdigest = hexdigest.substring(hexdigest.length() - 6);
        String hexdigest2 = MD5.hexdigest(hexdigest + hexdigest.substring(0, 4));
        return hexdigest + hexdigest2.substring(hexdigest2.length() - 1);
    }

    private static byte[] a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bytes = str.getBytes("utf-8");
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static long getTime(Context context) {
        return context.getSharedPreferences(h, 0).getLong("lasttime", 0);
    }

    private static void a(Context context, Long l) {
        Editor edit = context.getSharedPreferences(h, 0).edit();
        edit.putLong("lasttime", l.longValue());
        edit.commit();
    }
}
