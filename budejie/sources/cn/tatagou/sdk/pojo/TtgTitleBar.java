package cn.tatagou.sdk.pojo;

import android.graphics.Color;
import android.graphics.Typeface;

public class TtgTitleBar {
    private static TtgTitleBar sInstance;
    private String backIcon;
    private int backIconColor = Color.parseColor("#4f4f4f");
    private int backIconLeftPadding = 12;
    private int backIconRightPadding = 15;
    private int backIconSearchRightPadding = 15;
    private int backIconSize = 20;
    private int bgColor = Color.parseColor("#FFFFFF");
    private String cartIcon;
    private int cartIconSize;
    private int circleColor = Color.parseColor("#FF0000");
    private int height = 56;
    private int iconColor = Color.parseColor("#505050");
    private boolean isBackIconShown = false;
    private boolean isCartIconShown = false;
    private boolean isChangeStatusBarFontColor = false;
    private boolean isMyShoppingIconShown = true;
    private boolean isSearchCenter = false;
    private boolean isSearchIconRight = false;
    private boolean isSearchIconShown = true;
    private boolean isSearchShown = true;
    private boolean isTabBackShown = true;
    private boolean isTitleCenter = false;
    private boolean isTitleIconShown = false;
    private String myShoppingIcon;
    private int myShoppingIconSize;
    private int searchColor = Color.parseColor("#f4f5f6");
    private String searchIcon;
    private int searchIconSize;
    private int smallCircleXSize = 27;
    private int smallCircleYSize = 0;
    private boolean spStatusBar = false;
    private int statusBarBgColor = -1;
    private String title = "她他精选";
    private int titleColor = Color.parseColor("#505050");
    private Typeface titleFont;
    private int titleSize = 20;

    public static TtgTitleBar getInstance() {
        if (sInstance == null) {
            sInstance = new TtgTitleBar();
        }
        return sInstance;
    }

    public int getBackIconLeftPadding() {
        return this.backIconLeftPadding;
    }

    public int getBackIconSearchRightPadding() {
        return this.backIconSearchRightPadding;
    }

    public int getBackIconRightPadding() {
        return this.backIconRightPadding;
    }

    public TtgTitleBar setBackIconRightPadding(int i) {
        this.backIconRightPadding = i;
        return this;
    }

    public TtgTitleBar setBackIconSearchRightPadding(int i) {
        this.backIconSearchRightPadding = i;
        return this;
    }

    public TtgTitleBar setBackIconLeftPadding(int i) {
        this.backIconLeftPadding = i;
        return this;
    }

    public int getBackIconSize() {
        return this.backIconSize;
    }

    public TtgTitleBar setBackIconSize(int i) {
        this.backIconSize = i;
        return this;
    }

    public TtgTitleBar setBackIcon(String str, int i) {
        this.backIconSize = i;
        this.backIcon = str;
        return this;
    }

    public String getBackIcon() {
        return this.backIcon;
    }

    public int getBackIconColor() {
        return this.backIconColor;
    }

    public TtgTitleBar setBackIconColor(int i) {
        this.backIconColor = i;
        return this;
    }

    public String getSearchIcon() {
        return this.searchIcon;
    }

    public int getSearchIconSize() {
        return this.searchIconSize;
    }

    public TtgTitleBar setSearchIconSize(int i) {
        this.searchIconSize = i;
        return this;
    }

    public int getSmallCircleXSize() {
        return this.smallCircleXSize;
    }

    public TtgTitleBar setSmallCirclePositionSize(int i, int i2) {
        this.smallCircleXSize = i;
        this.smallCircleYSize = i2;
        return this;
    }

    public int getSmallCircleYSize() {
        return this.smallCircleYSize;
    }

    public TtgTitleBar setSmallCircleYSize(int i) {
        this.smallCircleYSize = i;
        return this;
    }

    public boolean isSearchShown() {
        return this.isSearchShown;
    }

    public TtgTitleBar setSearchShown(boolean z) {
        this.isSearchShown = z;
        return this;
    }

    public TtgTitleBar setSearchIcon(String str) {
        this.searchIcon = str;
        return this;
    }

    public int getCircleColor() {
        return this.circleColor;
    }

    public TtgTitleBar setCircleColor(int i) {
        this.circleColor = i;
        return this;
    }

