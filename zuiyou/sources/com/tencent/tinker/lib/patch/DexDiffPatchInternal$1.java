package com.tencent.tinker.lib.patch;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerDexOptimizer.ResultCallback;
import java.io.File;
import java.util.List;

class DexDiffPatchInternal$1 implements ResultCallback {
    long a;
    final /* synthetic */ List b;
    final /* synthetic */ Throwable[] c;

    DexDiffPatchInternal$1(List list, Throwable[] thArr) {
        this.b = list;
        this.c = thArr;
    }

    public void onStart(File file, File file2) {
        this.a = System.currentTimeMillis();
        TinkerLog.i("Tinker.DexDiffPatchInternal", "start to parallel optimize dex %s, size: %d", new Object[]{file.getPath(), Long.valueOf(file.length())});
    }

    public void onSuccess(File file, File file2, File file3) {
        TinkerLog.i("Tinker.DexDiffPatchInternal", "success to parallel optimize dex %s, opt file:%s, opt file size: %d, use time %d", new Object[]{file.getPath(), file3.getPath(), Long.valueOf(file3.length()), Long.valueOf(System.currentTimeMillis() - this.a)});
    }

    public void onFailed(File file, File file2, Throwable th) {
        TinkerLog.i("Tinker.DexDiffPatchInternal", "fail to parallel optimize dex %s use time %d", new Object[]{file.getPath(), Long.valueOf(System.currentTimeMillis() - this.a)});
        this.b.add(file);
        this.c[0] = th;
    }
}
