package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dx.util.Hex;
import java.util.HashSet;
import java.util.Set;

public abstract class InstructionComparator {
    private final b[] insnHolders1;
    private final b[] insnHolders2;
    private final short[] insns1;
    private final short[] insns2;
    private final Set<String> visitedInsnAddrPairs;

    private static class b {
        int d;
        int e;
        int f;
        int g;
        int h;
        long i;
        int j;
        int k;
        int l;
        int m;
        int n;
        int o;

        private b() {
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
        }
    }

    private static class a extends b {
        Object a;
        int b;
        int c;

        private a() {
            super();
            this.a = null;
            this.b = 0;
            this.c = 0;
        }
    }

    private static class c extends b {
        int a;
        int[] b;

        private c() {
            super();
            this.a = 0;
            this.b = null;
        }
    }

    private static class d extends b {
        int[] a;
        int[] b;

        private d() {
            super();
            this.a = null;
            this.b = null;
        }
    }

    protected abstract boolean compareField(int i, int i2);

    protected abstract boolean compareMethod(int i, int i2);

    protected abstract boolean compareString(int i, int i2);

    protected abstract boolean compareType(int i, int i2);

    public InstructionComparator(short[] sArr, short[] sArr2) {
        this.insns1 = sArr;
        this.insns2 = sArr2;
        if (sArr != null) {
            this.insnHolders1 = readInstructionsIntoHolders(new ShortArrayCodeInput(sArr), sArr.length);
        } else {
            this.insnHolders1 = null;
        }
        if (sArr2 != null) {
            this.insnHolders2 = readInstructionsIntoHolders(new ShortArrayCodeInput(sArr2), sArr2.length);
        } else {
            this.insnHolders2 = null;
        }
        this.visitedInsnAddrPairs = new HashSet();
    }

    private b[] readInstructionsIntoHolders(ShortArrayCodeInput shortArrayCodeInput, int i) {
        shortArrayCodeInput.reset();
        final b[] bVarArr = new b[i];
        try {
            new InstructionReader(shortArrayCodeInput).accept(new InstructionVisitor(this, null) {
                final /* synthetic */ InstructionComparator b;

                public void visitZeroRegisterInsn(int i, int i2, int i3, int i4, int i5, long j) {
                    b bVar = new b();
                    bVar.d = InstructionCodec.getInstructionFormat(i2);
                    bVar.e = i;
                    bVar.f = i2;
                    bVar.g = i3;
                    bVar.h = i5;
                    bVar.i = j;
                    bVarArr[i] = bVar;
                }

                public void visitOneRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6) {
                    b bVar = new b();
                    bVar.d = InstructionCodec.getInstructionFormat(i2);
                    bVar.e = i;
                    bVar.f = i2;
                    bVar.g = i3;
                    bVar.h = i5;
                    bVar.i = j;
                    bVar.j = 1;
                    bVar.k = i6;
                    bVarArr[i] = bVar;
                }

                public void visitTwoRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
                    b bVar = new b();
                    bVar.d = InstructionCodec.getInstructionFormat(i2);
                    bVar.e = i;
                    bVar.f = i2;
                    bVar.g = i3;
                    bVar.h = i5;
                    bVar.i = j;
                    bVar.j = 2;
                    bVar.k = i6;
                    bVar.l = i7;
                    bVarArr[i] = bVar;
                }

                public void visitThreeRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8) {
                    b bVar = new b();
                    bVar.d = InstructionCodec.getInstructionFormat(i2);
                    bVar.e = i;
                    bVar.f = i2;
                    bVar.g = i3;
                    bVar.h = i5;
                    bVar.i = j;
                    bVar.j = 3;
                    bVar.k = i6;
                    bVar.l = i7;
                    bVar.m = i8;
                    bVarArr[i] = bVar;
                }

                public void visitFourRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
                    b bVar = new b();
                    bVar.d = InstructionCodec.getInstructionFormat(i2);
                    bVar.e = i;
                    bVar.f = i2;
                    bVar.g = i3;
                    bVar.h = i5;
                    bVar.i = j;
                    bVar.j = 4;
                    bVar.k = i6;
                    bVar.l = i7;
                    bVar.m = i8;
                    bVar.n = i9;
                    bVarArr[i] = bVar;
                }

