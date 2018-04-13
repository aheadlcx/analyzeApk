package com.budejie.www.bean;

import com.budejie.www.type.ProfileInfo.Data;
import com.budejie.www.type.SearchItem;
import java.io.Serializable;

public class Fans implements Serializable {
    String code;
    String count;
    String fansCount;
    String fllowId;
    String followCount;
    String hasData;
    String id;
    String introduction;
    public boolean is_vip;
    String msg;
    long recentContactTime;
    String relationship;
    String sex;
    String sortLetters;
    public FollowTopic topic;
    public int unread;
    String userPic;
    String username;

    public Fans(ListItemObject listItemObject) {
        this.id = listItemObject.getUid();
        this.username = listItemObject.getName();
        this.userPic = listItemObject.getProfile();
    }

    public Fans(RecommendUser recommendUser) {
        this.id = recommendUser.getUserid();
        this.username = recommendUser.getUsername();
        this.userPic = recommendUser.getProfile_image();
    }

    public Fans(SuggestedFollowsListItem suggestedFollowsListItem) {
        this.id = suggestedFollowsListItem.id;
        this.username = suggestedFollowsListItem.screen_name;
        this.userPic = suggestedFollowsListItem.header;
    }

    public Fans(LabelUser labelUser) {
        this.id = labelUser.uid;
        this.username = labelUser.name;
        this.userPic = labelUser.header;
    }

    public Fans(HeadPortraitItem headPortraitItem) {
        this.id = headPortraitItem.getUserid();
        this.username = headPortraitItem.getUsername();
        this.userPic = headPortraitItem.getProfile_image();
    }

    public Fans(MyMsgItem myMsgItem) {
        this.id = String.valueOf(myMsgItem.getUserid());
        this.username = myMsgItem.getUsername();
        this.userPic = myMsgItem.getProfile_image();
    }

    public Fans(SearchItem searchItem) {
        this.id = searchItem.getId();
        this.username = searchItem.getUsername();
        this.userPic = searchItem.getProfileImage();
    }

    public Fans(Data data) {
        this.id = data.getId();
        this.username = data.getUsername();
        this.userPic = data.getProfileImage();
    }

    public Fans(MyFollowItem myFollowItem) {
        this.id = myFollowItem.user.id;
        this.username = myFollowItem.user.username;
        this.userPic = myFollowItem.user.profile_image;
        this.unread = myFollowItem.unread;
        this.topic = myFollowItem.topic;
    }

    public String getSortLetters() {
        return this.sortLetters;
    }

    public void setSortLetters(String str) {
        this.sortLetters = str;
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

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public String getHasData() {
        return this.hasData;
    }

    public void setHasData(String str) {
        this.hasData = str;
    }

    public String getFllowId() {
        return this.fllowId;
    }

    public void setFllowId(String str) {
        this.fllowId = str;
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

    public String getUserPic() {
        return this.userPic;
    }

    public void setUserPic(String str) {
        this.userPic = str;
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

    public long getRecentContactTime() {
        return this.recentContactTime;
    }

    public void setRecentContactTime(long j) {
        this.recentContactTime = j;
    }
}
