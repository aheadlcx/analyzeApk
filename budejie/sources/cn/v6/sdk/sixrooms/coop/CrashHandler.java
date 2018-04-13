package cn.v6.sdk.sixrooms.coop;

import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import cn.v6.sixrooms.Manage;
import cn.v6.sixrooms.engine.CrashErrorInfoEngine;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.DateUtil;
import cn.v6.sixrooms.utils.FileUtil;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.umeng.analytics.pro.x;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import org.json.JSONException;
import org.json.JSONObject;

public class CrashHandler implements UncaughtExceptionHandler {
    private static CrashHandler INSTANCE = new CrashHandler();
    public static final String TAG = "CrashHandler";
    public static final boolean debug = false;
    private JSONObject currentErrorJson = null;
    private CrashErrorInfoEngine errorEngine = new CrashErrorInfoEngine(new cn.v6.sixrooms.engine.CrashErrorInfoEngine.CallBack() {
        public void result(boolean z) {
            CrashHandler.this.currentErrorJson = null;
        }

        public void handleErrorInfo(String str, String str2) {
        }

        public void error(int i) {
        }
    });
    private UncaughtExceptionHandler mDefaultHandler;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(boolean z);
    }

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init() {
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (handleException(th) || this.mDefaultHandler == null) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Manage.getInstance().exit();
            return;
        }
        this.mDefaultHandler.uncaughtException(thread, th);
    }

    private boolean handleException(final Throwable th) {
        if (th == null) {
            return false;
        }
        new Thread() {
            public void run() {
                Looper.prepare();
                CrashHandler.this.errorEngine.sendCrashErroInfo(CrashHandler.this.GetCrashInfo(th));
                CrashHandler.this.writeCrash2File(CrashHandler.this.GetCrashInfo(th), "crash" + DateUtil.getStringDate() + ".txt");
                try {
                    Toast.makeText(V6Coop.getInstance().getContext(), "很抱歉,程序出现异常!", 0).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Looper.loop();
            }
        }.start();
        return true;
    }

    private JSONObject collectCrashInfo(Throwable th) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object visitorId;
            String str = HistoryOpenHelper.COLUMN_UID;
            if (LoginUtils.getLoginUserBean() == null) {
                visitorId = SaveUserInfoUtils.getVisitorId(V6Coop.getInstance().getContext());
            } else {
                visitorId = LoginUtils.getLoginUID();
            }
            jSONObject.put(str, visitorId);
            jSONObject.put("errorInfo", GetCrashInfo(th));
            jSONObject.put("manuFaturer", Build.MANUFACTURER);
            jSONObject.put("versionCode", String.valueOf(AppInfoUtils.getAppCode()));
            jSONObject.put(x.F, AppInfoUtils.getLanguage());
            jSONObject.put("deviceVersion", AppInfoUtils.getDeviceVersion());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.currentErrorJson = jSONObject;
        return jSONObject;
    }

    private void writeCrash2File(String str, String str2) {
        if (!TextUtils.isEmpty(str) && FileUtil.isSdCard()) {
            File file = new File(Environment.getExternalStorageDirectory() + "/sixrooms/logs/");
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + File.separator + str2);
                if (str != null && str.length() > 0) {
                    fileOutputStream.write(str.getBytes());
                }
                fileOutputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private String GetCrashInfo(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        String stringBuffer2 = stringBuffer.toString();
        Log.e(TAG, stringBuffer2);
        return stringBuffer2;
    }
}
