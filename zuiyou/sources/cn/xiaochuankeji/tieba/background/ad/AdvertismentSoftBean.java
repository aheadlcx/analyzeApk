package cn.xiaochuankeji.tieba.background.ad;

import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public class AdvertismentSoftBean extends AbstractPost implements c, Serializable {
    @JSONField(name = "advert")
    public Advert advert;
    @JSONField(name = "c_type")
    public int cType;
    @JSONField(name = "content")
    public String content;
    @JSONField(name = "ct")
    public long ct;
    @JSONField(name = "down")
    public int down;
    @JSONField(name = "god_reviews")
    public List<GodReview> godReviews;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "imgs")
    public List<Img> imgs;
    @JSONField(name = "likes")
    public int likes;
    @JSONField(name = "member")
    public Member member;
    @JSONField(name = "mid")
    public long mid;
    @JSONField(name = "reviews")
    public int reviews;
    @JSONField(name = "share")
    public int share;
    @JSONField(name = "status")
    public int status;
    @JSONField(name = "tid")
    public long tid;
    @JSONField(name = "topic")
    public Topic topic;
    @JSONField(name = "type")
    public int type;
    @JSONField(name = "up")
    public int up;
    @JSONField(name = "videos")
    public JSONObject videoObjects;
    @JSONField(name = "webpage")
    public WebPageBean webPage;

    public static class Advert implements Serializable {
        @JSONField(name = "adid")
        public long adid;
        @JSONField(name = "ext")
        public Ext ext;
        @JSONField(name = "filter_words")
        public List<Filter> filters;
        @JSONField(name = "label")
        public String label;
    }

    public static class Ext implements Serializable {
        @JSONField(name = "lp_open_url")
        public String lpOpenUrl;
        @JSONField(name = "small_image")
        public String smallImage;
        @JSONField(name = "title")
        public String title;
    }

    public static class Filter implements Serializable {
        @JSONField(name = "id")
        public int id;
        @JSONField(name = "is_selected")
        public boolean isDefaultSelect;
        @JSONField(name = "name")
        public String name;
    }

    public static class GodReview implements Serializable {
        @JSONField(name = "_id")
        public long _id;
        @JSONField(name = "avatar")
        public long avatar;
        @JSONField(name = "ct")
        public long ct;
        @JSONField(name = "disp")
        public long disp;
        @JSONField(name = "down")
        public long down;
        @JSONField(name = "gender")
        public int gender;
        @JSONField(name = "godcheck")
        public int godcheck;
        @JSONField(name = "godt")
        public long godt;
        @JSONField(name = "id")
        public long id;
        @JSONField(name = "isgod")
        public int isgod;
        @JSONField(name = "likes")
        public int likes;
        @JSONField(name = "mid")
        public long mid;
        @JSONField(name = "mname")
        public String mname;
        @JSONField(name = "pid")
        public long pid;
        @JSONField(name = "pos")
        public int pos;
        @JSONField(name = "review")
        public String review;
        @JSONField(name = "score")
        public int score;
        @JSONField(name = "source")
        public String source;
        @JSONField(name = "status")
        public int status;
        @JSONField(name = "svut")
        public long svut;
        @JSONField(name = "up")
        public long up;
        @JSONField(name = "ut")
        public long ut;
    }

    public static class Img implements Serializable {
        @JSONField(name = "fmt")
        public String fmt;
        @JSONField(name = "h")
        public int h;
        @JSONField(name = "id")
        public long id;
        @JSONField(name = "video")
        public int videoStatus;
        @JSONField(name = "w")
        public int w;
    }

    public static class Member implements Serializable {
        @JSONField(name = "atted")
        public int atted;
        @JSONField(name = "atts")
        public int atts;
        @JSONField(name = "avatar")
        public long avatar;
        @JSONField(name = "cover")
        public long cover;
        @JSONField(name = "ct")
        public long ct;
        @JSONField(name = "fans")
        public int fans;
        @JSONField(name = "gender")
        public int gender;
        @JSONField(name = "id")
        public long id;
        @JSONField(name = "isreg")
        public int isReg;
        @JSONField(name = "name")
        public String name;
        @JSONField(name = "sign")
        public String sign;
    }

    public static class Topic implements Serializable {
        @JSONField(name = "addition")
        public String addition;
        @JSONField(name = "admins")
        public List<Integer> admins;
        @JSONField(name = "atts")
        public long atts;
        @JSONField(name = "atts_title")
        public String atts_title;
        @JSONField(name = "click_cb")
        public String click_cb;
        @JSONField(name = "cover")
        public long cover;
        @JSONField(name = "ct")
        public long ct;
        @JSONField(name = "enable_black")
        public int enable_black;
        @JSONField(name = "flag")
        public int flag;
        @JSONField(name = "id")
        public long id;
        @JSONField(name = "list_show")
        public String list_show;
        @JSONField(name = "partid")
        public int partid;
        @JSONField(name = "partners")
        public long partners;
        @JSONField(name = "posts")
        public long posts;
        @JSONField(name = "publish_ctypes")
        public List<Integer> publish_ctypes;
        @JSONField(name = "recruiting")
        public int recruiting;
        @JSONField(name = "share")
        public int share;
        @JSONField(name = "skin")
        public int skin;
        @JSONField(name = "topic")
        public String topic;
        @JSONField(name = "type")
        public int type;
        @JSONField(name = "ut")
        public long ut;
    }

    public static class Video implements Serializable {
        @JSONField(name = "dur")
        public int dur;
        @JSONField(name = "playcnt")
        public int playcnt;
        @JSONField(name = "priority")
        public int priority;
        @JSONField(name = "thumb")
        public long thumb;
        @JSONField(name = "url")
        public String url;
        @JSONField(name = "urlext")
        public String urlext;
        @JSONField(name = "urlsrc")
        public String urlsrc;
        @JSONField(name = "urlwm")
        public String urlwm;
    }

    public class WebPageBean implements Serializable {
        @JSONField(name = "author")
        public String author;
        @JSONField(name = "desc")
        public String describe;
        @JSONField(name = "thumburl")
        public String thumbUrl;
        @JSONField(name = "title")
        public String title;
        @JSONField(name = "url_type")
        public int type;
        @JSONField(name = "url")
        public String url;
    }

    public static LinkedHashMap<String, String> createTediumReason(Advert advert) {
        List list = advert.filters;
        if (list == null || list.size() <= 0) {
            return null;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            linkedHashMap.put(((Filter) list.get(i)).id + "", ((Filter) list.get(i)).name);
        }
        return linkedHashMap;
    }

    public static AdvertismentSoftBean createByJson(org.json.JSONObject jSONObject) {
        return (AdvertismentSoftBean) JSON.parseObject(jSONObject.toString(), AdvertismentSoftBean.class);
    }

    public int classType() {
        return 5;
    }

    public long getCreateTime() {
        return this.ct;
    }

    public int localPostType() {
        return 8;
    }

    public int getShareNum() {
        return this.share;
    }

    public long getId() {
        return this.id;
    }

    public long getMemberId() {
        return this.member.id;
    }

    public void setFollowStatus(int i) {
    }

    public Video getVideoInfo(String str) {
        if (str == null || this.videoObjects == null) {
            return null;
        }
        JSONObject jSONObject = this.videoObjects.getJSONObject(str);
        if (jSONObject != null) {
            return (Video) jSONObject.toJavaObject(Video.class);
        }
        return null;
    }

    public boolean videoPost() {
        if (this.imgs == null || this.imgs.size() <= 0 || getVideoInfo(String.valueOf(((Img) this.imgs.get(0)).id)) == null) {
            return false;
        }
        return true;
    }

    public static PostDataBean ConvertToPostDataBean(AdvertismentSoftBean advertismentSoftBean) {
        return (PostDataBean) JSON.parseObject(JSON.toJSONString(advertismentSoftBean), PostDataBean.class);
    }
}
