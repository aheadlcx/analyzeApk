package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

public class d extends a<ParcelFileDescriptor> {
    protected /* synthetic */ Object a(AssetManager assetManager, String str) throws IOException {
        return b(assetManager, str);
    }

    public d(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    protected ParcelFileDescriptor b(AssetManager assetManager, String str) throws IOException {
        return assetManager.openFd(str).getParcelFileDescriptor();
    }

    protected void a(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        parcelFileDescriptor.close();
    }
}
