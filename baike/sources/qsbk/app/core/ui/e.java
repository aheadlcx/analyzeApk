package qsbk.app.core.ui;

import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.text.format.DateFormat;
import com.qiushibaike.httpdns.lib.AppContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

class e implements Runnable {
    final /* synthetic */ BrowseLargeImageActivity a;

    e(BrowseLargeImageActivity browseLargeImageActivity) {
        this.a = browseLargeImageActivity;
    }

    public void run() {
        try {
            String str = DateFormat.format("yyyyMMddhhmmss", new Date()).toString() + ".png";
            String str2 = Environment.getExternalStorageDirectory() + "/Save/";
            File file = new File(str2);
            if (!(file.exists() && file.isDirectory())) {
                file.mkdirs();
            }
            file = new File(str2, str);
            OutputStream fileOutputStream = new FileOutputStream(file);
            this.a.e.compress(CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            this.a.postDelayed(new f(this));
            BrowseLargeImageActivity.b(AppContext.getContext(), file, str);
        } catch (Throwable th) {
            this.a.postDelayed(new g(this));
            th.printStackTrace();
        }
    }
}
