package cn.v6.sixrooms.room.chat;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.view.DraweeSpan.Builder;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.V6StringUtils;

public class SmashEggStyle implements IChatStyle {
    private Context a;
    private SetClickableSpanListener b;
    private String c;
    private int d;

    public SmashEggStyle(Context context, SetClickableSpanListener setClickableSpanListener, String str) {
        this.b = setClickableSpanListener;
        this.c = str;
        this.a = context;
        this.d = context.getResources().getColor(R.color.full_chat_white);
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        Object removeSpecialCharacter;
        Gift giftItemBean = roommsgBean.getGiftItemBean();
        String str = "";
        String chatMode = roommsgBean.getChatMode();
        String from = roommsgBean.getFrom();
        String to = roommsgBean.getTo();
        String content = roommsgBean.getContent();
        String str2 = "对";
        String str3 = ": ";
        if ("0".equals(chatMode)) {
            String alias = LoginUtils.getLoginUserBean().getAlias();
            chatMode = V6StringUtils.removeSpecialCharacter(from);
            removeSpecialCharacter = V6StringUtils.removeSpecialCharacter(to);
            if (alias.equals(chatMode)) {
                chatMode = "我";
            }
            if (alias.equals(removeSpecialCharacter)) {
                removeSpecialCharacter = "我";
                to = chatMode;
            } else {
                to = chatMode;
            }
        } else {
            from = V6StringUtils.removeSpecialCharacter(from);
            chatMode = V6StringUtils.removeSpecialCharacter(to);
            to = from;
            from = chatMode;
        }
        if (giftItemBean != null) {
            str3 = " 玩 ";
            chatMode = " 获得 ";
        } else {
            chatMode = str3;
            str3 = str2;
        }
        str2 = to + str;
        if (TextUtils.isEmpty(removeSpecialCharacter)) {
            chatMode = str2 + chatMode + content;
        } else {
            chatMode = str2 + str3 + removeSpecialCharacter + chatMode + content;
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(Html2Text.Html2Text(chatMode));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.d), 0, spannableStringBuilder.toString().length(), 33);
        if (giftItemBean != null) {
            int lastIndexOf = spannableStringBuilder.toString().lastIndexOf("*");
            spannableStringBuilder.setSpan(new Builder(giftItemBean.getSpic().getImg()).setLayout(DensityUtil.dip2px(23.0f), DensityUtil.dip2px(23.0f)).setPlaceHolderImage(this.a.getResources().getDrawable(R.drawable.phone_gift_def_bg)).build(), lastIndexOf, lastIndexOf + 1, 33);
        }
        if (draweeTextView != null) {
            draweeTextView.setText(spannableStringBuilder);
        }
    }
}
