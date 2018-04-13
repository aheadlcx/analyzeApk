package cn.v6.sixrooms.room.utils;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.ConfigUpdateBean;
import cn.v6.sixrooms.presenter.BadgeConfigPresenter;
import cn.v6.sixrooms.room.engine.ConfigUpdateEngine.CallBack;
import cn.v6.sixrooms.room.gift.GiftConfigPresenter;
import cn.v6.sixrooms.room.gift.GiftNumConfigPresenter;
import cn.v6.sixrooms.room.presenter.StickerConfigPresenter;
import cn.v6.sixrooms.utils.LogUtils;

final class b implements CallBack {
    b() {
    }

    public final void result(ConfigUpdateBean configUpdateBean) {
        if (configUpdateBean == null) {
            LogUtils.e(ConfigUpdataDispatcher.TAG, "配置更新：没有新配置文件。");
            return;
        }
        if (!TextUtils.isEmpty(configUpdateBean.getDown())) {
            new GiftConfigPresenter().downLoadGiftConfig(configUpdateBean);
        }
        if (!TextUtils.isEmpty(configUpdateBean.getPdown())) {
            new BadgeConfigPresenter().downLoadBadgeConfig(configUpdateBean);
        }
        if (!TextUtils.isEmpty(configUpdateBean.getFdown())) {
            new StickerConfigPresenter().downLoadPasterConfig(configUpdateBean);
        }
        if (!TextUtils.isEmpty(configUpdateBean.getNdown())) {
            new GiftNumConfigPresenter().downLoadGiftNumConfig(configUpdateBean);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        LogUtils.e(ConfigUpdataDispatcher.TAG, "配置更新：" + str + str2);
    }

    public final void error(int i) {
        LogUtils.e(ConfigUpdataDispatcher.TAG, "配置更新error：" + i);
    }
}
