package cn.xiaochuankeji.tieba.abmgr.data;

public enum Requirement {
    HOME_REFRESH("主页刷新按钮", "ab_short_refresh_btn", RequireStyle.HOME_REFRESH_VISIBLE, RequireStyle.HOME_REFRESH_GONE);
    
    public String description;
    public String key;
    public RequireStyle[] requireStyleArray;

    private Requirement(String str, String str2, RequireStyle... requireStyleArr) {
        this.requireStyleArray = requireStyleArr;
        this.description = str;
        this.key = str2;
    }
}
