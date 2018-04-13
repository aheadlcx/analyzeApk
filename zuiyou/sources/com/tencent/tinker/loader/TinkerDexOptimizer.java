package com.tencent.tinker.loader;

import android.os.Build.VERSION;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareFileLockHelper;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class TinkerDexOptimizer {
    private static final String INTERPRET_LOCK_FILE_NAME = "interpret.lock";
    private static final String TAG = "Tinker.ParallelDex";

    public interface ResultCallback {
        void onFailed(File file, File file2, Throwable th);

        void onStart(File file, File file2);

        void onSuccess(File file, File file2, File file3);
    }

    private static class OptimizeWorker {
        private static String a = null;
        private final File b;
        private final File c;
        private final boolean d;
        private final ResultCallback e;

        OptimizeWorker(File file, File file2, boolean z, String str, ResultCallback resultCallback) {
            this.b = file;
            this.c = file2;
            this.d = z;
            this.e = resultCallback;
            a = str;
        }

        public boolean a() {
            try {
                if (SharePatchFileUtil.isLegalFile(this.b) || this.e == null) {
                    if (this.e != null) {
                        this.e.onStart(this.b, this.c);
                    }
                    String optimizedPathFor = SharePatchFileUtil.optimizedPathFor(this.b, this.c);
                    if (this.d) {
                        a(this.b.getAbsolutePath(), optimizedPathFor);
                    } else {
                        DexFile.loadDex(this.b.getAbsolutePath(), optimizedPathFor, 0);
                    }
                    if (this.e != null) {
                        this.e.onSuccess(this.b, this.c, new File(optimizedPathFor));
                    }
                    return true;
                }
                this.e.onFailed(this.b, this.c, new IOException("dex file " + this.b.getAbsolutePath() + " is not exist!"));
                return false;
            } catch (Throwable th) {
                Log.e(TinkerDexOptimizer.TAG, "Failed to optimize dex: " + this.b.getAbsolutePath(), th);
                if (this.e != null) {
                    this.e.onFailed(this.b, this.c, th);
                    return false;
                }
            }
        }

        private void a(String str, String str2) throws IOException {
            File file = new File(str2);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            ShareFileLockHelper shareFileLockHelper = null;
            try {
                shareFileLockHelper = ShareFileLockHelper.getFileLock(new File(file.getParentFile(), TinkerDexOptimizer.INTERPRET_LOCK_FILE_NAME));
                List arrayList = new ArrayList();
                arrayList.add("dex2oat");
                if (VERSION.SDK_INT >= 24) {
                    arrayList.add("--runtime-arg");
                    arrayList.add("-classpath");
                    arrayList.add("--runtime-arg");
                    arrayList.add("&");
                }
                arrayList.add("--dex-file=" + str);
                arrayList.add("--oat-file=" + str2);
                arrayList.add("--instruction-set=" + a);
                if (VERSION.SDK_INT > 25) {
                    arrayList.add("--compiler-filter=quicken");
                } else {
                    arrayList.add("--compiler-filter=interpret-only");
                }
                ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
                processBuilder.redirectErrorStream(true);
                Process start = processBuilder.start();
                StreamConsumer.a(start.getInputStream());
                StreamConsumer.a(start.getErrorStream());
                int waitFor = start.waitFor();
                if (waitFor != 0) {
                    throw new IOException("dex2oat works unsuccessfully, exit code: " + waitFor);
                } else if (shareFileLockHelper != null) {
                    try {
                        shareFileLockHelper.close();
                    } catch (Throwable e) {
                        Log.w(TinkerDexOptimizer.TAG, "release interpret Lock error", e);
                    }
                }
            } catch (Throwable e2) {
                throw new IOException("dex2oat is interrupted, msg: " + e2.getMessage(), e2);
            } catch (Throwable th) {
                if (shareFileLockHelper != null) {
                    try {
                        shareFileLockHelper.close();
                    } catch (Throwable e3) {
                        Log.w(TinkerDexOptimizer.TAG, "release interpret Lock error", e3);
                    }
                }
            }
        }
    }

    private static class StreamConsumer {
        static final Executor a = Executors.newSingleThreadExecutor();

        private StreamConsumer() {
        }

        static void a(final InputStream inputStream) {
            a.execute(new Runnable() {
                public void run() {
                    if (inputStream != null) {
                        while (true) {
                            try {
                                if (inputStream.read(new byte[256]) <= 0) {
                                    try {
                                        inputStream.close();
                                        return;
                                    } catch (Exception e) {
                                        return;
                                    }
                                }
                            } catch (IOException e2) {
                                try {
                                    inputStream.close();
                                    return;
                                } catch (Exception e3) {
                                    return;
                                }
                            } catch (Throwable th) {
                                try {
                                    inputStream.close();
                                } catch (Exception e4) {
                                }
                                throw th;
                            }
                        }
                    }
                }
            });
        }
    }

    public static boolean optimizeAll(Collection<File> collection, File file, ResultCallback resultCallback) {
        return optimizeAll(collection, file, false, null, resultCallback);
    }

    public static boolean optimizeAll(Collection<File> collection, File file, boolean z, String str, ResultCallback resultCallback) {
        Object arrayList = new ArrayList(collection);
        Collections.sort(arrayList, new Comparator<File>() {
            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((File) obj, (File) obj2);
            }

            public int a(File file, File file2) {
                long length = file.length() - file2.length();
                if (length > 0) {
                    return 1;
                }
                if (length == 0) {
                    return 0;
                }
                return -1;
            }
        });
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!new OptimizeWorker((File) it.next(), file, z, str, resultCallback).a()) {
                return false;
            }
        }
        return true;
    }
}
