package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents.Section.Item;
import com.tencent.tinker.android.dex.util.CompareUtils;

public final class FieldId extends Item<FieldId> {
    public int declaringClassIndex;
    public int nameIndex;
    public int typeIndex;

    public FieldId(int i, int i2, int i3, int i4) {
        super(i);
        this.declaringClassIndex = i2;
        this.typeIndex = i3;
        this.nameIndex = i4;
    }

    public int compareTo(FieldId fieldId) {
        if (this.declaringClassIndex != fieldId.declaringClassIndex) {
            return CompareUtils.uCompare(this.declaringClassIndex, fieldId.declaringClassIndex);
        }
        if (this.nameIndex != fieldId.nameIndex) {
            return CompareUtils.uCompare(this.nameIndex, fieldId.nameIndex);
        }
        return CompareUtils.uCompare(this.typeIndex, fieldId.typeIndex);
    }

    public int byteCountInDex() {
        return 8;
    }
}
