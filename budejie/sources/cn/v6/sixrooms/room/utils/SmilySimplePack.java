package cn.v6.sixrooms.room.utils;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import cn.v6.sixrooms.utils.LogUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SmilySimplePack {
    int a;
    FileChannel b;
    Map<String, a> c = new HashMap();
    Map<String, byte[]> d = new HashMap();

    class a {
        public int a;
        public int b;
        final /* synthetic */ SmilySimplePack c;

        a(SmilySimplePack smilySimplePack) {
            this.c = smilySimplePack;
        }
    }

    private static int a(ByteBuffer byteBuffer) {
        return (((byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8)) | ((byteBuffer.get() & 255) << 16)) | ((byteBuffer.get() & 255) << 24);
    }

    public SmilySimplePack(AssetManager assetManager) throws IOException {
        try {
            AssetFileDescriptor openFd = assetManager.openFd("smilys.jpg");
            this.a = (int) openFd.getStartOffset();
            this.b = openFd.createInputStream().getChannel();
            int length = (int) openFd.getLength();
            ByteBuffer allocate = ByteBuffer.allocate(4);
            this.b.read(allocate, (long) ((length - 4) + this.a));
            allocate.position(0);
            int i = length - 4;
            length = a(allocate);
            while (length < i) {
                allocate = ByteBuffer.allocate(16);
                this.b.read(allocate, (long) (this.a + length));
                allocate.position(0);
                int a = a(allocate);
                if (a != 0) {
                    a aVar = new a(this);
                    allocate.position(8);
                    aVar.a = a(allocate);
                    aVar.b = a(allocate);
                    ByteBuffer allocate2 = ByteBuffer.allocate(a - 16);
                    this.b.read(allocate2, (long) ((length + 16) + this.a));
                    allocate2.position(0);
                    String str = "";
                    while (true) {
                        char c = (char) ((allocate2.get() & 255) | ((allocate2.get() & 255) << 8));
                        if (c == '\u0000') {
                            break;
                        }
                        str = str + c;
                    }
                    this.c.put(str, aVar);
                    length += a;
                    if (!str.contains("original")) {
                        ByteBuffer allocate3 = ByteBuffer.allocate(aVar.b);
                        this.b.read(allocate3, (long) (aVar.a + this.a));
                        this.d.put(str, allocate3.array());
                    }
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
        if (str.contains("original")) {
            if (!this.c.containsKey(str)) {
                return null;
            }
            try {
                a aVar = (a) this.c.get(str);
                ByteBuffer allocate = ByteBuffer.allocate(aVar.b);
                this.b.read(allocate, (long) (aVar.a + this.a));
                return allocate.array();
            } catch (IOException e) {
                e.printStackTrace();
                LogUtils.i("huzhi", "表情获取出现问题！！" + e.toString());
                return null;
            }
        } else if (this.d.containsKey(str)) {
            return (byte[]) this.d.get(str);
        } else {
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
