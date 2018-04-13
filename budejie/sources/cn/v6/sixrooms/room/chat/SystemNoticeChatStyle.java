package cn.v6.sixrooms.room.chat;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.utils.Html2Text;

public class SystemNoticeChatStyle implements IChatStyle {
    private BaseRoomActivity a;

    public SystemNoticeChatStyle(BaseRoomActivity baseRoomActivity) {
        this.a = baseRoomActivity;
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        Object Html2Text = Html2Text.Html2Text(roommsgBean.getContent());
        CharSequence spannableStringBuilder = new SpannableStringBuilder(Html2Text);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getResources().getColor(R.color.white)), 0, Html2Text.length(), 33);
        draweeTextView.setText(spannableStringBuilder);
    }
}
