package com.sina.weibo.sdk.utils;

class a implements Runnable {
    final /* synthetic */ AidTask a;

    a(AidTask aidTask) {
        this.a = aidTask;
    }

    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                this.a.a(i).delete();
            } catch (Exception e) {
            }
        }
    }
}
