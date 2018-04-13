package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.Map;
import qsbk.app.Constants;

class yz implements OnClickListener {
    final /* synthetic */ Map a;
    final /* synthetic */ yy b;

    yz(yy yyVar, Map map) {
        this.b = yyVar;
        this.a = map;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        OtherInfoActivity.a(this.b.a, Constants.UNSUBSCRIBE_TA, Constants.UNSUBSCRIBE_TA, this.a);
        dialogInterface.cancel();
    }
}
