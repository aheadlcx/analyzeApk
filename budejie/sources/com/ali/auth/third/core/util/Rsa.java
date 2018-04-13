package com.ali.auth.third.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class Rsa {
    private static final String ALGORITHM = "RSA";
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    private static PublicKey getPublicKeyFromX509(String str, String str2) throws NoSuchAlgorithmException, Exception {
        return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(Base64.decode(str2)));
    }

    public static String encrypt(String str, String str2) {
        Exception e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            Key publicKeyFromX509 = getPublicKeyFromX509(ALGORITHM, str2);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, publicKeyFromX509);
            byte[] bytes = str.getBytes("UTF-8");
            int blockSize = instance.getBlockSize();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bytes.length) {
                try {
                    int length;
                    if (bytes.length - i < blockSize) {
                        length = bytes.length - i;
                    } else {
                        length = blockSize;
                    }
                    byteArrayOutputStream.write(instance.doFinal(bytes, i, length));
                    i += blockSize;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            String str3 = new String(Base64.encode(byteArrayOutputStream.toByteArray()));
            if (byteArrayOutputStream == null) {
                return str3;
            }
            try {
                byteArrayOutputStream.close();
                return str3;
            } catch (IOException e3) {
                e3.printStackTrace();
                return str3;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = null;
            try {
                e.printStackTrace();
                if (byteArrayOutputStream == null) {
                    return null;
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static String decrypt(String str, String str2) {
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            Key generatePrivate = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str2)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, generatePrivate);
            byte[] decode = Base64.decode(str);
            int blockSize = instance.getBlockSize();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < decode.length) {
                try {
                    int length;
                    if (decode.length - i < blockSize) {
                        length = decode.length - i;
                    } else {
                        length = blockSize;
                    }
                    byteArrayOutputStream.write(instance.doFinal(decode, i, length));
                    i += blockSize;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            String str3 = new String(byteArrayOutputStream.toByteArray());
            if (byteArrayOutputStream == null) {
                return str3;
            }
            try {
                byteArrayOutputStream.close();
                return str3;
            } catch (IOException e3) {
                e3.printStackTrace();
                return str3;
            }
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = null;
            try {
                e.printStackTrace();
                if (byteArrayOutputStream == null) {
                    return null;
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            throw th;
        }
    }

    public static String sign(String str, String str2) {
        String str3 = "utf-8";
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str2)));
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initSign(generatePrivate);
            instance.update(str.getBytes(str3));
            return Base64.encode(instance.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean doCheck(String str, String str2, String str3) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(str3)));
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initVerify(generatePublic);
            instance.update(str.getBytes("utf-8"));
            return instance.verify(Base64.decode(str2));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
