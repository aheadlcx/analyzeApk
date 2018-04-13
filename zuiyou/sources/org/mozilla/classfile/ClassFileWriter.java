package org.mozilla.classfile;

import android.support.v4.view.ViewCompat;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.UintMap;

public class ClassFileWriter {
    public static final short ACC_ABSTRACT = (short) 1024;
    public static final short ACC_FINAL = (short) 16;
    public static final short ACC_NATIVE = (short) 256;
    public static final short ACC_PRIVATE = (short) 2;
    public static final short ACC_PROTECTED = (short) 4;
    public static final short ACC_PUBLIC = (short) 1;
    public static final short ACC_STATIC = (short) 8;
    public static final short ACC_SUPER = (short) 32;
    public static final short ACC_SYNCHRONIZED = (short) 32;
    public static final short ACC_TRANSIENT = (short) 128;
    public static final short ACC_VOLATILE = (short) 64;
    private static final boolean DEBUGCODE = false;
    private static final boolean DEBUGLABELS = false;
    private static final boolean DEBUGSTACK = false;
    private static final int ExceptionTableSize = 4;
    private static final int FileHeaderConstant = -889275714;
    private static final boolean GenerateStackMap;
    private static final int LineNumberTableSize = 16;
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
    private static final int MIN_LABEL_TABLE_SIZE = 32;
    private static final int MajorVersion;
    private static final int MinorVersion;
    private static final int SuperBlockStartsSize = 4;
    private String generatedClassName;
    private byte[] itsCodeBuffer = new byte[256];
    private int itsCodeBufferTop;
    private ConstantPool itsConstantPool;
    private ClassFileMethod itsCurrentMethod;
    private ExceptionTableEntry[] itsExceptionTable;
    private int itsExceptionTableTop;
    private ObjArray itsFields = new ObjArray();
    private long[] itsFixupTable;
    private int itsFixupTableTop;
    private short itsFlags;
    private ObjArray itsInterfaces = new ObjArray();
    private UintMap itsJumpFroms = null;
    private int[] itsLabelTable;
    private int itsLabelTableTop;
    private int[] itsLineNumberTable;
    private int itsLineNumberTableTop;
    private short itsMaxLocals;
    private short itsMaxStack;
    private ObjArray itsMethods = new ObjArray();
    private short itsSourceFileNameIndex;
    private short itsStackTop;
    private int[] itsSuperBlockStarts = null;
    private int itsSuperBlockStartsTop = 0;
    private short itsSuperClassIndex;
    private short itsThisClassIndex;
    private ObjArray itsVarDescriptors;
    private char[] tmpCharBuffer = new char[64];

    public static class ClassFileFormatException extends RuntimeException {
        private static final long serialVersionUID = 1263998431033790599L;

        ClassFileFormatException(String str) {
            super(str);
        }
    }

    final class StackMapTable {
        static final boolean DEBUGSTACKMAP = false;
        private int[] locals = null;
        private int localsTop = 0;
        private byte[] rawStackMap = null;
        private int rawStackMapTop = 0;
        private int[] stack = null;
        private int stackTop = 0;
        private SuperBlock[] superBlockDeps;
        private SuperBlock[] superBlocks = null;
        private boolean wide = false;
        private SuperBlock[] workList = null;
        private int workListTop = 0;

        StackMapTable() {
        }

        void generate() {
            this.superBlocks = new SuperBlock[ClassFileWriter.this.itsSuperBlockStartsTop];
            int[] access$100 = ClassFileWriter.this.createInitialLocals();
            for (int i = 0; i < ClassFileWriter.this.itsSuperBlockStartsTop; i++) {
                int access$300;
                int i2 = ClassFileWriter.this.itsSuperBlockStarts[i];
                if (i == ClassFileWriter.this.itsSuperBlockStartsTop - 1) {
                    access$300 = ClassFileWriter.this.itsCodeBufferTop;
                } else {
                    access$300 = ClassFileWriter.this.itsSuperBlockStarts[i + 1];
                }
                this.superBlocks[i] = new SuperBlock(i, i2, access$300, access$100);
            }
            this.superBlockDeps = getSuperBlockDependencies();
            verify();
        }

        private SuperBlock getSuperBlockFromOffset(int i) {
            int i2 = 0;
            while (i2 < this.superBlocks.length) {
                SuperBlock superBlock = this.superBlocks[i2];
                if (superBlock == null) {
                    break;
                } else if (i >= superBlock.getStart() && i < superBlock.getEnd()) {
                    return superBlock;
                } else {
                    i2++;
                }
            }
            throw new IllegalArgumentException("bad offset: " + i);
        }

        private boolean isSuperBlockEnd(int i) {
            switch (i) {
                case 167:
                case 170:
                case 171:
                case 172:
                case 173:
                case 174:
                case 176:
                case 177:
                case 191:
                case 200:
                    return true;
                default:
                    return false;
            }
        }

        private SuperBlock[] getSuperBlockDependencies() {
            int i = 0;
            SuperBlock[] superBlockArr = new SuperBlock[this.superBlocks.length];
            for (int i2 = 0; i2 < ClassFileWriter.this.itsExceptionTableTop; i2++) {
                ExceptionTableEntry exceptionTableEntry = ClassFileWriter.this.itsExceptionTable[i2];
                short labelPC = (short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsStartLabel);
                SuperBlock superBlockFromOffset = getSuperBlockFromOffset((short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsHandlerLabel));
                superBlockArr[superBlockFromOffset.getIndex()] = getSuperBlockFromOffset(labelPC);
            }
            int[] keys = ClassFileWriter.this.itsJumpFroms.getKeys();
            while (i < keys.length) {
                int i3 = keys[i];
                superBlockArr[getSuperBlockFromOffset(i3).getIndex()] = getSuperBlockFromOffset(ClassFileWriter.this.itsJumpFroms.getInt(i3, -1));
                i++;
            }
            return superBlockArr;
        }

        private SuperBlock getBranchTarget(int i) {
            int operand;
            if ((ClassFileWriter.this.itsCodeBuffer[i] & 255) == 200) {
                operand = getOperand(i + 1, 4) + i;
            } else {
                operand = ((short) getOperand(i + 1, 2)) + i;
            }
            return getSuperBlockFromOffset(operand);
        }

        private boolean isBranch(int i) {
            switch (i) {
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
                case 198:
                case 199:
                case 200:
                    return true;
                default:
                    return false;
            }
        }

        private int getOperand(int i) {
            return getOperand(i, 1);
        }

        private int getOperand(int i, int i2) {
            int i3 = 0;
            if (i2 > 4) {
                throw new IllegalArgumentException("bad operand size");
            }
            int i4 = 0;
            while (i3 < i2) {
                i4 = (i4 << 8) | (ClassFileWriter.this.itsCodeBuffer[i + i3] & 255);
                i3++;
            }
            return i4;
        }

        private void verify() {
            int i = 0;
            int[] access$100 = ClassFileWriter.this.createInitialLocals();
            this.superBlocks[0].merge(access$100, access$100.length, new int[0], 0, ClassFileWriter.this.itsConstantPool);
            this.workList = new SuperBlock[]{this.superBlocks[0]};
            this.workListTop = 1;
            executeWorkList();
            while (i < this.superBlocks.length) {
                SuperBlock superBlock = this.superBlocks[i];
                if (!superBlock.isInitialized()) {
                    killSuperBlock(superBlock);
                }
                i++;
            }
            executeWorkList();
        }

