package qsbk.app.model;

import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.utils.json.JsonKeyName;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.QiuyouCircleNotificationItemView.Type;

public class Article extends QbBean {
    public static final String FORMAT_GIF = "gif";
    public static final String FORMAT_IMAGE = "image";
    public static final String FORMAT_MULTI = "multi";
    public static final String FORMAT_VIDEO = "video";
    public static final String FORMAT_WORD = "word";
    public static final int STATE_PUBLISHING = 0;
    public static final int STATE_PUBLISH_FAILED = 1;
    private static final Random a = new Random();
    public int absPicHeight;
    public String absPicPath;
    public int absPicWidth;
    public boolean allow_comment;
    public boolean anonymous;
    public int comment_count;
    public String content;
    public long created_at;
    public String currentImagePath;
    public int display;
    public String editUuid;
    public String filePath;
    public String format;
    public int from_topic;
    @JsonKeyName("high_url")
    public String high;
    public Comment hotComment;
    public String id;
    public String image;
    public ArrayList<ArticleImage> imageInfos = new ArrayList();
    public String image_format;
    public String image_height;
    public ImageSizes image_size;
    public String image_width;
    public int imgsrc;
    public boolean isPublishArticle;
    public boolean is_mine;
    public String location;
    public String login;
    @JsonKeyName("loop")
    public long loop;
    @JsonKeyName("low_url")
    public String low;
    public String mHashOrigin;
    public boolean mIsFromLocal;
    public boolean mIsVideoFacingFront;
    public int mVideoHeight;
    public int mVideoWidth;
    public Location publish_location;
    public long published_at;
    public String qiniuUuid;
    public QiushiTopic qiushiTopic;
    public Integer random;
    public String report_reason;
    public String screen_height;
    public String screen_width;
    public String selectedPath;
    public int shareCount;
    public String source;
    public String state;
    public int stateExtra = 0;
    public String tag;
    public String uploadFile;
    public String user_icon;
    public String user_id;
    public String uuid;
    public int vote_down;
    public int vote_up;

    public Article(JSONObject jSONObject) throws QiushibaikeException {
        a(jSONObject);
        this.random = Integer.valueOf(Util.getRandom());
    }

