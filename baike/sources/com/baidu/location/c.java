package com.baidu.location;

import com.baidu.location.a.b;

class c extends Thread {
    final /* synthetic */ LocationClient a;

    c(LocationClient locationClient) {
        this.a = locationClient;
    }

    public void run() {
        if (this.a.mloc == null) {
            this.a.mloc = new b(this.a.mContext, this.a.mOption, this.a);
        }
        this.a.mloc.c();
    }
}
