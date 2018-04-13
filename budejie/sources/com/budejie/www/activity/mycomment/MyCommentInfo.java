package com.budejie.www.activity.mycomment;

import com.budejie.www.bean.User;
import java.io.Serializable;
import java.util.ArrayList;

public class MyCommentInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public String content;
    public String ctime;
    public String data_id;
    private String dingOrCai;
    public String hate_count;
    public String id;
    public boolean isLiked;
    public String like_count;
    private int mAudioDuration;
    private String mAudioUrl;
    private String[] mGifDownLoadUrls;
    private int mGifHeight;
    private ArrayList<String> mGifShowUrl;
    private String mGifThumbUrl;
    private int mGifWidth;
    private String[] mImageDownloadUrls;
    private int mImageHeight;
    private String mImageShowUrl;
    private String mImageThumbUrl;
    private int mImageWidth;
    private String mVideoDownLoadUrl;
    private String mVideoPlayUrl;
    private String mVideoThumbnail;
    public String precid;
    public MyCommentInfo precmt;
    public String preuid;
    public int status;
    private String type;
    public User user;
    public String voicetime;
    public String voiceuri;

    public MyCommentInfo(String str, String str2, String str3, User user) {
        this.content = str;
        this.voiceuri = str2;
        this.voicetime = str3;
        this.user = user;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
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

    public String getAudioUrl() {
        return this.mAudioUrl;
    }

    public void setAudioUrl(String str) {
        this.mAudioUrl = str;
    }

    public int getAudioDuration() {
        return this.mAudioDuration;
    }

    public void setAudioDuration(int i) {
        this.mAudioDuration = i;
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
}
