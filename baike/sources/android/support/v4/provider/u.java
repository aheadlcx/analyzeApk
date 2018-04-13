package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class u extends DocumentFile {
    private Context a;
    private Uri b;

    u(DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.a = context;
        this.b = uri;
    }

    public DocumentFile createFile(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    public DocumentFile createDirectory(String str) {
        throw new UnsupportedOperationException();
    }

    public Uri getUri() {
        return this.b;
    }

    public String getName() {
        return a.getName(this.a, this.b);
    }

    public String getType() {
        return a.getType(this.a, this.b);
    }

    public boolean isDirectory() {
        return a.isDirectory(this.a, this.b);
    }

    public boolean isFile() {
        return a.isFile(this.a, this.b);
    }

    public boolean isVirtual() {
        return a.isVirtual(this.a, this.b);
    }

    public long lastModified() {
        return a.lastModified(this.a, this.b);
    }

    public long length() {
        return a.length(this.a, this.b);
    }

    public boolean canRead() {
        return a.canRead(this.a, this.b);
    }

    public boolean canWrite() {
        return a.canWrite(this.a, this.b);
    }

    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.a.getContentResolver(), this.b);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean exists() {
        return a.exists(this.a, this.b);
    }

    public DocumentFile[] listFiles() {
        throw new UnsupportedOperationException();
    }

    public boolean renameTo(String str) {
        throw new UnsupportedOperationException();
    }
}
