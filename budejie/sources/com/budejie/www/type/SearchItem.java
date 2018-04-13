package com.budejie.www.type;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SearchItem implements Serializable {
    @SerializedName("background_image")
    private String backgroundImage;
    @SerializedName("comment_count")
    private String commentsCount;
    @SerializedName("fans_count")
    private String fansCount;
    @SerializedName("follow_count")
    private String followCount;
    @SerializedName("forum_ctime")
    private String forumTime;
    private String id;
    private String introduction;
    @SerializedName("tiezi_count")
    private String postsCount;
    @SerializedName("praise_count")
    private String praiseCount;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("profile_image_large")
    private String profileImageLarge;
    @SerializedName("is_follow")
    private String relationship;
    private int role;
    private String sex;
    private int type;
    private String username;

    public int getRole() {
        return this.role;
    }

    public void setRole(int i) {
        this.role = i;
    }

    public String getForumTime() {
        return this.forumTime;
    }

    public void setForumTime(String str) {
        this.forumTime = str;
    }

    public String getPraiseCount() {
        return this.praiseCount;
    }

    public void setPraiseCount(String str) {
        this.praiseCount = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
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

    public String getRelationship() {
        return this.relationship;
    }

    public void setRelationship(String str) {
        this.relationship = str;
    }

    public String getPostsCount() {
        return this.postsCount;
    }

    public void setPostsCount(String str) {
        this.postsCount = str;
    }

    public String getCommentsCount() {
        return this.commentsCount;
    }

    public void setCommentsCount(String str) {
        this.commentsCount = str;
    }
}
