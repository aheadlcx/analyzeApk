package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.game.GameCenterBean;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class GameParamsEngine {
    private static final String[] a = new String[]{"encpass", "logiuid", "gid"};
    private CallBack b;

    public interface CallBack {
        void onGetFail(String str);

        void onGetParams(GameCenterBean gameCenterBean, String str, String str2);
    }

    public GameParamsEngine(CallBack callBack) {
        this.b = callBack;
    }

    private static List<NameValuePair> a(String[] strArr) {
        List<NameValuePair> arrayList = new ArrayList();
        for (int i = 0; i < a.length; i++) {
            arrayList.add(new BasicNameValuePair(a[i], strArr[i]));
        }
        return arrayList;
    }

    public void getGameParams(GameCenterBean gameCenterBean, String... strArr) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new u(this, gameCenterBean), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-loginGame.php", a(strArr));
    }
}
