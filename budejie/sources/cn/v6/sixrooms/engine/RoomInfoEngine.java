package cn.v6.sixrooms.engine;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.bean.LiveinfoBean;
import cn.v6.sixrooms.bean.RoomParamInfoBean;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.utils.CheckRoomTypeUtils;
import cn.v6.sixrooms.utils.DateUtil;
import cn.v6.sixrooms.utils.Html2Text;
import cn.v6.sixrooms.utils.JsonParseUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class RoomInfoEngine {
    protected static final String TAG = "RoomInfoEngine";
    private CallBack a;
    private RoominfoBean b;
    private int c = 0;

    public interface CallBack {
        void error(int i);

        void getMicroIP_PORT(String str, String str2);

        void getPriv(String str);

        void handleErrorInfo(String str, String str2);

        void resultInfo(WrapRoomInfo wrapRoomInfo);

        void rtmpURL(String str);
    }

    public RoomInfoEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void getRoomInfo(String str, String str2, String str3, String str4) {
        a(str, str2, str3, str4, "");
    }

    public void getRoomInfoByUid(String str, String str2, String str3, String str4) {
        a(str, str2, str3, "", str4);
    }

    private void a(String str, String str2, String str3, String str4, String str5) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("playeruid", str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(a.k, "2.4");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("logiuid", str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(new BasicNameValuePair("encpass", str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            arrayList.add(new BasicNameValuePair("rid", str4));
        }
        if (!TextUtils.isEmpty(str5)) {
            arrayList.add(new BasicNameValuePair(RoomBaseFragment.RUID_KEY, str5));
        }
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new at(this, str, str2, str3, str4, str5), UrlStrs.URL_INDEX_INFO + "?padapi=coop-mobile-inroom.php", arrayList);
    }

    private static void a(Map<String, SofaBean> map, JSONObject jSONObject, String str) throws JSONException {
        Object optString = jSONObject.optString(str);
        if (!TextUtils.isEmpty(optString)) {
            map.put(str, JsonParseUtils.json2Obj(optString, SofaBean.class));
        }
    }

    public void getRTMPAddress(String str) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("s", str));
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new bb(this), UrlUtils.getUrl(UrlStrs.URL_RTMP_ADDRESS, arrayList), "");
    }

    static /* synthetic */ void a(RoomInfoEngine roomInfoEngine, JSONObject jSONObject, WrapRoomInfo wrapRoomInfo) throws JSONException {
        JSONObject jSONObject2;
        RoomParamInfoBean roomParamInfoBean = new RoomParamInfoBean();
        if (!jSONObject.isNull("fans_num")) {
            roomParamInfoBean.setFans_num(String.valueOf(jSONObject.getInt("fans_num")));
        }
        Map hashMap = new HashMap();
        String string = jSONObject.getString("sofa");
        if (string.length() >= 3) {
            jSONObject2 = new JSONObject(string);
            a(hashMap, jSONObject2, "1");
            a(hashMap, jSONObject2, "2");
            a(hashMap, jSONObject2, "3");
            a(hashMap, jSONObject2, "4");
        }
        roomParamInfoBean.setSofa(hashMap);
        jSONObject2 = jSONObject.getJSONObject("operation");
        String str = "";
        if (jSONObject2.has("privnote")) {
            str = jSONObject2.getString("privnote");
        }
        string = Html2Text.Html2Text(str);
        RoommsgBean roommsgBean = new RoommsgBean();
        roommsgBean.setContent(string);
        if (CheckRoomTypeUtils.isFamilyRoomType(roomInfoEngine.b.getId())) {
            roommsgBean.setFrom(roomInfoEngine.b.getAlias() + "家族");
        } else {
            roommsgBean.setFrom(roomInfoEngine.b.getAlias());
        }
        roommsgBean.setFid(roomInfoEngine.b.getId());
        roommsgBean.setFrid(roomInfoEngine.b.getRid());
        roommsgBean.setfPic(roomInfoEngine.b.getUoption().getPicuser());
        roommsgBean.setTo("我");
        roommsgBean.setTm(DateUtil.getHourMinuteCurr());
        ArrayList arrayList = new ArrayList();
        arrayList.add(roommsgBean);
        wrapRoomInfo.setPrivateRoommsgBeans(arrayList);
        String string2 = jSONObject2.getString("pubchat");
        int i = 2;
        if (jSONObject2.has("setranking")) {
            i = jSONObject2.getInt("setranking");
        }
        roomParamInfoBean.setSetranking(i);
        Object string3 = jSONObject2.getString("backstyle");
        if (!TextUtils.isEmpty(string3)) {
            roomParamInfoBean.setBGURL(new JSONObject(string3).getString("bgimg"));
        }
        roomParamInfoBean.setPrivnote(str);
        roomParamInfoBean.setPubchat(string2);
        wrapRoomInfo.setRoomParamInfoBean(roomParamInfoBean);
    }

    static /* synthetic */ int a(String str) {
        if (str.contains("rich")) {
            return Integer.parseInt(str.substring(str.indexOf("rich") + 4, str.indexOf("'>")));
        }
        return 0;
    }

    static /* synthetic */ LiveinfoBean a(RoomInfoEngine roomInfoEngine, JSONObject jSONObject, String str) throws JSONException {
        LiveinfoBean liveinfoBean = (LiveinfoBean) JsonParseUtils.json2Obj(jSONObject.toString(), LiveinfoBean.class);
        Object obj = jSONObject.getJSONObject("content").get("1");
        String str2 = "";
        String str3 = "";
        if (obj instanceof JSONObject) {
            JSONObject jSONObject2 = (JSONObject) obj;
            if (jSONObject2.has("secflvtitle")) {
                str3 = jSONObject2.getString("secflvtitle");
            }
            if (jSONObject2.has("flvtitle")) {
                str2 = jSONObject2.getString("flvtitle");
            }
            liveinfoBean.setAllgetnum(jSONObject2.getJSONObject("red").getString("allgetnum"));
        }
        if (!jSONObject.has("flvtitle")) {
            roomInfoEngine.getRTMPAddress(str);
        } else if (TextUtils.isEmpty(jSONObject.getString("flvtitle"))) {
            VLScheduler.instance.schedule(0, 0, new ba(roomInfoEngine));
        } else {
            roomInfoEngine.getRTMPAddress(str);
        }
        liveinfoBean.setFlvtitle(str2);
        liveinfoBean.setSecflvtitle(str3);
        return liveinfoBean;
    }
}
