package qsbk.app.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.OtherQiushiAdapter;
import qsbk.app.model.UserInfo;

public class OthersQiuShiFragment extends PureArticleListFragment {
    private UserInfo Q;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.u = String.format(Constants.ONES_ARTICLES, new Object[]{getArguments().getString("uid")});
        this.Q = new UserInfo(getArguments().getString("user_info"));
        this.v = "ones_articles" + getArguments().getString("uid");
        this.w = false;
    }

    protected boolean y() {
        return true;
    }

    protected BaseImageAdapter b() {
        boolean z = true;
        if (QsbkApp.currentUser != null && TextUtils.equals(QsbkApp.currentUser.userId, this.Q.userId)) {
            z = false;
        }
        return new OtherQiushiAdapter(this.B, this.m, this.j, getVotePoint(), this.v, z);
    }

    protected boolean u() {
        return false;
    }

    protected void d() {
        this.l.refresh();
    }
}
