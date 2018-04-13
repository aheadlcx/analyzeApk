package qsbk.app.live.ui.family;

import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

public class FamilyBlankActivity extends BaseActivity {
    protected int getLayoutId() {
        return R.layout.activity_blank;
    }

    protected void initView() {
    }

    protected void initData() {
        User user = AppUtils.getInstance().getUserInfoProvider().getUser();
        if (!AppUtils.getInstance().getUserInfoProvider().isLogin() || user == null) {
            AppUtils.getInstance().getUserInfoProvider().toLogin(getActivity(), 1001);
            finish();
            return;
        }
        NetRequest.getInstance().get(UrlConstants.FAMILY_CREATE_APPLY, new b(this), "FAMILY_CREATE_APPLY", true);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
