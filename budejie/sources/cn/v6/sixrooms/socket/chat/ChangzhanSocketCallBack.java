package cn.v6.sixrooms.socket.chat;

import cn.v6.sixrooms.bean.ChangzhanBeginBean;
import cn.v6.sixrooms.bean.ChangzhanFinalUsersBean;
import cn.v6.sixrooms.bean.ChangzhanFinishBean;
import cn.v6.sixrooms.bean.ChangzhanStatusBean;
import cn.v6.sixrooms.bean.ChangzhanTimeBean;

public interface ChangzhanSocketCallBack {
    void onChangzhanBegin(ChangzhanBeginBean changzhanBeginBean);

    void onChangzhanFinalUsersChange(ChangzhanFinalUsersBean changzhanFinalUsersBean);

    void onChangzhanFinish(ChangzhanFinishBean changzhanFinishBean);

    void onChangzhanStatusUpdate(ChangzhanStatusBean changzhanStatusBean);

    void onChangzhanTimeChange(ChangzhanTimeBean changzhanTimeBean);
}
