package com.tencent.bugly.beta.tinker;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.bugly.beta.tinker.TinkerReport.Reporter;
import com.tencent.bugly.beta.tinker.TinkerUtils.ScreenState;
import com.tencent.tinker.lib.library.TinkerLoadLibrary;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Properties;

public class TinkerManager {
    public static final String MF_FILE = "YAPATCH.MF";
    public static final String PATCH_DIR = "dex";
    public static final String PATCH_NAME = "patch.apk";
    private static final String TAG = "Tinker.TinkerManager";
    public static String apkOriginalBuildNum = "";
    private static boolean isInstalled = false;
    public static String patchCurBuildNum = "";
    static TinkerPatchResultListener patchResultListener;
    private static UncaughtExceptionHandler systemExceptionHandler;
    private static TinkerManager tinkerManager = new TinkerManager();
    public static TinkerReport tinkerReport;
    private static TinkerUncaughtExceptionHandler uncaughtExceptionHandler;
    static LoadReporter userLoadReporter;
    static PatchListener userPatchListener;
    static PatchReporter userPatchReporter;
    static AbstractPatch userUpgradePatchProcessor;
    private Application application;
    private ApplicationLike applicationLike;
    private TinkerListener tinkerListener;

    public interface TinkerListener {
        void onApplyFailure(String str);

        void onApplySuccess(String str);

        void onDownloadFailure(String str);

        void onDownloadSuccess(String str);

        void onPatchRollback();

        void onPatchStart();
    }

    public interface TinkerPatchResultListener {
        void onPatchResult(PatchResult patchResult);
    }

    public static TinkerManager getInstance() {
        return tinkerManager;
    }

    private void setTinkerApplicationLike(ApplicationLike applicationLike) {
        this.applicationLike = applicationLike;
        if (applicationLike != null) {
            this.application = applicationLike.getApplication();
        }
    }

    public static ApplicationLike getTinkerApplicationLike() {
        return getInstance().applicationLike;
    }

    public static Application getApplication() {
        return getInstance().application;
    }

