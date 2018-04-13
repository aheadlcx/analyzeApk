package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import java.util.List;

@SuppressLint({"HandlerLeak"})
public class FansListEngine {
    protected static final String TAG = "FansListEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void fansList(List<FansBean> list, List<FansBean> list2);

        void fansList(List<FansBean> list, List<FansBean> list2, List<FansBean> list3);

        void handleErrorInfo(String str, String str2);
    }

    public FansListEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getNowFansList(String str, String str2) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new q(this), UrlStrs.ROOM_FANS + "?id=" + str + "&t=" + str2, "");
    }

    public void getSupperSortFansList(String str) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new r(this), UrlStrs.ROOM_ALL_FANS + "?uid=" + str, "");
    }
}
