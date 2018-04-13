package cn.v6.sixrooms.engine;

import android.location.Location;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.AppUpdateBean;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.utils.LocationUtil;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alipay.sdk.sys.a;
import com.qq.e.comm.constants.Constants.KEYS;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class AppUpdateEngine {
    public CallBack mCallBack;

    public interface CallBack {
        void error(int i);

        void requestUpdate(AppUpdateBean appUpdateBean);
    }

    public AppUpdateEngine(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public void update(String str, String str2) {
        String encryptContent;
        Object obj = "";
        if (GlobleValue.getUserBean() != null) {
            obj = GlobleValue.getUserBean().getId();
        } else if (TextUtils.isEmpty(obj)) {
            obj = SaveUserInfoUtils.getVisitorId(V6Coop.getInstance().getContext());
        }
        String appVersFion = AppInfoUtils.getAppVersFion();
        int appCode = AppInfoUtils.getAppCode();
        String number = AppInfoUtils.getNumber();
        Object obj2 = "";
        Object obj3 = "";
        Location location = LocationUtil.getLocation();
        if (location != null) {
            obj2 = String.valueOf(location.getLatitude());
            obj3 = String.valueOf(location.getLongitude());
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HistoryOpenHelper.COLUMN_UID, obj);
            jSONObject.put("ov", AppInfoUtils.getDeviceVersion());
            jSONObject.put("ol", AppInfoUtils.getLanguage());
            jSONObject.put("dn", "");
            jSONObject.put("dt", str2);
            jSONObject.put("jb", "0");
            jSONObject.put("imei", AppInfoUtils.getIMEII());
            jSONObject.put("imsi", AppInfoUtils.getIMSII());
            jSONObject.put("mac", AppInfoUtils.getMacI());
            jSONObject.put("latitude", obj2);
            jSONObject.put("longitude", obj3);
            LogUtils.d("AppUpdateEngine", "AppUpdateEngine---object=" + jSONObject.toString());
            encryptContent = SocketUtil.encryptContent(jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            encryptContent = null;
        }
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(a.k, appVersFion));
        arrayList.add(new BasicNameValuePair(KEYS.PLACEMENTS, encryptContent));
        arrayList.add(new BasicNameValuePair("gv", appVersFion));
        arrayList.add(new BasicNameValuePair("ac", String.valueOf(appCode)));
        arrayList.add(new BasicNameValuePair("channelID", number));
        LogUtils.d("AppUpdateEngine", "AppUpdateEngine---params=" + arrayList.toString());
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new b(this), UrlStrs.APP_UPDATE_URL + "?op=" + str, arrayList);
    }
}
