package cn.v6.sixrooms.room.chat;

import android.content.Context;
import android.text.SpannableStringBuilder;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.utils.Html2Text;

public class SuperFireworksStyle implements IChatStyle {
    private Context a;
    private ChatListAdapter b;

    public SuperFireworksStyle(Context context, ChatListAdapter chatListAdapter) {
        this.a = context;
        this.b = chatListAdapter;
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(Html2Text.Html2Text(roommsgBean.getContent()));
        if (this.a instanceof RoomActivity) {
            spannableStringBuilder.setSpan(this.b.typeClick(roommsgBean.getFrid(), roommsgBean.getCustomRuid()), 0, spannableStringBuilder.length(), 33);
        }
        draweeTextView.setText(spannableStringBuilder);
    }
}
