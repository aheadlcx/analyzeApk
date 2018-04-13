package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;

public final class InstructionWriter extends InstructionVisitor {
    private final ShortArrayCodeOutput codeOut;
    private final boolean hasPromoter;
    private final InstructionPromoter insnPromoter;

    public InstructionWriter(ShortArrayCodeOutput shortArrayCodeOutput, InstructionPromoter instructionPromoter) {
        super(null);
        this.codeOut = shortArrayCodeOutput;
        this.insnPromoter = instructionPromoter;
        this.hasPromoter = instructionPromoter != null;
    }

    public void visitZeroRegisterInsn(int i, int i2, int i3, int i4, int i5, long j) {
        if (this.hasPromoter) {
            i5 = this.insnPromoter.getPromotedAddress(i5);
        }
        int target;
        switch (i2) {
            case -1:
            case 0:
            case 14:
                this.codeOut.write((short) i2);
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 0)), (short) i3, InstructionCodec.codeUnit(0, 0, 0, 0));
                return;
            case 40:
                if (this.hasPromoter) {
                    target = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                    if (target == ((byte) target)) {
                        this.codeOut.write(InstructionCodec.codeUnit(i2, target & 255));
                        return;
                    } else if (target != ((short) target)) {
                        this.codeOut.write((short) 42, InstructionCodec.unit0(target), InstructionCodec.unit1(target));
                        return;
                    } else {
                        this.codeOut.write((short) 41, (short) target);
                        return;
                    }
                }
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.getTargetByte(i5, this.codeOut.cursor())));
                return;
            case 41:
                short s;
                if (this.hasPromoter) {
                    target = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                    if (target != ((short) target)) {
                        this.codeOut.write((short) 42, InstructionCodec.unit0(target), InstructionCodec.unit1(target));
                        return;
                    }
                    s = (short) i2;
                    this.codeOut.write(s, (short) target);
                    return;
                }
                s = (short) i2;
                this.codeOut.write(s, InstructionCodec.getTargetUnit(i5, this.codeOut.cursor()));
                return;
            case 42:
                target = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                this.codeOut.write((short) i2, InstructionCodec.unit0(target), InstructionCodec.unit1(target));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitOneRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6) {
        if (this.hasPromoter) {
            i5 = this.insnPromoter.getPromotedAddress(i5);
        }
        int literalInt;
        switch (i2) {
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 29:
            case 30:
            case 39:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6));
                return;
            case 18:
                this.codeOut.write(InstructionCodec.codeUnit((short) i2, InstructionCodec.makeByte(i6, InstructionCodec.getLiteralNibble(j))));
                return;
            case 19:
            case 22:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.getLiteralUnit(j));
                return;
            case 20:
            case 23:
                literalInt = InstructionCodec.getLiteralInt(j);
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(literalInt), InstructionCodec.unit1(literalInt));
                return;
            case 21:
            case 25:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) ((int) (j >> (i2 == 21 ? 16 : 48))));
                return;
            case 24:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(j), InstructionCodec.unit1(j), InstructionCodec.unit2(j), InstructionCodec.unit3(j));
                return;
            case 26:
                if (this.hasPromoter) {
                    if (i3 > 65535) {
                        this.codeOut.write(InstructionCodec.codeUnit(27, i6), InstructionCodec.unit0(i3), InstructionCodec.unit1(i3));
                        return;
                    } else {
                        this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) i3);
                        return;
                    }
                } else if (i3 > 65535) {
                    throw new DexException("string index out of bound: " + Hex.u4(i3) + ", perhaps you need to enable force jumbo mode.");
                } else {
                    this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) i3);
                    return;
                }
            case 27:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(i3), InstructionCodec.unit1(i3));
                return;
            case 28:
            case 31:
            case 34:
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) i3);
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 1)), (short) i3, InstructionCodec.codeUnit(i6, 0, 0, 0));
                return;
            case 38:
            case 43:
            case 44:
                switch (i2) {
                    case 43:
                    case 44:
                        this.codeOut.setBaseAddress(i5, this.codeOut.cursor());
                        break;
                }
                literalInt = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(literalInt), InstructionCodec.unit1(literalInt));
                return;
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.getTargetUnit(i5, this.codeOut.cursor()));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitTwoRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        if (this.hasPromoter) {
            i5 = this.insnPromoter.getPromotedAddress(i5);
        }
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
                this.codeOut.write(InstructionCodec.codeUnit((short) i2, InstructionCodec.makeByte(i6, i7)));
                return;
            case 2:
            case 5:
            case 8:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.getBUnit(i7));
                return;
            case 3:
            case 6:
            case 9:
                this.codeOut.write((short) i2, InstructionCodec.getAUnit(i6), InstructionCodec.getBUnit(i7));
                return;
            case 32:
            case 35:
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i6, i7)), (short) i3);
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 2)), (short) i3, InstructionCodec.codeUnit(i6, i7, 0, 0));
                return;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i6, i7)), InstructionCodec.getTargetUnit(i5, this.codeOut.cursor()));
                return;
            case 208:
            case 209:
            case Opcodes.MUL_INT_LIT16 /*210*/:
            case Opcodes.DIV_INT_LIT16 /*211*/:
            case Opcodes.REM_INT_LIT16 /*212*/:
            case Opcodes.AND_INT_LIT16 /*213*/:
            case Opcodes.OR_INT_LIT16 /*214*/:
            case Opcodes.XOR_INT_LIT16 /*215*/:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i6, i7)), InstructionCodec.getLiteralUnit(j));
                return;
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.codeUnit(i7, InstructionCodec.getLiteralByte(j)));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitThreeRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8) {
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 3)), (short) i3, InstructionCodec.codeUnit(i6, i7, i8, 0));
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.codeUnit(i7, i8));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFourRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 4)), (short) i3, InstructionCodec.codeUnit(i6, i7, i8, i9));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFiveRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9, int i10) {
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i10, 5)), (short) i3, InstructionCodec.codeUnit(i6, i7, i8, i9));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitRegisterRangeInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        switch (i2) {
            case 37:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i7), (short) i3, InstructionCodec.getAUnit(i6));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitSparseSwitchPayloadInsn(int i, int i2, int[] iArr, int[] iArr2) {
        int length;
        int i3 = 0;
        int baseAddressForCursor = this.codeOut.baseAddressForCursor();
        this.codeOut.write((short) i2);
        this.codeOut.write(InstructionCodec.asUnsignedUnit(iArr2.length));
        for (int writeInt : iArr) {
            this.codeOut.writeInt(writeInt);
        }
        if (this.hasPromoter) {
            length = iArr2.length;
            while (i3 < length) {
                this.codeOut.writeInt(this.insnPromoter.getPromotedAddress(iArr2[i3]) - baseAddressForCursor);
                i3++;
            }
            return;
        }
        length = iArr2.length;
        while (i3 < length) {
            this.codeOut.writeInt(iArr2[i3] - baseAddressForCursor);
            i3++;
        }
    }

    public void visitPackedSwitchPayloadInsn(int i, int i2, int i3, int[] iArr) {
        int i4 = 0;
        int baseAddressForCursor = this.codeOut.baseAddressForCursor();
        this.codeOut.write((short) i2);
        this.codeOut.write(InstructionCodec.asUnsignedUnit(iArr.length));
        this.codeOut.writeInt(i3);
        int length;
        if (this.hasPromoter) {
            length = iArr.length;
            while (i4 < length) {
                this.codeOut.writeInt(this.insnPromoter.getPromotedAddress(iArr[i4]) - baseAddressForCursor);
                i4++;
            }
            return;
        }
        length = iArr.length;
        while (i4 < length) {
            this.codeOut.writeInt(iArr[i4] - baseAddressForCursor);
            i4++;
        }
    }

    public void visitFillArrayDataPayloadInsn(int i, int i2, Object obj, int i3, int i4) {
        this.codeOut.write((short) i2);
        this.codeOut.write((short) i4);
        this.codeOut.writeInt(i3);
        switch (i4) {
            case 1:
                this.codeOut.write((byte[]) obj);
                return;
            case 2:
                this.codeOut.write((short[]) obj);
                return;
            case 4:
                this.codeOut.write((int[]) obj);
                return;
            case 8:
                this.codeOut.write((long[]) obj);
                return;
            default:
                throw new DexException("bogus element_width: " + Hex.u2(i4));
        }
    }
}
