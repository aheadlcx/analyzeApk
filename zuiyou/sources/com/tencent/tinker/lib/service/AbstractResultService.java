package com.tencent.tinker.lib.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;

public abstract class AbstractResultService extends IntentService {
    private static final String RESULT_EXTRA = "result_extra";
    private static final String TAG = "Tinker.AbstractResultService";

    public abstract void onPatchResult(PatchResult patchResult);

    public AbstractResultService() {
        super(AbstractResultService.class.getSimpleName());
    }

    public static void runResultService(Context context, PatchResult patchResult, String str) {
        if (str == null) {
            throw new TinkerRuntimeException("resultServiceClass is null.");
        }
        try {
            Intent intent = new Intent();
            intent.setClassName(context, str);
            intent.putExtra(RESULT_EXTRA, patchResult);
            context.startService(intent);
        } catch (Throwable th) {
            TinkerLog.e(TAG, "run result service fail, exception:" + th, new Object[0]);
        }
    }

    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            TinkerLog.e(TAG, "AbstractResultService received a null intent, ignoring.", new Object[0]);
        } else {
            onPatchResult((PatchResult) ShareIntentUtil.getSerializableExtra(intent, RESULT_EXTRA));
        }
    }
}
