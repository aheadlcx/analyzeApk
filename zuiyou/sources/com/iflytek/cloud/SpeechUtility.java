package com.iflytek.cloud;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.iflytek.cloud.thirdparty.bh;
import com.iflytek.cloud.thirdparty.bj;
import com.iflytek.cloud.thirdparty.bk;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.cd;
import com.iflytek.cloud.thirdparty.ce;
import com.iflytek.cloud.thirdparty.cq;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import com.iflytek.speech.SpeechComponent;
import com.iflytek.speech.UtilityConfig;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SpeechUtility extends bh {
    public static final com.iflytek.cloud.thirdparty.bh.a DEF_ENGINE_MODE = com.iflytek.cloud.thirdparty.bh.a.MSC;
    public static final String TAG_RESOURCE_CONTENT = "tag_rescontent";
    public static final String TAG_RESOURCE_RESULT = "result";
    public static final String TAG_RESOURCE_RET = "ret";
    private static SpeechUtility d = null;
    protected com.iflytek.cloud.thirdparty.bh.a a = DEF_ENGINE_MODE;
    private ArrayList<SpeechComponent> e = new ArrayList();
    private int f = -1;
    private Context g = null;
    private boolean h = false;
    private a i = null;

    private class a extends BroadcastReceiver {
        final /* synthetic */ SpeechUtility a;

        private a(SpeechUtility speechUtility) {
            this.a = speechUtility;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            String concat = String.valueOf("package:").concat(UtilityConfig.COMPONENT_PKG);
            String str = "android.intent.action.PACKAGE_REMOVED";
            String str2 = "android.intent.action.PACKAGE_REPLACED";
            if (("android.intent.action.PACKAGE_ADDED".equals(action) || str.equals(action) || str2.equals(action)) && concat.equals(dataString) && SpeechUtility.getUtility() != null) {
                SpeechUtility.getUtility().checkServiceInstalled();
            }
        }
    }

    private SpeechUtility(Context context, String str) throws SpeechError {
        boolean z = false;
        this.g = context.getApplicationContext();
        super.setParameter(SpeechConstant.PARAMS, str);
        MSC.loadLibrary(this.c.b(SpeechConstant.LIB_NAME, SpeechConstant.MODE_MSC));
        cb.c();
        setParameter(SpeechConstant.PARAMS, str);
        this.a = com.iflytek.cloud.thirdparty.bh.a.MSC;
        int b = b();
        if (b != 0) {
            throw new SpeechError(b);
        }
        d();
        e();
        cd a = cd.a(context);
        a.a();
        a.b();
        try {
            com.iflytek.common.a.a(context, "appid", this.c.e("appid"));
            com.iflytek.common.a.a(context);
            com.iflytek.common.a.a(false);
        } catch (Throwable e) {
            cb.a(e);
        }
        Object parameter = getParameter("lxy_tp_dc");
        if (TextUtils.isEmpty(parameter) || !"false".equals(parameter)) {
            z = true;
        }
        cb.a("DC init enable=" + parameter);
        cq.a(context, getParameter("appid"), z);
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PackageManager packageManager = this.g.getPackageManager();
            Intent intent = new Intent(str);
            intent.setPackage(UtilityConfig.COMPONENT_PKG);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, Opcodes.SHL_INT_LIT8);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    SpeechComponent b = b(resolveInfo.serviceInfo.packageName);
                    if (b != null) {
                        try {
                            for (String addEngine : resolveInfo.serviceInfo.metaData.getString(UtilityConfig.METADATA_KEY_ENGINE_TYPE).split(",")) {
                                b.addEngine(addEngine);
                            }
                        } catch (Throwable e) {
                            cb.a(e);
                        }
                    }
                }
            }
        }
    }

    private static boolean a(Context context) {
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        cb.a("process name:" + runningAppProcessInfo.processName);
                        if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).packageName.equals(runningAppProcessInfo.processName)) {
                            cb.a("process name:" + runningAppProcessInfo.processName + "is own process");
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            cb.a(e);
        }
        return false;
    }

    private int b() {
        if (!MSC.isLoaded()) {
            return 21002;
        }
        cb.a("SpeechUtility start login");
        SpeechError a = new bk(this.g, this.c).a(this.c.e("usr"), this.c.e("pwd"));
        return a == null ? 0 : a.getErrorCode();
    }

    private SpeechComponent b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Object obj;
        SpeechComponent speechComponent;
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            if (str.equals(((SpeechComponent) it.next()).getPackageName())) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj == null) {
            speechComponent = new SpeechComponent(str);
            this.e.add(speechComponent);
        } else {
            speechComponent = null;
        }
        return speechComponent;
    }

    private boolean c() {
        return MSC.isLoaded() ? bj.a() : true;
    }

    private boolean c(String str) {
        PackageManager packageManager = this.g.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(UtilityConfig.COMPONENT_PKG);
        return packageManager.queryIntentActivities(intent, 1).size() > 0;
    }

    public static synchronized SpeechUtility createUtility(Context context, String str) {
        SpeechUtility speechUtility;
        synchronized (SpeechUtility.class) {
            synchronized (b) {
                if (d == null) {
                    ce ceVar = new ce();
                    ceVar.b(str);
                    if (ceVar.a(SpeechConstant.FORCE_LOGIN, false) || a(context.getApplicationContext())) {
                        try {
                            d = new SpeechUtility(context, str);
                        } catch (Throwable e) {
                            cb.c("init failed");
                            cb.a(e);
                        }
                    } else {
                        cb.c("init failed, please call this method in your main process!");
                        d = null;
                    }
                }
            }
            speechUtility = d;
        }
        return speechUtility;
    }

    private void d() {
        if (checkServiceInstalled()) {
            a(UtilityConfig.ACTION_SPEECH_RECOGNIZER);
            a(UtilityConfig.ACTION_SPEECH_SYNTHESIZER);
            a(UtilityConfig.ACTION_SPEECH_UNDERSTANDER);
            a(UtilityConfig.ACTION_TEXT_UNDERSTANDER);
            a(UtilityConfig.ACTION_SPEECH_WAKEUP);
        }
    }

    private void e() {
        this.i = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme(EnvConsts.PACKAGE_MANAGER_SRVNAME);
        this.g.registerReceiver(this.i, intentFilter);
    }

    public static synchronized SpeechUtility getUtility() {
        SpeechUtility speechUtility;
        synchronized (SpeechUtility.class) {
            speechUtility = d;
        }
        return speechUtility;
    }

    protected boolean a() {
        try {
            return this.g.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0) != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public boolean checkServiceInstalled() {
        boolean z = false;
        int i = -1;
        try {
            PackageInfo packageInfo = this.g.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
            if (packageInfo != null) {
                z = true;
                i = packageInfo.versionCode;
            }
        } catch (NameNotFoundException e) {
        } catch (Throwable th) {
        }
        if (!(z == this.h && this.f == i)) {
            this.h = z;
            this.f = i;
            if (SpeechRecognizer.getRecognizer() != null) {
                SpeechRecognizer.getRecognizer().a(this.g);
            }
            if (SpeechSynthesizer.getSynthesizer() != null) {
                SpeechSynthesizer.getSynthesizer().a(this.g);
            }
            if (SpeechUnderstander.getUnderstander() != null) {
                SpeechUnderstander.getUnderstander().a(this.g);
            }
            if (TextUnderstander.getTextUnderstander() != null) {
                TextUnderstander.getTextUnderstander().a(this.g);
            }
        }
        return z;
    }

    public boolean destroy() {
        boolean z = true;
        if (d != null) {
            super.destroy();
            z = c();
        }
        if (z) {
            BroadcastReceiver broadcastReceiver = this.i;
            if (broadcastReceiver != null) {
                this.g.unregisterReceiver(broadcastReceiver);
            }
            this.i = null;
            cb.a("SpeechUtility destory success");
            cq.a();
            synchronized (b) {
                d = null;
            }
        }
        return z;
    }

    public String getComponentUrl() {
        StringBuffer stringBuffer = new StringBuffer(UtilityConfig.COMPONENT_URL);
        UtilityConfig.appendHttpParam(stringBuffer, "key", URLEncoder.encode(Base64.encodeToString(UtilityConfig.getComponentUrlParam(this.g).getBytes(), 0)));
        UtilityConfig.appendHttpParam(stringBuffer, "version", "2.0");
        return stringBuffer.toString();
    }

    public com.iflytek.cloud.thirdparty.bh.a getEngineMode() {
        return this.a;
    }

    public String getParameter(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.c.g(str)) {
            return super.getParameter(str);
        }
        if (!MSC.isLoaded()) {
            return null;
        }
        try {
            if (str.equals("ver_msc") || str.equals("ver_asr") || str.equals("ver_tts") || str.equals("ver_ivw")) {
                byte[] bytes = str.getBytes("utf-8");
                MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
                return mSCSessionInfo.errorcode == 0 ? new String(MSC.QMSPGetVersion(bytes, mSCSessionInfo), "utf-8") : null;
            } else {
                byte[] bytes2 = str.getBytes("utf-8");
                MSCSessionInfo mSCSessionInfo2 = new MSCSessionInfo();
                return MSC.QMSPGetParam(bytes2, mSCSessionInfo2) == 0 ? new String(mSCSessionInfo2.buffer, "utf-8") : null;
            }
        } catch (Throwable e) {
            cb.b(e);
            return null;
        } catch (Throwable e2) {
            cb.b(e2);
            return null;
        }
    }

    public String getPlusLocalInfo(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String str2 = "";
        if (!checkServiceInstalled()) {
            jSONObject.put("ret", 21001);
            return jSONObject.toString();
        } else if (getServiceVersion() < 97) {
            jSONObject.put("ret", 20018);
            return jSONObject.toString();
        } else if (10000 > getServiceVersion() || getServiceVersion() > 11000) {
            Object string;
            Cursor query = this.g.getContentResolver().query(Uri.parse("content://com.iflytek.vflynote.providers.LocalResourceProvider"), null, str, null, null);
            int columnIndex = query.getColumnIndex(TAG_RESOURCE_CONTENT);
            if (query == null || !query.moveToFirst()) {
                String str3 = str2;
            } else {
                string = query.getString(columnIndex);
                Log.v("SpeechUtility", string);
            }
            if (query != null) {
                query.close();
            }
            if (TextUtils.isEmpty(string)) {
                jSONObject.put("ret", 20004);
                return jSONObject.toString();
            }
            jSONObject.put("ret", 0);
            jSONObject.put(TAG_RESOURCE_RESULT, new JSONObject(string));
            return jSONObject.toString();
        } else {
            jSONObject.put("ret", 20020);
            return jSONObject.toString();
        }
    }

    public int getServiceVersion() {
        if (this.f < 0) {
            try {
                PackageInfo packageInfo = this.g.getPackageManager().getPackageInfo(UtilityConfig.COMPONENT_PKG, 0);
                if (packageInfo != null) {
                    this.f = packageInfo.versionCode;
                }
            } catch (NameNotFoundException e) {
            }
        }
        return this.f;
    }

    public int openEngineSettings(String str) {
        try {
            Intent intent = new Intent();
            intent.setPackage(UtilityConfig.COMPONENT_PKG);
            String str2 = UtilityConfig.COMPONENT_PKG;
            if ("tts".equals(str) && c(UtilityConfig.SETTINGS_ACTION_TTS)) {
                str2 = UtilityConfig.SETTINGS_ACTION_TTS;
            } else if ("asr".equals(str) && c(UtilityConfig.SETTINGS_ACTION_ASR)) {
                str2 = UtilityConfig.SETTINGS_ACTION_ASR;
            } else if (c(UtilityConfig.SETTINGS_ACTION_MAIN)) {
                str2 = UtilityConfig.SETTINGS_ACTION_MAIN;
            }
            intent.setAction(str2);
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            this.g.startActivity(intent);
            return 0;
        } catch (Throwable e) {
            cb.a(e);
            return 21002;
        }
    }

    public String[] queryAvailableEngines() {
        this.e.clear();
        d();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((SpeechComponent) it.next()).getEngines());
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }

    public boolean setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        super.setParameter(str, str2);
        if (!MSC.isLoaded() || SpeechConstant.PARAMS.equals(str)) {
            return true;
        }
        try {
            return MSC.QMSPSetParam(str.getBytes("utf-8"), str2.getBytes("utf-8")) == 0;
        } catch (Throwable e) {
            cb.a(e);
            return false;
        } catch (Throwable e2) {
            cb.a(e2);
            return false;
        }
    }
}
