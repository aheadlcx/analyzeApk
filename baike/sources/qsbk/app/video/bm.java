package qsbk.app.video;

import java.io.File;
import qsbk.app.core.AsyncTask;

class bm extends AsyncTask<Void, Void, File> {
    final /* synthetic */ VideoRecordActivity a;

    bm(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    protected File a(Void... voidArr) {
        File file = new File(this.a.d() + System.currentTimeMillis() + ".mp4");
        this.a.c(file);
        return file;
    }

    protected void a(File file) {
        this.a.b(file);
    }
}
