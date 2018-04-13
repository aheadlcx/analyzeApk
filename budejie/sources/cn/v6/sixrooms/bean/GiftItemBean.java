package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class GiftItemBean extends GiftBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String animType;
    private String id;
    private boolean isSelected;
    private String largeName;
    private int level;
    private String name;
    private String originalName;
    private String price;
    private int rank;
    private String smallName;
    private String specialName;
    private String tag;

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String getAnimType() {
        return this.animType;
    }

    public void setAnimType(String str) {
        this.animType = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSmallName() {
        return this.smallName;
    }

    public void setSmallName(String str) {
        this.smallName = str;
    }

    public String getLargeName() {
        return this.largeName;
    }

    public void setLargeName(String str) {
        this.largeName = str;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String str) {
        this.originalName = str;
    }

    public String getSpecialName() {
        return this.specialName;
    }

    public void setSpecialName(String str) {
        this.specialName = str;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public static long getSerialversionuid() {
        return 1;
    }

    public String toString() {
        return "GiftItemBean [tag=" + this.tag + ", id=" + this.id + ", price=" + this.price + ", name=" + this.name + ", animType=" + this.animType + ", smallName=" + this.smallName + ", largeName=" + this.largeName + ", originalName=" + this.originalName + ", specialName=" + this.specialName + ", rank=" + this.rank + "]";
    }

    public static GiftItemBean cloneGiftInfo(GiftItemBean giftItemBean) {
        GiftItemBean giftItemBean2 = new GiftItemBean();
        giftItemBean2.level = giftItemBean.level;
        giftItemBean2.from = giftItemBean.from;
        giftItemBean2.to = giftItemBean.to;
        giftItemBean2.num = giftItemBean.num;
        giftItemBean2.originalName = giftItemBean.originalName;
        return giftItemBean2;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