    public static void registJavaCrashHandler() {
        if (uncaughtExceptionHandler == null) {
            systemExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            uncaughtExceptionHandler = new TinkerUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
    }

    public static void unregistJavaCrashHandler() {
        if (systemExceptionHandler != null) {
            Thread.setDefaultUncaughtExceptionHandler(systemExceptionHandler);
        }
    }

    public static void setUpgradeRetryEnable(boolean z) {
        UpgradePatchRetry.getInstance(getTinkerApplicationLike().getApplication()).setRetryEnable(z);
    }

    private static void installDefaultTinker(ApplicationLike applicationLike) {
        if (isInstalled) {
            TinkerLog.w(TAG, "install tinker, but has installed, ignore", new Object[0]);
        } else if (applicationLike == null) {
            TinkerLog.e(TAG, "Tinker ApplicationLike is null", new Object[0]);
        } else {
            getInstance().setTinkerApplicationLike(applicationLike);
            registJavaCrashHandler();
            setUpgradeRetryEnable(true);
            tinkerReport = new TinkerReport();
            TinkerLog.setTinkerLogImp(new TinkerLogger());
            if (TinkerInstaller.install(applicationLike, new TinkerLoadReporter(applicationLike.getApplication()), new TinkerPatchReporter(applicationLike.getApplication()), new TinkerPatchListener(applicationLike.getApplication()), TinkerResultService.class, new UpgradePatch()) != null) {
                isInstalled = true;
            }
        }
    }

    public static void installTinker(Object obj) {
        if (obj == null) {
            TinkerLog.e(TAG, "Tinker ApplicationLike is null", new Object[0]);
        } else if (obj instanceof ApplicationLike) {
            installDefaultTinker((ApplicationLike) obj);
        } else {
            TinkerLog.e(TAG, "NOT tinker ApplicationLike object", new Object[0]);
        }
    }

    public static void installTinker(Object obj, Object obj2, Object obj3, Object obj4, TinkerPatchResultListener tinkerPatchResultListener, Object obj5) {
        if (obj2 != null) {
            if (obj2 instanceof LoadReporter) {
                userLoadReporter = (LoadReporter) obj2;
            } else {
                TinkerLog.e(TAG, "NOT LoadReporter object", new Object[0]);
                return;
            }
        }
        if (obj3 != null) {
            if (obj3 instanceof PatchReporter) {
                userPatchReporter = (PatchReporter) obj3;
            } else {
                TinkerLog.e(TAG, "NOT PatchReporter object", new Object[0]);
                return;
            }
        }
        if (obj4 != null) {
            if (obj4 instanceof PatchListener) {
                userPatchListener = (PatchListener) obj4;
            } else {
                TinkerLog.e(TAG, "NOT PatchListener object", new Object[0]);
                return;
            }
        }
        if (tinkerPatchResultListener != null) {
            if (tinkerPatchResultListener instanceof TinkerPatchResultListener) {
                patchResultListener = tinkerPatchResultListener;
            } else {
                TinkerLog.e(TAG, "NOT TinkerPatchResultListener object", new Object[0]);
                return;
            }
        }
        if (obj5 != null) {
            if (obj5 instanceof AbstractPatch) {
                userUpgradePatchProcessor = (AbstractPatch) obj5;
            } else {
                TinkerLog.e(TAG, "NOT AbstractPatch object", new Object[0]);
                return;
            }
        }
        installTinker(obj);
    }

    public static void loadArmLibrary(Context context, String str) {
        TinkerLoadLibrary.loadArmLibrary(context, str);
    }

    public static void loadArmV7Library(Context context, String str) {
        TinkerLoadLibrary.loadArmV7Library(context, str);
    }

    public static void loadLibraryFromTinker(Context context, String str, String str2) {
        TinkerLoadLibrary.loadLibraryFromTinker(context, str, str2);
    }

    public void applyPatch(Context context, String str) {
        if (isInstalled) {
            if (this.tinkerListener != null) {
                this.tinkerListener.onPatchStart();
            }
            TinkerInstaller.onReceiveUpgradePatch(context, str);
            return;
        }
        TinkerLog.w(TAG, "Tinker has not been installed.", new Object[0]);
    }

    public static String getTinkerId() {
        if (Tinker.with(getApplication()).isTinkerLoaded()) {
            HashMap packageConfigs = TinkerApplicationHelper.getPackageConfigs(getTinkerApplicationLike());
            if (packageConfigs != null) {
                return String.valueOf(packageConfigs.get(ShareConstants.TINKER_ID)).replace("tinker_id_", "");
            }
            return "";
        }
        Object manifestTinkerID = ShareTinkerInternals.getManifestTinkerID(getApplication());
        if (TextUtils.isEmpty(manifestTinkerID)) {
            return "";
        }
        return manifestTinkerID.replace("tinker_id_", "");
    }

    public static String getNewTinkerId() {
        HashMap packageConfigs = TinkerApplicationHelper.getPackageConfigs(getTinkerApplicationLike());
        if (packageConfigs != null) {
            return String.valueOf(packageConfigs.get(ShareConstants.NEW_TINKER_ID)).replace("tinker_id_", "");
        }
        return "";
    }

    public void cleanPatch(boolean z) {
        onPatchRollback(z);
    }

    public static boolean isTinkerManagerInstalled() {
        return isInstalled;
    }

    public void setTinkerListener(TinkerListener tinkerListener) {
        this.tinkerListener = tinkerListener;
    }

    public TinkerListener getTinkerListener() {
        return this.tinkerListener;
    }

    public void setTinkerReport(Reporter reporter) {
        if (tinkerReport != null) {
            tinkerReport.setReporter(reporter);
        }
    }

    public void onDownloadSuccess(String str, boolean z) {
        try {
            TinkerLog.d(TAG, "onDownloadSuccess.", new Object[0]);
            if (this.tinkerListener != null) {
                this.tinkerListener.onDownloadSuccess(str);
            }
            applyPatch(str, z);
        } catch (Exception e) {
            TinkerLog.e(TAG, "apply patch failed", new Object[0]);
        }
    }

    public void applyPatch(String str, boolean z) {
        try {
            File file = new File(this.applicationLike.getApplication().getDir("dex", 0).getAbsolutePath(), PATCH_NAME);
            File file2 = null;
            if (checkNewPatch(str)) {
                TinkerLog.d(TAG, "has new patch.", new Object[0]);
                file2 = new File(str);
                TinkerUtils.copy(file2, file);
            }
            if (!file.exists()) {
                TinkerLog.d(TAG, "patch not exist, just return.", new Object[0]);
            } else if (file2 != null && z) {
                TinkerLog.d(TAG, "starting patch.", new Object[0]);
                applyPatch(this.applicationLike.getApplication(), file2.getAbsolutePath());
            }
        } catch (Exception e) {
            TinkerLog.e(TAG, e.getMessage(), new Object[0]);
        }
    }

    public boolean checkNewPatch(String str) {
        File file;
        boolean z;
        boolean z2 = true;
        TinkerLog.d(TAG, "check if has new patch.", new Object[0]);
        apkOriginalBuildNum = getTinkerId();
        patchCurBuildNum = getNewTinkerId();
        if (TextUtils.isEmpty(str)) {
            file = null;
            z = false;
        } else {
            File file2 = new File(str);
            if (file2.exists()) {
                file = file2;
                z = true;
            } else {
                file = file2;
                z = false;
            }
        }
        if (z) {
            byte[] readJarEntry = TinkerUtils.readJarEntry(file, MF_FILE);
            if (readJarEntry == null) {
                return false;
            }
            InputStream byteArrayInputStream = new ByteArrayInputStream(readJarEntry);
            try {
                Properties properties = new Properties();
                properties.load(byteArrayInputStream);
                if (properties.getProperty("From") == null || properties.getProperty("To") == null) {
                    TinkerLog.e(TAG, "From/To is null", new Object[0]);
                    return false;
                } else if (apkOriginalBuildNum == null) {
                    TinkerLog.e(TAG, "patchCurBuildNum is null", new Object[0]);
                    return false;
                } else if (apkOriginalBuildNum.equalsIgnoreCase(properties.getProperty("From"))) {
                    patchCurBuildNum = properties.getProperty("To");
                } else {
                    TinkerLog.e(TAG, "orign buildno invalid", new Object[0]);
                    z2 = false;
                }
            } catch (Exception e) {
                TinkerLog.e(TAG, "get properties failed", new Object[0]);
                z2 = false;
            }
        } else {
            z2 = z;
        }
        return z2;
    }

    public File getPatchDirectory(Context context) {
        return SharePatchFileUtil.getPatchDirectory(context);
    }

    public void onDownloadFailure(String str) {
        if (this.tinkerListener != null) {
            this.tinkerListener.onDownloadFailure(str);
        }
    }

    public void onApplySuccess(String str) {
        if (this.tinkerListener != null) {
            this.tinkerListener.onApplySuccess(str);
        }
    }

    public void onApplyFailure(String str) {
        if (this.tinkerListener != null) {
            this.tinkerListener.onApplyFailure(str);
        }
    }

    public void onPatchRollback(boolean z) {
        if (Tinker.with(getApplication()).isTinkerLoaded()) {
            if (z) {
                TinkerLog.i(TAG, "delete patch now", new Object[0]);
                TinkerUtils.rollbackPatch(getApplication());
            } else {
                TinkerLog.i(TAG, "tinker wait screen to restart process", new Object[0]);
                ScreenState screenState = new ScreenState(getApplication(), new a(this) {
                    final /* synthetic */ TinkerManager a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        TinkerUtils.rollbackPatch(TinkerManager.getApplication());
                    }
                });
            }
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ TinkerManager a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.tinkerListener != null) {
                        this.a.tinkerListener.onPatchRollback();
                    }
                }
            });
            return;
        }
        TinkerLog.w("Tinker.PatchRequestCallback", "TinkerPatchRequestCallback: onPatchRollback, tinker is not loaded, just return", new Object[0]);
    }
}
