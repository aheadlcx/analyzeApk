package qsbk.app.im;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Pair;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.EncryptDecryptUtil;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.DeeplinkInfo;
import qsbk.app.model.GroupNotice;
import qsbk.app.model.GroupSystemMsg;
import qsbk.app.model.InviteInfo;
import qsbk.app.model.LaiseeImInfo;
import qsbk.app.model.LaiseeImLog;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SpringFestivalUtil;
import qsbk.app.utils.json.JSONAble;
import qsbk.app.utils.json.JSONUtil;
import qsbk.app.utils.json.JsonEncodePrivate;
import qsbk.app.utils.json.JsonPrivate;

public class ChatMsg extends JSONAble {
    public static final long MIN_GROUP_DB_ID = 4611686018427387903L;
    public static final String UID_LIVE = "32862889";
    private static long a = -1;
    public String at;
    public String data;
    @JsonPrivate
    public long dbid;
    public int ex_create = 0;
    public String from;
    public int fromage;
    public String fromgender;
    public String fromicon;
    public String fromnick;
    public String gicon;
    public String gid;
    public String gnick;
    public int inout = 0;
    public boolean isLike = false;
    public boolean iscontent = false;
    public int mType;
    public String msgid;
    @JsonPrivate
    public List<String> msgids;
    public String msgsrc;
    public boolean notify = true;
    @JsonEncodePrivate
    public long pre_seqid;
    @JsonEncodePrivate
    public String seq_type;
    @JsonPrivate
    public boolean showTime = false;
    public int showType;
    public int status;
    @JsonPrivate
    public Object tag;
    @JsonEncodePrivate
    public long this_seqid;
    public long time = 0;
    public String to;
    public int totalLikeNum = 0;
    public int type;
    public String uid;
    public int usertype;

    public interface TYPE {
        public static final int CONTENT_TYPE_DIVEDER = 100;
        public static final int ITEM_TYPE_MEDAL_NOTIFY = 28;
        public static final int TYPE_CIRCLE_TOPIC_SHARE = 24;
        public static final int TYPE_CORRECT_TIME = 108;
        public static final int TYPE_DELETE_CONTACT = 201;
        public static final int TYPE_DISCONNECT = 105;
        public static final int TYPE_DRAFT = 100;
        public static final int TYPE_DUOBAO = 30;
        public static final int TYPE_GROUP_NOTICE = 202;
        public static final int TYPE_GROUP_SYSTEM = 301;
        public static final int TYPE_IMG = 3;
        public static final int TYPE_INVITE = 203;
        public static final int TYPE_LAISEE = 33;
        public static final int TYPE_LAISEE_LOG = 34;
        public static final int TYPE_LAISEE_NOT_GOT = 35;
        public static final int TYPE_LAISEE_VOICE = 38;
        public static final int TYPE_LIVE_BIGIN = 26;
        public static final int TYPE_LIVE_BIGIN_SHARE = 25;
        public static final int TYPE_LIVE_WEB_SHARE = 29;
        public static final int TYPE_MSG_DIVIDER = 990;
        public static final int TYPE_NEWFANS = 200;
        public static final int TYPE_NEWS_SHARE = 31;
        public static final int TYPE_NEW_PEOPLE_INTO_GROUP = 302;
        public static final int TYPE_OFFICIAL = 10;
        public static final int TYPE_PURE_EMOTION = 5;
        public static final int TYPE_PUSH_DEEPLINK = 39;
        public static final int TYPE_QIUSHI_ARTICLE_SHARE = 22;
        public static final int TYPE_QIUSHI_NOTIFY = 20;
        public static final int TYPE_QIUSHI_TOPIC_SHARE = 37;
        public static final int TYPE_QSJX_SHARE = 36;
        public static final int TYPE_READ = 107;
        @Deprecated
        public static final int TYPE_SENDED = 106;
        public static final int TYPE_SENDING = 102;
        public static final int TYPE_SHARE_QIUYOUCIRCLE = 27;
        public static final int TYPE_SOUND = 4;
        public static final int TYPE_SPRING_FESTIVAL = 32;
        public static final int TYPE_SYNC_CONTROL = 402;
        public static final int TYPE_SYNC_LIST = 401;
        public static final int TYPE_SYNC_LIST_MAINTENANCE = 400;
        public static final int TYPE_SYSTEM = 8;
        public static final int TYPE_TXT = 1;
        public static final int TYPE_WEB_SHARE = 23;
    }

