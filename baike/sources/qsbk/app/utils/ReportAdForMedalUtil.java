package qsbk.app.utils;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleHttpTask;

public class ReportAdForMedalUtil {

    public enum AD_PROVIDER {
        GDT("gdtad"),
        BAIDU("bdad"),
        QIUBAI("qbad"),
        AGEAD("agead"),
        QH("qh360");
        
        private String a;

        private AD_PROVIDER(String str) {
            this.a = str;
        }

        public String getValue() {
            return this.a;
        }
    }

    public enum AD_TYPE {
        QIUSHILIST(1),
        KAIPING(2),
        QIUYOUQUANLIST(3),
        QIUWEN(4),
        WEBACT(5),
        DOWNLOAD(6);
        
        private int a;

        private AD_TYPE(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    private ReportAdForMedalUtil() {
    }

    public static void report(AD_PROVIDER ad_provider, AD_TYPE ad_type) {
        if (QsbkApp.currentUser != null) {
            Map hashMap = new HashMap();
            hashMap.put("merchant", ad_provider.getValue());
            hashMap.put("ad_type", Integer.valueOf(ad_type.getValue()));
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.AD_CLICK_REPORT, new au());
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
        }
    }
}
