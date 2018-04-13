package cn.xiaochuankeji.tieba.d;

import java.io.IOException;
import java.io.StringReader;
import org.a.a.a.g;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class f {
    public static final org.a.a.a.f a = new org.a.a.a.f();

    public static void a(String str, ContentHandler contentHandler) {
        g gVar = new g();
        try {
            gVar.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", a);
            gVar.setContentHandler(contentHandler);
            gVar.parse(new InputSource(new StringReader(str)));
        } catch (SAXNotRecognizedException e) {
            e.printStackTrace();
        } catch (SAXNotSupportedException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (SAXException e4) {
            e4.printStackTrace();
        }
    }
}
