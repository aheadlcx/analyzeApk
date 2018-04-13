package cn.v6.sixrooms.room.chat;

import android.text.method.LinkMovementMethod;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;

public class MovementChatStyle implements IChatStyle {
    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        try {
            draweeTextView.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
