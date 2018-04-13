package cz.msebera.android.httpclient.entity.mime;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.content.ByteArrayBody;
import cz.msebera.android.httpclient.entity.mime.content.ContentBody;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;
import cz.msebera.android.httpclient.entity.mime.content.InputStreamBody;
import cz.msebera.android.httpclient.entity.mime.content.StringBody;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.Args;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import qsbk.app.thirdparty.net.HttpManager;

public class MultipartEntityBuilder {
    private static final char[] a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private ContentType b;
    private HttpMultipartMode c = HttpMultipartMode.STRICT;
    private String d = null;
    private Charset e = null;
    private List<FormBodyPart> f = null;

    public static MultipartEntityBuilder create() {
        return new MultipartEntityBuilder();
    }

    MultipartEntityBuilder() {
    }

    public MultipartEntityBuilder setMode(HttpMultipartMode httpMultipartMode) {
        this.c = httpMultipartMode;
        return this;
    }

    public MultipartEntityBuilder setLaxMode() {
        this.c = HttpMultipartMode.BROWSER_COMPATIBLE;
        return this;
    }

    public MultipartEntityBuilder setStrictMode() {
        this.c = HttpMultipartMode.STRICT;
        return this;
    }

    public MultipartEntityBuilder setBoundary(String str) {
        this.d = str;
        return this;
    }

    public MultipartEntityBuilder setMimeSubtype(String str) {
        Args.notBlank(str, "MIME subtype");
        this.b = ContentType.create("multipart/" + str);
        return this;
    }

    public MultipartEntityBuilder seContentType(ContentType contentType) {
        Args.notNull(contentType, "Content type");
        this.b = contentType;
        return this;
    }

    public MultipartEntityBuilder setCharset(Charset charset) {
        this.e = charset;
        return this;
    }

    public MultipartEntityBuilder addPart(FormBodyPart formBodyPart) {
        if (formBodyPart != null) {
            if (this.f == null) {
                this.f = new ArrayList();
            }
            this.f.add(formBodyPart);
        }
        return this;
    }

    public MultipartEntityBuilder addPart(String str, ContentBody contentBody) {
        Args.notNull(str, "Name");
        Args.notNull(contentBody, "Content body");
        return addPart(FormBodyPartBuilder.create(str, contentBody).build());
    }

    public MultipartEntityBuilder addTextBody(String str, String str2, ContentType contentType) {
        return addPart(str, new StringBody(str2, contentType));
    }

    public MultipartEntityBuilder addTextBody(String str, String str2) {
        return addTextBody(str, str2, ContentType.DEFAULT_TEXT);
    }

    public MultipartEntityBuilder addBinaryBody(String str, byte[] bArr, ContentType contentType, String str2) {
        return addPart(str, new ByteArrayBody(bArr, contentType, str2));
    }

    public MultipartEntityBuilder addBinaryBody(String str, byte[] bArr) {
        return addBinaryBody(str, bArr, ContentType.DEFAULT_BINARY, null);
    }

    public MultipartEntityBuilder addBinaryBody(String str, File file, ContentType contentType, String str2) {
        return addPart(str, new FileBody(file, contentType, str2));
    }

    public MultipartEntityBuilder addBinaryBody(String str, File file) {
        return addBinaryBody(str, file, ContentType.DEFAULT_BINARY, file != null ? file.getName() : null);
    }

    public MultipartEntityBuilder addBinaryBody(String str, InputStream inputStream, ContentType contentType, String str2) {
        return addPart(str, new InputStreamBody(inputStream, contentType, str2));
    }

    public MultipartEntityBuilder addBinaryBody(String str, InputStream inputStream) {
        return addBinaryBody(str, inputStream, ContentType.DEFAULT_BINARY, null);
    }

    private String b() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int nextInt = random.nextInt(11) + 30;
        for (int i = 0; i < nextInt; i++) {
            stringBuilder.append(a[random.nextInt(a.length)]);
        }
        return stringBuilder.toString();
    }

    f a() {
        String b;
        Charset charset;
        ContentType withParameters;
        List arrayList;
        a bVar;
        String str = this.d;
        if (str == null && this.b != null) {
            str = this.b.getParameter("boundary");
        }
        if (str == null) {
            b = b();
        } else {
            b = str;
        }
        Charset charset2 = this.e;
        if (charset2 != null || this.b == null) {
            charset = charset2;
        } else {
            charset = this.b.getCharset();
        }
        List arrayList2 = new ArrayList(2);
        arrayList2.add(new BasicNameValuePair("boundary", b));
        if (charset != null) {
            arrayList2.add(new BasicNameValuePair("charset", charset.name()));
        }
        NameValuePair[] nameValuePairArr = (NameValuePair[]) arrayList2.toArray(new NameValuePair[arrayList2.size()]);
        if (this.b != null) {
            withParameters = this.b.withParameters(nameValuePairArr);
        } else {
            withParameters = ContentType.create(HttpManager.MULTIPART_FORM_DATA, nameValuePairArr);
        }
        if (this.f != null) {
            arrayList = new ArrayList(this.f);
        } else {
            arrayList = Collections.emptyList();
        }
        switch (e.a[(this.c != null ? this.c : HttpMultipartMode.STRICT).ordinal()]) {
            case 1:
                bVar = new b(charset, b, arrayList);
                break;
            case 2:
                bVar = new c(charset, b, arrayList);
                break;
            default:
                bVar = new d(charset, b, arrayList);
                break;
        }
        return new f(bVar, withParameters, bVar.getTotalLength());
    }

    public HttpEntity build() {
        return a();
    }
}
