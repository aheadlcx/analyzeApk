package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.Setting.LOG_LEVEL;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.Version;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.umeng.analytics.b.g;

public class cg {
    private static String a = "xiaoyan";
    private static String b = null;

    public static int a(be beVar) {
        return (beVar == null || !beVar.m()) ? 4000 : 5000;
    }

    public static String a() {
        return SpeechUtility.getUtility().getParameter("appid");
    }

    public static String a(Context context) {
        if (context == null) {
            return "null";
        }
        ce a = bp.a(context);
        String str = a.e("os.imsi") + "|" + a.e("os.imei");
        if (str.length() < 10) {
            str = a.e("net.mac");
        }
        return (TextUtils.isEmpty(str) || str.length() <= 0) ? null : str;
    }

    public static String a(Context context, be beVar) throws SpeechError {
        if (context == null) {
            throw new SpeechError(20012);
        }
        ce b = beVar.x().b();
        b = b.b(SpeechConstant.NET_TYPE, b);
        a(context, b);
        b.a(SpeechConstant.NET_TIMEOUT, Strategy.DEVICE_ERROR_CODE, false);
        b.a("auth", "1", false);
        b.a("msc.ver", Version.getVersion());
        b.a("mac", bp.a(context).e("net.mac"), false);
        b.a("dvc", a(context), false);
        b.a("unique_id", ca.a(context));
        b.a("msc.lat", "" + br.a(context).a("msc.lat"), false);
        b.a("msc.lng", "" + br.a(context).a("msc.lng"), false);
        b.a(bp.b(context));
        a(b);
        b(context, b);
        b.a(cf.c);
        return b.toString();
    }

    public static String a(Context context, String str, be beVar) {
        ce b = beVar.x().b();
        b.c(SpeechConstant.CLOUD_GRAMMAR);
        a(context, b);
        b(context, b);
        b.a("language", "zh_cn", false);
        b.a("accent", "mandarin", false);
        b.a(SpeechConstant.RESULT_TYPE, "json", false);
        b.a("rse", beVar.s(), false);
        b.a(SpeechConstant.TEXT_ENCODING, beVar.r(), false);
        b.a("ssm", "1", false);
        b.a("msc.skin", "0", false);
        if (TextUtils.isEmpty(str)) {
            b.a(SpeechConstant.SUBJECT, "iat", false);
        } else {
            b.a(SpeechConstant.SUBJECT, "asr", false);
        }
        int t = beVar.t();
        b.a("auf=audio/L16;rate", Integer.toString(t), false);
        if (t == 16000) {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex-wb", false);
        } else {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex", false);
        }
        b.a("vad_bos", Integer.toString(a(beVar)), false);
        b.a("vad_eos", Integer.toString(b(beVar)), false);
        if (b.a(SpeechConstant.DVC_INFO, false)) {
            a(context, b);
            b.a("dvc", a(context), false);
            b.a("unique_id", ca.a(context));
            b.a("msc.lat", "" + br.a(context).a("msc.lat"), false);
            b.a("msc.lng", "" + br.a(context).a("msc.lng"), false);
            String a = bp.a(bp.e(context));
            String f = bp.f(context);
            b.a("user_agent", a, false);
            b.a(g.T, f, false);
            b.a(bp.b(context));
        }
        b.a(cf.c);
        return b.toString();
    }

