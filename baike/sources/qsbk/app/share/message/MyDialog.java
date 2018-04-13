package qsbk.app.share.message;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;

@SuppressLint({"HandlerLeak"})
public class MyDialog extends AlertDialog {
    private int a = 1;
    private boolean b = true;
    private Handler c = new c(this);
    private Thread d = new d(this);

    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, String str) {
        super(context);
        setTitle(str);
    }

    public MyDialog(Context context, String str, String str2) {
        super(context);
        setTitle(str);
        setMessage(str2);
    }

    public void show() {
        super.show();
        this.d.start();
    }

    public void dismiss() {
        super.dismiss();
        this.b = false;
    }
}
