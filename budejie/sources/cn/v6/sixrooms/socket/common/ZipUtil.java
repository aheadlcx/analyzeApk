package cn.v6.sixrooms.socket.common;

import cn.v6.sdk.sixrooms.base.Base64;
import com.ali.auth.third.login.LoginConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipUtil {
    public static byte[] inflate(byte[] bArr) throws DataFormatException, IOException {
        Throwable th;
        Inflater inflater = new Inflater(true);
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            inflater.setInput(bArr);
            byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            try {
                byte[] bArr2 = new byte[1024];
                while (!inflater.finished()) {
                    int inflate = inflater.inflate(bArr2);
                    byteArrayOutputStream.write(bArr2, 0, inflate);
                    if (inflate == 0) {
                        break;
                    }
                }
                byteArrayOutputStream.close();
                bArr2 = byteArrayOutputStream.toByteArray();
                inflater.end();
                return bArr2;
            } catch (Throwable th2) {
                th = th2;
                inflater.end();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            inflater.end();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static byte[] compress(byte[] bArr) {
        Deflater deflater = new Deflater(9, true);
        deflater.reset();
        deflater.setInput(bArr);
        deflater.finish();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        try {
            byte[] bArr2 = new byte[1024];
            while (!deflater.finished()) {
                byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
            }
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
        deflater.end();
        return bArr;
    }

    public static void main(String[] strArr) throws Exception {
        String str = new String(inflate(Base64.decodeBase64(new String(Base64.encodeBase64(compress("{\"flag\":\"001\",\"content\":{\"typeID\":\"1413\",\"content\":[{\"typeID\":1304,\"tm\":1371780782,\"fid\":\"37196161\",\"frid\":\"47582933\",\"from\":\"979099497\",\"content\":{\"num\":1}}]}}".getBytes()), false)).replaceAll("\\+", "(").replaceAll("/", ")").replaceAll(LoginConstants.EQUAL, "@").replaceAll("\\(", "+").replaceAll("\\)", "/").replaceAll("@", LoginConstants.EQUAL).getBytes())));
    }
}
