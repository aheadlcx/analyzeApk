package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class DoubleMetaphone implements StringEncoder {
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER = new String[]{"ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER"};
    private static final String[] L_R_N_M_B_H_F_V_W_SPACE = new String[]{"L", "R", "N", "M", "B", "H", "F", "V", "W", " "};
    private static final String[] L_T_K_S_N_M_B_Z = new String[]{"L", "T", "K", "S", "N", "M", "B", "Z"};
    private static final String[] SILENT_START = new String[]{"GN", "KN", "PN", "WR", "PS"};
    private static final String VOWELS = "AEIOUY";
    protected int maxCodeLen = 4;

    public class DoubleMetaphoneResult {
        private StringBuffer alternate = new StringBuffer(this.this$0.getMaxCodeLen());
        private int maxLength;
        private StringBuffer primary = new StringBuffer(this.this$0.getMaxCodeLen());
        private final DoubleMetaphone this$0;

        public DoubleMetaphoneResult(DoubleMetaphone doubleMetaphone, int i) {
            this.this$0 = doubleMetaphone;
            this.maxLength = i;
        }

        public void append(char c) {
            appendPrimary(c);
            appendAlternate(c);
        }

        public void append(char c, char c2) {
            appendPrimary(c);
            appendAlternate(c2);
        }

        public void appendPrimary(char c) {
            if (this.primary.length() < this.maxLength) {
                this.primary.append(c);
            }
        }

        public void appendAlternate(char c) {
            if (this.alternate.length() < this.maxLength) {
                this.alternate.append(c);
            }
        }

        public void append(String str) {
            appendPrimary(str);
            appendAlternate(str);
        }

        public void append(String str, String str2) {
            appendPrimary(str);
            appendAlternate(str2);
        }

        public void appendPrimary(String str) {
            int length = this.maxLength - this.primary.length();
            if (str.length() <= length) {
                this.primary.append(str);
            } else {
                this.primary.append(str.substring(0, length));
            }
        }

        public void appendAlternate(String str) {
            int length = this.maxLength - this.alternate.length();
            if (str.length() <= length) {
                this.alternate.append(str);
            } else {
                this.alternate.append(str.substring(0, length));
            }
        }

        public String getPrimary() {
            return this.primary.toString();
        }

        public String getAlternate() {
            return this.alternate.toString();
        }

        public boolean isComplete() {
            return this.primary.length() >= this.maxLength && this.alternate.length() >= this.maxLength;
        }
    }

    public String doubleMetaphone(String str) {
        return doubleMetaphone(str, false);
    }

    public String doubleMetaphone(String str, boolean z) {
        String cleanInput = cleanInput(str);
        if (cleanInput == null) {
            return null;
        }
        boolean isSlavoGermanic = isSlavoGermanic(cleanInput);
        int i = isSilentStart(cleanInput) ? 1 : 0;
        DoubleMetaphoneResult doubleMetaphoneResult = new DoubleMetaphoneResult(this, getMaxCodeLen());
        while (!doubleMetaphoneResult.isComplete() && i <= cleanInput.length() - 1) {
            switch (cleanInput.charAt(i)) {
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                case 'Y':
                    i = handleAEIOUY(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'B':
                    doubleMetaphoneResult.append('P');
                    if (charAt(cleanInput, i + 1) != 'B') {
                        i++;
                        break;
                    }
                    i += 2;
                    break;
                case 'C':
                    i = handleC(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'D':
                    i = handleD(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'F':
                    doubleMetaphoneResult.append('F');
                    if (charAt(cleanInput, i + 1) != 'F') {
                        i++;
                        break;
                    }
                    i += 2;
                    break;
                case 'G':
                    i = handleG(cleanInput, doubleMetaphoneResult, i, isSlavoGermanic);
                    break;
                case 'H':
                    i = handleH(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'J':
                    i = handleJ(cleanInput, doubleMetaphoneResult, i, isSlavoGermanic);
                    break;
                case 'K':
                    doubleMetaphoneResult.append('K');
                    if (charAt(cleanInput, i + 1) != 'K') {
                        i++;
                        break;
                    }
                    i += 2;
                    break;
                case 'L':
                    i = handleL(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'M':
                    doubleMetaphoneResult.append('M');
                    if (!conditionM0(cleanInput, i)) {
                        i++;
                        break;
                    }
                    i += 2;
                    break;
                case 'N':
                    doubleMetaphoneResult.append('N');
                    if (charAt(cleanInput, i + 1) != 'N') {
                        i++;
                        break;
                    }
                    i += 2;
                    break;
                case 'P':
                    i = handleP(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'Q':
                    doubleMetaphoneResult.append('K');
                    if (charAt(cleanInput, i + 1) != 'Q') {
                        i++;
                        break;
                    }
                    i += 2;
                    break;
                case 'R':
                    i = handleR(cleanInput, doubleMetaphoneResult, i, isSlavoGermanic);
                    break;
                case 'S':
                    i = handleS(cleanInput, doubleMetaphoneResult, i, isSlavoGermanic);
                    break;
                case 'T':
                    i = handleT(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'V':
                    doubleMetaphoneResult.append('F');
                    if (charAt(cleanInput, i + 1) != 'V') {
                        i++;
                        break;
                    }
                    i += 2;
                    break;
                case 'W':
                    i = handleW(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'X':
                    i = handleX(cleanInput, doubleMetaphoneResult, i);
                    break;
                case 'Z':
                    i = handleZ(cleanInput, doubleMetaphoneResult, i, isSlavoGermanic);
                    break;
                case 'Ç':
                    doubleMetaphoneResult.append('S');
                    i++;
                    break;
                case 'Ñ':
                    doubleMetaphoneResult.append('N');
                    i++;
                    break;
                default:
                    i++;
                    break;
            }
        }
        return z ? doubleMetaphoneResult.getAlternate() : doubleMetaphoneResult.getPrimary();
    }

    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return doubleMetaphone((String) obj);
        }
        throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
    }

    public String encode(String str) {
        return doubleMetaphone(str);
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2) {
        return isDoubleMetaphoneEqual(str, str2, false);
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2, boolean z) {
        return doubleMetaphone(str, z).equals(doubleMetaphone(str2, z));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int i) {
        this.maxCodeLen = i;
    }

    private int handleAEIOUY(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i == 0) {
            doubleMetaphoneResult.append('A');
        }
        return i + 1;
    }

    private int handleC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (conditionC0(str, i)) {
            doubleMetaphoneResult.append('K');
            return i + 2;
        } else if (i == 0 && contains(str, i, 6, "CAESAR")) {
            doubleMetaphoneResult.append('S');
            return i + 2;
        } else if (contains(str, i, 2, "CH")) {
            return handleCH(str, doubleMetaphoneResult, i);
        } else {
            if (contains(str, i, 2, "CZ") && !contains(str, i - 2, 4, "WICZ")) {
                doubleMetaphoneResult.append('S', 'X');
                return i + 2;
            } else if (contains(str, i + 1, 3, "CIA")) {
                doubleMetaphoneResult.append('X');
                return i + 3;
            } else if (contains(str, i, 2, "CC") && (i != 1 || charAt(str, 0) != 'M')) {
                return handleCC(str, doubleMetaphoneResult, i);
            } else {
                if (contains(str, i, 2, "CK", "CG", "CQ")) {
                    doubleMetaphoneResult.append('K');
                    return i + 2;
                }
                if (contains(str, i, 2, "CI", "CE", "CY")) {
                    if (contains(str, i, 3, "CIO", "CIE", "CIA")) {
                        doubleMetaphoneResult.append('S', 'X');
                    } else {
                        doubleMetaphoneResult.append('S');
                    }
                    return i + 2;
                }
                doubleMetaphoneResult.append('K');
                if (contains(str, i + 1, 2, " C", " Q", " G")) {
                    return i + 3;
                }
                if (!contains(str, i + 1, 1, "C", "K", "Q") || contains(str, i + 1, 2, "CE", "CI")) {
                    return i + 1;
                }
                return i + 2;
            }
        }
    }

    private int handleCC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (!contains(str, i + 2, 1, "I", "E", "H") || contains(str, i + 2, 2, "HU")) {
            doubleMetaphoneResult.append('K');
            return i + 2;
        }
        if ((i == 1 && charAt(str, i - 1) == 'A') || contains(str, i - 1, 5, "UCCEE", "UCCES")) {
            doubleMetaphoneResult.append("KS");
        } else {
            doubleMetaphoneResult.append('X');
        }
        return i + 3;
    }

    private int handleCH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i > 0 && contains(str, i, 4, "CHAE")) {
            doubleMetaphoneResult.append('K', 'X');
            return i + 2;
        } else if (conditionCH0(str, i)) {
            doubleMetaphoneResult.append('K');
            return i + 2;
        } else if (conditionCH1(str, i)) {
            doubleMetaphoneResult.append('K');
            return i + 2;
        } else {
            if (i <= 0) {
                doubleMetaphoneResult.append('X');
            } else if (contains(str, 0, 2, "MC")) {
                doubleMetaphoneResult.append('K');
            } else {
                doubleMetaphoneResult.append('X', 'K');
            }
            return i + 2;
        }
    }

    private int handleD(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 2, "DG")) {
            if (contains(str, i + 2, 1, "I", "E", "Y")) {
                doubleMetaphoneResult.append('J');
                return i + 3;
            }
            doubleMetaphoneResult.append("TK");
            return i + 2;
        } else if (contains(str, i, 2, "DT", "DD")) {
            doubleMetaphoneResult.append('T');
            return i + 2;
        } else {
            doubleMetaphoneResult.append('T');
            return i + 1;
        }
    }

    private int handleG(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (charAt(str, i + 1) == 'H') {
            return handleGH(str, doubleMetaphoneResult, i);
        }
        if (charAt(str, i + 1) == 'N') {
            if (i == 1 && isVowel(charAt(str, 0)) && !z) {
                doubleMetaphoneResult.append("KN", "N");
            } else if (contains(str, i + 2, 2, "EY") || charAt(str, i + 1) == 'Y' || z) {
                doubleMetaphoneResult.append("KN");
            } else {
                doubleMetaphoneResult.append("N", "KN");
            }
            return i + 2;
        } else if (contains(str, i + 1, 2, "LI") && !z) {
            doubleMetaphoneResult.append("KL", "L");
            return i + 2;
        } else if (i == 0 && (charAt(str, i + 1) == 'Y' || contains(str, i + 1, 2, ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER))) {
            doubleMetaphoneResult.append('K', 'J');
            return i + 2;
        } else {
            if (contains(str, i + 1, 2, "ER") || charAt(str, i + 1) == 'Y') {
                if (!(contains(str, 0, 6, "DANGER", "RANGER", "MANGER") || contains(str, i - 1, 1, "E", "I") || contains(str, i - 1, 3, "RGY", "OGY"))) {
                    doubleMetaphoneResult.append('K', 'J');
                    return i + 2;
                }
            }
            if (contains(str, i + 1, 1, "E", "I", "Y") || contains(str, i - 1, 4, "AGGI", "OGGI")) {
                if (contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH") || contains(str, i + 1, 2, "ET")) {
                    doubleMetaphoneResult.append('K');
                } else if (contains(str, i + 1, 3, "IER")) {
                    doubleMetaphoneResult.append('J');
                } else {
                    doubleMetaphoneResult.append('J', 'K');
                }
                return i + 2;
            } else if (charAt(str, i + 1) == 'G') {
                r0 = i + 2;
                doubleMetaphoneResult.append('K');
                return r0;
            } else {
                r0 = i + 1;
                doubleMetaphoneResult.append('K');
                return r0;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int handleGH(java.lang.String r11, org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult r12, int r13) {
        /*
        r10 = this;
        r9 = 73;
        r6 = 2;
        r8 = 75;
        r2 = 1;
        if (r13 <= 0) goto L_0x001a;
    L_0x0008:
        r0 = r13 + -1;
        r0 = r10.charAt(r11, r0);
        r0 = r10.isVowel(r0);
        if (r0 != 0) goto L_0x001a;
    L_0x0014:
        r12.append(r8);
        r0 = r13 + 2;
    L_0x0019:
        return r0;
    L_0x001a:
        if (r13 != 0) goto L_0x0030;
    L_0x001c:
        r0 = r13 + 2;
        r0 = r10.charAt(r11, r0);
        if (r0 != r9) goto L_0x002c;
    L_0x0024:
        r0 = 74;
        r12.append(r0);
    L_0x0029:
        r0 = r13 + 2;
        goto L_0x0019;
    L_0x002c:
        r12.append(r8);
        goto L_0x0029;
    L_0x0030:
        if (r13 <= r2) goto L_0x0041;
    L_0x0032:
        r1 = r13 + -2;
        r3 = "B";
        r4 = "H";
        r5 = "D";
        r0 = r11;
        r0 = contains(r0, r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x0061;
    L_0x0041:
        if (r13 <= r6) goto L_0x0052;
    L_0x0043:
        r1 = r13 + -3;
        r3 = "B";
        r4 = "H";
        r5 = "D";
        r0 = r11;
        r0 = contains(r0, r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x0061;
    L_0x0052:
        r0 = 3;
        if (r13 <= r0) goto L_0x0064;
    L_0x0055:
        r0 = r13 + -4;
        r1 = "B";
        r3 = "H";
        r0 = contains(r11, r0, r2, r1, r3);
        if (r0 == 0) goto L_0x0064;
    L_0x0061:
        r0 = r13 + 2;
        goto L_0x0019;
    L_0x0064:
        if (r13 <= r6) goto L_0x008b;
    L_0x0066:
        r0 = r13 + -1;
        r0 = r10.charAt(r11, r0);
        r1 = 85;
        if (r0 != r1) goto L_0x008b;
    L_0x0070:
        r1 = r13 + -3;
        r3 = "C";
        r4 = "G";
        r5 = "L";
        r6 = "R";
        r7 = "T";
        r0 = r11;
        r0 = contains(r0, r1, r2, r3, r4, r5, r6, r7);
        if (r0 == 0) goto L_0x008b;
    L_0x0083:
        r0 = 70;
        r12.append(r0);
    L_0x0088:
        r0 = r13 + 2;
        goto L_0x0019;
    L_0x008b:
        if (r13 <= 0) goto L_0x0088;
    L_0x008d:
        r0 = r13 + -1;
        r0 = r10.charAt(r11, r0);
        if (r0 == r9) goto L_0x0088;
    L_0x0095:
        r12.append(r8);
        goto L_0x0088;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.DoubleMetaphone.handleGH(java.lang.String, org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult, int):int");
    }

    private int handleH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if ((i != 0 && !isVowel(charAt(str, i - 1))) || !isVowel(charAt(str, i + 1))) {
            return i + 1;
        }
        doubleMetaphoneResult.append('H');
        return i + 2;
    }

    private int handleJ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (contains(str, i, 4, "JOSE") || contains(str, 0, 4, "SAN ")) {
            if ((i == 0 && charAt(str, i + 4) == ' ') || str.length() == 4 || contains(str, 0, 4, "SAN ")) {
                doubleMetaphoneResult.append('H');
            } else {
                doubleMetaphoneResult.append('J', 'H');
            }
            return i + 1;
        }
        if (i == 0 && !contains(str, i, 4, "JOSE")) {
            doubleMetaphoneResult.append('J', 'A');
        } else if (isVowel(charAt(str, i - 1)) && !z && (charAt(str, i + 1) == 'A' || charAt(str, i + 1) == 'O')) {
            doubleMetaphoneResult.append('J', 'H');
        } else if (i == str.length() - 1) {
            doubleMetaphoneResult.append('J', ' ');
        } else if (!contains(str, i + 1, 1, L_T_K_S_N_M_B_Z)) {
            if (!contains(str, i - 1, 1, "S", "K", "L")) {
                doubleMetaphoneResult.append('J');
            }
        }
        if (charAt(str, i + 1) == 'J') {
            return i + 2;
        }
        return i + 1;
    }

    private int handleL(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (charAt(str, i + 1) == 'L') {
            if (conditionL0(str, i)) {
                doubleMetaphoneResult.appendPrimary('L');
            } else {
                doubleMetaphoneResult.append('L');
            }
            return i + 2;
        }
        int i2 = i + 1;
        doubleMetaphoneResult.append('L');
        return i2;
    }

    private int handleP(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (charAt(str, i + 1) == 'H') {
            doubleMetaphoneResult.append('F');
            return i + 2;
        }
        doubleMetaphoneResult.append('P');
        return contains(str, i + 1, 1, "P", "B") ? i + 2 : i + 1;
    }

    private int handleR(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (i != str.length() - 1 || z || !contains(str, i - 2, 2, "IE") || contains(str, i - 4, 2, "ME", "MA")) {
            doubleMetaphoneResult.append('R');
        } else {
            doubleMetaphoneResult.appendAlternate('R');
        }
        return charAt(str, i + 1) == 'R' ? i + 2 : i + 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int handleS(java.lang.String r11, org.apache.commons.codec.language.DoubleMetaphone.DoubleMetaphoneResult r12, int r13, boolean r14) {
        /*
        r10 = this;
        r4 = 3;
        r9 = 2;
        r8 = 88;
        r2 = 1;
        r7 = 83;
        r0 = r13 + -1;
        r1 = "ISL";
        r3 = "YSL";
        r0 = contains(r11, r0, r4, r1, r3);
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r0 = r13 + 1;
    L_0x0015:
        return r0;
    L_0x0016:
        if (r13 != 0) goto L_0x0027;
    L_0x0018:
        r0 = 5;
        r1 = "SUGAR";
        r0 = contains(r11, r13, r0, r1);
        if (r0 == 0) goto L_0x0027;
    L_0x0021:
        r12.append(r8, r7);
        r0 = r13 + 1;
        goto L_0x0015;
    L_0x0027:
        r0 = "SH";
        r0 = contains(r11, r13, r9, r0);
        if (r0 == 0) goto L_0x004b;
    L_0x002f:
        r1 = r13 + 1;
        r2 = 4;
        r3 = "HEIM";
        r4 = "HOEK";
        r5 = "HOLM";
        r6 = "HOLZ";
        r0 = r11;
        r0 = contains(r0, r1, r2, r3, r4, r5, r6);
        if (r0 == 0) goto L_0x0047;
    L_0x0041:
        r12.append(r7);
    L_0x0044:
        r0 = r13 + 2;
        goto L_0x0015;
    L_0x0047:
        r12.append(r8);
        goto L_0x0044;
    L_0x004b:
        r0 = "SIO";
        r1 = "SIA";
        r0 = contains(r11, r13, r4, r0, r1);
        if (r0 != 0) goto L_0x005e;
    L_0x0055:
        r0 = 4;
        r1 = "SIAN";
        r0 = contains(r11, r13, r0, r1);
        if (r0 == 0) goto L_0x006a;
    L_0x005e:
        if (r14 == 0) goto L_0x0066;
    L_0x0060:
        r12.append(r7);
    L_0x0063:
        r0 = r13 + 3;
        goto L_0x0015;
    L_0x0066:
        r12.append(r7, r8);
        goto L_0x0063;
    L_0x006a:
        if (r13 != 0) goto L_0x007d;
    L_0x006c:
        r1 = r13 + 1;
        r3 = "M";
        r4 = "N";
        r5 = "L";
        r6 = "W";
        r0 = r11;
        r0 = contains(r0, r1, r2, r3, r4, r5, r6);
        if (r0 != 0) goto L_0x0087;
    L_0x007d:
        r0 = r13 + 1;
        r1 = "Z";
        r0 = contains(r11, r0, r2, r1);
        if (r0 == 0) goto L_0x009c;
    L_0x0087:
        r12.append(r7, r8);
        r0 = r13 + 1;
        r1 = "Z";
        r0 = contains(r11, r0, r2, r1);
        if (r0 == 0) goto L_0x0098;
    L_0x0094:
        r0 = r13 + 2;
        goto L_0x0015;
    L_0x0098:
        r0 = r13 + 1;
        goto L_0x0015;
    L_0x009c:
        r0 = "SC";
        r0 = contains(r11, r13, r9, r0);
        if (r0 == 0) goto L_0x00aa;
    L_0x00a4:
        r0 = r10.handleSC(r11, r12, r13);
        goto L_0x0015;
    L_0x00aa:
        r0 = r11.length();
        r0 = r0 + -1;
        if (r13 != r0) goto L_0x00d1;
    L_0x00b2:
        r0 = r13 + -2;
        r1 = "AI";
        r3 = "OI";
        r0 = contains(r11, r0, r9, r1, r3);
        if (r0 == 0) goto L_0x00d1;
    L_0x00be:
        r12.appendAlternate(r7);
    L_0x00c1:
        r0 = r13 + 1;
        r1 = "S";
        r3 = "Z";
        r0 = contains(r11, r0, r2, r1, r3);
        if (r0 == 0) goto L_0x00d5;
    L_0x00cd:
        r0 = r13 + 2;
        goto L_0x0015;
    L_0x00d1:
        r12.append(r7);
        goto L_0x00c1;
    L_0x00d5:
        r0 = r13 + 1;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.DoubleMetaphone.handleS(java.lang.String, org.apache.commons.codec.language.DoubleMetaphone$DoubleMetaphoneResult, int, boolean):int");
    }

    private int handleSC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (charAt(str, i + 2) == 'H') {
            if (contains(str, i + 3, 2, "OO", "ER", "EN", "UY", "ED", "EM")) {
                if (contains(str, i + 3, 2, "ER", "EN")) {
                    doubleMetaphoneResult.append("X", "SK");
                } else {
                    doubleMetaphoneResult.append("SK");
                }
            } else if (i != 0 || isVowel(charAt(str, 3)) || charAt(str, 3) == 'W') {
                doubleMetaphoneResult.append('X');
            } else {
                doubleMetaphoneResult.append('X', 'S');
            }
        } else {
            if (contains(str, i + 2, 1, "I", "E", "Y")) {
                doubleMetaphoneResult.append('S');
            } else {
                doubleMetaphoneResult.append("SK");
            }
        }
        return i + 3;
    }

    private int handleT(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 4, "TION")) {
            doubleMetaphoneResult.append('X');
            return i + 3;
        } else if (contains(str, i, 3, "TIA", "TCH")) {
            doubleMetaphoneResult.append('X');
            return i + 3;
        } else if (contains(str, i, 2, "TH") || contains(str, i, 3, "TTH")) {
            if (contains(str, i + 2, 2, "OM", "AM") || contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.append('T');
            } else {
                doubleMetaphoneResult.append('0', 'T');
            }
            return i + 2;
        } else {
            doubleMetaphoneResult.append('T');
            return contains(str, i + 1, 1, "T", "D") ? i + 2 : i + 1;
        }
    }

    private int handleW(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 2, "WR")) {
            doubleMetaphoneResult.append('R');
            return i + 2;
        } else if (i == 0 && (isVowel(charAt(str, i + 1)) || contains(str, i, 2, "WH"))) {
            if (isVowel(charAt(str, i + 1))) {
                doubleMetaphoneResult.append('A', 'F');
            } else {
                doubleMetaphoneResult.append('A');
            }
            return i + 1;
        } else {
            if (!(i == str.length() - 1 && isVowel(charAt(str, i - 1)))) {
                if (!(contains(str, i - 1, 5, "EWSKI", "EWSKY", "OWSKI", "OWSKY") || contains(str, 0, 3, "SCH"))) {
                    if (!contains(str, i, 4, "WICZ", "WITZ")) {
                        return i + 1;
                    }
                    doubleMetaphoneResult.append("TS", "FX");
                    return i + 4;
                }
            }
            doubleMetaphoneResult.appendAlternate('F');
            return i + 1;
        }
    }

    private int handleX(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i == 0) {
            doubleMetaphoneResult.append('S');
            return i + 1;
        }
        if (!(i == str.length() - 1 && (contains(str, i - 3, 3, "IAU", "EAU") || contains(str, i - 2, 2, "AU", "OU")))) {
            doubleMetaphoneResult.append("KS");
        }
        return contains(str, i + 1, 1, "C", "X") ? i + 2 : i + 1;
    }

    private int handleZ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (charAt(str, i + 1) == 'H') {
            doubleMetaphoneResult.append('J');
            return i + 2;
        }
        if (contains(str, i + 1, 2, "ZO", "ZI", "ZA") || (z && i > 0 && charAt(str, i - 1) != 'T')) {
            doubleMetaphoneResult.append("S", "TS");
        } else {
            doubleMetaphoneResult.append('S');
        }
        return charAt(str, i + 1) == 'Z' ? i + 2 : i + 1;
    }

    private boolean conditionC0(String str, int i) {
        if (contains(str, i, 4, "CHIA")) {
            return true;
        }
        if (i <= 1 || isVowel(charAt(str, i - 2)) || !contains(str, i - 1, 3, "ACH")) {
            return false;
        }
        char charAt = charAt(str, i + 2);
        if ((charAt == 'I' || charAt == 'E') && !contains(str, i - 2, 6, "BACHER", "MACHER")) {
            return false;
        }
        return true;
    }

    private boolean conditionCH0(String str, int i) {
        if (i != 0) {
            return false;
        }
        if (!contains(str, i + 1, 5, "HARAC", "HARIS")) {
            if (!contains(str, i + 1, 3, "HOR", "HYM", "HIA", "HEM")) {
                return false;
            }
        }
        if (contains(str, 0, 5, "CHORE")) {
            return false;
        }
        return true;
    }

    private boolean conditionCH1(String str, int i) {
        if (!(contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH"))) {
            if (!(contains(str, i - 2, 6, "ORCHES", "ARCHIT", "ORCHID") || contains(str, i + 2, 1, "T", "S"))) {
                if (!((contains(str, i - 1, 1, "A", "O", "U", "E") || i == 0) && (contains(str, i + 2, 1, L_R_N_M_B_H_F_V_W_SPACE) || i + 1 == str.length() - 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean conditionL0(String str, int i) {
        if (i == str.length() - 3) {
            if (contains(str, i - 1, 4, "ILLO", "ILLA", "ALLE")) {
                return true;
            }
        }
        if ((contains(str, str.length() - 2, 2, "AS", "OS") || contains(str, str.length() - 1, 1, "A", "O")) && contains(str, i - 1, 4, "ALLE")) {
            return true;
        }
        return false;
    }

    private boolean conditionM0(String str, int i) {
        if (charAt(str, i + 1) == 'M') {
            return true;
        }
        if (contains(str, i - 1, 3, "UMB") && (i + 1 == str.length() - 1 || contains(str, i + 2, 2, "ER"))) {
            return true;
        }
        return false;
    }

    private boolean isSlavoGermanic(String str) {
        return str.indexOf(87) > -1 || str.indexOf(75) > -1 || str.indexOf("CZ") > -1 || str.indexOf("WITZ") > -1;
    }

    private boolean isVowel(char c) {
        return VOWELS.indexOf(c) != -1;
    }

    private boolean isSilentStart(String str) {
        for (String startsWith : SILENT_START) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    private String cleanInput(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() != 0) {
            return trim.toUpperCase(Locale.ENGLISH);
        }
        return null;
    }

    protected char charAt(String str, int i) {
        if (i < 0 || i >= str.length()) {
            return '\u0000';
        }
        return str.charAt(i);
    }

    private static boolean contains(String str, int i, int i2, String str2) {
        return contains(str, i, i2, new String[]{str2});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3) {
        return contains(str, i, i2, new String[]{str2, str3});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4) {
        return contains(str, i, i2, new String[]{str2, str3, str4});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4, String str5) {
        return contains(str, i, i2, new String[]{str2, str3, str4, str5});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4, String str5, String str6) {
        return contains(str, i, i2, new String[]{str2, str3, str4, str5, str6});
    }

    private static boolean contains(String str, int i, int i2, String str2, String str3, String str4, String str5, String str6, String str7) {
        return contains(str, i, i2, new String[]{str2, str3, str4, str5, str6, str7});
    }

    protected static boolean contains(String str, int i, int i2, String[] strArr) {
        if (i < 0 || i + i2 > str.length()) {
            return false;
        }
        String substring = str.substring(i, i + i2);
        for (Object equals : strArr) {
            if (substring.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
