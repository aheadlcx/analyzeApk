package cn.tatagou.sdk.pojo;

public class Config {
    private static Config sInstance;
    private String appDeviceId;
    private String appVersion;
    private String authFirst;
    private String catNoMoreHint;
    private String ip;
    private boolean isLaunchOpen = true;
    private String jsPatch;
    private String loginType;
    private int phoneSys = -1;
    private int rcmmNum = 10;
    private String rmEnd;
    private String rmFresh;
    private String rmbgn;
    private String sex;
    private String sexList;
    private String specialNoMoreHint;
    private String specialTopHint;
    private String tabTitle;
    private String timeForNewItems;
    private String traceId;
    private String ttgAboutPage;

    public int getPhoneSys() {
        return this.phoneSys;
    }

    public Config setPhoneSys(int i) {
        this.phoneSys = i;
        return this;
    }

    public String getSexList() {
        return this.sexList;
    }

    public Config setSexList(String str) {
        this.sexList = str;
        return this;
    }

    public static Config getInstance() {
        if (sInstance == null) {
            sInstance = new Config();
        }
        return sInstance;
    }

    public String getJsPatch() {
        return this.jsPatch;
    }

    public Config setJsPatch(String str) {
        this.jsPatch = str;
        return this;
    }

    public String getIp() {
        return this.ip;
    }

    public Config setIp(String str) {
        this.ip = str;
        return this;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public Config setTraceId(String str) {
        this.traceId = str;
        return this;
    }

    public String getLoginType() {
        return this.loginType;
    }

    public Config setLoginType(String str) {
        this.loginType = str;
        return this;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public Config setAppVersion(String str) {
        this.appVersion = str;
        return this;
    }

    public String getTtgAboutPage() {
        return this.ttgAboutPage;
    }

    public Config setTtgAboutPage(String str) {
        this.ttgAboutPage = str;
        return this;
    }

    public boolean isLaunchOpen() {
        return this.isLaunchOpen;
    }

    public Config setLaunchOpen(boolean z) {
        this.isLaunchOpen = z;
        return this;
    }

    public String getAppDeviceId() {
        return this.appDeviceId;
    }

    public Config setAppDeviceId(String str) {
        this.appDeviceId = str;
        return this;
    }

    public int getRcmmNum() {
        return this.rcmmNum;
    }

    public Config setRcmmNum(int i) {
        this.rcmmNum = i;
        return this;
    }

    public String getSex() {
        return this.sex;
    }

    public Config setSex(String str) {
        this.sex = str;
        return this;
    }

    public String getRmbgn() {
        return this.rmbgn;
    }

    public Config setRmbgn(String str) {
        this.rmbgn = str;
        return this;
    }

    public String getSpecialTopHint() {
        return this.specialTopHint;
    }

    public Config setSpecialTopHint(String str) {
        this.specialTopHint = str;
        return this;
    }

    public String getSpecialNoMoreHint() {
        return this.specialNoMoreHint;
    }

    public Config setSpecialNoMoreHint(String str) {
        this.specialNoMoreHint = str;
        return this;
    }

    public String getTimeForNewItems() {
        return this.timeForNewItems;
    }

    public Config setTimeForNewItems(String str) {
        this.timeForNewItems = str;
        return this;
    }

    public String getCatNoMoreHint() {
        return this.catNoMoreHint;
    }

    public Config setCatNoMoreHint(String str) {
        this.catNoMoreHint = str;
        return this;
    }

    public String getRmFresh() {
        return this.rmFresh;
    }

    public Config setRmFresh(String str) {
        this.rmFresh = str;
        return this;
    }

    public String getRmEnd() {
        return this.rmEnd;
    }

    public Config setRmEnd(String str) {
        this.rmEnd = str;
        return this;
    }

    public String getAuthFirst() {
        return this.authFirst;
    }

    public Config setAuthFirst(String str) {
        this.authFirst = str;
        return this;
    }

    public String getTabTitle() {
        return this.tabTitle;
    }

    public Config setTabTitle(String str) {
        this.tabTitle = str;
        return this;
    }
}
