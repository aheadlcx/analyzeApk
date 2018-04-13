package qsbk.app.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ZipUtils {
    public static void zipFiles(Collection<File> collection, File file) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File a : collection) {
            a(a, zipOutputStream, "");
        }
        zipOutputStream.close();
    }

    public static void zipFiles(Collection<File> collection, File file, String str) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File a : collection) {
            a(a, zipOutputStream, "");
        }
        zipOutputStream.setComment(str);
        zipOutputStream.close();
    }

    public static void upZipFile(String str, String str2) throws ZipException, IOException {
        InputStream inputStream = null;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        ZipFile zipFile = new ZipFile(str);
        try {
            OutputStream fileOutputStream;
            Throwable th;
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                String str3 = new String((str2 + File.separator + zipEntry.getName()).getBytes("8859_1"), "GB2312");
                File file2 = new File(str3);
                if (!(file2.isDirectory() || str3.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR))) {
                    DebugUtil.debug("UNZIP", "unzip file:" + str3);
                    if (!file2.exists()) {
                        File parentFile = file2.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        file2.createNewFile();
                    }
                    try {
                        InputStream inputStream2 = zipFile.getInputStream(zipEntry);
                        try {
                            fileOutputStream = new FileOutputStream(file2);
                            try {
                                byte[] bArr = new byte[1048576];
                                while (true) {
                                    int read = inputStream2.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream = inputStream2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = null;
                            inputStream = inputStream2;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream = null;
                    }
                }
            }
            return;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        } finally {
            zipFile.close();
        }
    }

    public static void upZipAssetFile(InputStream inputStream, String str) throws ZipException, IOException {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        byte[] bArr = new byte[1048576];
        for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
            File file2;
            if (nextEntry.isDirectory()) {
                file2 = new File(str + File.separator + nextEntry.getName());
                if (!file2.exists()) {
                    file2.mkdir();
                }
            } else {
                file2 = new File(str + File.separator + nextEntry.getName());
                if (!file2.exists()) {
                    file2.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                }
            }
        }
        zipInputStream.close();
    }

    public static ArrayList<File> upZipSelectedFile(File file, String str, String str2) throws ZipException, IOException {
        OutputStream outputStream = null;
        ArrayList<File> arrayList = new ArrayList();
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        ZipFile zipFile = new ZipFile(file);
        try {
            Throwable th;
            Enumeration entries = zipFile.entries();
            InputStream inputStream = null;
            while (entries.hasMoreElements()) {
                OutputStream fileOutputStream;
                InputStream inputStream2;
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                if (zipEntry.getName().contains(str2)) {
                    File file3 = new File(new String((str + File.separator + zipEntry.getName()).getBytes("8859_1"), "GB2312"));
                    if (!file3.exists()) {
                        File parentFile = file3.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        file3.createNewFile();
                    }
                    try {
                        inputStream = zipFile.getInputStream(zipEntry);
                        fileOutputStream = new FileOutputStream(file3);
                        try {
                            byte[] bArr = new byte[1048576];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            arrayList.add(file3);
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                                inputStream2 = inputStream;
                            } else {
                                inputStream2 = inputStream;
                            }
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            outputStream = fileOutputStream;
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } else {
                    fileOutputStream = outputStream;
                    inputStream2 = inputStream;
                }
                inputStream = inputStream2;
                outputStream = fileOutputStream;
            }
            return arrayList;
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        } finally {
            zipFile.close();
        }
    }

    public static ArrayList<String> getEntriesNames(File file) throws ZipException, IOException {
        ArrayList<String> arrayList = new ArrayList();
        Enumeration entriesEnumeration = getEntriesEnumeration(file);
        while (entriesEnumeration.hasMoreElements()) {
            arrayList.add(new String(getEntryName((ZipEntry) entriesEnumeration.nextElement()).getBytes("GB2312"), "8859_1"));
        }
        return arrayList;
    }

    public static Enumeration<?> getEntriesEnumeration(File file) throws ZipException, IOException {
        return new ZipFile(file).entries();
    }

    public static String getEntryComment(ZipEntry zipEntry) throws UnsupportedEncodingException {
        return new String(zipEntry.getComment().getBytes("GB2312"), "8859_1");
    }

    public static String getEntryName(ZipEntry zipEntry) throws UnsupportedEncodingException {
        return new String(zipEntry.getName().getBytes("GB2312"), "8859_1");
    }

    private static void a(File file, ZipOutputStream zipOutputStream, String str) throws FileNotFoundException, IOException {
        String str2 = new String((str + (str.trim().length() == 0 ? "" : File.separator) + file.getName()).getBytes("8859_1"), "GB2312");
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                a(a, zipOutputStream, str2);
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }
}
