package cn.v6.sixrooms.engine;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.UrlUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class BuyItemInShopEngine {
    public static final String PADAPI = "user-prop-buyProp.php";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str);
    }

    public BuyItemInShopEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void sendRequest(String str, String str2, String str3, String str4, String str5, String str6) {
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, PADAPI);
        NetworkServiceSingleton createInstance = NetworkServiceSingleton.createInstance();
        VLAsyncHandler iVar = new i(this);
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("encpass", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("logiuid", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("type", "coin6");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("rid", str3));
        }
        arrayList.add(new BasicNameValuePair("id", str4));
        arrayList.add(new BasicNameValuePair(IXAdRequestInfo.MAX_TITLE_LENGTH, str5));
        if (!TextUtils.isEmpty(str6)) {
            arrayList.add(new BasicNameValuePair("trid", str6));
        }
        createInstance.sendAsyncRequest(iVar, padapiUrl, arrayList);
    }
}
