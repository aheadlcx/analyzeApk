package com.tencent.tinker.loader.shareutil;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ShareElfFile implements Closeable {
    public static final int FILE_TYPE_ELF = 1;
    public static final int FILE_TYPE_ODEX = 0;
    public static final int FILE_TYPE_OTHERS = -1;
    public ElfHeader elfHeader = null;
    private final FileInputStream fis;
    public ProgramHeader[] programHeaders = null;
    public SectionHeader[] sectionHeaders = null;
    private final Map<String, SectionHeader> sectionNameToHeaderMap = new HashMap();

    public static class ElfHeader {
        public static final int EI_CLASS = 4;
        public static final int EI_DATA = 5;
        private static final int EI_NINDENT = 16;
        public static final int EI_VERSION = 6;
        public static final int ELFCLASS32 = 1;
        public static final int ELFCLASS64 = 2;
        public static final int ELFDATA2LSB = 1;
        public static final int ELFDATA2MSB = 2;
        public static final int ET_CORE = 4;
        public static final int ET_DYN = 3;
        public static final int ET_EXEC = 2;
        public static final int ET_HIPROC = 65535;
        public static final int ET_LOPROC = 65280;
        public static final int ET_NONE = 0;
        public static final int ET_REL = 1;
        public static final int EV_CURRENT = 1;
        public final short eEhSize;
        public final long eEntry;
        public final int eFlags;
        public final byte[] eIndent;
        public final short eMachine;
        public final short ePhEntSize;
        public final short ePhNum;
        public final long ePhOff;
        public final short eShEntSize;
        public final short eShNum;
        public final long eShOff;
        public final short eShStrNdx;
        public final short eType;
        public final int eVersion;

        private ElfHeader(FileChannel fileChannel) throws IOException {
            this.eIndent = new byte[16];
            fileChannel.position(0);
            fileChannel.read(ByteBuffer.wrap(this.eIndent));
            if (this.eIndent[0] == Byte.MAX_VALUE && this.eIndent[1] == (byte) 69 && this.eIndent[2] == (byte) 76 && this.eIndent[3] == (byte) 70) {
                ShareElfFile.assertInRange(this.eIndent[4], 1, 2, "bad elf class: " + this.eIndent[4]);
                ShareElfFile.assertInRange(this.eIndent[5], 1, 2, "bad elf data encoding: " + this.eIndent[5]);
                ByteBuffer allocate = ByteBuffer.allocate(this.eIndent[4] == (byte) 1 ? 36 : 48);
                allocate.order(this.eIndent[5] == (byte) 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
                ShareElfFile.readUntilLimit(fileChannel, allocate, "failed to read rest part of ehdr.");
                this.eType = allocate.getShort();
                this.eMachine = allocate.getShort();
                this.eVersion = allocate.getInt();
                ShareElfFile.assertInRange(this.eVersion, 1, 1, "bad elf version: " + this.eVersion);
                switch (this.eIndent[4]) {
                    case (byte) 1:
                        this.eEntry = (long) allocate.getInt();
                        this.ePhOff = (long) allocate.getInt();
                        this.eShOff = (long) allocate.getInt();
                        break;
                    case (byte) 2:
                        this.eEntry = allocate.getLong();
                        this.ePhOff = allocate.getLong();
                        this.eShOff = allocate.getLong();
                        break;
                    default:
                        throw new IOException("Unexpected elf class: " + this.eIndent[4]);
                }
                this.eFlags = allocate.getInt();
                this.eEhSize = allocate.getShort();
                this.ePhEntSize = allocate.getShort();
                this.ePhNum = allocate.getShort();
                this.eShEntSize = allocate.getShort();
                this.eShNum = allocate.getShort();
                this.eShStrNdx = allocate.getShort();
                return;
            }
            throw new IOException(String.format("bad elf magic: %x %x %x %x.", new Object[]{Byte.valueOf(this.eIndent[0]), Byte.valueOf(this.eIndent[1]), Byte.valueOf(this.eIndent[2]), Byte.valueOf(this.eIndent[3])}));
        }
    }

    public static class ProgramHeader {
        public static final int PF_R = 4;
        public static final int PF_W = 2;
        public static final int PF_X = 1;
        public static final int PT_DYNAMIC = 2;
        public static final int PT_HIPROC = Integer.MAX_VALUE;
        public static final int PT_INTERP = 3;
        public static final int PT_LOAD = 1;
        public static final int PT_LOPROC = 1879048192;
        public static final int PT_NOTE = 4;
        public static final int PT_NULL = 0;
        public static final int PT_PHDR = 6;
        public static final int PT_SHLIB = 5;
        public final long pAlign;
        public final long pFileSize;
        public final int pFlags;
        public final long pMemSize;
        public final long pOffset;
        public final long pPddr;
        public final int pType;
        public final long pVddr;

        private ProgramHeader(ByteBuffer byteBuffer, int i) throws IOException {
            switch (i) {
                case 1:
                    this.pType = byteBuffer.getInt();
                    this.pOffset = (long) byteBuffer.getInt();
                    this.pVddr = (long) byteBuffer.getInt();
                    this.pPddr = (long) byteBuffer.getInt();
                    this.pFileSize = (long) byteBuffer.getInt();
                    this.pMemSize = (long) byteBuffer.getInt();
                    this.pFlags = byteBuffer.getInt();
                    this.pAlign = (long) byteBuffer.getInt();
                    return;
                case 2:
                    this.pType = byteBuffer.getInt();
                    this.pFlags = byteBuffer.getInt();
                    this.pOffset = byteBuffer.getLong();
                    this.pVddr = byteBuffer.getLong();
                    this.pPddr = byteBuffer.getLong();
                    this.pFileSize = byteBuffer.getLong();
                    this.pMemSize = byteBuffer.getLong();
                    this.pAlign = byteBuffer.getLong();
                    return;
                default:
                    throw new IOException("Unexpected elf class: " + i);
            }
        }
    }

    public static class SectionHeader {
        public static final int SHF_ALLOC = 2;
        public static final int SHF_EXECINSTR = 4;
        public static final int SHF_MASKPROC = -268435456;
        public static final int SHF_WRITE = 1;
        public static final int SHN_ABS = 65521;
        public static final int SHN_COMMON = 65522;
        public static final int SHN_HIPROC = 65311;
        public static final int SHN_HIRESERVE = 65535;
        public static final int SHN_LOPROC = 65280;
        public static final int SHN_LORESERVE = 65280;
        public static final int SHN_UNDEF = 0;
        public static final int SHT_DYNAMIC = 6;
        public static final int SHT_DYNSYM = 11;
        public static final int SHT_HASH = 5;
        public static final int SHT_HIPROC = Integer.MAX_VALUE;
        public static final int SHT_HIUSER = -1;
        public static final int SHT_LOPROC = 1879048192;
        public static final int SHT_LOUSER = Integer.MIN_VALUE;
        public static final int SHT_NOBITS = 8;
        public static final int SHT_NOTE = 7;
        public static final int SHT_NULL = 0;
        public static final int SHT_PROGBITS = 1;
        public static final int SHT_REL = 9;
        public static final int SHT_RELA = 4;
        public static final int SHT_SHLIB = 10;
        public static final int SHT_STRTAB = 3;
        public static final int SHT_SYMTAB = 2;
        public final long shAddr;
        public final long shAddrAlign;
        public final long shEntSize;
        public final long shFlags;
        public final int shInfo;
        public final int shLink;
        public final int shName;
        public String shNameStr;
        public final long shOffset;
        public final long shSize;
        public final int shType;

        private SectionHeader(ByteBuffer byteBuffer, int i) throws IOException {
            switch (i) {
                case 1:
                    this.shName = byteBuffer.getInt();
                    this.shType = byteBuffer.getInt();
                    this.shFlags = (long) byteBuffer.getInt();
                    this.shAddr = (long) byteBuffer.getInt();
                    this.shOffset = (long) byteBuffer.getInt();
                    this.shSize = (long) byteBuffer.getInt();
                    this.shLink = byteBuffer.getInt();
                    this.shInfo = byteBuffer.getInt();
                    this.shAddrAlign = (long) byteBuffer.getInt();
                    this.shEntSize = (long) byteBuffer.getInt();
                    break;
                case 2:
                    this.shName = byteBuffer.getInt();
                    this.shType = byteBuffer.getInt();
                    this.shFlags = byteBuffer.getLong();
                    this.shAddr = byteBuffer.getLong();
                    this.shOffset = byteBuffer.getLong();
                    this.shSize = byteBuffer.getLong();
                    this.shLink = byteBuffer.getInt();
                    this.shInfo = byteBuffer.getInt();
                    this.shAddrAlign = byteBuffer.getLong();
                    this.shEntSize = byteBuffer.getLong();
                    break;
                default:
                    throw new IOException("Unexpected elf class: " + i);
            }
            this.shNameStr = null;
        }
    }

    public ShareElfFile(File file) throws IOException {
        int i;
        int i2 = 0;
        this.fis = new FileInputStream(file);
        FileChannel channel = this.fis.getChannel();
        this.elfHeader = new ElfHeader(channel);
        ByteBuffer allocate = ByteBuffer.allocate(128);
        allocate.limit(this.elfHeader.ePhEntSize);
        allocate.order(this.elfHeader.eIndent[5] == (byte) 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        channel.position(this.elfHeader.ePhOff);
        this.programHeaders = new ProgramHeader[this.elfHeader.ePhNum];
        for (i = 0; i < this.programHeaders.length; i++) {
            readUntilLimit(channel, allocate, "failed to read phdr.");
            this.programHeaders[i] = new ProgramHeader(allocate, this.elfHeader.eIndent[4]);
        }
        channel.position(this.elfHeader.eShOff);
        allocate.limit(this.elfHeader.eShEntSize);
        this.sectionHeaders = new SectionHeader[this.elfHeader.eShNum];
        for (i = 0; i < this.sectionHeaders.length; i++) {
            readUntilLimit(channel, allocate, "failed to read shdr.");
            this.sectionHeaders[i] = new SectionHeader(allocate, this.elfHeader.eIndent[4]);
        }
        if (this.elfHeader.eShStrNdx > (short) 0) {
            ByteBuffer section = getSection(this.sectionHeaders[this.elfHeader.eShStrNdx]);
            SectionHeader[] sectionHeaderArr = this.sectionHeaders;
            int length = sectionHeaderArr.length;
            while (i2 < length) {
                SectionHeader sectionHeader = sectionHeaderArr[i2];
                section.position(sectionHeader.shName);
                sectionHeader.shNameStr = readCString(section);
                this.sectionNameToHeaderMap.put(sectionHeader.shNameStr, sectionHeader);
                i2++;
            }
        }
    }

    private static void assertInRange(int i, int i2, int i3, String str) throws IOException {
        if (i < i2 || i > i3) {
            throw new IOException(str);
        }
    }

    public static int getFileTypeByMagic(File file) throws IOException {
        Throwable th;
        InputStream inputStream;
        try {
            byte[] bArr = new byte[4];
            InputStream fileInputStream = new FileInputStream(file);
            try {
                fileInputStream.read(bArr);
                if (bArr[0] == (byte) 100 && bArr[1] == (byte) 101 && bArr[2] == (byte) 121 && bArr[3] == (byte) 10) {
                    if (fileInputStream == null) {
                        return 0;
                    }
                    try {
                        fileInputStream.close();
                        return 0;
                    } catch (Throwable th2) {
                        return 0;
                    }
                } else if (bArr[0] == Byte.MAX_VALUE && bArr[1] == (byte) 69 && bArr[2] == (byte) 76 && bArr[3] == (byte) 70) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                    return 1;
                } else if (fileInputStream == null) {
                    return -1;
                } else {
                    try {
                        fileInputStream.close();
                        return -1;
                    } catch (Throwable th4) {
                        return -1;
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                inputStream = fileInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th6) {
                    }
                }
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static void readUntilLimit(FileChannel fileChannel, ByteBuffer byteBuffer, String str) throws IOException {
        byteBuffer.rewind();
        int read = fileChannel.read(byteBuffer);
        if (read != byteBuffer.limit()) {
            throw new IOException(str + " Rest bytes insufficient, expect to read " + byteBuffer.limit() + " bytes but only " + read + " bytes were read.");
        }
        byteBuffer.flip();
    }

    public static String readCString(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int position = byteBuffer.position();
        while (byteBuffer.hasRemaining() && array[byteBuffer.position()] != (byte) 0) {
            byteBuffer.position(byteBuffer.position() + 1);
        }
        byteBuffer.position(byteBuffer.position() + 1);
        return new String(array, position, (byteBuffer.position() - position) - 1, Charset.forName("ASCII"));
    }

    public FileChannel getChannel() {
        return this.fis.getChannel();
    }

    public boolean is32BitElf() {
        return this.elfHeader.eIndent[4] == (byte) 1;
    }

    public ByteOrder getDataOrder() {
        return this.elfHeader.eIndent[5] == (byte) 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
    }

    public SectionHeader getSectionHeaderByName(String str) {
        return (SectionHeader) this.sectionNameToHeaderMap.get(str);
    }

    public ByteBuffer getSection(SectionHeader sectionHeader) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate((int) sectionHeader.shSize);
        this.fis.getChannel().position(sectionHeader.shOffset);
        readUntilLimit(this.fis.getChannel(), allocate, "failed to read section: " + sectionHeader.shNameStr);
        return allocate;
    }

    public ByteBuffer getSegment(ProgramHeader programHeader) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate((int) programHeader.pFileSize);
        this.fis.getChannel().position(programHeader.pOffset);
        readUntilLimit(this.fis.getChannel(), allocate, "failed to read segment (type: " + programHeader.pType + ").");
        return allocate;
    }

    public void close() throws IOException {
        this.fis.close();
        this.sectionNameToHeaderMap.clear();
        this.programHeaders = null;
        this.sectionHeaders = null;
    }
}
