package qsbk.app.live.share;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import qsbk.app.core.utils.ToastUtil;

class h implements IUiListener {
    final /* synthetic */ LiveShareActivity a;

    h(LiveShareActivity liveShareActivity) {
        this.a = liveShareActivity;
    }

    public void onCancel() {
        ToastUtil.Short("取消分享");
    }

    public void onComplete(Object obj) {
        this.a.p();
    }

    public void onError(UiError uiError) {
        ToastUtil.Short("分享失败");
    }
}
