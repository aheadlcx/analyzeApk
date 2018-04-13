package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class EntityEnclosingRequestWrapper$a extends HttpEntityWrapper {
    final /* synthetic */ EntityEnclosingRequestWrapper a;

    EntityEnclosingRequestWrapper$a(EntityEnclosingRequestWrapper entityEnclosingRequestWrapper, HttpEntity httpEntity) {
        this.a = entityEnclosingRequestWrapper;
        super(httpEntity);
    }

    public void consumeContent() throws IOException {
        EntityEnclosingRequestWrapper.a(this.a, true);
        super.consumeContent();
    }

    public InputStream getContent() throws IOException {
        EntityEnclosingRequestWrapper.a(this.a, true);
        return super.getContent();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        EntityEnclosingRequestWrapper.a(this.a, true);
        super.writeTo(outputStream);
    }
}
