package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents.Section.Item;
import com.tencent.tinker.android.dex.util.CompareUtils;

public class AnnotationSet extends Item<AnnotationSet> {
    public int[] annotationOffsets;

    public AnnotationSet(int i, int[] iArr) {
        super(i);
        this.annotationOffsets = iArr;
    }

    public int compareTo(AnnotationSet annotationSet) {
        int length = this.annotationOffsets.length;
        int length2 = annotationSet.annotationOffsets.length;
        if (length != length2) {
            return CompareUtils.uCompare(length, length2);
        }
        for (length2 = 0; length2 < length; length2++) {
            if (this.annotationOffsets[length2] != annotationSet.annotationOffsets[length2]) {
                return CompareUtils.uCompare(this.annotationOffsets[length2], annotationSet.annotationOffsets[length2]);
            }
        }
        return 0;
    }

    public int byteCountInDex() {
        return (this.annotationOffsets.length + 1) * 4;
    }
}
