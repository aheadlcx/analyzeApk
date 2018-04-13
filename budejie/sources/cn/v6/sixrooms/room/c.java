package cn.v6.sixrooms.room;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.AnchorPrompt;
import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.BroadcastBean;
import cn.v6.sixrooms.bean.ChatPermissionBean;
import cn.v6.sixrooms.bean.CustomExceptionBean;
import cn.v6.sixrooms.bean.ErrorBean;
import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.GiftListBean;
import cn.v6.sixrooms.bean.GuardStausBean;
import cn.v6.sixrooms.bean.LiveBroadcastBean;
import cn.v6.sixrooms.bean.LiveMessage;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.MicroWaitorBean;
import cn.v6.sixrooms.bean.NoticeTmBean;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.bean.UpdateCoinWealthBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.bean.VoteBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.socket.chat.ChatMsgSocketCallBack;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.List;

final class c implements ChatMsgSocketCallBack {
    final /* synthetic */ BaseRoomActivity a;

    c(BaseRoomActivity baseRoomActivity) {
        this.a = baseRoomActivity;
    }

    public final void onUpdateGiftNum(UpdateGiftNumBean updateGiftNumBean) {
        this.a.processGiftNumUpdate(updateGiftNumBean);
        for (OnChatMsgSocketCallBack updateGiftNum : this.a.j) {
            updateGiftNum.updateGiftNum(updateGiftNumBean);
        }
    }

    public final void onShowSongQueueList(List<SubLiveListBean> list) {
        for (OnChatMsgSocketCallBack showSongQueueList : this.a.j) {
            showSongQueueList.showSongQueueList(list);
        }
    }

    public final void onShowSongMenuList(List<SubLiveListBean> list) {
        this.a.processSongMenuList(list);
        for (OnChatMsgSocketCallBack showSongMenuList : this.a.j) {
            showSongMenuList.showSongMenuList(list);
        }
    }

    public final void onReconnectChatSocket() {
        LogUtils.d("BaseRoomActivity", "createChatMsgSocket---onReconnectChatSocket");
        this.a.processChatSocketReconnect();
        for (OnChatMsgSocketCallBack reconnectChatSocket : this.a.j) {
            reconnectChatSocket.reconnectChatSocket();
        }
    }

    public final void onReceiveSpeakState(AuthKeyBean authKeyBean) {
        boolean z;
        this.a.processSocketListenerSet();
        authKeyBean.analyze();
        if (authKeyBean.getUserIdentity() == 7 || authKeyBean.getUserIdentity() == 10 || authKeyBean.getUserIdentity() == 9) {
            z = true;
        } else {
            z = false;
        }
        WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
        if (localRoomInfo != null) {
            localRoomInfo.setRoomManager(z);
        }
        this.a.mSpeakState = authKeyBean.getSpeakState();
        this.a.currentIdentity = authKeyBean.getUserIdentity();
        for (OnChatMsgSocketCallBack receiveSpeakState : this.a.j) {
            receiveSpeakState.receiveSpeakState(authKeyBean, z);
        }
        this.a.processSpeakStateChange(authKeyBean, z);
    }

    public final void onReceiveGift(Gift gift) {
        LogUtils.d("Base", "giftItemBean---" + gift.toString());
        if (!BaseRoomActivity.a(this.a, gift)) {
            LogUtils.d("Base", "checkDownLoadGiftRes---true");
            this.a.processSocketGift(gift);
            for (OnChatMsgSocketCallBack receiveGift : this.a.j) {
                receiveGift.receiveGift(gift);
            }
        }
    }

    public final void onReceiveFansTm(String str) {
        this.a.processFansTmChange(str);
        for (OnChatMsgSocketCallBack receiveFansTm : this.a.j) {
            receiveFansTm.receiveFansTm(str);
        }
    }

    public final void onReceiveError(ErrorBean errorBean) {
        this.a.g.post(new d(this, errorBean));
    }

    public final void onReceiveChatPermission(ChatPermissionBean chatPermissionBean) {
        this.a.pubchat = chatPermissionBean.getChatType();
        WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
        if (localRoomInfo != null) {
            localRoomInfo.getRoomParamInfoBean().setPubchat(this.a.pubchat);
        }
        for (OnChatMsgSocketCallBack receiveChatPermission : this.a.j) {
            receiveChatPermission.receiveChatPermission(chatPermissionBean);
        }
    }

