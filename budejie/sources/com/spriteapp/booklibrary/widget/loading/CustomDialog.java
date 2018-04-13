package com.spriteapp.booklibrary.widget.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import com.spriteapp.booklibrary.a$g;
import com.spriteapp.booklibrary.util.ScreenUtil;

public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        this(context, 0);
    }

    public CustomDialog(Context context, int i) {
        super(context, i);
    }

    public static CustomDialog instance(Activity activity) {
        CustomDialog customDialog = new CustomDialog(activity, a$g.loading_dialog);
        customDialog.setContentView(new ProgressBar(activity, null, 16842871), new LayoutParams(ScreenUtil.dpToPxInt(40.0f), ScreenUtil.dpToPxInt(40.0f)));
        return customDialog;
    }
}
