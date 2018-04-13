package cn.v6.sixrooms.room.chat;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.utils.Html2Text;

public class WelcomeChatStyle implements IChatStyle {
    public static final String TAG = WelcomeChatStyle.class.getSimpleName();
    private ChatListAdapter a;
    private BaseRoomActivity b;

    public WelcomeChatStyle(ChatListAdapter chatListAdapter, BaseRoomActivity baseRoomActivity) {
        this.a = chatListAdapter;
        this.b = baseRoomActivity;
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        int color;
        Object Html2Text = Html2Text.Html2Text(roommsgBean.getContent());
        CharSequence spannableStringBuilder = new SpannableStringBuilder(Html2Text);
        if ((this.a.getRoomType() != 1 || this.b.isInputShow) && (this.a.getRoomType() != 0 || this.b.isInputShow)) {
            color = this.b.getResources().getColor(R.color.room_chat_welcome_color);
        } else {
            draweeTextView.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            color = this.b.getResources().getColor(R.color.room_chat_welcome_color_80);
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), 0, Html2Text.length(), 33);
        if (draweeTextView != null) {
            draweeTextView.setText(spannableStringBuilder);
        }
    }
}
