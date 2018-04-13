package cn.v6.sixrooms.room.utils;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.presenter.BadgeConfigPresenter;
import cn.v6.sixrooms.room.engine.ConfigUpdateEngine;
import cn.v6.sixrooms.room.gift.GiftConfigPresenter;
import cn.v6.sixrooms.room.gift.GiftNumConfigPresenter;
import cn.v6.sixrooms.room.presenter.StickerConfigPresenter;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class ConfigUpdataDispatcher {
    public static final String TAG = ConfigUpdataDispatcher.class.getSimpleName();

    public static void updateConfig() {
        ConfigUpdateEngine configUpdateEngine = new ConfigUpdateEngine(new b());
        LogUtils.e("test", "礼物版本号: -> " + new GiftConfigPresenter().getConfigVersion() + "   徽章版本号 -> " + new BadgeConfigPresenter().getConfigVersion() + "  贴纸版本号 -> " + new StickerConfigPresenter().getConfigVersion() + "  礼物数量图形版本号 -> " + new GiftNumConfigPresenter().getConfigVersion());
        configUpdateEngine.getConfigUpdate(new GiftConfigPresenter().getConfigVersion(), new BadgeConfigPresenter().getConfigVersion(), new StickerConfigPresenter().getConfigVersion(), new GiftNumConfigPresenter().getConfigVersion(), LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
    }
}
