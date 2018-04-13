package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.bean.FindUserNameBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.http.message.BasicNameValuePair;

public class FindUsernameEngine {
    protected static final String TAG = FindUsernameEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void findUsernameSucceed(ArrayList<FindUserNameBean> arrayList);

        void handleErrorInfo(String str, String str2);
    }

    public FindUsernameEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void findUsername(String str, String str2) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("mobile", str2);
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        String padapiUrl = UrlUtils.getPadapiUrl(UrlStrs.URL_INDEX_INFO, "auth-findUsername.php");
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new s(this), padapiUrl, arrayList);
        LogUtils.i(TAG, "url=" + padapiUrl + "  list=" + arrayList);
    }

    static /* synthetic */ ArrayList a(String str) {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split("\\|");
        int i = 0;
        while (i < split.length) {
            FindUserNameBean findUserNameBean = new FindUserNameBean();
            findUserNameBean.setUsernameLeft(split[i]);
            if (i + 1 >= split.length) {
                findUserNameBean.setUsernameRight("");
            } else {
                findUserNameBean.setUsernameRight(split[i + 1]);
                i++;
            }
            arrayList.add(findUserNameBean);
            i++;
        }
        return arrayList;
    }
}
