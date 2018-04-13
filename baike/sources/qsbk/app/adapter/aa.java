package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.utils.ToastAndDialog;

class aa implements OnClickListener {
    final /* synthetic */ y a;

    aa(y yVar) {
        this.a = yVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.c.a(this.a.b, Config.DEVICE_NAME, this.a.a.id, "active");
                break;
            case 1:
                this.a.c.b(this.a.a);
                break;
        }
        ToastAndDialog.makeText(this.a.c.k, "收到你的反馈").show();
        this.a.c.m.remove(this.a.a);
        this.a.c.notifyDataSetChanged();
    }
}
