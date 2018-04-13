package cn.v6.sixrooms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BigGiftUtil {
    private static final String d = BigGiftUtil.class.getSimpleName();
    int a;
    FileChannel b;
    Map<String, a> c = new HashMap();

    class a {
        public int a;
        public int b;
        final /* synthetic */ BigGiftUtil c;

        a(BigGiftUtil bigGiftUtil) {
            this.c = bigGiftUtil;
        }
    }

    private static int a(ByteBuffer byteBuffer) {
        return (((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8)) | ((byteBuffer.get() & 255) << 16)) | ((byteBuffer.get() & 255) << 24);
    }

    public BigGiftUtil(String str, int i) throws IOException {
        try {
            this.a = 0;
            this.b = new FileInputStream(new File(str)).getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(4);
            this.b.read(allocate, (long) ((i - 4) + this.a));
            allocate.position(0);
            int i2 = i - 4;
            int a = a(allocate);
            while (a < i2) {
                allocate = ByteBuffer.allocate(16);
                this.b.read(allocate, (long) (this.a + a));
                allocate.position(0);
                int a2 = a(allocate);
                if (a2 != 0) {
                    a aVar = new a(this);
                    allocate.position(8);
                    aVar.a = a(allocate);
                    aVar.b = a(allocate);
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(a2 - 16);
                    this.b.read(allocateDirect, (long) ((a + 16) + this.a));
                    allocateDirect.position(0);
                    String str2 = "";
                    while (true) {
                        char c = (char) ((allocateDirect.get() & 255) | ((allocateDirect.get() & 255) << 8));
                        if (c == '\u0000') {
                            break;
                        }
                        str2 = str2 + c;
                    }
                    this.c.put(str2, aVar);
                    a += a2;
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            delete();
            throw e;
        }
    }

    public byte[] ReadFile(String str) {
        if (!this.c.containsKey(str)) {
            return null;
        }
        try {
            a aVar = (a) this.c.get(str);
            ByteBuffer allocate = ByteBuffer.allocate(aVar.b);
            this.b.read(allocate, (long) (aVar.a + this.a));
            return allocate.array();
        } catch (IOException e) {
            return null;
        }
    }

    public Set<String> GetFileNames() {
        return this.c.keySet();
    }

    public void delete() {
        try {
            if (this.b != null) {
                this.b.close();
                this.b = null;
            }
        } catch (IOException e) {
        }
    }
}
