package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.ArrayList;

@RequiresApi(21)
class v extends DocumentFile {
    private Context a;
    private Uri b;

    v(DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.a = context;
        this.b = uri;
    }

    public DocumentFile createFile(String str, String str2) {
        Uri a = a(this.a, this.b, str, str2);
        return a != null ? new v(this, this.a, a) : null;
    }

    private static Uri a(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception e) {
            return null;
        }
    }

    public DocumentFile createDirectory(String str) {
        Uri a = a(this.a, this.b, "vnd.android.document/directory", str);
        return a != null ? new v(this, this.a, a) : null;
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
        AutoCloseable query;
        Object e;
        Uri[] uriArr;
        DocumentFile[] documentFileArr;
        int i;
        Throwable th;
        ContentResolver contentResolver = this.a.getContentResolver();
        Uri buildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(this.b, DocumentsContract.getDocumentId(this.b));
        ArrayList arrayList = new ArrayList();
        try {
            query = contentResolver.query(buildChildDocumentsUriUsingTree, new String[]{"document_id"}, null, null, null);
            while (query.moveToNext()) {
                try {
                    arrayList.add(DocumentsContract.buildDocumentUriUsingTree(this.b, query.getString(0)));
                } catch (Exception e2) {
                    e = e2;
                }
            }
            a(query);
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                Log.w("DocumentFile", "Failed query: " + e);
                a(query);
                uriArr = (Uri[]) arrayList.toArray(new Uri[arrayList.size()]);
                documentFileArr = new DocumentFile[uriArr.length];
                for (i = 0; i < uriArr.length; i++) {
                    documentFileArr[i] = new v(this, this.a, uriArr[i]);
                }
                return documentFileArr;
            } catch (Throwable th2) {
                th = th2;
                a(query);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            a(query);
            throw th;
        }
        uriArr = (Uri[]) arrayList.toArray(new Uri[arrayList.size()]);
        documentFileArr = new DocumentFile[uriArr.length];
        for (i = 0; i < uriArr.length; i++) {
            documentFileArr[i] = new v(this, this.a, uriArr[i]);
        }
        return documentFileArr;
    }

    private static void a(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public boolean renameTo(String str) {
        try {
            Uri renameDocument = DocumentsContract.renameDocument(this.a.getContentResolver(), this.b, str);
            if (renameDocument == null) {
                return false;
            }
            this.b = renameDocument;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
