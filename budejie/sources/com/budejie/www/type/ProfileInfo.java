package com.budejie.www.type;

import com.google.gson.annotations.SerializedName;

public class ProfileInfo {
    private String code;
    private Data data;
    private String msg;

    public class Data {
        @SerializedName("background_image")
        private String backgroundImage;
        private String comment_count;
        private String credit;
        private String experience;
        @SerializedName("fans_add")
        private String fansAdd;
        @SerializedName("fans_count")
        private String fansCount;
        @SerializedName("follow_count")
        private String followCount;
        private String id;
        private String introduction;
        public boolean is_vip;
        private String jie_v;
        private String level;
        @SerializedName("praise_count")
        private String praise_count;
        @SerializedName("profile_image")
        private String profileImage;
        @SerializedName("profile_image_large")
        private String profileImageLarge;
        @SerializedName("relationship")
        private String relationship;
        @SerializedName("setting_push_comment")
        private String settingPushComment;
        @SerializedName("setting_push_feed")
        private String settingPushFeed;
        @SerializedName("setting_push_o1")
        private String settingPushO1;
        @SerializedName("setting_push_o2")
        private String settingPushO2;
        private String sex;
        private String share_count;
        private String sina_v;
        private String tiezi_count;
        private String total_cmt_like_count;
        private String username;
        private String v_desc;

        public String getRelationship() {
            return this.relationship;
        }

        public void setRelationship(String str) {
            this.relationship = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public String getSex() {
            return this.sex;
        }

        public void setSex(String str) {
            this.sex = str;
        }

        public String getIntroduction() {
            return this.introduction;
        }

        public void setIntroduction(String str) {
            this.introduction = str;
        }

        public String getProfileImage() {
            return this.profileImage;
        }

        public void setProfileImage(String str) {
            this.profileImage = str;
        }

        public String getProfileImageLarge() {
            return this.profileImageLarge;
        }

        public void setProfileImageLarge(String str) {
            this.profileImageLarge = str;
        }

        public String getBackgroundImage() {
            return this.backgroundImage;
        }

        public void setBackgroundImage(String str) {
            this.backgroundImage = str;
        }

        public String getFansAdd() {
            return this.fansAdd;
        }

        public void setFansAdd(String str) {
            this.fansAdd = str;
        }

        public String getFollowCount() {
            return this.followCount;
        }

        public void setFollowCount(String str) {
            this.followCount = str;
        }

        public String getFansCount() {
            return this.fansCount;
        }

        public void setFansCount(String str) {
            this.fansCount = str;
        }

        public String getSettingPushFeed() {
            return this.settingPushFeed;
        }

        public void setSettingPushFeed(String str) {
            this.settingPushFeed = str;
        }

        public String getSettingPushComment() {
            return this.settingPushComment;
        }

        public void setSettingPushComment(String str) {
            this.settingPushComment = str;
        }

        public String getSettingPushO1() {
            return this.settingPushO1;
        }

        public void setSettingPushO1(String str) {
            this.settingPushO1 = str;
        }

        public String getSettingPushO2() {
            return this.settingPushO2;
        }

        public void setSettingPushO2(String str) {
            this.settingPushO2 = str;
        }

        public String getPraise_count() {
            return this.praise_count;
        }

        public void setPraise_count(String str) {
            this.praise_count = str;
        }

        public String getLevel() {
            return this.level;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public String getCredit() {
            return this.credit;
        }

        public void setCredit(String str) {
            this.credit = str;
        }

        public String getExperience() {
            return this.experience;
        }

        public void setExperience(String str) {
            this.experience = str;
        }

        public String getSina_v() {
            return this.sina_v;
        }

        public void setSina_v(String str) {
            this.sina_v = str;
        }

        public String getJie_v() {
            return this.jie_v;
        }

        public void setJie_v(String str) {
            this.jie_v = str;
        }

        public String getV_desc() {
            return this.v_desc;
        }

        public void setV_desc(String str) {
            this.v_desc = str;
        }

        public String getTiezi_count() {
            return this.tiezi_count;
        }

        public void setTiezi_count(String str) {
            this.tiezi_count = str;
        }

        public String getShare_count() {
            return this.share_count;
        }

        public void setShare_count(String str) {
            this.share_count = str;
        }

        public String getComment_count() {
            return this.comment_count;
        }

        public void setComment_count(String str) {
            this.comment_count = str;
        }

        public String getTotal_cmt_like_count() {
            return this.total_cmt_like_count;
        }

        public void setTotal_cmt_like_count(String str) {
            this.total_cmt_like_count = str;
        }
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
