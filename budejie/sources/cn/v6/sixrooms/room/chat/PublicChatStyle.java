package cn.v6.sixrooms.room.chat;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.view.DraweeSpan.Builder;
import cn.v6.sixrooms.room.view.DraweeTextView;
import cn.v6.sixrooms.socket.chat.IChatStyle;
import cn.v6.sixrooms.surfaceanim.specialenterframe.FortuneUtils;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.PropParseUtil;
import cn.v6.sixrooms.utils.V6StringUtils;
import cn.v6.sixrooms.utils.phone.PhoneSmileyParser;
import cn.v6.sixrooms.widgets.phone.ImageSpanCenter;
import cn.v6.sixrooms.widgets.phone.NoLineClickSpan;
import java.util.List;

public class PublicChatStyle implements IChatStyle {
    private static final String[] d = new String[]{"v.6.cn/", "www.6.cn/"};
    int a;
    int b;
    int c;
    private String e;
    private SetClickableSpanListener f;
    private Context g;
    private final int h;

    public PublicChatStyle(Context context, SetClickableSpanListener setClickableSpanListener, String str) {
        this.g = context;
        this.f = setClickableSpanListener;
        this.e = str;
        Resources resources = context.getResources();
        this.a = resources.getColor(R.color.full_chat_anthor);
        this.b = resources.getColor(R.color.full_chat_name_guard_yellow);
        this.c = resources.getColor(R.color.full_first_fans_color);
        this.h = resources.getColor(R.color.full_five_star_color);
    }

