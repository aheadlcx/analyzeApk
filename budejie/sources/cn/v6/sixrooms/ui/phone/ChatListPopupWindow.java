package cn.v6.sixrooms.ui.phone;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.RoomChatListAdapter;
import cn.v6.sixrooms.adapter.RoomChatListBaseAdapter;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.engine.RefreshChatListEngine;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.widgets.phone.PullToRefreshView;
import java.util.ArrayList;
import java.util.List;

public class ChatListPopupWindow {
    private List<UserInfoBean> a = new ArrayList();
    private PopupWindow b;
    private ListView c;
    private RoomChatListBaseAdapter d;
    private BaseRoomActivity e;
    private PullToRefreshView f;
    private RefreshChatListEngine g;
    private PopupWindowControlListener h;
    private WrapRoomInfo i;
    private View j;
    private View k;

    public interface PopupWindowControlListener {
        void error(int i);

        void onDismiss();

        void onItemClick(AdapterView<?> adapterView, View view, int i, long j);

        void onShow();

        List<UserInfoBean> reviseData(WrapUserInfo wrapUserInfo);
    }

    public ChatListPopupWindow(Context context, int i, int i2, boolean z, WrapRoomInfo wrapRoomInfo, PopupWindowControlListener popupWindowControlListener) {
        this.e = (BaseRoomActivity) context;
        this.h = popupWindowControlListener;
        this.i = wrapRoomInfo;
        this.j = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.phone_room_chat_list_test, null);
        this.k = this.j.findViewById(R.id.triangle);
        this.f = (PullToRefreshView) this.j.findViewById(R.id.pullRefresh);
        this.b = new PopupWindow(this.j, i, i2, z);
        this.b.setInputMethodMode(2);
        this.b.setBackgroundDrawable(new ColorDrawable(0));
        this.c = (ListView) this.j.findViewById(R.id.lv_chat_list_pop);
        this.c.setOnItemClickListener(new t(this));
        this.b.setOnDismissListener(new u(this));
        this.f.setOnHeaderRefreshListener(new v(this));
        this.f.setOnFooterRefreshListener(new w(this));
    }

    public void setIndicatorViewState(int i) {
        if (this.k != null) {
            this.k.setVisibility(i);
        }
    }

    public void setIndicatorViewMargin(int i) {
        ((LayoutParams) this.k.getLayoutParams()).leftMargin = i;
    }

    public void setChatListDataAndRefreshAdapter(List<UserInfoBean> list, boolean z, boolean z2, boolean z3, boolean z4) {
        if (list != null) {
            setRefreshState(z, z2, z3, z4);
            this.a = list;
            if (this.d == null) {
                this.d = new RoomChatListAdapter(this.a, this.e);
                this.c.setAdapter(this.d);
                return;
            }
            this.d.setData(list);
            this.d.notifyDataSetChanged();
        }
    }

    public void setChatListAdapter(RoomChatListBaseAdapter roomChatListBaseAdapter) {
        this.d = roomChatListBaseAdapter;
        this.a = roomChatListBaseAdapter.getData();
        this.c.setAdapter(this.d);
    }

    public void setRefreshState(boolean z, boolean z2, boolean z3, boolean z4) {
        if (z) {
            this.f.onHeaderRefreshComplete();
        }
        if (z2) {
            this.f.onFooterRefreshComplete();
        }
        this.f.isBanPullUpRefresh(z4);
        this.f.isBanPullDownRefresh(z3);
        if (z3) {
            this.f.onHeaderRefreshComplete();
        }
        if (z4) {
            this.f.onFooterRefreshComplete();
        }
    }

    public List<UserInfoBean> getChatListData() {
        return this.a;
    }

    public void notifyDataSetChanged() {
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }

    public RefreshChatListEngine getChatListEngine() {
        return this.g;
    }

    public boolean isShowing() {
        return this.b.isShowing();
    }

    public void dismiss() {
        if (this.b != null) {
            this.b.dismiss();
        }
    }

    public void showAsDropDown(View view, int i, int i2) {
        if (this.b != null) {
            if (this.h != null) {
                this.h.onShow();
            }
            this.b.showAsDropDown(view, i, i2);
        }
    }

    public void refreshChatList(String str, String str2) {
        if (this.g != null) {
            this.g.getRoomList(str, str2);
        }
    }

    public int getHeight() {
        if (this.b != null) {
            return this.b.getHeight();
        }
        return 0;
    }

    public int getWidth() {
        if (this.b != null) {
            return this.b.getWidth();
        }
        return 0;
    }

    public void setContentView(View view) {
        if (this.b != null) {
            this.b.setContentView(view);
        }
    }

    public View getChatListView() {
        return this.c;
    }

    public View getChatContentView() {
        return this.j;
    }

    public void setHeight(int i) {
        if (this.b != null) {
            this.b.setHeight(i);
        }
    }

    public void setBackground(int i) {
        if (this.f != null) {
            this.f.setBackgroundResource(i);
        }
        if (this.k != null) {
            this.k.setVisibility(8);
        }
    }
}
