package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents.Section;
import com.tencent.tinker.android.dex.TableOfContents.Section.Item;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;
import java.util.Arrays;

public abstract class DexSectionPatchAlgorithm<T extends Comparable<T>> {
    protected final Dex oldDex;
    private final SparseIndexMap oldToPatchedIndexMap;
    protected final DexPatchFile patchFile;

    protected abstract int getItemSize(T t);

    protected abstract Section getTocSection(Dex dex);

    protected abstract T nextItem(DexDataBuffer dexDataBuffer);

    protected abstract int writePatchedItem(T t);

    public DexSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, SparseIndexMap sparseIndexMap) {
        this.patchFile = dexPatchFile;
        this.oldDex = dex;
        this.oldToPatchedIndexMap = sparseIndexMap;
    }

    protected T adjustItem(AbstractIndexMap abstractIndexMap, T t) {
        return t;
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
    }

    private int[] readDeltaIndiciesOrOffsets(int i) {
        int i2 = 0;
        int[] iArr = new int[i];
        int i3 = 0;
        while (i2 < i) {
            i3 += this.patchFile.getBuffer().readSleb128();
            iArr[i2] = i3;
            i2++;
        }
        return iArr;
    }

    private int getItemOffsetOrIndex(int i, T t) {
        if (t instanceof Item) {
            return ((Item) t).off;
        }
        return i;
    }

    public void execute() {
        int[] readDeltaIndiciesOrOffsets = readDeltaIndiciesOrOffsets(this.patchFile.getBuffer().readUleb128());
        int[] readDeltaIndiciesOrOffsets2 = readDeltaIndiciesOrOffsets(this.patchFile.getBuffer().readUleb128());
        int[] readDeltaIndiciesOrOffsets3 = readDeltaIndiciesOrOffsets(this.patchFile.getBuffer().readUleb128());
        Section tocSection = getTocSection(this.oldDex);
        Dex.Section section = null;
        int i = 0;
        if (tocSection.exists()) {
            section = this.oldDex.openSection(tocSection);
            i = tocSection.size;
        }
        doFullPatch(section, i, readDeltaIndiciesOrOffsets, readDeltaIndiciesOrOffsets2, readDeltaIndiciesOrOffsets3);
    }

    private void doFullPatch(Dex.Section section, int i, int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        int length2 = iArr2.length;
        int length3 = iArr3.length;
        int i2 = (i + length2) - length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i3 >= i && i4 >= i2) {
                break;
            } else if (i6 < length2 && iArr2[i6] == i4) {
                writePatchedItem(nextItem(this.patchFile.getBuffer()));
                i4++;
                i6++;
            } else if (i5 < length3 && iArr3[i5] == i4) {
                writePatchedItem(nextItem(this.patchFile.getBuffer()));
                i4++;
                i5++;
            } else if (Arrays.binarySearch(iArr, i3) >= 0) {
                markDeletedIndexOrOffset(this.oldToPatchedIndexMap, i3, getItemOffsetOrIndex(i3, nextItem(section)));
                i3++;
                i7++;
            } else if (Arrays.binarySearch(iArr3, i3) >= 0) {
                markDeletedIndexOrOffset(this.oldToPatchedIndexMap, i3, getItemOffsetOrIndex(i3, nextItem(section)));
                i3++;
            } else if (i3 < i) {
                Comparable adjustItem = adjustItem(this.oldToPatchedIndexMap, nextItem(section));
                int writePatchedItem = writePatchedItem(adjustItem);
                updateIndexOrOffset(this.oldToPatchedIndexMap, i3, getItemOffsetOrIndex(i3, adjustItem), i4, writePatchedItem);
                i3++;
                i4++;
            }
        }
        if (i6 != length2 || i7 != length || i5 != length3) {
            throw new IllegalStateException(String.format("bad patch operation sequence. addCounter: %d, addCount: %d, delCounter: %d, delCount: %d, replaceCounter: %d, replaceCount:%d", new Object[]{Integer.valueOf(i6), Integer.valueOf(length2), Integer.valueOf(i7), Integer.valueOf(length), Integer.valueOf(i5), Integer.valueOf(length3)}));
        }
    }
}
