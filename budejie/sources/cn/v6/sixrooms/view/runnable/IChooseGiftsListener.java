package cn.v6.sixrooms.view.runnable;

import android.content.Context;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import java.util.ArrayList;

public interface IChooseGiftsListener {
    void anonymousSend(String str, String str2, String str3, int i, int i2, String str4);

    Context getActivity();

    ArrayList<RepertoryBean> getStockList();

    void gotoLogin();

    void gotoRecharge();

    WrapRoomInfo obtainWrapRoomInfo();

    void onDismiss();

    void publicSend(String str, String str2, String str3, int i, int i2, String str4);
}
