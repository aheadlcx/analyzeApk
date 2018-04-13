package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.InputLimit;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Immutable
public class FileResourceFactory implements ResourceFactory {
    private final File a;
    private final e b = new e();

    public FileResourceFactory(File file) {
        this.a = file;
    }

    private File a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        this.b.generate(stringBuilder);
        stringBuilder.append('.');
        int min = Math.min(str.length(), 100);
        for (int i = 0; i < min; i++) {
            char charAt = str.charAt(i);
            if (Character.isLetterOrDigit(charAt) || charAt == '.') {
                stringBuilder.append(charAt);
            } else {
                stringBuilder.append('-');
            }
        }
        return new File(this.a, stringBuilder.toString());
    }

    public Resource generate(String str, InputStream inputStream, InputLimit inputLimit) throws IOException {
        File a = a(str);
        FileOutputStream fileOutputStream = new FileOutputStream(a);
        try {
            byte[] bArr = new byte[2048];
            long j = 0;
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
                j += (long) read;
                if (inputLimit != null && j > inputLimit.getValue()) {
                    break;
                }
            }
            inputLimit.reached();
            fileOutputStream.close();
            return new FileResource(a);
        } catch (Throwable th) {
            fileOutputStream.close();
        }
    }

    public Resource copy(String str, Resource resource) throws IOException {
        File a = a(str);
        if (resource instanceof FileResource) {
            s.a(((FileResource) resource).a(), a);
        } else {
            s.b(resource.getInputStream(), new FileOutputStream(a));
        }
        return new FileResource(a);
    }
}
