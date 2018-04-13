package kotlin.concurrent;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"kotlin/concurrent/ThreadsKt$thread$thread$1", "Ljava/lang/Thread;", "(Lkotlin/jvm/functions/Function0;)V", "run", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class ThreadsKt$thread$thread$1 extends Thread {
    final /* synthetic */ Function0 a;

    ThreadsKt$thread$thread$1(Function0 function0) {
        this.a = function0;
    }

    public void run() {
        this.a.invoke();
    }
}
