package cn.v6.sixrooms.room.interfaces;

import cn.v6.sixrooms.presenter.runnable.BaseRoomActivityErrorable;
import cn.v6.sixrooms.room.bean.OnHeadlineBean;
import java.util.List;

public interface HeadLineViewable extends BaseRoomActivityErrorable {
    void dismiss();

    void showHeadLineDetail();

    void updateCountDownTime(String str);

    void updateTop8View(List<OnHeadlineBean> list);
}
