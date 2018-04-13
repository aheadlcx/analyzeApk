package com.huawei.hms.update.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.a.a.a.a.b;
import java.text.NumberFormat;

public class h extends b {
    private ProgressBar a;
    private TextView b;
    private OnKeyListener c = new a();

    private static class a implements OnKeyListener {
        private a() {
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4 && keyEvent.getRepeatCount() == 0;
        }
    }

    public AlertDialog a() {
        Builder builder = new Builder(f(), g());
        View inflate = View.inflate(f(), b.hms_download_progress, null);
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setOnKeyListener(this.c);
        this.a = (ProgressBar) inflate.findViewById(com.huawei.a.a.a.a.a.download_info_progress);
        this.b = (TextView) inflate.findViewById(com.huawei.a.a.a.a.a.hms_progress_text);
        a(0, 0);
        return builder.create();
    }

    void a(int i, int i2) {
        Activity f = f();
        if (f == null || f.isFinishing()) {
            com.huawei.hms.support.log.a.c("DownloadProgress", "In setDownloading, The activity is null or finishing.");
        } else if (this.b != null && this.a != null) {
            int i3 = 0;
            if (i >= 0 && i2 > 0) {
                i3 = (int) ((((long) i) * 100) / ((long) i2));
            }
            this.a.setProgress(i3);
            this.b.setText(NumberFormat.getPercentInstance().format((double) (((float) i3) / 100.0f)));
        }
    }
}
