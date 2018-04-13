package cn.v6.sixrooms.pay.engine;

import android.annotation.SuppressLint;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.UrlUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class MakeOrderEngine {
    protected static final String TAG = "MakeOrderEwngine";
    private String a = "coop-mobile-payClientMakeOrder.php";
    private String b = "coop-mobile-makePayClientOrder.php";
    private CallBack c;

    public interface CallBack {
        void error(int i);

        void handleResult(String str, OrderBean orderBean);
    }

    public MakeOrderEngine(CallBack callBack) {
        this.c = callBack;
    }

    public void makeOrder(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("gatetype", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("encpass", str3);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("money", str4);
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("ovalue", str5);
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("pxuid", str6);
        BasicNameValuePair basicNameValuePair7 = new BasicNameValuePair("cardno", str7);
        BasicNameValuePair basicNameValuePair8 = new BasicNameValuePair("cardpwd", str8);
        BasicNameValuePair basicNameValuePair9 = new BasicNameValuePair("from_module", StatisticValue.getInstance().getFromRechargePageModule());
        BasicNameValuePair basicNameValuePair10 = new BasicNameValuePair("page", StatisticValue.getInstance().getRechargePage());
        BasicNameValuePair basicNameValuePair11 = new BasicNameValuePair("module", StatisticValue.getInstance().getRechargeModule());
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        arrayList.add(basicNameValuePair7);
        arrayList.add(basicNameValuePair8);
        arrayList.add(basicNameValuePair9);
        arrayList.add(basicNameValuePair10);
        arrayList.add(basicNameValuePair11);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new b(this), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, this.a), arrayList);
    }

    public void coopMakeOrder(String str, String str2, String str3, MakeOrderEngine$CoopRechargeCallBack makeOrderEngine$CoopRechargeCallBack) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("coop", V6Coop.mCoop);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("ovalue", str3);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new c(this, makeOrderEngine$CoopRechargeCallBack), UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, this.b), arrayList);
    }
}
