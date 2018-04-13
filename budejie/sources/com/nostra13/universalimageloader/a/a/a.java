package com.nostra13.universalimageloader.a.a;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.b.d$a;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Deprecated
public interface a {
    File a(String str);

    void a();

    boolean a(String str, Bitmap bitmap) throws IOException;

    boolean a(String str, InputStream inputStream, d$a d_a) throws IOException;
}
