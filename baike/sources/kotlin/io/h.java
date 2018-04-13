package kotlin.io;

import com.baidu.mobstat.Config;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import qsbk.app.core.model.User;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "f", "Ljava/io/File;", "e", "Ljava/io/IOException;", "invoke"}, k = 3, mv = {1, 1, 6})
final class h extends Lambda implements Function2<File, IOException, Unit> {
    final /* synthetic */ Function2 a;

    h(Function2 function2) {
        this.a = function2;
        super(2);
    }

    public final void invoke(@NotNull File file, @NotNull IOException iOException) {
        Intrinsics.checkParameterIsNotNull(file, User.FEMALE);
        Intrinsics.checkParameterIsNotNull(iOException, Config.SESSTION_END_TIME);
        if (Intrinsics.areEqual((OnErrorAction) this.a.invoke(file, iOException), OnErrorAction.TERMINATE)) {
            throw new j(file);
        }
    }
}
