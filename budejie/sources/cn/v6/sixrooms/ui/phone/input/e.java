package cn.v6.sixrooms.ui.phone.input;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.presenter.PropListPresenter;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.widgets.phone.ExpressionKeyboard.OnPermissionDenyListener;

final class e implements OnPermissionDenyListener {
    final /* synthetic */ BaseRoomInputDialog a;

    e(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void onPermissionInvalid() {
        if (BaseRoomInputDialog.d(this.a) != null) {
            BaseRoomInputDialog.d(this.a).dismiss();
        }
        BaseRoomInputDialog.a(this.a, this.a.mActivity.mDialogUtils.createDiaglog(this.a.mActivity.getString(R.string.keyboard_expression_tip_trying)));
        BaseRoomInputDialog.d(this.a).show();
        UserBean loginUserBean = LoginUtils.getLoginUserBean();
        WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
        if (!(loginUserBean == null || localRoomInfo == null)) {
            PropListPresenter.getInstance().getNetData(loginUserBean.getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), localRoomInfo.getRoominfoBean().getId());
        }
        this.a.dismiss();
    }

    public final void onPermissionDeny(int i) {
        if (i == 1) {
            if (BaseRoomInputDialog.d(this.a) != null) {
                BaseRoomInputDialog.d(this.a).dismiss();
            }
            BaseRoomInputDialog.a(this.a, this.a.mActivity.mDialogUtils.createConfirmDialog(0, this.a.mActivity.getString(R.string.tip_show_tip_title), this.a.mActivity.getString(R.string.tip_not_guard), this.a.mActivity.getString(R.string.tip_not_now), this.a.mActivity.getString(R.string.tip_open_guard), new f(this)));
            this.a.dismiss();
            BaseRoomInputDialog.d(this.a).show();
        } else if (i == 2) {
            if (BaseRoomInputDialog.d(this.a) != null) {
                BaseRoomInputDialog.d(this.a).dismiss();
            }
            BaseRoomInputDialog.a(this.a, this.a.mActivity.mDialogUtils.createConfirmDialog(0, this.a.mActivity.getString(R.string.tip_show_tip_title), this.a.mActivity.getString(R.string.tip_purchase_vip_mgs), this.a.mActivity.getString(R.string.tip_not_buy), this.a.mActivity.getString(R.string.tip_purchase_vip_immediately), new g(this)));
            this.a.dismiss();
            BaseRoomInputDialog.d(this.a).show();
        }
    }
}
