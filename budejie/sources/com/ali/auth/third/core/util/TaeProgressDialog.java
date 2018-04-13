package com.ali.auth.third.core.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TaeProgressDialog extends ProgressDialog {
    private boolean indeterminate;
    private CharSequence message;
    private ProgressBar progressBar;
    private boolean progressVisiable;
    private TextView textView;

    public TaeProgressDialog(Context context) {
        super(context);
    }

    public TaeProgressDialog(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(ResourceUtils.getIdentifier("layout", "com_taobao_tae_sdk_progress_dialog"));
        getWindow().setBackgroundDrawableResource(17170445);
        this.progressBar = (ProgressBar) findViewById(16908301);
        this.textView = (TextView) findViewById(ResourceUtils.getIdentifier("id", "com_taobao_tae_sdk_progress_dialog_message"));
        setMessageAndView();
        setIndeterminate(this.indeterminate);
    }

    private void setMessageAndView() {
        int i = 8;
        this.textView.setText(this.message);
        if (this.message == null || "".equals(this.message)) {
            this.textView.setVisibility(8);
        }
        ProgressBar progressBar = this.progressBar;
        if (this.progressVisiable) {
            i = 0;
        }
        progressBar.setVisibility(i);
    }

    public void setMessage(CharSequence charSequence) {
        this.message = charSequence;
    }

    public void setProgressVisiable(boolean z) {
        this.progressVisiable = z;
    }

    public void setIndeterminate(boolean z) {
        if (this.progressBar != null) {
            this.progressBar.setIndeterminate(z);
        } else {
            this.indeterminate = z;
        }
    }
}
