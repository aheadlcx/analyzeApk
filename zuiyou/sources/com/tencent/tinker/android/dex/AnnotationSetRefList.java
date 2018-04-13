package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents.Section.Item;
import com.tencent.tinker.android.dex.util.CompareUtils;

public class AnnotationSetRefList extends Item<AnnotationSetRefList> {
    public int[] annotationSetRefItems;

    public AnnotationSetRefList(int i, int[] iArr) {
        super(i);
        this.annotationSetRefItems = iArr;
    }

    public int compareTo(AnnotationSetRefList annotationSetRefList) {
        int length = this.annotationSetRefItems.length;
        int length2 = annotationSetRefList.annotationSetRefItems.length;
        if (length != length2) {
            return CompareUtils.uCompare(length, length2);
        }
        for (length2 = 0; length2 < length; length2++) {
            if (this.annotationSetRefItems[length2] != annotationSetRefList.annotationSetRefItems[length2]) {
                return CompareUtils.uCompare(this.annotationSetRefItems[length2], annotationSetRefList.annotationSetRefItems[length2]);
            }
        }
        return 0;
    }

    public int byteCountInDex() {
        return (this.annotationSetRefItems.length + 1) * 4;
    }
}
