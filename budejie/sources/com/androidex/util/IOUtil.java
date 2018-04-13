package com.androidex.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class IOUtil {
    public static byte[] getFileBytes(String str) {
        InputStream fileInputStream;
        OutputStream byteArrayOutputStream;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e2) {
                e = e2;
                byteArrayOutputStream = bArr;
                try {
                    e.printStackTrace();
                    closeInStream(fileInputStream);
                    closeOutStream(byteArrayOutputStream);
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    closeInStream(fileInputStream);
                    closeOutStream(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                byteArrayOutputStream = bArr;
                th = th3;
                closeInStream(fileInputStream);
                closeOutStream(byteArrayOutputStream);
                throw th;
            }
            try {
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                bArr = byteArrayOutputStream.toByteArray();
                closeInStream(fileInputStream);
                closeOutStream(byteArrayOutputStream);
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                closeInStream(fileInputStream);
                closeOutStream(byteArrayOutputStream);
                return bArr;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = bArr;
            fileInputStream = bArr;
            e.printStackTrace();
            closeInStream(fileInputStream);
            closeOutStream(byteArrayOutputStream);
            return bArr;
        } catch (Throwable th32) {
            byteArrayOutputStream = bArr;
            fileInputStream = bArr;
            th = th32;
            closeInStream(fileInputStream);
            closeOutStream(byteArrayOutputStream);
            throw th;
        }
        return bArr;
    }

    public static void closeInStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeOutStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeReader(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeWriter(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeObj(Object obj, File file) {
        OutputStream objectOutputStream;
        Exception e;
        Throwable th;
        OutputStream outputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            try {
                objectOutputStream.writeObject(obj);
                closeOutStream(objectOutputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    closeOutStream(objectOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = objectOutputStream;
                    closeOutStream(outputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            objectOutputStream = null;
            e.printStackTrace();
            closeOutStream(objectOutputStream);
        } catch (Throwable th3) {
            th = th3;
            closeOutStream(outputStream);
            throw th;
        }
    }

    public static Object readObj(File file) {
        InputStream objectInputStream;
        Object readObject;
        Exception e;
        Throwable th;
        InputStream inputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
                readObject = objectInputStream.readObject();
                closeInStream(objectInputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    closeInStream(objectInputStream);
                    return readObject;
                } catch (Throwable th2) {
                    th = th2;
                    closeInStream(objectInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            objectInputStream = inputStream;
            e.printStackTrace();
            closeInStream(objectInputStream);
            return readObject;
        } catch (Throwable th3) {
            objectInputStream = inputStream;
            th = th3;
            closeInStream(objectInputStream);
            throw th;
        }
        return readObject;
    }
}
