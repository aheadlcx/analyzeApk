package qsbk.app.video;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.ye.videotools.utils.VideoEditer;

class bn extends AsyncTask<Void, Void, Bitmap> {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ File c;
    final /* synthetic */ VideoRecordActivity d;

    bn(VideoRecordActivity videoRecordActivity, String str, String str2, File file) {
        this.d = videoRecordActivity;
        this.a = str;
        this.b = str2;
        this.c = file;
    }

    protected Bitmap a(Void... voidArr) {
        return BitmapFactory.decodeResource(this.d.getResources(), R.drawable.ic_end_watermark);
    }

    protected void a(Bitmap bitmap) {
        super.a(bitmap);
        VideoEditer create = VideoEditer.create();
        create.addDataSource(this.a);
        create.setFrameRate(15);
        create.setTargetPath(this.b);
        create.setCropArea(0, 0, VideoEditActivity.MAX_VIDEO_WIDTH, VideoEditActivity.MAX_VIDEO_WIDTH);
        create.setTargetSize(VideoEditActivity.MAX_VIDEO_WIDTH, VideoEditActivity.MAX_VIDEO_WIDTH);
        create.setOnCompletionListener(new bo(this, bitmap));
        create.setOnErrorListener(new bp(this));
        create.prepare();
        create.start();
    }
}
