package com.tencent.tinker.lib.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.SystemClock;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import java.io.File;

public class TinkerPatchService extends IntentService {
    private static final String PATCH_PATH_EXTRA = "patch_path_extra";
    private static final String RESULT_CLASS_EXTRA = "patch_result_class";
    private static final String TAG = "Tinker.TinkerPatchService";
    private static int notificationId = ShareConstants.TINKER_PATCH_SERVICE_NOTIFICATION;
    private static Class<? extends AbstractResultService> resultServiceClass = null;
    private static AbstractPatch upgradePatchProcessor = null;

    public static class InnerService extends Service {
        public void onCreate() {
            super.onCreate();
            try {
                startForeground(TinkerPatchService.notificationId, new Notification());
            } catch (Throwable th) {
                TinkerLog.e(TinkerPatchService.TAG, "InnerService set service for push exception:%s.", th);
            }
            stopSelf();
        }

        public void onDestroy() {
            stopForeground(true);
            super.onDestroy();
        }

        public IBinder onBind(Intent intent) {
            return null;
        }
    }

    public TinkerPatchService() {
        super(TinkerPatchService.class.getSimpleName());
    }

    public static void runPatchService(Context context, String str) {
        try {
            Intent intent = new Intent(context, TinkerPatchService.class);
            intent.putExtra(PATCH_PATH_EXTRA, str);
            intent.putExtra(RESULT_CLASS_EXTRA, resultServiceClass.getName());
            context.startService(intent);
        } catch (Throwable th) {
            TinkerLog.e(TAG, "start patch service fail, exception:" + th, new Object[0]);
        }
    }

    public static void setPatchProcessor(AbstractPatch abstractPatch, Class<? extends AbstractResultService> cls) {
        upgradePatchProcessor = abstractPatch;
        resultServiceClass = cls;
        try {
            Class.forName(cls.getName());
        } catch (ClassNotFoundException e) {
        }
    }

    public static String getPatchPathExtra(Intent intent) {
        if (intent != null) {
            return ShareIntentUtil.getStringExtra(intent, PATCH_PATH_EXTRA);
        }
        throw new TinkerRuntimeException("getPatchPathExtra, but intent is null");
    }

    public static String getPatchResultExtra(Intent intent) {
        if (intent != null) {
            return ShareIntentUtil.getStringExtra(intent, RESULT_CLASS_EXTRA);
        }
        throw new TinkerRuntimeException("getPatchResultExtra, but intent is null");
    }

    public static void setTinkerNotificationId(int i) {
        notificationId = i;
    }

    protected void onHandleIntent(Intent intent) {
        boolean z = false;
        Context applicationContext = getApplicationContext();
        Tinker with = Tinker.with(applicationContext);
        with.getPatchReporter().onPatchServiceStart(intent);
        if (intent == null) {
            TinkerLog.e(TAG, "TinkerPatchService received a null intent, ignoring.", new Object[z]);
            return;
        }
        String patchPathExtra = getPatchPathExtra(intent);
        if (patchPathExtra == null) {
            TinkerLog.e(TAG, "TinkerPatchService can't get the path extra, ignoring.", new Object[z]);
            return;
        }
        File file = new File(patchPathExtra);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Throwable th = null;
        increasingPriority();
        PatchResult patchResult = new PatchResult();
        try {
            if (upgradePatchProcessor == null) {
                throw new TinkerRuntimeException("upgradePatchProcessor is null.");
            }
            z = upgradePatchProcessor.tryPatch(applicationContext, patchPathExtra, patchResult);
            elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
            with.getPatchReporter().onPatchResult(file, z, elapsedRealtime);
            patchResult.isSuccess = z;
            patchResult.rawPatchFilePath = patchPathExtra;
            patchResult.costTime = elapsedRealtime;
            patchResult.e = th;
            AbstractResultService.runResultService(applicationContext, patchResult, getPatchResultExtra(intent));
        } catch (Throwable th2) {
            th = th2;
            with.getPatchReporter().onPatchException(file, th);
        }
    }

    private void increasingPriority() {
        TinkerLog.i(TAG, "try to increase patch process priority", new Object[0]);
        try {
            Notification notification = new Notification();
            if (VERSION.SDK_INT < 18) {
                startForeground(notificationId, notification);
                return;
            }
            startForeground(notificationId, notification);
            startService(new Intent(this, InnerService.class));
        } catch (Throwable th) {
            TinkerLog.i(TAG, "try to increase patch process priority error:" + th, new Object[0]);
        }
    }
}
