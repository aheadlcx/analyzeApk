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

public class GrilSystemGiftStyle implements IChatStyle {
    private String a;
    private Context b;
    private int c = this.b.getResources().getColor(R.color.full_chat_white);
    private SetClickableSpanListener d;

    public GrilSystemGiftStyle(Context context, SetClickableSpanListener setClickableSpanListener, String str) {
        this.b = context;
        this.a = str;
        this.d = setClickableSpanListener;
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        String alias;
        String str;
        Gift giftItemBean = roommsgBean.getGiftItemBean();
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String chatMode = roommsgBean.getChatMode();
        if (giftItemBean != null) {
            str2 = giftItemBean.getFrom();
            str3 = giftItemBean.getTo();
        }
        String content = roommsgBean.getContent();
        roommsgBean.getFrid();
        roommsgBean.getTorid();
        CharSequence toid = roommsgBean.getToid();
        String str5 = "对";
        String str6 = ": ";
        if ("0".equals(chatMode)) {
            alias = LoginUtils.getLoginUserBean().getAlias();
            str2 = V6StringUtils.removeSpecialCharacter(str2);
            chatMode = V6StringUtils.removeSpecialCharacter(str3);
            if (alias.equals(str2)) {
                str3 = "我";
            } else {
                str3 = str2;
            }
            if (alias.equals(chatMode)) {
                alias = "我";
                str = str3;
            } else {
                alias = chatMode;
                str = str3;
            }
        } else {
            str = V6StringUtils.removeSpecialCharacter(str2);
            alias = V6StringUtils.removeSpecialCharacter(str3);
            chatMode = str3;
        }
        if (giftItemBean != null) {
            str2 = "向";
            str3 = "送 ";
        } else {
            str3 = str6;
            str2 = str5;
        }
        String str7 = str + str4;
        if (TextUtils.isEmpty(alias)) {
            str2 = str7 + str3 + content;
            Object obj = null;
        } else {
            if (giftItemBean != null) {
                str5 = roommsgBean.getFrom();
                if (TextUtils.isEmpty(toid) || !this.a.equals(toid)) {
                    str6 = chatMode;
                } else {
                    str6 = "";
                    str2 = "";
                }
                str5 = V6StringUtils.removeSpecialCharacter(str5);
                str6 = V6StringUtils.removeSpecialCharacter(str6);
            } else {
                str6 = alias;
                str5 = str;
            }
            alias = str6;
            str = str5;
            str2 = str7 + str2 + str6 + str3 + content;
            int i = 1;
        }
        Object Html2Text = Html2Text.Html2Text(str2);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(Html2Text);
        if (!(giftItemBean == null || giftItemBean.getFrid() == 0)) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.c), 0, spannableStringBuilder.toString().length(), 33);
        }
        if (obj != null) {
            i = Html2Text.indexOf(alias);
            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, this.d, R.color.full_chat_name), i, str.length() + i, 0);
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
        if (!(giftItemBean == null || giftItemBean.getItem() == GiftIdStrs.GOLD_GUARD || giftItemBean.getItem() == GiftIdStrs.SILVER_GUARD)) {
            i = spannableStringBuilder.toString().lastIndexOf("*");
            spannableStringBuilder.setSpan(new Builder(giftItemBean.getSpic().getImg()).setLayout(DensityUtil.dip2px(23.0f), DensityUtil.dip2px(23.0f)).build(), i, i + 1, 33);
        }
        if (draweeTextView != null) {
            draweeTextView.setText(spannableStringBuilder);
        }
    }
}
