package qsbk.app.widget.imageviewer;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import qsbk.app.utils.StorageUtils;

public abstract class ImageDownloadSubscriber extends BaseDataSubscriber<CloseableReference<PooledByteBuffer>> {
    private final File a;
    private volatile boolean b;

    @WorkerThread
    protected abstract void a(int i);

    @WorkerThread
    protected abstract void a(File file);

    @WorkerThread
    protected abstract void a(Throwable th);

    public ImageDownloadSubscriber(Context context) {
        this.a = new File(context.getCacheDir(), "" + System.currentTimeMillis() + ".jpg");
    }

    public void onProgressUpdate(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        if (!this.b) {
            a((int) (dataSource.getProgress() * 100.0f));
        }
    }

    protected void onNewResultImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        InputStream pooledByteBufferInputStream;
        OutputStream fileOutputStream;
        Throwable e;
        InputStream inputStream;
        OutputStream outputStream = null;
        if (dataSource.isFinished() && dataSource.getResult() != null) {
            try {
                pooledByteBufferInputStream = new PooledByteBufferInputStream((PooledByteBuffer) ((CloseableReference) dataSource.getResult()).get());
                try {
                    fileOutputStream = new FileOutputStream(this.a);
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = null;
                    inputStream = pooledByteBufferInputStream;
                    try {
                        a(e);
                        StorageUtils.closeQuietly(inputStream);
                        StorageUtils.closeQuietly(fileOutputStream);
                    } catch (Throwable th) {
                        e = th;
                        pooledByteBufferInputStream = inputStream;
                        outputStream = fileOutputStream;
                        StorageUtils.closeQuietly(pooledByteBufferInputStream);
                        StorageUtils.closeQuietly(outputStream);
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    StorageUtils.closeQuietly(pooledByteBufferInputStream);
                    StorageUtils.closeQuietly(outputStream);
                    throw e;
                }
                try {
                    StorageUtils.copy(pooledByteBufferInputStream, fileOutputStream);
                    this.b = true;
                    a(this.a);
                    StorageUtils.closeQuietly(pooledByteBufferInputStream);
                    StorageUtils.closeQuietly(fileOutputStream);
                } catch (IOException e3) {
                    e = e3;
                    inputStream = pooledByteBufferInputStream;
                    a(e);
                    StorageUtils.closeQuietly(inputStream);
                    StorageUtils.closeQuietly(fileOutputStream);
                } catch (Throwable th3) {
                    e = th3;
                    outputStream = fileOutputStream;
                    StorageUtils.closeQuietly(pooledByteBufferInputStream);
                    StorageUtils.closeQuietly(outputStream);
                    throw e;
                }
            } catch (IOException e4) {
                e = e4;
                fileOutputStream = null;
                a(e);
                StorageUtils.closeQuietly(inputStream);
                StorageUtils.closeQuietly(fileOutputStream);
            } catch (Throwable th4) {
                e = th4;
                pooledByteBufferInputStream = null;
                StorageUtils.closeQuietly(pooledByteBufferInputStream);
                StorageUtils.closeQuietly(outputStream);
                throw e;
            }
        }
    }

    protected void onFailureImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        this.b = true;
        a(new RuntimeException("onFailureImpl"));
    }
}
