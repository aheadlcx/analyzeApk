package com.alibaba.baichuan.android.jsbridge;

import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.fastjson.JSONObject;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class AlibcJsResult {
    public static final String CLOSED = "7";
    public static final String FAIL = "6";
    public static final String NO_METHOD = "1";
    public static final String NO_PERMISSION = "4";
    public static final String PARAM_ERR = "2";
    public static final AlibcJsResult RET_CLOSED = new AlibcJsResult(CLOSED);
    public static final AlibcJsResult RET_FAIL = new AlibcJsResult("6");
    public static final AlibcJsResult RET_NO_METHOD = new AlibcJsResult("1");
    public static final AlibcJsResult RET_NO_PERMISSION = new AlibcJsResult("4");
    public static final AlibcJsResult RET_PARAM_ERR = new AlibcJsResult("2");
    public static final AlibcJsResult RET_SUCCESS = new AlibcJsResult("0");
    public static final String SUCCESS = "0";
    public static final String TIMEOUT = "5";
    public static final String UNKNOWN_ERR = "3";
    private JSONObject a = new JSONObject();
    private String b = "6";
    private String c = "";

    public AlibcJsResult(String str) {
        setResultMsg(str);
    }

    public void addData(String str, Object obj) {
        this.a.put(str, obj);
    }

    public String getErrorMessage(String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    obj = null;
                    break;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    obj = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    obj = 2;
                    break;
                }
                break;
            case 51:
                if (str.equals("3")) {
                    obj = 3;
                    break;
                }
                break;
            case 52:
                if (str.equals("4")) {
                    obj = 4;
                    break;
                }
                break;
            case 53:
                if (str.equals("5")) {
                    obj = 5;
                    break;
                }
                break;
            case 54:
                if (str.equals("6")) {
                    obj = 6;
                    break;
                }
                break;
            case 55:
                if (str.equals(CLOSED)) {
                    obj = 7;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return "BC_SUCCESS";
            case 1:
                return "BC_NO_HANDLER";
            case 2:
                return "BC_PARAM_ERR";
            case 3:
                return "BC_UNKNOWN_ERR";
            case 4:
                return "BC_NO_PERMISSION";
            case 5:
                return "BC_TIMEOUT";
            case 6:
                return "BC_FAILED";
            case 7:
                return "BC_CLOSED";
            default:
                return "BC_UNKNOWN";
        }
    }

    public void setResultCode(String str) {
        this.b = str;
        this.c = getErrorMessage(str);
    }

    public void setResultMsg(String str) {
        this.c = str;
    }

    public void setSuccess() {
        this.b = "0";
        this.c = getErrorMessage("0");
    }

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, this.b);
            jSONObject.put("msg", this.c);
            jSONObject.put("data", this.a);
        } catch (Exception e) {
            AlibcLogger.e("alibc", e.getMessage());
        }
        return jSONObject.toString();
    }
}
