package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.view.DraweeTextView;

public interface IChatStyle {
    public static final int ACTIVITY_STYLE = 10;
    public static final int GRIL_TEAM_SYSTEM_GIFT = 9;
    public static final int HEADLINE_STYLE = 0;
    public static final int NEXT_LEVEL_TREASURE_STYLE = 4;
    public static final int PUBLIC_CHAT_STYLE = 2;
    public static final int SEND_GIFT_STYLE = 6;
    public static final int SEND_RED_PACKET_STYLE = 8;
    public static final int SMASH_GOLDEN_EGGS = 7;
    public static final int SUPER_FIREWORKS_STYLE = 5;
    public static final int SYSTEM_NOTICE_STYLE = 3;
    public static final int WELCOME_STYLE = 1;

    void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean);
}
