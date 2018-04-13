package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.cache.InputLimit;
import cz.msebera.android.httpclient.client.cache.Resource;
import cz.msebera.android.httpclient.client.cache.ResourceFactory;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;

@NotThreadSafe
class ac {
    private final ResourceFactory a;
    private final long b;
    private final HttpRequest c;
    private final CloseableHttpResponse d;
    private InputStream e;
    private InputLimit f;
    private Resource g;
    private boolean h;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0017 in list [B:7:0x003c]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = this;
        r4.e();
        r0 = 1;
        r4.h = r0;
        r0 = new cz.msebera.android.httpclient.client.cache.InputLimit;
        r2 = r4.b;
        r0.<init>(r2);
        r4.f = r0;
        r0 = r4.d;
        r0 = r0.getEntity();
        if (r0 != 0) goto L_0x0018;
    L_0x0017:
        return;
    L_0x0018:
        r1 = r4.c;
        r1 = r1.getRequestLine();
        r1 = r1.getUri();
        r0 = r0.getContent();
        r4.e = r0;
        r0 = r4.a;	 Catch:{ all -> 0x0042 }
        r2 = r4.e;	 Catch:{ all -> 0x0042 }
        r3 = r4.f;	 Catch:{ all -> 0x0042 }
        r0 = r0.generate(r1, r2, r3);	 Catch:{ all -> 0x0042 }
        r4.g = r0;	 Catch:{ all -> 0x0042 }
        r0 = r4.f;
        r0 = r0.isReached();
        if (r0 != 0) goto L_0x0017;
    L_0x003c:
        r0 = r4.e;
        r0.close();
        goto L_0x0017;
    L_0x0042:
        r0 = move-exception;
        r1 = r4.f;
        r1 = r1.isReached();
        if (r1 != 0) goto L_0x0050;
    L_0x004b:
        r1 = r4.e;
        r1.close();
    L_0x0050:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.client.cache.ac.g():void");
    }

    public ac(ResourceFactory resourceFactory, long j, HttpRequest httpRequest, CloseableHttpResponse closeableHttpResponse) {
        this.a = resourceFactory;
        this.b = j;
        this.c = httpRequest;
        this.d = closeableHttpResponse;
    }

    protected void a() throws IOException {
        if (!this.h) {
            g();
        }
    }

    private void e() {
        if (this.h) {
            throw new IllegalStateException("Response has already been consumed");
        }
    }

    private void f() {
        if (!this.h) {
            throw new IllegalStateException("Response has not been consumed");
        }
    }

    boolean b() {
        f();
        return this.f.isReached();
    }

    Resource c() {
        f();
        return this.g;
    }

    CloseableHttpResponse d() throws IOException {
        f();
        HttpResponse basicHttpResponse = new BasicHttpResponse(this.d.getStatusLine());
        basicHttpResponse.setHeaders(this.d.getAllHeaders());
        HttpEntity pVar = new p(this.g, this.e);
        HttpEntity entity = this.d.getEntity();
        if (entity != null) {
            pVar.setContentType(entity.getContentType());
            pVar.setContentEncoding(entity.getContentEncoding());
            pVar.setChunked(entity.isChunked());
        }
        basicHttpResponse.setEntity(pVar);
        return (CloseableHttpResponse) Proxy.newProxyInstance(ab.class.getClassLoader(), new Class[]{CloseableHttpResponse.class}, new ad(this, basicHttpResponse));
    }
}