    public ChatMsg(int i, String str) {
        this.type = i;
        this.data = str;
    }

    public ChatMsg(int i) {
        this.type = i;
    }

    public static long getFakeLocalDbId() {
        long j = a;
        a = j - 1;
        return j;
    }

    public boolean isContentMsg() {
        return this.type < 100 || this.type == 203 || this.type == 202 || this.type == 301 || this.type == 302 || this.type == 26 || this.type == 25 || this.type == 20;
    }

    public boolean isValidIcon() {
        return (TextUtils.isEmpty(this.fromicon) || "null".equalsIgnoreCase(this.fromicon)) ? false : true;
    }

    public ChatMsg from(String str) {
        this.from = str;
        return this;
    }

    public ChatMsg to(String str) {
        this.to = str;
        return this;
    }

    public ChatMsg group(String str) {
        this.gid = str;
        return this;
    }

    public boolean isGroupMsg() {
        return !TextUtils.isEmpty(this.gid);
    }

    public String getGroupMsgTips() {
        if (this.type == 1 || this.type == 4 || this.type == 3 || this.type == 22 || this.type == 23 || this.type == 29 || this.type == 5 || this.type == 24 || this.type == 27) {
            return this.fromnick + "：" + getMsgTips();
        }
        return getMsgTips();
    }

