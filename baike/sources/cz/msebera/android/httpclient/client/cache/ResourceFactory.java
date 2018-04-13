package cz.msebera.android.httpclient.client.cache;

import java.io.IOException;
import java.io.InputStream;

public interface ResourceFactory {
    Resource copy(String str, Resource resource) throws IOException;

    Resource generate(String str, InputStream inputStream, InputLimit inputLimit) throws IOException;
}
