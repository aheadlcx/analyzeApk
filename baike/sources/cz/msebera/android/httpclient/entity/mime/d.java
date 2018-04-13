package cz.msebera.android.httpclient.entity.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

class d extends a {
    private final List<FormBodyPart> c;

    public d(Charset charset, String str, List<FormBodyPart> list) {
        super(charset, str);
        this.c = list;
    }

    public List<FormBodyPart> getBodyParts() {
        return this.c;
    }

    protected void a(FormBodyPart formBodyPart, OutputStream outputStream) throws IOException {
        Iterator it = formBodyPart.getHeader().iterator();
        while (it.hasNext()) {
            a.a((MinimalField) it.next(), outputStream);
        }
    }
}
