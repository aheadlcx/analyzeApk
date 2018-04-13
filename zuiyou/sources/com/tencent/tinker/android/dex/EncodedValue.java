package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents.Section.Item;
import com.tencent.tinker.android.dex.util.ByteInput;
import com.tencent.tinker.android.dex.util.CompareUtils;

public final class EncodedValue extends Item<EncodedValue> {
    public byte[] data;

    public EncodedValue(int i, byte[] bArr) {
        super(i);
        this.data = bArr;
    }

    public ByteInput asByteInput() {
        return new ByteInput(this) {
            final /* synthetic */ EncodedValue a;
            private int b = 0;

            {
                this.a = r2;
            }

            public byte readByte() {
                byte[] bArr = this.a.data;
                int i = this.b;
                this.b = i + 1;
                return bArr[i];
            }
        };
    }

    public int compareTo(EncodedValue encodedValue) {
        return CompareUtils.uArrCompare(this.data, encodedValue.data);
    }

    public int byteCountInDex() {
        return this.data.length * 1;
    }
}
