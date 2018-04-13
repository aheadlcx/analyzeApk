package cn.v6.sixrooms.socket.chat;

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
import cn.v6.sixrooms.bean.VoteBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.BecomeGodBean;
import cn.v6.sixrooms.room.InitHeadLineBean;
import cn.v6.sixrooms.room.PublicNoticeBean;
import cn.v6.sixrooms.room.SmallFlyTextBean;
import cn.v6.sixrooms.room.SuperFireworksBean;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.gift.Gift;
import java.util.List;

public interface ChatMsgSocketCallBack {
    void handlerAnchorPrompt(AnchorPrompt anchorPrompt);

    void handlerLiveWarning(LiveMessage liveMessage);

    void handlerSocketException(CustomExceptionBean customExceptionBean);

    void handlerUpgradeMessage(RoomUpgradeMsg roomUpgradeMsg);

    void onGiftList(GiftListBean giftListBean);

    void onHeadLineMessage(OnHeadlineBeans onHeadlineBeans);

    void onLiveStateReceive(LiveStateBean liveStateBean);

    void onMicro_Closed(int i);

    void onMicro_EmceeAccept();

    void onMicro_Open(String str, String str2);

    void onMicro_Refused(String str);

    void onMicro_WaitorListRefresh(List<MicroWaitorBean> list);

    void onMiniGameEnd(MiniGameBean miniGameBean);

    void onMiniGameStart(MiniGameBean miniGameBean);

    void onMiniGameUpdate(String str);

    void onNotifyPrivateDataSetChanged(RoommsgBean roommsgBean);

    void onNotifyPublicDataSetChanged(RoommsgBean roommsgBean, boolean z);

    void onRececiveBecomeGod(BecomeGodBean becomeGodBean);

    void onRececiveBroadcast(BroadcastBean broadcastBean);

    void onRececiveGuardShow(GuardStausBean guardStausBean);

    void onRececivePublicNotice(PublicNoticeBean publicNoticeBean);

    void onRececiveSuperFireworks(SuperFireworksBean superFireworksBean);

    void onRececiveWelcome(WelcomeBean welcomeBean);

    void onReceiveAllChatList(WrapUserInfo wrapUserInfo);

    void onReceiveChatList(String str);

    void onReceiveChatPermission(ChatPermissionBean chatPermissionBean);

    void onReceiveError(ErrorBean errorBean);

    void onReceiveFansTm(String str);

    void onReceiveFlyText(FlyTextBean flyTextBean);

    void onReceiveGift(Gift gift);

    void onReceiveNoticeTm(NoticeTmBean noticeTmBean);

    void onReceiveSmallFlyText(SmallFlyTextBean smallFlyTextBean);

    void onReceiveSpeakState(AuthKeyBean authKeyBean);

    void onReconnectChatSocket();

    void onShowMainLive(LiveBroadcastBean liveBroadcastBean);

    void onShowSongMenuList(List<SubLiveListBean> list);

    void onShowSongQueueList(List<SubLiveListBean> list);

    void onShowSongUpdataList(List<SubLiveListBean> list);

    void onSofaUpdated(SofaBean sofaBean);

    void onUpdateCoinWealth(UpdateCoinWealthBean updateCoinWealthBean);

    void onUpdateGiftNum(UpdateGiftNumBean updateGiftNumBean);

    void onVoteReceive(VoteBean voteBean);

    void updateHeadLineMsg(InitHeadLineBean initHeadLineBean);
}
