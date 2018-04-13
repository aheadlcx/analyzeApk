package qsbk.app.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class BlackProgressDialog extends AlertDialog {
    public BlackProgressDialog(Context context) {
        super(context);
    }

    public BlackProgressDialog(Context context, int i) {
        super(context, i);
    }

    public BlackProgressDialog(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setLayout(UIHelper.dip2px(getContext(), 180.0f), UIHelper.dip2px(getContext(), 100.0f));
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        FrameLayout frameLayout = (FrameLayout) findViewById(16908331);
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_progress, null));
    }
}
