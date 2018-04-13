package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.ChatPermissionBean;
import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.GiftListBean;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.NoticeTmBean;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.bean.UpdateCoinWealthBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.gift.Gift;
import java.util.List;

public interface OnChatMsgSocketCallBack {
    void endHeadLineMessage(OnHeadlineBeans onHeadlineBeans);

    void liveStateReceive(LiveStateBean liveStateBean);

    void miniGameEnd(MiniGameBean miniGameBean);

    void miniGameStart(MiniGameBean miniGameBean);

    void miniGameUpdate(String str);

    void notifyPrivateDataSetChanged(RoommsgBean roommsgBean);

    void notifyPublicDataSetChanged(RoommsgBean roommsgBean, boolean z);

    void receiveAllChatList(WrapUserInfo wrapUserInfo);

    void receiveChatList(String str);

    void receiveChatPermission(ChatPermissionBean chatPermissionBean);

    void receiveFansTm(String str);

    void receiveFlyText(FlyTextBean flyTextBean);

    void receiveGift(Gift gift);

    void receiveGiftList(GiftListBean giftListBean);

    void receiveNoticeTm(NoticeTmBean noticeTmBean);

    void receiveRed(RoommsgBean roommsgBean, boolean z);

    void receiveSmallFlyText(SmallFlyTextBean smallFlyTextBean);

    void receiveSofaUpdated(SofaBean sofaBean);

    void receiveSpeakState(AuthKeyBean authKeyBean, boolean z);

    void reconnectChatSocket();

    void showSongMenuList(List<SubLiveListBean> list);

    void showSongQueueList(List<SubLiveListBean> list);

    void showSongUpdataList(List<SubLiveListBean> list);

    void updateCoinWealth(UpdateCoinWealthBean updateCoinWealthBean);

    void updateGiftNum(UpdateGiftNumBean updateGiftNumBean);

    void updateHeadLineMsg(InitHeadLineBean initHeadLineBean);
}
