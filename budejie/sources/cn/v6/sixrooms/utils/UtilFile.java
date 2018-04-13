package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class UtilFile {
    public static boolean isExistFile(String str) {
        return new File(str).exists();
    }

    public static void copyAsset(Activity activity, String str, String str2) throws IOException {
        InputStream open;
        OutputStream fileOutputStream;
        Exception e;
        Throwable th;
        InputStream inputStream = null;
        File file = new File(str2);
        if (file.exists()) {
            file.delete();
        } else {
            String absolutePath = file.getAbsolutePath();
            new File(absolutePath.substring(0, absolutePath.lastIndexOf("/"))).mkdirs();
            file.createNewFile();
        }
        byte[] bArr = new byte[1024];
        try {
            open = activity.getAssets().open(str);
            try {
                fileOutputStream = new FileOutputStream(file);
                while (true) {
                    try {
                        int read = open.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    } catch (Exception e2) {
                        e = e2;
                        inputStream = open;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (open != null) {
                    open.close();
                }
                fileOutputStream.close();
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                inputStream = open;
                try {
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    open = inputStream;
                    if (open != null) {
                        open.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                if (open != null) {
                    open.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
            e.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            open = null;
            if (open != null) {
                open.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static void appendStrToFile(String str, String str2) {
        File file = new File(str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(str2, true);
                fileWriter.write(str);
                fileWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void saveStrToFile(String str, String str2) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        File file = new File(str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        try {
            fileOutputStream = new FileOutputStream(str2);
            try {
                PrintWriter printWriter = new PrintWriter(fileOutputStream);
                printWriter.write(str);
                printWriter.flush();
                try {
                    fileOutputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static String getFileStrContent(String str) {
        if (str != null) {
            return getFileStrContent(new File(str));
        }
        return null;
    }

    public static String getShaderStrContent(String str) {
        if (str != null) {
            return getShaderFileStrContent(new File(str));
        }
        return null;
    }

    public static String getFileStrContent(File file) {
        String str = null;
        if (file != null && file.exists()) {
            try {
                str = a(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String getShaderFileStrContent(File file) {
        String str = null;
        if (file != null && file.exists()) {
            try {
                str = b(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String readAssertResource(Context context, String str) {
        String str2 = "";
        try {
            str2 = a(context.getAssets().open(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str2;
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                        }
                    }
                } catch (IOException e2) {
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    bufferedReader2 = bufferedReader;
                    th = th3;
                }
            }
            bufferedReader.close();
        } catch (IOException e3) {
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th4) {
            th = th4;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
        return stringBuilder.toString();
    }

    private static String b(InputStream inputStream) {
        Throwable th;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine + "\n");
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                        }
                    }
                } catch (IOException e2) {
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    bufferedReader = bufferedReader2;
                    th = th3;
                }
            }
            bufferedReader2.close();
        } catch (IOException e3) {
            bufferedReader2 = null;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th4) {
            th = th4;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
        return stringBuilder.toString();
    }

    public static String getImgCacheDir(Context context) {
        File file = new File("/data/data/" + context.getPackageName() + "/cache/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static void delFile(String str) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static long getSize(String str) {
        StatFs statFs = new StatFs(str);
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static boolean checkImgSize(String str, long j) {
        if (getSize(str) < j) {
            return false;
        }
        return true;
    }

    public static void writeDataToFile(byte[] bArr, String str) throws IOException {
        FileNotFoundException e;
        Throwable th;
        File file = new File(str);
        if (!file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
            } catch (IOException e2) {
                try {
                    e2.printStackTrace();
                } catch (FileNotFoundException e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
            try {
                fileOutputStream.close();
            } catch (IOException e222) {
                e222.printStackTrace();
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static boolean checkFileSize(String str, long j) {
        if (getSize(str) < j) {
            return false;
        }
        return true;
    }

    public static void delDirFile(String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
        }
    }
}
