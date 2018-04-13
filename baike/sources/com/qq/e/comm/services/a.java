package com.qq.e.comm.services;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobstat.Config;
import com.qq.e.comm.constants.Constants.KEYS;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.managers.plugin.PM;
import com.qq.e.comm.managers.setting.SM;
import com.qq.e.comm.managers.status.APPStatus;
import com.qq.e.comm.managers.status.DeviceStatus;
import com.qq.e.comm.net.NetworkCallBack;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.NetworkClientImpl;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.net.rr.S2SSRequest;
import com.qq.e.comm.services.RetCodeService.RetCodeInfo;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static final a a = new a();
    private volatile Boolean b = Boolean.valueOf(false);

    public static a a() {
        return a;
    }

    private static String a(Context context) {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private static String a(SM sm, PM pm, DeviceStatus deviceStatus, APPStatus aPPStatus, Context context, long j) {
        JSONObject a;
        Throwable e;
        JSONObject jSONObject = new JSONObject();
        try {
            a = com.qq.e.comm.a.a(sm);
            try {
                String str = "sig";
                JSONObject jSONObject2 = new JSONObject();
                if (sm != null) {
                    jSONObject2.putOpt("app", sm.getDevCloudSettingSig());
                    jSONObject2.putOpt("sdk", sm.getSdkCloudSettingSig());
                }
                if (pm != null) {
                    jSONObject2.putOpt("jar", pm.getLocalSig());
                    jSONObject2.putOpt(KEYS.PLUGIN_VERSION, Integer.valueOf(pm.getPluginVersion()));
                }
                a.put(str, jSONObject2);
                str = "dev";
                jSONObject2 = new JSONObject();
                if (deviceStatus != null) {
                    jSONObject2.putOpt("did", deviceStatus.getPlainDid());
                    jSONObject2.putOpt(IXAdRequestInfo.TEST_MODE, deviceStatus.model);
                    jSONObject2.putOpt("lg", deviceStatus.getLanguage());
                    jSONObject2.putOpt("w", Integer.valueOf(deviceStatus.getDeviceWidth()));
                    jSONObject2.putOpt("h", Integer.valueOf(deviceStatus.getDeviceHeight()));
                    jSONObject2.putOpt(Config.DEVICE_ID_SEC, Integer.valueOf(deviceStatus.getDeviceDensity()));
                    jSONObject2.putOpt("apil", Integer.valueOf(deviceStatus.getVersion()));
                    jSONObject2.putOpt("os", "android");
                    jSONObject2.putOpt(Config.OPERATOR, deviceStatus.getOperator());
                    jSONObject2.putOpt("mf", Build.MANUFACTURER);
                }
                a.put(str, jSONObject2);
                a.put("app", com.qq.e.comm.a.a(aPPStatus));
                jSONObject = com.qq.e.comm.a.a(deviceStatus);
                jSONObject.putOpt("process", a(context));
                a.put("c", jSONObject);
                a.put("sdk", com.qq.e.comm.a.a(pm));
                jSONObject = new JSONObject();
                jSONObject2 = new JSONObject();
                jSONObject2.put("sdk_init_time", (System.nanoTime() - j) / 1000000);
                jSONObject.put("performance", jSONObject2);
                a.put("biz", jSONObject);
            } catch (JSONException e2) {
                e = e2;
                GDTLogger.e("JSONException while build init req", e);
                return a.toString();
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            a = jSONObject;
            e = th;
            GDTLogger.e("JSONException while build init req", e);
            return a.toString();
        }
        return a.toString();
    }

    public static void a(String str, Throwable th) {
        if (GDTADManager.getInstance() == null || !GDTADManager.getInstance().isInitialized()) {
            GDTLogger.w("Report Not Work while  ADManager  not Inited");
            return;
        }
        try {
            JSONObject a = com.qq.e.comm.a.a(GDTADManager.getInstance().getSM());
            a.put("c", com.qq.e.comm.a.a(GDTADManager.getInstance().getDeviceStatus()));
            a.put("app", com.qq.e.comm.a.a(GDTADManager.getInstance().getAppStatus()));
            Map hashMap = new HashMap();
            if (th != null) {
                hashMap.put("extype", th.getClass().getName());
                hashMap.put("ext", str + "\r" + th.getMessage() + "\r" + Arrays.toString(th.getStackTrace()));
            } else {
                hashMap.put("extype", "");
                hashMap.put(Config.EXCEPTION_PART, str);
            }
            a.put("biz", new JSONObject(hashMap));
            NetworkClientImpl.getInstance().submit(new S2SSRequest("http://sdk.e.qq.com/err", a.toString().getBytes()));
        } catch (Throwable th2) {
            GDTLogger.w("Exception While build s2ss error report req", th2);
        }
    }

    public final void a(Context context, SM sm, PM pm, DeviceStatus deviceStatus, APPStatus aPPStatus, long j) {
        if (!this.b.booleanValue()) {
            synchronized (this.b) {
                if (this.b.booleanValue()) {
                    return;
                }
                String a = a(sm, pm, deviceStatus, aPPStatus, context, j);
                String str = !StringUtil.isEmpty(sm.getSuid()) ? "http://sdk.e.qq.com/launch" : "http://sdk.e.qq.com/activate";
                final long currentTimeMillis = System.currentTimeMillis();
                final SM sm2 = sm;
                final PM pm2 = pm;
                NetworkClientImpl.getInstance().submit(new S2SSRequest(str, a.getBytes()), Priority.High, new NetworkCallBack(this) {
                    public final void onException(Exception exception) {
                        GDTLogger.e("ActivateError", exception);
                        RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", "", -1, (int) (System.currentTimeMillis() - currentTimeMillis), 0, 0, 1));
                    }

                    public final void onResponse(Request request, Response response) {
                        try {
                            if (response.getStatusCode() == 200) {
                                String stringContent = response.getStringContent();
                                GDTLogger.d("ACTIVERESPONSE:" + stringContent);
                                if (StringUtil.isEmpty(stringContent)) {
                                    GDTLogger.report("SDK Server response empty string,maybe zip or tea format error");
                                    RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", "", response.getStatusCode(), (int) (System.currentTimeMillis() - currentTimeMillis), 0, 0, 1));
                                    return;
                                }
                                JSONObject jSONObject = new JSONObject(stringContent);
                                int i = -1;
                                if (jSONObject.has(KEYS.RET)) {
                                    i = jSONObject.getInt(KEYS.RET);
                                }
                                if (i != 0) {
                                    GDTLogger.e("Response Error,retCode=" + i);
                                } else {
                                    if (jSONObject.has("suid")) {
                                        stringContent = jSONObject.getString("suid");
                                        if (!StringUtil.isEmpty(stringContent)) {
                                            sm2.updateSUID(stringContent);
                                        }
                                    }
                                    if (jSONObject.has("sid")) {
                                        stringContent = jSONObject.getString("sid");
                                        if (!StringUtil.isEmpty(stringContent)) {
                                            sm2.updateSID(stringContent);
                                        }
                                    }
                                    if (jSONObject.has("sig")) {
                                        JSONObject jSONObject2 = jSONObject.getJSONObject("sig");
                                        if (jSONObject.has(com.alipay.sdk.sys.a.j)) {
                                            String string;
                                            jSONObject = jSONObject.getJSONObject(com.alipay.sdk.sys.a.j);
                                            if (jSONObject.has("app") && jSONObject2.has("app")) {
                                                string = jSONObject.getString("app");
                                                sm2.updateDEVCloudSetting(jSONObject2.getString("app"), string);
                                            }
                                            if (jSONObject.has("sdk") && jSONObject2.has("sdk")) {
                                                string = jSONObject.getString("sdk");
                                                sm2.updateSDKCloudSetting(jSONObject2.getString("sdk"), string);
                                            }
                                            if (jSONObject.has("c")) {
                                                sm2.updateContextSetting(jSONObject.getString("c"));
                                            } else {
                                                sm2.updateContextSetting(null);
                                            }
                                        }
                                        if (jSONObject2.has("jar") && jSONObject2.has("url")) {
                                            pm2.update(jSONObject2.getString("jar"), jSONObject2.getString("url"));
                                        }
                                    }
                                }
                            } else {
                                GDTLogger.e("SDK server response code error while launch or activate,code:" + response.getStatusCode());
                            }
                            RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", "", response.getStatusCode(), (int) (System.currentTimeMillis() - currentTimeMillis), 0, 0, 1));
                        } catch (Throwable e) {
                            GDTLogger.e("ActivateError", e);
                            RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", "", response.getStatusCode(), (int) (System.currentTimeMillis() - currentTimeMillis), 0, 0, 1));
                        } catch (Throwable e2) {
                            GDTLogger.e("Parse Active or launch response exception", e2);
                            RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", "", response.getStatusCode(), (int) (System.currentTimeMillis() - currentTimeMillis), 0, 0, 1));
                        } catch (Throwable e22) {
                            Throwable th = e22;
                            RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", "", response.getStatusCode(), (int) (System.currentTimeMillis() - currentTimeMillis), 0, 0, 1));
                        }
                    }
                });
                this.b = Boolean.valueOf(true);
            }
        }
    }
}
