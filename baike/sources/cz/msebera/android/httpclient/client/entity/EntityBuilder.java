package cz.msebera.android.httpclient.client.entity;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.entity.BasicHttpEntity;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.FileEntity;
import cz.msebera.android.httpclient.entity.InputStreamEntity;
import cz.msebera.android.httpclient.entity.SerializableEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@NotThreadSafe
public class EntityBuilder {
    private String a;
    private byte[] b;
    private InputStream c;
    private List<NameValuePair> d;
    private Serializable e;
    private File f;
    private ContentType g;
    private String h;
    private boolean i;
    private boolean j;

    EntityBuilder() {
    }

    public static EntityBuilder create() {
        return new EntityBuilder();
    }

    private void a() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
    }

    public String getText() {
        return this.a;
    }

    public EntityBuilder setText(String str) {
        a();
        this.a = str;
        return this;
    }

    public byte[] getBinary() {
        return this.b;
    }

    public EntityBuilder setBinary(byte[] bArr) {
        a();
        this.b = bArr;
        return this;
    }

    public InputStream getStream() {
        return this.c;
    }

    public EntityBuilder setStream(InputStream inputStream) {
        a();
        this.c = inputStream;
        return this;
    }

    public List<NameValuePair> getParameters() {
        return this.d;
    }

    public EntityBuilder setParameters(List<NameValuePair> list) {
        a();
        this.d = list;
        return this;
    }

    public EntityBuilder setParameters(NameValuePair... nameValuePairArr) {
        return setParameters(Arrays.asList(nameValuePairArr));
    }

    public Serializable getSerializable() {
        return this.e;
    }

    public EntityBuilder setSerializable(Serializable serializable) {
        a();
        this.e = serializable;
        return this;
    }

    public File getFile() {
        return this.f;
    }

    public EntityBuilder setFile(File file) {
        a();
        this.f = file;
        return this;
    }

    public ContentType getContentType() {
        return this.g;
    }

    public EntityBuilder setContentType(ContentType contentType) {
        this.g = contentType;
        return this;
    }

    public String getContentEncoding() {
        return this.h;
    }

    public EntityBuilder setContentEncoding(String str) {
        this.h = str;
        return this;
    }

    public boolean isChunked() {
        return this.i;
    }

    public EntityBuilder chunked() {
        this.i = true;
        return this;
    }

    public boolean isGzipCompress() {
        return this.j;
    }

    public EntityBuilder gzipCompress() {
        this.j = true;
        return this;
    }

    private ContentType a(ContentType contentType) {
        return this.g != null ? this.g : contentType;
    }

    public HttpEntity build() {
        HttpEntity stringEntity;
        if (this.a != null) {
            stringEntity = new StringEntity(this.a, a(ContentType.DEFAULT_TEXT));
        } else if (this.b != null) {
            stringEntity = new ByteArrayEntity(this.b, a(ContentType.DEFAULT_BINARY));
        } else if (this.c != null) {
            stringEntity = new InputStreamEntity(this.c, -1, a(ContentType.DEFAULT_BINARY));
        } else if (this.d != null) {
            Object urlEncodedFormEntity = new UrlEncodedFormEntity(this.d, this.g != null ? this.g.getCharset() : null);
        } else if (this.e != null) {
            stringEntity = new SerializableEntity(this.e);
            stringEntity.setContentType(ContentType.DEFAULT_BINARY.toString());
        } else if (this.f != null) {
            stringEntity = new FileEntity(this.f, a(ContentType.DEFAULT_BINARY));
        } else {
            stringEntity = new BasicHttpEntity();
        }
        if (!(stringEntity.getContentType() == null || this.g == null)) {
            stringEntity.setContentType(this.g.toString());
        }
        stringEntity.setContentEncoding(this.h);
        stringEntity.setChunked(this.i);
        if (this.j) {
            return new GzipCompressingEntity(stringEntity);
        }
        return stringEntity;
    }
}
