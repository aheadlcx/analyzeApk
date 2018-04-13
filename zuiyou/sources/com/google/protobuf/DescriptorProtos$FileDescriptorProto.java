package com.google.protobuf;

import com.google.protobuf.DescriptorProtos.DescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.EnumDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.FileDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.ServiceDescriptorProtoOrBuilder;
import com.google.protobuf.DescriptorProtos.SourceCodeInfoOrBuilder;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.GeneratedMessageV3.Builder;
import com.google.protobuf.GeneratedMessageV3.BuilderParent;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos$FileDescriptorProto extends GeneratedMessageV3 implements FileDescriptorProtoOrBuilder {
    private static final DescriptorProtos$FileDescriptorProto DEFAULT_INSTANCE = new DescriptorProtos$FileDescriptorProto();
    public static final int DEPENDENCY_FIELD_NUMBER = 3;
    public static final int ENUM_TYPE_FIELD_NUMBER = 5;
    public static final int EXTENSION_FIELD_NUMBER = 7;
    public static final int MESSAGE_TYPE_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 8;
    public static final int PACKAGE_FIELD_NUMBER = 2;
    @Deprecated
    public static final Parser<DescriptorProtos$FileDescriptorProto> PARSER = new DescriptorProtos$FileDescriptorProto$1();
    public static final int PUBLIC_DEPENDENCY_FIELD_NUMBER = 10;
    public static final int SERVICE_FIELD_NUMBER = 6;
    public static final int SOURCE_CODE_INFO_FIELD_NUMBER = 9;
    public static final int SYNTAX_FIELD_NUMBER = 12;
    public static final int WEAK_DEPENDENCY_FIELD_NUMBER = 11;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private LazyStringList dependency_;
    private List<DescriptorProtos$EnumDescriptorProto> enumType_;
    private List<DescriptorProtos$FieldDescriptorProto> extension_;
    private byte memoizedIsInitialized;
    private List<DescriptorProtos$DescriptorProto> messageType_;
    private volatile Object name_;
    private DescriptorProtos$FileOptions options_;
    private volatile Object package_;
    private List<Integer> publicDependency_;
    private List<DescriptorProtos$ServiceDescriptorProto> service_;
    private DescriptorProtos$SourceCodeInfo sourceCodeInfo_;
    private volatile Object syntax_;
    private List<Integer> weakDependency_;

    private DescriptorProtos$FileDescriptorProto(Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DescriptorProtos$FileDescriptorProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.package_ = "";
        this.dependency_ = LazyStringArrayList.EMPTY;
        this.publicDependency_ = Collections.emptyList();
        this.weakDependency_ = Collections.emptyList();
        this.messageType_ = Collections.emptyList();
        this.enumType_ = Collections.emptyList();
        this.service_ = Collections.emptyList();
        this.extension_ = Collections.emptyList();
        this.syntax_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DescriptorProtos$FileDescriptorProto(com.google.protobuf.CodedInputStream r13, com.google.protobuf.ExtensionRegistryLite r14) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
        r12 = this;
        r10 = 64;
        r9 = 32;
        r8 = 16;
        r7 = 4;
        r6 = 8;
        r12.<init>();
        r2 = 0;
        r4 = com.google.protobuf.UnknownFieldSet.newBuilder();
        r1 = 0;
    L_0x0012:
        if (r1 != 0) goto L_0x0269;
    L_0x0014:
        r0 = r13.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        switch(r0) {
            case 0: goto L_0x0026;
            case 10: goto L_0x0029;
            case 18: goto L_0x0038;
            case 26: goto L_0x0047;
            case 34: goto L_0x0061;
            case 42: goto L_0x007d;
            case 50: goto L_0x0099;
            case 58: goto L_0x00b8;
            case 66: goto L_0x00d7;
            case 74: goto L_0x0106;
            case 80: goto L_0x0135;
            case 82: goto L_0x0154;
            case 88: goto L_0x01f6;
            case 90: goto L_0x0215;
            case 98: goto L_0x0259;
            default: goto L_0x001b;
        };	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
    L_0x001b:
        r0 = r12.parseUnknownField(r13, r4, r14, r0);	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        if (r0 != 0) goto L_0x02f7;
    L_0x0021:
        r0 = 1;
        r1 = r2;
    L_0x0023:
        r2 = r1;
        r1 = r0;
        goto L_0x0012;
    L_0x0026:
        r0 = 1;
        r1 = r2;
        goto L_0x0023;
    L_0x0029:
        r0 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r3 | 1;
        r12.bitField0_ = r3;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r1;
        r1 = r2;
        goto L_0x0023;
    L_0x0038:
        r0 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r3 | 2;
        r12.bitField0_ = r3;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.package_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r1;
        r1 = r2;
        goto L_0x0023;
    L_0x0047:
        r3 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 & 4;
        if (r0 == r7) goto L_0x02f4;
    L_0x004f:
        r0 = new com.google.protobuf.LazyStringArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.dependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 4;
    L_0x0058:
        r2 = r12.dependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x0061:
        r0 = r2 & 32;
        if (r0 == r9) goto L_0x02f1;
    L_0x0065:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.messageType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 32;
    L_0x006e:
        r2 = r12.messageType_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = com.google.protobuf.DescriptorProtos$DescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x007d:
        r0 = r2 & 64;
        if (r0 == r10) goto L_0x02ee;
    L_0x0081:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.enumType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 64;
    L_0x008a:
        r2 = r12.enumType_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = com.google.protobuf.DescriptorProtos$EnumDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x0099:
        r0 = r2 & 128;
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r0 == r3) goto L_0x02eb;
    L_0x009f:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.service_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 128;
    L_0x00a8:
        r2 = r12.service_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = com.google.protobuf.DescriptorProtos$ServiceDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x00b8:
        r0 = r2 & 256;
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r0 == r3) goto L_0x02e8;
    L_0x00be:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.extension_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 256;
    L_0x00c7:
        r2 = r12.extension_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = com.google.protobuf.DescriptorProtos$FieldDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x00d7:
        r0 = 0;
        r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r3 & 4;
        if (r3 != r7) goto L_0x02e5;
    L_0x00de:
        r0 = r12.options_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r0;
    L_0x00e5:
        r0 = com.google.protobuf.DescriptorProtos$FileOptions.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r13.readMessage(r0, r14);	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = (com.google.protobuf.DescriptorProtos$FileOptions) r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        if (r3 == 0) goto L_0x00fc;
    L_0x00f1:
        r0 = r12.options_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r3.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
    L_0x00fc:
        r0 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r0 | 4;
        r12.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r1;
        r1 = r2;
        goto L_0x0023;
    L_0x0106:
        r0 = 0;
        r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r3 & 8;
        if (r3 != r6) goto L_0x02e2;
    L_0x010d:
        r0 = r12.sourceCodeInfo_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r0;
    L_0x0114:
        r0 = com.google.protobuf.DescriptorProtos$SourceCodeInfo.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r13.readMessage(r0, r14);	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = (com.google.protobuf.DescriptorProtos$SourceCodeInfo) r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.sourceCodeInfo_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        if (r3 == 0) goto L_0x012b;
    L_0x0120:
        r0 = r12.sourceCodeInfo_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r3.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.sourceCodeInfo_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
    L_0x012b:
        r0 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r0 | 8;
        r12.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r1;
        r1 = r2;
        goto L_0x0023;
    L_0x0135:
        r0 = r2 & 8;
        if (r0 == r6) goto L_0x02df;
    L_0x0139:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.publicDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 8;
    L_0x0142:
        r2 = r12.publicDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x0154:
        r0 = r13.readRawVarint32();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r13.pushLimit(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 & 8;
        if (r0 == r6) goto L_0x02dc;
    L_0x0160:
        r0 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        if (r0 <= 0) goto L_0x02dc;
    L_0x0166:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.publicDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 8;
    L_0x016f:
        r2 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        if (r2 <= 0) goto L_0x01ee;
    L_0x0175:
        r2 = r12.publicDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r5 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        goto L_0x016f;
    L_0x0183:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0186:
        r0 = r0.setUnfinishedMessage(r12);	 Catch:{ all -> 0x018b }
        throw r0;	 Catch:{ all -> 0x018b }
    L_0x018b:
        r0 = move-exception;
    L_0x018c:
        r1 = r2 & 4;
        if (r1 != r7) goto L_0x0198;
    L_0x0190:
        r1 = r12.dependency_;
        r1 = r1.getUnmodifiableView();
        r12.dependency_ = r1;
    L_0x0198:
        r1 = r2 & 32;
        if (r1 != r9) goto L_0x01a4;
    L_0x019c:
        r1 = r12.messageType_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.messageType_ = r1;
    L_0x01a4:
        r1 = r2 & 64;
        if (r1 != r10) goto L_0x01b0;
    L_0x01a8:
        r1 = r12.enumType_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.enumType_ = r1;
    L_0x01b0:
        r1 = r2 & 128;
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r1 != r3) goto L_0x01be;
    L_0x01b6:
        r1 = r12.service_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.service_ = r1;
    L_0x01be:
        r1 = r2 & 256;
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r1 != r3) goto L_0x01cc;
    L_0x01c4:
        r1 = r12.extension_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.extension_ = r1;
    L_0x01cc:
        r1 = r2 & 8;
        if (r1 != r6) goto L_0x01d8;
    L_0x01d0:
        r1 = r12.publicDependency_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.publicDependency_ = r1;
    L_0x01d8:
        r1 = r2 & 16;
        if (r1 != r8) goto L_0x01e4;
    L_0x01dc:
        r1 = r12.weakDependency_;
        r1 = java.util.Collections.unmodifiableList(r1);
        r12.weakDependency_ = r1;
    L_0x01e4:
        r1 = r4.build();
        r12.unknownFields = r1;
        r12.makeExtensionsImmutable();
        throw r0;
    L_0x01ee:
        r13.popLimit(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x01f6:
        r0 = r2 & 16;
        if (r0 == r8) goto L_0x02d9;
    L_0x01fa:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.weakDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 16;
    L_0x0203:
        r2 = r12.weakDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x0215:
        r0 = r13.readRawVarint32();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r13.pushLimit(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 & 16;
        if (r0 == r8) goto L_0x02d6;
    L_0x0221:
        r0 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        if (r0 <= 0) goto L_0x02d6;
    L_0x0227:
        r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.weakDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r2 | 16;
    L_0x0230:
        r2 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        if (r2 <= 0) goto L_0x0251;
    L_0x0236:
        r2 = r12.weakDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r5 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r2.add(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        goto L_0x0230;
    L_0x0244:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0247:
        r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x018b }
        r1.<init>(r0);	 Catch:{ all -> 0x018b }
        r0 = r1.setUnfinishedMessage(r12);	 Catch:{ all -> 0x018b }
        throw r0;	 Catch:{ all -> 0x018b }
    L_0x0251:
        r13.popLimit(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0183, IOException -> 0x0244, all -> 0x02cb }
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x0023;
    L_0x0259:
        r0 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r3 = r3 | 16;
        r12.bitField0_ = r3;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r12.syntax_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02d3, IOException -> 0x02d0 }
        r0 = r1;
        r1 = r2;
        goto L_0x0023;
    L_0x0269:
        r0 = r2 & 4;
        if (r0 != r7) goto L_0x0275;
    L_0x026d:
        r0 = r12.dependency_;
        r0 = r0.getUnmodifiableView();
        r12.dependency_ = r0;
    L_0x0275:
        r0 = r2 & 32;
        if (r0 != r9) goto L_0x0281;
    L_0x0279:
        r0 = r12.messageType_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.messageType_ = r0;
    L_0x0281:
        r0 = r2 & 64;
        if (r0 != r10) goto L_0x028d;
    L_0x0285:
        r0 = r12.enumType_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.enumType_ = r0;
    L_0x028d:
        r0 = r2 & 128;
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r0 != r1) goto L_0x029b;
    L_0x0293:
        r0 = r12.service_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.service_ = r0;
    L_0x029b:
        r0 = r2 & 256;
        r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r0 != r1) goto L_0x02a9;
    L_0x02a1:
        r0 = r12.extension_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.extension_ = r0;
    L_0x02a9:
        r0 = r2 & 8;
        if (r0 != r6) goto L_0x02b5;
    L_0x02ad:
        r0 = r12.publicDependency_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.publicDependency_ = r0;
    L_0x02b5:
        r0 = r2 & 16;
        if (r0 != r8) goto L_0x02c1;
    L_0x02b9:
        r0 = r12.weakDependency_;
        r0 = java.util.Collections.unmodifiableList(r0);
        r12.weakDependency_ = r0;
    L_0x02c1:
        r0 = r4.build();
        r12.unknownFields = r0;
        r12.makeExtensionsImmutable();
        return;
    L_0x02cb:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x018c;
    L_0x02d0:
        r0 = move-exception;
        goto L_0x0247;
    L_0x02d3:
        r0 = move-exception;
        goto L_0x0186;
    L_0x02d6:
        r0 = r2;
        goto L_0x0230;
    L_0x02d9:
        r0 = r2;
        goto L_0x0203;
    L_0x02dc:
        r0 = r2;
        goto L_0x016f;
    L_0x02df:
        r0 = r2;
        goto L_0x0142;
    L_0x02e2:
        r3 = r0;
        goto L_0x0114;
    L_0x02e5:
        r3 = r0;
        goto L_0x00e5;
    L_0x02e8:
        r0 = r2;
        goto L_0x00c7;
    L_0x02eb:
        r0 = r2;
        goto L_0x00a8;
    L_0x02ee:
        r0 = r2;
        goto L_0x008a;
    L_0x02f1:
        r0 = r2;
        goto L_0x006e;
    L_0x02f4:
        r0 = r2;
        goto L_0x0058;
    L_0x02f7:
        r0 = r1;
        r1 = r2;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorProtos$FileDescriptorProto.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return DescriptorProtos.access$700();
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return DescriptorProtos.access$800().ensureFieldAccessorsInitialized(DescriptorProtos$FileDescriptorProto.class, DescriptorProtos$FileDescriptorProto$Builder.class);
    }

    public boolean hasName() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.name_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasPackage() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getPackage() {
        Object obj = this.package_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.package_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getPackageBytes() {
        Object obj = this.package_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.package_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public ProtocolStringList getDependencyList() {
        return this.dependency_;
    }

    public int getDependencyCount() {
        return this.dependency_.size();
    }

    public String getDependency(int i) {
        return (String) this.dependency_.get(i);
    }

    public ByteString getDependencyBytes(int i) {
        return this.dependency_.getByteString(i);
    }

    public List<Integer> getPublicDependencyList() {
        return this.publicDependency_;
    }

    public int getPublicDependencyCount() {
        return this.publicDependency_.size();
    }

    public int getPublicDependency(int i) {
        return ((Integer) this.publicDependency_.get(i)).intValue();
    }

    public List<Integer> getWeakDependencyList() {
        return this.weakDependency_;
    }

    public int getWeakDependencyCount() {
        return this.weakDependency_.size();
    }

    public int getWeakDependency(int i) {
        return ((Integer) this.weakDependency_.get(i)).intValue();
    }

    public List<DescriptorProtos$DescriptorProto> getMessageTypeList() {
        return this.messageType_;
    }

    public List<? extends DescriptorProtoOrBuilder> getMessageTypeOrBuilderList() {
        return this.messageType_;
    }

    public int getMessageTypeCount() {
        return this.messageType_.size();
    }

    public DescriptorProtos$DescriptorProto getMessageType(int i) {
        return (DescriptorProtos$DescriptorProto) this.messageType_.get(i);
    }

    public DescriptorProtoOrBuilder getMessageTypeOrBuilder(int i) {
        return (DescriptorProtoOrBuilder) this.messageType_.get(i);
    }

    public List<DescriptorProtos$EnumDescriptorProto> getEnumTypeList() {
        return this.enumType_;
    }

    public List<? extends EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
        return this.enumType_;
    }

    public int getEnumTypeCount() {
        return this.enumType_.size();
    }

    public DescriptorProtos$EnumDescriptorProto getEnumType(int i) {
        return (DescriptorProtos$EnumDescriptorProto) this.enumType_.get(i);
    }

    public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i) {
        return (EnumDescriptorProtoOrBuilder) this.enumType_.get(i);
    }

    public List<DescriptorProtos$ServiceDescriptorProto> getServiceList() {
        return this.service_;
    }

    public List<? extends ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList() {
        return this.service_;
    }

    public int getServiceCount() {
        return this.service_.size();
    }

    public DescriptorProtos$ServiceDescriptorProto getService(int i) {
        return (DescriptorProtos$ServiceDescriptorProto) this.service_.get(i);
    }

    public ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int i) {
        return (ServiceDescriptorProtoOrBuilder) this.service_.get(i);
    }

    public List<DescriptorProtos$FieldDescriptorProto> getExtensionList() {
        return this.extension_;
    }

    public List<? extends FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
        return this.extension_;
    }

    public int getExtensionCount() {
        return this.extension_.size();
    }

    public DescriptorProtos$FieldDescriptorProto getExtension(int i) {
        return (DescriptorProtos$FieldDescriptorProto) this.extension_.get(i);
    }

    public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i) {
        return (FieldDescriptorProtoOrBuilder) this.extension_.get(i);
    }

    public boolean hasOptions() {
        return (this.bitField0_ & 4) == 4;
    }

    public DescriptorProtos$FileOptions getOptions() {
        return this.options_ == null ? DescriptorProtos$FileOptions.getDefaultInstance() : this.options_;
    }

    public DescriptorProtos$FileOptionsOrBuilder getOptionsOrBuilder() {
        return this.options_ == null ? DescriptorProtos$FileOptions.getDefaultInstance() : this.options_;
    }

    public boolean hasSourceCodeInfo() {
        return (this.bitField0_ & 8) == 8;
    }

    public DescriptorProtos$SourceCodeInfo getSourceCodeInfo() {
        return this.sourceCodeInfo_ == null ? DescriptorProtos$SourceCodeInfo.getDefaultInstance() : this.sourceCodeInfo_;
    }

    public SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
        return this.sourceCodeInfo_ == null ? DescriptorProtos$SourceCodeInfo.getDefaultInstance() : this.sourceCodeInfo_;
    }

    public boolean hasSyntax() {
        return (this.bitField0_ & 16) == 16;
    }

    public String getSyntax() {
        Object obj = this.syntax_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String toStringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.syntax_ = toStringUtf8;
        }
        return toStringUtf8;
    }

    public ByteString getSyntaxBytes() {
        Object obj = this.syntax_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.syntax_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == (byte) 1) {
            return true;
        }
        if (b == (byte) 0) {
            return false;
        }
        int i = 0;
        while (i < getMessageTypeCount()) {
            if (getMessageType(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getEnumTypeCount()) {
            if (getEnumType(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getServiceCount()) {
            if (getService(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        i = 0;
        while (i < getExtensionCount()) {
            if (getExtension(i).isInitialized()) {
                i++;
            } else {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
        }
        if (!hasOptions() || getOptions().isInitialized()) {
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }
        this.memoizedIsInitialized = (byte) 0;
        return false;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i;
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if ((this.bitField0_ & 2) == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.package_);
        }
        for (int i3 = 0; i3 < this.dependency_.size(); i3++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.dependency_.getRaw(i3));
        }
        for (i = 0; i < this.messageType_.size(); i++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.messageType_.get(i));
        }
        for (i = 0; i < this.enumType_.size(); i++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.enumType_.get(i));
        }
        for (i = 0; i < this.service_.size(); i++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.service_.get(i));
        }
        for (i = 0; i < this.extension_.size(); i++) {
            codedOutputStream.writeMessage(7, (MessageLite) this.extension_.get(i));
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeMessage(8, getOptions());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeMessage(9, getSourceCodeInfo());
        }
        for (i = 0; i < this.publicDependency_.size(); i++) {
            codedOutputStream.writeInt32(10, ((Integer) this.publicDependency_.get(i)).intValue());
        }
        while (i2 < this.weakDependency_.size()) {
            codedOutputStream.writeInt32(11, ((Integer) this.weakDependency_.get(i2)).intValue());
            i2++;
        }
        if ((this.bitField0_ & 16) == 16) {
            GeneratedMessageV3.writeString(codedOutputStream, 12, this.syntax_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = this.memoizedSize;
        if (i2 != -1) {
            return i2;
        }
        int i3;
        if ((this.bitField0_ & 1) == 1) {
            i2 = GeneratedMessageV3.computeStringSize(1, this.name_) + 0;
        } else {
            i2 = 0;
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.package_);
        }
        int i4 = 0;
        for (i3 = 0; i3 < this.dependency_.size(); i3++) {
            i4 += computeStringSizeNoTag(this.dependency_.getRaw(i3));
        }
        i4 = (i2 + i4) + (getDependencyList().size() * 1);
        for (i3 = 0; i3 < this.messageType_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.messageType_.get(i3));
        }
        for (i3 = 0; i3 < this.enumType_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.enumType_.get(i3));
        }
        for (i3 = 0; i3 < this.service_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.service_.get(i3));
        }
        for (i3 = 0; i3 < this.extension_.size(); i3++) {
            i4 += CodedOutputStream.computeMessageSize(7, (MessageLite) this.extension_.get(i3));
        }
        if ((this.bitField0_ & 4) == 4) {
            i4 += CodedOutputStream.computeMessageSize(8, getOptions());
        }
        if ((this.bitField0_ & 8) == 8) {
            i4 += CodedOutputStream.computeMessageSize(9, getSourceCodeInfo());
        }
        int i5 = 0;
        for (i3 = 0; i3 < this.publicDependency_.size(); i3++) {
            i5 += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.publicDependency_.get(i3)).intValue());
        }
        i4 = (i4 + i5) + (getPublicDependencyList().size() * 1);
        i3 = 0;
        while (i < this.weakDependency_.size()) {
            i++;
            i3 = CodedOutputStream.computeInt32SizeNoTag(((Integer) this.weakDependency_.get(i)).intValue()) + i3;
        }
        i2 = (i4 + i3) + (getWeakDependencyList().size() * 1);
        if ((this.bitField0_ & 16) == 16) {
            i2 += GeneratedMessageV3.computeStringSize(12, this.syntax_);
        }
        i2 += this.unknownFields.getSerializedSize();
        this.memoizedSize = i2;
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DescriptorProtos$FileDescriptorProto)) {
            return super.equals(obj);
        }
        DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto = (DescriptorProtos$FileDescriptorProto) obj;
        boolean z = hasName() == descriptorProtos$FileDescriptorProto.hasName();
        if (hasName()) {
            z = z && getName().equals(descriptorProtos$FileDescriptorProto.getName());
        }
        if (z && hasPackage() == descriptorProtos$FileDescriptorProto.hasPackage()) {
            z = true;
        } else {
            z = false;
        }
        if (hasPackage()) {
            if (z && getPackage().equals(descriptorProtos$FileDescriptorProto.getPackage())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && getDependencyList().equals(descriptorProtos$FileDescriptorProto.getDependencyList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getPublicDependencyList().equals(descriptorProtos$FileDescriptorProto.getPublicDependencyList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getWeakDependencyList().equals(descriptorProtos$FileDescriptorProto.getWeakDependencyList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getMessageTypeList().equals(descriptorProtos$FileDescriptorProto.getMessageTypeList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getEnumTypeList().equals(descriptorProtos$FileDescriptorProto.getEnumTypeList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getServiceList().equals(descriptorProtos$FileDescriptorProto.getServiceList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && getExtensionList().equals(descriptorProtos$FileDescriptorProto.getExtensionList())) {
            z = true;
        } else {
            z = false;
        }
        if (z && hasOptions() == descriptorProtos$FileDescriptorProto.hasOptions()) {
            z = true;
        } else {
            z = false;
        }
        if (hasOptions()) {
            if (z && getOptions().equals(descriptorProtos$FileDescriptorProto.getOptions())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasSourceCodeInfo() == descriptorProtos$FileDescriptorProto.hasSourceCodeInfo()) {
            z = true;
        } else {
            z = false;
        }
        if (hasSourceCodeInfo()) {
            if (z && getSourceCodeInfo().equals(descriptorProtos$FileDescriptorProto.getSourceCodeInfo())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && hasSyntax() == descriptorProtos$FileDescriptorProto.hasSyntax()) {
            z = true;
        } else {
            z = false;
        }
        if (hasSyntax()) {
            if (z && getSyntax().equals(descriptorProtos$FileDescriptorProto.getSyntax())) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z && this.unknownFields.equals(descriptorProtos$FileDescriptorProto.unknownFields)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (hasName()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getName().hashCode();
        }
        if (hasPackage()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getPackage().hashCode();
        }
        if (getDependencyCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getDependencyList().hashCode();
        }
        if (getPublicDependencyCount() > 0) {
            hashCode = (((hashCode * 37) + 10) * 53) + getPublicDependencyList().hashCode();
        }
        if (getWeakDependencyCount() > 0) {
            hashCode = (((hashCode * 37) + 11) * 53) + getWeakDependencyList().hashCode();
        }
        if (getMessageTypeCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getMessageTypeList().hashCode();
        }
        if (getEnumTypeCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getEnumTypeList().hashCode();
        }
        if (getServiceCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getServiceList().hashCode();
        }
        if (getExtensionCount() > 0) {
            hashCode = (((hashCode * 37) + 7) * 53) + getExtensionList().hashCode();
        }
        if (hasOptions()) {
            hashCode = (((hashCode * 37) + 8) * 53) + getOptions().hashCode();
        }
        if (hasSourceCodeInfo()) {
            hashCode = (((hashCode * 37) + 9) * 53) + getSourceCodeInfo().hashCode();
        }
        if (hasSyntax()) {
            hashCode = (((hashCode * 37) + 12) * 53) + getSyntax().hashCode();
        }
        hashCode = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorProto) PARSER.parseFrom(byteBuffer);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorProto) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorProto) PARSER.parseFrom(byteString);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorProto) PARSER.parseFrom(bArr);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DescriptorProtos$FileDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FileDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DescriptorProtos$FileDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DescriptorProtos$FileDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileDescriptorProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DescriptorProtos$FileDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DescriptorProtos$FileDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DescriptorProtos$FileDescriptorProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public DescriptorProtos$FileDescriptorProto$Builder newBuilderForType() {
        return newBuilder();
    }

    public static DescriptorProtos$FileDescriptorProto$Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static DescriptorProtos$FileDescriptorProto$Builder newBuilder(DescriptorProtos$FileDescriptorProto descriptorProtos$FileDescriptorProto) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(descriptorProtos$FileDescriptorProto);
    }

    public DescriptorProtos$FileDescriptorProto$Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new DescriptorProtos$FileDescriptorProto$Builder(null);
        }
        return new DescriptorProtos$FileDescriptorProto$Builder(null).mergeFrom(this);
    }

    protected DescriptorProtos$FileDescriptorProto$Builder newBuilderForType(BuilderParent builderParent) {
        return new DescriptorProtos$FileDescriptorProto$Builder(builderParent, null);
    }

    public static DescriptorProtos$FileDescriptorProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DescriptorProtos$FileDescriptorProto> parser() {
        return PARSER;
    }

    public Parser<DescriptorProtos$FileDescriptorProto> getParserForType() {
        return PARSER;
    }

    public DescriptorProtos$FileDescriptorProto getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
