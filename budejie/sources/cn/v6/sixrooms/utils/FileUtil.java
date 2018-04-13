package cn.v6.sixrooms.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import org.apache.http.util.EncodingUtils;

public class FileUtil {
    private static final String a = FileUtil.class.getSimpleName();

    public static boolean saveBitmap(Bitmap bitmap, String str) {
        File file = new File(str);
        try {
            file.createNewFile();
            try {
                OutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                try {
                    fileOutputStream.flush();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return false;
                }
            } catch (FileNotFoundException e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (IOException e4) {
            return false;
        }
    }

    public static boolean isSdCard() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static void saveBitmap(Bitmap bitmap, File file) {
        FileOutputStream fileOutputStream;
        IOException e;
        Throwable th;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
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
        } catch (IOException e5) {
            e22 = e5;
            fileOutputStream = null;
            e22.printStackTrace();
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

    public static int readPictureDegree(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Bitmap rotaingImageView(int i, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static byte[] readInputStream(InputStream inputStream) throws Exception {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                inputStream.close();
                return bArr;
            }
        }
    }

    public static Bitmap getBitmapFromBytes(byte[] bArr, Options options) {
        if (bArr == null) {
            return null;
        }
        if (options != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static String readFile(String str) throws IOException {
        String string;
        Exception e;
        String str2 = "";
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            byte[] bArr = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bArr);
            string = EncodingUtils.getString(bArr, "UTF-8");
            try {
                bufferedInputStream.close();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return string;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            string = str2;
            e = exception;
            e.printStackTrace();
            return string;
        }
        return string;
    }

    public static void writeFile(String str, String str2) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            try {
                outputStreamWriter.write(str2);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
    }

    public static String getFromAssets(String str) {
        String str2 = "";
        try {
            InputStream open = V6Coop.getInstance().getContext().getResources().getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            str2 = EncodingUtils.getString(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static <T> boolean saveBeanToFile(T t) {
        File file;
        FileOutputStream fileOutputStream;
        IOException e;
        FileOutputStream fileOutputStream2;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2;
        File file2 = null;
        try {
            file = new File(V6Coop.getInstance().getContext().getFilesDir().toString() + File.separator + "Serializable" + File.separator + t.getClass().getSimpleName() + ".dat");
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException e2) {
                e = e2;
                fileOutputStream2 = null;
                try {
                    e.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    if (file == null) {
                    }
                    return false;
                } catch (Throwable th) {
                    fileOutputStream = fileOutputStream2;
                    objectOutputStream2 = objectOutputStream;
                    file2 = file;
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                        }
                    }
                    if (file2 == null && file2.exists() && file2.length() > 0) {
                        return true;
                    }
                    return false;
                }
            } catch (Throwable th2) {
                objectOutputStream2 = null;
                fileOutputStream = null;
                file2 = file;
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (file2 == null) {
                }
                return false;
            }
            try {
                objectOutputStream2 = new ObjectOutputStream(fileOutputStream);
                try {
                    objectOutputStream2.writeObject(t);
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e32222) {
                        e32222.printStackTrace();
                    }
                    try {
                        fileOutputStream.close();
                    } catch (IOException e322222) {
                        e322222.printStackTrace();
                    }
                    if (!file.exists() || file.length() <= 0) {
                        return false;
                    }
                    return true;
                } catch (IOException e4) {
                    e322222 = e4;
                    objectOutputStream = objectOutputStream2;
                    fileOutputStream2 = fileOutputStream;
                    e322222.printStackTrace();
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (file == null && file.exists() && file.length() > 0) {
                        return true;
                    }
                    return false;
                } catch (Throwable th3) {
                    file2 = file;
                    if (objectOutputStream2 != null) {
                        objectOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (file2 == null) {
                    }
                    return false;
                }
            } catch (IOException e5) {
                e322222 = e5;
                fileOutputStream2 = fileOutputStream;
                e322222.printStackTrace();
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (file == null) {
                }
                return false;
            } catch (Throwable th4) {
                objectOutputStream2 = null;
                file2 = file;
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (file2 == null) {
                }
                return false;
            }
        } catch (IOException e6) {
            e322222 = e6;
            file = null;
            fileOutputStream2 = null;
            e322222.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            if (file == null) {
            }
            return false;
        } catch (Throwable th5) {
            objectOutputStream2 = null;
            fileOutputStream = null;
            if (objectOutputStream2 != null) {
                objectOutputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (file2 == null) {
            }
            return false;
        }
    }

    public static <T> T getBeanFromFile(Class<T> cls) {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        IOException e;
        ClassNotFoundException e2;
        OptionalDataException e3;
        FileNotFoundException e4;
        StreamCorruptedException e5;
        T t;
        T t2 = null;
        try {
            File file = new File(V6Coop.getInstance().getContext().getFilesDir().toString() + File.separator + "Serializable" + File.separator + cls.getSimpleName() + ".dat");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    try {
                        t2 = objectInputStream.readObject();
                        try {
                            objectInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e62) {
                            e62.printStackTrace();
                        }
                    } catch (ClassNotFoundException e7) {
                        e2 = e7;
                        try {
                            e2.printStackTrace();
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e622) {
                                    e622.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e6222) {
                                    e6222.printStackTrace();
                                }
                            }
                        } catch (Throwable th) {
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e62222) {
                                    e62222.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e622222) {
                                    e622222.printStackTrace();
                                }
                            }
                            return t2;
                        }
                        return t2;
                    } catch (OptionalDataException e8) {
                        e3 = e8;
                        e3.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e6222222) {
                                e6222222.printStackTrace();
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e62222222) {
                                e62222222.printStackTrace();
                            }
                        }
                        return t2;
                    } catch (FileNotFoundException e9) {
                        e4 = e9;
                        e4.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e622222222) {
                                e622222222.printStackTrace();
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e6222222222) {
                                e6222222222.printStackTrace();
                            }
                        }
                        return t2;
                    } catch (StreamCorruptedException e10) {
                        e5 = e10;
                        e5.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e62222222222) {
                                e62222222222.printStackTrace();
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e622222222222) {
                                e622222222222.printStackTrace();
                            }
                        }
                        return t2;
                    } catch (IOException e11) {
                        e622222222222 = e11;
                        e622222222222.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e6222222222222) {
                                e6222222222222.printStackTrace();
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e62222222222222) {
                                e62222222222222.printStackTrace();
                            }
                        }
                        return t2;
                    }
                } catch (ClassNotFoundException e12) {
                    e2 = e12;
                    t = t2;
                    e2.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return t2;
                } catch (OptionalDataException e13) {
                    e3 = e13;
                    t = t2;
                    e3.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return t2;
                } catch (FileNotFoundException e14) {
                    e4 = e14;
                    t = t2;
                    e4.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return t2;
                } catch (StreamCorruptedException e15) {
                    e5 = e15;
                    t = t2;
                    e5.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return t2;
                } catch (IOException e16) {
                    e62222222222222 = e16;
                    t = t2;
                    e62222222222222.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return t2;
                } catch (Throwable th2) {
                    t = t2;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return t2;
                }
            }
        } catch (ClassNotFoundException e17) {
            e2 = e17;
            objectInputStream = t2;
            fileInputStream = t2;
            e2.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return t2;
        } catch (OptionalDataException e18) {
            e3 = e18;
            objectInputStream = t2;
            fileInputStream = t2;
            e3.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return t2;
        } catch (FileNotFoundException e19) {
            e4 = e19;
            objectInputStream = t2;
            fileInputStream = t2;
            e4.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return t2;
        } catch (StreamCorruptedException e20) {
            e5 = e20;
            objectInputStream = t2;
            fileInputStream = t2;
            e5.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return t2;
        } catch (IOException e21) {
            e62222222222222 = e21;
            objectInputStream = t2;
            fileInputStream = t2;
            e62222222222222.printStackTrace();
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return t2;
        } catch (Throwable th3) {
            objectInputStream = t2;
            fileInputStream = t2;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return t2;
        }
        return t2;
    }

    public static void cleanBeanFromFile(Class cls) {
        try {
            File file = new File(V6Coop.getInstance().getContext().getFilesDir().toString() + File.separator + "Serializable" + File.separator + cls.getSimpleName() + ".dat");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cleanBeanFromFile(Context context, Class cls) {
        try {
            File file = new File(context.getFilesDir().toString() + File.separator + "Serializable" + File.separator + cls.getSimpleName() + ".dat");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String inputStream2String(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return stringBuffer.toString();
            }
            stringBuffer.append(new String(bArr, 0, read));
        }
    }

    public static String inputStream2String(InputStreamReader inputStreamReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuffer.append(readLine);
            } else {
                bufferedReader.close();
                return stringBuffer.toString();
            }
        }
    }
}
