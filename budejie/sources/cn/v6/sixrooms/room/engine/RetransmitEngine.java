package cn.v6.sixrooms.room.engine;

import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.bean.WeiBoDetailsBean;
import cn.v6.sixrooms.room.bean.WeiBoForwardPicBean;
import cn.v6.sixrooms.room.bean.WeiBoListMsgBean;
import cn.v6.sixrooms.room.bean.WeiBoMp3Bean;
import cn.v6.sixrooms.room.bean.WeiBoPicBean;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.tencent.connect.common.Constants;
import com.tencent.stat.DeviceInfo;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class RetransmitEngine {
    protected static final String TAG = "RetransmitEngine";
    private CallBack a;

    public interface CallBack {
        void error(int i);

        void handleErrorInfo(String str, String str2);

        void result(WeiBoListMsgBean weiBoListMsgBean);
    }

    public RetransmitEngine(CallBack callBack) {
        this.a = callBack;
    }

    public void retransmitBlog(String str, String str2, String str3, String str4, String str5, String str6) {
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("msg", str2);
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair(DeviceInfo.TAG_MID, str);
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("islast", str3);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("isroot", str4);
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("encpass", str6);
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("logiuid", str5);
        BasicNameValuePair basicNameValuePair7 = new BasicNameValuePair(a.k, "1.3");
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        arrayList.add(basicNameValuePair3);
        arrayList.add(basicNameValuePair4);
        arrayList.add(basicNameValuePair5);
        arrayList.add(basicNameValuePair6);
        arrayList.add(basicNameValuePair7);
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new j(this), UrlStrs.URL_INDEX_INFO + "?padapi=message-message_forward.php", arrayList);
    }

    private static WeiBoDetailsBean a(JSONObject jSONObject, String str) throws JSONException, UnsupportedEncodingException {
        WeiBoDetailsBean weiBoDetailsBean = new WeiBoDetailsBean();
        String optString;
        String optString2;
        String optString3;
        String optString4;
        JSONObject optJSONObject;
        String optString5;
        if ("1".equals(str)) {
            optString = jSONObject.optString("msg");
            WeiBoPicBean weiBoPicBean = new WeiBoPicBean();
            JSONObject optJSONObject2 = jSONObject.optJSONObject("pic");
            if (optJSONObject2 != null) {
                optString2 = optJSONObject2.optString("url");
                optString3 = optJSONObject2.optString("pid");
                optString4 = optJSONObject2.optString("link");
                weiBoPicBean.setUrl(optString2);
                weiBoPicBean.setPid(optString3);
                weiBoPicBean.setLink(optString4);
            }
            WeiBoMp3Bean weiBoMp3Bean = new WeiBoMp3Bean();
            optJSONObject = jSONObject.optJSONObject("mp3");
            if (optJSONObject != null) {
                optString3 = optJSONObject.optString("audname");
                optString5 = optJSONObject.optString(DeviceInfo.TAG_ANDROID_ID);
                optString2 = optJSONObject.optString("link");
                weiBoMp3Bean.setAid(optString5);
                weiBoMp3Bean.setAudname(optString3);
                weiBoMp3Bean.setLink(optString2);
            }
            weiBoDetailsBean.setMsg(optString);
            weiBoDetailsBean.setPic(weiBoPicBean);
            weiBoDetailsBean.setMp3(weiBoMp3Bean);
        } else if ("2".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("url");
            optString2 = jSONObject.optString("pid");
            optString3 = jSONObject.optString("link");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setUrl(optString4);
            weiBoDetailsBean.setPid(optString2);
            weiBoDetailsBean.setLink(optString3);
        } else if ("3".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("audname");
            optString2 = jSONObject.optString(DeviceInfo.TAG_ANDROID_ID);
            optString3 = jSONObject.optString("link");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setAudname(optString4);
            weiBoDetailsBean.setAid(optString2);
            weiBoDetailsBean.setLink(optString3);
        } else if ("4".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("url");
            optString2 = jSONObject.optString("link");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setUrl(optString4);
            weiBoDetailsBean.setLink(optString2);
        } else if ("5".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
        } else if ("6".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("tuid");
            optString2 = jSONObject.optString("talias");
            optString3 = jSONObject.optString("gift");
            optString5 = jSONObject.optString("num");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setTuid(optString4);
            weiBoDetailsBean.setTalias(optString2);
            weiBoDetailsBean.setGift(optString3);
            weiBoDetailsBean.setNum(optString5);
        } else if (AlibcJsResult.CLOSED.equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("fuid");
            optString2 = jSONObject.optString("falias");
            optString3 = jSONObject.optString("gift");
            optString5 = jSONObject.optString("num");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setFuid(optString4);
            weiBoDetailsBean.setFalias(optString2);
            weiBoDetailsBean.setGift(optString3);
            weiBoDetailsBean.setNum(optString5);
        } else if ("8".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("fuid");
            optString2 = jSONObject.optString("falias");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setFuid(optString4);
            weiBoDetailsBean.setFalias(optString2);
        } else if ("9".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("num");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setNum(optString4);
        } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("num");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setNum(optString4);
        } else if ("11".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("num");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setNum(optString4);
        } else if ("12".equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("num");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setNum(optString4);
        } else if (Constants.VIA_REPORT_TYPE_JOININ_GROUP.equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("fname");
            optString2 = jSONObject.optString("fuid");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setFname(optString4);
            weiBoDetailsBean.setFuid(optString2);
        } else if (Constants.VIA_REPORT_TYPE_MAKE_FRIEND.equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("fname");
            optString2 = jSONObject.optString("fuid");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setFname(optString4);
            weiBoDetailsBean.setFuid(optString2);
        } else if (Constants.VIA_REPORT_TYPE_WPA_STATE.equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("tuid");
            optString2 = jSONObject.optString("talias");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setTuid(optString4);
            weiBoDetailsBean.setTalias(optString2);
        } else if (Constants.VIA_REPORT_TYPE_START_WAP.equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("tuid");
            optString2 = jSONObject.optString("talias");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setTuid(optString4);
            weiBoDetailsBean.setTalias(optString2);
        } else if (Constants.VIA_REPORT_TYPE_START_GROUP.equals(str)) {
            optString = jSONObject.optString(HistoryOpenHelper.COLUMN_UID);
            r2 = jSONObject.optString("ualias");
            optString4 = jSONObject.optString("game");
            optString2 = jSONObject.optString("num");
            optString3 = jSONObject.optString("gift");
            weiBoDetailsBean.setUid(optString);
            weiBoDetailsBean.setUalias(r2);
            weiBoDetailsBean.setGame(optString4);
            weiBoDetailsBean.setNum(optString2);
            weiBoDetailsBean.setGift(optString3);
        } else if ("18".equals(str)) {
            optString = jSONObject.optString("msg");
            r2 = jSONObject.optString(DeviceInfo.TAG_MID);
            optString4 = jSONObject.optString("rootid");
            optJSONObject = jSONObject.optJSONObject("pic");
            WeiBoForwardPicBean weiBoForwardPicBean = new WeiBoForwardPicBean();
            if (optJSONObject != null) {
                optString5 = optJSONObject.optString("url");
                String optString6 = optJSONObject.optString("smpicw");
                optString2 = optJSONObject.optString("smpich");
                weiBoForwardPicBean.setUrl(optString5);
                weiBoForwardPicBean.setSmpich(optString2);
                weiBoForwardPicBean.setSmpicw(optString6);
            }
            weiBoDetailsBean.setMsg(optString);
            weiBoDetailsBean.setMid(r2);
            weiBoDetailsBean.setRootid(optString4);
            weiBoDetailsBean.setForwardPic(weiBoForwardPicBean);
        }
        return weiBoDetailsBean;
    }

    static /* synthetic */ WeiBoListMsgBean a(JSONObject jSONObject) throws JSONException, UnsupportedEncodingException {
        WeiBoListMsgBean weiBoListMsgBean = new WeiBoListMsgBean();
        String string = jSONObject.getString("type");
        weiBoListMsgBean.setType(string);
        weiBoListMsgBean.setId(jSONObject.getString("id"));
        weiBoListMsgBean.setCommnum(jSONObject.optString("commnum"));
        weiBoListMsgBean.setForwardnum(jSONObject.optString("forwardnum"));
        weiBoListMsgBean.setTm(jSONObject.optString(IXAdRequestInfo.MAX_TITLE_LENGTH));
        weiBoListMsgBean.setUid(jSONObject.optString(HistoryOpenHelper.COLUMN_UID));
        weiBoListMsgBean.setRid(jSONObject.optString("rid"));
        weiBoListMsgBean.setAlias(jSONObject.optString("alias"));
        weiBoListMsgBean.setUserpic(jSONObject.optString("userpic"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("content");
        if ("18".equals(string)) {
            JSONObject optJSONObject = jSONObject.optJSONObject("forward");
            if (optJSONObject != null) {
                WeiBoListMsgBean weiBoListMsgBean2 = new WeiBoListMsgBean();
                String optString = optJSONObject.optString("type");
                String optString2 = optJSONObject.optString("id");
                String optString3 = optJSONObject.optString("commnum");
                String optString4 = optJSONObject.optString("forwardnum");
                String optString5 = optJSONObject.optString(IXAdRequestInfo.MAX_TITLE_LENGTH);
                String optString6 = optJSONObject.optString(HistoryOpenHelper.COLUMN_UID);
                String optString7 = optJSONObject.optString("rid");
                String optString8 = optJSONObject.optString("alias");
                String optString9 = optJSONObject.optString("userpic");
                WeiBoDetailsBean a = a(optJSONObject.getJSONObject("content"), optString);
                weiBoListMsgBean2.setType(optString);
                weiBoListMsgBean2.setId(optString2);
                weiBoListMsgBean2.setCommnum(optString3);
                weiBoListMsgBean2.setForwardnum(optString4);
                weiBoListMsgBean2.setTm(optString5);
                weiBoListMsgBean2.setUid(optString6);
                weiBoListMsgBean2.setRid(optString7);
                weiBoListMsgBean2.setAlias(optString8);
                weiBoListMsgBean2.setUserpic(optString9);
                weiBoListMsgBean2.setMsgBean(a);
                weiBoListMsgBean.setForWardBean(weiBoListMsgBean2);
            }
        }
        weiBoListMsgBean.setMsgBean(a(jSONObject2, string));
        return weiBoListMsgBean;
    }
}