    public static void a(Context context, ce ceVar) {
        if (TextUtils.isEmpty(ceVar.e(SpeechConstant.NET_TYPE)) && !TextUtils.isEmpty(b)) {
            ceVar.a(SpeechConstant.NET_TYPE, b, false);
        } else if (context == null) {
            ceVar.a(SpeechConstant.NET_TYPE, "none", false);
        } else {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    ceVar.a(SpeechConstant.NET_TYPE, "none", false);
                    return;
                }
                ceVar.a(SpeechConstant.NET_TYPE, bz.a(activeNetworkInfo), false);
                ceVar.a("net_subtype", ce.f(bz.b(activeNetworkInfo)), false);
            } catch (SecurityException e) {
                cb.b("appendNetProxyParam exceptoin: " + e.getLocalizedMessage());
            } catch (Throwable th) {
                cb.a(th);
            }
        }
    }

    private static void a(ce ceVar) {
        if (ceVar != null && Setting.getLogLevel() != LOG_LEVEL.none) {
            String logPath = Setting.getLogPath();
            if (TextUtils.isEmpty(logPath)) {
                logPath = "/sdcard/msc/msc.log";
            }
            int i = -1;
            if (Setting.getLogLevel() == LOG_LEVEL.detail) {
                i = 31;
            } else if (Setting.getLogLevel() == LOG_LEVEL.normal) {
                i = 15;
            } else if (Setting.getLogLevel() == LOG_LEVEL.low) {
                i = 7;
            }
            bv.b(logPath);
            ceVar.a("log", logPath);
            ceVar.a("lvl", "" + i);
            ceVar.a("output", "1", false);
        }
    }

    public static boolean a(String str) {
        return str != null && (str.contains("sms") || str.contains("iat"));
    }

    public static int b(be beVar) {
        return (beVar == null || !beVar.m()) ? 700 : 1800;
    }

    public static String b(Context context, be beVar) {
        ce b = beVar.x().b();
        a(context, b);
        b(context, b);
        b.a(SpeechConstant.RESULT_TYPE, "json");
        b.a("rse", beVar.s());
        b.a(SpeechConstant.TEXT_ENCODING, beVar.r());
        b.a("ssm", "1", false);
        b.a(SpeechConstant.SUBJECT, SpeechConstant.ENG_IVP, false);
        int t = beVar.t();
        b.a("auf=audio/L16;rate", Integer.toString(t), false);
        if (t == 16000) {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex-wb;10", false);
        } else {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex", false);
        }
        b.a("vad_bos", "3000", false);
        b.a("vad_eos", "700", false);
        b.a(cf.c);
        return b.toString();
    }

    public static String b(Context context, String str, be beVar) {
        ce b = beVar.x().b();
        a(context, b);
        b(context, b);
        b.a("sub", "mfv", false);
        b.a("prot_ver", "50", false);
        b.a("mver", "2.0", false);
        if ("verify".equals(b.e("sst"))) {
            b.a("scene_mode", "vfy", false);
        } else {
            b.a("scene_mode", "gen", false);
        }
        b.a(cf.c);
        return b.toString();
    }

    public static void b(Context context, ce ceVar) {
        int i = 0;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (context != null && br.b(context)) {
            try {
                int lac;
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                int phoneType = telephonyManager.getPhoneType();
                String networkOperator = telephonyManager.getNetworkOperator();
                int parseInt = Integer.parseInt(networkOperator.substring(0, 3));
                int parseInt2 = Integer.parseInt(networkOperator.substring(3));
                switch (phoneType) {
                    case 1:
                        GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                        i = gsmCellLocation.getCid();
                        lac = gsmCellLocation.getLac();
                        break;
                    case 2:
                        CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                        i = cdmaCellLocation.getBaseStationId();
                        lac = cdmaCellLocation.getNetworkId();
                        break;
                    default:
                        lac = 0;
                        break;
                }
                ceVar.a("mmlc", parseInt + "|" + parseInt2 + "|" + lac + "|" + i);
                cb.d("MCC = " + parseInt + "\t MNC = " + parseInt2 + "\t phoneType = " + phoneType + "\t LAC = " + lac + "\t CID = " + i);
            } catch (Exception e) {
                cb.d("get mmlc failed");
            }
            cb.d("get mmlc time cost:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private static void b(ce ceVar) {
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null) {
            Object parameter = utility.getParameter("ver_tts");
            if (!ceVar.g("speed_increase")) {
                int a = ceVar.a("speed", 50);
                if (a <= 100) {
                    ceVar.a("speed", "" + a);
                    ceVar.a("speed_increase", "1");
                } else if (100 < a && a <= 150 && (TextUtils.isEmpty(parameter) || parameter.contains("5.5."))) {
                    ceVar.a("speed", "" + (a - 50));
                    ceVar.a("speed_increase", "2");
                } else if (100 < a && a <= 200) {
                    ceVar.a("speed", "" + (a - 100));
                    ceVar.a("speed_increase", "4");
                }
            }
        }
    }

    public static String c(Context context, be beVar) {
        ce b = beVar.x().b();
        a(context, b);
        b(context, b);
        b.a("ssm", "1", false);
        b.a(SpeechConstant.RESULT_TYPE, "json", false);
        b.a("rse", beVar.s(), false);
        b.a(SpeechConstant.TEXT_ENCODING, beVar.r(), false);
        b.a(cf.c);
        return b.toString();
    }

    public static String c(Context context, ce ceVar) {
        ce b = ceVar.b();
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null) {
            b.a("appid", utility.getParameter("appid"));
        }
        b.a(bp.b(context));
        b.a("dvc", a(context), false);
        b.a(SpeechConstant.AUDIO_FORMAT_AUE, ShareConstants.DEXMODE_RAW, false);
        b.a(cf.c);
        return b.toString();
    }

    public static String d(Context context, be beVar) {
        ce b = beVar.x().b();
        a(context, b);
        b(context, b);
        b.a("ssm", "1", false);
        int t = beVar.t();
        b.a("auf=audio/L16;rate", Integer.toString(t));
        if (t == 16000) {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex-wb", false);
        } else {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex", false);
        }
        b.a(SpeechConstant.VOICE_NAME, b.b(SpeechConstant.VOICE_NAME, a), true);
        b.a(SpeechConstant.TEXT_ENCODING, beVar.r(), false);
        b(b);
        b.a(cf.c);
        return b.toString();
    }

    public static String e(Context context, be beVar) {
        ce b = beVar.x().b();
        a(context, b);
        b(context, b);
        b.a("ssm", "1", false);
        int t = beVar.t();
        b.a("auf=audio/L16;rate", Integer.toString(t), false);
        if (t == 16000) {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex-wb", false);
        } else {
            b.a(SpeechConstant.AUDIO_FORMAT_AUE, "speex", false);
        }
        b.a(SpeechConstant.TEXT_ENCODING, beVar.r(), false);
        b.a("plev", "1", false);
        b.a("accent", "mandarin", false);
        b.a(SpeechConstant.DOMAIN, "ise", false);
        b.a(SpeechConstant.SUBJECT, "ise", false);
        b.a(SpeechConstant.RESULT_TYPE, "xml", false);
        b.a("vad_bos", "5000", false);
        b.a("vad_eos", "1800", false);
        b.a("vad_speech_timeout", "2147483647", false);
        b.a(cf.c);
        return b.toString();
    }
}
