package cn.v6.sixrooms.room.presenter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow.PopupWindowControlListener;
import java.util.List;

final class j implements PopupWindowControlListener {
    final /* synthetic */ PrivateChatPresenter a;

    j(PrivateChatPresenter privateChatPresenter) {
        this.a = privateChatPresenter;
    }

    public final void onShow() {
        Drawable drawable = this.a.a.getResources().getDrawable(R.drawable.choose_gift_select_number_pop_up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (this.a.b != null) {
            this.a.b.setCompoundDrawables(drawable);
        }
    }

    public final void onDismiss() {
        if (this.a.b != null) {
            Drawable drawable = this.a.a.getResources().getDrawable(R.drawable.choose_gift_select_number_pop_down);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.a.b.setCompoundDrawables(drawable);
        }
    }

    public final List<UserInfoBean> reviseData(WrapUserInfo wrapUserInfo) {
        List<UserInfoBean> chatListData = this.a.i.getChatListData();
        chatListData.clear();
        chatListData.addAll(wrapUserInfo.getAllList());
        if (chatListData.size() > 0) {
            chatListData.remove(chatListData.size() - 1);
        }
        return chatListData;
    }

    public final void error(int i) {
        this.a.a.showErrorToast(i);
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.h = (UserInfoBean) this.a.i.getChatListData().get(i);
        if (!"1000000000".equals(this.a.h.getUid())) {
            if (this.a.c != null) {
                this.a.c.showInputDialog(this.a.h);
            }
            if (this.a.b != null) {
                this.a.b.setClickInputVisibility(0);
            }
        }
    }
}
