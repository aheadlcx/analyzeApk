package android.support.v4.provider;

import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class p extends DocumentFile {
    private File a;

    p(DocumentFile documentFile, File file) {
        super(documentFile);
        this.a = file;
    }

    public DocumentFile createFile(String str, String str2) {
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        if (extensionFromMimeType != null) {
            str2 = str2 + "." + extensionFromMimeType;
        }
        File file = new File(this.a, str2);
        try {
            file.createNewFile();
            return new p(this, file);
        } catch (IOException e) {
            Log.w("DocumentFile", "Failed to createFile: " + e);
            return null;
        }
    }

    public DocumentFile createDirectory(String str) {
        File file = new File(this.a, str);
        if (file.isDirectory() || file.mkdir()) {
            return new p(this, file);
        }
        return null;
    }

    public Uri getUri() {
        return Uri.fromFile(this.a);
    }

    public String getName() {
        return this.a.getName();
    }

    public String getType() {
        if (this.a.isDirectory()) {
            return null;
        }
        return a(this.a.getName());
    }

    public boolean isDirectory() {
        return this.a.isDirectory();
    }

    public boolean isFile() {
        return this.a.isFile();
    }

    public boolean isVirtual() {
        return false;
    }

    public long lastModified() {
        return this.a.lastModified();
    }

    public long length() {
        return this.a.length();
    }

    public boolean canRead() {
        return this.a.canRead();
    }

    public boolean canWrite() {
        return this.a.canWrite();
    }

    public boolean delete() {
        a(this.a);
        return this.a.delete();
    }

    public boolean exists() {
        return this.a.exists();
    }

    public DocumentFile[] listFiles() {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = this.a.listFiles();
        if (listFiles != null) {
            for (File pVar : listFiles) {
                arrayList.add(new p(this, pVar));
            }
        }
        return (DocumentFile[]) arrayList.toArray(new DocumentFile[arrayList.size()]);
    }

    public boolean renameTo(String str) {
        File file = new File(this.a.getParentFile(), str);
        if (!this.a.renameTo(file)) {
            return false;
        }
        this.a = file;
        return true;
    }

    private static String a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(lastIndexOf + 1).toLowerCase());
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        return "application/octet-stream";
    }

    private static boolean a(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    z &= a(file2);
                }
                if (!file2.delete()) {
                    Log.w("DocumentFile", "Failed to delete " + file2);
                    z = false;
                }
            }
        }
        return z;
    }
}
