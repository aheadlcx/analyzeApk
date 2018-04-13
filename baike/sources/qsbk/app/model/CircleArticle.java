package qsbk.app.model;

import android.text.TextUtils;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.model.CustomButton;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.QiuyouCircleNotificationItemView.Type;

public class CircleArticle extends QbBean {
    public static final int AUDIT_FAILED = 0;
    public static final int AUDIT_ING = 2;
    public static final int AUDIT_PASSED = 1;
    public static final int GIF_SHARE = 13;
    public static final int IMAGE = 1;
    public static final int LINK_SHARE = 9;
    public static final int LIVE_SHARE = 11;
    public static final int NEWS_SHARE = 15;
    public static final int NORMAL = 0;
    public static final int QIUSHI_TOPIC_SHARE = 17;
    public static final int QSJX_SHARE = 16;
    public static final int SHARE = 4;
    public static final int TOPIC = 5;
    public static final int UNSUPPORT = 14;
    public static final int VIDEO = 10;
    public static final int VIDEO_SHARE = 8;
    public static final int VOTE = 2;
    public static final int VOTE_IMAGE = 3;
    public static final int WEB_AD = 18;
    public static final int WEB_SHARE = 12;
    public String adPic = "";
    public String adUrl = "";
    public ArrayList<AtInfo> atInfoTexts = new ArrayList();
    public String ats;
    public int auditStatus = 1;
    public long[] clockedInfo;
    public int commentCount;
    public String content;
    public int createAt;
    public String distance;
    public CircleComment hotComment;
    public String id;
    public boolean isRecommend;
    public boolean isTop;
    public String label;
    public String latitude;
    public int likeCount;
    public boolean liked;
    public int live_origin;
    public String longitude;
    public CircleArticle originalCircleArticle;
    public ArrayList<PicUrl> picUrls;
    public String report_reason;
    public String shareContent;
    public int shareCount;
    public String shareLink;
    public CircleTopic topic;
    public int type;
    public BaseUserInfo user;
    public CircleVideo video;
    public VoteInfo voteInfo;

    public static class CircleVideo implements Serializable {
        public int duration;
        public int height;
        public String highUrl;
        public int loop;
        public String lowUrl;
        public String picUrl;
        public int width;

