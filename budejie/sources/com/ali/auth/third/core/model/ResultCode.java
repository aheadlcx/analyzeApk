package com.ali.auth.third.core.model;

import cn.v6.sixrooms.constants.CommonInts;
import com.ali.auth.third.core.message.Message;
import com.ali.auth.third.core.message.MessageUtils;
import com.ali.auth.third.mtop.rpc.ResultActionType;
import com.taobao.dp.http.ResCode;

public class ResultCode {
    @Deprecated
    public static final ResultCode BRIDGE_EXCEPTION = new ResultCode(10001, "BRIDGE_EXCEPTION");
    public static final ResultCode CHECK = new ResultCode(108, "CHECK");
    @Deprecated
    public static final ResultCode HTTP_REQUEST_EXCEPTION = new ResultCode(ResCode.NPE_WSG_DECRYTION);
    public static final ResultCode IGNORE = new ResultCode(-100, "IGNORE");
    @Deprecated
    public static final ResultCode ILLEGAL_PARAM = new ResultCode(10013, "ILLEGAL_PARAM");
    @Deprecated
    public static final ResultCode INIT_EXCEPTION = new ResultCode(CommonInts.GT_ERROR, "INIT_EXCEPTION");
    @Deprecated
    public static final ResultCode NETWORK_NOT_AVAILABLE = new ResultCode(10014, "NETWORK_NOT_AVAILABLE");
    @Deprecated
    public static final ResultCode NO_SUCH_METHOD = new ResultCode(10000, "NO_SUCH_METHOD");
    @Deprecated
    public static final ResultCode RSA_DECRYPT_EXCEPTION = new ResultCode(SystemMessageConstants.TAOBAO_ERROR_CODE);
    @Deprecated
    public static final ResultCode SECURITY_GUARD_INIT_EXCEPTION = new ResultCode(10016, "SECURITY_GUARD_INIT_EXCEPTION");
    public static final ResultCode SUCCESS = new ResultCode(100, ResultActionType.SUCCESS);
    @Deprecated
    public static final ResultCode SYSTEM_EXCEPTION = new ResultCode(KernelMessageConstants.GENERIC_SYSTEM_ERROR, "SYSTEM_EXCEPTION");
    @Deprecated
    public static final ResultCode USER_CANCEL = new ResultCode(SystemMessageConstants.USER_CANCEL_CODE, "USER_CANCEL");
    public int code;
    public String message;

    public ResultCode(int i) {
        this(i, null);
    }

    public ResultCode(int i, String str) {
        this.code = i;
        this.message = str;
    }

    public boolean isSuccess() {
        return this.code == 100;
    }

    public static ResultCode create(Message message) {
        return new ResultCode(message.code, message.message);
    }

    public static ResultCode create(int i, Object... objArr) {
        return new ResultCode(i, MessageUtils.getMessageContent(i, objArr));
    }

    public int hashCode() {
        return this.code + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.code != ((ResultCode) obj).code) {
            return false;
        }
        return true;
    }
}
