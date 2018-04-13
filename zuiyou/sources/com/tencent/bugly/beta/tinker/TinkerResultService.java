package com.tencent.bugly.beta.tinker;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.tencent.bugly.beta.tinker.TinkerUtils.ScreenState;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import java.io.File;

public class TinkerResultService extends DefaultTinkerResultService {
    private static final String TAG = "Tinker.TinkerResultService";

    public void onPatchResult(final PatchResult patchResult) {
        if (TinkerManager.patchResultListener != null) {
            TinkerManager.patchResultListener.onPatchResult(patchResult);
        }
        if (patchResult == null) {
            TinkerLog.e(TAG, "TinkerResultService received null result!!!!", new Object[0]);
            return;
        }
        TinkerLog.i(TAG, "TinkerResultService receive result: %s", patchResult.toString());
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ TinkerResultService b;

            public void run() {
                if (patchResult.isSuccess) {
                    TinkerManager.getInstance().onApplySuccess(patchResult.toString());
                } else {
                    TinkerManager.getInstance().onApplyFailure(patchResult.toString());
                }
            }
        });
        if (patchResult.isSuccess) {
            deleteRawPatchFile(new File(patchResult.rawPatchFilePath));
            if (!checkIfNeedKill(patchResult)) {
                TinkerLog.i(TAG, "I have already install the newly patch version!", new Object[0]);
            } else if (TinkerUtils.isBackground()) {
                TinkerLog.i(TAG, "it is in background, just restart process", new Object[0]);
                restartProcess();
            } else {
                TinkerLog.i(TAG, "tinker wait screen to restart process", new Object[0]);
                ScreenState screenState = new ScreenState(getApplicationContext(), new a(this) {
                    final /* synthetic */ TinkerResultService a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.restartProcess();
                    }
                });
            }
        }
    }

    private void restartProcess() {
        TinkerLog.i(TAG, "app is background now, i can kill quietly", new Object[0]);
        Process.killProcess(Process.myPid());
    }
}
