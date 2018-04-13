package com.microquation.linkedme.android.f;

public class a {
    String a = "";
    int b = -113;

    public a(String str, int i) {
        this.a = str + a(i);
    }

    private String a(int i) {
        switch (i) {
            case -1234:
                this.b = -114;
                return "LinkedMe服务接口访问失败:\t请确认您在AndroidManifest.xml已经正确配置了LinkedMe Key";
            case -1009:
                this.b = -113;
                return "LinkedMe服务接口访问失败:\t网络链接不稳定,请稍后重试";
            case -118:
                this.b = -118;
                return "非深度链接唤起应用";
            case -111:
                this.b = -111;
                return "请求LinkedMe服务器超时,请检查您的网络状况";
            case -110:
                this.b = -110;
                return "没有LinkedMe提供分享支持的App";
            case -109:
                this.b = -109;
                return "请您确保已经在Application中已经初始化了LinkedMe.getInstance(Context)";
            case -108:
                this.b = -108;
                return "LinkedMeApp需要工作在最低API14的SDK版本,如果您需要支持API14一下的版本,请使用LinkedMe.getInstance(Context)代替";
            case -107:
                this.b = -107;
                return "无法申请兑换奖励,请确认您有足够的积分";
            case -106:
                this.b = -106;
                return "这个LinkedMe的referral code已经没使用了";
            case -105:
                this.b = -105;
                return "无法使用这个alias创建url.如果您想从用alias,请确保您提交的配置中包含相同参数和用户信息";
            case -104:
                this.b = -104;
                return "LinkedMe服务接口访问失败:\t请您在首次调用initSession之前一定调用LinkedMe的初始化过程";
            case -102:
                this.b = -102;
                return "请您确保已经在AndroidManifest.xml已经声明了Internet权限";
            case -101:
                this.b = -101;
                return "LinkedMe服务接口访问失败:\t请您在调用其它LinkedMe服务前,确保已经初始化session";
            default:
                if (i >= 500) {
                    this.b = -112;
                    return "无法访问LinkedMe服务器暂不可用,请稍后重试";
                } else if (i == 409) {
                    this.b = -115;
                    return "当前身份的资源存在冲突";
                } else if (i > 400) {
                    this.b = -116;
                    return "请求无效";
                } else {
                    this.b = -113;
                    return "请检查网络状况并确保您进行了正确的初始化工作";
                }
        }
    }

    public String a() {
        return this.a;
    }

    public String toString() {
        return a();
    }
}
