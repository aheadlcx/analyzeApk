package cn.v6.sixrooms.ui.phone.input;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.room.presenter.PrivateChatPresenter;
import cn.v6.sixrooms.ui.phone.ChatListPopupWindow;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.LoginUtils;

public class PrivateInputDialog extends BaseRoomInputDialog {
    public static final int MSG_CLOSE_PRIVATE_CHAT = 1;
    public static final int MSG_REFRESH_PRIVATE_CHAT = 2;
    private RelativeLayout a;
    private String b;
    private ChatListPopupWindow c;
    private PrivateChatPresenter d;
    private Handler e = new l(this);
    private TextView f;

    public PrivateInputDialog(BaseRoomActivity baseRoomActivity) {
        super(baseRoomActivity);
        setInputEditHint("请选择悄悄话对象");
        this.mBgLayout.setOnTouchListener(new m(this));
    }

    public void setPrivateChatPresenter(PrivateChatPresenter privateChatPresenter) {
        this.d = privateChatPresenter;
    }

    public boolean setContentView() {
        this.mInputLayoutFactory = new LiveRoomInputLayoutFactory(this.mActivity, RoomInputTheme.FULL_SCREEN_CONTACT_THEME);
        return true;
    }

    public void initView() {
        super.initView();
        this.a = (RelativeLayout) findViewById(R.id.iv_contact);
        this.f = (TextView) findViewById(R.id.tv_current_chat_name);
        this.a.setOnClickListener(this);
    }

    protected void onStart() {
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            getWindow().addFlags(1024);
        } else {
            getWindow().addFlags(2048);
        }
        super.onStart();
    }

    public boolean sendChat() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.mActivity.showLoginDialog();
            return false;
        }
        if (this.mActivity != null && this.mActivity.isChatQuietly) {
            Object filtrationString = filtrationString(this.mInputEditText.getText().toString());
            if (TextUtils.isEmpty(filtrationString)) {
                this.mActivity.showToast(this.mActivity.getString(R.string.str_chat_empty));
                return false;
            } else if ("我".equals(this.mActivity.currentUserInfoBean.getUname()) || LoginUtils.getLoginUID().equals(this.mActivity.currentUserInfoBean.getUid())) {
                this.mInputEditText.setText("");
                this.mActivity.showToast("不能对自己发私聊");
                return false;
            } else {
                this.mActivity.sendPrivateChat(filtrationString, InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId(), this.mActivity.currentUserInfoBean.getUid(), this.mActivity.currentUserInfoBean.getUname(), this.mActivity.currentUserInfoBean.getUrid());
                this.mInputEditText.setText("");
                this.e.sendEmptyMessageDelayed(2, 100);
            }
        }
        return true;
    }

    public void setChatRule() {
        this.mExpressionBtn.setEnabled(true);
        if (this.mActivity.currentUserInfoBean == null || RoomActivity.VIDEOTYPE_UNKNOWN.equals(this.mActivity.currentUserInfoBean.getUid())) {
            this.f.setText("请选择聊天对象");
        } else {
            this.f.setText(this.mActivity.currentUserInfoBean.getUname());
        }
    }

    public void receiveAllChatList(WrapUserInfo wrapUserInfo) {
    }

    public void setContactImg(String str) {
        this.b = str;
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.iv_contact) {
            if (this.c == null && InroomPresenter.getInstance().getLocalRoomInfo() != null && this.c == null) {
                this.c = new ChatListPopupWindow(this.mActivity, DensityUtil.dip2px(175.0f), DensityUtil.dip2px(175.0f), true, InroomPresenter.getInstance().getLocalRoomInfo(), new n(this));
                if (this.d.getmUserInfoBeans() == null) {
                    this.d.setmUserInfoBeans(this.mActivity.initChatListData());
                }
                this.c.setChatListDataAndRefreshAdapter(this.d.getmUserInfoBeans(), true, true, true, true);
            }
            if (!this.c.isShowing()) {
                if (this.mActivity.getResources().getConfiguration().orientation == 2) {
                    this.c.setIndicatorViewState(4);
                } else {
                    this.c.setIndicatorViewState(0);
                    this.c.setIndicatorViewMargin(DensityUtil.dip2px(22.0f));
                }
                if (getContext().getResources().getConfiguration().orientation == 2) {
                    this.c.showAsDropDown(view, view.getWidth() + DensityUtil.dip2px(-60.0f), DensityUtil.dip2px(-10.0f) - (this.c.getHeight() / 2));
                } else {
                    this.c.showAsDropDown(view, DensityUtil.dip2px(-18.0f), DensityUtil.dip2px(-10.0f));
                }
            }
        }
    }

    public void show() {
        super.show();
        updateContactHeader();
    }

    public void updateContactHeader() {
    }
}