    public boolean isSearchIconShown() {
        return this.isSearchIconShown;
    }

    public TtgTitleBar setSearchIconShown(boolean z) {
        this.isSearchIconShown = z;
        return this;
    }

    public boolean isSearchIconRight() {
        return this.isSearchIconRight;
    }

    public TtgTitleBar setSearchIconRight(boolean z) {
        this.isSearchIconRight = z;
        return this;
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public TtgTitleBar setBgColor(int i) {
        this.bgColor = i;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public TtgTitleBar setTitle(String str) {
        this.title = str;
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public TtgTitleBar setHeight(int i) {
        this.height = i;
        return this;
    }

    public boolean isTitleCenter() {
        return this.isTitleCenter;
    }

    public TtgTitleBar setTitleCenter(boolean z) {
        this.isTitleCenter = z;
        return this;
    }

    public int getTitleSize() {
        return this.titleSize;
    }

    public TtgTitleBar setTitleSize(int i) {
        this.titleSize = i;
        return this;
    }

    public Typeface getTitleFont() {
        return this.titleFont;
    }

    public TtgTitleBar setTitleFont(Typeface typeface) {
        this.titleFont = typeface;
        return this;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public TtgTitleBar setTitleColor(int i) {
        this.titleColor = i;
        return this;
    }

    public int getIconColor() {
        return this.iconColor;
    }

    public TtgTitleBar setIconColor(int i) {
        this.iconColor = i;
        return this;
    }

    public boolean isBackIconShown() {
        return this.isBackIconShown;
    }

    public TtgTitleBar setBackIconShown(boolean z) {
        this.isBackIconShown = z;
        return this;
    }

    public int getStatusBarBgColor() {
        return this.statusBarBgColor;
    }

    public TtgTitleBar setStatusBarBgColor(int i) {
        this.statusBarBgColor = i;
        return this;
    }

    public boolean spStatusBar() {
        return this.spStatusBar;
    }

    public TtgTitleBar setSpStatusBar(boolean z) {
        this.spStatusBar = z;
        return this;
    }

    public String getMyShoppingIcon() {
        return this.myShoppingIcon;
    }

    public int getMyShoppingIconSize() {
        return this.myShoppingIconSize;
    }

    public TtgTitleBar setMyShoppingIcon(String str, int i) {
        this.myShoppingIcon = str;
        this.myShoppingIconSize = i;
        return this;
    }

    public TtgTitleBar setMyShoppingIcon(String str) {
        this.myShoppingIcon = str;
        return this;
    }

    public String getCartIcon() {
        return this.cartIcon;
    }

    public int getCartIconSize() {
        return this.cartIconSize;
    }

    public TtgTitleBar setCartIconSize(int i) {
        this.cartIconSize = i;
        return this;
    }

    public TtgTitleBar setCartIcon(String str) {
        this.cartIcon = str;
        return this;
    }

    public boolean isCartIconShown() {
        return this.isCartIconShown;
    }

    public TtgTitleBar setCartIconShown(boolean z) {
        this.isCartIconShown = z;
        return this;
    }

    public boolean isMyShoppingIconShown() {
        return this.isMyShoppingIconShown;
    }

    public TtgTitleBar setMyShoppingIconShown(boolean z) {
        this.isMyShoppingIconShown = z;
        return this;
    }

    public boolean isSearchCenter() {
        return this.isSearchCenter;
    }

    public TtgTitleBar setSearchCenter(boolean z) {
        this.isSearchCenter = z;
        return this;
    }

    public boolean isTitleIconShown() {
        return this.isTitleIconShown;
    }

    public TtgTitleBar setTitleIconShown(boolean z) {
        this.isTitleIconShown = z;
        return this;
    }

    public int getSearchColor() {
        return this.searchColor;
    }

    public TtgTitleBar setSearchColor(int i) {
        this.searchColor = i;
        return this;
    }

    public boolean isTabBackShown() {
        return this.isTabBackShown;
    }

    public TtgTitleBar setTabBackShown(boolean z) {
        this.isTabBackShown = z;
        return this;
    }

    public boolean isChangeStatusBarFontColor() {
        return this.isChangeStatusBarFontColor;
    }

    public TtgTitleBar setChangeStatusBarFontColor(boolean z) {
        this.isChangeStatusBarFontColor = z;
        return this;
    }
}
