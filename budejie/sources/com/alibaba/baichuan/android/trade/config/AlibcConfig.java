package com.alibaba.baichuan.android.trade.config;

import android.content.Context;
import android.os.Handler;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.config.a.b;
import com.alibaba.baichuan.android.trade.config.a.c;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.utils.StringUtils;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.umeng.analytics.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public class AlibcConfig {
    public static final int DEFAULT_APPMONITOR_SAMPLING = 10000;
    public static final int DOUBLE_11_OPEN_TYPE_AUTO = 0;
    public static final int DOUBLE_11_OPEN_TYPE_H5 = 2;
    public static final int DOUBLE_11_OPEN_TYPE_NATIVE = 1;
    private static String b = "albbTradeConfig";
    public static String channel;
    private static volatile AlibcConfig f;
    Runnable a = new a(this);
    private b c = new b();
    private b d;
    private Context e = b();
    public long expiredTimeStamp = (System.currentTimeMillis() + a.j);
    private c g = new c();
    private final long h = 21600000;
    private final long i = a.j;
    public String isvVersion = com.umeng.onlineconfig.a.b;
    private Handler j = new Handler();
    public String taobaoNativeSource;
    public AlibcTaokeParams taokeParams;

    AlibcConfig() {
        a();
        this.d = new b(this.e, new AlibcConfig$a(this));
        startRepeatingLoadConfigTask();
    }

    private void a() {
        AlibcLogger.d("AlibcConfig", "config设置默认值开始");
        if (this.g.a() != null) {
            AlibcLogger.d("AlibcConfig", "configspwrapper有值");
            this.c.a(this.g.a());
            return;
        }
        AlibcLogger.d("AlibcConfig", "读取本地配置文件");
        try {
            String str = "{\n  \"group0\": {\n    \"dataId\": \"com.alibaba.baichuan.nbcp.meta.default\",\n    \"group\": \"1.0.0\",\n    \"lastUpdate\": \"Jun 14, 2016 2:12:22 PM\",\n    \"sign\": \"\"\n  },\n  \"albbTradeConfig\": {\n    \"isSyncForTaoke\": \"YES\",\n    \"isForceH5\": \"NO\",\n    \"isNeedAlipay\": \"YES\",\n    \"loginDegarade\": \"NO\"\n  },\n  \"group2\": {\n    \"abc1\": \"agc1\",\n    \"abc2\": \"agc2\",\n    \"abc3\": \"agc3\",\n    \"11111\":\"11111\"\n  }\n}";
            AlibcLogger.d("AlibcConfig", "本地配置文件的值为" + str);
            JSONObject jSONObject = new JSONObject(str);
            com.alibaba.baichuan.android.trade.config.a.a aVar = new com.alibaba.baichuan.android.trade.config.a.a();
            aVar.a(jSONObject);
            this.c.a(aVar);
            AlibcLogger.d("AlibcConfig", "读取本地配置文件成功");
        } catch (JSONException e) {
            AlibcLogger.e("AlibcConfig", "本地默认配置文件解析错误" + e.getMessage());
        }
    }

    private boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z'))) {
                return false;
            }
        }
        return true;
    }

    private Context b() {
        return AlibcContext.context;
    }

    public static AlibcConfig getInstance() {
        if (f == null) {
            synchronized (AlibcConfig.class) {
                if (f == null) {
                    f = new AlibcConfig();
                }
            }
        }
        return f;
    }

    public int getAppMonitorSampling() {
        Object globalConfig = getGlobalConfig("sampling");
        if (!(globalConfig instanceof String)) {
            return 10000;
        }
        try {
            return Integer.parseInt((String) globalConfig);
        } catch (NumberFormatException e) {
            AlibcLogger.e("AlibcConfig", e.toString());
            return 10000;
        }
    }

    public String getChannel() {
        String str = (String) getGlobalConfig("channelName");
        if (str == null || str.length() <= 0) {
            if (!a(channel)) {
                AlibcLogger.e("initChannel", "Channel chars must in [0-9][a-z][A-Z], now : " + channel);
                channel = "0";
            }
            return channel;
        }
        String str2 = (String) getGlobalConfig("channelType");
        return (str2 == null || str2.length() <= 0) ? "0" + str : str2 + str;
    }

    public int getDouble11OpenType() {
        Object globalConfig = getGlobalConfig("double11OpenType");
        if (!(globalConfig instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) globalConfig);
        } catch (NumberFormatException e) {
            AlibcLogger.e("AlibcConfig", e.toString());
            return 0;
        }
    }

    public String getFromAssets(String str) {
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        Throwable th;
        try {
            inputStreamReader = new InputStreamReader(this.e.getResources().getAssets().open(str));
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str2 = "";
                str2 = "";
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str2 = str2 + readLine;
                }
                if (inputStreamReader == null) {
                    return str2;
                }
                try {
                    inputStreamReader.close();
                    return str2;
                } catch (IOException e) {
                    e.printStackTrace();
                    return str2;
                }
            } catch (Exception e2) {
                inputStreamReader2 = inputStreamReader;
                try {
                    AlibcLogger.e("AlibcConfig", "本地默认配置文件读取错误");
                    if (inputStreamReader2 != null) {
                        try {
                            inputStreamReader2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    inputStreamReader = inputStreamReader2;
                    th = th2;
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            inputStreamReader2 = null;
            AlibcLogger.e("AlibcConfig", "本地默认配置文件读取错误");
            if (inputStreamReader2 != null) {
                inputStreamReader2.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
    }

    public Object getGlobalConfig(String str) {
        return str == null ? null : this.c.b(b, str, null);
    }

    public Object getGlobalConfig(String str, Object obj) {
        return str == null ? null : this.c.b(b, str, obj);
    }

    public Object getGroupConfig(String str, String str2) {
        return (str == null || str2 == null) ? null : this.c.b(str, str2, null);
    }

    public Object getGroupConfig(String str, String str2, Object obj) {
        return (str == null || str2 == null) ? null : this.c.b(str, str2, obj);
    }

    public boolean getIsSyncForTaoke() {
        return "YES".equals(getGlobalConfig("isSyncForTaoke"));
    }

    public String getIsvCode() {
        return AlibcContext.isvCode != null ? AlibcContext.isvCode : getGlobalConfig("isvCode") != null ? (String) getGlobalConfig("isvCode") : null;
    }

    public String getIsvVersion() {
        return getGlobalConfig("isvVersion") != null ? (String) getGlobalConfig("isvVersion") : this.isvVersion;
    }

    public boolean getLoginDegarade(boolean z) {
        return StringUtils.obj2Boolean(getGlobalConfig("loginDegarade", Boolean.valueOf(z)));
    }

    public AlibcTaokeParams getNBTTradeTaokeParams() {
        return this.taokeParams;
    }

    public AlibcTaokeParams getTaokeParams() {
        return this.taokeParams;
    }

    public String getWebTTID() {
        return String.format("2014_%s_%s@baichuan_android_%s", new Object[]{getChannel(), AlibcContext.getAppKey(), AlibcContext.sdkVersion});
    }

    public boolean isForceH5() {
        return "YES".equals(getGlobalConfig("isForceH5"));
    }

    public boolean needTaoke() {
        return "YES".equals(getGlobalConfig("isTaokeUT"));
    }

    public void setChangeSlickUrl(boolean z) {
        setGlobalConfig("IS_TAOKE_SCLICK", Boolean.valueOf(z));
    }

    public void setChannel(String str, String str2) {
        setGlobalConfig("channelType", str);
        setGlobalConfig("channelName", str2);
    }

    public boolean setGlobalConfig(String str, Object obj) {
        return setGroupConfig(b, str, obj);
    }

    public boolean setGroupConfig(String str, String str2, Object obj) {
        if (str == null || str2 == null || obj == null) {
            return false;
        }
        this.c.a(str, str2, obj);
        return true;
    }

    public boolean setIsForceH5(boolean z) {
        this.c.a("isForceH5");
        return setGlobalConfig("isForceH5", z ? "YES" : "NO");
    }

    public boolean setIsSyncForTaoke(boolean z) {
        this.c.a("isSyncForTaoke");
        return setGlobalConfig("isSyncForTaoke", z ? "YES" : "NO");
    }

    public boolean setIsvCode(String str) {
        this.c.a("isvCode");
        return setGlobalConfig("isvCode", str);
    }

    public boolean setIsvVersion(String str) {
        this.c.a("isvVersion");
        return setGlobalConfig("isvVersion", str);
    }

    public boolean setShouldUseAlipay(boolean z) {
        this.c.a("isNeedAlipay");
        return setGlobalConfig("isNeedAlipay", z ? "YES" : "NO");
    }

    public void setTaokeParams(AlibcTaokeParams alibcTaokeParams) {
        if (alibcTaokeParams != null) {
            setGlobalConfig("pid", alibcTaokeParams.pid);
            setGlobalConfig("subPid", alibcTaokeParams.subPid);
            setGlobalConfig("unionId", alibcTaokeParams.unionId);
            this.taokeParams = alibcTaokeParams;
        }
    }

    public boolean shouldUseAlipay(boolean z) {
        return StringUtils.obj2Boolean(getGlobalConfig("isNeedAlipay", Boolean.valueOf(z)));
    }

    public void startRepeatingLoadConfigTask() {
        this.a.run();
    }
}
