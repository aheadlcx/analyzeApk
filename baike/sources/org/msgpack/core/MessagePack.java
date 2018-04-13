package org.msgpack.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.msgpack.core.buffer.ArrayBufferInput;
import org.msgpack.core.buffer.ByteBufferInput;
import org.msgpack.core.buffer.ChannelBufferInput;
import org.msgpack.core.buffer.ChannelBufferOutput;
import org.msgpack.core.buffer.InputStreamBufferInput;
import org.msgpack.core.buffer.MessageBufferInput;
import org.msgpack.core.buffer.MessageBufferOutput;
import org.msgpack.core.buffer.OutputStreamBufferOutput;

public class MessagePack {
    public static final PackerConfig DEFAULT_PACKER_CONFIG = new PackerConfig();
    public static final UnpackerConfig DEFAULT_UNPACKER_CONFIG = new UnpackerConfig();
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static final class Code {
        public static final byte ARRAY16 = (byte) -36;
        public static final byte ARRAY32 = (byte) -35;
        public static final byte BIN16 = (byte) -59;
        public static final byte BIN32 = (byte) -58;
        public static final byte BIN8 = (byte) -60;
        public static final byte EXT16 = (byte) -56;
        public static final byte EXT32 = (byte) -55;
        public static final byte EXT8 = (byte) -57;
        public static final byte FALSE = (byte) -62;
        public static final byte FIXARRAY_PREFIX = (byte) -112;
        public static final byte FIXEXT1 = (byte) -44;
        public static final byte FIXEXT16 = (byte) -40;
        public static final byte FIXEXT2 = (byte) -43;
        public static final byte FIXEXT4 = (byte) -42;
        public static final byte FIXEXT8 = (byte) -41;
        public static final byte FIXMAP_PREFIX = Byte.MIN_VALUE;
        public static final byte FIXSTR_PREFIX = (byte) -96;
        public static final byte FLOAT32 = (byte) -54;
        public static final byte FLOAT64 = (byte) -53;
        public static final byte INT16 = (byte) -47;
        public static final byte INT32 = (byte) -46;
        public static final byte INT64 = (byte) -45;
        public static final byte INT8 = (byte) -48;
        public static final byte MAP16 = (byte) -34;
        public static final byte MAP32 = (byte) -33;
        public static final byte NEGFIXINT_PREFIX = (byte) -32;
        public static final byte NEVER_USED = (byte) -63;
        public static final byte NIL = (byte) -64;
        public static final byte POSFIXINT_MASK = Byte.MIN_VALUE;
        public static final byte STR16 = (byte) -38;
        public static final byte STR32 = (byte) -37;
        public static final byte STR8 = (byte) -39;
        public static final byte TRUE = (byte) -61;
        public static final byte UINT16 = (byte) -51;
        public static final byte UINT32 = (byte) -50;
        public static final byte UINT64 = (byte) -49;
        public static final byte UINT8 = (byte) -52;

        public static final boolean isFixInt(byte b) {
            int i = b & 255;
            return i <= 127 || i >= 224;
        }

        public static final boolean isPosFixInt(byte b) {
            return (b & -128) == 0;
        }

        public static final boolean isNegFixInt(byte b) {
            return (b & -32) == -32;
        }

        public static final boolean isFixStr(byte b) {
            return (b & -32) == -96;
        }

        public static final boolean isFixedArray(byte b) {
            return (b & -16) == -112;
        }

        public static final boolean isFixedMap(byte b) {
            return (b & -16) == -128;
        }

        public static final boolean isFixedRaw(byte b) {
            return (b & -32) == -96;
        }
    }

    public static class PackerConfig implements Cloneable {
        private int bufferFlushThreshold = 8192;
        private int bufferSize = 8192;
        private int smallStringOptimizationThreshold = 512;
        private boolean str8FormatSupport = true;

        private PackerConfig(PackerConfig packerConfig) {
            this.smallStringOptimizationThreshold = packerConfig.smallStringOptimizationThreshold;
            this.bufferFlushThreshold = packerConfig.bufferFlushThreshold;
            this.bufferSize = packerConfig.bufferSize;
            this.str8FormatSupport = packerConfig.str8FormatSupport;
        }

        public PackerConfig clone() {
            return new PackerConfig(this);
        }

