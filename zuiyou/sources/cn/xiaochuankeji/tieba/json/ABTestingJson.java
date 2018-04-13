package cn.xiaochuankeji.tieba.json;

import com.alibaba.fastjson.annotation.JSONField;

public class ABTestingJson {
    @JSONField(name = "abtesting")
    public String abtestingId;
    @JSONField(name = "qq")
    public Info qqInfo;
    @JSONField(name = "qqzone")
    public Info qqzoneInfo;
    @JSONField(name = "wechatCircle")
    public Info wechatCircleInfo;
    @JSONField(name = "wechat")
    public Info wechatInfo;
    @JSONField(name = "weibo")
    public Info weiboInfo;

    public class Info {
        @JSONField(name = "share_post_desc")
        public String sharePostDesc;
        @JSONField(name = "share_post_icon")
        public String sharePostIcon;
        @JSONField(name = "share_post_title")
        public String sharePostTitle;
        @JSONField(name = "share_review_desc")
        public String shareReviewDesc;
        @JSONField(name = "share_review_icon")
        public String shareReviewIcon;
        @JSONField(name = "share_review_title")
        public String shareReviewTitle;
    }

    public String getSharePostTitle(int i) {
        Info infoByPlatform = getInfoByPlatform(i);
        return infoByPlatform != null ? infoByPlatform.sharePostTitle : null;
    }

    public String getSharePostDescription(int i) {
        Info infoByPlatform = getInfoByPlatform(i);
        return infoByPlatform != null ? infoByPlatform.sharePostDesc : null;
    }

    public String getShareReviewTitle(int i) {
        Info infoByPlatform = getInfoByPlatform(i);
        return infoByPlatform != null ? infoByPlatform.shareReviewTitle : null;
    }

    public String getShareReviewDescription(int i) {
        Info infoByPlatform = getInfoByPlatform(i);
        return infoByPlatform != null ? infoByPlatform.shareReviewDesc : null;
    }

    public String getSharePostIconUrl(int i) {
        Info infoByPlatform = getInfoByPlatform(i);
        return infoByPlatform != null ? infoByPlatform.sharePostIcon : null;
    }

    public String getShareReviewIconUrl(int i) {
        Info infoByPlatform = getInfoByPlatform(i);
        return infoByPlatform != null ? infoByPlatform.shareReviewIcon : null;
    }

    private Info getInfoByPlatform(int i) {
        if (1 == i) {
            return this.wechatInfo;
        }
        if (2 == i) {
            return this.wechatCircleInfo;
        }
        if (3 == i) {
            return this.weiboInfo;
        }
        if (4 == i) {
            return this.qqzoneInfo;
        }
        if (5 == i) {
            return this.qqInfo;
        }
        return null;
    }
}
