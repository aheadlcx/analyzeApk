package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.cache.InputLimit;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Immutable
public class HeapResourceFactory implements ResourceFactory {
    public Resource generate(String str, InputStream inputStream, InputLimit inputLimit) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
            j += (long) read;
            if (inputLimit != null && j > inputLimit.getValue()) {
                break;
            }
        }
        inputLimit.reached();
        return a(byteArrayOutputStream.toByteArray());
    }

    public Resource copy(String str, Resource resource) throws IOException {
        byte[] a;
        if (resource instanceof HeapResource) {
            a = ((HeapResource) resource).a();
        } else {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            s.b(resource.getInputStream(), byteArrayOutputStream);
            a = byteArrayOutputStream.toByteArray();
        }
        return a(a);
    }

    Resource a(byte[] bArr) {
        return new HeapResource(bArr);
    }
}