        public int hashCode() {
            return (this.str8FormatSupport ? 1 : 0) + (((((this.smallStringOptimizationThreshold * 31) + this.bufferFlushThreshold) * 31) + this.bufferSize) * 31);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PackerConfig)) {
                return false;
            }
            PackerConfig packerConfig = (PackerConfig) obj;
            if (this.smallStringOptimizationThreshold == packerConfig.smallStringOptimizationThreshold && this.bufferFlushThreshold == packerConfig.bufferFlushThreshold && this.bufferSize == packerConfig.bufferSize && this.str8FormatSupport == packerConfig.str8FormatSupport) {
                return true;
            }
            return false;
        }

        public MessagePacker newPacker(MessageBufferOutput messageBufferOutput) {
            return new MessagePacker(messageBufferOutput, this);
        }

        public MessagePacker newPacker(OutputStream outputStream) {
            return newPacker(new OutputStreamBufferOutput(outputStream, this.bufferSize));
        }

        public MessagePacker newPacker(WritableByteChannel writableByteChannel) {
            return newPacker(new ChannelBufferOutput(writableByteChannel, this.bufferSize));
        }

        public MessageBufferPacker newBufferPacker() {
            return new MessageBufferPacker(this);
        }

        public PackerConfig withSmallStringOptimizationThreshold(int i) {
            PackerConfig clone = clone();
            clone.smallStringOptimizationThreshold = i;
            return clone;
        }

        public int getSmallStringOptimizationThreshold() {
            return this.smallStringOptimizationThreshold;
        }

        public PackerConfig withBufferFlushThreshold(int i) {
            PackerConfig clone = clone();
            clone.bufferFlushThreshold = i;
            return clone;
        }

        public int getBufferFlushThreshold() {
            return this.bufferFlushThreshold;
        }

        public PackerConfig withBufferSize(int i) {
            PackerConfig clone = clone();
            clone.bufferSize = i;
            return clone;
        }

        public int getBufferSize() {
            return this.bufferSize;
        }

        public PackerConfig withStr8FormatSupport(boolean z) {
            PackerConfig clone = clone();
            clone.str8FormatSupport = z;
            return clone;
        }

        public boolean isStr8FormatSupport() {
            return this.str8FormatSupport;
        }
    }

    public static class UnpackerConfig implements Cloneable {
        private CodingErrorAction actionOnMalformedString = CodingErrorAction.REPLACE;
        private CodingErrorAction actionOnUnmappableString = CodingErrorAction.REPLACE;
        private boolean allowReadingBinaryAsString = true;
        private boolean allowReadingStringAsBinary = true;
        private int bufferSize = 8192;
        private int stringDecoderBufferSize = 8192;
        private int stringSizeLimit = Integer.MAX_VALUE;

        private UnpackerConfig(UnpackerConfig unpackerConfig) {
            this.allowReadingStringAsBinary = unpackerConfig.allowReadingStringAsBinary;
            this.allowReadingBinaryAsString = unpackerConfig.allowReadingBinaryAsString;
            this.actionOnMalformedString = unpackerConfig.actionOnMalformedString;
            this.actionOnUnmappableString = unpackerConfig.actionOnUnmappableString;
            this.stringSizeLimit = unpackerConfig.stringSizeLimit;
            this.bufferSize = unpackerConfig.bufferSize;
        }

        public UnpackerConfig clone() {
            return new UnpackerConfig(this);
        }

        public int hashCode() {
            int i = 1;
            int i2 = 0;
            int i3 = (this.allowReadingStringAsBinary ? 1 : 0) * 31;
            if (!this.allowReadingBinaryAsString) {
                i = 0;
            }
            i = (i3 + i) * 31;
            if (this.actionOnMalformedString != null) {
                i3 = this.actionOnMalformedString.hashCode();
            } else {
                i3 = 0;
            }
            i3 = (i3 + i) * 31;
            if (this.actionOnUnmappableString != null) {
                i2 = this.actionOnUnmappableString.hashCode();
            }
            return ((((((i3 + i2) * 31) + this.stringSizeLimit) * 31) + this.bufferSize) * 31) + this.stringDecoderBufferSize;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof UnpackerConfig)) {
                return false;
            }
            UnpackerConfig unpackerConfig = (UnpackerConfig) obj;
            if (this.allowReadingStringAsBinary == unpackerConfig.allowReadingStringAsBinary && this.allowReadingBinaryAsString == unpackerConfig.allowReadingBinaryAsString && this.actionOnMalformedString == unpackerConfig.actionOnMalformedString && this.actionOnUnmappableString == unpackerConfig.actionOnUnmappableString && this.stringSizeLimit == unpackerConfig.stringSizeLimit && this.stringDecoderBufferSize == unpackerConfig.stringDecoderBufferSize && this.bufferSize == unpackerConfig.bufferSize) {
                return true;
            }
            return false;
        }

        public MessageUnpacker newUnpacker(MessageBufferInput messageBufferInput) {
            return new MessageUnpacker(messageBufferInput, this);
        }

        public MessageUnpacker newUnpacker(InputStream inputStream) {
            return newUnpacker(new InputStreamBufferInput(inputStream, this.bufferSize));
        }

        public MessageUnpacker newUnpacker(ReadableByteChannel readableByteChannel) {
            return newUnpacker(new ChannelBufferInput(readableByteChannel, this.bufferSize));
        }

        public MessageUnpacker newUnpacker(byte[] bArr) {
            return newUnpacker(new ArrayBufferInput(bArr));
        }

        public MessageUnpacker newUnpacker(byte[] bArr, int i, int i2) {
            return newUnpacker(new ArrayBufferInput(bArr, i, i2));
        }

        public MessageUnpacker newUnpacker(ByteBuffer byteBuffer) {
            return newUnpacker(new ByteBufferInput(byteBuffer));
        }

        public UnpackerConfig withAllowReadingStringAsBinary(boolean z) {
            UnpackerConfig clone = clone();
            clone.allowReadingStringAsBinary = z;
            return clone;
        }

        public boolean getAllowReadingStringAsBinary() {
            return this.allowReadingStringAsBinary;
        }

        public UnpackerConfig withAllowReadingBinaryAsString(boolean z) {
            UnpackerConfig clone = clone();
            clone.allowReadingBinaryAsString = z;
            return clone;
        }

        public boolean getAllowReadingBinaryAsString() {
            return this.allowReadingBinaryAsString;
        }

        public UnpackerConfig withActionOnMalformedString(CodingErrorAction codingErrorAction) {
            UnpackerConfig clone = clone();
            clone.actionOnMalformedString = codingErrorAction;
            return clone;
        }

        public CodingErrorAction getActionOnMalformedString() {
            return this.actionOnMalformedString;
        }

        public UnpackerConfig withActionOnUnmappableString(CodingErrorAction codingErrorAction) {
            UnpackerConfig clone = clone();
            clone.actionOnUnmappableString = codingErrorAction;
            return clone;
        }

        public CodingErrorAction getActionOnUnmappableString() {
            return this.actionOnUnmappableString;
        }

        public UnpackerConfig withStringSizeLimit(int i) {
            UnpackerConfig clone = clone();
            clone.stringSizeLimit = i;
            return clone;
        }

        public int getStringSizeLimit() {
            return this.stringSizeLimit;
        }

        public UnpackerConfig withStringDecoderBufferSize(int i) {
            UnpackerConfig clone = clone();
            clone.stringDecoderBufferSize = i;
            return clone;
        }

        public int getStringDecoderBufferSize() {
            return this.stringDecoderBufferSize;
        }

        public UnpackerConfig withBufferSize(int i) {
            UnpackerConfig clone = clone();
            clone.bufferSize = i;
            return clone;
        }

        public int getBufferSize() {
            return this.bufferSize;
        }
    }

    private MessagePack() {
    }

    public static MessagePacker newDefaultPacker(MessageBufferOutput messageBufferOutput) {
        return DEFAULT_PACKER_CONFIG.newPacker(messageBufferOutput);
    }

    public static MessagePacker newDefaultPacker(OutputStream outputStream) {
        return DEFAULT_PACKER_CONFIG.newPacker(outputStream);
    }

    public static MessagePacker newDefaultPacker(WritableByteChannel writableByteChannel) {
        return DEFAULT_PACKER_CONFIG.newPacker(writableByteChannel);
    }

    public static MessageBufferPacker newDefaultBufferPacker() {
        return DEFAULT_PACKER_CONFIG.newBufferPacker();
    }

    public static MessageUnpacker newDefaultUnpacker(MessageBufferInput messageBufferInput) {
        return DEFAULT_UNPACKER_CONFIG.newUnpacker(messageBufferInput);
    }

    public static MessageUnpacker newDefaultUnpacker(InputStream inputStream) {
        return DEFAULT_UNPACKER_CONFIG.newUnpacker(inputStream);
    }

    public static MessageUnpacker newDefaultUnpacker(ReadableByteChannel readableByteChannel) {
        return DEFAULT_UNPACKER_CONFIG.newUnpacker(readableByteChannel);
    }

    public static MessageUnpacker newDefaultUnpacker(byte[] bArr) {
        return DEFAULT_UNPACKER_CONFIG.newUnpacker(bArr);
    }

    public static MessageUnpacker newDefaultUnpacker(byte[] bArr, int i, int i2) {
        return DEFAULT_UNPACKER_CONFIG.newUnpacker(bArr, i, i2);
    }

    public static MessageUnpacker newDefaultUnpacker(ByteBuffer byteBuffer) {
        return DEFAULT_UNPACKER_CONFIG.newUnpacker(byteBuffer);
    }
}
