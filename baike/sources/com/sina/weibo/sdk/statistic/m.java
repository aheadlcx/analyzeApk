package com.sina.weibo.sdk.statistic;

class m implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ k b;

    m(k kVar, String str) {
        this.b = kVar;
        this.a = str;
    }

    public void run() {
        e.writeToFile(e.getAppLogPath(e.ANALYTICS_FILE_NAME), this.a, true);
    }
}
