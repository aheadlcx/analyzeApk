package cn.v6.sixrooms.mvp.interfaces;

import cn.v6.sixrooms.hall.BaseViewable;

public interface V6Viewable extends BaseViewable {
    void handleError(int i);

    void handleErrorInfo(String str, String str2);

    void hideLoading();

    void showLoading();
}
