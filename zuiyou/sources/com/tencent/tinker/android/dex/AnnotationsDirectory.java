package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents.Section.Item;
import com.tencent.tinker.android.dex.util.CompareUtils;

public class AnnotationsDirectory extends Item<AnnotationsDirectory> {
    public int classAnnotationsOffset;
    public int[][] fieldAnnotations;
    public int[][] methodAnnotations;
    public int[][] parameterAnnotations;

    public AnnotationsDirectory(int i, int i2, int[][] iArr, int[][] iArr2, int[][] iArr3) {
        super(i);
        this.classAnnotationsOffset = i2;
        this.fieldAnnotations = iArr;
        this.methodAnnotations = iArr2;
        this.parameterAnnotations = iArr3;
    }

    public int compareTo(AnnotationsDirectory annotationsDirectory) {
        if (this.classAnnotationsOffset != annotationsDirectory.classAnnotationsOffset) {
            return CompareUtils.uCompare(this.classAnnotationsOffset, annotationsDirectory.classAnnotationsOffset);
        }
        int length = this.fieldAnnotations.length;
        int length2 = this.methodAnnotations.length;
        int length3 = this.parameterAnnotations.length;
        int length4 = annotationsDirectory.fieldAnnotations.length;
        int length5 = annotationsDirectory.methodAnnotations.length;
        int length6 = annotationsDirectory.parameterAnnotations.length;
        if (length != length4) {
            return CompareUtils.sCompare(length, length4);
        }
        if (length2 != length5) {
            return CompareUtils.sCompare(length2, length5);
        }
        if (length3 != length6) {
            return CompareUtils.sCompare(length3, length6);
        }
        for (length4 = 0; length4 < length; length4++) {
            length5 = this.fieldAnnotations[length4][0];
            length6 = this.fieldAnnotations[length4][1];
            int i = annotationsDirectory.fieldAnnotations[length4][0];
            int i2 = annotationsDirectory.fieldAnnotations[length4][1];
            if (length5 != i) {
                return CompareUtils.uCompare(length5, i);
            }
            if (length6 != i2) {
                return CompareUtils.sCompare(length6, i2);
            }
        }
        for (length4 = 0; length4 < length2; length4++) {
            length = this.methodAnnotations[length4][0];
            length5 = this.methodAnnotations[length4][1];
            length6 = annotationsDirectory.methodAnnotations[length4][0];
            i = annotationsDirectory.methodAnnotations[length4][1];
            if (length != length6) {
                return CompareUtils.uCompare(length, length6);
            }
            if (length5 != i) {
                return CompareUtils.sCompare(length5, i);
            }
        }
        for (length4 = 0; length4 < length3; length4++) {
            length = this.parameterAnnotations[length4][0];
            length2 = this.parameterAnnotations[length4][1];
            length5 = annotationsDirectory.parameterAnnotations[length4][0];
            length6 = annotationsDirectory.parameterAnnotations[length4][1];
            if (length != length5) {
                return CompareUtils.uCompare(length, length5);
            }
            if (length2 != length6) {
                return CompareUtils.sCompare(length2, length6);
            }
        }
        return 0;
    }

    public int byteCountInDex() {
        return ((((this.fieldAnnotations.length + this.methodAnnotations.length) + this.parameterAnnotations.length) * 2) + 4) * 4;
    }
}
