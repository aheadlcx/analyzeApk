package qsbk.app.utils.gif;

import android.graphics.Bitmap;

public class GifFrame {
    public int delay;
    public Bitmap image;
    public String imageName = null;
    public GifFrame nextFrame = null;

    public GifFrame(Bitmap bitmap, int i) {
        this.image = bitmap;
        this.delay = i;
    }

    public GifFrame(String str, int i) {
        this.imageName = str;
        this.delay = i;
    }
}
