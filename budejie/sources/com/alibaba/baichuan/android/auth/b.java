package com.alibaba.baichuan.android.auth;

import com.tencent.connect.common.Constants;
import java.util.HashMap;

final class b extends HashMap {
    b() {
        put("FAIL_BIZ_PARAM_ERROR", "03");
        put("FAIL_BIZ_SYSTEM_ERROR", "04");
        put("token过期", "05");
        put("FAIL_BIZ_TOKEN_NOAUTH", "06");
        put("FAIL_BIZ_APPKEY_IN_BLACKLIST", "07");
        put("FAIL_BIZ_TOKEN_CANNOT_DECODE", "08");
        put("FAIL_BIZ_APPKEY_ILLEGAL", "09");
        put("FAIL_BIZ_UID_ILLEGAL", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        put("FAIL_BIZ_DEVICEID_ILLEGAL", "11");
        put("FAIL_BIZ_HINT_ILLEGAL", "12");
        put("FAIL_BIZ_HINT_AND_TOKEN_ILLEGAL", Constants.VIA_REPORT_TYPE_JOININ_GROUP);
        put("FAIL_BIZ_TOKEN_ILLEGAL", Constants.VIA_REPORT_TYPE_MAKE_FRIEND);
        put("FAIL_BIZ_API_ILLEGAL", Constants.VIA_REPORT_TYPE_WPA_STATE);
    }
}
