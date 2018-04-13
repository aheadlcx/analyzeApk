package cn.tatagou.sdk.pojo;

import java.util.List;

public class HomeData extends CommonResponseResult {
    private List<Special> bannerSpecialList;
    private List<Special> cateSpecialList;
    private int currPage;
    private HomeAd mainAd;
    private List<Special> normalSpecialList;
    private List<AppCate> specialCats;
    private String timestamp;

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public int getCurrPage() {
        return this.currPage;
    }

    public void setCurrPage(int i) {
        this.currPage = i;
    }

    public List<Special> getBannerSpecialList() {
        return this.bannerSpecialList;
    }

    public void setBannerSpecialList(List<Special> list) {
        this.bannerSpecialList = list;
    }

    public List<Special> getCateSpecialList() {
        return this.cateSpecialList;
    }

    public void setCateSpecialList(List<Special> list) {
        this.cateSpecialList = list;
    }

    public List<Special> getNormalSpecialList() {
        return this.normalSpecialList;
    }

    public void setNormalSpecialList(List<Special> list) {
        this.normalSpecialList = list;
    }

    public List<AppCate> getSpecialCats() {
        return this.specialCats;
    }

    public void setSpecialCats(List<AppCate> list) {
        this.specialCats = list;
    }

    public HomeAd getMainAd() {
        return this.mainAd;
    }

    public void setMainAd(HomeAd homeAd) {
        this.mainAd = homeAd;
    }
}
