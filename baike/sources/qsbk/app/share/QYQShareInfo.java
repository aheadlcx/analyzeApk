package qsbk.app.share;

import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.PicUrl;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;

public class QYQShareInfo implements Serializable {
    public static final String TYPE_GIF = "gif";
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_LINK = "link";
    public static final String TYPE_LIVE = "live";
    public static final HashMap<String, Integer> TYPE_MAP = new HashMap();
    public static final String TYPE_NEWS = "news";
    public static final String TYPE_QIUSHI_TOPIC = "topic";
    public static final String TYPE_QSJX = "qsjx";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_VOTE = "vote";
    public static final String TYPE_WEB = "web";
    private String a;
    public Article article;
    public CircleArticle circleArticle;
    public String content = "";
    public String link = "";
    public int live_origin;
    public int newsId;
    public String picUrl = "";
    public String type;

    static {
        TYPE_MAP.put("image", Integer.valueOf(4));
        TYPE_MAP.put("text", Integer.valueOf(4));
        TYPE_MAP.put("video", Integer.valueOf(8));
        TYPE_MAP.put("link", Integer.valueOf(9));
        TYPE_MAP.put("web", Integer.valueOf(12));
        TYPE_MAP.put("live", Integer.valueOf(11));
        TYPE_MAP.put(TYPE_VOTE, Integer.valueOf(2));
        TYPE_MAP.put("gif", Integer.valueOf(13));
        TYPE_MAP.put(TYPE_NEWS, Integer.valueOf(15));
        TYPE_MAP.put(TYPE_QSJX, Integer.valueOf(16));
        TYPE_MAP.put("topic", Integer.valueOf(17));
    }

    public QYQShareInfo(ShareMsgItem shareMsgItem, String str) {
        this.picUrl = shareMsgItem.imageUrl;
        this.link = shareMsgItem.link;
        this.content = shareMsgItem.content;
        if (TextUtils.isEmpty(str)) {
            str = "link";
        }
        this.type = str;
        this.live_origin = shareMsgItem.live_origin;
        this.newsId = shareMsgItem.shareFor == 5 ? shareMsgItem.news_id : -1;
        this.a = shareMsgItem.title;
    }

