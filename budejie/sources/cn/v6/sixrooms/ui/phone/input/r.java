package cn.v6.sixrooms.ui.phone.input;

import android.view.View;
import android.widget.AdapterView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow.PopupWindowControlListener;
import java.util.List;

final class r implements PopupWindowControlListener {
    final /* synthetic */ RoomInputDialog a;

    r(RoomInputDialog roomInputDialog) {
        this.a = roomInputDialog;
    }

    public final void onShow() {
        this.a.b.setBackgroundResource(R.drawable.rooms_third_room_triangle_up);
    }

    public final void onDismiss() {
        this.a.g = false;
        this.a.b.setBackgroundResource(R.drawable.rooms_third_room_triangle_down);
    }

    public final List<UserInfoBean> reviseData(WrapUserInfo wrapUserInfo) {
        List<UserInfoBean> chatListData = this.a.a.getChatListData();
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
        List chatListData = this.a.a.getChatListData();
        this.a.mActivity.currentUserInfoBean = (UserInfoBean) chatListData.get(i);
        this.a.c.setText(this.a.mActivity.currentUserInfoBean.getUname());
    }
}
