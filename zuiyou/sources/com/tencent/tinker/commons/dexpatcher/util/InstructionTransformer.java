package com.tencent.tinker.commons.dexpatcher.util;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.instruction.InstructionPromoter;
import com.tencent.tinker.android.dx.instruction.InstructionReader;
import com.tencent.tinker.android.dx.instruction.InstructionVisitor;
import com.tencent.tinker.android.dx.instruction.InstructionWriter;
import com.tencent.tinker.android.dx.instruction.ShortArrayCodeInput;
import com.tencent.tinker.android.dx.instruction.ShortArrayCodeOutput;

public final class InstructionTransformer {
    private final AbstractIndexMap indexMap;

    private final class a extends InstructionVisitor {
        final /* synthetic */ InstructionTransformer a;

        a(InstructionTransformer instructionTransformer, InstructionVisitor instructionVisitor) {
            this.a = instructionTransformer;
            super(instructionVisitor);
        }

        public void visitZeroRegisterInsn(int i, int i2, int i3, int i4, int i5, long j) {
            super.visitZeroRegisterInsn(i, i2, a(i3, i4), i4, i5, j);
        }

        public void visitOneRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6) {
            super.visitOneRegisterInsn(i, i2, a(i3, i4), i4, i5, j, i6);
        }

        public void visitTwoRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
            super.visitTwoRegisterInsn(i, i2, a(i3, i4), i4, i5, j, i6, i7);
        }

        public void visitThreeRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8) {
            super.visitThreeRegisterInsn(i, i2, a(i3, i4), i4, i5, j, i6, i7, i8);
        }

        public void visitFourRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
            super.visitFourRegisterInsn(i, i2, a(i3, i4), i4, i5, j, i6, i7, i8, i9);
        }

        public void visitFiveRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9, int i10) {
            super.visitFiveRegisterInsn(i, i2, a(i3, i4), i4, i5, j, i6, i7, i8, i9, i10);
        }

        public void visitRegisterRangeInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
            super.visitRegisterRangeInsn(i, i2, a(i3, i4), i4, i5, j, i6, i7);
        }

        private int a(int i, int i2) {
            switch (i2) {
                case 2:
                    return this.a.indexMap.adjustTypeIdIndex(i);
                case 3:
                    return this.a.indexMap.adjustStringIndex(i);
                case 4:
                    return this.a.indexMap.adjustMethodIdIndex(i);
                case 5:
                    return this.a.indexMap.adjustFieldIdIndex(i);
                default:
                    return i;
            }
        }
    }

    public InstructionTransformer(AbstractIndexMap abstractIndexMap) {
        this.indexMap = abstractIndexMap;
    }

    public short[] transform(short[] sArr) throws DexException {
        ShortArrayCodeOutput shortArrayCodeOutput = new ShortArrayCodeOutput(sArr.length);
        InstructionVisitor instructionPromoter = new InstructionPromoter();
        InstructionVisitor instructionWriter = new InstructionWriter(shortArrayCodeOutput, instructionPromoter);
        InstructionReader instructionReader = new InstructionReader(new ShortArrayCodeInput(sArr));
        try {
            instructionReader.accept(new a(this, instructionPromoter));
            instructionReader.accept(new a(this, instructionWriter));
            return shortArrayCodeOutput.getArray();
        } catch (Throwable e) {
            throw new DexException(e);
        }
    }
}
