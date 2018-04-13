package cn.xiaochuan.push.huawei;

import com.huawei.hms.support.api.entity.core.CommonCode.ErrorCode;

final class b {
    static String a(int i) {
        switch (i) {
            case 0:
                return "成功";
            case ErrorCode.ARGUMENTS_INVALID /*907135000*/:
                return "传入的参数错误 ";
            case ErrorCode.INTERNAL_ERROR /*907135001*/:
                return "内部错误，表示内部出现异常且无法恢复";
            case ErrorCode.NAMING_INVALID /*907135002*/:
                return "服务不存在，调用的接口不存在";
            case ErrorCode.CLIENT_API_INVALID /*907135003*/:
                return "ApiClient对象无效";
            case ErrorCode.EXECUTE_TIMEOUT /*907135004*/:
                return "调用AIDL超时";
            case ErrorCode.NOT_IN_SERVICE /*907135005*/:
                return "当前区域不支持此业务";
            case ErrorCode.SESSION_INVALID /*907135006*/:
                return "AIDL连接session无效";
            default:
                return String.valueOf(i);
        }
    }
}
