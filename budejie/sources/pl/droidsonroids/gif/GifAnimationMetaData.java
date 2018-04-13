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

public class GifAnimationMetaData implements Parcelable, Serializable {
    public static final Creator<GifAnimationMetaData> CREATOR = new Creator<GifAnimationMetaData>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public GifAnimationMetaData a(Parcel parcel) {
            return new GifAnimationMetaData(parcel);
        }

        public GifAnimationMetaData[] a(int i) {
            return new GifAnimationMetaData[i];
        }
    };
    private static final long serialVersionUID = 6518019337497570800L;
    private final int[] mMetaData;

    public GifAnimationMetaData(Resources resources, int i) throws NotFoundException, IOException {
        this(resources.openRawResourceFd(i));
    }

    public GifAnimationMetaData(AssetManager assetManager, String str) throws IOException {
        this(assetManager.openFd(str));
    }

    public GifAnimationMetaData(String str) throws IOException {
        this.mMetaData = new int[5];
        if (str == null) {
            throw new NullPointerException("Source is null");
        }
        init(GifDrawable.openFile(this.mMetaData, str, true));
    }

    public GifAnimationMetaData(File file) throws IOException {
        this.mMetaData = new int[5];
        if (file == null) {
            throw new NullPointerException("Source is null");
        }
        init(GifDrawable.openFile(this.mMetaData, file.getPath(), true));
    }

    public GifAnimationMetaData(InputStream inputStream) throws IOException {
        this.mMetaData = new int[5];
        if (inputStream == null) {
            throw new NullPointerException("Source is null");
        } else if (inputStream.markSupported()) {
            init(GifDrawable.openStream(this.mMetaData, inputStream, true));
        } else {
            throw new IllegalArgumentException("InputStream does not support marking");
        }
    }

    public GifAnimationMetaData(AssetFileDescriptor assetFileDescriptor) throws IOException {
        this.mMetaData = new int[5];
        if (assetFileDescriptor == null) {
            throw new NullPointerException("Source is null");
        }
        try {
            init(GifDrawable.openFd(this.mMetaData, assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), true));
        } catch (IOException e) {
            assetFileDescriptor.close();
            throw e;
        }
    }

    public GifAnimationMetaData(FileDescriptor fileDescriptor) throws IOException {
        this.mMetaData = new int[5];
        if (fileDescriptor == null) {
            throw new NullPointerException("Source is null");
        }
        init(GifDrawable.openFd(this.mMetaData, fileDescriptor, 0, true));
    }

    public GifAnimationMetaData(byte[] bArr) throws IOException {
        this.mMetaData = new int[5];
        if (bArr == null) {
            throw new NullPointerException("Source is null");
        }
        init(GifDrawable.openByteArray(this.mMetaData, bArr, true));
    }

    public GifAnimationMetaData(ByteBuffer byteBuffer) throws IOException {
        this.mMetaData = new int[5];
        if (byteBuffer == null) {
            throw new NullPointerException("Source is null");
        } else if (byteBuffer.isDirect()) {
            init(GifDrawable.openDirectByteBuffer(this.mMetaData, byteBuffer, true));
        } else {
            throw new IllegalArgumentException("ByteBuffer is not direct");
        }
    }

    public GifAnimationMetaData(ContentResolver contentResolver, Uri uri) throws IOException {
        this(contentResolver.openAssetFileDescriptor(uri, "r"));
    }

    private void init(long j) {
        this.mMetaData[3] = GifDrawable.getLoopCount(j);
        this.mMetaData[4] = GifDrawable.getDuration(j);
        GifDrawable.free(j);
    }

    public int getWidth() {
        return this.mMetaData[0];
    }

    public int getHeight() {
        return this.mMetaData[1];
    }

    public int getNumberOfFrames() {
        return this.mMetaData[2];
    }

    public int getLoopCount() {
        return this.mMetaData[3];
    }

    public int getDuration() {
        return this.mMetaData[4];
    }

    public boolean isAnimated() {
        return this.mMetaData[2] > 1 && this.mMetaData[4] > 0;
    }

    public String toString() {
        String num = this.mMetaData[3] == 0 ? "Infinity" : Integer.toString(this.mMetaData[3]);
        num = String.format(Locale.US, "GIF: size: %dx%d, frames: %d, loops: %s, duration: %d", new Object[]{Integer.valueOf(this.mMetaData[0]), Integer.valueOf(this.mMetaData[1]), Integer.valueOf(this.mMetaData[2]), num, Integer.valueOf(this.mMetaData[4])});
        return isAnimated() ? "Animated " + num : num;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        for (int writeInt : this.mMetaData) {
            parcel.writeInt(writeInt);
        }
    }

    private GifAnimationMetaData(Parcel parcel) {
        this.mMetaData = new int[5];
        for (int i = 0; i < this.mMetaData.length; i++) {
            this.mMetaData[i] = parcel.readInt();
        }
    }
}
