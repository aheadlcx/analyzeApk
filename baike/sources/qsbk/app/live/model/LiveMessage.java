package qsbk.app.live.model;

import android.graphics.Color;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import qsbk.app.core.model.RedEnvelopes;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveMessage extends LiveMessageBase {
    public static final int TYPE_ACTIVITY = 61;
    public static final int TYPE_ADMIN_ADD = 27;
    public static final int TYPE_ADMIN_CANCEL = 28;
    public static final int TYPE_ANCHOR_CONTINUE = 18;
    public static final int TYPE_ANCHOR_SUSPEND = 17;
    public static final int TYPE_BALANCE = 14;
    public static final int TYPE_BARRAGE = 24;
    public static final int TYPE_BEAUTY = 16;
    public static final int TYPE_CLOSE = 12;
    public static final int TYPE_COMMENT = 1;
    public static final int TYPE_CONNECT = -1;
    public static final int TYPE_CREATE_ROOM = 8;
    public static final int TYPE_DOLL = 86;
    public static final int TYPE_DOLL_ACTION = 89;
    public static final int TYPE_DOLL_ROOM = 87;
    public static final int TYPE_DOLL_ROOM_SINGLE = 88;
    public static final int TYPE_EMPTY = -2;
    public static final int TYPE_ENTER = 4;
    public static final int TYPE_EXIT = 7;
    public static final int TYPE_FIRST_CHARGE = 60;
    public static final int TYPE_FOLLOW = 2;
    public static final int TYPE_FRAME_ANIMATION = 56;
    public static final int TYPE_FREEGIFT_AVAILABLE = 72;
    public static final int TYPE_FREEGIFT_GET = 73;
    public static final int TYPE_FREEGIFT_START = 71;
    public static final int TYPE_GAME_BEST_BET_RESULT = 43;
    public static final int TYPE_GAME_BET = 46;
    public static final int TYPE_GAME_BET_ANCHOR = 48;
    public static final int TYPE_GAME_BET_RESULT = 47;
    public static final int TYPE_GAME_DATA = 49;
    public static final int TYPE_GAME_FANFANLE_ANCHOR_SELECT = 93;
    public static final int TYPE_GAME_FANFANLE_AUTO_CLOSE = 96;
    public static final int TYPE_GAME_FANFANLE_SELECT = 94;
    public static final int TYPE_GAME_FANFANLE_STEP = 95;
    public static final int TYPE_GAME_INIT = 44;
    public static final int TYPE_GAME_ME_BET = 42;
    public static final int TYPE_GAME_NEXT_ROUND = 92;
    public static final int TYPE_GAME_RESULT = 45;
    public static final int TYPE_GIFT = 6;
    public static final int TYPE_GIFT_WEEK_RANK = 57;
    public static final int TYPE_GIFT_WEEK_RANK_HINT = 58;
    public static final int TYPE_GIFT_Zhouxing_RANK = 59;
    public static final int TYPE_GLOBAL_GIFT = 29;
    public static final int TYPE_GLOBAL_RED_ENVELOPES = 97;
    public static final int TYPE_GUESS_CLOSE = 110;
    public static final int TYPE_GUESS_HISTORY = 107;
    public static final int TYPE_GUESS_INFO = 109;
    public static final int TYPE_GUESS_RESULT = 108;
    public static final int TYPE_HEART_BEAT = 10;
    public static final int TYPE_LEVEL_UP = 23;
    public static final int TYPE_LIVE_DATA = 19;
    public static final int TYPE_LOVE = 3;
    public static final int TYPE_MIC = 52;
    public static final int TYPE_MIC_ROOM = 53;
    public static final int TYPE_NEWER_DISCOUNT = 74;
    public static final int TYPE_PRO = 41;
    public static final int TYPE_RECONNECT = 11;
    public static final int TYPE_RED_ENVELOPES = 98;
    public static final int TYPE_RED_ENVELOPES_TEXT = 104;
    public static final int TYPE_RED_ENVELOPES_UPDATE = 103;
    public static final int TYPE_REMIND_TIP = 105;
    public static final int TYPE_REPUSH = 25;
    public static final int TYPE_RICH = 22;
    public static final int TYPE_ROB_RED_ENVELOPES = 101;
    public static final int TYPE_ROB_RED_ENVELOPES_RESULT = 102;
    public static final int TYPE_SEND_ERROR = 9;
    public static final int TYPE_SEND_RED_ENVELOPES = 100;
    public static final int TYPE_SHARE = 5;
    public static final int TYPE_SILENT = 21;
    public static final int TYPE_SILENT_CANCEL = 51;
    public static final int TYPE_SYSTEM = 13;
    public long c;
    public long s;
    public long u;
    public long w;

    public LiveMessage(long j, int i) {
        super(j, i);
    }

    public static LiveMessageBase createSilentMessage(long j, long j2, long j3) {
        return new LiveSilentMsg(j, 21, j2, j3);
    }

    public static LiveMessageBase createSilentCancelMessage(long j, long j2, long j3) {
        return new LiveSilentMsg(j, 51, j2, j3);
    }

    public static LiveMessage createSystemMessage(long j, String str) {
        LiveSystemMessageContent liveSystemMessageContent = new LiveSystemMessageContent();
        liveSystemMessageContent.m = str;
        liveSystemMessageContent.t = 0;
        return new LiveSystemMessage(j, 13, liveSystemMessageContent);
    }

    public static LiveMessageBase createCommentMessage(long j, String str) {
        return new LiveCommentMsg(j, 1, str);
    }

    public static LiveMessage createCommentLiveMessage(long j, String str) {
        return new LiveCommentMessage(j, 1, str);
    }

    public static LiveMessageBase createBarrageMessage(long j, String str, String str2, long j2) {
        return new LiveBarrageMsg(j, 24, str, str2, j2);
    }

    public static LiveMessageBase createFreeGiftMessage(long j) {
        return new LiveFreeGiftMsg(j, 73);
    }

    public static LiveMessageBase createFollowMessage(long j, long j2) {
        return new LiveFollowMessage(j, 2, j2);
    }

    public static LiveMessageBase createLoveMessage(long j, int i) {
        return createLoveMessage(j, 1, i);
    }

    public static LiveMessageBase createLoveMessage(long j, int i, int i2) {
        return new LiveLoveMsg(j, 3, i > 0 ? i : 1, i2);
    }

    public static LiveMessage createLoveLiveMessage(long j, int i, int i2) {
        return new LiveLoveMessage(j, 3, i, i2);
    }

    public static LiveMessageBase createShareMessage(long j, String str) {
        return new LiveShareMessage(j, 5, str);
    }

    public static LiveMessageBase createGiftMessage(long j, long j2, long j3, long j4) {
        return createGiftMessage(j, j2, j3, j4, 1);
    }

    public static LiveMessageBase createGiftMessage(long j, long j2, long j3, long j4, long j5) {
        return new LiveGiftMsg(j, 6, j3, j4, j2, j5);
    }

    public static LiveMessageBase createExitMessage(long j) {
        return new LiveMessageBase(j, 7);
    }

    public static LiveMessageBase createHeartBeatMessage(long j) {
        return new LiveMessageBase(j, 10);
    }

    public static LiveMessageBase createCloseMessage(long j) {
        return new LiveMessageBase(j, 12);
    }

    public static LiveMessageBase createBeautyMessage(long j, String str, boolean z) {
        return new LiveBeautyMsg(j, 16, str, z);
    }

    public static LiveMessageBase createAnchorSuspendMessage(long j) {
        return new LiveMessageBase(j, 17);
    }

    public static LiveMessage createAnchorSuspendMessage(long j, String str) {
        LiveCommonMessageContent liveCommonMessageContent = new LiveCommonMessageContent();
        liveCommonMessageContent.m = str;
        return new LiveCommonMessage(j, 17, liveCommonMessageContent);
    }

    public static LiveMessageBase createAnchorContinueMessage(long j) {
        return new LiveMessageBase(j, 18);
    }

    public static LiveMessageBase createAdminMessage(long j, long j2, long j3, boolean z) {
        return new LiveAdminMsg(j, z ? 27 : 28, j2, j3);
    }

    public static LiveMessageBase createGameBetMessage(long j, long j2, long j3, long j4, long j5) {
        return new LiveGameBetMsg(j, 46, j2, j3, j4, j5);
    }

    public static LiveMessageBase createGameAnchorSelectMessage(long j, long j2, long j3, int i) {
        return new LiveGameAnchorSelectMsg(j, 93, j2, j3, i);
    }

    public static LiveMessageBase createGameFanfanleSelectMessage(long j, long j2, long j3, long j4, long j5) {
        return new LiveGameBetMsg(j, 94, j2, j3, j4, j5);
    }

    public static LiveMessageBase createGameNextRoundMessage(long j, long j2, long j3, int i) {
        return new LiveGameNextRoundMsg(j, 92, j2, j3, i);
    }

    public static LiveMessage createEmptyMessage() {
        return new LiveCommonMessage(0, -2, null);
    }

    public static LiveMessage createConnectMessage(String str) {
        LiveCommonMessageContent liveCommonMessageContent = new LiveCommonMessageContent();
        liveCommonMessageContent.m = str;
        return new LiveCommonMessage(0, -1, liveCommonMessageContent);
    }

    public static LiveMessage createMicMessage(long j, int i, String str, long j2, long j3) {
        return new LiveMicMessage(j, 52, i, str, j2, j3);
    }

    public static LiveMessage createFirstChargeMessage(long j, long j2) {
        return new LiveFirstChargeMessage(60, j2, j);
    }

    public static LiveMessage createDollMessage(long j, int i) {
        return new LiveDollMessage(j, 86, i);
    }

    public static LiveMessage createDollActionMessage(long j, int i) {
        return new LiveDollActionMessage(j, 89, i);
    }

    public static LiveMessageBase createSendRedEnvelopesMessage(long j, String str, RedEnvelopes redEnvelopes) {
        return new LiveSendRedEnvelopesMsg(j, 100, str, redEnvelopes);
    }

    public static LiveMessageBase createRobRedEnvelopesMessage(long j, String str, long j2) {
        return new LiveRobRedEnvelopesMsg(j, 101, str, j2);
    }

    public static LiveMessageBase createFollowHintMessage(long j, int i) {
        return new LiveSendFollowHintMsg(j, 105, i);
    }

    @JsonIgnore
    public boolean isConnectMessage() {
        return this.p == -1;
    }

    @JsonIgnore
    public boolean isEmptyMessage() {
        return this.p == -2;
    }

    @JsonIgnore
    public boolean isSystemMessage() {
        return this.p == 13;
    }

    @JsonIgnore
    public boolean isCommentMessage() {
        return this.p == 1;
    }

    public boolean isHistoryCommentMessage() {
        return isCommentMessage() && getClientMessageId() == -1;
    }

    @JsonIgnore
    public boolean isBarrageMessage() {
        return this.p == 24;
    }

    @JsonIgnore
    public boolean isLoveMessage() {
        return this.p == 3;
    }

    @JsonIgnore
    public boolean isRemindMessage() {
        return this.p == 105;
    }

    @JsonIgnore
    public boolean isGiftMessage() {
        return this.p == 6;
    }

    @JsonIgnore
    public boolean isFrameAnimation() {
        return this.p == 56;
    }

    @JsonIgnore
    public String getContent() {
        String str = null;
        LiveCommonMessageContent liveMessageContent = getLiveMessageContent();
        switch (getMessageType()) {
            case -2:
                break;
            case -1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 9:
            case 12:
            case 13:
            case 17:
            case 18:
            case 21:
            case 25:
            case 27:
            case 28:
            case 29:
            case 41:
            case 48:
            case 51:
                if (!(liveMessageContent == null || TextUtils.isEmpty(liveMessageContent.m))) {
                    str = liveMessageContent.m;
                    break;
                }
            case 1:
            case 24:
            case 104:
                if (liveMessageContent != null) {
                    CharSequence charSequence;
                    if (TextUtils.isEmpty(liveMessageContent.m)) {
                        charSequence = null;
                    } else {
                        charSequence = liveMessageContent.m;
                    }
                    LiveCommentMessageContent liveCommentMessageContent = (LiveCommentMessageContent) liveMessageContent;
                    if (TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(liveCommentMessageContent.c)) {
                        charSequence = liveCommentMessageContent.c;
                    }
                    CharSequence charSequence2 = charSequence;
                    break;
                }
                break;
            case 6:
            case 56:
                LiveGiftMessageContent liveGiftMessageContent = (LiveGiftMessageContent) liveMessageContent;
                if (!(liveMessageContent == null || liveGiftMessageContent.m == null || liveGiftMessageContent.g == null)) {
                    str = liveGiftMessageContent.m.replace("$", liveGiftMessageContent.g.n);
                    break;
                }
            case 52:
            case 53:
                LiveMicMessageContent liveMicMessageContent = (LiveMicMessageContent) liveMessageContent;
                if (!(liveMicMessageContent == null || TextUtils.isEmpty(liveMicMessageContent.d))) {
                    str = liveMicMessageContent.d;
                    break;
                }
            default:
                str = AppUtils.getInstance().getAppContext().getString(R.string.live_msg_type_not_support);
                break;
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    @JsonIgnore
    public String getGiftContent() {
        if (getMessageType() == 6 || getMessageType() == 56) {
            LiveGiftMessageContent liveGiftMessageContent = (LiveGiftMessageContent) getLiveMessageContent();
            if (!(liveGiftMessageContent == null || liveGiftMessageContent.m == null || liveGiftMessageContent.g == null)) {
                return liveGiftMessageContent.m.replace("$", liveGiftMessageContent.g.n);
            }
        }
        return null;
    }

    public LiveCommonMessageContent getLiveMessageContent() {
        return null;
    }

    @JsonIgnore
    public String getUserAvatar() {
        LiveUser user = getUser();
        return user != null ? user.av : null;
    }

    @JsonIgnore
    public void setUserAvatar(String str) {
        LiveUser user = getUser();
        if (user != null) {
            user.av = str;
        }
    }

    @JsonIgnore
    public String getUserAvatarTemplate() {
        LiveUser user = getUser();
        return user != null ? user.t : null;
    }

    @JsonIgnore
    public List<String> getUserLabel() {
        LiveUser user = getUser();
        return user != null ? user.pu : null;
    }

    @JsonIgnore
    public String getUserName() {
        LiveUser user = getUser();
        return user != null ? user.n : null;
    }

    @JsonIgnore
    public int getUserLevel() {
        LiveUser user = getUser();
        return user != null ? user.l : 0;
    }

    public int getUserAdmin() {
        LiveUser user = getUser();
        return user != null ? user.m : 0;
    }

    public boolean isUserAdmin() {
        if (getUserAdmin() == 1) {
            return true;
        }
        return false;
    }

    @JsonIgnore
    public void setUserLevel(int i) {
        LiveUser user = getUser();
        if (user != null) {
            user.l = i;
        }
    }

    @JsonIgnore
    public User getConvertedUser() {
        LiveUser user = getUser();
        if (user == null) {
            return null;
        }
        User user2 = new User();
        user2.id = this.i;
        user2.name = user.n;
        user2.headurl = user.av;
        user2.level = user.l;
        user2.origin = this.o;
        user2.is_admin = user.m;
        return user2;
    }

    @JsonIgnore
    public LiveUser getUser() {
        LiveCommonMessageContent liveMessageContent = getLiveMessageContent();
        return (liveMessageContent == null || liveMessageContent.u == null) ? null : liveMessageContent.u;
    }

    public void setUser(LiveUser liveUser) {
        LiveCommonMessageContent liveMessageContent = getLiveMessageContent();
        if (liveMessageContent != null) {
            liveMessageContent.u = liveUser;
        }
    }

    @JsonIgnore
    public long getUserId() {
        return this.i;
    }

    @JsonIgnore
    public long getOrigin() {
        return this.o;
    }

    @JsonIgnore
    public long getOnlineUserCount() {
        return this.u;
    }

    @JsonIgnore
    public long getReceivedGiftCount() {
        return this.c;
    }

    @JsonIgnore
    public long getReceivedGiftWeekCount() {
        return this.w;
    }

    @JsonIgnore
    public long getSeqId() {
        return this.s;
    }

    public int[] getMessageColors() {
        LiveCommonMessageContent liveMessageContent = getLiveMessageContent();
        if (liveMessageContent == null || liveMessageContent.z == null) {
            return null;
        }
        int size = liveMessageContent.z.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = Color.parseColor((String) liveMessageContent.z.get(i));
        }
        if (size > 1) {
            liveMessageContent.z = liveMessageContent.z.subList(0, 1);
        }
        return iArr;
    }

    @JsonIgnore
    public String getMessageBadge() {
        LiveUser user = getUser();
        return user != null ? user.b : null;
    }

    @JsonIgnore
    public String getFamilyLeaderBadge() {
        LiveUser user = getUser();
        return user != null ? user.lb : null;
    }

    @JsonIgnore
    public int getMessageFamilyLevel() {
        LiveUser user = getUser();
        return user != null ? user.fl : 0;
    }
}
