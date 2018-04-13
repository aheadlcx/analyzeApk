package cn.v6.sixrooms.room.statistic;

import android.text.TextUtils;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.hall.AttentionFragment;
import cn.v6.sixrooms.hall.HallActivity;
import cn.v6.sixrooms.utils.LoginUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class StatiscProxy {
    public static void homeStatistics(int i) {
        switch (i) {
            case 0:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_FOLLOW);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(AttentionFragment.class.getSimpleName()), "follow");
                StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), "follow");
                StatisticValue.getInstance().setRegisterPageModule(StatisticValue.getInstance().getHomeTypePage(AttentionFragment.class.getSimpleName()), "follow");
                return;
            case 1:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_ALL_ROOMLIST);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), "index");
                StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), "index");
                StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), "index");
                return;
            case 2:
                StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ME, StatisticCodeTable.PROFILE);
                StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PROFILE);
                StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.PROFILE);
                StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PROFILE);
                return;
            default:
                return;
        }
    }

    public static void pushIntoRoom() {
        StatisticValue.getInstance().setFromRoomPageModule("HallActivity", "HallActivity");
        StatisticValue.getInstance().setFromAttentionPageModule("HallActivity", "HallActivity");
        StatisticValue.getInstance().setFromRechargePageModule("HallActivity", "HallActivity");
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", "HallActivity");
    }

    public static void moreClickStatistics(int i) {
        switch (i) {
            case 0:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_ALL_ROOMLIST);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.HOT_M);
                return;
            case 1:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MLIVE);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.MLIVE_M);
                return;
            case 2:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_LOCATION);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.LOCATION_M);
                return;
            case 3:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MUSIC);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.U0_M);
                return;
            case 4:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_DANCE);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.U1_M);
                return;
            case 5:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MC);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.U2_M);
                return;
            case 6:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_TALK);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.U3_M);
                return;
            case 7:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MALE);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage(HallActivity.class.getSimpleName()), StatisticCodeTable.MALE_M);
                return;
            default:
                return;
        }
    }

    public static void homeClickStatistic(int i) {
        switch (i) {
            case 0:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_ALL_ROOMLIST);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerHotFragment"), StatisticCodeTable.HOT);
                return;
            case 1:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MLIVE);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerCommonFragment"), CommonStrs.TYPE_MLIVE);
                return;
            case 2:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_LOCATION);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerLocationFragment"), CommonStrs.TYPE_LOCATION);
                return;
            case 3:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MUSIC);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerMusicFragment"), CommonStrs.TYPE_MUSIC);
                return;
            case 4:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_DANCE);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerCommonFragment"), CommonStrs.TYPE_DANCE);
                return;
            case 5:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MC);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerCommonFragment"), CommonStrs.TYPE_MC);
                return;
            case 6:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_TALK);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerCommonFragment"), CommonStrs.TYPE_TALK);
                return;
            case 7:
                StatisticValue.getInstance().setPageType(CommonStrs.TYPE_MALE);
                StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerCommonFragment"), CommonStrs.TYPE_MALE);
                return;
            default:
                return;
        }
    }

    public static void setMusicClickStatistic(String str) {
        StatisticValue.getInstance().setPageType(str);
        StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallPagerMusicFragment"), str);
    }

    public static void bindHomeListData(JSONObject jSONObject, String str, String str2) {
        try {
            if (jSONObject.has("pagename")) {
                Object string = jSONObject.getString("pagename");
                if (!TextUtils.isEmpty(string)) {
                    StatisticValue.getInstance().setHomeTypePage(str, string);
                }
            }
            if (jSONObject.has("recid")) {
                CharSequence string2 = jSONObject.getString("recid");
                StatisticValue.recid = string2;
                if (!TextUtils.isEmpty(string2)) {
                    StatisticValue.getInstance().setTypeRecid(str, str2, StatisticValue.recid);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void homeIntoRoomListStatistics(String str, String str2, String str3, String str4, int i) {
        StatisticValue.getInstance().setWatchid();
        if (TextUtils.isEmpty(str3)) {
            str3 = CommonStrs.TYPE_LOCATION;
        } else if ("recFollow".equals(str3)) {
            str3 = CommonStrs.TYPE_FOLLOW;
        }
        StatisticManager.getInstance().intoStatistic(StatisticValue.getInstance().getHomeTypePage(str3, str), StatisticValue.getInstance().getHomeModule(str3), "/" + str2, String.valueOf(i), str4);
        StatisticValue.getInstance().setFromRoomPageModule(StatisticValue.getInstance().getHomeTypePage(str3, str), StatisticValue.getInstance().getHomeModule(str3));
        StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomeTypePage(str3, str), StatisticValue.getInstance().getHomeModule(str3));
        StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomeTypePage(str3, str), StatisticValue.getInstance().getHomeModule(str3));
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticValue.getInstance().getHomeTypePage(str3, str), StatisticValue.getInstance().getHomeModule(str3));
    }

    public static void liveHallSearchClick() {
        StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getHomeTypePage("LiveHallFragment"), StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromRoomPageModule(StatisticValue.getInstance().getHomeTypePage("LiveHallFragment"), StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticValue.getInstance().getHomeTypePage("LiveHallFragment"), StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomeTypePage("LiveHallFragment"), StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomeTypePage("LiveHallFragment"), StatisticCodeTable.SEARCH);
    }

    public static void dynamicListClick() {
        StatisticValue.getInstance().setFromRoomPageModule("DynamicMsgFragment", StatisticCodeTable.RMORE_AMESSAGE);
        StatisticValue.getInstance().setFromRechargePageModule("DynamicMsgFragment", StatisticCodeTable.RMORE_AMESSAGE);
        StatisticValue.getInstance().setFromRegisterPageModule("DynamicMsgFragment", StatisticCodeTable.RMORE_AMESSAGE);
        StatisticValue.getInstance().setFromAttentionPageModule("DynamicMsgFragment", StatisticCodeTable.RMORE_AMESSAGE);
    }

    public static void findPageSearchClick(String str) {
        StatisticValue.getInstance().setFromRoomPageModule(str, StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromRechargePageModule(str, StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromRegisterPageModule(str, StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromAttentionPageModule(str, StatisticCodeTable.SEARCH);
    }

    public static void findRechargeClick() {
        if (LoginUtils.isLogin()) {
            StatisticValue.getInstance().setFromRechargePageModule("HallActivity", StatisticCodeTable.DISCOVER);
            StatisticValue.getInstance().setRechargePageModule("FindFragment", StatisticCodeTable.DIS_PAY);
            return;
        }
        StatisticValue.getInstance().setRegisterPageModule("FindFragment", StatisticCodeTable.DIS_PAY);
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.DISCOVER);
    }

    public static void findShoppingClick() {
        if (LoginUtils.isLogin()) {
            StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomePageOnActivity("HallActivity"), StatisticCodeTable.DISCOVER);
            StatisticValue.getInstance().setRechargePageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_SHOP);
            return;
        }
        StatisticValue.getInstance().setRegisterPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_SHOP);
        StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomePageOnActivity("HallActivity"), StatisticCodeTable.DISCOVER);
    }

    public static void findGameCenterClick() {
        StatisticValue.getInstance().setFromRoomPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_GAME_CENTER);
        StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_GAME_CENTER);
        StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_GAME_CENTER);
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_GAME_CENTER);
    }

    public static void findHotActiveClick() {
        StatisticValue.getInstance().setFromRoomPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_EVENT_HOT);
        StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_EVENT_HOT);
        StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_EVENT_HOT);
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_EVENT_HOT);
    }

    public static void findRankingClick(String str) {
        StatisticValue.getInstance().setFromRoomPageModule(str, StatisticCodeTable.DIS_RANK);
        StatisticValue.getInstance().setFromRechargePageModule(str, StatisticCodeTable.DIS_RANK);
        StatisticValue.getInstance().setFromRegisterPageModule(str, StatisticCodeTable.DIS_RANK);
        StatisticValue.getInstance().setFromAttentionPageModule(str, StatisticCodeTable.DIS_RANK);
    }

    public static void findAttentionDynamicClick() {
        if (LoginUtils.isLogin()) {
            StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_MESSAGE);
            StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_MESSAGE);
        } else {
            StatisticValue.getInstance().setFromRechargePageModule(StatisticValue.getInstance().getHomePageOnActivity("HallActivity"), StatisticCodeTable.DISCOVER);
            StatisticValue.getInstance().setFromRegisterPageModule(StatisticValue.getInstance().getHomePageOnActivity("HallActivity"), StatisticCodeTable.DISCOVER);
        }
        StatisticValue.getInstance().setFromRoomPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_MESSAGE);
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticValue.getInstance().getHomePageOnActivity("FindFragment"), StatisticCodeTable.DIS_MESSAGE);
    }

    public static void roomGuardClick() {
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FVANGLE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_ANGLE);
        StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_ANGLE);
    }

    public static void roomSongClick() {
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_CALL);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_CALL);
        StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_CALL);
    }

    public static void meSearchClick() {
        StatisticValue.getInstance().setFromRoomPageModule(StatisticCodeTable.ME, StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticCodeTable.ME, StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.SEARCH);
        StatisticValue.getInstance().setFromRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.SEARCH);
    }

    public static void myChatClick(boolean z) {
        if (z) {
            StatisticValue.getInstance().setFromRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_IM);
            StatisticValue.getInstance().setFromRoomPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_IM);
            StatisticValue.getInstance().setFromAttentionPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_IM);
            StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_IM);
            return;
        }
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_IM);
    }

    public static void myFansClick(boolean z) {
        if (z) {
            StatisticValue.getInstance().setFromRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FANS);
            StatisticValue.getInstance().setFromRoomPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FANS);
            StatisticValue.getInstance().setFromAttentionPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FANS);
            StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FANS);
            return;
        }
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FANS);
    }

    public static void myFollowListClick(boolean z) {
        if (z) {
            StatisticValue.getInstance().setFromRoomPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FOLLOW);
            StatisticValue.getInstance().setFromAttentionPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FOLLOW);
            StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FOLLOW);
            StatisticValue.getInstance().setFromRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FOLLOW);
            return;
        }
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_FOLLOW);
    }

    public static void myProFilePhoneClick(boolean z) {
        if (z) {
            StatisticValue.getInstance().setFromRoomPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_MY);
            StatisticValue.getInstance().setFromAttentionPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_MY);
            StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_MY);
            StatisticValue.getInstance().setFromRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_MY);
            return;
        }
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_MY);
    }

    public static void myRoomManagerClick(boolean z) {
        if (!z) {
            StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_MYADMIN);
        }
    }

    public static void myBillClick(boolean z) {
        if (!z) {
            StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_BILL);
        }
    }

    public static void myPropClick(boolean z) {
        if (z) {
            StatisticValue.getInstance().setFromRoomPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_PROP);
            StatisticValue.getInstance().setFromAttentionPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_PROP);
            StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_PROP);
            StatisticValue.getInstance().setFromRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_PROP);
            return;
        }
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_PROP);
    }

    public static void myWithdRawalsClick() {
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_TIXIAN);
    }

    public static void myExchangeClick() {
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_EXCHAGE);
    }

    public static void meRechargeClick(boolean z) {
        if (z) {
            StatisticValue.getInstance().setFromRechargePageModule("HallActivity", StatisticCodeTable.PROFILE);
            StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_PAY);
            return;
        }
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_PAY);
    }

    public static void meGetGiftClick() {
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_GETGIFT);
    }

    public static void meHistoryClick() {
        StatisticValue.getInstance().setFromRoomPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_HISTROY);
        StatisticValue.getInstance().setFromAttentionPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_HISTROY);
        StatisticValue.getInstance().setFromRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_HISTROY);
        StatisticValue.getInstance().setFromRechargePageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_HISTROY);
    }

    public static void meRemindClick() {
        StatisticValue.getInstance().setFromRegisterPageModule("HallActivity", StatisticCodeTable.PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ME, StatisticCodeTable.PRO_LIVENOTICE);
    }

    public static void userDetailData(boolean z) {
        if (z) {
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_PROFILE);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_PROFILE);
            return;
        }
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_PROFILE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_PROFILE);
    }

    public static void userDataOpenRemind() {
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_LIVENOTICE);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_LIVENOTICE);
    }

    public static void userDataAddFriends(boolean z) {
        if (z) {
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_ADDFRIEND);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_ADDFRIEND);
            return;
        }
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_ADDFRIEND);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_ADDFRIEND);
    }

    public static void userDataFollowClick(boolean z) {
        if (z) {
            StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_FOLLOW);
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FPRO_FOLLOW);
            return;
        }
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_FOLLOW);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_FOLLOW);
    }

    public static void userDataPrivateChatClick(boolean z) {
        if (z) {
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_CHAT);
            return;
        }
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_PCHAT);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_PCHAT);
    }

    public static void userDataGiftClick() {
        StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_SENDGIFT);
        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_SENDGIFT);
        StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FUP_SENDGIFT);
    }
}