        protected void a(JSONObject jSONObject) {
            this.loop = jSONObject.optInt(Type.QIUSHI_VIDEO_LOOP);
            this.width = jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH);
            this.height = jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT);
            this.duration = jSONObject.optInt("duration");
            this.picUrl = jSONObject.optString("pic_url");
            this.lowUrl = jSONObject.optString("low_url");
            this.highUrl = jSONObject.optString("high_url");
        }

        protected JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Type.QIUSHI_VIDEO_LOOP, this.loop);
                jSONObject.put(IndexEntry.COLUMN_NAME_WIDTH, this.width);
                jSONObject.put(IndexEntry.COLUMN_NAME_HEIGHT, this.height);
                jSONObject.put("duration", this.duration);
                jSONObject.put("pic_url", this.picUrl);
                jSONObject.put("low_url", this.lowUrl);
                jSONObject.put("high_url", this.highUrl);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }

        public float getAspectRatio() {
            return this.height == 0 ? 1.0f : (1.0f * ((float) this.width)) / ((float) this.height);
        }
    }

    public static class VoteInfo implements Serializable {
        public static final int VOTE_A = 0;
        public static final int VOTE_B = 1;
        public static final int VOTE_NONE = -1;
        public String a;
        public int aCount;
        public String b;
        public int bCount;
        public String id;
        public int vote;

        public static VoteInfo cloneValue(VoteInfo voteInfo, VoteInfo voteInfo2) {
            if (voteInfo == null) {
                voteInfo = new VoteInfo();
            }
            voteInfo.aCount = voteInfo2.aCount;
            voteInfo.a = voteInfo2.a;
            voteInfo.bCount = voteInfo2.bCount;
            voteInfo.b = voteInfo2.b;
            voteInfo.id = voteInfo2.id;
            voteInfo.vote = voteInfo2.vote;
            return voteInfo;
        }

        public static String getVoteDescription(String str, int i) {
            String str2 = "";
            if (i >= 10000) {
                str2 = (i / 10000) + "W";
            } else if (i >= 1000) {
                str2 = (i / 1000) + "K";
            } else {
                str2 = i + "";
            }
            return String.format("%s %s", new Object[]{str, str2});
        }

        public static void uploadVote(String str, int i) {
            String format = String.format(Constants.CIRCLE_ARTICLE_VOTE, new Object[]{str});
            Map hashMap = new HashMap();
            hashMap.put("option", i == 0 ? "a" : CustomButton.POSITION_BOTTOM);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new e(str, i));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
        }

        protected void a(JSONObject jSONObject) {
            try {
                this.id = jSONObject.optString("id");
                this.a = jSONObject.getString("option_a");
                this.b = jSONObject.getString("option_b");
                this.aCount = jSONObject.getInt("oa_count");
                this.bCount = jSONObject.getInt("ob_count");
                this.vote = jSONObject.optInt("voted", -1);
                if (this.vote == 0 && this.aCount == 0) {
                    this.aCount++;
                }
                if (this.vote == 1 && this.bCount == 0) {
                    this.bCount++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public JSONObject toJSONObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.id);
            jSONObject.put("option_a", this.a);
            jSONObject.put("option_b", this.b);
            jSONObject.put("oa_count", this.aCount);
            jSONObject.put("ob_count", this.bCount);
            jSONObject.put("voted", this.vote);
            return jSONObject;
        }

        public void vote(String str, int i) {
            if (this.vote == -1) {
                this.vote = i;
                switch (i) {
                    case 0:
                        this.aCount++;
                        uploadVote(str, i);
                        return;
                    case 1:
                        this.bCount++;
                        uploadVote(str, i);
                        return;
                    default:
                        return;
                }
            }
        }

        public String toString() {
            return "VoteInfo{id='" + this.id + '\'' + ", vote=" + this.vote + ", a='" + this.a + '\'' + ", b='" + this.b + '\'' + ", aCount=" + this.aCount + ", bCount=" + this.bCount + '}';
        }
    }

    public static Object parseJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            if (jSONObject.optInt("type") != 5) {
                return parseAsCircleArticle(jSONObject);
            }
            ArrayList parseTopics = CircleTopicManager.parseTopics(jSONObject);
            int min = Math.min(5, parseTopics.size());
            if (min == 0) {
                return null;
            }
            CircleTopicPackage circleTopicPackage = new CircleTopicPackage();
            circleTopicPackage.topics = new CircleTopic[min];
            circleTopicPackage.picIndexs = new int[min];
            for (int i = 0; i < min; i++) {
                int random = (int) (Math.random() * ((double) parseTopics.size()));
                CircleTopic circleTopic = (CircleTopic) parseTopics.get(random);
                parseTopics.remove(random);
                circleTopicPackage.topics[i] = circleTopic;
                circleTopicPackage.picIndexs[i] = circleTopic.getRandomPicIndex();
            }
            return circleTopicPackage;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CircleArticle parseAsCircleArticle(JSONObject jSONObject) throws JSONException {
        int i = 0;
        int optInt = jSONObject.optInt("type");
        if (optInt == 5) {
            return null;
        }
        JSONObject jSONObject2;
        boolean z;
        CircleArticle circleArticle = new CircleArticle();
        circleArticle.type = optInt;
        circleArticle.id = jSONObject.getString("id");
        circleArticle.content = jSONObject.optString("content");
        if ((circleArticle.isShare() || circleArticle.isAd()) && !TextUtils.isEmpty(circleArticle.content)) {
            jSONObject2 = new JSONObject(circleArticle.content);
            circleArticle.content = jSONObject2.optString("circle_content");
            circleArticle.shareLink = jSONObject2.optString("qiushi_link");
            circleArticle.shareContent = jSONObject2.optString("qiushi_content");
            circleArticle.live_origin = jSONObject2.optInt("live_origin");
            circleArticle.adUrl = jSONObject2.optString("android_url");
            circleArticle.adPic = jSONObject2.optString("pic_url");
        }
        circleArticle.createAt = jSONObject.optInt(QsbkDatabase.CREATE_AT);
        JSONArray optJSONArray = jSONObject.optJSONArray("pic_urls");
        circleArticle.picUrls = new ArrayList();
        optInt = optJSONArray != null ? optJSONArray.length() : 0;
        for (int i2 = 0; i2 < optInt; i2++) {
            PicUrl picUrl = new PicUrl(circleArticle.createAt);
            picUrl.constructJson(optJSONArray.getJSONObject(i2));
            circleArticle.picUrls.add(picUrl);
        }
        jSONObject2 = jSONObject.optJSONObject("topic");
        if (jSONObject2 != null) {
            circleArticle.topic = CircleTopic.parseJson(jSONObject2);
        }
        if (circleArticle.type == 2 || circleArticle.type == 3) {
            jSONObject2 = jSONObject.optJSONObject(QYQShareInfo.TYPE_VOTE);
            if (jSONObject2 != null) {
                circleArticle.voteInfo = new VoteInfo();
                circleArticle.voteInfo.a(jSONObject2);
            }
        } else {
            circleArticle.voteInfo = null;
        }
        if (circleArticle.type == 10) {
            jSONObject2 = jSONObject.optJSONObject("video");
            if (jSONObject2 != null) {
                circleArticle.video = new CircleVideo();
                circleArticle.video.a(jSONObject2);
            }
        }
        jSONObject2 = jSONObject.optJSONObject("user");
        circleArticle.user = new BaseUserInfo();
        if (jSONObject2 != null) {
            circleArticle.user.parseBaseInfo(jSONObject2);
            if (circleArticle.user.isAnonymous() && jSONObject.optBoolean("is_me")) {
                circleArticle.user.isMe = true;
            }
        }
        circleArticle.isTop = jSONObject.optBoolean("is_top");
        circleArticle.isRecommend = jSONObject.optBoolean("is_recommend");
        try {
            optInt = jSONObject.getInt("distance");
            String str = optInt < 0 ? "糗星球" : optInt > 1000 ? (optInt / 1000) + "km" : optInt + "m";
            circleArticle.distance = str;
        } catch (JSONException e) {
            circleArticle.distance = jSONObject.optString("distance");
        }
        circleArticle.longitude = jSONObject.optString(ParamKey.LONGITUDE);
        circleArticle.latitude = jSONObject.optString(ParamKey.LATITUDE);
        circleArticle.likeCount = jSONObject.optInt("like_count");
        if (jSONObject.optInt("liked", 0) == 1) {
            z = true;
        } else {
            z = false;
        }
        circleArticle.liked = z;
        circleArticle.commentCount = jSONObject.optInt("comment_count");
        circleArticle.ats = jSONObject.optString("at");
        circleArticle.shareCount = jSONObject.optInt("share_count");
        circleArticle.auditStatus = jSONObject.optInt("audit", 1);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("at_users");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            for (optInt = 0; optInt < optJSONArray2.length(); optInt++) {
                AtInfo atInfo = new AtInfo();
                atInfo = AtInfo.constructJson(optJSONArray2.getJSONObject(optInt));
                if (atInfo != null) {
                    circleArticle.atInfoTexts.add(atInfo);
                }
            }
        }
        if (circleArticle.topic != null && circleArticle.topic.status == 6) {
            JSONArray optJSONArray3 = jSONObject.optJSONArray("punches");
            if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                circleArticle.clockedInfo = new long[optJSONArray3.length()];
                while (i < optJSONArray3.length()) {
                    circleArticle.clockedInfo[i] = optJSONArray3.getLong(i);
                    i++;
                }
            }
        }
        if (jSONObject.has("article")) {
            jSONObject2 = jSONObject.getJSONObject("article");
            if (jSONObject2 != null) {
                circleArticle.originalCircleArticle = parseAsCircleArticle(jSONObject2);
            }
        }
        jSONObject2 = jSONObject.optJSONObject("hot_comment");
        if (jSONObject2 != null) {
            circleArticle.hotComment = CircleComment.newInstance(jSONObject2);
        }
        if (!jSONObject.isNull("reason")) {
            circleArticle.report_reason = jSONObject.getString("reason");
        }
        circleArticle.label = jSONObject.optString("label");
        return circleArticle;
    }

    public static CircleArticle parseAsHotCommentCircleArticle(JSONObject jSONObject) throws JSONException {
        CircleArticle circleArticle = null;
        if (jSONObject.has("article")) {
            circleArticle = parseAsCircleArticle(jSONObject.optJSONObject("article"));
            if (circleArticle != null) {
                circleArticle.hotComment = CircleComment.newInstance(jSONObject);
            }
        }
        return circleArticle;
    }

    public static JSONObject toJSONObject(CircleArticle circleArticle) {
        int i = 0;
        if (circleArticle == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", circleArticle.type);
            jSONObject.put("content", circleArticle.content);
            jSONObject.put("id", circleArticle.id);
            jSONObject.put("is_recommnd", circleArticle.isRecommend);
            if (circleArticle.isShare() || circleArticle.isAd()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("circle_content", circleArticle.content);
                jSONObject2.put("qiushi_content", circleArticle.shareContent);
                jSONObject2.put("qiushi_link", circleArticle.shareLink);
                jSONObject2.put("pic_url", circleArticle.adPic);
                jSONObject2.put("android_url", circleArticle.adUrl);
                jSONObject.put("content", jSONObject2);
            }
            if (circleArticle.user != null) {
                jSONObject.put("user", circleArticle.user.toJSONObject());
            }
            if (circleArticle.voteInfo != null) {
                jSONObject.put(QYQShareInfo.TYPE_VOTE, circleArticle.voteInfo.toJSONObject());
            }
            if (circleArticle.topic != null) {
                jSONObject.put("topic", CircleTopic.toJson(circleArticle.topic));
            }
            if (circleArticle.picUrls != null && circleArticle.picUrls.size() > 0) {
                int size = circleArticle.picUrls.size();
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < size; i2++) {
                    jSONArray.put(((PicUrl) circleArticle.picUrls.get(i2)).toJSONObject());
                }
                jSONObject.put("pic_urls", jSONArray);
            }
            if (circleArticle.video != null) {
                jSONObject.put("video", circleArticle.video.a());
            }
            jSONObject.put("is_top", circleArticle.isTop);
            jSONObject.put(QsbkDatabase.CREATE_AT, circleArticle.createAt);
            jSONObject.put(ParamKey.LONGITUDE, circleArticle.longitude);
            jSONObject.put(ParamKey.LATITUDE, circleArticle.latitude);
            jSONObject.put("like_count", circleArticle.likeCount);
            jSONObject.put("liked", circleArticle.liked ? 1 : 0);
            jSONObject.put("comment_count", circleArticle.commentCount);
            jSONObject.put("distance", circleArticle.distance);
            jSONObject.put("share_count", circleArticle.shareCount);
            jSONObject.put("at", circleArticle.ats);
            jSONObject.put("audit", circleArticle.auditStatus);
            jSONObject.put("label", circleArticle.label);
            if (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                while (i < jSONArray2.length()) {
                    jSONArray2.put(((AtInfo) circleArticle.atInfoTexts.get(i)).encodeToJsonObject());
                    i++;
                }
                jSONObject.put("atinfos", jSONArray2);
            }
            if (circleArticle.clockedInfo != null && circleArticle.clockedInfo.length > 0) {
                jSONObject.put("punches", circleArticle.clockedInfo);
            }
            if (circleArticle.isForward()) {
                jSONObject.put("article", toJSONObject(circleArticle.originalCircleArticle));
            }
            if (circleArticle.hotComment != null) {
                jSONObject.put("hot_comment", circleArticle.hotComment.toJson());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public boolean isShare() {
        return this.type == 4 || ((this.type >= 6 && this.type <= 9) || this.type == 11 || this.type == 12 || this.type == 13 || this.type == 15 || this.type == 16 || this.type == 17);
    }

    public boolean isAd() {
        return this.type == 18;
    }

    public boolean isClocked() {
        return this.topic != null && this.topic.status == 6;
    }

    public boolean isForward() {
        return this.originalCircleArticle != null;
    }

    public void updateWith(CircleArticle circleArticle) {
        this.voteInfo = circleArticle.voteInfo;
        this.distance = circleArticle.distance;
        this.likeCount = circleArticle.likeCount;
        this.commentCount = circleArticle.commentCount;
        this.shareCount = circleArticle.shareCount;
        if (circleArticle.voteInfo != null) {
            this.voteInfo = VoteInfo.cloneValue(this.voteInfo, circleArticle.voteInfo);
        }
        this.liked = circleArticle.liked;
        if (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0) {
            this.atInfoTexts = circleArticle.atInfoTexts;
        }
    }

    public boolean hasImage() {
        return this.picUrls != null && this.picUrls.size() > 0;
    }

    public int getImageCount() {
        return (this.picUrls == null || isShare() || isForward() || isVideoArticle()) ? 0 : this.picUrls.size();
    }

    public String[] getThubnailList() {
        String[] strArr = new String[getImageCount()];
        for (int i = 0; i < getImageCount(); i++) {
            strArr[i] = QsbkApp.absoluteUrlOfCircleWebpImage(((PicUrl) this.picUrls.get(i)).url, this.createAt);
        }
        return strArr;
    }

    public String[] getBigImageList() {
        String[] strArr = new String[getImageCount()];
        for (int i = 0; i < getImageCount(); i++) {
            String absoluteUrlOfCircleWebpImage = QsbkApp.absoluteUrlOfCircleWebpImage(((PicUrl) this.picUrls.get(i)).url, this.createAt);
            int indexOf = absoluteUrlOfCircleWebpImage.indexOf("?");
            if (indexOf != -1) {
                absoluteUrlOfCircleWebpImage = absoluteUrlOfCircleWebpImage.substring(0, indexOf);
            }
            strArr[i] = absoluteUrlOfCircleWebpImage;
        }
        return strArr;
    }

    public boolean isVideoArticle() {
        return this.type == 10;
    }

    public boolean isUnSupport() {
        return this.type == 14;
    }

    public boolean hasGIF() {
        if (hasImage()) {
            return ((PicUrl) this.picUrls.get(0)).isGIFVideo();
        }
        return false;
    }

    public boolean hasVideo() {
        return (this.video == null || (TextUtils.isEmpty(this.video.lowUrl) && TextUtils.isEmpty(this.video.highUrl))) ? false : true;
    }

    public String getVideoUrl() {
        return TextUtils.isEmpty(this.video.highUrl) ? this.video.lowUrl : this.video.highUrl;
    }

    public int getVideoWidth() {
        return this.video.width;
    }

    public int getVideoHeight() {
        return this.video.height;
    }

    public int getAuditIconResource() {
        switch (this.auditStatus) {
            case 0:
                return UIHelper.isNightTheme() ? R.drawable.my_content_no_pass_night : R.drawable.my_content_no_pass;
            case 2:
                return UIHelper.isNightTheme() ? R.drawable.my_content_waiting_icon_night : R.drawable.my_content_waiting_icon;
            default:
                return UIHelper.isNightTheme() ? R.drawable.my_content_pass_icon_night : R.drawable.my_content_pass_icon;
        }
    }

    public CharSequence getAuditText() {
        switch (this.auditStatus) {
            case 0:
                return "投稿失败";
            case 2:
                return "审核中";
            default:
                return "已通过";
        }
    }

    public int hashCode() {
        return this.id != null ? this.id.hashCode() : super.hashCode();
    }

    public String toString() {
        return toJSONObject(this).toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CircleArticle) {
            return TextUtils.equals(this.id, ((CircleArticle) obj).id);
        }
        return false;
    }
}
