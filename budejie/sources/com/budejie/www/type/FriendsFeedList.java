package com.budejie.www.type;

import com.budejie.www.bean.ListItemObject;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FriendsFeedList {
    private String code;
    private Data data;
    private String msg;

    public class Data {
        private Info info;
        private List<ListItemObject> list;

        public Info getInfo() {
            return this.info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public List<ListItemObject> getList() {
            return this.list;
        }

        public void setList(List<ListItemObject> list) {
            this.list = list;
        }
    }

    public class Info {
        private boolean hasdata;
        private String readid;

        public String getReadid() {
            return this.readid;
        }

        public void setReadid(String str) {
            this.readid = str;
        }

        public boolean getHasdata() {
            return this.hasdata;
        }

        public void setHasdata(boolean z) {
            this.hasdata = z;
        }
    }

    public class Posts {
        private String bimageuri;
        private String bookmark;
        private String cai;
        private String comment;
        @SerializedName("content_type")
        private String contentType;
        @SerializedName("created_at")
        private String createdAt;
        private String favourite;
        private String forward;
        private String from;
        private String gifFistFrame;
        private String hate;
        private String height;
        private String id;
        private String image0;
        private String image1;
        private String image2;
        @SerializedName("image_small")
        private String imageSmall;
        @SerializedName("is_gif")
        private String isGif;
        private String love;
        private String mid;
        private String name;
        private String passtime;
        private String playcount;
        private String playfcount;
        @SerializedName("profile_image")
        private String profileImage;
        private String repost;
        @SerializedName("screen_name")
        private String screenName;
        @SerializedName("source_image")
        private String sourceImage;
        private String status;
        private String tag;
        private String text;
        private String type;
        private String url;
        @SerializedName("user_id")
        private String userId;
        private String voicelength;
        private String voicetime;
        private String voiceuri;
        @SerializedName("weixin_url")
        private String weixinUrl;
        private String width;

        public String getImageSmall() {
            return this.imageSmall;
        }

        public void setImageSmall(String str) {
            this.imageSmall = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public String getSourceImage() {
            return this.sourceImage;
        }

        public void setSourceImage(String str) {
            this.sourceImage = str;
        }

        public String getContentType() {
            return this.contentType;
        }

        public void setContentType(String str) {
            this.contentType = str;
        }

        public String getPlaycount() {
            return this.playcount;
        }

        public void setPlaycount(String str) {
            this.playcount = str;
        }

        public String getPlayfcount() {
            return this.playfcount;
        }

        public void setPlayfcount(String str) {
            this.playfcount = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getScreenName() {
            return this.screenName;
        }

        public void setScreenName(String str) {
            this.screenName = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getMid() {
            return this.mid;
        }

        public void setMid(String str) {
            this.mid = str;
        }

        public String getFrom() {
            return this.from;
        }

        public void setFrom(String str) {
            this.from = str;
        }

        public String getCreatedAt() {
            return this.createdAt;
        }

        public void setCreatedAt(String str) {
            this.createdAt = str;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getWidth() {
            return this.width;
        }

        public void setWidth(String str) {
            this.width = str;
        }

        public String getHeight() {
            return this.height;
        }

        public void setHeight(String str) {
            this.height = str;
        }

        public String getTag() {
            return this.tag;
        }

        public void setTag(String str) {
            this.tag = str;
        }

        public String getProfileImage() {
            return this.profileImage;
        }

        public void setProfileImage(String str) {
            this.profileImage = str;
        }

        public String getImage0() {
            return this.image0;
        }

        public void setImage0(String str) {
            this.image0 = str;
        }

        public String getImage2() {
            return this.image2;
        }

        public void setImage2(String str) {
            this.image2 = str;
        }

        public String getImage1() {
            return this.image1;
        }

        public void setImage1(String str) {
            this.image1 = str;
        }

        public String getWeixinUrl() {
            return this.weixinUrl;
        }

        public void setWeixinUrl(String str) {
            this.weixinUrl = str;
        }

        public String getIsGif() {
            return this.isGif;
        }

        public void setIsGif(String str) {
            this.isGif = str;
        }

        public String getPasstime() {
            return this.passtime;
        }

        public void setPasstime(String str) {
            this.passtime = str;
        }

        public String getLove() {
            return this.love;
        }

        public void setLove(String str) {
            this.love = str;
        }

        public String getHate() {
            return this.hate;
        }

        public void setHate(String str) {
            this.hate = str;
        }

        public String getComment() {
            return this.comment;
        }

        public void setComment(String str) {
            this.comment = str;
        }

        public String getBookmark() {
            return this.bookmark;
        }

        public void setBookmark(String str) {
            this.bookmark = str;
        }

        public String getFavourite() {
            return this.favourite;
        }

        public void setFavourite(String str) {
            this.favourite = str;
        }

        public String getForward() {
            return this.forward;
        }

        public void setForward(String str) {
            this.forward = str;
        }

        public String getCai() {
            return this.cai;
        }

        public void setCai(String str) {
            this.cai = str;
        }

        public String getRepost() {
            return this.repost;
        }

        public void setRepost(String str) {
            this.repost = str;
        }

        public String getVoiceuri() {
            return this.voiceuri;
        }

        public void setVoiceuri(String str) {
            this.voiceuri = str;
        }

        public String getBimageuri() {
            return this.bimageuri;
        }

        public void setBimageuri(String str) {
            this.bimageuri = str;
        }

        public String getVoicetime() {
            return this.voicetime;
        }

        public void setVoicetime(String str) {
            this.voicetime = str;
        }

        public String getVoicelength() {
            return this.voicelength;
        }

        public void setVoicelength(String str) {
            this.voicelength = str;
        }

        public String getGifFistFrame() {
            return this.gifFistFrame;
        }

        public void setGifFistFrame(String str) {
            this.gifFistFrame = str;
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
