package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import qsbk.app.core.model.CustomButton;

public class GifDrawableBuilder {
    private g a;
    private GifDrawable b;
    private ScheduledThreadPoolExecutor c;

    private interface g {
        GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException;
    }

    private static class a implements g {
        private final AssetManager a;
        private final String b;

        private a(AssetManager assetManager, String str) {
            this.a = assetManager;
            this.b = str;
        }

        public GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException {
            return new d(this.a.openFd(this.b)).build(gifDrawable, scheduledThreadPoolExecutor);
        }
    }

    private static class b implements g {
        private final byte[] a;

        private b(byte[] bArr) {
            this.a = bArr;
        }

        public GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException {
            return new GifDrawable(GifInfoHandle.openByteArray(this.a, false), (long) this.a.length, gifDrawable, scheduledThreadPoolExecutor);
        }
    }

    private static class c implements g {
        private final ByteBuffer a;

        private c(ByteBuffer byteBuffer) {
            this.a = byteBuffer;
        }

        public GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException {
            return new GifDrawable(GifInfoHandle.openDirectByteBuffer(this.a, false), (long) this.a.capacity(), gifDrawable, scheduledThreadPoolExecutor);
        }
    }

    private static class d implements g {
        private final FileDescriptor a;
        private final long b;
        private final long c;

        private d(AssetFileDescriptor assetFileDescriptor) {
            this.a = assetFileDescriptor.getFileDescriptor();
            this.b = assetFileDescriptor.getLength();
            this.c = assetFileDescriptor.getStartOffset();
        }

        private d(FileDescriptor fileDescriptor) {
            this.a = fileDescriptor;
            this.b = -1;
            this.c = 0;
        }

        private d(Resources resources, int i) {
            this(resources.openRawResourceFd(i));
        }

        public GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException {
            return new GifDrawable(GifInfoHandle.openFd(this.a, this.c, false), this.b, gifDrawable, scheduledThreadPoolExecutor);
        }
    }

    private static class e implements g {
        private final File a;

        private e(File file) {
            this.a = file;
        }

        private e(String str) {
            this.a = new File(str);
        }

        public GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException {
            return new GifDrawable(GifInfoHandle.openFile(this.a.getPath(), false), this.a.length(), gifDrawable, scheduledThreadPoolExecutor);
        }
    }

    private static class f implements g {
        private final InputStream a;

        private f(InputStream inputStream) {
            this.a = inputStream;
        }

        public GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException {
            return new GifDrawable(GifInfoHandle.a(this.a, false), -1, gifDrawable, scheduledThreadPoolExecutor);
        }
    }

    private static class h implements g {
        private final ContentResolver a;
        private final Uri b;

        private h(ContentResolver contentResolver, Uri uri) {
            this.a = contentResolver;
            this.b = uri;
        }

        public GifDrawable build(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) throws IOException {
            return new d(this.a.openAssetFileDescriptor(this.b, CustomButton.POSITION_RIGHT)).build(gifDrawable, scheduledThreadPoolExecutor);
        }
    }

    public GifDrawable build() throws IOException {
        if (this.a != null) {
            return this.a.build(this.b, this.c);
        }
        throw new NullPointerException("Source is not set");
    }

    public GifDrawableBuilder with(GifDrawable gifDrawable) {
        this.b = gifDrawable;
        return this;
    }

    public GifDrawableBuilder threadPoolSize(int i) {
        this.c = new ScheduledThreadPoolExecutor(i);
        return this;
    }

    public GifDrawableBuilder taskExecutor(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        this.c = scheduledThreadPoolExecutor;
        return this;
    }

    public GifDrawableBuilder from(InputStream inputStream) {
        this.a = new f(inputStream);
        return this;
    }

    public GifDrawableBuilder from(AssetFileDescriptor assetFileDescriptor) {
        this.a = new d(assetFileDescriptor);
        return this;
    }

    public GifDrawableBuilder from(FileDescriptor fileDescriptor) {
        this.a = new d(fileDescriptor);
        return this;
    }

    public GifDrawableBuilder from(AssetManager assetManager, String str) {
        this.a = new a(assetManager, str);
        return this;
    }

    public GifDrawableBuilder from(ContentResolver contentResolver, Uri uri) {
        this.a = new h(contentResolver, uri);
        return this;
    }

    public GifDrawableBuilder from(File file) {
        this.a = new e(file);
        return this;
    }

    public GifDrawableBuilder from(String str) {
        this.a = new e(str);
        return this;
    }

    public GifDrawableBuilder from(byte[] bArr) {
        this.a = new b(bArr);
        return this;
    }

    public GifDrawableBuilder from(ByteBuffer byteBuffer) {
        this.a = new c(byteBuffer);
        return this;
    }

    public GifDrawableBuilder from(Resources resources, int i) {
        this.a = new d(resources, i);
        return this;
    }
}
