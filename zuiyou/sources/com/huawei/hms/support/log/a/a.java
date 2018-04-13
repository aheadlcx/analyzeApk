package com.huawei.hms.support.log.a;

import android.content.Context;
import android.util.Log;
import com.huawei.hms.support.log.c;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class a implements c {
    private File a;

    public static class a implements c {
        private final c a;
        private c b;
        private final Executor c = Executors.newSingleThreadExecutor();

        public a(c cVar) {
            this.a = cVar;
        }

        public void a(Context context, String str) {
            this.c.execute(new b(this, context, str));
            if (this.b != null) {
                this.b.a(context, str);
            }
        }

        public void a(String str, int i, String str2, String str3) {
            this.c.execute(new c(this, str, i, str2, str3));
            if (this.b != null) {
                this.b.a(str, i, str2, str3);
            }
        }
    }

    public void a(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            Log.e("FileLogNode", "Failed to initialize the file logger, parameter error.");
            return;
        }
        if (this.a == null) {
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                File file = new File(externalFilesDir, "Log");
                if (file.isDirectory() || file.mkdirs()) {
                    this.a = new File(file, str + ".log");
                    this.a.setReadable(true);
                    this.a.setWritable(true);
                    this.a.setExecutable(false, false);
                    return;
                }
            }
        }
        Log.e("FileLogNode", "Failed to initialize the file logger.");
    }

    public void a(String str, int i, String str2, String str3) {
        if (this.a != null && str != null) {
            String str4 = str + '\n';
            if (a(str4)) {
                b(str4);
            }
        }
    }

    private boolean a(String str) {
        if (this.a.length() + ((long) str.length()) > 524288) {
            if (!this.a.renameTo(new File(this.a.getPath() + ".bak"))) {
                Log.w("FileLogNode", "Failed to backup the log file.");
                return false;
            }
        }
        return true;
    }

    private void b(String str) {
        Closeable fileOutputStream;
        Closeable bufferedOutputStream;
        Closeable outputStreamWriter;
        Throwable th;
        Throwable th2;
        Closeable closeable = null;
        try {
            fileOutputStream = new FileOutputStream(this.a, true);
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                try {
                    outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
                    try {
                        outputStreamWriter.write(str);
                        outputStreamWriter.flush();
                        a(outputStreamWriter);
                        a(bufferedOutputStream);
                        a(fileOutputStream);
                    } catch (FileNotFoundException e) {
                        closeable = bufferedOutputStream;
                        bufferedOutputStream = fileOutputStream;
                        try {
                            Log.w("FileLogNode", "Exception when writing the log file.");
                            a(outputStreamWriter);
                            a(closeable);
                            a(bufferedOutputStream);
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = bufferedOutputStream;
                            bufferedOutputStream = closeable;
                            closeable = outputStreamWriter;
                            th2 = th;
                            a(closeable);
                            a(bufferedOutputStream);
                            a(fileOutputStream);
                            throw th2;
                        }
                    } catch (IOException e2) {
                        closeable = outputStreamWriter;
                        try {
                            Log.w("FileLogNode", "Exception when writing the log file.");
                            a(closeable);
                            a(bufferedOutputStream);
                            a(fileOutputStream);
                        } catch (Throwable th4) {
                            th2 = th4;
                            a(closeable);
                            a(bufferedOutputStream);
                            a(fileOutputStream);
                            throw th2;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        closeable = outputStreamWriter;
                        th2 = th;
                        a(closeable);
                        a(bufferedOutputStream);
                        a(fileOutputStream);
                        throw th2;
                    }
                } catch (FileNotFoundException e3) {
                    outputStreamWriter = null;
                    closeable = bufferedOutputStream;
                    bufferedOutputStream = fileOutputStream;
                    Log.w("FileLogNode", "Exception when writing the log file.");
                    a(outputStreamWriter);
                    a(closeable);
                    a(bufferedOutputStream);
                } catch (IOException e4) {
                    Log.w("FileLogNode", "Exception when writing the log file.");
                    a(closeable);
                    a(bufferedOutputStream);
                    a(fileOutputStream);
                }
            } catch (FileNotFoundException e5) {
                outputStreamWriter = null;
                bufferedOutputStream = fileOutputStream;
                Log.w("FileLogNode", "Exception when writing the log file.");
                a(outputStreamWriter);
                a(closeable);
                a(bufferedOutputStream);
            } catch (IOException e6) {
                bufferedOutputStream = null;
                Log.w("FileLogNode", "Exception when writing the log file.");
                a(closeable);
                a(bufferedOutputStream);
                a(fileOutputStream);
            } catch (Throwable th6) {
                th2 = th6;
                bufferedOutputStream = null;
                a(closeable);
                a(bufferedOutputStream);
                a(fileOutputStream);
                throw th2;
            }
        } catch (FileNotFoundException e7) {
            outputStreamWriter = null;
            bufferedOutputStream = null;
            Log.w("FileLogNode", "Exception when writing the log file.");
            a(outputStreamWriter);
            a(closeable);
            a(bufferedOutputStream);
        } catch (IOException e8) {
            bufferedOutputStream = null;
            fileOutputStream = null;
            Log.w("FileLogNode", "Exception when writing the log file.");
            a(closeable);
            a(bufferedOutputStream);
            a(fileOutputStream);
        } catch (Throwable th7) {
            th2 = th7;
            bufferedOutputStream = null;
            fileOutputStream = null;
            a(closeable);
            a(bufferedOutputStream);
            a(fileOutputStream);
            throw th2;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.w("FileLogNode", "Exception when closing the closeable.");
            }
        }
    }
}
