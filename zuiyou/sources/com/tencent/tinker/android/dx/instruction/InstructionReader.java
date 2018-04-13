package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;
import java.io.EOFException;

public final class InstructionReader {
    private final ShortArrayCodeInput codeIn;

    public InstructionReader(ShortArrayCodeInput shortArrayCodeInput) {
        this.codeIn = shortArrayCodeInput;
    }

    public void accept(InstructionVisitor instructionVisitor) throws EOFException {
        this.codeIn.reset();
        while (this.codeIn.hasMore()) {
            int cursor = this.codeIn.cursor();
            int read = this.codeIn.read();
            int extractOpcodeFromUnit = Opcodes.extractOpcodeFromUnit(read);
            int byte0;
            int byte02;
            int read2;
            int nibble0;
            int nibble3;
            int instructionIndexType;
            int readInt;
            int[] iArr;
            switch (extractOpcodeFromUnit) {
                case -1:
                    instructionVisitor.visitZeroRegisterInsn(cursor, read, 0, 1, 0, 0);
                    break;
                case 0:
                case 14:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) InstructionCodec.byte1(read));
                    break;
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
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, 0, InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
                case 2:
                case 5:
                case 8:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, 0, InstructionCodec.byte1(read), this.codeIn.read());
                    break;
                case 3:
                case 6:
                case 9:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) InstructionCodec.byte1(read), this.codeIn.read(), this.codeIn.read());
                    break;
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
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, 0, InstructionCodec.byte1(read));
                    break;
                case 18:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) ((InstructionCodec.nibble3(read) << 28) >> 28), InstructionCodec.nibble2(read));
                    break;
                case 19:
                case 22:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) ((short) this.codeIn.read()), InstructionCodec.byte1(read));
                    break;
                case 20:
                case 23:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) this.codeIn.readInt(), InstructionCodec.byte1(read));
                    break;
                case 21:
                case 25:
                    byte0 = InstructionCodec.byte0(read);
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, 0, 1, 0, ((long) ((short) this.codeIn.read())) << (byte0 == 21 ? 16 : 48), InstructionCodec.byte1(read));
                    break;
                case 24:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, this.codeIn.readLong(), InstructionCodec.byte1(read));
                    break;
                case 26:
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
                    byte0 = InstructionCodec.byte0(read);
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, this.codeIn.read(), InstructionCodec.getInstructionIndexType(byte0), 0, 0, InstructionCodec.byte1(read));
                    break;
                case 27:
                    byte0 = InstructionCodec.byte0(read);
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, this.codeIn.readInt(), InstructionCodec.getInstructionIndexType(byte0), 0, 0, InstructionCodec.byte1(read));
                    break;
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
                    byte02 = InstructionCodec.byte0(read);
                    instructionVisitor.visitTwoRegisterInsn(cursor, byte02, this.codeIn.read(), InstructionCodec.getInstructionIndexType(byte02), 0, 0, InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
                case 36:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                    int byte03 = InstructionCodec.byte0(read);
                    int nibble2 = InstructionCodec.nibble2(read);
                    extractOpcodeFromUnit = InstructionCodec.nibble3(read);
                    read2 = this.codeIn.read();
                    read = this.codeIn.read();
                    nibble0 = InstructionCodec.nibble0(read);
                    byte0 = InstructionCodec.nibble1(read);
                    byte02 = InstructionCodec.nibble2(read);
                    nibble3 = InstructionCodec.nibble3(read);
                    instructionIndexType = InstructionCodec.getInstructionIndexType(byte03);
                    switch (extractOpcodeFromUnit) {
                        case 0:
                            instructionVisitor.visitZeroRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0);
                            break;
                        case 1:
                            instructionVisitor.visitOneRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0);
                            break;
                        case 2:
                            instructionVisitor.visitTwoRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0);
                            break;
                        case 3:
                            instructionVisitor.visitThreeRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0, byte02);
                            break;
                        case 4:
                            instructionVisitor.visitFourRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0, byte02, nibble3);
                            break;
                        case 5:
                            instructionVisitor.visitFiveRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0, byte02, nibble3, nibble2);
                            break;
                        default:
                            throw new DexException("bogus registerCount: " + Hex.uNibble(extractOpcodeFromUnit));
                    }
                case 37:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                    byte02 = InstructionCodec.byte0(read);
                    instructionVisitor.visitRegisterRangeInsn(cursor, byte02, this.codeIn.read(), InstructionCodec.getInstructionIndexType(byte02), 0, 0, this.codeIn.read(), InstructionCodec.byte1(read));
                    break;
                case 38:
                case 43:
                case 44:
                    byte0 = InstructionCodec.byte0(read);
                    nibble0 = InstructionCodec.byte1(read);
                    readInt = cursor + this.codeIn.readInt();
                    switch (byte0) {
                        case 43:
                        case 44:
                            this.codeIn.setBaseAddress(readInt + 1, cursor);
                            break;
                    }
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, 0, 1, readInt, 0, nibble0);
                    break;
                case 40:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((byte) InstructionCodec.byte1(read)), 0);
                    break;
                case 41:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((short) this.codeIn.read()), (long) InstructionCodec.byte1(read));
                    break;
                case 42:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + this.codeIn.readInt(), (long) InstructionCodec.byte1(read));
                    break;
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
                    nibble3 = InstructionCodec.byte0(read);
                    nibble0 = InstructionCodec.byte1(read);
                    extractOpcodeFromUnit = this.codeIn.read();
                    instructionVisitor.visitThreeRegisterInsn(cursor, nibble3, 0, 1, 0, 0, nibble0, InstructionCodec.byte0(extractOpcodeFromUnit), InstructionCodec.byte1(extractOpcodeFromUnit));
                    break;
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((short) this.codeIn.read()), 0, InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((short) this.codeIn.read()), 0, InstructionCodec.byte1(read));
                    break;
                case 208:
                case 209:
                case Opcodes.MUL_INT_LIT16 /*210*/:
                case Opcodes.DIV_INT_LIT16 /*211*/:
                case Opcodes.REM_INT_LIT16 /*212*/:
                case Opcodes.AND_INT_LIT16 /*213*/:
                case Opcodes.OR_INT_LIT16 /*214*/:
                case Opcodes.XOR_INT_LIT16 /*215*/:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) ((short) this.codeIn.read()), InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
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
                    byte02 = InstructionCodec.byte0(read);
                    nibble0 = InstructionCodec.byte1(read);
                    extractOpcodeFromUnit = this.codeIn.read();
                    instructionVisitor.visitTwoRegisterInsn(cursor, byte02, 0, 1, 0, (long) ((byte) InstructionCodec.byte1(extractOpcodeFromUnit)), nibble0, InstructionCodec.byte0(extractOpcodeFromUnit));
                    break;
                case 256:
                    read2 = this.codeIn.baseAddressForCursor();
                    instructionIndexType = this.codeIn.read();
                    readInt = this.codeIn.readInt();
                    iArr = new int[instructionIndexType];
                    for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < instructionIndexType; extractOpcodeFromUnit++) {
                        iArr[extractOpcodeFromUnit] = this.codeIn.readInt() + read2;
                    }
                    instructionVisitor.visitPackedSwitchPayloadInsn(cursor, read, readInt, iArr);
                    break;
                case 512:
                    read2 = this.codeIn.baseAddressForCursor();
                    instructionIndexType = this.codeIn.read();
                    int[] iArr2 = new int[instructionIndexType];
                    iArr = new int[instructionIndexType];
                    for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < instructionIndexType; extractOpcodeFromUnit++) {
                        iArr2[extractOpcodeFromUnit] = this.codeIn.readInt();
                    }
                    for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < instructionIndexType; extractOpcodeFromUnit++) {
                        iArr[extractOpcodeFromUnit] = this.codeIn.readInt() + read2;
                    }
                    instructionVisitor.visitSparseSwitchPayloadInsn(cursor, read, iArr2, iArr);
                    break;
                case Opcodes.FILL_ARRAY_DATA_PAYLOAD /*768*/:
                    extractOpcodeFromUnit = this.codeIn.read();
                    int readInt2 = this.codeIn.readInt();
                    Object obj;
                    switch (extractOpcodeFromUnit) {
                        case 1:
                            obj = new byte[readInt2];
                            extractOpcodeFromUnit = 0;
                            Object obj2 = 1;
                            readInt = 0;
                            while (readInt < readInt2) {
                                if (obj2 != null) {
                                    extractOpcodeFromUnit = this.codeIn.read();
                                }
                                obj[readInt] = (byte) (extractOpcodeFromUnit & 255);
                                readInt++;
                                obj2 = obj2 == null ? 1 : null;
                                extractOpcodeFromUnit >>= 8;
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 1);
                            break;
                        case 2:
                            obj = new short[readInt2];
                            for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < readInt2; extractOpcodeFromUnit++) {
                                obj[extractOpcodeFromUnit] = (short) this.codeIn.read();
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 2);
                            break;
                        case 4:
                            obj = new int[readInt2];
                            for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < readInt2; extractOpcodeFromUnit++) {
                                obj[extractOpcodeFromUnit] = this.codeIn.readInt();
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 4);
                            break;
                        case 8:
                            obj = new long[readInt2];
                            for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < readInt2; extractOpcodeFromUnit++) {
                                obj[extractOpcodeFromUnit] = this.codeIn.readLong();
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 8);
                            break;
                        default:
                            throw new DexException("bogus element_width: " + Hex.u2(extractOpcodeFromUnit));
                    }
                default:
                    throw new IllegalStateException("Unknown opcode: " + Hex.u4(extractOpcodeFromUnit));
            }
        }
    }
}
