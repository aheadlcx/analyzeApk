package org.eclipse.paho.client.mqttv3.persist;

import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Enumeration;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.FileLock;
import org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;

public class MqttDefaultFilePersistence implements MqttClientPersistence {
    private static final FilenameFilter d = new a();
    private File a;
    private File b;
    private FileLock c;

    public MqttDefaultFilePersistence() {
        this(System.getProperty("user.dir"));
    }

    public MqttDefaultFilePersistence(String str) {
        this.b = null;
        this.c = null;
        this.a = new File(str);
    }

    public void open(String str, String str2) throws MqttPersistenceException {
        int i = 0;
        if (this.a.exists() && !this.a.isDirectory()) {
            throw new MqttPersistenceException();
        } else if (!this.a.exists() && !this.a.mkdirs()) {
            throw new MqttPersistenceException();
        } else if (this.a.canWrite()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (a(charAt)) {
                    stringBuffer.append(charAt);
                }
            }
            stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            while (i < str2.length()) {
                char charAt2 = str2.charAt(i);
                if (a(charAt2)) {
                    stringBuffer.append(charAt2);
                }
                i++;
            }
            synchronized (this) {
                if (this.b == null) {
                    this.b = new File(this.a, stringBuffer.toString());
                    if (!this.b.exists()) {
                        this.b.mkdir();
                    }
                }
                try {
                    this.c = new FileLock(this.b, ".lck");
                } catch (Exception e) {
                }
                a(this.b);
            }
        } else {
            throw new MqttPersistenceException();
        }
    }

    private void a() throws MqttPersistenceException {
        if (this.b == null) {
            throw new MqttPersistenceException();
        }
    }

    public void close() throws MqttPersistenceException {
        synchronized (this) {
            if (this.c != null) {
                this.c.release();
            }
            if (b().length == 0) {
                this.b.delete();
            }
            this.b = null;
        }
    }

    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        a();
        File file = new File(this.b, new StringBuffer(String.valueOf(str)).append(".msg").toString());
        File file2 = new File(this.b, new StringBuffer(String.valueOf(str)).append(".msg").append(".bup").toString());
        if (file.exists() && !file.renameTo(file2)) {
            file2.delete();
            file.renameTo(file2);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength());
            if (mqttPersistable.getPayloadBytes() != null) {
                fileOutputStream.write(mqttPersistable.getPayloadBytes(), mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength());
            }
            fileOutputStream.getFD().sync();
            fileOutputStream.close();
            if (file2.exists()) {
                file2.delete();
            }
            if (file2.exists() && !file2.renameTo(file)) {
                file.delete();
                file2.renameTo(file);
            }
        } catch (Throwable e) {
            throw new MqttPersistenceException(e);
        } catch (Throwable th) {
            if (file2.exists() && !file2.renameTo(file)) {
                file.delete();
                file2.renameTo(file);
            }
        }
    }

    public MqttPersistable get(String str) throws MqttPersistenceException {
        a();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(this.b, new StringBuffer(String.valueOf(str)).append(".msg").toString()));
            int available = fileInputStream.available();
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += fileInputStream.read(bArr, i, available - i)) {
            }
            fileInputStream.close();
            return new MqttPersistentData(str, bArr, 0, bArr.length, null, 0, 0);
        } catch (Throwable e) {
            throw new MqttPersistenceException(e);
        }
    }

    public void remove(String str) throws MqttPersistenceException {
        a();
        File file = new File(this.b, new StringBuffer(String.valueOf(str)).append(".msg").toString());
        if (file.exists()) {
            file.delete();
        }
    }

    public Enumeration keys() throws MqttPersistenceException {
        a();
        File[] b = b();
        Vector vector = new Vector(b.length);
        for (File name : b) {
            String name2 = name.getName();
            vector.addElement(name2.substring(0, name2.length() - ".msg".length()));
        }
        return vector.elements();
    }

    private File[] b() throws MqttPersistenceException {
        a();
        File[] listFiles = this.b.listFiles(d);
        if (listFiles != null) {
            return listFiles;
        }
        throw new MqttPersistenceException();
    }

    private boolean a(char c) {
        return Character.isJavaIdentifierPart(c) || c == '-';
    }

    private void a(File file) throws MqttPersistenceException {
        File[] listFiles = file.listFiles(new b(this));
        if (listFiles == null) {
            throw new MqttPersistenceException();
        }
        for (int i = 0; i < listFiles.length; i++) {
            File file2 = new File(file, listFiles[i].getName().substring(0, listFiles[i].getName().length() - ".bup".length()));
            if (!listFiles[i].renameTo(file2)) {
                file2.delete();
                listFiles[i].renameTo(file2);
            }
        }
    }

    public boolean containsKey(String str) throws MqttPersistenceException {
        a();
        return new File(this.b, new StringBuffer(String.valueOf(str)).append(".msg").toString()).exists();
    }

    public void clear() throws MqttPersistenceException {
        a();
        File[] b = b();
        for (File delete : b) {
            delete.delete();
        }
    }
}
