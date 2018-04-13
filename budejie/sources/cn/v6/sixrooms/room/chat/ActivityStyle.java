package cn.v6.sixrooms.room.chat;

import android.content.Context;
import android.text.SpannableStringBuilder;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.V6StringUtils;
import cn.v6.sixrooms.widgets.phone.ImageSpanCenter;
import cn.v6.sixrooms.widgets.phone.NoLineClickSpan;

public class ActivityStyle implements IChatStyle {
    int a;
    SpannableStringBuilder b = null;
    private final Context c;
    private final SetClickableSpanListener d;

    public ActivityStyle(Context context, SetClickableSpanListener setClickableSpanListener) {
        this.c = context;
        this.d = setClickableSpanListener;
        this.a = this.c.getResources().getColor(R.color.full_chat_white);
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        int chatStyle = roommsgBean.getChatStyle();
        String from = roommsgBean.getFrom();
        String content = roommsgBean.getContent();
        String removeSpecialCharacter = V6StringUtils.removeSpecialCharacter(from);
        if (10 == chatStyle) {
            from = removeSpecialCharacter + ": " + content + " *active";
        } else {
            from = removeSpecialCharacter;
        }
        from = Html2Text.Html2Text(from);
        this.b = new SpannableStringBuilder(from);
        int indexOf = from.indexOf(removeSpecialCharacter);
        this.b.setSpan(new NoLineClickSpan(roommsgBean, 0, this.d, R.color.full_chat_name), indexOf, removeSpecialCharacter.length() + indexOf, 0);
        if (10 == chatStyle) {
            this.b.setSpan(new ImageSpanCenter(this.c, R.drawable.activ_popularity_ticket), from.lastIndexOf("*"), from.length(), 33);
        }
        draweeTextView.setText(this.b);
    }
}
