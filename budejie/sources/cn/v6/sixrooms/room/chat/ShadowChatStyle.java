package cn.v6.sixrooms.room.chat;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;

public class ShadowChatStyle implements IChatStyle {
    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        draweeTextView.setShadowLayer(1.0f, 0.0f, 2.0f, draweeTextView.getResources().getColor(R.color.full_chat_shadow_black_80));
    }
}
