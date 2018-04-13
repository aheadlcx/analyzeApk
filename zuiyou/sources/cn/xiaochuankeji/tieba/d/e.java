package cn.xiaochuankeji.tieba.d;

import com.izuiyou.a.a.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class e {
    private static final String a = e.class.getSimpleName();

    public static void a(String str, String str2) throws IOException {
        ZipOutputStream zipOutputStream;
        IOException e;
        Throwable th;
        try {
            File file = new File(str2);
            File file2 = new File(str);
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
            try {
                if (file2.isFile()) {
                    a(zipOutputStream, file2, "");
                } else {
                    File[] listFiles = file2.listFiles();
                    for (File file22 : listFiles) {
                        a(zipOutputStream, file22, "");
                    }
                }
                if (zipOutputStream != null) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        a.d(a, "zip exception " + e2.toString());
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    a.d(a, "zip exception " + e2.toString());
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                            a.d(a, "zip exception " + e22.toString());
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (zipOutputStream != null) {
                        try {
                            zipOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            a.d(a, "zip exception " + e4.toString());
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            zipOutputStream = null;
            e22.printStackTrace();
            a.d(a, "zip exception " + e22.toString());
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            zipOutputStream = null;
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            throw th;
        }
    }

    private static void a(ZipOutputStream zipOutputStream, File file, String str) throws IOException {
        IOException e;
        Throwable th;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2;
        try {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File a : listFiles) {
                    a(zipOutputStream, a, str + file.getName() + "/");
                }
                fileInputStream2 = null;
            } else {
                byte[] bArr = new byte[4096];
                fileInputStream2 = new FileInputStream(file);
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.closeEntry();
                } catch (IOException e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        } catch (IOException e5) {
            e32 = e5;
            fileInputStream2 = null;
            e32.printStackTrace();
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static boolean b(String str, String str2) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int i = 0;
            boolean z = true;
            while (i < listFiles.length) {
                if (listFiles[i].isFile() && !listFiles[i].getName().equalsIgnoreCase(str2)) {
                    z = a(listFiles[i].getAbsolutePath());
                    if (!z) {
                        break;
                    }
                } else if (listFiles[i].isDirectory()) {
                    z = b(listFiles[i].getAbsolutePath(), str2);
                    if (!z) {
                        break;
                    }
                } else {
                    continue;
                }
                i++;
            }
            if (!z) {
                a.a(a, (Object) "删除目录失败！");
                return false;
            } else if (!file.delete()) {
                return false;
            } else {
                a.a(a, "删除目录" + str + "成功！");
                return true;
            }
        }
        a.a(a, "删除目录失败：" + str + "不存在！");
        return false;
    }

    public static boolean a(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            a.a(a, "删除单个文件失败：" + str + "不存在！");
            return false;
        } else if (file.delete()) {
            a.a(a, "删除单个文件" + str + "成功！");
            return true;
        } else {
            a.a(a, "删除单个文件" + str + "失败！");
            return false;
        }
    }
}
