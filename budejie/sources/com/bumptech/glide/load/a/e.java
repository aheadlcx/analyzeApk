package com.bumptech.glide.load.a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class e extends g<ParcelFileDescriptor> {
    protected /* synthetic */ Object b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        return a(uri, contentResolver);
    }

    public e(Context context, Uri uri) {
        super(context, uri);
    }

    protected ParcelFileDescriptor a(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        return contentResolver.openAssetFileDescriptor(uri, "r").getParcelFileDescriptor();
    }

    protected void a(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        parcelFileDescriptor.close();
    }
}
