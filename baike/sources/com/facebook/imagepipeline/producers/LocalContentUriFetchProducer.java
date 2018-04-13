package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class LocalContentUriFetchProducer extends LocalFetchProducer {
    public static final String PRODUCER_NAME = "LocalContentUriFetchProducer";
    private static final String[] PROJECTION = new String[]{FileDownloadModel.ID, "_data"};
    private final ContentResolver mContentResolver;

    public LocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.mContentResolver = contentResolver;
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        Uri sourceUri = imageRequest.getSourceUri();
        if (UriUtil.isLocalContactUri(sourceUri)) {
            InputStream openInputStream;
            if (sourceUri.toString().endsWith("/photo")) {
                openInputStream = this.mContentResolver.openInputStream(sourceUri);
            } else {
                openInputStream = Contacts.openContactPhotoInputStream(this.mContentResolver, sourceUri);
                if (openInputStream == null) {
                    throw new IOException("Contact photo does not exist: " + sourceUri);
                }
            }
            return getEncodedImage(openInputStream, -1);
        }
        if (UriUtil.isLocalCameraUri(sourceUri)) {
            EncodedImage cameraImage = getCameraImage(sourceUri);
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return getEncodedImage(this.mContentResolver.openInputStream(sourceUri), -1);
    }

    @Nullable
    private EncodedImage getCameraImage(Uri uri) throws IOException {
        EncodedImage encodedImage = null;
        Cursor query = this.mContentResolver.query(uri, PROJECTION, null, null, null);
        if (query != null) {
            try {
                if (query.getCount() != 0) {
                    query.moveToFirst();
                    String string = query.getString(query.getColumnIndex("_data"));
                    if (string != null) {
                        encodedImage = getEncodedImage(new FileInputStream(string), getLength(string));
                        query.close();
                    } else {
                        query.close();
                    }
                }
            } finally {
                query.close();
            }
        }
        return encodedImage;
    }

    private static int getLength(String str) {
        return str == null ? -1 : (int) new File(str).length();
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
