package com.ak.android.engine.download;

import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public final class a implements DynamicObject {
    private ApkListener a;

    public a(ApkListener apkListener) {
        this.a = apkListener;
    }

    public final Object invoke(int i, Object... objArr) {
        if (this.a != null) {
            switch (i) {
                case d.V /*57001*/:
                    this.a.onApkDownloadStart((String) objArr[0]);
                    break;
                case d.W /*57002*/:
                    this.a.onApkDownloadProgress((String) objArr[0], ((Integer) objArr[1]).intValue());
                    break;
                case d.X /*57003*/:
                    this.a.onApkDownloadCompleted((String) objArr[0]);
                    break;
                case d.Y /*57004*/:
                    this.a.onApkDownloadFailed((String) objArr[0]);
                    break;
                case d.Z /*57005*/:
                    this.a.onApkDownloadCanceled((String) objArr[0]);
                    break;
                case d.aa /*57006*/:
                    this.a.onApkDownloadPaused((String) objArr[0]);
                    break;
                case d.ab /*57007*/:
                    this.a.onApkDownloadContinued((String) objArr[0]);
                    break;
                case d.ac /*57008*/:
                    this.a.onApkInstallCompleted((String) objArr[0], (String) objArr[1]);
                    break;
            }
        }
        return null;
    }
}
