package mtopsdk.mtop.domain;

import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;

public enum MtopHeaderFieldEnum {
    ACT("x-act", "accessToken"),
    WUAT("x-wuat", "wua"),
    SID("x-sid", "sid"),
    TIME("x-t", "t"),
    APPKEY("x-appkey", "appKey"),
    TTID("x-ttid", AlibcConstants.TTID),
    UTDID("x-utdid", "utdid"),
    SIGN("x-sign", "sign"),
    NQ("x-nq", "nq"),
    NETTYPE("x-nettype", "netType"),
    PV("x-pv", "pv"),
    UID("x-uid", HistoryOpenHelper.COLUMN_UID),
    UMID("x-umt", "umt"),
    MTOP_FEATURE("x-features", "x-features"),
    X_APP_VER("x-app-ver", "x-app-ver"),
    USER_AGENT("user-agent", "user-agent");
    
    private String headField;
    private String xstateKey;

    private MtopHeaderFieldEnum(String str, String str2) {
        this.headField = str;
        this.xstateKey = str2;
    }

    public final String getHeadField() {
        return this.headField;
    }

    public final String getXstateKey() {
        return this.xstateKey;
    }
}
