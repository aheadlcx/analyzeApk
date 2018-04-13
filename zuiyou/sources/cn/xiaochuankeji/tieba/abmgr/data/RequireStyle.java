package cn.xiaochuankeji.tieba.abmgr.data;

public enum RequireStyle {
    HOME_REFRESH_VISIBLE("显示刷新按钮", 1, false),
    HOME_REFRESH_GONE("隐藏刷新按钮", 0, true);
    
    public String description;
    public boolean isDefault;
    public int value;

    private RequireStyle(String str, int i, boolean z) {
        this.description = str;
        this.isDefault = z;
        this.value = i;
    }
}
