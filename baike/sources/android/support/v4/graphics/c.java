package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(14)
@RestrictTo({Scope.LIBRARY_GROUP})
class c implements a {

    private interface a<T> {
        int getWeight(T t);

        boolean isItalic(T t);
    }

    c() {
    }

    private static <T> T a(T[] tArr, int i, a<T> aVar) {
        boolean z;
        int i2 = (i & 1) == 0 ? 400 : ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR;
        if ((i & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        T t = null;
        int i3 = Integer.MAX_VALUE;
        int length = tArr.length;
        int i4 = 0;
        while (i4 < length) {
            int i5;
            T t2;
            T t3 = tArr[i4];
            int abs = Math.abs(aVar.getWeight(t3) - i2) * 2;
            if (aVar.isItalic(t3) == z) {
                i5 = 0;
            } else {
                i5 = 1;
            }
            i5 += abs;
            if (t == null || r3 > i5) {
                i3 = i5;
                t2 = t3;
            } else {
                t2 = t;
            }
            i4++;
            t = t2;
        }
        return t;
    }

    protected FontInfo a(FontInfo[] fontInfoArr, int i) {
        return (FontInfo) a(fontInfoArr, i, new d(this));
    }

    protected Typeface a(Context context, InputStream inputStream) {
        Typeface typeface = null;
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile != null) {
            try {
                if (TypefaceCompatUtil.copyToFile(tempFile, inputStream)) {
                    typeface = Typeface.createFromFile(tempFile.getPath());
                    tempFile.delete();
                }
            } catch (RuntimeException e) {
            } finally {
                tempFile.delete();
            }
        }
        return typeface;
    }

    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr, int i) {
        Closeable openInputStream;
        Throwable th;
        Typeface typeface = null;
        if (fontInfoArr.length >= 1) {
            try {
                openInputStream = context.getContentResolver().openInputStream(a(fontInfoArr, i).getUri());
                try {
                    typeface = a(context, (InputStream) openInputStream);
                    TypefaceCompatUtil.closeQuietly(openInputStream);
                } catch (IOException e) {
                    TypefaceCompatUtil.closeQuietly(openInputStream);
                    return typeface;
                } catch (Throwable th2) {
                    th = th2;
                    TypefaceCompatUtil.closeQuietly(openInputStream);
                    throw th;
                }
            } catch (IOException e2) {
                openInputStream = typeface;
                TypefaceCompatUtil.closeQuietly(openInputStream);
                return typeface;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                openInputStream = typeface;
                th = th4;
                TypefaceCompatUtil.closeQuietly(openInputStream);
                throw th;
            }
        }
        return typeface;
    }

    private FontFileResourceEntry a(FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i) {
        return (FontFileResourceEntry) a(fontFamilyFilesResourceEntry.getEntries(), i, new e(this));
    }

    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        FontFileResourceEntry a = a(fontFamilyFilesResourceEntry, i);
        if (a == null) {
            return null;
        }
        return TypefaceCompat.createFromResourcesFontFile(context, resources, a.getResourceId(), a.getFileName(), i);
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        Typeface typeface = null;
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile != null) {
            try {
                if (TypefaceCompatUtil.copyToFile(tempFile, resources, i)) {
                    typeface = Typeface.createFromFile(tempFile.getPath());
                    tempFile.delete();
                }
            } catch (RuntimeException e) {
            } finally {
                tempFile.delete();
            }
        }
        return typeface;
    }
}
