package kotlin.io;

import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lkotlin/io/FileSystemException;", "Ljava/io/IOException;", "file", "Ljava/io/File;", "other", "reason", "", "(Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V", "getFile", "()Ljava/io/File;", "getOther", "getReason", "()Ljava/lang/String;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public class FileSystemException extends IOException {
    @NotNull
    private final File a;
    @Nullable
    private final File b;
    @Nullable
    private final String c;

    public FileSystemException(@NotNull File file, @Nullable File file2, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(file, UriUtil.LOCAL_FILE_SCHEME);
        super(ExceptionsKt.a(file, file2, str));
        this.a = file;
        this.b = file2;
        this.c = str;
    }

    @NotNull
    public final File getFile() {
        return this.a;
    }

    public /* synthetic */ FileSystemException(File file, File file2, String str, int i, j jVar) {
        File file3;
        if ((i & 2) != 0) {
            file3 = (File) null;
        } else {
            file3 = file2;
        }
        this(file, file3, (i & 4) != 0 ? (String) null : str);
    }

    @Nullable
    public final File getOther() {
        return this.b;
    }

    @Nullable
    public final String getReason() {
        return this.c;
    }
}
