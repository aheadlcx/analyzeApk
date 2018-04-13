package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.j;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001a\u001b\u001cB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0001\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00128\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0002J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0014J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bJ \u0010\f\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\rJ\u001a\u0010\n\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R@\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class FileTreeWalk implements Sequence<File> {
    private final File a;
    private final FileWalkDirection b;
    private final Function1<File, Boolean> c;
    private final Function1<File, Unit> d;
    private final Function2<File, IOException, Unit> e;
    private final int f;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
    private static abstract class c {
        @NotNull
        private final File a;

        @Nullable
        public abstract File step();

        public c(@NotNull File file) {
            Intrinsics.checkParameterIsNotNull(file, "root");
            this.a = file;
        }

        @NotNull
        public final File getRoot() {
            return this.a;
        }
    }

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
    private static abstract class a extends c {
        public a(@NotNull File file) {
            Intrinsics.checkParameterIsNotNull(file, "rootDir");
            super(file);
            if (_Assertions.ENABLED) {
                boolean isDirectory = file.isDirectory();
                if (_Assertions.ENABLED && !isDirectory) {
                    throw new AssertionError("rootDir must be verified to be directory beforehand.");
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\r\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/Stack;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
    private final class b extends AbstractIterator<File> {
        final /* synthetic */ FileTreeWalk a;
        private final Stack<c> b = new Stack();

        @Metadata(bv = {1, 0, 1}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
        private final class a extends a {
            final /* synthetic */ b a;
            private boolean b;
            private File[] c;
            private int d;
            private boolean e;

            public a(b bVar, @NotNull File file) {
                Intrinsics.checkParameterIsNotNull(file, "rootDir");
                this.a = bVar;
                super(file);
            }

            @Nullable
            public File step() {
                Function1 access$getOnEnter$p;
                if (!this.e && this.c == null) {
                    Object obj;
                    access$getOnEnter$p = this.a.a.c;
                    if (access$getOnEnter$p != null) {
                        obj = (Boolean) access$getOnEnter$p.invoke(getRoot());
                    } else {
                        obj = null;
                    }
                    if (Intrinsics.areEqual(obj, Boolean.valueOf(false))) {
                        return null;
                    }
                    this.c = getRoot().listFiles();
                    if (this.c == null) {
                        Function2 access$getOnFail$p = this.a.a.e;
                        if (access$getOnFail$p != null) {
                            Unit unit = (Unit) access$getOnFail$p.invoke(getRoot(), new AccessDeniedException(getRoot(), null, "Cannot list files in a directory", 2, null));
                        }
                        this.e = true;
                    }
                }
                if (this.c != null) {
                    int i = this.d;
                    File[] fileArr = this.c;
                    if (fileArr == null) {
                        Intrinsics.throwNpe();
                    }
                    if (i < ((Object[]) fileArr).length) {
                        fileArr = this.c;
                        if (fileArr == null) {
                            Intrinsics.throwNpe();
                        }
                        i = this.d;
                        this.d = i + 1;
                        return fileArr[i];
                    }
                }
                if (this.b) {
                    access$getOnEnter$p = this.a.a.d;
                    if (access$getOnEnter$p == null) {
                        return null;
                    }
                    unit = (Unit) access$getOnEnter$p.invoke(getRoot());
                    return null;
                }
                this.b = true;
                return getRoot();
            }
        }

        @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
        private final class b extends c {
            final /* synthetic */ b a;
            private boolean b;

            public b(b bVar, @NotNull File file) {
                Intrinsics.checkParameterIsNotNull(file, "rootFile");
                this.a = bVar;
                super(file);
                if (_Assertions.ENABLED) {
                    boolean isFile = file.isFile();
                    if (_Assertions.ENABLED && !isFile) {
                        throw new AssertionError("rootFile must be verified to be file beforehand.");
                    }
                }
            }

            @Nullable
            public File step() {
                if (this.b) {
                    return null;
                }
                this.b = true;
                return getRoot();
            }
        }

        @Metadata(bv = {1, 0, 1}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
        private final class c extends a {
            final /* synthetic */ b a;
            private boolean b;
            private File[] c;
            private int d;

            public c(b bVar, @NotNull File file) {
                Intrinsics.checkParameterIsNotNull(file, "rootDir");
                this.a = bVar;
                super(file);
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            @org.jetbrains.annotations.Nullable
            public java.io.File step() {
                /*
                r8 = this;
                r2 = 0;
                r0 = r8.b;
                if (r0 != 0) goto L_0x002f;
            L_0x0005:
                r0 = r8.a;
                r0 = r0.a;
                r0 = r0.c;
                if (r0 == 0) goto L_0x0025;
            L_0x000f:
                r1 = r8.getRoot();
                r0 = r0.invoke(r1);
                r0 = (java.lang.Boolean) r0;
            L_0x0019:
                r1 = 0;
                r1 = java.lang.Boolean.valueOf(r1);
                r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
                if (r0 == 0) goto L_0x0027;
            L_0x0024:
                return r2;
            L_0x0025:
                r0 = r2;
                goto L_0x0019;
            L_0x0027:
                r0 = 1;
                r8.b = r0;
                r2 = r8.getRoot();
                goto L_0x0024;
            L_0x002f:
                r0 = r8.c;
                if (r0 == 0) goto L_0x0041;
            L_0x0033:
                r1 = r8.d;
                r0 = r8.c;
                if (r0 != 0) goto L_0x003c;
            L_0x0039:
                kotlin.jvm.internal.Intrinsics.throwNpe();
            L_0x003c:
                r0 = (java.lang.Object[]) r0;
                r0 = r0.length;
                if (r1 >= r0) goto L_0x00aa;
            L_0x0041:
                r0 = r8.c;
                if (r0 != 0) goto L_0x0099;
            L_0x0045:
                r0 = r8.getRoot();
                r0 = r0.listFiles();
                r8.c = r0;
                r0 = r8.c;
                if (r0 != 0) goto L_0x0074;
            L_0x0053:
                r0 = r8.a;
                r0 = r0.a;
                r6 = r0.e;
                if (r6 == 0) goto L_0x0074;
            L_0x005d:
                r7 = r8.getRoot();
                r0 = new kotlin.io.AccessDeniedException;
                r1 = r8.getRoot();
                r3 = "Cannot list files in a directory";
                r4 = 2;
                r5 = r2;
                r0.<init>(r1, r2, r3, r4, r5);
                r0 = r6.invoke(r7, r0);
                r0 = (kotlin.Unit) r0;
            L_0x0074:
                r0 = r8.c;
                if (r0 == 0) goto L_0x0084;
            L_0x0078:
                r0 = r8.c;
                if (r0 != 0) goto L_0x007f;
            L_0x007c:
                kotlin.jvm.internal.Intrinsics.throwNpe();
            L_0x007f:
                r0 = (java.lang.Object[]) r0;
                r0 = r0.length;
                if (r0 != 0) goto L_0x0099;
            L_0x0084:
                r0 = r8.a;
                r0 = r0.a;
                r0 = r0.d;
                if (r0 == 0) goto L_0x0024;
            L_0x008e:
                r1 = r8.getRoot();
                r0 = r0.invoke(r1);
                r0 = (kotlin.Unit) r0;
                goto L_0x0024;
            L_0x0099:
                r0 = r8.c;
                if (r0 != 0) goto L_0x00a0;
            L_0x009d:
                kotlin.jvm.internal.Intrinsics.throwNpe();
            L_0x00a0:
                r1 = r8.d;
                r2 = r1 + 1;
                r8.d = r2;
                r2 = r0[r1];
                goto L_0x0024;
            L_0x00aa:
                r0 = r8.a;
                r0 = r0.a;
                r0 = r0.d;
                if (r0 == 0) goto L_0x0024;
            L_0x00b4:
                r1 = r8.getRoot();
                r0 = r0.invoke(r1);
                r0 = (kotlin.Unit) r0;
                goto L_0x0024;
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FileTreeWalk.b.c.step():java.io.File");
            }
        }

        public b(FileTreeWalk fileTreeWalk) {
            this.a = fileTreeWalk;
            if (fileTreeWalk.a.isDirectory()) {
                this.b.push(a(fileTreeWalk.a));
            } else if (fileTreeWalk.a.isFile()) {
                this.b.push(new b(this, fileTreeWalk.a));
            } else {
                b();
            }
        }

        protected void a() {
            File c = c();
            if (c != null) {
                a(c);
            } else {
                b();
            }
        }

        private final a a(File file) {
            switch (FileTreeWalk$FileTreeWalkIterator$WhenMappings.$EnumSwitchMapping$0[this.a.b.ordinal()]) {
                case 1:
                    return new c(this, file);
                case 2:
                    return new a(this, file);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        private final File c() {
            while (!this.b.empty()) {
                Object peek = this.b.peek();
                if (peek == null) {
                    Intrinsics.throwNpe();
                }
                c cVar = (c) peek;
                File step = cVar.step();
                if (step == null) {
                    this.b.pop();
                } else if (Intrinsics.areEqual(step, cVar.getRoot()) || !step.isDirectory() || this.b.size() >= this.a.f) {
                    return step;
                } else {
                    this.b.push(a(step));
                }
            }
            return null;
        }
    }

    private FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1<? super File, Boolean> function1, Function1<? super File, Unit> function12, Function2<? super File, ? super IOException, Unit> function2, int i) {
        this.a = file;
        this.b = fileWalkDirection;
        this.c = function1;
        this.d = function12;
        this.e = function2;
        this.f = i;
    }

    /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1 function1, Function1 function12, Function2 function2, int i, int i2, j jVar) {
        FileWalkDirection fileWalkDirection2;
        if ((i2 & 2) != 0) {
            fileWalkDirection2 = FileWalkDirection.TOP_DOWN;
        } else {
            fileWalkDirection2 = fileWalkDirection;
        }
        this(file, fileWalkDirection2, function1, function12, function2, (i2 & 32) != 0 ? Integer.MAX_VALUE : i);
    }

    public FileTreeWalk(@NotNull File file, @NotNull FileWalkDirection fileWalkDirection) {
        Intrinsics.checkParameterIsNotNull(file, "start");
        Intrinsics.checkParameterIsNotNull(fileWalkDirection, "direction");
        this(file, fileWalkDirection, null, null, null, 0, 32, null);
    }

    public /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, int i, j jVar) {
        if ((i & 2) != 0) {
            fileWalkDirection = FileWalkDirection.TOP_DOWN;
        }
        this(file, fileWalkDirection);
    }

    @NotNull
    public Iterator<File> iterator() {
        return new b(this);
    }

    @NotNull
    public final FileTreeWalk onEnter(@NotNull Function1<? super File, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "function");
        return new FileTreeWalk(this.a, this.b, function1, this.d, this.e, this.f);
    }

    @NotNull
    public final FileTreeWalk onLeave(@NotNull Function1<? super File, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "function");
        return new FileTreeWalk(this.a, this.b, this.c, function1, this.e, this.f);
    }

    @NotNull
    public final FileTreeWalk onFail(@NotNull Function2<? super File, ? super IOException, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "function");
        return new FileTreeWalk(this.a, this.b, this.c, this.d, function2, this.f);
    }

    @NotNull
    public final FileTreeWalk maxDepth(int i) {
        if (i > 0) {
            return new FileTreeWalk(this.a, this.b, this.c, this.d, this.e, i);
        }
        throw new IllegalArgumentException("depth must be positive, but was " + i + ".");
    }
}
