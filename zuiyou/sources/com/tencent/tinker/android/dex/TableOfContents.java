package com.tencent.tinker.android.dex;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.InputDeviceCompat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public final class TableOfContents {
    public static final short SECTION_TYPE_ANNOTATIONS = (short) 8196;
    public static final short SECTION_TYPE_ANNOTATIONSDIRECTORIES = (short) 8198;
    public static final short SECTION_TYPE_ANNOTATIONSETREFLISTS = (short) 4098;
    public static final short SECTION_TYPE_ANNOTATIONSETS = (short) 4099;
    public static final short SECTION_TYPE_CLASSDATA = (short) 8192;
    public static final short SECTION_TYPE_CLASSDEFS = (short) 6;
    public static final short SECTION_TYPE_CODES = (short) 8193;
    public static final short SECTION_TYPE_DEBUGINFOS = (short) 8195;
    public static final short SECTION_TYPE_ENCODEDARRAYS = (short) 8197;
    public static final short SECTION_TYPE_FIELDIDS = (short) 4;
    public static final short SECTION_TYPE_HEADER = (short) 0;
    public static final short SECTION_TYPE_MAPLIST = (short) 4096;
    public static final short SECTION_TYPE_METHODIDS = (short) 5;
    public static final short SECTION_TYPE_PROTOIDS = (short) 3;
    public static final short SECTION_TYPE_STRINGDATAS = (short) 8194;
    public static final short SECTION_TYPE_STRINGIDS = (short) 1;
    public static final short SECTION_TYPE_TYPEIDS = (short) 2;
    public static final short SECTION_TYPE_TYPELISTS = (short) 4097;
    public final Section annotationSetRefLists = new Section(InputDeviceCompat.SOURCE_TOUCHSCREEN, true);
    public final Section annotationSets = new Section(FragmentTransaction.TRANSIT_FRAGMENT_FADE, true);
    public final Section annotations = new Section(8196, false);
    public final Section annotationsDirectories = new Section(8198, true);
    public int checksum;
    public final Section classDatas = new Section(8192, false);
    public final Section classDefs = new Section(6, true);
    public final Section codes = new Section(8193, true);
    public int dataOff;
    public int dataSize;
    public final Section debugInfos = new Section(8195, false);
    public final Section encodedArrays = new Section(8197, false);
    public final Section fieldIds = new Section(4, true);
    public int fileSize;
    public final Section header = new Section(0, true);
    public int linkOff;
    public int linkSize;
    public final Section mapList = new Section(4096, true);
    public final Section methodIds = new Section(5, true);
    public final Section protoIds = new Section(3, true);
    public final Section[] sections = new Section[]{this.header, this.stringIds, this.typeIds, this.protoIds, this.fieldIds, this.methodIds, this.classDefs, this.mapList, this.typeLists, this.annotationSetRefLists, this.annotationSets, this.classDatas, this.codes, this.stringDatas, this.debugInfos, this.annotations, this.encodedArrays, this.annotationsDirectories};
    public byte[] signature = new byte[20];
    public final Section stringDatas = new Section(8194, false);
    public final Section stringIds = new Section(1, true);
    public final Section typeIds = new Section(2, true);
    public final Section typeLists = new Section(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, true);

    public static class Section implements Comparable<Section> {
        public static final int UNDEF_INDEX = -1;
        public static final int UNDEF_OFFSET = -1;
        public int byteCount = 0;
        public boolean isElementFourByteAligned;
        public int off = -1;
        public int size = 0;
        public final short type;

        public static abstract class Item<T> implements Comparable<T> {
            public int off;

            public abstract int byteCountInDex();

            public Item(int i) {
                this.off = i;
            }

            public boolean equals(Object obj) {
                return compareTo(obj) == 0;
            }
        }

        public Section(int i, boolean z) {
            this.type = (short) i;
            this.isElementFourByteAligned = z;
            if (i == 0) {
                this.off = 0;
                this.size = 1;
                this.byteCount = 112;
            } else if (i == 4096) {
                this.size = 1;
            }
        }

        public boolean exists() {
            return this.size > 0;
        }

        private int remapTypeOrderId(int i) {
            switch (i) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                    return 6;
                case 4096:
                    return 17;
                case FragmentTransaction.TRANSIT_FRAGMENT_OPEN /*4097*/:
                    return 8;
                case InputDeviceCompat.SOURCE_TOUCHSCREEN /*4098*/:
                    return 11;
                case FragmentTransaction.TRANSIT_FRAGMENT_FADE /*4099*/:
                    return 10;
                case 8192:
                    return 15;
                case 8193:
                    return 14;
                case 8194:
                    return 7;
                case 8195:
                    return 13;
                case 8196:
                    return 9;
                case 8197:
                    return 16;
                case 8198:
                    return 12;
                default:
                    throw new IllegalArgumentException("unknown section type: " + i);
            }
        }

        public int compareTo(Section section) {
            if (this.off == section.off) {
                int remapTypeOrderId = remapTypeOrderId(this.type);
                int remapTypeOrderId2 = remapTypeOrderId(section.type);
                if (remapTypeOrderId == remapTypeOrderId2) {
                    return 0;
                }
                if (remapTypeOrderId >= remapTypeOrderId2) {
                    return 1;
                }
                return -1;
            } else if (this.off < section.off) {
                return -1;
            } else {
                return 1;
            }
        }

        public String toString() {
            return String.format("Section[type=%#x,off=%#x,size=%#x]", new Object[]{Short.valueOf(this.type), Integer.valueOf(this.off), Integer.valueOf(this.size)});
        }
    }

    public Section getSectionByType(int i) {
        switch (i) {
            case 0:
                return this.header;
            case 1:
                return this.stringIds;
            case 2:
                return this.typeIds;
            case 3:
                return this.protoIds;
            case 4:
                return this.fieldIds;
            case 5:
                return this.methodIds;
            case 6:
                return this.classDefs;
            case 4096:
                return this.mapList;
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN /*4097*/:
                return this.typeLists;
            case InputDeviceCompat.SOURCE_TOUCHSCREEN /*4098*/:
                return this.annotationSetRefLists;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE /*4099*/:
                return this.annotationSets;
            case 8192:
                return this.classDatas;
            case 8193:
                return this.codes;
            case 8194:
                return this.stringDatas;
            case 8195:
                return this.debugInfos;
            case 8196:
                return this.annotations;
            case 8197:
                return this.encodedArrays;
            case 8198:
                return this.annotationsDirectories;
            default:
                throw new IllegalArgumentException("unknown section type: " + i);
        }
    }

    public void readFrom(Dex dex) throws IOException {
        readHeader(dex.openSection(this.header));
        readMap(dex.openSection(this.mapList.off));
        computeSizesFromOffsets();
    }

    private void readHeader(com.tencent.tinker.android.dex.Dex.Section section) throws UnsupportedEncodingException {
        byte[] readByteArray = section.readByteArray(8);
        if (DexFormat.magicToApi(readByteArray) != 13) {
            throw new DexException("Unexpected magic: " + Arrays.toString(readByteArray));
        }
        this.checksum = section.readInt();
        this.signature = section.readByteArray(20);
        this.fileSize = section.readInt();
        int readInt = section.readInt();
        if (readInt != 112) {
            throw new DexException("Unexpected header: 0x" + Integer.toHexString(readInt));
        }
        readInt = section.readInt();
        if (readInt != DexFormat.ENDIAN_TAG) {
            throw new DexException("Unexpected endian tag: 0x" + Integer.toHexString(readInt));
        }
        this.linkSize = section.readInt();
        this.linkOff = section.readInt();
        this.mapList.off = section.readInt();
        if (this.mapList.off == 0) {
            throw new DexException("Cannot merge dex files that do not contain a map");
        }
        this.stringIds.size = section.readInt();
        this.stringIds.off = section.readInt();
        this.typeIds.size = section.readInt();
        this.typeIds.off = section.readInt();
        this.protoIds.size = section.readInt();
        this.protoIds.off = section.readInt();
        this.fieldIds.size = section.readInt();
        this.fieldIds.off = section.readInt();
        this.methodIds.size = section.readInt();
        this.methodIds.off = section.readInt();
        this.classDefs.size = section.readInt();
        this.classDefs.off = section.readInt();
        this.dataSize = section.readInt();
        this.dataOff = section.readInt();
    }

    private void readMap(com.tencent.tinker.android.dex.Dex.Section section) throws IOException {
        int readInt = section.readInt();
        Section section2 = null;
        int i = 0;
        while (i < readInt) {
            short readShort = section.readShort();
            section.readShort();
            Section section3 = getSection(readShort);
            int readInt2 = section.readInt();
            int readInt3 = section.readInt();
            if ((section3.size == 0 || section3.size == readInt2) && (section3.off == -1 || section3.off == readInt3)) {
                section3.size = readInt2;
                section3.off = readInt3;
                if (section2 == null || section2.off <= section3.off) {
                    i++;
                    section2 = section3;
                } else {
                    throw new DexException("Map is unsorted at " + section2 + ", " + section3);
                }
            }
            throw new DexException("Unexpected map value for 0x" + Integer.toHexString(readShort));
        }
        this.header.off = 0;
        Arrays.sort(this.sections);
        for (i = 1; i < this.sections.length; i++) {
            if (this.sections[i].off == -1) {
                this.sections[i].off = this.sections[i - 1].off;
            }
        }
    }

    public void computeSizesFromOffsets() {
        int i = this.fileSize;
        for (int length = this.sections.length - 1; length >= 0; length--) {
            Section section = this.sections[length];
            if (section.off != -1) {
                if (section.off > i) {
                    throw new DexException("Map is unsorted at " + section);
                }
                section.byteCount = i - section.off;
                i = section.off;
            }
        }
        this.dataOff = (((((this.header.byteCount + this.stringIds.byteCount) + this.typeIds.byteCount) + this.protoIds.byteCount) + this.fieldIds.byteCount) + this.methodIds.byteCount) + this.classDefs.byteCount;
        this.dataSize = this.fileSize - this.dataOff;
    }

    private Section getSection(short s) {
        for (Section section : this.sections) {
            if (section.type == s) {
                return section;
            }
        }
        throw new IllegalArgumentException("No such map item: " + s);
    }

    public void writeHeader(com.tencent.tinker.android.dex.Dex.Section section) throws IOException {
        int i;
        int i2 = 0;
        section.write(DexFormat.apiToMagic(13).getBytes("UTF-8"));
        section.writeInt(this.checksum);
        section.write(this.signature);
        section.writeInt(this.fileSize);
        section.writeInt(112);
        section.writeInt(DexFormat.ENDIAN_TAG);
        section.writeInt(this.linkSize);
        section.writeInt(this.linkOff);
        section.writeInt(this.mapList.off);
        section.writeInt(this.stringIds.size);
        section.writeInt(this.stringIds.exists() ? this.stringIds.off : 0);
        section.writeInt(this.typeIds.size);
        if (this.typeIds.exists()) {
            i = this.typeIds.off;
        } else {
            i = 0;
        }
        section.writeInt(i);
        section.writeInt(this.protoIds.size);
        if (this.protoIds.exists()) {
            i = this.protoIds.off;
        } else {
            i = 0;
        }
        section.writeInt(i);
        section.writeInt(this.fieldIds.size);
        if (this.fieldIds.exists()) {
            i = this.fieldIds.off;
        } else {
            i = 0;
        }
        section.writeInt(i);
        section.writeInt(this.methodIds.size);
        if (this.methodIds.exists()) {
            i = this.methodIds.off;
        } else {
            i = 0;
        }
        section.writeInt(i);
        section.writeInt(this.classDefs.size);
        if (this.classDefs.exists()) {
            i2 = this.classDefs.off;
        }
        section.writeInt(i2);
        section.writeInt(this.dataSize);
        section.writeInt(this.dataOff);
    }

    public void writeMap(com.tencent.tinker.android.dex.Dex.Section section) throws IOException {
        int i = 0;
        for (Section exists : this.sections) {
            if (exists.exists()) {
                i++;
            }
        }
        section.writeInt(i);
        for (Section section2 : this.sections) {
            if (section2.exists()) {
                section.writeShort(section2.type);
                section.writeShort((short) 0);
                section.writeInt(section2.size);
                section.writeInt(section2.off);
            }
        }
    }
}
