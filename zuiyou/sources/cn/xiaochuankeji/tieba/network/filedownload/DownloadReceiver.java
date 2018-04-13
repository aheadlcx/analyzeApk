package cn.xiaochuankeji.tieba.network.filedownload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.facebook.common.util.d;

public class DownloadReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "cn.xiaochuan.download.retry".equalsIgnoreCase(intent.getAction())) {
            String stringExtra = intent.getStringExtra("download_url");
            if (d.a(Uri.parse(stringExtra))) {
                int intExtra = intent.getIntExtra("download_type", 0);
                if (intExtra == 1) {
                    e.a(stringExtra);
                } else if (intExtra == 2) {
                    e.b(stringExtra);
                }
            }
        }
    }
}
