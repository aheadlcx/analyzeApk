package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;
import com.tencent.tinker.android.utils.SparseIntArray;

public final class InstructionPromoter extends InstructionVisitor {
    private final SparseIntArray addressMap = new SparseIntArray();
    private int currentPromotedAddress = 0;

    public InstructionPromoter() {
        super(null);
    }

    private void mapAddressIfNeeded(int i) {
        if (i != this.currentPromotedAddress) {
            this.addressMap.append(i, this.currentPromotedAddress);
        }
    }

    public int getPromotedAddress(int i) {
        int indexOfKey = this.addressMap.indexOfKey(i);
        return indexOfKey < 0 ? i : this.addressMap.valueAt(indexOfKey);
    }

    public int getPromotedAddressCount() {
        return this.addressMap.size();
    }

    public void visitZeroRegisterInsn(int i, int i2, int i3, int i4, int i5, long j) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case -1:
            case 0:
            case 14:
                this.currentPromotedAddress++;
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            case 40:
                byte target = InstructionCodec.getTarget(i5, this.currentPromotedAddress);
                if (target == ((byte) target)) {
                    this.currentPromotedAddress++;
                    return;
                } else if (target != ((short) target)) {
                    this.currentPromotedAddress += 3;
                    return;
                } else {
                    this.currentPromotedAddress += 2;
                    return;
                }
            case 41:
                short target2 = InstructionCodec.getTarget(i5, this.currentPromotedAddress);
                if (target2 != ((short) target2)) {
                    this.currentPromotedAddress += 3;
                    return;
                } else {
                    this.currentPromotedAddress += 2;
                    return;
                }
            case 42:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitOneRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 29:
            case 30:
            case 39:
                this.currentPromotedAddress++;
                return;
            case 19:
            case 21:
            case 22:
            case 25:
            case 28:
            case 31:
            case 34:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
                this.currentPromotedAddress += 2;
                return;
            case 20:
            case 23:
            case 36:
            case 38:
            case 43:
            case 44:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            case 24:
                this.currentPromotedAddress += 5;
                return;
            case 26:
                if (i3 > 65535) {
                    this.currentPromotedAddress += 3;
                    return;
                } else {
                    this.currentPromotedAddress += 2;
                    return;
                }
            case 27:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitTwoRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 1:
            case 4:
            case 7:
            case 33:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case Opcodes.USHR_INT_2ADDR /*186*/:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
                this.currentPromotedAddress++;
                return;
            case 2:
            case 5:
            case 8:
                this.currentPromotedAddress += 2;
                return;
            case 3:
            case 6:
            case 9:
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            case 32:
            case 35:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 208:
            case 209:
            case Opcodes.MUL_INT_LIT16 /*210*/:
            case Opcodes.DIV_INT_LIT16 /*211*/:
            case Opcodes.REM_INT_LIT16 /*212*/:
            case Opcodes.AND_INT_LIT16 /*213*/:
            case Opcodes.OR_INT_LIT16 /*214*/:
            case Opcodes.XOR_INT_LIT16 /*215*/:
            case Opcodes.ADD_INT_LIT8 /*216*/:
            case Opcodes.RSUB_INT_LIT8 /*217*/:
            case Opcodes.MUL_INT_LIT8 /*218*/:
            case Opcodes.DIV_INT_LIT8 /*219*/:
            case Opcodes.REM_INT_LIT8 /*220*/:
            case Opcodes.AND_INT_LIT8 /*221*/:
            case Opcodes.OR_INT_LIT8 /*222*/:
            case Opcodes.XOR_INT_LIT8 /*223*/:
            case Opcodes.SHL_INT_LIT8 /*224*/:
            case Opcodes.SHR_INT_LIT8 /*225*/:
            case Opcodes.USHR_INT_LIT8 /*226*/:
                this.currentPromotedAddress += 2;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitThreeRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
                this.currentPromotedAddress += 2;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFourRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFiveRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9, int i10) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitRegisterRangeInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 37:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitSparseSwitchPayloadInsn(int i, int i2, int[] iArr, int[] iArr2) {
        mapAddressIfNeeded(i);
        this.currentPromotedAddress += 2;
        this.currentPromotedAddress += iArr.length * 2;
        this.currentPromotedAddress += iArr2.length * 2;
    }

    public void visitPackedSwitchPayloadInsn(int i, int i2, int i3, int[] iArr) {
        mapAddressIfNeeded(i);
        this.currentPromotedAddress += 4;
        this.currentPromotedAddress += iArr.length * 2;
    }

    public void visitFillArrayDataPayloadInsn(int i, int i2, Object obj, int i3, int i4) {
        mapAddressIfNeeded(i);
        this.currentPromotedAddress += 4;
        switch (i4) {
            case 1:
                int length = ((byte[]) obj).length;
                this.currentPromotedAddress = ((length & 1) + (length >> 1)) + this.currentPromotedAddress;
                return;
            case 2:
                this.currentPromotedAddress += ((short[]) obj).length * 1;
                return;
            case 4:
                this.currentPromotedAddress += ((int[]) obj).length * 2;
                return;
            case 8:
                this.currentPromotedAddress += ((long[]) obj).length * 4;
                return;
            default:
                throw new DexException("bogus element_width: " + Hex.u2(i4));
        }
    }
}
