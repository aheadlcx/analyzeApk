package com.ta.utdid2.b.a;

import com.tencent.bugly.beta.tinker.TinkerReport;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.UnsupportedEncodingException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class b {
    static final /* synthetic */ boolean a;

    static abstract class a {
        public int a;
        public byte[] b;

        a() {
        }
    }

    static class b extends a {
        private static final int[] a;
        private static final int[] b;
        private final int[] c;
        private int state;
        private int value;

        static {
            int[] iArr = new int[256];
            iArr[0] = -1;
            iArr[1] = -1;
            iArr[2] = -1;
            iArr[3] = -1;
            iArr[4] = -1;
            iArr[5] = -1;
            iArr[6] = -1;
            iArr[7] = -1;
            iArr[8] = -1;
            iArr[9] = -1;
            iArr[10] = -1;
            iArr[11] = -1;
            iArr[12] = -1;
            iArr[13] = -1;
            iArr[14] = -1;
            iArr[15] = -1;
            iArr[16] = -1;
            iArr[17] = -1;
            iArr[18] = -1;
            iArr[19] = -1;
            iArr[20] = -1;
            iArr[21] = -1;
            iArr[22] = -1;
            iArr[23] = -1;
            iArr[24] = -1;
            iArr[25] = -1;
            iArr[26] = -1;
            iArr[27] = -1;
            iArr[28] = -1;
            iArr[29] = -1;
            iArr[30] = -1;
            iArr[31] = -1;
            iArr[32] = -1;
            iArr[33] = -1;
            iArr[34] = -1;
            iArr[35] = -1;
            iArr[36] = -1;
            iArr[37] = -1;
            iArr[38] = -1;
            iArr[39] = -1;
            iArr[40] = -1;
            iArr[41] = -1;
            iArr[42] = -1;
            iArr[43] = 62;
            iArr[44] = -1;
            iArr[45] = -1;
            iArr[46] = -1;
            iArr[47] = 63;
            iArr[48] = 52;
            iArr[49] = 53;
            iArr[50] = 54;
            iArr[51] = 55;
            iArr[52] = 56;
            iArr[53] = 57;
            iArr[54] = 58;
            iArr[55] = 59;
            iArr[56] = 60;
            iArr[57] = 61;
            iArr[58] = -1;
            iArr[59] = -1;
            iArr[60] = -1;
            iArr[61] = -2;
            iArr[62] = -1;
            iArr[63] = -1;
            iArr[64] = -1;
            iArr[66] = 1;
            iArr[67] = 2;
            iArr[68] = 3;
            iArr[69] = 4;
            iArr[70] = 5;
            iArr[71] = 6;
            iArr[72] = 7;
            iArr[73] = 8;
            iArr[74] = 9;
            iArr[75] = 10;
            iArr[76] = 11;
            iArr[77] = 12;
            iArr[78] = 13;
            iArr[79] = 14;
            iArr[80] = 15;
            iArr[81] = 16;
            iArr[82] = 17;
            iArr[83] = 18;
            iArr[84] = 19;
            iArr[85] = 20;
            iArr[86] = 21;
            iArr[87] = 22;
            iArr[88] = 23;
            iArr[89] = 24;
            iArr[90] = 25;
            iArr[91] = -1;
            iArr[92] = -1;
            iArr[93] = -1;
            iArr[94] = -1;
            iArr[95] = -1;
            iArr[96] = -1;
            iArr[97] = 26;
            iArr[98] = 27;
            iArr[99] = 28;
            iArr[100] = 29;
            iArr[101] = 30;
            iArr[102] = 31;
            iArr[103] = 32;
            iArr[104] = 33;
            iArr[105] = 34;
            iArr[106] = 35;
            iArr[107] = 36;
            iArr[108] = 37;
            iArr[109] = 38;
            iArr[110] = 39;
            iArr[111] = 40;
            iArr[112] = 41;
            iArr[113] = 42;
            iArr[114] = 43;
            iArr[115] = 44;
            iArr[116] = 45;
            iArr[117] = 46;
            iArr[118] = 47;
            iArr[119] = 48;
            iArr[120] = 49;
            iArr[121] = 50;
            iArr[122] = 51;
            iArr[123] = -1;
            iArr[124] = -1;
            iArr[125] = -1;
            iArr[126] = -1;
            iArr[127] = -1;
            iArr[128] = -1;
            iArr[129] = -1;
            iArr[130] = -1;
            iArr[131] = -1;
            iArr[132] = -1;
            iArr[133] = -1;
            iArr[134] = -1;
            iArr[135] = -1;
            iArr[136] = -1;
            iArr[137] = -1;
            iArr[138] = -1;
            iArr[139] = -1;
            iArr[140] = -1;
            iArr[141] = -1;
            iArr[142] = -1;
            iArr[143] = -1;
            iArr[144] = -1;
            iArr[145] = -1;
            iArr[146] = -1;
            iArr[147] = -1;
            iArr[148] = -1;
            iArr[149] = -1;
            iArr[150] = -1;
            iArr[151] = -1;
            iArr[152] = -1;
            iArr[153] = -1;
            iArr[154] = -1;
            iArr[155] = -1;
            iArr[156] = -1;
            iArr[157] = -1;
            iArr[158] = -1;
            iArr[159] = -1;
            iArr[160] = -1;
            iArr[161] = -1;
            iArr[162] = -1;
            iArr[163] = -1;
            iArr[164] = -1;
            iArr[165] = -1;
            iArr[166] = -1;
            iArr[167] = -1;
            iArr[168] = -1;
            iArr[169] = -1;
            iArr[170] = -1;
            iArr[171] = -1;
            iArr[172] = -1;
            iArr[173] = -1;
            iArr[174] = -1;
            iArr[175] = -1;
            iArr[176] = -1;
            iArr[177] = -1;
            iArr[178] = -1;
            iArr[179] = -1;
            iArr[180] = -1;
            iArr[181] = -1;
            iArr[182] = -1;
            iArr[183] = -1;
            iArr[184] = -1;
            iArr[185] = -1;
            iArr[Opcodes.USHR_INT_2ADDR] = -1;
            iArr[187] = -1;
            iArr[188] = -1;
            iArr[189] = -1;
            iArr[190] = -1;
            iArr[191] = -1;
            iArr[192] = -1;
            iArr[193] = -1;
            iArr[194] = -1;
            iArr[195] = -1;
            iArr[196] = -1;
            iArr[197] = -1;
            iArr[198] = -1;
            iArr[199] = -1;
            iArr[200] = -1;
            iArr[201] = -1;
            iArr[202] = -1;
            iArr[203] = -1;
            iArr[204] = -1;
            iArr[205] = -1;
            iArr[206] = -1;
            iArr[207] = -1;
            iArr[208] = -1;
            iArr[209] = -1;
            iArr[Opcodes.MUL_INT_LIT16] = -1;
            iArr[Opcodes.DIV_INT_LIT16] = -1;
            iArr[Opcodes.REM_INT_LIT16] = -1;
            iArr[Opcodes.AND_INT_LIT16] = -1;
            iArr[Opcodes.OR_INT_LIT16] = -1;
            iArr[Opcodes.XOR_INT_LIT16] = -1;
            iArr[Opcodes.ADD_INT_LIT8] = -1;
            iArr[Opcodes.RSUB_INT_LIT8] = -1;
            iArr[Opcodes.MUL_INT_LIT8] = -1;
            iArr[Opcodes.DIV_INT_LIT8] = -1;
            iArr[Opcodes.REM_INT_LIT8] = -1;
            iArr[Opcodes.AND_INT_LIT8] = -1;
            iArr[Opcodes.OR_INT_LIT8] = -1;
            iArr[Opcodes.XOR_INT_LIT8] = -1;
            iArr[Opcodes.SHL_INT_LIT8] = -1;
            iArr[Opcodes.SHR_INT_LIT8] = -1;
            iArr[Opcodes.USHR_INT_LIT8] = -1;
            iArr[227] = -1;
            iArr[228] = -1;
            iArr[229] = -1;
            iArr[230] = -1;
            iArr[231] = -1;
            iArr[232] = -1;
            iArr[233] = -1;
            iArr[234] = -1;
            iArr[235] = -1;
            iArr[236] = -1;
            iArr[237] = -1;
            iArr[238] = -1;
            iArr[239] = -1;
            iArr[240] = -1;
            iArr[241] = -1;
            iArr[242] = -1;
            iArr[243] = -1;
            iArr[IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE] = -1;
            iArr[245] = -1;
            iArr[246] = -1;
            iArr[247] = -1;
            iArr[248] = -1;
            iArr[249] = -1;
            iArr[250] = -1;
            iArr[TinkerReport.KEY_LOADED_UNCAUGHT_EXCEPTION] = -1;
            iArr[TinkerReport.KEY_LOADED_EXCEPTION_DEX] = -1;
            iArr[TinkerReport.KEY_LOADED_EXCEPTION_DEX_CHECK] = -1;
            iArr[254] = -1;
            iArr[255] = -1;
            a = iArr;
            iArr = new int[256];
            iArr[0] = -1;
            iArr[1] = -1;
            iArr[2] = -1;
            iArr[3] = -1;
            iArr[4] = -1;
            iArr[5] = -1;
            iArr[6] = -1;
            iArr[7] = -1;
            iArr[8] = -1;
            iArr[9] = -1;
            iArr[10] = -1;
            iArr[11] = -1;
            iArr[12] = -1;
            iArr[13] = -1;
            iArr[14] = -1;
            iArr[15] = -1;
            iArr[16] = -1;
            iArr[17] = -1;
            iArr[18] = -1;
            iArr[19] = -1;
            iArr[20] = -1;
            iArr[21] = -1;
            iArr[22] = -1;
            iArr[23] = -1;
            iArr[24] = -1;
            iArr[25] = -1;
            iArr[26] = -1;
            iArr[27] = -1;
            iArr[28] = -1;
            iArr[29] = -1;
            iArr[30] = -1;
            iArr[31] = -1;
            iArr[32] = -1;
            iArr[33] = -1;
            iArr[34] = -1;
            iArr[35] = -1;
            iArr[36] = -1;
            iArr[37] = -1;
            iArr[38] = -1;
            iArr[39] = -1;
            iArr[40] = -1;
            iArr[41] = -1;
            iArr[42] = -1;
            iArr[43] = -1;
            iArr[44] = -1;
            iArr[45] = 62;
            iArr[46] = -1;
            iArr[47] = -1;
            iArr[48] = 52;
            iArr[49] = 53;
            iArr[50] = 54;
            iArr[51] = 55;
            iArr[52] = 56;
            iArr[53] = 57;
            iArr[54] = 58;
            iArr[55] = 59;
            iArr[56] = 60;
            iArr[57] = 61;
            iArr[58] = -1;
            iArr[59] = -1;
            iArr[60] = -1;
            iArr[61] = -2;
            iArr[62] = -1;
            iArr[63] = -1;
            iArr[64] = -1;
            iArr[66] = 1;
            iArr[67] = 2;
            iArr[68] = 3;
            iArr[69] = 4;
            iArr[70] = 5;
            iArr[71] = 6;
            iArr[72] = 7;
            iArr[73] = 8;
            iArr[74] = 9;
            iArr[75] = 10;
            iArr[76] = 11;
            iArr[77] = 12;
            iArr[78] = 13;
            iArr[79] = 14;
            iArr[80] = 15;
            iArr[81] = 16;
            iArr[82] = 17;
            iArr[83] = 18;
            iArr[84] = 19;
            iArr[85] = 20;
            iArr[86] = 21;
            iArr[87] = 22;
            iArr[88] = 23;
            iArr[89] = 24;
            iArr[90] = 25;
            iArr[91] = -1;
            iArr[92] = -1;
            iArr[93] = -1;
            iArr[94] = -1;
            iArr[95] = 63;
            iArr[96] = -1;
            iArr[97] = 26;
            iArr[98] = 27;
            iArr[99] = 28;
            iArr[100] = 29;
            iArr[101] = 30;
            iArr[102] = 31;
            iArr[103] = 32;
            iArr[104] = 33;
            iArr[105] = 34;
            iArr[106] = 35;
            iArr[107] = 36;
            iArr[108] = 37;
            iArr[109] = 38;
            iArr[110] = 39;
            iArr[111] = 40;
            iArr[112] = 41;
            iArr[113] = 42;
            iArr[114] = 43;
            iArr[115] = 44;
            iArr[116] = 45;
            iArr[117] = 46;
            iArr[118] = 47;
            iArr[119] = 48;
            iArr[120] = 49;
            iArr[121] = 50;
            iArr[122] = 51;
            iArr[123] = -1;
            iArr[124] = -1;
            iArr[125] = -1;
            iArr[126] = -1;
            iArr[127] = -1;
            iArr[128] = -1;
            iArr[129] = -1;
            iArr[130] = -1;
            iArr[131] = -1;
            iArr[132] = -1;
            iArr[133] = -1;
            iArr[134] = -1;
            iArr[135] = -1;
            iArr[136] = -1;
            iArr[137] = -1;
            iArr[138] = -1;
            iArr[139] = -1;
            iArr[140] = -1;
            iArr[141] = -1;
            iArr[142] = -1;
            iArr[143] = -1;
            iArr[144] = -1;
            iArr[145] = -1;
            iArr[146] = -1;
            iArr[147] = -1;
            iArr[148] = -1;
            iArr[149] = -1;
            iArr[150] = -1;
            iArr[151] = -1;
            iArr[152] = -1;
            iArr[153] = -1;
            iArr[154] = -1;
            iArr[155] = -1;
            iArr[156] = -1;
            iArr[157] = -1;
            iArr[158] = -1;
            iArr[159] = -1;
            iArr[160] = -1;
            iArr[161] = -1;
            iArr[162] = -1;
            iArr[163] = -1;
            iArr[164] = -1;
            iArr[165] = -1;
            iArr[166] = -1;
            iArr[167] = -1;
            iArr[168] = -1;
            iArr[169] = -1;
            iArr[170] = -1;
            iArr[171] = -1;
            iArr[172] = -1;
            iArr[173] = -1;
            iArr[174] = -1;
            iArr[175] = -1;
            iArr[176] = -1;
            iArr[177] = -1;
            iArr[178] = -1;
            iArr[179] = -1;
            iArr[180] = -1;
            iArr[181] = -1;
            iArr[182] = -1;
            iArr[183] = -1;
            iArr[184] = -1;
            iArr[185] = -1;
            iArr[Opcodes.USHR_INT_2ADDR] = -1;
            iArr[187] = -1;
            iArr[188] = -1;
            iArr[189] = -1;
            iArr[190] = -1;
            iArr[191] = -1;
            iArr[192] = -1;
            iArr[193] = -1;
            iArr[194] = -1;
            iArr[195] = -1;
            iArr[196] = -1;
            iArr[197] = -1;
            iArr[198] = -1;
            iArr[199] = -1;
            iArr[200] = -1;
            iArr[201] = -1;
            iArr[202] = -1;
            iArr[203] = -1;
            iArr[204] = -1;
            iArr[205] = -1;
            iArr[206] = -1;
            iArr[207] = -1;
            iArr[208] = -1;
            iArr[209] = -1;
            iArr[Opcodes.MUL_INT_LIT16] = -1;
            iArr[Opcodes.DIV_INT_LIT16] = -1;
            iArr[Opcodes.REM_INT_LIT16] = -1;
            iArr[Opcodes.AND_INT_LIT16] = -1;
            iArr[Opcodes.OR_INT_LIT16] = -1;
            iArr[Opcodes.XOR_INT_LIT16] = -1;
            iArr[Opcodes.ADD_INT_LIT8] = -1;
            iArr[Opcodes.RSUB_INT_LIT8] = -1;
            iArr[Opcodes.MUL_INT_LIT8] = -1;
            iArr[Opcodes.DIV_INT_LIT8] = -1;
            iArr[Opcodes.REM_INT_LIT8] = -1;
            iArr[Opcodes.AND_INT_LIT8] = -1;
            iArr[Opcodes.OR_INT_LIT8] = -1;
            iArr[Opcodes.XOR_INT_LIT8] = -1;
            iArr[Opcodes.SHL_INT_LIT8] = -1;
            iArr[Opcodes.SHR_INT_LIT8] = -1;
            iArr[Opcodes.USHR_INT_LIT8] = -1;
            iArr[227] = -1;
            iArr[228] = -1;
            iArr[229] = -1;
            iArr[230] = -1;
            iArr[231] = -1;
            iArr[232] = -1;
            iArr[233] = -1;
            iArr[234] = -1;
            iArr[235] = -1;
            iArr[236] = -1;
            iArr[237] = -1;
            iArr[238] = -1;
            iArr[239] = -1;
            iArr[240] = -1;
            iArr[241] = -1;
            iArr[242] = -1;
            iArr[243] = -1;
            iArr[IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE] = -1;
            iArr[245] = -1;
            iArr[246] = -1;
            iArr[247] = -1;
            iArr[248] = -1;
            iArr[249] = -1;
            iArr[250] = -1;
            iArr[TinkerReport.KEY_LOADED_UNCAUGHT_EXCEPTION] = -1;
            iArr[TinkerReport.KEY_LOADED_EXCEPTION_DEX] = -1;
            iArr[TinkerReport.KEY_LOADED_EXCEPTION_DEX_CHECK] = -1;
            iArr[254] = -1;
            iArr[255] = -1;
            b = iArr;
        }

        public b(int i, byte[] bArr) {
            this.b = bArr;
            this.c = (i & 8) == 0 ? a : b;
            this.state = 0;
            this.value = 0;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(byte[] r10, int r11, int r12, boolean r13) {
            /*
            r9 = this;
            r0 = r9.state;
            r1 = 6;
            if (r0 != r1) goto L_0x0007;
        L_0x0005:
            r0 = 0;
        L_0x0006:
            return r0;
        L_0x0007:
            r4 = r12 + r11;
            r2 = r9.state;
            r1 = r9.value;
            r0 = 0;
            r5 = r9.b;
            r6 = r9.c;
            r3 = r2;
            r2 = r11;
        L_0x0014:
            if (r2 < r4) goto L_0x0021;
        L_0x0016:
            r2 = r1;
        L_0x0017:
            if (r13 != 0) goto L_0x0115;
        L_0x0019:
            r9.state = r3;
            r9.value = r2;
            r9.a = r0;
            r0 = 1;
            goto L_0x0006;
        L_0x0021:
            if (r3 != 0) goto L_0x006a;
        L_0x0023:
            r7 = r2 + 4;
            if (r7 > r4) goto L_0x0050;
        L_0x0027:
            r1 = r10[r2];
            r1 = r1 & 255;
            r1 = r6[r1];
            r1 = r1 << 18;
            r7 = r2 + 1;
            r7 = r10[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r7 = r7 << 12;
            r1 = r1 | r7;
            r7 = r2 + 2;
            r7 = r10[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r7 = r7 << 6;
            r1 = r1 | r7;
            r7 = r2 + 3;
            r7 = r10[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r1 = r1 | r7;
            if (r1 >= 0) goto L_0x0054;
        L_0x0050:
            if (r2 < r4) goto L_0x006a;
        L_0x0052:
            r2 = r1;
            goto L_0x0017;
        L_0x0054:
            r7 = r0 + 2;
            r8 = (byte) r1;
            r5[r7] = r8;
            r7 = r0 + 1;
            r8 = r1 >> 8;
            r8 = (byte) r8;
            r5[r7] = r8;
            r7 = r1 >> 16;
            r7 = (byte) r7;
            r5[r0] = r7;
            r0 = r0 + 3;
            r2 = r2 + 4;
            goto L_0x0023;
        L_0x006a:
            r11 = r2 + 1;
            r2 = r10[r2];
            r2 = r2 & 255;
            r2 = r6[r2];
            switch(r3) {
                case 0: goto L_0x0077;
                case 1: goto L_0x0087;
                case 2: goto L_0x009a;
                case 3: goto L_0x00be;
                case 4: goto L_0x00fa;
                case 5: goto L_0x010c;
                default: goto L_0x0075;
            };
        L_0x0075:
            r2 = r11;
            goto L_0x0014;
        L_0x0077:
            if (r2 < 0) goto L_0x007f;
        L_0x0079:
            r1 = r3 + 1;
            r3 = r1;
            r1 = r2;
            r2 = r11;
            goto L_0x0014;
        L_0x007f:
            r7 = -1;
            if (r2 == r7) goto L_0x0075;
        L_0x0082:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0087:
            if (r2 < 0) goto L_0x0091;
        L_0x0089:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r3 + 1;
            r3 = r2;
            r2 = r11;
            goto L_0x0014;
        L_0x0091:
            r7 = -1;
            if (r2 == r7) goto L_0x0075;
        L_0x0094:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x009a:
            if (r2 < 0) goto L_0x00a5;
        L_0x009c:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r3 + 1;
            r3 = r2;
            r2 = r11;
            goto L_0x0014;
        L_0x00a5:
            r7 = -2;
            if (r2 != r7) goto L_0x00b5;
        L_0x00a8:
            r2 = r0 + 1;
            r3 = r1 >> 4;
            r3 = (byte) r3;
            r5[r0] = r3;
            r0 = 4;
            r3 = r0;
            r0 = r2;
            r2 = r11;
            goto L_0x0014;
        L_0x00b5:
            r7 = -1;
            if (r2 == r7) goto L_0x0075;
        L_0x00b8:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00be:
            if (r2 < 0) goto L_0x00db;
        L_0x00c0:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r0 + 2;
            r3 = (byte) r1;
            r5[r2] = r3;
            r2 = r0 + 1;
            r3 = r1 >> 8;
            r3 = (byte) r3;
            r5[r2] = r3;
            r2 = r1 >> 16;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r0 + 3;
            r2 = 0;
            r3 = r2;
            r2 = r11;
            goto L_0x0014;
        L_0x00db:
            r7 = -2;
            if (r2 != r7) goto L_0x00f1;
        L_0x00de:
            r2 = r0 + 1;
            r3 = r1 >> 2;
            r3 = (byte) r3;
            r5[r2] = r3;
            r2 = r1 >> 10;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r0 + 2;
            r2 = 5;
            r3 = r2;
            r2 = r11;
            goto L_0x0014;
        L_0x00f1:
            r7 = -1;
            if (r2 == r7) goto L_0x0075;
        L_0x00f4:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00fa:
            r7 = -2;
            if (r2 != r7) goto L_0x0103;
        L_0x00fd:
            r2 = r3 + 1;
            r3 = r2;
            r2 = r11;
            goto L_0x0014;
        L_0x0103:
            r7 = -1;
            if (r2 == r7) goto L_0x0075;
        L_0x0106:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x010c:
            r7 = -1;
            if (r2 == r7) goto L_0x0075;
        L_0x010f:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0115:
            switch(r3) {
                case 0: goto L_0x0118;
                case 1: goto L_0x011f;
                case 2: goto L_0x0125;
                case 3: goto L_0x012e;
                case 4: goto L_0x013d;
                default: goto L_0x0118;
            };
        L_0x0118:
            r9.state = r3;
            r9.a = r0;
            r0 = 1;
            goto L_0x0006;
        L_0x011f:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0125:
            r1 = r0 + 1;
            r2 = r2 >> 4;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r1;
            goto L_0x0118;
        L_0x012e:
            r1 = r0 + 1;
            r4 = r2 >> 10;
            r4 = (byte) r4;
            r5[r0] = r4;
            r0 = r1 + 1;
            r2 = r2 >> 2;
            r2 = (byte) r2;
            r5[r1] = r2;
            goto L_0x0118;
        L_0x013d:
            r0 = 6;
            r9.state = r0;
            r0 = 0;
            goto L_0x0006;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.b.b.a(byte[], int, int, boolean):boolean");
        }
    }

    static class c extends a {
        static final /* synthetic */ boolean a;
        private static final byte[] c = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        private static final byte[] d = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, Framer.STDIN_FRAME_PREFIX, Framer.STDIN_REQUEST_FRAME_PREFIX};
        int b;
        /* renamed from: b */
        public final boolean f23b;
        /* renamed from: c */
        public final boolean f24c;
        private int count;
        /* renamed from: d */
        public final boolean f25d;
        private final byte[] e;
        private final byte[] f;

        static {
            boolean z;
            if (b.class.desiredAssertionStatus()) {
                z = false;
            } else {
                z = true;
            }
            a = z;
        }

        public c(int i, byte[] bArr) {
            boolean z;
            boolean z2 = true;
            this.b = bArr;
            this.f23b = (i & 1) == 0;
            if ((i & 2) == 0) {
                z = true;
            } else {
                z = false;
            }
            this.f24c = z;
            if ((i & 4) == 0) {
                z2 = false;
            }
            this.f25d = z2;
            this.f = (i & 8) == 0 ? c : d;
            this.e = new byte[2];
            this.b = 0;
            this.count = this.f24c ? 19 : -1;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(byte[] r12, int r13, int r14, boolean r15) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r11 = this;
            r6 = r11.f;
            r7 = r11.b;
            r1 = 0;
            r0 = r11.count;
            r8 = r14 + r13;
            r2 = -1;
            r3 = r11.b;
            switch(r3) {
                case 0: goto L_0x00b4;
                case 1: goto L_0x00b7;
                case 2: goto L_0x00da;
                default: goto L_0x000f;
            };
        L_0x000f:
            r3 = r13;
        L_0x0010:
            r4 = -1;
            if (r2 == r4) goto L_0x0241;
        L_0x0013:
            r4 = 1;
            r5 = r2 >> 18;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r1] = r5;
            r1 = 2;
            r5 = r2 >> 12;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r4] = r5;
            r4 = 3;
            r5 = r2 >> 6;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r1] = r5;
            r1 = 4;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r4] = r2;
            r0 = r0 + -1;
            if (r0 != 0) goto L_0x0241;
        L_0x0039:
            r0 = r11.f25d;
            if (r0 == 0) goto L_0x0245;
        L_0x003d:
            r0 = 5;
            r2 = 13;
            r7[r1] = r2;
        L_0x0042:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = 19;
            r5 = r0;
            r4 = r1;
        L_0x004c:
            r0 = r3 + 3;
            if (r0 <= r8) goto L_0x00fd;
        L_0x0050:
            if (r15 == 0) goto L_0x01fd;
        L_0x0052:
            r0 = r11.b;
            r0 = r3 - r0;
            r1 = r8 + -1;
            if (r0 != r1) goto L_0x015e;
        L_0x005a:
            r2 = 0;
            r0 = r11.b;
            if (r0 <= 0) goto L_0x0155;
        L_0x005f:
            r0 = r11.e;
            r1 = 1;
            r0 = r0[r2];
            r2 = r3;
        L_0x0065:
            r0 = r0 & 255;
            r3 = r0 << 4;
            r0 = r11.b;
            r0 = r0 - r1;
            r11.b = r0;
            r1 = r4 + 1;
            r0 = r3 >> 6;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r4] = r0;
            r0 = r1 + 1;
            r3 = r3 & 63;
            r3 = r6[r3];
            r7[r1] = r3;
            r1 = r11.f23b;
            if (r1 == 0) goto L_0x0090;
        L_0x0084:
            r1 = r0 + 1;
            r3 = 61;
            r7[r0] = r3;
            r0 = r1 + 1;
            r3 = 61;
            r7[r1] = r3;
        L_0x0090:
            r1 = r11.f24c;
            if (r1 == 0) goto L_0x023a;
        L_0x0094:
            r1 = r11.f25d;
            if (r1 == 0) goto L_0x009f;
        L_0x0098:
            r1 = r0 + 1;
            r3 = 13;
            r7[r0] = r3;
            r0 = r1;
        L_0x009f:
            r4 = r0 + 1;
            r1 = 10;
            r7[r0] = r1;
            r3 = r2;
        L_0x00a6:
            r0 = a;
            if (r0 != 0) goto L_0x01f1;
        L_0x00aa:
            r0 = r11.b;
            if (r0 == 0) goto L_0x01f1;
        L_0x00ae:
            r0 = new java.lang.AssertionError;
            r0.<init>();
            throw r0;
        L_0x00b4:
            r3 = r13;
            goto L_0x0010;
        L_0x00b7:
            r3 = r13 + 2;
            if (r3 > r8) goto L_0x000f;
        L_0x00bb:
            r2 = r11.e;
            r3 = 0;
            r2 = r2[r3];
            r2 = r2 & 255;
            r2 = r2 << 16;
            r3 = r13 + 1;
            r4 = r12[r13];
            r4 = r4 & 255;
            r4 = r4 << 8;
            r2 = r2 | r4;
            r13 = r3 + 1;
            r3 = r12[r3];
            r3 = r3 & 255;
            r2 = r2 | r3;
            r3 = 0;
            r11.b = r3;
            r3 = r13;
            goto L_0x0010;
        L_0x00da:
            r3 = r13 + 1;
            if (r3 > r8) goto L_0x000f;
        L_0x00de:
            r2 = r11.e;
            r3 = 0;
            r2 = r2[r3];
            r2 = r2 & 255;
            r2 = r2 << 16;
            r3 = r11.e;
            r4 = 1;
            r3 = r3[r4];
            r3 = r3 & 255;
            r3 = r3 << 8;
            r2 = r2 | r3;
            r3 = r13 + 1;
            r4 = r12[r13];
            r4 = r4 & 255;
            r2 = r2 | r4;
            r4 = 0;
            r11.b = r4;
            goto L_0x0010;
        L_0x00fd:
            r0 = r12[r3];
            r0 = r0 & 255;
            r0 = r0 << 16;
            r1 = r3 + 1;
            r1 = r12[r1];
            r1 = r1 & 255;
            r1 = r1 << 8;
            r0 = r0 | r1;
            r1 = r3 + 2;
            r1 = r12[r1];
            r1 = r1 & 255;
            r0 = r0 | r1;
            r1 = r0 >> 18;
            r1 = r1 & 63;
            r1 = r6[r1];
            r7[r4] = r1;
            r1 = r4 + 1;
            r2 = r0 >> 12;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r4 + 2;
            r2 = r0 >> 6;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r4 + 3;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r1] = r0;
            r3 = r3 + 3;
            r1 = r4 + 4;
            r0 = r5 + -1;
            if (r0 != 0) goto L_0x0241;
        L_0x013f:
            r0 = r11.f25d;
            if (r0 == 0) goto L_0x023e;
        L_0x0143:
            r0 = r1 + 1;
            r2 = 13;
            r7[r1] = r2;
        L_0x0149:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = 19;
            r5 = r0;
            r4 = r1;
            goto L_0x004c;
        L_0x0155:
            r1 = r3 + 1;
            r0 = r12[r3];
            r10 = r2;
            r2 = r1;
            r1 = r10;
            goto L_0x0065;
        L_0x015e:
            r0 = r11.b;
            r0 = r3 - r0;
            r1 = r8 + -2;
            if (r0 != r1) goto L_0x01d5;
        L_0x0166:
            r2 = 0;
            r0 = r11.b;
            r1 = 1;
            if (r0 <= r1) goto L_0x01c8;
        L_0x016c:
            r0 = r11.e;
            r1 = 1;
            r0 = r0[r2];
        L_0x0171:
            r0 = r0 & 255;
            r9 = r0 << 10;
            r0 = r11.b;
            if (r0 <= 0) goto L_0x01cf;
        L_0x0179:
            r0 = r11.e;
            r2 = r1 + 1;
            r0 = r0[r1];
            r1 = r2;
        L_0x0180:
            r0 = r0 & 255;
            r0 = r0 << 2;
            r0 = r0 | r9;
            r2 = r11.b;
            r1 = r2 - r1;
            r11.b = r1;
            r1 = r4 + 1;
            r2 = r0 >> 12;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r4] = r2;
            r2 = r1 + 1;
            r4 = r0 >> 6;
            r4 = r4 & 63;
            r4 = r6[r4];
            r7[r1] = r4;
            r1 = r2 + 1;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r2] = r0;
            r0 = r11.f23b;
            if (r0 == 0) goto L_0x0237;
        L_0x01ab:
            r0 = r1 + 1;
            r2 = 61;
            r7[r1] = r2;
        L_0x01b1:
            r1 = r11.f24c;
            if (r1 == 0) goto L_0x0234;
        L_0x01b5:
            r1 = r11.f25d;
            if (r1 == 0) goto L_0x01c0;
        L_0x01b9:
            r1 = r0 + 1;
            r2 = 13;
            r7[r0] = r2;
            r0 = r1;
        L_0x01c0:
            r4 = r0 + 1;
            r1 = 10;
            r7[r0] = r1;
            goto L_0x00a6;
        L_0x01c8:
            r1 = r3 + 1;
            r0 = r12[r3];
            r3 = r1;
            r1 = r2;
            goto L_0x0171;
        L_0x01cf:
            r2 = r3 + 1;
            r0 = r12[r3];
            r3 = r2;
            goto L_0x0180;
        L_0x01d5:
            r0 = r11.f24c;
            if (r0 == 0) goto L_0x00a6;
        L_0x01d9:
            if (r4 <= 0) goto L_0x00a6;
        L_0x01db:
            r0 = 19;
            if (r5 == r0) goto L_0x00a6;
        L_0x01df:
            r0 = r11.f25d;
            if (r0 == 0) goto L_0x0232;
        L_0x01e3:
            r0 = r4 + 1;
            r1 = 13;
            r7[r4] = r1;
        L_0x01e9:
            r4 = r0 + 1;
            r1 = 10;
            r7[r0] = r1;
            goto L_0x00a6;
        L_0x01f1:
            r0 = a;
            if (r0 != 0) goto L_0x020d;
        L_0x01f5:
            if (r3 == r8) goto L_0x020d;
        L_0x01f7:
            r0 = new java.lang.AssertionError;
            r0.<init>();
            throw r0;
        L_0x01fd:
            r0 = r8 + -1;
            if (r3 != r0) goto L_0x0213;
        L_0x0201:
            r0 = r11.e;
            r1 = r11.b;
            r2 = r1 + 1;
            r11.b = r2;
            r2 = r12[r3];
            r0[r1] = r2;
        L_0x020d:
            r11.a = r4;
            r11.count = r5;
            r0 = 1;
            return r0;
        L_0x0213:
            r0 = r8 + -2;
            if (r3 != r0) goto L_0x020d;
        L_0x0217:
            r0 = r11.e;
            r1 = r11.b;
            r2 = r1 + 1;
            r11.b = r2;
            r2 = r12[r3];
            r0[r1] = r2;
            r0 = r11.e;
            r1 = r11.b;
            r2 = r1 + 1;
            r11.b = r2;
            r2 = r3 + 1;
            r2 = r12[r2];
            r0[r1] = r2;
            goto L_0x020d;
        L_0x0232:
            r0 = r4;
            goto L_0x01e9;
        L_0x0234:
            r4 = r0;
            goto L_0x00a6;
        L_0x0237:
            r0 = r1;
            goto L_0x01b1;
        L_0x023a:
            r3 = r2;
            r4 = r0;
            goto L_0x00a6;
        L_0x023e:
            r0 = r1;
            goto L_0x0149;
        L_0x0241:
            r5 = r0;
            r4 = r1;
            goto L_0x004c;
        L_0x0245:
            r0 = r1;
            goto L_0x0042;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.b.c.a(byte[], int, int, boolean):boolean");
        }
    }

    static {
        boolean z;
        if (b.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, new byte[((i2 * 3) / 4)]);
        if (!bVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.a == bVar.b.length) {
            return bVar.b;
        } else {
            byte[] bArr2 = new byte[bVar.a];
            System.arraycopy(bVar.b, 0, bArr2, 0, bVar.a);
            return bArr2;
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        c cVar = new c(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!cVar.f23b) {
            switch (i2 % 3) {
                case 0:
                    break;
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
                default:
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (cVar.f24c && i2 > 0) {
            int i5;
            int i6 = ((i2 - 1) / 57) + 1;
            if (cVar.f25d) {
                i5 = 2;
            } else {
                i5 = 1;
            }
            i4 += i5 * i6;
        }
        cVar.b = new byte[i4];
        cVar.a(bArr, i, i2, true);
        if (a || cVar.a == i4) {
            return cVar.b;
        }
        throw new AssertionError();
    }

    private b() {
    }
}
