package com.bumptech.glide.load.a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class i extends g<InputStream> {
    protected /* synthetic */ Object b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        return a(uri, contentResolver);
    }

    public i(Context context, Uri uri) {
        super(context, uri);
    }

    protected InputStream a(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        return contentResolver.openInputStream(uri);
    }

    protected void a(InputStream inputStream) throws IOException {
        inputStream.close();
    }
}
