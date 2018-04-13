package org.apache.commons.httpclient.auth;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.util.EncodingUtil;

final class NTLM {
    public static final String DEFAULT_CHARSET = "ASCII";
    private String credentialCharset = DEFAULT_CHARSET;
    private int currentPosition = 0;
    private byte[] currentResponse;

    NTLM() {
    }

    public final String getResponseFor(String str, String str2, String str3, String str4, String str5) throws AuthenticationException {
        if (str == null || str.trim().equals("")) {
            return getType1Message(str4, str5);
        }
        return getType3Message(str2, str3, str4, str5, parseType2Message(str));
    }

    private Cipher getCipher(byte[] bArr) throws AuthenticationException {
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, new SecretKeySpec(setupKey(bArr), "DES"));
            return instance;
        } catch (Throwable e) {
            throw new AuthenticationException("DES encryption is not available.", e);
        } catch (Throwable e2) {
            throw new AuthenticationException("Invalid key for DES encryption.", e2);
        } catch (Throwable e22) {
            throw new AuthenticationException("NoPadding option for DES is not available.", e22);
        }
    }

    private byte[] setupKey(byte[] bArr) {
        int i = 0;
        byte[] bArr2 = new byte[]{(byte) ((bArr[0] >> 1) & 255), (byte) ((((bArr[0] & 1) << 6) | (((bArr[1] & 255) >> 2) & 255)) & 255), (byte) ((((bArr[1] & 3) << 5) | (((bArr[2] & 255) >> 3) & 255)) & 255), (byte) ((((bArr[2] & 7) << 4) | (((bArr[3] & 255) >> 4) & 255)) & 255), (byte) ((((bArr[3] & 15) << 3) | (((bArr[4] & 255) >> 5) & 255)) & 255), (byte) ((((bArr[4] & 31) << 2) | (((bArr[5] & 255) >> 6) & 255)) & 255), (byte) ((((bArr[5] & 63) << 1) | (((bArr[6] & 255) >> 7) & 255)) & 255), (byte) (bArr[6] & 127)};
        while (i < bArr2.length) {
            bArr2[i] = (byte) (bArr2[i] << 1);
            i++;
        }
        return bArr2;
    }

    private byte[] encrypt(byte[] bArr, byte[] bArr2) throws AuthenticationException {
        try {
            return getCipher(bArr).doFinal(bArr2);
        } catch (Throwable e) {
            throw new AuthenticationException("Invalid block size for DES encryption.", e);
        } catch (Throwable e2) {
            throw new AuthenticationException("Data not padded correctly for DES encryption.", e2);
        }
    }

    private void prepareResponse(int i) {
        this.currentResponse = new byte[i];
        this.currentPosition = 0;
    }

    private void addByte(byte b) {
        this.currentResponse[this.currentPosition] = b;
        this.currentPosition++;
    }

    private void addBytes(byte[] bArr) {
        for (byte b : bArr) {
            this.currentResponse[this.currentPosition] = b;
            this.currentPosition++;
        }
    }

    private String getResponse() {
        byte[] bArr;
        if (this.currentResponse.length > this.currentPosition) {
            byte[] bArr2 = new byte[this.currentPosition];
            for (int i = 0; i < this.currentPosition; i++) {
                bArr2[i] = this.currentResponse[i];
            }
            bArr = bArr2;
        } else {
            bArr = this.currentResponse;
        }
        return EncodingUtil.getAsciiString(Base64.encodeBase64(bArr));
    }

    public String getType1Message(String str, String str2) {
        String toUpperCase = str.toUpperCase();
        String toUpperCase2 = str2.toUpperCase();
        byte[] bytes = EncodingUtil.getBytes(toUpperCase, DEFAULT_CHARSET);
        byte[] bytes2 = EncodingUtil.getBytes(toUpperCase2, DEFAULT_CHARSET);
        prepareResponse((bytes.length + 32) + bytes2.length);
        addBytes(EncodingUtil.getBytes("NTLMSSP", DEFAULT_CHARSET));
        addByte((byte) 0);
        addByte((byte) 1);
        addByte((byte) 0);
        addByte((byte) 0);
        addByte((byte) 0);
        addByte((byte) 6);
        addByte((byte) 82);
        addByte((byte) 0);
        addByte((byte) 0);
        byte[] convertShort = convertShort(bytes2.length);
        addByte(convertShort[0]);
        addByte(convertShort[1]);
        addByte(convertShort[0]);
        addByte(convertShort[1]);
        convertShort = convertShort(bytes.length + 32);
        addByte(convertShort[0]);
        addByte(convertShort[1]);
        addByte((byte) 0);
        addByte((byte) 0);
        convertShort = convertShort(bytes.length);
        addByte(convertShort[0]);
        addByte(convertShort[1]);
        addByte(convertShort[0]);
        addByte(convertShort[1]);
        convertShort = convertShort(32);
        addByte(convertShort[0]);
        addByte(convertShort[1]);
        addByte((byte) 0);
        addByte((byte) 0);
        addBytes(bytes);
        addBytes(bytes2);
        return getResponse();
    }

    public byte[] parseType2Message(String str) {
        byte[] decodeBase64 = Base64.decodeBase64(EncodingUtil.getBytes(str, DEFAULT_CHARSET));
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = decodeBase64[i + 24];
        }
        return bArr;
    }

    public String getType3Message(String str, String str2, String str3, String str4, byte[] bArr) throws AuthenticationException {
        String toUpperCase = str4.toUpperCase();
        String toUpperCase2 = str3.toUpperCase();
        String toUpperCase3 = str.toUpperCase();
        byte[] bytes = EncodingUtil.getBytes(toUpperCase, DEFAULT_CHARSET);
        byte[] bytes2 = EncodingUtil.getBytes(toUpperCase2, DEFAULT_CHARSET);
        byte[] bytes3 = EncodingUtil.getBytes(toUpperCase3, this.credentialCharset);
        int length = bytes.length;
        int length2 = bytes2.length;
        int length3 = bytes3.length;
        int i = ((length + 88) + length3) + length2;
        prepareResponse(i);
        addBytes(EncodingUtil.getBytes("NTLMSSP", DEFAULT_CHARSET));
        addByte((byte) 0);
        addByte((byte) 3);
        addByte((byte) 0);
        addByte((byte) 0);
        addByte((byte) 0);
        addBytes(convertShort(24));
        addBytes(convertShort(24));
        addBytes(convertShort(i - 24));
        addByte((byte) 0);
        addByte((byte) 0);
        addBytes(convertShort(0));
        addBytes(convertShort(0));
        addBytes(convertShort(i));
        addByte((byte) 0);
        addByte((byte) 0);
        addBytes(convertShort(length));
        addBytes(convertShort(length));
        addBytes(convertShort(64));
        addByte((byte) 0);
        addByte((byte) 0);
        addBytes(convertShort(length3));
        addBytes(convertShort(length3));
        addBytes(convertShort(length + 64));
        addByte((byte) 0);
        addByte((byte) 0);
        addBytes(convertShort(length2));
        addBytes(convertShort(length2));
        addBytes(convertShort((length + 64) + length3));
        for (length = 0; length < 6; length++) {
            addByte((byte) 0);
        }
        addBytes(convertShort(i));
        addByte((byte) 0);
        addByte((byte) 0);
        addByte((byte) 6);
        addByte((byte) 82);
        addByte((byte) 0);
        addByte((byte) 0);
        addBytes(bytes);
        addBytes(bytes3);
        addBytes(bytes2);
        addBytes(hashPassword(str2, bArr));
        return getResponse();
    }

    private byte[] hashPassword(String str, byte[] bArr) throws AuthenticationException {
        int i = 7;
        byte[] bytes = EncodingUtil.getBytes(str.toUpperCase(), this.credentialCharset);
        byte[] bArr2 = new byte[7];
        byte[] bArr3 = new byte[7];
        int length = bytes.length;
        if (length > 7) {
            length = 7;
        }
        int i2 = 0;
        while (i2 < length) {
            bArr2[i2] = bytes[i2];
            i2++;
        }
        for (length = i2; length < 7; length++) {
            bArr2[length] = (byte) 0;
        }
        length = bytes.length;
        if (length > 14) {
            length = 14;
        }
        while (i < length) {
            bArr3[i - 7] = bytes[i];
            i++;
        }
        for (length = i; length < 14; length++) {
            bArr3[length - 7] = (byte) 0;
        }
        byte[] bArr4 = new byte[]{(byte) 75, (byte) 71, (byte) 83, (byte) 33, (byte) 64, (byte) 35, (byte) 36, (byte) 37};
        byte[] encrypt = encrypt(bArr2, bArr4);
        byte[] encrypt2 = encrypt(bArr3, bArr4);
        byte[] bArr5 = new byte[21];
        for (length = 0; length < encrypt.length; length++) {
            bArr5[length] = encrypt[length];
        }
        for (length = 0; length < encrypt2.length; length++) {
            bArr5[length + 8] = encrypt2[length];
        }
        for (length = 0; length < 5; length++) {
            bArr5[length + 16] = (byte) 0;
        }
        bArr4 = new byte[24];
        calcResp(bArr5, bArr, bArr4);
        return bArr4;
    }

    private void calcResp(byte[] bArr, byte[] bArr2, byte[] bArr3) throws AuthenticationException {
        int i;
        int i2 = 0;
        byte[] bArr4 = new byte[7];
        byte[] bArr5 = new byte[7];
        byte[] bArr6 = new byte[7];
        for (i = 0; i < 7; i++) {
            bArr4[i] = bArr[i];
        }
        for (i = 0; i < 7; i++) {
            bArr5[i] = bArr[i + 7];
        }
        for (i = 0; i < 7; i++) {
            bArr6[i] = bArr[i + 14];
        }
        bArr4 = encrypt(bArr4, bArr2);
        bArr5 = encrypt(bArr5, bArr2);
        bArr6 = encrypt(bArr6, bArr2);
        for (i = 0; i < 8; i++) {
            bArr3[i] = bArr4[i];
        }
        for (i = 0; i < 8; i++) {
            bArr3[i + 8] = bArr5[i];
        }
        while (i2 < 8) {
            bArr3[i2 + 16] = bArr6[i2];
            i2++;
        }
    }

    private byte[] convertShort(int i) {
        byte[] bArr = new byte[2];
        String num = Integer.toString(i, 16);
        while (num.length() < 4) {
            num = new StringBuffer().append("0").append(num).toString();
        }
        String substring = num.substring(2, 4);
        num = num.substring(0, 2);
        bArr[0] = (byte) Integer.parseInt(substring, 16);
        bArr[1] = (byte) Integer.parseInt(num, 16);
        return bArr;
    }

    public String getCredentialCharset() {
        return this.credentialCharset;
    }

    public void setCredentialCharset(String str) {
        this.credentialCharset = str;
    }
}