                public void visitFiveRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9, int i10) {
                    b bVar = new b();
                    bVar.d = InstructionCodec.getInstructionFormat(i2);
                    bVar.e = i;
                    bVar.f = i2;
                    bVar.g = i3;
                    bVar.h = i5;
                    bVar.i = j;
                    bVar.j = 5;
                    bVar.k = i6;
                    bVar.l = i7;
                    bVar.m = i8;
                    bVar.n = i9;
                    bVar.o = i10;
                    bVarArr[i] = bVar;
                }

                public void visitRegisterRangeInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
                    b bVar = new b();
                    bVar.d = InstructionCodec.getInstructionFormat(i2);
                    bVar.e = i;
                    bVar.f = i2;
                    bVar.g = i3;
                    bVar.h = i5;
                    bVar.i = j;
                    bVar.j = i7;
                    bVar.k = i6;
                    bVarArr[i] = bVar;
                }

                public void visitSparseSwitchPayloadInsn(int i, int i2, int[] iArr, int[] iArr2) {
                    d dVar = new d();
                    dVar.d = InstructionCodec.getInstructionFormat(i2);
                    dVar.e = i;
                    dVar.f = i2;
                    dVar.a = iArr;
                    dVar.b = iArr2;
                    bVarArr[i] = dVar;
                }

                public void visitPackedSwitchPayloadInsn(int i, int i2, int i3, int[] iArr) {
                    c cVar = new c();
                    cVar.d = InstructionCodec.getInstructionFormat(i2);
                    cVar.e = i;
                    cVar.f = i2;
                    cVar.a = i3;
                    cVar.b = iArr;
                    bVarArr[i] = cVar;
                }

                public void visitFillArrayDataPayloadInsn(int i, int i2, Object obj, int i3, int i4) {
                    a aVar = new a();
                    aVar.d = InstructionCodec.getInstructionFormat(i2);
                    aVar.e = i;
                    aVar.f = i2;
                    aVar.a = obj;
                    aVar.b = i3;
                    aVar.c = i4;
                    bVarArr[i] = aVar;
                }
            });
            return bVarArr;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean compare() {
        this.visitedInsnAddrPairs.clear();
        if (this.insnHolders1 == null && this.insnHolders2 == null) {
            return true;
        }
        if (this.insnHolders1 == null || this.insnHolders2 == null) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < this.insnHolders1.length && r3 < this.insnHolders2.length) {
            b bVar = null;
            while (i4 < this.insnHolders1.length && bVar == null) {
                int i5 = i4 + 1;
                b bVar2 = this.insnHolders1[i4];
                i4 = i5;
                bVar = bVar2;
            }
            if (bVar == null) {
                break;
            }
            i2++;
            b bVar3 = null;
            while (i3 < this.insnHolders2.length && bVar3 == null) {
                int i6 = i3 + 1;
                bVar2 = this.insnHolders2[i3];
                i3 = i6;
                bVar3 = bVar2;
            }
            if (bVar3 == null) {
                break;
            }
            i++;
            if (bVar.f != bVar3.f) {
                if (bVar.f == 26 && bVar3.f == 27) {
                    if (!compareString(bVar.g, bVar3.g)) {
                        return false;
                    }
                } else if (bVar.f != 27 || bVar3.f != 26) {
                    return false;
                } else {
                    if (!compareString(bVar.g, bVar3.g)) {
                        return false;
                    }
                }
            } else if (!isSameInstruction(bVar.e, bVar3.e)) {
                return false;
            }
        }
        while (i4 < this.insnHolders1.length) {
            i5 = i4 + 1;
            if (this.insnHolders1[i4] != null) {
                return false;
            }
            i4 = i5;
        }
        while (i3 < this.insnHolders2.length) {
            i4 = i3 + 1;
            if (this.insnHolders2[i3] != null) {
                return false;
            }
            i3 = i4;
        }
        if (i2 == i) {
            return true;
        }
        return false;
    }

    public boolean isSameInstruction(int i, int i2) {
        boolean z = true;
        b bVar = this.insnHolders1[i];
        b bVar2 = this.insnHolders2[i2];
        if (bVar == null && bVar2 == null) {
            return true;
        }
        if (bVar == null || bVar2 == null || bVar.f != bVar2.f) {
            return false;
        }
        int i3 = bVar.f;
        int length;
        switch (bVar.d) {
            case 2:
            case 7:
            case 11:
            case 15:
            case 18:
            case 21:
                return this.visitedInsnAddrPairs.add(new StringBuilder().append(i).append("-").append(i2).toString()) ? isSameInstruction(bVar.h, bVar2.h) : true;
            case 8:
            case 13:
            case 19:
            case 23:
            case 24:
                return compareIndex(i3, bVar.g, bVar2.g);
            case 26:
                a aVar = (a) bVar;
                a aVar2 = (a) bVar2;
                if (aVar.c != aVar2.c || aVar.b != aVar2.b) {
                    return false;
                }
                i3 = aVar.c;
                switch (i3) {
                    case 1:
                        return CompareUtils.uArrCompare((byte[]) ((byte[]) aVar.a), (byte[]) ((byte[]) aVar2.a)) == 0;
                    case 2:
                        if (CompareUtils.uArrCompare((short[]) aVar.a, (short[]) aVar2.a) != 0) {
                            z = false;
                        }
                        return z;
                    case 4:
                        if (CompareUtils.uArrCompare((int[]) aVar.a, (int[]) aVar2.a) != 0) {
                            z = false;
                        }
                        return z;
                    case 8:
                        if (CompareUtils.sArrCompare((long[]) aVar.a, (long[]) aVar2.a) != 0) {
                            z = false;
                        }
                        return z;
                    default:
                        throw new DexException("bogus element_width: " + Hex.u2(i3));
                }
            case 27:
                c cVar = (c) bVar;
                c cVar2 = (c) bVar2;
                if (cVar.a != cVar2.a || cVar.b.length != cVar2.b.length) {
                    return false;
                }
                length = cVar.b.length;
                for (i3 = 0; i3 < length; i3++) {
                    if (!isSameInstruction(cVar.b[i3], cVar2.b[i3])) {
                        return false;
                    }
                }
                return true;
            case 28:
                d dVar = (d) bVar;
                d dVar2 = (d) bVar2;
                if (CompareUtils.uArrCompare(dVar.a, dVar2.a) != 0 || dVar.b.length != dVar2.b.length) {
                    return false;
                }
                length = dVar.b.length;
                for (i3 = 0; i3 < length; i3++) {
                    if (!isSameInstruction(dVar.b[i3], dVar2.b[i3])) {
                        return false;
                    }
                }
                return true;
            default:
                if (bVar.i == bVar2.i && bVar.j == bVar2.j && bVar.k == bVar2.k && bVar.l == bVar2.l && bVar.m == bVar2.m && bVar.n == bVar2.n && bVar.o == bVar2.o) {
                    return true;
                }
                return false;
        }
    }

    private boolean compareIndex(int i, int i2, int i3) {
        switch (InstructionCodec.getInstructionIndexType(i)) {
            case 2:
                return compareType(i2, i3);
            case 3:
                return compareString(i2, i3);
            case 4:
                return compareMethod(i2, i3);
            case 5:
                return compareField(i2, i3);
            default:
                if (i2 == i3) {
                    return true;
                }
                return false;
        }
    }
}
