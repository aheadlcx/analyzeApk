package com.zxt.download2;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import com.budejie.www.R;
import com.zxt.download2.DownloadListActivity.AnonymousClass8;
import java.io.File;

class DownloadListActivity$8$1 implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ AnonymousClass8 b;

    DownloadListActivity$8$1(AnonymousClass8 anonymousClass8, int i) {
        this.b = anonymousClass8;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            Toast.makeText(DownloadListActivity.a(this.b.a), R.string.download_deleted_task_file_ok, 1).show();
            g.a(DownloadListActivity.a(this.b.a)).e((f) this.b.a.b.get(this.a));
            g.a(DownloadListActivity.a(this.b.a)).f((f) this.b.a.b.get(this.a));
            this.b.a.b.remove(this.a);
            this.b.a.d.notifyDataSetChanged();
        } else if (i == 1) {
            Toast.makeText(DownloadListActivity.a(this.b.a), R.string.download_share_weixin_friend, 1).show();
            try {
                DownloadListActivity.a(this.b.a, new File(((f) this.b.a.b.get(this.a)).d() + "/" + ((f) this.b.a.b.get(this.a)).c()));
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(DownloadListActivity.a(this.b.a), "请先安装微信再分享哦~", 1).show();
            }
        }
    }
}
