package cn.v6.sixrooms.ui.phone.input;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.LoginUtils;
import java.util.ArrayList;
import java.util.List;

public class RoomFullInputDialog extends BaseRoomInputDialog {
    private boolean a = false;
    private boolean b = false;
    private RelativeLayout c;
    private TextView d;
    private ChatListPopupWindow e;
    private List<UserInfoBean> f = this.mActivity.initChatListData();
    private boolean g = false;
    private Handler h = new o(this);
    private ImageView i;
    private ImageView j;

    public RoomFullInputDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity);
    }

    public boolean setContentView() {
        this.mInputLayoutFactory = new LiveRoomInputLayoutFactory(this.mActivity, RoomInputTheme.FULL_SCREEN_THEME);
        return true;
    }

    public void initView() {
        super.initView();
        this.c = (RelativeLayout) findViewById(R.id.rl_select_chat);
        this.d = (TextView) findViewById(R.id.tv_current_chat_name);
        this.i = (ImageView) findViewById(R.id.big_fly);
        this.j = (ImageView) findViewById(R.id.small_fly);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        if (this.mActivity.isSuperGirlRoom().booleanValue()) {
            findViewById(R.id.bt_fly_msg).setVisibility(8);
            disableGuardExpress();
            disableExpress();
        }
    }

    public void initListener() {
        super.initListener();
        this.c.setOnClickListener(this);
    }

    public void onClick(View view) {
        boolean z = true;
        super.onClick(view);
        int id = view.getId();
        boolean z2;
        if (id == R.id.rl_select_chat) {
            if (LoginUtils.getLoginUserBean() == null) {
                this.mActivity.showLoginDialog();
                return;
            }
            if (this.g) {
                z2 = false;
            } else {
                z2 = true;
            }
            this.g = z2;
            if (InroomPresenter.getInstance().getLocalRoomInfo() != null && this.e == null) {
                this.e = new ChatListPopupWindow(this.mActivity, DensityUtil.dip2px(160.0f), DensityUtil.dip2px(160.0f), true, InroomPresenter.getInstance().getLocalRoomInfo(), new p(this));
                if (this.f == null) {
                    this.f = this.mActivity.initChatListData();
                }
                this.e.setChatListDataAndRefreshAdapter(this.f, true, true, true, true);
            }
            if (this.e != null) {
                Math.abs(this.c.getWidth() - this.e.getWidth());
                if (this.g) {
                    if (this.mActivity.getResources().getConfiguration().orientation == 2) {
                        this.e.setIndicatorViewState(4);
                    } else {
                        this.e.setIndicatorViewState(0);
                        this.e.setIndicatorViewMargin(DensityUtil.dip2px(15.0f));
                    }
                    if (getContext().getResources().getConfiguration().orientation == 2) {
                        this.e.showAsDropDown(this.c, this.c.getWidth() + DensityUtil.dip2px(-60.0f), DensityUtil.dip2px(-10.0f) - (this.e.getHeight() / 2));
                        return;
                    } else {
                        this.e.showAsDropDown(this.c, DensityUtil.dip2px(-18.0f), DensityUtil.dip2px(5.0f));
                        return;
                    }
                }
                this.e.dismiss();
            }
        } else if (id == R.id.big_fly) {
            if (this.a) {
                this.i.setImageResource(R.drawable.sixroom_big_fly_screen_nomal);
                a();
            } else {
                setInputEditHint(this.mActivity.getString(R.string.chat_big_fly_hint));
                this.i.setImageResource(R.drawable.sixroom_big_fly_screen_select);
            }
            if (this.a) {
                z2 = false;
            } else {
                z2 = true;
            }
            this.a = z2;
            if (this.b) {
                if (this.b) {
                    z = false;
                }
                this.b = z;
                this.j.setImageResource(R.drawable.sixroom_small_fly_screen_nomal);
            }
        } else if (id == R.id.small_fly) {
            if (this.b) {
                this.j.setImageResource(R.drawable.sixroom_small_fly_screen_nomal);
                a();
            } else {
                this.j.setImageResource(R.drawable.sixroom_small_fly_screen_select);
                setInputEditHint(this.mActivity.getString(R.string.chat_small_fly_hint));
            }
            if (this.b) {
                z2 = false;
            } else {
                z2 = true;
            }
            this.b = z2;
            if (this.a) {
                if (this.a) {
                    z = false;
                }
                this.a = z;
                this.i.setImageResource(R.drawable.sixroom_big_fly_screen_nomal);
            }
        }
    }

    public void receiveAllChatList(WrapUserInfo wrapUserInfo) {
        Message obtain = Message.obtain();
        if (this.e != null && this.e.isShowing()) {
            List arrayList = new ArrayList();
            arrayList.addAll(wrapUserInfo.getAllList());
            if (!this.mActivity.isChatQuietly) {
                arrayList.add(0, this.mActivity.tempUserInfoBean);
            }
            obtain.obj = arrayList;
            obtain.what = 8;
            this.h.sendMessage(obtain);
        }
    }

    public void receiveChatList(String str) {
        if (this.e != null && this.e.isShowing() && this.mActivity.allChatList != null && this.mActivity.allChatList.size() <= 51) {
            Message obtain = Message.obtain();
            obtain.obj = str;
            obtain.what = 12;
            this.h.sendMessage(obtain);
        }
    }

    public void setChatRule() {
        super.setChatRule();
        if (!(this.mActivity.allChatList == null || this.mActivity.allChatList.contains(this.mActivity.tempUserInfoBean))) {
            this.mActivity.allChatList.add(0, this.mActivity.tempUserInfoBean);
            if (this.e != null) {
                this.e.notifyDataSetChanged();
            }
        }
        a();
    }

    private void a() {
        if (this.mActivity.currentUserInfoBean == null || RoomActivity.VIDEOTYPE_UNKNOWN.equals(this.mActivity.currentUserInfoBean.getUid())) {
            this.d.setText(this.mActivity.getString(R.string.pad_room_chat_to_all_str));
            setInputEditHint("对所有人说");
            return;
        }
        if (!(this.f == null || this.f.contains(this.mActivity.currentUserInfoBean))) {
            this.f.add(this.mActivity.currentUserInfoBean);
        }
        this.d.setText(this.mActivity.currentUserInfoBean.getUname());
        setInputEditHint("对" + this.mActivity.currentUserInfoBean.getUname() + "说");
    }

    protected void onStart() {
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            getWindow().addFlags(1024);
        } else {
            getWindow().addFlags(2048);
        }
        if (this.j != null) {
            this.j.setFocusable(true);
        }
        if (this.i != null) {
            this.j.setFocusable(true);
        }
        super.onStart();
    }

    public boolean sendChat() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.mActivity.showLoginDialog();
            return false;
        } else if (this.a) {
            return sendFlyText();
        } else {
            if (this.b) {
                return sendSmallFlyText();
            }
            if (this.mActivity != null) {
                String filtrationString = filtrationString(this.mInputEditText.getText().toString());
                if (TextUtils.isEmpty(filtrationString)) {
                    this.mActivity.showToast(this.mActivity.getString(R.string.str_chat_empty));
                    return false;
                } else if (!this.mActivity.isCanSpeak) {
                    this.mActivity.showSpeakOverquick();
                    return false;
                } else if (TextUtils.isEmpty(this.mActivity.pubchat) || !"2".equals(this.mActivity.pubchat) || filtrationString.length() <= 10 || !LoginUtils.getLoginUserBean().getCoin6rank().equals("0")) {
                    if (this.mActivity.currentUserInfoBean != null) {
                        if (RoomActivity.VIDEOTYPE_UNKNOWN.equals(this.mActivity.currentUserInfoBean.getUid())) {
                            this.mActivity.sendPublicChat(filtrationString, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId());
                        } else {
                            this.mActivity.sendPublicToPersonChat(filtrationString, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId(), this.mActivity.currentUserInfoBean.getUid(), this.mActivity.currentUserInfoBean.getUname(), this.mActivity.currentUserInfoBean.getUrid());
                        }
                    } else {
                        this.mActivity.sendPublicChat(filtrationString, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId());
                    }
                    this.mInputEditText.setText("");
                } else {
                    this.mActivity.showChatLengthy();
                    return false;
                }
            }
            return true;
        }
    }

    public boolean sendFlyText() {
        Object filtrationString = filtrationString(this.mInputEditText.getText().toString());
        if (TextUtils.isEmpty(filtrationString)) {
            this.mActivity.showToast(this.mActivity.getString(R.string.str_chat_empty));
            return false;
        } else if (filtrationString.length() <= 40) {
            return super.sendFlyText();
        } else {
            this.mActivity.showToast(this.mActivity.getString(R.string.fly_msg_overlength));
            return false;
        }
    }

    public boolean sendSmallFlyText() {
        Object filtrationString = filtrationString(this.mInputEditText.getText().toString());
        if (TextUtils.isEmpty(filtrationString)) {
            this.mActivity.showToast(this.mActivity.getString(R.string.str_chat_empty));
            return false;
        } else if (filtrationString.length() <= 40) {
            return super.sendSmallFlyText();
        } else {
            this.mActivity.showToast(this.mActivity.getString(R.string.fly_msg_overlength));
            return false;
        }
    }

    public void dismiss() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
        this.mActivity.currentUserInfoBean = null;
        super.dismiss();
    }
}
