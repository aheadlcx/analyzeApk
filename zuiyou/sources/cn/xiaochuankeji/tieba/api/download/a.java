package cn.xiaochuankeji.tieba.api.download;

import cn.htjyb.c.a.b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ab;

public class a {
    public static boolean a(File file, ab abVar) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file2 = new File(file.getAbsolutePath() + ".tmp");
        boolean z = true;
        InputStream byteStream;
        try {
            fileOutputStream = new FileOutputStream(file2, false);
            try {
                byteStream = abVar.byteStream();
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = byteStream.read(bArr);
                        if (-1 == read) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    fileOutputStream = null;
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream2 = fileOutputStream;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (byteStream == null) {
                            try {
                                byteStream.close();
                                z = false;
                            } catch (IOException e32) {
                                e32.printStackTrace();
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        b.a(file2, file);
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (byteStream != null) {
                            try {
                                byteStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (byteStream != null) {
                        byteStream.close();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                byteStream = null;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (byteStream == null) {
                    z = false;
                } else {
                    byteStream.close();
                    z = false;
                }
                b.a(file2, file);
                return z;
            } catch (Throwable th4) {
                th = th4;
                byteStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream != null) {
                    byteStream.close();
                }
                throw th;
            }
            try {
                byteStream.close();
                byteStream = null;
                if (null != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                if (null != null) {
                    try {
                        byteStream.close();
                    } catch (IOException e52) {
                        e52.printStackTrace();
                    }
                }
            } catch (Exception e8) {
                e = e8;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (byteStream == null) {
                    byteStream.close();
                    z = false;
                } else {
                    z = false;
                }
                b.a(file2, file);
                return z;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream != null) {
                    byteStream.close();
                }
                throw th;
            }
        } catch (Exception e9) {
            e = e9;
            byteStream = null;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            if (byteStream == null) {
                z = false;
            } else {
                byteStream.close();
                z = false;
            }
            b.a(file2, file);
            return z;
        } catch (Throwable th6) {
            th = th6;
            byteStream = null;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (byteStream != null) {
                byteStream.close();
            }
            throw th;
        }
        if (z && !file2.renameTo(file)) {
            b.a(file2, file);
        }
        return z;
    }
}
