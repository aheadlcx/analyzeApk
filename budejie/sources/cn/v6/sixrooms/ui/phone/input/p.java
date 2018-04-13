package cn.v6.sixrooms.ui.phone.input;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow.PopupWindowControlListener;
import java.util.List;

final class p implements PopupWindowControlListener {
    final /* synthetic */ RoomFullInputDialog a;

    p(RoomFullInputDialog roomFullInputDialog) {
        this.a = roomFullInputDialog;
    }

    public final void onShow() {
        Drawable drawable = V6Coop.getInstance().getContext().getResources().getDrawable(R.drawable.choose_gift_select_number_pop_up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.a.d.setCompoundDrawables(null, null, drawable, null);
    }

    public final void onDismiss() {
        this.a.g = false;
        Drawable drawable = V6Coop.getInstance().getContext().getResources().getDrawable(R.drawable.choose_gift_select_number_pop_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.a.d.setCompoundDrawables(null, null, drawable, null);
    }

    public final List<UserInfoBean> reviseData(WrapUserInfo wrapUserInfo) {
        List<UserInfoBean> chatListData = this.a.e.getChatListData();
        chatListData.clear();
        chatListData.addAll(wrapUserInfo.getAllList());
        if (chatListData.size() > 0) {
            chatListData.remove(chatListData.size() - 1);
        }
        if (!this.a.mActivity.isChatQuietly) {
            chatListData.add(0, this.a.mActivity.tempUserInfoBean);
        }
        return chatListData;
    }

    public final void error(int i) {
        this.a.mActivity.showErrorToast(i);
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        List chatListData = this.a.e.getChatListData();
        this.a.mActivity.currentUserInfoBean = (UserInfoBean) chatListData.get(i);
        this.a.d.setText(this.a.mActivity.currentUserInfoBean.getUname());
        if (!this.a.a && !this.a.b) {
            this.a.setInputEditHint("对" + this.a.mActivity.currentUserInfoBean.getUname() + "说");
        }
    }
}
