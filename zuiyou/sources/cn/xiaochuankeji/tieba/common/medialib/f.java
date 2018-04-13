package cn.xiaochuankeji.tieba.common.medialib;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class f {
    public static void a(Bitmap bitmap, CompressFormat compressFormat, int i, String str) {
        File file = new File(str);
        file.delete();
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(compressFormat, i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean a(String str, String str2) {
        return a(str, 0, str2);
    }

    public static boolean a(String str, int i, String str2) {
        Bitmap a = a(str, i);
        if (a == null) {
            return false;
        }
        a(a, CompressFormat.JPEG, 100, str2);
        return true;
    }

    public static Bitmap a(String str, int i) {
        FFmpegMediaMetadataRetriever fFmpegMediaMetadataRetriever;
        FFmpegMediaMetadataRetriever fFmpegMediaMetadataRetriever2;
        Exception exception;
        Throwable th;
        Bitmap bitmap = null;
        try {
            fFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
            try {
                fFmpegMediaMetadataRetriever.setDataSource(str);
                bitmap = fFmpegMediaMetadataRetriever.getFrameAtTime((long) (i * 1000), 3);
                if (fFmpegMediaMetadataRetriever != null) {
                    try {
                        fFmpegMediaMetadataRetriever.release();
                    } catch (Exception e) {
                    }
                }
            } catch (IllegalArgumentException e2) {
                if (fFmpegMediaMetadataRetriever != null) {
                    try {
                        fFmpegMediaMetadataRetriever.release();
                    } catch (Exception e3) {
                    }
                }
                return bitmap;
            } catch (RuntimeException e4) {
                if (fFmpegMediaMetadataRetriever != null) {
                    try {
                        fFmpegMediaMetadataRetriever.release();
                    } catch (Exception e5) {
                    }
                }
                return bitmap;
            } catch (Exception e6) {
                Exception exception2 = e6;
                fFmpegMediaMetadataRetriever2 = fFmpegMediaMetadataRetriever;
                exception = exception2;
                try {
                    exception.printStackTrace();
                    if (fFmpegMediaMetadataRetriever2 != null) {
                        try {
                            fFmpegMediaMetadataRetriever2.release();
                        } catch (Exception e7) {
                        }
                    }
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    fFmpegMediaMetadataRetriever = fFmpegMediaMetadataRetriever2;
                    if (fFmpegMediaMetadataRetriever != null) {
                        try {
                            fFmpegMediaMetadataRetriever.release();
                        } catch (Exception e8) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (fFmpegMediaMetadataRetriever != null) {
                    fFmpegMediaMetadataRetriever.release();
                }
                throw th;
            }
        } catch (IllegalArgumentException e9) {
            fFmpegMediaMetadataRetriever = bitmap;
            if (fFmpegMediaMetadataRetriever != null) {
                fFmpegMediaMetadataRetriever.release();
            }
            return bitmap;
        } catch (RuntimeException e10) {
            fFmpegMediaMetadataRetriever = bitmap;
            if (fFmpegMediaMetadataRetriever != null) {
                fFmpegMediaMetadataRetriever.release();
            }
            return bitmap;
        } catch (Exception e11) {
            exception = e11;
            fFmpegMediaMetadataRetriever2 = bitmap;
            exception.printStackTrace();
            if (fFmpegMediaMetadataRetriever2 != null) {
                fFmpegMediaMetadataRetriever2.release();
            }
            return bitmap;
        } catch (Throwable th4) {
            Throwable th5 = th4;
            fFmpegMediaMetadataRetriever = bitmap;
            th = th5;
            if (fFmpegMediaMetadataRetriever != null) {
                fFmpegMediaMetadataRetriever.release();
            }
            throw th;
        }
        return bitmap;
    }

    public static int a(String str) {
        Object b = b(str, FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        return Integer.parseInt(b);
    }

    public static int b(String str) {
        Object b = b(str, FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        return Integer.parseInt(b);
    }

    public static int c(String str) {
        Object b = b(str, "duration");
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        return Integer.parseInt(b);
    }

    public static String b(String str, String str2) {
        FFmpegMediaMetadataRetriever fFmpegMediaMetadataRetriever;
        Exception e;
        Throwable th;
        String str3 = null;
        if (new File(str).exists()) {
            try {
                fFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
                try {
                    fFmpegMediaMetadataRetriever.setDataSource(str);
                    str3 = fFmpegMediaMetadataRetriever.extractMetadata(str2);
                    if (fFmpegMediaMetadataRetriever != null) {
                        fFmpegMediaMetadataRetriever.release();
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (fFmpegMediaMetadataRetriever != null) {
                            fFmpegMediaMetadataRetriever.release();
                        }
                        return str3;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fFmpegMediaMetadataRetriever != null) {
                            fFmpegMediaMetadataRetriever.release();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fFmpegMediaMetadataRetriever = str3;
                e.printStackTrace();
                if (fFmpegMediaMetadataRetriever != null) {
                    fFmpegMediaMetadataRetriever.release();
                }
                return str3;
            } catch (Throwable th3) {
                fFmpegMediaMetadataRetriever = str3;
                th = th3;
                if (fFmpegMediaMetadataRetriever != null) {
                    fFmpegMediaMetadataRetriever.release();
                }
                throw th;
            }
        }
        return str3;
    }

    public static long a(long j, int i, int i2) {
        int i3 = i2 * 2;
        return ((((long) i3) - 1) ^ -1) & (((((long) i) * j) * ((long) i3)) / 1000);
    }
}
