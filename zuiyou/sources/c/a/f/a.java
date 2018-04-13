package c.a.f;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class a extends d {
    protected String b(Context context, String str) {
        return c(context, str);
    }

    public String a(Context context, String str, int i) {
        return null;
    }

    public int a() {
        return 0;
    }

    private String c(Context context, String str) {
        String absolutePath = new File(c.a.h.a.a(context), str).getAbsolutePath();
        try {
            InputStream open = context.getAssets().open("skins" + File.separator + str);
            OutputStream fileOutputStream = new FileOutputStream(absolutePath);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return absolutePath;
    }
}
