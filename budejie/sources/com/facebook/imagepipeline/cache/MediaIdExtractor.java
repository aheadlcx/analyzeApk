package com.facebook.imagepipeline.cache;

import android.net.Uri;
import javax.annotation.Nullable;

public interface MediaIdExtractor {
    @Nullable
    String getMediaIdFrom(Uri uri);
}
