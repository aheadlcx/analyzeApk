package cn.tatagou.sdk.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class Special implements Parcelable {
    public static final Creator<Special> CREATOR = new Creator<Special>() {
        public Special createFromParcel(Parcel parcel) {
            return new Special(parcel);
        }

        public Special[] newArray(int i) {
            return new Special[i];
        }
    };
    private String accessTime;
    private String coverImg;
    private double coverImgScale;
    private int crmmFlag = 0;
    private int defaultSort;
    private String gSubCat;
    private String id;
    private String intro;
    private String isBanner;
    private String isCountDown;
    private String isShowCountdown;
    private String isShowViewNum;
    private String isTest;
    private Item item;
    private String lastEditTime;
    private String lastEditorId;
    private String marker;
    private String offlineTime;
    private String onlineTime;
    private String orderNum;
    private String platform;
    private String serverTime;
    private String sex;
    private String showTitle;
    private float spRi;
    private String specialCateId;
    private String status;
    private String subImage;
    private List<String> tags;
    private String testText;
    private String title;
    private String type;
    private String url;

    public void setTags(List<String> list) {
        this.tags = list;
    }

    public String getgSubCat() {
        return this.gSubCat;
    }

    public void setgSubCat(String str) {
        this.gSubCat = str;
    }

    public int getDefaultSort() {
        return this.defaultSort;
    }

    public void setDefaultSort(int i) {
        this.defaultSort = i;
    }

    public String getTestText() {
        return this.testText;
    }

    public void setTestText(String str) {
        this.testText = str;
    }

    public int getCrmmFlag() {
        return this.crmmFlag;
    }

    public void setCrmmFlag(int i) {
        this.crmmFlag = i;
    }

    public float getSpRi() {
        return this.spRi;
    }

    public void setSpRi(float f) {
        this.spRi = f;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public double getCoverImgScale() {
        return this.coverImgScale;
    }

    public void setCoverImgScale(double d) {
        this.coverImgScale = d;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getSubImage() {
        return this.subImage;
    }

    public void setSubImage(String str) {
        this.subImage = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Special(String str) {
        this.id = str;
    }

    protected Special(Parcel parcel) {
        this.coverImg = parcel.readString();
        this.id = parcel.readString();
        this.intro = parcel.readString();
        this.isShowCountdown = parcel.readString();
        this.isShowViewNum = parcel.readString();
        this.isTest = parcel.readString();
        this.lastEditTime = parcel.readString();
        this.lastEditorId = parcel.readString();
        this.offlineTime = parcel.readString();
        this.onlineTime = parcel.readString();
        this.orderNum = parcel.readString();
        this.platform = parcel.readString();
        this.sex = parcel.readString();
        this.showTitle = parcel.readString();
        this.specialCateId = parcel.readString();
        this.status = parcel.readString();
        this.title = parcel.readString();
        this.type = parcel.readString();
        this.isBanner = parcel.readString();
        this.serverTime = parcel.readString();
    }

    public String getAccessTime() {
        return this.accessTime;
    }

    public void setAccessTime(String str) {
        this.accessTime = str;
    }

    public String getServerTime() {
        return this.serverTime;
    }

    public String getIsCountDown() {
        return this.isCountDown;
    }

    public void setIsCountDown(String str) {
        this.isCountDown = str;
    }

    public void setServerTime(String str) {
        this.serverTime = str;
    }

    public String getIsBanner() {
        return this.isBanner;
    }

    public void setIsBanner(String str) {
        this.isBanner = str;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getCoverImg() {
        return this.coverImg;
    }

    public void setCoverImg(String str) {
        this.coverImg = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String str) {
        this.intro = str;
    }

    public String getIsShowCountdown() {
        return this.isShowCountdown;
    }

    public void setIsShowCountdown(String str) {
        this.isShowCountdown = str;
    }

    public String getIsShowViewNum() {
        return this.isShowViewNum;
    }

    public void setIsShowViewNum(String str) {
        this.isShowViewNum = str;
    }

    public String getIsTest() {
        return this.isTest;
    }

    public void setIsTest(String str) {
        this.isTest = str;
    }

    public String getLastEditTime() {
        return this.lastEditTime;
    }

    public void setLastEditTime(String str) {
        this.lastEditTime = str;
    }

    public String getLastEditorId() {
        return this.lastEditorId;
    }

    public void setLastEditorId(String str) {
        this.lastEditorId = str;
    }

    public String getOfflineTime() {
        return this.offlineTime;
    }

    public void setOfflineTime(String str) {
        this.offlineTime = str;
    }

    public String getOnlineTime() {
        return this.onlineTime;
    }

    public void setOnlineTime(String str) {
        this.onlineTime = str;
    }

    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String str) {
        this.orderNum = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getShowTitle() {
        return this.showTitle;
    }

    public void setShowTitle(String str) {
        this.showTitle = str;
    }

    public String getSpecialCateId() {
        return this.specialCateId;
    }

    public void setSpecialCateId(String str) {
        this.specialCateId = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.coverImg);
        parcel.writeString(this.id);
        parcel.writeString(this.intro);
        parcel.writeString(this.isShowCountdown);
        parcel.writeString(this.isShowViewNum);
        parcel.writeString(this.isTest);
        parcel.writeString(this.lastEditTime);
        parcel.writeString(this.lastEditorId);
        parcel.writeString(this.offlineTime);
        parcel.writeString(this.onlineTime);
        parcel.writeString(this.orderNum);
        parcel.writeString(this.platform);
        parcel.writeString(this.sex);
        parcel.writeString(this.showTitle);
        parcel.writeString(this.specialCateId);
        parcel.writeString(this.status);
        parcel.writeString(this.title);
        parcel.writeString(this.type);
        parcel.writeString(this.isBanner);
        parcel.writeString(this.serverTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.id.equals(((Special) obj).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
