package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.StringUtils;

abstract class RFC1522Codec {
    protected static final String POSTFIX = "?=";
    protected static final String PREFIX = "=?";
    protected static final char SEP = '?';

    protected abstract byte[] doDecoding(byte[] bArr) throws DecoderException;

    protected abstract byte[] doEncoding(byte[] bArr) throws EncoderException;

    protected abstract String getEncoding();

    RFC1522Codec() {
    }

    protected String encodeText(String str, String str2) throws EncoderException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(PREFIX);
        stringBuffer.append(str2);
        stringBuffer.append(SEP);
        stringBuffer.append(getEncoding());
        stringBuffer.append(SEP);
        stringBuffer.append(StringUtils.newStringUsAscii(doEncoding(str.getBytes(str2))));
        stringBuffer.append(POSTFIX);
        return stringBuffer.toString();
    }

    protected String decodeText(String str) throws DecoderException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        if (str.startsWith(PREFIX) && str.endsWith(POSTFIX)) {
            int length = str.length() - 2;
            int indexOf = str.indexOf(63, 2);
            if (indexOf == length) {
                throw new DecoderException("RFC 1522 violation: charset token not found");
            }
            String substring = str.substring(2, indexOf);
            if (substring.equals("")) {
                throw new DecoderException("RFC 1522 violation: charset not specified");
            }
            indexOf++;
            int indexOf2 = str.indexOf(63, indexOf);
            if (indexOf2 == length) {
                throw new DecoderException("RFC 1522 violation: encoding token not found");
            }
            String substring2 = str.substring(indexOf, indexOf2);
            if (getEncoding().equalsIgnoreCase(substring2)) {
                length = indexOf2 + 1;
                return new String(doDecoding(StringUtils.getBytesUsAscii(str.substring(length, str.indexOf(63, length)))), substring);
            }
            throw new DecoderException(new StringBuffer().append("This codec cannot decode ").append(substring2).append(" encoded content").toString());
        }
        throw new DecoderException("RFC 1522 violation: malformed encoded content");
    }
}
