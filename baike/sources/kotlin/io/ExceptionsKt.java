package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0002¨\u0006\u0006"}, d2 = {"constructMessage", "", "file", "Ljava/io/File;", "other", "reason", "kotlin-stdlib"}, k = 2, mv = {1, 1, 6})
public final class ExceptionsKt {
    private static final String a(File file, File file2, String str) {
        StringBuilder stringBuilder = new StringBuilder(file.toString());
        if (file2 != null) {
            stringBuilder.append(" -> " + file2);
        }
        if (str != null) {
            stringBuilder.append(": " + str);
        }
        String stringBuilder2 = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuilder2, "sb.toString()");
        return stringBuilder2;
    }
}