    public String getMsgTips() {
        CharSequence optString;
        JSONException e;
        if (this.type == 1) {
            return this.data;
        }
        if (this.type == 4) {
            return "[语音]";
        }
        if (this.type == 3) {
            return "[图片]";
        }
        String str;
        if (this.type == 200) {
            str = "";
            try {
                return new JSONObject(this.data).optString("fan_names");
            } catch (JSONException e2) {
                e2.printStackTrace();
                return str;
            }
        } else if (this.type == 10) {
            str = "";
            try {
                return new JSONObject(this.data).optString("title");
            } catch (Exception e3) {
                e3.printStackTrace();
                return str;
            }
        } else if (this.type == 8) {
            return this.data;
        } else {
            if (this.type == 5) {
                return new ChatMsgEmotionData(this.data).name;
            }
            if (this.type == 203) {
                return getInviteInfo().title;
            }
            if (this.type == 202) {
                if (this.tag == null || !(this.tag instanceof String)) {
                    return getGroupNotice().title;
                }
                return (String) this.tag;
            } else if (this.type == 20) {
                str = "";
                try {
                    return new JSONObject(this.data).optString("d");
                } catch (Exception e32) {
                    e32.printStackTrace();
                    return str;
                }
            } else if (this.type == 22) {
                str = "";
                try {
                    str = new JSONObject(this.data).optString("plainText");
                } catch (JSONException e22) {
                    e22.printStackTrace();
                }
                if (str.length() > 15) {
                    return "[糗事分享]" + str.substring(0, 15);
                }
                return "[糗事分享]" + str;
            } else if (this.type == 23 || this.type == 29) {
                ShareMsgItem shareMsgItem = getShareMsgItem();
                str = shareMsgItem == null ? "" : this.type == 23 ? shareMsgItem.content : shareMsgItem.title;
                if (str.length() > 15) {
                    return "[分享]" + str.substring(0, 15);
                }
                return "[分享]" + str;
            } else if (this.type == 24) {
                str = "";
                try {
                    str = new JSONObject(this.data).optString("title");
                } catch (JSONException e222) {
                    e222.printStackTrace();
                }
                if (str.length() > 15) {
                    return "[话题分享]" + str.substring(0, 15);
                }
                return "[话题分享]" + str;
            } else if (this.type == 27) {
                str = "";
                try {
                    str = new JSONObject(this.data).optString("plainText");
                } catch (JSONException e2222) {
                    e2222.printStackTrace();
                }
                if (str.length() > 15) {
                    return "[动态分享]" + str.substring(0, 15);
                }
                return "[动态分享]" + str;
            } else if (this.type == 302) {
                return "[" + ((String) getNewMemberJoinInfo().second) + "]";
            } else {
                if (this.type == 301) {
                    return "[系统消息]";
                }
                if (this.type == 26) {
                    str = "";
                    try {
                        str = new JSONObject(this.data).optString("content");
                    } catch (JSONException e22222) {
                        e22222.printStackTrace();
                    }
                    if (TextUtils.isEmpty(str)) {
                        return "我开始新的直播啦，快来围观~";
                    }
                    return str;
                } else if (this.type == 25) {
                    String str2 = "";
                    str = "";
                    try {
                        JSONObject jSONObject = new JSONObject(this.data);
                        optString = jSONObject.optString("title");
                        try {
                            str = jSONObject.optString("content");
                        } catch (JSONException e4) {
                            e = e4;
                            e.printStackTrace();
                            if (TextUtils.isEmpty(optString)) {
                                return optString;
                            }
                            if (TextUtils.isEmpty(str)) {
                                return "我开始新的直播啦，快来围观~";
                            }
                            return str;
                        }
                    } catch (JSONException e222222) {
                        JSONException jSONException = e222222;
                        optString = str2;
                        e = jSONException;
                        e.printStackTrace();
                        if (TextUtils.isEmpty(optString)) {
                            return optString;
                        }
                        if (TextUtils.isEmpty(str)) {
                            return str;
                        }
                        return "我开始新的直播啦，快来围观~";
                    }
                    if (TextUtils.isEmpty(optString)) {
                        return optString;
                    }
                    if (TextUtils.isEmpty(str)) {
                        return "我开始新的直播啦，快来围观~";
                    }
                    return str;
                } else if (this.type == 28) {
                    str = "";
                    try {
                        str = new JSONObject(this.data).optString("text");
                    } catch (JSONException e2222222) {
                        e2222222.printStackTrace();
                    }
                    if (TextUtils.isEmpty(str)) {
                        return "恭喜您获得一枚新勋章！点击查看";
                    }
                    return str;
                } else if (this.type == 30) {
                    str = "";
                    try {
                        return new JSONObject(this.data).optString("title");
                    } catch (Exception e322) {
                        e322.printStackTrace();
                        return str;
                    }
                } else if (this.type == 31) {
                    str = "";
                    try {
                        str = new JSONObject(this.data).optString("title");
                    } catch (Exception e3222) {
                        e3222.printStackTrace();
                    }
                    return "[糗闻分享]" + str;
                } else if (this.type == 32) {
                    str = "";
                    try {
                        JSONObject jSONObject2 = new JSONObject(this.data);
                        if (jSONObject2.has("t")) {
                            CharSequence optString2 = jSONObject2.optString("t");
                            if (TextUtils.equals(optString2, SpringFestivalUtil.HAMMER_QSJX)) {
                                str = jSONObject2.optString("content");
                            } else if (TextUtils.equals(optString2, SpringFestivalUtil.HAMMER)) {
                                str = jSONObject2.optString("title");
                            }
                        }
                    } catch (Exception e32222) {
                        e32222.printStackTrace();
                    }
                    return "[春节活动]" + str;
                } else if (this.type == 33) {
                    r0 = getLaiseeInfo();
                    return "[糗百红包]" + (r0 == null ? "" : r0.content);
                } else if (this.type == 38) {
                    r0 = getLaiseeInfo();
                    return "[语音红包]" + (r0 == null ? "" : r0.content);
                } else if (this.type == 35) {
                    str = "";
                    try {
                        str = new JSONObject(EncryptDecryptUtil.processDecrypt(new JSONObject(this.data).optString("data"))).optString("title");
                    } catch (Exception e322222) {
                        e322222.printStackTrace();
                    }
                    return "[糗百红包]" + str;
                } else if (this.type == 34) {
                    LaiseeImLog laiseeLog = getLaiseeLog();
                    if (laiseeLog != null) {
                        return laiseeLog.getDesc();
                    }
                    return "[糗百红包领取]";
                } else if (this.type == 36) {
                    return "[糗事精选分享]" + getQsjxInfo().title;
                } else {
                    if (this.type == 37) {
                        return "[糗百爆社]" + getQiushiTopicInfo().content;
                    }
                    if (this.type != 39) {
                        return "[未知消息，升级后查看]";
                    }
                    DeeplinkInfo deeplinkInfo = getDeeplinkInfo();
                    str = "新消息提醒";
                    if (deeplinkInfo != null) {
                        return deeplinkInfo.content;
                    }
                    return str;
                }
            }
        }
    }

