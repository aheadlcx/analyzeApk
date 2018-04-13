package cn.v6.sixrooms.ui.phone.input;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow.PopupWindowControlListener;
import java.util.List;

final class n implements PopupWindowControlListener {
    final /* synthetic */ PrivateInputDialog a;

    n(PrivateInputDialog privateInputDialog) {
        this.a = privateInputDialog;
    }

    public final void onShow() {
        Drawable drawable = this.a.a.getResources().getDrawable(R.drawable.choose_gift_select_number_pop_up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.a.f.setCompoundDrawables(null, null, drawable, null);
    }

    public final void onDismiss() {
        Drawable drawable = this.a.a.getResources().getDrawable(R.drawable.choose_gift_select_number_pop_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.a.f.setCompoundDrawables(null, null, drawable, null);
    }

    public final List<UserInfoBean> reviseData(WrapUserInfo wrapUserInfo) {
        List<UserInfoBean> chatListData = this.a.c.getChatListData();
        chatListData.clear();
        chatListData.addAll(wrapUserInfo.getAllList());
        if (chatListData.size() > 0) {
            chatListData.remove(chatListData.size() - 1);
        }
        return chatListData;
    }

    public final void error(int i) {
        this.a.mActivity.showErrorToast(i);
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        List chatListData = this.a.c.getChatListData();
        this.a.mActivity.currentUserInfoBean = (UserInfoBean) chatListData.get(i);
        this.a.d.setmSelectUserInfoBean(this.a.mActivity.currentUserInfoBean);
        if (!"1000000000".equals(this.a.mActivity.currentUserInfoBean.getUid())) {
            this.a.setInputEditHint("悄悄对" + this.a.mActivity.currentUserInfoBean.getUname() + "说");
            this.a.f.setText(this.a.mActivity.currentUserInfoBean.getUname());
            this.a.setContactImg(this.a.mActivity.currentUserInfoBean.getUserpic());
            this.a.updateContactHeader();
        }
    }
}
