package qsbk.app.utils.image.issue;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class c implements Task {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ Reporter d;

    c(Reporter reporter, Context context, String str, String str2) {
        this.d = reporter;
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public Object proccess() throws QiushibaikeException {
        Object sDPath = DeviceUtils.getSDPath();
        if (!TextUtils.isEmpty(sDPath)) {
            File file = new File(sDPath + File.separator + this.a.getPackageName() + File.separator + "img_issue");
            if (file.exists()) {
                FileUtils.removeOldFiles(file, new d(this), PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
            } else {
                file.mkdirs();
            }
            try {
                FileWriter fileWriter = new FileWriter(new File(file, this.b));
                fileWriter.write(this.c);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                throw new QiushibaikeException(String.format("%1$s happened when writing file %2$s", new Object[]{e.toString(), this.b}));
            }
        }
        return null;
    }

    public void success(Object obj) {
    }

    public void fail(Throwable th) {
    }
}
