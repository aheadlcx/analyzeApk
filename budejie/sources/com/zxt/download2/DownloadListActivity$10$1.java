package com.zxt.download2;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import com.budejie.www.R;
import com.zxt.download2.DownloadListActivity.AnonymousClass10;

class DownloadListActivity$10$1 implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ AnonymousClass10 b;

    DownloadListActivity$10$1(AnonymousClass10 anonymousClass10, int i) {
        this.b = anonymousClass10;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            Toast.makeText(DownloadListActivity.a(this.b.a), R.string.download_deleted_task_ok, 1).show();
            if (this.a >= 0 && this.a < this.b.a.a.size()) {
                g.a(DownloadListActivity.a(this.b.a)).e((f) this.b.a.a.get(this.a));
                this.b.a.a.remove(this.a);
                this.b.a.c.notifyDataSetChanged();
            }
        }
    }
}
