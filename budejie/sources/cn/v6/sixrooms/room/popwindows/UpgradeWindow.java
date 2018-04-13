package cn.v6.sixrooms.room.popwindows;

import android.content.Context;
import android.widget.PopupWindow;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.room.engine.RetransmitEngine;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.ToastUtils;

public abstract class UpgradeWindow extends PopupWindow {
    private RetransmitEngine a = new RetransmitEngine(new g(this));

    public abstract void show(RoomUpgradeMsg roomUpgradeMsg);

    public UpgradeWindow(Context context) {
        super(context);
        setWidth(-2);
        setHeight(-2);
    }

    public void deliver(RoomUpgradeMsg roomUpgradeMsg) {
        if (roomUpgradeMsg != null) {
            UserBean loginUserBean = LoginUtils.getLoginUserBean();
            if (loginUserBean == null) {
                ToastUtils.showToast(V6Coop.getInstance().getContext().getString(R.string.tip_this_function_need_login));
                return;
            }
            String encpass = SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext());
            this.a.retransmitBlog(roomUpgradeMsg.getMsgid(), V6Coop.getInstance().getContext().getString(R.string.congratulation_upgrade, new Object[]{roomUpgradeMsg.getName()}), "0", "0", loginUserBean.getId(), encpass);
        }
    }
}
