package com.budejie.www.bean;

import com.budejie.www.j.a;
import com.budejie.www.util.aa;
import java.io.Serializable;
import java.util.ArrayList;

public class CommentItem implements Serializable {
    private static final long serialVersionUID = 1;
    private String authorUid;
    private ArrayList<CommentItem> author_replay;
    private String caiCount;
    private String content;
    private String dingCount;
    private String dingOrCai;
    private int hotNum;
    private String id;
    private boolean isDing;
    private boolean isHotAuthorReplay;
    private boolean isHotAuthorReplayFirst;
    private boolean isPub;
    private String is_vip;
    private boolean ishot;
    private boolean isnew;
    private ListItemObject itemObject;
    private int mAudioDuration;
    private String mAudioUrl;
    private String mCmtLikeCount;
    private String mFloorNum;
    private String[] mGifDownLoadUrls;
    private int mGifHeight;
    private ArrayList<String> mGifShowUrl;
    private String mGifThumbUrl;
    private int mGifWidth;
    private String mHotCount;
    private String mHotNp;
    private String[] mImageDownloadUrls;
    private int mImageHeight;
    private String mImageShowUrl;
    private String mImageThumbUrl;
    private int mImageWidth;
    private String mLapped;
    private String mNormalCount;
    private String mNormalNp;
    private ArrayList<CommentItem> mReplyList;
    private String mStatus;
    private String mVideoDownLoadUrl;
    private int mVideoHeight;
    private String mVideoPlayUrl;
    private String mVideoThumbnail;
    private String mVideoTime;
    private int mVideoWidth;
    private String mpreContent = "";
    private String mpreName = "";
    private String profile;
    private String qqUid;
    private String qzoneUid;
    private String sex;
    private String sinaUid;
    private int state;
    private boolean tagIsShow;
    private String time;
    private String type;
    private String uid;
    private String uname;
    private String voicetime;
    private String voiceuri;
    private VoteData voteData;
    private String voteDataJson;

    public String getCmtLikeCount() {
        return this.mCmtLikeCount;
    }

    public void setCmtLikeCount(String str) {
        this.mCmtLikeCount = str;
    }

    public String getIs_vip() {
        return this.is_vip;
    }

    public void setIs_vip(String str) {
        this.is_vip = str;
    }

    public int getHotNum() {
        return this.hotNum;
    }

