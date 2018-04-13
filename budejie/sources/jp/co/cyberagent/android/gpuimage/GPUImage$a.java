package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import java.io.File;
import java.io.IOException;

class GPUImage$a extends GPUImage$b {
    final /* synthetic */ GPUImage a;
    private final File c;

    public GPUImage$a(GPUImage gPUImage, GPUImage gPUImage2, File file) {
        this.a = gPUImage;
        super(gPUImage, gPUImage2);
        this.c = file;
    }

    protected Bitmap a(Options options) {
        return BitmapFactory.decodeFile(this.c.getAbsolutePath(), options);
    }

    protected int a() throws IOException {
        switch (new ExifInterface(this.c.getAbsolutePath()).getAttributeInt("Orientation", 1)) {
            case 3:
                return 180;
            case 6:
                return 90;
            case 8:
                return 270;
            default:
                return 0;
        }
    }
}