    public QYQShareInfo(Article article) {
        this.article = article;
        if (article.isVideoArticle()) {
            this.picUrl = article.absPicPath;
        } else if (!TextUtils.isEmpty(article.image)) {
            this.picUrl = QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image);
        }
        this.content = article.content;
        this.link = article.id;
        String str = article.isGIFArticle() ? "gif" : article.isVideoArticle() ? "video" : article.isImageArticle() ? "image" : "text";
        this.type = str;
    }

    public QYQShareInfo(CircleArticle circleArticle) {
        this.circleArticle = circleArticle;
        if (circleArticle.isForward()) {
            CircleArticle circleArticle2 = circleArticle.originalCircleArticle;
            this.content = circleArticle2.content;
            this.link = circleArticle2.id;
            switch (circleArticle2.type) {
                case 1:
                    this.type = "image";
                    if (circleArticle2.hasImage()) {
                        this.picUrl = ((PicUrl) circleArticle2.picUrls.get(0)).url;
                        return;
                    }
                    return;
                case 3:
                    if (circleArticle2.hasImage()) {
                        this.picUrl = ((PicUrl) circleArticle2.picUrls.get(0)).url;
                        break;
                    }
                    break;
                case 10:
                    this.type = "video";
                    this.picUrl = circleArticle2.video == null ? "" : circleArticle2.video.picUrl;
                    return;
            }
            this.type = "text";
        } else if (circleArticle.isShare()) {
            this.content = circleArticle.shareContent;
            this.link = circleArticle.shareLink;
            switch (circleArticle.type) {
                case 1:
                    this.type = "image";
                    break;
                case 8:
                    this.type = "video";
                    break;
                case 9:
                    this.type = "link";
                    break;
                case 11:
                    this.type = "live";
                    break;
                case 12:
                    this.type = "web";
                    break;
                case 13:
                    this.type = "gif";
                    break;
                case 15:
                    this.type = TYPE_NEWS;
                    break;
                case 16:
                    this.type = TYPE_QSJX;
                    break;
                default:
                    if (!circleArticle.hasImage()) {
                        this.type = "text";
                        break;
                    } else {
                        this.type = "image";
                        break;
                    }
            }
            if (circleArticle.hasImage()) {
                this.picUrl = ((PicUrl) circleArticle.picUrls.get(0)).url;
            }
        } else {
            this.content = circleArticle.content;
            this.link = circleArticle.id;
            switch (circleArticle.type) {
                case 1:
                    this.type = "image";
                    if (circleArticle.hasImage()) {
                        this.picUrl = ((PicUrl) circleArticle.picUrls.get(0)).url;
                        break;
                    }
                    break;
                case 10:
                    this.type = circleArticle.hasGIF() ? "gif" : "video";
                    this.picUrl = circleArticle.video == null ? "" : circleArticle.video.picUrl;
                    break;
                default:
                    this.type = "text";
                    break;
            }
            if (circleArticle.hasImage()) {
                this.picUrl = ((PicUrl) circleArticle.picUrls.get(0)).url;
            }
        }
    }

    public QYQShareInfo(JSONObject jSONObject) throws QiushibaikeException {
        constructJson(jSONObject);
    }

    public QYQShareInfo(Qsjx qsjx) {
        this.content = qsjx.getShareTitle();
        this.link = qsjx.msgId;
        this.picUrl = qsjx.picUrl;
        this.type = TYPE_QSJX;
    }

    public QYQShareInfo(QiushiTopic qiushiTopic) {
        this.content = qiushiTopic.content;
        this.link = qiushiTopic.id + "";
        this.picUrl = qiushiTopic.icon;
        this.type = "topic";
    }

    public boolean hasArticle() {
        return this.article != null;
    }

    public String getDefaultComment() {
        if ("live".equals(this.type)) {
            return "分享了直播";
        }
        if (TYPE_QSJX.equals(this.type)) {
            return "分享了合集";
        }
        if ("topic".equals(this.type)) {
            return "分享了爆社";
        }
        if (this.circleArticle != null) {
            return "转发了动态";
        }
        if (this.article != null) {
            return "分享了糗事";
        }
        if (this.newsId != -1) {
            return "分享了糗闻";
        }
        if (TextUtils.isEmpty(this.a)) {
            return "分享了";
        }
        return this.a;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(SocialConstants.PARAM_APP_ICON, this.picUrl);
        jSONObject.put("link", this.link);
        jSONObject.put("type", this.type);
        jSONObject.put("content", TextUtils.isEmpty(this.content) ? this.a : this.content);
        if (this.article != null) {
            jSONObject.put("article", this.article.toJSONObject());
        }
        jSONObject.put("live_origin", this.live_origin);
        if (this.circleArticle != null) {
            jSONObject.put("circleArticle", CircleArticle.toJSONObject(this.circleArticle));
        }
        return jSONObject;
    }

    public void constructJson(JSONObject jSONObject) throws QiushibaikeException {
        this.picUrl = jSONObject.optString(SocialConstants.PARAM_APP_ICON);
        this.link = jSONObject.optString("link");
        this.type = jSONObject.optString("type");
        this.content = jSONObject.optString("content");
        this.live_origin = jSONObject.optInt("live_origin");
        try {
            JSONObject optJSONObject;
            if (jSONObject.has("article")) {
                optJSONObject = jSONObject.optJSONObject("article");
                if (optJSONObject != null) {
                    this.article = new Article(optJSONObject);
                }
            }
            if (jSONObject.has("circleArticle")) {
                optJSONObject = jSONObject.optJSONObject("circleArticle");
                if (optJSONObject != null) {
                    this.circleArticle = CircleArticle.parseAsCircleArticle(optJSONObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