        private void killSuperBlock(SuperBlock superBlock) {
            int i;
            int[] iArr = new int[0];
            int[] iArr2 = new int[]{TypeInfo.OBJECT("java/lang/Throwable", ClassFileWriter.this.itsConstantPool)};
            for (i = 0; i < ClassFileWriter.this.itsExceptionTableTop; i++) {
                ExceptionTableEntry exceptionTableEntry = ClassFileWriter.this.itsExceptionTable[i];
                int labelPC = ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsStartLabel);
                int labelPC2 = ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsEndLabel);
                SuperBlock superBlockFromOffset = getSuperBlockFromOffset(ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsHandlerLabel));
                if ((superBlock.getStart() > labelPC && superBlock.getStart() < labelPC2) || (labelPC > superBlock.getStart() && labelPC < superBlock.getEnd() && superBlockFromOffset.isInitialized())) {
                    iArr = superBlockFromOffset.getLocals();
                    break;
                }
            }
            i = 0;
            while (i < ClassFileWriter.this.itsExceptionTableTop) {
                if (ClassFileWriter.this.getLabelPC(ClassFileWriter.this.itsExceptionTable[i].itsStartLabel) == superBlock.getStart()) {
                    for (int i2 = i + 1; i2 < ClassFileWriter.this.itsExceptionTableTop; i2++) {
                        ClassFileWriter.this.itsExceptionTable[i2 - 1] = ClassFileWriter.this.itsExceptionTable[i2];
                    }
                    ClassFileWriter.this.itsExceptionTableTop = ClassFileWriter.this.itsExceptionTableTop - 1;
                    i--;
                }
                i++;
            }
            superBlock.merge(iArr, iArr.length, iArr2, iArr2.length, ClassFileWriter.this.itsConstantPool);
            int end = superBlock.getEnd() - 1;
            ClassFileWriter.this.itsCodeBuffer[end] = (byte) -65;
            for (i = superBlock.getStart(); i < end; i++) {
                ClassFileWriter.this.itsCodeBuffer[i] = (byte) 0;
            }
        }

        private void executeWorkList() {
            while (this.workListTop > 0) {
                SuperBlock[] superBlockArr = this.workList;
                int i = this.workListTop - 1;
                this.workListTop = i;
                SuperBlock superBlock = superBlockArr[i];
                superBlock.setInQueue(false);
                this.locals = superBlock.getLocals();
                this.stack = superBlock.getStack();
                this.localsTop = this.locals.length;
                this.stackTop = this.stack.length;
                executeBlock(superBlock);
            }
        }

        private void executeBlock(SuperBlock superBlock) {
            short start = superBlock.getStart();
            int i = 0;
            while (start < superBlock.getEnd()) {
                int i2 = ClassFileWriter.this.itsCodeBuffer[start] & 255;
                int execute = execute(start);
                if (isBranch(i2)) {
                    flowInto(getBranchTarget(start));
                } else if (i2 == 170) {
                    i = (start + 1) + ((start ^ -1) & 3);
                    flowInto(getSuperBlockFromOffset(getOperand(i, 4) + start));
                    int operand = (getOperand(i + 8, 4) - getOperand(i + 4, 4)) + 1;
                    int i3 = i + 12;
                    for (i = 0; i < operand; i++) {
                        flowInto(getSuperBlockFromOffset(getOperand((i * 4) + i3, 4) + start));
                    }
                }
                for (int i4 = 0; i4 < ClassFileWriter.this.itsExceptionTableTop; i4++) {
                    ExceptionTableEntry exceptionTableEntry = ClassFileWriter.this.itsExceptionTable[i4];
                    short labelPC = (short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsEndLabel);
                    if (start >= ((short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsStartLabel)) && start < labelPC) {
                        int OBJECT;
                        SuperBlock superBlockFromOffset = getSuperBlockFromOffset((short) ClassFileWriter.this.getLabelPC(exceptionTableEntry.itsHandlerLabel));
                        if (exceptionTableEntry.itsCatchType == (short) 0) {
                            OBJECT = TypeInfo.OBJECT(ClassFileWriter.this.itsConstantPool.addClass("java/lang/Throwable"));
                        } else {
                            OBJECT = TypeInfo.OBJECT(exceptionTableEntry.itsCatchType);
                        }
                        superBlockFromOffset.merge(this.locals, this.localsTop, new int[]{OBJECT}, 1, ClassFileWriter.this.itsConstantPool);
                        addToWorkList(superBlockFromOffset);
                    }
                }
                start += execute;
                i = i2;
            }
            if (!isSuperBlockEnd(i)) {
                i = superBlock.getIndex() + 1;
                if (i < this.superBlocks.length) {
                    flowInto(this.superBlocks[i]);
                }
            }
        }

        private void flowInto(SuperBlock superBlock) {
            if (superBlock.merge(this.locals, this.localsTop, this.stack, this.stackTop, ClassFileWriter.this.itsConstantPool)) {
                addToWorkList(superBlock);
            }
        }

        private void addToWorkList(SuperBlock superBlock) {
            if (!superBlock.isInQueue()) {
                superBlock.setInQueue(true);
                superBlock.setInitialized(true);
                if (this.workListTop == this.workList.length) {
                    Object obj = new SuperBlock[(this.workListTop * 2)];
                    System.arraycopy(this.workList, 0, obj, 0, this.workListTop);
                    this.workList = obj;
                }
                SuperBlock[] superBlockArr = this.workList;
                int i = this.workListTop;
                this.workListTop = i + 1;
                superBlockArr[i] = superBlock;
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int execute(int r9) {
            /*
            r8 = this;
            r7 = 3;
            r3 = 0;
            r6 = 4;
            r1 = 1;
            r0 = 2;
            r2 = org.mozilla.classfile.ClassFileWriter.this;
            r2 = r2.itsCodeBuffer;
            r2 = r2[r9];
            r4 = r2 & 255;
            switch(r4) {
                case 0: goto L_0x002c;
                case 1: goto L_0x0062;
                case 2: goto L_0x006e;
                case 3: goto L_0x006e;
                case 4: goto L_0x006e;
                case 5: goto L_0x006e;
                case 6: goto L_0x006e;
                case 7: goto L_0x006e;
                case 8: goto L_0x006e;
                case 9: goto L_0x0079;
                case 10: goto L_0x0079;
                case 11: goto L_0x0084;
                case 12: goto L_0x0084;
                case 13: goto L_0x0084;
                case 14: goto L_0x008f;
                case 15: goto L_0x008f;
                case 16: goto L_0x006e;
                case 17: goto L_0x006e;
                case 18: goto L_0x0151;
                case 19: goto L_0x0151;
                case 20: goto L_0x0151;
                case 21: goto L_0x006e;
                case 22: goto L_0x0079;
                case 23: goto L_0x0084;
                case 24: goto L_0x008f;
                case 25: goto L_0x00f9;
                case 26: goto L_0x006e;
                case 27: goto L_0x006e;
                case 28: goto L_0x006e;
                case 29: goto L_0x006e;
                case 30: goto L_0x0079;
                case 31: goto L_0x0079;
                case 32: goto L_0x0079;
                case 33: goto L_0x0079;
                case 34: goto L_0x0084;
                case 35: goto L_0x0084;
                case 36: goto L_0x0084;
                case 37: goto L_0x0084;
                case 38: goto L_0x008f;
                case 39: goto L_0x008f;
                case 40: goto L_0x008f;
                case 41: goto L_0x008f;
                case 42: goto L_0x010b;
                case 43: goto L_0x010b;
                case 44: goto L_0x010b;
                case 45: goto L_0x010b;
                case 46: goto L_0x0068;
                case 47: goto L_0x0073;
                case 48: goto L_0x007e;
                case 49: goto L_0x0089;
                case 50: goto L_0x0374;
                case 51: goto L_0x0068;
                case 52: goto L_0x0068;
                case 53: goto L_0x0068;
                case 54: goto L_0x0094;
                case 55: goto L_0x00ac;
                case 56: goto L_0x00c6;
                case 57: goto L_0x00df;
                case 58: goto L_0x0113;
                case 59: goto L_0x00a5;
                case 60: goto L_0x00a5;
                case 61: goto L_0x00a5;
                case 62: goto L_0x00a5;
                case 63: goto L_0x00be;
                case 64: goto L_0x00be;
                case 65: goto L_0x00be;
                case 66: goto L_0x00be;
                case 67: goto L_0x00d7;
                case 68: goto L_0x00d7;
                case 69: goto L_0x00d7;
                case 70: goto L_0x00d7;
                case 71: goto L_0x00f1;
                case 72: goto L_0x00f1;
                case 73: goto L_0x00f1;
                case 74: goto L_0x00f1;
                case 75: goto L_0x0125;
                case 76: goto L_0x0125;
                case 77: goto L_0x0125;
                case 78: goto L_0x0125;
                case 79: goto L_0x0052;
                case 80: goto L_0x0052;
                case 81: goto L_0x0052;
                case 82: goto L_0x0052;
                case 83: goto L_0x0052;
                case 84: goto L_0x0052;
                case 85: goto L_0x0052;
                case 86: goto L_0x0052;
                case 87: goto L_0x0058;
                case 88: goto L_0x005d;
                case 89: goto L_0x02ea;
                case 90: goto L_0x02f7;
                case 91: goto L_0x030b;
                case 92: goto L_0x031f;
                case 93: goto L_0x032c;
                case 94: goto L_0x0340;
                case 95: goto L_0x0140;
                case 96: goto L_0x0068;
                case 97: goto L_0x0073;
                case 98: goto L_0x007e;
                case 99: goto L_0x0089;
                case 100: goto L_0x0068;
                case 101: goto L_0x0073;
                case 102: goto L_0x007e;
                case 103: goto L_0x0089;
                case 104: goto L_0x0068;
                case 105: goto L_0x0073;
                case 106: goto L_0x007e;
                case 107: goto L_0x0089;
                case 108: goto L_0x0068;
                case 109: goto L_0x0073;
                case 110: goto L_0x007e;
                case 111: goto L_0x0089;
                case 112: goto L_0x0068;
                case 113: goto L_0x0073;
                case 114: goto L_0x007e;
                case 115: goto L_0x0089;
                case 116: goto L_0x006b;
                case 117: goto L_0x0076;
                case 118: goto L_0x0081;
                case 119: goto L_0x008c;
                case 120: goto L_0x0068;
                case 121: goto L_0x0073;
                case 122: goto L_0x0068;
                case 123: goto L_0x0073;
                case 124: goto L_0x0068;
                case 125: goto L_0x0073;
                case 126: goto L_0x0068;
                case 127: goto L_0x0073;
                case 128: goto L_0x0068;
                case 129: goto L_0x0073;
                case 130: goto L_0x0068;
                case 131: goto L_0x0073;
                case 132: goto L_0x002c;
                case 133: goto L_0x0076;
                case 134: goto L_0x0081;
                case 135: goto L_0x008c;
                case 136: goto L_0x006b;
                case 137: goto L_0x0081;
                case 138: goto L_0x008c;
                case 139: goto L_0x006b;
                case 140: goto L_0x0076;
                case 141: goto L_0x008c;
                case 142: goto L_0x006b;
                case 143: goto L_0x0076;
                case 144: goto L_0x0081;
                case 145: goto L_0x006b;
                case 146: goto L_0x006b;
                case 147: goto L_0x006b;
                case 148: goto L_0x0068;
                case 149: goto L_0x0068;
                case 150: goto L_0x0068;
                case 151: goto L_0x0068;
                case 152: goto L_0x0068;
                case 153: goto L_0x0058;
                case 154: goto L_0x0058;
                case 155: goto L_0x0058;
                case 156: goto L_0x0058;
                case 157: goto L_0x0058;
                case 158: goto L_0x0058;
                case 159: goto L_0x0055;
                case 160: goto L_0x0055;
                case 161: goto L_0x0055;
                case 162: goto L_0x0055;
                case 163: goto L_0x0055;
                case 164: goto L_0x0055;
                case 165: goto L_0x0055;
                case 166: goto L_0x0055;
                case 167: goto L_0x002c;
                case 168: goto L_0x0012;
                case 169: goto L_0x0012;
                case 170: goto L_0x0354;
                case 171: goto L_0x0012;
                case 172: goto L_0x012d;
                case 173: goto L_0x012d;
                case 174: goto L_0x012d;
                case 175: goto L_0x012d;
                case 176: goto L_0x012d;
                case 177: goto L_0x012d;
                case 178: goto L_0x02c0;
                case 179: goto L_0x0058;
                case 180: goto L_0x02bd;
                case 181: goto L_0x0055;
                case 182: goto L_0x0237;
                case 183: goto L_0x0237;
                case 184: goto L_0x0237;
                case 185: goto L_0x0237;
                case 186: goto L_0x0012;
                case 187: goto L_0x01b4;
                case 188: goto L_0x01be;
                case 189: goto L_0x01f8;
                case 190: goto L_0x006b;
                case 191: goto L_0x0133;
                case 192: goto L_0x0040;
                case 193: goto L_0x006b;
                case 194: goto L_0x0058;
                case 195: goto L_0x0058;
                case 196: goto L_0x03b6;
                case 197: goto L_0x0012;
                case 198: goto L_0x0058;
                case 199: goto L_0x0058;
                case 200: goto L_0x002c;
                default: goto L_0x0012;
            };
        L_0x0012:
            r0 = new java.lang.IllegalArgumentException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "bad opcode: ";
            r1 = r1.append(r2);
            r1 = r1.append(r4);
            r1 = r1.toString();
            r0.<init>(r1);
            throw r0;
        L_0x002c:
            r0 = r3;
        L_0x002d:
            if (r0 != 0) goto L_0x0035;
        L_0x002f:
            r0 = r8.wide;
            r0 = org.mozilla.classfile.ClassFileWriter.opcodeLength(r4, r0);
        L_0x0035:
            r1 = r8.wide;
            if (r1 == 0) goto L_0x003f;
        L_0x0039:
            r1 = 196; // 0xc4 float:2.75E-43 double:9.7E-322;
            if (r4 == r1) goto L_0x003f;
        L_0x003d:
            r8.wide = r3;
        L_0x003f:
            return r0;
        L_0x0040:
            r8.pop();
            r1 = r9 + 1;
            r0 = r8.getOperand(r1, r0);
            r0 = org.mozilla.classfile.TypeInfo.OBJECT(r0);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0052:
            r8.pop();
        L_0x0055:
            r8.pop();
        L_0x0058:
            r8.pop();
            r0 = r3;
            goto L_0x002d;
        L_0x005d:
            r8.pop2();
            r0 = r3;
            goto L_0x002d;
        L_0x0062:
            r0 = 5;
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0068:
            r8.pop();
        L_0x006b:
            r8.pop();
        L_0x006e:
            r8.push(r1);
            r0 = r3;
            goto L_0x002d;
        L_0x0073:
            r8.pop();
        L_0x0076:
            r8.pop();
        L_0x0079:
            r8.push(r6);
            r0 = r3;
            goto L_0x002d;
        L_0x007e:
            r8.pop();
        L_0x0081:
            r8.pop();
        L_0x0084:
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0089:
            r8.pop();
        L_0x008c:
            r8.pop();
        L_0x008f:
            r8.push(r7);
            r0 = r3;
            goto L_0x002d;
        L_0x0094:
            r2 = r9 + 1;
            r5 = r8.wide;
            if (r5 == 0) goto L_0x00a3;
        L_0x009a:
            r0 = r8.getOperand(r2, r0);
            r8.executeStore(r0, r1);
            r0 = r3;
            goto L_0x002d;
        L_0x00a3:
            r0 = r1;
            goto L_0x009a;
        L_0x00a5:
            r0 = r4 + -59;
            r8.executeStore(r0, r1);
            r0 = r3;
            goto L_0x002d;
        L_0x00ac:
            r2 = r9 + 1;
            r5 = r8.wide;
            if (r5 == 0) goto L_0x00bc;
        L_0x00b2:
            r0 = r8.getOperand(r2, r0);
            r8.executeStore(r0, r6);
            r0 = r3;
            goto L_0x002d;
        L_0x00bc:
            r0 = r1;
            goto L_0x00b2;
        L_0x00be:
            r0 = r4 + -63;
            r8.executeStore(r0, r6);
            r0 = r3;
            goto L_0x002d;
        L_0x00c6:
            r2 = r9 + 1;
            r5 = r8.wide;
            if (r5 == 0) goto L_0x00cd;
        L_0x00cc:
            r1 = r0;
        L_0x00cd:
            r1 = r8.getOperand(r2, r1);
            r8.executeStore(r1, r0);
            r0 = r3;
            goto L_0x002d;
        L_0x00d7:
            r1 = r4 + -67;
            r8.executeStore(r1, r0);
            r0 = r3;
            goto L_0x002d;
        L_0x00df:
            r2 = r9 + 1;
            r5 = r8.wide;
            if (r5 == 0) goto L_0x00ef;
        L_0x00e5:
            r0 = r8.getOperand(r2, r0);
            r8.executeStore(r0, r7);
            r0 = r3;
            goto L_0x002d;
        L_0x00ef:
            r0 = r1;
            goto L_0x00e5;
        L_0x00f1:
            r0 = r4 + -71;
            r8.executeStore(r0, r7);
            r0 = r3;
            goto L_0x002d;
        L_0x00f9:
            r2 = r9 + 1;
            r5 = r8.wide;
            if (r5 == 0) goto L_0x0109;
        L_0x00ff:
            r0 = r8.getOperand(r2, r0);
            r8.executeALoad(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0109:
            r0 = r1;
            goto L_0x00ff;
        L_0x010b:
            r0 = r4 + -42;
            r8.executeALoad(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0113:
            r2 = r9 + 1;
            r5 = r8.wide;
            if (r5 == 0) goto L_0x0123;
        L_0x0119:
            r0 = r8.getOperand(r2, r0);
            r8.executeAStore(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0123:
            r0 = r1;
            goto L_0x0119;
        L_0x0125:
            r0 = r4 + -75;
            r8.executeAStore(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x012d:
            r8.clearStack();
            r0 = r3;
            goto L_0x002d;
        L_0x0133:
            r0 = r8.pop();
            r8.clearStack();
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0140:
            r0 = r8.pop();
            r1 = r8.pop();
            r8.push(r0);
            r8.push(r1);
            r0 = r3;
            goto L_0x002d;
        L_0x0151:
            r2 = 18;
            if (r4 != r2) goto L_0x0182;
        L_0x0155:
            r2 = r9 + 1;
            r2 = r8.getOperand(r2);
        L_0x015b:
            r5 = org.mozilla.classfile.ClassFileWriter.this;
            r5 = r5.itsConstantPool;
            r2 = r5.getConstantType(r2);
            switch(r2) {
                case 3: goto L_0x019b;
                case 4: goto L_0x018f;
                case 5: goto L_0x0195;
                case 6: goto L_0x0189;
                case 7: goto L_0x0168;
                case 8: goto L_0x01a1;
                default: goto L_0x0168;
            };
        L_0x0168:
            r0 = new java.lang.IllegalArgumentException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r3 = "bad const type ";
            r1 = r1.append(r3);
            r1 = r1.append(r2);
            r1 = r1.toString();
            r0.<init>(r1);
            throw r0;
        L_0x0182:
            r2 = r9 + 1;
            r2 = r8.getOperand(r2, r0);
            goto L_0x015b;
        L_0x0189:
            r8.push(r7);
            r0 = r3;
            goto L_0x002d;
        L_0x018f:
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0195:
            r8.push(r6);
            r0 = r3;
            goto L_0x002d;
        L_0x019b:
            r8.push(r1);
            r0 = r3;
            goto L_0x002d;
        L_0x01a1:
            r0 = "java/lang/String";
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = org.mozilla.classfile.TypeInfo.OBJECT(r0, r1);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x01b4:
            r0 = org.mozilla.classfile.TypeInfo.UNINITIALIZED_VARIABLE(r9);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x01be:
            r8.pop();
            r0 = org.mozilla.classfile.ClassFileWriter.this;
            r0 = r0.itsCodeBuffer;
            r1 = r9 + 1;
            r0 = r0[r1];
            r0 = org.mozilla.classfile.ClassFileWriter.arrayTypeToName(r0);
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
            r5 = "[";
            r2 = r2.append(r5);
            r0 = r2.append(r0);
            r0 = r0.toString();
            r0 = r1.addClass(r0);
            r0 = (short) r0;
            r0 = org.mozilla.classfile.TypeInfo.OBJECT(r0);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x01f8:
            r1 = r9 + 1;
            r0 = r8.getOperand(r1, r0);
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = r1.getConstantData(r0);
            r0 = (java.lang.String) r0;
            r8.pop();
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "[L";
            r1 = r1.append(r2);
            r0 = r1.append(r0);
            r1 = 59;
            r0 = r0.append(r1);
            r0 = r0.toString();
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = org.mozilla.classfile.TypeInfo.OBJECT(r0, r1);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0237:
            r1 = r9 + 1;
            r0 = r8.getOperand(r1, r0);
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = r1.getConstantData(r0);
            r0 = (org.mozilla.classfile.FieldOrMethodRef) r0;
            r1 = r0.getType();
            r2 = r0.getName();
            r0 = org.mozilla.classfile.ClassFileWriter.sizeOfParameters(r1);
            r5 = r0 >>> 16;
            r0 = r3;
        L_0x0258:
            if (r0 >= r5) goto L_0x0260;
        L_0x025a:
            r8.pop();
            r0 = r0 + 1;
            goto L_0x0258;
        L_0x0260:
            r0 = 184; // 0xb8 float:2.58E-43 double:9.1E-322;
            if (r4 == r0) goto L_0x028b;
        L_0x0264:
            r0 = r8.pop();
            r5 = org.mozilla.classfile.TypeInfo.getTag(r0);
            r6 = org.mozilla.classfile.TypeInfo.UNINITIALIZED_VARIABLE(r3);
            if (r5 == r6) goto L_0x0275;
        L_0x0272:
            r6 = 6;
            if (r5 != r6) goto L_0x028b;
        L_0x0275:
            r5 = "<init>";
            r2 = r5.equals(r2);
            if (r2 == 0) goto L_0x02b4;
        L_0x027e:
            r2 = org.mozilla.classfile.ClassFileWriter.this;
            r2 = r2.itsThisClassIndex;
            r2 = org.mozilla.classfile.TypeInfo.OBJECT(r2);
            r8.initializeTypeInfo(r0, r2);
        L_0x028b:
            r0 = 41;
            r0 = r1.indexOf(r0);
            r0 = r0 + 1;
            r0 = r1.substring(r0);
            r0 = org.mozilla.classfile.ClassFileWriter.descriptorToInternalName(r0);
            r1 = "V";
            r1 = r0.equals(r1);
            if (r1 != 0) goto L_0x03bb;
        L_0x02a4:
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = org.mozilla.classfile.TypeInfo.fromType(r0, r1);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x02b4:
            r0 = new java.lang.IllegalStateException;
            r1 = "bad instance";
            r0.<init>(r1);
            throw r0;
        L_0x02bd:
            r8.pop();
        L_0x02c0:
            r1 = r9 + 1;
            r0 = r8.getOperand(r1, r0);
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = r1.getConstantData(r0);
            r0 = (org.mozilla.classfile.FieldOrMethodRef) r0;
            r0 = r0.getType();
            r0 = org.mozilla.classfile.ClassFileWriter.descriptorToInternalName(r0);
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = org.mozilla.classfile.TypeInfo.fromType(r0, r1);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x02ea:
            r0 = r8.pop();
            r8.push(r0);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x02f7:
            r0 = r8.pop();
            r1 = r8.pop();
            r8.push(r0);
            r8.push(r1);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x030b:
            r0 = r8.pop();
            r6 = r8.pop2();
            r8.push(r0);
            r8.push2(r6);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x031f:
            r0 = r8.pop2();
            r8.push2(r0);
            r8.push2(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x032c:
            r0 = r8.pop2();
            r2 = r8.pop();
            r8.push2(r0);
            r8.push(r2);
            r8.push2(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0340:
            r0 = r8.pop2();
            r6 = r8.pop2();
            r8.push2(r0);
            r8.push2(r6);
            r8.push2(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x0354:
            r0 = r9 + 1;
            r1 = r9 ^ -1;
            r1 = r1 & 3;
            r0 = r0 + r1;
            r1 = r0 + 4;
            r1 = r8.getOperand(r1, r6);
            r2 = r0 + 8;
            r2 = r8.getOperand(r2, r6);
            r1 = r2 - r1;
            r1 = r1 + 4;
            r1 = r1 * 4;
            r0 = r0 + r1;
            r0 = r0 - r9;
            r8.pop();
            goto L_0x002d;
        L_0x0374:
            r8.pop();
            r0 = r8.pop();
            r0 = r0 >>> 8;
            r2 = org.mozilla.classfile.ClassFileWriter.this;
            r2 = r2.itsConstantPool;
            r0 = r2.getConstantData(r0);
            r0 = (java.lang.String) r0;
            r2 = r0.charAt(r3);
            r5 = 91;
            if (r2 == r5) goto L_0x039a;
        L_0x0391:
            r0 = new java.lang.IllegalStateException;
            r1 = "bad array type";
            r0.<init>(r1);
            throw r0;
        L_0x039a:
            r0 = r0.substring(r1);
            r0 = org.mozilla.classfile.ClassFileWriter.descriptorToInternalName(r0);
            r1 = org.mozilla.classfile.ClassFileWriter.this;
            r1 = r1.itsConstantPool;
            r0 = r1.addClass(r0);
            r0 = org.mozilla.classfile.TypeInfo.OBJECT(r0);
            r8.push(r0);
            r0 = r3;
            goto L_0x002d;
        L_0x03b6:
            r8.wide = r1;
            r0 = r3;
            goto L_0x002d;
        L_0x03bb:
            r0 = r3;
            goto L_0x002d;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.StackMapTable.execute(int):int");
        }

        private void executeALoad(int i) {
            int local = getLocal(i);
            int tag = TypeInfo.getTag(local);
            if (tag == 7 || tag == 6 || tag == 8 || tag == 5) {
                push(local);
                return;
            }
            throw new IllegalStateException("bad local variable type: " + local + " at index: " + i);
        }

        private void executeAStore(int i) {
            setLocal(i, pop());
        }

        private void executeStore(int i, int i2) {
            pop();
            setLocal(i, i2);
        }

        private void initializeTypeInfo(int i, int i2) {
            initializeTypeInfo(i, i2, this.locals, this.localsTop);
            initializeTypeInfo(i, i2, this.stack, this.stackTop);
        }

        private void initializeTypeInfo(int i, int i2, int[] iArr, int i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (iArr[i4] == i) {
                    iArr[i4] = i2;
                }
            }
        }

        private int getLocal(int i) {
            if (i < this.localsTop) {
                return this.locals[i];
            }
            return 0;
        }

        private void setLocal(int i, int i2) {
            if (i >= this.localsTop) {
                Object obj = new int[(i + 1)];
                System.arraycopy(this.locals, 0, obj, 0, this.localsTop);
                this.locals = obj;
                this.localsTop = i + 1;
            }
            this.locals[i] = i2;
        }

        private void push(int i) {
            if (this.stackTop == this.stack.length) {
                Object obj = new int[Math.max(this.stackTop * 2, 4)];
                System.arraycopy(this.stack, 0, obj, 0, this.stackTop);
                this.stack = obj;
            }
            int[] iArr = this.stack;
            int i2 = this.stackTop;
            this.stackTop = i2 + 1;
            iArr[i2] = i;
        }

        private int pop() {
            int[] iArr = this.stack;
            int i = this.stackTop - 1;
            this.stackTop = i;
            return iArr[i];
        }

        private void push2(long j) {
            push((int) (j & 16777215));
            long j2 = j >>> 32;
            if (j2 != 0) {
                push((int) (j2 & 16777215));
            }
        }

        private long pop2() {
            long pop = (long) pop();
            return TypeInfo.isTwoWords((int) pop) ? pop : (pop << 32) | ((long) (pop() & ViewCompat.MEASURED_SIZE_MASK));
        }

        private void clearStack() {
            this.stackTop = 0;
        }

        int computeWriteSize() {
            this.rawStackMap = new byte[getWorstCaseWriteSize()];
            computeRawStackMap();
            return this.rawStackMapTop + 2;
        }

        int write(byte[] bArr, int i) {
            int putInt16 = ClassFileWriter.putInt16(this.superBlocks.length - 1, bArr, ClassFileWriter.putInt32(this.rawStackMapTop + 2, bArr, i));
            System.arraycopy(this.rawStackMap, 0, bArr, putInt16, this.rawStackMapTop);
            return putInt16 + this.rawStackMapTop;
        }

        private void computeRawStackMap() {
            int[] trimmedLocals = this.superBlocks[0].getTrimmedLocals();
            int i = -1;
            int i2 = 1;
            while (i2 < this.superBlocks.length) {
                SuperBlock superBlock = this.superBlocks[i2];
                int[] trimmedLocals2 = superBlock.getTrimmedLocals();
                int[] stack = superBlock.getStack();
                int start = (superBlock.getStart() - i) - 1;
                if (stack.length == 0) {
                    i = trimmedLocals.length > trimmedLocals2.length ? trimmedLocals2.length : trimmedLocals.length;
                    int abs = Math.abs(trimmedLocals.length - trimmedLocals2.length);
                    int i3 = 0;
                    while (i3 < i && trimmedLocals[i3] == trimmedLocals2[i3]) {
                        i3++;
                    }
                    if (i3 == trimmedLocals2.length && abs == 0) {
                        writeSameFrame(trimmedLocals2, start);
                    } else if (i3 == trimmedLocals2.length && abs <= 3) {
                        writeChopFrame(abs, start);
                    } else if (i3 != trimmedLocals.length || abs > 3) {
                        writeFullFrame(trimmedLocals2, stack, start);
                    } else {
                        writeAppendFrame(trimmedLocals2, abs, start);
                    }
                } else if (stack.length != 1) {
                    writeFullFrame(trimmedLocals2, stack, start);
                } else if (Arrays.equals(trimmedLocals, trimmedLocals2)) {
                    writeSameLocalsOneStackItemFrame(trimmedLocals2, stack, start);
                } else {
                    writeFullFrame(trimmedLocals2, stack, start);
                }
                i = superBlock.getStart();
                i2++;
                trimmedLocals = trimmedLocals2;
            }
        }

        private int getWorstCaseWriteSize() {
            return (this.superBlocks.length - 1) * (((ClassFileWriter.this.itsMaxLocals * 3) + 7) + (ClassFileWriter.this.itsMaxStack * 3));
        }

        private void writeSameFrame(int[] iArr, int i) {
            if (i <= 63) {
                byte[] bArr = this.rawStackMap;
                int i2 = this.rawStackMapTop;
                this.rawStackMapTop = i2 + 1;
                bArr[i2] = (byte) i;
                return;
            }
            bArr = this.rawStackMap;
            i2 = this.rawStackMapTop;
            this.rawStackMapTop = i2 + 1;
            bArr[i2] = (byte) -5;
            this.rawStackMapTop = ClassFileWriter.putInt16(i, this.rawStackMap, this.rawStackMapTop);
        }

        private void writeSameLocalsOneStackItemFrame(int[] iArr, int[] iArr2, int i) {
            byte[] bArr;
            int i2;
            if (i <= 63) {
                bArr = this.rawStackMap;
                i2 = this.rawStackMapTop;
                this.rawStackMapTop = i2 + 1;
                bArr[i2] = (byte) (i + 64);
            } else {
                bArr = this.rawStackMap;
                i2 = this.rawStackMapTop;
                this.rawStackMapTop = i2 + 1;
                bArr[i2] = (byte) -9;
                this.rawStackMapTop = ClassFileWriter.putInt16(i, this.rawStackMap, this.rawStackMapTop);
            }
            writeType(iArr2[0]);
        }

        private void writeFullFrame(int[] iArr, int[] iArr2, int i) {
            byte[] bArr = this.rawStackMap;
            int i2 = this.rawStackMapTop;
            this.rawStackMapTop = i2 + 1;
            bArr[i2] = (byte) -1;
            this.rawStackMapTop = ClassFileWriter.putInt16(i, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = ClassFileWriter.putInt16(iArr.length, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = writeTypes(iArr);
            this.rawStackMapTop = ClassFileWriter.putInt16(iArr2.length, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = writeTypes(iArr2);
        }

        private void writeAppendFrame(int[] iArr, int i, int i2) {
            int length = iArr.length - i;
            byte[] bArr = this.rawStackMap;
            int i3 = this.rawStackMapTop;
            this.rawStackMapTop = i3 + 1;
            bArr[i3] = (byte) (i + TinkerReport.KEY_LOADED_UNCAUGHT_EXCEPTION);
            this.rawStackMapTop = ClassFileWriter.putInt16(i2, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = writeTypes(iArr, length);
        }

        private void writeChopFrame(int i, int i2) {
            byte[] bArr = this.rawStackMap;
            int i3 = this.rawStackMapTop;
            this.rawStackMapTop = i3 + 1;
            bArr[i3] = (byte) (251 - i);
            this.rawStackMapTop = ClassFileWriter.putInt16(i2, this.rawStackMap, this.rawStackMapTop);
        }

        private int writeTypes(int[] iArr) {
            return writeTypes(iArr, 0);
        }

        private int writeTypes(int[] iArr, int i) {
            int i2 = this.rawStackMapTop;
            while (i < iArr.length) {
                this.rawStackMapTop = writeType(iArr[i]);
                i++;
            }
            return this.rawStackMapTop;
        }

        private int writeType(int i) {
            int i2 = i & 255;
            byte[] bArr = this.rawStackMap;
            int i3 = this.rawStackMapTop;
            this.rawStackMapTop = i3 + 1;
            bArr[i3] = (byte) i2;
            if (i2 == 7 || i2 == 8) {
                this.rawStackMapTop = ClassFileWriter.putInt16(i >>> 8, this.rawStackMap, this.rawStackMapTop);
            }
            return this.rawStackMapTop;
        }
    }

    public ClassFileWriter(String str, String str2, String str3) {
        this.generatedClassName = str;
        this.itsConstantPool = new ConstantPool(this);
        this.itsThisClassIndex = this.itsConstantPool.addClass(str);
        this.itsSuperClassIndex = this.itsConstantPool.addClass(str2);
        if (str3 != null) {
            this.itsSourceFileNameIndex = this.itsConstantPool.addUtf8(str3);
        }
        this.itsFlags = (short) 33;
    }

    public final String getClassName() {
        return this.generatedClassName;
    }

    public void addInterface(String str) {
        this.itsInterfaces.add(Short.valueOf(this.itsConstantPool.addClass(str)));
    }

    public void setFlags(short s) {
        this.itsFlags = s;
    }

    static String getSlashedForm(String str) {
        return str.replace('.', '/');
    }

    public static String classNameToSignature(String str) {
        int i = 1;
        int length = str.length();
        int i2 = length + 1;
        char[] cArr = new char[(i2 + 1)];
        cArr[0] = 'L';
        cArr[i2] = ';';
        str.getChars(0, length, cArr, 1);
        while (i != i2) {
            if (cArr[i] == '.') {
                cArr[i] = '/';
            }
            i++;
        }
        return new String(cArr, 0, i2 + 1);
    }

    public void addField(String str, String str2, short s) {
        this.itsFields.add(new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s));
    }

    public void addField(String str, String str2, short s, int i) {
        ClassFileField classFileField = new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s);
        classFileField.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), (short) 0, (short) 0, this.itsConstantPool.addConstant(i));
        this.itsFields.add(classFileField);
    }

    public void addField(String str, String str2, short s, long j) {
        ClassFileField classFileField = new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s);
        classFileField.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), (short) 0, (short) 2, this.itsConstantPool.addConstant(j));
        this.itsFields.add(classFileField);
    }

    public void addField(String str, String str2, short s, double d) {
        ClassFileField classFileField = new ClassFileField(this.itsConstantPool.addUtf8(str), this.itsConstantPool.addUtf8(str2), s);
        classFileField.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), (short) 0, (short) 2, this.itsConstantPool.addConstant(d));
        this.itsFields.add(classFileField);
    }

    public void addVariableDescriptor(String str, String str2, int i, int i2) {
        short addUtf8 = this.itsConstantPool.addUtf8(str);
        short addUtf82 = this.itsConstantPool.addUtf8(str2);
        Object obj = new int[]{addUtf8, addUtf82, i, i2};
        if (this.itsVarDescriptors == null) {
            this.itsVarDescriptors = new ObjArray();
        }
        this.itsVarDescriptors.add(obj);
    }

    public void startMethod(String str, String str2, short s) {
        this.itsCurrentMethod = new ClassFileMethod(str, this.itsConstantPool.addUtf8(str), str2, this.itsConstantPool.addUtf8(str2), s);
        this.itsJumpFroms = new UintMap();
        this.itsMethods.add(this.itsCurrentMethod);
        addSuperBlockStart(0);
    }

    public void stopMethod(short s) {
        if (this.itsCurrentMethod == null) {
            throw new IllegalStateException("No method to stop");
        }
        StackMapTable stackMapTable;
        int i;
        int size;
        int computeWriteSize;
        Object obj;
        short labelPC;
        short labelPC2;
        short labelPC3;
        short s2;
        int size2;
        int i2;
        fixLabelGotos();
        this.itsMaxLocals = s;
        if (GenerateStackMap) {
            finalizeSuperBlockStarts();
            StackMapTable stackMapTable2 = new StackMapTable();
            stackMapTable2.generate();
            stackMapTable = stackMapTable2;
        } else {
            stackMapTable = null;
        }
        if (this.itsLineNumberTable != null) {
            i = (this.itsLineNumberTableTop * 4) + 8;
        } else {
            i = 0;
        }
        if (this.itsVarDescriptors != null) {
            size = (this.itsVarDescriptors.size() * 10) + 8;
        } else {
            size = 0;
        }
        if (stackMapTable != null) {
            computeWriteSize = stackMapTable.computeWriteSize();
            if (computeWriteSize > 0) {
                computeWriteSize += 6;
                i = ((i + ((((this.itsCodeBufferTop + 14) + 2) + (this.itsExceptionTableTop * 8)) + 2)) + size) + computeWriteSize;
                if (i <= 65536) {
                    throw new ClassFileFormatException("generated bytecode for method exceeds 64K limit.");
                }
                obj = new byte[i];
                i = putInt32(this.itsCodeBufferTop, obj, putInt16(this.itsMaxLocals, obj, putInt16(this.itsMaxStack, obj, putInt32(i - 6, obj, putInt16(this.itsConstantPool.addUtf8(MNSConstants.ERROR_CODE_TAG), obj, 0)))));
                System.arraycopy(this.itsCodeBuffer, 0, obj, i, this.itsCodeBufferTop);
                i += this.itsCodeBufferTop;
                if (this.itsExceptionTableTop <= 0) {
                    size = putInt16(this.itsExceptionTableTop, obj, i);
                    i = 0;
                    while (i < this.itsExceptionTableTop) {
                        ExceptionTableEntry exceptionTableEntry = this.itsExceptionTable[i];
                        labelPC = (short) getLabelPC(exceptionTableEntry.itsStartLabel);
                        labelPC2 = (short) getLabelPC(exceptionTableEntry.itsEndLabel);
                        labelPC3 = (short) getLabelPC(exceptionTableEntry.itsHandlerLabel);
                        s2 = exceptionTableEntry.itsCatchType;
                        if (labelPC == (short) -1) {
                            throw new IllegalStateException("start label not defined");
                        } else if (labelPC2 == (short) -1) {
                            throw new IllegalStateException("end label not defined");
                        } else if (labelPC3 != (short) -1) {
                            throw new IllegalStateException("handler label not defined");
                        } else {
                            size = putInt16(s2, obj, putInt16(labelPC3, obj, putInt16(labelPC2, obj, putInt16(labelPC, obj, size))));
                            i++;
                        }
                    }
                } else {
                    size = putInt16(0, obj, i);
                }
                if (this.itsLineNumberTable == null) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (this.itsVarDescriptors != null) {
                    i++;
                }
                if (computeWriteSize > 0) {
                    i++;
                }
                size = putInt16(i, obj, size);
                if (this.itsLineNumberTable != null) {
                    size = putInt16(this.itsLineNumberTableTop, obj, putInt32((this.itsLineNumberTableTop * 4) + 2, obj, putInt16(this.itsConstantPool.addUtf8("LineNumberTable"), obj, size)));
                    for (i = 0; i < this.itsLineNumberTableTop; i++) {
                        size = putInt32(this.itsLineNumberTable[i], obj, size);
                    }
                }
                if (this.itsVarDescriptors != null) {
                    i = putInt16(this.itsConstantPool.addUtf8("LocalVariableTable"), obj, size);
                    size2 = this.itsVarDescriptors.size();
                    size = putInt16(size2, obj, putInt32((size2 * 10) + 2, obj, i));
                    for (i2 = 0; i2 < size2; i2++) {
                        int[] iArr = (int[]) this.itsVarDescriptors.get(i2);
                        int i3 = iArr[0];
                        int i4 = iArr[1];
                        int i5 = iArr[2];
                        size = putInt16(iArr[3], obj, putInt16(i4, obj, putInt16(i3, obj, putInt16(this.itsCodeBufferTop - i5, obj, putInt16(i5, obj, size)))));
                    }
                }
                if (computeWriteSize > 0) {
                    stackMapTable.write(obj, putInt16(this.itsConstantPool.addUtf8("StackMapTable"), obj, size));
                }
                this.itsCurrentMethod.setCodeAttribute(obj);
                this.itsExceptionTable = null;
                this.itsExceptionTableTop = 0;
                this.itsLineNumberTableTop = 0;
                this.itsCodeBufferTop = 0;
                this.itsCurrentMethod = null;
                this.itsMaxStack = (short) 0;
                this.itsStackTop = (short) 0;
                this.itsLabelTableTop = 0;
                this.itsFixupTableTop = 0;
                this.itsVarDescriptors = null;
                this.itsSuperBlockStarts = null;
                this.itsSuperBlockStartsTop = 0;
                this.itsJumpFroms = null;
                return;
            }
        }
        computeWriteSize = 0;
        i = ((i + ((((this.itsCodeBufferTop + 14) + 2) + (this.itsExceptionTableTop * 8)) + 2)) + size) + computeWriteSize;
        if (i <= 65536) {
            obj = new byte[i];
            i = putInt32(this.itsCodeBufferTop, obj, putInt16(this.itsMaxLocals, obj, putInt16(this.itsMaxStack, obj, putInt32(i - 6, obj, putInt16(this.itsConstantPool.addUtf8(MNSConstants.ERROR_CODE_TAG), obj, 0)))));
            System.arraycopy(this.itsCodeBuffer, 0, obj, i, this.itsCodeBufferTop);
            i += this.itsCodeBufferTop;
            if (this.itsExceptionTableTop <= 0) {
                size = putInt16(0, obj, i);
            } else {
                size = putInt16(this.itsExceptionTableTop, obj, i);
                i = 0;
                while (i < this.itsExceptionTableTop) {
                    ExceptionTableEntry exceptionTableEntry2 = this.itsExceptionTable[i];
                    labelPC = (short) getLabelPC(exceptionTableEntry2.itsStartLabel);
                    labelPC2 = (short) getLabelPC(exceptionTableEntry2.itsEndLabel);
                    labelPC3 = (short) getLabelPC(exceptionTableEntry2.itsHandlerLabel);
                    s2 = exceptionTableEntry2.itsCatchType;
                    if (labelPC == (short) -1) {
                        throw new IllegalStateException("start label not defined");
                    } else if (labelPC2 == (short) -1) {
                        throw new IllegalStateException("end label not defined");
                    } else if (labelPC3 != (short) -1) {
                        size = putInt16(s2, obj, putInt16(labelPC3, obj, putInt16(labelPC2, obj, putInt16(labelPC, obj, size))));
                        i++;
                    } else {
                        throw new IllegalStateException("handler label not defined");
                    }
                }
            }
            if (this.itsLineNumberTable == null) {
                i = 0;
            } else {
                i = 1;
            }
            if (this.itsVarDescriptors != null) {
                i++;
            }
            if (computeWriteSize > 0) {
                i++;
            }
            size = putInt16(i, obj, size);
            if (this.itsLineNumberTable != null) {
                size = putInt16(this.itsLineNumberTableTop, obj, putInt32((this.itsLineNumberTableTop * 4) + 2, obj, putInt16(this.itsConstantPool.addUtf8("LineNumberTable"), obj, size)));
                for (i = 0; i < this.itsLineNumberTableTop; i++) {
                    size = putInt32(this.itsLineNumberTable[i], obj, size);
                }
            }
            if (this.itsVarDescriptors != null) {
                i = putInt16(this.itsConstantPool.addUtf8("LocalVariableTable"), obj, size);
                size2 = this.itsVarDescriptors.size();
                size = putInt16(size2, obj, putInt32((size2 * 10) + 2, obj, i));
                for (i2 = 0; i2 < size2; i2++) {
                    int[] iArr2 = (int[]) this.itsVarDescriptors.get(i2);
                    int i32 = iArr2[0];
                    int i42 = iArr2[1];
                    int i52 = iArr2[2];
                    size = putInt16(iArr2[3], obj, putInt16(i42, obj, putInt16(i32, obj, putInt16(this.itsCodeBufferTop - i52, obj, putInt16(i52, obj, size)))));
                }
            }
            if (computeWriteSize > 0) {
                stackMapTable.write(obj, putInt16(this.itsConstantPool.addUtf8("StackMapTable"), obj, size));
            }
            this.itsCurrentMethod.setCodeAttribute(obj);
            this.itsExceptionTable = null;
            this.itsExceptionTableTop = 0;
            this.itsLineNumberTableTop = 0;
            this.itsCodeBufferTop = 0;
            this.itsCurrentMethod = null;
            this.itsMaxStack = (short) 0;
            this.itsStackTop = (short) 0;
            this.itsLabelTableTop = 0;
            this.itsFixupTableTop = 0;
            this.itsVarDescriptors = null;
            this.itsSuperBlockStarts = null;
            this.itsSuperBlockStartsTop = 0;
            this.itsJumpFroms = null;
            return;
        }
        throw new ClassFileFormatException("generated bytecode for method exceeds 64K limit.");
    }

    public void add(int i) {
        if (opcodeCount(i) != 0) {
            throw new IllegalArgumentException("Unexpected operands");
        }
        short stackChange = this.itsStackTop + stackChange(i);
        if (stackChange < (short) 0 || Short.MAX_VALUE < stackChange) {
            badStack(stackChange);
        }
        addToCodeBuffer(i);
        this.itsStackTop = (short) stackChange;
        if (stackChange > this.itsMaxStack) {
            this.itsMaxStack = (short) stackChange;
        }
        if (i == 191) {
            addSuperBlockStart(this.itsCodeBufferTop);
        }
    }

    public void add(int i, int i2) {
        short stackChange = this.itsStackTop + stackChange(i);
        if (stackChange < (short) 0 || Short.MAX_VALUE < stackChange) {
            badStack(stackChange);
        }
        switch (i) {
            case 16:
                if (((byte) i2) == i2) {
                    addToCodeBuffer(i);
                    addToCodeBuffer((byte) i2);
                    break;
                }
                throw new IllegalArgumentException("out of range byte");
            case 17:
                if (((short) i2) == i2) {
                    addToCodeBuffer(i);
                    addToCodeInt16(i2);
                    break;
                }
                throw new IllegalArgumentException("out of range short");
            case 18:
            case 19:
            case 20:
                if (i2 >= 0 && i2 < 65536) {
                    if (i2 < 256 && i != 19 && i != 20) {
                        addToCodeBuffer(i);
                        addToCodeBuffer(i2);
                        break;
                    }
                    if (i == 18) {
                        addToCodeBuffer(19);
                    } else {
                        addToCodeBuffer(i);
                    }
                    addToCodeInt16(i2);
                    break;
                }
                throw new IllegalArgumentException("out of range index");
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 169:
                if (i2 >= 0 && i2 < 65536) {
                    if (i2 < 256) {
                        addToCodeBuffer(i);
                        addToCodeBuffer(i2);
                        break;
                    }
                    addToCodeBuffer(196);
                    addToCodeBuffer(i);
                    addToCodeInt16(i2);
                    break;
                }
                throw new ClassFileFormatException("out of range variable");
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
            case 168:
            case 198:
            case 199:
                break;
            case 167:
                addSuperBlockStart(this.itsCodeBufferTop + 3);
                break;
            case 180:
            case 181:
                if (i2 >= 0 && i2 < 65536) {
                    addToCodeBuffer(i);
                    addToCodeInt16(i2);
                    break;
                }
                throw new IllegalArgumentException("out of range field");
            case 188:
                if (i2 >= 0 && i2 < 256) {
                    addToCodeBuffer(i);
                    addToCodeBuffer(i2);
                    break;
                }
                throw new IllegalArgumentException("out of range index");
            default:
                throw new IllegalArgumentException("Unexpected opcode for 1 operand");
        }
        if ((i2 & Integer.MIN_VALUE) == Integer.MIN_VALUE || (i2 >= 0 && i2 <= 65535)) {
            int i3 = this.itsCodeBufferTop;
            addToCodeBuffer(i);
            int i4;
            if ((i2 & Integer.MIN_VALUE) != Integer.MIN_VALUE) {
                addToCodeInt16(i2);
                i4 = i2 + i3;
                addSuperBlockStart(i4);
                this.itsJumpFroms.put(i4, i3);
            } else {
                i4 = getLabelPC(i2);
                if (i4 != -1) {
                    addToCodeInt16(i4 - i3);
                    addSuperBlockStart(i4);
                    this.itsJumpFroms.put(i4, i3);
                } else {
                    addLabelFixup(i2, i3 + 1);
                    addToCodeInt16(0);
                }
            }
            this.itsStackTop = (short) stackChange;
            if (stackChange > this.itsMaxStack) {
                this.itsMaxStack = (short) stackChange;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Bad label for branch");
    }

    public void addLoadConstant(int i) {
        switch (i) {
            case 0:
                add(3);
                return;
            case 1:
                add(4);
                return;
            case 2:
                add(5);
                return;
            case 3:
                add(6);
                return;
            case 4:
                add(7);
                return;
            case 5:
                add(8);
                return;
            default:
                add(18, this.itsConstantPool.addConstant(i));
                return;
        }
    }

    public void addLoadConstant(long j) {
        add(20, this.itsConstantPool.addConstant(j));
    }

    public void addLoadConstant(float f) {
        add(18, this.itsConstantPool.addConstant(f));
    }

    public void addLoadConstant(double d) {
        add(20, this.itsConstantPool.addConstant(d));
    }

    public void addLoadConstant(String str) {
        add(18, this.itsConstantPool.addConstant(str));
    }

    public void add(int i, int i2, int i3) {
        short stackChange = this.itsStackTop + stackChange(i);
        if (stackChange < (short) 0 || Short.MAX_VALUE < stackChange) {
            badStack(stackChange);
        }
        if (i == 132) {
            if (i2 < 0 || i2 >= 65536) {
                throw new ClassFileFormatException("out of range variable");
            } else if (i3 < 0 || i3 >= 65536) {
                throw new ClassFileFormatException("out of range increment");
            } else if (i2 > 255 || i3 < -128 || i3 > 127) {
                addToCodeBuffer(196);
                addToCodeBuffer(132);
                addToCodeInt16(i2);
                addToCodeInt16(i3);
            } else {
                addToCodeBuffer(132);
                addToCodeBuffer(i2);
                addToCodeBuffer(i3);
            }
        } else if (i != 197) {
            throw new IllegalArgumentException("Unexpected opcode for 2 operands");
        } else if (i2 < 0 || i2 >= 65536) {
            throw new IllegalArgumentException("out of range index");
        } else if (i3 < 0 || i3 >= 256) {
            throw new IllegalArgumentException("out of range dimensions");
        } else {
            addToCodeBuffer(197);
            addToCodeInt16(i2);
            addToCodeBuffer(i3);
        }
        this.itsStackTop = (short) stackChange;
        if (stackChange > this.itsMaxStack) {
            this.itsMaxStack = (short) stackChange;
        }
    }

    public void add(int i, String str) {
        short stackChange = this.itsStackTop + stackChange(i);
        if (stackChange < (short) 0 || Short.MAX_VALUE < stackChange) {
            badStack(stackChange);
        }
        switch (i) {
            case 187:
            case 189:
            case 192:
            case 193:
                short addClass = this.itsConstantPool.addClass(str);
                addToCodeBuffer(i);
                addToCodeInt16(addClass);
                this.itsStackTop = (short) stackChange;
                if (stackChange > this.itsMaxStack) {
                    this.itsMaxStack = (short) stackChange;
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("bad opcode for class reference");
        }
    }

    public void add(int i, String str, String str2, String str3) {
        short s;
        int stackChange = stackChange(i) + this.itsStackTop;
        char charAt = str3.charAt(0);
        int i2 = (charAt == 'J' || charAt == 'D') ? 2 : 1;
        switch (i) {
            case 178:
            case 180:
                s = i2 + stackChange;
                break;
            case 179:
            case 181:
                s = stackChange - i2;
                break;
            default:
                throw new IllegalArgumentException("bad opcode for field reference");
        }
        if (s < (short) 0 || Short.MAX_VALUE < s) {
            badStack(s);
        }
        short addFieldRef = this.itsConstantPool.addFieldRef(str, str2, str3);
        addToCodeBuffer(i);
        addToCodeInt16(addFieldRef);
        this.itsStackTop = (short) s;
        if (s > this.itsMaxStack) {
            this.itsMaxStack = (short) s;
        }
    }

    public void addInvoke(int i, String str, String str2, String str3) {
        int sizeOfParameters = sizeOfParameters(str3);
        int i2 = sizeOfParameters >>> 16;
        short stackChange = (((short) sizeOfParameters) + this.itsStackTop) + stackChange(i);
        if (stackChange < (short) 0 || Short.MAX_VALUE < stackChange) {
            badStack(stackChange);
        }
        switch (i) {
            case 182:
            case 183:
            case 184:
            case 185:
                addToCodeBuffer(i);
                if (i == 185) {
                    addToCodeInt16(this.itsConstantPool.addInterfaceMethodRef(str, str2, str3));
                    addToCodeBuffer(i2 + 1);
                    addToCodeBuffer(0);
                } else {
                    addToCodeInt16(this.itsConstantPool.addMethodRef(str, str2, str3));
                }
                this.itsStackTop = (short) stackChange;
                if (stackChange > this.itsMaxStack) {
                    this.itsMaxStack = (short) stackChange;
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("bad opcode for method reference");
        }
    }

    public void addPush(int i) {
        if (((byte) i) == i) {
            if (i == -1) {
                add(2);
            } else if (i < 0 || i > 5) {
                add(16, (byte) i);
            } else {
                add((byte) (i + 3));
            }
        } else if (((short) i) == i) {
            add(17, (short) i);
        } else {
            addLoadConstant(i);
        }
    }

    public void addPush(boolean z) {
        add(z ? 4 : 3);
    }

    public void addPush(long j) {
        int i = (int) j;
        if (((long) i) == j) {
            addPush(i);
            add(133);
            return;
        }
        addLoadConstant(j);
    }

    public void addPush(double d) {
        if (d == 0.0d) {
            add(14);
            if (1.0d / d < 0.0d) {
                add(119);
            }
        } else if (d == 1.0d || d == -1.0d) {
            add(15);
            if (d < 0.0d) {
                add(119);
            }
        } else {
            addLoadConstant(d);
        }
    }

    public void addPush(String str) {
        int i = 0;
        int length = str.length();
        int utfEncodingLimit = this.itsConstantPool.getUtfEncodingLimit(str, 0, length);
        if (utfEncodingLimit == length) {
            addLoadConstant(str);
            return;
        }
        String str2 = "java/lang/StringBuilder";
        add(187, "java/lang/StringBuilder");
        add(89);
        addPush(length);
        addInvoke(183, "java/lang/StringBuilder", "<init>", "(I)V");
        while (true) {
            add(89);
            addLoadConstant(str.substring(i, utfEncodingLimit));
            addInvoke(182, "java/lang/StringBuilder", RequestParameters.SUBRESOURCE_APPEND, "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            add(87);
            if (utfEncodingLimit == length) {
                addInvoke(182, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
                return;
            }
            int i2 = utfEncodingLimit;
            utfEncodingLimit = this.itsConstantPool.getUtfEncodingLimit(str, utfEncodingLimit, length);
            i = i2;
        }
    }

    public boolean isUnderStringSizeLimit(String str) {
        return this.itsConstantPool.isUnderUtfEncodingLimit(str);
    }

    public void addIStore(int i) {
        xop(59, 54, i);
    }

    public void addLStore(int i) {
        xop(63, 55, i);
    }

    public void addFStore(int i) {
        xop(67, 56, i);
    }

    public void addDStore(int i) {
        xop(71, 57, i);
    }

    public void addAStore(int i) {
        xop(75, 58, i);
    }

    public void addILoad(int i) {
        xop(26, 21, i);
    }

    public void addLLoad(int i) {
        xop(30, 22, i);
    }

    public void addFLoad(int i) {
        xop(34, 23, i);
    }

    public void addDLoad(int i) {
        xop(38, 24, i);
    }

    public void addALoad(int i) {
        xop(42, 25, i);
    }

    public void addLoadThis() {
        add(42);
    }

    private void xop(int i, int i2, int i3) {
        switch (i3) {
            case 0:
                add(i);
                return;
            case 1:
                add(i + 1);
                return;
            case 2:
                add(i + 2);
                return;
            case 3:
                add(i + 3);
                return;
            default:
                add(i2, i3);
                return;
        }
    }

    public int addTableSwitch(int i, int i2) {
        if (i > i2) {
            throw new ClassFileFormatException("Bad bounds: " + i + ' ' + i2);
        }
        short stackChange = this.itsStackTop + stackChange(170);
        if (stackChange < (short) 0 || Short.MAX_VALUE < stackChange) {
            badStack(stackChange);
        }
        int i3 = (this.itsCodeBufferTop ^ -1) & 3;
        int addReservedCodeSpace = addReservedCodeSpace(((((i2 - i) + 1) + 3) * 4) + (i3 + 1));
        int i4 = addReservedCodeSpace + 1;
        this.itsCodeBuffer[addReservedCodeSpace] = (byte) -86;
        int i5 = i3;
        while (i5 != 0) {
            i3 = i4 + 1;
            this.itsCodeBuffer[i4] = (byte) 0;
            i5--;
            i4 = i3;
        }
        putInt32(i2, this.itsCodeBuffer, putInt32(i, this.itsCodeBuffer, i4 + 4));
        this.itsStackTop = (short) stackChange;
        if (stackChange > this.itsMaxStack) {
            this.itsMaxStack = (short) stackChange;
        }
        return addReservedCodeSpace;
    }

    public final void markTableSwitchDefault(int i) {
        addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, i);
        setTableSwitchJump(i, -1, this.itsCodeBufferTop);
    }

    public final void markTableSwitchCase(int i, int i2) {
        addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, i);
        setTableSwitchJump(i, i2, this.itsCodeBufferTop);
    }

    public final void markTableSwitchCase(int i, int i2, int i3) {
        if (i3 < 0 || i3 > this.itsMaxStack) {
            throw new IllegalArgumentException("Bad stack index: " + i3);
        }
        this.itsStackTop = (short) i3;
        addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, i);
        setTableSwitchJump(i, i2, this.itsCodeBufferTop);
    }

    public void setTableSwitchJump(int i, int i2, int i3) {
        if (i3 < 0 || i3 > this.itsCodeBufferTop) {
            throw new IllegalArgumentException("Bad jump target: " + i3);
        } else if (i2 < -1) {
            throw new IllegalArgumentException("Bad case index: " + i2);
        } else {
            int i4;
            int i5 = (i ^ -1) & 3;
            if (i2 < 0) {
                i4 = (i + 1) + i5;
            } else {
                i4 = ((i + 1) + i5) + ((i2 + 3) * 4);
            }
            if (i < 0 || i > ((this.itsCodeBufferTop - 16) - i5) - 1) {
                throw new IllegalArgumentException(i + " is outside a possible range of tableswitch" + " in already generated code");
            } else if ((this.itsCodeBuffer[i] & 255) != 170) {
                throw new IllegalArgumentException(i + " is not offset of tableswitch statement");
            } else if (i4 < 0 || i4 + 4 > this.itsCodeBufferTop) {
                throw new ClassFileFormatException("Too big case index: " + i2);
            } else {
                putInt32(i3 - i, this.itsCodeBuffer, i4);
            }
        }
    }

    public int acquireLabel() {
        int i = this.itsLabelTableTop;
        if (this.itsLabelTable == null || i == this.itsLabelTable.length) {
            if (this.itsLabelTable == null) {
                this.itsLabelTable = new int[32];
            } else {
                Object obj = new int[(this.itsLabelTable.length * 2)];
                System.arraycopy(this.itsLabelTable, 0, obj, 0, i);
                this.itsLabelTable = obj;
            }
        }
        this.itsLabelTableTop = i + 1;
        this.itsLabelTable[i] = -1;
        return i | Integer.MIN_VALUE;
    }

    public void markLabel(int i) {
        if (i >= 0) {
            throw new IllegalArgumentException("Bad label, no biscuit");
        }
        int i2 = Integer.MAX_VALUE & i;
        if (i2 > this.itsLabelTableTop) {
            throw new IllegalArgumentException("Bad label");
        } else if (this.itsLabelTable[i2] != -1) {
            throw new IllegalStateException("Can only mark label once");
        } else {
            this.itsLabelTable[i2] = this.itsCodeBufferTop;
        }
    }

    public void markLabel(int i, short s) {
        markLabel(i);
        this.itsStackTop = s;
    }

    public void markHandler(int i) {
        this.itsStackTop = (short) 1;
        markLabel(i);
    }

    public int getLabelPC(int i) {
        if (i >= 0) {
            throw new IllegalArgumentException("Bad label, no biscuit");
        }
        int i2 = Integer.MAX_VALUE & i;
        if (i2 < this.itsLabelTableTop) {
            return this.itsLabelTable[i2];
        }
        throw new IllegalArgumentException("Bad label");
    }

    private void addLabelFixup(int i, int i2) {
        if (i >= 0) {
            throw new IllegalArgumentException("Bad label, no biscuit");
        }
        int i3 = Integer.MAX_VALUE & i;
        if (i3 >= this.itsLabelTableTop) {
            throw new IllegalArgumentException("Bad label");
        }
        int i4 = this.itsFixupTableTop;
        if (this.itsFixupTable == null || i4 == this.itsFixupTable.length) {
            if (this.itsFixupTable == null) {
                this.itsFixupTable = new long[40];
            } else {
                Object obj = new long[(this.itsFixupTable.length * 2)];
                System.arraycopy(this.itsFixupTable, 0, obj, 0, i4);
                this.itsFixupTable = obj;
            }
        }
        this.itsFixupTableTop = i4 + 1;
        this.itsFixupTable[i4] = (((long) i3) << 32) | ((long) i2);
    }

    private void fixLabelGotos() {
        byte[] bArr = this.itsCodeBuffer;
        for (int i = 0; i < this.itsFixupTableTop; i++) {
            long j = this.itsFixupTable[i];
            int i2 = (int) (j >> 32);
            int i3 = (int) j;
            i2 = this.itsLabelTable[i2];
            if (i2 == -1) {
                throw new RuntimeException();
            }
            addSuperBlockStart(i2);
            this.itsJumpFroms.put(i2, i3 - 1);
            short s = i2 - (i3 - 1);
            if (((short) s) != s) {
                throw new ClassFileFormatException("Program too complex: too big jump offset");
            }
            bArr[i3] = (byte) (s >> 8);
            bArr[i3 + 1] = (byte) s;
        }
        this.itsFixupTableTop = 0;
    }

    public int getCurrentCodeOffset() {
        return this.itsCodeBufferTop;
    }

    public short getStackTop() {
        return this.itsStackTop;
    }

    public void setStackTop(short s) {
        this.itsStackTop = s;
    }

    public void adjustStackTop(int i) {
        short s = this.itsStackTop + i;
        if (s < (short) 0 || Short.MAX_VALUE < s) {
            badStack(s);
        }
        this.itsStackTop = (short) s;
        if (s > this.itsMaxStack) {
            this.itsMaxStack = (short) s;
        }
    }

    private void addToCodeBuffer(int i) {
        this.itsCodeBuffer[addReservedCodeSpace(1)] = (byte) i;
    }

    private void addToCodeInt16(int i) {
        putInt16(i, this.itsCodeBuffer, addReservedCodeSpace(2));
    }

    private int addReservedCodeSpace(int i) {
        if (this.itsCurrentMethod == null) {
            throw new IllegalArgumentException("No method to add to");
        }
        int i2 = this.itsCodeBufferTop;
        int i3 = i2 + i;
        if (i3 > this.itsCodeBuffer.length) {
            int length = this.itsCodeBuffer.length * 2;
            if (i3 > length) {
                length = i3;
            }
            Object obj = new byte[length];
            System.arraycopy(this.itsCodeBuffer, 0, obj, 0, i2);
            this.itsCodeBuffer = obj;
        }
        this.itsCodeBufferTop = i3;
        return i2;
    }

    public void addExceptionHandler(int i, int i2, int i3, String str) {
        if ((i & Integer.MIN_VALUE) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad startLabel");
        } else if ((i2 & Integer.MIN_VALUE) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad endLabel");
        } else if ((i3 & Integer.MIN_VALUE) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad handlerLabel");
        } else {
            short s;
            if (str == null) {
                s = (short) 0;
            } else {
                s = this.itsConstantPool.addClass(str);
            }
            ExceptionTableEntry exceptionTableEntry = new ExceptionTableEntry(i, i2, i3, s);
            int i4 = this.itsExceptionTableTop;
            if (i4 == 0) {
                this.itsExceptionTable = new ExceptionTableEntry[4];
            } else if (i4 == this.itsExceptionTable.length) {
                Object obj = new ExceptionTableEntry[(i4 * 2)];
                System.arraycopy(this.itsExceptionTable, 0, obj, 0, i4);
                this.itsExceptionTable = obj;
            }
            this.itsExceptionTable[i4] = exceptionTableEntry;
            this.itsExceptionTableTop = i4 + 1;
        }
    }

    public void addLineNumberEntry(short s) {
        if (this.itsCurrentMethod == null) {
            throw new IllegalArgumentException("No method to stop");
        }
        int i = this.itsLineNumberTableTop;
        if (i == 0) {
            this.itsLineNumberTable = new int[16];
        } else if (i == this.itsLineNumberTable.length) {
            Object obj = new int[(i * 2)];
            System.arraycopy(this.itsLineNumberTable, 0, obj, 0, i);
            this.itsLineNumberTable = obj;
        }
        this.itsLineNumberTable[i] = (this.itsCodeBufferTop << 16) + s;
        this.itsLineNumberTableTop = i + 1;
    }

    private static char arrayTypeToName(int i) {
        switch (i) {
            case 4:
                return 'Z';
            case 5:
                return 'C';
            case 6:
                return 'F';
            case 7:
                return 'D';
            case 8:
                return 'B';
            case 9:
                return 'S';
            case 10:
                return 'I';
            case 11:
                return 'J';
            default:
                throw new IllegalArgumentException("bad operand");
        }
    }

    private static String classDescriptorToInternalName(String str) {
        return str.substring(1, str.length() - 1);
    }

    private static String descriptorToInternalName(String str) {
        switch (str.charAt(0)) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'V':
            case 'Z':
            case '[':
                return str;
            case 'L':
                return classDescriptorToInternalName(str);
            default:
                throw new IllegalArgumentException("bad descriptor:" + str);
        }
    }

    private int[] createInitialLocals() {
        int i = 1;
        int[] iArr = new int[this.itsMaxLocals];
        if ((this.itsCurrentMethod.getFlags() & 8) != 0) {
            i = 0;
        } else if ("<init>".equals(this.itsCurrentMethod.getName())) {
            iArr[0] = 6;
        } else {
            iArr[0] = TypeInfo.OBJECT(this.itsThisClassIndex);
        }
        String type = this.itsCurrentMethod.getType();
        int indexOf = type.indexOf(40);
        int indexOf2 = type.indexOf(41);
        if (indexOf != 0 || indexOf2 < 0) {
            throw new IllegalArgumentException("bad method type");
        }
        indexOf++;
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = i;
        while (indexOf < indexOf2) {
            switch (type.charAt(indexOf)) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'Z':
                    stringBuilder.append(type.charAt(indexOf));
                    i = indexOf + 1;
                    break;
                case 'L':
                    i = type.indexOf(59, indexOf) + 1;
                    stringBuilder.append(type.substring(indexOf, i));
                    break;
                case '[':
                    stringBuilder.append('[');
                    indexOf++;
                    continue;
                default:
                    i = indexOf;
                    break;
            }
            int fromType = TypeInfo.fromType(descriptorToInternalName(stringBuilder.toString()), this.itsConstantPool);
            indexOf = i2 + 1;
            iArr[i2] = fromType;
            if (TypeInfo.isTwoWords(fromType)) {
                indexOf++;
            }
            stringBuilder.setLength(0);
            i2 = indexOf;
            indexOf = i;
        }
        return iArr;
    }

    public void write(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    private int getWriteSize() {
        int i;
        int i2 = 0;
        if (this.itsSourceFileNameIndex != (short) 0) {
            this.itsConstantPool.addUtf8("SourceFile");
        }
        int writeSize = ((((((this.itsConstantPool.getWriteSize() + 8) + 2) + 2) + 2) + 2) + (this.itsInterfaces.size() * 2)) + 2;
        for (i = 0; i < this.itsFields.size(); i++) {
            writeSize += ((ClassFileField) this.itsFields.get(i)).getWriteSize();
        }
        i = writeSize + 2;
        while (i2 < this.itsMethods.size()) {
            i2++;
            i = ((ClassFileMethod) this.itsMethods.get(i2)).getWriteSize() + i;
        }
        if (this.itsSourceFileNameIndex != (short) 0) {
            return (((i + 2) + 2) + 4) + 2;
        }
        return i + 2;
    }

    public byte[] toByteArray() {
        int addUtf8;
        int i;
        int putInt16;
        int writeSize = getWriteSize();
        byte[] bArr = new byte[writeSize];
        if (this.itsSourceFileNameIndex != (short) 0) {
            addUtf8 = this.itsConstantPool.addUtf8("SourceFile");
        } else {
            addUtf8 = 0;
        }
        int putInt162 = putInt16(this.itsInterfaces.size(), bArr, putInt16(this.itsSuperClassIndex, bArr, putInt16(this.itsThisClassIndex, bArr, putInt16(this.itsFlags, bArr, this.itsConstantPool.write(bArr, putInt16(MajorVersion, bArr, putInt16(MinorVersion, bArr, putInt32(FileHeaderConstant, bArr, 0))))))));
        for (i = 0; i < this.itsInterfaces.size(); i++) {
            putInt162 = putInt16(((Short) this.itsInterfaces.get(i)).shortValue(), bArr, putInt162);
        }
        putInt162 = putInt16(this.itsFields.size(), bArr, putInt162);
        for (i = 0; i < this.itsFields.size(); i++) {
            putInt162 = ((ClassFileField) this.itsFields.get(i)).write(bArr, putInt162);
        }
        putInt162 = putInt16(this.itsMethods.size(), bArr, putInt162);
        for (i = 0; i < this.itsMethods.size(); i++) {
            putInt162 = ((ClassFileMethod) this.itsMethods.get(i)).write(bArr, putInt162);
        }
        if (this.itsSourceFileNameIndex != (short) 0) {
            putInt16 = putInt16(this.itsSourceFileNameIndex, bArr, putInt32(2, bArr, putInt16(addUtf8, bArr, putInt16(1, bArr, putInt162))));
        } else {
            putInt16 = putInt16(0, bArr, putInt162);
        }
        if (putInt16 == writeSize) {
            return bArr;
        }
        throw new RuntimeException();
    }

    static int putInt64(long j, byte[] bArr, int i) {
        return putInt32((int) j, bArr, putInt32((int) (j >>> 32), bArr, i));
    }

    private static void badStack(int i) {
        String str;
        if (i < 0) {
            str = "Stack underflow: " + i;
        } else {
            str = "Too big stack: " + i;
        }
        throw new IllegalStateException(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int sizeOfParameters(java.lang.String r9) {
        /*
        r5 = 1;
        r2 = 0;
        r0 = r9.length();
        r1 = 41;
        r6 = r9.lastIndexOf(r1);
        r1 = 3;
        if (r1 > r0) goto L_0x008c;
    L_0x000f:
        r1 = r9.charAt(r2);
        r3 = 40;
        if (r1 != r3) goto L_0x008c;
    L_0x0017:
        if (r5 > r6) goto L_0x008c;
    L_0x0019:
        r1 = r6 + 1;
        if (r1 >= r0) goto L_0x008c;
    L_0x001d:
        r3 = r2;
        r0 = r2;
        r1 = r5;
    L_0x0020:
        if (r1 == r6) goto L_0x00a6;
    L_0x0022:
        r4 = r9.charAt(r1);
        switch(r4) {
            case 66: goto L_0x0042;
            case 67: goto L_0x0042;
            case 68: goto L_0x0040;
            case 70: goto L_0x0042;
            case 73: goto L_0x0042;
            case 74: goto L_0x0040;
            case 76: goto L_0x006c;
            case 83: goto L_0x0042;
            case 90: goto L_0x0042;
            case 91: goto L_0x0049;
            default: goto L_0x0029;
        };
    L_0x0029:
        r1 = r2;
    L_0x002a:
        if (r1 == 0) goto L_0x008c;
    L_0x002c:
        r4 = r6 + 1;
        r4 = r9.charAt(r4);
        switch(r4) {
            case 66: goto L_0x0089;
            case 67: goto L_0x0089;
            case 68: goto L_0x0087;
            case 69: goto L_0x0035;
            case 70: goto L_0x0089;
            case 71: goto L_0x0035;
            case 72: goto L_0x0035;
            case 73: goto L_0x0089;
            case 74: goto L_0x0087;
            case 75: goto L_0x0035;
            case 76: goto L_0x0089;
            case 77: goto L_0x0035;
            case 78: goto L_0x0035;
            case 79: goto L_0x0035;
            case 80: goto L_0x0035;
            case 81: goto L_0x0035;
            case 82: goto L_0x0035;
            case 83: goto L_0x0089;
            case 84: goto L_0x0035;
            case 85: goto L_0x0035;
            case 86: goto L_0x0036;
            case 87: goto L_0x0035;
            case 88: goto L_0x0035;
            case 89: goto L_0x0035;
            case 90: goto L_0x0089;
            case 91: goto L_0x0089;
            default: goto L_0x0035;
        };
    L_0x0035:
        r1 = r2;
    L_0x0036:
        if (r1 == 0) goto L_0x008c;
    L_0x0038:
        r1 = r3 << 16;
        r2 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r0 = r0 & r2;
        r0 = r0 | r1;
        return r0;
    L_0x0040:
        r0 = r0 + -1;
    L_0x0042:
        r0 = r0 + -1;
        r3 = r3 + 1;
        r1 = r1 + 1;
        goto L_0x0020;
    L_0x0049:
        r4 = r1 + 1;
        r1 = r9.charAt(r4);
        r8 = r1;
        r1 = r4;
        r4 = r8;
    L_0x0052:
        r7 = 91;
        if (r4 != r7) goto L_0x0060;
    L_0x0056:
        r4 = r1 + 1;
        r1 = r9.charAt(r4);
        r8 = r1;
        r1 = r4;
        r4 = r8;
        goto L_0x0052;
    L_0x0060:
        switch(r4) {
            case 66: goto L_0x0065;
            case 67: goto L_0x0065;
            case 68: goto L_0x0065;
            case 70: goto L_0x0065;
            case 73: goto L_0x0065;
            case 74: goto L_0x0065;
            case 76: goto L_0x006c;
            case 83: goto L_0x0065;
            case 90: goto L_0x0065;
            default: goto L_0x0063;
        };
    L_0x0063:
        r1 = r2;
        goto L_0x002a;
    L_0x0065:
        r0 = r0 + -1;
        r3 = r3 + 1;
        r1 = r1 + 1;
        goto L_0x0020;
    L_0x006c:
        r4 = r0 + -1;
        r0 = r3 + 1;
        r1 = r1 + 1;
        r3 = 59;
        r3 = r9.indexOf(r3, r1);
        r1 = r1 + 1;
        if (r1 > r3) goto L_0x007e;
    L_0x007c:
        if (r3 < r6) goto L_0x0082;
    L_0x007e:
        r3 = r0;
        r1 = r2;
        r0 = r4;
        goto L_0x002a;
    L_0x0082:
        r1 = r3 + 1;
        r3 = r0;
        r0 = r4;
        goto L_0x0020;
    L_0x0087:
        r0 = r0 + 1;
    L_0x0089:
        r0 = r0 + 1;
        goto L_0x0036;
    L_0x008c:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Bad parameter signature: ";
        r1 = r1.append(r2);
        r1 = r1.append(r9);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00a6:
        r1 = r5;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.sizeOfParameters(java.lang.String):int");
    }

    static int putInt16(int i, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
        return i2 + 2;
    }

    static int putInt32(int i, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
        return i2 + 4;
    }

    static int opcodeLength(int i, boolean z) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
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
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
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
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 190:
            case 191:
            case 194:
            case 195:
            case 196:
            case 202:
            case 254:
            case 255:
                return 1;
            case 16:
            case 18:
            case 188:
                return 2;
            case 17:
            case 19:
            case 20:
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
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 187:
            case 189:
            case 192:
            case 193:
            case 198:
            case 199:
                return 3;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 169:
                if (z) {
                    return 3;
                }
                return 2;
            case 132:
                if (z) {
                    return 5;
                }
                return 3;
            case 185:
            case 200:
            case 201:
                return 5;
            case 197:
                return 4;
            default:
                throw new IllegalArgumentException("Bad opcode: " + i);
        }
    }

    static int opcodeCount(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
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
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
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
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 190:
            case 191:
            case 194:
            case 195:
            case 196:
            case 202:
            case 254:
            case 255:
                return 0;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
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
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 187:
            case 188:
            case 189:
            case 192:
            case 193:
            case 198:
            case 199:
            case 200:
            case 201:
                return 1;
            case 132:
            case 197:
                return 2;
            case 170:
            case 171:
                return -1;
            default:
                throw new IllegalArgumentException("Bad opcode: " + i);
        }
    }

    static int stackChange(int i) {
        switch (i) {
            case 0:
            case 47:
            case 49:
            case 95:
            case 116:
            case 117:
            case 118:
            case 119:
            case 132:
            case 134:
            case 138:
            case 139:
            case 143:
            case 145:
            case 146:
            case 147:
            case 167:
            case 169:
            case 177:
            case 178:
            case 179:
            case 184:
            case 188:
            case 189:
            case 190:
            case 192:
            case 193:
            case 196:
            case 200:
            case 202:
            case 254:
            case 255:
                return 0;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 34:
            case 35:
            case 36:
            case 37:
            case 42:
            case 43:
            case 44:
            case 45:
            case 89:
            case 90:
            case 91:
            case 133:
            case 135:
            case 140:
            case 141:
            case 168:
            case 187:
            case 197:
            case 201:
                return 1;
            case 9:
            case 10:
            case 14:
            case 15:
            case 20:
            case 22:
            case 24:
            case 30:
            case 31:
            case 32:
            case 33:
            case 38:
            case 39:
            case 40:
            case 41:
            case 92:
            case 93:
            case 94:
                return 2;
            case 46:
            case 48:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 56:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 67:
            case 68:
            case 69:
            case 70:
            case 75:
            case 76:
            case 77:
            case 78:
            case 87:
            case 96:
            case 98:
            case 100:
            case 102:
            case 104:
            case 106:
            case 108:
            case 110:
            case 112:
            case 114:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 128:
            case 130:
            case 136:
            case 137:
            case 142:
            case 144:
            case 149:
            case 150:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 170:
            case 171:
            case 172:
            case 174:
            case 176:
            case 180:
            case 181:
            case 182:
            case 183:
            case 185:
            case 191:
            case 194:
            case 195:
            case 198:
            case 199:
                return -1;
            case 55:
            case 57:
            case 63:
            case 64:
            case 65:
            case 66:
            case 71:
            case 72:
            case 73:
            case 74:
            case 88:
            case 97:
            case 99:
            case 101:
            case 103:
            case 105:
            case 107:
            case 109:
            case 111:
            case 113:
            case 115:
            case 127:
            case 129:
            case 131:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 173:
            case 175:
                return -2;
            case 79:
            case 81:
            case 83:
            case 84:
            case 85:
            case 86:
            case 148:
            case 151:
            case 152:
                return -3;
            case 80:
            case 82:
                return -4;
            default:
                throw new IllegalArgumentException("Bad opcode: " + i);
        }
    }

    private static String bytecodeStr(int i) {
        return "";
    }

    final char[] getCharBuffer(int i) {
        if (i > this.tmpCharBuffer.length) {
            int length = this.tmpCharBuffer.length * 2;
            if (i <= length) {
                i = length;
            }
            this.tmpCharBuffer = new char[i];
        }
        return this.tmpCharBuffer;
    }

    private void addSuperBlockStart(int i) {
        if (GenerateStackMap) {
            if (this.itsSuperBlockStarts == null) {
                this.itsSuperBlockStarts = new int[4];
            } else if (this.itsSuperBlockStarts.length == this.itsSuperBlockStartsTop) {
                Object obj = new int[(this.itsSuperBlockStartsTop * 2)];
                System.arraycopy(this.itsSuperBlockStarts, 0, obj, 0, this.itsSuperBlockStartsTop);
                this.itsSuperBlockStarts = obj;
            }
            int[] iArr = this.itsSuperBlockStarts;
            int i2 = this.itsSuperBlockStartsTop;
            this.itsSuperBlockStartsTop = i2 + 1;
            iArr[i2] = i;
        }
    }

    private void finalizeSuperBlockStarts() {
        int i = 1;
        if (GenerateStackMap) {
            int i2;
            for (i2 = 0; i2 < this.itsExceptionTableTop; i2++) {
                addSuperBlockStart((short) getLabelPC(this.itsExceptionTable[i2].itsHandlerLabel));
            }
            Arrays.sort(this.itsSuperBlockStarts, 0, this.itsSuperBlockStartsTop);
            int i3 = this.itsSuperBlockStarts[0];
            for (i2 = 1; i2 < this.itsSuperBlockStartsTop; i2++) {
                int i4 = this.itsSuperBlockStarts[i2];
                if (i3 != i4) {
                    if (i != i2) {
                        this.itsSuperBlockStarts[i] = i4;
                    }
                    i++;
                    i3 = i4;
                }
            }
            this.itsSuperBlockStartsTop = i;
            if (this.itsSuperBlockStarts[i - 1] == this.itsCodeBufferTop) {
                this.itsSuperBlockStartsTop--;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r7 = 8;
        r0 = 1;
        r1 = 0;
        r2 = 0;
        r4 = 48;
        r3 = org.mozilla.classfile.ClassFileWriter.class;
        r5 = "ClassFileWriter.class";
        r2 = r3.getResourceAsStream(r5);	 Catch:{ Exception -> 0x0085, all -> 0x0069 }
        if (r2 != 0) goto L_0x008c;
    L_0x0012:
        r3 = "org/mozilla/classfile/ClassFileWriter.class";
        r3 = java.lang.ClassLoader.getSystemResourceAsStream(r3);	 Catch:{ Exception -> 0x0085, all -> 0x007c }
    L_0x0019:
        r2 = 8;
        r5 = new byte[r2];	 Catch:{ Exception -> 0x002e, all -> 0x0080 }
        r2 = r1;
    L_0x001e:
        if (r2 >= r7) goto L_0x003f;
    L_0x0020:
        r6 = 8 - r2;
        r6 = r3.read(r5, r2, r6);	 Catch:{ Exception -> 0x002e, all -> 0x0080 }
        if (r6 >= 0) goto L_0x003d;
    L_0x0028:
        r0 = new java.io.IOException;	 Catch:{ Exception -> 0x002e, all -> 0x0080 }
        r0.<init>();	 Catch:{ Exception -> 0x002e, all -> 0x0080 }
        throw r0;	 Catch:{ Exception -> 0x002e, all -> 0x0080 }
    L_0x002e:
        r0 = move-exception;
        r0 = r1;
        r2 = r3;
    L_0x0031:
        MinorVersion = r0;
        MajorVersion = r4;
        GenerateStackMap = r1;
        if (r2 == 0) goto L_0x003c;
    L_0x0039:
        r2.close();	 Catch:{ IOException -> 0x0078 }
    L_0x003c:
        return;
    L_0x003d:
        r2 = r2 + r6;
        goto L_0x001e;
    L_0x003f:
        r2 = 4;
        r2 = r5[r2];	 Catch:{ Exception -> 0x002e, all -> 0x0080 }
        r2 = r2 << 8;
        r6 = 5;
        r6 = r5[r6];	 Catch:{ Exception -> 0x002e, all -> 0x0080 }
        r6 = r6 & 255;
        r2 = r2 | r6;
        r6 = 6;
        r6 = r5[r6];	 Catch:{ Exception -> 0x0088, all -> 0x0083 }
        r6 = r6 << 8;
        r7 = 7;
        r4 = r5[r7];	 Catch:{ Exception -> 0x0088, all -> 0x0083 }
        r4 = r4 & 255;
        r4 = r4 | r6;
        MinorVersion = r2;
        MajorVersion = r4;
        r2 = 50;
        if (r4 < r2) goto L_0x0067;
    L_0x005d:
        GenerateStackMap = r0;
        if (r3 == 0) goto L_0x003c;
    L_0x0061:
        r3.close();	 Catch:{ IOException -> 0x0065 }
        goto L_0x003c;
    L_0x0065:
        r0 = move-exception;
        goto L_0x003c;
    L_0x0067:
        r0 = r1;
        goto L_0x005d;
    L_0x0069:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
    L_0x006c:
        MinorVersion = r2;
        MajorVersion = r4;
        GenerateStackMap = r1;
        if (r3 == 0) goto L_0x0077;
    L_0x0074:
        r3.close();	 Catch:{ IOException -> 0x007a }
    L_0x0077:
        throw r0;
    L_0x0078:
        r0 = move-exception;
        goto L_0x003c;
    L_0x007a:
        r1 = move-exception;
        goto L_0x0077;
    L_0x007c:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
        goto L_0x006c;
    L_0x0080:
        r0 = move-exception;
        r2 = r1;
        goto L_0x006c;
    L_0x0083:
        r0 = move-exception;
        goto L_0x006c;
    L_0x0085:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0031;
    L_0x0088:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
        goto L_0x0031;
    L_0x008c:
        r3 = r2;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.classfile.ClassFileWriter.<clinit>():void");
    }
}
