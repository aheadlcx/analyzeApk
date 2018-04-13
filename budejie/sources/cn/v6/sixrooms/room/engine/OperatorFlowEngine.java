package cn.v6.sixrooms.room.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.ChangzhanStatusBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.bean.OperatorFlowBean;
import cn.v6.sixrooms.utils.UrlUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class OperatorFlowEngine {
    protected static final String TAG = ChangzhanStatusBean.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(OperatorFlowBean operatorFlowBean);
    }

    public OperatorFlowEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getOperatorFlow(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("padapi", "coop-mobile-operatorFlowActivity.php");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new h(this), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList), "");
    }
}
