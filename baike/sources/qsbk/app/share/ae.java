package qsbk.app.share;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import qsbk.app.utils.LogUtil;

final class ae implements IUiListener {
    ae() {
    }

    public void onError(UiError uiError) {
        LogUtil.e("onErroMessager " + uiError.errorMessage);
    }

    public void onComplete(Object obj) {
    }

    public void onCancel() {
    }
}
