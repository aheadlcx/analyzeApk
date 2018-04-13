package com.alibaba.baichuan.android.trade.utils;

import android.app.AlertDialog.Builder;

final class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    b(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public void run() {
        Builder builder = new Builder(a.a());
        builder.setMessage("class = " + this.a + "\n" + "method = " + this.b + "\n" + "errMsg = " + this.c);
        builder.setTitle("执行过程参数发生错误");
        builder.show();
    }
}
