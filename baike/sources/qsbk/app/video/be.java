package qsbk.app.video;

import java.io.File;
import java.lang.ref.WeakReference;
import qsbk.app.QsbkApp;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.FileUtils.CallBack;
import qsbk.app.utils.ToastAndDialog;

final class be implements CallBack {
    final /* synthetic */ File a;
    final /* synthetic */ WeakReference b;

    be(File file, WeakReference weakReference) {
        this.a = file;
        this.b = weakReference;
    }

    public void onResult(boolean z) {
        if (z) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "保存成功，保存在SD卡/qsbk/video/下。").show();
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "保存失败").show();
        }
        FileUtils.notifyFileChanged(QsbkApp.getInstance(), this.a);
        CallBack callBack = (CallBack) this.b.get();
        if (callBack != null) {
            callBack.onResult(z);
        }
    }
}
