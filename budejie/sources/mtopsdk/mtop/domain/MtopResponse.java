package mtopsdk.mtop.domain;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.qq.e.comm.constants.Constants.KEYS;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.util.a;
import mtopsdk.mtop.util.h;
import org.json.JSONArray;
import org.json.JSONObject;

public class MtopResponse implements Serializable, b {
    private static final String SHARP = "::";
    private static final String TAG = "mtopsdk.MtopResponse";
    private static final long serialVersionUID = 1566423746968673499L;
    private String api;
    private volatile boolean bParsed = false;
    private byte[] bytedata;
    private byte[] data;
    private JSONObject dataJsonObject;
    private Map headerFields;
    private h mtopStat;
    private int responseCode;
    private ResponseSource responseSource = ResponseSource.NETWORK_REQUEST;
    private String[] ret;
    private String retCode;
    private String retMsg;
    private String v;

    public enum ResponseSource {
        FRESH_CACHE,
        EXPIRED_CACHE,
        NETWORK_REQUEST
    }

    public MtopResponse(String str, String str2) {
        this.retCode = str;
        this.retMsg = str2;
    }

    public MtopResponse(String str, String str2, String str3, String str4) {
        this.api = str;
        this.v = str2;
        this.retCode = str3;
        this.retMsg = str4;
    }

    private void parserRet(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            String str = strArr[0];
            if (l.a(str)) {
                String[] split = str.split(SHARP);
                if (split != null && split.length > 1) {
                    this.retCode = split[0];
                    this.retMsg = split[1];
                }
            }
        }
    }

    public String getApi() {
        if (this.api == null && !this.bParsed) {
            parseJsonByte();
        }
        return this.api;
    }

    public byte[] getBytedata() {
        return this.bytedata;
    }

    public byte[] getData() {
        return this.data;
    }

    public JSONObject getDataJsonObject() {
        if (this.dataJsonObject == null && !this.bParsed) {
            parseJsonByte();
        }
        return this.dataJsonObject;
    }

    public String getFullKey() {
        return (l.b(this.api) || l.b(this.v)) ? null : l.a(this.api, this.v);
    }

    public Map getHeaderFields() {
        return this.headerFields;
    }

    public h getMtopStat() {
        return this.mtopStat;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String[] getRet() {
        if (this.ret == null && !this.bParsed) {
            parseJsonByte();
        }
        return this.ret;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public String getRetMsg() {
        if (this.retMsg == null && !this.bParsed) {
            parseJsonByte();
        }
        return this.retMsg;
    }

    public ResponseSource getSource() {
        return this.responseSource;
    }

    public String getV() {
        if (this.v == null && !this.bParsed) {
            parseJsonByte();
        }
        return this.v;
    }

    public boolean is41XResult() {
        return a.j(getRetCode());
    }

    public boolean isApiLockedResult() {
        return a.k(getRetCode());
    }

    public boolean isApiSuccess() {
        return a.i(getRetCode()) && getBytedata() != null;
    }

    public boolean isExpiredRequest() {
        return a.l(getRetCode());
    }

    public boolean isIllegelSign() {
        return a.h(getRetCode());
    }

    public boolean isMtopSdkError() {
        return a.d(getRetCode());
    }

    public boolean isMtopServerError() {
        return a.m(getRetCode());
    }

    public boolean isNetworkError() {
        return a.e(getRetCode());
    }

    public boolean isNoNetwork() {
        return a.f(getRetCode());
    }

    public boolean isSessionInvalid() {
        return a.g(getRetCode());
    }

    public boolean isSystemError() {
        return a.c(getRetCode());
    }

    public void parseJsonByte() {
        if (!this.bParsed) {
            synchronized (this) {
                if (this.bParsed) {
                } else if (this.bytedata == null || this.bytedata.length == 0) {
                    if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                        m.c(TAG, "[parseJsonByte]bytedata is blank ---api=" + this.api + ",v=" + this.v);
                    }
                    this.retCode = "ANDROID_SYS_JSONDATA_BLANK";
                    this.retMsg = "返回JSONDATA为空";
                    this.bParsed = true;
                } else {
                    try {
                        String str = new String(this.bytedata);
                        if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                            m.a(TAG, "[parseJsonByte]response : " + str);
                        }
                        JSONObject jSONObject = new JSONObject(str);
                        if (this.api == null) {
                            this.api = jSONObject.getString("api");
                        }
                        if (this.v == null) {
                            this.v = jSONObject.getString(IXAdRequestInfo.V);
                        }
                        JSONArray jSONArray = jSONObject.getJSONArray(KEYS.RET);
                        int length = jSONArray.length();
                        this.ret = new String[length];
                        for (int i = 0; i < length; i++) {
                            this.ret[i] = jSONArray.getString(i);
                        }
                        parserRet(this.ret);
                        this.dataJsonObject = jSONObject.optJSONObject("data");
                        this.bParsed = true;
                    } catch (Throwable th) {
                        this.bParsed = true;
                    }
                }
            }
        }
    }

    public void setApi(String str) {
        this.api = str;
    }

    public void setBytedata(byte[] bArr) {
        this.bytedata = bArr;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setDataJsonObject(JSONObject jSONObject) {
        this.dataJsonObject = jSONObject;
    }

    public void setHeaderFields(Map map) {
        this.headerFields = map;
    }

    public void setMtopStat(h hVar) {
        this.mtopStat = hVar;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public void setRet(String[] strArr) {
        this.ret = strArr;
    }

    public void setRetCode(String str) {
        this.retCode = str;
    }

    public void setRetMsg(String str) {
        this.retMsg = str;
    }

    public void setSource(ResponseSource responseSource) {
        this.responseSource = responseSource;
    }

    public void setV(String str) {
        this.v = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MtopResponse");
        try {
            stringBuilder.append("[api=").append(this.api);
            stringBuilder.append(",v=").append(this.v);
            stringBuilder.append(",responseCode=").append(this.responseCode);
            stringBuilder.append(",headerFields=").append(this.headerFields);
            stringBuilder.append(",retCode=").append(this.retCode);
            stringBuilder.append(",retMsg=").append(this.retMsg);
            stringBuilder.append(",ret=").append(Arrays.toString(this.ret));
            stringBuilder.append(",data=").append(this.dataJsonObject);
            stringBuilder.append(",bytedata=").append(this.bytedata == null ? null : new String(this.bytedata));
            stringBuilder.append("]");
            return stringBuilder.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return super.toString();
        }
    }
}
