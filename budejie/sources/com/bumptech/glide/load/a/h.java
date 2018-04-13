package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

public class h extends a<InputStream> {
    protected /* synthetic */ Object a(AssetManager assetManager, String str) throws IOException {
        return b(assetManager, str);
    }

    public h(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    protected InputStream b(AssetManager assetManager, String str) throws IOException {
        return assetManager.open(str);
    }

    protected void a(InputStream inputStream) throws IOException {
        inputStream.close();
    }
}
