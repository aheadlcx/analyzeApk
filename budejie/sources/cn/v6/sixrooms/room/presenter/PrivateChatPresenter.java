package cn.v6.sixrooms.room.presenter;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.mvp.interfaces.PrivateChatrable;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.adapter.PrivateChatAdapter;
import cn.v6.sixrooms.room.chat.PrivateChatControlable;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow;
import cn.v6.sixrooms.ui.phone.input.BaseRoomInputDialog.OnKeyBoardLister;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.widgets.phone.NoShadowListView;
import java.util.ArrayList;
import java.util.List;

public class PrivateChatPresenter implements PrivateChatControlable, OnKeyBoardLister {
    public static final int CHAT_DATA_MAX_SIZE = 200;
    public static final int MSG_ADD_DATA = 256;
    public static final int MSG_ADD_DATA_LIST = 257;
    public static final int MSG_DEL_ALL_DATA = 258;
    public static final int MSG_SET_SELECTION_BOTTOM = 259;
    private BaseRoomActivity a;
    private PrivateChatViewProxy b;
    private PrivateChatrable c;
    private List<RoommsgBean> d = new ArrayList();
    private boolean e;
    private int f;
    private List<UserInfoBean> g;
    private UserInfoBean h;
    private ChatListPopupWindow i;
    private Handler j = new i(this);

    public static class PrivateChatViewProxy implements OnClickListener, OnItemClickListener, PrivateChatrable {
        private BaseRoomActivity a;
        private View b;
        private PrivateChatPresenter c;
        private NoShadowListView d;
        private RelativeLayout e;
        private PrivateChatAdapter f;
        private List<RoommsgBean> g;
        private View h;
        private int i;
        private int j;
        private View k;
        private RelativeLayout l;
        private TextView m;
        private TextView n;

        public PrivateChatViewProxy(BaseRoomActivity baseRoomActivity, PrivateChatPresenter privateChatPresenter, List<RoommsgBean> list) {
            this.a = baseRoomActivity;
            this.c = privateChatPresenter;
            this.g = list;
        }

        public void setChatUserName() {
            if (this.a.currentUserInfoBean == null || RoomActivity.VIDEOTYPE_UNKNOWN.equals(this.a.currentUserInfoBean.getUid())) {
                this.m.setText("请选择聊天对象");
                return;
            }
            this.m.setText(this.a.currentUserInfoBean.getUname());
            this.m.setText(this.a.currentUserInfoBean.getUname());
        }

        public void setChatNullName() {
            this.m.setText("请选择聊天对象");
        }

        public void setClickInputVisibility(int i) {
            this.n.setVisibility(i);
        }

        public void setCompoundDrawables(Drawable drawable) {
            this.m.setCompoundDrawables(null, null, drawable, null);
        }

        public void initBackground() {
            if (this.i == 0 || this.i == 1) {
                setListBackgroundColor(this.a.getResources().getColor(R.color.transparent));
                setBackgroundColor(this.a.getResources().getColor(R.color.transparent));
                setListBackgroundResource(R.drawable.room_chat_common_backgroud);
                return;
            }
            setListBackgroundResource(0);
            setListBackgroundColor(this.a.getResources().getColor(R.color.room_private_chat_view_bg));
            setBackgroundColor(this.a.getResources().getColor(R.color.transparent));
        }

        public void initListViewHeight() {
            if (this.j != 0) {
                return;
            }
            if (this.a.getResources().getConfiguration().orientation == 2) {
                this.j = DensityUtil.dip2px(180.0f);
            } else {
                this.j = DensityUtil.dip2px(232.0f);
            }
        }

        public void setChatContentHeight(int i) {
            this.j = i;
            if (this.k != null) {
                LayoutParams layoutParams = (LayoutParams) this.k.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.height = i;
                }
            }
        }

