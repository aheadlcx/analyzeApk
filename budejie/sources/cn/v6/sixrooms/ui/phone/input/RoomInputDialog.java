package cn.v6.sixrooms.ui.phone.input;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.LoginUtils;
import java.util.ArrayList;
import java.util.List;

public class RoomInputDialog extends BaseRoomInputDialog {
    private ChatListPopupWindow a;
    private ImageView b;
    private TextView c;
    private ImageView d;
    private RelativeLayout e;
    private LinearLayout f;
    private boolean g = false;
    private Handler h = new q(this);

    public RoomInputDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity);
    }

    public boolean setContentView() {
        this.mInputLayoutFactory = new LiveRoomInputLayoutFactory(this.mActivity, RoomInputTheme.COMMON_THEME);
        return true;
    }

    public void initView() {
        super.initView();
        this.b = (ImageView) findViewById(R.id.iv_select_arrow);
        this.c = (TextView) findViewById(R.id.tv_current_chat_name);
        this.d = (ImageView) findViewById(R.id.iv_quietly_public);
        this.e = (RelativeLayout) findViewById(R.id.rl_select_chat);
        this.f = (LinearLayout) findViewById(R.id.ll_chat_select);
    }

    public void initListener() {
        super.initListener();
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    private void a() {
        if (InroomPresenter.getInstance().getLocalRoomInfo() != null && this.a == null) {
            this.a = new ChatListPopupWindow(this.mActivity, DensityUtil.dip2px(160.0f), DensityUtil.dip2px(160.0f), true, InroomPresenter.getInstance().getLocalRoomInfo(), new r(this));
            if (this.mActivity != null) {
                this.mActivity.initChatListData();
            }
            this.a.setChatListDataAndRefreshAdapter(this.mActivity.allChatList, true, true, false, false);
        }
    }

    public void setChatRule() {
        super.setChatRule();
        if (this.mActivity.isChatQuietly) {
            if (!TextUtils.isEmpty(this.mActivity.pubchat)) {
                setInputEditHint(null);
            }
            updateSpeakState();
            this.d.setBackgroundResource(R.drawable.rooms_third_room_private_selected);
            if (this.mActivity.currentUserInfoBean == null || RoomActivity.VIDEOTYPE_UNKNOWN.equals(this.mActivity.currentUserInfoBean.getUid())) {
                this.mActivity.currentUserInfoBean = null;
                this.c.setText(this.mActivity.getResources().getString(R.string.pad_room_choose_chat_to_str));
            } else {
                this.c.setText(this.mActivity.currentUserInfoBean.getUname());
            }
            if (this.mActivity.allChatList != null && this.mActivity.allChatList.size() > 0 && this.mActivity.allChatList.contains(this.mActivity.tempUserInfoBean)) {
                this.mActivity.allChatList.remove(this.mActivity.tempUserInfoBean);
                if (this.a != null) {
                    this.a.notifyDataSetChanged();
                    return;
                }
                return;
            }
            return;
        }
        this.d.setBackgroundResource(R.drawable.rooms_third_room_private_normal);
        if (!(this.mActivity.allChatList == null || this.mActivity.allChatList.contains(this.mActivity.tempUserInfoBean))) {
            this.mActivity.allChatList.add(0, this.mActivity.tempUserInfoBean);
            if (this.a != null) {
                this.a.notifyDataSetChanged();
            }
        }
        if (this.mActivity.currentUserInfoBean != null) {
            this.c.setText(this.mActivity.currentUserInfoBean.getUname());
        } else {
            this.c.setText(this.mActivity.getString(R.string.pad_room_chat_to_all_str));
        }
    }

    public void receiveChatList(String str) {
        if (this.a != null && this.a.isShowing() && this.mActivity.allChatList != null && this.mActivity.allChatList.size() <= 51) {
            Message obtain = Message.obtain();
            obtain.obj = str;
            obtain.what = 12;
            this.h.sendMessage(obtain);
        }
    }

    public void receiveAllChatList(WrapUserInfo wrapUserInfo) {
        Message obtain = Message.obtain();
        if (this.a != null && this.a.isShowing()) {
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

    public void onClick(View view) {
        boolean z = true;
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.rl_select_chat) {
            if (LoginUtils.getLoginUserBean() == null) {
                this.mActivity.showLoginDialog();
                return;
            }
            if (this.g) {
                z = false;
            }
            this.g = z;
            a();
            if (this.a != null) {
                int abs = Math.abs(this.e.getWidth() - this.a.getWidth()) / 2;
                if (this.g) {
                    this.a.showAsDropDown(this.e, abs, ((-this.e.getHeight()) - this.a.getHeight()) - DensityUtil.dip2px(5.0f));
                } else {
                    this.a.dismiss();
                }
            }
        } else if (id == R.id.ll_chat_select) {
            BaseRoomActivity baseRoomActivity = this.mActivity;
            if (this.mActivity.isChatQuietly) {
                z = false;
            }
            baseRoomActivity.isChatQuietly = z;
            a();
            this.mActivity.chatChange();
            updateState();
        }
    }

    public void updateSpeakState() {
        super.updateSpeakState();
        if (this.mActivity.mSpeakState == 1) {
            this.f.setClickable(false);
        } else {
            this.f.setClickable(true);
        }
    }

    public void dismiss() {
        if (this.a != null && this.a.isShowing()) {
            this.a.dismiss();
        }
        super.dismiss();
    }

    public boolean sendChat() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.mActivity.showLoginDialog();
            return false;
        }
        if (this.mActivity != null) {
            String obj = this.mInputEditText.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                this.mActivity.showToast(this.mActivity.getString(R.string.str_chat_empty));
                return false;
            } else if (this.mActivity.isChatQuietly) {
                if (this.mActivity.currentUserInfoBean != null) {
                    this.mActivity.sendPrivateChat(obj, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId(), this.mActivity.currentUserInfoBean.getUid(), this.mActivity.currentUserInfoBean.getUname(), this.mActivity.currentUserInfoBean.getUrid());
                    this.mInputEditText.setText("");
                    this.mExpressionKeyboard.setVisibility(8);
                    dismiss();
                } else {
                    this.mActivity.showToast(this.mActivity.getResources().getString(R.string.pad_room_choose_chat_to_str));
                }
            } else if (!this.mActivity.isCanSpeak) {
                this.mActivity.showSpeakOverquick();
                return false;
            } else if (TextUtils.isEmpty(this.mActivity.pubchat) || !"2".equals(this.mActivity.pubchat) || obj.length() <= 10 || !LoginUtils.getLoginUserBean().getCoin6rank().equals("0")) {
                if (this.mActivity.currentUserInfoBean != null) {
                    if (RoomActivity.VIDEOTYPE_UNKNOWN.equals(this.mActivity.currentUserInfoBean.getUid())) {
                        this.mActivity.sendPublicChat(obj, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId());
                    } else {
                        this.mActivity.sendPublicToPersonChat(obj, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId(), this.mActivity.currentUserInfoBean.getUid(), this.mActivity.currentUserInfoBean.getUname(), this.mActivity.currentUserInfoBean.getUrid());
                    }
                } else {
                    this.mActivity.sendPublicChat(obj, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId());
                }
                this.mInputEditText.setText("");
                this.mInputManager.hideSoftInputFromWindow(this.mInputEditText.getWindowToken(), 0);
            } else {
                this.mActivity.showChatLengthy();
                return false;
            }
        }
        return true;
    }
}
