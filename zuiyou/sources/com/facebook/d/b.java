package com.facebook.d;

import com.facebook.common.internal.g;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.io.InputStream;

public class b {
    public static int a(int i) {
        return d.a(i);
    }

    public static int a(InputStream inputStream) {
        int i = 0;
        try {
            int b = b(inputStream);
            if (b != 0) {
                i = d.a(inputStream, b);
            }
        } catch (IOException e) {
        }
        return i;
    }

    public static boolean a(InputStream inputStream, int i) throws IOException {
        g.a((Object) inputStream);
        while (c.a(inputStream, 1, false) == 255) {
            int i2 = 255;
            while (i2 == 255) {
                i2 = c.a(inputStream, 1, false);
            }
            if ((i == 192 && b(i2)) || i2 == i) {
                return true;
            }
            if (!(i2 == Opcodes.ADD_INT_LIT8 || i2 == 1)) {
                if (i2 == Opcodes.RSUB_INT_LIT8 || i2 == Opcodes.MUL_INT_LIT8) {
                    return false;
                }
                inputStream.skip((long) (c.a(inputStream, 2, false) - 2));
            }
        }
        return false;
    }

    private static boolean b(int i) {
        switch (i) {
            case 192:
            case 193:
            case 194:
            case 195:
            case 197:
            case 198:
            case 199:
            case 201:
            case 202:
            case 203:
            case 205:
            case 206:
            case 207:
                return true;
            default:
                return false;
        }
    }

    private static int b(InputStream inputStream) throws IOException {
        if (a(inputStream, Opcodes.SHR_INT_LIT8)) {
            int a = c.a(inputStream, 2, false) - 2;
            if (a > 6) {
                int a2 = c.a(inputStream, 4, false);
                a -= 4;
                int a3 = c.a(inputStream, 2, false);
                a -= 2;
                if (a2 == 1165519206 && a3 == 0) {
                    return a;
                }
            }
        }
        return 0;
    }
}
