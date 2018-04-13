package com.baidu.mobads.e;

import android.text.TextUtils;
import com.baidu.mobads.interfaces.error.IXAdErrorCode;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.Map;

public class a implements IXAdErrorCode {
    protected final IXAdLogger a;

    public a(IXAdLogger iXAdLogger) {
        this.a = iXAdLogger;
    }

    public void printErrorMessage(XAdErrorCode xAdErrorCode, String str) {
        this.a.e(genCompleteErrorMessage(xAdErrorCode, str));
    }

    public void printErrorMessage(String str, String str2, String str3) {
        this.a.e(a(str, str2, str3));
    }

    public String genCompleteErrorMessage(XAdErrorCode xAdErrorCode, String str) {
        if (xAdErrorCode == null) {
            return "";
        }
        return a(xAdErrorCode.getCode() + "", xAdErrorCode.getMessage(), str);
    }

    public String a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append("ErrorCode: [");
            stringBuilder.append(str);
            stringBuilder.append("];");
        }
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append("ErrorDesc: [");
            stringBuilder.append(str2);
            stringBuilder.append("];");
        }
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append(" Extra: [");
            stringBuilder.append(str3);
            stringBuilder.append("];");
        }
        return stringBuilder.toString();
    }

    public String getMessage(Map<String, Object> map) {
        if (map != null) {
            try {
                if (map.containsKey("msg")) {
                    return XAdSDKFoundationFacade.getInstance().getErrorCode().genCompleteErrorMessage((XAdErrorCode) map.get("msg"), "");
                } else if (map.containsKey("error_message")) {
                    return (String) map.get("error_message");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
