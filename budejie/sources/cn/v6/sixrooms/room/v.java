package cn.v6.sixrooms.room;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.ConfigureInfoBean;
import cn.v6.sixrooms.engine.GetInfoEngine.CallBack;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import java.util.Map;

final class v implements CallBack {
    final /* synthetic */ RoomActivity a;

    v(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void result(ConfigureInfoBean configureInfoBean) {
        LogUtils.e("test", "ConfigureInfoBean banzou -> " + configureInfoBean.getBanzou());
        SharedPreferencesUtils.putOnDefault(V6Coop.getInstance().getContext(), 0, SharedPreferencesUtils.GPS_CONFIGURE, configureInfoBean.getGps());
        SharedPreferencesUtils.putOnDefault(V6Coop.getInstance().getContext(), 0, SharedPreferencesUtils.CODEC_CONFIGURE, configureInfoBean.getLivesize());
        SharedPreferencesUtils.put(0, SharedPreferencesUtils.SOUND_SWITCH, configureInfoBean.getBanzou());
        SharedPreferencesUtils.put(0, SharedPreferencesUtils.MINI_GAME_SWITCH, configureInfoBean.getGame());
        SharedPreferencesUtils.put(GiftConfigUtil.H5_FILE, 0, GiftConfigUtil.H5_KEY, configureInfoBean.getH5flag());
        Map giftResMap = configureInfoBean.getGiftResMap();
        if (giftResMap != null) {
            for (String str : giftResMap.keySet()) {
                SharedPreferencesUtils.put(GiftConfigUtil.GIFT_RES_URL_FILE, 32768, str, giftResMap.get(str));
            }
        }
    }

    public final void error(int i) {
    }
}
