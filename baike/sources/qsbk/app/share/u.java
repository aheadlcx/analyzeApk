package qsbk.app.share;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import qsbk.app.utils.LogUtil;

final class u implements IUiListener {
    u() {
    }

    public void onError(UiError uiError) {
        LogUtil.d(uiError.errorMessage);
    }

    public void onComplete(Object obj) {
    }

    public void onCancel() {
    }
}