    public void onStyle(DraweeTextView draweeTextView, RoommsgBean roommsgBean) {
        Object obj;
        int lastIndexOf;
        LogUtils.e("PublicChatStyle", "发消息次数");
        Gift giftItemBean = roommsgBean.getGiftItemBean();
        PhoneSmileyParser instance = PhoneSmileyParser.getInstance(V6Coop.getInstance().getContext());
        int chatStyle = roommsgBean.getChatStyle();
        roommsgBean.getPosition();
        String fid = roommsgBean.getFid();
        String str = "";
        String chatMode = roommsgBean.getChatMode();
        String from = roommsgBean.getFrom();
        String to = roommsgBean.getTo();
        String content = roommsgBean.getContent();
        roommsgBean.getFrid();
        roommsgBean.getTorid();
        roommsgBean.getToid();
        String str2 = "对";
        String str3 = ": ";
        if ("0".equals(chatMode)) {
            String alias = LoginUtils.getLoginUserBean().getAlias();
            chatMode = V6StringUtils.removeSpecialCharacter(from);
            from = V6StringUtils.removeSpecialCharacter(to);
            if (alias.equals(chatMode)) {
                chatMode = "我";
            }
            if (alias.equals(from)) {
                from = chatMode;
                chatMode = "我";
            } else {
                String str4 = from;
                from = chatMode;
                chatMode = str4;
            }
        } else {
            from = V6StringUtils.removeSpecialCharacter(from);
            chatMode = V6StringUtils.removeSpecialCharacter(to);
        }
        List prop = roommsgBean.getProp();
        if (!roommsgBean.isPropParsedRes()) {
            roommsgBean.setPropResId(PropParseUtil.parsePropDrawbleList(prop));
            roommsgBean.setPropParsedRes(true);
        }
        if (!roommsgBean.isPropParsedImgUrl()) {
            roommsgBean.setPropImgUrl(PropParseUtil.parsePropImgUrlList(prop));
            roommsgBean.setPropParsedImgUrl(true);
        }
        String str5 = from + str;
        if (TextUtils.isEmpty(chatMode)) {
            obj = null;
            str3 = str5 + str3 + content;
        } else {
            obj = 1;
            str3 = str5 + str2 + chatMode + str3 + content;
        }
        Object Html2Text = Html2Text.Html2Text(str3);
        CharSequence addSmileySpans = instance.addSmileySpans(Html2Text, roommsgBean.getPriv(), roommsgBean.getProp());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(addSmileySpans);
        int indexOf = Html2Text.indexOf(from);
        if (obj != null) {
            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, this.f, R.color.full_chat_name), indexOf, from.length() + indexOf, 0);
            int length = ((from.length() + indexOf) + str.length()) + str2.length();
            spannableStringBuilder = spannableStringBuilder;
            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 1, this.f, R.color.full_chat_name), length, chatMode.length() + length, 0);
        } else {
            spannableStringBuilder = spannableStringBuilder;
            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, this.f, R.color.full_chat_name), indexOf, from.length() + indexOf, 0);
        }
        if (PropParseUtil.isFlashStar(prop) || roommsgBean.getWealth() >= 25) {
            lastIndexOf = spannableStringBuilder.toString().lastIndexOf(content);
            if (lastIndexOf >= 0) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.h), lastIndexOf, content.length() + lastIndexOf, 33);
            }
        }
        if (giftItemBean == null && 8 != chatStyle && roommsgBean.isFirstFans()) {
            lastIndexOf = spannableStringBuilder.toString().lastIndexOf(content);
            if (lastIndexOf >= 0) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.c), lastIndexOf, content.length() + lastIndexOf, 33);
            }
        }
        if (prop != null && prop.contains("7570") && giftItemBean == null) {
            lastIndexOf = spannableStringBuilder.toString().lastIndexOf(content);
            if (lastIndexOf >= 0) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.b), lastIndexOf, content.length() + lastIndexOf, 33);
            }
        }
        if (this.e != null && this.e.equals(fid) && giftItemBean == null && 8 != chatStyle) {
            lastIndexOf = spannableStringBuilder.toString().lastIndexOf(content);
            if (lastIndexOf >= 0) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a), lastIndexOf, content.length() + lastIndexOf, 33);
            }
        }
        if (this.g instanceof RoomActivity) {
            a(this.g, spannableStringBuilder, addSmileySpans.toString());
        }
        if (roommsgBean.getPropImgUrl() != null && roommsgBean.getPropImgUrl().size() > 0) {
            for (String chatMode2 : roommsgBean.getPropImgUrl()) {
                spannableStringBuilder.insert(0, "* ");
                spannableStringBuilder.setSpan(new Builder(chatMode2).setLayout(DensityUtil.dip2px(17.0f), DensityUtil.dip2px(17.0f)).setPlaceHolderImage(this.g.getResources().getDrawable(R.drawable.badge_default)).build(), 0, 1, 33);
            }
        }
        if (roommsgBean.getPropResId() != null && roommsgBean.getPropResId().size() > 0) {
            for (Integer num : roommsgBean.getPropResId()) {
                spannableStringBuilder.insert(0, "* ");
                spannableStringBuilder.setSpan(new ImageSpanCenter(this.g, num.intValue()), 0, 1, 33);
            }
        }
        Object obj2 = null;
        if (roommsgBean.getWealth() < 27 && roommsgBean.getWealth() > 0) {
            length = DensityUtil.dip2px(30.0f);
            lastIndexOf = DensityUtil.dip2px(12.0f);
            if (roommsgBean.getWealth() >= 25) {
                length = DensityUtil.dip2px(39.0f);
                lastIndexOf = DensityUtil.dip2px(12.0f);
            }
            obj2 = new Builder("res://cn.v6.sixrooms/" + FortuneUtils.getFortuneLevelUrl(String.valueOf(roommsgBean.getWealth()))).setLayout(length, lastIndexOf).setPlaceHolderImage(this.g.getResources().getDrawable(R.drawable.custom_wealth_default)).build();
        } else if (roommsgBean.getWealth() >= 27) {
            obj2 = new Builder(DrawableResourceUtils.getCustomWealthRankImg(roommsgBean.getFid())).setLayout(DensityUtil.dip2px(39.0f), DensityUtil.dip2px(12.0f)).setPlaceHolderImage(this.g.getResources().getDrawable(R.drawable.custom_wealth_default)).build();
        }
        if (obj2 != null) {
            spannableStringBuilder.insert(0, "* ");
            spannableStringBuilder.setSpan(obj2, 0, 1, 33);
        }
        if (draweeTextView != null) {
            draweeTextView.setText(spannableStringBuilder);
        }
    }

    private static CharSequence a(Context context, SpannableStringBuilder spannableStringBuilder, String str) {
        int i = 0;
        String[] strArr = d;
        if (strArr.length <= 0) {
            return str;
        }
        String str2 = strArr[0];
        if (!str.contains(str2) || str.contains("/f/")) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        int indexOf = str.indexOf(str2);
        String substring = str.substring(str2.length() + indexOf);
        while (i < substring.length()) {
            char charAt = substring.charAt(i);
            if (charAt < '0' || charAt > '9') {
                break;
            }
            stringBuilder.append((char) charAt);
            i++;
        }
        if (TextUtils.isEmpty(stringBuilder.toString())) {
            return str;
        }
        spannableStringBuilder.setSpan(new b(context, stringBuilder.toString()), indexOf, (str2.length() + indexOf) + stringBuilder.toString().length(), 33);
        return spannableStringBuilder;
    }
}
