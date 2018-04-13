package cn.v6.sixrooms.utils.phone;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.internal.view.SupportMenu;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.listener.SetClickableSpanListener;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.V6StringUtils;
import cn.v6.sixrooms.widgets.phone.ImageSpanCenter;
import cn.v6.sixrooms.widgets.phone.NoLineClickSpan;
import java.util.List;

public class PhoneChatStringFormet {
    private static final String[] a = new String[]{"v.6.cn/", "www.6.cn/"};

    public static Spannable TextViewShowType(RoommsgBean roommsgBean, Context context, String str, String str2, SetClickableSpanListener setClickableSpanListener) {
        Gift giftItemBean = roommsgBean.getGiftItemBean();
        PhoneSmileyParser instance = PhoneSmileyParser.getInstance(V6Coop.getInstance().getContext());
        Resources resources = context.getResources();
        int color = resources.getColor(R.color.room_rid_color);
        int color2 = resources.getColor(R.color.anchor_typeface);
        if (roommsgBean == null) {
            return null;
        }
        String typeID = roommsgBean.getTypeID();
        Object content;
        Spannable spannableStringBuilder;
        if ("1502".equals(typeID)) {
            content = roommsgBean.getContent();
            spannableStringBuilder = new SpannableStringBuilder(content);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.red)), 0, content.length(), 17);
            return spannableStringBuilder;
        } else if ("504".equals(typeID)) {
            content = Html.fromHtml(roommsgBean.getContent());
            spannableStringBuilder = new SpannableStringBuilder(content);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.red)), 0, content.length(), 17);
            return spannableStringBuilder;
        } else if ("1521".equals(typeID)) {
            r5 = roommsgBean.getContent();
            r6 = r5.lastIndexOf("*");
            spannableStringBuilder = new SpannableStringBuilder(r5);
            if (r6 != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), 0, r6, 34);
                spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.game_green_stone), r6, r5.length(), 33);
                return spannableStringBuilder;
            }
            spannableStringBuilder.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), 0, r5.length(), 34);
            return spannableStringBuilder;
        } else {
            String str3;
            CharSequence charSequence;
            CharSequence charSequence2;
            int i;
            Spannable addSmileySpans;
            int indexOf;
            ImageSpanCenter imageSpanCenter;
            String chatMode = roommsgBean.getChatMode();
            String from = roommsgBean.getFrom();
            r5 = roommsgBean.getTo();
            CharSequence toid = roommsgBean.getToid();
            if ("0".equals(chatMode)) {
                chatMode = V6StringUtils.removeSpecialCharacter(from);
                r5 = V6StringUtils.removeSpecialCharacter(r5);
                from = LoginUtils.getLoginUserBean().getAlias();
                if (from.equals(chatMode)) {
                    chatMode = "我";
                }
                if (from.equals(r5)) {
                    str3 = r5;
                    r5 = "我";
                    from = chatMode;
                    chatMode = str3;
                } else {
                    from = chatMode;
                    chatMode = r5;
                }
            } else {
                from = V6StringUtils.removeSpecialCharacter(from);
                str3 = r5;
                r5 = V6StringUtils.removeSpecialCharacter(r5);
                chatMode = str3;
            }
            String content2 = roommsgBean.getContent();
            String frid = roommsgBean.getFrid();
            if ("null".equals(roommsgBean.getTorid())) {
                charSequence = null;
            } else {
                charSequence = roommsgBean.getTorid();
            }
            String str4 = " ";
            String str5 = "对";
            String str6 = "说：";
            String str7 = "(";
            String str8 = ")";
            if (giftItemBean != null) {
                if (giftItemBean.getFrid() == 0) {
                    str5 = " 玩 ";
                    str6 = " 获得 ";
                } else {
                    str5 = "向";
                    str6 = "送 ";
                }
            }
            int i2 = -1;
            if (!TextUtils.isEmpty(frid)) {
                i2 = Integer.parseInt(frid);
            }
            boolean a = a(i2);
            String str9 = "";
            if (a) {
                str9 = str7 + frid + str8;
            }
            int i3 = -1;
            if (!TextUtils.isEmpty(charSequence)) {
                i3 = Integer.parseInt(charSequence);
            }
            boolean a2 = a(i3);
            frid = "";
            if (a2) {
                frid = str7 + charSequence + str8;
            }
            List prop = roommsgBean.getProp();
            str8 = "";
            str7 = "";
            String str10 = "";
            String str11 = "";
            String str12 = "";
            String str13 = "";
            String str14 = "";
            if (prop != null) {
                if (prop.contains("7569")) {
                    str8 = " #silver";
                } else if (prop.contains("7570")) {
                    str7 = " #gold";
                }
                if (prop.contains("7122")) {
                    str10 = " #love";
                }
                if (prop.contains("7827")) {
                    str11 = " #rob1";
                } else if (prop.contains("7828")) {
                    str12 = " #rob2";
                } else if (prop.contains("7829")) {
                    str13 = " #rob3";
                }
                if (prop.contains("7858")) {
                    str14 = str8;
                    str8 = str7;
                    str7 = " #song";
                    charSequence2 = str14 + str8 + str10 + str11 + str12 + str13 + str7;
                    if (TextUtils.isEmpty(charSequence2)) {
                        str4 = charSequence2 + str4 + from + str9;
                    } else {
                        str4 = from + str9;
                    }
                    if (TextUtils.isEmpty(r5)) {
                        if (!(giftItemBean == null || giftItemBean.getFrid() == 0)) {
                            r5 = roommsgBean.getFrom();
                            if (!TextUtils.isEmpty(toid) && str.equals(toid)) {
                                chatMode = "";
                                frid = "";
                                str5 = "";
                            }
                            from = V6StringUtils.removeSpecialCharacter(r5);
                            r5 = V6StringUtils.removeSpecialCharacter(chatMode);
                        }
                        chatMode = str4 + str5 + r5 + frid + str6 + content2;
                        str6 = from;
                        from = r5;
                        i = 1;
                    } else if ("1304".equals(typeID)) {
                        chatMode = str4 + ": " + content2 + "*red";
                        str6 = from;
                        from = r5;
                        content = null;
                    } else if ("1305".equals(typeID)) {
                        chatMode = str4 + str6 + content2;
                        str6 = from;
                        from = r5;
                        content = null;
                    } else {
                        chatMode = str4 + ": " + content2 + "*freevote";
                        str6 = from;
                        from = r5;
                        content = null;
                    }
                    str4 = Html2Text.Html2Text(chatMode);
                    addSmileySpans = instance.addSmileySpans(str4, roommsgBean.getPriv(), roommsgBean.getProp());
                    spannableStringBuilder = new SpannableStringBuilder(addSmileySpans);
                    if (!(giftItemBean == null || giftItemBean.getFrid() == 0)) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.room_chat_get_gift_color)), 0, spannableStringBuilder.toString().length(), 33);
                    }
                    if (context instanceof RoomActivity) {
                        a(context, spannableStringBuilder, addSmileySpans.toString());
                    }
                    indexOf = str4.indexOf(str6);
                    if (content == null) {
                        i = str4.indexOf(str9);
                        if (TextUtils.isEmpty(str9) && a) {
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(color), i, str9.length() + i, 33);
                            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, setClickableSpanListener), indexOf, i, 0);
                        } else if (giftItemBean == null && giftItemBean.getFrid() == 0) {
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.room_welcome_text_color)), 0, spannableStringBuilder.toString().length(), 33);
                        } else {
                            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, setClickableSpanListener), indexOf, str6.length() + indexOf, 0);
                        }
                        i = ((str6.length() + indexOf) + str9.length()) + str5.length();
                        if (TextUtils.isEmpty(charSequence) && a2) {
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(color), from.length() + i, (from.length() + i) + frid.length(), 33);
                            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 1, setClickableSpanListener), i, from.length() + i, 33);
                        } else if (giftItemBean == null && giftItemBean.getFrid() == 0) {
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.room_welcome_text_color)), 0, spannableStringBuilder.toString().length(), 33);
                        } else {
                            spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 1, setClickableSpanListener), i, from.length() + i, 0);
                        }
                    } else if (TextUtils.isEmpty(str9) && a) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), str4.indexOf(str9), str4.indexOf(str9) + str9.length(), 33);
                        spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, setClickableSpanListener), indexOf, str4.indexOf(str9), 0);
                    } else {
                        spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, setClickableSpanListener), indexOf, str6.length() + indexOf, 0);
                    }
                    if ("1304".equals(typeID)) {
                        spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_room_red), str4.lastIndexOf("*"), str4.length(), 33);
                    } else if ("1305".equals(typeID)) {
                        spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_freevote), str4.lastIndexOf("*"), str4.length(), 33);
                    }
                    if (prop != null) {
                        if (!TextUtils.isEmpty(str14)) {
                            spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_guard_silver), str4.indexOf(str14) + 1, str4.indexOf(str14) + str14.length(), 33);
                        } else if (!TextUtils.isEmpty(str8)) {
                            spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_guard_gold), str4.indexOf(str8) + 1, str4.indexOf(str8) + str8.length(), 33);
                        }
                        if (!TextUtils.isEmpty(str10)) {
                            spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_love_big), str4.indexOf(str10) + 1, str4.indexOf(str10) + str10.length(), 33);
                        }
                        if (!TextUtils.isEmpty(str11)) {
                            spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_yellow_big), str4.indexOf(str11) + 1, str4.indexOf(str11) + str11.length(), 33);
                        } else if (!TextUtils.isEmpty(str12)) {
                            spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_red_big), str4.indexOf(str12) + 1, str4.indexOf(str12) + str12.length(), 33);
                        } else if (!TextUtils.isEmpty(str13)) {
                            spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_violet_big), str4.indexOf(str13) + 1, str4.indexOf(str13) + str13.length(), 33);
                        }
                        if (!TextUtils.isEmpty(str7)) {
                            spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_music_talent_big), str4.indexOf(str7) + 1, str4.indexOf(str7) + str7.length(), 33);
                        }
                    }
                    r5 = roommsgBean.getFid();
                    if (str != null && str.equals(r5) && CommonStrs.PUBLIC_CHAT.equals(str2)) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(color2), str4.indexOf(content2.toString()), str4.indexOf(content2.toString()) + content2.length(), 33);
                    }
                    if (giftItemBean != null) {
                        return spannableStringBuilder;
                    }
                    if (GiftIdStrs.SILVER_GUARD == giftItemBean.getItem()) {
                        imageSpanCenter = new ImageSpanCenter(context, R.drawable.rooms_third_guard_silver);
                        r6 = spannableStringBuilder.toString().lastIndexOf("*");
                        spannableStringBuilder.setSpan(imageSpanCenter, r6, r6 + 1, 33);
                        return spannableStringBuilder;
                    } else if (GiftIdStrs.GOLD_GUARD == giftItemBean.getItem()) {
                        return spannableStringBuilder;
                    } else {
                        imageSpanCenter = new ImageSpanCenter(context, R.drawable.rooms_third_guard_gold);
                        r6 = spannableStringBuilder.toString().lastIndexOf("*");
                        spannableStringBuilder.setSpan(imageSpanCenter, r6, r6 + 1, 33);
                        return spannableStringBuilder;
                    }
                }
            }
            str3 = str14;
            str14 = str8;
            str8 = str7;
            str7 = str3;
            charSequence2 = str14 + str8 + str10 + str11 + str12 + str13 + str7;
            if (TextUtils.isEmpty(charSequence2)) {
                str4 = charSequence2 + str4 + from + str9;
            } else {
                str4 = from + str9;
            }
            if (TextUtils.isEmpty(r5)) {
                r5 = roommsgBean.getFrom();
                chatMode = "";
                frid = "";
                str5 = "";
                from = V6StringUtils.removeSpecialCharacter(r5);
                r5 = V6StringUtils.removeSpecialCharacter(chatMode);
                chatMode = str4 + str5 + r5 + frid + str6 + content2;
                str6 = from;
                from = r5;
                i = 1;
            } else if ("1304".equals(typeID)) {
                chatMode = str4 + ": " + content2 + "*red";
                str6 = from;
                from = r5;
                content = null;
            } else if ("1305".equals(typeID)) {
                chatMode = str4 + str6 + content2;
                str6 = from;
                from = r5;
                content = null;
            } else {
                chatMode = str4 + ": " + content2 + "*freevote";
                str6 = from;
                from = r5;
                content = null;
            }
            str4 = Html2Text.Html2Text(chatMode);
            addSmileySpans = instance.addSmileySpans(str4, roommsgBean.getPriv(), roommsgBean.getProp());
            spannableStringBuilder = new SpannableStringBuilder(addSmileySpans);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.room_chat_get_gift_color)), 0, spannableStringBuilder.toString().length(), 33);
            if (context instanceof RoomActivity) {
                a(context, spannableStringBuilder, addSmileySpans.toString());
            }
            indexOf = str4.indexOf(str6);
            if (content == null) {
                if (TextUtils.isEmpty(str9)) {
                }
                spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, setClickableSpanListener), indexOf, str6.length() + indexOf, 0);
            } else {
                i = str4.indexOf(str9);
                if (TextUtils.isEmpty(str9)) {
                }
                if (giftItemBean == null) {
                }
                spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 0, setClickableSpanListener), indexOf, str6.length() + indexOf, 0);
                i = ((str6.length() + indexOf) + str9.length()) + str5.length();
                if (TextUtils.isEmpty(charSequence)) {
                }
                if (giftItemBean == null) {
                }
                spannableStringBuilder.setSpan(new NoLineClickSpan(roommsgBean, 1, setClickableSpanListener), i, from.length() + i, 0);
            }
            if ("1304".equals(typeID)) {
                spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_room_red), str4.lastIndexOf("*"), str4.length(), 33);
            } else if ("1305".equals(typeID)) {
                spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_freevote), str4.lastIndexOf("*"), str4.length(), 33);
            }
            if (prop != null) {
                if (!TextUtils.isEmpty(str14)) {
                    spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_guard_silver), str4.indexOf(str14) + 1, str4.indexOf(str14) + str14.length(), 33);
                } else if (TextUtils.isEmpty(str8)) {
                    spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_guard_gold), str4.indexOf(str8) + 1, str4.indexOf(str8) + str8.length(), 33);
                }
                if (TextUtils.isEmpty(str10)) {
                    spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_love_big), str4.indexOf(str10) + 1, str4.indexOf(str10) + str10.length(), 33);
                }
                if (!TextUtils.isEmpty(str11)) {
                    spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_yellow_big), str4.indexOf(str11) + 1, str4.indexOf(str11) + str11.length(), 33);
                } else if (!TextUtils.isEmpty(str12)) {
                    spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_red_big), str4.indexOf(str12) + 1, str4.indexOf(str12) + str12.length(), 33);
                } else if (TextUtils.isEmpty(str13)) {
                    spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_violet_big), str4.indexOf(str13) + 1, str4.indexOf(str13) + str13.length(), 33);
                }
                if (TextUtils.isEmpty(str7)) {
                    spannableStringBuilder.setSpan(new ImageSpanCenter(context, R.drawable.rooms_third_music_talent_big), str4.indexOf(str7) + 1, str4.indexOf(str7) + str7.length(), 33);
                }
            }
            r5 = roommsgBean.getFid();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(color2), str4.indexOf(content2.toString()), str4.indexOf(content2.toString()) + content2.length(), 33);
            if (giftItemBean != null) {
                return spannableStringBuilder;
            }
            if (GiftIdStrs.SILVER_GUARD == giftItemBean.getItem()) {
                imageSpanCenter = new ImageSpanCenter(context, R.drawable.rooms_third_guard_silver);
                r6 = spannableStringBuilder.toString().lastIndexOf("*");
                spannableStringBuilder.setSpan(imageSpanCenter, r6, r6 + 1, 33);
                return spannableStringBuilder;
            } else if (GiftIdStrs.GOLD_GUARD == giftItemBean.getItem()) {
                return spannableStringBuilder;
            } else {
                imageSpanCenter = new ImageSpanCenter(context, R.drawable.rooms_third_guard_gold);
                r6 = spannableStringBuilder.toString().lastIndexOf("*");
                spannableStringBuilder.setSpan(imageSpanCenter, r6, r6 + 1, 33);
                return spannableStringBuilder;
            }
        }
    }

    private static boolean a(int i) {
        if (i <= 0 || ((i >= 30000000 && i < 60000000) || (i >= 200000000 && i < 900000000))) {
            return false;
        }
        return true;
    }

    private static CharSequence a(Context context, SpannableStringBuilder spannableStringBuilder, String str) {
        int i = 0;
        String[] strArr = a;
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
        spannableStringBuilder.setSpan(new a(context, stringBuilder.toString()), indexOf, (str2.length() + indexOf) + stringBuilder.toString().length(), 33);
        return spannableStringBuilder;
    }
}
