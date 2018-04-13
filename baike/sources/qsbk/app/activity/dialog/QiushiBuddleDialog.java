package qsbk.app.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import qsbk.app.R;

public class QiushiBuddleDialog extends Dialog {
    public QiushiBuddleDialog(Context context, int i) {
        super(context, i);
    }

    public QiushiBuddleDialog(Context context) {
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.qiushi_buddle);
    }
}
