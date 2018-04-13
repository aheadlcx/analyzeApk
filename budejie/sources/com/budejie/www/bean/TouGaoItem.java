package com.budejie.www.bean;

import android.text.TextUtils;
import com.budejie.www.activity.plate.bean.PlateBean;
import java.io.Serializable;
import java.util.ArrayList;

public class TouGaoItem implements Serializable {
    private ArrayList<PlateBean> PlateDatas;
    private String PlateDatasJson;
    private String bimageuri;
    private String caiCount;
    private String cai_flag;
    private String cnd_img;
    private String commendCount;
    private String content;
    private String dataId;
    private String dingCount;
    private DraftBean draftBean;
    private String flag;
    private String forwardCount;
    private String gifFistFrame;
    private ArrayList<HeadPortraitItem> headPortraitItem;
    private String height;
    private String help_uri;
    private String imgUrl;
    private boolean isCollect;
    private boolean isGif;
    private String[] mDownloadImageUris;
    private String[] mDownloadVideoUris;
    private String mid;
    private String original_pid;
    private ListItemObject original_topic;
    private String playcount;
    private String profileImage;
    private RichObject richObject;
    private String screenName;
    private int status;
    private String status_text;
    private int theme_id;
    private String theme_name;
    private int theme_type;
    private String time;
    private String type;
    private String uid;
    private String videotime;
    private String videouri;
    private String videouriBackup;
    private String voicetime;
    private String voiceuri;
    private VoteData voteData;
    private String voteDataJson;
    private String weixin_url;
    private String width;

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public boolean isCollect() {
        return this.isCollect;
    }

    public void setCollect(boolean z) {
        this.isCollect = z;
    }

    public String getCai_flag() {
        return this.cai_flag;
    }

    public void setCai_flag(String str) {
        this.cai_flag = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getOriginal_pid() {
        return this.original_pid;
    }

    public void setOriginal_pid(String str) {
        this.original_pid = str;
    }

    public ListItemObject getOriginal_topic() {
        return this.original_topic;
    }

    public void setOriginal_topic(ListItemObject listItemObject) {
        this.original_topic = listItemObject;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public void setScreenName(String str) {
        this.screenName = str;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String str) {
        this.profileImage = str;
    }

    public boolean getIsGif() {
        return this.isGif;
    }

    public void setIsGif(boolean z) {
        this.isGif = z;
    }

    public String getWeixin_url() {
        return this.weixin_url;
    }

    public void setWeixin_url(String str) {
        this.weixin_url = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
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

    public String getDingCount() {
        return this.dingCount;
    }

    public void setDingCount(String str) {
        this.dingCount = str;
    }

    public String getCaiCount() {
        return this.caiCount;
    }

    public void setCaiCount(String str) {
        this.caiCount = str;
    }

    public String getCommendCount() {
        return this.commendCount;
    }

    public void setCommendCount(String str) {
        this.commendCount = str;
    }

    public String getDataId() {
        return this.dataId;
    }

    public void setDataId(String str) {
        this.dataId = str;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String str) {
        this.mid = str;
    }

    public String getStatus_text() {
        return this.status_text;
    }

    public void setStatus_text(String str) {
        this.status_text = str;
    }

    public String getForwardCount() {
        return this.forwardCount;
    }

    public void setForwardCount(String str) {
        this.forwardCount = str;
    }

    public void setHelpUri(String str) {
        this.help_uri = str;
    }

    public String getHelpUri() {
        return this.help_uri;
    }

    public String getBimageuri() {
        return this.bimageuri;
    }

    public void setBimageuri(String str) {
        this.bimageuri = str;
    }

    public String getCnd_img() {
        return this.cnd_img;
    }

    public void setCnd_img(String str) {
        this.cnd_img = str;
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

    public String getPlaycount() {
        return this.playcount;
    }

    public void setPlaycount(String str) {
        this.playcount = str;
    }

    public String getVideouri() {
        return this.videouri;
    }

    public void setVideouri(String str) {
        this.videouri = str;
    }

    public String getVideouriBackup() {
        return this.videouriBackup;
    }

    public void setVideouriBackup(String str) {
        this.videouriBackup = str;
    }

    public String getVideotime() {
        return this.videotime;
    }

    public void setVideotime(String str) {
        this.videotime = str;
    }

    public DraftBean getDraftBean() {
        return this.draftBean;
    }

    public void setDraftBean(DraftBean draftBean) {
        this.draftBean = draftBean;
    }

    public String getHelp_uri() {
        return this.help_uri;
    }

    public void setHelp_uri(String str) {
        this.help_uri = str;
    }

    public String getGifFistFrame() {
        return this.gifFistFrame;
    }

    public void setGifFistFrame(String str) {
        this.gifFistFrame = str;
    }

    public int getTheme_id() {
        return this.theme_id;
    }

    public void setTheme_id(int i) {
        this.theme_id = i;
    }

    public int getTheme_type() {
        return this.theme_type;
    }

    public void setTheme_type(int i) {
        this.theme_type = i;
    }

    public String getTheme_name() {
        return this.theme_name;
    }

    public void setTheme_name(String str) {
        this.theme_name = str;
    }

    public String[] getDownloadVideoUris() {
        return this.mDownloadVideoUris;
    }

    public String getDownloadVideoUri() {
        if (this.mDownloadVideoUris == null) {
            return null;
        }
        String str = this.mDownloadVideoUris[0];
        if (!TextUtils.isEmpty(str) || this.mDownloadVideoUris.length <= 1) {
            return str;
        }
        return this.mDownloadVideoUris[1];
    }

    public void setDownloadVideoUris(String... strArr) {
        this.mDownloadVideoUris = strArr;
    }

    public String[] getDownloadImageUris() {
        return this.mDownloadImageUris;
    }

    public void setDownloadImageUris(String[] strArr) {
        this.mDownloadImageUris = strArr;
    }

    public RichObject getRichObject() {
        return this.richObject;
    }

    public void setRichObject(RichObject richObject) {
        this.richObject = richObject;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TouGaoItem) {
            TouGaoItem touGaoItem = (TouGaoItem) obj;
            if (!TextUtils.isEmpty(this.dataId)) {
                return this.dataId.equals(touGaoItem.getDataId());
            }
        }
        return super.equals(obj);
    }

    public void setHeadPortraitItem(ArrayList<HeadPortraitItem> arrayList) {
        this.headPortraitItem = arrayList;
    }

    public ArrayList<HeadPortraitItem> getHeadPortraitItem() {
        return this.headPortraitItem;
    }

    public String getVoteDataJson() {
        return this.voteDataJson;
    }

    public void setVoteDataJson(String str) {
        this.voteDataJson = str;
    }

    public VoteData getVoteData() {
        return this.voteData;
    }

    public void setVoteData(VoteData voteData) {
        this.voteData = voteData;
    }

    public ArrayList<PlateBean> getPlateDatas() {
        return this.PlateDatas;
    }

    public void setPlateDatas(ArrayList<PlateBean> arrayList) {
        this.PlateDatas = arrayList;
    }

    public String getPlateDatasJson() {
        return this.PlateDatasJson;
    }

    public void setPlateDatasJson(String str) {
        this.PlateDatasJson = str;
    }
}