    public final void onUpdateCoinWealth(UpdateCoinWealthBean updateCoinWealthBean) {
        UserBean userBean = GlobleValue.getUserBean();
        if (userBean != null) {
            String coin6 = updateCoinWealthBean.getCoin6();
            String wealth = updateCoinWealthBean.getWealth();
            userBean.setCoin6(coin6);
            userBean.setWealth(wealth);
            GlobleValue.setUserBean(userBean);
        }
        for (OnChatMsgSocketCallBack updateCoinWealth : this.a.j) {
            updateCoinWealth.updateCoinWealth(updateCoinWealthBean);
        }
    }

    public final void onReceiveChatList(String str) {
        for (OnChatMsgSocketCallBack receiveChatList : this.a.j) {
            receiveChatList.receiveChatList(str);
        }
    }

    public final void onReceiveAllChatList(WrapUserInfo wrapUserInfo) {
        WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
        if (localRoomInfo != null) {
            localRoomInfo.setWrapUserInfo(wrapUserInfo);
        }
        for (OnChatMsgSocketCallBack receiveAllChatList : this.a.j) {
            receiveAllChatList.receiveAllChatList(wrapUserInfo);
        }
    }

    public final void onNotifyPublicDataSetChanged(RoommsgBean roommsgBean, boolean z) {
        LogUtils.d("baseroom", "onNotifyPublicDataSetChanged-" + Thread.currentThread().getName());
        if (!this.a.isSuperGirlRoom().booleanValue() || (!"-2".equals(roommsgBean.getTypeID()) && !"135".equals(roommsgBean.getTypeID()) && !"1304".equals(roommsgBean.getTypeID()))) {
            WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
            if (z) {
                int i;
                Object allgetnum = localRoomInfo.getLiveinfoBean().getAllgetnum();
                if (TextUtils.isEmpty(allgetnum)) {
                    i = 0;
                } else {
                    i = Integer.parseInt(allgetnum);
                }
                localRoomInfo.getLiveinfoBean().setAllgetnum(String.valueOf(i + 1));
                this.a.processSocketRed(roommsgBean, z);
                for (OnChatMsgSocketCallBack receiveRed : this.a.j) {
                    receiveRed.receiveRed(roommsgBean, z);
                }
            }
            if (this.a.mBasePublicChatItem.size() >= 200) {
                this.a.mBasePublicChatItem.remove(0);
            }
            if (this.a.mFistFansUid != null && this.a.mFistFansUid.equals(roommsgBean.getFid())) {
                roommsgBean.setFirstFans(true);
            }
            this.a.mBasePublicChatItem.add(roommsgBean);
            localRoomInfo.setPublicRoommsgBeans(this.a.mBasePublicChatItem);
            for (OnChatMsgSocketCallBack receiveRed2 : this.a.j) {
                receiveRed2.notifyPublicDataSetChanged(roommsgBean, z);
            }
        }
    }

    public final void onNotifyPrivateDataSetChanged(RoommsgBean roommsgBean) {
        if (!this.a.isSuperGirlRoom().booleanValue()) {
            WrapRoomInfo localRoomInfo = InroomPresenter.getInstance().getLocalRoomInfo();
            if (this.a.mBasePrivateChatItem.size() >= 200) {
                this.a.mBasePrivateChatItem.remove(0);
            }
            this.a.mBasePrivateChatItem.add(roommsgBean);
            localRoomInfo.setPrivateRoommsgBeans(this.a.mBasePrivateChatItem);
            for (OnChatMsgSocketCallBack notifyPrivateDataSetChanged : this.a.j) {
                notifyPrivateDataSetChanged.notifyPrivateDataSetChanged(roommsgBean);
            }
        }
    }

    public final void onLiveStateReceive(LiveStateBean liveStateBean) {
        this.a.processliveState(liveStateBean);
        for (OnChatMsgSocketCallBack liveStateReceive : this.a.j) {
            liveStateReceive.liveStateReceive(liveStateBean);
        }
    }

    public final void onVoteReceive(VoteBean voteBean) {
    }

    public final void onMicro_Open(String str, String str2) {
    }

    public final void onMicro_Closed(int i) {
    }

    public final void onMicro_Refused(String str) {
    }

    public final void onMicro_EmceeAccept() {
    }

    public final void onMicro_WaitorListRefresh(List<MicroWaitorBean> list) {
    }

    public final void onReceiveNoticeTm(NoticeTmBean noticeTmBean) {
        BaseRoomActivity.a(this.a, noticeTmBean.getTime());
    }

