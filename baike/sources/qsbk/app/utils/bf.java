package qsbk.app.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.AppManager;

final class bf implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;

    bf(String str, Context context) {
        this.a = str;
        this.b = context;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"qiushibaike@gmail.com"});
        intent.putExtra("android.intent.extra.SUBJECT", "糗事百科Android客户端 - 错误报告");
        intent.putExtra("android.intent.extra.TEXT", this.a);
        this.b.startActivity(Intent.createChooser(intent, "发送错误报告"));
        AppManager.getAppManager().AppExit(this.b);
    }
}