    public void setHotNum(int i) {
        this.hotNum = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getMpreName() {
        return this.mpreName;
    }

    public void setMpreName(String str) {
        this.mpreName = str;
    }

    public String getMpreContent() {
        return this.mpreContent;
    }

    public void setMpreContent(String str) {
        this.mpreContent = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getDingCount() {
        return this.dingCount;
    }

    public void setDingCount(String str) {
        this.dingCount = str;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String str) {
        this.profile = str;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getQqUid() {
        return this.qqUid;
    }

    public void setQqUid(String str) {
        this.qqUid = str;
    }

    public String getSinaUid() {
        return this.sinaUid;
    }

    public void setSinaUid(String str) {
        this.sinaUid = str;
    }

    public String getQzoneUid() {
        return this.qzoneUid;
    }

    public void setQzoneUid(String str) {
        this.qzoneUid = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public boolean isIshot() {
        return this.ishot;
    }

    public void setIshot(boolean z) {
        this.ishot = z;
    }

    public boolean isIsnew() {
        return this.isnew;
    }

    public void setIsnew(boolean z) {
        this.isnew = z;
    }

    public boolean isTagIsShow() {
        return this.tagIsShow;
    }

    public void setTagIsShow(boolean z) {
        this.tagIsShow = z;
    }

    public ListItemObject getItemObject() {
        return this.itemObject;
    }

    public void setItemObject(ListItemObject listItemObject) {
        this.itemObject = listItemObject;
    }

    public boolean isDing() {
        return this.isDing;
    }

    public void setDing(boolean z) {
        this.isDing = z;
    }

    public String getVoiceuri() {
        return this.voiceuri;
    }

    public void setVoiceuri(String str) {
        this.voiceuri = str;
    }

    public String getVoicetime() {
        return this.voicetime;
    }

    public void setVoicetime(String str) {
        this.voicetime = str;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUid() {
        return this.uid;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public ArrayList<CommentItem> getReplyList() {
        return this.mReplyList;
    }

    public void setReplyList(ArrayList<CommentItem> arrayList) {
        this.mReplyList = arrayList;
    }

    public String getLapped() {
        return this.mLapped;
    }

    public void setLapped(String str) {
        this.mLapped = str;
    }

    public String getFloorNum() {
        return this.mFloorNum;
    }

    public void setFloorNum(String str) {
        this.mFloorNum = str;
    }

    public String getHotCount() {
        return this.mHotCount;
    }

    public void setHotCount(String str) {
        this.mHotCount = str;
    }

    public String getHotNp() {
        return this.mHotNp;
    }

    public void setHotNp(String str) {
        this.mHotNp = str;
    }

    public String getNormalCount() {
        return this.mNormalCount;
    }

    public void setNormalCount(String str) {
        this.mNormalCount = str;
    }

    public String getNormalNp() {
        return this.mNormalNp;
    }

    public void setNormalNp(String str) {
        this.mNormalNp = str;
    }

    public String getVideoDownLoadUrl() {
        return this.mVideoDownLoadUrl;
    }

    public void setVideoDownLoadUrl(String str) {
        this.mVideoDownLoadUrl = str;
    }

    public String getVideoThumbnail() {
        return this.mVideoThumbnail;
    }

    public void setVideoThumbnail(String str) {
        this.mVideoThumbnail = str;
    }

    public String getVideoPlayUrl() {
        return this.mVideoPlayUrl;
    }

    public void setVideoPlayUrl(String str) {
        this.mVideoPlayUrl = str;
    }

    public boolean isPub() {
        return this.isPub;
    }

    public void setPub(boolean z) {
        this.isPub = z;
    }

    public String getmVideoTime() {
        return this.mVideoTime;
    }

    public void setmVideoTime(String str) {
        this.mVideoTime = str;
    }

    public String getAudioUrl() {
        return this.mAudioUrl;
    }

    public void setAudioUrl(String str) {
        this.mAudioUrl = str;
    }

    public String[] getImageDownloadUrls() {
        return this.mImageDownloadUrls;
    }

    public void setImageDownloadUrls(String[] strArr) {
        this.mImageDownloadUrls = strArr;
    }

    public String getImageThumbUrl() {
        return this.mImageThumbUrl;
    }

    public void setImageThumbUrl(String str) {
        this.mImageThumbUrl = str;
    }

    public String getImageShowUrl() {
        return this.mImageShowUrl;
    }

    public void setImageShowUrl(String str) {
        this.mImageShowUrl = str;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public void setImageWidth(int i) {
        this.mImageWidth = i;
    }

    public int getImageHeight() {
        return this.mImageHeight;
    }

    public void setImageHeight(int i) {
        this.mImageHeight = i;
    }

    public int getAudioDuration() {
        return this.mAudioDuration;
    }

    public void setAudioDuration(int i) {
        this.mAudioDuration = i;
    }

    public String[] getGifDownLoadUrls() {
        return this.mGifDownLoadUrls;
    }

    public void setGifDownLoadUrls(String[] strArr) {
        this.mGifDownLoadUrls = strArr;
    }

    public ArrayList<String> getGifShowUrl() {
        return this.mGifShowUrl;
    }

    public void setGifShowUrl(ArrayList<String> arrayList) {
        this.mGifShowUrl = arrayList;
    }

    public String getGifThumbUrl() {
        return this.mGifThumbUrl;
    }

    public void setGifThumbUrl(String str) {
        this.mGifThumbUrl = str;
    }

    public int getGifWidth() {
        return this.mGifWidth;
    }

    public void setGifWidth(int i) {
        this.mGifWidth = i;
    }

    public int getGifHeight() {
        return this.mGifHeight;
    }

    public void setGifHeight(int i) {
        this.mGifHeight = i;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public void setVideoWidth(int i) {
        this.mVideoWidth = i;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public void setVideoHeight(int i) {
        this.mVideoHeight = i;
    }

    public String getCaiCount() {
        return this.caiCount;
    }

    public void setCaiCount(String str) {
        this.caiCount = str;
    }

    public String getDingOrCai() {
        return this.dingOrCai;
    }

    public void setDingOrCai(String str) {
        this.dingOrCai = str;
    }

    public boolean isAlreadyDingCai() {
        if ("like".equals(this.dingOrCai) || "hate".equals(this.dingOrCai)) {
            return true;
        }
        return false;
    }

    public String getVoteDataJson() {
        return this.voteDataJson;
    }

    public void setVoteDataJson(String str) {
        aa.b("CommentItem", "setVoteDataJson voteDataJson=" + str);
        this.voteDataJson = str;
        this.voteData = a.b(str);
        if (this.voteData != null) {
            this.voteData.cid = this.id;
        }
    }

    public VoteData getVoteData() {
        return this.voteData;
    }

    public void setVoteData(VoteData voteData) {
        this.voteData = voteData;
    }

    public String getAuthorUid() {
        return this.authorUid;
    }

    public void setAuthorUid(String str) {
        this.authorUid = str;
    }

    public ArrayList<CommentItem> getAuthor_replay() {
        return this.author_replay;
    }

    public void setAuthor_replay(ArrayList<CommentItem> arrayList) {
        this.author_replay = arrayList;
    }

    public boolean isHotAuthorReplay() {
        return this.isHotAuthorReplay;
    }

    public void setHotAuthorReplay(boolean z) {
        this.isHotAuthorReplay = z;
    }

    public boolean isHotAuthorReplayFirst() {
        return this.isHotAuthorReplayFirst;
    }

    public void setHotAuthorReplayFirst(boolean z) {
        this.isHotAuthorReplayFirst = z;
    }
}
