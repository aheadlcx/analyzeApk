package cz.msebera.android.httpclient.entity.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

class b extends a {
    private final List<FormBodyPart> c;

    public b(Charset charset, String str, List<FormBodyPart> list) {
        super(charset, str);
        this.c = list;
    }

    public List<FormBodyPart> getBodyParts() {
        return this.c;
    }

    protected void a(FormBodyPart formBodyPart, OutputStream outputStream) throws IOException {
        Header header = formBodyPart.getHeader();
        a.a(header.getField(MIME.CONTENT_DISPOSITION), this.a, outputStream);
        if (formBodyPart.getBody().getFilename() != null) {
            a.a(header.getField("Content-Type"), this.a, outputStream);
        }
    }
}
