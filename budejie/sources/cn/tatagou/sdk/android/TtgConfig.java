package cn.tatagou.sdk.android;

import android.graphics.Color;
import cn.tatagou.sdk.e.a;
import cn.tatagou.sdk.pojo.TtgTitleBar;

public class TtgConfig {
    private static TtgConfig sInstance;
    private int cachePages = 2;
    private int cateBarColor = this.themeColor;
    private int pid;
    private int rmTextColor = Color.parseColor("#FF276CE1");
    private int themeColor = Color.parseColor("#FFFF2738");

    public static TtgConfig getInstance() {
        if (sInstance == null) {
            sInstance = new TtgConfig();
        }
        return sInstance;
    }

    public int getRmTextColor() {
        return this.rmTextColor;
    }

    public TtgConfig setRmTextColor(int i) {
        this.rmTextColor = i;
        return this;
    }

    public TtgTitleBar getTitleBar() {
        return TtgTitleBar.getInstance();
    }

    public int getPid() {
        return this.pid;
    }

    public TtgConfig setPid(int i) {
        this.pid = i;
        a.init(TtgSDK.getContext()).setPid(String.valueOf(i));
        return this;
    }

    public int getCachePages() {
        return this.cachePages;
    }

    public TtgConfig setCachePages(int i) {
        this.cachePages = i;
        return this;
    }

    public int getThemeColor() {
        return this.themeColor;
    }

    public TtgConfig setThemeColor(int i) {
        this.themeColor = i;
        return this;
    }

    public int getCateBarColor() {
        return this.cateBarColor;
    }

    public TtgConfig setCateBarColor(int i) {
        this.cateBarColor = i;
        return this;
    }
}
