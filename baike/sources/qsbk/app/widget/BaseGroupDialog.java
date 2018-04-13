package qsbk.app.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import qsbk.app.R;

public abstract class BaseGroupDialog extends Dialog {
    public BaseGroupDialog(Context context) {
        super(context);
        Window window = getWindow();
        window.requestFeature(1);
        window.clearFlags(65792);
        window.setSoftInputMode(32);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.group_dialog_bg));
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }
}
