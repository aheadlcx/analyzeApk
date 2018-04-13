package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

@SuppressLint({"HandlerLeak"})
public class RoomRepertoryGiftEngine {
    protected static final String TAG = RoomRepertoryGiftEngine.class.getSimpleName();
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void result(ArrayList<RepertoryBean> arrayList);
    }

    public RoomRepertoryGiftEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getRepertory(String str, String str2, String str3) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("r", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("logiuid", str2);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("encpass", str3);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair(a.k, "1.5");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new bc(this), UrlStrs.URL_INDEX_INFO + "?padapi=room-roomUser.php", arrayList);
    }
}
