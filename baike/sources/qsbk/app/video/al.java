package qsbk.app.video;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.TimeDelta;
import qsbk.app.ye.videotools.utils.VideoEditer;

class al extends AsyncTask<Void, Void, Bitmap> {
    final /* synthetic */ VideoEditActivity a;

    al(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    protected Bitmap a(Void... voidArr) {
        return BitmapFactory.decodeResource(this.a.getResources(), R.drawable.ic_end_watermark);
    }

    protected void a(Bitmap bitmap) {
        int i;
        super.a(bitmap);
        String filePath = this.a.d.getFilePath(this.a);
        String str = this.a.e() + "trim_" + filePath.substring(filePath.lastIndexOf(47) + 1);
        this.a.c = new TimeDelta();
        int min = Math.min(this.a.d.width, this.a.d.height);
        VideoEditer create = VideoEditer.create();
        create.addDataSource(filePath);
        create.setFrameRate(15);
        create.setTargetPath(str);
        create.setSegment(this.a.n, this.a.o);
        if (this.a.s) {
            create.setCropArea(this.a.q, this.a.r, min, min);
        }
        if (!this.a.s) {
            if (this.a.d.height > this.a.d.width) {
                if (this.a.d.height > VideoEditActivity.MAX_VIDEO_HEIGHT) {
                    this.a.b = VideoEditActivity.MAX_VIDEO_HEIGHT;
                }
                this.a.a = (int) (((float) (this.a.b * this.a.d.width)) / (((float) this.a.d.height) * 1.0f));
            } else {
                if (this.a.d.width > VideoEditActivity.MAX_VIDEO_WIDTH) {
                    this.a.a = VideoEditActivity.MAX_VIDEO_WIDTH;
                }
                this.a.b = (int) (((float) (this.a.d.height * VideoEditActivity.MAX_VIDEO_WIDTH)) / (((float) this.a.d.width) * 1.0f));
            }
        }
        min = this.a.a;
        int i2 = this.a.b;
        VideoEditActivity videoEditActivity = this.a;
        if (this.a.u.endsWith("90") || this.a.u.endsWith("270")) {
            i = i2;
        } else {
            i = min;
        }
        videoEditActivity.a = i;
        VideoEditActivity videoEditActivity2 = this.a;
        if (this.a.u.endsWith("90") || this.a.u.endsWith("270")) {
            i2 = min;
        }
        videoEditActivity2.b = i2;
        i = this.a.a % 2;
        if (i != 0) {
            VideoEditActivity videoEditActivity3 = this.a;
            videoEditActivity3.a -= i;
        }
        i = this.a.b % 2;
        if (i != 0) {
            videoEditActivity3 = this.a;
            videoEditActivity3.b -= i;
        }
        create.setTargetSize(this.a.a, this.a.b);
        create.setOnCompletionListener(new am(this, str, bitmap));
        create.setOnErrorListener(new an(this, str));
        create.prepare();
        create.start();
    }
}
