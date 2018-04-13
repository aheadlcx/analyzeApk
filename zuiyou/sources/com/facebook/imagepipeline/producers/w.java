package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import com.facebook.common.c.a;
import com.facebook.common.memory.g;
import com.facebook.common.util.d;
import com.facebook.d.b;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class w extends y implements av<e> {
    private static final Class<?> a = w.class;
    private static final String[] b = new String[]{"_id", "_data"};
    private static final String[] c = new String[]{"_data"};
    private static final Rect d = new Rect(0, 0, 512, BitmapCounterProvider.MAX_BITMAP_COUNT);
    private static final Rect e = new Rect(0, 0, 96, 96);
    private final ContentResolver f;

    public w(Executor executor, g gVar, ContentResolver contentResolver) {
        super(executor, gVar);
        this.f = contentResolver;
    }

    public boolean a(c cVar) {
        return aw.a(d.width(), d.height(), cVar);
    }

    protected e a(ImageRequest imageRequest) throws IOException {
        Uri b = imageRequest.b();
        if (d.e(b)) {
            e a = a(b, imageRequest.g());
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    @Nullable
    private e a(Uri uri, c cVar) throws IOException {
        Cursor query = this.f.query(uri, b, null, null, null);
        if (query == null) {
            return null;
        }
        try {
            if (query.getCount() == 0) {
                return null;
            }
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("_data"));
            if (cVar != null) {
                e a = a(cVar, query.getInt(query.getColumnIndex("_id")));
                if (a != null) {
                    a.c(b(string));
                    query.close();
                    return a;
                }
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    private e a(c cVar, int i) throws IOException {
        Throwable th;
        e eVar = null;
        int b = b(cVar);
        if (b != 0) {
            Cursor queryMiniThumbnail;
            try {
                queryMiniThumbnail = Thumbnails.queryMiniThumbnail(this.f, (long) i, b, c);
                if (queryMiniThumbnail != null) {
                    try {
                        queryMiniThumbnail.moveToFirst();
                        if (queryMiniThumbnail.getCount() > 0) {
                            String string = queryMiniThumbnail.getString(queryMiniThumbnail.getColumnIndex("_data"));
                            if (new File(string).exists()) {
                                eVar = b(new FileInputStream(string), a(string));
                                if (queryMiniThumbnail != null) {
                                    queryMiniThumbnail.close();
                                }
                            }
                        }
                        if (queryMiniThumbnail != null) {
                            queryMiniThumbnail.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (queryMiniThumbnail != null) {
                            queryMiniThumbnail.close();
                        }
                        throw th;
                    }
                } else if (queryMiniThumbnail != null) {
                    queryMiniThumbnail.close();
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                queryMiniThumbnail = null;
                th = th4;
                if (queryMiniThumbnail != null) {
                    queryMiniThumbnail.close();
                }
                throw th;
            }
        }
        return eVar;
    }

    private static int b(c cVar) {
        if (aw.a(e.width(), e.height(), cVar)) {
            return 3;
        }
        if (aw.a(d.width(), d.height(), cVar)) {
            return 1;
        }
        return 0;
    }

    private static int a(String str) {
        return str == null ? -1 : (int) new File(str).length();
    }

    protected String a() {
        return "LocalContentUriThumbnailFetchProducer";
    }

    private static int b(String str) {
        int i = 0;
        if (str != null) {
            try {
                i = b.a(new ExifInterface(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1));
            } catch (Throwable e) {
                a.c(a, e, "Unable to retrieve thumbnail rotation for %s", new Object[]{str});
            }
        }
        return i;
    }
}
