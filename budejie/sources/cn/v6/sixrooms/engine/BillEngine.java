package cn.v6.sixrooms.engine;

import android.util.SparseArray;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.BillBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class BillEngine {
    public static final int EXPENSE = 3;
    public static final int GIVE_GIFT = 1;
    public static final int PAGE_COUNT = 2;
    public static final int PAGE_NUMBER = 1;
    public static final int RECEIVE_GIFT = 0;
    public static final int RECHARGE = 2;
    public static final int TIME = 0;
    private OnCallBack a;

    public interface OnCallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void success(SparseArray<String> sparseArray, List<BillBean> list, int i);
    }

    public void setCallBack(OnCallBack onCallBack) {
        this.a = onCallBack;
    }

    public void getGift(String str, String str2, String str3, int i, int i2) {
        String url;
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler fVar = new f(this, i);
        List arrayList2 = new ArrayList();
        arrayList2.add(new BasicNameValuePair("count", "30"));
        arrayList2.add(new BasicNameValuePair("p", String.valueOf(i2)));
        String str4 = "user-detail.php";
        switch (i) {
            case 0:
                arrayList2.add(new BasicNameValuePair(IXAdRequestInfo.PHONE_TYPE, IXAdRequestInfo.GPS));
                arrayList2.add(new BasicNameValuePair(AppLinkConstants.TIME, str3));
                arrayList2.add(new BasicNameValuePair("padapi", str4));
                url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2);
                break;
            case 1:
                arrayList2.add(new BasicNameValuePair(IXAdRequestInfo.PHONE_TYPE, "p"));
                arrayList2.add(new BasicNameValuePair(AppLinkConstants.TIME, str3));
                arrayList2.add(new BasicNameValuePair("padapi", str4));
                url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2);
                break;
            case 2:
                arrayList2.add(new BasicNameValuePair("padapi", "user-liveorder.php"));
                url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2);
                break;
            case 3:
                arrayList2.add(new BasicNameValuePair(AppLinkConstants.TIME, str3));
                arrayList2.add(new BasicNameValuePair("padapi", "user-detailcon.php"));
                url = UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2);
                break;
            default:
                url = null;
                break;
        }
        createInstance.sendAsyncRequest(fVar, url, arrayList);
    }
}
