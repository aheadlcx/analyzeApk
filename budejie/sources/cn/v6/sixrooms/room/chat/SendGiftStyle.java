package cn.v6.sixrooms.room.chat;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.view.DraweeSpan.Builder;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.V6StringUtils;
import cn.v6.sixrooms.widgets.phone.ImageSpanCenter;
import cn.v6.sixrooms.widgets.phone.NoLineClickSpan;

public class SendGiftStyle implements IChatStyle {
    int a;
    private Context b;
    private SetClickableSpanListener c;
    private String d;

    public SendGiftStyle(Context context, SetClickableSpanListener setClickableSpanListener, String str) {
        this.c = setClickableSpanListener;
        this.d = str;
        this.b = context;
        this.a = context.getResources().getColor(R.color.full_chat_white);
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        String alias;
        Gift giftItemBean = roommsgBean.getGiftItemBean();
        String str = "";
        String chatMode = roommsgBean.getChatMode();
        String from = roommsgBean.getFrom();
        String to = roommsgBean.getTo();
        String content = roommsgBean.getContent();
        roommsgBean.getFrid();
        roommsgBean.getTorid();
        CharSequence toid = roommsgBean.getToid();
        String str2 = "对";
        String str3 = ": ";
        if ("0".equals(chatMode)) {
            alias = LoginUtils.getLoginUserBean().getAlias();
            chatMode = V6StringUtils.removeSpecialCharacter(from);
            to = V6StringUtils.removeSpecialCharacter(to);
            if (alias.equals(chatMode)) {
                chatMode = "我";
            }
            if (alias.equals(to)) {
                from = "我";
                alias = chatMode;
            } else {
                from = to;
                alias = chatMode;
            }
        } else {
            chatMode = V6StringUtils.removeSpecialCharacter(from);
            from = V6StringUtils.removeSpecialCharacter(to);
            alias = chatMode;
        }
        if (giftItemBean != null) {
            str3 = "向";
            chatMode = "送 ";
        } else {
            chatMode = str3;
            str3 = str2;
        }
        String str4 = alias + str;
        if (TextUtils.isEmpty(from)) {
            str2 = str4 + chatMode + content;
            Object obj = null;
        } else {
            if (giftItemBean == null || giftItemBean.getFrid() == 0) {
                str2 = from;
                to = alias;
            } else {
                from = roommsgBean.getFrom();
                if (TextUtils.isEmpty(toid) || !this.d.equals(toid)) {
                    str2 = to;
                } else {
                    str2 = "";
                    str3 = "";
                }
                to = V6StringUtils.removeSpecialCharacter(from);
                str2 = V6StringUtils.removeSpecialCharacter(str2);
            }
            from = str2;
            alias = to;
            str2 = str4 + str3 + str2 + chatMode + content;
            int i = 1;
        }
        Object Html2Text = Html2Text.Html2Text(str2);
        Object spannableStringBuilder = new SpannableStringBuilder(Html2Text);
        if (!(giftItemBean == null || giftItemBean.getFrid() == 0)) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a), 0, spannableStringBuilder.toString().length(), 33);
        }
        int indexOf = Html2Text.indexOf(alias);
        if (obj != null) {
            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, this.c, R.color.full_chat_name), indexOf, alias.length() + indexOf, 0);
            i = ((alias.length() + indexOf) + str.length()) + str3.length();
            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 1, this.c, R.color.full_chat_name), i, from.length() + i, 0);
        } else {
            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, this.c, R.color.full_chat_name), indexOf, alias.length() + indexOf, 0);
        }
        if (giftItemBean != null) {
            ImageSpanCenter imageSpanCenter;
            int lastIndexOf;
            if (GiftIdStrs.SILVER_GUARD == giftItemBean.getItem()) {
                imageSpanCenter = new ImageSpanCenter(this.b, R.drawable.rooms_third_guard_silver);
                lastIndexOf = spannableStringBuilder.toString().lastIndexOf("*");
                spannableStringBuilder.setSpan(imageSpanCenter, lastIndexOf, lastIndexOf + 1, 33);
            } else if (GiftIdStrs.GOLD_GUARD == giftItemBean.getItem()) {
                imageSpanCenter = new ImageSpanCenter(this.b, R.drawable.rooms_third_guard_gold);
                lastIndexOf = spannableStringBuilder.toString().lastIndexOf("*");
                spannableStringBuilder.setSpan(imageSpanCenter, lastIndexOf, lastIndexOf + 1, 33);
            }
        }
        if (giftItemBean.getItem() != GiftIdStrs.GOLD_GUARD && giftItemBean.getItem() != GiftIdStrs.SILVER_GUARD) {
            i = spannableStringBuilder.toString().lastIndexOf("*");
            spannableStringBuilder.setSpan(new Builder(giftItemBean.getSpic().getImg()).setLayout(DensityUtil.dip2px(23.0f), DensityUtil.dip2px(23.0f)).setPlaceHolderImage(this.b.getResources().getDrawable(R.drawable.phone_gift_def_bg)).build(), i, i + 1, 33);
            if (draweeTextView != null) {
                draweeTextView.setText(spannableStringBuilder);
            }
        }
    }
}