    public InviteInfo getInviteInfo() {
        if (this.type != 203) {
            return null;
        }
        if (this.tag == null) {
            try {
                this.tag = new InviteInfo(new JSONObject(this.data));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return (InviteInfo) this.tag;
    }

    public GroupNotice getGroupNotice() {
        if (this.type != 202) {
            return null;
        }
        if (this.tag == null) {
            GroupNotice groupNotice = new GroupNotice(this.time);
            groupNotice.parse(this.data, this.msgid);
            this.tag = groupNotice;
        }
        return (GroupNotice) this.tag;
    }

    public GroupSystemMsg getGroupSystemMsg() {
        if (this.type != 301) {
            return null;
        }
        if (this.tag == null) {
            GroupSystemMsg groupSystemMsg = new GroupSystemMsg();
            groupSystemMsg.parse(this.data);
            this.tag = groupSystemMsg;
        }
        return (GroupSystemMsg) this.tag;
    }

    public Pair<BaseUserInfo, String> getNewMemberJoinInfo() {
        if (this.type != 302) {
            return null;
        }
        if (this.tag == null) {
            LogUtil.d("解析msg里面的data");
            BaseUserInfo baseUserInfo = new BaseUserInfo();
            try {
                JSONObject jSONObject = new JSONObject(this.data);
                JSONObject optJSONObject = jSONObject.optJSONObject("user");
                String optString = jSONObject.optString("title");
                baseUserInfo.parseBaseInfo(optJSONObject);
                this.tag = new Pair(baseUserInfo, optString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return (Pair) this.tag;
    }

    public ShareMsgItem getShareMsgItem() {
        if (this.type != 23 && this.type != 29) {
            return null;
        }
        if (this.tag == null) {
            try {
                this.tag = ShareMsgItem.parseJson(new JSONObject(this.data));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return (ShareMsgItem) this.tag;
    }

    public LaiseeImInfo getLaiseeInfo() {
        if (this.type != 33 && this.type != 38) {
            return null;
        }
        if (this.tag == null) {
            LaiseeImInfo laiseeImInfo = new LaiseeImInfo();
            laiseeImInfo.parseEncryptJson(this.data);
            this.tag = laiseeImInfo;
        }
        return (LaiseeImInfo) this.tag;
    }

    public LaiseeImLog getLaiseeLog() {
        if (this.type != 34) {
            return null;
        }
        LaiseeImLog laiseeImLog = (LaiseeImLog) this.tag;
        if (laiseeImLog == null) {
            laiseeImLog = new LaiseeImLog();
            this.tag = laiseeImLog;
        }
        if (TextUtils.isEmpty(this.data)) {
            return laiseeImLog;
        }
        laiseeImLog.parseEncryptJson(this.data);
        return laiseeImLog;
    }

    public Qsjx getQsjxInfo() {
        if (this.type != 36) {
            return null;
        }
        Qsjx qsjx = (Qsjx) this.tag;
        if (qsjx == null) {
            qsjx = new Qsjx();
            this.tag = qsjx;
        }
        if (TextUtils.isEmpty(this.data)) {
            return qsjx;
        }
        try {
            qsjx.fromMsgJson(new JSONObject(this.data));
            return qsjx;
        } catch (JSONException e) {
            e.printStackTrace();
            return qsjx;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return qsjx;
        }
    }

    public QiushiTopic getQiushiTopicInfo() {
        if (this.type != 37) {
            return null;
        }
        QiushiTopic qiushiTopic = (QiushiTopic) this.tag;
        if (qiushiTopic == null) {
            qiushiTopic = new QiushiTopic();
            this.tag = qiushiTopic;
        }
        if (TextUtils.isEmpty(this.data)) {
            return qiushiTopic;
        }
        try {
            qiushiTopic.fromImJson(new JSONObject(this.data));
            return qiushiTopic;
        } catch (JSONException e) {
            e.printStackTrace();
            return qiushiTopic;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return qiushiTopic;
        }
    }

    public DeeplinkInfo getDeeplinkInfo() {
        if (this.type != 39) {
            return null;
        }
        DeeplinkInfo deeplinkInfo = (DeeplinkInfo) this.tag;
        if (deeplinkInfo == null) {
            deeplinkInfo = new DeeplinkInfo();
            this.tag = deeplinkInfo;
        }
        if (TextUtils.isEmpty(this.data)) {
            return deeplinkInfo;
        }
        try {
            deeplinkInfo.parseJson(new JSONObject(this.data));
            return deeplinkInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return deeplinkInfo;
        }
    }

    public String getFromNick() {
        return this.fromnick;
    }

    public String getFromIcon() {
        return this.fromicon;
    }

    public boolean isDisconnectMsg() {
        return this.type == 105;
    }

    public boolean isWith(String str) {
        return (this.from != null && this.from.equals(str)) || (this.to != null && this.to.equals(str));
    }

    public boolean isGroup(String str) {
        return this.gid != null && this.gid.equals(str);
    }

    public boolean isIncomingMsg() {
        return this.inout == 1;
    }

    public String genMsgId() {
        if (TextUtils.isEmpty(this.msgid) || this.msgid.contains("_")) {
            this.msgid = Md5.MD5_16(String.format("%s_%s_%s", new Object[]{this.uid, this.data, Long.valueOf(this.time)}));
        }
        return this.msgid;
    }

    public String _genMsgId() {
        return Md5.MD5_16(String.format("%s_%s_%s", new Object[]{this.uid, this.data, Long.valueOf(this.time)}));
    }

    public void parseFromJSONObject(JSONObject jSONObject) {
        LogUtil.d("开始解析数据");
        JSONUtil.parseFromJSONObject(jSONObject, this);
        JSONArray optJSONArray = jSONObject.optJSONArray("msgids");
        if (optJSONArray != null) {
            this.msgids = new LinkedList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.msgids.add(optJSONArray.optString(i));
            }
        }
        if (this.type == 33) {
            getLaiseeInfo();
        }
    }

    public String toString() {
        return "ChatMsg{usertype=" + this.usertype + ", data='" + this.data + '\'' + ", type=" + this.type + ", from='" + this.from + '\'' + ", to='" + this.to + '\'' + ", gid='" + this.gid + '\'' + ", status=" + this.status + ", time=" + this.time + ", inout=" + this.inout + ", uid='" + this.uid + '\'' + ", msgid='" + this.msgid + '\'' + ", ex_create=" + this.ex_create + ", notify=" + this.notify + ", at='" + this.at + '\'' + ", fromage=" + this.fromage + ", fromgender='" + this.fromgender + '\'' + ", msgids=" + this.msgids + ", msgsrc='" + this.msgsrc + '\'' + ", iscontent=" + this.iscontent + ", fromnick='" + this.fromnick + '\'' + ", fromicon='" + this.fromicon + '\'' + ", gnick='" + this.gnick + '\'' + ", gicon='" + this.gicon + '\'' + ", mType=" + this.mType + ", tag=" + this.tag + ", dbid=" + this.dbid + ", showTime=" + this.showTime + ", isLike=" + this.isLike + ", totalLikeNum=" + this.totalLikeNum + ", showType=" + this.showType + ", seq_type='" + this.seq_type + '\'' + ", this_seqid=" + this.this_seqid + ", pre_seqid=" + this.pre_seqid + '}';
    }

    @SuppressLint({"DefaultLocale"})
    public String getTimeStr() {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(this.time);
        boolean isSameDay = TimeUtils.isSameDay(instance, instance2);
        String format = String.format("%s:%02d", new Object[]{Integer.valueOf(instance2.get(11)), Integer.valueOf(instance2.get(12))});
        if (isSameDay) {
            return format;
        }
        if (TimeUtils.isYesterDay(instance, instance2)) {
            return String.format("昨天 %s", new Object[]{format});
        } else if (!TimeUtils.inSameWeek(instance, instance2)) {
            return new SimpleDateFormat("yy-MM-dd HH:mm").format(new Date(instance2.getTimeInMillis()));
        } else {
            return String.format("%s %s", new Object[]{TimeUtils.getDayOfWeek(instance2), format});
        }
    }

    public boolean isAtAll() {
        if (!TextUtils.isEmpty(this.at)) {
            this.at = ',' + this.at.replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "") + ',';
            return this.at.contains(",all,");
        } else if (TextUtils.isEmpty(this.data)) {
            return false;
        } else {
            return this.data.contains("@全体成员");
        }
    }
}
