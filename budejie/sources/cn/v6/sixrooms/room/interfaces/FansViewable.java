package cn.v6.sixrooms.room.interfaces;

import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.bean.GiftListItemBean;
import cn.v6.sixrooms.presenter.runnable.BaseRoomActivityErrorable;
import java.util.List;

public interface FansViewable extends BaseRoomActivityErrorable {
    void sendGiftListSocket();

    void updateFansView(List<FansBean> list);

    void updateGiftsView(List<GiftListItemBean> list);

    void updateSelectedType(int i);
}
