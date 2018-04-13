package com.qiniu.resumableio;

import com.qiniu.auth.Authorizer;
import com.qiniu.conf.Conf;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.ChunkUploadCallRet;
import com.qiniu.rs.PutExtra;
import com.qiniu.utils.Crc32;
import com.qiniu.utils.InputStreamAt;
import com.qiniu.utils.InputStreamAt.Input;
import com.qiniu.utils.UploadTask;
import com.qiniu.utils.Util;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SliceUploadTask extends UploadTask {
    private List<Block> h;
    private volatile long i = 0;

    public static class Block {
        private final int a;
        private final String b;
        private final long c;
        private final String d;

        public Block(int i, String str, long j, String str2) {
            this.a = i;
            this.b = str;
            this.c = j;
            this.d = str2;
        }

        public int getIdx() {
            return this.a;
        }

        public String getCtx() {
            return this.b;
        }

        public String getHost() {
            return this.d;
        }

        public long getLength() {
            return this.c;
        }
    }

    static class a {
        private final int a;
        private Input b;
        private SliceUploadTask c;
        private volatile String d = Conf.UP_HOST;
        private volatile int e = 0;

        a(SliceUploadTask sliceUploadTask, int i, Input input) {
            this.c = sliceUploadTask;
            this.a = i;
            this.b = input;
        }

        public int getUpTotal() {
            return this.e;
        }

        ChunkUploadCallRet a() throws IOException {
            try {
                ChunkUploadCallRet b = b();
                return b;
            } finally {
                this.b = null;
                this.c = null;
                this.d = null;
            }
        }

        ChunkUploadCallRet b() throws IOException {
            int i = Conf.FIRST_CHUNK;
            int i2 = Conf.CHUNK_SIZE;
            int min = Math.min(this.a, i);
            ChunkUploadCallRet a = a((long) this.a, this.b.readNext(min));
            if (!a.isOk()) {
                return a;
            }
            a(min);
            if (this.a <= i) {
                return a;
            }
            int i3 = (((this.a - i) + i2) - 1) / i2;
            for (min = 0; min < i3; min++) {
                if (this.c.isCancelled()) {
                    return new ChunkUploadCallRet(-1, "", Conf.PROCESS_MSG);
                }
                int min2 = Math.min(this.a - ((i2 * min) + i), i2);
                a = a(a, this.b.readNext(min2));
                if (!a.isOk()) {
                    return a;
                }
                a(min2);
            }
            return a;
        }

        private void a(int i) {
            this.e += i;
            this.c.a((long) i);
        }

        private ChunkUploadCallRet a(long j, byte[] bArr) {
            return a(j, bArr, Conf.CHUNK_TRY_TIMES);
        }

        private ChunkUploadCallRet a(long j, byte[] bArr, int i) {
            CallRet a = a(a(j), bArr, i);
            if (!a.isOk()) {
                if (Util.needChangeUpAdress(a)) {
                    this.d = Conf.UP_HOST.equals(this.d) ? Conf.UP_HOST2 : Conf.UP_HOST;
                }
                if (i > 0) {
                    return a(j, bArr, i - 1);
                }
            }
            return a;
        }

        private ChunkUploadCallRet a(ChunkUploadCallRet chunkUploadCallRet, byte[] bArr) {
            return a(b(chunkUploadCallRet), bArr, Conf.CHUNK_TRY_TIMES);
        }

        private ChunkUploadCallRet a(String str, byte[] bArr, int i) {
            int i2 = -1;
            try {
                if (this.c.isCancelled()) {
                    int i3 = i - (Conf.CHUNK_TRY_TIMES * 2);
                    return new ChunkUploadCallRet(-1, "", Conf.PROCESS_MSG);
                }
                this.c.d = Util.newPost(str);
                this.c.d.setHeader("Authorization", "UpToken " + this.c.e.getUploadToken());
                this.c.d.setEntity(a(bArr));
                return a(str, bArr, i, new ChunkUploadCallRet(Util.handleResult(this.c.b().execute(this.c.d))));
            } catch (Exception e) {
                Exception exception = e;
                if (!this.c.isCancelled()) {
                    i2 = 0;
                }
                return new ChunkUploadCallRet(i2, exception);
            }
        }

        private HttpEntity a(byte[] bArr) throws IOException {
            return new ByteArrayEntity(bArr);
        }

        private ChunkUploadCallRet a(String str, byte[] bArr, int i, ChunkUploadCallRet chunkUploadCallRet) throws IOException {
            if (!chunkUploadCallRet.isOk()) {
                return (i <= 0 || !a(chunkUploadCallRet)) ? chunkUploadCallRet : a(str, bArr, i - 1);
            } else {
                if (chunkUploadCallRet.getCrc32() == Crc32.calc(bArr)) {
                    return chunkUploadCallRet;
                }
                if (i > 0) {
                    return a(str, bArr, i - 1);
                }
                return new ChunkUploadCallRet(0, "", "local's crc32 do not match.");
            }
        }

        private boolean a(ChunkUploadCallRet chunkUploadCallRet) {
            return chunkUploadCallRet.getStatusCode() == 406 || chunkUploadCallRet.getStatusCode() == 996 || chunkUploadCallRet.getStatusCode() / 100 == 5;
        }

        private String a(long j) {
            return this.d + "/mkblk/" + j;
        }

        private String b(ChunkUploadCallRet chunkUploadCallRet) {
            return chunkUploadCallRet.getHost() + "/bput/" + chunkUploadCallRet.getCtx() + MqttTopic.TOPIC_LEVEL_SEPARATOR + chunkUploadCallRet.getOffset();
        }
    }

    public SliceUploadTask(Authorizer authorizer, InputStreamAt inputStreamAt, String str, PutExtra putExtra, CallBack callBack) throws IOException {
        super(authorizer, inputStreamAt, str, putExtra, callBack);
    }

    public void setLastUploadBlocks(List<Block> list) {
        this.h = list;
    }

    protected CallRet a(Object... objArr) {
        try {
            List arrayList = new ArrayList();
            CallRet a = a(arrayList);
            if (a == null) {
                return b(arrayList);
            }
            return a;
        } catch (Exception e) {
            return new CallRet(0, "", e);
        }
    }

    protected void a() {
        super.a();
        this.h = null;
    }

    private CallRet a(List<Block> list) throws IOException {
        long length = this.f.length();
        int i = (int) (((length + 4194304) - 1) / 4194304);
        for (int i2 = 0; i2 < i; i2++) {
            int min = (int) Math.min(4194304, length - ((long) (4194304 * i2)));
            Block a = a(i2);
            Input readNext = this.f.readNext(min);
            if (a != null) {
                list.add(a);
                a((long) min);
            } else {
                CallRet a2 = a(min, readNext, Conf.CHUNK_TRY_TIMES);
                if (!a2.isOk()) {
                    return a2;
                }
                list.add(new Block(i2, a2.getCtx(), (long) min, a2.getHost()));
                publishProgress(new Object[]{r1});
            }
        }
        return null;
    }

    private ChunkUploadCallRet a(int i, Input input, int i2) throws IOException {
        a aVar = new a(this, i, input);
        ChunkUploadCallRet a = aVar.a();
        if (a.isOk()) {
            return a;
        }
        a((long) (-aVar.getUpTotal()));
        if (a.getStatusCode() != 701 || i2 <= 0) {
            return a;
        }
        return a(i, input, i2 - 1);
    }

    private void a(long j) {
        this.i += j;
        publishProgress(new Object[]{Long.valueOf(this.i), Long.valueOf(this.b)});
    }

    private Block a(int i) {
        if (this.h == null) {
            return null;
        }
        for (Block block : this.h) {
            if (i == block.getIdx()) {
                return block;
            }
        }
        return null;
    }

    private CallRet b(List<Block> list) {
        return a(c((List) list), ((Block) list.get(list.size() - 1)).getHost(), Conf.CHUNK_TRY_TIMES + 1);
    }

    private String c(List<Block> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Block ctx : list) {
            stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP).append(ctx.getCtx());
        }
        return stringBuffer.substring(1);
    }

    private CallRet a(String str, String str2, int i) {
        try {
            HttpUriRequest newPost = Util.newPost(a(str2));
            newPost.setHeader("Authorization", "UpToken " + this.e.getUploadToken());
            newPost.setEntity(new StringEntity(str));
            CallRet handleResult = Util.handleResult(b().execute(newPost));
            if (handleResult.getStatusCode() == 579 || handleResult.getStatusCode() / 100 != 5 || i <= 0) {
                return handleResult;
            }
            return a(str, str2, i - 1);
        } catch (Throwable e) {
            if (i > 0) {
                return a(str, str2, i - 1);
            }
            throw new RuntimeException(e);
        }
    }

    private String a(String str) {
        StringBuilder stringBuilder = new StringBuilder(str + "/mkfile/" + this.b);
        if (this.c != null) {
            stringBuilder.append("/key/").append(Util.urlsafeBase64(this.c));
        }
        if (!(this.g.mimeType == null || this.g.mimeType.trim().length() == 0)) {
            stringBuilder.append("/mimeType/").append(Util.urlsafeBase64(this.g.mimeType));
        }
        if (this.g.params != null) {
            for (Entry entry : this.g.params.entrySet()) {
                if (((String) entry.getKey()).startsWith("x:")) {
                    stringBuilder.append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append((String) entry.getKey()).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(Util.urlsafeBase64((String) entry.getValue()));
                }
            }
        }
        return stringBuilder.toString();
    }
}
