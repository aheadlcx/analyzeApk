package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import java.io.File;

public abstract class DocumentFile {
    private final DocumentFile a;

    public abstract boolean canRead();

    public abstract boolean canWrite();

    public abstract DocumentFile createDirectory(String str);

    public abstract DocumentFile createFile(String str, String str2);

    public abstract boolean delete();

    public abstract boolean exists();

    public abstract String getName();

    public abstract String getType();

    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract boolean isVirtual();

    public abstract long lastModified();

    public abstract long length();

    public abstract DocumentFile[] listFiles();

    public abstract boolean renameTo(String str);

    DocumentFile(DocumentFile documentFile) {
        this.a = documentFile;
    }

    public static DocumentFile fromFile(File file) {
        return new p(null, file);
    }

    public static DocumentFile fromSingleUri(Context context, Uri uri) {
        if (VERSION.SDK_INT >= 19) {
            return new u(null, context, uri);
        }
        return null;
    }

    public static DocumentFile fromTreeUri(Context context, Uri uri) {
        if (VERSION.SDK_INT >= 21) {
            return new v(null, context, DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)));
        }
        return null;
    }

    public static boolean isDocumentUri(Context context, Uri uri) {
        if (VERSION.SDK_INT >= 19) {
            return a.isDocumentUri(context, uri);
        }
        return false;
    }

    public DocumentFile getParentFile() {
        return this.a;
    }

    public DocumentFile findFile(String str) {
        for (DocumentFile documentFile : listFiles()) {
            if (str.equals(documentFile.getName())) {
                return documentFile;
            }
        }
        return null;
    }
}
