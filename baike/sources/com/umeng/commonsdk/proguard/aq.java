package com.umeng.commonsdk.proguard;

import java.util.BitSet;

public final class aq extends ae {

    public static class a implements am {
        public ak a(ay ayVar) {
            return new aq(ayVar);
        }
    }

    public aq(ay ayVar) {
        super(ayVar);
    }

    public Class<? extends as> D() {
        return av.class;
    }

    public void a(BitSet bitSet, int i) throws r {
        for (byte a : b(bitSet, i)) {
            a(a);
        }
    }

    public BitSet b(int i) throws r {
        int ceil = (int) Math.ceil(((double) i) / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = u();
        }
        return a(bArr);
    }

    public static BitSet a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    public static byte[] b(BitSet bitSet, int i) {
        byte[] bArr = new byte[((int) Math.ceil(((double) i) / 8.0d))];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) (bArr[length] | (1 << (i2 % 8)));
            }
        }
        return bArr;
    }
}
