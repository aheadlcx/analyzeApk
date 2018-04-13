package com.spriteapp.booklibrary.config;

import android.content.Context;
import com.spriteapp.booklibrary.listener.ActivityResultListener;
import com.spriteapp.booklibrary.listener.ChannelListener;

public class HuaXiConfig {
    final int backImageResource;
    final int bottomBackground;
    final int channelId;
    final ChannelListener channelListener;
    final int clientId;
    final Context context;
    final boolean isNightMode;
    final ActivityResultListener resultListener;
    final int rightTitleColor;
    final String signSecret;
    final int statusBarColor;
    final int titleBackground;
    final int titleColor;

    private HuaXiConfig(HuaXiConfig$Builder huaXiConfig$Builder) {
        this.channelId = HuaXiConfig$Builder.access$000(huaXiConfig$Builder);
        this.clientId = HuaXiConfig$Builder.access$100(huaXiConfig$Builder);
        this.signSecret = HuaXiConfig$Builder.access$200(huaXiConfig$Builder);
        this.context = HuaXiConfig$Builder.access$300(huaXiConfig$Builder);
        this.channelListener = HuaXiConfig$Builder.access$400(huaXiConfig$Builder);
        this.titleBackground = HuaXiConfig$Builder.access$500(huaXiConfig$Builder);
        this.titleColor = HuaXiConfig$Builder.access$600(huaXiConfig$Builder);
        this.rightTitleColor = HuaXiConfig$Builder.access$700(huaXiConfig$Builder);
        this.bottomBackground = HuaXiConfig$Builder.access$800(huaXiConfig$Builder);
        this.backImageResource = HuaXiConfig$Builder.access$900(huaXiConfig$Builder);
        this.statusBarColor = HuaXiConfig$Builder.access$1000(huaXiConfig$Builder);
        this.isNightMode = HuaXiConfig$Builder.access$1100(huaXiConfig$Builder);
        this.resultListener = HuaXiConfig$Builder.access$1200(huaXiConfig$Builder);
    }

    public Context getContext() {
        return this.context;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public int getClientId() {
        return this.clientId;
    }

    public String getSignSecret() {
        return this.signSecret;
    }

    public ChannelListener getChannelListener() {
        return this.channelListener;
    }

    public int getTitleBackground() {
        return this.titleBackground;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public int getRightTitleColor() {
        return this.rightTitleColor;
    }

    public int getBottomBackground() {
        return this.bottomBackground;
    }

    public int getBackImageResource() {
        return this.backImageResource;
    }

    public int getStatusBarColor() {
        return this.statusBarColor;
    }
}
