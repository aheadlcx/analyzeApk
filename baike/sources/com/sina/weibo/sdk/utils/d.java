package com.sina.weibo.sdk.utils;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ AidTask4Plug b;

    d(AidTask4Plug aidTask4Plug, String str) {
        this.b = aidTask4Plug;
        this.a = str;
    }

    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                this.b.a(i, this.a).delete();
            } catch (Exception e) {
            }
        }
    }
}
