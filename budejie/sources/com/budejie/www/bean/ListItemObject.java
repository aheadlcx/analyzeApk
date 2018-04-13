package com.budejie.www.bean;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.a.c;
import com.budejie.www.activity.mycomment.MyCommentInfo;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.recommendvideo.AutoPlayVideoListActivity;
import com.budejie.www.util.aa;
import com.google.gson.annotations.SerializedName;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.NativeAdRef;
import com.volokh.danylo.visibility_utils.b.a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class ListItemObject implements c, a, Serializable {
    private static final long serialVersionUID = 1;
    private ArrayList<PlateBean> PlateDatas;
    private String PlateDatasJson;
    private NativeAdRef adItem;
    private NativeAdData adItemData;
    private int ad_id;
    private String ad_type;
    private String ad_url;
    @SerializedName("create_time")
    private String addtime;
    private int cai;
    private String cai_flag;
    private String cmdShowTime;
    private String cnd_SmallImg;
    private String cnd_img;
    private String comment;
    @SerializedName("text")
    private String content;
    private int dbId;
    private int favorite;
    private String favoriteTime;
    private String flag;
    private boolean forwardAndCollect;
    private boolean forwardAndCollectFlag;
    private boolean forwardAndCollect_isweixin;
    private boolean forwardNoCollect = true;
    private String gifFistFrame;
    private String hasData;
    private ArrayList<HeadPortraitItem> headPortraitItems;
    private int height;
    private List<MyCommentInfo> hotComments = new ArrayList();
    private String hotCommentsJson;
    private MyCommentInfo hotcmt;
    private boolean ifLocal;
    private boolean ifPP;
    private boolean ifRepostBigerUmeng;
    private String image0;
    private String image1;
    private String image2;
    private String image_small;
    private String imgPath;
    private String imgUrl;
    private boolean insertRemind;
    private boolean isCollect;
    private boolean isDraftBean;
    private boolean isShwFollow = false;
    private boolean isTopTopic;
    private boolean is_ad;
    private String is_gif;
    private String is_vip;
    public boolean isreport;
    private String jie_v;
    private String length;
    private int love;
    private String[] mDownloadImageUris;
    private String[] mDownloadVideoUris;
    private boolean mHistoryTodayHotPost;
    private boolean mIsRecsysData;
    private Object mMultiHistoryData;
    private ListItemObject$ShareInfo mShareInfo;
    public int mcollapsibleState = 0;
    private String mid;
    private String name;
    private int newItemType;
    private String noVoiceCmt;
    public String opends;
    private OperationItem operation;
    private String original_pid;
    private ListItemObject original_topic;
    @SerializedName("passtime")
    private String passtime;
    private int placeholder;
    private String playcount;
    private String playfcount;
    private MyCommentInfo precmt;
    @SerializedName("profile_image")
    private String profile;
    private String readid;
    private List<SuggestedFollowsListItem> recommendList;
    private String repost;
    @SerializedName("richtxt")
    private RichObject richObject;
    @SerializedName("screen_name")
    private String screen_name;
    private String sina_v;
    @SerializedName("thumbnail_small")
    private String smallImage;
    private int state;
    private int status;
    private String status_text;
    private int theme_id;
    private String theme_name;
    private int theme_type;
    private String title;
    private List<CommentItem> top_cmt;
    private String type;
    @SerializedName("user_id")
    private String uid;
    private String videotime;
    private String videouri;
    private String videouriBackup;
    private int voiceBgId;
    @SerializedName("voiceuri")
    private String voiceUri;
    private String voicelength;
    @SerializedName("voicetime")
    private String voicetime;
    private VoteData voteData;
    private String voteDataJson;
    private String weixin_url;
    private String werbo;
    @SerializedName("id")
    private String wid;
    private int width;

    public String getIs_vip() {
        return this.is_vip;
    }

    public void setIs_vip(String str) {
        this.is_vip = str;
    }

    public NativeAdRef getAdItem() {
        return this.adItem;
    }

    public void setAdItem(NativeAdRef nativeAdRef) {
        this.adItem = nativeAdRef;
    }

    public NativeAdData getAdItemData() {
        return this.adItemData;
    }

    public void setAdItemData(NativeAdData nativeAdData) {
        this.adItemData = nativeAdData;
    }

    public OperationItem getOperation() {
        return this.operation;
    }

    public void setOperation(OperationItem operationItem) {
        this.operation = operationItem;
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

    public boolean isShwFollow() {
        return this.isShwFollow;
    }

    public void setShwFollow(boolean z) {
        this.isShwFollow = z;
    }

    public RichObject getRichObject() {
        return this.richObject;
    }

    public void setRichObject(RichObject richObject) {
        this.richObject = richObject;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public boolean getIfPP() {
        return this.ifPP;
    }

    public void setIfPP(boolean z) {
        this.ifPP = z;
    }

    public boolean isIfRepostBigerUmeng() {
        return this.ifRepostBigerUmeng;
    }

    public void setIfRepostBigerUmeng(boolean z) {
        this.ifRepostBigerUmeng = z;
    }

    public MyCommentInfo getHotcmt() {
        return this.hotcmt;
    }

    public void setHotcmt(MyCommentInfo myCommentInfo) {
        this.hotcmt = myCommentInfo;
    }

    public MyCommentInfo getPrecmt() {
        return this.precmt;
    }

    public void setPrecmt(MyCommentInfo myCommentInfo) {
        this.precmt = myCommentInfo;
    }

    public List<MyCommentInfo> getHotComments() {
        return this.hotComments;
    }

    public void setHotComments(List<MyCommentInfo> list) {
        this.hotComments = list;
    }

    public String getHotCommentsJson() {
        return this.hotCommentsJson;
    }

    public void setHotCommentsJson(String str) {
        this.hotCommentsJson = str;
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        MyCommentInfo b = com.budejie.www.j.a.b(jSONArray.getJSONObject(i));
                        if (b != null) {
                            this.hotComments.add(b);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isTopTopic() {
        return this.isTopTopic;
    }

    public void setTopTopic(boolean z) {
        this.isTopTopic = z;
    }

    public boolean getIsDraftBean() {
        return this.isDraftBean;
    }

    public void setIsDraftBean(boolean z) {
        this.isDraftBean = z;
    }

    public String getCmdShowTime() {
        return this.cmdShowTime;
    }

    public void setCmdShowTime(String str) {
        this.cmdShowTime = str;
    }

    public boolean isForwardNoCollect() {
        return this.forwardNoCollect;
    }

    public void setForwardNoCollect(boolean z) {
        this.forwardNoCollect = z;
    }

    public boolean isForwardAndCollect_isweixin() {
        return this.forwardAndCollect_isweixin;
    }

    public void setForwardAndCollect_isweixin(boolean z) {
        this.forwardAndCollect_isweixin = z;
    }

    public boolean isForwardAndCollectFlag() {
        return this.forwardAndCollectFlag;
    }

    public void setForwardAndCollectFlag(boolean z) {
        this.forwardAndCollectFlag = z;
    }

    public boolean isForwardAndCollect() {
        return this.forwardAndCollect;
    }

    public void setForwardAndCollect(boolean z) {
        this.forwardAndCollect = z;
    }

    public int getNewItemType() {
        return this.newItemType;
    }

    public void setNewItemType(int i) {
        this.newItemType = i;
    }

    public List<SuggestedFollowsListItem> getRecommendList() {
        return this.recommendList;
    }

    public void setRecommendList(List<SuggestedFollowsListItem> list) {
        this.recommendList = list;
    }

    public boolean getIfLocal() {
        return this.ifLocal;
    }

    public void setIfLocal(boolean z) {
        this.ifLocal = z;
    }

    public int getVoiceBgId() {
        return this.voiceBgId;
    }

    public void setVoiceBgId(int i) {
        this.voiceBgId = i;
    }

    public String getStatus_text() {
        return this.status_text;
    }

    public void setStatus_text(String str) {
        this.status_text = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getHasData() {
        return this.hasData;
    }

    public void setHasData(String str) {
        this.hasData = str;
    }

    public String getReadid() {
        return this.readid;
    }

    public void setReadid(String str) {
        this.readid = str;
    }

    public String getWid() {
        return this.wid;
    }

    public void setWid(String str) {
        this.wid = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getId() {
        return this.dbId;
    }

    public void setId(int i) {
        this.dbId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddtime() {
        return this.addtime;
    }

    public void setAddtime(String str) {
        this.addtime = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getWerbo() {
        return this.werbo;
    }

    public void setWerbo(String str) {
        this.werbo = str;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String str) {
        this.mid = str;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public String getScreen_name() {
        return this.screen_name;
    }

    public void setScreen_name(String str) {
        this.screen_name = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getRepost() {
        return this.repost;
    }

    public void setRepost(String str) {
        this.repost = str;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setImgPath(String str) {
        this.imgPath = str;
    }

    public String getImgUrl() {
        if (!TextUtils.isEmpty(this.imgUrl)) {
            return this.imgUrl;
        }
        switch (BudejieApplication.h) {
            case 0:
                if (!TextUtils.isEmpty(this.image0)) {
                    this.imgUrl = this.image0;
                    break;
                }
            case 1:
                if (!TextUtils.isEmpty(this.image1)) {
                    this.imgUrl = this.image1;
                    break;
                }
            case 2:
                if (TextUtils.isEmpty(this.image2)) {
                    if (BudejieApplication.h != 2) {
                        if (BudejieApplication.h == 1) {
                            this.imgUrl = this.image0;
                            break;
                        }
                    }
                    this.imgUrl = !TextUtils.isEmpty(this.image1) ? this.image1 : this.image0;
                    break;
                }
                this.imgUrl = this.image2;
                break;
                break;
        }
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getCnd_img() {
        return this.cnd_img;
    }

    public void setCnd_img(String str) {
        this.cnd_img = str;
    }

    public String getcnd_SmallImg() {
        return this.cnd_SmallImg;
    }

    public void setcnd_SmallImg(String str) {
        this.cnd_SmallImg = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String str) {
        this.profile = str;
    }

    public int getFavorite() {
        return this.favorite;
    }

    public void setFavorite(int i) {
        this.favorite = i;
    }

    public int getLove() {
        return this.love;
    }

    public void setLove(int i) {
        this.love = i;
    }

    public String getIs_gif() {
        return this.is_gif;
    }

    public void setIs_gif(String str) {
        this.is_gif = str;
    }

    public boolean isIs_ad() {
        return this.is_ad;
    }

    public void setIs_ad(boolean z) {
        this.is_ad = z;
    }

    public String getAd_url() {
        return this.ad_url;
    }

    public void setAd_url(String str) {
        this.ad_url = str;
    }

    public String getAd_type() {
        return this.ad_type;
    }

    public void setAd_type(String str) {
        this.ad_type = str;
    }

    public String getGifFistFrame() {
        return this.gifFistFrame;
    }

    public void setGifFistFrame(String str) {
        this.gifFistFrame = str;
    }

    public int getCai() {
        return this.cai;
    }

    public void setCai(int i) {
        this.cai = i;
    }

    public String getWeixin_url() {
        return this.weixin_url;
    }

    public void setWeixin_url(String str) {
        this.weixin_url = str;
    }

    public boolean isInsertRemind() {
        return this.insertRemind;
    }

    public void setInsertRemind(boolean z) {
        this.insertRemind = z;
    }

    public int getAd_id() {
        return this.ad_id;
    }

    public void setAd_id(int i) {
        this.ad_id = i;
    }

    public String getFavoriteTime() {
        return this.favoriteTime;
    }

    public void setFavoriteTime(String str) {
        this.favoriteTime = str;
    }

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

    public String getVoiceUri() {
        return this.voiceUri;
    }

    public void setVoiceUri(String str) {
        this.voiceUri = str;
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

    public String getNoVoiceCmt() {
        return this.noVoiceCmt;
    }

    public void setNoVoiceCmt(String str) {
        this.noVoiceCmt = str;
    }

    public String getCai_flag() {
        return this.cai_flag;
    }

    public void setCai_flag(String str) {
        this.cai_flag = str;
    }

    public String getPasstime() {
        return this.passtime;
    }

    public void setPasstime(String str) {
        this.passtime = str;
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

    public String getLength() {
        return this.length;
    }

    public void setLength(String str) {
        this.length = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ListItemObject)) {
            return false;
        }
        ListItemObject listItemObject = (ListItemObject) obj;
        if (TextUtils.isEmpty(this.wid) || !this.wid.equals(listItemObject.getWid()) || this.is_ad) {
            return false;
        }
        return true;
    }

    public void setPlaceholder(int i) {
        this.placeholder = i;
    }

    public int getPlaceholder() {
        return this.placeholder;
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

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getOriginal_pid() {
        return this.original_pid;
    }

    public void setOriginal_pid(String str) {
        this.original_pid = str;
    }

    public List<CommentItem> getTop_cmt() {
        return this.top_cmt;
    }

    public void setTop_cmt(List<CommentItem> list) {
        this.top_cmt = list;
    }

    public ListItemObject getOriginal_topic() {
        return this.original_topic;
    }

    public void setOriginal_topic(ListItemObject listItemObject) {
        this.original_topic = listItemObject;
    }

    public String getImage_small() {
        return this.image_small;
    }

    public void setImage_small(String str) {
        this.image_small = str;
    }

    public String getImage0() {
        return this.image0;
    }

    public void setImage0(String str) {
        this.image0 = str;
    }

    public String getImage1() {
        return this.image1;
    }

    public void setImage1(String str) {
        this.image1 = str;
    }

    public String getImage2() {
        return this.image2;
    }

    public void setImage2(String str) {
        this.image2 = str;
    }

    public String getsmallImage() {
        return this.smallImage;
    }

    public void setSmallImage(String str) {
        this.smallImage = str;
    }

    public boolean ismHistoryTodayHotPost() {
        return this.mHistoryTodayHotPost;
    }

    public void setmHistoryTodayHotPost(boolean z) {
        this.mHistoryTodayHotPost = z;
    }

    public Object getmMultiHistoryData() {
        return this.mMultiHistoryData;
    }

    public void setmMultiHistoryData(Object obj) {
        this.mMultiHistoryData = obj;
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

    public boolean getIsIsRecsysData() {
        return this.mIsRecsysData;
    }

    public void setIsRecsysData(boolean z) {
        this.mIsRecsysData = z;
    }

    public ListItemObject$ShareInfo getShareInfo() {
        return this.mShareInfo;
    }

    public void setShareInfo(ListItemObject$ShareInfo listItemObject$ShareInfo) {
        this.mShareInfo = listItemObject$ShareInfo;
    }

    public ArrayList<HeadPortraitItem> getHeadPortraitItems() {
        return this.headPortraitItems;
    }

    public void setHeadPortraitItems(ArrayList<HeadPortraitItem> arrayList) {
        this.headPortraitItems = arrayList;
    }

    public int getVisibilityPercents(View view) {
        return 100;
    }

    public boolean getIsPlayArea(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int height = view.getHeight();
        int i = iArr[1];
        if (BudejieApplication.i / 2 < i || BudejieApplication.i / 2 > i + height) {
            return false;
        }
        return true;
    }

    public void setActive(View view, int i, boolean z, boolean z2) {
        if (view == null) {
            return;
        }
        if (z) {
            TextView textView = (TextView) view.findViewById(R.id.recommend_post_item_mask);
            AutoPlayVideoListActivity.b.setDuration(300);
            textView.startAnimation(AutoPlayVideoListActivity.b);
            textView.setVisibility(8);
            return;
        }
        if (z2) {
            textView = (TextView) view.findViewById(R.id.recommend_post_item_mask);
            AutoPlayVideoListActivity.b.setDuration(300);
            textView.startAnimation(AutoPlayVideoListActivity.b);
            textView.setVisibility(8);
        }
        view.findViewById(R.id.video_play_btn).performClick();
    }

    public void deactivate(View view, int i) {
        if (view != null) {
            view.findViewById(R.id.video_stop_btn).performClick();
            TextView textView = (TextView) view.findViewById(R.id.recommend_post_item_mask);
            AutoPlayVideoListActivity.a.setDuration(300);
            textView.startAnimation(AutoPlayVideoListActivity.a);
            textView.setVisibility(0);
        }
    }

    public boolean isDelete() {
        if (getStatus() == 11 || getStatus() == 12) {
            return true;
        }
        return false;
    }

    public String getVoteDataJson() {
        return this.voteDataJson;
    }

    public void setVoteDataJson(String str) {
        aa.b("ListItemObject", "setVoteDataJson voteDataJson=" + str);
        this.voteDataJson = str;
        this.voteData = com.budejie.www.j.a.b(str);
        if (this.voteData != null) {
            this.voteData.pid = this.wid;
        }
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
        this.PlateDatas = com.budejie.www.j.a.c(this.PlateDatasJson);
    }

    public PlateBean getPlateBean(int i) {
        try {
            return (PlateBean) this.PlateDatas.get(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPlateNames() {
        String str = "";
        if (this.PlateDatas == null) {
            return str;
        }
        Iterator it = this.PlateDatas.iterator();
        String str2 = str;
        while (it.hasNext()) {
            str2 = str2 + "," + ((PlateBean) it.next()).theme_name;
        }
        if (str2.length() > 0) {
            return str2.substring(1);
        }
        return str2;
    }
}
