package android.support.v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.provider.SelfDestructiveThread.ReplyCallback;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import com.facebook.common.util.UriUtil;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontsContractCompat {
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    private static final LruCache<String, Typeface> a = new LruCache(16);
    private static final SelfDestructiveThread b = new SelfDestructiveThread("fonts", 10, 10000);
    private static final Object c = new Object();
    @GuardedBy("sLock")
    private static final SimpleArrayMap<String, ArrayList<ReplyCallback<a>>> d = new SimpleArrayMap();
    private static final Comparator<byte[]> e = new o();

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final int a;
        private final FontInfo[] b;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontFamilyResult(int i, @Nullable FontInfo[] fontInfoArr) {
            this.a = i;
            this.b = fontInfoArr;
        }

        public int getStatusCode() {
            return this.a;
        }

        public FontInfo[] getFonts() {
            return this.b;
        }
    }

    public static class FontInfo {
        private final Uri a;
        private final int b;
        private final int c;
        private final boolean d;
        private final int e;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
            this.a = (Uri) Preconditions.checkNotNull(uri);
            this.b = i;
            this.c = i2;
            this.d = z;
            this.e = i3;
        }

        @NonNull
        public Uri getUri() {
            return this.a;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.b;
        }

        @IntRange(from = 1, to = 1000)
        public int getWeight() {
            return this.c;
        }

        public boolean isItalic() {
            return this.d;
        }

        public int getResultCode() {
            return this.e;
        }
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public static final int RESULT_OK = 0;

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }

        public void onTypefaceRequestFailed(int i) {
        }
    }

    private static final class a {
        final Typeface a;
        final int b;

        a(@Nullable Typeface typeface, int i) {
            this.a = typeface;
            this.b = i;
        }
    }

    private FontsContractCompat() {
    }

    @NonNull
    private static a b(Context context, FontRequest fontRequest, int i) {
        int i2 = -3;
        try {
            FontFamilyResult fetchFonts = fetchFonts(context, null, fontRequest);
            if (fetchFonts.getStatusCode() == 0) {
                Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fetchFonts.getFonts(), i);
                if (createFromFontInfo != null) {
                    i2 = 0;
                }
                return new a(createFromFontInfo, i2);
            }
            if (fetchFonts.getStatusCode() == 1) {
                i2 = -2;
            }
            return new a(null, i2);
        } catch (NameNotFoundException e) {
            return new a(null, -1);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final void resetCache() {
        a.evictAll();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static android.graphics.Typeface getFontSync(android.content.Context r6, android.support.v4.provider.FontRequest r7, @android.support.annotation.Nullable android.support.v4.content.res.ResourcesCompat.FontCallback r8, @android.support.annotation.Nullable android.os.Handler r9, boolean r10, int r11, int r12) {
        /*
        r2 = 0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r7.getIdentifier();
        r0 = r0.append(r1);
        r1 = "-";
        r0 = r0.append(r1);
        r0 = r0.append(r12);
        r3 = r0.toString();
        r0 = a;
        r0 = r0.get(r3);
        r0 = (android.graphics.Typeface) r0;
        if (r0 == 0) goto L_0x002d;
    L_0x0026:
        if (r8 == 0) goto L_0x002b;
    L_0x0028:
        r8.onFontRetrieved(r0);
    L_0x002b:
        r2 = r0;
    L_0x002c:
        return r2;
    L_0x002d:
        if (r10 == 0) goto L_0x004a;
    L_0x002f:
        r0 = -1;
        if (r11 != r0) goto L_0x004a;
    L_0x0032:
        r0 = b(r6, r7, r12);
        if (r8 == 0) goto L_0x0041;
    L_0x0038:
        r1 = r0.b;
        if (r1 != 0) goto L_0x0044;
    L_0x003c:
        r1 = r0.a;
        r8.callbackSuccessAsync(r1, r9);
    L_0x0041:
        r2 = r0.a;
        goto L_0x002c;
    L_0x0044:
        r1 = r0.b;
        r8.callbackFailAsync(r1, r9);
        goto L_0x0041;
    L_0x004a:
        r4 = new android.support.v4.provider.b;
        r4.<init>(r6, r7, r12, r3);
        if (r10 == 0) goto L_0x005c;
    L_0x0051:
        r0 = b;	 Catch:{ InterruptedException -> 0x009e }
        r0 = r0.postAndWait(r4, r11);	 Catch:{ InterruptedException -> 0x009e }
        r0 = (android.support.v4.provider.FontsContractCompat.a) r0;	 Catch:{ InterruptedException -> 0x009e }
        r2 = r0.a;	 Catch:{ InterruptedException -> 0x009e }
        goto L_0x002c;
    L_0x005c:
        if (r8 != 0) goto L_0x007c;
    L_0x005e:
        r1 = r2;
    L_0x005f:
        r5 = c;
        monitor-enter(r5);
        r0 = d;	 Catch:{ all -> 0x0079 }
        r0 = r0.containsKey(r3);	 Catch:{ all -> 0x0079 }
        if (r0 == 0) goto L_0x0083;
    L_0x006a:
        if (r1 == 0) goto L_0x0077;
    L_0x006c:
        r0 = d;	 Catch:{ all -> 0x0079 }
        r0 = r0.get(r3);	 Catch:{ all -> 0x0079 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x0079 }
        r0.add(r1);	 Catch:{ all -> 0x0079 }
    L_0x0077:
        monitor-exit(r5);	 Catch:{ all -> 0x0079 }
        goto L_0x002c;
    L_0x0079:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0079 }
        throw r0;
    L_0x007c:
        r0 = new android.support.v4.provider.c;
        r0.<init>(r8, r9);
        r1 = r0;
        goto L_0x005f;
    L_0x0083:
        if (r1 == 0) goto L_0x0092;
    L_0x0085:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0079 }
        r0.<init>();	 Catch:{ all -> 0x0079 }
        r0.add(r1);	 Catch:{ all -> 0x0079 }
        r1 = d;	 Catch:{ all -> 0x0079 }
        r1.put(r3, r0);	 Catch:{ all -> 0x0079 }
    L_0x0092:
        monitor-exit(r5);	 Catch:{ all -> 0x0079 }
        r0 = b;
        r1 = new android.support.v4.provider.d;
        r1.<init>(r3);
        r0.postAndReply(r4, r1);
        goto L_0x002c;
    L_0x009e:
        r0 = move-exception;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontSync(android.content.Context, android.support.v4.provider.FontRequest, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, int, int):android.graphics.Typeface");
    }

    public static void requestFont(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        handler.post(new e(context, fontRequest, new Handler(), fontRequestCallback));
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    @RequiresApi(19)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        Map hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(0, a(context, fontRequest, provider.authority, cancellationSignal));
    }

    @VisibleForTesting
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Nullable
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List a = a(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(a, e);
            List a2 = a(fontRequest, resources);
            for (int i = 0; i < a2.size(); i++) {
                List arrayList = new ArrayList((Collection) a2.get(i));
                Collections.sort(arrayList, e);
                if (a(a, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    private static List<List<byte[]>> a(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean a(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals((byte[]) list.get(i), (byte[]) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> a(Signature[] signatureArr) {
        List<byte[]> arrayList = new ArrayList();
        for (Signature toByteArray : signatureArr) {
            arrayList.add(toByteArray.toByteArray());
        }
        return arrayList;
    }

    @VisibleForTesting
    @NonNull
    static FontInfo[] a(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        Throwable th;
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Uri build = new Builder().scheme("content").authority(str).build();
        Uri build2 = new Builder().scheme("content").authority(str).appendPath(UriUtil.LOCAL_FILE_SCHEME).build();
        try {
            Cursor query;
            ArrayList arrayList2;
            if (VERSION.SDK_INT > 16) {
                query = context.getContentResolver().query(build, new String[]{FileDownloadModel.ID, Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null, cancellationSignal);
            } else {
                query = context.getContentResolver().query(build, new String[]{FileDownloadModel.ID, Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null);
            }
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        int columnIndex = query.getColumnIndex(Columns.RESULT_CODE);
                        arrayList2 = new ArrayList();
                        int columnIndex2 = query.getColumnIndex(FileDownloadModel.ID);
                        int columnIndex3 = query.getColumnIndex(Columns.FILE_ID);
                        int columnIndex4 = query.getColumnIndex(Columns.TTC_INDEX);
                        int columnIndex5 = query.getColumnIndex(Columns.WEIGHT);
                        int columnIndex6 = query.getColumnIndex(Columns.ITALIC);
                        while (query.moveToNext()) {
                            Uri withAppendedId;
                            int i = columnIndex != -1 ? query.getInt(columnIndex) : 0;
                            int i2 = columnIndex4 != -1 ? query.getInt(columnIndex4) : 0;
                            if (columnIndex3 == -1) {
                                withAppendedId = ContentUris.withAppendedId(build, query.getLong(columnIndex2));
                            } else {
                                withAppendedId = ContentUris.withAppendedId(build2, query.getLong(columnIndex3));
                            }
                            int i3 = columnIndex5 != -1 ? query.getInt(columnIndex5) : 400;
                            boolean z = columnIndex6 != -1 && query.getInt(columnIndex6) == 1;
                            arrayList2.add(new FontInfo(withAppendedId, i2, i3, z, i));
                        }
                        if (query != null) {
                            query.close();
                        }
                        return (FontInfo[]) arrayList2.toArray(new FontInfo[0]);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                }
            }
            arrayList2 = arrayList;
            if (query != null) {
                query.close();
            }
            return (FontInfo[]) arrayList2.toArray(new FontInfo[0]);
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
