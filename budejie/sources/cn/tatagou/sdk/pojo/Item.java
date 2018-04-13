package cn.tatagou.sdk.pojo;

import java.util.List;

public class Item extends CommonResponseResult {
    private String accessTime;
    private String badges;
    private Coupon coupon;
    private String coverImg;
    private String finalPrice;
    private String gCat;
    private String gSubCat;
    private String id;
    private int isPriceChange;
    private String isTodayOnline;
    private List<Item> items;
    private String marker;
    private String menuPrice;
    private String onlineTime;
    private String oriPrice;
    private int realTaobaoStock;
    private String sellCount;
    private Special special;
    private String taobaoId;
    private String taobaoType;
    private String title;

    public int getRealTaobaoStock() {
        return this.realTaobaoStock;
    }

    public void setRealTaobaoStock(int i) {
        this.realTaobaoStock = i;
    }

    public int getIsPriceChange() {
        return this.isPriceChange;
    }

    public void setIsPriceChange(int i) {
        this.isPriceChange = i;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public Coupon getCoupon() {
        return this.coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public String getgSubCat() {
        return this.gSubCat;
    }

    public void setgSubCat(String str) {
        this.gSubCat = str;
    }

    public String getgCat() {
        return this.gCat;
    }

    public void setgCat(String str) {
        this.gCat = str;
    }

    public String getAccessTime() {
        return this.accessTime;
    }

    public void setAccessTime(String str) {
        this.accessTime = str;
    }

    public String getSellCount() {
        return this.sellCount;
    }

    public void setSellCount(String str) {
        this.sellCount = str;
    }

    public Special getSpecial() {
        return this.special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> list) {
        this.items = list;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getTaobaoId() {
        return this.taobaoId;
    }

    public void setTaobaoId(String str) {
        this.taobaoId = str;
    }

    public String getBadges() {
        return this.badges;
    }

    public void setBadges(String str) {
        this.badges = str;
    }

    public String getCoverImg() {
        return this.coverImg;
    }

    public void setCoverImg(String str) {
        this.coverImg = str;
    }

    public String getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(String str) {
        this.finalPrice = str;
    }

    public String getIsTodayOnline() {
        return this.isTodayOnline;
    }

    public void setIsTodayOnline(String str) {
        this.isTodayOnline = str;
    }

    public void setTodayOnline(String str) {
        this.isTodayOnline = str;
    }

    public String getOnlineTime() {
        return this.onlineTime;
    }

    public void setOnlineTime(String str) {
        this.onlineTime = str;
    }

    public String getOriPrice() {
        return this.oriPrice;
    }

    public void setOriPrice(String str) {
        this.oriPrice = str;
    }

    public String getTaobaoType() {
        return this.taobaoType;
    }

    public void setTaobaoType(String str) {
        this.taobaoType = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMenuPrice() {
        return this.menuPrice;
    }

    public void setMenuPrice(String str) {
        this.menuPrice = str;
    }
}
