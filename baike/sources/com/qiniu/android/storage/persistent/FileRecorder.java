package com.qiniu.android.storage.persistent;

import com.qiniu.android.storage.Recorder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import qsbk.app.live.ui.LiveBaseActivity;

public final class FileRecorder implements Recorder {
    public String directory;

    public FileRecorder(String str) throws IOException {
        this.directory = str;
        File file = new File(str);
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IOException("does not mkdir");
            }
        } else if (!file.mkdirs()) {
            throw new IOException("mkdir failed");
        }
    }

    public void set(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        IOException e;
        try {
            fileOutputStream = new FileOutputStream(new File(this.directory, a(str)));
            try {
                fileOutputStream.write(bArr);
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                if (fileOutputStream == null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return;
                    }
                }
            }
        } catch (IOException e4) {
            e3 = e4;
            fileOutputStream = null;
            e3.printStackTrace();
            if (fileOutputStream == null) {
                fileOutputStream.close();
            }
        }
        if (fileOutputStream == null) {
            fileOutputStream.close();
        }
    }

    public byte[] get(String str) {
        byte[] bArr;
        FileInputStream fileInputStream;
        IOException e;
        int i;
        File file = new File(this.directory, a(str));
        byte[] bArr2;
        try {
            if (a(file)) {
                file.delete();
                return null;
            }
            bArr = new byte[((int) file.length())];
            try {
                fileInputStream = new FileInputStream(file);
            } catch (IOException e2) {
                e = e2;
                fileInputStream = null;
                e.printStackTrace();
                bArr2 = bArr;
                i = 0;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (i == 0) {
                    return null;
                }
                return bArr2;
            }
            try {
                bArr2 = bArr;
                i = fileInputStream.read(bArr);
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
                bArr2 = bArr;
                i = 0;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (i == 0) {
                    return null;
                }
                return bArr2;
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (i == 0) {
                return bArr2;
            }
            return null;
        } catch (IOException e5) {
            e = e5;
            bArr = null;
            fileInputStream = null;
            e.printStackTrace();
            bArr2 = bArr;
            i = 0;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (i == 0) {
                return bArr2;
            }
            return null;
        }
    }

    private boolean a(File file) {
        return file.lastModified() + LiveBaseActivity.INNER < new Date().getTime();
    }

    public void del(String str) {
        new File(this.directory, a(str)).delete();
    }

    private static String a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-1").digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
