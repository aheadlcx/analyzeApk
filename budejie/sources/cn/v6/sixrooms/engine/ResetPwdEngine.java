package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.message.BasicNameValuePair;

public class ResetPwdEngine {
    protected static final String TAG = "ResetPwdEngine";
    private static final String b = UrlStrs.URL_RESET_PASSWORD;
    private ResetPwdCallBack a;

    public interface ResetPwdCallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(String str);
    }

    public ResetPwdEngine(ResetPwdCallBack resetPwdCallBack) {
        this.a = resetPwdCallBack;
    }

    public void resetPwd(String str, String str2, String str3, String str4, String str5, String str6) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("uname", str));
        arrayList.add(new BasicNameValuePair("mobile", str2));
        arrayList.add(new BasicNameValuePair(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str3));
        arrayList.add(new BasicNameValuePair(HistoryOpenHelper.COLUMN_UID, str4));
        arrayList.add(new BasicNameValuePair("passwd1", str5));
        arrayList.add(new BasicNameValuePair("passwd2", str6));
        arrayList.add(new BasicNameValuePair("mode", "phone"));
        LogUtils.d(TAG, "resetPwd----list----" + arrayList.toString());
        String str7 = b + "?domain=Android";
        LogUtils.d(TAG, "resetPwd----url----" + str7);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new as(this), str7, arrayList);
    }
}
