package qsbk.app.video;

import android.hardware.Camera.Size;
import java.util.Comparator;

class p implements Comparator<Size> {
    final /* synthetic */ VideoCamera a;

    p(VideoCamera videoCamera) {
        this.a = videoCamera;
    }

    public int compare(Size size, Size size2) {
        if (size.height != size2.height) {
            return size.height - size2.height;
        }
        return size.width - size2.width;
    }
}
