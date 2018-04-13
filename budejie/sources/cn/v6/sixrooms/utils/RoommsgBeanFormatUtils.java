package cn.v6.sixrooms.utils;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.BadgeBean;
import cn.v6.sixrooms.bean.FreeVoteMsgBean;
import cn.v6.sixrooms.bean.GiftBean;
import cn.v6.sixrooms.bean.PrivateChatBean;
import cn.v6.sixrooms.bean.PublicChatBean;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SubRedBean;
import cn.v6.sixrooms.bean.SysNotificationBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.SuperFireworksTextUtils;

public class RoommsgBeanFormatUtils {
    public static RoommsgBean formatFromSubRedBean(SubRedBean subRedBean) {
        RoommsgBean roommsgBean = new RoommsgBean();
        roommsgBean.setContent(new StringBuilder(SuperFireworksTextUtils.s1).append(subRedBean.getContent().getNum()).append("个红包 ").toString());
        roommsgBean.setFid(subRedBean.getFid());
        roommsgBean.setFrid(subRedBean.getFrid());
        roommsgBean.setFrom(subRedBean.getFrom());
        roommsgBean.setTm(subRedBean.getTm());
        roommsgBean.setTo("");
        roommsgBean.setTorid("");
        return roommsgBean;
    }

    public static RoommsgBean formatFromFreeVote(FreeVoteMsgBean freeVoteMsgBean) {
        RoommsgBean roommsgBean = new RoommsgBean();
        roommsgBean.setContent(new StringBuilder(SuperFireworksTextUtils.s1).append(freeVoteMsgBean.getContent().getNum()).append("张唱战票").toString());
        roommsgBean.setFid(freeVoteMsgBean.getFid());
        roommsgBean.setFrid(freeVoteMsgBean.getFrid());
        roommsgBean.setFrom(freeVoteMsgBean.getFrom());
        roommsgBean.setTm(freeVoteMsgBean.getTm());
        roommsgBean.setTo("");
        roommsgBean.setTorid("");
        roommsgBean.setTypeID(freeVoteMsgBean.getTypeId());
        return roommsgBean;
    }

    public static RoommsgBean formatFromGift(GiftBean giftBean, Gift gift) {
        RoommsgBean roommsgBean = new RoommsgBean();
        String str = "*" + gift.getTitle() + "，共" + gift.getNum() + "个";
        if (giftBean.getItem() == GiftIdStrs.GOLD_GUARD || giftBean.getItem() == GiftIdStrs.SILVER_GUARD) {
            str = "*" + (giftBean.getItem() == GiftIdStrs.GOLD_GUARD ? "黄金守护 " : "白银守护 ") + giftBean.getNum() + "个";
        }
        roommsgBean.setContent(str);
        roommsgBean.setFid(giftBean.getFid());
        if (giftBean.getFrid() == 0) {
            roommsgBean.setFrid("0");
            roommsgBean.setFrom(giftBean.getTo());
            roommsgBean.setTo(giftBean.getFrom());
            roommsgBean.setTorid("0");
        } else {
            roommsgBean.setFrid(giftBean.getFrid());
            roommsgBean.setFrom(giftBean.getFrom());
            roommsgBean.setTo(giftBean.getTo());
            roommsgBean.setTorid(giftBean.getTrid());
            roommsgBean.setToid(giftBean.getTid());
        }
        roommsgBean.setTm(giftBean.getTm());
        roommsgBean.setTypeID(giftBean.getTypeId());
        roommsgBean.setGiftItemBean(gift);
        return roommsgBean;
    }

    public static RoommsgBean formatFromSysNotificationBean(SysNotificationBean sysNotificationBean) {
        RoommsgBean roommsgBean = new RoommsgBean();
        roommsgBean.setContent(sysNotificationBean.getContent());
        roommsgBean.setTypeID("-2");
        roommsgBean.setTm(sysNotificationBean.getTm());
        roommsgBean.setRankFlag(sysNotificationBean.isRankFlag());
        roommsgBean.setRank(sysNotificationBean.getRank());
        return roommsgBean;
    }

    public static RoommsgBean formatFromPublicChatBean(PublicChatBean publicChatBean) {
        RoommsgBean roommsgBean = new RoommsgBean();
        roommsgBean.setContent(Html2Text.Html2Text(publicChatBean.getContent()));
        roommsgBean.setFid(publicChatBean.getFid());
        roommsgBean.setFrid(publicChatBean.getFrid());
        roommsgBean.setFrom(publicChatBean.getFrom());
        roommsgBean.setTm(publicChatBean.getTm());
        roommsgBean.setTo(publicChatBean.getTo());
        roommsgBean.setToid(publicChatBean.getToid());
        roommsgBean.setTorid(publicChatBean.getTorid());
        roommsgBean.setTypeID(publicChatBean.getTypeId());
        roommsgBean.setPriv(publicChatBean.getPriv());
        roommsgBean.setProp(publicChatBean.getProp());
        roommsgBean.setWealth(publicChatBean.getCr());
        roommsgBean.setDefinedWealth(false);
        return roommsgBean;
    }

    public static RoommsgBean formatFromPrivateChatBean(PrivateChatBean privateChatBean) {
        RoommsgBean roommsgBean = new RoommsgBean();
        roommsgBean.setContent(privateChatBean.getContent());
        roommsgBean.setFid(privateChatBean.getFid());
        roommsgBean.setFrid(privateChatBean.getFrid());
        roommsgBean.setFrom(privateChatBean.getFrom());
        roommsgBean.setTm(privateChatBean.getTm());
        roommsgBean.setTo(privateChatBean.getTname());
        roommsgBean.setTypeID(privateChatBean.getTypeId());
        roommsgBean.setTorid(privateChatBean.getTrid());
        roommsgBean.setToid(privateChatBean.getTo());
        roommsgBean.setChatMode("0");
        return roommsgBean;
    }

    public static RoommsgBean formatFromWelcomeBean(WelcomeBean welcomeBean) {
        RoommsgBean roommsgBean = new RoommsgBean();
        int driver = welcomeBean.getDriver();
        String str = "";
        if (1 == driver) {
            str = "( 来自 * )";
            roommsgBean.setDriver(1);
        } else if (2 == driver) {
            str = "( 来自 * )";
            roommsgBean.setDriver(2);
        } else if (3 == driver) {
            str = "( 来自 * )";
            roommsgBean.setDriver(3);
        } else if (4 == driver) {
            str = "( 来自 * )";
            roommsgBean.setDriver(4);
        }
        roommsgBean.setContent(Html2Text.Html2Text(welcomeBean.getMsg()) + str);
        roommsgBean.setFrom(welcomeBean.getAlias());
        roommsgBean.setTm(welcomeBean.getTm());
        roommsgBean.setTypeID(welcomeBean.getTypeId());
        BadgeBean badgeBean = new BadgeBean();
        String pngcar = welcomeBean.getPngcar();
        if (pngcar != null) {
            badgeBean.setPngcar(pngcar);
        }
        pngcar = welcomeBean.getPriv();
        if (pngcar != null) {
            badgeBean.setPriv(pngcar);
        }
        pngcar = welcomeBean.getRid();
        if (pngcar != null) {
            badgeBean.setRid(pngcar);
        }
        if (!TextUtils.isEmpty(welcomeBean.getRich())) {
            roommsgBean.setWealth(Integer.valueOf(welcomeBean.getRich()).intValue());
        }
        badgeBean.setProp(welcomeBean.getProp());
        roommsgBean.setBadgeBean(badgeBean);
        return roommsgBean;
    }
}
