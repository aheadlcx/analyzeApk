package com.xiaomi.push.log;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.misc.h;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class f implements LoggerInterface {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aaa");
    private static h b = new h(true);
    private static String c = "/MiPushLog";
    private static List<Pair<String, Throwable>> f = Collections.synchronizedList(new ArrayList());
    private String d;
    private Context e;

    public f(Context context) {
        this.e = context;
        if (context.getApplicationContext() != null) {
            this.e = context.getApplicationContext();
        }
        this.d = this.e.getPackageName();
    }

    private void b() {
        RandomAccessFile randomAccessFile;
        FileLock lock;
        String str;
        Throwable e;
        FileLock fileLock;
        RandomAccessFile randomAccessFile2;
        String str2;
        BufferedWriter bufferedWriter = null;
        RandomAccessFile randomAccessFile3 = null;
        FileLock fileLock2 = null;
        BufferedWriter bufferedWriter2 = null;
        BufferedWriter bufferedWriter3;
        try {
            File file = new File(this.e.getExternalFilesDir(null) + c);
            if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
                File file2 = new File(file, "log.lock");
                if (!file2.exists() || file2.isDirectory()) {
                    file2.createNewFile();
                }
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    lock = randomAccessFile.getChannel().lock();
                    try {
                        bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file, "log1.txt"), true)));
                        while (!f.isEmpty()) {
                            try {
                                Pair pair = (Pair) f.remove(0);
                                str = (String) pair.first;
                                if (pair.second != null) {
                                    str = (str + "\n") + Log.getStackTraceString((Throwable) pair.second);
                                }
                                bufferedWriter2.write(str + "\n");
                            } catch (Exception e2) {
                                e = e2;
                                bufferedWriter3 = bufferedWriter2;
                                fileLock = lock;
                                randomAccessFile2 = randomAccessFile;
                            } catch (Throwable th) {
                                e = th;
                                bufferedWriter = bufferedWriter2;
                            }
                        }
                        bufferedWriter2.flush();
                        if (bufferedWriter2 != null) {
                            bufferedWriter2.close();
                            bufferedWriter3 = bufferedWriter;
                        } else {
                            bufferedWriter3 = bufferedWriter2;
                        }
                        try {
                            file2 = new File(file, "log1.txt");
                            if (file2.length() >= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                                File file3 = new File(file, "log0.txt");
                                if (file3.exists() && file3.isFile()) {
                                    file3.delete();
                                }
                                file2.renameTo(file3);
                            }
                            if (bufferedWriter3 != null) {
                                try {
                                    bufferedWriter3.close();
                                } catch (Throwable e3) {
                                    Log.e(this.d, "", e3);
                                }
                            }
                            if (lock != null && lock.isValid()) {
                                try {
                                    lock.release();
                                } catch (Throwable e32) {
                                    Log.e(this.d, "", e32);
                                }
                            }
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                    return;
                                } catch (IOException e4) {
                                    e32 = e4;
                                    str = this.d;
                                    str2 = "";
                                    Log.e(str, str2, e32);
                                    return;
                                }
                            }
                            return;
                        } catch (Exception e5) {
                            e32 = e5;
                            fileLock = lock;
                            randomAccessFile2 = randomAccessFile;
                            try {
                                Log.e(this.d, "", e32);
                                if (bufferedWriter3 != null) {
                                    try {
                                        bufferedWriter3.close();
                                    } catch (Throwable e322) {
                                        Log.e(this.d, "", e322);
                                    }
                                }
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (Throwable e3222) {
                                        Log.e(this.d, "", e3222);
                                    }
                                }
                                if (randomAccessFile2 == null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (IOException e6) {
                                        e3222 = e6;
                                        str = this.d;
                                        str2 = "";
                                        Log.e(str, str2, e3222);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                e3222 = th2;
                                lock = fileLock;
                                randomAccessFile = randomAccessFile2;
                                bufferedWriter = bufferedWriter3;
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (Throwable e7) {
                                        Log.e(this.d, "", e7);
                                    }
                                }
                                try {
                                    lock.release();
                                } catch (Throwable e72) {
                                    Log.e(this.d, "", e72);
                                }
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable e722) {
                                        Log.e(this.d, "", e722);
                                    }
                                }
                                throw e3222;
                            }
                        } catch (Throwable th3) {
                            e3222 = th3;
                            bufferedWriter = bufferedWriter3;
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                            if (lock != null && lock.isValid()) {
                                lock.release();
                            }
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            throw e3222;
                        }
                    } catch (Exception e8) {
                        e3222 = e8;
                        bufferedWriter3 = bufferedWriter;
                        randomAccessFile2 = randomAccessFile;
                        fileLock = lock;
                        Log.e(this.d, "", e3222);
                        if (bufferedWriter3 != null) {
                            bufferedWriter3.close();
                        }
                        fileLock.release();
                        if (randomAccessFile2 == null) {
                            randomAccessFile2.close();
                        }
                    } catch (Throwable th4) {
                        e3222 = th4;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        lock.release();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw e3222;
                    }
                } catch (Exception e9) {
                    e3222 = e9;
                    bufferedWriter3 = bufferedWriter;
                    randomAccessFile2 = randomAccessFile;
                    Log.e(this.d, "", e3222);
                    if (bufferedWriter3 != null) {
                        bufferedWriter3.close();
                    }
                    fileLock.release();
                    if (randomAccessFile2 == null) {
                        randomAccessFile2.close();
                    }
                } catch (Throwable th5) {
                    e3222 = th5;
                    Object obj = bufferedWriter;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    lock.release();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw e3222;
                }
            }
            Log.w(this.d, "Create mipushlog directory fail.");
            if (bufferedWriter != null) {
                try {
                    bufferedWriter2.close();
                } catch (Throwable e10) {
                    Log.e(this.d, "", e10);
                }
            }
            if (bufferedWriter != null && bufferedWriter.isValid()) {
                try {
                    fileLock2.release();
                } catch (Throwable e7222) {
                    Log.e(this.d, "", e7222);
                }
            }
            if (bufferedWriter != null) {
                try {
                    randomAccessFile3.close();
                } catch (IOException e11) {
                    e3222 = e11;
                    str = this.d;
                    str2 = "";
                }
            }
        } catch (Exception e12) {
            e3222 = e12;
            bufferedWriter3 = bufferedWriter;
            Object obj2 = bufferedWriter;
            Log.e(this.d, "", e3222);
            if (bufferedWriter3 != null) {
                bufferedWriter3.close();
            }
            fileLock.release();
            if (randomAccessFile2 == null) {
                randomAccessFile2.close();
            }
        } catch (Throwable th6) {
            e3222 = th6;
            lock = bufferedWriter;
            randomAccessFile = bufferedWriter;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            lock.release();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw e3222;
        }
    }

    public final void log(String str) {
        log(str, null);
    }

    public final void log(String str, Throwable th) {
        f.add(new Pair(String.format("%1$s %2$s %3$s ", new Object[]{a.format(new Date()), this.d, str}), th));
        b.a(new g(this));
    }

    public final void setTag(String str) {
        this.d = str;
    }
}
