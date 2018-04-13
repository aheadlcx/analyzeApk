package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.ProviderResourceEntry;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.support.v4.util.LruCache;
import com.xiaomi.mipush.sdk.Constants;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompat {
    private static final a a;
    private static final LruCache<String, Typeface> b = new LruCache(16);

    interface a {
        Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i);

        Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr, int i);

        Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2);
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new TypefaceCompatApi26Impl();
        } else if (VERSION.SDK_INT >= 24 && b.isUsable()) {
            a = new b();
        } else if (VERSION.SDK_INT >= 21) {
            a = new a();
        } else {
            a = new c();
        }
    }

    private TypefaceCompat() {
    }

    @Nullable
    public static Typeface findFromCache(@NonNull Resources resources, int i, int i2) {
        return (Typeface) b.get(a(resources, i, i2));
    }

    private static String a(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + Constants.ACCEPT_TIME_SEPARATOR_SERVER + i + Constants.ACCEPT_TIME_SEPARATOR_SERVER + i2;
    }

    @Nullable
    public static Typeface createFromResourcesFamilyXml(@NonNull Context context, @NonNull FamilyResourceEntry familyResourceEntry, @NonNull Resources resources, int i, int i2, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        Typeface fontSync;
        boolean z2 = true;
        if (familyResourceEntry instanceof ProviderResourceEntry) {
            ProviderResourceEntry providerResourceEntry = (ProviderResourceEntry) familyResourceEntry;
            if (z) {
                if (providerResourceEntry.getFetchStrategy() != 0) {
                    z2 = false;
                }
            } else if (fontCallback != null) {
                z2 = false;
            }
            fontSync = FontsContractCompat.getFontSync(context, providerResourceEntry.getRequest(), fontCallback, handler, z2, z ? providerResourceEntry.getTimeout() : -1, i2);
        } else {
            fontSync = a.createFromFontFamilyFilesResourceEntry(context, (FontFamilyFilesResourceEntry) familyResourceEntry, resources, i2);
            if (fontCallback != null) {
                if (fontSync != null) {
                    fontCallback.callbackSuccessAsync(fontSync, handler);
                } else {
                    fontCallback.callbackFailAsync(-3, handler);
                }
            }
        }
        if (fontSync != null) {
            b.put(a(resources, i, i2), fontSync);
        }
        return fontSync;
    }

    @Nullable
    public static Typeface createFromResourcesFontFile(@NonNull Context context, @NonNull Resources resources, int i, String str, int i2) {
        Typeface createFromResourcesFontFile = a.createFromResourcesFontFile(context, resources, i, str, i2);
        if (createFromResourcesFontFile != null) {
            b.put(a(resources, i, i2), createFromResourcesFontFile);
        }
        return createFromResourcesFontFile;
    }

    @Nullable
    public static Typeface createFromFontInfo(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr, int i) {
        return a.createFromFontInfo(context, cancellationSignal, fontInfoArr, i);
    }
}
