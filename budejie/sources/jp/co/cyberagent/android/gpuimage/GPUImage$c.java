package jp.co.cyberagent.android.gpuimage;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class GPUImage$c extends GPUImage$b {
    final /* synthetic */ GPUImage a;
    private final Uri c;

    public GPUImage$c(GPUImage gPUImage, GPUImage gPUImage2, Uri uri) {
        this.a = gPUImage;
        super(gPUImage, gPUImage2);
        this.c = uri;
    }

    protected Bitmap a(Options options) {
        try {
            InputStream openStream;
            if (this.c.getScheme().startsWith(UriUtil.HTTP_SCHEME) || this.c.getScheme().startsWith("https")) {
                openStream = new URL(this.c.toString()).openStream();
            } else {
                openStream = GPUImage.b(this.a).getContentResolver().openInputStream(this.c);
            }
            return BitmapFactory.decodeStream(openStream, null, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected int a() throws IOException {
        Cursor query = GPUImage.b(this.a).getContentResolver().query(this.c, new String[]{"orientation"}, null, null, null);
        if (query == null || query.getCount() != 1) {
            return 0;
        }
        query.moveToFirst();
        int i = query.getInt(0);
        query.close();
        return i;
    }
}
