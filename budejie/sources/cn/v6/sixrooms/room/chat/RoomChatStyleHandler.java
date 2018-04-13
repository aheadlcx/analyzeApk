package cn.v6.sixrooms.room.chat;

import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.view.DraweeTextView;

public class RoomChatStyleHandler extends ChatStyleHandler {
    private ChatListAdapter a;

    public RoomChatStyleHandler(ChatListAdapter chatListAdapter) {
        this.a = chatListAdapter;
        registerChatStyle(new ShadowChatStyle());
        registerChatStyle(new MovementChatStyle());
        registerChatStyle(0, new OnHeadLineChatStyle(this.a));
        registerChatStyle(1, new WelcomeChatStyle(this.a, this.a.getBaseActivity()));
        registerChatStyle(3, new SystemNoticeChatStyle(this.a.getBaseActivity()));
        registerChatStyle(4, new WealthRankStyle(this.a.getBaseActivity()));
        registerChatStyle(5, new SuperFireworksStyle(this.a.getBaseActivity(), this.a));
        registerChatStyle(6, new SendGiftStyle(this.a.getBaseActivity(), this.a, this.a.getFrid()));
        registerChatStyle(2, new PublicChatStyle(this.a.getBaseActivity(), this.a, this.a.getFrid()));
        registerChatStyle(7, new SmashEggStyle(this.a.getBaseActivity(), this.a, this.a.getFrid()));
        registerChatStyle(8, new SendRedStyle(this.a.getBaseActivity(), this.a));
        registerChatStyle(9, new GrilSystemGiftStyle(this.a.getBaseActivity(), this.a, this.a.getFrid()));
        registerChatStyle(10, new ActivityStyle(this.a.getBaseActivity(), this.a));
    }

    public void processBean(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        if (roommsgBean.getChatStyle() != -1) {
            handleStyle(roommsgBean.getChatStyle(), roommsgBean, draweeTextView);
        }
    }
}
