package cn.tatagou.sdk.pojo;

public class TitleBar {
    private int backIconRightPadding = TtgTitleBar.getInstance().getBackIconRightPadding();
    private boolean isCartIconShow;
    private boolean isLeftIconShow;
    private boolean isRightIconShow;
    private boolean isSearchIconRight = false;
    private boolean isSearchIconShown = false;
    private boolean isSearchShown;
    private boolean isSmallCircleShown = false;
    private boolean isTtgMain;
    private String title;
    private String tvMineIconFontCode;
    private int tvMineIconSize;
    private int tvRightIconSize;
    private String tvRightIconfontCode;
    private int tvSearchIconSize;
    private String tvSearchIconfontCode;

    public int getBackIconRightPadding() {
        return this.backIconRightPadding;
    }

    public void setBackIconRightPadding(int i) {
        this.backIconRightPadding = i;
    }

    public boolean isTtgMain() {
        return this.isTtgMain;
    }

    public void setTtgMain(boolean z) {
        this.isTtgMain = z;
    }

    public int getTvSearchIconSize() {
        return this.tvSearchIconSize;
    }

    public void setTvSearchIconSize(int i) {
        this.tvSearchIconSize = i;
    }

    public String getTvSearchIconfontCode() {
        return this.tvSearchIconfontCode;
    }

    public void setTvSearchIconfontCode(String str) {
        this.tvSearchIconfontCode = str;
    }

    public boolean isSearchIconShown() {
        return this.isSearchIconShown;
    }

    public void setSearchIconShown(boolean z) {
        this.isSearchIconShown = z;
    }

    public boolean isSearchIconRight() {
        return this.isSearchIconRight;
    }

    public void setSearchIconRight(boolean z) {
        this.isSearchIconRight = z;
    }

    public String getTvMineIconFontCode() {
        return this.tvMineIconFontCode;
    }

    public void setTvMineIconFontCode(String str) {
        this.tvMineIconFontCode = str;
    }

    public int getTvMineIconSize() {
        return this.tvMineIconSize;
    }

    public void setTvMineIconSize(int i) {
        this.tvMineIconSize = i;
    }

    public boolean isSearchShown() {
        return this.isSearchShown;
    }

    public boolean isCartIconShow() {
        return this.isCartIconShow;
    }

    public void setCartIconShow(boolean z) {
        this.isCartIconShow = z;
    }

    public void setSearchShown(boolean z) {
        this.isSearchShown = z;
    }

    public boolean isLeftIconShow() {
        return this.isLeftIconShow;
    }

    public void setLeftIconShow(boolean z) {
        this.isLeftIconShow = z;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTvRightIconfontCode() {
        return this.tvRightIconfontCode;
    }

    public void setTvRightIconfontCode(String str) {
        this.tvRightIconfontCode = str;
    }

    public boolean isRightIconShow() {
        return this.isRightIconShow;
    }

    public void setRightIconShow(boolean z) {
        this.isRightIconShow = z;
    }

    public int getTvRightIconSize() {
        return this.tvRightIconSize;
    }

    public void setTvRightIconSize(int i) {
        this.tvRightIconSize = i;
    }

    public boolean isSmallCircleShown() {
        return this.isSmallCircleShown;
    }

    public void setSmallCircleShown(boolean z) {
        this.isSmallCircleShown = z;
    }
}
