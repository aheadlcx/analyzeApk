package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import com.facebook.common.memory.g;
import com.facebook.common.util.d;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class v extends y {
    private static final String[] a = new String[]{"_id", "_data"};
    private final ContentResolver b;

    public v(Executor executor, g gVar, ContentResolver contentResolver) {
        super(executor, gVar);
        this.b = contentResolver;
    }

    protected e a(ImageRequest imageRequest) throws IOException {
        Uri b = imageRequest.b();
        if (d.d(b)) {
            InputStream openInputStream;
            if (b.toString().endsWith("/photo")) {
                openInputStream = this.b.openInputStream(b);
            } else {
                openInputStream = Contacts.openContactPhotoInputStream(this.b, b);
                if (openInputStream == null) {
                    throw new IOException("Contact photo does not exist: " + b);
                }
            }
            return b(openInputStream, -1);
        }
        if (d.e(b)) {
            e a = a(b);
            if (a != null) {
                return a;
            }
        }
        return b(this.b.openInputStream(b), -1);
    }

    @Nullable
    private e a(Uri uri) throws IOException {
        e eVar = null;
        Cursor query = this.b.query(uri, a, null, null, null);
        if (query != null) {
            try {
                if (query.getCount() != 0) {
                    query.moveToFirst();
                    String string = query.getString(query.getColumnIndex("_data"));
                    if (string != null) {
                        eVar = b(new FileInputStream(string), a(string));
                        query.close();
                    } else {
                        query.close();
                    }
                }
            } finally {
                query.close();
            }
        }
        return eVar;
    }

    private static int a(String str) {
        return str == null ? -1 : (int) new File(str).length();
    }

    protected String a() {
        return "LocalContentUriFetchProducer";
    }
}
