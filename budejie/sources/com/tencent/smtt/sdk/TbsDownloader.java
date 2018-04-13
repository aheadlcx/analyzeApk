package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.n;
import com.tencent.smtt.utils.x;
import java.io.File;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";
    static boolean a;
    private static String b;
    private static Context c;
    private static Handler d;
    private static String e;
    private static Object f = new byte[0];
    private static ac g;
    private static HandlerThread h;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;

    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i);
    }

    protected static File a(int i) {
        File file = null;
        for (String str : TbsShareManager.getCoreProviderAppList()) {
            if (!str.equals(c.getApplicationInfo().packageName)) {
                file = new File(k.a(c, str, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                if (file == null || !file.exists()) {
                    TbsLog.i(LOGTAG, "can not find local backup core file");
                } else if (a.a(c, file) == i) {
                    TbsLog.i(LOGTAG, "local tbs version fond,path = " + file.getAbsolutePath());
                    break;
                } else {
                    TbsLog.i(LOGTAG, "version is not match");
                }
            }
        }
        return file;
    }

    static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String str;
        Locale locale = Locale.getDefault();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = VERSION.RELEASE;
        try {
            str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e) {
            str = str2;
        }
        if (str.length() > 0) {
            stringBuffer.append(str);
        } else {
            stringBuffer.append("1.0");
        }
        stringBuffer.append("; ");
        str = locale.getLanguage();
        if (str != null) {
            stringBuffer.append(str.toLowerCase());
            str = locale.getCountry();
            if (str != null) {
                stringBuffer.append("-");
                stringBuffer.append(str.toLowerCase());
            }
        } else {
            stringBuffer.append("en");
        }
        if ("REL".equals(VERSION.CODENAME)) {
            str2 = Build.MODEL;
            try {
                str = new String(str2.getBytes("UTF-8"), "ISO8859-1");
            } catch (Exception e2) {
                str = str2;
            }
            if (str.length() > 0) {
                stringBuffer.append("; ");
                stringBuffer.append(str);
            }
        }
        str = Build.ID.replaceAll("[一-龥]", "");
        if (str.length() > 0) {
            stringBuffer.append(" Build/");
            stringBuffer.append(str);
        }
        str = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", new Object[]{stringBuffer});
        b = str;
        return str;
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static void a(boolean z, TbsDownloaderCallback tbsDownloaderCallback) {
        int i = 0;
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        d.removeMessages(100);
        Message obtain = Message.obtain(d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        if (z) {
            i = 1;
        }
        obtain.arg1 = i;
        obtain.sendToTarget();
    }

    private static boolean a(Context context, boolean z, TbsDownloaderCallback tbsDownloaderCallback) {
        Matcher matcher = null;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (VERSION.SDK_INT < 8) {
            instance.setDownloadInterruptCode(-102);
            return false;
        } else if (QbSdk.c || !TbsShareManager.isThirdPartyApp(c) || c()) {
            if (!instance.mPreferences.contains(TbsConfigKey.KEY_IS_OVERSEA)) {
                if (z && !"com.tencent.mm".equals(context.getApplicationInfo().packageName)) {
                    TbsLog.i(LOGTAG, "needDownload-oversea is true, but not WX");
                    z = false;
                }
                instance.a.put(TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
                instance.commit();
                j = z;
                TbsLog.i(LOGTAG, "needDownload-first-called--isoversea = " + z);
            }
            if (!getOverSea(context) || VERSION.SDK_INT == 16 || VERSION.SDK_INT == 17 || VERSION.SDK_INT == 18) {
                e = instance.mPreferences.getString(TbsConfigKey.KEY_DEVICE_CPUABI, matcher);
                if (!TextUtils.isEmpty(e)) {
                    try {
                        matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
                    } catch (Exception e) {
                    }
                    if (matcher != null && matcher.find()) {
                        if (tbsDownloaderCallback != null) {
                            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                        }
                        instance.setDownloadInterruptCode(-104);
                        return false;
                    }
                }
                return true;
            }
            TbsLog.i(LOGTAG, "needDownload- return false,  because of  version is " + VERSION.SDK_INT + ", and overea");
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            instance.setDownloadInterruptCode(-103);
            return false;
        } else if (tbsDownloaderCallback == null) {
            return false;
        } else {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            return false;
        }
    }

    private static boolean a(Context context, boolean z, boolean z2) {
        boolean z3;
        String str;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (z) {
            z3 = true;
            str = null;
        } else {
            String string = instance.mPreferences.getString(TbsConfigKey.KEY_APP_VERSIONNAME, null);
            int i = instance.mPreferences.getInt("app_versioncode", 0);
            String string2 = instance.mPreferences.getString(TbsConfigKey.KEY_APP_METADATA, null);
            String a = b.a(c);
            int b = b.b(c);
            String a2 = b.a(c, "com.tencent.mm.BuildInfo.CLIENT_VERSION");
            TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] appVersionName=" + a + " oldAppVersionName=" + string + " appVersionCode=" + b + " oldAppVersionCode=" + i + " appMetadata=" + a2 + " oldAppVersionMetadata=" + string2);
            long currentTimeMillis = System.currentTimeMillis();
            long j = instance.mPreferences.getLong(TbsConfigKey.KEY_LAST_CHECK, 0);
            TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] timeLastCheck=" + j + " timeNow=" + currentTimeMillis);
            if (z2) {
                boolean contains = instance.mPreferences.contains(TbsConfigKey.KEY_LAST_CHECK);
                TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] hasLaskCheckKey=" + contains);
                if (contains && j == 0) {
                    j = currentTimeMillis;
                }
            }
            long retryInterval = instance.getRetryInterval();
            TbsLog.i(LOGTAG, "retryInterval = " + retryInterval + " s");
            if (currentTimeMillis - j > retryInterval * 1000) {
                z3 = true;
                str = null;
            } else if (TbsShareManager.isThirdPartyApp(c) && TbsShareManager.findCoreForThirdPartyApp(c) == 0 && !e()) {
                k.b(c.getDir("tbs", 0));
                aj.a.set(Integer.valueOf(0));
                z3 = true;
                str = null;
            } else {
                if (a == null || b == 0 || a2 == null) {
                    if (TbsShareManager.isThirdPartyApp(c)) {
                        str = "timeNow - timeLastCheck is " + (currentTimeMillis - j) + " TbsShareManager.findCoreForThirdPartyApp(sAppContext) is " + TbsShareManager.findCoreForThirdPartyApp(c) + " sendRequestWithSameHostCoreVersion() is " + e() + " appVersionName is " + a + " appVersionCode is " + b + " appMetadata is " + a2 + " oldAppVersionName is " + string + " oldAppVersionCode is " + i + " oldAppVersionMetadata is " + string2;
                        z3 = false;
                    }
                } else if (!(a.equals(string) && b == i && a2.equals(string2))) {
                    z3 = true;
                    str = null;
                }
                str = null;
                z3 = false;
            }
        }
        if (!z3 && TbsShareManager.isThirdPartyApp(c)) {
            TbsLogReport.a(c).h(-119);
            TbsLogReport.a(c).e(str);
            TbsLogReport.a(c).a(EventType.TYPE_DOWNLOAD);
        }
        return z3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(11)
    private static boolean a(java.lang.String r28, int r29, boolean r30, boolean r31) {
        /*
        r2 = "TbsDownload";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "[TbsDownloader.readResponse] response=";
        r3 = r3.append(r4);
        r0 = r28;
        r3 = r3.append(r0);
        r3 = r3.toString();
        com.tencent.smtt.utils.TbsLog.i(r2, r3);
        r2 = c;
        r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2);
        r2 = android.text.TextUtils.isEmpty(r28);
        if (r2 == 0) goto L_0x0035;
    L_0x0026:
        if (r30 == 0) goto L_0x002f;
    L_0x0028:
        r2 = -108; // 0xffffffffffffff94 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x002d:
        r2 = 0;
    L_0x002e:
        return r2;
    L_0x002f:
        r2 = -208; // 0xffffffffffffff30 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x002d;
    L_0x0035:
        r11 = new org.json.JSONObject;
        r0 = r28;
        r11.<init>(r0);
        r2 = "RET";
        r2 = r11.getInt(r2);
        if (r2 == 0) goto L_0x0053;
    L_0x0044:
        if (r30 == 0) goto L_0x004d;
    L_0x0046:
        r2 = -109; // 0xffffffffffffff93 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x004b:
        r2 = 0;
        goto L_0x002e;
    L_0x004d:
        r2 = -209; // 0xffffffffffffff2f float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x004b;
    L_0x0053:
        r2 = "RESPONSECODE";
        r12 = r11.getInt(r2);
        r2 = "DOWNLOADURL";
        r13 = r11.getString(r2);
        r2 = "URLLIST";
        r3 = "";
        r14 = r11.optString(r2, r3);
        r2 = "TBSAPKSERVERVERSION";
        r15 = r11.getInt(r2);
        r2 = "DOWNLOADMAXFLOW";
        r16 = r11.getInt(r2);
        r2 = "DOWNLOAD_MIN_FREE_SPACE";
        r17 = r11.getInt(r2);
        r2 = "DOWNLOAD_SUCCESS_MAX_RETRYTIMES";
        r18 = r11.getInt(r2);
        r2 = "DOWNLOAD_FAILED_MAX_RETRYTIMES";
        r19 = r11.getInt(r2);
        r2 = "DOWNLOAD_SINGLE_TIMEOUT";
        r20 = r11.getLong(r2);
        r2 = "TBSAPKFILESIZE";
        r22 = r11.getLong(r2);
        r2 = "RETRY_INTERVAL";
        r4 = 0;
        r8 = r11.optLong(r2, r4);
        r2 = "FLOWCTR";
        r3 = -1;
        r24 = r11.optInt(r2, r3);
        r2 = 0;
        r3 = "USEBBACKUPVER";
        r2 = r11.getInt(r3);	 Catch:{ Exception -> 0x043c }
    L_0x00a7:
        r3 = r10.a;
        r4 = "use_backup_version";
        r2 = java.lang.Integer.valueOf(r2);
        r3.put(r4, r2);
        r7 = 0;
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r3 = "";
        r2 = "PKGMD5";
        r7 = r11.getString(r2);	 Catch:{ Exception -> 0x0149 }
        r2 = "RESETX5";
        r6 = r11.getInt(r2);	 Catch:{ Exception -> 0x0435 }
        r2 = "UPLOADLOG";
        r5 = r11.getInt(r2);	 Catch:{ Exception -> 0x0435 }
        r2 = "RESETTOKEN";
        r2 = r11.has(r2);	 Catch:{ Exception -> 0x0435 }
        if (r2 == 0) goto L_0x00dc;
    L_0x00d2:
        r2 = "RESETTOKEN";
        r2 = r11.getInt(r2);	 Catch:{ Exception -> 0x0435 }
        if (r2 == 0) goto L_0x0147;
    L_0x00da:
        r2 = 1;
    L_0x00db:
        r4 = r2;
    L_0x00dc:
        r2 = "SETTOKEN";
        r2 = r11.has(r2);	 Catch:{ Exception -> 0x0435 }
        if (r2 == 0) goto L_0x0445;
    L_0x00e4:
        r2 = "SETTOKEN";
        r2 = r11.getString(r2);	 Catch:{ Exception -> 0x0435 }
    L_0x00ea:
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
    L_0x00ee:
        r7 = f;
        monitor-enter(r7);
        if (r3 == 0) goto L_0x0100;
    L_0x00f3:
        r3 = r10.a;	 Catch:{ all -> 0x0154 }
        r25 = "tbs_deskey_token";
        r26 = "";
        r0 = r25;
        r1 = r26;
        r3.put(r0, r1);	 Catch:{ all -> 0x0154 }
    L_0x0100:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x0154 }
        if (r3 != 0) goto L_0x0134;
    L_0x0106:
        r3 = r2.length();	 Catch:{ all -> 0x0154 }
        r25 = 96;
        r0 = r25;
        if (r3 != r0) goto L_0x0134;
    L_0x0110:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0154 }
        r3.<init>();	 Catch:{ all -> 0x0154 }
        r2 = r3.append(r2);	 Catch:{ all -> 0x0154 }
        r3 = "&";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0154 }
        r3 = com.tencent.smtt.utils.p.c();	 Catch:{ all -> 0x0154 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0154 }
        r2 = r2.toString();	 Catch:{ all -> 0x0154 }
        r3 = r10.a;	 Catch:{ all -> 0x0154 }
        r25 = "tbs_deskey_token";
        r0 = r25;
        r3.put(r0, r2);	 Catch:{ all -> 0x0154 }
    L_0x0134:
        monitor-exit(r7);	 Catch:{ all -> 0x0154 }
        r2 = 1;
        if (r5 != r2) goto L_0x015d;
    L_0x0138:
        if (r30 == 0) goto L_0x0157;
    L_0x013a:
        r2 = -110; // 0xffffffffffffff92 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x013f:
        r2 = c;
        com.tencent.smtt.sdk.QbSdk.reset(r2);
        r2 = 0;
        goto L_0x002e;
    L_0x0147:
        r2 = 0;
        goto L_0x00db;
    L_0x0149:
        r2 = move-exception;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
    L_0x014e:
        r27 = r3;
        r3 = r2;
        r2 = r27;
        goto L_0x00ee;
    L_0x0154:
        r2 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0154 }
        throw r2;
    L_0x0157:
        r2 = -210; // 0xffffffffffffff2e float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x013f;
    L_0x015d:
        r2 = 1;
        if (r4 != r2) goto L_0x0172;
    L_0x0160:
        r2 = d;
        r3 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r2.removeMessages(r3);
        r2 = d;
        r3 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r2 = android.os.Message.obtain(r2, r3);
        r2.sendToTarget();
    L_0x0172:
        r4 = 86400; // 0x15180 float:1.21072E-40 double:4.26873E-319;
        r2 = 1;
        r0 = r24;
        if (r0 != r2) goto L_0x043f;
    L_0x017a:
        r2 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
        r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0442;
    L_0x0181:
        r2 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
    L_0x0184:
        r8 = 0;
        r7 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r7 <= 0) goto L_0x043f;
    L_0x018a:
        r4 = r10.a;
        r5 = "retry_interval";
        r2 = java.lang.Long.valueOf(r2);
        r4.put(r5, r2);
        r2 = android.text.TextUtils.isEmpty(r13);
        if (r2 == 0) goto L_0x01bd;
    L_0x019b:
        r2 = c;
        r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r2);
        if (r2 == 0) goto L_0x01bd;
    L_0x01a3:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        r10.commit();
        if (r30 == 0) goto L_0x01ba;
    L_0x01b4:
        r2 = c;
        r3 = 0;
        com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(r2, r15, r3);
    L_0x01ba:
        r2 = 0;
        goto L_0x002e;
    L_0x01bd:
        if (r12 != 0) goto L_0x01f3;
    L_0x01bf:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        if (r30 == 0) goto L_0x01e0;
    L_0x01cd:
        r2 = r10.a;
        r3 = "tbs_download_interrupt_code_reason";
        r4 = -111; // 0xffffffffffffff91 float:NaN double:NaN;
        r4 = java.lang.Integer.valueOf(r4);
        r2.put(r3, r4);
    L_0x01da:
        r10.commit();
        r2 = 0;
        goto L_0x002e;
    L_0x01e0:
        r2 = r10.a;
        r3 = "tbs_download_interrupt_code_reason";
        r4 = -211; // 0xffffffffffffff2d float:NaN double:NaN;
        r4 = java.lang.Integer.valueOf(r4);
        r2.put(r3, r4);
        r2 = -211; // 0xffffffffffffff2d float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x01da;
    L_0x01f3:
        r2 = c;
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2);
        r2 = r2.mPreferences;
        r3 = "tbs_download_version";
        r4 = 0;
        r3 = r2.getInt(r3, r4);
        if (r3 <= r15) goto L_0x0212;
    L_0x0204:
        r2 = g;
        r2.c();
        r2 = com.tencent.smtt.sdk.aj.a();
        r4 = c;
        r2.g(r4);
    L_0x0212:
        r0 = r29;
        if (r0 >= r15) goto L_0x021c;
    L_0x0216:
        r2 = android.text.TextUtils.isEmpty(r13);
        if (r2 == 0) goto L_0x02d1;
    L_0x021c:
        r2 = r10.a;
        r4 = "tbs_needdownload";
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);
        r2.put(r4, r5);
        if (r30 == 0) goto L_0x02ab;
    L_0x022a:
        r2 = android.text.TextUtils.isEmpty(r13);
        if (r2 == 0) goto L_0x027b;
    L_0x0230:
        r2 = r10.a;
        r4 = "tbs_download_interrupt_code_reason";
        r5 = -124; // 0xffffffffffffff84 float:NaN double:NaN;
        r5 = java.lang.Integer.valueOf(r5);
        r2.put(r4, r5);
    L_0x023d:
        r10.commit();
        r2 = "TbsDownload";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "version error or downloadUrl empty ,return ahead tbsLocalVersion=";
        r4 = r4.append(r5);
        r0 = r29;
        r4 = r4.append(r0);
        r5 = " tbsDownloadVersion=";
        r4 = r4.append(r5);
        r4 = r4.append(r15);
        r5 = " tbsLastDownloadVersion=";
        r4 = r4.append(r5);
        r3 = r4.append(r3);
        r4 = " downloadUrl=";
        r3 = r3.append(r4);
        r3 = r3.append(r13);
        r3 = r3.toString();
        com.tencent.smtt.utils.TbsLog.i(r2, r3);
        r2 = 0;
        goto L_0x002e;
    L_0x027b:
        if (r15 > 0) goto L_0x028b;
    L_0x027d:
        r2 = r10.a;
        r4 = "tbs_download_interrupt_code_reason";
        r5 = -125; // 0xffffffffffffff83 float:NaN double:NaN;
        r5 = java.lang.Integer.valueOf(r5);
        r2.put(r4, r5);
        goto L_0x023d;
    L_0x028b:
        r0 = r29;
        if (r0 < r15) goto L_0x029d;
    L_0x028f:
        r2 = r10.a;
        r4 = "tbs_download_interrupt_code_reason";
        r5 = -127; // 0xffffffffffffff81 float:NaN double:NaN;
        r5 = java.lang.Integer.valueOf(r5);
        r2.put(r4, r5);
        goto L_0x023d;
    L_0x029d:
        r2 = r10.a;
        r4 = "tbs_download_interrupt_code_reason";
        r5 = -112; // 0xffffffffffffff90 float:NaN double:NaN;
        r5 = java.lang.Integer.valueOf(r5);
        r2.put(r4, r5);
        goto L_0x023d;
    L_0x02ab:
        r2 = -212; // 0xffffffffffffff2c float:NaN double:NaN;
        r4 = android.text.TextUtils.isEmpty(r13);
        if (r4 == 0) goto L_0x02c5;
    L_0x02b3:
        r2 = -217; // 0xffffffffffffff27 float:NaN double:NaN;
    L_0x02b5:
        r4 = r10.a;
        r5 = "tbs_download_interrupt_code_reason";
        r6 = java.lang.Integer.valueOf(r2);
        r4.put(r5, r6);
        r10.setDownloadInterruptCode(r2);
        goto L_0x023d;
    L_0x02c5:
        if (r15 > 0) goto L_0x02ca;
    L_0x02c7:
        r2 = -218; // 0xffffffffffffff26 float:NaN double:NaN;
        goto L_0x02b5;
    L_0x02ca:
        r0 = r29;
        if (r0 < r15) goto L_0x02b5;
    L_0x02ce:
        r2 = -219; // 0xffffffffffffff25 float:NaN double:NaN;
        goto L_0x02b5;
    L_0x02d1:
        r2 = r10.mPreferences;
        r3 = "tbs_downloadurl";
        r4 = 0;
        r2 = r2.getString(r3, r4);
        r2 = r13.equals(r2);
        if (r2 != 0) goto L_0x02fd;
    L_0x02e0:
        r2 = g;
        r2.c();
        r2 = r10.a;
        r3 = "tbs_download_failed_retrytimes";
        r4 = 0;
        r4 = java.lang.Integer.valueOf(r4);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_success_retrytimes";
        r4 = 0;
        r4 = java.lang.Integer.valueOf(r4);
        r2.put(r3, r4);
    L_0x02fd:
        r2 = r10.a;
        r3 = "tbs_download_version";
        r4 = java.lang.Integer.valueOf(r15);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_downloadurl";
        r2.put(r3, r13);
        r2 = r10.a;
        r3 = "tbs_downloadurl_list";
        r2.put(r3, r14);
        r2 = r10.a;
        r3 = "tbs_responsecode";
        r4 = java.lang.Integer.valueOf(r12);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_maxflow";
        r4 = java.lang.Integer.valueOf(r16);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_min_free_space";
        r4 = java.lang.Integer.valueOf(r17);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_success_max_retrytimes";
        r4 = java.lang.Integer.valueOf(r18);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_download_failed_max_retrytimes";
        r4 = java.lang.Integer.valueOf(r19);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_single_timeout";
        r4 = java.lang.Long.valueOf(r20);
        r2.put(r3, r4);
        r2 = r10.a;
        r3 = "tbs_apkfilesize";
        r4 = java.lang.Long.valueOf(r22);
        r2.put(r3, r4);
        r10.commit();
        if (r6 == 0) goto L_0x036f;
    L_0x0368:
        r2 = r10.a;
        r3 = "tbs_apk_md5";
        r2.put(r3, r6);
    L_0x036f:
        if (r31 != 0) goto L_0x03c7;
    L_0x0371:
        r2 = com.tencent.smtt.sdk.aj.a();
        r3 = c;
        r2 = r2.a(r3, r15);
        if (r2 == 0) goto L_0x03c7;
    L_0x037d:
        if (r30 == 0) goto L_0x03b4;
    L_0x037f:
        r2 = r10.a;
        r3 = "tbs_download_interrupt_code_reason";
        r4 = -113; // 0xffffffffffffff8f float:NaN double:NaN;
        r4 = java.lang.Integer.valueOf(r4);
        r2.put(r3, r4);
    L_0x038c:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
    L_0x0398:
        r2 = "stop_pre_oat";
        r3 = 0;
        r2 = r11.optInt(r2, r3);
        r3 = 1;
        if (r2 != r3) goto L_0x03ae;
    L_0x03a2:
        r2 = r10.a;
        r3 = "tbs_stop_preoat";
        r4 = 1;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
    L_0x03ae:
        r10.commit();
        r2 = 1;
        goto L_0x002e;
    L_0x03b4:
        r2 = r10.a;
        r3 = "tbs_download_interrupt_code_reason";
        r4 = -213; // 0xffffffffffffff2b float:NaN double:NaN;
        r4 = java.lang.Integer.valueOf(r4);
        r2.put(r3, r4);
        r2 = -213; // 0xffffffffffffff2b float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
        goto L_0x038c;
    L_0x03c7:
        if (r31 != 0) goto L_0x0420;
    L_0x03c9:
        r3 = g;
        r2 = 1;
        if (r12 == r2) goto L_0x03d1;
    L_0x03ce:
        r2 = 2;
        if (r12 != r2) goto L_0x041e;
    L_0x03d1:
        r2 = 1;
    L_0x03d2:
        r0 = r31;
        r2 = r3.a(r0, r2);
        if (r2 == 0) goto L_0x0420;
    L_0x03da:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 0;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        r2 = c;
        r2 = com.tencent.smtt.sdk.TbsLogReport.a(r2);
        r3 = 100;
        r2.h(r3);
        r2 = c;
        r2 = com.tencent.smtt.sdk.TbsLogReport.a(r2);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "use local backup apk in needDownload";
        r3 = r3.append(r4);
        r4 = g;
        r4 = r4.a;
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.e(r3);
        r2 = c;
        r2 = com.tencent.smtt.sdk.TbsLogReport.a(r2);
        r3 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD;
        r2.a(r3);
        goto L_0x0398;
    L_0x041e:
        r2 = 0;
        goto L_0x03d2;
    L_0x0420:
        if (r30 != 0) goto L_0x0427;
    L_0x0422:
        r2 = -216; // 0xffffffffffffff28 float:NaN double:NaN;
        r10.setDownloadInterruptCode(r2);
    L_0x0427:
        r2 = r10.a;
        r3 = "tbs_needdownload";
        r4 = 1;
        r4 = java.lang.Boolean.valueOf(r4);
        r2.put(r3, r4);
        goto L_0x0398;
    L_0x0435:
        r2 = move-exception;
        r2 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        goto L_0x014e;
    L_0x043c:
        r3 = move-exception;
        goto L_0x00a7;
    L_0x043f:
        r2 = r4;
        goto L_0x018a;
    L_0x0442:
        r2 = r8;
        goto L_0x0184;
    L_0x0445:
        r2 = r3;
        goto L_0x00ea;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(java.lang.String, int, boolean, boolean):boolean");
    }

    private static JSONObject b(boolean z, boolean z2) {
        JSONObject jSONObject;
        int f;
        int i;
        JSONArray h;
        Object obj = null;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        String a = a(c);
        String d = b.d(c);
        String c = b.c(c);
        String f2 = b.f(c);
        String str = "";
        String str2 = "";
        String str3 = "";
        str3 = TimeZone.getDefault().getID();
        if (str3 != null) {
            Object obj2 = str3;
        } else {
            String str4 = str;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService("phone");
            if (telephonyManager != null) {
                Object simCountryIso;
                simCountryIso = telephonyManager.getSimCountryIso();
                if (simCountryIso == null) {
                    str = str2;
                }
                jSONObject = new JSONObject();
                jSONObject.put("TIMEZONEID", obj2);
                jSONObject.put("COUNTRYISO", simCountryIso);
                jSONObject.put("PROTOCOLVERSION", 1);
                if (TbsShareManager.isThirdPartyApp(c)) {
                    f = aj.a().f(c);
                    if (!z && f == 0 && aj.a().e(c)) {
                        f = -1;
                    }
                    TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] tbsLocalVersion=" + f + " isDownloadForeground=" + z2);
                    if (z2) {
                        i = f;
                    } else {
                        if (!aj.a().e(c)) {
                            f = 0;
                        }
                        i = f;
                    }
                } else {
                    i = QbSdk.c ? TbsShareManager.a(c, false) : TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
                }
                if (z) {
                    jSONObject.put("FUNCTION", i != 0 ? 0 : 1);
                } else {
                    jSONObject.put("FUNCTION", 2);
                }
                if (TbsShareManager.isThirdPartyApp(c)) {
                    h = h();
                    if (Apn.getApnType(c) != 3 && h.length() != 0 && i == 0 && z) {
                        jSONObject.put("TBSBACKUPARR", h);
                    }
                } else {
                    h = f();
                    jSONObject.put("TBSVLARR", h);
                    instance.a.put(TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, h.toString());
                    instance.commit();
                    if (QbSdk.c) {
                        jSONObject.put("THIRDREQ", 1);
                    }
                }
                jSONObject.put("APPN", c.getPackageName());
                jSONObject.put("APPVN", a(instance.mPreferences.getString(TbsConfigKey.KEY_APP_VERSIONNAME, null)));
                jSONObject.put("APPVC", instance.mPreferences.getInt("app_versioncode", 0));
                jSONObject.put("APPMETA", a(instance.mPreferences.getString(TbsConfigKey.KEY_APP_METADATA, null)));
                jSONObject.put("TBSSDKV", 43100);
                jSONObject.put("TBSV", i);
                if (i != 0) {
                    jSONObject.put("TBSBACKUPV", g.a());
                }
                jSONObject.put("CPU", e);
                jSONObject.put("UA", a);
                jSONObject.put("IMSI", a(d));
                jSONObject.put("IMEI", a(c));
                jSONObject.put("ANDROID_ID", a(f2));
                if (!TbsShareManager.isThirdPartyApp(c)) {
                    if (i == 0) {
                        jSONObject.put("STATUS", QbSdk.a(c, i) ? 0 : 1);
                    } else {
                        jSONObject.put("STATUS", 0);
                    }
                }
                boolean z3 = TbsDownloadConfig.getInstance(c).mPreferences.getBoolean(TbsConfigKey.KEY_FULL_PACKAGE, false);
                simCountryIso = QbSdk.a(c, "can_unlzma", null);
                boolean booleanValue = (simCountryIso == null && (simCountryIso instanceof Boolean)) ? ((Boolean) simCountryIso).booleanValue() : false;
                if (booleanValue && !z3) {
                    obj = 1;
                }
                if (obj != null) {
                    jSONObject.put("REQUEST_LZMA", 1);
                }
                if (getOverSea(c)) {
                    jSONObject.put("OVERSEA", 1);
                }
                if (z2) {
                    jSONObject.put("DOWNLOAD_FOREGROUND", 1);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] jsonData=" + jSONObject.toString());
                return jSONObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        str = str3;
        if (simCountryIso == null) {
            str = str2;
        }
        jSONObject = new JSONObject();
        try {
            jSONObject.put("TIMEZONEID", obj2);
            jSONObject.put("COUNTRYISO", simCountryIso);
            jSONObject.put("PROTOCOLVERSION", 1);
            if (TbsShareManager.isThirdPartyApp(c)) {
                f = aj.a().f(c);
                f = -1;
                TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] tbsLocalVersion=" + f + " isDownloadForeground=" + z2);
                if (z2) {
                    i = f;
                } else {
                    if (aj.a().e(c)) {
                        f = 0;
                    }
                    i = f;
                }
            } else if (QbSdk.c) {
            }
            if (z) {
                if (i != 0) {
                }
                jSONObject.put("FUNCTION", i != 0 ? 0 : 1);
            } else {
                jSONObject.put("FUNCTION", 2);
            }
            if (TbsShareManager.isThirdPartyApp(c)) {
                h = h();
                jSONObject.put("TBSBACKUPARR", h);
            } else {
                h = f();
                jSONObject.put("TBSVLARR", h);
                instance.a.put(TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, h.toString());
                instance.commit();
                if (QbSdk.c) {
                    jSONObject.put("THIRDREQ", 1);
                }
            }
            jSONObject.put("APPN", c.getPackageName());
            jSONObject.put("APPVN", a(instance.mPreferences.getString(TbsConfigKey.KEY_APP_VERSIONNAME, null)));
            jSONObject.put("APPVC", instance.mPreferences.getInt("app_versioncode", 0));
            jSONObject.put("APPMETA", a(instance.mPreferences.getString(TbsConfigKey.KEY_APP_METADATA, null)));
            jSONObject.put("TBSSDKV", 43100);
            jSONObject.put("TBSV", i);
            if (i != 0) {
                jSONObject.put("TBSBACKUPV", g.a());
            }
            jSONObject.put("CPU", e);
            jSONObject.put("UA", a);
            jSONObject.put("IMSI", a(d));
            jSONObject.put("IMEI", a(c));
            jSONObject.put("ANDROID_ID", a(f2));
            if (TbsShareManager.isThirdPartyApp(c)) {
                if (i == 0) {
                    jSONObject.put("STATUS", 0);
                } else {
                    if (QbSdk.a(c, i)) {
                    }
                    jSONObject.put("STATUS", QbSdk.a(c, i) ? 0 : 1);
                }
            }
            boolean z32 = TbsDownloadConfig.getInstance(c).mPreferences.getBoolean(TbsConfigKey.KEY_FULL_PACKAGE, false);
            simCountryIso = QbSdk.a(c, "can_unlzma", null);
            if (simCountryIso == null) {
            }
            obj = 1;
            if (obj != null) {
                jSONObject.put("REQUEST_LZMA", 1);
            }
            if (getOverSea(c)) {
                jSONObject.put("OVERSEA", 1);
            }
            if (z2) {
                jSONObject.put("DOWNLOAD_FOREGROUND", 1);
            }
        } catch (Exception e2) {
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] jsonData=" + jSONObject.toString());
        return jSONObject;
    }

    @TargetApi(11)
    static void b(Context context) {
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.a(context).d();
        ac.c(context);
        (VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_extension_config", 4) : context.getSharedPreferences("tbs_extension_config", 0)).edit().clear().commit();
        (VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).edit().clear().commit();
    }

    private static boolean c() {
        try {
            for (String sharedTbsCoreVersion : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(c, sharedTbsCoreVersion) > 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean c(boolean z, boolean z2) {
        TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest]isQuery: " + z);
        if (aj.a().b(c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] -- isTbsLocalInstalled!");
            return false;
        }
        int i;
        boolean a;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        File file = new File(k.a(c, 1), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file2 = new File(k.a(c, 2), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file3 = new File(k.a(c, 3), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        File file4 = new File(k.a(c, 4), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
        if (!file4.exists()) {
            if (file3.exists()) {
                file3.renameTo(file4);
            } else if (file2.exists()) {
                file2.renameTo(file4);
            } else if (file.exists()) {
                file.renameTo(file4);
            }
        }
        instance.a.put(TbsConfigKey.KEY_LAST_CHECK, Long.valueOf(System.currentTimeMillis()));
        instance.a.put(TbsConfigKey.KEY_APP_VERSIONNAME, b.a(c));
        instance.a.put("app_versioncode", Integer.valueOf(b.b(c)));
        instance.a.put(TbsConfigKey.KEY_APP_METADATA, b.a(c, "com.tencent.mm.BuildInfo.CLIENT_VERSION"));
        instance.commit();
        if (e == null) {
            e = b.a();
            instance.a.put(TbsConfigKey.KEY_DEVICE_CPUABI, e);
            instance.commit();
        }
        if (!TextUtils.isEmpty(e)) {
            Matcher matcher = null;
            try {
                matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
            } catch (Exception e) {
            }
            if (matcher != null && matcher.find()) {
                if (TbsShareManager.isThirdPartyApp(c)) {
                    if (z) {
                        instance.setDownloadInterruptCode(-104);
                        TbsLogReport.a(c).h(-104);
                    } else {
                        instance.setDownloadInterruptCode(-205);
                        TbsLogReport.a(c).h(-205);
                    }
                    TbsLogReport.a(c).e("mycpu is " + e);
                    TbsLogReport.a(c).a(EventType.TYPE_DOWNLOAD);
                    return false;
                } else if (z) {
                    instance.setDownloadInterruptCode(-104);
                    return false;
                } else {
                    instance.setDownloadInterruptCode(-205);
                    return false;
                }
            }
        }
        JSONObject b = b(z, z2);
        try {
            i = b.getInt("TBSV");
        } catch (Exception e2) {
            i = -1;
        }
        if (i != -1) {
            try {
                String d = x.a(c).d();
                TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] postUrl=" + d);
                a = a(n.a(d, b.toString().getBytes("utf-8"), new ag(z, instance), false), i, z, z2);
            } catch (Throwable th) {
                th.printStackTrace();
                if (z) {
                    instance.setDownloadInterruptCode(-106);
                    a = false;
                } else {
                    instance.setDownloadInterruptCode(-206);
                }
            }
            return a;
        }
        a = false;
        return a;
    }

    private static synchronized void d() {
        synchronized (TbsDownloader.class) {
            if (h == null) {
                h = ah.a();
                try {
                    g = new ac(c);
                    d = new af(h.getLooper());
                } catch (Exception e) {
                    i = true;
                    TbsLog.e(LOGTAG, "TbsApkDownloader init has Exception");
                }
            }
        }
    }

    private static boolean e() {
        try {
            return TbsDownloadConfig.getInstance(c).mPreferences.getString(TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, "").equals(f().toString());
        } catch (Exception e) {
            return false;
        }
    }

    private static JSONArray f() {
        if (!TbsShareManager.isThirdPartyApp(c)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Object coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        String packageName = c.getApplicationContext().getPackageName();
        Object obj;
        if (packageName.equals(TbsShareManager.e(c))) {
            int length = coreProviderAppList.length;
            obj = new String[(length + 1)];
            System.arraycopy(coreProviderAppList, 0, obj, 0, length);
            obj[length] = packageName;
        } else {
            obj = coreProviderAppList;
        }
        for (String str : r0) {
            int sharedTbsCoreVersion = TbsShareManager.getSharedTbsCoreVersion(c, str);
            if (sharedTbsCoreVersion > 0) {
                Context a = TbsShareManager.a(c, str);
                if (a == null || aj.a().c(a)) {
                    int i;
                    for (i = 0; i < jSONArray.length(); i++) {
                        if (jSONArray.optInt(i) == sharedTbsCoreVersion) {
                            i = 1;
                            break;
                        }
                    }
                    i = 0;
                    if (i == 0) {
                        jSONArray.put(sharedTbsCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                }
            }
        }
        return jSONArray;
    }

    private static boolean g() {
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        if (instance.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= instance.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            instance.setDownloadInterruptCode(-115);
            return false;
        } else if (instance.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= instance.getDownloadFailedMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            instance.setDownloadInterruptCode(-116);
            return false;
        } else if (k.b(c)) {
            if (System.currentTimeMillis() - instance.mPreferences.getLong(TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0) <= com.umeng.analytics.a.i) {
                long j = instance.mPreferences.getLong(TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0);
                TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j);
                if (j >= instance.getDownloadMaxflow()) {
                    TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    instance.setDownloadInterruptCode(-120);
                    return false;
                }
            }
            return true;
        } else {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            instance.setDownloadInterruptCode(-117);
            return false;
        }
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!k) {
                k = true;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
                if (instance.mPreferences.contains(TbsConfigKey.KEY_IS_OVERSEA)) {
                    j = instance.mPreferences.getBoolean(TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + j);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + j);
            }
            z = j;
        }
        return z;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return h;
    }

    private static JSONArray h() {
        JSONArray jSONArray = new JSONArray();
        for (String a : TbsShareManager.getCoreProviderAppList()) {
            File file = new File(k.a(c, a, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file != null && file.exists()) {
                long a2 = (long) a.a(c, file);
                if (a2 > 0) {
                    boolean z;
                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (((long) jSONArray.optInt(i)) == a2) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    if (!z) {
                        jSONArray.put(a2);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static boolean isDownloadForeground() {
        return g != null && g.d();
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading]");
            z = a;
        }
        return z;
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        TbsLog.initIfNeed(context);
        if (!aj.b) {
            TbsLog.app_extra(LOGTAG, context);
            c = context.getApplicationContext();
            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
            instance.setDownloadInterruptCode(-100);
            if (!a(c, z, tbsDownloaderCallback)) {
                return false;
            }
            d();
            if (i) {
                if (tbsDownloaderCallback != null) {
                    tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                }
                instance.setDownloadInterruptCode(-105);
                return false;
            }
            boolean contains;
            boolean a = a(c, z2, false);
            if (a) {
                a(z2, tbsDownloaderCallback);
                instance.setDownloadInterruptCode(-114);
            }
            d.removeMessages(102);
            Message.obtain(d, 102).sendToTarget();
            if (QbSdk.c || !TbsShareManager.isThirdPartyApp(context)) {
                contains = instance.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD);
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + contains);
                contains = (contains || TbsShareManager.isThirdPartyApp(context)) ? instance.mPreferences.getBoolean(TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
            } else {
                contains = false;
            }
            if (!contains) {
                int f = aj.a().f(c);
                if (a || f <= 0) {
                    d.removeMessages(103);
                    if (f > 0 || a) {
                        Message.obtain(d, 103, 1, 0, c).sendToTarget();
                    } else {
                        Message.obtain(d, 103, 0, 0, c).sendToTarget();
                    }
                    instance.setDownloadInterruptCode(-121);
                } else {
                    instance.setDownloadInterruptCode(-119);
                }
            } else if (g()) {
                instance.setDownloadInterruptCode(-118);
            } else {
                contains = false;
            }
            if (!(a || tbsDownloaderCallback == null)) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] needDownload=" + contains);
            return contains;
        } else if (tbsDownloaderCallback == null) {
            return false;
        } else {
            tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            return false;
        }
    }

    public static boolean needSendRequest(Context context, boolean z) {
        boolean z2 = true;
        c = context.getApplicationContext();
        TbsLog.initIfNeed(context);
        if (!a(c, z, null)) {
            return false;
        }
        int f = aj.a().f(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] localTbsVersion=" + f);
        if (f > 0) {
            return false;
        }
        if (a(c, false, true)) {
            return true;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        boolean contains = instance.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] hasNeedDownloadKey=" + contains);
        boolean z3 = !contains ? true : instance.mPreferences.getBoolean(TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] needDownload=" + z3);
        if (!(z3 && g())) {
            z2 = false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] ret=" + z2);
        return z2;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        int i = 1;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + c);
            if (!aj.b) {
                a = true;
                c = context.getApplicationContext();
                TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-200);
                if (VERSION.SDK_INT < 8) {
                    QbSdk.j.onDownloadFinish(110);
                    TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-201);
                } else {
                    d();
                    if (i) {
                        QbSdk.j.onDownloadFinish(121);
                        TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-202);
                    } else {
                        if (z) {
                            stopDownload();
                        }
                        d.removeMessages(101);
                        d.removeMessages(100);
                        Message obtain = Message.obtain(d, 101, QbSdk.j);
                        if (!z) {
                            i = 0;
                        }
                        obtain.arg1 = i;
                        obtain.sendToTarget();
                    }
                }
            }
        }
    }

    public static void stopDownload() {
        if (!i) {
            TbsLog.i(LOGTAG, "[TbsDownloader.stopDownload]");
            if (g != null) {
                g.b();
            }
            if (d != null) {
                d.removeMessages(100);
                d.removeMessages(101);
            }
        }
    }
}
