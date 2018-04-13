package cn.tatagou.sdk.util;

import cn.tatagou.sdk.android.TtgSDK;

public interface d {
    public static final String a;
    public static final String b;

    public enum a {
        a("mm_117613736_17620149_"),
        b("mm_117613736_15438020_"),
        c("mm_117613736_17624113_"),
        d("mm_117613736_17706117_"),
        e("mm_117385491_15328148_"),
        WIFI("mm_117385491_15328148_"),
        MOJI("mm_121968581_23962877_"),
        h("mm_119433043_17514616_"),
        i("mm_120597370_19540029_"),
        j("mm_119764741_19552581_"),
        MEIXIN_APP("mm_30146700_6158682_"),
        TA_APP("mm_30146700_9086423_"),
        m("mm_119764741_20470916_"),
        YUEHUI("mm_123267443_23692547_"),
        o("mm_120404448_20354375_"),
        FREEME("mm_121164795_21820456_"),
        BIZHI("mm_122115739_21814775_"),
        DTBIZHI("mm_122115739_21802907_"),
        LHL("mm_121913381_22102768_"),
        WONIU("mm_121330154_22676873_"),
        MEIRZ("mm_121164795_22960983_"),
        DOUGUO("mm_118623251_23082240_"),
        HAODOU("mm_122410543_23694608_");
        
        private String x;

        private a(String str) {
            this.x = str;
        }

        public String getValue() {
            return this.x;
        }
    }

    static {
        String str = TtgSDK.isDebug ? TtgSDK.isTest ? "https://testapi.tatagou.com.cn:20446/v2/" : "https://testapi.tatagou.com.cn:20446/v2/" : "https://api.tatagou.com.cn/v2/";
        a = str;
        str = TtgSDK.isDebug ? TtgSDK.isTest ? "https://testlog.tatagou.com.cn:20446" : "https://testlog.tatagou.com.cn:20446" : "https://log.tatagou.com.cn:30003";
        b = str;
    }
}
