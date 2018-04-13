package com.spriteapp.booklibrary.config;

import android.content.Context;
import com.spriteapp.booklibrary.listener.ActivityResultListener;
import com.spriteapp.booklibrary.listener.ChannelListener;

public class HuaXiConfig$Builder {
    private int backImageResource;
    private int bottomBackground;
    private int channelId;
    private ChannelListener channelListener;
    private int clientId;
    private Context context;
    private boolean isNightMode;
    private ActivityResultListener resultListener;
    private int rightTitleColor;
    private String signSecret;
    private int statusBarColor;
    private int titleBackground;
    private int titleColor;

    public HuaXiConfig$Builder setChannelId(int i) {
        this.channelId = i;
        return this;
    }

    public HuaXiConfig$Builder setClientId(int i) {
        this.clientId = i;
        return this;
    }

    public HuaXiConfig$Builder setSignSecret(String str) {
        this.signSecret = str;
        return this;
    }

    public HuaXiConfig$Builder setContext(Context context) {
        this.context = context;
        return this;
    }

    public HuaXiConfig$Builder setChannelListener(ChannelListener channelListener) {
        this.channelListener = channelListener;
        return this;
    }

    public HuaXiConfig$Builder setTitleBackground(int i) {
        this.titleBackground = i;
        return this;
    }

    public HuaXiConfig$Builder setTitleColor(int i) {
        this.titleColor = i;
        return this;
    }

    public HuaXiConfig$Builder setRightTitleColor(int i) {
        this.rightTitleColor = i;
        return this;
    }

    public HuaXiConfig$Builder setBottomBackground(int i) {
        this.bottomBackground = i;
        return this;
    }

    public HuaXiConfig$Builder setBackImageResource(int i) {
        this.backImageResource = i;
        return this;
    }

    public HuaXiConfig$Builder setStatusBarColor(int i) {
        this.statusBarColor = i;
        return this;
    }

    public HuaXiConfig$Builder setNightMode(boolean z) {
        this.isNightMode = z;
        return this;
    }

    public HuaXiConfig$Builder setResultListener(ActivityResultListener activityResultListener) {
        this.resultListener = activityResultListener;
        return this;
    }

    public HuaXiConfig build() {
        return new HuaXiConfig(this, null);
    }
}
