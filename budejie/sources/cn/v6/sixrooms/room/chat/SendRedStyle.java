package cn.v6.sixrooms.room.chat;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.V6StringUtils;
import cn.v6.sixrooms.widgets.phone.ImageSpanCenter;
import cn.v6.sixrooms.widgets.phone.NoLineClickSpan;

public class SendRedStyle implements IChatStyle {
    SpannableStringBuilder a = null;
    private Context b;
    private SetClickableSpanListener c;

    public SendRedStyle(Context context, SetClickableSpanListener setClickableSpanListener) {
        this.b = context;
        this.c = setClickableSpanListener;
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        int chatStyle = roommsgBean.getChatStyle();
        String from = roommsgBean.getFrom();
        String to = roommsgBean.getTo();
        String content = roommsgBean.getContent();
        from = V6StringUtils.removeSpecialCharacter(from);
        CharSequence removeSpecialCharacter = V6StringUtils.removeSpecialCharacter(to);
        String str = from + "";
        if (TextUtils.isEmpty(removeSpecialCharacter) && 8 == chatStyle) {
            str = str + ": " + content + "*red";
        }
        str = Html2Text.Html2Text(str);
        this.a = new SpannableStringBuilder(str);
        int indexOf = str.indexOf(from);
        this.a.setSpan(new NoLineClickSpan(roommsgBean, 0, this.c, R.color.full_chat_name), indexOf, from.length() + indexOf, 0);
        if (8 == chatStyle) {
            this.a.setSpan(new ImageSpanCenter(this.b, R.drawable.rooms_room_red), str.lastIndexOf("*"), str.length(), 33);
        }
        draweeTextView.setText(this.a);
    }
}
