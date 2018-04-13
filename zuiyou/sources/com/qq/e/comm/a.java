package com.qq.e.comm;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.qq.e.comm.constants.Constants.PLUGIN;
import com.qq.e.comm.constants.CustomPkgConstants;
import com.qq.e.comm.constants.ErrorCode$AdError;
import com.qq.e.comm.constants.ErrorCode$NetWorkError;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import com.qq.e.comm.managers.plugin.PM;
import com.qq.e.comm.managers.setting.SM;
import com.qq.e.comm.managers.status.APPStatus;
import com.qq.e.comm.managers.status.DeviceStatus;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.umeng.analytics.b.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private String a;
    private com.qq.e.comm.managers.setting.a b;

    public a(String str, com.qq.e.comm.managers.setting.a aVar) {
        this.a = str;
        this.b = aVar;
    }

    public static AdError a(int i) {
        switch (i) {
            case 300:
            case 2001:
                return new AdError(2001, "初始化错误");
            case 301:
            case 200101:
                return new AdError(2001, "初始化错误，详细码：200101");
            case 302:
            case 200102:
                return new AdError(2001, "初始化错误，详细码：200102");
            case 303:
            case 200103:
                return new AdError(2001, "初始化错误，详细码：200103");
            case 400:
            case 403:
            case 3001:
                return new AdError(3001, "网络异常");
            case 404:
            case 4011:
                return new AdError(4011, "开屏广告拉取超时");
            case ErrorCode$NetWorkError.RESOURCE_LOAD_FAIL_ERROR /*405*/:
            case 5007:
                return new AdError(5007, "资源加载错误");
            case ErrorCode$NetWorkError.IMG_LOAD_ERROR /*406*/:
            case 5008:
                return new AdError(5008, "图片加载错误");
            case 500:
            case 4003:
                return new AdError(4003, "广告位错误");
            case 501:
            case 5004:
                return new AdError(5004, "没有广告");
            case ErrorCode$AdError.JSON_PARSE_ERROR /*502*/:
            case 5001:
                return new AdError(5001, "服务端数据错误");
            case 600:
            case 4004:
                return new AdError(4004, "开屏广告容器不可见");
            case ErrorCode$OtherError.NETWORK_TYPE_ERROR /*601*/:
            case 3003:
                return new AdError(3003, "网络类型错误，当前设备的网络类型不符合开屏广告的加载条件");
            case ErrorCode$OtherError.ANDROID_PERMMISON_ERROR /*602*/:
            case 4002:
                return new AdError(4002, "Manifest文件中Activity/Service/Permission的声明有问题");
            case ErrorCode$OtherError.GET_PARAS_FROM_JS_ERROR /*603*/:
            case 200201:
                return new AdError(2002, "内部错误，详细码：200201");
            case ErrorCode$OtherError.GET_PARAS_FROM_NATIVE_ERROR /*604*/:
            case 200202:
                return new AdError(2002, "内部错误，详细码：200202");
            case ErrorCode$OtherError.CONTAINER_HEIGHT_ERROR /*606*/:
            case 4005:
                return new AdError(4005, "开屏广告容器的高度低于400dp");
            case ErrorCode$OtherError.NATIVE_FORCE_EXPOSURE /*607*/:
            case 4006:
                return new AdError(4006, "原生广告接口调用顺序错误，调用点击接口前未调用曝光接口");
            case ErrorCode$OtherError.SKIP_VIEW_SIZE_ERROR /*608*/:
            case 4009:
                return new AdError(4009, "开屏广告的自定义跳过按钮尺寸小于3x3dp");
            case 700:
            case 5002:
                return new AdError(5002, "视频素材下载错误");
            case 701:
            case 5003:
                return new AdError(5003, "视频素材播放错误");
            case 800:
                return new AdError(4012, "内容接口调用顺序错误，调用点击接口前未调用曝光接口");
            case 2002:
                return new AdError(2002, "内部错误");
            case 4001:
                return new AdError(4001, "传入的参数有错误");
            case 4008:
                return new AdError(4008, "设备方向不适合展示广告");
            case 5005:
            case 109506:
                return new AdError(5005, "广告请求量或者消耗等超过日限额，请明天再请求广告");
            case 5006:
            case 107030:
                return new AdError(5006, "包名校验错误，当前App的包名和广点通移动联盟官网注册的媒体包名不一致，因此无广告返回");
            case 5009:
            case 109507:
                return new AdError(5009, "广告请求量或者消耗等超过小时限额，请一小时后再请求广告");
            case 107034:
                return new AdError(5010, "广告样式校验失败，请检查广告位与接口使用是否一致");
            case 107035:
                return new AdError(4013, "使用支持视频素材的原生模板广告位前，请升级您的SDK");
            case 400101:
                return new AdError(4001, "传入的参数有错误，详细码：" + i);
            case 400102:
                return new AdError(4001, "传入的参数有错误，详细码：" + i);
            case 400103:
                return new AdError(4001, "传入的参数有错误，详细码：" + i);
            case 400104:
                return new AdError(4001, "传入的参数有错误，详细码：" + i);
            default:
                return new AdError(6000, "未知错误，详细码：" + i);
        }
    }

    public static JSONObject a(PM pm) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("sdkv", SDKStatus.getSDKVersion());
        jSONObject.putOpt(Parameters.PUSH_SDK_VERSION, Integer.valueOf(pm.getPluginVersion()));
        return jSONObject;
    }

    public static JSONObject a(SM sm) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (sm != null) {
            jSONObject.putOpt("suid", sm.getSuid());
            jSONObject.putOpt(SpeechConstant.IST_SESSION_ID, sm.getSid());
        }
        return jSONObject;
    }

    public static JSONObject a(APPStatus aPPStatus) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (aPPStatus != null) {
            jSONObject.putOpt("an", aPPStatus.getAPPName());
            jSONObject.putOpt(g.a, aPPStatus.getAPPID());
            jSONObject.putOpt("appv", aPPStatus.getAPPVersion());
            jSONObject.putOpt("appn", aPPStatus.getAPPRealName());
        }
        return jSONObject;
    }

    public static JSONObject a(DeviceStatus deviceStatus) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (deviceStatus != null) {
            jSONObject.putOpt("so", deviceStatus.getScreenOrientation());
            jSONObject.putOpt("dn", deviceStatus.getDataNet());
            jSONObject.putOpt(g.ae, deviceStatus.getLat());
            jSONObject.putOpt(g.af, deviceStatus.getLng());
            for (Entry entry : deviceStatus.getLacAndCeilId().entrySet()) {
                jSONObject.putOpt((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    public static boolean a(Context context) {
        try {
            if (b(context)) {
                if (a(context, Class.forName(CustomPkgConstants.getADActivityName()))) {
                    if (b(context, Class.forName(CustomPkgConstants.getDownLoadServiceName()))) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            GDTLogger.e("Exception While check SDK Env", th);
            return false;
        }
    }

    public static boolean a(Context context, File file, File file2) {
        AssetManager assets = context.getAssets();
        try {
            if (Arrays.binarySearch(assets.list(CustomPkgConstants.getAssetPluginDir()), CustomPkgConstants.getAssetPluginName()) < 0) {
                return false;
            }
            String str = CustomPkgConstants.getAssetPluginDir() + File.separator + CustomPkgConstants.getAssetPluginName();
            StringUtil.writeTo("559#####" + PLUGIN.ASSET_PLUGIN_SIG, file2);
            if (StringUtil.isEmpty(CustomPkgConstants.getAssetPluginXorKey())) {
                return FileUtil.copyTo(assets.open(str), file);
            }
            InputStream open = assets.open(str);
            OutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = CustomPkgConstants.getAssetPluginXorKey().getBytes();
            byte[] bArr = new byte[1024];
            int length = bytes.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    int i3 = 0;
                    while (i3 < read) {
                        int i4 = i + 1;
                        if (i >= 64) {
                            i = i2 + 1;
                            bArr[i3] = (byte) (bytes[i2 % length] ^ bArr[i3]);
                        } else {
                            i = i2;
                        }
                        i3++;
                        i2 = i;
                        i = i4;
                    }
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Throwable th) {
            GDTLogger.report("Exception while init default plugin manager", th);
            return false;
        }
    }

    private static boolean a(Context context, Class<?>... clsArr) {
        int i = 0;
        while (i <= 0) {
            try {
                Intent intent = new Intent();
                intent.setClass(context, clsArr[0]);
                if (context.getPackageManager().resolveActivity(intent, 65536) == null) {
                    GDTLogger.e(String.format("Activity[%s] is required in AndroidManifest.xml", new Object[]{clsArr[0].getName()}));
                    return false;
                }
                i++;
            } catch (Throwable th) {
                GDTLogger.e("Exception while checking required activities", th);
                return false;
            }
        }
        return true;
    }

    public static byte[] a(byte[] bArr) {
        Exception e;
        Throwable th;
        byte[] bArr2 = null;
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream;
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.finish();
                bArr2 = byteArrayOutputStream.toByteArray();
                try {
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                    return bArr2;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return bArr2;
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                            return bArr2;
                        }
                    }
                    byteArrayOutputStream.close();
                    return bArr2;
                } catch (Throwable th2) {
                    th = th2;
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e222) {
                            e222.printStackTrace();
                            throw th;
                        }
                    }
                    byteArrayOutputStream.close();
                    throw th;
                }
            }
        } catch (Exception e4) {
            e222 = e4;
            gZIPOutputStream = bArr2;
            e222.printStackTrace();
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Throwable th3) {
            gZIPOutputStream = bArr2;
            th = th3;
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            byteArrayOutputStream.close();
            throw th;
        }
    }

    private static boolean b(Context context) {
        String[] strArr = new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        int i = 0;
        while (i < 5) {
            try {
                if (context.checkCallingOrSelfPermission(strArr[i]) == -1) {
                    GDTLogger.e(String.format("Permission %s is required in AndroidManifest.xml", new Object[]{strArr[i]}));
                    return false;
                }
                i++;
            } catch (Throwable th) {
                GDTLogger.e("Check required Permissions error", th);
                return false;
            }
        }
        return true;
    }

    private static boolean b(Context context, Class<?>... clsArr) {
        int i = 0;
        while (i <= 0) {
            try {
                Class cls = clsArr[0];
                Intent intent = new Intent();
                intent.setClass(context, cls);
                if (context.getPackageManager().resolveService(intent, 65536) == null) {
                    GDTLogger.e(String.format("Service[%s] is required in AndroidManifest.xml", new Object[]{cls.getName()}));
                    return false;
                }
                i++;
            } catch (Throwable th) {
                GDTLogger.e("Exception while checking required services", th);
                return false;
            }
        }
        return true;
    }

    public static byte[] b(byte[] bArr) {
        GZIPInputStream gZIPInputStream;
        Exception e;
        Throwable th;
        byte[] bArr2 = null;
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr3 = new byte[1024];
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            while (true) {
                try {
                    int read = gZIPInputStream.read(bArr3);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr3, 0, read);
                    } else {
                        byteArrayOutputStream.flush();
                        bArr2 = byteArrayOutputStream.toByteArray();
                        try {
                            gZIPInputStream.close();
                            byteArrayInputStream.close();
                            byteArrayOutputStream.close();
                            return bArr2;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return bArr2;
                        }
                    }
                } catch (Exception e3) {
                    e2 = e3;
                }
            }
        } catch (Exception e4) {
            e2 = e4;
            gZIPInputStream = bArr2;
            try {
                e2.printStackTrace();
                if (gZIPInputStream != null) {
                    try {
                        gZIPInputStream.close();
                    } catch (Exception e22) {
                        e22.printStackTrace();
                        return bArr2;
                    }
                }
                byteArrayInputStream.close();
                byteArrayOutputStream.close();
                return bArr2;
            } catch (Throwable th2) {
                th = th2;
                if (gZIPInputStream != null) {
                    try {
                        gZIPInputStream.close();
                    } catch (Exception e222) {
                        e222.printStackTrace();
                        throw th;
                    }
                }
                byteArrayInputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (Throwable th3) {
            gZIPInputStream = bArr2;
            th = th3;
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public String a() {
        return this.a;
    }

    public com.qq.e.comm.managers.setting.a b() {
        return this.b;
    }
}
