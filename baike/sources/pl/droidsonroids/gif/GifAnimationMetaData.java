package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Locale;
import qsbk.app.core.model.CustomButton;

public class GifAnimationMetaData implements Parcelable, Serializable {
    public static final Creator<GifAnimationMetaData> CREATOR = new a();
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public GifAnimationMetaData(Resources resources, int i) throws NotFoundException, IOException {
        this(resources.openRawResourceFd(i));
    }

    public GifAnimationMetaData(AssetManager assetManager, String str) throws IOException {
        this(assetManager.openFd(str));
    }

    public GifAnimationMetaData(String str) throws IOException {
        this(GifInfoHandle.openFile(str, true));
    }

    public GifAnimationMetaData(File file) throws IOException {
        this(GifInfoHandle.openFile(file.getPath(), true));
    }

    public GifAnimationMetaData(InputStream inputStream) throws IOException {
        this(GifInfoHandle.a(inputStream, true));
    }

    public GifAnimationMetaData(AssetFileDescriptor assetFileDescriptor) throws IOException {
        this(GifInfoHandle.a(assetFileDescriptor, true));
    }

    public GifAnimationMetaData(FileDescriptor fileDescriptor) throws IOException {
        this(GifInfoHandle.openFd(fileDescriptor, 0, true));
    }

    public GifAnimationMetaData(byte[] bArr) throws IOException {
        this(GifInfoHandle.openByteArray(bArr, true));
    }

    public GifAnimationMetaData(ByteBuffer byteBuffer) throws IOException {
        this(GifInfoHandle.openDirectByteBuffer(byteBuffer, true));
    }

    public GifAnimationMetaData(ContentResolver contentResolver, Uri uri) throws IOException {
        this(contentResolver.openAssetFileDescriptor(uri, CustomButton.POSITION_RIGHT));
    }

    private GifAnimationMetaData(GifInfoHandle gifInfoHandle) {
        this.a = gifInfoHandle.f();
        this.b = gifInfoHandle.h();
        gifInfoHandle.a();
        this.d = gifInfoHandle.a;
        this.c = gifInfoHandle.b;
        this.e = gifInfoHandle.c;
    }

    public int getWidth() {
        return this.d;
    }

    public int getHeight() {
        return this.c;
    }

    public int getNumberOfFrames() {
        return this.e;
    }

    public int getLoopCount() {
        return this.a;
    }

    public int getDuration() {
        return this.b;
    }

    public boolean isAnimated() {
        return this.e > 1 && this.b > 0;
    }

    public String toString() {
        String num = this.a == 0 ? "Infinity" : Integer.toString(this.a);
        num = String.format(Locale.US, "GIF: size: %dx%d, frames: %d, loops: %s, duration: %d", new Object[]{Integer.valueOf(this.d), Integer.valueOf(this.c), Integer.valueOf(this.e), num, Integer.valueOf(this.b)});
        return isAnimated() ? "Animated " + num : num;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
    }

    private GifAnimationMetaData(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
    }
}
