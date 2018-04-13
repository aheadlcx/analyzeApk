package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.BitSet;

@Immutable
public class TokenParser {
    public static final char CR = '\r';
    public static final char DQUOTE = '\"';
    public static final char ESCAPE = '\\';
    public static final char HT = '\t';
    public static final TokenParser INSTANCE = new TokenParser();
    public static final char LF = '\n';
    public static final char SP = ' ';

    public static BitSet INIT_BITSET(int... iArr) {
        BitSet bitSet = new BitSet();
        for (int i : iArr) {
            bitSet.set(i);
        }
        return bitSet;
    }

    public static boolean isWhitespace(char c) {
        return c == SP || c == '\t' || c == CR || c == '\n';
    }

    public String parseToken(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        while (!parserCursor.atEnd()) {
            char charAt = charArrayBuffer.charAt(parserCursor.getPos());
            if (bitSet != null && bitSet.get(charAt)) {
                break;
            } else if (isWhitespace(charAt)) {
                skipWhiteSpace(charArrayBuffer, parserCursor);
                obj = 1;
            } else {
                if (obj != null && stringBuilder.length() > 0) {
                    stringBuilder.append(SP);
                }
                copyContent(charArrayBuffer, parserCursor, bitSet, stringBuilder);
                obj = null;
            }
        }
        return stringBuilder.toString();
    }

    public String parseValue(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        while (!parserCursor.atEnd()) {
            char charAt = charArrayBuffer.charAt(parserCursor.getPos());
            if (bitSet != null && bitSet.get(charAt)) {
                break;
            } else if (isWhitespace(charAt)) {
                skipWhiteSpace(charArrayBuffer, parserCursor);
                obj = 1;
            } else if (charAt == '\"') {
                if (obj != null && stringBuilder.length() > 0) {
                    stringBuilder.append(SP);
                }
                copyQuotedContent(charArrayBuffer, parserCursor, stringBuilder);
                obj = null;
            } else {
                if (obj != null && stringBuilder.length() > 0) {
                    stringBuilder.append(SP);
                }
                copyUnquotedContent(charArrayBuffer, parserCursor, bitSet, stringBuilder);
                obj = null;
            }
        }
        return stringBuilder.toString();
    }

    public void skipWhiteSpace(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        int pos = parserCursor.getPos();
        int pos2 = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        while (pos2 < upperBound && isWhitespace(charArrayBuffer.charAt(pos2))) {
            pos++;
            pos2++;
        }
        parserCursor.updatePos(pos);
    }

    public void copyContent(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet, StringBuilder stringBuilder) {
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        for (int pos2 = parserCursor.getPos(); pos2 < upperBound; pos2++) {
            char charAt = charArrayBuffer.charAt(pos2);
            if ((bitSet != null && bitSet.get(charAt)) || isWhitespace(charAt)) {
                break;
            }
            pos++;
            stringBuilder.append(charAt);
        }
        parserCursor.updatePos(pos);
    }

    public void copyUnquotedContent(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, BitSet bitSet, StringBuilder stringBuilder) {
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        for (int pos2 = parserCursor.getPos(); pos2 < upperBound; pos2++) {
            char charAt = charArrayBuffer.charAt(pos2);
            if ((bitSet != null && bitSet.get(charAt)) || isWhitespace(charAt) || charAt == '\"') {
                break;
            }
            pos++;
            stringBuilder.append(charAt);
        }
        parserCursor.updatePos(pos);
    }

    public void copyQuotedContent(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, StringBuilder stringBuilder) {
        if (!parserCursor.atEnd()) {
            int pos = parserCursor.getPos();
            int pos2 = parserCursor.getPos();
            int upperBound = parserCursor.getUpperBound();
            if (charArrayBuffer.charAt(pos) == '\"') {
                int i = pos + 1;
                pos2++;
                Object obj = null;
                while (pos2 < upperBound) {
                    char charAt = charArrayBuffer.charAt(pos2);
                    if (obj != null) {
                        if (!(charAt == '\"' || charAt == ESCAPE)) {
                            stringBuilder.append(ESCAPE);
                        }
                        stringBuilder.append(charAt);
                        obj = null;
                    } else if (charAt == '\"') {
                        pos = i + 1;
                        break;
                    } else if (charAt == ESCAPE) {
                        obj = 1;
                    } else if (!(charAt == CR || charAt == '\n')) {
                        stringBuilder.append(charAt);
                    }
                    pos2++;
                    i++;
                }
                pos = i;
                parserCursor.updatePos(pos);
            }
        }
    }
}
