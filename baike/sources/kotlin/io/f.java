package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import qsbk.app.database.QsbkDatabase;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 6}, xi = 1, xs = "kotlin/io/FilesKt")
class f extends e {
    @NotNull
    public static /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        return createTempDir(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (File) null : file);
    }

    @NotNull
    public static final File createTempDir(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        createTempFile.delete();
        if (createTempFile.mkdir()) {
            Intrinsics.checkExpressionValueIsNotNull(createTempFile, "dir");
            return createTempFile;
        }
        throw new IOException("Unable to create temporary directory " + createTempFile + ".");
    }

    @NotNull
    public static /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        return createTempFile(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (File) null : file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        Intrinsics.checkExpressionValueIsNotNull(createTempFile, "File.createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    @NotNull
    public static final String getExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return u.substringAfterLast(file.getName(), '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        if (File.separatorChar != '/') {
            return t.replace$default(file.getPath(), File.separatorChar, '/', false, 4, null);
        }
        String path = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        return path;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return u.substringBeforeLast$default(file.getName(), ".", null, 2, null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String a = a(file, file2);
        if (a != null) {
            return a;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + ".");
    }

    @NotNull
    public static final File relativeTo(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        return new File(toRelativeString(file, file2));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String a = a(file, file2);
        return a != null ? new File(a) : file;
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String a = a(file, file2);
        return a != null ? new File(a) : null;
    }

    private static final String a(@NotNull File file, File file2) {
        FilePathComponents a = a(b.toComponents(file));
        FilePathComponents a2 = a(b.toComponents(file2));
        if ((Intrinsics.areEqual(a.getRoot(), a2.getRoot()) ^ 1) != 0) {
            return null;
        }
        int size = a2.getSize();
        int size2 = a.getSize();
        int min = Math.min(size2, size);
        int i = 0;
        while (i < min && Intrinsics.areEqual((File) a.getSegments().get(i), (File) a2.getSegments().get(i))) {
            i++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = size - 1;
        if (i2 >= i) {
            int i3 = i2;
            while (!Intrinsics.areEqual(((File) a2.getSegments().get(i3)).getName(), "..")) {
                stringBuilder.append("..");
                if (i3 != i) {
                    stringBuilder.append(File.separatorChar);
                }
                if (i3 != i) {
                    i3--;
                }
            }
            return null;
        }
        if (i < size2) {
            if (i < size) {
                stringBuilder.append(File.separatorChar);
            }
            Iterable drop = v.drop(a.getSegments(), i);
            Appendable appendable = stringBuilder;
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            v.joinTo$default(drop, appendable, str, null, null, 0, null, null, 124, null);
        }
        return stringBuilder.toString();
    }

    @NotNull
    public static /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return copyTo(file, file2, z, i);
    }

    @NotNull
    public static final File copyTo(@NotNull File file, @NotNull File file2, boolean z, int i) {
        Throwable th;
        Object obj;
        Object obj2 = null;
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, QsbkDatabase.TARGET);
        if (file.exists()) {
            if (file2.exists()) {
                Object obj3;
                if (!z) {
                    obj3 = 1;
                } else if (file2.delete()) {
                    obj3 = null;
                } else {
                    int i2 = 1;
                }
                if (obj3 != null) {
                    throw new FileAlreadyExistsException(file, file2, "The destination file already exists.");
                }
            }
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                Closeable fileInputStream = new FileInputStream(file);
                try {
                    Closeable fileOutputStream = new FileOutputStream(file2);
                    try {
                        ByteStreamsKt.copyTo((FileInputStream) fileInputStream, (FileOutputStream) fileOutputStream, i);
                        fileOutputStream.close();
                        fileInputStream.close();
                    } catch (Exception e) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e2) {
                        }
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        obj = 1;
                        if (obj == null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e4) {
                    }
                    throw e3;
                } catch (Throwable th3) {
                    th = th3;
                    obj2 = 1;
                    if (obj2 == null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } else if (!file2.mkdirs()) {
                throw new FileSystemException(file, file2, "Failed to create target directory.");
            }
            return file2;
        }
        throw new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return copyRecursively(file, file2, z, (i & 4) != 0 ? g.INSTANCE : function2);
    }

    public static final boolean copyRecursively(@NotNull File file, @NotNull File file2, boolean z, @NotNull Function2<? super File, ? super IOException, ? extends OnErrorAction> function2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, QsbkDatabase.TARGET);
        Intrinsics.checkParameterIsNotNull(function2, "onError");
        if (file.exists()) {
            try {
                Iterator it = e.walkTopDown(file).onFail(new h(function2)).iterator();
                while (it.hasNext()) {
                    File file3 = (File) it.next();
                    if (file3.exists()) {
                        File file4 = new File(file2, toRelativeString(file3, file));
                        if (file4.exists() && !(file3.isDirectory() && file4.isDirectory())) {
                            boolean z2 = !z ? true : file4.isDirectory() ? !deleteRecursively(file4) : !file4.delete();
                            if (z2) {
                                if (Intrinsics.areEqual((OnErrorAction) function2.invoke(file4, new FileAlreadyExistsException(file3, file4, "The destination file already exists.")), OnErrorAction.TERMINATE)) {
                                    return false;
                                }
                            }
                        }
                        if (file3.isDirectory()) {
                            file4.mkdirs();
                        } else if (copyTo$default(file3, file4, z, 0, 4, null).length() != file3.length() && Intrinsics.areEqual((OnErrorAction) function2.invoke(file3, new IOException("Source file wasn't copied completely, length of destination file differs.")), OnErrorAction.TERMINATE)) {
                            return false;
                        }
                    } else if (Intrinsics.areEqual((OnErrorAction) function2.invoke(file3, new NoSuchFileException(file3, null, "The source file doesn't exist.", 2, null)), OnErrorAction.TERMINATE)) {
                        return false;
                    }
                }
                return true;
            } catch (j e) {
                return false;
            }
        }
        return Intrinsics.areEqual((OnErrorAction) function2.invoke(file, new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null)), OnErrorAction.TERMINATE) ^ 1;
    }

    public static final boolean deleteRecursively(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        boolean z = true;
        for (File file2 : e.walkBottomUp(file)) {
            boolean z2 = (file2.delete() || !file2.exists()) && z;
            z = z2;
        }
        return z;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        FilePathComponents toComponents = b.toComponents(file);
        FilePathComponents toComponents2 = b.toComponents(file2);
        if ((Intrinsics.areEqual(toComponents.getRoot(), toComponents2.getRoot()) ^ 1) == 0 && toComponents.getSize() >= toComponents2.getSize()) {
            return toComponents.getSegments().subList(0, toComponents2.getSize()).equals(toComponents2.getSegments());
        }
        return false;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return startsWith(file, new File(str));
    }

    public static final boolean endsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        FilePathComponents toComponents = b.toComponents(file);
        FilePathComponents toComponents2 = b.toComponents(file2);
        if (toComponents2.isRooted()) {
            return Intrinsics.areEqual(file, file2);
        }
        int size = toComponents.getSize() - toComponents2.getSize();
        if (size < 0) {
            return false;
        }
        return toComponents.getSegments().subList(size, toComponents.getSize()).equals(toComponents2.getSegments());
    }

    public static final boolean endsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return endsWith(file, new File(str));
    }

    @NotNull
    public static final File normalize(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        FilePathComponents toComponents = b.toComponents(file);
        File root = toComponents.getRoot();
        Iterable a = a(toComponents.getSegments());
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        return resolve(root, v.joinToString$default(a, str, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents a(@NotNull FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.getRoot(), a(filePathComponents.getSegments()));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.List<java.io.File> a(@org.jetbrains.annotations.NotNull java.util.List<? extends java.io.File> r5) {
        /*
        r0 = new java.util.ArrayList;
        r1 = r5.size();
        r0.<init>(r1);
        r0 = (java.util.List) r0;
        r3 = r5.iterator();
    L_0x000f:
        r1 = r3.hasNext();
        if (r1 == 0) goto L_0x0066;
    L_0x0015:
        r1 = r3.next();
        r1 = (java.io.File) r1;
        r2 = r1.getName();
        if (r2 != 0) goto L_0x0025;
    L_0x0021:
        r0.add(r1);
        goto L_0x000f;
    L_0x0025:
        r4 = r2.hashCode();
        switch(r4) {
            case 46: goto L_0x002d;
            case 1472: goto L_0x0036;
            default: goto L_0x002c;
        };
    L_0x002c:
        goto L_0x0021;
    L_0x002d:
        r4 = ".";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x0021;
    L_0x0035:
        goto L_0x000f;
    L_0x0036:
        r4 = "..";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x0021;
    L_0x003e:
        r2 = r0.isEmpty();
        if (r2 != 0) goto L_0x0062;
    L_0x0044:
        r2 = kotlin.collections.v.last(r0);
        r2 = (java.io.File) r2;
        r2 = r2.getName();
        r4 = "..";
        r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4);
        r2 = r2 ^ 1;
        if (r2 == 0) goto L_0x0062;
    L_0x0058:
        r1 = r0.size();
        r1 = r1 + -1;
        r0.remove(r1);
        goto L_0x000f;
    L_0x0062:
        r0.add(r1);
        goto L_0x000f;
    L_0x0066:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.f.a(java.util.List):java.util.List<java.io.File>");
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "relative");
        if (b.isRooted(file2)) {
            return file2;
        }
        String file3 = file.toString();
        File file4 = ((((CharSequence) file3).length() == 0) || u.endsWith$default((CharSequence) file3, File.separatorChar, false, 2, null)) ? new File(file3 + file2) : new File(file3 + File.separatorChar + file2);
        return file4;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "relative");
        return resolve(file, new File(str));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "relative");
        FilePathComponents toComponents = b.toComponents(file);
        return resolve(resolve(toComponents.getRoot(), toComponents.getSize() == 0 ? new File("..") : toComponents.subPath(0, toComponents.getSize() - 1)), file2);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "relative");
        return resolveSibling(file, new File(str));
    }
}
