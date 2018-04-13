package cn.tatagou.sdk.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class AppHomeData {
    private static AppHomeData sInstance;
    private List<Special> browserCateSpList;
    private HomeData homeData;
    private RcmmParam rcmmParam;
    private int rcmmSexNum;
    private String sex;
    private List<String> sexList;
    private Set<Special> spHistorySet;
    private TtgUrl ttgUrl;
    private List<Special> unBrowserCateSpList;

    public static AppHomeData getInstance() {
        if (sInstance == null) {
            sInstance = new AppHomeData();
        }
        return sInstance;
    }

    public HomeData getHomeData() {
        return this.homeData;
    }

    public void setHomeData(HomeData homeData) {
        this.homeData = homeData;
    }

    public TtgUrl getTtgUrl() {
        return this.ttgUrl;
    }

    public void setTtgUrl(TtgUrl ttgUrl) {
        this.ttgUrl = ttgUrl;
    }

    public RcmmParam getRcmmParam() {
        return this.rcmmParam;
    }

    public void setRcmmParam(RcmmParam rcmmParam) {
        this.rcmmParam = rcmmParam;
    }

    public Set<Special> getSpHistorySet() {
        return this.spHistorySet;
    }

    public int getSpHistorySize() {
        if (this.spHistorySet == null) {
            return 0;
        }
        return this.spHistorySet.size();
    }

    public void addAllSpHistorySet(List<Special> list) {
        if (this.spHistorySet == null) {
            this.spHistorySet = new CopyOnWriteArraySet();
        }
        this.spHistorySet.addAll(list);
    }

    public List<Special> getBrowserCateSpList() {
        return this.browserCateSpList;
    }

    public void setBrowserCateSpList(List<Special> list) {
        this.browserCateSpList = list;
    }

    public void setUnBrowserCateSpList(List<Special> list) {
        this.unBrowserCateSpList = list;
    }

    public void addAllBrowserCateSpList(List<Special> list) {
        if (this.browserCateSpList == null) {
            this.browserCateSpList = new ArrayList();
        }
        this.browserCateSpList.addAll(list);
    }

    public void addBrowserCateSpList(Special special) {
        if (this.browserCateSpList == null) {
            this.browserCateSpList = new ArrayList();
        }
        this.browserCateSpList.add(special);
    }

    public int getBrowserCateSpSize() {
        if (this.browserCateSpList == null) {
            return 0;
        }
        return this.browserCateSpList.size();
    }

    public void clearBrowserCateSpList() {
        if (this.browserCateSpList != null) {
            this.browserCateSpList.clear();
        }
    }

    public List<Special> getUnBrowserCateSpList() {
        return this.unBrowserCateSpList;
    }

    public void addAllUnBrowserCateSpList(List<Special> list) {
        if (this.unBrowserCateSpList == null) {
            this.unBrowserCateSpList = new ArrayList();
        }
        this.unBrowserCateSpList.addAll(list);
    }

    public int getUnBrowserCateSpSize() {
        if (this.unBrowserCateSpList == null) {
            return 0;
        }
        return this.unBrowserCateSpList.size();
    }

    public void addUnBrowserCateSpList(Special special) {
        if (this.unBrowserCateSpList == null) {
            this.unBrowserCateSpList = new ArrayList();
        }
        this.unBrowserCateSpList.add(special);
    }

    public void clearUnBrowserCateSpList() {
        if (this.unBrowserCateSpList != null) {
            this.unBrowserCateSpList.clear();
        }
    }

    public void clearSpecialSet() {
        if (this.spHistorySet != null) {
            this.spHistorySet.clear();
        }
    }

    public void clearSexList() {
        if (this.sexList != null) {
            this.sexList.clear();
        }
    }

    public List<String> getSexList() {
        return this.sexList;
    }

    public void setSexList(List<String> list) {
        this.sexList = list;
    }

    public int getRcmmSexNum() {
        return this.rcmmSexNum;
    }

    public void setRcmmSexNum(int i) {
        this.rcmmSexNum = i;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public void clear() {
        this.ttgUrl = null;
    }

    public void clearHistory() {
        clear();
        clearUnBrowserCateSpList();
        clearBrowserCateSpList();
        clearSpecialSet();
    }
}
