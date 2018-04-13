package com.tencent.bugly.proguard;

import com.tencent.bugly.beta.global.a;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.tinker.TinkerManager.TinkerListener;
import com.tencent.bugly.beta.ui.g;

class r$1 implements TinkerListener {
    final /* synthetic */ e a;

    r$1(e eVar) {
        this.a = eVar;
    }

    public void onDownloadSuccess(String str) {
        if (this.a.V != null) {
            this.a.V.onDownloadSuccess(str);
        }
    }

    public void onDownloadFailure(String str) {
        if (this.a.V != null) {
            this.a.V.onDownloadFailure(str);
        }
    }

    public void onPatchStart() {
        this.a.ab = true;
        a.a("IS_PATCHING", true);
    }

    public void onApplySuccess(String str) {
        this.a.ab = false;
        this.a.N = true;
        a.a("IS_PATCHING", false);
        a.a("PatchResult", true);
        an.a("Tinker patch success, result: " + str, new Object[0]);
        if (this.a.W) {
            g.a(new com.tencent.bugly.beta.ui.e(), true);
        }
        if (this.a.V != null) {
            this.a.V.onApplySuccess(str);
        }
    }

    public void onApplyFailure(String str) {
        this.a.N = false;
        a.a("PatchResult", false);
        a.a("IS_PATCHING", false);
        an.a("Tinker patch failure, result: " + str, new Object[0]);
        if (this.a.V != null) {
            this.a.V.onApplyFailure(str);
        }
    }

    public void onPatchRollback() {
        an.a("patch rollback callback.", new Object[0]);
        if (this.a.V != null) {
            this.a.V.onPatchRollback();
        }
        if (TinkerManager.getInstance().getPatchDirectory(this.a.s) != null && !TinkerManager.getInstance().getPatchDirectory(this.a.s).exists()) {
            a.a("IS_PATCH_ROLL_BACK", false);
        }
    }
}
