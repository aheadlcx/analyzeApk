package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.shareutil.ShareElfFile.SectionHeader;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public final class ShareOatUtil {
    private static final String TAG = "Tinker.OatUtil";

    private enum InstructionSet {
        kNone,
        kArm,
        kArm64,
        kThumb2,
        kX86,
        kX86_64,
        kMips,
        kMips64
    }

    private ShareOatUtil() {
        throw new UnsupportedOperationException();
    }

    public static String getOatFileInstructionSet(File file) throws Throwable {
        Throwable th;
        String str = "";
        ShareElfFile shareElfFile;
        try {
            shareElfFile = new ShareElfFile(file);
            String str2;
            try {
                SectionHeader sectionHeaderByName = shareElfFile.getSectionHeaderByName(".rodata");
                if (sectionHeaderByName == null) {
                    throw new IOException("Unable to find .rodata section.");
                }
                FileChannel channel = shareElfFile.getChannel();
                channel.position(sectionHeaderByName.shOffset);
                byte[] bArr = new byte[8];
                ShareElfFile.readUntilLimit(channel, ByteBuffer.wrap(bArr), "Failed to read oat magic and version.");
                if (bArr[0] == (byte) 111 && bArr[1] == (byte) 97 && bArr[2] == (byte) 116 && bArr[3] == (byte) 10) {
                    str2 = new String(bArr, 4, 3, Charset.forName("ASCII"));
                    Integer.parseInt(str2);
                    ByteBuffer allocate = ByteBuffer.allocate(128);
                    allocate.order(shareElfFile.getDataOrder());
                    channel.position(sectionHeaderByName.shOffset + 12);
                    allocate.limit(4);
                    ShareElfFile.readUntilLimit(channel, allocate, "Failed to read isa num.");
                    int i = allocate.getInt();
                    if (i < 0 || i >= InstructionSet.values().length) {
                        throw new IOException("Bad isa num: " + i);
                    }
                    switch (InstructionSet.values()[i]) {
                        case kArm:
                        case kThumb2:
                            str = "arm";
                            break;
                        case kArm64:
                            str = "arm64";
                            break;
                        case kX86:
                            str = "x86";
                            break;
                        case kX86_64:
                            str = "x86_64";
                            break;
                        case kMips:
                            str = "mips";
                            break;
                        case kMips64:
                            str = "mips64";
                            break;
                        case kNone:
                            str = "none";
                            break;
                        default:
                            throw new IOException("Should not reach here.");
                    }
                    if (shareElfFile != null) {
                        try {
                            shareElfFile.close();
                        } catch (Exception e) {
                        }
                    }
                    return str;
                }
                throw new IOException(String.format("Bad oat magic: %x %x %x %x", new Object[]{Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3])}));
            } catch (NumberFormatException e2) {
                throw new IOException("Bad oat version: " + str2);
            } catch (Throwable th2) {
                th = th2;
                if (shareElfFile != null) {
                    try {
                        shareElfFile.close();
                    } catch (Exception e3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            shareElfFile = null;
            if (shareElfFile != null) {
                shareElfFile.close();
            }
            throw th;
        }
    }
}