    public static JSONObject toJSONObjectToSaveLocal(Article article) throws JSONException {
        if (article == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("format", article.format);
        jSONObject.put("content", article.content);
        jSONObject.put("uuid", article.uuid);
        jSONObject.put("anonymous", article.anonymous);
        jSONObject.put("image_height", article.image_height);
        jSONObject.put("image_width", article.image_width);
        jSONObject.put(QsbkDatabase.CREATE_AT, article.created_at);
        jSONObject.put("state", article.state);
        if (!TextUtils.isEmpty(article.image_format)) {
            jSONObject.put("image_format", article.image_format);
        }
        if (article.publish_location != null) {
            jSONObject.put("publish_location", article.publish_location);
        }
        jSONObject.put("display", article.display);
        jSONObject.put("screen_height", article.screen_height);
        jSONObject.put("screen_width", article.screen_width);
        jSONObject.put("isPublishArticle", article.isPublishArticle);
        if (!TextUtils.isEmpty(article.user_id)) {
            jSONObject.put("user_id", article.user_id);
        }
        if (!TextUtils.isEmpty(article.login)) {
            jSONObject.put(QsbkDatabase.LOGIN, article.login);
        }
        if (!TextUtils.isEmpty(article.user_icon)) {
            jSONObject.put("user_icon", article.user_icon);
        }
        if (article.isPublishArticle) {
            jSONObject.put("allow_comment", article.allow_comment);
        }
        if (!TextUtils.isEmpty(article.uploadFile)) {
            jSONObject.put("uploadFile", article.uploadFile);
        }
        if (!TextUtils.isEmpty(article.selectedPath)) {
            jSONObject.put("selectedPath", article.selectedPath);
        }
        if (!TextUtils.isEmpty(article.currentImagePath)) {
            jSONObject.put("currentImagePath", article.currentImagePath);
        }
        if (!TextUtils.isEmpty(article.qiniuUuid)) {
            jSONObject.put("qiniuUuid", article.qiniuUuid);
        }
        if (!TextUtils.isEmpty(article.source)) {
            jSONObject.put("source", article.source);
        }
        if (!TextUtils.isEmpty(article.mHashOrigin)) {
            jSONObject.put("mHashOrigin", article.mHashOrigin);
        }
        if (!TextUtils.isEmpty(article.editUuid)) {
            jSONObject.put("editUuid", article.editUuid);
        }
        if (!TextUtils.isEmpty(article.filePath)) {
            jSONObject.put("filePath", article.filePath);
        }
        jSONObject.put("mVideoWidth", article.mVideoWidth);
        jSONObject.put("mVideoHeight", article.mVideoHeight);
        jSONObject.put("imgsrc", article.imgsrc);
        jSONObject.put("from_topic", article.from_topic);
        jSONObject.put("mIsFromLocal", article.mIsFromLocal);
        jSONObject.put("mIsVideoFacingFront", article.mIsVideoFacingFront);
        jSONObject.put("stateExtra", article.stateExtra);
        if (article.hotComment != null) {
            jSONObject.put("hot_comment", article.hotComment.toJson());
        }
        if (article.qiushiTopic != null) {
            jSONObject.put("topic", article.qiushiTopic.toJson());
        }
        if (article.imageInfos != null && article.imageInfos.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = article.imageInfos.iterator();
            while (it.hasNext()) {
                jSONArray.put(((ArticleImage) it.next()).toJson());
            }
            jSONObject.put("attachments", jSONArray);
        }
        return jSONObject;
    }

    public static Article parseJsonFromLocal(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            Article article = new Article();
            article.format = jSONObject.optString("format");
            article.content = jSONObject.getString("content");
            article.state = jSONObject.optString("state", ManageMyContentsAdapter.NEW_PUBLISH);
            article.filePath = jSONObject.optString("filePath");
            article.uuid = jSONObject.getString("uuid");
            article.user_id = jSONObject.optString("user_id");
            article.qiniuUuid = jSONObject.optString("qiniuUuid");
            article.mVideoHeight = jSONObject.optInt("mVideoHeight");
            article.mVideoWidth = jSONObject.optInt("mVideoWidth");
            article.anonymous = jSONObject.getBoolean("anonymous");
            article.user_icon = jSONObject.optString("user_icon");
            article.login = jSONObject.optString(QsbkDatabase.LOGIN);
            article.created_at = (long) jSONObject.optInt(QsbkDatabase.CREATE_AT);
            article.image_height = jSONObject.optString("image_height");
            article.image_width = jSONObject.optString("image_width");
            article.image_format = jSONObject.optString("image_format");
            article.stateExtra = jSONObject.optInt("stateExtra");
            JSONObject optJSONObject = jSONObject.optJSONObject("publish_location");
            if (optJSONObject != null) {
                Location location = new Location();
                location.district = optJSONObject.optString("district");
                CharSequence optString = optJSONObject.optString(ParamKey.LONGITUDE);
                if (!TextUtils.isEmpty(optString)) {
                    location.longitude = Double.parseDouble(optString.trim());
                }
                String optString2 = optJSONObject.optString(ParamKey.LATITUDE);
                if (!TextUtils.isEmpty(optString)) {
                    location.latitude = Double.parseDouble(optString2.trim());
                }
                location.city = optJSONObject.optString("city");
                article.publish_location = location;
            }
            article.display = jSONObject.optInt("display");
            article.isPublishArticle = jSONObject.optBoolean("isPublishArticle");
            article.allow_comment = jSONObject.optBoolean("allow_comment");
            article.from_topic = jSONObject.optInt("from_topic");
            article.imgsrc = jSONObject.optInt("imgsrc");
            article.uploadFile = jSONObject.optString("uploadFile");
            article.editUuid = jSONObject.optString("editUuid");
            article.source = jSONObject.optString("source");
            article.mHashOrigin = jSONObject.optString("mHashOrigin");
            article.uploadFile = jSONObject.optString("uploadFile");
            article.selectedPath = jSONObject.optString("selectedPath");
            article.currentImagePath = jSONObject.optString("currentImagePath");
            article.mIsFromLocal = jSONObject.optBoolean("mIsFromLocal");
            article.mIsVideoFacingFront = jSONObject.optBoolean("mIsVideoFacingFront");
            optJSONObject = jSONObject.optJSONObject("hot_comment");
            if (optJSONObject != null) {
                article.hotComment = Comment.newInstance(optJSONObject);
            }
            optJSONObject = jSONObject.optJSONObject("topic");
            if (optJSONObject != null) {
                QiushiTopic qiushiTopic = new QiushiTopic();
                qiushiTopic.constructJson(optJSONObject);
                article.qiushiTopic = qiushiTopic;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("attachments");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        arrayList.add(new ArticleImage(jSONObject2));
                    }
                }
                article.imageInfos = arrayList;
            }
            return article;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void a(JSONObject jSONObject) throws QiushibaikeException {
        try {
            this.id = jSONObject.getString("id");
            this.content = jSONObject.getString("content");
            this.is_mine = jSONObject.optBoolean("is_mine", false);
            if (!jSONObject.isNull("comments_count")) {
                this.comment_count = jSONObject.getInt("comments_count");
            }
            if (!jSONObject.isNull("state")) {
                this.state = jSONObject.getString("state");
            }
            this.shareCount = jSONObject.optInt("share_count");
            if (!jSONObject.isNull("reason")) {
                this.report_reason = jSONObject.getString("reason");
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(QsbkDatabase.VOTE_TABLE_NAME);
            if (optJSONObject != null) {
                if (!optJSONObject.isNull("up")) {
                    this.vote_up = optJSONObject.getInt("up");
                }
                if (!optJSONObject.isNull("down")) {
                    this.vote_down = optJSONObject.getInt("down");
                }
            }
            if (!jSONObject.isNull(QsbkDatabase.CREATE_AT)) {
                this.created_at = jSONObject.getLong(QsbkDatabase.CREATE_AT);
            }
            this.format = jSONObject.optString("format");
            if (!jSONObject.isNull("image_size")) {
                this.image_size = ImageSizes.newImageSizes(jSONObject.optJSONObject("image_size"));
            }
            if (!jSONObject.isNull("attachments")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("attachments");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        this.imageInfos.add(new ArticleImage(optJSONArray.optJSONObject(i)));
                    }
                }
            } else if (!jSONObject.isNull("image")) {
                this.image = jSONObject.getString("image");
                if (!TextUtils.isEmpty(this.image)) {
                    ArticleImage articleImage = new ArticleImage();
                    articleImage.mediaFormat = MediaFormat.getMediaFormatFromNetwork(this.format);
                    articleImage.url = QsbkApp.absoluteUrlOfMediumContentImage(this.id, this.image);
                    if (this.image_size != null) {
                        ImageSize mediumSize = this.image_size.mediumSize();
                        if (mediumSize != null) {
                            articleImage.width = mediumSize.getWidth();
                            articleImage.height = mediumSize.getHeight();
                        }
                        if (articleImage.mediaFormat == MediaFormat.IMAGE_STATIC && Util.isLongImage(articleImage.width, articleImage.height)) {
                            articleImage.mediaFormat = MediaFormat.IMAGE_LONG;
                        }
                    }
                    switch (a.a[articleImage.mediaFormat.ordinal()]) {
                        case 1:
                            articleImage.url = ArticleImage.getCorrectUrl(jSONObject.optString("pic_url"));
                            break;
                        case 2:
                        case 3:
                            articleImage.url = ArticleImage.getCorrectUrl(jSONObject.optString("pic_url"));
                            articleImage.videoLowUrl = ArticleImage.getCorrectUrl(jSONObject.optString("low_url"));
                            articleImage.videoHighUrl = ArticleImage.getCorrectUrl(jSONObject.optString("high_url"));
                            break;
                    }
                    this.imageInfos.add(articleImage);
                }
            }
            if (!jSONObject.isNull("allow_comment")) {
                this.allow_comment = jSONObject.getBoolean("allow_comment");
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("user");
            if (optJSONObject2 != null) {
                this.user_id = optJSONObject2.getString("id");
                if (!optJSONObject2.isNull(QsbkDatabase.LOGIN)) {
                    this.login = optJSONObject2.getString(QsbkDatabase.LOGIN);
                    if (!TextUtils.isEmpty(this.login)) {
                        this.login = this.login.trim();
                    }
                }
                if (!optJSONObject2.isNull("icon")) {
                    this.user_icon = optJSONObject2.getString("icon");
                }
                this.anonymous = false;
            } else {
                this.anonymous = true;
            }
            if (!jSONObject.isNull("published_at")) {
                this.published_at = jSONObject.getLong("published_at");
            }
            if (!jSONObject.isNull("location")) {
                this.location = jSONObject.getString("location");
            }
            this.low = jSONObject.optString("low_url");
            this.high = jSONObject.optString("high_url");
            this.loop = jSONObject.optLong(Type.QIUSHI_VIDEO_LOOP);
            this.absPicPath = jSONObject.optString("pic_url");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("pic_size");
            if (optJSONArray2 != null) {
                this.absPicWidth = optJSONArray2.optInt(0);
                this.absPicHeight = optJSONArray2.optInt(1);
            }
            optJSONObject2 = jSONObject.optJSONObject("hot_comment");
            if (optJSONObject2 != null) {
                this.hotComment = Comment.newInstance(optJSONObject2);
            }
            optJSONObject2 = jSONObject.optJSONObject("topic");
            if (optJSONObject2 != null) {
                this.qiushiTopic = new QiushiTopic();
                this.qiushiTopic.constructJson(optJSONObject2);
            }
            this.tag = jSONObject.optString(VideoInListHelper.TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", this.id);
        jSONObject2.put("is_mine", this.is_mine);
        jSONObject2.put("content", this.content);
        jSONObject2.put("comments_count", this.comment_count);
        jSONObject2.put("share_count", this.shareCount);
        jSONObject2.put("state", this.state);
        jSONObject2.put(QsbkDatabase.CREATE_AT, this.created_at);
        jSONObject2.put("image", this.image);
        jSONObject2.put("allow_comment", this.allow_comment);
        jSONObject2.put("published_at", this.published_at);
        if (this.anonymous) {
            jSONObject2.put("user", null);
        } else {
            jSONObject = new JSONObject();
            jSONObject.put("id", this.user_id);
            jSONObject.put(QsbkDatabase.LOGIN, this.login);
            jSONObject.put("icon", this.user_icon);
            jSONObject2.put("user", jSONObject);
        }
        jSONObject = new JSONObject();
        jSONObject.put("up", this.vote_up);
        jSONObject.put("down", this.vote_down);
        jSONObject2.put(QsbkDatabase.VOTE_TABLE_NAME, jSONObject);
        jSONObject2.put("image_size", ImageSizes.toJSONObject(this.image_size));
        jSONObject2.put("location", this.location);
        jSONObject2.put("low_url", this.low);
        jSONObject2.put("high_url", this.high);
        jSONObject2.put(Type.QIUSHI_VIDEO_LOOP, this.loop);
        jSONObject2.put("pic_url", this.absPicPath);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.absPicWidth);
        jSONArray.put(this.absPicHeight);
        jSONObject2.put("pic_size", jSONArray);
        if (this.hotComment != null) {
            jSONObject2.put("hot_comment", this.hotComment.toJson());
        }
        if (this.qiushiTopic != null) {
            jSONObject2.put("topic", this.qiushiTopic.toJson());
        }
        jSONObject2.put("format", this.format);
        jSONObject2.put(VideoInListHelper.TAG, this.tag);
        return jSONObject2;
    }

    public boolean isRssArticle() {
        return this instanceof RssArticle;
    }

    public boolean isVideoArticle() {
        return "video".equals(this.format) || ((a() && this.imageInfos != null && this.imageInfos.size() > 0 && ((ArticleImage) this.imageInfos.get(0)).mediaFormat == MediaFormat.VIDEO) || !("gif".equals(this.format) || TextUtils.isEmpty(this.low) || TextUtils.isEmpty(this.high) || TextUtils.isEmpty(this.absPicPath)));
    }

    public boolean isGIFArticle() {
        return (!"gif".equals(this.format) || TextUtils.isEmpty(this.absPicPath) || ((TextUtils.isEmpty(this.low) || TextUtils.isEmpty(this.high)) && TextUtils.isEmpty(this.filePath))) ? false : true;
    }

    public boolean isImageArticle() {
        return ((isVideoArticle() || this.imageInfos == null || this.imageInfos.size() <= 0) && (isVideoArticle() || TextUtils.isEmpty(this.image))) ? false : true;
    }

    private boolean a() {
        return FORMAT_MULTI.equals(this.format);
    }

    public String getLoopString() {
        if (this.loop <= 0) {
            return "";
        }
        String str = UIHelper.getDot() + "再生 %1$s";
        Object[] objArr = new Object[1];
        objArr[0] = this.loop < 10000 ? String.valueOf(this.loop) : (this.loop / 10000) + "w";
        return String.format(str, objArr);
    }

    public int getDisplayLaugth() {
        return Math.max(this.vote_up + this.vote_down, 0);
    }

    public int generateLoopRandom() {
        if (isGIFArticle()) {
            return a.nextInt(3) + 1;
        }
        return a.nextInt(10) + 1;
    }

    public String getVideoUrl() {
        if (TextUtils.isEmpty(this.high) && TextUtils.isEmpty(this.low) && !TextUtils.isEmpty(this.filePath)) {
            return this.filePath;
        }
        if (this.imageInfos.size() <= 0 || ((ArticleImage) this.imageInfos.get(0)).mediaFormat != MediaFormat.VIDEO) {
            return HttpUtils.isWifi(QsbkApp.mContext) ? this.high : this.low;
        } else {
            return ((ArticleImage) this.imageInfos.get(0)).getVideoUrl();
        }
    }

    public String getImageUrl() {
        if (this.imageInfos == null || this.imageInfos.size() <= 0) {
            return QsbkApp.absoluteUrlOfMediumContentImage(this.id, this.image);
        }
        return ((ArticleImage) this.imageInfos.get(0)).getImageUrl();
    }

    public boolean isWordsOnly() {
        return (isVideoArticle() || isImageArticle() || isGIFArticle()) ? false : true;
    }

    public void updateWith(Article article) {
        this.comment_count = article.comment_count;
        this.loop = article.loop;
        this.vote_down = article.vote_down;
        this.vote_up = article.vote_up;
        this.shareCount = article.shareCount;
    }

    public String getContent() {
        return this.content;
    }

    public int getVideoDuration() {
        if (this.image_size != null) {
            if (this.image_size.mediumSize() != null) {
                return this.image_size.mediumSize().getSize();
            }
            if (this.image_size.smallSize() != null) {
                return this.image_size.smallSize().getSize();
            }
            return 0;
        } else if (this.imageInfos.size() > 0) {
            return ((ArticleImage) this.imageInfos.get(0)).getVideoDuration();
        } else {
            return 0;
        }
    }

    public String getVideoDurationText() {
        if (getVideoDuration() < 0) {
            return "--:--";
        }
        int videoDuration = getVideoDuration() / 60;
        int videoDuration2 = getVideoDuration() % 60;
        return String.format("%d:%02d", new Object[]{Integer.valueOf(videoDuration), Integer.valueOf(videoDuration2)});
    }

    public int[] getVideoWidthAndHeight() {
        int[] iArr = new int[2];
        if (this.absPicWidth != 0 && this.absPicHeight != 0) {
            iArr[0] = this.absPicWidth;
            iArr[1] = this.absPicHeight;
        } else if (this.image_size != null) {
            if (this.image_size.mediumSize() != null) {
                iArr[0] = this.image_size.mediumSize().getWidth();
                iArr[1] = this.image_size.mediumSize().getHeight();
            } else if (this.image_size.smallSize() != null) {
                iArr[0] = this.image_size.smallSize().getWidth();
                iArr[1] = this.image_size.smallSize().getHeight();
            }
        } else if (this.imageInfos.size() > 0) {
            ImageInfo imageInfo = (ImageInfo) this.imageInfos.get(0);
            iArr[0] = imageInfo.width;
            iArr[1] = imageInfo.height;
        } else {
            iArr[0] = this.mVideoWidth;
            iArr[1] = this.mVideoHeight;
        }
        return iArr;
    }

    public int hashCode() {
        if (this.id == null) {
            return 0;
        }
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof Article) && TextUtils.equals(this.id, ((Article) obj).id)) {
            return true;
        }
        return false;
    }

    public String getStatType() {
        if (!(this instanceof RssArticle)) {
            return null;
        }
        RssArticle rssArticle = (RssArticle) this;
        if (TextUtils.isEmpty(rssArticle.type)) {
            return RssArticle.Type.HOT;
        }
        return rssArticle.type;
    }

    public boolean isMine(String str) {
        if (this.anonymous) {
            return this.is_mine;
        }
        if (str != null) {
            return str.equals(this.user_id);
        }
        return false;
    }

    public boolean hasQiushiTopic() {
        return this.qiushiTopic != null;
    }

    public String toString() {
        return "Article{content='" + this.content + '\'' + ", anonymous=" + this.anonymous + ", comment_count=" + this.comment_count + ", state='" + this.state + '\'' + ", report_reason='" + this.report_reason + '\'' + ", vote_up=" + this.vote_up + ", vote_down=" + this.vote_down + ", created_at=" + this.created_at + ", shareCount=" + this.shareCount + ", image='" + this.image + '\'' + ", allow_comment=" + this.allow_comment + ", published_at=" + this.published_at + ", user_id='" + this.user_id + '\'' + ", login='" + this.login + '\'' + ", user_icon='" + this.user_icon + '\'' + ", random=" + this.random + ", image_size=" + this.image_size + ", location='" + this.location + '\'' + ", is_mine=" + this.is_mine + ", uuid='" + this.uuid + '\'' + ", image_width='" + this.image_width + '\'' + ", image_height='" + this.image_height + '\'' + ", image_format='" + this.image_format + '\'' + ", publish_location=" + this.publish_location + ", display=" + this.display + ", screen_width='" + this.screen_width + '\'' + ", screen_height='" + this.screen_height + '\'' + ", uploadFile='" + this.uploadFile + '\'' + ", isPublishArticle=" + this.isPublishArticle + ", selectedPath='" + this.selectedPath + '\'' + ", currentImagePath='" + this.currentImagePath + '\'' + ", qiniuUuid='" + this.qiniuUuid + '\'' + ", source='" + this.source + '\'' + ", mIsFromLocal=" + this.mIsFromLocal + ", mIsVideoFacingFront=" + this.mIsVideoFacingFront + ", mHashOrigin='" + this.mHashOrigin + '\'' + ", mVideoWidth=" + this.mVideoWidth + ", mVideoHeight=" + this.mVideoHeight + ", filePath='" + this.filePath + '\'' + ", low='" + this.low + '\'' + ", high='" + this.high + '\'' + ", loop=" + this.loop + ", absPicWidth=" + this.absPicWidth + ", absPicHeight=" + this.absPicHeight + ", absPicPath='" + this.absPicPath + '\'' + '}';
    }
}
