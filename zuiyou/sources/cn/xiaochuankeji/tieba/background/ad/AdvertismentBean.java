package cn.xiaochuankeji.tieba.background.ad;

import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONObject;

public class AdvertismentBean extends AbstractPost implements c, Serializable {
    public static final int AD_STATUS_DELETE = -3;
    public static final int AD_STATUS_NOPASS = -2;
    public static final int AD_STATUS_NORMAL = 1;
    public static final int AD_STATUS_PASS = 2;
    public static final int AD_STATUS_STOP = -1;
    public static final int AD_TYPE_LUODI1 = 2;
    public static final int AD_TYPE_LUODI2 = 3;
    public static final int AD_TYPE_SPLASH = 1;
    public static final int AD_TYPE_TUIGUANG1 = 4;
    public static final int AD_TYPE_TUIGUANG2 = 5;
    @JSONField(name = "filter_words")
    public List<Filter> adFilters;
    @JSONField(name = "label")
    public String adLabel;
    @JSONField(name = "status")
    public int adStatus;
    @JSONField(name = "title")
    public String adTitle;
    @JSONField(name = "ad_type")
    public int adType;
    @JSONField(name = "android_app")
    public AdExtraInfo appExtraInfo;
    @JSONField(name = "appname")
    public String appName;
    @JSONField(name = "impression_url")
    public String attachCbuRL;
    @JSONField(name = "c_type")
    public int c_type;
    @JSONField(name = "click_url")
    public String clickCbURL;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "dsp_cb_extra")
    public String dspCb;
    @JSONField(name = "extra")
    public String extraCallback;
    @JSONField(name = "external")
    public AdFeedExtraInfo feedExtraInfo;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "multimedia")
    public List<AdMultiMedia> media;
    @JSONField(name = "member")
    public AdsMember member;
    @JSONField(name = "is_in_app")
    public boolean needOpen;
    @JSONField(name = "lp_open_url")
    public String openDetailUrl;
    @JSONField(name = "splash")
    public AdMultiMedia splashMedia;
    @JSONField(name = "ut")
    public long updateTime;
    @JSONField(name = "video_play_valid_url")
    public String videoPlayFailUrl;
    @JSONField(name = "video_play_finish_url")
    public String videoPlayFinishUrl;
    @JSONField(name = "video_play_start_url")
    public String videoPlayStartUrl;

    public static class AdExtraInfo implements Serializable {
        @JSONField(name = "download_url")
        public String apkDownloadUrl;
        @JSONField(name = "package")
        public String apkPackageName;
        @JSONField(name = "app_name")
        public String appName;
        @JSONField(name = "web_url")
        public String h5Url;
        @JSONField(name = "open_url")
        public String openAppUri;
    }

    public static class AdFeedExtraInfo implements Serializable {
        @JSONField(name = "advanced_creative_type")
        public int FeedExtraType;
        @JSONField(name = "button_text")
        public String buttonText;
        @JSONField(name = "sub_title")
        public String subTitle;
        @JSONField(name = "title")
        public String title;
        @JSONField(name = "url")
        public String url;
    }

    public static class AdMultiMedia implements Serializable {
        @JSONField(name = "dur")
        public int duration;
        @JSONField(name = "img_urls")
        public List<String> imageUrls;
        @JSONField(name = "video_urls")
        public List<String> videoUrls;
    }

    public static class AdsMember implements Serializable {
        @JSONField(name = "avatar")
        public String avatarUrl;
        @JSONField(name = "id")
        public long memberId;
        @JSONField(name = "name")
        public String memberName;
    }

    public static class Filter implements Serializable {
        @JSONField(name = "id")
        public int id;
        @JSONField(name = "is_selected")
        public boolean isDefaultSelect;
        @JSONField(name = "name")
        public String name;
    }

    public int classType() {
        return 5;
    }

    public long getCreateTime() {
        return 0;
    }

    public int localPostType() {
        return 8;
    }

    public int getShareNum() {
        return 0;
    }

    public long getId() {
        return 0;
    }

    public long getMemberId() {
        return this.member.memberId;
    }

    public void setFollowStatus(int i) {
    }

    public boolean equals(Object obj) {
        if ((obj instanceof AdvertismentBean) && ((AdvertismentBean) obj).id == this.id) {
            return true;
        }
        return false;
    }

    public boolean isApiHardAd() {
        return this.c_type == 8;
    }

    public LinkedHashMap<String, String> createTediumReason() {
        if (this.adFilters == null || this.adFilters.size() <= 0) {
            return null;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < this.adFilters.size(); i++) {
            linkedHashMap.put(((Filter) this.adFilters.get(i)).id + "", ((Filter) this.adFilters.get(i)).name);
        }
        return linkedHashMap;
    }

    public static AdvertismentBean createByJson(JSONObject jSONObject) {
        return (AdvertismentBean) JSON.parseObject(jSONObject.toString(), AdvertismentBean.class);
    }
}