    public final void onReceiveFlyText(FlyTextBean flyTextBean) {
        this.a.processSocketFlyText(flyTextBean);
        for (OnChatMsgSocketCallBack receiveFlyText : this.a.j) {
            receiveFlyText.receiveFlyText(flyTextBean);
        }
    }

    public final void onReceiveSmallFlyText(SmallFlyTextBean smallFlyTextBean) {
        this.a.processSocketSmallFlyText(smallFlyTextBean);
        for (OnChatMsgSocketCallBack receiveSmallFlyText : this.a.j) {
            receiveSmallFlyText.receiveSmallFlyText(smallFlyTextBean);
        }
    }

    public final void onSofaUpdated(SofaBean sofaBean) {
        InroomPresenter.getInstance().getLocalRoomInfo().getRoomParamInfoBean().getSofa().put(sofaBean.getSite(), sofaBean);
        for (OnChatMsgSocketCallBack receiveSofaUpdated : this.a.j) {
            receiveSofaUpdated.receiveSofaUpdated(sofaBean);
        }
    }

    public final void onShowMainLive(LiveBroadcastBean liveBroadcastBean) {
    }

    public final void handlerSocketException(CustomExceptionBean customExceptionBean) {
        this.a.sendSocketException(customExceptionBean);
    }

    public final void handlerLiveWarning(LiveMessage liveMessage) {
        this.a.showLiveWarningMessage(liveMessage);
    }

    public final void onRececiveGuardShow(GuardStausBean guardStausBean) {
        this.a.showOpenGuardianAnimation(guardStausBean);
    }

    public final void handlerUpgradeMessage(RoomUpgradeMsg roomUpgradeMsg) {
        if (!this.a.isSuperGirlRoom().booleanValue()) {
            this.a.processUpgradeMessage(roomUpgradeMsg);
        }
    }

    public final void onGiftList(GiftListBean giftListBean) {
        for (OnChatMsgSocketCallBack receiveGiftList : this.a.j) {
            receiveGiftList.receiveGiftList(giftListBean);
        }
    }

    public final void onShowSongUpdataList(List<SubLiveListBean> list) {
        for (OnChatMsgSocketCallBack showSongUpdataList : this.a.j) {
            showSongUpdataList.showSongUpdataList(list);
        }
    }

    public final void onHeadLineMessage(OnHeadlineBeans onHeadlineBeans) {
        if (!this.a.isSuperGirlRoom().booleanValue()) {
            for (OnChatMsgSocketCallBack endHeadLineMessage : this.a.j) {
                endHeadLineMessage.endHeadLineMessage(onHeadlineBeans);
            }
        }
    }

    public final void onMiniGameStart(MiniGameBean miniGameBean) {
        for (OnChatMsgSocketCallBack miniGameStart : this.a.j) {
            miniGameStart.miniGameStart(miniGameBean);
        }
    }

    public final void onMiniGameEnd(MiniGameBean miniGameBean) {
        for (OnChatMsgSocketCallBack miniGameEnd : this.a.j) {
            miniGameEnd.miniGameEnd(miniGameBean);
        }
    }

    public final void onMiniGameUpdate(String str) {
        for (OnChatMsgSocketCallBack miniGameUpdate : this.a.j) {
            miniGameUpdate.miniGameUpdate(str);
        }
    }

    public final void handlerAnchorPrompt(AnchorPrompt anchorPrompt) {
        this.a.processAnchorPrompt(anchorPrompt);
    }

    public final void updateHeadLineMsg(InitHeadLineBean initHeadLineBean) {
        if (!this.a.isSuperGirlRoom().booleanValue()) {
            this.a.handler.post(new e(this, initHeadLineBean));
        }
    }

    public final void onRececiveBroadcast(BroadcastBean broadcastBean) {
        this.a.handler.post(new f(this, broadcastBean));
    }

    public final void onRececiveBecomeGod(BecomeGodBean becomeGodBean) {
        this.a.handler.post(new g(this, becomeGodBean));
    }

    public final void onRececiveSuperFireworks(SuperFireworksBean superFireworksBean) {
        this.a.handler.post(new h(this, superFireworksBean));
    }

    public final void onRececivePublicNotice(PublicNoticeBean publicNoticeBean) {
        this.a.handler.post(new i(this, publicNoticeBean));
    }

    public final void onRececiveWelcome(WelcomeBean welcomeBean) {
        this.a.handler.post(new j(this, welcomeBean));
    }
}