        public void setMarginBottom(int i) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.setMargins(0, 0, 0, i);
            this.b.setLayoutParams(layoutParams);
        }

        public void setListBackgroundColor(int i) {
            if (this.k != null) {
                this.k.setBackgroundColor(i);
            }
        }

        public void setListBackgroundResource(int i) {
            if (this.k != null) {
                this.k.setBackgroundResource(i);
            }
        }

        public void setBackgroundColor(int i) {
            if (this.b != null) {
                this.b.setBackgroundColor(i);
            }
        }

        public Rect getPullDownViewRect() {
            if (this.h == null) {
                return null;
            }
            int[] iArr = new int[2];
            this.h.getLocationOnScreen(iArr);
            return new Rect(iArr[0], iArr[1], iArr[0] + this.h.getWidth(), iArr[1] + this.h.getHeight());
        }

        public void setRoomType(int i) {
            this.i = i;
        }

        public void setPrivateChatViewShow() {
            if (this.b == null) {
                this.b = this.a.findViewById(R.id.ll_private_chat);
                setMarginBottom(0);
                this.b.setOnClickListener(this);
                this.k = this.b.findViewById(R.id.iv_chat_layout);
                this.d = (NoShadowListView) this.b.findViewById(R.id.lv_chat);
                this.m = (TextView) this.b.findViewById(R.id.tv_current_chat_name);
                this.n = (TextView) this.b.findViewById(R.id.tv_input);
                this.n.setOnClickListener(this);
                initBackground();
                initListViewHeight();
                this.e = (RelativeLayout) this.b.findViewById(R.id.iv_user);
                this.e.setOnClickListener(this);
                this.l = (RelativeLayout) this.b.findViewById(R.id.quietly_chat);
                this.f = new PrivateChatAdapter(this.a);
                this.f.setData(this.g);
                this.d.setAdapter(this.f);
                this.d.setOnItemClickListener(this);
                this.h = this.b.findViewById(R.id.iv_pulldown);
                this.h.setOnClickListener(this);
                setChatContentHeight(this.j);
            }
            if (this.b != null) {
                this.b.setVisibility(0);
            }
        }

        public void setPrivateChatViewHide() {
            if (this.b != null) {
                this.b.setVisibility(8);
            }
            setClickInputVisibility(8);
            this.a.currentUserInfoBean = null;
        }

        public void showInputDialog(UserInfoBean userInfoBean) {
        }

        public void setNewMsgViewShow() {
        }

        public void setNewMsgViewHide() {
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.ll_private_chat || id == R.id.iv_pulldown) {
                if (view.getVisibility() == 0) {
                    this.c.hidePrivateChatView();
                }
            } else if (id == R.id.iv_user) {
                this.c.clickContacts(view);
            } else if (id == R.id.tv_input) {
                this.c.showPrivateChatView(this.c.getmSelectUserInfoBean());
            }
        }

        public void notifyDataSetChanged() {
            if (this.d != null) {
                int count = this.d.getCount();
                if (!(this.f == null || this.b == null || this.b.getVisibility() != 0)) {
                    this.f.notifyDataSetChanged();
                }
                if (this.d.getLastVisiblePosition() == count - 1) {
                    setSelection(this.d.getCount());
                }
            }
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.c.clickListItem(i);
        }

        public void setBtnPulldownState(boolean z) {
            if (this.h == null) {
                return;
            }
            if (z) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
        }

        public void setContactViewState(int i) {
            if (this.l != null) {
                this.l.setVisibility(i);
            }
            if (i == 0) {
                setChatUserName();
            }
        }

        public void setSelection(int i) {
            this.d.setSelection(i);
        }
    }

    public List<UserInfoBean> getmUserInfoBeans() {
        return this.g;
    }

    public void setmUserInfoBeans(List<UserInfoBean> list) {
        this.g = list;
    }

    public UserInfoBean getmSelectUserInfoBean() {
        return this.h;
    }

    public void setmSelectUserInfoBean(UserInfoBean userInfoBean) {
        this.h = userInfoBean;
    }

    public PrivateChatPresenter(BaseRoomActivity baseRoomActivity) {
        this.a = baseRoomActivity;
        this.b = new PrivateChatViewProxy(baseRoomActivity, this, this.d);
        this.g = this.a.initChatListData();
    }

    public void setRoomType(int i) {
        this.f = i;
        this.b.setRoomType(this.f);
        this.b.initBackground();
    }

    public void setPrevateChatPresenter(PrivateChatrable privateChatrable) {
        this.c = privateChatrable;
    }

    public void hidePrivateChatView() {
        this.e = false;
        this.b.setPrivateChatViewHide();
        if (this.c != null) {
            this.c.setPrivateChatViewHide();
        }
        this.a.isChatQuietly = false;
    }

    public void clickContacts(View view) {
        if (InroomPresenter.getInstance().getLocalRoomInfo() != null) {
            if (this.i == null && this.i == null) {
                this.i = new ChatListPopupWindow(this.a, DensityUtil.dip2px(175.0f), DensityUtil.dip2px(175.0f), true, InroomPresenter.getInstance().getLocalRoomInfo(), new j(this));
                if (this.g == null) {
                    this.g = this.a.initChatListData();
                }
                this.i.setChatListDataAndRefreshAdapter(this.g, true, true, true, true);
            }
            if (!this.i.isShowing()) {
                this.i.setIndicatorViewMargin(DensityUtil.dip2px(22.0f));
                this.i.showAsDropDown(view, DensityUtil.dip2px(-120.0f), DensityUtil.dip2px(-12.0f));
            }
        }
    }

    public void clickListItem(int i) {
        UserInfoBean userInfoBean = new UserInfoBean();
        RoommsgBean roommsgBean = (RoommsgBean) this.d.get(i);
        if (roommsgBean.getFid().equals(LoginUtils.getLoginUserBean().getId())) {
            userInfoBean.setUid(roommsgBean.getToid());
            userInfoBean.setUname(roommsgBean.getTo());
            userInfoBean.setUrid(roommsgBean.getTorid());
            userInfoBean.setUserpic(roommsgBean.gettPic());
        } else {
            userInfoBean.setUid(roommsgBean.getFid());
            userInfoBean.setUname(roommsgBean.getFrom());
            userInfoBean.setUrid(roommsgBean.getFrid());
            userInfoBean.setUserpic(roommsgBean.getfPic());
        }
        if (this.c != null) {
            this.c.showInputDialog(userInfoBean);
        }
        this.h = userInfoBean;
        if (this.b != null) {
            this.b.setClickInputVisibility(0);
        }
    }

    public void addData(RoommsgBean roommsgBean) {
        if (roommsgBean.getFid() == null || !roommsgBean.getFid().equals(roommsgBean.getToid())) {
            Message obtain = Message.obtain();
            obtain.what = 256;
            obtain.obj = roommsgBean;
            this.j.sendMessage(obtain);
        }
    }

    public void clearData() {
        this.j.sendEmptyMessage(258);
    }

    public void OnKeyBoardChange(boolean z, int i) {
        this.b.setBtnPulldownState(z);
        if (z) {
            this.b.setMarginBottom(i);
            this.b.setContactViewState(8);
            this.b.setListBackgroundColor(this.a.getResources().getColor(R.color.transparent));
            this.b.setBackgroundColor(this.a.getResources().getColor(R.color.room_private_chat_view_bg));
            return;
        }
        this.b.setMarginBottom(0);
        this.b.setContactViewState(0);
        this.b.initBackground();
    }

    public void setSelectionToBottom() {
        this.j.sendEmptyMessage(259);
    }

    public void updateChatList(List<UserInfoBean> list) {
        this.g = list;
        if (this.i != null) {
            boolean z;
            if (list.size() > 51) {
                z = true;
            } else {
                if (this.g != null && this.g.size() > 0 && "1000000000".equals(((UserInfoBean) this.g.get(this.g.size() - 1)).getUid())) {
                    this.g.remove(this.g.size() - 1);
                }
                z = false;
            }
            this.i.setChatListDataAndRefreshAdapter(list, true, true, false, z);
        }
    }

    public void setChatContentHeight(int i) {
        this.b.setChatContentHeight(i);
    }

    public void showPrivateChatView(UserInfoBean userInfoBean) {
        if (LoginUtils.getLoginUserBean() == null) {
            this.a.showLoginDialog();
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.PCHAT);
            return;
        }
        if (!this.e) {
            this.e = true;
            this.b.setRoomType(this.f);
            this.b.setPrivateChatViewShow();
            setChatUserName();
            this.b.notifyDataSetChanged();
            if (this.c != null) {
                this.c.setPrivateChatViewShow();
                this.c.setNewMsgViewHide();
            }
            this.a.isChatQuietly = true;
        }
        if (userInfoBean != null && this.c != null) {
            if (!(this.g == null || this.g.contains(userInfoBean))) {
                this.g.add(userInfoBean);
            }
            this.c.showInputDialog(userInfoBean);
            this.h = userInfoBean;
            if (this.b != null) {
                this.b.setClickInputVisibility(0);
            }
        }
    }

    public boolean isShow() {
        return this.e;
    }

    public Rect getPullDownViewRect() {
        return this.b.getPullDownViewRect();
    }

    public void setChatUserName() {
        if (this.b != null) {
            this.b.setChatNullName();
        }
    }
}
