package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.MD5Utils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public class WebGiftEngine {
    private static final String a = WebGiftEngine.class.getSimpleName();

    public static void uploadGiftSlowData(String str) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("input", str);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("giftTicket", MD5Utils.getMD5Str(str + "X14~!L388"));
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new k(str), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-giftKartun.php", arrayList);
    }
}
