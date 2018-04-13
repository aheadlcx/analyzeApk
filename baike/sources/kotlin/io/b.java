package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 6}, xi = 1, xs = "kotlin/io/FilesKt")
class b {
    private static final int a(@NotNull String str) {
        int indexOf$default = u.indexOf$default((CharSequence) str, File.separatorChar, 0, false, 4, null);
        if (indexOf$default == 0) {
            if (str.length() > 1 && str.charAt(1) == File.separatorChar) {
                indexOf$default = u.indexOf$default((CharSequence) str, File.separatorChar, 2, false, 4, null);
                if (indexOf$default >= 0) {
                    indexOf$default = u.indexOf$default((CharSequence) str, File.separatorChar, indexOf$default + 1, false, 4, null);
                    if (indexOf$default >= 0) {
                        return indexOf$default + 1;
                    }
                    return str.length();
                }
            }
            return 1;
        } else if (indexOf$default > 0 && str.charAt(indexOf$default - 1) == ':') {
            return indexOf$default + 1;
        } else {
            if (indexOf$default == -1 && u.endsWith$default((CharSequence) str, ':', false, 2, null)) {
                return str.length();
            }
            return 0;
        }
    }

    @NotNull
    public static final String getRootName(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        String path = file.getPath();
        int a = a(file.getPath());
        if (path == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        path = path.substring(0, a);
        Intrinsics.checkExpressionValueIsNotNull(path, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return path;
    }

    @NotNull
    public static final File getRoot(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return new File(getRootName(file));
    }

    public static final boolean isRooted(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return a(file.getPath()) > 0;
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        String path = file.getPath();
        int a = a(path);
        if (path == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = path.substring(0, a);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (path == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        List emptyList;
        String substring2 = path.substring(a);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        if (substring2.length() == 0) {
            int i = 1;
        } else {
            boolean z = false;
        }
        if (i != 0) {
            emptyList = q.emptyList();
        } else {
            Iterable<String> split$default = u.split$default((CharSequence) substring2, new char[]{File.separatorChar}, false, 0, 6, null);
            Collection arrayList = new ArrayList(r.collectionSizeOrDefault(split$default, 10));
            for (String path2 : split$default) {
                arrayList.add(new File(path2));
            }
            emptyList = (List) arrayList;
        }
        return new FilePathComponents(new File(substring), emptyList);
    }

    @NotNull
    public static final File subPath(@NotNull File file, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return toComponents(file).subPath(i, i2);
    }
}
