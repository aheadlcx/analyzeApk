package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.BroadcastBean;
import cn.v6.sixrooms.bean.ChatPermissionBean;
import cn.v6.sixrooms.bean.CustomExceptionBean;
import cn.v6.sixrooms.bean.ErrorBean;
import cn.v6.sixrooms.bean.FansListTmBean;
import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.FreeVoteMsgBean;
import cn.v6.sixrooms.bean.GiftBean;
import cn.v6.sixrooms.bean.GiftListBean;
import cn.v6.sixrooms.bean.GuardStausBean;
import cn.v6.sixrooms.bean.LiveBroadcastBean;
import cn.v6.sixrooms.bean.LiveMessage;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.NoticeTmBean;
import cn.v6.sixrooms.bean.PrivateChatBean;
import cn.v6.sixrooms.bean.PublicChatBean;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.bean.SocketRoomMessageSofaBean;
import cn.v6.sixrooms.bean.SongLiveListBean;
import cn.v6.sixrooms.bean.SubRedBean;
import cn.v6.sixrooms.bean.UpdateCoinWealthBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.bean.UserListTmBean;
import cn.v6.sixrooms.bean.VoteBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import java.util.EventListener;

public interface ChatListener extends EventListener {
    void handlerSocketException(CustomExceptionBean customExceptionBean);

    void onAllUserListReceive(WrapUserInfo wrapUserInfo);

    void onAuthKeyReceive(AuthKeyBean authKeyBean);

    void onBroadcastReceive(BroadcastBean broadcastBean);

    void onChatPermissionReceive(ChatPermissionBean chatPermissionBean);

    void onErrorReceive(ErrorBean errorBean);

    void onFansListTmReceive(FansListTmBean fansListTmBean);

    void onFreeVoteMsgReceive(FreeVoteMsgBean freeVoteMsgBean);

    void onGiftList(GiftListBean giftListBean);

    void onGiftReceive(GiftBean giftBean);

    void onHeadLineMessage(OnHeadlineBeans onHeadlineBeans);

    void onLiveStateReceive(LiveStateBean liveStateBean);

    void onLiveWarningMessage(LiveMessage liveMessage);

    void onNoticeTmReceive(NoticeTmBean noticeTmBean);

    void onPrivateChatReceive(PrivateChatBean privateChatBean);

    void onPublicChatReceive(PublicChatBean publicChatBean);

    void onRececiveGuardShow(GuardStausBean guardStausBean);

    void onReceiveFlyText(FlyTextBean flyTextBean);

    void onReconnectSocket();

    void onRedEnvelopeReceive(SubRedBean subRedBean);

    void onShowMainLive(LiveBroadcastBean liveBroadcastBean);

    void onSofaUpdated(SocketRoomMessageSofaBean socketRoomMessageSofaBean);

    void onSongLiveListReceive(SongLiveListBean songLiveListBean);

    void onUpdateCoinAndWealth(UpdateCoinWealthBean updateCoinWealthBean);

    void onUpdateGiftNum(UpdateGiftNumBean updateGiftNumBean);

    void onUpgradeMessage(RoomUpgradeMsg roomUpgradeMsg);

    void onUserListTmReceive(UserListTmBean userListTmBean);

    void onVoteReceive(VoteBean voteBean);

    void onWelcomeReceive(WelcomeBean welcomeBean);
}
