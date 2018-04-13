package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.IsFollowEngine.CallBack;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.view.interfaces.FollowViewable;
import java.util.Iterator;

final class e implements CallBack {
    final /* synthetic */ FollowPresenter a;

    e(FollowPresenter followPresenter) {
        this.a = followPresenter;
    }

    public final void result(boolean z) {
        FollowPresenter.a(this.a, z);
        FollowPresenter.b(this.a, z);
        LogUtils.d("FollowPresenter", "FollowPresenter----IsFollowEngine--list--" + FollowPresenter.a(this.a).size());
        Iterator it = FollowPresenter.a(this.a).iterator();
        while (it.hasNext()) {
            ((FollowViewable) it.next()).initFollow(z);
        }
    }

    public final void error(int i) {
        Iterator it = FollowPresenter.a(this.a).iterator();
        while (it.hasNext()) {
            ((FollowViewable) it.next()).updateFollowNetError(FollowPresenter.b(this.a), i);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        Iterator it = FollowPresenter.a(this.a).iterator();
        while (it.hasNext()) {
            ((FollowViewable) it.next()).updateFollowServerError(FollowPresenter.b(this.a), str, str2);
        }
    }
}
