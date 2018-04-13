package cn.v6.sixrooms.ui.view.indicator;

import java.util.List;

public interface IPagerIndicator {
    void onPageScrollStateChanged(int i);

    void onPageScrolled(int i, float f, int i2);

    void onPageSelected(int i);

    void onPositionDataProvide(List<PositionData> list);
}
